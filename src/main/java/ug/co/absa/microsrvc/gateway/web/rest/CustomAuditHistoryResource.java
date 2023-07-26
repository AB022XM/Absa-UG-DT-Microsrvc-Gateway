package ug.co.absa.microsrvc.gateway.web.rest;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
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
import ug.co.absa.microsrvc.gateway.domain.CustomAuditHistory;
import ug.co.absa.microsrvc.gateway.repository.CustomAuditHistoryRepository;
import ug.co.absa.microsrvc.gateway.repository.search.CustomAuditHistorySearchRepository;
import ug.co.absa.microsrvc.gateway.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link ug.co.absa.microsrvc.gateway.domain.CustomAuditHistory}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CustomAuditHistoryResource {

    private final Logger log = LoggerFactory.getLogger(CustomAuditHistoryResource.class);

    private static final String ENTITY_NAME = "customAuditHistory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CustomAuditHistoryRepository customAuditHistoryRepository;

    private final CustomAuditHistorySearchRepository customAuditHistorySearchRepository;

    public CustomAuditHistoryResource(
        CustomAuditHistoryRepository customAuditHistoryRepository,
        CustomAuditHistorySearchRepository customAuditHistorySearchRepository
    ) {
        this.customAuditHistoryRepository = customAuditHistoryRepository;
        this.customAuditHistorySearchRepository = customAuditHistorySearchRepository;
    }

    /**
     * {@code POST  /custom-audit-histories} : Create a new customAuditHistory.
     *
     * @param customAuditHistory the customAuditHistory to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new customAuditHistory, or with status {@code 400 (Bad Request)} if the customAuditHistory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/custom-audit-histories")
    public Mono<ResponseEntity<CustomAuditHistory>> createCustomAuditHistory(@Valid @RequestBody CustomAuditHistory customAuditHistory)
        throws URISyntaxException {
        log.debug("REST request to save CustomAuditHistory : {}", customAuditHistory);
        if (customAuditHistory.getId() != null) {
            throw new BadRequestAlertException("A new customAuditHistory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        customAuditHistory.setId(UUID.randomUUID());
        return customAuditHistoryRepository
            .save(customAuditHistory)
            .flatMap(customAuditHistorySearchRepository::save)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/custom-audit-histories/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /custom-audit-histories/:id} : Updates an existing customAuditHistory.
     *
     * @param id the id of the customAuditHistory to save.
     * @param customAuditHistory the customAuditHistory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customAuditHistory,
     * or with status {@code 400 (Bad Request)} if the customAuditHistory is not valid,
     * or with status {@code 500 (Internal Server Error)} if the customAuditHistory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/custom-audit-histories/{id}")
    public Mono<ResponseEntity<CustomAuditHistory>> updateCustomAuditHistory(
        @PathVariable(value = "id", required = false) final UUID id,
        @Valid @RequestBody CustomAuditHistory customAuditHistory
    ) throws URISyntaxException {
        log.debug("REST request to update CustomAuditHistory : {}, {}", id, customAuditHistory);
        if (customAuditHistory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, customAuditHistory.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return customAuditHistoryRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return customAuditHistoryRepository
                    .save(customAuditHistory.setIsPersisted())
                    .flatMap(customAuditHistorySearchRepository::save)
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
     * {@code PATCH  /custom-audit-histories/:id} : Partial updates given fields of an existing customAuditHistory, field will ignore if it is null
     *
     * @param id the id of the customAuditHistory to save.
     * @param customAuditHistory the customAuditHistory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customAuditHistory,
     * or with status {@code 400 (Bad Request)} if the customAuditHistory is not valid,
     * or with status {@code 404 (Not Found)} if the customAuditHistory is not found,
     * or with status {@code 500 (Internal Server Error)} if the customAuditHistory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/custom-audit-histories/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<CustomAuditHistory>> partialUpdateCustomAuditHistory(
        @PathVariable(value = "id", required = false) final UUID id,
        @NotNull @RequestBody CustomAuditHistory customAuditHistory
    ) throws URISyntaxException {
        log.debug("REST request to partial update CustomAuditHistory partially : {}, {}", id, customAuditHistory);
        if (customAuditHistory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, customAuditHistory.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return customAuditHistoryRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<CustomAuditHistory> result = customAuditHistoryRepository
                    .findById(customAuditHistory.getId())
                    .map(existingCustomAuditHistory -> {
                        if (customAuditHistory.getRecordId() != null) {
                            existingCustomAuditHistory.setRecordId(customAuditHistory.getRecordId());
                        }
                        if (customAuditHistory.getActionId() != null) {
                            existingCustomAuditHistory.setActionId(customAuditHistory.getActionId());
                        }
                        if (customAuditHistory.getTimestamp() != null) {
                            existingCustomAuditHistory.setTimestamp(customAuditHistory.getTimestamp());
                        }
                        if (customAuditHistory.getOldValue() != null) {
                            existingCustomAuditHistory.setOldValue(customAuditHistory.getOldValue());
                        }
                        if (customAuditHistory.getNewValue() != null) {
                            existingCustomAuditHistory.setNewValue(customAuditHistory.getNewValue());
                        }
                        if (customAuditHistory.getChangeReason() != null) {
                            existingCustomAuditHistory.setChangeReason(customAuditHistory.getChangeReason());
                        }
                        if (customAuditHistory.getDescription() != null) {
                            existingCustomAuditHistory.setDescription(customAuditHistory.getDescription());
                        }
                        if (customAuditHistory.getDescription1() != null) {
                            existingCustomAuditHistory.setDescription1(customAuditHistory.getDescription1());
                        }
                        if (customAuditHistory.getDescription2() != null) {
                            existingCustomAuditHistory.setDescription2(customAuditHistory.getDescription2());
                        }
                        if (customAuditHistory.getDescription3() != null) {
                            existingCustomAuditHistory.setDescription3(customAuditHistory.getDescription3());
                        }
                        if (customAuditHistory.getDescription4() != null) {
                            existingCustomAuditHistory.setDescription4(customAuditHistory.getDescription4());
                        }
                        if (customAuditHistory.getDescription5() != null) {
                            existingCustomAuditHistory.setDescription5(customAuditHistory.getDescription5());
                        }
                        if (customAuditHistory.getDescription6() != null) {
                            existingCustomAuditHistory.setDescription6(customAuditHistory.getDescription6());
                        }
                        if (customAuditHistory.getDescription7() != null) {
                            existingCustomAuditHistory.setDescription7(customAuditHistory.getDescription7());
                        }
                        if (customAuditHistory.getDescription8() != null) {
                            existingCustomAuditHistory.setDescription8(customAuditHistory.getDescription8());
                        }
                        if (customAuditHistory.getDescription9() != null) {
                            existingCustomAuditHistory.setDescription9(customAuditHistory.getDescription9());
                        }
                        if (customAuditHistory.getFreeText1() != null) {
                            existingCustomAuditHistory.setFreeText1(customAuditHistory.getFreeText1());
                        }
                        if (customAuditHistory.getFreeText2() != null) {
                            existingCustomAuditHistory.setFreeText2(customAuditHistory.getFreeText2());
                        }
                        if (customAuditHistory.getFreeText3() != null) {
                            existingCustomAuditHistory.setFreeText3(customAuditHistory.getFreeText3());
                        }
                        if (customAuditHistory.getFreeText4() != null) {
                            existingCustomAuditHistory.setFreeText4(customAuditHistory.getFreeText4());
                        }
                        if (customAuditHistory.getFreeText5() != null) {
                            existingCustomAuditHistory.setFreeText5(customAuditHistory.getFreeText5());
                        }
                        if (customAuditHistory.getFreeText6() != null) {
                            existingCustomAuditHistory.setFreeText6(customAuditHistory.getFreeText6());
                        }
                        if (customAuditHistory.getFreeText7() != null) {
                            existingCustomAuditHistory.setFreeText7(customAuditHistory.getFreeText7());
                        }
                        if (customAuditHistory.getFreeText8() != null) {
                            existingCustomAuditHistory.setFreeText8(customAuditHistory.getFreeText8());
                        }
                        if (customAuditHistory.getFreeText9() != null) {
                            existingCustomAuditHistory.setFreeText9(customAuditHistory.getFreeText9());
                        }
                        if (customAuditHistory.getFreeText10() != null) {
                            existingCustomAuditHistory.setFreeText10(customAuditHistory.getFreeText10());
                        }
                        if (customAuditHistory.getFreeText11() != null) {
                            existingCustomAuditHistory.setFreeText11(customAuditHistory.getFreeText11());
                        }
                        if (customAuditHistory.getFreeText12() != null) {
                            existingCustomAuditHistory.setFreeText12(customAuditHistory.getFreeText12());
                        }
                        if (customAuditHistory.getFreeText13() != null) {
                            existingCustomAuditHistory.setFreeText13(customAuditHistory.getFreeText13());
                        }
                        if (customAuditHistory.getFreeText14() != null) {
                            existingCustomAuditHistory.setFreeText14(customAuditHistory.getFreeText14());
                        }
                        if (customAuditHistory.getFreeText15() != null) {
                            existingCustomAuditHistory.setFreeText15(customAuditHistory.getFreeText15());
                        }
                        if (customAuditHistory.getFreeText16() != null) {
                            existingCustomAuditHistory.setFreeText16(customAuditHistory.getFreeText16());
                        }
                        if (customAuditHistory.getFreeText17() != null) {
                            existingCustomAuditHistory.setFreeText17(customAuditHistory.getFreeText17());
                        }
                        if (customAuditHistory.getFreeText18() != null) {
                            existingCustomAuditHistory.setFreeText18(customAuditHistory.getFreeText18());
                        }
                        if (customAuditHistory.getFreeText19() != null) {
                            existingCustomAuditHistory.setFreeText19(customAuditHistory.getFreeText19());
                        }
                        if (customAuditHistory.getFreeText20() != null) {
                            existingCustomAuditHistory.setFreeText20(customAuditHistory.getFreeText20());
                        }
                        if (customAuditHistory.getFreeText21() != null) {
                            existingCustomAuditHistory.setFreeText21(customAuditHistory.getFreeText21());
                        }
                        if (customAuditHistory.getFreeText22() != null) {
                            existingCustomAuditHistory.setFreeText22(customAuditHistory.getFreeText22());
                        }
                        if (customAuditHistory.getFreeText23() != null) {
                            existingCustomAuditHistory.setFreeText23(customAuditHistory.getFreeText23());
                        }
                        if (customAuditHistory.getFreeText24() != null) {
                            existingCustomAuditHistory.setFreeText24(customAuditHistory.getFreeText24());
                        }
                        if (customAuditHistory.getFreeText25() != null) {
                            existingCustomAuditHistory.setFreeText25(customAuditHistory.getFreeText25());
                        }
                        if (customAuditHistory.getFreeText26() != null) {
                            existingCustomAuditHistory.setFreeText26(customAuditHistory.getFreeText26());
                        }
                        if (customAuditHistory.getFreeText27() != null) {
                            existingCustomAuditHistory.setFreeText27(customAuditHistory.getFreeText27());
                        }
                        if (customAuditHistory.getFreeText28() != null) {
                            existingCustomAuditHistory.setFreeText28(customAuditHistory.getFreeText28());
                        }
                        if (customAuditHistory.getCreatedAt() != null) {
                            existingCustomAuditHistory.setCreatedAt(customAuditHistory.getCreatedAt());
                        }
                        if (customAuditHistory.getCreatedBy() != null) {
                            existingCustomAuditHistory.setCreatedBy(customAuditHistory.getCreatedBy());
                        }
                        if (customAuditHistory.getUpdatedAt() != null) {
                            existingCustomAuditHistory.setUpdatedAt(customAuditHistory.getUpdatedAt());
                        }
                        if (customAuditHistory.getUpdatedBy() != null) {
                            existingCustomAuditHistory.setUpdatedBy(customAuditHistory.getUpdatedBy());
                        }

                        return existingCustomAuditHistory;
                    })
                    .flatMap(customAuditHistoryRepository::save)
                    .flatMap(savedCustomAuditHistory -> {
                        customAuditHistorySearchRepository.save(savedCustomAuditHistory);

                        return Mono.just(savedCustomAuditHistory);
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
     * {@code GET  /custom-audit-histories} : get all the customAuditHistories.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of customAuditHistories in body.
     */
    @GetMapping("/custom-audit-histories")
    public Mono<List<CustomAuditHistory>> getAllCustomAuditHistories() {
        log.debug("REST request to get all CustomAuditHistories");
        return customAuditHistoryRepository.findAll().collectList();
    }

    /**
     * {@code GET  /custom-audit-histories} : get all the customAuditHistories as a stream.
     * @return the {@link Flux} of customAuditHistories.
     */
    @GetMapping(value = "/custom-audit-histories", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<CustomAuditHistory> getAllCustomAuditHistoriesAsStream() {
        log.debug("REST request to get all CustomAuditHistories as a stream");
        return customAuditHistoryRepository.findAll();
    }

    /**
     * {@code GET  /custom-audit-histories/:id} : get the "id" customAuditHistory.
     *
     * @param id the id of the customAuditHistory to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the customAuditHistory, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/custom-audit-histories/{id}")
    public Mono<ResponseEntity<CustomAuditHistory>> getCustomAuditHistory(@PathVariable UUID id) {
        log.debug("REST request to get CustomAuditHistory : {}", id);
        Mono<CustomAuditHistory> customAuditHistory = customAuditHistoryRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(customAuditHistory);
    }

    /**
     * {@code DELETE  /custom-audit-histories/:id} : delete the "id" customAuditHistory.
     *
     * @param id the id of the customAuditHistory to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/custom-audit-histories/{id}")
    public Mono<ResponseEntity<Void>> deleteCustomAuditHistory(@PathVariable UUID id) {
        log.debug("REST request to delete CustomAuditHistory : {}", id);
        return customAuditHistoryRepository
            .deleteById(id)
            .then(customAuditHistorySearchRepository.deleteById(id))
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
     * {@code SEARCH  /_search/custom-audit-histories?query=:query} : search for the customAuditHistory corresponding
     * to the query.
     *
     * @param query the query of the customAuditHistory search.
     * @return the result of the search.
     */
    @GetMapping("/_search/custom-audit-histories")
    public Mono<List<CustomAuditHistory>> searchCustomAuditHistories(@RequestParam String query) {
        log.debug("REST request to search CustomAuditHistories for query {}", query);
        return customAuditHistorySearchRepository.search(query).collectList();
    }
}
