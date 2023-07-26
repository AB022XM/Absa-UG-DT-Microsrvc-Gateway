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
import ug.co.absa.microsrvc.gateway.domain.BlockedAccounts;
import ug.co.absa.microsrvc.gateway.repository.BlockedAccountsRepository;
import ug.co.absa.microsrvc.gateway.repository.search.BlockedAccountsSearchRepository;
import ug.co.absa.microsrvc.gateway.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link ug.co.absa.microsrvc.gateway.domain.BlockedAccounts}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class BlockedAccountsResource {

    private final Logger log = LoggerFactory.getLogger(BlockedAccountsResource.class);

    private static final String ENTITY_NAME = "blockedAccounts";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BlockedAccountsRepository blockedAccountsRepository;

    private final BlockedAccountsSearchRepository blockedAccountsSearchRepository;

    public BlockedAccountsResource(
        BlockedAccountsRepository blockedAccountsRepository,
        BlockedAccountsSearchRepository blockedAccountsSearchRepository
    ) {
        this.blockedAccountsRepository = blockedAccountsRepository;
        this.blockedAccountsSearchRepository = blockedAccountsSearchRepository;
    }

    /**
     * {@code POST  /blocked-accounts} : Create a new blockedAccounts.
     *
     * @param blockedAccounts the blockedAccounts to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new blockedAccounts, or with status {@code 400 (Bad Request)} if the blockedAccounts has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/blocked-accounts")
    public Mono<ResponseEntity<BlockedAccounts>> createBlockedAccounts(@Valid @RequestBody BlockedAccounts blockedAccounts)
        throws URISyntaxException {
        log.debug("REST request to save BlockedAccounts : {}", blockedAccounts);
        if (blockedAccounts.getId() != null) {
            throw new BadRequestAlertException("A new blockedAccounts cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return blockedAccountsRepository
            .save(blockedAccounts)
            .flatMap(blockedAccountsSearchRepository::save)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/blocked-accounts/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /blocked-accounts/:id} : Updates an existing blockedAccounts.
     *
     * @param id the id of the blockedAccounts to save.
     * @param blockedAccounts the blockedAccounts to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated blockedAccounts,
     * or with status {@code 400 (Bad Request)} if the blockedAccounts is not valid,
     * or with status {@code 500 (Internal Server Error)} if the blockedAccounts couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/blocked-accounts/{id}")
    public Mono<ResponseEntity<BlockedAccounts>> updateBlockedAccounts(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody BlockedAccounts blockedAccounts
    ) throws URISyntaxException {
        log.debug("REST request to update BlockedAccounts : {}, {}", id, blockedAccounts);
        if (blockedAccounts.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, blockedAccounts.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return blockedAccountsRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return blockedAccountsRepository
                    .save(blockedAccounts)
                    .flatMap(blockedAccountsSearchRepository::save)
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
     * {@code PATCH  /blocked-accounts/:id} : Partial updates given fields of an existing blockedAccounts, field will ignore if it is null
     *
     * @param id the id of the blockedAccounts to save.
     * @param blockedAccounts the blockedAccounts to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated blockedAccounts,
     * or with status {@code 400 (Bad Request)} if the blockedAccounts is not valid,
     * or with status {@code 404 (Not Found)} if the blockedAccounts is not found,
     * or with status {@code 500 (Internal Server Error)} if the blockedAccounts couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/blocked-accounts/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<BlockedAccounts>> partialUpdateBlockedAccounts(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody BlockedAccounts blockedAccounts
    ) throws URISyntaxException {
        log.debug("REST request to partial update BlockedAccounts partially : {}, {}", id, blockedAccounts);
        if (blockedAccounts.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, blockedAccounts.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return blockedAccountsRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<BlockedAccounts> result = blockedAccountsRepository
                    .findById(blockedAccounts.getId())
                    .map(existingBlockedAccounts -> {
                        if (blockedAccounts.getAbsaTranRef() != null) {
                            existingBlockedAccounts.setAbsaTranRef(blockedAccounts.getAbsaTranRef());
                        }
                        if (blockedAccounts.getCustomerId() != null) {
                            existingBlockedAccounts.setCustomerId(blockedAccounts.getCustomerId());
                        }
                        if (blockedAccounts.getCustomerAccountNumber() != null) {
                            existingBlockedAccounts.setCustomerAccountNumber(blockedAccounts.getCustomerAccountNumber());
                        }
                        if (blockedAccounts.getDtDTransactionId() != null) {
                            existingBlockedAccounts.setDtDTransactionId(blockedAccounts.getDtDTransactionId());
                        }
                        if (blockedAccounts.getBlockId() != null) {
                            existingBlockedAccounts.setBlockId(blockedAccounts.getBlockId());
                        }
                        if (blockedAccounts.getBlockCode() != null) {
                            existingBlockedAccounts.setBlockCode(blockedAccounts.getBlockCode());
                        }
                        if (blockedAccounts.getBlockDate() != null) {
                            existingBlockedAccounts.setBlockDate(blockedAccounts.getBlockDate());
                        }
                        if (blockedAccounts.getBlockType() != null) {
                            existingBlockedAccounts.setBlockType(blockedAccounts.getBlockType());
                        }
                        if (blockedAccounts.getBlockStatus() != null) {
                            existingBlockedAccounts.setBlockStatus(blockedAccounts.getBlockStatus());
                        }
                        if (blockedAccounts.getBlockReason() != null) {
                            existingBlockedAccounts.setBlockReason(blockedAccounts.getBlockReason());
                        }
                        if (blockedAccounts.getBlockReasonCode1() != null) {
                            existingBlockedAccounts.setBlockReasonCode1(blockedAccounts.getBlockReasonCode1());
                        }
                        if (blockedAccounts.getBlockReasonCode2() != null) {
                            existingBlockedAccounts.setBlockReasonCode2(blockedAccounts.getBlockReasonCode2());
                        }
                        if (blockedAccounts.getBlockReason1() != null) {
                            existingBlockedAccounts.setBlockReason1(blockedAccounts.getBlockReason1());
                        }
                        if (blockedAccounts.getBlockReasonCode3() != null) {
                            existingBlockedAccounts.setBlockReasonCode3(blockedAccounts.getBlockReasonCode3());
                        }
                        if (blockedAccounts.getBlockReasonCode4() != null) {
                            existingBlockedAccounts.setBlockReasonCode4(blockedAccounts.getBlockReasonCode4());
                        }
                        if (blockedAccounts.getStartDate() != null) {
                            existingBlockedAccounts.setStartDate(blockedAccounts.getStartDate());
                        }
                        if (blockedAccounts.getEndDate() != null) {
                            existingBlockedAccounts.setEndDate(blockedAccounts.getEndDate());
                        }
                        if (blockedAccounts.getIsTemp() != null) {
                            existingBlockedAccounts.setIsTemp(blockedAccounts.getIsTemp());
                        }
                        if (blockedAccounts.getBlockFreeText() != null) {
                            existingBlockedAccounts.setBlockFreeText(blockedAccounts.getBlockFreeText());
                        }
                        if (blockedAccounts.getBlockFreeText1() != null) {
                            existingBlockedAccounts.setBlockFreeText1(blockedAccounts.getBlockFreeText1());
                        }
                        if (blockedAccounts.getBlockFreeText2() != null) {
                            existingBlockedAccounts.setBlockFreeText2(blockedAccounts.getBlockFreeText2());
                        }
                        if (blockedAccounts.getBlockFreeText3() != null) {
                            existingBlockedAccounts.setBlockFreeText3(blockedAccounts.getBlockFreeText3());
                        }
                        if (blockedAccounts.getBlockFreeText4() != null) {
                            existingBlockedAccounts.setBlockFreeText4(blockedAccounts.getBlockFreeText4());
                        }
                        if (blockedAccounts.getBlockFreeText5() != null) {
                            existingBlockedAccounts.setBlockFreeText5(blockedAccounts.getBlockFreeText5());
                        }
                        if (blockedAccounts.getBlockFreeText6() != null) {
                            existingBlockedAccounts.setBlockFreeText6(blockedAccounts.getBlockFreeText6());
                        }
                        if (blockedAccounts.getBlockReasonCode5() != null) {
                            existingBlockedAccounts.setBlockReasonCode5(blockedAccounts.getBlockReasonCode5());
                        }
                        if (blockedAccounts.getBlockReasonCode6() != null) {
                            existingBlockedAccounts.setBlockReasonCode6(blockedAccounts.getBlockReasonCode6());
                        }
                        if (blockedAccounts.getBlockReasonCode7() != null) {
                            existingBlockedAccounts.setBlockReasonCode7(blockedAccounts.getBlockReasonCode7());
                        }
                        if (blockedAccounts.getBlockReasonCode8() != null) {
                            existingBlockedAccounts.setBlockReasonCode8(blockedAccounts.getBlockReasonCode8());
                        }
                        if (blockedAccounts.getBlockReasonCode9() != null) {
                            existingBlockedAccounts.setBlockReasonCode9(blockedAccounts.getBlockReasonCode9());
                        }
                        if (blockedAccounts.getBlockReasonCode10() != null) {
                            existingBlockedAccounts.setBlockReasonCode10(blockedAccounts.getBlockReasonCode10());
                        }
                        if (blockedAccounts.getBlockReasonCode11() != null) {
                            existingBlockedAccounts.setBlockReasonCode11(blockedAccounts.getBlockReasonCode11());
                        }
                        if (blockedAccounts.getBlockReasonCode12() != null) {
                            existingBlockedAccounts.setBlockReasonCode12(blockedAccounts.getBlockReasonCode12());
                        }
                        if (blockedAccounts.getBlockReasonCode13() != null) {
                            existingBlockedAccounts.setBlockReasonCode13(blockedAccounts.getBlockReasonCode13());
                        }
                        if (blockedAccounts.getBlockReasonCode14() != null) {
                            existingBlockedAccounts.setBlockReasonCode14(blockedAccounts.getBlockReasonCode14());
                        }
                        if (blockedAccounts.getBlockReasonCode15() != null) {
                            existingBlockedAccounts.setBlockReasonCode15(blockedAccounts.getBlockReasonCode15());
                        }
                        if (blockedAccounts.getBlockReasonCode16() != null) {
                            existingBlockedAccounts.setBlockReasonCode16(blockedAccounts.getBlockReasonCode16());
                        }
                        if (blockedAccounts.getCreatedAt() != null) {
                            existingBlockedAccounts.setCreatedAt(blockedAccounts.getCreatedAt());
                        }
                        if (blockedAccounts.getCreatedBy() != null) {
                            existingBlockedAccounts.setCreatedBy(blockedAccounts.getCreatedBy());
                        }
                        if (blockedAccounts.getUpdatedAt() != null) {
                            existingBlockedAccounts.setUpdatedAt(blockedAccounts.getUpdatedAt());
                        }
                        if (blockedAccounts.getUpdatedBy() != null) {
                            existingBlockedAccounts.setUpdatedBy(blockedAccounts.getUpdatedBy());
                        }
                        if (blockedAccounts.getDelflg() != null) {
                            existingBlockedAccounts.setDelflg(blockedAccounts.getDelflg());
                        }
                        if (blockedAccounts.getStatus() != null) {
                            existingBlockedAccounts.setStatus(blockedAccounts.getStatus());
                        }

                        return existingBlockedAccounts;
                    })
                    .flatMap(blockedAccountsRepository::save)
                    .flatMap(savedBlockedAccounts -> {
                        blockedAccountsSearchRepository.save(savedBlockedAccounts);

                        return Mono.just(savedBlockedAccounts);
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
     * {@code GET  /blocked-accounts} : get all the blockedAccounts.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of blockedAccounts in body.
     */
    @GetMapping("/blocked-accounts")
    public Mono<List<BlockedAccounts>> getAllBlockedAccounts() {
        log.debug("REST request to get all BlockedAccounts");
        return blockedAccountsRepository.findAll().collectList();
    }

    /**
     * {@code GET  /blocked-accounts} : get all the blockedAccounts as a stream.
     * @return the {@link Flux} of blockedAccounts.
     */
    @GetMapping(value = "/blocked-accounts", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<BlockedAccounts> getAllBlockedAccountsAsStream() {
        log.debug("REST request to get all BlockedAccounts as a stream");
        return blockedAccountsRepository.findAll();
    }

    /**
     * {@code GET  /blocked-accounts/:id} : get the "id" blockedAccounts.
     *
     * @param id the id of the blockedAccounts to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the blockedAccounts, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/blocked-accounts/{id}")
    public Mono<ResponseEntity<BlockedAccounts>> getBlockedAccounts(@PathVariable Long id) {
        log.debug("REST request to get BlockedAccounts : {}", id);
        Mono<BlockedAccounts> blockedAccounts = blockedAccountsRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(blockedAccounts);
    }

    /**
     * {@code DELETE  /blocked-accounts/:id} : delete the "id" blockedAccounts.
     *
     * @param id the id of the blockedAccounts to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/blocked-accounts/{id}")
    public Mono<ResponseEntity<Void>> deleteBlockedAccounts(@PathVariable Long id) {
        log.debug("REST request to delete BlockedAccounts : {}", id);
        return blockedAccountsRepository
            .deleteById(id)
            .then(blockedAccountsSearchRepository.deleteById(id))
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
     * {@code SEARCH  /_search/blocked-accounts?query=:query} : search for the blockedAccounts corresponding
     * to the query.
     *
     * @param query the query of the blockedAccounts search.
     * @return the result of the search.
     */
    @GetMapping("/_search/blocked-accounts")
    public Mono<List<BlockedAccounts>> searchBlockedAccounts(@RequestParam String query) {
        log.debug("REST request to search BlockedAccounts for query {}", query);
        return blockedAccountsSearchRepository.search(query).collectList();
    }
}
