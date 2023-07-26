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
import ug.co.absa.microsrvc.gateway.domain.Apps;
import ug.co.absa.microsrvc.gateway.repository.AppsRepository;
import ug.co.absa.microsrvc.gateway.repository.search.AppsSearchRepository;
import ug.co.absa.microsrvc.gateway.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link ug.co.absa.microsrvc.gateway.domain.Apps}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class AppsResource {

    private final Logger log = LoggerFactory.getLogger(AppsResource.class);

    private static final String ENTITY_NAME = "apps";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final AppsRepository appsRepository;

    private final AppsSearchRepository appsSearchRepository;

    public AppsResource(AppsRepository appsRepository, AppsSearchRepository appsSearchRepository) {
        this.appsRepository = appsRepository;
        this.appsSearchRepository = appsSearchRepository;
    }

    /**
     * {@code POST  /apps} : Create a new apps.
     *
     * @param apps the apps to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new apps, or with status {@code 400 (Bad Request)} if the apps has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/apps")
    public Mono<ResponseEntity<Apps>> createApps(@Valid @RequestBody Apps apps) throws URISyntaxException {
        log.debug("REST request to save Apps : {}", apps);
        if (apps.getId() != null) {
            throw new BadRequestAlertException("A new apps cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return appsRepository
            .save(apps)
            .flatMap(appsSearchRepository::save)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/apps/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /apps/:id} : Updates an existing apps.
     *
     * @param id the id of the apps to save.
     * @param apps the apps to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated apps,
     * or with status {@code 400 (Bad Request)} if the apps is not valid,
     * or with status {@code 500 (Internal Server Error)} if the apps couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/apps/{id}")
    public Mono<ResponseEntity<Apps>> updateApps(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Apps apps
    ) throws URISyntaxException {
        log.debug("REST request to update Apps : {}, {}", id, apps);
        if (apps.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, apps.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return appsRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return appsRepository
                    .save(apps)
                    .flatMap(appsSearchRepository::save)
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
     * {@code PATCH  /apps/:id} : Partial updates given fields of an existing apps, field will ignore if it is null
     *
     * @param id the id of the apps to save.
     * @param apps the apps to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated apps,
     * or with status {@code 400 (Bad Request)} if the apps is not valid,
     * or with status {@code 404 (Not Found)} if the apps is not found,
     * or with status {@code 500 (Internal Server Error)} if the apps couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/apps/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<Apps>> partialUpdateApps(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Apps apps
    ) throws URISyntaxException {
        log.debug("REST request to partial update Apps partially : {}, {}", id, apps);
        if (apps.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, apps.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return appsRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<Apps> result = appsRepository
                    .findById(apps.getId())
                    .map(existingApps -> {
                        if (apps.getRecordId() != null) {
                            existingApps.setRecordId(apps.getRecordId());
                        }
                        if (apps.getConfigId() != null) {
                            existingApps.setConfigId(apps.getConfigId());
                        }
                        if (apps.getAppId() != null) {
                            existingApps.setAppId(apps.getAppId());
                        }
                        if (apps.getAppCurrentVersion() != null) {
                            existingApps.setAppCurrentVersion(apps.getAppCurrentVersion());
                        }
                        if (apps.getAppName() != null) {
                            existingApps.setAppName(apps.getAppName());
                        }
                        if (apps.getAppDescription() != null) {
                            existingApps.setAppDescription(apps.getAppDescription());
                        }
                        if (apps.getAppStatus() != null) {
                            existingApps.setAppStatus(apps.getAppStatus());
                        }
                        if (apps.getFreeField1() != null) {
                            existingApps.setFreeField1(apps.getFreeField1());
                        }
                        if (apps.getFreeField2() != null) {
                            existingApps.setFreeField2(apps.getFreeField2());
                        }
                        if (apps.getFreeField3() != null) {
                            existingApps.setFreeField3(apps.getFreeField3());
                        }
                        if (apps.getFreeField4() != null) {
                            existingApps.setFreeField4(apps.getFreeField4());
                        }
                        if (apps.getFreeField5() != null) {
                            existingApps.setFreeField5(apps.getFreeField5());
                        }
                        if (apps.getFreeField6() != null) {
                            existingApps.setFreeField6(apps.getFreeField6());
                        }
                        if (apps.getFreeField7() != null) {
                            existingApps.setFreeField7(apps.getFreeField7());
                        }
                        if (apps.getFreeField8() != null) {
                            existingApps.setFreeField8(apps.getFreeField8());
                        }
                        if (apps.getFreeField9() != null) {
                            existingApps.setFreeField9(apps.getFreeField9());
                        }
                        if (apps.getFreeField10() != null) {
                            existingApps.setFreeField10(apps.getFreeField10());
                        }
                        if (apps.getFreeField11() != null) {
                            existingApps.setFreeField11(apps.getFreeField11());
                        }
                        if (apps.getFreeField12() != null) {
                            existingApps.setFreeField12(apps.getFreeField12());
                        }
                        if (apps.getFreeField13() != null) {
                            existingApps.setFreeField13(apps.getFreeField13());
                        }
                        if (apps.getDelflg() != null) {
                            existingApps.setDelflg(apps.getDelflg());
                        }
                        if (apps.getTimestamp() != null) {
                            existingApps.setTimestamp(apps.getTimestamp());
                        }
                        if (apps.getCreatedAt() != null) {
                            existingApps.setCreatedAt(apps.getCreatedAt());
                        }
                        if (apps.getCreatedBy() != null) {
                            existingApps.setCreatedBy(apps.getCreatedBy());
                        }
                        if (apps.getUpdatedAt() != null) {
                            existingApps.setUpdatedAt(apps.getUpdatedAt());
                        }
                        if (apps.getUpdatedby() != null) {
                            existingApps.setUpdatedby(apps.getUpdatedby());
                        }

                        return existingApps;
                    })
                    .flatMap(appsRepository::save)
                    .flatMap(savedApps -> {
                        appsSearchRepository.save(savedApps);

                        return Mono.just(savedApps);
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
     * {@code GET  /apps} : get all the apps.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of apps in body.
     */
    @GetMapping("/apps")
    public Mono<List<Apps>> getAllApps() {
        log.debug("REST request to get all Apps");
        return appsRepository.findAll().collectList();
    }

    /**
     * {@code GET  /apps} : get all the apps as a stream.
     * @return the {@link Flux} of apps.
     */
    @GetMapping(value = "/apps", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<Apps> getAllAppsAsStream() {
        log.debug("REST request to get all Apps as a stream");
        return appsRepository.findAll();
    }

    /**
     * {@code GET  /apps/:id} : get the "id" apps.
     *
     * @param id the id of the apps to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the apps, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/apps/{id}")
    public Mono<ResponseEntity<Apps>> getApps(@PathVariable Long id) {
        log.debug("REST request to get Apps : {}", id);
        Mono<Apps> apps = appsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(apps);
    }

    /**
     * {@code DELETE  /apps/:id} : delete the "id" apps.
     *
     * @param id the id of the apps to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/apps/{id}")
    public Mono<ResponseEntity<Void>> deleteApps(@PathVariable Long id) {
        log.debug("REST request to delete Apps : {}", id);
        return appsRepository
            .deleteById(id)
            .then(appsSearchRepository.deleteById(id))
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
     * {@code SEARCH  /_search/apps?query=:query} : search for the apps corresponding
     * to the query.
     *
     * @param query the query of the apps search.
     * @return the result of the search.
     */
    @GetMapping("/_search/apps")
    public Mono<List<Apps>> searchApps(@RequestParam String query) {
        log.debug("REST request to search Apps for query {}", query);
        return appsSearchRepository.search(query).collectList();
    }
}
