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
import ug.co.absa.microsrvc.gateway.domain.Currency;
import ug.co.absa.microsrvc.gateway.repository.CurrencyRepository;
import ug.co.absa.microsrvc.gateway.repository.search.CurrencySearchRepository;
import ug.co.absa.microsrvc.gateway.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link ug.co.absa.microsrvc.gateway.domain.Currency}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CurrencyResource {

    private final Logger log = LoggerFactory.getLogger(CurrencyResource.class);

    private static final String ENTITY_NAME = "currency";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CurrencyRepository currencyRepository;

    private final CurrencySearchRepository currencySearchRepository;

    public CurrencyResource(CurrencyRepository currencyRepository, CurrencySearchRepository currencySearchRepository) {
        this.currencyRepository = currencyRepository;
        this.currencySearchRepository = currencySearchRepository;
    }

    /**
     * {@code POST  /currencies} : Create a new currency.
     *
     * @param currency the currency to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new currency, or with status {@code 400 (Bad Request)} if the currency has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/currencies")
    public Mono<ResponseEntity<Currency>> createCurrency(@Valid @RequestBody Currency currency) throws URISyntaxException {
        log.debug("REST request to save Currency : {}", currency);
        if (currency.getId() != null) {
            throw new BadRequestAlertException("A new currency cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return currencyRepository
            .save(currency)
            .flatMap(currencySearchRepository::save)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/currencies/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /currencies/:id} : Updates an existing currency.
     *
     * @param id the id of the currency to save.
     * @param currency the currency to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated currency,
     * or with status {@code 400 (Bad Request)} if the currency is not valid,
     * or with status {@code 500 (Internal Server Error)} if the currency couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/currencies/{id}")
    public Mono<ResponseEntity<Currency>> updateCurrency(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Currency currency
    ) throws URISyntaxException {
        log.debug("REST request to update Currency : {}, {}", id, currency);
        if (currency.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, currency.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return currencyRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return currencyRepository
                    .save(currency)
                    .flatMap(currencySearchRepository::save)
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
     * {@code PATCH  /currencies/:id} : Partial updates given fields of an existing currency, field will ignore if it is null
     *
     * @param id the id of the currency to save.
     * @param currency the currency to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated currency,
     * or with status {@code 400 (Bad Request)} if the currency is not valid,
     * or with status {@code 404 (Not Found)} if the currency is not found,
     * or with status {@code 500 (Internal Server Error)} if the currency couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/currencies/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<Currency>> partialUpdateCurrency(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Currency currency
    ) throws URISyntaxException {
        log.debug("REST request to partial update Currency partially : {}, {}", id, currency);
        if (currency.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, currency.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return currencyRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<Currency> result = currencyRepository
                    .findById(currency.getId())
                    .map(existingCurrency -> {
                        if (currency.getAbsaTranRef() != null) {
                            existingCurrency.setAbsaTranRef(currency.getAbsaTranRef());
                        }
                        if (currency.getDtDTransactionId() != null) {
                            existingCurrency.setDtDTransactionId(currency.getDtDTransactionId());
                        }
                        if (currency.getAmolDTransactionId() != null) {
                            existingCurrency.setAmolDTransactionId(currency.getAmolDTransactionId());
                        }
                        if (currency.getTransactionReferenceNumber() != null) {
                            existingCurrency.setTransactionReferenceNumber(currency.getTransactionReferenceNumber());
                        }
                        if (currency.getToken() != null) {
                            existingCurrency.setToken(currency.getToken());
                        }
                        if (currency.getCurrencyUniqueId() != null) {
                            existingCurrency.setCurrencyUniqueId(currency.getCurrencyUniqueId());
                        }
                        if (currency.getCurrencyCode() != null) {
                            existingCurrency.setCurrencyCode(currency.getCurrencyCode());
                        }
                        if (currency.getCurrencyName() != null) {
                            existingCurrency.setCurrencyName(currency.getCurrencyName());
                        }
                        if (currency.getFreeField1() != null) {
                            existingCurrency.setFreeField1(currency.getFreeField1());
                        }
                        if (currency.getFreeField2() != null) {
                            existingCurrency.setFreeField2(currency.getFreeField2());
                        }
                        if (currency.getFreeField3() != null) {
                            existingCurrency.setFreeField3(currency.getFreeField3());
                        }
                        if (currency.getFreeField4() != null) {
                            existingCurrency.setFreeField4(currency.getFreeField4());
                        }
                        if (currency.getFreeField5() != null) {
                            existingCurrency.setFreeField5(currency.getFreeField5());
                        }
                        if (currency.getFreeField6() != null) {
                            existingCurrency.setFreeField6(currency.getFreeField6());
                        }
                        if (currency.getTimestamp() != null) {
                            existingCurrency.setTimestamp(currency.getTimestamp());
                        }
                        if (currency.getRecordStatus() != null) {
                            existingCurrency.setRecordStatus(currency.getRecordStatus());
                        }
                        if (currency.getCreatedAt() != null) {
                            existingCurrency.setCreatedAt(currency.getCreatedAt());
                        }
                        if (currency.getCreatedBy() != null) {
                            existingCurrency.setCreatedBy(currency.getCreatedBy());
                        }
                        if (currency.getUpdatedAt() != null) {
                            existingCurrency.setUpdatedAt(currency.getUpdatedAt());
                        }
                        if (currency.getUpdatedBy() != null) {
                            existingCurrency.setUpdatedBy(currency.getUpdatedBy());
                        }

                        return existingCurrency;
                    })
                    .flatMap(currencyRepository::save)
                    .flatMap(savedCurrency -> {
                        currencySearchRepository.save(savedCurrency);

                        return Mono.just(savedCurrency);
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
     * {@code GET  /currencies} : get all the currencies.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of currencies in body.
     */
    @GetMapping("/currencies")
    public Mono<List<Currency>> getAllCurrencies() {
        log.debug("REST request to get all Currencies");
        return currencyRepository.findAll().collectList();
    }

    /**
     * {@code GET  /currencies} : get all the currencies as a stream.
     * @return the {@link Flux} of currencies.
     */
    @GetMapping(value = "/currencies", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<Currency> getAllCurrenciesAsStream() {
        log.debug("REST request to get all Currencies as a stream");
        return currencyRepository.findAll();
    }

    /**
     * {@code GET  /currencies/:id} : get the "id" currency.
     *
     * @param id the id of the currency to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the currency, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/currencies/{id}")
    public Mono<ResponseEntity<Currency>> getCurrency(@PathVariable Long id) {
        log.debug("REST request to get Currency : {}", id);
        Mono<Currency> currency = currencyRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(currency);
    }

    /**
     * {@code DELETE  /currencies/:id} : delete the "id" currency.
     *
     * @param id the id of the currency to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/currencies/{id}")
    public Mono<ResponseEntity<Void>> deleteCurrency(@PathVariable Long id) {
        log.debug("REST request to delete Currency : {}", id);
        return currencyRepository
            .deleteById(id)
            .then(currencySearchRepository.deleteById(id))
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
     * {@code SEARCH  /_search/currencies?query=:query} : search for the currency corresponding
     * to the query.
     *
     * @param query the query of the currency search.
     * @return the result of the search.
     */
    @GetMapping("/_search/currencies")
    public Mono<List<Currency>> searchCurrencies(@RequestParam String query) {
        log.debug("REST request to search Currencies for query {}", query);
        return currencySearchRepository.search(query).collectList();
    }
}
