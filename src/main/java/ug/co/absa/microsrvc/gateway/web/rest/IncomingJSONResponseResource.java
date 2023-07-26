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
import ug.co.absa.microsrvc.gateway.domain.IncomingJSONResponse;
import ug.co.absa.microsrvc.gateway.repository.IncomingJSONResponseRepository;
import ug.co.absa.microsrvc.gateway.repository.search.IncomingJSONResponseSearchRepository;
import ug.co.absa.microsrvc.gateway.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link ug.co.absa.microsrvc.gateway.domain.IncomingJSONResponse}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class IncomingJSONResponseResource {

    private final Logger log = LoggerFactory.getLogger(IncomingJSONResponseResource.class);

    private static final String ENTITY_NAME = "incomingJSONResponse";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final IncomingJSONResponseRepository incomingJSONResponseRepository;

    private final IncomingJSONResponseSearchRepository incomingJSONResponseSearchRepository;

    public IncomingJSONResponseResource(
        IncomingJSONResponseRepository incomingJSONResponseRepository,
        IncomingJSONResponseSearchRepository incomingJSONResponseSearchRepository
    ) {
        this.incomingJSONResponseRepository = incomingJSONResponseRepository;
        this.incomingJSONResponseSearchRepository = incomingJSONResponseSearchRepository;
    }

    /**
     * {@code POST  /incoming-json-responses} : Create a new incomingJSONResponse.
     *
     * @param incomingJSONResponse the incomingJSONResponse to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new incomingJSONResponse, or with status {@code 400 (Bad Request)} if the incomingJSONResponse has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/incoming-json-responses")
    public Mono<ResponseEntity<IncomingJSONResponse>> createIncomingJSONResponse(
        @Valid @RequestBody IncomingJSONResponse incomingJSONResponse
    ) throws URISyntaxException {
        log.debug("REST request to save IncomingJSONResponse : {}", incomingJSONResponse);
        if (incomingJSONResponse.getId() != null) {
            throw new BadRequestAlertException("A new incomingJSONResponse cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return incomingJSONResponseRepository
            .save(incomingJSONResponse)
            .flatMap(incomingJSONResponseSearchRepository::save)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/incoming-json-responses/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /incoming-json-responses/:id} : Updates an existing incomingJSONResponse.
     *
     * @param id the id of the incomingJSONResponse to save.
     * @param incomingJSONResponse the incomingJSONResponse to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated incomingJSONResponse,
     * or with status {@code 400 (Bad Request)} if the incomingJSONResponse is not valid,
     * or with status {@code 500 (Internal Server Error)} if the incomingJSONResponse couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/incoming-json-responses/{id}")
    public Mono<ResponseEntity<IncomingJSONResponse>> updateIncomingJSONResponse(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody IncomingJSONResponse incomingJSONResponse
    ) throws URISyntaxException {
        log.debug("REST request to update IncomingJSONResponse : {}, {}", id, incomingJSONResponse);
        if (incomingJSONResponse.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, incomingJSONResponse.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return incomingJSONResponseRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return incomingJSONResponseRepository
                    .save(incomingJSONResponse)
                    .flatMap(incomingJSONResponseSearchRepository::save)
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
     * {@code PATCH  /incoming-json-responses/:id} : Partial updates given fields of an existing incomingJSONResponse, field will ignore if it is null
     *
     * @param id the id of the incomingJSONResponse to save.
     * @param incomingJSONResponse the incomingJSONResponse to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated incomingJSONResponse,
     * or with status {@code 400 (Bad Request)} if the incomingJSONResponse is not valid,
     * or with status {@code 404 (Not Found)} if the incomingJSONResponse is not found,
     * or with status {@code 500 (Internal Server Error)} if the incomingJSONResponse couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/incoming-json-responses/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<IncomingJSONResponse>> partialUpdateIncomingJSONResponse(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody IncomingJSONResponse incomingJSONResponse
    ) throws URISyntaxException {
        log.debug("REST request to partial update IncomingJSONResponse partially : {}, {}", id, incomingJSONResponse);
        if (incomingJSONResponse.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, incomingJSONResponse.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return incomingJSONResponseRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<IncomingJSONResponse> result = incomingJSONResponseRepository
                    .findById(incomingJSONResponse.getId())
                    .map(existingIncomingJSONResponse -> {
                        if (incomingJSONResponse.getAbsaTranRef() != null) {
                            existingIncomingJSONResponse.setAbsaTranRef(incomingJSONResponse.getAbsaTranRef());
                        }
                        if (incomingJSONResponse.getDtDTransactionId() != null) {
                            existingIncomingJSONResponse.setDtDTransactionId(incomingJSONResponse.getDtDTransactionId());
                        }
                        if (incomingJSONResponse.getAmolDTransactionId() != null) {
                            existingIncomingJSONResponse.setAmolDTransactionId(incomingJSONResponse.getAmolDTransactionId());
                        }
                        if (incomingJSONResponse.getTransactionReferenceNumber() != null) {
                            existingIncomingJSONResponse.setTransactionReferenceNumber(
                                incomingJSONResponse.getTransactionReferenceNumber()
                            );
                        }
                        if (incomingJSONResponse.getToken() != null) {
                            existingIncomingJSONResponse.setToken(incomingJSONResponse.getToken());
                        }
                        if (incomingJSONResponse.getResponseId() != null) {
                            existingIncomingJSONResponse.setResponseId(incomingJSONResponse.getResponseId());
                        }
                        if (incomingJSONResponse.getTransactionId() != null) {
                            existingIncomingJSONResponse.setTransactionId(incomingJSONResponse.getTransactionId());
                        }
                        if (incomingJSONResponse.getResponseJson() != null) {
                            existingIncomingJSONResponse.setResponseJson(incomingJSONResponse.getResponseJson());
                        }
                        if (incomingJSONResponse.getResponseType() != null) {
                            existingIncomingJSONResponse.setResponseType(incomingJSONResponse.getResponseType());
                        }
                        if (incomingJSONResponse.getResponseDescription() != null) {
                            existingIncomingJSONResponse.setResponseDescription(incomingJSONResponse.getResponseDescription());
                        }
                        if (incomingJSONResponse.getResponseStatus() != null) {
                            existingIncomingJSONResponse.setResponseStatus(incomingJSONResponse.getResponseStatus());
                        }
                        if (incomingJSONResponse.getAdditionalInfo() != null) {
                            existingIncomingJSONResponse.setAdditionalInfo(incomingJSONResponse.getAdditionalInfo());
                        }
                        if (incomingJSONResponse.getTimestamp() != null) {
                            existingIncomingJSONResponse.setTimestamp(incomingJSONResponse.getTimestamp());
                        }
                        if (incomingJSONResponse.getExceptionMessage() != null) {
                            existingIncomingJSONResponse.setExceptionMessage(incomingJSONResponse.getExceptionMessage());
                        }
                        if (incomingJSONResponse.getFreeField() != null) {
                            existingIncomingJSONResponse.setFreeField(incomingJSONResponse.getFreeField());
                        }
                        if (incomingJSONResponse.getFreeField1() != null) {
                            existingIncomingJSONResponse.setFreeField1(incomingJSONResponse.getFreeField1());
                        }
                        if (incomingJSONResponse.getFreeField2() != null) {
                            existingIncomingJSONResponse.setFreeField2(incomingJSONResponse.getFreeField2());
                        }
                        if (incomingJSONResponse.getFreeField3() != null) {
                            existingIncomingJSONResponse.setFreeField3(incomingJSONResponse.getFreeField3());
                        }
                        if (incomingJSONResponse.getFreeField4() != null) {
                            existingIncomingJSONResponse.setFreeField4(incomingJSONResponse.getFreeField4());
                        }
                        if (incomingJSONResponse.getFreeField5() != null) {
                            existingIncomingJSONResponse.setFreeField5(incomingJSONResponse.getFreeField5());
                        }
                        if (incomingJSONResponse.getFreeField6() != null) {
                            existingIncomingJSONResponse.setFreeField6(incomingJSONResponse.getFreeField6());
                        }
                        if (incomingJSONResponse.getFreeField8() != null) {
                            existingIncomingJSONResponse.setFreeField8(incomingJSONResponse.getFreeField8());
                        }
                        if (incomingJSONResponse.getFreeField9() != null) {
                            existingIncomingJSONResponse.setFreeField9(incomingJSONResponse.getFreeField9());
                        }
                        if (incomingJSONResponse.getFreeField10() != null) {
                            existingIncomingJSONResponse.setFreeField10(incomingJSONResponse.getFreeField10());
                        }
                        if (incomingJSONResponse.getFreeField11() != null) {
                            existingIncomingJSONResponse.setFreeField11(incomingJSONResponse.getFreeField11());
                        }
                        if (incomingJSONResponse.getFreeField12() != null) {
                            existingIncomingJSONResponse.setFreeField12(incomingJSONResponse.getFreeField12());
                        }
                        if (incomingJSONResponse.getFreeField13() != null) {
                            existingIncomingJSONResponse.setFreeField13(incomingJSONResponse.getFreeField13());
                        }
                        if (incomingJSONResponse.getFreeField14() != null) {
                            existingIncomingJSONResponse.setFreeField14(incomingJSONResponse.getFreeField14());
                        }
                        if (incomingJSONResponse.getFreeField15() != null) {
                            existingIncomingJSONResponse.setFreeField15(incomingJSONResponse.getFreeField15());
                        }
                        if (incomingJSONResponse.getFreeField15ContentType() != null) {
                            existingIncomingJSONResponse.setFreeField15ContentType(incomingJSONResponse.getFreeField15ContentType());
                        }
                        if (incomingJSONResponse.getFreeField16() != null) {
                            existingIncomingJSONResponse.setFreeField16(incomingJSONResponse.getFreeField16());
                        }
                        if (incomingJSONResponse.getFreeField17() != null) {
                            existingIncomingJSONResponse.setFreeField17(incomingJSONResponse.getFreeField17());
                        }
                        if (incomingJSONResponse.getFreeField18() != null) {
                            existingIncomingJSONResponse.setFreeField18(incomingJSONResponse.getFreeField18());
                        }
                        if (incomingJSONResponse.getFreeField18ContentType() != null) {
                            existingIncomingJSONResponse.setFreeField18ContentType(incomingJSONResponse.getFreeField18ContentType());
                        }
                        if (incomingJSONResponse.getFreeField19() != null) {
                            existingIncomingJSONResponse.setFreeField19(incomingJSONResponse.getFreeField19());
                        }
                        if (incomingJSONResponse.getCreatedAt() != null) {
                            existingIncomingJSONResponse.setCreatedAt(incomingJSONResponse.getCreatedAt());
                        }
                        if (incomingJSONResponse.getCreatedBy() != null) {
                            existingIncomingJSONResponse.setCreatedBy(incomingJSONResponse.getCreatedBy());
                        }
                        if (incomingJSONResponse.getUpdatedAt() != null) {
                            existingIncomingJSONResponse.setUpdatedAt(incomingJSONResponse.getUpdatedAt());
                        }
                        if (incomingJSONResponse.getUpdatedBy() != null) {
                            existingIncomingJSONResponse.setUpdatedBy(incomingJSONResponse.getUpdatedBy());
                        }

                        return existingIncomingJSONResponse;
                    })
                    .flatMap(incomingJSONResponseRepository::save)
                    .flatMap(savedIncomingJSONResponse -> {
                        incomingJSONResponseSearchRepository.save(savedIncomingJSONResponse);

                        return Mono.just(savedIncomingJSONResponse);
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
     * {@code GET  /incoming-json-responses} : get all the incomingJSONResponses.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of incomingJSONResponses in body.
     */
    @GetMapping("/incoming-json-responses")
    public Mono<List<IncomingJSONResponse>> getAllIncomingJSONResponses() {
        log.debug("REST request to get all IncomingJSONResponses");
        return incomingJSONResponseRepository.findAll().collectList();
    }

    /**
     * {@code GET  /incoming-json-responses} : get all the incomingJSONResponses as a stream.
     * @return the {@link Flux} of incomingJSONResponses.
     */
    @GetMapping(value = "/incoming-json-responses", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<IncomingJSONResponse> getAllIncomingJSONResponsesAsStream() {
        log.debug("REST request to get all IncomingJSONResponses as a stream");
        return incomingJSONResponseRepository.findAll();
    }

    /**
     * {@code GET  /incoming-json-responses/:id} : get the "id" incomingJSONResponse.
     *
     * @param id the id of the incomingJSONResponse to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the incomingJSONResponse, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/incoming-json-responses/{id}")
    public Mono<ResponseEntity<IncomingJSONResponse>> getIncomingJSONResponse(@PathVariable Long id) {
        log.debug("REST request to get IncomingJSONResponse : {}", id);
        Mono<IncomingJSONResponse> incomingJSONResponse = incomingJSONResponseRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(incomingJSONResponse);
    }

    /**
     * {@code DELETE  /incoming-json-responses/:id} : delete the "id" incomingJSONResponse.
     *
     * @param id the id of the incomingJSONResponse to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/incoming-json-responses/{id}")
    public Mono<ResponseEntity<Void>> deleteIncomingJSONResponse(@PathVariable Long id) {
        log.debug("REST request to delete IncomingJSONResponse : {}", id);
        return incomingJSONResponseRepository
            .deleteById(id)
            .then(incomingJSONResponseSearchRepository.deleteById(id))
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
     * {@code SEARCH  /_search/incoming-json-responses?query=:query} : search for the incomingJSONResponse corresponding
     * to the query.
     *
     * @param query the query of the incomingJSONResponse search.
     * @return the result of the search.
     */
    @GetMapping("/_search/incoming-json-responses")
    public Mono<List<IncomingJSONResponse>> searchIncomingJSONResponses(@RequestParam String query) {
        log.debug("REST request to search IncomingJSONResponses for query {}", query);
        return incomingJSONResponseSearchRepository.search(query).collectList();
    }
}
