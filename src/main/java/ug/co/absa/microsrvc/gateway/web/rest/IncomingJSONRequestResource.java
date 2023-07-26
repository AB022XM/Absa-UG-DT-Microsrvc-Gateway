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
import ug.co.absa.microsrvc.gateway.domain.IncomingJSONRequest;
import ug.co.absa.microsrvc.gateway.repository.IncomingJSONRequestRepository;
import ug.co.absa.microsrvc.gateway.repository.search.IncomingJSONRequestSearchRepository;
import ug.co.absa.microsrvc.gateway.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link ug.co.absa.microsrvc.gateway.domain.IncomingJSONRequest}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class IncomingJSONRequestResource {

    private final Logger log = LoggerFactory.getLogger(IncomingJSONRequestResource.class);

    private static final String ENTITY_NAME = "incomingJSONRequest";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final IncomingJSONRequestRepository incomingJSONRequestRepository;

    private final IncomingJSONRequestSearchRepository incomingJSONRequestSearchRepository;

    public IncomingJSONRequestResource(
        IncomingJSONRequestRepository incomingJSONRequestRepository,
        IncomingJSONRequestSearchRepository incomingJSONRequestSearchRepository
    ) {
        this.incomingJSONRequestRepository = incomingJSONRequestRepository;
        this.incomingJSONRequestSearchRepository = incomingJSONRequestSearchRepository;
    }

    /**
     * {@code POST  /incoming-json-requests} : Create a new incomingJSONRequest.
     *
     * @param incomingJSONRequest the incomingJSONRequest to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new incomingJSONRequest, or with status {@code 400 (Bad Request)} if the incomingJSONRequest has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/incoming-json-requests")
    public Mono<ResponseEntity<IncomingJSONRequest>> createIncomingJSONRequest(@Valid @RequestBody IncomingJSONRequest incomingJSONRequest)
        throws URISyntaxException {
        log.debug("REST request to save IncomingJSONRequest : {}", incomingJSONRequest);
        if (incomingJSONRequest.getId() != null) {
            throw new BadRequestAlertException("A new incomingJSONRequest cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return incomingJSONRequestRepository
            .save(incomingJSONRequest)
            .flatMap(incomingJSONRequestSearchRepository::save)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/incoming-json-requests/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /incoming-json-requests/:id} : Updates an existing incomingJSONRequest.
     *
     * @param id the id of the incomingJSONRequest to save.
     * @param incomingJSONRequest the incomingJSONRequest to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated incomingJSONRequest,
     * or with status {@code 400 (Bad Request)} if the incomingJSONRequest is not valid,
     * or with status {@code 500 (Internal Server Error)} if the incomingJSONRequest couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/incoming-json-requests/{id}")
    public Mono<ResponseEntity<IncomingJSONRequest>> updateIncomingJSONRequest(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody IncomingJSONRequest incomingJSONRequest
    ) throws URISyntaxException {
        log.debug("REST request to update IncomingJSONRequest : {}, {}", id, incomingJSONRequest);
        if (incomingJSONRequest.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, incomingJSONRequest.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return incomingJSONRequestRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return incomingJSONRequestRepository
                    .save(incomingJSONRequest)
                    .flatMap(incomingJSONRequestSearchRepository::save)
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
     * {@code PATCH  /incoming-json-requests/:id} : Partial updates given fields of an existing incomingJSONRequest, field will ignore if it is null
     *
     * @param id the id of the incomingJSONRequest to save.
     * @param incomingJSONRequest the incomingJSONRequest to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated incomingJSONRequest,
     * or with status {@code 400 (Bad Request)} if the incomingJSONRequest is not valid,
     * or with status {@code 404 (Not Found)} if the incomingJSONRequest is not found,
     * or with status {@code 500 (Internal Server Error)} if the incomingJSONRequest couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/incoming-json-requests/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<IncomingJSONRequest>> partialUpdateIncomingJSONRequest(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody IncomingJSONRequest incomingJSONRequest
    ) throws URISyntaxException {
        log.debug("REST request to partial update IncomingJSONRequest partially : {}, {}", id, incomingJSONRequest);
        if (incomingJSONRequest.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, incomingJSONRequest.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return incomingJSONRequestRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<IncomingJSONRequest> result = incomingJSONRequestRepository
                    .findById(incomingJSONRequest.getId())
                    .map(existingIncomingJSONRequest -> {
                        if (incomingJSONRequest.getAbsaTranRef() != null) {
                            existingIncomingJSONRequest.setAbsaTranRef(incomingJSONRequest.getAbsaTranRef());
                        }
                        if (incomingJSONRequest.getDtDTransactionId() != null) {
                            existingIncomingJSONRequest.setDtDTransactionId(incomingJSONRequest.getDtDTransactionId());
                        }
                        if (incomingJSONRequest.getAmolDTransactionId() != null) {
                            existingIncomingJSONRequest.setAmolDTransactionId(incomingJSONRequest.getAmolDTransactionId());
                        }
                        if (incomingJSONRequest.getTransactionReferenceNumber() != null) {
                            existingIncomingJSONRequest.setTransactionReferenceNumber(incomingJSONRequest.getTransactionReferenceNumber());
                        }
                        if (incomingJSONRequest.getToken() != null) {
                            existingIncomingJSONRequest.setToken(incomingJSONRequest.getToken());
                        }
                        if (incomingJSONRequest.getTransactionId() != null) {
                            existingIncomingJSONRequest.setTransactionId(incomingJSONRequest.getTransactionId());
                        }
                        if (incomingJSONRequest.getFromEndpoint() != null) {
                            existingIncomingJSONRequest.setFromEndpoint(incomingJSONRequest.getFromEndpoint());
                        }
                        if (incomingJSONRequest.getToEndpoint() != null) {
                            existingIncomingJSONRequest.setToEndpoint(incomingJSONRequest.getToEndpoint());
                        }
                        if (incomingJSONRequest.getRequestJson() != null) {
                            existingIncomingJSONRequest.setRequestJson(incomingJSONRequest.getRequestJson());
                        }
                        if (incomingJSONRequest.getRequestType() != null) {
                            existingIncomingJSONRequest.setRequestType(incomingJSONRequest.getRequestType());
                        }
                        if (incomingJSONRequest.getRequestDescription() != null) {
                            existingIncomingJSONRequest.setRequestDescription(incomingJSONRequest.getRequestDescription());
                        }
                        if (incomingJSONRequest.getRequestStatus() != null) {
                            existingIncomingJSONRequest.setRequestStatus(incomingJSONRequest.getRequestStatus());
                        }
                        if (incomingJSONRequest.getAdditionalInfo() != null) {
                            existingIncomingJSONRequest.setAdditionalInfo(incomingJSONRequest.getAdditionalInfo());
                        }
                        if (incomingJSONRequest.getFreeField1() != null) {
                            existingIncomingJSONRequest.setFreeField1(incomingJSONRequest.getFreeField1());
                        }
                        if (incomingJSONRequest.getFreeField2() != null) {
                            existingIncomingJSONRequest.setFreeField2(incomingJSONRequest.getFreeField2());
                        }
                        if (incomingJSONRequest.getFreeField3() != null) {
                            existingIncomingJSONRequest.setFreeField3(incomingJSONRequest.getFreeField3());
                        }
                        if (incomingJSONRequest.getFreeField4() != null) {
                            existingIncomingJSONRequest.setFreeField4(incomingJSONRequest.getFreeField4());
                        }
                        if (incomingJSONRequest.getFreeField5() != null) {
                            existingIncomingJSONRequest.setFreeField5(incomingJSONRequest.getFreeField5());
                        }
                        if (incomingJSONRequest.getFreeField6() != null) {
                            existingIncomingJSONRequest.setFreeField6(incomingJSONRequest.getFreeField6());
                        }
                        if (incomingJSONRequest.getFreeField6ContentType() != null) {
                            existingIncomingJSONRequest.setFreeField6ContentType(incomingJSONRequest.getFreeField6ContentType());
                        }
                        if (incomingJSONRequest.getFreeField7() != null) {
                            existingIncomingJSONRequest.setFreeField7(incomingJSONRequest.getFreeField7());
                        }
                        if (incomingJSONRequest.getFreeField7ContentType() != null) {
                            existingIncomingJSONRequest.setFreeField7ContentType(incomingJSONRequest.getFreeField7ContentType());
                        }
                        if (incomingJSONRequest.getFreeField8() != null) {
                            existingIncomingJSONRequest.setFreeField8(incomingJSONRequest.getFreeField8());
                        }
                        if (incomingJSONRequest.getFreeField8ContentType() != null) {
                            existingIncomingJSONRequest.setFreeField8ContentType(incomingJSONRequest.getFreeField8ContentType());
                        }
                        if (incomingJSONRequest.getFreeField9() != null) {
                            existingIncomingJSONRequest.setFreeField9(incomingJSONRequest.getFreeField9());
                        }
                        if (incomingJSONRequest.getFreeField10() != null) {
                            existingIncomingJSONRequest.setFreeField10(incomingJSONRequest.getFreeField10());
                        }
                        if (incomingJSONRequest.getFreeField11() != null) {
                            existingIncomingJSONRequest.setFreeField11(incomingJSONRequest.getFreeField11());
                        }
                        if (incomingJSONRequest.getFreeField12() != null) {
                            existingIncomingJSONRequest.setFreeField12(incomingJSONRequest.getFreeField12());
                        }
                        if (incomingJSONRequest.getFreeField13() != null) {
                            existingIncomingJSONRequest.setFreeField13(incomingJSONRequest.getFreeField13());
                        }
                        if (incomingJSONRequest.getFreeField14() != null) {
                            existingIncomingJSONRequest.setFreeField14(incomingJSONRequest.getFreeField14());
                        }
                        if (incomingJSONRequest.getFreeField15() != null) {
                            existingIncomingJSONRequest.setFreeField15(incomingJSONRequest.getFreeField15());
                        }
                        if (incomingJSONRequest.getFreeField16() != null) {
                            existingIncomingJSONRequest.setFreeField16(incomingJSONRequest.getFreeField16());
                        }
                        if (incomingJSONRequest.getFreeField17() != null) {
                            existingIncomingJSONRequest.setFreeField17(incomingJSONRequest.getFreeField17());
                        }
                        if (incomingJSONRequest.getFreeField18() != null) {
                            existingIncomingJSONRequest.setFreeField18(incomingJSONRequest.getFreeField18());
                        }
                        if (incomingJSONRequest.getFreeField19() != null) {
                            existingIncomingJSONRequest.setFreeField19(incomingJSONRequest.getFreeField19());
                        }
                        if (incomingJSONRequest.getFreeField20() != null) {
                            existingIncomingJSONRequest.setFreeField20(incomingJSONRequest.getFreeField20());
                        }
                        if (incomingJSONRequest.getTimestamp() != null) {
                            existingIncomingJSONRequest.setTimestamp(incomingJSONRequest.getTimestamp());
                        }
                        if (incomingJSONRequest.getCreatedAt() != null) {
                            existingIncomingJSONRequest.setCreatedAt(incomingJSONRequest.getCreatedAt());
                        }
                        if (incomingJSONRequest.getCreatedBy() != null) {
                            existingIncomingJSONRequest.setCreatedBy(incomingJSONRequest.getCreatedBy());
                        }
                        if (incomingJSONRequest.getUpdatedAt() != null) {
                            existingIncomingJSONRequest.setUpdatedAt(incomingJSONRequest.getUpdatedAt());
                        }
                        if (incomingJSONRequest.getUpdatedBy() != null) {
                            existingIncomingJSONRequest.setUpdatedBy(incomingJSONRequest.getUpdatedBy());
                        }

                        return existingIncomingJSONRequest;
                    })
                    .flatMap(incomingJSONRequestRepository::save)
                    .flatMap(savedIncomingJSONRequest -> {
                        incomingJSONRequestSearchRepository.save(savedIncomingJSONRequest);

                        return Mono.just(savedIncomingJSONRequest);
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
     * {@code GET  /incoming-json-requests} : get all the incomingJSONRequests.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of incomingJSONRequests in body.
     */
    @GetMapping("/incoming-json-requests")
    public Mono<List<IncomingJSONRequest>> getAllIncomingJSONRequests() {
        log.debug("REST request to get all IncomingJSONRequests");
        return incomingJSONRequestRepository.findAll().collectList();
    }

    /**
     * {@code GET  /incoming-json-requests} : get all the incomingJSONRequests as a stream.
     * @return the {@link Flux} of incomingJSONRequests.
     */
    @GetMapping(value = "/incoming-json-requests", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<IncomingJSONRequest> getAllIncomingJSONRequestsAsStream() {
        log.debug("REST request to get all IncomingJSONRequests as a stream");
        return incomingJSONRequestRepository.findAll();
    }

    /**
     * {@code GET  /incoming-json-requests/:id} : get the "id" incomingJSONRequest.
     *
     * @param id the id of the incomingJSONRequest to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the incomingJSONRequest, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/incoming-json-requests/{id}")
    public Mono<ResponseEntity<IncomingJSONRequest>> getIncomingJSONRequest(@PathVariable Long id) {
        log.debug("REST request to get IncomingJSONRequest : {}", id);
        Mono<IncomingJSONRequest> incomingJSONRequest = incomingJSONRequestRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(incomingJSONRequest);
    }

    /**
     * {@code DELETE  /incoming-json-requests/:id} : delete the "id" incomingJSONRequest.
     *
     * @param id the id of the incomingJSONRequest to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/incoming-json-requests/{id}")
    public Mono<ResponseEntity<Void>> deleteIncomingJSONRequest(@PathVariable Long id) {
        log.debug("REST request to delete IncomingJSONRequest : {}", id);
        return incomingJSONRequestRepository
            .deleteById(id)
            .then(incomingJSONRequestSearchRepository.deleteById(id))
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
     * {@code SEARCH  /_search/incoming-json-requests?query=:query} : search for the incomingJSONRequest corresponding
     * to the query.
     *
     * @param query the query of the incomingJSONRequest search.
     * @return the result of the search.
     */
    @GetMapping("/_search/incoming-json-requests")
    public Mono<List<IncomingJSONRequest>> searchIncomingJSONRequests(@RequestParam String query) {
        log.debug("REST request to search IncomingJSONRequests for query {}", query);
        return incomingJSONRequestSearchRepository.search(query).collectList();
    }
}
