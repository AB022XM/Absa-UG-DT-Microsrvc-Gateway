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
import ug.co.absa.microsrvc.gateway.domain.ErrorCodes;
import ug.co.absa.microsrvc.gateway.repository.ErrorCodesRepository;
import ug.co.absa.microsrvc.gateway.repository.search.ErrorCodesSearchRepository;
import ug.co.absa.microsrvc.gateway.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link ug.co.absa.microsrvc.gateway.domain.ErrorCodes}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ErrorCodesResource {

    private final Logger log = LoggerFactory.getLogger(ErrorCodesResource.class);

    private static final String ENTITY_NAME = "errorCodes";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ErrorCodesRepository errorCodesRepository;

    private final ErrorCodesSearchRepository errorCodesSearchRepository;

    public ErrorCodesResource(ErrorCodesRepository errorCodesRepository, ErrorCodesSearchRepository errorCodesSearchRepository) {
        this.errorCodesRepository = errorCodesRepository;
        this.errorCodesSearchRepository = errorCodesSearchRepository;
    }

    /**
     * {@code POST  /error-codes} : Create a new errorCodes.
     *
     * @param errorCodes the errorCodes to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new errorCodes, or with status {@code 400 (Bad Request)} if the errorCodes has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/error-codes")
    public Mono<ResponseEntity<ErrorCodes>> createErrorCodes(@Valid @RequestBody ErrorCodes errorCodes) throws URISyntaxException {
        log.debug("REST request to save ErrorCodes : {}", errorCodes);
        if (errorCodes.getId() != null) {
            throw new BadRequestAlertException("A new errorCodes cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return errorCodesRepository
            .save(errorCodes)
            .flatMap(errorCodesSearchRepository::save)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/error-codes/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /error-codes/:id} : Updates an existing errorCodes.
     *
     * @param id the id of the errorCodes to save.
     * @param errorCodes the errorCodes to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated errorCodes,
     * or with status {@code 400 (Bad Request)} if the errorCodes is not valid,
     * or with status {@code 500 (Internal Server Error)} if the errorCodes couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/error-codes/{id}")
    public Mono<ResponseEntity<ErrorCodes>> updateErrorCodes(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ErrorCodes errorCodes
    ) throws URISyntaxException {
        log.debug("REST request to update ErrorCodes : {}, {}", id, errorCodes);
        if (errorCodes.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, errorCodes.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return errorCodesRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return errorCodesRepository
                    .save(errorCodes)
                    .flatMap(errorCodesSearchRepository::save)
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
     * {@code PATCH  /error-codes/:id} : Partial updates given fields of an existing errorCodes, field will ignore if it is null
     *
     * @param id the id of the errorCodes to save.
     * @param errorCodes the errorCodes to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated errorCodes,
     * or with status {@code 400 (Bad Request)} if the errorCodes is not valid,
     * or with status {@code 404 (Not Found)} if the errorCodes is not found,
     * or with status {@code 500 (Internal Server Error)} if the errorCodes couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/error-codes/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<ErrorCodes>> partialUpdateErrorCodes(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ErrorCodes errorCodes
    ) throws URISyntaxException {
        log.debug("REST request to partial update ErrorCodes partially : {}, {}", id, errorCodes);
        if (errorCodes.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, errorCodes.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return errorCodesRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<ErrorCodes> result = errorCodesRepository
                    .findById(errorCodes.getId())
                    .map(existingErrorCodes -> {
                        if (errorCodes.getAbsaTranRef() != null) {
                            existingErrorCodes.setAbsaTranRef(errorCodes.getAbsaTranRef());
                        }
                        if (errorCodes.getRecordId() != null) {
                            existingErrorCodes.setRecordId(errorCodes.getRecordId());
                        }
                        if (errorCodes.getPaymentItemCode() != null) {
                            existingErrorCodes.setPaymentItemCode(errorCodes.getPaymentItemCode());
                        }
                        if (errorCodes.getDtDTransactionId() != null) {
                            existingErrorCodes.setDtDTransactionId(errorCodes.getDtDTransactionId());
                        }
                        if (errorCodes.getTransactionReferenceNumber() != null) {
                            existingErrorCodes.setTransactionReferenceNumber(errorCodes.getTransactionReferenceNumber());
                        }
                        if (errorCodes.getExternalTranid() != null) {
                            existingErrorCodes.setExternalTranid(errorCodes.getExternalTranid());
                        }
                        if (errorCodes.getErrorCode() != null) {
                            existingErrorCodes.setErrorCode(errorCodes.getErrorCode());
                        }
                        if (errorCodes.getErrorMessage() != null) {
                            existingErrorCodes.setErrorMessage(errorCodes.getErrorMessage());
                        }
                        if (errorCodes.getResponseCode() != null) {
                            existingErrorCodes.setResponseCode(errorCodes.getResponseCode());
                        }
                        if (errorCodes.getResponseMessage() != null) {
                            existingErrorCodes.setResponseMessage(errorCodes.getResponseMessage());
                        }
                        if (errorCodes.getResponseBody() != null) {
                            existingErrorCodes.setResponseBody(errorCodes.getResponseBody());
                        }
                        if (errorCodes.getTimestamp() != null) {
                            existingErrorCodes.setTimestamp(errorCodes.getTimestamp());
                        }
                        if (errorCodes.getRequestRef() != null) {
                            existingErrorCodes.setRequestRef(errorCodes.getRequestRef());
                        }
                        if (errorCodes.getStatus() != null) {
                            existingErrorCodes.setStatus(errorCodes.getStatus());
                        }
                        if (errorCodes.getCustomerField1() != null) {
                            existingErrorCodes.setCustomerField1(errorCodes.getCustomerField1());
                        }
                        if (errorCodes.getCustomerField2() != null) {
                            existingErrorCodes.setCustomerField2(errorCodes.getCustomerField2());
                        }
                        if (errorCodes.getCustomerField3() != null) {
                            existingErrorCodes.setCustomerField3(errorCodes.getCustomerField3());
                        }
                        if (errorCodes.getCustomerField4() != null) {
                            existingErrorCodes.setCustomerField4(errorCodes.getCustomerField4());
                        }
                        if (errorCodes.getCustomerField5() != null) {
                            existingErrorCodes.setCustomerField5(errorCodes.getCustomerField5());
                        }
                        if (errorCodes.getCustomerField6() != null) {
                            existingErrorCodes.setCustomerField6(errorCodes.getCustomerField6());
                        }
                        if (errorCodes.getCustomerField7() != null) {
                            existingErrorCodes.setCustomerField7(errorCodes.getCustomerField7());
                        }
                        if (errorCodes.getCustomerField8() != null) {
                            existingErrorCodes.setCustomerField8(errorCodes.getCustomerField8());
                        }
                        if (errorCodes.getCreatedAt() != null) {
                            existingErrorCodes.setCreatedAt(errorCodes.getCreatedAt());
                        }
                        if (errorCodes.getCreatedBy() != null) {
                            existingErrorCodes.setCreatedBy(errorCodes.getCreatedBy());
                        }
                        if (errorCodes.getUpdatedAt() != null) {
                            existingErrorCodes.setUpdatedAt(errorCodes.getUpdatedAt());
                        }
                        if (errorCodes.getUpdatedBy() != null) {
                            existingErrorCodes.setUpdatedBy(errorCodes.getUpdatedBy());
                        }

                        return existingErrorCodes;
                    })
                    .flatMap(errorCodesRepository::save)
                    .flatMap(savedErrorCodes -> {
                        errorCodesSearchRepository.save(savedErrorCodes);

                        return Mono.just(savedErrorCodes);
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
     * {@code GET  /error-codes} : get all the errorCodes.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of errorCodes in body.
     */
    @GetMapping("/error-codes")
    public Mono<List<ErrorCodes>> getAllErrorCodes() {
        log.debug("REST request to get all ErrorCodes");
        return errorCodesRepository.findAll().collectList();
    }

    /**
     * {@code GET  /error-codes} : get all the errorCodes as a stream.
     * @return the {@link Flux} of errorCodes.
     */
    @GetMapping(value = "/error-codes", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<ErrorCodes> getAllErrorCodesAsStream() {
        log.debug("REST request to get all ErrorCodes as a stream");
        return errorCodesRepository.findAll();
    }

    /**
     * {@code GET  /error-codes/:id} : get the "id" errorCodes.
     *
     * @param id the id of the errorCodes to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the errorCodes, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/error-codes/{id}")
    public Mono<ResponseEntity<ErrorCodes>> getErrorCodes(@PathVariable Long id) {
        log.debug("REST request to get ErrorCodes : {}", id);
        Mono<ErrorCodes> errorCodes = errorCodesRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(errorCodes);
    }

    /**
     * {@code DELETE  /error-codes/:id} : delete the "id" errorCodes.
     *
     * @param id the id of the errorCodes to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/error-codes/{id}")
    public Mono<ResponseEntity<Void>> deleteErrorCodes(@PathVariable Long id) {
        log.debug("REST request to delete ErrorCodes : {}", id);
        return errorCodesRepository
            .deleteById(id)
            .then(errorCodesSearchRepository.deleteById(id))
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
     * {@code SEARCH  /_search/error-codes?query=:query} : search for the errorCodes corresponding
     * to the query.
     *
     * @param query the query of the errorCodes search.
     * @return the result of the search.
     */
    @GetMapping("/_search/error-codes")
    public Mono<List<ErrorCodes>> searchErrorCodes(@RequestParam String query) {
        log.debug("REST request to search ErrorCodes for query {}", query);
        return errorCodesSearchRepository.search(query).collectList();
    }
}
