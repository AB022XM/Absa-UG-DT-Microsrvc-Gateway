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
import ug.co.absa.microsrvc.gateway.domain.DefaultSettings;
import ug.co.absa.microsrvc.gateway.repository.DefaultSettingsRepository;
import ug.co.absa.microsrvc.gateway.repository.search.DefaultSettingsSearchRepository;
import ug.co.absa.microsrvc.gateway.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link ug.co.absa.microsrvc.gateway.domain.DefaultSettings}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class DefaultSettingsResource {

    private final Logger log = LoggerFactory.getLogger(DefaultSettingsResource.class);

    private static final String ENTITY_NAME = "defaultSettings";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DefaultSettingsRepository defaultSettingsRepository;

    private final DefaultSettingsSearchRepository defaultSettingsSearchRepository;

    public DefaultSettingsResource(
        DefaultSettingsRepository defaultSettingsRepository,
        DefaultSettingsSearchRepository defaultSettingsSearchRepository
    ) {
        this.defaultSettingsRepository = defaultSettingsRepository;
        this.defaultSettingsSearchRepository = defaultSettingsSearchRepository;
    }

    /**
     * {@code POST  /default-settings} : Create a new defaultSettings.
     *
     * @param defaultSettings the defaultSettings to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new defaultSettings, or with status {@code 400 (Bad Request)} if the defaultSettings has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/default-settings")
    public Mono<ResponseEntity<DefaultSettings>> createDefaultSettings(@Valid @RequestBody DefaultSettings defaultSettings)
        throws URISyntaxException {
        log.debug("REST request to save DefaultSettings : {}", defaultSettings);
        if (defaultSettings.getId() != null) {
            throw new BadRequestAlertException("A new defaultSettings cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return defaultSettingsRepository
            .save(defaultSettings)
            .flatMap(defaultSettingsSearchRepository::save)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/default-settings/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /default-settings/:id} : Updates an existing defaultSettings.
     *
     * @param id the id of the defaultSettings to save.
     * @param defaultSettings the defaultSettings to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated defaultSettings,
     * or with status {@code 400 (Bad Request)} if the defaultSettings is not valid,
     * or with status {@code 500 (Internal Server Error)} if the defaultSettings couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/default-settings/{id}")
    public Mono<ResponseEntity<DefaultSettings>> updateDefaultSettings(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody DefaultSettings defaultSettings
    ) throws URISyntaxException {
        log.debug("REST request to update DefaultSettings : {}, {}", id, defaultSettings);
        if (defaultSettings.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, defaultSettings.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return defaultSettingsRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return defaultSettingsRepository
                    .save(defaultSettings)
                    .flatMap(defaultSettingsSearchRepository::save)
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
     * {@code PATCH  /default-settings/:id} : Partial updates given fields of an existing defaultSettings, field will ignore if it is null
     *
     * @param id the id of the defaultSettings to save.
     * @param defaultSettings the defaultSettings to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated defaultSettings,
     * or with status {@code 400 (Bad Request)} if the defaultSettings is not valid,
     * or with status {@code 404 (Not Found)} if the defaultSettings is not found,
     * or with status {@code 500 (Internal Server Error)} if the defaultSettings couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/default-settings/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<DefaultSettings>> partialUpdateDefaultSettings(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody DefaultSettings defaultSettings
    ) throws URISyntaxException {
        log.debug("REST request to partial update DefaultSettings partially : {}, {}", id, defaultSettings);
        if (defaultSettings.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, defaultSettings.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return defaultSettingsRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<DefaultSettings> result = defaultSettingsRepository
                    .findById(defaultSettings.getId())
                    .map(existingDefaultSettings -> {
                        if (defaultSettings.getAbsaTranRef() != null) {
                            existingDefaultSettings.setAbsaTranRef(defaultSettings.getAbsaTranRef());
                        }
                        if (defaultSettings.getDtDTransactionId() != null) {
                            existingDefaultSettings.setDtDTransactionId(defaultSettings.getDtDTransactionId());
                        }
                        if (defaultSettings.getAmolDTransactionId() != null) {
                            existingDefaultSettings.setAmolDTransactionId(defaultSettings.getAmolDTransactionId());
                        }
                        if (defaultSettings.getTransactionReferenceNumber() != null) {
                            existingDefaultSettings.setTransactionReferenceNumber(defaultSettings.getTransactionReferenceNumber());
                        }
                        if (defaultSettings.getToken() != null) {
                            existingDefaultSettings.setToken(defaultSettings.getToken());
                        }
                        if (defaultSettings.getThirdPartyDTransactionId() != null) {
                            existingDefaultSettings.setThirdPartyDTransactionId(defaultSettings.getThirdPartyDTransactionId());
                        }
                        if (defaultSettings.getDefaultSettingCode() != null) {
                            existingDefaultSettings.setDefaultSettingCode(defaultSettings.getDefaultSettingCode());
                        }
                        if (defaultSettings.getJsonSettings() != null) {
                            existingDefaultSettings.setJsonSettings(defaultSettings.getJsonSettings());
                        }
                        if (defaultSettings.getAppConfig() != null) {
                            existingDefaultSettings.setAppConfig(defaultSettings.getAppConfig());
                        }
                        if (defaultSettings.getAppName() != null) {
                            existingDefaultSettings.setAppName(defaultSettings.getAppName());
                        }
                        if (defaultSettings.getFreeField() != null) {
                            existingDefaultSettings.setFreeField(defaultSettings.getFreeField());
                        }
                        if (defaultSettings.getFreeField1() != null) {
                            existingDefaultSettings.setFreeField1(defaultSettings.getFreeField1());
                        }
                        if (defaultSettings.getFreeField2() != null) {
                            existingDefaultSettings.setFreeField2(defaultSettings.getFreeField2());
                        }
                        if (defaultSettings.getFreeField3() != null) {
                            existingDefaultSettings.setFreeField3(defaultSettings.getFreeField3());
                        }
                        if (defaultSettings.getFreeField4() != null) {
                            existingDefaultSettings.setFreeField4(defaultSettings.getFreeField4());
                        }
                        if (defaultSettings.getFreeField5() != null) {
                            existingDefaultSettings.setFreeField5(defaultSettings.getFreeField5());
                        }
                        if (defaultSettings.getFreeField6() != null) {
                            existingDefaultSettings.setFreeField6(defaultSettings.getFreeField6());
                        }
                        if (defaultSettings.getFreeField8() != null) {
                            existingDefaultSettings.setFreeField8(defaultSettings.getFreeField8());
                        }
                        if (defaultSettings.getFreeField9() != null) {
                            existingDefaultSettings.setFreeField9(defaultSettings.getFreeField9());
                        }
                        if (defaultSettings.getFreeField10() != null) {
                            existingDefaultSettings.setFreeField10(defaultSettings.getFreeField10());
                        }
                        if (defaultSettings.getFreeField11() != null) {
                            existingDefaultSettings.setFreeField11(defaultSettings.getFreeField11());
                        }
                        if (defaultSettings.getFreeField12() != null) {
                            existingDefaultSettings.setFreeField12(defaultSettings.getFreeField12());
                        }
                        if (defaultSettings.getFreeField13() != null) {
                            existingDefaultSettings.setFreeField13(defaultSettings.getFreeField13());
                        }
                        if (defaultSettings.getFreeField14() != null) {
                            existingDefaultSettings.setFreeField14(defaultSettings.getFreeField14());
                        }
                        if (defaultSettings.getFreeField15() != null) {
                            existingDefaultSettings.setFreeField15(defaultSettings.getFreeField15());
                        }
                        if (defaultSettings.getFreeField15ContentType() != null) {
                            existingDefaultSettings.setFreeField15ContentType(defaultSettings.getFreeField15ContentType());
                        }
                        if (defaultSettings.getFreeField16() != null) {
                            existingDefaultSettings.setFreeField16(defaultSettings.getFreeField16());
                        }
                        if (defaultSettings.getFreeField17() != null) {
                            existingDefaultSettings.setFreeField17(defaultSettings.getFreeField17());
                        }
                        if (defaultSettings.getFreeField18() != null) {
                            existingDefaultSettings.setFreeField18(defaultSettings.getFreeField18());
                        }
                        if (defaultSettings.getFreeField18ContentType() != null) {
                            existingDefaultSettings.setFreeField18ContentType(defaultSettings.getFreeField18ContentType());
                        }
                        if (defaultSettings.getFreeField19() != null) {
                            existingDefaultSettings.setFreeField19(defaultSettings.getFreeField19());
                        }
                        if (defaultSettings.getTimestamp() != null) {
                            existingDefaultSettings.setTimestamp(defaultSettings.getTimestamp());
                        }
                        if (defaultSettings.getRecordStatus() != null) {
                            existingDefaultSettings.setRecordStatus(defaultSettings.getRecordStatus());
                        }
                        if (defaultSettings.getCreatedAt() != null) {
                            existingDefaultSettings.setCreatedAt(defaultSettings.getCreatedAt());
                        }
                        if (defaultSettings.getCreatedBy() != null) {
                            existingDefaultSettings.setCreatedBy(defaultSettings.getCreatedBy());
                        }
                        if (defaultSettings.getUpdatedAt() != null) {
                            existingDefaultSettings.setUpdatedAt(defaultSettings.getUpdatedAt());
                        }
                        if (defaultSettings.getUpdatedby() != null) {
                            existingDefaultSettings.setUpdatedby(defaultSettings.getUpdatedby());
                        }

                        return existingDefaultSettings;
                    })
                    .flatMap(defaultSettingsRepository::save)
                    .flatMap(savedDefaultSettings -> {
                        defaultSettingsSearchRepository.save(savedDefaultSettings);

                        return Mono.just(savedDefaultSettings);
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
     * {@code GET  /default-settings} : get all the defaultSettings.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of defaultSettings in body.
     */
    @GetMapping("/default-settings")
    public Mono<List<DefaultSettings>> getAllDefaultSettings() {
        log.debug("REST request to get all DefaultSettings");
        return defaultSettingsRepository.findAll().collectList();
    }

    /**
     * {@code GET  /default-settings} : get all the defaultSettings as a stream.
     * @return the {@link Flux} of defaultSettings.
     */
    @GetMapping(value = "/default-settings", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<DefaultSettings> getAllDefaultSettingsAsStream() {
        log.debug("REST request to get all DefaultSettings as a stream");
        return defaultSettingsRepository.findAll();
    }

    /**
     * {@code GET  /default-settings/:id} : get the "id" defaultSettings.
     *
     * @param id the id of the defaultSettings to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the defaultSettings, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/default-settings/{id}")
    public Mono<ResponseEntity<DefaultSettings>> getDefaultSettings(@PathVariable Long id) {
        log.debug("REST request to get DefaultSettings : {}", id);
        Mono<DefaultSettings> defaultSettings = defaultSettingsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(defaultSettings);
    }

    /**
     * {@code DELETE  /default-settings/:id} : delete the "id" defaultSettings.
     *
     * @param id the id of the defaultSettings to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/default-settings/{id}")
    public Mono<ResponseEntity<Void>> deleteDefaultSettings(@PathVariable Long id) {
        log.debug("REST request to delete DefaultSettings : {}", id);
        return defaultSettingsRepository
            .deleteById(id)
            .then(defaultSettingsSearchRepository.deleteById(id))
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
     * {@code SEARCH  /_search/default-settings?query=:query} : search for the defaultSettings corresponding
     * to the query.
     *
     * @param query the query of the defaultSettings search.
     * @return the result of the search.
     */
    @GetMapping("/_search/default-settings")
    public Mono<List<DefaultSettings>> searchDefaultSettings(@RequestParam String query) {
        log.debug("REST request to search DefaultSettings for query {}", query);
        return defaultSettingsSearchRepository.search(query).collectList();
    }
}
