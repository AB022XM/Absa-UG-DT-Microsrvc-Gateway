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
import ug.co.absa.microsrvc.gateway.domain.CustomAudit;
import ug.co.absa.microsrvc.gateway.repository.CustomAuditRepository;
import ug.co.absa.microsrvc.gateway.repository.search.CustomAuditSearchRepository;
import ug.co.absa.microsrvc.gateway.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link ug.co.absa.microsrvc.gateway.domain.CustomAudit}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CustomAuditResource {

    private final Logger log = LoggerFactory.getLogger(CustomAuditResource.class);

    private static final String ENTITY_NAME = "customAudit";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CustomAuditRepository customAuditRepository;

    private final CustomAuditSearchRepository customAuditSearchRepository;

    public CustomAuditResource(CustomAuditRepository customAuditRepository, CustomAuditSearchRepository customAuditSearchRepository) {
        this.customAuditRepository = customAuditRepository;
        this.customAuditSearchRepository = customAuditSearchRepository;
    }

    /**
     * {@code POST  /custom-audits} : Create a new customAudit.
     *
     * @param customAudit the customAudit to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new customAudit, or with status {@code 400 (Bad Request)} if the customAudit has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/custom-audits")
    public Mono<ResponseEntity<CustomAudit>> createCustomAudit(@Valid @RequestBody CustomAudit customAudit) throws URISyntaxException {
        log.debug("REST request to save CustomAudit : {}", customAudit);
        if (customAudit.getId() != null) {
            throw new BadRequestAlertException("A new customAudit cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return customAuditRepository
            .save(customAudit)
            .flatMap(customAuditSearchRepository::save)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/custom-audits/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /custom-audits/:id} : Updates an existing customAudit.
     *
     * @param id the id of the customAudit to save.
     * @param customAudit the customAudit to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customAudit,
     * or with status {@code 400 (Bad Request)} if the customAudit is not valid,
     * or with status {@code 500 (Internal Server Error)} if the customAudit couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/custom-audits/{id}")
    public Mono<ResponseEntity<CustomAudit>> updateCustomAudit(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody CustomAudit customAudit
    ) throws URISyntaxException {
        log.debug("REST request to update CustomAudit : {}, {}", id, customAudit);
        if (customAudit.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, customAudit.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return customAuditRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return customAuditRepository
                    .save(customAudit)
                    .flatMap(customAuditSearchRepository::save)
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
     * {@code PATCH  /custom-audits/:id} : Partial updates given fields of an existing customAudit, field will ignore if it is null
     *
     * @param id the id of the customAudit to save.
     * @param customAudit the customAudit to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customAudit,
     * or with status {@code 400 (Bad Request)} if the customAudit is not valid,
     * or with status {@code 404 (Not Found)} if the customAudit is not found,
     * or with status {@code 500 (Internal Server Error)} if the customAudit couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/custom-audits/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<CustomAudit>> partialUpdateCustomAudit(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody CustomAudit customAudit
    ) throws URISyntaxException {
        log.debug("REST request to partial update CustomAudit partially : {}, {}", id, customAudit);
        if (customAudit.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, customAudit.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return customAuditRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<CustomAudit> result = customAuditRepository
                    .findById(customAudit.getId())
                    .map(existingCustomAudit -> {
                        if (customAudit.getAbsaTranRef() != null) {
                            existingCustomAudit.setAbsaTranRef(customAudit.getAbsaTranRef());
                        }
                        if (customAudit.getRecordId() != null) {
                            existingCustomAudit.setRecordId(customAudit.getRecordId());
                        }
                        if (customAudit.getActionId() != null) {
                            existingCustomAudit.setActionId(customAudit.getActionId());
                        }
                        if (customAudit.getTimestamp() != null) {
                            existingCustomAudit.setTimestamp(customAudit.getTimestamp());
                        }
                        if (customAudit.getOldValue() != null) {
                            existingCustomAudit.setOldValue(customAudit.getOldValue());
                        }
                        if (customAudit.getNewValue() != null) {
                            existingCustomAudit.setNewValue(customAudit.getNewValue());
                        }
                        if (customAudit.getChangeResaon() != null) {
                            existingCustomAudit.setChangeResaon(customAudit.getChangeResaon());
                        }
                        if (customAudit.getDescription() != null) {
                            existingCustomAudit.setDescription(customAudit.getDescription());
                        }
                        if (customAudit.getDescription1() != null) {
                            existingCustomAudit.setDescription1(customAudit.getDescription1());
                        }
                        if (customAudit.getDescription2() != null) {
                            existingCustomAudit.setDescription2(customAudit.getDescription2());
                        }
                        if (customAudit.getDescription3() != null) {
                            existingCustomAudit.setDescription3(customAudit.getDescription3());
                        }
                        if (customAudit.getDescription4() != null) {
                            existingCustomAudit.setDescription4(customAudit.getDescription4());
                        }
                        if (customAudit.getDescription5() != null) {
                            existingCustomAudit.setDescription5(customAudit.getDescription5());
                        }
                        if (customAudit.getDescription6() != null) {
                            existingCustomAudit.setDescription6(customAudit.getDescription6());
                        }
                        if (customAudit.getDescription7() != null) {
                            existingCustomAudit.setDescription7(customAudit.getDescription7());
                        }
                        if (customAudit.getDescription8() != null) {
                            existingCustomAudit.setDescription8(customAudit.getDescription8());
                        }
                        if (customAudit.getDescription9() != null) {
                            existingCustomAudit.setDescription9(customAudit.getDescription9());
                        }
                        if (customAudit.getFreeText1() != null) {
                            existingCustomAudit.setFreeText1(customAudit.getFreeText1());
                        }
                        if (customAudit.getFreeText2() != null) {
                            existingCustomAudit.setFreeText2(customAudit.getFreeText2());
                        }
                        if (customAudit.getFreeText3() != null) {
                            existingCustomAudit.setFreeText3(customAudit.getFreeText3());
                        }
                        if (customAudit.getFreeText4() != null) {
                            existingCustomAudit.setFreeText4(customAudit.getFreeText4());
                        }
                        if (customAudit.getFreeText5() != null) {
                            existingCustomAudit.setFreeText5(customAudit.getFreeText5());
                        }
                        if (customAudit.getFreeText6() != null) {
                            existingCustomAudit.setFreeText6(customAudit.getFreeText6());
                        }
                        if (customAudit.getFreeText7() != null) {
                            existingCustomAudit.setFreeText7(customAudit.getFreeText7());
                        }
                        if (customAudit.getFreeText8() != null) {
                            existingCustomAudit.setFreeText8(customAudit.getFreeText8());
                        }
                        if (customAudit.getFreeText9() != null) {
                            existingCustomAudit.setFreeText9(customAudit.getFreeText9());
                        }
                        if (customAudit.getFreeText10() != null) {
                            existingCustomAudit.setFreeText10(customAudit.getFreeText10());
                        }
                        if (customAudit.getFreeText11() != null) {
                            existingCustomAudit.setFreeText11(customAudit.getFreeText11());
                        }
                        if (customAudit.getFreeText12() != null) {
                            existingCustomAudit.setFreeText12(customAudit.getFreeText12());
                        }
                        if (customAudit.getFreeText13() != null) {
                            existingCustomAudit.setFreeText13(customAudit.getFreeText13());
                        }
                        if (customAudit.getFreeText14() != null) {
                            existingCustomAudit.setFreeText14(customAudit.getFreeText14());
                        }
                        if (customAudit.getFreeText15() != null) {
                            existingCustomAudit.setFreeText15(customAudit.getFreeText15());
                        }
                        if (customAudit.getFreeText16() != null) {
                            existingCustomAudit.setFreeText16(customAudit.getFreeText16());
                        }
                        if (customAudit.getFreeText17() != null) {
                            existingCustomAudit.setFreeText17(customAudit.getFreeText17());
                        }
                        if (customAudit.getFreeText18() != null) {
                            existingCustomAudit.setFreeText18(customAudit.getFreeText18());
                        }
                        if (customAudit.getFreeText19() != null) {
                            existingCustomAudit.setFreeText19(customAudit.getFreeText19());
                        }
                        if (customAudit.getFreeText20() != null) {
                            existingCustomAudit.setFreeText20(customAudit.getFreeText20());
                        }
                        if (customAudit.getFreeText21() != null) {
                            existingCustomAudit.setFreeText21(customAudit.getFreeText21());
                        }
                        if (customAudit.getFreeText22() != null) {
                            existingCustomAudit.setFreeText22(customAudit.getFreeText22());
                        }
                        if (customAudit.getFreeText23() != null) {
                            existingCustomAudit.setFreeText23(customAudit.getFreeText23());
                        }
                        if (customAudit.getFreeText24() != null) {
                            existingCustomAudit.setFreeText24(customAudit.getFreeText24());
                        }
                        if (customAudit.getFreeText25() != null) {
                            existingCustomAudit.setFreeText25(customAudit.getFreeText25());
                        }
                        if (customAudit.getFreeText26() != null) {
                            existingCustomAudit.setFreeText26(customAudit.getFreeText26());
                        }
                        if (customAudit.getFreeText27() != null) {
                            existingCustomAudit.setFreeText27(customAudit.getFreeText27());
                        }
                        if (customAudit.getFreeText28() != null) {
                            existingCustomAudit.setFreeText28(customAudit.getFreeText28());
                        }
                        if (customAudit.getCreatedAt() != null) {
                            existingCustomAudit.setCreatedAt(customAudit.getCreatedAt());
                        }
                        if (customAudit.getCreatedBy() != null) {
                            existingCustomAudit.setCreatedBy(customAudit.getCreatedBy());
                        }
                        if (customAudit.getUpdatedAt() != null) {
                            existingCustomAudit.setUpdatedAt(customAudit.getUpdatedAt());
                        }
                        if (customAudit.getUpdatedBy() != null) {
                            existingCustomAudit.setUpdatedBy(customAudit.getUpdatedBy());
                        }

                        return existingCustomAudit;
                    })
                    .flatMap(customAuditRepository::save)
                    .flatMap(savedCustomAudit -> {
                        customAuditSearchRepository.save(savedCustomAudit);

                        return Mono.just(savedCustomAudit);
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
     * {@code GET  /custom-audits} : get all the customAudits.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of customAudits in body.
     */
    @GetMapping("/custom-audits")
    public Mono<List<CustomAudit>> getAllCustomAudits() {
        log.debug("REST request to get all CustomAudits");
        return customAuditRepository.findAll().collectList();
    }

    /**
     * {@code GET  /custom-audits} : get all the customAudits as a stream.
     * @return the {@link Flux} of customAudits.
     */
    @GetMapping(value = "/custom-audits", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<CustomAudit> getAllCustomAuditsAsStream() {
        log.debug("REST request to get all CustomAudits as a stream");
        return customAuditRepository.findAll();
    }

    /**
     * {@code GET  /custom-audits/:id} : get the "id" customAudit.
     *
     * @param id the id of the customAudit to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the customAudit, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/custom-audits/{id}")
    public Mono<ResponseEntity<CustomAudit>> getCustomAudit(@PathVariable Long id) {
        log.debug("REST request to get CustomAudit : {}", id);
        Mono<CustomAudit> customAudit = customAuditRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(customAudit);
    }

    /**
     * {@code DELETE  /custom-audits/:id} : delete the "id" customAudit.
     *
     * @param id the id of the customAudit to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/custom-audits/{id}")
    public Mono<ResponseEntity<Void>> deleteCustomAudit(@PathVariable Long id) {
        log.debug("REST request to delete CustomAudit : {}", id);
        return customAuditRepository
            .deleteById(id)
            .then(customAuditSearchRepository.deleteById(id))
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
     * {@code SEARCH  /_search/custom-audits?query=:query} : search for the customAudit corresponding
     * to the query.
     *
     * @param query the query of the customAudit search.
     * @return the result of the search.
     */
    @GetMapping("/_search/custom-audits")
    public Mono<List<CustomAudit>> searchCustomAudits(@RequestParam String query) {
        log.debug("REST request to search CustomAudits for query {}", query);
        return customAuditSearchRepository.search(query).collectList();
    }
}
