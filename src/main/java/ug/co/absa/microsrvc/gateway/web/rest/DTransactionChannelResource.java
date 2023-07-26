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
import ug.co.absa.microsrvc.gateway.domain.DTransactionChannel;
import ug.co.absa.microsrvc.gateway.repository.DTransactionChannelRepository;
import ug.co.absa.microsrvc.gateway.repository.search.DTransactionChannelSearchRepository;
import ug.co.absa.microsrvc.gateway.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link ug.co.absa.microsrvc.gateway.domain.DTransactionChannel}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class DTransactionChannelResource {

    private final Logger log = LoggerFactory.getLogger(DTransactionChannelResource.class);

    private static final String ENTITY_NAME = "dTransactionChannel";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DTransactionChannelRepository dTransactionChannelRepository;

    private final DTransactionChannelSearchRepository dTransactionChannelSearchRepository;

    public DTransactionChannelResource(
        DTransactionChannelRepository dTransactionChannelRepository,
        DTransactionChannelSearchRepository dTransactionChannelSearchRepository
    ) {
        this.dTransactionChannelRepository = dTransactionChannelRepository;
        this.dTransactionChannelSearchRepository = dTransactionChannelSearchRepository;
    }

    /**
     * {@code POST  /d-transaction-channels} : Create a new dTransactionChannel.
     *
     * @param dTransactionChannel the dTransactionChannel to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new dTransactionChannel, or with status {@code 400 (Bad Request)} if the dTransactionChannel has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/d-transaction-channels")
    public Mono<ResponseEntity<DTransactionChannel>> createDTransactionChannel(@Valid @RequestBody DTransactionChannel dTransactionChannel)
        throws URISyntaxException {
        log.debug("REST request to save DTransactionChannel : {}", dTransactionChannel);
        if (dTransactionChannel.getId() != null) {
            throw new BadRequestAlertException("A new dTransactionChannel cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return dTransactionChannelRepository
            .save(dTransactionChannel)
            .flatMap(dTransactionChannelSearchRepository::save)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/d-transaction-channels/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /d-transaction-channels/:id} : Updates an existing dTransactionChannel.
     *
     * @param id the id of the dTransactionChannel to save.
     * @param dTransactionChannel the dTransactionChannel to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dTransactionChannel,
     * or with status {@code 400 (Bad Request)} if the dTransactionChannel is not valid,
     * or with status {@code 500 (Internal Server Error)} if the dTransactionChannel couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/d-transaction-channels/{id}")
    public Mono<ResponseEntity<DTransactionChannel>> updateDTransactionChannel(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody DTransactionChannel dTransactionChannel
    ) throws URISyntaxException {
        log.debug("REST request to update DTransactionChannel : {}, {}", id, dTransactionChannel);
        if (dTransactionChannel.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dTransactionChannel.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return dTransactionChannelRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return dTransactionChannelRepository
                    .save(dTransactionChannel)
                    .flatMap(dTransactionChannelSearchRepository::save)
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
     * {@code PATCH  /d-transaction-channels/:id} : Partial updates given fields of an existing dTransactionChannel, field will ignore if it is null
     *
     * @param id the id of the dTransactionChannel to save.
     * @param dTransactionChannel the dTransactionChannel to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated dTransactionChannel,
     * or with status {@code 400 (Bad Request)} if the dTransactionChannel is not valid,
     * or with status {@code 404 (Not Found)} if the dTransactionChannel is not found,
     * or with status {@code 500 (Internal Server Error)} if the dTransactionChannel couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/d-transaction-channels/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<DTransactionChannel>> partialUpdateDTransactionChannel(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody DTransactionChannel dTransactionChannel
    ) throws URISyntaxException {
        log.debug("REST request to partial update DTransactionChannel partially : {}, {}", id, dTransactionChannel);
        if (dTransactionChannel.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, dTransactionChannel.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return dTransactionChannelRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<DTransactionChannel> result = dTransactionChannelRepository
                    .findById(dTransactionChannel.getId())
                    .map(existingDTransactionChannel -> {
                        if (dTransactionChannel.getAbsaTranRef() != null) {
                            existingDTransactionChannel.setAbsaTranRef(dTransactionChannel.getAbsaTranRef());
                        }
                        if (dTransactionChannel.getBillerId() != null) {
                            existingDTransactionChannel.setBillerId(dTransactionChannel.getBillerId());
                        }
                        if (dTransactionChannel.getPaymentItemCode() != null) {
                            existingDTransactionChannel.setPaymentItemCode(dTransactionChannel.getPaymentItemCode());
                        }
                        if (dTransactionChannel.getDtDTransactionId() != null) {
                            existingDTransactionChannel.setDtDTransactionId(dTransactionChannel.getDtDTransactionId());
                        }
                        if (dTransactionChannel.getTransactionReferenceNumber() != null) {
                            existingDTransactionChannel.setTransactionReferenceNumber(dTransactionChannel.getTransactionReferenceNumber());
                        }
                        if (dTransactionChannel.getExternalTranid() != null) {
                            existingDTransactionChannel.setExternalTranid(dTransactionChannel.getExternalTranid());
                        }
                        if (dTransactionChannel.getChannelCode() != null) {
                            existingDTransactionChannel.setChannelCode(dTransactionChannel.getChannelCode());
                        }
                        if (dTransactionChannel.getChannelName() != null) {
                            existingDTransactionChannel.setChannelName(dTransactionChannel.getChannelName());
                        }
                        if (dTransactionChannel.getChannelDescription() != null) {
                            existingDTransactionChannel.setChannelDescription(dTransactionChannel.getChannelDescription());
                        }
                        if (dTransactionChannel.getTimestamp() != null) {
                            existingDTransactionChannel.setTimestamp(dTransactionChannel.getTimestamp());
                        }
                        if (dTransactionChannel.getChanelFreeText() != null) {
                            existingDTransactionChannel.setChanelFreeText(dTransactionChannel.getChanelFreeText());
                        }
                        if (dTransactionChannel.getChanelFreeText1() != null) {
                            existingDTransactionChannel.setChanelFreeText1(dTransactionChannel.getChanelFreeText1());
                        }
                        if (dTransactionChannel.getChanelFreeText2() != null) {
                            existingDTransactionChannel.setChanelFreeText2(dTransactionChannel.getChanelFreeText2());
                        }
                        if (dTransactionChannel.getChanelFreeText3() != null) {
                            existingDTransactionChannel.setChanelFreeText3(dTransactionChannel.getChanelFreeText3());
                        }
                        if (dTransactionChannel.getChanelFreeText4() != null) {
                            existingDTransactionChannel.setChanelFreeText4(dTransactionChannel.getChanelFreeText4());
                        }
                        if (dTransactionChannel.getChanelFreeText5() != null) {
                            existingDTransactionChannel.setChanelFreeText5(dTransactionChannel.getChanelFreeText5());
                        }
                        if (dTransactionChannel.getChanelFreeText6() != null) {
                            existingDTransactionChannel.setChanelFreeText6(dTransactionChannel.getChanelFreeText6());
                        }
                        if (dTransactionChannel.getChanelFreeText7() != null) {
                            existingDTransactionChannel.setChanelFreeText7(dTransactionChannel.getChanelFreeText7());
                        }
                        if (dTransactionChannel.getChanelFreeText8() != null) {
                            existingDTransactionChannel.setChanelFreeText8(dTransactionChannel.getChanelFreeText8());
                        }
                        if (dTransactionChannel.getChanelFreeText9() != null) {
                            existingDTransactionChannel.setChanelFreeText9(dTransactionChannel.getChanelFreeText9());
                        }
                        if (dTransactionChannel.getChanelFreeText10() != null) {
                            existingDTransactionChannel.setChanelFreeText10(dTransactionChannel.getChanelFreeText10());
                        }
                        if (dTransactionChannel.getChanelFreeText11() != null) {
                            existingDTransactionChannel.setChanelFreeText11(dTransactionChannel.getChanelFreeText11());
                        }
                        if (dTransactionChannel.getChanelFreeText12() != null) {
                            existingDTransactionChannel.setChanelFreeText12(dTransactionChannel.getChanelFreeText12());
                        }
                        if (dTransactionChannel.getChanelFreeText13() != null) {
                            existingDTransactionChannel.setChanelFreeText13(dTransactionChannel.getChanelFreeText13());
                        }
                        if (dTransactionChannel.getChanelFreeText14() != null) {
                            existingDTransactionChannel.setChanelFreeText14(dTransactionChannel.getChanelFreeText14());
                        }
                        if (dTransactionChannel.getChanelFreeText15() != null) {
                            existingDTransactionChannel.setChanelFreeText15(dTransactionChannel.getChanelFreeText15());
                        }
                        if (dTransactionChannel.getChanelFreeText16() != null) {
                            existingDTransactionChannel.setChanelFreeText16(dTransactionChannel.getChanelFreeText16());
                        }
                        if (dTransactionChannel.getChanelFreeText17() != null) {
                            existingDTransactionChannel.setChanelFreeText17(dTransactionChannel.getChanelFreeText17());
                        }
                        if (dTransactionChannel.getChanelFreeText18() != null) {
                            existingDTransactionChannel.setChanelFreeText18(dTransactionChannel.getChanelFreeText18());
                        }
                        if (dTransactionChannel.getChanelFreeText19() != null) {
                            existingDTransactionChannel.setChanelFreeText19(dTransactionChannel.getChanelFreeText19());
                        }
                        if (dTransactionChannel.getChanelFreeText20() != null) {
                            existingDTransactionChannel.setChanelFreeText20(dTransactionChannel.getChanelFreeText20());
                        }
                        if (dTransactionChannel.getChanelFreeText21() != null) {
                            existingDTransactionChannel.setChanelFreeText21(dTransactionChannel.getChanelFreeText21());
                        }
                        if (dTransactionChannel.getChanelFreeText22() != null) {
                            existingDTransactionChannel.setChanelFreeText22(dTransactionChannel.getChanelFreeText22());
                        }
                        if (dTransactionChannel.getChanelFreeText23() != null) {
                            existingDTransactionChannel.setChanelFreeText23(dTransactionChannel.getChanelFreeText23());
                        }
                        if (dTransactionChannel.getChanelFreeText24() != null) {
                            existingDTransactionChannel.setChanelFreeText24(dTransactionChannel.getChanelFreeText24());
                        }
                        if (dTransactionChannel.getCreatedAt() != null) {
                            existingDTransactionChannel.setCreatedAt(dTransactionChannel.getCreatedAt());
                        }
                        if (dTransactionChannel.getCreatedBy() != null) {
                            existingDTransactionChannel.setCreatedBy(dTransactionChannel.getCreatedBy());
                        }
                        if (dTransactionChannel.getUpdatedAt() != null) {
                            existingDTransactionChannel.setUpdatedAt(dTransactionChannel.getUpdatedAt());
                        }
                        if (dTransactionChannel.getUpdatedBy() != null) {
                            existingDTransactionChannel.setUpdatedBy(dTransactionChannel.getUpdatedBy());
                        }
                        if (dTransactionChannel.getDelflg() != null) {
                            existingDTransactionChannel.setDelflg(dTransactionChannel.getDelflg());
                        }

                        return existingDTransactionChannel;
                    })
                    .flatMap(dTransactionChannelRepository::save)
                    .flatMap(savedDTransactionChannel -> {
                        dTransactionChannelSearchRepository.save(savedDTransactionChannel);

                        return Mono.just(savedDTransactionChannel);
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
     * {@code GET  /d-transaction-channels} : get all the dTransactionChannels.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of dTransactionChannels in body.
     */
    @GetMapping("/d-transaction-channels")
    public Mono<List<DTransactionChannel>> getAllDTransactionChannels() {
        log.debug("REST request to get all DTransactionChannels");
        return dTransactionChannelRepository.findAll().collectList();
    }

    /**
     * {@code GET  /d-transaction-channels} : get all the dTransactionChannels as a stream.
     * @return the {@link Flux} of dTransactionChannels.
     */
    @GetMapping(value = "/d-transaction-channels", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<DTransactionChannel> getAllDTransactionChannelsAsStream() {
        log.debug("REST request to get all DTransactionChannels as a stream");
        return dTransactionChannelRepository.findAll();
    }

    /**
     * {@code GET  /d-transaction-channels/:id} : get the "id" dTransactionChannel.
     *
     * @param id the id of the dTransactionChannel to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the dTransactionChannel, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/d-transaction-channels/{id}")
    public Mono<ResponseEntity<DTransactionChannel>> getDTransactionChannel(@PathVariable Long id) {
        log.debug("REST request to get DTransactionChannel : {}", id);
        Mono<DTransactionChannel> dTransactionChannel = dTransactionChannelRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(dTransactionChannel);
    }

    /**
     * {@code DELETE  /d-transaction-channels/:id} : delete the "id" dTransactionChannel.
     *
     * @param id the id of the dTransactionChannel to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/d-transaction-channels/{id}")
    public Mono<ResponseEntity<Void>> deleteDTransactionChannel(@PathVariable Long id) {
        log.debug("REST request to delete DTransactionChannel : {}", id);
        return dTransactionChannelRepository
            .deleteById(id)
            .then(dTransactionChannelSearchRepository.deleteById(id))
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
     * {@code SEARCH  /_search/d-transaction-channels?query=:query} : search for the dTransactionChannel corresponding
     * to the query.
     *
     * @param query the query of the dTransactionChannel search.
     * @return the result of the search.
     */
    @GetMapping("/_search/d-transaction-channels")
    public Mono<List<DTransactionChannel>> searchDTransactionChannels(@RequestParam String query) {
        log.debug("REST request to search DTransactionChannels for query {}", query);
        return dTransactionChannelSearchRepository.search(query).collectList();
    }
}
