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
import ug.co.absa.microsrvc.gateway.domain.Biller;
import ug.co.absa.microsrvc.gateway.repository.BillerRepository;
import ug.co.absa.microsrvc.gateway.repository.search.BillerSearchRepository;
import ug.co.absa.microsrvc.gateway.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link ug.co.absa.microsrvc.gateway.domain.Biller}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class BillerResource {

    private final Logger log = LoggerFactory.getLogger(BillerResource.class);

    private static final String ENTITY_NAME = "biller";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BillerRepository billerRepository;

    private final BillerSearchRepository billerSearchRepository;

    public BillerResource(BillerRepository billerRepository, BillerSearchRepository billerSearchRepository) {
        this.billerRepository = billerRepository;
        this.billerSearchRepository = billerSearchRepository;
    }

    /**
     * {@code POST  /billers} : Create a new biller.
     *
     * @param biller the biller to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new biller, or with status {@code 400 (Bad Request)} if the biller has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/billers")
    public Mono<ResponseEntity<Biller>> createBiller(@Valid @RequestBody Biller biller) throws URISyntaxException {
        log.debug("REST request to save Biller : {}", biller);
        if (biller.getId() != null) {
            throw new BadRequestAlertException("A new biller cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return billerRepository
            .save(biller)
            .flatMap(billerSearchRepository::save)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/billers/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /billers/:id} : Updates an existing biller.
     *
     * @param id the id of the biller to save.
     * @param biller the biller to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated biller,
     * or with status {@code 400 (Bad Request)} if the biller is not valid,
     * or with status {@code 500 (Internal Server Error)} if the biller couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/billers/{id}")
    public Mono<ResponseEntity<Biller>> updateBiller(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Biller biller
    ) throws URISyntaxException {
        log.debug("REST request to update Biller : {}, {}", id, biller);
        if (biller.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, biller.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return billerRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return billerRepository
                    .save(biller)
                    .flatMap(billerSearchRepository::save)
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
     * {@code PATCH  /billers/:id} : Partial updates given fields of an existing biller, field will ignore if it is null
     *
     * @param id the id of the biller to save.
     * @param biller the biller to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated biller,
     * or with status {@code 400 (Bad Request)} if the biller is not valid,
     * or with status {@code 404 (Not Found)} if the biller is not found,
     * or with status {@code 500 (Internal Server Error)} if the biller couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/billers/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<Biller>> partialUpdateBiller(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Biller biller
    ) throws URISyntaxException {
        log.debug("REST request to partial update Biller partially : {}, {}", id, biller);
        if (biller.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, biller.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return billerRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<Biller> result = billerRepository
                    .findById(biller.getId())
                    .map(existingBiller -> {
                        if (biller.getAbsaTranRef() != null) {
                            existingBiller.setAbsaTranRef(biller.getAbsaTranRef());
                        }
                        if (biller.getBillerId() != null) {
                            existingBiller.setBillerId(biller.getBillerId());
                        }
                        if (biller.getBillerCode() != null) {
                            existingBiller.setBillerCode(biller.getBillerCode());
                        }
                        if (biller.getBillerName() != null) {
                            existingBiller.setBillerName(biller.getBillerName());
                        }
                        if (biller.getBillerCategoryId() != null) {
                            existingBiller.setBillerCategoryId(biller.getBillerCategoryId());
                        }
                        if (biller.getAddressId() != null) {
                            existingBiller.setAddressId(biller.getAddressId());
                        }
                        if (biller.getNarative() != null) {
                            existingBiller.setNarative(biller.getNarative());
                        }
                        if (biller.getNarative1() != null) {
                            existingBiller.setNarative1(biller.getNarative1());
                        }
                        if (biller.getNarative2() != null) {
                            existingBiller.setNarative2(biller.getNarative2());
                        }
                        if (biller.getNarative3() != null) {
                            existingBiller.setNarative3(biller.getNarative3());
                        }
                        if (biller.getNarative4() != null) {
                            existingBiller.setNarative4(biller.getNarative4());
                        }
                        if (biller.getNarative5() != null) {
                            existingBiller.setNarative5(biller.getNarative5());
                        }
                        if (biller.getNarative6() != null) {
                            existingBiller.setNarative6(biller.getNarative6());
                        }
                        if (biller.getNarative7() != null) {
                            existingBiller.setNarative7(biller.getNarative7());
                        }
                        if (biller.getTimestamp() != null) {
                            existingBiller.setTimestamp(biller.getTimestamp());
                        }
                        if (biller.getFreeField1() != null) {
                            existingBiller.setFreeField1(biller.getFreeField1());
                        }
                        if (biller.getFreeField2() != null) {
                            existingBiller.setFreeField2(biller.getFreeField2());
                        }
                        if (biller.getFreeField3() != null) {
                            existingBiller.setFreeField3(biller.getFreeField3());
                        }
                        if (biller.getFreeField4() != null) {
                            existingBiller.setFreeField4(biller.getFreeField4());
                        }
                        if (biller.getFreeField5() != null) {
                            existingBiller.setFreeField5(biller.getFreeField5());
                        }
                        if (biller.getFreeField6() != null) {
                            existingBiller.setFreeField6(biller.getFreeField6());
                        }
                        if (biller.getFreeField7() != null) {
                            existingBiller.setFreeField7(biller.getFreeField7());
                        }
                        if (biller.getFreeField8() != null) {
                            existingBiller.setFreeField8(biller.getFreeField8());
                        }
                        if (biller.getFreeField9() != null) {
                            existingBiller.setFreeField9(biller.getFreeField9());
                        }
                        if (biller.getFreeField10() != null) {
                            existingBiller.setFreeField10(biller.getFreeField10());
                        }
                        if (biller.getFreeField11() != null) {
                            existingBiller.setFreeField11(biller.getFreeField11());
                        }
                        if (biller.getFreeField12() != null) {
                            existingBiller.setFreeField12(biller.getFreeField12());
                        }
                        if (biller.getFreeField13() != null) {
                            existingBiller.setFreeField13(biller.getFreeField13());
                        }
                        if (biller.getDelflg() != null) {
                            existingBiller.setDelflg(biller.getDelflg());
                        }
                        if (biller.getCreatedAt() != null) {
                            existingBiller.setCreatedAt(biller.getCreatedAt());
                        }
                        if (biller.getCreatedBy() != null) {
                            existingBiller.setCreatedBy(biller.getCreatedBy());
                        }
                        if (biller.getUpdatedAt() != null) {
                            existingBiller.setUpdatedAt(biller.getUpdatedAt());
                        }
                        if (biller.getUpdatedBy() != null) {
                            existingBiller.setUpdatedBy(biller.getUpdatedBy());
                        }

                        return existingBiller;
                    })
                    .flatMap(billerRepository::save)
                    .flatMap(savedBiller -> {
                        billerSearchRepository.save(savedBiller);

                        return Mono.just(savedBiller);
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
     * {@code GET  /billers} : get all the billers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of billers in body.
     */
    @GetMapping("/billers")
    public Mono<List<Biller>> getAllBillers() {
        log.debug("REST request to get all Billers");
        return billerRepository.findAll().collectList();
    }

    /**
     * {@code GET  /billers} : get all the billers as a stream.
     * @return the {@link Flux} of billers.
     */
    @GetMapping(value = "/billers", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<Biller> getAllBillersAsStream() {
        log.debug("REST request to get all Billers as a stream");
        return billerRepository.findAll();
    }

    /**
     * {@code GET  /billers/:id} : get the "id" biller.
     *
     * @param id the id of the biller to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the biller, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/billers/{id}")
    public Mono<ResponseEntity<Biller>> getBiller(@PathVariable Long id) {
        log.debug("REST request to get Biller : {}", id);
        Mono<Biller> biller = billerRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(biller);
    }

    /**
     * {@code DELETE  /billers/:id} : delete the "id" biller.
     *
     * @param id the id of the biller to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/billers/{id}")
    public Mono<ResponseEntity<Void>> deleteBiller(@PathVariable Long id) {
        log.debug("REST request to delete Biller : {}", id);
        return billerRepository
            .deleteById(id)
            .then(billerSearchRepository.deleteById(id))
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
     * {@code SEARCH  /_search/billers?query=:query} : search for the biller corresponding
     * to the query.
     *
     * @param query the query of the biller search.
     * @return the result of the search.
     */
    @GetMapping("/_search/billers")
    public Mono<List<Biller>> searchBillers(@RequestParam String query) {
        log.debug("REST request to search Billers for query {}", query);
        return billerSearchRepository.search(query).collectList();
    }
}
