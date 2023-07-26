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
import ug.co.absa.microsrvc.gateway.domain.DTransactionDetails;
import ug.co.absa.microsrvc.gateway.repository.DTransactionDetailsRepository;
import ug.co.absa.microsrvc.gateway.repository.search.DTransactionDetailsSearchRepository;
import ug.co.absa.microsrvc.gateway.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link ug.co.absa.microsrvc.gateway.domain.DTransactionDetails}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class DTransactionDetailsResource {

    private final Logger log = LoggerFactory.getLogger(DTransactionDetailsResource.class);

    private static final String ENTITY_NAME = "dTransactionDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DTransactionDetailsRepository dTransactionDetailsRepository;

    private final DTransactionDetailsSearchRepository dTransactionDetailsSearchRepository;

    public DTransactionDetailsResource(
        DTransactionDetailsRepository dTransactionDetailsRepository,
        DTransactionDetailsSearchRepository dTransactionDetailsSearchRepository
    ) {
        this.dTransactionDetailsRepository = dTransactionDetailsRepository;
        this.dTransactionDetailsSearchRepository = dTransactionDetailsSearchRepository;
    }

    /**
     * {@code POST  /d-transaction-details} : Create a new dTransactionDetails.
     *
     * @param dTransactionDetails the dTransactionDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dTransactionDetails, or with status {@code 400 (Bad Request)} if the dTransactionDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/d-transaction-details")
    public Mono<ResponseEntity<DTransactionDetails>> createDTransactionDetails(@Valid @RequestBody DTransactionDetails dTransactionDetails)
        throws URISyntaxException {
        log.debug("REST request to save DTransactionDetails : {}", dTransactionDetails);
        if (dTransactionDetails.getId() != null) {
            throw new BadRequestAlertException("A new dTransactionDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return dTransactionDetailsRepository
            .save(dTransactionDetails)
            .flatMap(dTransactionDetailsSearchRepository::save)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/d-transaction-details/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /d-transaction-details/:id} : Updates an existing dTransactionDetails.
     *
     * @param id the id of the dTransactionDetails to save.
     * @param dTransactionDetails the dTransactionDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dTransactionDetails,
     * or with status {@code 400 (Bad Request)} if the dTransactionDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dTransactionDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/d-transaction-details/{id}")
    public Mono<ResponseEntity<DTransactionDetails>> updateDTransactionDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody DTransactionDetails dTransactionDetails
    ) throws URISyntaxException {
        log.debug("REST request to update DTransactionDetails : {}, {}", id, dTransactionDetails);
        if (dTransactionDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dTransactionDetails.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return dTransactionDetailsRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return dTransactionDetailsRepository
                    .save(dTransactionDetails)
                    .flatMap(dTransactionDetailsSearchRepository::save)
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
     * {@code PATCH  /d-transaction-details/:id} : Partial updates given fields of an existing dTransactionDetails, field will ignore if it is null
     *
     * @param id the id of the dTransactionDetails to save.
     * @param dTransactionDetails the dTransactionDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dTransactionDetails,
     * or with status {@code 400 (Bad Request)} if the dTransactionDetails is not valid,
     * or with status {@code 404 (Not Found)} if the dTransactionDetails is not found,
     * or with status {@code 500 (Internal Server Error)} if the dTransactionDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/d-transaction-details/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<DTransactionDetails>> partialUpdateDTransactionDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody DTransactionDetails dTransactionDetails
    ) throws URISyntaxException {
        log.debug("REST request to partial update DTransactionDetails partially : {}, {}", id, dTransactionDetails);
        if (dTransactionDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dTransactionDetails.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return dTransactionDetailsRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<DTransactionDetails> result = dTransactionDetailsRepository
                    .findById(dTransactionDetails.getId())
                    .map(existingDTransactionDetails -> {
                        if (dTransactionDetails.getAbsaTranRef() != null) {
                            existingDTransactionDetails.setAbsaTranRef(dTransactionDetails.getAbsaTranRef());
                        }
                        if (dTransactionDetails.getBillerId() != null) {
                            existingDTransactionDetails.setBillerId(dTransactionDetails.getBillerId());
                        }
                        if (dTransactionDetails.getPaymentItemCode() != null) {
                            existingDTransactionDetails.setPaymentItemCode(dTransactionDetails.getPaymentItemCode());
                        }
                        if (dTransactionDetails.getDtDTransactionId() != null) {
                            existingDTransactionDetails.setDtDTransactionId(dTransactionDetails.getDtDTransactionId());
                        }
                        if (dTransactionDetails.getAmolDTransactionId() != null) {
                            existingDTransactionDetails.setAmolDTransactionId(dTransactionDetails.getAmolDTransactionId());
                        }
                        if (dTransactionDetails.getTransactionReferenceNumber() != null) {
                            existingDTransactionDetails.setTransactionReferenceNumber(dTransactionDetails.getTransactionReferenceNumber());
                        }
                        if (dTransactionDetails.getToken() != null) {
                            existingDTransactionDetails.setToken(dTransactionDetails.getToken());
                        }
                        if (dTransactionDetails.getTransferId() != null) {
                            existingDTransactionDetails.setTransferId(dTransactionDetails.getTransferId());
                        }
                        if (dTransactionDetails.getProductCode() != null) {
                            existingDTransactionDetails.setProductCode(dTransactionDetails.getProductCode());
                        }
                        if (dTransactionDetails.getPaymentChannelCode() != null) {
                            existingDTransactionDetails.setPaymentChannelCode(dTransactionDetails.getPaymentChannelCode());
                        }
                        if (dTransactionDetails.getDebitAccountNumber() != null) {
                            existingDTransactionDetails.setDebitAccountNumber(dTransactionDetails.getDebitAccountNumber());
                        }
                        if (dTransactionDetails.getCreditAccountNumber() != null) {
                            existingDTransactionDetails.setCreditAccountNumber(dTransactionDetails.getCreditAccountNumber());
                        }
                        if (dTransactionDetails.getDebitAmount() != null) {
                            existingDTransactionDetails.setDebitAmount(dTransactionDetails.getDebitAmount());
                        }
                        if (dTransactionDetails.getCreditAmount() != null) {
                            existingDTransactionDetails.setCreditAmount(dTransactionDetails.getCreditAmount());
                        }
                        if (dTransactionDetails.getTransactionAmount() != null) {
                            existingDTransactionDetails.setTransactionAmount(dTransactionDetails.getTransactionAmount());
                        }
                        if (dTransactionDetails.getDebitDate() != null) {
                            existingDTransactionDetails.setDebitDate(dTransactionDetails.getDebitDate());
                        }
                        if (dTransactionDetails.getCreditDate() != null) {
                            existingDTransactionDetails.setCreditDate(dTransactionDetails.getCreditDate());
                        }
                        if (dTransactionDetails.getStatus() != null) {
                            existingDTransactionDetails.setStatus(dTransactionDetails.getStatus());
                        }
                        if (dTransactionDetails.getSettlementDate() != null) {
                            existingDTransactionDetails.setSettlementDate(dTransactionDetails.getSettlementDate());
                        }
                        if (dTransactionDetails.getDebitCurrency() != null) {
                            existingDTransactionDetails.setDebitCurrency(dTransactionDetails.getDebitCurrency());
                        }
                        if (dTransactionDetails.getTimestamp() != null) {
                            existingDTransactionDetails.setTimestamp(dTransactionDetails.getTimestamp());
                        }
                        if (dTransactionDetails.getPhoneNumber() != null) {
                            existingDTransactionDetails.setPhoneNumber(dTransactionDetails.getPhoneNumber());
                        }
                        if (dTransactionDetails.getEmail() != null) {
                            existingDTransactionDetails.setEmail(dTransactionDetails.getEmail());
                        }
                        if (dTransactionDetails.getPayerName() != null) {
                            existingDTransactionDetails.setPayerName(dTransactionDetails.getPayerName());
                        }
                        if (dTransactionDetails.getPayerAddress() != null) {
                            existingDTransactionDetails.setPayerAddress(dTransactionDetails.getPayerAddress());
                        }
                        if (dTransactionDetails.getPayerEmail() != null) {
                            existingDTransactionDetails.setPayerEmail(dTransactionDetails.getPayerEmail());
                        }
                        if (dTransactionDetails.getPayerPhoneNumber() != null) {
                            existingDTransactionDetails.setPayerPhoneNumber(dTransactionDetails.getPayerPhoneNumber());
                        }
                        if (dTransactionDetails.getPayerNarration() != null) {
                            existingDTransactionDetails.setPayerNarration(dTransactionDetails.getPayerNarration());
                        }
                        if (dTransactionDetails.getPayerBranchId() != null) {
                            existingDTransactionDetails.setPayerBranchId(dTransactionDetails.getPayerBranchId());
                        }
                        if (dTransactionDetails.getBeneficiaryName() != null) {
                            existingDTransactionDetails.setBeneficiaryName(dTransactionDetails.getBeneficiaryName());
                        }
                        if (dTransactionDetails.getBeneficiaryAccount() != null) {
                            existingDTransactionDetails.setBeneficiaryAccount(dTransactionDetails.getBeneficiaryAccount());
                        }
                        if (dTransactionDetails.getBeneficiaryBranchId() != null) {
                            existingDTransactionDetails.setBeneficiaryBranchId(dTransactionDetails.getBeneficiaryBranchId());
                        }
                        if (dTransactionDetails.getBeneficiaryPhoneNumber() != null) {
                            existingDTransactionDetails.setBeneficiaryPhoneNumber(dTransactionDetails.getBeneficiaryPhoneNumber());
                        }
                        if (dTransactionDetails.getTranStatus() != null) {
                            existingDTransactionDetails.setTranStatus(dTransactionDetails.getTranStatus());
                        }
                        if (dTransactionDetails.getNarration1() != null) {
                            existingDTransactionDetails.setNarration1(dTransactionDetails.getNarration1());
                        }
                        if (dTransactionDetails.getNarration2() != null) {
                            existingDTransactionDetails.setNarration2(dTransactionDetails.getNarration2());
                        }
                        if (dTransactionDetails.getNarration3() != null) {
                            existingDTransactionDetails.setNarration3(dTransactionDetails.getNarration3());
                        }
                        if (dTransactionDetails.getNarration4() != null) {
                            existingDTransactionDetails.setNarration4(dTransactionDetails.getNarration4());
                        }
                        if (dTransactionDetails.getNarration5() != null) {
                            existingDTransactionDetails.setNarration5(dTransactionDetails.getNarration5());
                        }
                        if (dTransactionDetails.getNarration6() != null) {
                            existingDTransactionDetails.setNarration6(dTransactionDetails.getNarration6());
                        }
                        if (dTransactionDetails.getNarration7() != null) {
                            existingDTransactionDetails.setNarration7(dTransactionDetails.getNarration7());
                        }
                        if (dTransactionDetails.getNarration8() != null) {
                            existingDTransactionDetails.setNarration8(dTransactionDetails.getNarration8());
                        }
                        if (dTransactionDetails.getNarration9() != null) {
                            existingDTransactionDetails.setNarration9(dTransactionDetails.getNarration9());
                        }
                        if (dTransactionDetails.getNarration10() != null) {
                            existingDTransactionDetails.setNarration10(dTransactionDetails.getNarration10());
                        }
                        if (dTransactionDetails.getNarration11() != null) {
                            existingDTransactionDetails.setNarration11(dTransactionDetails.getNarration11());
                        }
                        if (dTransactionDetails.getNarration12() != null) {
                            existingDTransactionDetails.setNarration12(dTransactionDetails.getNarration12());
                        }
                        if (dTransactionDetails.getModeOfPayment() != null) {
                            existingDTransactionDetails.setModeOfPayment(dTransactionDetails.getModeOfPayment());
                        }
                        if (dTransactionDetails.getRetries() != null) {
                            existingDTransactionDetails.setRetries(dTransactionDetails.getRetries());
                        }
                        if (dTransactionDetails.getPosted() != null) {
                            existingDTransactionDetails.setPosted(dTransactionDetails.getPosted());
                        }
                        if (dTransactionDetails.getApiId() != null) {
                            existingDTransactionDetails.setApiId(dTransactionDetails.getApiId());
                        }
                        if (dTransactionDetails.getApiVersion() != null) {
                            existingDTransactionDetails.setApiVersion(dTransactionDetails.getApiVersion());
                        }
                        if (dTransactionDetails.getPostingApi() != null) {
                            existingDTransactionDetails.setPostingApi(dTransactionDetails.getPostingApi());
                        }
                        if (dTransactionDetails.getIsPosted() != null) {
                            existingDTransactionDetails.setIsPosted(dTransactionDetails.getIsPosted());
                        }
                        if (dTransactionDetails.getPostedBy() != null) {
                            existingDTransactionDetails.setPostedBy(dTransactionDetails.getPostedBy());
                        }
                        if (dTransactionDetails.getPostedDate() != null) {
                            existingDTransactionDetails.setPostedDate(dTransactionDetails.getPostedDate());
                        }
                        if (dTransactionDetails.getTranFreeField1() != null) {
                            existingDTransactionDetails.setTranFreeField1(dTransactionDetails.getTranFreeField1());
                        }
                        if (dTransactionDetails.getTranFreeField3() != null) {
                            existingDTransactionDetails.setTranFreeField3(dTransactionDetails.getTranFreeField3());
                        }
                        if (dTransactionDetails.getTranFreeField4() != null) {
                            existingDTransactionDetails.setTranFreeField4(dTransactionDetails.getTranFreeField4());
                        }
                        if (dTransactionDetails.getTranFreeField5() != null) {
                            existingDTransactionDetails.setTranFreeField5(dTransactionDetails.getTranFreeField5());
                        }
                        if (dTransactionDetails.getTranFreeField6() != null) {
                            existingDTransactionDetails.setTranFreeField6(dTransactionDetails.getTranFreeField6());
                        }
                        if (dTransactionDetails.getTranFreeField7() != null) {
                            existingDTransactionDetails.setTranFreeField7(dTransactionDetails.getTranFreeField7());
                        }
                        if (dTransactionDetails.getTranFreeField8() != null) {
                            existingDTransactionDetails.setTranFreeField8(dTransactionDetails.getTranFreeField8());
                        }
                        if (dTransactionDetails.getTranFreeField9() != null) {
                            existingDTransactionDetails.setTranFreeField9(dTransactionDetails.getTranFreeField9());
                        }
                        if (dTransactionDetails.getTranFreeField10() != null) {
                            existingDTransactionDetails.setTranFreeField10(dTransactionDetails.getTranFreeField10());
                        }
                        if (dTransactionDetails.getTranFreeField11() != null) {
                            existingDTransactionDetails.setTranFreeField11(dTransactionDetails.getTranFreeField11());
                        }
                        if (dTransactionDetails.getTranFreeField12() != null) {
                            existingDTransactionDetails.setTranFreeField12(dTransactionDetails.getTranFreeField12());
                        }
                        if (dTransactionDetails.getTranFreeField12ContentType() != null) {
                            existingDTransactionDetails.setTranFreeField12ContentType(dTransactionDetails.getTranFreeField12ContentType());
                        }
                        if (dTransactionDetails.getTranFreeField13() != null) {
                            existingDTransactionDetails.setTranFreeField13(dTransactionDetails.getTranFreeField13());
                        }
                        if (dTransactionDetails.getTranFreeField14() != null) {
                            existingDTransactionDetails.setTranFreeField14(dTransactionDetails.getTranFreeField14());
                        }
                        if (dTransactionDetails.getTranFreeField15() != null) {
                            existingDTransactionDetails.setTranFreeField15(dTransactionDetails.getTranFreeField15());
                        }
                        if (dTransactionDetails.getTranFreeField15ContentType() != null) {
                            existingDTransactionDetails.setTranFreeField15ContentType(dTransactionDetails.getTranFreeField15ContentType());
                        }
                        if (dTransactionDetails.getTranFreeField16() != null) {
                            existingDTransactionDetails.setTranFreeField16(dTransactionDetails.getTranFreeField16());
                        }
                        if (dTransactionDetails.getTranFreeField17() != null) {
                            existingDTransactionDetails.setTranFreeField17(dTransactionDetails.getTranFreeField17());
                        }
                        if (dTransactionDetails.getTranFreeField18() != null) {
                            existingDTransactionDetails.setTranFreeField18(dTransactionDetails.getTranFreeField18());
                        }
                        if (dTransactionDetails.getTranFreeField19() != null) {
                            existingDTransactionDetails.setTranFreeField19(dTransactionDetails.getTranFreeField19());
                        }
                        if (dTransactionDetails.getTranFreeField20() != null) {
                            existingDTransactionDetails.setTranFreeField20(dTransactionDetails.getTranFreeField20());
                        }
                        if (dTransactionDetails.getTranFreeField21() != null) {
                            existingDTransactionDetails.setTranFreeField21(dTransactionDetails.getTranFreeField21());
                        }
                        if (dTransactionDetails.getTranFreeField22() != null) {
                            existingDTransactionDetails.setTranFreeField22(dTransactionDetails.getTranFreeField22());
                        }
                        if (dTransactionDetails.getTranFreeField23() != null) {
                            existingDTransactionDetails.setTranFreeField23(dTransactionDetails.getTranFreeField23());
                        }
                        if (dTransactionDetails.getTranFreeField24() != null) {
                            existingDTransactionDetails.setTranFreeField24(dTransactionDetails.getTranFreeField24());
                        }
                        if (dTransactionDetails.getTranFreeField25() != null) {
                            existingDTransactionDetails.setTranFreeField25(dTransactionDetails.getTranFreeField25());
                        }
                        if (dTransactionDetails.getTranFreeField26() != null) {
                            existingDTransactionDetails.setTranFreeField26(dTransactionDetails.getTranFreeField26());
                        }
                        if (dTransactionDetails.getTranFreeField27() != null) {
                            existingDTransactionDetails.setTranFreeField27(dTransactionDetails.getTranFreeField27());
                        }
                        if (dTransactionDetails.getTranFreeField28() != null) {
                            existingDTransactionDetails.setTranFreeField28(dTransactionDetails.getTranFreeField28());
                        }
                        if (dTransactionDetails.getInternalErrorCode() != null) {
                            existingDTransactionDetails.setInternalErrorCode(dTransactionDetails.getInternalErrorCode());
                        }
                        if (dTransactionDetails.getExternalErrorCode() != null) {
                            existingDTransactionDetails.setExternalErrorCode(dTransactionDetails.getExternalErrorCode());
                        }

                        return existingDTransactionDetails;
                    })
                    .flatMap(dTransactionDetailsRepository::save)
                    .flatMap(savedDTransactionDetails -> {
                        dTransactionDetailsSearchRepository.save(savedDTransactionDetails);

                        return Mono.just(savedDTransactionDetails);
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
     * {@code GET  /d-transaction-details} : get all the dTransactionDetails.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dTransactionDetails in body.
     */
    @GetMapping("/d-transaction-details")
    public Mono<List<DTransactionDetails>> getAllDTransactionDetails() {
        log.debug("REST request to get all DTransactionDetails");
        return dTransactionDetailsRepository.findAll().collectList();
    }

    /**
     * {@code GET  /d-transaction-details} : get all the dTransactionDetails as a stream.
     * @return the {@link Flux} of dTransactionDetails.
     */
    @GetMapping(value = "/d-transaction-details", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<DTransactionDetails> getAllDTransactionDetailsAsStream() {
        log.debug("REST request to get all DTransactionDetails as a stream");
        return dTransactionDetailsRepository.findAll();
    }

    /**
     * {@code GET  /d-transaction-details/:id} : get the "id" dTransactionDetails.
     *
     * @param id the id of the dTransactionDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dTransactionDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/d-transaction-details/{id}")
    public Mono<ResponseEntity<DTransactionDetails>> getDTransactionDetails(@PathVariable Long id) {
        log.debug("REST request to get DTransactionDetails : {}", id);
        Mono<DTransactionDetails> dTransactionDetails = dTransactionDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(dTransactionDetails);
    }

    /**
     * {@code DELETE  /d-transaction-details/:id} : delete the "id" dTransactionDetails.
     *
     * @param id the id of the dTransactionDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/d-transaction-details/{id}")
    public Mono<ResponseEntity<Void>> deleteDTransactionDetails(@PathVariable Long id) {
        log.debug("REST request to delete DTransactionDetails : {}", id);
        return dTransactionDetailsRepository
            .deleteById(id)
            .then(dTransactionDetailsSearchRepository.deleteById(id))
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
     * {@code SEARCH  /_search/d-transaction-details?query=:query} : search for the dTransactionDetails corresponding
     * to the query.
     *
     * @param query the query of the dTransactionDetails search.
     * @return the result of the search.
     */
    @GetMapping("/_search/d-transaction-details")
    public Mono<List<DTransactionDetails>> searchDTransactionDetails(@RequestParam String query) {
        log.debug("REST request to search DTransactionDetails for query {}", query);
        return dTransactionDetailsSearchRepository.search(query).collectList();
    }
}
