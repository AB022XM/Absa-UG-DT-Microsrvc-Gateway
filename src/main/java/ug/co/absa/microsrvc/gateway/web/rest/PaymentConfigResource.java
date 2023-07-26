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
import ug.co.absa.microsrvc.gateway.domain.PaymentConfig;
import ug.co.absa.microsrvc.gateway.repository.PaymentConfigRepository;
import ug.co.absa.microsrvc.gateway.repository.search.PaymentConfigSearchRepository;
import ug.co.absa.microsrvc.gateway.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link ug.co.absa.microsrvc.gateway.domain.PaymentConfig}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class PaymentConfigResource {

    private final Logger log = LoggerFactory.getLogger(PaymentConfigResource.class);

    private static final String ENTITY_NAME = "paymentConfig";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PaymentConfigRepository paymentConfigRepository;

    private final PaymentConfigSearchRepository paymentConfigSearchRepository;

    public PaymentConfigResource(
        PaymentConfigRepository paymentConfigRepository,
        PaymentConfigSearchRepository paymentConfigSearchRepository
    ) {
        this.paymentConfigRepository = paymentConfigRepository;
        this.paymentConfigSearchRepository = paymentConfigSearchRepository;
    }

    /**
     * {@code POST  /payment-configs} : Create a new paymentConfig.
     *
     * @param paymentConfig the paymentConfig to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new paymentConfig, or with status {@code 400 (Bad Request)} if the paymentConfig has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/payment-configs")
    public Mono<ResponseEntity<PaymentConfig>> createPaymentConfig(@Valid @RequestBody PaymentConfig paymentConfig)
        throws URISyntaxException {
        log.debug("REST request to save PaymentConfig : {}", paymentConfig);
        if (paymentConfig.getId() != null) {
            throw new BadRequestAlertException("A new paymentConfig cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return paymentConfigRepository
            .save(paymentConfig)
            .flatMap(paymentConfigSearchRepository::save)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/payment-configs/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /payment-configs/:id} : Updates an existing paymentConfig.
     *
     * @param id the id of the paymentConfig to save.
     * @param paymentConfig the paymentConfig to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paymentConfig,
     * or with status {@code 400 (Bad Request)} if the paymentConfig is not valid,
     * or with status {@code 500 (Internal Server Error)} if the paymentConfig couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/payment-configs/{id}")
    public Mono<ResponseEntity<PaymentConfig>> updatePaymentConfig(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody PaymentConfig paymentConfig
    ) throws URISyntaxException {
        log.debug("REST request to update PaymentConfig : {}, {}", id, paymentConfig);
        if (paymentConfig.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, paymentConfig.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return paymentConfigRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return paymentConfigRepository
                    .save(paymentConfig)
                    .flatMap(paymentConfigSearchRepository::save)
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
     * {@code PATCH  /payment-configs/:id} : Partial updates given fields of an existing paymentConfig, field will ignore if it is null
     *
     * @param id the id of the paymentConfig to save.
     * @param paymentConfig the paymentConfig to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated paymentConfig,
     * or with status {@code 400 (Bad Request)} if the paymentConfig is not valid,
     * or with status {@code 404 (Not Found)} if the paymentConfig is not found,
     * or with status {@code 500 (Internal Server Error)} if the paymentConfig couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/payment-configs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<PaymentConfig>> partialUpdatePaymentConfig(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody PaymentConfig paymentConfig
    ) throws URISyntaxException {
        log.debug("REST request to partial update PaymentConfig partially : {}, {}", id, paymentConfig);
        if (paymentConfig.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, paymentConfig.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return paymentConfigRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<PaymentConfig> result = paymentConfigRepository
                    .findById(paymentConfig.getId())
                    .map(existingPaymentConfig -> {
                        if (paymentConfig.getRecordId() != null) {
                            existingPaymentConfig.setRecordId(paymentConfig.getRecordId());
                        }
                        if (paymentConfig.getPaymentId() != null) {
                            existingPaymentConfig.setPaymentId(paymentConfig.getPaymentId());
                        }
                        if (paymentConfig.getPaymentName() != null) {
                            existingPaymentConfig.setPaymentName(paymentConfig.getPaymentName());
                        }
                        if (paymentConfig.getPaymentType() != null) {
                            existingPaymentConfig.setPaymentType(paymentConfig.getPaymentType());
                        }
                        if (paymentConfig.getPaymentDescription() != null) {
                            existingPaymentConfig.setPaymentDescription(paymentConfig.getPaymentDescription());
                        }
                        if (paymentConfig.getPaymentStatus() != null) {
                            existingPaymentConfig.setPaymentStatus(paymentConfig.getPaymentStatus());
                        }
                        if (paymentConfig.getPaymentMethod() != null) {
                            existingPaymentConfig.setPaymentMethod(paymentConfig.getPaymentMethod());
                        }
                        if (paymentConfig.getPaymentAmount() != null) {
                            existingPaymentConfig.setPaymentAmount(paymentConfig.getPaymentAmount());
                        }
                        if (paymentConfig.getAdditionalConfig() != null) {
                            existingPaymentConfig.setAdditionalConfig(paymentConfig.getAdditionalConfig());
                        }
                        if (paymentConfig.getFreeField1() != null) {
                            existingPaymentConfig.setFreeField1(paymentConfig.getFreeField1());
                        }
                        if (paymentConfig.getFreeField2() != null) {
                            existingPaymentConfig.setFreeField2(paymentConfig.getFreeField2());
                        }
                        if (paymentConfig.getFreeField3() != null) {
                            existingPaymentConfig.setFreeField3(paymentConfig.getFreeField3());
                        }
                        if (paymentConfig.getFreeField4() != null) {
                            existingPaymentConfig.setFreeField4(paymentConfig.getFreeField4());
                        }
                        if (paymentConfig.getFreeField5() != null) {
                            existingPaymentConfig.setFreeField5(paymentConfig.getFreeField5());
                        }
                        if (paymentConfig.getFreeField6() != null) {
                            existingPaymentConfig.setFreeField6(paymentConfig.getFreeField6());
                        }
                        if (paymentConfig.getFreeField8() != null) {
                            existingPaymentConfig.setFreeField8(paymentConfig.getFreeField8());
                        }
                        if (paymentConfig.getFreeField9() != null) {
                            existingPaymentConfig.setFreeField9(paymentConfig.getFreeField9());
                        }
                        if (paymentConfig.getFreeField10() != null) {
                            existingPaymentConfig.setFreeField10(paymentConfig.getFreeField10());
                        }
                        if (paymentConfig.getFreeField11() != null) {
                            existingPaymentConfig.setFreeField11(paymentConfig.getFreeField11());
                        }
                        if (paymentConfig.getFreeField12() != null) {
                            existingPaymentConfig.setFreeField12(paymentConfig.getFreeField12());
                        }
                        if (paymentConfig.getFreeField13() != null) {
                            existingPaymentConfig.setFreeField13(paymentConfig.getFreeField13());
                        }
                        if (paymentConfig.getFreeField14() != null) {
                            existingPaymentConfig.setFreeField14(paymentConfig.getFreeField14());
                        }
                        if (paymentConfig.getFreeField15() != null) {
                            existingPaymentConfig.setFreeField15(paymentConfig.getFreeField15());
                        }
                        if (paymentConfig.getFreeField15ContentType() != null) {
                            existingPaymentConfig.setFreeField15ContentType(paymentConfig.getFreeField15ContentType());
                        }
                        if (paymentConfig.getFreeField16() != null) {
                            existingPaymentConfig.setFreeField16(paymentConfig.getFreeField16());
                        }
                        if (paymentConfig.getFreeField17() != null) {
                            existingPaymentConfig.setFreeField17(paymentConfig.getFreeField17());
                        }
                        if (paymentConfig.getFreeField18() != null) {
                            existingPaymentConfig.setFreeField18(paymentConfig.getFreeField18());
                        }
                        if (paymentConfig.getFreeField18ContentType() != null) {
                            existingPaymentConfig.setFreeField18ContentType(paymentConfig.getFreeField18ContentType());
                        }
                        if (paymentConfig.getFreeField19() != null) {
                            existingPaymentConfig.setFreeField19(paymentConfig.getFreeField19());
                        }
                        if (paymentConfig.getFreeField20() != null) {
                            existingPaymentConfig.setFreeField20(paymentConfig.getFreeField20());
                        }
                        if (paymentConfig.getTimestamp() != null) {
                            existingPaymentConfig.setTimestamp(paymentConfig.getTimestamp());
                        }
                        if (paymentConfig.getCreatedAt() != null) {
                            existingPaymentConfig.setCreatedAt(paymentConfig.getCreatedAt());
                        }
                        if (paymentConfig.getCreatedBy() != null) {
                            existingPaymentConfig.setCreatedBy(paymentConfig.getCreatedBy());
                        }
                        if (paymentConfig.getUpdatedAt() != null) {
                            existingPaymentConfig.setUpdatedAt(paymentConfig.getUpdatedAt());
                        }
                        if (paymentConfig.getUpdatedBy() != null) {
                            existingPaymentConfig.setUpdatedBy(paymentConfig.getUpdatedBy());
                        }

                        return existingPaymentConfig;
                    })
                    .flatMap(paymentConfigRepository::save)
                    .flatMap(savedPaymentConfig -> {
                        paymentConfigSearchRepository.save(savedPaymentConfig);

                        return Mono.just(savedPaymentConfig);
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
     * {@code GET  /payment-configs} : get all the paymentConfigs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of paymentConfigs in body.
     */
    @GetMapping("/payment-configs")
    public Mono<List<PaymentConfig>> getAllPaymentConfigs() {
        log.debug("REST request to get all PaymentConfigs");
        return paymentConfigRepository.findAll().collectList();
    }

    /**
     * {@code GET  /payment-configs} : get all the paymentConfigs as a stream.
     * @return the {@link Flux} of paymentConfigs.
     */
    @GetMapping(value = "/payment-configs", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<PaymentConfig> getAllPaymentConfigsAsStream() {
        log.debug("REST request to get all PaymentConfigs as a stream");
        return paymentConfigRepository.findAll();
    }

    /**
     * {@code GET  /payment-configs/:id} : get the "id" paymentConfig.
     *
     * @param id the id of the paymentConfig to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the paymentConfig, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/payment-configs/{id}")
    public Mono<ResponseEntity<PaymentConfig>> getPaymentConfig(@PathVariable Long id) {
        log.debug("REST request to get PaymentConfig : {}", id);
        Mono<PaymentConfig> paymentConfig = paymentConfigRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(paymentConfig);
    }

    /**
     * {@code DELETE  /payment-configs/:id} : delete the "id" paymentConfig.
     *
     * @param id the id of the paymentConfig to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/payment-configs/{id}")
    public Mono<ResponseEntity<Void>> deletePaymentConfig(@PathVariable Long id) {
        log.debug("REST request to delete PaymentConfig : {}", id);
        return paymentConfigRepository
            .deleteById(id)
            .then(paymentConfigSearchRepository.deleteById(id))
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
     * {@code SEARCH  /_search/payment-configs?query=:query} : search for the paymentConfig corresponding
     * to the query.
     *
     * @param query the query of the paymentConfig search.
     * @return the result of the search.
     */
    @GetMapping("/_search/payment-configs")
    public Mono<List<PaymentConfig>> searchPaymentConfigs(@RequestParam String query) {
        log.debug("REST request to search PaymentConfigs for query {}", query);
        return paymentConfigSearchRepository.search(query).collectList();
    }
}
