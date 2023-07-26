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
import ug.co.absa.microsrvc.gateway.domain.BillerAccount;
import ug.co.absa.microsrvc.gateway.repository.BillerAccountRepository;
import ug.co.absa.microsrvc.gateway.repository.search.BillerAccountSearchRepository;
import ug.co.absa.microsrvc.gateway.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link ug.co.absa.microsrvc.gateway.domain.BillerAccount}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class BillerAccountResource {

    private final Logger log = LoggerFactory.getLogger(BillerAccountResource.class);

    private static final String ENTITY_NAME = "billerAccount";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BillerAccountRepository billerAccountRepository;

    private final BillerAccountSearchRepository billerAccountSearchRepository;

    public BillerAccountResource(
        BillerAccountRepository billerAccountRepository,
        BillerAccountSearchRepository billerAccountSearchRepository
    ) {
        this.billerAccountRepository = billerAccountRepository;
        this.billerAccountSearchRepository = billerAccountSearchRepository;
    }

    /**
     * {@code POST  /biller-accounts} : Create a new billerAccount.
     *
     * @param billerAccount the billerAccount to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new billerAccount, or with status {@code 400 (Bad Request)} if the billerAccount has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/biller-accounts")
    public Mono<ResponseEntity<BillerAccount>> createBillerAccount(@Valid @RequestBody BillerAccount billerAccount)
        throws URISyntaxException {
        log.debug("REST request to save BillerAccount : {}", billerAccount);
        if (billerAccount.getId() != null) {
            throw new BadRequestAlertException("A new billerAccount cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return billerAccountRepository
            .save(billerAccount)
            .flatMap(billerAccountSearchRepository::save)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/biller-accounts/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /biller-accounts/:id} : Updates an existing billerAccount.
     *
     * @param id the id of the billerAccount to save.
     * @param billerAccount the billerAccount to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated billerAccount,
     * or with status {@code 400 (Bad Request)} if the billerAccount is not valid,
     * or with status {@code 500 (Internal Server Error)} if the billerAccount couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/biller-accounts/{id}")
    public Mono<ResponseEntity<BillerAccount>> updateBillerAccount(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody BillerAccount billerAccount
    ) throws URISyntaxException {
        log.debug("REST request to update BillerAccount : {}, {}", id, billerAccount);
        if (billerAccount.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, billerAccount.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return billerAccountRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return billerAccountRepository
                    .save(billerAccount)
                    .flatMap(billerAccountSearchRepository::save)
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
     * {@code PATCH  /biller-accounts/:id} : Partial updates given fields of an existing billerAccount, field will ignore if it is null
     *
     * @param id the id of the billerAccount to save.
     * @param billerAccount the billerAccount to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated billerAccount,
     * or with status {@code 400 (Bad Request)} if the billerAccount is not valid,
     * or with status {@code 404 (Not Found)} if the billerAccount is not found,
     * or with status {@code 500 (Internal Server Error)} if the billerAccount couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/biller-accounts/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<BillerAccount>> partialUpdateBillerAccount(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody BillerAccount billerAccount
    ) throws URISyntaxException {
        log.debug("REST request to partial update BillerAccount partially : {}, {}", id, billerAccount);
        if (billerAccount.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, billerAccount.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return billerAccountRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<BillerAccount> result = billerAccountRepository
                    .findById(billerAccount.getId())
                    .map(existingBillerAccount -> {
                        if (billerAccount.getAbsaTranRef() != null) {
                            existingBillerAccount.setAbsaTranRef(billerAccount.getAbsaTranRef());
                        }
                        if (billerAccount.getRecordId() != null) {
                            existingBillerAccount.setRecordId(billerAccount.getRecordId());
                        }
                        if (billerAccount.getBillerId() != null) {
                            existingBillerAccount.setBillerId(billerAccount.getBillerId());
                        }
                        if (billerAccount.getBillerName() != null) {
                            existingBillerAccount.setBillerName(billerAccount.getBillerName());
                        }
                        if (billerAccount.getBillerAccNumber() != null) {
                            existingBillerAccount.setBillerAccNumber(billerAccount.getBillerAccNumber());
                        }
                        if (billerAccount.getBillerAccountDescription() != null) {
                            existingBillerAccount.setBillerAccountDescription(billerAccount.getBillerAccountDescription());
                        }
                        if (billerAccount.getTimestamp() != null) {
                            existingBillerAccount.setTimestamp(billerAccount.getTimestamp());
                        }
                        if (billerAccount.getBillerFreeField1() != null) {
                            existingBillerAccount.setBillerFreeField1(billerAccount.getBillerFreeField1());
                        }
                        if (billerAccount.getBillerFreeField2() != null) {
                            existingBillerAccount.setBillerFreeField2(billerAccount.getBillerFreeField2());
                        }
                        if (billerAccount.getBillerFreeField3() != null) {
                            existingBillerAccount.setBillerFreeField3(billerAccount.getBillerFreeField3());
                        }
                        if (billerAccount.getBillerFreeField4() != null) {
                            existingBillerAccount.setBillerFreeField4(billerAccount.getBillerFreeField4());
                        }
                        if (billerAccount.getBillerFreeField5() != null) {
                            existingBillerAccount.setBillerFreeField5(billerAccount.getBillerFreeField5());
                        }
                        if (billerAccount.getBillerFreeField6() != null) {
                            existingBillerAccount.setBillerFreeField6(billerAccount.getBillerFreeField6());
                        }
                        if (billerAccount.getBillerFreeField7() != null) {
                            existingBillerAccount.setBillerFreeField7(billerAccount.getBillerFreeField7());
                        }
                        if (billerAccount.getBillerFreeField8() != null) {
                            existingBillerAccount.setBillerFreeField8(billerAccount.getBillerFreeField8());
                        }
                        if (billerAccount.getBillerFreeField9() != null) {
                            existingBillerAccount.setBillerFreeField9(billerAccount.getBillerFreeField9());
                        }
                        if (billerAccount.getBillerFreeField10() != null) {
                            existingBillerAccount.setBillerFreeField10(billerAccount.getBillerFreeField10());
                        }
                        if (billerAccount.getBillerFreeField11() != null) {
                            existingBillerAccount.setBillerFreeField11(billerAccount.getBillerFreeField11());
                        }
                        if (billerAccount.getBillerFreeField12() != null) {
                            existingBillerAccount.setBillerFreeField12(billerAccount.getBillerFreeField12());
                        }
                        if (billerAccount.getBillerFreeField13() != null) {
                            existingBillerAccount.setBillerFreeField13(billerAccount.getBillerFreeField13());
                        }
                        if (billerAccount.getDelflg() != null) {
                            existingBillerAccount.setDelflg(billerAccount.getDelflg());
                        }
                        if (billerAccount.getCreatedAt() != null) {
                            existingBillerAccount.setCreatedAt(billerAccount.getCreatedAt());
                        }
                        if (billerAccount.getCreatedBy() != null) {
                            existingBillerAccount.setCreatedBy(billerAccount.getCreatedBy());
                        }
                        if (billerAccount.getUpdatedAt() != null) {
                            existingBillerAccount.setUpdatedAt(billerAccount.getUpdatedAt());
                        }
                        if (billerAccount.getUpdatedBy() != null) {
                            existingBillerAccount.setUpdatedBy(billerAccount.getUpdatedBy());
                        }

                        return existingBillerAccount;
                    })
                    .flatMap(billerAccountRepository::save)
                    .flatMap(savedBillerAccount -> {
                        billerAccountSearchRepository.save(savedBillerAccount);

                        return Mono.just(savedBillerAccount);
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
     * {@code GET  /biller-accounts} : get all the billerAccounts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of billerAccounts in body.
     */
    @GetMapping("/biller-accounts")
    public Mono<List<BillerAccount>> getAllBillerAccounts() {
        log.debug("REST request to get all BillerAccounts");
        return billerAccountRepository.findAll().collectList();
    }

    /**
     * {@code GET  /biller-accounts} : get all the billerAccounts as a stream.
     * @return the {@link Flux} of billerAccounts.
     */
    @GetMapping(value = "/biller-accounts", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<BillerAccount> getAllBillerAccountsAsStream() {
        log.debug("REST request to get all BillerAccounts as a stream");
        return billerAccountRepository.findAll();
    }

    /**
     * {@code GET  /biller-accounts/:id} : get the "id" billerAccount.
     *
     * @param id the id of the billerAccount to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the billerAccount, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/biller-accounts/{id}")
    public Mono<ResponseEntity<BillerAccount>> getBillerAccount(@PathVariable Long id) {
        log.debug("REST request to get BillerAccount : {}", id);
        Mono<BillerAccount> billerAccount = billerAccountRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(billerAccount);
    }

    /**
     * {@code DELETE  /biller-accounts/:id} : delete the "id" billerAccount.
     *
     * @param id the id of the billerAccount to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/biller-accounts/{id}")
    public Mono<ResponseEntity<Void>> deleteBillerAccount(@PathVariable Long id) {
        log.debug("REST request to delete BillerAccount : {}", id);
        return billerAccountRepository
            .deleteById(id)
            .then(billerAccountSearchRepository.deleteById(id))
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
     * {@code SEARCH  /_search/biller-accounts?query=:query} : search for the billerAccount corresponding
     * to the query.
     *
     * @param query the query of the billerAccount search.
     * @return the result of the search.
     */
    @GetMapping("/_search/biller-accounts")
    public Mono<List<BillerAccount>> searchBillerAccounts(@RequestParam String query) {
        log.debug("REST request to search BillerAccounts for query {}", query);
        return billerAccountSearchRepository.search(query).collectList();
    }
}
