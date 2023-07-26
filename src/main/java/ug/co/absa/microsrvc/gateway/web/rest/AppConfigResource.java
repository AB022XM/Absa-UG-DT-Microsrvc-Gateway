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
import ug.co.absa.microsrvc.gateway.domain.AppConfig;
import ug.co.absa.microsrvc.gateway.repository.AppConfigRepository;
import ug.co.absa.microsrvc.gateway.repository.search.AppConfigSearchRepository;
import ug.co.absa.microsrvc.gateway.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link ug.co.absa.microsrvc.gateway.domain.AppConfig}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AppConfigResource {

    private final Logger log = LoggerFactory.getLogger(AppConfigResource.class);

    private static final String ENTITY_NAME = "appConfig";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AppConfigRepository appConfigRepository;

    private final AppConfigSearchRepository appConfigSearchRepository;

    public AppConfigResource(AppConfigRepository appConfigRepository, AppConfigSearchRepository appConfigSearchRepository) {
        this.appConfigRepository = appConfigRepository;
        this.appConfigSearchRepository = appConfigSearchRepository;
    }

    /**
     * {@code POST  /app-configs} : Create a new appConfig.
     *
     * @param appConfig the appConfig to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new appConfig, or with status {@code 400 (Bad Request)} if the appConfig has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/app-configs")
    public Mono<ResponseEntity<AppConfig>> createAppConfig(@Valid @RequestBody AppConfig appConfig) throws URISyntaxException {
        log.debug("REST request to save AppConfig : {}", appConfig);
        if (appConfig.getId() != null) {
            throw new BadRequestAlertException("A new appConfig cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return appConfigRepository
            .save(appConfig)
            .flatMap(appConfigSearchRepository::save)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/app-configs/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /app-configs/:id} : Updates an existing appConfig.
     *
     * @param id the id of the appConfig to save.
     * @param appConfig the appConfig to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated appConfig,
     * or with status {@code 400 (Bad Request)} if the appConfig is not valid,
     * or with status {@code 500 (Internal Server Error)} if the appConfig couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/app-configs/{id}")
    public Mono<ResponseEntity<AppConfig>> updateAppConfig(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody AppConfig appConfig
    ) throws URISyntaxException {
        log.debug("REST request to update AppConfig : {}, {}", id, appConfig);
        if (appConfig.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, appConfig.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return appConfigRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return appConfigRepository
                    .save(appConfig)
                    .flatMap(appConfigSearchRepository::save)
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
     * {@code PATCH  /app-configs/:id} : Partial updates given fields of an existing appConfig, field will ignore if it is null
     *
     * @param id the id of the appConfig to save.
     * @param appConfig the appConfig to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated appConfig,
     * or with status {@code 400 (Bad Request)} if the appConfig is not valid,
     * or with status {@code 404 (Not Found)} if the appConfig is not found,
     * or with status {@code 500 (Internal Server Error)} if the appConfig couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/app-configs/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<AppConfig>> partialUpdateAppConfig(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody AppConfig appConfig
    ) throws URISyntaxException {
        log.debug("REST request to partial update AppConfig partially : {}, {}", id, appConfig);
        if (appConfig.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, appConfig.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return appConfigRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<AppConfig> result = appConfigRepository
                    .findById(appConfig.getId())
                    .map(existingAppConfig -> {
                        if (appConfig.getAbsaTranRef() != null) {
                            existingAppConfig.setAbsaTranRef(appConfig.getAbsaTranRef());
                        }
                        if (appConfig.getRecordId() != null) {
                            existingAppConfig.setRecordId(appConfig.getRecordId());
                        }
                        if (appConfig.getConfigId() != null) {
                            existingAppConfig.setConfigId(appConfig.getConfigId());
                        }
                        if (appConfig.getConfigName() != null) {
                            existingAppConfig.setConfigName(appConfig.getConfigName());
                        }
                        if (appConfig.getConfigValue() != null) {
                            existingAppConfig.setConfigValue(appConfig.getConfigValue());
                        }
                        if (appConfig.getConfigType() != null) {
                            existingAppConfig.setConfigType(appConfig.getConfigType());
                        }
                        if (appConfig.getConfigDescription() != null) {
                            existingAppConfig.setConfigDescription(appConfig.getConfigDescription());
                        }
                        if (appConfig.getConfigStatus() != null) {
                            existingAppConfig.setConfigStatus(appConfig.getConfigStatus());
                        }
                        if (appConfig.getFreeField1() != null) {
                            existingAppConfig.setFreeField1(appConfig.getFreeField1());
                        }
                        if (appConfig.getFreeField2() != null) {
                            existingAppConfig.setFreeField2(appConfig.getFreeField2());
                        }
                        if (appConfig.getFreeField3() != null) {
                            existingAppConfig.setFreeField3(appConfig.getFreeField3());
                        }
                        if (appConfig.getFreeField4() != null) {
                            existingAppConfig.setFreeField4(appConfig.getFreeField4());
                        }
                        if (appConfig.getFreeField5() != null) {
                            existingAppConfig.setFreeField5(appConfig.getFreeField5());
                        }
                        if (appConfig.getFreeField6() != null) {
                            existingAppConfig.setFreeField6(appConfig.getFreeField6());
                        }
                        if (appConfig.getFreeField7() != null) {
                            existingAppConfig.setFreeField7(appConfig.getFreeField7());
                        }
                        if (appConfig.getFreeField8() != null) {
                            existingAppConfig.setFreeField8(appConfig.getFreeField8());
                        }
                        if (appConfig.getFreeField9() != null) {
                            existingAppConfig.setFreeField9(appConfig.getFreeField9());
                        }
                        if (appConfig.getFreeField10() != null) {
                            existingAppConfig.setFreeField10(appConfig.getFreeField10());
                        }
                        if (appConfig.getFreeField11() != null) {
                            existingAppConfig.setFreeField11(appConfig.getFreeField11());
                        }
                        if (appConfig.getFreeField12() != null) {
                            existingAppConfig.setFreeField12(appConfig.getFreeField12());
                        }
                        if (appConfig.getFreeField13() != null) {
                            existingAppConfig.setFreeField13(appConfig.getFreeField13());
                        }
                        if (appConfig.getFreeField14() != null) {
                            existingAppConfig.setFreeField14(appConfig.getFreeField14());
                        }
                        if (appConfig.getFreeField14ContentType() != null) {
                            existingAppConfig.setFreeField14ContentType(appConfig.getFreeField14ContentType());
                        }
                        if (appConfig.getFreeField15() != null) {
                            existingAppConfig.setFreeField15(appConfig.getFreeField15());
                        }
                        if (appConfig.getFreeField15ContentType() != null) {
                            existingAppConfig.setFreeField15ContentType(appConfig.getFreeField15ContentType());
                        }
                        if (appConfig.getFreeField16() != null) {
                            existingAppConfig.setFreeField16(appConfig.getFreeField16());
                        }
                        if (appConfig.getFreeField17() != null) {
                            existingAppConfig.setFreeField17(appConfig.getFreeField17());
                        }
                        if (appConfig.getFreeField18() != null) {
                            existingAppConfig.setFreeField18(appConfig.getFreeField18());
                        }
                        if (appConfig.getFreeField18ContentType() != null) {
                            existingAppConfig.setFreeField18ContentType(appConfig.getFreeField18ContentType());
                        }
                        if (appConfig.getFreeField19() != null) {
                            existingAppConfig.setFreeField19(appConfig.getFreeField19());
                        }
                        if (appConfig.getDelflg() != null) {
                            existingAppConfig.setDelflg(appConfig.getDelflg());
                        }
                        if (appConfig.getTimestamp() != null) {
                            existingAppConfig.setTimestamp(appConfig.getTimestamp());
                        }
                        if (appConfig.getCreatedAt() != null) {
                            existingAppConfig.setCreatedAt(appConfig.getCreatedAt());
                        }
                        if (appConfig.getCreatedBy() != null) {
                            existingAppConfig.setCreatedBy(appConfig.getCreatedBy());
                        }
                        if (appConfig.getUpdatedAt() != null) {
                            existingAppConfig.setUpdatedAt(appConfig.getUpdatedAt());
                        }
                        if (appConfig.getUpdatedBy() != null) {
                            existingAppConfig.setUpdatedBy(appConfig.getUpdatedBy());
                        }

                        return existingAppConfig;
                    })
                    .flatMap(appConfigRepository::save)
                    .flatMap(savedAppConfig -> {
                        appConfigSearchRepository.save(savedAppConfig);

                        return Mono.just(savedAppConfig);
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
     * {@code GET  /app-configs} : get all the appConfigs.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of appConfigs in body.
     */
    @GetMapping("/app-configs")
    public Mono<List<AppConfig>> getAllAppConfigs() {
        log.debug("REST request to get all AppConfigs");
        return appConfigRepository.findAll().collectList();
    }

    /**
     * {@code GET  /app-configs} : get all the appConfigs as a stream.
     * @return the {@link Flux} of appConfigs.
     */
    @GetMapping(value = "/app-configs", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<AppConfig> getAllAppConfigsAsStream() {
        log.debug("REST request to get all AppConfigs as a stream");
        return appConfigRepository.findAll();
    }

    /**
     * {@code GET  /app-configs/:id} : get the "id" appConfig.
     *
     * @param id the id of the appConfig to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the appConfig, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/app-configs/{id}")
    public Mono<ResponseEntity<AppConfig>> getAppConfig(@PathVariable Long id) {
        log.debug("REST request to get AppConfig : {}", id);
        Mono<AppConfig> appConfig = appConfigRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(appConfig);
    }

    /**
     * {@code DELETE  /app-configs/:id} : delete the "id" appConfig.
     *
     * @param id the id of the appConfig to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/app-configs/{id}")
    public Mono<ResponseEntity<Void>> deleteAppConfig(@PathVariable Long id) {
        log.debug("REST request to delete AppConfig : {}", id);
        return appConfigRepository
            .deleteById(id)
            .then(appConfigSearchRepository.deleteById(id))
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
     * {@code SEARCH  /_search/app-configs?query=:query} : search for the appConfig corresponding
     * to the query.
     *
     * @param query the query of the appConfig search.
     * @return the result of the search.
     */
    @GetMapping("/_search/app-configs")
    public Mono<List<AppConfig>> searchAppConfigs(@RequestParam String query) {
        log.debug("REST request to search AppConfigs for query {}", query);
        return appConfigSearchRepository.search(query).collectList();
    }
}
