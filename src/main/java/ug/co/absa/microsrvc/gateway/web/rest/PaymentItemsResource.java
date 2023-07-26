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
import ug.co.absa.microsrvc.gateway.domain.PaymentItems;
import ug.co.absa.microsrvc.gateway.repository.PaymentItemsRepository;
import ug.co.absa.microsrvc.gateway.repository.search.PaymentItemsSearchRepository;
import ug.co.absa.microsrvc.gateway.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link ug.co.absa.microsrvc.gateway.domain.PaymentItems}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PaymentItemsResource {

    private final Logger log = LoggerFactory.getLogger(PaymentItemsResource.class);

    private static final String ENTITY_NAME = "paymentItems";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PaymentItemsRepository paymentItemsRepository;

    private final PaymentItemsSearchRepository paymentItemsSearchRepository;

    public PaymentItemsResource(PaymentItemsRepository paymentItemsRepository, PaymentItemsSearchRepository paymentItemsSearchRepository) {
        this.paymentItemsRepository = paymentItemsRepository;
        this.paymentItemsSearchRepository = paymentItemsSearchRepository;
    }

    /**
     * {@code POST  /payment-items} : Create a new paymentItems.
     *
     * @param paymentItems the paymentItems to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paymentItems, or with status {@code 400 (Bad Request)} if the paymentItems has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/payment-items")
    public Mono<ResponseEntity<PaymentItems>> createPaymentItems(@Valid @RequestBody PaymentItems paymentItems) throws URISyntaxException {
        log.debug("REST request to save PaymentItems : {}", paymentItems);
        if (paymentItems.getId() != null) {
            throw new BadRequestAlertException("A new paymentItems cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return paymentItemsRepository
            .save(paymentItems)
            .flatMap(paymentItemsSearchRepository::save)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/payment-items/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /payment-items/:id} : Updates an existing paymentItems.
     *
     * @param id the id of the paymentItems to save.
     * @param paymentItems the paymentItems to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paymentItems,
     * or with status {@code 400 (Bad Request)} if the paymentItems is not valid,
     * or with status {@code 500 (Internal Server Error)} if the paymentItems couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/payment-items/{id}")
    public Mono<ResponseEntity<PaymentItems>> updatePaymentItems(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody PaymentItems paymentItems
    ) throws URISyntaxException {
        log.debug("REST request to update PaymentItems : {}, {}", id, paymentItems);
        if (paymentItems.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, paymentItems.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return paymentItemsRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return paymentItemsRepository
                    .save(paymentItems)
                    .flatMap(paymentItemsSearchRepository::save)
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
     * {@code PATCH  /payment-items/:id} : Partial updates given fields of an existing paymentItems, field will ignore if it is null
     *
     * @param id the id of the paymentItems to save.
     * @param paymentItems the paymentItems to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paymentItems,
     * or with status {@code 400 (Bad Request)} if the paymentItems is not valid,
     * or with status {@code 404 (Not Found)} if the paymentItems is not found,
     * or with status {@code 500 (Internal Server Error)} if the paymentItems couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/payment-items/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<PaymentItems>> partialUpdatePaymentItems(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody PaymentItems paymentItems
    ) throws URISyntaxException {
        log.debug("REST request to partial update PaymentItems partially : {}, {}", id, paymentItems);
        if (paymentItems.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, paymentItems.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return paymentItemsRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<PaymentItems> result = paymentItemsRepository
                    .findById(paymentItems.getId())
                    .map(existingPaymentItems -> {
                        if (paymentItems.getAbsaTranRef() != null) {
                            existingPaymentItems.setAbsaTranRef(paymentItems.getAbsaTranRef());
                        }
                        if (paymentItems.getRecordId() != null) {
                            existingPaymentItems.setRecordId(paymentItems.getRecordId());
                        }
                        if (paymentItems.getProductCategoryId() != null) {
                            existingPaymentItems.setProductCategoryId(paymentItems.getProductCategoryId());
                        }
                        if (paymentItems.getBillerId() != null) {
                            existingPaymentItems.setBillerId(paymentItems.getBillerId());
                        }
                        if (paymentItems.getPaymentItemCode() != null) {
                            existingPaymentItems.setPaymentItemCode(paymentItems.getPaymentItemCode());
                        }
                        if (paymentItems.getPaymentItemId() != null) {
                            existingPaymentItems.setPaymentItemId(paymentItems.getPaymentItemId());
                        }
                        if (paymentItems.getPaymentItemName() != null) {
                            existingPaymentItems.setPaymentItemName(paymentItems.getPaymentItemName());
                        }
                        if (paymentItems.getPaymentItemDescription() != null) {
                            existingPaymentItems.setPaymentItemDescription(paymentItems.getPaymentItemDescription());
                        }
                        if (paymentItems.getIsActive() != null) {
                            existingPaymentItems.setIsActive(paymentItems.getIsActive());
                        }
                        if (paymentItems.getHasFixedPrice() != null) {
                            existingPaymentItems.setHasFixedPrice(paymentItems.getHasFixedPrice());
                        }
                        if (paymentItems.getHasVariablePrice() != null) {
                            existingPaymentItems.setHasVariablePrice(paymentItems.getHasVariablePrice());
                        }
                        if (paymentItems.getHasDiscount() != null) {
                            existingPaymentItems.setHasDiscount(paymentItems.getHasDiscount());
                        }
                        if (paymentItems.getHasTax() != null) {
                            existingPaymentItems.setHasTax(paymentItems.getHasTax());
                        }
                        if (paymentItems.getAmount() != null) {
                            existingPaymentItems.setAmount(paymentItems.getAmount());
                        }
                        if (paymentItems.getChargeAmount() != null) {
                            existingPaymentItems.setChargeAmount(paymentItems.getChargeAmount());
                        }
                        if (paymentItems.getHasChargeAmount() != null) {
                            existingPaymentItems.setHasChargeAmount(paymentItems.getHasChargeAmount());
                        }
                        if (paymentItems.getTaxAmount() != null) {
                            existingPaymentItems.setTaxAmount(paymentItems.getTaxAmount());
                        }
                        if (paymentItems.getFreeText() != null) {
                            existingPaymentItems.setFreeText(paymentItems.getFreeText());
                        }
                        if (paymentItems.getFreeText1() != null) {
                            existingPaymentItems.setFreeText1(paymentItems.getFreeText1());
                        }
                        if (paymentItems.getFreeText2() != null) {
                            existingPaymentItems.setFreeText2(paymentItems.getFreeText2());
                        }
                        if (paymentItems.getFreeText3() != null) {
                            existingPaymentItems.setFreeText3(paymentItems.getFreeText3());
                        }
                        if (paymentItems.getFreeText4() != null) {
                            existingPaymentItems.setFreeText4(paymentItems.getFreeText4());
                        }
                        if (paymentItems.getFreeText5() != null) {
                            existingPaymentItems.setFreeText5(paymentItems.getFreeText5());
                        }
                        if (paymentItems.getFreeText6() != null) {
                            existingPaymentItems.setFreeText6(paymentItems.getFreeText6());
                        }
                        if (paymentItems.getFreeText7() != null) {
                            existingPaymentItems.setFreeText7(paymentItems.getFreeText7());
                        }
                        if (paymentItems.getFreeText8() != null) {
                            existingPaymentItems.setFreeText8(paymentItems.getFreeText8());
                        }
                        if (paymentItems.getFreeText9() != null) {
                            existingPaymentItems.setFreeText9(paymentItems.getFreeText9());
                        }
                        if (paymentItems.getFreeText10() != null) {
                            existingPaymentItems.setFreeText10(paymentItems.getFreeText10());
                        }
                        if (paymentItems.getFreeText11() != null) {
                            existingPaymentItems.setFreeText11(paymentItems.getFreeText11());
                        }
                        if (paymentItems.getFreeText12() != null) {
                            existingPaymentItems.setFreeText12(paymentItems.getFreeText12());
                        }
                        if (paymentItems.getFreeText13() != null) {
                            existingPaymentItems.setFreeText13(paymentItems.getFreeText13());
                        }
                        if (paymentItems.getFreeText14() != null) {
                            existingPaymentItems.setFreeText14(paymentItems.getFreeText14());
                        }
                        if (paymentItems.getFreeText15() != null) {
                            existingPaymentItems.setFreeText15(paymentItems.getFreeText15());
                        }
                        if (paymentItems.getFreeText16() != null) {
                            existingPaymentItems.setFreeText16(paymentItems.getFreeText16());
                        }
                        if (paymentItems.getFreeText17() != null) {
                            existingPaymentItems.setFreeText17(paymentItems.getFreeText17());
                        }
                        if (paymentItems.getFreeText18() != null) {
                            existingPaymentItems.setFreeText18(paymentItems.getFreeText18());
                        }
                        if (paymentItems.getFreeText19() != null) {
                            existingPaymentItems.setFreeText19(paymentItems.getFreeText19());
                        }
                        if (paymentItems.getDelflg() != null) {
                            existingPaymentItems.setDelflg(paymentItems.getDelflg());
                        }
                        if (paymentItems.getStatus() != null) {
                            existingPaymentItems.setStatus(paymentItems.getStatus());
                        }
                        if (paymentItems.getCreatedAt() != null) {
                            existingPaymentItems.setCreatedAt(paymentItems.getCreatedAt());
                        }
                        if (paymentItems.getUpdatedAt() != null) {
                            existingPaymentItems.setUpdatedAt(paymentItems.getUpdatedAt());
                        }
                        if (paymentItems.getDeletedAt() != null) {
                            existingPaymentItems.setDeletedAt(paymentItems.getDeletedAt());
                        }
                        if (paymentItems.getDeletedBy() != null) {
                            existingPaymentItems.setDeletedBy(paymentItems.getDeletedBy());
                        }

                        return existingPaymentItems;
                    })
                    .flatMap(paymentItemsRepository::save)
                    .flatMap(savedPaymentItems -> {
                        paymentItemsSearchRepository.save(savedPaymentItems);

                        return Mono.just(savedPaymentItems);
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
     * {@code GET  /payment-items} : get all the paymentItems.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of paymentItems in body.
     */
    @GetMapping("/payment-items")
    public Mono<List<PaymentItems>> getAllPaymentItems() {
        log.debug("REST request to get all PaymentItems");
        return paymentItemsRepository.findAll().collectList();
    }

    /**
     * {@code GET  /payment-items} : get all the paymentItems as a stream.
     * @return the {@link Flux} of paymentItems.
     */
    @GetMapping(value = "/payment-items", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<PaymentItems> getAllPaymentItemsAsStream() {
        log.debug("REST request to get all PaymentItems as a stream");
        return paymentItemsRepository.findAll();
    }

    /**
     * {@code GET  /payment-items/:id} : get the "id" paymentItems.
     *
     * @param id the id of the paymentItems to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paymentItems, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/payment-items/{id}")
    public Mono<ResponseEntity<PaymentItems>> getPaymentItems(@PathVariable Long id) {
        log.debug("REST request to get PaymentItems : {}", id);
        Mono<PaymentItems> paymentItems = paymentItemsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(paymentItems);
    }

    /**
     * {@code DELETE  /payment-items/:id} : delete the "id" paymentItems.
     *
     * @param id the id of the paymentItems to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/payment-items/{id}")
    public Mono<ResponseEntity<Void>> deletePaymentItems(@PathVariable Long id) {
        log.debug("REST request to delete PaymentItems : {}", id);
        return paymentItemsRepository
            .deleteById(id)
            .then(paymentItemsSearchRepository.deleteById(id))
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
     * {@code SEARCH  /_search/payment-items?query=:query} : search for the paymentItems corresponding
     * to the query.
     *
     * @param query the query of the paymentItems search.
     * @return the result of the search.
     */
    @GetMapping("/_search/payment-items")
    public Mono<List<PaymentItems>> searchPaymentItems(@RequestParam String query) {
        log.debug("REST request to search PaymentItems for query {}", query);
        return paymentItemsSearchRepository.search(query).collectList();
    }
}
