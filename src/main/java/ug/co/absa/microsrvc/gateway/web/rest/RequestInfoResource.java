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
import ug.co.absa.microsrvc.gateway.domain.RequestInfo;
import ug.co.absa.microsrvc.gateway.repository.RequestInfoRepository;
import ug.co.absa.microsrvc.gateway.repository.search.RequestInfoSearchRepository;
import ug.co.absa.microsrvc.gateway.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link ug.co.absa.microsrvc.gateway.domain.RequestInfo}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class RequestInfoResource {

    private final Logger log = LoggerFactory.getLogger(RequestInfoResource.class);

    private static final String ENTITY_NAME = "requestInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final RequestInfoRepository requestInfoRepository;

    private final RequestInfoSearchRepository requestInfoSearchRepository;

    public RequestInfoResource(RequestInfoRepository requestInfoRepository, RequestInfoSearchRepository requestInfoSearchRepository) {
        this.requestInfoRepository = requestInfoRepository;
        this.requestInfoSearchRepository = requestInfoSearchRepository;
    }

    /**
     * {@code POST  /request-infos} : Create a new requestInfo.
     *
     * @param requestInfo the requestInfo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new requestInfo, or with status {@code 400 (Bad Request)} if the requestInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/request-infos")
    public Mono<ResponseEntity<RequestInfo>> createRequestInfo(@Valid @RequestBody RequestInfo requestInfo) throws URISyntaxException {
        log.debug("REST request to save RequestInfo : {}", requestInfo);
        if (requestInfo.getId() != null) {
            throw new BadRequestAlertException("A new requestInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return requestInfoRepository
            .save(requestInfo)
            .flatMap(requestInfoSearchRepository::save)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/request-infos/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /request-infos/:id} : Updates an existing requestInfo.
     *
     * @param id the id of the requestInfo to save.
     * @param requestInfo the requestInfo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated requestInfo,
     * or with status {@code 400 (Bad Request)} if the requestInfo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the requestInfo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/request-infos/{id}")
    public Mono<ResponseEntity<RequestInfo>> updateRequestInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody RequestInfo requestInfo
    ) throws URISyntaxException {
        log.debug("REST request to update RequestInfo : {}, {}", id, requestInfo);
        if (requestInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, requestInfo.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return requestInfoRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return requestInfoRepository
                    .save(requestInfo)
                    .flatMap(requestInfoSearchRepository::save)
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
     * {@code PATCH  /request-infos/:id} : Partial updates given fields of an existing requestInfo, field will ignore if it is null
     *
     * @param id the id of the requestInfo to save.
     * @param requestInfo the requestInfo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated requestInfo,
     * or with status {@code 400 (Bad Request)} if the requestInfo is not valid,
     * or with status {@code 404 (Not Found)} if the requestInfo is not found,
     * or with status {@code 500 (Internal Server Error)} if the requestInfo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/request-infos/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<RequestInfo>> partialUpdateRequestInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody RequestInfo requestInfo
    ) throws URISyntaxException {
        log.debug("REST request to partial update RequestInfo partially : {}, {}", id, requestInfo);
        if (requestInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, requestInfo.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return requestInfoRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<RequestInfo> result = requestInfoRepository
                    .findById(requestInfo.getId())
                    .map(existingRequestInfo -> {
                        if (requestInfo.getRecordId() != null) {
                            existingRequestInfo.setRecordId(requestInfo.getRecordId());
                        }
                        if (requestInfo.getTransactionId() != null) {
                            existingRequestInfo.setTransactionId(requestInfo.getTransactionId());
                        }
                        if (requestInfo.getRequestData() != null) {
                            existingRequestInfo.setRequestData(requestInfo.getRequestData());
                        }
                        if (requestInfo.getParamList() != null) {
                            existingRequestInfo.setParamList(requestInfo.getParamList());
                        }
                        if (requestInfo.getTimestamp() != null) {
                            existingRequestInfo.setTimestamp(requestInfo.getTimestamp());
                        }
                        if (requestInfo.getRequestRef() != null) {
                            existingRequestInfo.setRequestRef(requestInfo.getRequestRef());
                        }
                        if (requestInfo.getStatus() != null) {
                            existingRequestInfo.setStatus(requestInfo.getStatus());
                        }
                        if (requestInfo.getFreeField1() != null) {
                            existingRequestInfo.setFreeField1(requestInfo.getFreeField1());
                        }
                        if (requestInfo.getFreeField2() != null) {
                            existingRequestInfo.setFreeField2(requestInfo.getFreeField2());
                        }
                        if (requestInfo.getFreeField3() != null) {
                            existingRequestInfo.setFreeField3(requestInfo.getFreeField3());
                        }
                        if (requestInfo.getFreeField4() != null) {
                            existingRequestInfo.setFreeField4(requestInfo.getFreeField4());
                        }
                        if (requestInfo.getFreeField5() != null) {
                            existingRequestInfo.setFreeField5(requestInfo.getFreeField5());
                        }
                        if (requestInfo.getFreeField6() != null) {
                            existingRequestInfo.setFreeField6(requestInfo.getFreeField6());
                        }
                        if (requestInfo.getTime() != null) {
                            existingRequestInfo.setTime(requestInfo.getTime());
                        }
                        if (requestInfo.getErrorCode() != null) {
                            existingRequestInfo.setErrorCode(requestInfo.getErrorCode());
                        }
                        if (requestInfo.getErrorMessage() != null) {
                            existingRequestInfo.setErrorMessage(requestInfo.getErrorMessage());
                        }
                        if (requestInfo.getCreatedAt() != null) {
                            existingRequestInfo.setCreatedAt(requestInfo.getCreatedAt());
                        }
                        if (requestInfo.getCreatedBy() != null) {
                            existingRequestInfo.setCreatedBy(requestInfo.getCreatedBy());
                        }
                        if (requestInfo.getUpdatedAt() != null) {
                            existingRequestInfo.setUpdatedAt(requestInfo.getUpdatedAt());
                        }
                        if (requestInfo.getUpdatedBy() != null) {
                            existingRequestInfo.setUpdatedBy(requestInfo.getUpdatedBy());
                        }

                        return existingRequestInfo;
                    })
                    .flatMap(requestInfoRepository::save)
                    .flatMap(savedRequestInfo -> {
                        requestInfoSearchRepository.save(savedRequestInfo);

                        return Mono.just(savedRequestInfo);
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
     * {@code GET  /request-infos} : get all the requestInfos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of requestInfos in body.
     */
    @GetMapping("/request-infos")
    public Mono<List<RequestInfo>> getAllRequestInfos() {
        log.debug("REST request to get all RequestInfos");
        return requestInfoRepository.findAll().collectList();
    }

    /**
     * {@code GET  /request-infos} : get all the requestInfos as a stream.
     * @return the {@link Flux} of requestInfos.
     */
    @GetMapping(value = "/request-infos", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<RequestInfo> getAllRequestInfosAsStream() {
        log.debug("REST request to get all RequestInfos as a stream");
        return requestInfoRepository.findAll();
    }

    /**
     * {@code GET  /request-infos/:id} : get the "id" requestInfo.
     *
     * @param id the id of the requestInfo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the requestInfo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/request-infos/{id}")
    public Mono<ResponseEntity<RequestInfo>> getRequestInfo(@PathVariable Long id) {
        log.debug("REST request to get RequestInfo : {}", id);
        Mono<RequestInfo> requestInfo = requestInfoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(requestInfo);
    }

    /**
     * {@code DELETE  /request-infos/:id} : delete the "id" requestInfo.
     *
     * @param id the id of the requestInfo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/request-infos/{id}")
    public Mono<ResponseEntity<Void>> deleteRequestInfo(@PathVariable Long id) {
        log.debug("REST request to delete RequestInfo : {}", id);
        return requestInfoRepository
            .deleteById(id)
            .then(requestInfoSearchRepository.deleteById(id))
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
     * {@code SEARCH  /_search/request-infos?query=:query} : search for the requestInfo corresponding
     * to the query.
     *
     * @param query the query of the requestInfo search.
     * @return the result of the search.
     */
    @GetMapping("/_search/request-infos")
    public Mono<List<RequestInfo>> searchRequestInfos(@RequestParam String query) {
        log.debug("REST request to search RequestInfos for query {}", query);
        return requestInfoSearchRepository.search(query).collectList();
    }
}
