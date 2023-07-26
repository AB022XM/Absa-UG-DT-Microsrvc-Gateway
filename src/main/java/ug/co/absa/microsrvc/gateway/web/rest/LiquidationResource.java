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
import ug.co.absa.microsrvc.gateway.domain.Liquidation;
import ug.co.absa.microsrvc.gateway.repository.LiquidationRepository;
import ug.co.absa.microsrvc.gateway.repository.search.LiquidationSearchRepository;
import ug.co.absa.microsrvc.gateway.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link ug.co.absa.microsrvc.gateway.domain.Liquidation}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class LiquidationResource {

    private final Logger log = LoggerFactory.getLogger(LiquidationResource.class);

    private static final String ENTITY_NAME = "liquidation";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final LiquidationRepository liquidationRepository;

    private final LiquidationSearchRepository liquidationSearchRepository;

    public LiquidationResource(LiquidationRepository liquidationRepository, LiquidationSearchRepository liquidationSearchRepository) {
        this.liquidationRepository = liquidationRepository;
        this.liquidationSearchRepository = liquidationSearchRepository;
    }

    /**
     * {@code POST  /liquidations} : Create a new liquidation.
     *
     * @param liquidation the liquidation to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new liquidation, or with status {@code 400 (Bad Request)} if the liquidation has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/liquidations")
    public Mono<ResponseEntity<Liquidation>> createLiquidation(@Valid @RequestBody Liquidation liquidation) throws URISyntaxException {
        log.debug("REST request to save Liquidation : {}", liquidation);
        if (liquidation.getId() != null) {
            throw new BadRequestAlertException("A new liquidation cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return liquidationRepository
            .save(liquidation)
            .flatMap(liquidationSearchRepository::save)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/liquidations/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /liquidations/:id} : Updates an existing liquidation.
     *
     * @param id the id of the liquidation to save.
     * @param liquidation the liquidation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated liquidation,
     * or with status {@code 400 (Bad Request)} if the liquidation is not valid,
     * or with status {@code 500 (Internal Server Error)} if the liquidation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/liquidations/{id}")
    public Mono<ResponseEntity<Liquidation>> updateLiquidation(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Liquidation liquidation
    ) throws URISyntaxException {
        log.debug("REST request to update Liquidation : {}, {}", id, liquidation);
        if (liquidation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, liquidation.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return liquidationRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return liquidationRepository
                    .save(liquidation)
                    .flatMap(liquidationSearchRepository::save)
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
     * {@code PATCH  /liquidations/:id} : Partial updates given fields of an existing liquidation, field will ignore if it is null
     *
     * @param id the id of the liquidation to save.
     * @param liquidation the liquidation to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated liquidation,
     * or with status {@code 400 (Bad Request)} if the liquidation is not valid,
     * or with status {@code 404 (Not Found)} if the liquidation is not found,
     * or with status {@code 500 (Internal Server Error)} if the liquidation couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/liquidations/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<Liquidation>> partialUpdateLiquidation(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Liquidation liquidation
    ) throws URISyntaxException {
        log.debug("REST request to partial update Liquidation partially : {}, {}", id, liquidation);
        if (liquidation.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, liquidation.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return liquidationRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<Liquidation> result = liquidationRepository
                    .findById(liquidation.getId())
                    .map(existingLiquidation -> {
                        if (liquidation.getRecordId() != null) {
                            existingLiquidation.setRecordId(liquidation.getRecordId());
                        }
                        if (liquidation.getServiceLevel() != null) {
                            existingLiquidation.setServiceLevel(liquidation.getServiceLevel());
                        }
                        if (liquidation.getTimestamp() != null) {
                            existingLiquidation.setTimestamp(liquidation.getTimestamp());
                        }
                        if (liquidation.getPartnerCode() != null) {
                            existingLiquidation.setPartnerCode(liquidation.getPartnerCode());
                        }
                        if (liquidation.getAmount() != null) {
                            existingLiquidation.setAmount(liquidation.getAmount());
                        }
                        if (liquidation.getCurrency() != null) {
                            existingLiquidation.setCurrency(liquidation.getCurrency());
                        }
                        if (liquidation.getReceiverBankcode() != null) {
                            existingLiquidation.setReceiverBankcode(liquidation.getReceiverBankcode());
                        }
                        if (liquidation.getReceiverAccountNumber() != null) {
                            existingLiquidation.setReceiverAccountNumber(liquidation.getReceiverAccountNumber());
                        }
                        if (liquidation.getBeneficiaryName() != null) {
                            existingLiquidation.setBeneficiaryName(liquidation.getBeneficiaryName());
                        }
                        if (liquidation.getInstructionId() != null) {
                            existingLiquidation.setInstructionId(liquidation.getInstructionId());
                        }
                        if (liquidation.getSenderToReceiverInfo() != null) {
                            existingLiquidation.setSenderToReceiverInfo(liquidation.getSenderToReceiverInfo());
                        }
                        if (liquidation.getFreeText1() != null) {
                            existingLiquidation.setFreeText1(liquidation.getFreeText1());
                        }
                        if (liquidation.getFreeText2() != null) {
                            existingLiquidation.setFreeText2(liquidation.getFreeText2());
                        }
                        if (liquidation.getFreeText3() != null) {
                            existingLiquidation.setFreeText3(liquidation.getFreeText3());
                        }
                        if (liquidation.getFreeText4() != null) {
                            existingLiquidation.setFreeText4(liquidation.getFreeText4());
                        }
                        if (liquidation.getFreeText5() != null) {
                            existingLiquidation.setFreeText5(liquidation.getFreeText5());
                        }
                        if (liquidation.getFreeText6() != null) {
                            existingLiquidation.setFreeText6(liquidation.getFreeText6());
                        }
                        if (liquidation.getFreeText7() != null) {
                            existingLiquidation.setFreeText7(liquidation.getFreeText7());
                        }
                        if (liquidation.getFreeText8() != null) {
                            existingLiquidation.setFreeText8(liquidation.getFreeText8());
                        }
                        if (liquidation.getFreeText9() != null) {
                            existingLiquidation.setFreeText9(liquidation.getFreeText9());
                        }
                        if (liquidation.getFreeText10() != null) {
                            existingLiquidation.setFreeText10(liquidation.getFreeText10());
                        }
                        if (liquidation.getFreeText11() != null) {
                            existingLiquidation.setFreeText11(liquidation.getFreeText11());
                        }
                        if (liquidation.getFreeText12() != null) {
                            existingLiquidation.setFreeText12(liquidation.getFreeText12());
                        }
                        if (liquidation.getFreeText13() != null) {
                            existingLiquidation.setFreeText13(liquidation.getFreeText13());
                        }
                        if (liquidation.getFreeText14() != null) {
                            existingLiquidation.setFreeText14(liquidation.getFreeText14());
                        }
                        if (liquidation.getFreeText15() != null) {
                            existingLiquidation.setFreeText15(liquidation.getFreeText15());
                        }
                        if (liquidation.getFreeText16() != null) {
                            existingLiquidation.setFreeText16(liquidation.getFreeText16());
                        }
                        if (liquidation.getFreeText17() != null) {
                            existingLiquidation.setFreeText17(liquidation.getFreeText17());
                        }
                        if (liquidation.getFreeText18() != null) {
                            existingLiquidation.setFreeText18(liquidation.getFreeText18());
                        }
                        if (liquidation.getFreeText19() != null) {
                            existingLiquidation.setFreeText19(liquidation.getFreeText19());
                        }
                        if (liquidation.getFreeText20() != null) {
                            existingLiquidation.setFreeText20(liquidation.getFreeText20());
                        }
                        if (liquidation.getFreeText21() != null) {
                            existingLiquidation.setFreeText21(liquidation.getFreeText21());
                        }
                        if (liquidation.getFreeText22() != null) {
                            existingLiquidation.setFreeText22(liquidation.getFreeText22());
                        }
                        if (liquidation.getFreeText23() != null) {
                            existingLiquidation.setFreeText23(liquidation.getFreeText23());
                        }
                        if (liquidation.getFreeText24() != null) {
                            existingLiquidation.setFreeText24(liquidation.getFreeText24());
                        }
                        if (liquidation.getFreeText25() != null) {
                            existingLiquidation.setFreeText25(liquidation.getFreeText25());
                        }
                        if (liquidation.getFreeText26() != null) {
                            existingLiquidation.setFreeText26(liquidation.getFreeText26());
                        }
                        if (liquidation.getFreeText27() != null) {
                            existingLiquidation.setFreeText27(liquidation.getFreeText27());
                        }
                        if (liquidation.getFreeText28() != null) {
                            existingLiquidation.setFreeText28(liquidation.getFreeText28());
                        }
                        if (liquidation.getCreatedAt() != null) {
                            existingLiquidation.setCreatedAt(liquidation.getCreatedAt());
                        }
                        if (liquidation.getCreatedBy() != null) {
                            existingLiquidation.setCreatedBy(liquidation.getCreatedBy());
                        }
                        if (liquidation.getUpdatedAt() != null) {
                            existingLiquidation.setUpdatedAt(liquidation.getUpdatedAt());
                        }
                        if (liquidation.getUpdatedBy() != null) {
                            existingLiquidation.setUpdatedBy(liquidation.getUpdatedBy());
                        }

                        return existingLiquidation;
                    })
                    .flatMap(liquidationRepository::save)
                    .flatMap(savedLiquidation -> {
                        liquidationSearchRepository.save(savedLiquidation);

                        return Mono.just(savedLiquidation);
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
     * {@code GET  /liquidations} : get all the liquidations.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of liquidations in body.
     */
    @GetMapping("/liquidations")
    public Mono<List<Liquidation>> getAllLiquidations() {
        log.debug("REST request to get all Liquidations");
        return liquidationRepository.findAll().collectList();
    }

    /**
     * {@code GET  /liquidations} : get all the liquidations as a stream.
     * @return the {@link Flux} of liquidations.
     */
    @GetMapping(value = "/liquidations", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<Liquidation> getAllLiquidationsAsStream() {
        log.debug("REST request to get all Liquidations as a stream");
        return liquidationRepository.findAll();
    }

    /**
     * {@code GET  /liquidations/:id} : get the "id" liquidation.
     *
     * @param id the id of the liquidation to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the liquidation, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/liquidations/{id}")
    public Mono<ResponseEntity<Liquidation>> getLiquidation(@PathVariable Long id) {
        log.debug("REST request to get Liquidation : {}", id);
        Mono<Liquidation> liquidation = liquidationRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(liquidation);
    }

    /**
     * {@code DELETE  /liquidations/:id} : delete the "id" liquidation.
     *
     * @param id the id of the liquidation to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/liquidations/{id}")
    public Mono<ResponseEntity<Void>> deleteLiquidation(@PathVariable Long id) {
        log.debug("REST request to delete Liquidation : {}", id);
        return liquidationRepository
            .deleteById(id)
            .then(liquidationSearchRepository.deleteById(id))
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
     * {@code SEARCH  /_search/liquidations?query=:query} : search for the liquidation corresponding
     * to the query.
     *
     * @param query the query of the liquidation search.
     * @return the result of the search.
     */
    @GetMapping("/_search/liquidations")
    public Mono<List<Liquidation>> searchLiquidations(@RequestParam String query) {
        log.debug("REST request to search Liquidations for query {}", query);
        return liquidationSearchRepository.search(query).collectList();
    }
}
