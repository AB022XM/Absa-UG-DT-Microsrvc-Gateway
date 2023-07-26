package ug.co.absa.microsrvc.gateway.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import tech.jhipster.web.util.HeaderUtil;
import tech.jhipster.web.util.reactive.ResponseUtil;
import ug.co.absa.microsrvc.gateway.domain.Bank;
import ug.co.absa.microsrvc.gateway.repository.BankRepository;
import ug.co.absa.microsrvc.gateway.repository.search.BankSearchRepository;
import ug.co.absa.microsrvc.gateway.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link ug.co.absa.microsrvc.gateway.domain.Bank}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class BankResource {

    private final Logger log = LoggerFactory.getLogger(BankResource.class);

    private static final String ENTITY_NAME = "bank";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BankRepository bankRepository;

    private final BankSearchRepository bankSearchRepository;

    public BankResource(BankRepository bankRepository, BankSearchRepository bankSearchRepository) {
        this.bankRepository = bankRepository;
        this.bankSearchRepository = bankSearchRepository;
    }

    /**
     * {@code POST  /banks} : Create a new bank.
     *
     * @param bank the bank to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new bank, or with status {@code 400 (Bad Request)} if the bank has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/banks")
    public Mono<ResponseEntity<Bank>> createBank(@Valid @RequestBody Bank bank) throws URISyntaxException {
        log.debug("REST request to save Bank : {}", bank);
        if (bank.getId() != null) {
            throw new BadRequestAlertException("A new bank cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return bankRepository
            .save(bank)
            .flatMap(bankSearchRepository::save)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/banks/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /banks/:id} : Updates an existing bank.
     *
     * @param id the id of the bank to save.
     * @param bank the bank to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bank,
     * or with status {@code 400 (Bad Request)} if the bank is not valid,
     * or with status {@code 500 (Internal Server Error)} if the bank couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/banks/{id}")
    public Mono<ResponseEntity<Bank>> updateBank(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Bank bank
    ) throws URISyntaxException {
        log.debug("REST request to update Bank : {}, {}", id, bank);
        if (bank.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bank.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return bankRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return bankRepository
                    .save(bank)
                    .flatMap(bankSearchRepository::save)
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(result ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                            .body(result)
                    );
            });
    }

    /**
     * {@code PATCH  /banks/:id} : Partial updates given fields of an existing bank, field will ignore if it is null
     *
     * @param id the id of the bank to save.
     * @param bank the bank to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated bank,
     * or with status {@code 400 (Bad Request)} if the bank is not valid,
     * or with status {@code 404 (Not Found)} if the bank is not found,
     * or with status {@code 500 (Internal Server Error)} if the bank couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/banks/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<Bank>> partialUpdateBank(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Bank bank
    ) throws URISyntaxException {
        log.debug("REST request to partial update Bank partially : {}, {}", id, bank);
        if (bank.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, bank.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return bankRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<Bank> result = bankRepository
                    .findById(bank.getId())
                    .map(existingBank -> {
                        if (bank.getAbsaTranRef() != null) {
                            existingBank.setAbsaTranRef(bank.getAbsaTranRef());
                        }
                        if (bank.getBillerId() != null) {
                            existingBank.setBillerId(bank.getBillerId());
                        }
                        if (bank.getPaymentItemCode() != null) {
                            existingBank.setPaymentItemCode(bank.getPaymentItemCode());
                        }
                        if (bank.getDtDTransactionId() != null) {
                            existingBank.setDtDTransactionId(bank.getDtDTransactionId());
                        }
                        if (bank.getAmolDTransactionId() != null) {
                            existingBank.setAmolDTransactionId(bank.getAmolDTransactionId());
                        }
                        if (bank.getBankName() != null) {
                            existingBank.setBankName(bank.getBankName());
                        }
                        if (bank.getBankSwiftCode() != null) {
                            existingBank.setBankSwiftCode(bank.getBankSwiftCode());
                        }
                        if (bank.getBankBranchId() != null) {
                            existingBank.setBankBranchId(bank.getBankBranchId());
                        }
                        if (bank.getBankPhoneNumber() != null) {
                            existingBank.setBankPhoneNumber(bank.getBankPhoneNumber());
                        }
                        if (bank.getBankEmail() != null) {
                            existingBank.setBankEmail(bank.getBankEmail());
                        }
                        if (bank.getBankFreeField1() != null) {
                            existingBank.setBankFreeField1(bank.getBankFreeField1());
                        }
                        if (bank.getBankFreeField3() != null) {
                            existingBank.setBankFreeField3(bank.getBankFreeField3());
                        }
                        if (bank.getBankFreeField4() != null) {
                            existingBank.setBankFreeField4(bank.getBankFreeField4());
                        }
                        if (bank.getBankFreeField5() != null) {
                            existingBank.setBankFreeField5(bank.getBankFreeField5());
                        }
                        if (bank.getBankFreeField6() != null) {
                            existingBank.setBankFreeField6(bank.getBankFreeField6());
                        }
                        if (bank.getBankFreeField7() != null) {
                            existingBank.setBankFreeField7(bank.getBankFreeField7());
                        }
                        if (bank.getBankFreeField8() != null) {
                            existingBank.setBankFreeField8(bank.getBankFreeField8());
                        }
                        if (bank.getBankFreeField9() != null) {
                            existingBank.setBankFreeField9(bank.getBankFreeField9());
                        }
                        if (bank.getBankFreeField10() != null) {
                            existingBank.setBankFreeField10(bank.getBankFreeField10());
                        }
                        if (bank.getBankFreeField11() != null) {
                            existingBank.setBankFreeField11(bank.getBankFreeField11());
                        }
                        if (bank.getBankFreeField12() != null) {
                            existingBank.setBankFreeField12(bank.getBankFreeField12());
                        }
                        if (bank.getBankFreeField13() != null) {
                            existingBank.setBankFreeField13(bank.getBankFreeField13());
                        }
                        if (bank.getBankFreeField14() != null) {
                            existingBank.setBankFreeField14(bank.getBankFreeField14());
                        }
                        if (bank.getBankFreeField15() != null) {
                            existingBank.setBankFreeField15(bank.getBankFreeField15());
                        }
                        if (bank.getBankFreeField16() != null) {
                            existingBank.setBankFreeField16(bank.getBankFreeField16());
                        }
                        if (bank.getBankFreeField17() != null) {
                            existingBank.setBankFreeField17(bank.getBankFreeField17());
                        }
                        if (bank.getBankFreeField18() != null) {
                            existingBank.setBankFreeField18(bank.getBankFreeField18());
                        }
                        if (bank.getBankFreeField19() != null) {
                            existingBank.setBankFreeField19(bank.getBankFreeField19());
                        }
                        if (bank.getBankFreeField20() != null) {
                            existingBank.setBankFreeField20(bank.getBankFreeField20());
                        }
                        if (bank.getBankFreeField21() != null) {
                            existingBank.setBankFreeField21(bank.getBankFreeField21());
                        }
                        if (bank.getBankFreeField22() != null) {
                            existingBank.setBankFreeField22(bank.getBankFreeField22());
                        }
                        if (bank.getBankFreeField23() != null) {
                            existingBank.setBankFreeField23(bank.getBankFreeField23());
                        }
                        if (bank.getBankFreeField24() != null) {
                            existingBank.setBankFreeField24(bank.getBankFreeField24());
                        }
                        if (bank.getCreatedAt() != null) {
                            existingBank.setCreatedAt(bank.getCreatedAt());
                        }
                        if (bank.getCreatedBy() != null) {
                            existingBank.setCreatedBy(bank.getCreatedBy());
                        }
                        if (bank.getUpdatedAt() != null) {
                            existingBank.setUpdatedAt(bank.getUpdatedAt());
                        }
                        if (bank.getUpdatedBy() != null) {
                            existingBank.setUpdatedBy(bank.getUpdatedBy());
                        }
                        if (bank.getDelflg() != null) {
                            existingBank.setDelflg(bank.getDelflg());
                        }
                        if (bank.getStatus() != null) {
                            existingBank.setStatus(bank.getStatus());
                        }

                        return existingBank;
                    })
                    .flatMap(bankRepository::save)
                    .flatMap(savedBank -> {
                        bankSearchRepository.save(savedBank);

                        return Mono.just(savedBank);
                    });

                return result
                    .switchIfEmpty(Mono.error(new ResponseStatusException(HttpStatus.NOT_FOUND)))
                    .map(res ->
                        ResponseEntity
                            .ok()
                            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, res.getId().toString()))
                            .body(res)
                    );
            });
    }

    /**
     * {@code GET  /banks} : get all the banks.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of banks in body.
     */
    @GetMapping("/banks")
    public Mono<List<Bank>> getAllBanks() {
        log.debug("REST request to get all Banks");
        return bankRepository.findAll().collectList();
    }

    /**
     * {@code GET  /banks} : get all the banks as a stream.
     * @return the {@link Flux} of banks.
     */
    @GetMapping(value = "/banks", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<Bank> getAllBanksAsStream() {
        log.debug("REST request to get all Banks as a stream");
        return bankRepository.findAll();
    }

    /**
     * {@code GET  /banks/:id} : get the "id" bank.
     *
     * @param id the id of the bank to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the bank, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/banks/{id}")
    public Mono<ResponseEntity<Bank>> getBank(@PathVariable Long id) {
        log.debug("REST request to get Bank : {}", id);
        Mono<Bank> bank = bankRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(bank);
    }

    /**
     * {@code DELETE  /banks/:id} : delete the "id" bank.
     *
     * @param id the id of the bank to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/banks/{id}")
    public Mono<ResponseEntity<Void>> deleteBank(@PathVariable Long id) {
        log.debug("REST request to delete Bank : {}", id);
        return bankRepository
            .deleteById(id)
            .then(bankSearchRepository.deleteById(id))
            .then(
                Mono.just(
                    ResponseEntity
                        .noContent()
                        .headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString()))
                        .build()
                )
            );
    }

    /**
     * {@code SEARCH  /_search/banks?query=:query} : search for the bank corresponding
     * to the query.
     *
     * @param query the query of the bank search.
     * @return the result of the search.
     */
    @GetMapping("/_search/banks")
    public Mono<List<Bank>> searchBanks(@RequestParam String query) {
        log.debug("REST request to search Banks for query {}", query);
        return bankSearchRepository.search(query).collectList();
    }
}
