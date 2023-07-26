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
import ug.co.absa.microsrvc.gateway.domain.DTransactionSummary;
import ug.co.absa.microsrvc.gateway.repository.DTransactionSummaryRepository;
import ug.co.absa.microsrvc.gateway.repository.search.DTransactionSummarySearchRepository;
import ug.co.absa.microsrvc.gateway.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link ug.co.absa.microsrvc.gateway.domain.DTransactionSummary}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class DTransactionSummaryResource {

    private final Logger log = LoggerFactory.getLogger(DTransactionSummaryResource.class);

    private static final String ENTITY_NAME = "dTransactionSummary";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DTransactionSummaryRepository dTransactionSummaryRepository;

    private final DTransactionSummarySearchRepository dTransactionSummarySearchRepository;

    public DTransactionSummaryResource(
        DTransactionSummaryRepository dTransactionSummaryRepository,
        DTransactionSummarySearchRepository dTransactionSummarySearchRepository
    ) {
        this.dTransactionSummaryRepository = dTransactionSummaryRepository;
        this.dTransactionSummarySearchRepository = dTransactionSummarySearchRepository;
    }

    /**
     * {@code POST  /d-transaction-summaries} : Create a new dTransactionSummary.
     *
     * @param dTransactionSummary the dTransactionSummary to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dTransactionSummary, or with status {@code 400 (Bad Request)} if the dTransactionSummary has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/d-transaction-summaries")
    public Mono<ResponseEntity<DTransactionSummary>> createDTransactionSummary(@Valid @RequestBody DTransactionSummary dTransactionSummary)
        throws URISyntaxException {
        log.debug("REST request to save DTransactionSummary : {}", dTransactionSummary);
        if (dTransactionSummary.getId() != null) {
            throw new BadRequestAlertException("A new dTransactionSummary cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return dTransactionSummaryRepository
            .save(dTransactionSummary)
            .flatMap(dTransactionSummarySearchRepository::save)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/d-transaction-summaries/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /d-transaction-summaries/:id} : Updates an existing dTransactionSummary.
     *
     * @param id the id of the dTransactionSummary to save.
     * @param dTransactionSummary the dTransactionSummary to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dTransactionSummary,
     * or with status {@code 400 (Bad Request)} if the dTransactionSummary is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dTransactionSummary couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/d-transaction-summaries/{id}")
    public Mono<ResponseEntity<DTransactionSummary>> updateDTransactionSummary(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody DTransactionSummary dTransactionSummary
    ) throws URISyntaxException {
        log.debug("REST request to update DTransactionSummary : {}, {}", id, dTransactionSummary);
        if (dTransactionSummary.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dTransactionSummary.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return dTransactionSummaryRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return dTransactionSummaryRepository
                    .save(dTransactionSummary)
                    .flatMap(dTransactionSummarySearchRepository::save)
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
     * {@code PATCH  /d-transaction-summaries/:id} : Partial updates given fields of an existing dTransactionSummary, field will ignore if it is null
     *
     * @param id the id of the dTransactionSummary to save.
     * @param dTransactionSummary the dTransactionSummary to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dTransactionSummary,
     * or with status {@code 400 (Bad Request)} if the dTransactionSummary is not valid,
     * or with status {@code 404 (Not Found)} if the dTransactionSummary is not found,
     * or with status {@code 500 (Internal Server Error)} if the dTransactionSummary couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/d-transaction-summaries/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<DTransactionSummary>> partialUpdateDTransactionSummary(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody DTransactionSummary dTransactionSummary
    ) throws URISyntaxException {
        log.debug("REST request to partial update DTransactionSummary partially : {}, {}", id, dTransactionSummary);
        if (dTransactionSummary.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dTransactionSummary.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return dTransactionSummaryRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<DTransactionSummary> result = dTransactionSummaryRepository
                    .findById(dTransactionSummary.getId())
                    .map(existingDTransactionSummary -> {
                        if (dTransactionSummary.getAbsaTranRef() != null) {
                            existingDTransactionSummary.setAbsaTranRef(dTransactionSummary.getAbsaTranRef());
                        }
                        if (dTransactionSummary.getBillerId() != null) {
                            existingDTransactionSummary.setBillerId(dTransactionSummary.getBillerId());
                        }
                        if (dTransactionSummary.getPaymentItemCode() != null) {
                            existingDTransactionSummary.setPaymentItemCode(dTransactionSummary.getPaymentItemCode());
                        }
                        if (dTransactionSummary.getDtDTransactionId() != null) {
                            existingDTransactionSummary.setDtDTransactionId(dTransactionSummary.getDtDTransactionId());
                        }
                        if (dTransactionSummary.getTransactionReferenceNumber() != null) {
                            existingDTransactionSummary.setTransactionReferenceNumber(dTransactionSummary.getTransactionReferenceNumber());
                        }
                        if (dTransactionSummary.getRecordId() != null) {
                            existingDTransactionSummary.setRecordId(dTransactionSummary.getRecordId());
                        }
                        if (dTransactionSummary.getTransactionId() != null) {
                            existingDTransactionSummary.setTransactionId(dTransactionSummary.getTransactionId());
                        }
                        if (dTransactionSummary.getTransactionType() != null) {
                            existingDTransactionSummary.setTransactionType(dTransactionSummary.getTransactionType());
                        }
                        if (dTransactionSummary.getTransactionAmount() != null) {
                            existingDTransactionSummary.setTransactionAmount(dTransactionSummary.getTransactionAmount());
                        }
                        if (dTransactionSummary.getTransactionDate() != null) {
                            existingDTransactionSummary.setTransactionDate(dTransactionSummary.getTransactionDate());
                        }
                        if (dTransactionSummary.getTransactionStatus() != null) {
                            existingDTransactionSummary.setTransactionStatus(dTransactionSummary.getTransactionStatus());
                        }
                        if (dTransactionSummary.getFreeField1() != null) {
                            existingDTransactionSummary.setFreeField1(dTransactionSummary.getFreeField1());
                        }
                        if (dTransactionSummary.getFreeField2() != null) {
                            existingDTransactionSummary.setFreeField2(dTransactionSummary.getFreeField2());
                        }
                        if (dTransactionSummary.getFreeField3() != null) {
                            existingDTransactionSummary.setFreeField3(dTransactionSummary.getFreeField3());
                        }
                        if (dTransactionSummary.getFreeField4() != null) {
                            existingDTransactionSummary.setFreeField4(dTransactionSummary.getFreeField4());
                        }
                        if (dTransactionSummary.getFreeField5() != null) {
                            existingDTransactionSummary.setFreeField5(dTransactionSummary.getFreeField5());
                        }
                        if (dTransactionSummary.getFreeField6() != null) {
                            existingDTransactionSummary.setFreeField6(dTransactionSummary.getFreeField6());
                        }
                        if (dTransactionSummary.getFreeField6ContentType() != null) {
                            existingDTransactionSummary.setFreeField6ContentType(dTransactionSummary.getFreeField6ContentType());
                        }
                        if (dTransactionSummary.getCreatedAt() != null) {
                            existingDTransactionSummary.setCreatedAt(dTransactionSummary.getCreatedAt());
                        }
                        if (dTransactionSummary.getCreatedBy() != null) {
                            existingDTransactionSummary.setCreatedBy(dTransactionSummary.getCreatedBy());
                        }
                        if (dTransactionSummary.getUpdatedAt() != null) {
                            existingDTransactionSummary.setUpdatedAt(dTransactionSummary.getUpdatedAt());
                        }
                        if (dTransactionSummary.getUpdatedBy() != null) {
                            existingDTransactionSummary.setUpdatedBy(dTransactionSummary.getUpdatedBy());
                        }
                        if (dTransactionSummary.getErrorCode() != null) {
                            existingDTransactionSummary.setErrorCode(dTransactionSummary.getErrorCode());
                        }
                        if (dTransactionSummary.getErrorMessage() != null) {
                            existingDTransactionSummary.setErrorMessage(dTransactionSummary.getErrorMessage());
                        }

                        return existingDTransactionSummary;
                    })
                    .flatMap(dTransactionSummaryRepository::save)
                    .flatMap(savedDTransactionSummary -> {
                        dTransactionSummarySearchRepository.save(savedDTransactionSummary);

                        return Mono.just(savedDTransactionSummary);
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
     * {@code GET  /d-transaction-summaries} : get all the dTransactionSummaries.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dTransactionSummaries in body.
     */
    @GetMapping("/d-transaction-summaries")
    public Mono<List<DTransactionSummary>> getAllDTransactionSummaries() {
        log.debug("REST request to get all DTransactionSummaries");
        return dTransactionSummaryRepository.findAll().collectList();
    }

    /**
     * {@code GET  /d-transaction-summaries} : get all the dTransactionSummaries as a stream.
     * @return the {@link Flux} of dTransactionSummaries.
     */
    @GetMapping(value = "/d-transaction-summaries", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<DTransactionSummary> getAllDTransactionSummariesAsStream() {
        log.debug("REST request to get all DTransactionSummaries as a stream");
        return dTransactionSummaryRepository.findAll();
    }

    /**
     * {@code GET  /d-transaction-summaries/:id} : get the "id" dTransactionSummary.
     *
     * @param id the id of the dTransactionSummary to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dTransactionSummary, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/d-transaction-summaries/{id}")
    public Mono<ResponseEntity<DTransactionSummary>> getDTransactionSummary(@PathVariable Long id) {
        log.debug("REST request to get DTransactionSummary : {}", id);
        Mono<DTransactionSummary> dTransactionSummary = dTransactionSummaryRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(dTransactionSummary);
    }

    /**
     * {@code DELETE  /d-transaction-summaries/:id} : delete the "id" dTransactionSummary.
     *
     * @param id the id of the dTransactionSummary to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/d-transaction-summaries/{id}")
    public Mono<ResponseEntity<Void>> deleteDTransactionSummary(@PathVariable Long id) {
        log.debug("REST request to delete DTransactionSummary : {}", id);
        return dTransactionSummaryRepository
            .deleteById(id)
            .then(dTransactionSummarySearchRepository.deleteById(id))
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
     * {@code SEARCH  /_search/d-transaction-summaries?query=:query} : search for the dTransactionSummary corresponding
     * to the query.
     *
     * @param query the query of the dTransactionSummary search.
     * @return the result of the search.
     */
    @GetMapping("/_search/d-transaction-summaries")
    public Mono<List<DTransactionSummary>> searchDTransactionSummaries(@RequestParam String query) {
        log.debug("REST request to search DTransactionSummaries for query {}", query);
        return dTransactionSummarySearchRepository.search(query).collectList();
    }
}
