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
import ug.co.absa.microsrvc.gateway.domain.DTransaction;
import ug.co.absa.microsrvc.gateway.repository.DTransactionRepository;
import ug.co.absa.microsrvc.gateway.repository.search.DTransactionSearchRepository;
import ug.co.absa.microsrvc.gateway.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link ug.co.absa.microsrvc.gateway.domain.DTransaction}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class DTransactionResource {

    private final Logger log = LoggerFactory.getLogger(DTransactionResource.class);

    private static final String ENTITY_NAME = "dTransaction";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DTransactionRepository dTransactionRepository;

    private final DTransactionSearchRepository dTransactionSearchRepository;

    public DTransactionResource(DTransactionRepository dTransactionRepository, DTransactionSearchRepository dTransactionSearchRepository) {
        this.dTransactionRepository = dTransactionRepository;
        this.dTransactionSearchRepository = dTransactionSearchRepository;
    }

    /**
     * {@code POST  /d-transactions} : Create a new dTransaction.
     *
     * @param dTransaction the dTransaction to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dTransaction, or with status {@code 400 (Bad Request)} if the dTransaction has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/d-transactions")
    public Mono<ResponseEntity<DTransaction>> createDTransaction(@Valid @RequestBody DTransaction dTransaction) throws URISyntaxException {
        log.debug("REST request to save DTransaction : {}", dTransaction);
        if (dTransaction.getId() != null) {
            throw new BadRequestAlertException("A new dTransaction cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return dTransactionRepository
            .save(dTransaction)
            .flatMap(dTransactionSearchRepository::save)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/d-transactions/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /d-transactions/:id} : Updates an existing dTransaction.
     *
     * @param id the id of the dTransaction to save.
     * @param dTransaction the dTransaction to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dTransaction,
     * or with status {@code 400 (Bad Request)} if the dTransaction is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dTransaction couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/d-transactions/{id}")
    public Mono<ResponseEntity<DTransaction>> updateDTransaction(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody DTransaction dTransaction
    ) throws URISyntaxException {
        log.debug("REST request to update DTransaction : {}, {}", id, dTransaction);
        if (dTransaction.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dTransaction.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return dTransactionRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return dTransactionRepository
                    .save(dTransaction)
                    .flatMap(dTransactionSearchRepository::save)
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
     * {@code PATCH  /d-transactions/:id} : Partial updates given fields of an existing dTransaction, field will ignore if it is null
     *
     * @param id the id of the dTransaction to save.
     * @param dTransaction the dTransaction to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dTransaction,
     * or with status {@code 400 (Bad Request)} if the dTransaction is not valid,
     * or with status {@code 404 (Not Found)} if the dTransaction is not found,
     * or with status {@code 500 (Internal Server Error)} if the dTransaction couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/d-transactions/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<DTransaction>> partialUpdateDTransaction(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody DTransaction dTransaction
    ) throws URISyntaxException {
        log.debug("REST request to partial update DTransaction partially : {}, {}", id, dTransaction);
        if (dTransaction.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dTransaction.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return dTransactionRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<DTransaction> result = dTransactionRepository
                    .findById(dTransaction.getId())
                    .map(existingDTransaction -> {
                        if (dTransaction.getAbsaTranRef() != null) {
                            existingDTransaction.setAbsaTranRef(dTransaction.getAbsaTranRef());
                        }
                        if (dTransaction.getBillerId() != null) {
                            existingDTransaction.setBillerId(dTransaction.getBillerId());
                        }
                        if (dTransaction.getPaymentItemCode() != null) {
                            existingDTransaction.setPaymentItemCode(dTransaction.getPaymentItemCode());
                        }
                        if (dTransaction.getDtDTransactionId() != null) {
                            existingDTransaction.setDtDTransactionId(dTransaction.getDtDTransactionId());
                        }
                        if (dTransaction.getAmolDTransactionId() != null) {
                            existingDTransaction.setAmolDTransactionId(dTransaction.getAmolDTransactionId());
                        }
                        if (dTransaction.getTransactionReferenceNumber() != null) {
                            existingDTransaction.setTransactionReferenceNumber(dTransaction.getTransactionReferenceNumber());
                        }
                        if (dTransaction.getExternalTranid() != null) {
                            existingDTransaction.setExternalTranid(dTransaction.getExternalTranid());
                        }
                        if (dTransaction.getToken() != null) {
                            existingDTransaction.setToken(dTransaction.getToken());
                        }
                        if (dTransaction.getTransferId() != null) {
                            existingDTransaction.setTransferId(dTransaction.getTransferId());
                        }
                        if (dTransaction.getProductCode() != null) {
                            existingDTransaction.setProductCode(dTransaction.getProductCode());
                        }
                        if (dTransaction.getPaymentChannelCode() != null) {
                            existingDTransaction.setPaymentChannelCode(dTransaction.getPaymentChannelCode());
                        }
                        if (dTransaction.getAccountNumber() != null) {
                            existingDTransaction.setAccountNumber(dTransaction.getAccountNumber());
                        }
                        if (dTransaction.getAmount() != null) {
                            existingDTransaction.setAmount(dTransaction.getAmount());
                        }
                        if (dTransaction.getDebitAmount() != null) {
                            existingDTransaction.setDebitAmount(dTransaction.getDebitAmount());
                        }
                        if (dTransaction.getCreditAmount() != null) {
                            existingDTransaction.setCreditAmount(dTransaction.getCreditAmount());
                        }
                        if (dTransaction.getSettlementAmount() != null) {
                            existingDTransaction.setSettlementAmount(dTransaction.getSettlementAmount());
                        }
                        if (dTransaction.getSettlementDate() != null) {
                            existingDTransaction.setSettlementDate(dTransaction.getSettlementDate());
                        }
                        if (dTransaction.getTransactionDate() != null) {
                            existingDTransaction.setTransactionDate(dTransaction.getTransactionDate());
                        }
                        if (dTransaction.getIsRetried() != null) {
                            existingDTransaction.setIsRetried(dTransaction.getIsRetried());
                        }
                        if (dTransaction.getLastRetryDate() != null) {
                            existingDTransaction.setLastRetryDate(dTransaction.getLastRetryDate());
                        }
                        if (dTransaction.getFirstRetryDate() != null) {
                            existingDTransaction.setFirstRetryDate(dTransaction.getFirstRetryDate());
                        }
                        if (dTransaction.getRetryResposeCode() != null) {
                            existingDTransaction.setRetryResposeCode(dTransaction.getRetryResposeCode());
                        }
                        if (dTransaction.getRetryResponseMessage() != null) {
                            existingDTransaction.setRetryResponseMessage(dTransaction.getRetryResponseMessage());
                        }
                        if (dTransaction.getRetryCount() != null) {
                            existingDTransaction.setRetryCount(dTransaction.getRetryCount());
                        }
                        if (dTransaction.getIsRetriableFlg() != null) {
                            existingDTransaction.setIsRetriableFlg(dTransaction.getIsRetriableFlg());
                        }
                        if (dTransaction.getDoNotRetryDTransaction() != null) {
                            existingDTransaction.setDoNotRetryDTransaction(dTransaction.getDoNotRetryDTransaction());
                        }
                        if (dTransaction.getStatus() != null) {
                            existingDTransaction.setStatus(dTransaction.getStatus());
                        }
                        if (dTransaction.getStatusCode() != null) {
                            existingDTransaction.setStatusCode(dTransaction.getStatusCode());
                        }
                        if (dTransaction.getStatusDetails() != null) {
                            existingDTransaction.setStatusDetails(dTransaction.getStatusDetails());
                        }
                        if (dTransaction.getRetries() != null) {
                            existingDTransaction.setRetries(dTransaction.getRetries());
                        }
                        if (dTransaction.getTimestamp() != null) {
                            existingDTransaction.setTimestamp(dTransaction.getTimestamp());
                        }
                        if (dTransaction.getPostedBy() != null) {
                            existingDTransaction.setPostedBy(dTransaction.getPostedBy());
                        }
                        if (dTransaction.getPostedDate() != null) {
                            existingDTransaction.setPostedDate(dTransaction.getPostedDate());
                        }
                        if (dTransaction.getInternalErrorCode() != null) {
                            existingDTransaction.setInternalErrorCode(dTransaction.getInternalErrorCode());
                        }
                        if (dTransaction.getExternalErrorCode() != null) {
                            existingDTransaction.setExternalErrorCode(dTransaction.getExternalErrorCode());
                        }
                        if (dTransaction.getIsCrossCurrency() != null) {
                            existingDTransaction.setIsCrossCurrency(dTransaction.getIsCrossCurrency());
                        }
                        if (dTransaction.getIsCrossBank() != null) {
                            existingDTransaction.setIsCrossBank(dTransaction.getIsCrossBank());
                        }
                        if (dTransaction.getCurrency() != null) {
                            existingDTransaction.setCurrency(dTransaction.getCurrency());
                        }
                        if (dTransaction.getCreditAccount() != null) {
                            existingDTransaction.setCreditAccount(dTransaction.getCreditAccount());
                        }
                        if (dTransaction.getDebitAccount() != null) {
                            existingDTransaction.setDebitAccount(dTransaction.getDebitAccount());
                        }
                        if (dTransaction.getPhoneNumber() != null) {
                            existingDTransaction.setPhoneNumber(dTransaction.getPhoneNumber());
                        }
                        if (dTransaction.getCustomerNumber() != null) {
                            existingDTransaction.setCustomerNumber(dTransaction.getCustomerNumber());
                        }
                        if (dTransaction.getTranStatus() != null) {
                            existingDTransaction.setTranStatus(dTransaction.getTranStatus());
                        }
                        if (dTransaction.getTranStatusDetails() != null) {
                            existingDTransaction.setTranStatusDetails(dTransaction.getTranStatusDetails());
                        }
                        if (dTransaction.getTranFreeField1() != null) {
                            existingDTransaction.setTranFreeField1(dTransaction.getTranFreeField1());
                        }
                        if (dTransaction.getTranFreeField2() != null) {
                            existingDTransaction.setTranFreeField2(dTransaction.getTranFreeField2());
                        }
                        if (dTransaction.getTranFreeField3() != null) {
                            existingDTransaction.setTranFreeField3(dTransaction.getTranFreeField3());
                        }
                        if (dTransaction.getTranFreeField4() != null) {
                            existingDTransaction.setTranFreeField4(dTransaction.getTranFreeField4());
                        }
                        if (dTransaction.getTranFreeField5() != null) {
                            existingDTransaction.setTranFreeField5(dTransaction.getTranFreeField5());
                        }
                        if (dTransaction.getTranFreeField6() != null) {
                            existingDTransaction.setTranFreeField6(dTransaction.getTranFreeField6());
                        }
                        if (dTransaction.getTranFreeField7() != null) {
                            existingDTransaction.setTranFreeField7(dTransaction.getTranFreeField7());
                        }
                        if (dTransaction.getTranFreeField8() != null) {
                            existingDTransaction.setTranFreeField8(dTransaction.getTranFreeField8());
                        }
                        if (dTransaction.getTranFreeField9() != null) {
                            existingDTransaction.setTranFreeField9(dTransaction.getTranFreeField9());
                        }
                        if (dTransaction.getTranFreeField10() != null) {
                            existingDTransaction.setTranFreeField10(dTransaction.getTranFreeField10());
                        }
                        if (dTransaction.getTranFreeField11() != null) {
                            existingDTransaction.setTranFreeField11(dTransaction.getTranFreeField11());
                        }
                        if (dTransaction.getTranFreeField12() != null) {
                            existingDTransaction.setTranFreeField12(dTransaction.getTranFreeField12());
                        }
                        if (dTransaction.getTranFreeField13() != null) {
                            existingDTransaction.setTranFreeField13(dTransaction.getTranFreeField13());
                        }
                        if (dTransaction.getTranFreeField14() != null) {
                            existingDTransaction.setTranFreeField14(dTransaction.getTranFreeField14());
                        }
                        if (dTransaction.getTranFreeField14ContentType() != null) {
                            existingDTransaction.setTranFreeField14ContentType(dTransaction.getTranFreeField14ContentType());
                        }
                        if (dTransaction.getTranFreeField15() != null) {
                            existingDTransaction.setTranFreeField15(dTransaction.getTranFreeField15());
                        }
                        if (dTransaction.getTranFreeField16() != null) {
                            existingDTransaction.setTranFreeField16(dTransaction.getTranFreeField16());
                        }
                        if (dTransaction.getTranFreeField17() != null) {
                            existingDTransaction.setTranFreeField17(dTransaction.getTranFreeField17());
                        }
                        if (dTransaction.getTranFreeField18() != null) {
                            existingDTransaction.setTranFreeField18(dTransaction.getTranFreeField18());
                        }
                        if (dTransaction.getTranFreeField19() != null) {
                            existingDTransaction.setTranFreeField19(dTransaction.getTranFreeField19());
                        }
                        if (dTransaction.getTranFreeField20() != null) {
                            existingDTransaction.setTranFreeField20(dTransaction.getTranFreeField20());
                        }
                        if (dTransaction.getTranFreeField21() != null) {
                            existingDTransaction.setTranFreeField21(dTransaction.getTranFreeField21());
                        }
                        if (dTransaction.getTranFreeField22() != null) {
                            existingDTransaction.setTranFreeField22(dTransaction.getTranFreeField22());
                        }
                        if (dTransaction.getTranFreeField23() != null) {
                            existingDTransaction.setTranFreeField23(dTransaction.getTranFreeField23());
                        }
                        if (dTransaction.getTranFreeField24() != null) {
                            existingDTransaction.setTranFreeField24(dTransaction.getTranFreeField24());
                        }
                        if (dTransaction.getTranFreeField25() != null) {
                            existingDTransaction.setTranFreeField25(dTransaction.getTranFreeField25());
                        }
                        if (dTransaction.getTranFreeField26() != null) {
                            existingDTransaction.setTranFreeField26(dTransaction.getTranFreeField26());
                        }
                        if (dTransaction.getTranFreeField27() != null) {
                            existingDTransaction.setTranFreeField27(dTransaction.getTranFreeField27());
                        }
                        if (dTransaction.getTranFreeField28() != null) {
                            existingDTransaction.setTranFreeField28(dTransaction.getTranFreeField28());
                        }
                        if (dTransaction.getCreatedAt() != null) {
                            existingDTransaction.setCreatedAt(dTransaction.getCreatedAt());
                        }
                        if (dTransaction.getCreatedBy() != null) {
                            existingDTransaction.setCreatedBy(dTransaction.getCreatedBy());
                        }
                        if (dTransaction.getUpdatedAt() != null) {
                            existingDTransaction.setUpdatedAt(dTransaction.getUpdatedAt());
                        }
                        if (dTransaction.getUpdatedBy() != null) {
                            existingDTransaction.setUpdatedBy(dTransaction.getUpdatedBy());
                        }

                        return existingDTransaction;
                    })
                    .flatMap(dTransactionRepository::save)
                    .flatMap(savedDTransaction -> {
                        dTransactionSearchRepository.save(savedDTransaction);

                        return Mono.just(savedDTransaction);
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
     * {@code GET  /d-transactions} : get all the dTransactions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dTransactions in body.
     */
    @GetMapping("/d-transactions")
    public Mono<List<DTransaction>> getAllDTransactions() {
        log.debug("REST request to get all DTransactions");
        return dTransactionRepository.findAll().collectList();
    }

    /**
     * {@code GET  /d-transactions} : get all the dTransactions as a stream.
     * @return the {@link Flux} of dTransactions.
     */
    @GetMapping(value = "/d-transactions", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<DTransaction> getAllDTransactionsAsStream() {
        log.debug("REST request to get all DTransactions as a stream");
        return dTransactionRepository.findAll();
    }

    /**
     * {@code GET  /d-transactions/:id} : get the "id" dTransaction.
     *
     * @param id the id of the dTransaction to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dTransaction, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/d-transactions/{id}")
    public Mono<ResponseEntity<DTransaction>> getDTransaction(@PathVariable Long id) {
        log.debug("REST request to get DTransaction : {}", id);
        Mono<DTransaction> dTransaction = dTransactionRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(dTransaction);
    }

    /**
     * {@code DELETE  /d-transactions/:id} : delete the "id" dTransaction.
     *
     * @param id the id of the dTransaction to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/d-transactions/{id}")
    public Mono<ResponseEntity<Void>> deleteDTransaction(@PathVariable Long id) {
        log.debug("REST request to delete DTransaction : {}", id);
        return dTransactionRepository
            .deleteById(id)
            .then(dTransactionSearchRepository.deleteById(id))
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
     * {@code SEARCH  /_search/d-transactions?query=:query} : search for the dTransaction corresponding
     * to the query.
     *
     * @param query the query of the dTransaction search.
     * @return the result of the search.
     */
    @GetMapping("/_search/d-transactions")
    public Mono<List<DTransaction>> searchDTransactions(@RequestParam String query) {
        log.debug("REST request to search DTransactions for query {}", query);
        return dTransactionSearchRepository.search(query).collectList();
    }
}
