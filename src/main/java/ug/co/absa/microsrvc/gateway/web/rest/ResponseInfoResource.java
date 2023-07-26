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
import ug.co.absa.microsrvc.gateway.domain.ResponseInfo;
import ug.co.absa.microsrvc.gateway.repository.ResponseInfoRepository;
import ug.co.absa.microsrvc.gateway.repository.search.ResponseInfoSearchRepository;
import ug.co.absa.microsrvc.gateway.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link ug.co.absa.microsrvc.gateway.domain.ResponseInfo}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ResponseInfoResource {

    private final Logger log = LoggerFactory.getLogger(ResponseInfoResource.class);

    private static final String ENTITY_NAME = "responseInfo";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ResponseInfoRepository responseInfoRepository;

    private final ResponseInfoSearchRepository responseInfoSearchRepository;

    public ResponseInfoResource(ResponseInfoRepository responseInfoRepository, ResponseInfoSearchRepository responseInfoSearchRepository) {
        this.responseInfoRepository = responseInfoRepository;
        this.responseInfoSearchRepository = responseInfoSearchRepository;
    }

    /**
     * {@code POST  /response-infos} : Create a new responseInfo.
     *
     * @param responseInfo the responseInfo to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new responseInfo, or with status {@code 400 (Bad Request)} if the responseInfo has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/response-infos")
    public Mono<ResponseEntity<ResponseInfo>> createResponseInfo(@Valid @RequestBody ResponseInfo responseInfo) throws URISyntaxException {
        log.debug("REST request to save ResponseInfo : {}", responseInfo);
        if (responseInfo.getId() != null) {
            throw new BadRequestAlertException("A new responseInfo cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return responseInfoRepository
            .save(responseInfo)
            .flatMap(responseInfoSearchRepository::save)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/response-infos/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /response-infos/:id} : Updates an existing responseInfo.
     *
     * @param id the id of the responseInfo to save.
     * @param responseInfo the responseInfo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated responseInfo,
     * or with status {@code 400 (Bad Request)} if the responseInfo is not valid,
     * or with status {@code 500 (Internal Server Error)} if the responseInfo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/response-infos/{id}")
    public Mono<ResponseEntity<ResponseInfo>> updateResponseInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ResponseInfo responseInfo
    ) throws URISyntaxException {
        log.debug("REST request to update ResponseInfo : {}, {}", id, responseInfo);
        if (responseInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, responseInfo.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return responseInfoRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return responseInfoRepository
                    .save(responseInfo)
                    .flatMap(responseInfoSearchRepository::save)
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
     * {@code PATCH  /response-infos/:id} : Partial updates given fields of an existing responseInfo, field will ignore if it is null
     *
     * @param id the id of the responseInfo to save.
     * @param responseInfo the responseInfo to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated responseInfo,
     * or with status {@code 400 (Bad Request)} if the responseInfo is not valid,
     * or with status {@code 404 (Not Found)} if the responseInfo is not found,
     * or with status {@code 500 (Internal Server Error)} if the responseInfo couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/response-infos/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<ResponseInfo>> partialUpdateResponseInfo(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ResponseInfo responseInfo
    ) throws URISyntaxException {
        log.debug("REST request to partial update ResponseInfo partially : {}, {}", id, responseInfo);
        if (responseInfo.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, responseInfo.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return responseInfoRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<ResponseInfo> result = responseInfoRepository
                    .findById(responseInfo.getId())
                    .map(existingResponseInfo -> {
                        if (responseInfo.getAbsaTranRef() != null) {
                            existingResponseInfo.setAbsaTranRef(responseInfo.getAbsaTranRef());
                        }
                        if (responseInfo.getBillerId() != null) {
                            existingResponseInfo.setBillerId(responseInfo.getBillerId());
                        }
                        if (responseInfo.getPaymentItemCode() != null) {
                            existingResponseInfo.setPaymentItemCode(responseInfo.getPaymentItemCode());
                        }
                        if (responseInfo.getDtDTransactionId() != null) {
                            existingResponseInfo.setDtDTransactionId(responseInfo.getDtDTransactionId());
                        }
                        if (responseInfo.getTransactionReferenceNumber() != null) {
                            existingResponseInfo.setTransactionReferenceNumber(responseInfo.getTransactionReferenceNumber());
                        }
                        if (responseInfo.getExternalTranid() != null) {
                            existingResponseInfo.setExternalTranid(responseInfo.getExternalTranid());
                        }
                        if (responseInfo.getResponseCode() != null) {
                            existingResponseInfo.setResponseCode(responseInfo.getResponseCode());
                        }
                        if (responseInfo.getResponseMessage() != null) {
                            existingResponseInfo.setResponseMessage(responseInfo.getResponseMessage());
                        }
                        if (responseInfo.getResponseBody() != null) {
                            existingResponseInfo.setResponseBody(responseInfo.getResponseBody());
                        }
                        if (responseInfo.getTimestamp() != null) {
                            existingResponseInfo.setTimestamp(responseInfo.getTimestamp());
                        }
                        if (responseInfo.getRequestRef() != null) {
                            existingResponseInfo.setRequestRef(responseInfo.getRequestRef());
                        }
                        if (responseInfo.getStatus() != null) {
                            existingResponseInfo.setStatus(responseInfo.getStatus());
                        }
                        if (responseInfo.getFreeField1() != null) {
                            existingResponseInfo.setFreeField1(responseInfo.getFreeField1());
                        }
                        if (responseInfo.getFreeField2() != null) {
                            existingResponseInfo.setFreeField2(responseInfo.getFreeField2());
                        }
                        if (responseInfo.getFreeField2ContentType() != null) {
                            existingResponseInfo.setFreeField2ContentType(responseInfo.getFreeField2ContentType());
                        }
                        if (responseInfo.getFreeField3() != null) {
                            existingResponseInfo.setFreeField3(responseInfo.getFreeField3());
                        }
                        if (responseInfo.getFreeField4() != null) {
                            existingResponseInfo.setFreeField4(responseInfo.getFreeField4());
                        }
                        if (responseInfo.getFreeField5() != null) {
                            existingResponseInfo.setFreeField5(responseInfo.getFreeField5());
                        }
                        if (responseInfo.getFreeField6() != null) {
                            existingResponseInfo.setFreeField6(responseInfo.getFreeField6());
                        }
                        if (responseInfo.getTime() != null) {
                            existingResponseInfo.setTime(responseInfo.getTime());
                        }
                        if (responseInfo.getErrorCode() != null) {
                            existingResponseInfo.setErrorCode(responseInfo.getErrorCode());
                        }
                        if (responseInfo.getErrorMessage() != null) {
                            existingResponseInfo.setErrorMessage(responseInfo.getErrorMessage());
                        }
                        if (responseInfo.getCreatedAt() != null) {
                            existingResponseInfo.setCreatedAt(responseInfo.getCreatedAt());
                        }
                        if (responseInfo.getCreatedBy() != null) {
                            existingResponseInfo.setCreatedBy(responseInfo.getCreatedBy());
                        }
                        if (responseInfo.getUpdatedAt() != null) {
                            existingResponseInfo.setUpdatedAt(responseInfo.getUpdatedAt());
                        }
                        if (responseInfo.getUpdatedBy() != null) {
                            existingResponseInfo.setUpdatedBy(responseInfo.getUpdatedBy());
                        }

                        return existingResponseInfo;
                    })
                    .flatMap(responseInfoRepository::save)
                    .flatMap(savedResponseInfo -> {
                        responseInfoSearchRepository.save(savedResponseInfo);

                        return Mono.just(savedResponseInfo);
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
     * {@code GET  /response-infos} : get all the responseInfos.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of responseInfos in body.
     */
    @GetMapping("/response-infos")
    public Mono<List<ResponseInfo>> getAllResponseInfos() {
        log.debug("REST request to get all ResponseInfos");
        return responseInfoRepository.findAll().collectList();
    }

    /**
     * {@code GET  /response-infos} : get all the responseInfos as a stream.
     * @return the {@link Flux} of responseInfos.
     */
    @GetMapping(value = "/response-infos", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<ResponseInfo> getAllResponseInfosAsStream() {
        log.debug("REST request to get all ResponseInfos as a stream");
        return responseInfoRepository.findAll();
    }

    /**
     * {@code GET  /response-infos/:id} : get the "id" responseInfo.
     *
     * @param id the id of the responseInfo to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the responseInfo, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/response-infos/{id}")
    public Mono<ResponseEntity<ResponseInfo>> getResponseInfo(@PathVariable Long id) {
        log.debug("REST request to get ResponseInfo : {}", id);
        Mono<ResponseInfo> responseInfo = responseInfoRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(responseInfo);
    }

    /**
     * {@code DELETE  /response-infos/:id} : delete the "id" responseInfo.
     *
     * @param id the id of the responseInfo to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/response-infos/{id}")
    public Mono<ResponseEntity<Void>> deleteResponseInfo(@PathVariable Long id) {
        log.debug("REST request to delete ResponseInfo : {}", id);
        return responseInfoRepository
            .deleteById(id)
            .then(responseInfoSearchRepository.deleteById(id))
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
     * {@code SEARCH  /_search/response-infos?query=:query} : search for the responseInfo corresponding
     * to the query.
     *
     * @param query the query of the responseInfo search.
     * @return the result of the search.
     */
    @GetMapping("/_search/response-infos")
    public Mono<List<ResponseInfo>> searchResponseInfos(@RequestParam String query) {
        log.debug("REST request to search ResponseInfos for query {}", query);
        return responseInfoSearchRepository.search(query).collectList();
    }
}
