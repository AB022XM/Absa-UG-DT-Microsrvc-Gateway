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
import ug.co.absa.microsrvc.gateway.domain.ProductCategory;
import ug.co.absa.microsrvc.gateway.repository.ProductCategoryRepository;
import ug.co.absa.microsrvc.gateway.repository.search.ProductCategorySearchRepository;
import ug.co.absa.microsrvc.gateway.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link ug.co.absa.microsrvc.gateway.domain.ProductCategory}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class ProductCategoryResource {

    private final Logger log = LoggerFactory.getLogger(ProductCategoryResource.class);

    private static final String ENTITY_NAME = "productCategory";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final ProductCategoryRepository productCategoryRepository;

    private final ProductCategorySearchRepository productCategorySearchRepository;

    public ProductCategoryResource(
        ProductCategoryRepository productCategoryRepository,
        ProductCategorySearchRepository productCategorySearchRepository
    ) {
        this.productCategoryRepository = productCategoryRepository;
        this.productCategorySearchRepository = productCategorySearchRepository;
    }

    /**
     * {@code POST  /product-categories} : Create a new productCategory.
     *
     * @param productCategory the productCategory to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new productCategory, or with status {@code 400 (Bad Request)} if the productCategory has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/product-categories")
    public Mono<ResponseEntity<ProductCategory>> createProductCategory(@Valid @RequestBody ProductCategory productCategory)
        throws URISyntaxException {
        log.debug("REST request to save ProductCategory : {}", productCategory);
        if (productCategory.getId() != null) {
            throw new BadRequestAlertException("A new productCategory cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return productCategoryRepository
            .save(productCategory)
            .flatMap(productCategorySearchRepository::save)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/product-categories/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /product-categories/:id} : Updates an existing productCategory.
     *
     * @param id the id of the productCategory to save.
     * @param productCategory the productCategory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productCategory,
     * or with status {@code 400 (Bad Request)} if the productCategory is not valid,
     * or with status {@code 500 (Internal Server Error)} if the productCategory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/product-categories/{id}")
    public Mono<ResponseEntity<ProductCategory>> updateProductCategory(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody ProductCategory productCategory
    ) throws URISyntaxException {
        log.debug("REST request to update ProductCategory : {}, {}", id, productCategory);
        if (productCategory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, productCategory.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return productCategoryRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return productCategoryRepository
                    .save(productCategory)
                    .flatMap(productCategorySearchRepository::save)
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
     * {@code PATCH  /product-categories/:id} : Partial updates given fields of an existing productCategory, field will ignore if it is null
     *
     * @param id the id of the productCategory to save.
     * @param productCategory the productCategory to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated productCategory,
     * or with status {@code 400 (Bad Request)} if the productCategory is not valid,
     * or with status {@code 404 (Not Found)} if the productCategory is not found,
     * or with status {@code 500 (Internal Server Error)} if the productCategory couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/product-categories/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<ProductCategory>> partialUpdateProductCategory(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody ProductCategory productCategory
    ) throws URISyntaxException {
        log.debug("REST request to partial update ProductCategory partially : {}, {}", id, productCategory);
        if (productCategory.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, productCategory.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return productCategoryRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<ProductCategory> result = productCategoryRepository
                    .findById(productCategory.getId())
                    .map(existingProductCategory -> {
                        if (productCategory.getAbsaTranRef() != null) {
                            existingProductCategory.setAbsaTranRef(productCategory.getAbsaTranRef());
                        }
                        if (productCategory.getBillerId() != null) {
                            existingProductCategory.setBillerId(productCategory.getBillerId());
                        }
                        if (productCategory.getRecordId() != null) {
                            existingProductCategory.setRecordId(productCategory.getRecordId());
                        }
                        if (productCategory.getProductCategoryCode() != null) {
                            existingProductCategory.setProductCategoryCode(productCategory.getProductCategoryCode());
                        }
                        if (productCategory.getProductCategoryName() != null) {
                            existingProductCategory.setProductCategoryName(productCategory.getProductCategoryName());
                        }
                        if (productCategory.getProductCategoryDescription() != null) {
                            existingProductCategory.setProductCategoryDescription(productCategory.getProductCategoryDescription());
                        }
                        if (productCategory.getProductCategoryImage() != null) {
                            existingProductCategory.setProductCategoryImage(productCategory.getProductCategoryImage());
                        }
                        if (productCategory.getProductFreeField1() != null) {
                            existingProductCategory.setProductFreeField1(productCategory.getProductFreeField1());
                        }
                        if (productCategory.getProductFreeField2() != null) {
                            existingProductCategory.setProductFreeField2(productCategory.getProductFreeField2());
                        }
                        if (productCategory.getProductFreeField3() != null) {
                            existingProductCategory.setProductFreeField3(productCategory.getProductFreeField3());
                        }
                        if (productCategory.getProductFreeField4() != null) {
                            existingProductCategory.setProductFreeField4(productCategory.getProductFreeField4());
                        }
                        if (productCategory.getProductFreeField5() != null) {
                            existingProductCategory.setProductFreeField5(productCategory.getProductFreeField5());
                        }
                        if (productCategory.getProductFreeField6() != null) {
                            existingProductCategory.setProductFreeField6(productCategory.getProductFreeField6());
                        }
                        if (productCategory.getProductFreeField7() != null) {
                            existingProductCategory.setProductFreeField7(productCategory.getProductFreeField7());
                        }
                        if (productCategory.getProductFreeField8() != null) {
                            existingProductCategory.setProductFreeField8(productCategory.getProductFreeField8());
                        }
                        if (productCategory.getProductFreeField9() != null) {
                            existingProductCategory.setProductFreeField9(productCategory.getProductFreeField9());
                        }
                        if (productCategory.getProductFreeField10() != null) {
                            existingProductCategory.setProductFreeField10(productCategory.getProductFreeField10());
                        }
                        if (productCategory.getProductFreeField11() != null) {
                            existingProductCategory.setProductFreeField11(productCategory.getProductFreeField11());
                        }
                        if (productCategory.getDelflg() != null) {
                            existingProductCategory.setDelflg(productCategory.getDelflg());
                        }
                        if (productCategory.getCreatedAt() != null) {
                            existingProductCategory.setCreatedAt(productCategory.getCreatedAt());
                        }
                        if (productCategory.getCreatedBy() != null) {
                            existingProductCategory.setCreatedBy(productCategory.getCreatedBy());
                        }
                        if (productCategory.getUpdatedAt() != null) {
                            existingProductCategory.setUpdatedAt(productCategory.getUpdatedAt());
                        }
                        if (productCategory.getUpdatedBy() != null) {
                            existingProductCategory.setUpdatedBy(productCategory.getUpdatedBy());
                        }

                        return existingProductCategory;
                    })
                    .flatMap(productCategoryRepository::save)
                    .flatMap(savedProductCategory -> {
                        productCategorySearchRepository.save(savedProductCategory);

                        return Mono.just(savedProductCategory);
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
     * {@code GET  /product-categories} : get all the productCategories.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of productCategories in body.
     */
    @GetMapping("/product-categories")
    public Mono<List<ProductCategory>> getAllProductCategories() {
        log.debug("REST request to get all ProductCategories");
        return productCategoryRepository.findAll().collectList();
    }

    /**
     * {@code GET  /product-categories} : get all the productCategories as a stream.
     * @return the {@link Flux} of productCategories.
     */
    @GetMapping(value = "/product-categories", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<ProductCategory> getAllProductCategoriesAsStream() {
        log.debug("REST request to get all ProductCategories as a stream");
        return productCategoryRepository.findAll();
    }

    /**
     * {@code GET  /product-categories/:id} : get the "id" productCategory.
     *
     * @param id the id of the productCategory to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the productCategory, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/product-categories/{id}")
    public Mono<ResponseEntity<ProductCategory>> getProductCategory(@PathVariable Long id) {
        log.debug("REST request to get ProductCategory : {}", id);
        Mono<ProductCategory> productCategory = productCategoryRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(productCategory);
    }

    /**
     * {@code DELETE  /product-categories/:id} : delete the "id" productCategory.
     *
     * @param id the id of the productCategory to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/product-categories/{id}")
    public Mono<ResponseEntity<Void>> deleteProductCategory(@PathVariable Long id) {
        log.debug("REST request to delete ProductCategory : {}", id);
        return productCategoryRepository
            .deleteById(id)
            .then(productCategorySearchRepository.deleteById(id))
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
     * {@code SEARCH  /_search/product-categories?query=:query} : search for the productCategory corresponding
     * to the query.
     *
     * @param query the query of the productCategory search.
     * @return the result of the search.
     */
    @GetMapping("/_search/product-categories")
    public Mono<List<ProductCategory>> searchProductCategories(@RequestParam String query) {
        log.debug("REST request to search ProductCategories for query {}", query);
        return productCategorySearchRepository.search(query).collectList();
    }
}
