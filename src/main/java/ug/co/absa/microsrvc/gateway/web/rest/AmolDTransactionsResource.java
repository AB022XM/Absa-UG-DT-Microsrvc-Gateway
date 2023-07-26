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
import ug.co.absa.microsrvc.gateway.domain.AmolDTransactions;
import ug.co.absa.microsrvc.gateway.repository.AmolDTransactionsRepository;
import ug.co.absa.microsrvc.gateway.repository.search.AmolDTransactionsSearchRepository;
import ug.co.absa.microsrvc.gateway.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link ug.co.absa.microsrvc.gateway.domain.AmolDTransactions}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AmolDTransactionsResource {

    private final Logger log = LoggerFactory.getLogger(AmolDTransactionsResource.class);

    private static final String ENTITY_NAME = "amolDTransactions";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AmolDTransactionsRepository amolDTransactionsRepository;

    private final AmolDTransactionsSearchRepository amolDTransactionsSearchRepository;

    public AmolDTransactionsResource(
        AmolDTransactionsRepository amolDTransactionsRepository,
        AmolDTransactionsSearchRepository amolDTransactionsSearchRepository
    ) {
        this.amolDTransactionsRepository = amolDTransactionsRepository;
        this.amolDTransactionsSearchRepository = amolDTransactionsSearchRepository;
    }

    /**
     * {@code POST  /amol-d-transactions} : Create a new amolDTransactions.
     *
     * @param amolDTransactions the amolDTransactions to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new amolDTransactions, or with status {@code 400 (Bad Request)} if the amolDTransactions has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/amol-d-transactions")
    public Mono<ResponseEntity<AmolDTransactions>> createAmolDTransactions(@Valid @RequestBody AmolDTransactions amolDTransactions)
        throws URISyntaxException {
        log.debug("REST request to save AmolDTransactions : {}", amolDTransactions);
        if (amolDTransactions.getId() != null) {
            throw new BadRequestAlertException("A new amolDTransactions cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return amolDTransactionsRepository
            .save(amolDTransactions)
            .flatMap(amolDTransactionsSearchRepository::save)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/amol-d-transactions/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /amol-d-transactions/:id} : Updates an existing amolDTransactions.
     *
     * @param id the id of the amolDTransactions to save.
     * @param amolDTransactions the amolDTransactions to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated amolDTransactions,
     * or with status {@code 400 (Bad Request)} if the amolDTransactions is not valid,
     * or with status {@code 500 (Internal Server Error)} if the amolDTransactions couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/amol-d-transactions/{id}")
    public Mono<ResponseEntity<AmolDTransactions>> updateAmolDTransactions(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody AmolDTransactions amolDTransactions
    ) throws URISyntaxException {
        log.debug("REST request to update AmolDTransactions : {}, {}", id, amolDTransactions);
        if (amolDTransactions.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, amolDTransactions.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return amolDTransactionsRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return amolDTransactionsRepository
                    .save(amolDTransactions)
                    .flatMap(amolDTransactionsSearchRepository::save)
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
     * {@code PATCH  /amol-d-transactions/:id} : Partial updates given fields of an existing amolDTransactions, field will ignore if it is null
     *
     * @param id the id of the amolDTransactions to save.
     * @param amolDTransactions the amolDTransactions to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated amolDTransactions,
     * or with status {@code 400 (Bad Request)} if the amolDTransactions is not valid,
     * or with status {@code 404 (Not Found)} if the amolDTransactions is not found,
     * or with status {@code 500 (Internal Server Error)} if the amolDTransactions couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/amol-d-transactions/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<AmolDTransactions>> partialUpdateAmolDTransactions(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody AmolDTransactions amolDTransactions
    ) throws URISyntaxException {
        log.debug("REST request to partial update AmolDTransactions partially : {}, {}", id, amolDTransactions);
        if (amolDTransactions.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, amolDTransactions.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return amolDTransactionsRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<AmolDTransactions> result = amolDTransactionsRepository
                    .findById(amolDTransactions.getId())
                    .map(existingAmolDTransactions -> {
                        if (amolDTransactions.getAbsaTranRef() != null) {
                            existingAmolDTransactions.setAbsaTranRef(amolDTransactions.getAbsaTranRef());
                        }
                        if (amolDTransactions.getBillerId() != null) {
                            existingAmolDTransactions.setBillerId(amolDTransactions.getBillerId());
                        }
                        if (amolDTransactions.getDtDTransactionId() != null) {
                            existingAmolDTransactions.setDtDTransactionId(amolDTransactions.getDtDTransactionId());
                        }
                        if (amolDTransactions.getAmolDTransactionId() != null) {
                            existingAmolDTransactions.setAmolDTransactionId(amolDTransactions.getAmolDTransactionId());
                        }
                        if (amolDTransactions.getTransactionReferenceNumber() != null) {
                            existingAmolDTransactions.setTransactionReferenceNumber(amolDTransactions.getTransactionReferenceNumber());
                        }
                        if (amolDTransactions.getToken() != null) {
                            existingAmolDTransactions.setToken(amolDTransactions.getToken());
                        }
                        if (amolDTransactions.getTransferId() != null) {
                            existingAmolDTransactions.setTransferId(amolDTransactions.getTransferId());
                        }
                        if (amolDTransactions.getExternalTxnId() != null) {
                            existingAmolDTransactions.setExternalTxnId(amolDTransactions.getExternalTxnId());
                        }
                        if (amolDTransactions.getCustomerReference() != null) {
                            existingAmolDTransactions.setCustomerReference(amolDTransactions.getCustomerReference());
                        }
                        if (amolDTransactions.getDebitAccountNumber() != null) {
                            existingAmolDTransactions.setDebitAccountNumber(amolDTransactions.getDebitAccountNumber());
                        }
                        if (amolDTransactions.getCreditAccountNumber() != null) {
                            existingAmolDTransactions.setCreditAccountNumber(amolDTransactions.getCreditAccountNumber());
                        }
                        if (amolDTransactions.getDebitAmount() != null) {
                            existingAmolDTransactions.setDebitAmount(amolDTransactions.getDebitAmount());
                        }
                        if (amolDTransactions.getCreditAmount() != null) {
                            existingAmolDTransactions.setCreditAmount(amolDTransactions.getCreditAmount());
                        }
                        if (amolDTransactions.getTransactionAmount() != null) {
                            existingAmolDTransactions.setTransactionAmount(amolDTransactions.getTransactionAmount());
                        }
                        if (amolDTransactions.getDebitDate() != null) {
                            existingAmolDTransactions.setDebitDate(amolDTransactions.getDebitDate());
                        }
                        if (amolDTransactions.getCreditDate() != null) {
                            existingAmolDTransactions.setCreditDate(amolDTransactions.getCreditDate());
                        }
                        if (amolDTransactions.getStatus() != null) {
                            existingAmolDTransactions.setStatus(amolDTransactions.getStatus());
                        }
                        if (amolDTransactions.getSettlementDate() != null) {
                            existingAmolDTransactions.setSettlementDate(amolDTransactions.getSettlementDate());
                        }
                        if (amolDTransactions.getDebitCurrency() != null) {
                            existingAmolDTransactions.setDebitCurrency(amolDTransactions.getDebitCurrency());
                        }
                        if (amolDTransactions.getTimestamp() != null) {
                            existingAmolDTransactions.setTimestamp(amolDTransactions.getTimestamp());
                        }
                        if (amolDTransactions.getPhoneNumber() != null) {
                            existingAmolDTransactions.setPhoneNumber(amolDTransactions.getPhoneNumber());
                        }
                        if (amolDTransactions.getEmail() != null) {
                            existingAmolDTransactions.setEmail(amolDTransactions.getEmail());
                        }
                        if (amolDTransactions.getPayerName() != null) {
                            existingAmolDTransactions.setPayerName(amolDTransactions.getPayerName());
                        }
                        if (amolDTransactions.getPayerAddress() != null) {
                            existingAmolDTransactions.setPayerAddress(amolDTransactions.getPayerAddress());
                        }
                        if (amolDTransactions.getPayerEmail() != null) {
                            existingAmolDTransactions.setPayerEmail(amolDTransactions.getPayerEmail());
                        }
                        if (amolDTransactions.getPayerPhoneNumber() != null) {
                            existingAmolDTransactions.setPayerPhoneNumber(amolDTransactions.getPayerPhoneNumber());
                        }
                        if (amolDTransactions.getPayerNarration() != null) {
                            existingAmolDTransactions.setPayerNarration(amolDTransactions.getPayerNarration());
                        }
                        if (amolDTransactions.getPayerBranchId() != null) {
                            existingAmolDTransactions.setPayerBranchId(amolDTransactions.getPayerBranchId());
                        }
                        if (amolDTransactions.getBeneficiaryName() != null) {
                            existingAmolDTransactions.setBeneficiaryName(amolDTransactions.getBeneficiaryName());
                        }
                        if (amolDTransactions.getBeneficiaryAccount() != null) {
                            existingAmolDTransactions.setBeneficiaryAccount(amolDTransactions.getBeneficiaryAccount());
                        }
                        if (amolDTransactions.getBeneficiaryBranchId() != null) {
                            existingAmolDTransactions.setBeneficiaryBranchId(amolDTransactions.getBeneficiaryBranchId());
                        }
                        if (amolDTransactions.getBeneficiaryPhoneNumber() != null) {
                            existingAmolDTransactions.setBeneficiaryPhoneNumber(amolDTransactions.getBeneficiaryPhoneNumber());
                        }
                        if (amolDTransactions.getTranStatus() != null) {
                            existingAmolDTransactions.setTranStatus(amolDTransactions.getTranStatus());
                        }
                        if (amolDTransactions.getNarration1() != null) {
                            existingAmolDTransactions.setNarration1(amolDTransactions.getNarration1());
                        }
                        if (amolDTransactions.getNarration2() != null) {
                            existingAmolDTransactions.setNarration2(amolDTransactions.getNarration2());
                        }
                        if (amolDTransactions.getNarration3() != null) {
                            existingAmolDTransactions.setNarration3(amolDTransactions.getNarration3());
                        }
                        if (amolDTransactions.getTaxAmount() != null) {
                            existingAmolDTransactions.setTaxAmount(amolDTransactions.getTaxAmount());
                        }
                        if (amolDTransactions.getTaxGLAccount() != null) {
                            existingAmolDTransactions.setTaxGLAccount(amolDTransactions.getTaxGLAccount());
                        }
                        if (amolDTransactions.getTaxNarration() != null) {
                            existingAmolDTransactions.setTaxNarration(amolDTransactions.getTaxNarration());
                        }
                        if (amolDTransactions.getInternalErrorCode() != null) {
                            existingAmolDTransactions.setInternalErrorCode(amolDTransactions.getInternalErrorCode());
                        }
                        if (amolDTransactions.getExternalErrorCode() != null) {
                            existingAmolDTransactions.setExternalErrorCode(amolDTransactions.getExternalErrorCode());
                        }
                        if (amolDTransactions.getPayeeBranchId() != null) {
                            existingAmolDTransactions.setPayeeBranchId(amolDTransactions.getPayeeBranchId());
                        }
                        if (amolDTransactions.getPayeeProductInstanceReference() != null) {
                            existingAmolDTransactions.setPayeeProductInstanceReference(
                                amolDTransactions.getPayeeProductInstanceReference()
                            );
                        }
                        if (amolDTransactions.getPayeeProductCode() != null) {
                            existingAmolDTransactions.setPayeeProductCode(amolDTransactions.getPayeeProductCode());
                        }
                        if (amolDTransactions.getPayeeProductName() != null) {
                            existingAmolDTransactions.setPayeeProductName(amolDTransactions.getPayeeProductName());
                        }
                        if (amolDTransactions.getPayeeProductDescription() != null) {
                            existingAmolDTransactions.setPayeeProductDescription(amolDTransactions.getPayeeProductDescription());
                        }
                        if (amolDTransactions.getPayeeProductUnitOfMeasure() != null) {
                            existingAmolDTransactions.setPayeeProductUnitOfMeasure(amolDTransactions.getPayeeProductUnitOfMeasure());
                        }
                        if (amolDTransactions.getPayeeProductQuantity() != null) {
                            existingAmolDTransactions.setPayeeProductQuantity(amolDTransactions.getPayeeProductQuantity());
                        }
                        if (amolDTransactions.getSubCategoryCode() != null) {
                            existingAmolDTransactions.setSubCategoryCode(amolDTransactions.getSubCategoryCode());
                        }
                        if (amolDTransactions.getPayerBankCode() != null) {
                            existingAmolDTransactions.setPayerBankCode(amolDTransactions.getPayerBankCode());
                        }
                        if (amolDTransactions.getPayerBankName() != null) {
                            existingAmolDTransactions.setPayerBankName(amolDTransactions.getPayerBankName());
                        }
                        if (amolDTransactions.getPayerBankAddress() != null) {
                            existingAmolDTransactions.setPayerBankAddress(amolDTransactions.getPayerBankAddress());
                        }
                        if (amolDTransactions.getPayerBankCity() != null) {
                            existingAmolDTransactions.setPayerBankCity(amolDTransactions.getPayerBankCity());
                        }
                        if (amolDTransactions.getPayerBankState() != null) {
                            existingAmolDTransactions.setPayerBankState(amolDTransactions.getPayerBankState());
                        }
                        if (amolDTransactions.getPayerBankCountry() != null) {
                            existingAmolDTransactions.setPayerBankCountry(amolDTransactions.getPayerBankCountry());
                        }
                        if (amolDTransactions.getPayerBankPostalCode() != null) {
                            existingAmolDTransactions.setPayerBankPostalCode(amolDTransactions.getPayerBankPostalCode());
                        }
                        if (amolDTransactions.getCheckerId() != null) {
                            existingAmolDTransactions.setCheckerId(amolDTransactions.getCheckerId());
                        }
                        if (amolDTransactions.getRemittanceInformation() != null) {
                            existingAmolDTransactions.setRemittanceInformation(amolDTransactions.getRemittanceInformation());
                        }
                        if (amolDTransactions.getPaymentChannelCode() != null) {
                            existingAmolDTransactions.setPaymentChannelCode(amolDTransactions.getPaymentChannelCode());
                        }
                        if (amolDTransactions.getFeeAmount() != null) {
                            existingAmolDTransactions.setFeeAmount(amolDTransactions.getFeeAmount());
                        }
                        if (amolDTransactions.getFeeGLAccount() != null) {
                            existingAmolDTransactions.setFeeGLAccount(amolDTransactions.getFeeGLAccount());
                        }
                        if (amolDTransactions.getFeeNarration() != null) {
                            existingAmolDTransactions.setFeeNarration(amolDTransactions.getFeeNarration());
                        }
                        if (amolDTransactions.getTranFreeField1() != null) {
                            existingAmolDTransactions.setTranFreeField1(amolDTransactions.getTranFreeField1());
                        }
                        if (amolDTransactions.getTranFreeField2() != null) {
                            existingAmolDTransactions.setTranFreeField2(amolDTransactions.getTranFreeField2());
                        }
                        if (amolDTransactions.getTranFreeField3() != null) {
                            existingAmolDTransactions.setTranFreeField3(amolDTransactions.getTranFreeField3());
                        }
                        if (amolDTransactions.getTranFreeField4() != null) {
                            existingAmolDTransactions.setTranFreeField4(amolDTransactions.getTranFreeField4());
                        }
                        if (amolDTransactions.getTranFreeField5() != null) {
                            existingAmolDTransactions.setTranFreeField5(amolDTransactions.getTranFreeField5());
                        }
                        if (amolDTransactions.getTranFreeField6() != null) {
                            existingAmolDTransactions.setTranFreeField6(amolDTransactions.getTranFreeField6());
                        }
                        if (amolDTransactions.getTranFreeField7() != null) {
                            existingAmolDTransactions.setTranFreeField7(amolDTransactions.getTranFreeField7());
                        }
                        if (amolDTransactions.getTranFreeField8() != null) {
                            existingAmolDTransactions.setTranFreeField8(amolDTransactions.getTranFreeField8());
                        }
                        if (amolDTransactions.getTranFreeField9() != null) {
                            existingAmolDTransactions.setTranFreeField9(amolDTransactions.getTranFreeField9());
                        }
                        if (amolDTransactions.getTranFreeField10() != null) {
                            existingAmolDTransactions.setTranFreeField10(amolDTransactions.getTranFreeField10());
                        }
                        if (amolDTransactions.getTranFreeField11() != null) {
                            existingAmolDTransactions.setTranFreeField11(amolDTransactions.getTranFreeField11());
                        }
                        if (amolDTransactions.getTranFreeField12() != null) {
                            existingAmolDTransactions.setTranFreeField12(amolDTransactions.getTranFreeField12());
                        }
                        if (amolDTransactions.getTranFreeField13() != null) {
                            existingAmolDTransactions.setTranFreeField13(amolDTransactions.getTranFreeField13());
                        }
                        if (amolDTransactions.getTranFreeField14() != null) {
                            existingAmolDTransactions.setTranFreeField14(amolDTransactions.getTranFreeField14());
                        }
                        if (amolDTransactions.getTranFreeField15() != null) {
                            existingAmolDTransactions.setTranFreeField15(amolDTransactions.getTranFreeField15());
                        }
                        if (amolDTransactions.getTranFreeField16() != null) {
                            existingAmolDTransactions.setTranFreeField16(amolDTransactions.getTranFreeField16());
                        }
                        if (amolDTransactions.getTranFreeField17() != null) {
                            existingAmolDTransactions.setTranFreeField17(amolDTransactions.getTranFreeField17());
                        }
                        if (amolDTransactions.getTranFreeField18() != null) {
                            existingAmolDTransactions.setTranFreeField18(amolDTransactions.getTranFreeField18());
                        }
                        if (amolDTransactions.getTranFreeField19() != null) {
                            existingAmolDTransactions.setTranFreeField19(amolDTransactions.getTranFreeField19());
                        }
                        if (amolDTransactions.getTranFreeField20() != null) {
                            existingAmolDTransactions.setTranFreeField20(amolDTransactions.getTranFreeField20());
                        }
                        if (amolDTransactions.getTranFreeField21() != null) {
                            existingAmolDTransactions.setTranFreeField21(amolDTransactions.getTranFreeField21());
                        }
                        if (amolDTransactions.getTranFreeField22() != null) {
                            existingAmolDTransactions.setTranFreeField22(amolDTransactions.getTranFreeField22());
                        }
                        if (amolDTransactions.getTranFreeField23() != null) {
                            existingAmolDTransactions.setTranFreeField23(amolDTransactions.getTranFreeField23());
                        }
                        if (amolDTransactions.getTranFreeField23ContentType() != null) {
                            existingAmolDTransactions.setTranFreeField23ContentType(amolDTransactions.getTranFreeField23ContentType());
                        }
                        if (amolDTransactions.getTranFreeField24() != null) {
                            existingAmolDTransactions.setTranFreeField24(amolDTransactions.getTranFreeField24());
                        }
                        if (amolDTransactions.getResponseCode() != null) {
                            existingAmolDTransactions.setResponseCode(amolDTransactions.getResponseCode());
                        }
                        if (amolDTransactions.getResponseMessage() != null) {
                            existingAmolDTransactions.setResponseMessage(amolDTransactions.getResponseMessage());
                        }
                        if (amolDTransactions.getResponseDetails() != null) {
                            existingAmolDTransactions.setResponseDetails(amolDTransactions.getResponseDetails());
                        }
                        if (amolDTransactions.getTransferReferenceId() != null) {
                            existingAmolDTransactions.setTransferReferenceId(amolDTransactions.getTransferReferenceId());
                        }
                        if (amolDTransactions.getTransferStatus() != null) {
                            existingAmolDTransactions.setTransferStatus(amolDTransactions.getTransferStatus());
                        }
                        if (amolDTransactions.getResponseDateTime() != null) {
                            existingAmolDTransactions.setResponseDateTime(amolDTransactions.getResponseDateTime());
                        }
                        if (amolDTransactions.getCreatedAt() != null) {
                            existingAmolDTransactions.setCreatedAt(amolDTransactions.getCreatedAt());
                        }
                        if (amolDTransactions.getCreatedBy() != null) {
                            existingAmolDTransactions.setCreatedBy(amolDTransactions.getCreatedBy());
                        }
                        if (amolDTransactions.getUpdatedAt() != null) {
                            existingAmolDTransactions.setUpdatedAt(amolDTransactions.getUpdatedAt());
                        }
                        if (amolDTransactions.getUpdatedBy() != null) {
                            existingAmolDTransactions.setUpdatedBy(amolDTransactions.getUpdatedBy());
                        }

                        return existingAmolDTransactions;
                    })
                    .flatMap(amolDTransactionsRepository::save)
                    .flatMap(savedAmolDTransactions -> {
                        amolDTransactionsSearchRepository.save(savedAmolDTransactions);

                        return Mono.just(savedAmolDTransactions);
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
     * {@code GET  /amol-d-transactions} : get all the amolDTransactions.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of amolDTransactions in body.
     */
    @GetMapping("/amol-d-transactions")
    public Mono<List<AmolDTransactions>> getAllAmolDTransactions() {
        log.debug("REST request to get all AmolDTransactions");
        return amolDTransactionsRepository.findAll().collectList();
    }

    /**
     * {@code GET  /amol-d-transactions} : get all the amolDTransactions as a stream.
     * @return the {@link Flux} of amolDTransactions.
     */
    @GetMapping(value = "/amol-d-transactions", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<AmolDTransactions> getAllAmolDTransactionsAsStream() {
        log.debug("REST request to get all AmolDTransactions as a stream");
        return amolDTransactionsRepository.findAll();
    }

    /**
     * {@code GET  /amol-d-transactions/:id} : get the "id" amolDTransactions.
     *
     * @param id the id of the amolDTransactions to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the amolDTransactions, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/amol-d-transactions/{id}")
    public Mono<ResponseEntity<AmolDTransactions>> getAmolDTransactions(@PathVariable Long id) {
        log.debug("REST request to get AmolDTransactions : {}", id);
        Mono<AmolDTransactions> amolDTransactions = amolDTransactionsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(amolDTransactions);
    }

    /**
     * {@code DELETE  /amol-d-transactions/:id} : delete the "id" amolDTransactions.
     *
     * @param id the id of the amolDTransactions to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/amol-d-transactions/{id}")
    public Mono<ResponseEntity<Void>> deleteAmolDTransactions(@PathVariable Long id) {
        log.debug("REST request to delete AmolDTransactions : {}", id);
        return amolDTransactionsRepository
            .deleteById(id)
            .then(amolDTransactionsSearchRepository.deleteById(id))
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
     * {@code SEARCH  /_search/amol-d-transactions?query=:query} : search for the amolDTransactions corresponding
     * to the query.
     *
     * @param query the query of the amolDTransactions search.
     * @return the result of the search.
     */
    @GetMapping("/_search/amol-d-transactions")
    public Mono<List<AmolDTransactions>> searchAmolDTransactions(@RequestParam String query) {
        log.debug("REST request to search AmolDTransactions for query {}", query);
        return amolDTransactionsSearchRepository.search(query).collectList();
    }
}
