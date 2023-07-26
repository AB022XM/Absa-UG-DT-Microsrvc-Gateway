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
import ug.co.absa.microsrvc.gateway.domain.GenericConfigs;
import ug.co.absa.microsrvc.gateway.repository.GenericConfigsRepository;
import ug.co.absa.microsrvc.gateway.repository.search.GenericConfigsSearchRepository;
import ug.co.absa.microsrvc.gateway.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link ug.co.absa.microsrvc.gateway.domain.GenericConfigs}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class GenericConfigsResource {

    private final Logger log = LoggerFactory.getLogger(GenericConfigsResource.class);

    private static final String ENTITY_NAME = "genericConfigs";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final GenericConfigsRepository genericConfigsRepository;

    private final GenericConfigsSearchRepository genericConfigsSearchRepository;

    public GenericConfigsResource(
        GenericConfigsRepository genericConfigsRepository,
        GenericConfigsSearchRepository genericConfigsSearchRepository
    ) {
        this.genericConfigsRepository = genericConfigsRepository;
        this.genericConfigsSearchRepository = genericConfigsSearchRepository;
    }

    /**
     * {@code POST  /generic-configs} : Create a new genericConfigs.
     *
     * @param genericConfigs the genericConfigs to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new genericConfigs, or with status {@code 400 (Bad Request)} if the genericConfigs has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/generic-configs")
    public Mono<ResponseEntity<GenericConfigs>> createGenericConfigs(@Valid @RequestBody GenericConfigs genericConfigs)
        throws URISyntaxException {
        log.debug("REST request to save GenericConfigs : {}", genericConfigs);
        if (genericConfigs.getId() != null) {
            throw new BadRequestAlertException("A new genericConfigs cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return genericConfigsRepository
            .save(genericConfigs)
            .flatMap(genericConfigsSearchRepository::save)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/generic-configs/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /generic-configs/:id} : Updates an existing genericConfigs.
     *
     * @param id the id of the genericConfigs to save.
     * @param genericConfigs the genericConfigs to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated genericConfigs,
     * or with status {@code 400 (Bad Request)} if the genericConfigs is not valid,
     * or with status {@code 500 (Internal Server Error)} if the genericConfigs couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/generic-configs/{id}")
    public Mono<ResponseEntity<GenericConfigs>> updateGenericConfigs(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody GenericConfigs genericConfigs
    ) throws URISyntaxException {
        log.debug("REST request to update GenericConfigs : {}, {}", id, genericConfigs);
        if (genericConfigs.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, genericConfigs.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return genericConfigsRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return genericConfigsRepository
                    .save(genericConfigs)
                    .flatMap(genericConfigsSearchRepository::save)
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
     * {@code PATCH  /generic-configs/:id} : Partial updates given fields of an existing genericConfigs, field will ignore if it is null
     *
     * @param id the id of the genericConfigs to save.
     * @param genericConfigs the genericConfigs to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated genericConfigs,
     * or with status {@code 400 (Bad Request)} if the genericConfigs is not valid,
     * or with status {@code 404 (Not Found)} if the genericConfigs is not found,
     * or with status {@code 500 (Internal Server Error)} if the genericConfigs couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/generic-configs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<GenericConfigs>> partialUpdateGenericConfigs(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody GenericConfigs genericConfigs
    ) throws URISyntaxException {
        log.debug("REST request to partial update GenericConfigs partially : {}, {}", id, genericConfigs);
        if (genericConfigs.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, genericConfigs.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return genericConfigsRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<GenericConfigs> result = genericConfigsRepository
                    .findById(genericConfigs.getId())
                    .map(existingGenericConfigs -> {
                        if (genericConfigs.getAbsaTranRef() != null) {
                            existingGenericConfigs.setAbsaTranRef(genericConfigs.getAbsaTranRef());
                        }
                        if (genericConfigs.getRecordId() != null) {
                            existingGenericConfigs.setRecordId(genericConfigs.getRecordId());
                        }
                        if (genericConfigs.getConfigId() != null) {
                            existingGenericConfigs.setConfigId(genericConfigs.getConfigId());
                        }
                        if (genericConfigs.getConfigName() != null) {
                            existingGenericConfigs.setConfigName(genericConfigs.getConfigName());
                        }
                        if (genericConfigs.getConfigsDetails() != null) {
                            existingGenericConfigs.setConfigsDetails(genericConfigs.getConfigsDetails());
                        }
                        if (genericConfigs.getAdditionalConfigs() != null) {
                            existingGenericConfigs.setAdditionalConfigs(genericConfigs.getAdditionalConfigs());
                        }
                        if (genericConfigs.getFreeField() != null) {
                            existingGenericConfigs.setFreeField(genericConfigs.getFreeField());
                        }
                        if (genericConfigs.getFreeField1() != null) {
                            existingGenericConfigs.setFreeField1(genericConfigs.getFreeField1());
                        }
                        if (genericConfigs.getFreeField2() != null) {
                            existingGenericConfigs.setFreeField2(genericConfigs.getFreeField2());
                        }
                        if (genericConfigs.getFreeField3() != null) {
                            existingGenericConfigs.setFreeField3(genericConfigs.getFreeField3());
                        }
                        if (genericConfigs.getFreeField4() != null) {
                            existingGenericConfigs.setFreeField4(genericConfigs.getFreeField4());
                        }
                        if (genericConfigs.getFreeField5() != null) {
                            existingGenericConfigs.setFreeField5(genericConfigs.getFreeField5());
                        }
                        if (genericConfigs.getFreeField6() != null) {
                            existingGenericConfigs.setFreeField6(genericConfigs.getFreeField6());
                        }
                        if (genericConfigs.getFreeField8() != null) {
                            existingGenericConfigs.setFreeField8(genericConfigs.getFreeField8());
                        }
                        if (genericConfigs.getFreeField9() != null) {
                            existingGenericConfigs.setFreeField9(genericConfigs.getFreeField9());
                        }
                        if (genericConfigs.getFreeField10() != null) {
                            existingGenericConfigs.setFreeField10(genericConfigs.getFreeField10());
                        }
                        if (genericConfigs.getFreeField11() != null) {
                            existingGenericConfigs.setFreeField11(genericConfigs.getFreeField11());
                        }
                        if (genericConfigs.getFreeField12() != null) {
                            existingGenericConfigs.setFreeField12(genericConfigs.getFreeField12());
                        }
                        if (genericConfigs.getFreeField13() != null) {
                            existingGenericConfigs.setFreeField13(genericConfigs.getFreeField13());
                        }
                        if (genericConfigs.getFreeField14() != null) {
                            existingGenericConfigs.setFreeField14(genericConfigs.getFreeField14());
                        }
                        if (genericConfigs.getFreeField15() != null) {
                            existingGenericConfigs.setFreeField15(genericConfigs.getFreeField15());
                        }
                        if (genericConfigs.getFreeField15ContentType() != null) {
                            existingGenericConfigs.setFreeField15ContentType(genericConfigs.getFreeField15ContentType());
                        }
                        if (genericConfigs.getFreeField16() != null) {
                            existingGenericConfigs.setFreeField16(genericConfigs.getFreeField16());
                        }
                        if (genericConfigs.getFreeField17() != null) {
                            existingGenericConfigs.setFreeField17(genericConfigs.getFreeField17());
                        }
                        if (genericConfigs.getFreeField18() != null) {
                            existingGenericConfigs.setFreeField18(genericConfigs.getFreeField18());
                        }
                        if (genericConfigs.getFreeField18ContentType() != null) {
                            existingGenericConfigs.setFreeField18ContentType(genericConfigs.getFreeField18ContentType());
                        }
                        if (genericConfigs.getFreeField19() != null) {
                            existingGenericConfigs.setFreeField19(genericConfigs.getFreeField19());
                        }
                        if (genericConfigs.getFreeField20() != null) {
                            existingGenericConfigs.setFreeField20(genericConfigs.getFreeField20());
                        }
                        if (genericConfigs.getFreeField21() != null) {
                            existingGenericConfigs.setFreeField21(genericConfigs.getFreeField21());
                        }
                        if (genericConfigs.getFreeField22() != null) {
                            existingGenericConfigs.setFreeField22(genericConfigs.getFreeField22());
                        }
                        if (genericConfigs.getFreeField23() != null) {
                            existingGenericConfigs.setFreeField23(genericConfigs.getFreeField23());
                        }
                        if (genericConfigs.getFreeField24() != null) {
                            existingGenericConfigs.setFreeField24(genericConfigs.getFreeField24());
                        }
                        if (genericConfigs.getFreeField25() != null) {
                            existingGenericConfigs.setFreeField25(genericConfigs.getFreeField25());
                        }
                        if (genericConfigs.getFreeField25ContentType() != null) {
                            existingGenericConfigs.setFreeField25ContentType(genericConfigs.getFreeField25ContentType());
                        }
                        if (genericConfigs.getFreeField26() != null) {
                            existingGenericConfigs.setFreeField26(genericConfigs.getFreeField26());
                        }
                        if (genericConfigs.getFreeField27() != null) {
                            existingGenericConfigs.setFreeField27(genericConfigs.getFreeField27());
                        }
                        if (genericConfigs.getFreeField28() != null) {
                            existingGenericConfigs.setFreeField28(genericConfigs.getFreeField28());
                        }
                        if (genericConfigs.getFreeField28ContentType() != null) {
                            existingGenericConfigs.setFreeField28ContentType(genericConfigs.getFreeField28ContentType());
                        }
                        if (genericConfigs.getFreeField29() != null) {
                            existingGenericConfigs.setFreeField29(genericConfigs.getFreeField29());
                        }
                        if (genericConfigs.getFreeField30() != null) {
                            existingGenericConfigs.setFreeField30(genericConfigs.getFreeField30());
                        }
                        if (genericConfigs.getFreeField31() != null) {
                            existingGenericConfigs.setFreeField31(genericConfigs.getFreeField31());
                        }
                        if (genericConfigs.getFreeField32() != null) {
                            existingGenericConfigs.setFreeField32(genericConfigs.getFreeField32());
                        }
                        if (genericConfigs.getFreeField33() != null) {
                            existingGenericConfigs.setFreeField33(genericConfigs.getFreeField33());
                        }
                        if (genericConfigs.getFreeField34() != null) {
                            existingGenericConfigs.setFreeField34(genericConfigs.getFreeField34());
                        }
                        if (genericConfigs.getTimestamp() != null) {
                            existingGenericConfigs.setTimestamp(genericConfigs.getTimestamp());
                        }
                        if (genericConfigs.getRecordStatus() != null) {
                            existingGenericConfigs.setRecordStatus(genericConfigs.getRecordStatus());
                        }

                        return existingGenericConfigs;
                    })
                    .flatMap(genericConfigsRepository::save)
                    .flatMap(savedGenericConfigs -> {
                        genericConfigsSearchRepository.save(savedGenericConfigs);

                        return Mono.just(savedGenericConfigs);
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
     * {@code GET  /generic-configs} : get all the genericConfigs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of genericConfigs in body.
     */
    @GetMapping("/generic-configs")
    public Mono<List<GenericConfigs>> getAllGenericConfigs() {
        log.debug("REST request to get all GenericConfigs");
        return genericConfigsRepository.findAll().collectList();
    }

    /**
     * {@code GET  /generic-configs} : get all the genericConfigs as a stream.
     * @return the {@link Flux} of genericConfigs.
     */
    @GetMapping(value = "/generic-configs", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<GenericConfigs> getAllGenericConfigsAsStream() {
        log.debug("REST request to get all GenericConfigs as a stream");
        return genericConfigsRepository.findAll();
    }

    /**
     * {@code GET  /generic-configs/:id} : get the "id" genericConfigs.
     *
     * @param id the id of the genericConfigs to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the genericConfigs, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/generic-configs/{id}")
    public Mono<ResponseEntity<GenericConfigs>> getGenericConfigs(@PathVariable Long id) {
        log.debug("REST request to get GenericConfigs : {}", id);
        Mono<GenericConfigs> genericConfigs = genericConfigsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(genericConfigs);
    }

    /**
     * {@code DELETE  /generic-configs/:id} : delete the "id" genericConfigs.
     *
     * @param id the id of the genericConfigs to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/generic-configs/{id}")
    public Mono<ResponseEntity<Void>> deleteGenericConfigs(@PathVariable Long id) {
        log.debug("REST request to delete GenericConfigs : {}", id);
        return genericConfigsRepository
            .deleteById(id)
            .then(genericConfigsSearchRepository.deleteById(id))
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
     * {@code SEARCH  /_search/generic-configs?query=:query} : search for the genericConfigs corresponding
     * to the query.
     *
     * @param query the query of the genericConfigs search.
     * @return the result of the search.
     */
    @GetMapping("/_search/generic-configs")
    public Mono<List<GenericConfigs>> searchGenericConfigs(@RequestParam String query) {
        log.debug("REST request to search GenericConfigs for query {}", query);
        return genericConfigsSearchRepository.search(query).collectList();
    }
}
