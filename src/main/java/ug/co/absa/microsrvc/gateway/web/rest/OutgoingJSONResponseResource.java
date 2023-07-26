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
import ug.co.absa.microsrvc.gateway.domain.OutgoingJSONResponse;
import ug.co.absa.microsrvc.gateway.repository.OutgoingJSONResponseRepository;
import ug.co.absa.microsrvc.gateway.repository.search.OutgoingJSONResponseSearchRepository;
import ug.co.absa.microsrvc.gateway.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link ug.co.absa.microsrvc.gateway.domain.OutgoingJSONResponse}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class OutgoingJSONResponseResource {

    private final Logger log = LoggerFactory.getLogger(OutgoingJSONResponseResource.class);

    private static final String ENTITY_NAME = "outgoingJSONResponse";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final OutgoingJSONResponseRepository outgoingJSONResponseRepository;

    private final OutgoingJSONResponseSearchRepository outgoingJSONResponseSearchRepository;

    public OutgoingJSONResponseResource(
        OutgoingJSONResponseRepository outgoingJSONResponseRepository,
        OutgoingJSONResponseSearchRepository outgoingJSONResponseSearchRepository
    ) {
        this.outgoingJSONResponseRepository = outgoingJSONResponseRepository;
        this.outgoingJSONResponseSearchRepository = outgoingJSONResponseSearchRepository;
    }

    /**
     * {@code POST  /outgoing-json-responses} : Create a new outgoingJSONResponse.
     *
     * @param outgoingJSONResponse the outgoingJSONResponse to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new outgoingJSONResponse, or with status {@code 400 (Bad Request)} if the outgoingJSONResponse has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/outgoing-json-responses")
    public Mono<ResponseEntity<OutgoingJSONResponse>> createOutgoingJSONResponse(
        @Valid @RequestBody OutgoingJSONResponse outgoingJSONResponse
    ) throws URISyntaxException {
        log.debug("REST request to save OutgoingJSONResponse : {}", outgoingJSONResponse);
        if (outgoingJSONResponse.getId() != null) {
            throw new BadRequestAlertException("A new outgoingJSONResponse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return outgoingJSONResponseRepository
            .save(outgoingJSONResponse)
            .flatMap(outgoingJSONResponseSearchRepository::save)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/outgoing-json-responses/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /outgoing-json-responses/:id} : Updates an existing outgoingJSONResponse.
     *
     * @param id the id of the outgoingJSONResponse to save.
     * @param outgoingJSONResponse the outgoingJSONResponse to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated outgoingJSONResponse,
     * or with status {@code 400 (Bad Request)} if the outgoingJSONResponse is not valid,
     * or with status {@code 500 (Internal Server Error)} if the outgoingJSONResponse couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/outgoing-json-responses/{id}")
    public Mono<ResponseEntity<OutgoingJSONResponse>> updateOutgoingJSONResponse(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody OutgoingJSONResponse outgoingJSONResponse
    ) throws URISyntaxException {
        log.debug("REST request to update OutgoingJSONResponse : {}, {}", id, outgoingJSONResponse);
        if (outgoingJSONResponse.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, outgoingJSONResponse.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return outgoingJSONResponseRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return outgoingJSONResponseRepository
                    .save(outgoingJSONResponse)
                    .flatMap(outgoingJSONResponseSearchRepository::save)
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
     * {@code PATCH  /outgoing-json-responses/:id} : Partial updates given fields of an existing outgoingJSONResponse, field will ignore if it is null
     *
     * @param id the id of the outgoingJSONResponse to save.
     * @param outgoingJSONResponse the outgoingJSONResponse to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated outgoingJSONResponse,
     * or with status {@code 400 (Bad Request)} if the outgoingJSONResponse is not valid,
     * or with status {@code 404 (Not Found)} if the outgoingJSONResponse is not found,
     * or with status {@code 500 (Internal Server Error)} if the outgoingJSONResponse couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/outgoing-json-responses/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<OutgoingJSONResponse>> partialUpdateOutgoingJSONResponse(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody OutgoingJSONResponse outgoingJSONResponse
    ) throws URISyntaxException {
        log.debug("REST request to partial update OutgoingJSONResponse partially : {}, {}", id, outgoingJSONResponse);
        if (outgoingJSONResponse.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, outgoingJSONResponse.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return outgoingJSONResponseRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<OutgoingJSONResponse> result = outgoingJSONResponseRepository
                    .findById(outgoingJSONResponse.getId())
                    .map(existingOutgoingJSONResponse -> {
                        if (outgoingJSONResponse.getRecordId() != null) {
                            existingOutgoingJSONResponse.setRecordId(outgoingJSONResponse.getRecordId());
                        }
                        if (outgoingJSONResponse.getResponseId() != null) {
                            existingOutgoingJSONResponse.setResponseId(outgoingJSONResponse.getResponseId());
                        }
                        if (outgoingJSONResponse.getTransactionId() != null) {
                            existingOutgoingJSONResponse.setTransactionId(outgoingJSONResponse.getTransactionId());
                        }
                        if (outgoingJSONResponse.getResponseJson() != null) {
                            existingOutgoingJSONResponse.setResponseJson(outgoingJSONResponse.getResponseJson());
                        }
                        if (outgoingJSONResponse.getResponseType() != null) {
                            existingOutgoingJSONResponse.setResponseType(outgoingJSONResponse.getResponseType());
                        }
                        if (outgoingJSONResponse.getResponseDescription() != null) {
                            existingOutgoingJSONResponse.setResponseDescription(outgoingJSONResponse.getResponseDescription());
                        }
                        if (outgoingJSONResponse.getToEndpoint() != null) {
                            existingOutgoingJSONResponse.setToEndpoint(outgoingJSONResponse.getToEndpoint());
                        }
                        if (outgoingJSONResponse.getFromEndpoint() != null) {
                            existingOutgoingJSONResponse.setFromEndpoint(outgoingJSONResponse.getFromEndpoint());
                        }
                        if (outgoingJSONResponse.getResponseStatus() != null) {
                            existingOutgoingJSONResponse.setResponseStatus(outgoingJSONResponse.getResponseStatus());
                        }
                        if (outgoingJSONResponse.getAdditionalInfo() != null) {
                            existingOutgoingJSONResponse.setAdditionalInfo(outgoingJSONResponse.getAdditionalInfo());
                        }
                        if (outgoingJSONResponse.getTimestamp() != null) {
                            existingOutgoingJSONResponse.setTimestamp(outgoingJSONResponse.getTimestamp());
                        }
                        if (outgoingJSONResponse.getExceptionMessage() != null) {
                            existingOutgoingJSONResponse.setExceptionMessage(outgoingJSONResponse.getExceptionMessage());
                        }
                        if (outgoingJSONResponse.getFreeField() != null) {
                            existingOutgoingJSONResponse.setFreeField(outgoingJSONResponse.getFreeField());
                        }
                        if (outgoingJSONResponse.getFreeField1() != null) {
                            existingOutgoingJSONResponse.setFreeField1(outgoingJSONResponse.getFreeField1());
                        }
                        if (outgoingJSONResponse.getFreeField2() != null) {
                            existingOutgoingJSONResponse.setFreeField2(outgoingJSONResponse.getFreeField2());
                        }
                        if (outgoingJSONResponse.getFreeField3() != null) {
                            existingOutgoingJSONResponse.setFreeField3(outgoingJSONResponse.getFreeField3());
                        }
                        if (outgoingJSONResponse.getFreeField4() != null) {
                            existingOutgoingJSONResponse.setFreeField4(outgoingJSONResponse.getFreeField4());
                        }
                        if (outgoingJSONResponse.getFreeField5() != null) {
                            existingOutgoingJSONResponse.setFreeField5(outgoingJSONResponse.getFreeField5());
                        }
                        if (outgoingJSONResponse.getFreeField6() != null) {
                            existingOutgoingJSONResponse.setFreeField6(outgoingJSONResponse.getFreeField6());
                        }
                        if (outgoingJSONResponse.getFreeField8() != null) {
                            existingOutgoingJSONResponse.setFreeField8(outgoingJSONResponse.getFreeField8());
                        }
                        if (outgoingJSONResponse.getFreeField9() != null) {
                            existingOutgoingJSONResponse.setFreeField9(outgoingJSONResponse.getFreeField9());
                        }
                        if (outgoingJSONResponse.getFreeField10() != null) {
                            existingOutgoingJSONResponse.setFreeField10(outgoingJSONResponse.getFreeField10());
                        }
                        if (outgoingJSONResponse.getFreeField11() != null) {
                            existingOutgoingJSONResponse.setFreeField11(outgoingJSONResponse.getFreeField11());
                        }
                        if (outgoingJSONResponse.getFreeField12() != null) {
                            existingOutgoingJSONResponse.setFreeField12(outgoingJSONResponse.getFreeField12());
                        }
                        if (outgoingJSONResponse.getFreeField13() != null) {
                            existingOutgoingJSONResponse.setFreeField13(outgoingJSONResponse.getFreeField13());
                        }
                        if (outgoingJSONResponse.getFreeField14() != null) {
                            existingOutgoingJSONResponse.setFreeField14(outgoingJSONResponse.getFreeField14());
                        }
                        if (outgoingJSONResponse.getFreeField15() != null) {
                            existingOutgoingJSONResponse.setFreeField15(outgoingJSONResponse.getFreeField15());
                        }
                        if (outgoingJSONResponse.getFreeField15ContentType() != null) {
                            existingOutgoingJSONResponse.setFreeField15ContentType(outgoingJSONResponse.getFreeField15ContentType());
                        }
                        if (outgoingJSONResponse.getFreeField16() != null) {
                            existingOutgoingJSONResponse.setFreeField16(outgoingJSONResponse.getFreeField16());
                        }
                        if (outgoingJSONResponse.getFreeField17() != null) {
                            existingOutgoingJSONResponse.setFreeField17(outgoingJSONResponse.getFreeField17());
                        }
                        if (outgoingJSONResponse.getFreeField18() != null) {
                            existingOutgoingJSONResponse.setFreeField18(outgoingJSONResponse.getFreeField18());
                        }
                        if (outgoingJSONResponse.getFreeField18ContentType() != null) {
                            existingOutgoingJSONResponse.setFreeField18ContentType(outgoingJSONResponse.getFreeField18ContentType());
                        }
                        if (outgoingJSONResponse.getFreeField19() != null) {
                            existingOutgoingJSONResponse.setFreeField19(outgoingJSONResponse.getFreeField19());
                        }
                        if (outgoingJSONResponse.getCreatedAt() != null) {
                            existingOutgoingJSONResponse.setCreatedAt(outgoingJSONResponse.getCreatedAt());
                        }
                        if (outgoingJSONResponse.getCreatedBy() != null) {
                            existingOutgoingJSONResponse.setCreatedBy(outgoingJSONResponse.getCreatedBy());
                        }
                        if (outgoingJSONResponse.getUpdatedAt() != null) {
                            existingOutgoingJSONResponse.setUpdatedAt(outgoingJSONResponse.getUpdatedAt());
                        }
                        if (outgoingJSONResponse.getUpdatedBy() != null) {
                            existingOutgoingJSONResponse.setUpdatedBy(outgoingJSONResponse.getUpdatedBy());
                        }

                        return existingOutgoingJSONResponse;
                    })
                    .flatMap(outgoingJSONResponseRepository::save)
                    .flatMap(savedOutgoingJSONResponse -> {
                        outgoingJSONResponseSearchRepository.save(savedOutgoingJSONResponse);

                        return Mono.just(savedOutgoingJSONResponse);
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
     * {@code GET  /outgoing-json-responses} : get all the outgoingJSONResponses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of outgoingJSONResponses in body.
     */
    @GetMapping("/outgoing-json-responses")
    public Mono<List<OutgoingJSONResponse>> getAllOutgoingJSONResponses() {
        log.debug("REST request to get all OutgoingJSONResponses");
        return outgoingJSONResponseRepository.findAll().collectList();
    }

    /**
     * {@code GET  /outgoing-json-responses} : get all the outgoingJSONResponses as a stream.
     * @return the {@link Flux} of outgoingJSONResponses.
     */
    @GetMapping(value = "/outgoing-json-responses", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<OutgoingJSONResponse> getAllOutgoingJSONResponsesAsStream() {
        log.debug("REST request to get all OutgoingJSONResponses as a stream");
        return outgoingJSONResponseRepository.findAll();
    }

    /**
     * {@code GET  /outgoing-json-responses/:id} : get the "id" outgoingJSONResponse.
     *
     * @param id the id of the outgoingJSONResponse to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the outgoingJSONResponse, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/outgoing-json-responses/{id}")
    public Mono<ResponseEntity<OutgoingJSONResponse>> getOutgoingJSONResponse(@PathVariable Long id) {
        log.debug("REST request to get OutgoingJSONResponse : {}", id);
        Mono<OutgoingJSONResponse> outgoingJSONResponse = outgoingJSONResponseRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(outgoingJSONResponse);
    }

    /**
     * {@code DELETE  /outgoing-json-responses/:id} : delete the "id" outgoingJSONResponse.
     *
     * @param id the id of the outgoingJSONResponse to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/outgoing-json-responses/{id}")
    public Mono<ResponseEntity<Void>> deleteOutgoingJSONResponse(@PathVariable Long id) {
        log.debug("REST request to delete OutgoingJSONResponse : {}", id);
        return outgoingJSONResponseRepository
            .deleteById(id)
            .then(outgoingJSONResponseSearchRepository.deleteById(id))
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
     * {@code SEARCH  /_search/outgoing-json-responses?query=:query} : search for the outgoingJSONResponse corresponding
     * to the query.
     *
     * @param query the query of the outgoingJSONResponse search.
     * @return the result of the search.
     */
    @GetMapping("/_search/outgoing-json-responses")
    public Mono<List<OutgoingJSONResponse>> searchOutgoingJSONResponses(@RequestParam String query) {
        log.debug("REST request to search OutgoingJSONResponses for query {}", query);
        return outgoingJSONResponseSearchRepository.search(query).collectList();
    }
}
