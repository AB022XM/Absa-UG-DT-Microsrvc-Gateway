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
import ug.co.absa.microsrvc.gateway.domain.DTransactionHistory;
import ug.co.absa.microsrvc.gateway.repository.DTransactionHistoryRepository;
import ug.co.absa.microsrvc.gateway.repository.search.DTransactionHistorySearchRepository;
import ug.co.absa.microsrvc.gateway.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link ug.co.absa.microsrvc.gateway.domain.DTransactionHistory}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class DTransactionHistoryResource {

    private final Logger log = LoggerFactory.getLogger(DTransactionHistoryResource.class);

    private static final String ENTITY_NAME = "dTransactionHistory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DTransactionHistoryRepository dTransactionHistoryRepository;

    private final DTransactionHistorySearchRepository dTransactionHistorySearchRepository;

    public DTransactionHistoryResource(
        DTransactionHistoryRepository dTransactionHistoryRepository,
        DTransactionHistorySearchRepository dTransactionHistorySearchRepository
    ) {
        this.dTransactionHistoryRepository = dTransactionHistoryRepository;
        this.dTransactionHistorySearchRepository = dTransactionHistorySearchRepository;
    }

    /**
     * {@code POST  /d-transaction-histories} : Create a new dTransactionHistory.
     *
     * @param dTransactionHistory the dTransactionHistory to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dTransactionHistory, or with status {@code 400 (Bad Request)} if the dTransactionHistory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/d-transaction-histories")
    public Mono<ResponseEntity<DTransactionHistory>> createDTransactionHistory(@Valid @RequestBody DTransactionHistory dTransactionHistory)
        throws URISyntaxException {
        log.debug("REST request to save DTransactionHistory : {}", dTransactionHistory);
        if (dTransactionHistory.getId() != null) {
            throw new BadRequestAlertException("A new dTransactionHistory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return dTransactionHistoryRepository
            .save(dTransactionHistory)
            .flatMap(dTransactionHistorySearchRepository::save)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/d-transaction-histories/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /d-transaction-histories/:id} : Updates an existing dTransactionHistory.
     *
     * @param id the id of the dTransactionHistory to save.
     * @param dTransactionHistory the dTransactionHistory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dTransactionHistory,
     * or with status {@code 400 (Bad Request)} if the dTransactionHistory is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dTransactionHistory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/d-transaction-histories/{id}")
    public Mono<ResponseEntity<DTransactionHistory>> updateDTransactionHistory(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody DTransactionHistory dTransactionHistory
    ) throws URISyntaxException {
        log.debug("REST request to update DTransactionHistory : {}, {}", id, dTransactionHistory);
        if (dTransactionHistory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dTransactionHistory.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return dTransactionHistoryRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return dTransactionHistoryRepository
                    .save(dTransactionHistory)
                    .flatMap(dTransactionHistorySearchRepository::save)
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
     * {@code PATCH  /d-transaction-histories/:id} : Partial updates given fields of an existing dTransactionHistory, field will ignore if it is null
     *
     * @param id the id of the dTransactionHistory to save.
     * @param dTransactionHistory the dTransactionHistory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dTransactionHistory,
     * or with status {@code 400 (Bad Request)} if the dTransactionHistory is not valid,
     * or with status {@code 404 (Not Found)} if the dTransactionHistory is not found,
     * or with status {@code 500 (Internal Server Error)} if the dTransactionHistory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/d-transaction-histories/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<DTransactionHistory>> partialUpdateDTransactionHistory(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody DTransactionHistory dTransactionHistory
    ) throws URISyntaxException {
        log.debug("REST request to partial update DTransactionHistory partially : {}, {}", id, dTransactionHistory);
        if (dTransactionHistory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dTransactionHistory.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return dTransactionHistoryRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<DTransactionHistory> result = dTransactionHistoryRepository
                    .findById(dTransactionHistory.getId())
                    .map(existingDTransactionHistory -> {
                        if (dTransactionHistory.getRecordId() != null) {
                            existingDTransactionHistory.setRecordId(dTransactionHistory.getRecordId());
                        }
                        if (dTransactionHistory.getTransferId() != null) {
                            existingDTransactionHistory.setTransferId(dTransactionHistory.getTransferId());
                        }
                        if (dTransactionHistory.getProductCode() != null) {
                            existingDTransactionHistory.setProductCode(dTransactionHistory.getProductCode());
                        }
                        if (dTransactionHistory.getPaymentChannelCode() != null) {
                            existingDTransactionHistory.setPaymentChannelCode(dTransactionHistory.getPaymentChannelCode());
                        }
                        if (dTransactionHistory.getDebitAccountNumber() != null) {
                            existingDTransactionHistory.setDebitAccountNumber(dTransactionHistory.getDebitAccountNumber());
                        }
                        if (dTransactionHistory.getCreditAccountNumber() != null) {
                            existingDTransactionHistory.setCreditAccountNumber(dTransactionHistory.getCreditAccountNumber());
                        }
                        if (dTransactionHistory.getDebitAmount() != null) {
                            existingDTransactionHistory.setDebitAmount(dTransactionHistory.getDebitAmount());
                        }
                        if (dTransactionHistory.getCreditAmount() != null) {
                            existingDTransactionHistory.setCreditAmount(dTransactionHistory.getCreditAmount());
                        }
                        if (dTransactionHistory.getTransactionAmount() != null) {
                            existingDTransactionHistory.setTransactionAmount(dTransactionHistory.getTransactionAmount());
                        }
                        if (dTransactionHistory.getDebitDate() != null) {
                            existingDTransactionHistory.setDebitDate(dTransactionHistory.getDebitDate());
                        }
                        if (dTransactionHistory.getCreditDate() != null) {
                            existingDTransactionHistory.setCreditDate(dTransactionHistory.getCreditDate());
                        }
                        if (dTransactionHistory.getStatus() != null) {
                            existingDTransactionHistory.setStatus(dTransactionHistory.getStatus());
                        }
                        if (dTransactionHistory.getSettlementDate() != null) {
                            existingDTransactionHistory.setSettlementDate(dTransactionHistory.getSettlementDate());
                        }
                        if (dTransactionHistory.getDebitCurrency() != null) {
                            existingDTransactionHistory.setDebitCurrency(dTransactionHistory.getDebitCurrency());
                        }
                        if (dTransactionHistory.getTimestamp() != null) {
                            existingDTransactionHistory.setTimestamp(dTransactionHistory.getTimestamp());
                        }
                        if (dTransactionHistory.getPhoneNumber() != null) {
                            existingDTransactionHistory.setPhoneNumber(dTransactionHistory.getPhoneNumber());
                        }
                        if (dTransactionHistory.getEmail() != null) {
                            existingDTransactionHistory.setEmail(dTransactionHistory.getEmail());
                        }
                        if (dTransactionHistory.getPayerName() != null) {
                            existingDTransactionHistory.setPayerName(dTransactionHistory.getPayerName());
                        }
                        if (dTransactionHistory.getPayerAddress() != null) {
                            existingDTransactionHistory.setPayerAddress(dTransactionHistory.getPayerAddress());
                        }
                        if (dTransactionHistory.getPayerEmail() != null) {
                            existingDTransactionHistory.setPayerEmail(dTransactionHistory.getPayerEmail());
                        }
                        if (dTransactionHistory.getPayerPhoneNumber() != null) {
                            existingDTransactionHistory.setPayerPhoneNumber(dTransactionHistory.getPayerPhoneNumber());
                        }
                        if (dTransactionHistory.getPayerNarration() != null) {
                            existingDTransactionHistory.setPayerNarration(dTransactionHistory.getPayerNarration());
                        }
                        if (dTransactionHistory.getPayerBranchId() != null) {
                            existingDTransactionHistory.setPayerBranchId(dTransactionHistory.getPayerBranchId());
                        }
                        if (dTransactionHistory.getBeneficiaryName() != null) {
                            existingDTransactionHistory.setBeneficiaryName(dTransactionHistory.getBeneficiaryName());
                        }
                        if (dTransactionHistory.getBeneficiaryAccount() != null) {
                            existingDTransactionHistory.setBeneficiaryAccount(dTransactionHistory.getBeneficiaryAccount());
                        }
                        if (dTransactionHistory.getBeneficiaryBranchId() != null) {
                            existingDTransactionHistory.setBeneficiaryBranchId(dTransactionHistory.getBeneficiaryBranchId());
                        }
                        if (dTransactionHistory.getBeneficiaryPhoneNumber() != null) {
                            existingDTransactionHistory.setBeneficiaryPhoneNumber(dTransactionHistory.getBeneficiaryPhoneNumber());
                        }
                        if (dTransactionHistory.getTranStatus() != null) {
                            existingDTransactionHistory.setTranStatus(dTransactionHistory.getTranStatus());
                        }
                        if (dTransactionHistory.getNarration1() != null) {
                            existingDTransactionHistory.setNarration1(dTransactionHistory.getNarration1());
                        }
                        if (dTransactionHistory.getNarration2() != null) {
                            existingDTransactionHistory.setNarration2(dTransactionHistory.getNarration2());
                        }
                        if (dTransactionHistory.getNarration3() != null) {
                            existingDTransactionHistory.setNarration3(dTransactionHistory.getNarration3());
                        }
                        if (dTransactionHistory.getModeOfPayment() != null) {
                            existingDTransactionHistory.setModeOfPayment(dTransactionHistory.getModeOfPayment());
                        }
                        if (dTransactionHistory.getRetries() != null) {
                            existingDTransactionHistory.setRetries(dTransactionHistory.getRetries());
                        }
                        if (dTransactionHistory.getPosted() != null) {
                            existingDTransactionHistory.setPosted(dTransactionHistory.getPosted());
                        }
                        if (dTransactionHistory.getApiId() != null) {
                            existingDTransactionHistory.setApiId(dTransactionHistory.getApiId());
                        }
                        if (dTransactionHistory.getApiVersion() != null) {
                            existingDTransactionHistory.setApiVersion(dTransactionHistory.getApiVersion());
                        }
                        if (dTransactionHistory.getPostingApi() != null) {
                            existingDTransactionHistory.setPostingApi(dTransactionHistory.getPostingApi());
                        }
                        if (dTransactionHistory.getIsPosted() != null) {
                            existingDTransactionHistory.setIsPosted(dTransactionHistory.getIsPosted());
                        }
                        if (dTransactionHistory.getPostedBy() != null) {
                            existingDTransactionHistory.setPostedBy(dTransactionHistory.getPostedBy());
                        }
                        if (dTransactionHistory.getPostedDate() != null) {
                            existingDTransactionHistory.setPostedDate(dTransactionHistory.getPostedDate());
                        }
                        if (dTransactionHistory.getInternalErrorCode() != null) {
                            existingDTransactionHistory.setInternalErrorCode(dTransactionHistory.getInternalErrorCode());
                        }
                        if (dTransactionHistory.getExternalErrorCode() != null) {
                            existingDTransactionHistory.setExternalErrorCode(dTransactionHistory.getExternalErrorCode());
                        }
                        if (dTransactionHistory.getTranFreeField1() != null) {
                            existingDTransactionHistory.setTranFreeField1(dTransactionHistory.getTranFreeField1());
                        }
                        if (dTransactionHistory.getTranFreeField3() != null) {
                            existingDTransactionHistory.setTranFreeField3(dTransactionHistory.getTranFreeField3());
                        }
                        if (dTransactionHistory.getTranFreeField4() != null) {
                            existingDTransactionHistory.setTranFreeField4(dTransactionHistory.getTranFreeField4());
                        }
                        if (dTransactionHistory.getTranFreeField5() != null) {
                            existingDTransactionHistory.setTranFreeField5(dTransactionHistory.getTranFreeField5());
                        }
                        if (dTransactionHistory.getTranFreeField6() != null) {
                            existingDTransactionHistory.setTranFreeField6(dTransactionHistory.getTranFreeField6());
                        }
                        if (dTransactionHistory.getTranFreeField7() != null) {
                            existingDTransactionHistory.setTranFreeField7(dTransactionHistory.getTranFreeField7());
                        }
                        if (dTransactionHistory.getTranFreeField8() != null) {
                            existingDTransactionHistory.setTranFreeField8(dTransactionHistory.getTranFreeField8());
                        }
                        if (dTransactionHistory.getTranFreeField9() != null) {
                            existingDTransactionHistory.setTranFreeField9(dTransactionHistory.getTranFreeField9());
                        }
                        if (dTransactionHistory.getTranFreeField10() != null) {
                            existingDTransactionHistory.setTranFreeField10(dTransactionHistory.getTranFreeField10());
                        }
                        if (dTransactionHistory.getTranFreeField11() != null) {
                            existingDTransactionHistory.setTranFreeField11(dTransactionHistory.getTranFreeField11());
                        }
                        if (dTransactionHistory.getTranFreeField12() != null) {
                            existingDTransactionHistory.setTranFreeField12(dTransactionHistory.getTranFreeField12());
                        }
                        if (dTransactionHistory.getCreatedAt() != null) {
                            existingDTransactionHistory.setCreatedAt(dTransactionHistory.getCreatedAt());
                        }
                        if (dTransactionHistory.getCreatedBy() != null) {
                            existingDTransactionHistory.setCreatedBy(dTransactionHistory.getCreatedBy());
                        }
                        if (dTransactionHistory.getUpdatedAt() != null) {
                            existingDTransactionHistory.setUpdatedAt(dTransactionHistory.getUpdatedAt());
                        }
                        if (dTransactionHistory.getUpdatedBy() != null) {
                            existingDTransactionHistory.setUpdatedBy(dTransactionHistory.getUpdatedBy());
                        }

                        return existingDTransactionHistory;
                    })
                    .flatMap(dTransactionHistoryRepository::save)
                    .flatMap(savedDTransactionHistory -> {
                        dTransactionHistorySearchRepository.save(savedDTransactionHistory);

                        return Mono.just(savedDTransactionHistory);
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
     * {@code GET  /d-transaction-histories} : get all the dTransactionHistories.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dTransactionHistories in body.
     */
    @GetMapping("/d-transaction-histories")
    public Mono<List<DTransactionHistory>> getAllDTransactionHistories() {
        log.debug("REST request to get all DTransactionHistories");
        return dTransactionHistoryRepository.findAll().collectList();
    }

    /**
     * {@code GET  /d-transaction-histories} : get all the dTransactionHistories as a stream.
     * @return the {@link Flux} of dTransactionHistories.
     */
    @GetMapping(value = "/d-transaction-histories", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<DTransactionHistory> getAllDTransactionHistoriesAsStream() {
        log.debug("REST request to get all DTransactionHistories as a stream");
        return dTransactionHistoryRepository.findAll();
    }

    /**
     * {@code GET  /d-transaction-histories/:id} : get the "id" dTransactionHistory.
     *
     * @param id the id of the dTransactionHistory to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dTransactionHistory, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/d-transaction-histories/{id}")
    public Mono<ResponseEntity<DTransactionHistory>> getDTransactionHistory(@PathVariable Long id) {
        log.debug("REST request to get DTransactionHistory : {}", id);
        Mono<DTransactionHistory> dTransactionHistory = dTransactionHistoryRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(dTransactionHistory);
    }

    /**
     * {@code DELETE  /d-transaction-histories/:id} : delete the "id" dTransactionHistory.
     *
     * @param id the id of the dTransactionHistory to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/d-transaction-histories/{id}")
    public Mono<ResponseEntity<Void>> deleteDTransactionHistory(@PathVariable Long id) {
        log.debug("REST request to delete DTransactionHistory : {}", id);
        return dTransactionHistoryRepository
            .deleteById(id)
            .then(dTransactionHistorySearchRepository.deleteById(id))
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
     * {@code SEARCH  /_search/d-transaction-histories?query=:query} : search for the dTransactionHistory corresponding
     * to the query.
     *
     * @param query the query of the dTransactionHistory search.
     * @return the result of the search.
     */
    @GetMapping("/_search/d-transaction-histories")
    public Mono<List<DTransactionHistory>> searchDTransactionHistories(@RequestParam String query) {
        log.debug("REST request to search DTransactionHistories for query {}", query);
        return dTransactionHistorySearchRepository.search(query).collectList();
    }
}
