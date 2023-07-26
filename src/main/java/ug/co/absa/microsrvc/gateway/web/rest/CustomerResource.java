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
import ug.co.absa.microsrvc.gateway.domain.Customer;
import ug.co.absa.microsrvc.gateway.repository.CustomerRepository;
import ug.co.absa.microsrvc.gateway.repository.search.CustomerSearchRepository;
import ug.co.absa.microsrvc.gateway.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link ug.co.absa.microsrvc.gateway.domain.Customer}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class CustomerResource {

    private final Logger log = LoggerFactory.getLogger(CustomerResource.class);

    private static final String ENTITY_NAME = "customer";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CustomerRepository customerRepository;

    private final CustomerSearchRepository customerSearchRepository;

    public CustomerResource(CustomerRepository customerRepository, CustomerSearchRepository customerSearchRepository) {
        this.customerRepository = customerRepository;
        this.customerSearchRepository = customerSearchRepository;
    }

    /**
     * {@code POST  /customers} : Create a new customer.
     *
     * @param customer the customer to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new customer, or with status {@code 400 (Bad Request)} if the customer has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/customers")
    public Mono<ResponseEntity<Customer>> createCustomer(@Valid @RequestBody Customer customer) throws URISyntaxException {
        log.debug("REST request to save Customer : {}", customer);
        if (customer.getId() != null) {
            throw new BadRequestAlertException("A new customer cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return customerRepository
            .save(customer)
            .flatMap(customerSearchRepository::save)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/customers/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /customers/:id} : Updates an existing customer.
     *
     * @param id the id of the customer to save.
     * @param customer the customer to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customer,
     * or with status {@code 400 (Bad Request)} if the customer is not valid,
     * or with status {@code 500 (Internal Server Error)} if the customer couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/customers/{id}")
    public Mono<ResponseEntity<Customer>> updateCustomer(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Customer customer
    ) throws URISyntaxException {
        log.debug("REST request to update Customer : {}, {}", id, customer);
        if (customer.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, customer.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return customerRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return customerRepository
                    .save(customer)
                    .flatMap(customerSearchRepository::save)
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
     * {@code PATCH  /customers/:id} : Partial updates given fields of an existing customer, field will ignore if it is null
     *
     * @param id the id of the customer to save.
     * @param customer the customer to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated customer,
     * or with status {@code 400 (Bad Request)} if the customer is not valid,
     * or with status {@code 404 (Not Found)} if the customer is not found,
     * or with status {@code 500 (Internal Server Error)} if the customer couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/customers/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<Customer>> partialUpdateCustomer(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Customer customer
    ) throws URISyntaxException {
        log.debug("REST request to partial update Customer partially : {}, {}", id, customer);
        if (customer.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, customer.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return customerRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<Customer> result = customerRepository
                    .findById(customer.getId())
                    .map(existingCustomer -> {
                        if (customer.getAbsaTranRef() != null) {
                            existingCustomer.setAbsaTranRef(customer.getAbsaTranRef());
                        }
                        if (customer.getBillerId() != null) {
                            existingCustomer.setBillerId(customer.getBillerId());
                        }
                        if (customer.getPaymentItemCode() != null) {
                            existingCustomer.setPaymentItemCode(customer.getPaymentItemCode());
                        }
                        if (customer.getDtDTransactionId() != null) {
                            existingCustomer.setDtDTransactionId(customer.getDtDTransactionId());
                        }
                        if (customer.getAmolDTransactionId() != null) {
                            existingCustomer.setAmolDTransactionId(customer.getAmolDTransactionId());
                        }
                        if (customer.getTransactionReferenceNumber() != null) {
                            existingCustomer.setTransactionReferenceNumber(customer.getTransactionReferenceNumber());
                        }
                        if (customer.getToken() != null) {
                            existingCustomer.setToken(customer.getToken());
                        }
                        if (customer.getTransferId() != null) {
                            existingCustomer.setTransferId(customer.getTransferId());
                        }
                        if (customer.getRequestId() != null) {
                            existingCustomer.setRequestId(customer.getRequestId());
                        }
                        if (customer.getAccountName() != null) {
                            existingCustomer.setAccountName(customer.getAccountName());
                        }
                        if (customer.getReturnCode() != null) {
                            existingCustomer.setReturnCode(customer.getReturnCode());
                        }
                        if (customer.getReturnMessage() != null) {
                            existingCustomer.setReturnMessage(customer.getReturnMessage());
                        }
                        if (customer.getExternalTXNid() != null) {
                            existingCustomer.setExternalTXNid(customer.getExternalTXNid());
                        }
                        if (customer.getTransactionDate() != null) {
                            existingCustomer.setTransactionDate(customer.getTransactionDate());
                        }
                        if (customer.getCustomerId() != null) {
                            existingCustomer.setCustomerId(customer.getCustomerId());
                        }
                        if (customer.getCustomerProduct() != null) {
                            existingCustomer.setCustomerProduct(customer.getCustomerProduct());
                        }
                        if (customer.getCustomerType() != null) {
                            existingCustomer.setCustomerType(customer.getCustomerType());
                        }
                        if (customer.getAccountCategory() != null) {
                            existingCustomer.setAccountCategory(customer.getAccountCategory());
                        }
                        if (customer.getAccountType() != null) {
                            existingCustomer.setAccountType(customer.getAccountType());
                        }
                        if (customer.getAccountNumber() != null) {
                            existingCustomer.setAccountNumber(customer.getAccountNumber());
                        }
                        if (customer.getPhoneNumber() != null) {
                            existingCustomer.setPhoneNumber(customer.getPhoneNumber());
                        }
                        if (customer.getChannel() != null) {
                            existingCustomer.setChannel(customer.getChannel());
                        }
                        if (customer.getTranFreeField1() != null) {
                            existingCustomer.setTranFreeField1(customer.getTranFreeField1());
                        }
                        if (customer.getTranFreeField2() != null) {
                            existingCustomer.setTranFreeField2(customer.getTranFreeField2());
                        }
                        if (customer.getTranFreeField3() != null) {
                            existingCustomer.setTranFreeField3(customer.getTranFreeField3());
                        }
                        if (customer.getTranFreeField4() != null) {
                            existingCustomer.setTranFreeField4(customer.getTranFreeField4());
                        }
                        if (customer.getTranFreeField5() != null) {
                            existingCustomer.setTranFreeField5(customer.getTranFreeField5());
                        }
                        if (customer.getTranFreeField6() != null) {
                            existingCustomer.setTranFreeField6(customer.getTranFreeField6());
                        }
                        if (customer.getTranFreeField7() != null) {
                            existingCustomer.setTranFreeField7(customer.getTranFreeField7());
                        }
                        if (customer.getTranFreeField8() != null) {
                            existingCustomer.setTranFreeField8(customer.getTranFreeField8());
                        }
                        if (customer.getTranFreeField9() != null) {
                            existingCustomer.setTranFreeField9(customer.getTranFreeField9());
                        }
                        if (customer.getTranFreeField10() != null) {
                            existingCustomer.setTranFreeField10(customer.getTranFreeField10());
                        }
                        if (customer.getTranFreeField11() != null) {
                            existingCustomer.setTranFreeField11(customer.getTranFreeField11());
                        }
                        if (customer.getTranFreeField12() != null) {
                            existingCustomer.setTranFreeField12(customer.getTranFreeField12());
                        }
                        if (customer.getTranFreeField13() != null) {
                            existingCustomer.setTranFreeField13(customer.getTranFreeField13());
                        }
                        if (customer.getTranFreeField14() != null) {
                            existingCustomer.setTranFreeField14(customer.getTranFreeField14());
                        }
                        if (customer.getTranFreeField15() != null) {
                            existingCustomer.setTranFreeField15(customer.getTranFreeField15());
                        }
                        if (customer.getTranFreeField16() != null) {
                            existingCustomer.setTranFreeField16(customer.getTranFreeField16());
                        }
                        if (customer.getTranFreeField17() != null) {
                            existingCustomer.setTranFreeField17(customer.getTranFreeField17());
                        }
                        if (customer.getTranFreeField18() != null) {
                            existingCustomer.setTranFreeField18(customer.getTranFreeField18());
                        }
                        if (customer.getTranFreeField19() != null) {
                            existingCustomer.setTranFreeField19(customer.getTranFreeField19());
                        }
                        if (customer.getTranFreeField20() != null) {
                            existingCustomer.setTranFreeField20(customer.getTranFreeField20());
                        }
                        if (customer.getTranFreeField21() != null) {
                            existingCustomer.setTranFreeField21(customer.getTranFreeField21());
                        }
                        if (customer.getTranFreeField22() != null) {
                            existingCustomer.setTranFreeField22(customer.getTranFreeField22());
                        }
                        if (customer.getTranFreeField23() != null) {
                            existingCustomer.setTranFreeField23(customer.getTranFreeField23());
                        }
                        if (customer.getTranFreeField23ContentType() != null) {
                            existingCustomer.setTranFreeField23ContentType(customer.getTranFreeField23ContentType());
                        }
                        if (customer.getTranFreeField24() != null) {
                            existingCustomer.setTranFreeField24(customer.getTranFreeField24());
                        }
                        if (customer.getTranFreeField25() != null) {
                            existingCustomer.setTranFreeField25(customer.getTranFreeField25());
                        }
                        if (customer.getTranFreeField26() != null) {
                            existingCustomer.setTranFreeField26(customer.getTranFreeField26());
                        }
                        if (customer.getTranFreeField27() != null) {
                            existingCustomer.setTranFreeField27(customer.getTranFreeField27());
                        }
                        if (customer.getTranFreeField28() != null) {
                            existingCustomer.setTranFreeField28(customer.getTranFreeField28());
                        }
                        if (customer.getTranFreeField29() != null) {
                            existingCustomer.setTranFreeField29(customer.getTranFreeField29());
                        }
                        if (customer.getTranFreeField30() != null) {
                            existingCustomer.setTranFreeField30(customer.getTranFreeField30());
                        }
                        if (customer.getTranFreeField31() != null) {
                            existingCustomer.setTranFreeField31(customer.getTranFreeField31());
                        }
                        if (customer.getTranFreeField32() != null) {
                            existingCustomer.setTranFreeField32(customer.getTranFreeField32());
                        }
                        if (customer.getTranFreeField33() != null) {
                            existingCustomer.setTranFreeField33(customer.getTranFreeField33());
                        }
                        if (customer.getCreatedAt() != null) {
                            existingCustomer.setCreatedAt(customer.getCreatedAt());
                        }
                        if (customer.getCreatedBy() != null) {
                            existingCustomer.setCreatedBy(customer.getCreatedBy());
                        }
                        if (customer.getUpdatedAt() != null) {
                            existingCustomer.setUpdatedAt(customer.getUpdatedAt());
                        }
                        if (customer.getUpdatedBy() != null) {
                            existingCustomer.setUpdatedBy(customer.getUpdatedBy());
                        }

                        return existingCustomer;
                    })
                    .flatMap(customerRepository::save)
                    .flatMap(savedCustomer -> {
                        customerSearchRepository.save(savedCustomer);

                        return Mono.just(savedCustomer);
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
     * {@code GET  /customers} : get all the customers.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of customers in body.
     */
    @GetMapping("/customers")
    public Mono<List<Customer>> getAllCustomers() {
        log.debug("REST request to get all Customers");
        return customerRepository.findAll().collectList();
    }

    /**
     * {@code GET  /customers} : get all the customers as a stream.
     * @return the {@link Flux} of customers.
     */
    @GetMapping(value = "/customers", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<Customer> getAllCustomersAsStream() {
        log.debug("REST request to get all Customers as a stream");
        return customerRepository.findAll();
    }

    /**
     * {@code GET  /customers/:id} : get the "id" customer.
     *
     * @param id the id of the customer to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the customer, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/customers/{id}")
    public Mono<ResponseEntity<Customer>> getCustomer(@PathVariable Long id) {
        log.debug("REST request to get Customer : {}", id);
        Mono<Customer> customer = customerRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(customer);
    }

    /**
     * {@code DELETE  /customers/:id} : delete the "id" customer.
     *
     * @param id the id of the customer to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/customers/{id}")
    public Mono<ResponseEntity<Void>> deleteCustomer(@PathVariable Long id) {
        log.debug("REST request to delete Customer : {}", id);
        return customerRepository
            .deleteById(id)
            .then(customerSearchRepository.deleteById(id))
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
     * {@code SEARCH  /_search/customers?query=:query} : search for the customer corresponding
     * to the query.
     *
     * @param query the query of the customer search.
     * @return the result of the search.
     */
    @GetMapping("/_search/customers")
    public Mono<List<Customer>> searchCustomers(@RequestParam String query) {
        log.debug("REST request to search Customers for query {}", query);
        return customerSearchRepository.search(query).collectList();
    }
}
