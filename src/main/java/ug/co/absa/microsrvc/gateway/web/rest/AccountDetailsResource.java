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
import ug.co.absa.microsrvc.gateway.domain.AccountDetails;
import ug.co.absa.microsrvc.gateway.repository.AccountDetailsRepository;
import ug.co.absa.microsrvc.gateway.repository.search.AccountDetailsSearchRepository;
import ug.co.absa.microsrvc.gateway.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link ug.co.absa.microsrvc.gateway.domain.AccountDetails}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AccountDetailsResource {

    private final Logger log = LoggerFactory.getLogger(AccountDetailsResource.class);

    private static final String ENTITY_NAME = "accountDetails";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AccountDetailsRepository accountDetailsRepository;

    private final AccountDetailsSearchRepository accountDetailsSearchRepository;

    public AccountDetailsResource(
        AccountDetailsRepository accountDetailsRepository,
        AccountDetailsSearchRepository accountDetailsSearchRepository
    ) {
        this.accountDetailsRepository = accountDetailsRepository;
        this.accountDetailsSearchRepository = accountDetailsSearchRepository;
    }

    /**
     * {@code POST  /account-details} : Create a new accountDetails.
     *
     * @param accountDetails the accountDetails to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new accountDetails, or with status {@code 400 (Bad Request)} if the accountDetails has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/account-details")
    public Mono<ResponseEntity<AccountDetails>> createAccountDetails(@Valid @RequestBody AccountDetails accountDetails)
        throws URISyntaxException {
        log.debug("REST request to save AccountDetails : {}", accountDetails);
        if (accountDetails.getId() != null) {
            throw new BadRequestAlertException("A new accountDetails cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return accountDetailsRepository
            .save(accountDetails)
            .flatMap(accountDetailsSearchRepository::save)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/account-details/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /account-details/:id} : Updates an existing accountDetails.
     *
     * @param id the id of the accountDetails to save.
     * @param accountDetails the accountDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated accountDetails,
     * or with status {@code 400 (Bad Request)} if the accountDetails is not valid,
     * or with status {@code 500 (Internal Server Error)} if the accountDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/account-details/{id}")
    public Mono<ResponseEntity<AccountDetails>> updateAccountDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody AccountDetails accountDetails
    ) throws URISyntaxException {
        log.debug("REST request to update AccountDetails : {}, {}", id, accountDetails);
        if (accountDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, accountDetails.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return accountDetailsRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return accountDetailsRepository
                    .save(accountDetails)
                    .flatMap(accountDetailsSearchRepository::save)
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
     * {@code PATCH  /account-details/:id} : Partial updates given fields of an existing accountDetails, field will ignore if it is null
     *
     * @param id the id of the accountDetails to save.
     * @param accountDetails the accountDetails to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated accountDetails,
     * or with status {@code 400 (Bad Request)} if the accountDetails is not valid,
     * or with status {@code 404 (Not Found)} if the accountDetails is not found,
     * or with status {@code 500 (Internal Server Error)} if the accountDetails couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/account-details/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<AccountDetails>> partialUpdateAccountDetails(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody AccountDetails accountDetails
    ) throws URISyntaxException {
        log.debug("REST request to partial update AccountDetails partially : {}, {}", id, accountDetails);
        if (accountDetails.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, accountDetails.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return accountDetailsRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<AccountDetails> result = accountDetailsRepository
                    .findById(accountDetails.getId())
                    .map(existingAccountDetails -> {
                        if (accountDetails.getAbsaTranRef() != null) {
                            existingAccountDetails.setAbsaTranRef(accountDetails.getAbsaTranRef());
                        }
                        if (accountDetails.getBillerId() != null) {
                            existingAccountDetails.setBillerId(accountDetails.getBillerId());
                        }
                        if (accountDetails.getPaymentItemCode() != null) {
                            existingAccountDetails.setPaymentItemCode(accountDetails.getPaymentItemCode());
                        }
                        if (accountDetails.getDtDTransactionId() != null) {
                            existingAccountDetails.setDtDTransactionId(accountDetails.getDtDTransactionId());
                        }
                        if (accountDetails.getAmolDTransactionId() != null) {
                            existingAccountDetails.setAmolDTransactionId(accountDetails.getAmolDTransactionId());
                        }
                        if (accountDetails.getTransactionReferenceNumber() != null) {
                            existingAccountDetails.setTransactionReferenceNumber(accountDetails.getTransactionReferenceNumber());
                        }
                        if (accountDetails.getToken() != null) {
                            existingAccountDetails.setToken(accountDetails.getToken());
                        }
                        if (accountDetails.getTransferId() != null) {
                            existingAccountDetails.setTransferId(accountDetails.getTransferId());
                        }
                        if (accountDetails.getRequestId() != null) {
                            existingAccountDetails.setRequestId(accountDetails.getRequestId());
                        }
                        if (accountDetails.getAccountName() != null) {
                            existingAccountDetails.setAccountName(accountDetails.getAccountName());
                        }
                        if (accountDetails.getReturnCode() != null) {
                            existingAccountDetails.setReturnCode(accountDetails.getReturnCode());
                        }
                        if (accountDetails.getReturnMessage() != null) {
                            existingAccountDetails.setReturnMessage(accountDetails.getReturnMessage());
                        }
                        if (accountDetails.getExternalTXNid() != null) {
                            existingAccountDetails.setExternalTXNid(accountDetails.getExternalTXNid());
                        }
                        if (accountDetails.getTransactionDate() != null) {
                            existingAccountDetails.setTransactionDate(accountDetails.getTransactionDate());
                        }
                        if (accountDetails.getCustomerId() != null) {
                            existingAccountDetails.setCustomerId(accountDetails.getCustomerId());
                        }
                        if (accountDetails.getCustomerProduct() != null) {
                            existingAccountDetails.setCustomerProduct(accountDetails.getCustomerProduct());
                        }
                        if (accountDetails.getCustomerType() != null) {
                            existingAccountDetails.setCustomerType(accountDetails.getCustomerType());
                        }
                        if (accountDetails.getAccountCategory() != null) {
                            existingAccountDetails.setAccountCategory(accountDetails.getAccountCategory());
                        }
                        if (accountDetails.getAccountType() != null) {
                            existingAccountDetails.setAccountType(accountDetails.getAccountType());
                        }
                        if (accountDetails.getAccountNumber() != null) {
                            existingAccountDetails.setAccountNumber(accountDetails.getAccountNumber());
                        }
                        if (accountDetails.getPhoneNumber() != null) {
                            existingAccountDetails.setPhoneNumber(accountDetails.getPhoneNumber());
                        }
                        if (accountDetails.getChannel() != null) {
                            existingAccountDetails.setChannel(accountDetails.getChannel());
                        }
                        if (accountDetails.getTranFreeField1() != null) {
                            existingAccountDetails.setTranFreeField1(accountDetails.getTranFreeField1());
                        }
                        if (accountDetails.getTranFreeField2() != null) {
                            existingAccountDetails.setTranFreeField2(accountDetails.getTranFreeField2());
                        }
                        if (accountDetails.getTranFreeField3() != null) {
                            existingAccountDetails.setTranFreeField3(accountDetails.getTranFreeField3());
                        }
                        if (accountDetails.getTranFreeField4() != null) {
                            existingAccountDetails.setTranFreeField4(accountDetails.getTranFreeField4());
                        }
                        if (accountDetails.getTranFreeField5() != null) {
                            existingAccountDetails.setTranFreeField5(accountDetails.getTranFreeField5());
                        }
                        if (accountDetails.getTranFreeField6() != null) {
                            existingAccountDetails.setTranFreeField6(accountDetails.getTranFreeField6());
                        }
                        if (accountDetails.getTranFreeField7() != null) {
                            existingAccountDetails.setTranFreeField7(accountDetails.getTranFreeField7());
                        }
                        if (accountDetails.getTranFreeField8() != null) {
                            existingAccountDetails.setTranFreeField8(accountDetails.getTranFreeField8());
                        }
                        if (accountDetails.getTranFreeField9() != null) {
                            existingAccountDetails.setTranFreeField9(accountDetails.getTranFreeField9());
                        }
                        if (accountDetails.getTranFreeField10() != null) {
                            existingAccountDetails.setTranFreeField10(accountDetails.getTranFreeField10());
                        }
                        if (accountDetails.getTranFreeField11() != null) {
                            existingAccountDetails.setTranFreeField11(accountDetails.getTranFreeField11());
                        }
                        if (accountDetails.getTranFreeField12() != null) {
                            existingAccountDetails.setTranFreeField12(accountDetails.getTranFreeField12());
                        }
                        if (accountDetails.getTranFreeField13() != null) {
                            existingAccountDetails.setTranFreeField13(accountDetails.getTranFreeField13());
                        }
                        if (accountDetails.getTranFreeField14() != null) {
                            existingAccountDetails.setTranFreeField14(accountDetails.getTranFreeField14());
                        }
                        if (accountDetails.getTranFreeField15() != null) {
                            existingAccountDetails.setTranFreeField15(accountDetails.getTranFreeField15());
                        }
                        if (accountDetails.getTranFreeField16() != null) {
                            existingAccountDetails.setTranFreeField16(accountDetails.getTranFreeField16());
                        }
                        if (accountDetails.getTranFreeField17() != null) {
                            existingAccountDetails.setTranFreeField17(accountDetails.getTranFreeField17());
                        }
                        if (accountDetails.getTranFreeField18() != null) {
                            existingAccountDetails.setTranFreeField18(accountDetails.getTranFreeField18());
                        }
                        if (accountDetails.getTranFreeField19() != null) {
                            existingAccountDetails.setTranFreeField19(accountDetails.getTranFreeField19());
                        }
                        if (accountDetails.getTranFreeField20() != null) {
                            existingAccountDetails.setTranFreeField20(accountDetails.getTranFreeField20());
                        }
                        if (accountDetails.getTranFreeField21() != null) {
                            existingAccountDetails.setTranFreeField21(accountDetails.getTranFreeField21());
                        }
                        if (accountDetails.getTranFreeField22() != null) {
                            existingAccountDetails.setTranFreeField22(accountDetails.getTranFreeField22());
                        }
                        if (accountDetails.getTranFreeField23() != null) {
                            existingAccountDetails.setTranFreeField23(accountDetails.getTranFreeField23());
                        }
                        if (accountDetails.getTranFreeField23ContentType() != null) {
                            existingAccountDetails.setTranFreeField23ContentType(accountDetails.getTranFreeField23ContentType());
                        }
                        if (accountDetails.getTranFreeField24() != null) {
                            existingAccountDetails.setTranFreeField24(accountDetails.getTranFreeField24());
                        }
                        if (accountDetails.getTranFreeField25() != null) {
                            existingAccountDetails.setTranFreeField25(accountDetails.getTranFreeField25());
                        }
                        if (accountDetails.getTranFreeField26() != null) {
                            existingAccountDetails.setTranFreeField26(accountDetails.getTranFreeField26());
                        }
                        if (accountDetails.getTranFreeField27() != null) {
                            existingAccountDetails.setTranFreeField27(accountDetails.getTranFreeField27());
                        }
                        if (accountDetails.getTranFreeField28() != null) {
                            existingAccountDetails.setTranFreeField28(accountDetails.getTranFreeField28());
                        }
                        if (accountDetails.getTranFreeField29() != null) {
                            existingAccountDetails.setTranFreeField29(accountDetails.getTranFreeField29());
                        }
                        if (accountDetails.getTranFreeField30() != null) {
                            existingAccountDetails.setTranFreeField30(accountDetails.getTranFreeField30());
                        }
                        if (accountDetails.getTranFreeField31() != null) {
                            existingAccountDetails.setTranFreeField31(accountDetails.getTranFreeField31());
                        }
                        if (accountDetails.getTranFreeField32() != null) {
                            existingAccountDetails.setTranFreeField32(accountDetails.getTranFreeField32());
                        }
                        if (accountDetails.getTranFreeField33() != null) {
                            existingAccountDetails.setTranFreeField33(accountDetails.getTranFreeField33());
                        }
                        if (accountDetails.getCreatedAt() != null) {
                            existingAccountDetails.setCreatedAt(accountDetails.getCreatedAt());
                        }
                        if (accountDetails.getCreatedBy() != null) {
                            existingAccountDetails.setCreatedBy(accountDetails.getCreatedBy());
                        }
                        if (accountDetails.getUpdatedAt() != null) {
                            existingAccountDetails.setUpdatedAt(accountDetails.getUpdatedAt());
                        }
                        if (accountDetails.getUpdatedBy() != null) {
                            existingAccountDetails.setUpdatedBy(accountDetails.getUpdatedBy());
                        }

                        return existingAccountDetails;
                    })
                    .flatMap(accountDetailsRepository::save)
                    .flatMap(savedAccountDetails -> {
                        accountDetailsSearchRepository.save(savedAccountDetails);

                        return Mono.just(savedAccountDetails);
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
     * {@code GET  /account-details} : get all the accountDetails.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of accountDetails in body.
     */
    @GetMapping("/account-details")
    public Mono<List<AccountDetails>> getAllAccountDetails() {
        log.debug("REST request to get all AccountDetails");
        return accountDetailsRepository.findAll().collectList();
    }

    /**
     * {@code GET  /account-details} : get all the accountDetails as a stream.
     * @return the {@link Flux} of accountDetails.
     */
    @GetMapping(value = "/account-details", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<AccountDetails> getAllAccountDetailsAsStream() {
        log.debug("REST request to get all AccountDetails as a stream");
        return accountDetailsRepository.findAll();
    }

    /**
     * {@code GET  /account-details/:id} : get the "id" accountDetails.
     *
     * @param id the id of the accountDetails to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the accountDetails, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/account-details/{id}")
    public Mono<ResponseEntity<AccountDetails>> getAccountDetails(@PathVariable Long id) {
        log.debug("REST request to get AccountDetails : {}", id);
        Mono<AccountDetails> accountDetails = accountDetailsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(accountDetails);
    }

    /**
     * {@code DELETE  /account-details/:id} : delete the "id" accountDetails.
     *
     * @param id the id of the accountDetails to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/account-details/{id}")
    public Mono<ResponseEntity<Void>> deleteAccountDetails(@PathVariable Long id) {
        log.debug("REST request to delete AccountDetails : {}", id);
        return accountDetailsRepository
            .deleteById(id)
            .then(accountDetailsSearchRepository.deleteById(id))
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
     * {@code SEARCH  /_search/account-details?query=:query} : search for the accountDetails corresponding
     * to the query.
     *
     * @param query the query of the accountDetails search.
     * @return the result of the search.
     */
    @GetMapping("/_search/account-details")
    public Mono<List<AccountDetails>> searchAccountDetails(@RequestParam String query) {
        log.debug("REST request to search AccountDetails for query {}", query);
        return accountDetailsSearchRepository.search(query).collectList();
    }
}
