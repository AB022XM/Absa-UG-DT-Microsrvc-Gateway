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
import ug.co.absa.microsrvc.gateway.domain.Branch;
import ug.co.absa.microsrvc.gateway.repository.BranchRepository;
import ug.co.absa.microsrvc.gateway.repository.search.BranchSearchRepository;
import ug.co.absa.microsrvc.gateway.web.rest.errors.BadRequestAlertException;

/**
 * REST controller for managing {@link ug.co.absa.microsrvc.gateway.domain.Branch}.
 */
@RestController
@RequestMapping("/api")
@Transactional
public class BranchResource {

    private final Logger log = LoggerFactory.getLogger(BranchResource.class);

    private static final String ENTITY_NAME = "branch";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BranchRepository branchRepository;

    private final BranchSearchRepository branchSearchRepository;

    public BranchResource(BranchRepository branchRepository, BranchSearchRepository branchSearchRepository) {
        this.branchRepository = branchRepository;
        this.branchSearchRepository = branchSearchRepository;
    }

    /**
     * {@code POST  /branches} : Create a new branch.
     *
     * @param branch the branch to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new branch, or with status {@code 400 (Bad Request)} if the branch has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/branches")
    public Mono<ResponseEntity<Branch>> createBranch(@Valid @RequestBody Branch branch) throws URISyntaxException {
        log.debug("REST request to save Branch : {}", branch);
        if (branch.getId() != null) {
            throw new BadRequestAlertException("A new branch cannot already have an ID", ENTITY_NAME, "idexists");
        }
        return branchRepository
            .save(branch)
            .flatMap(branchSearchRepository::save)
            .map(result -> {
                try {
                    return ResponseEntity
                        .created(new URI("/api/branches/" + result.getId()))
                        .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
                        .body(result);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                }
            });
    }

    /**
     * {@code PUT  /branches/:id} : Updates an existing branch.
     *
     * @param id the id of the branch to save.
     * @param branch the branch to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated branch,
     * or with status {@code 400 (Bad Request)} if the branch is not valid,
     * or with status {@code 500 (Internal Server Error)} if the branch couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/branches/{id}")
    public Mono<ResponseEntity<Branch>> updateBranch(
        @PathVariable(value = "id", required = false) final Long id,
        @Valid @RequestBody Branch branch
    ) throws URISyntaxException {
        log.debug("REST request to update Branch : {}, {}", id, branch);
        if (branch.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, branch.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return branchRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                return branchRepository
                    .save(branch)
                    .flatMap(branchSearchRepository::save)
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
     * {@code PATCH  /branches/:id} : Partial updates given fields of an existing branch, field will ignore if it is null
     *
     * @param id the id of the branch to save.
     * @param branch the branch to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated branch,
     * or with status {@code 400 (Bad Request)} if the branch is not valid,
     * or with status {@code 404 (Not Found)} if the branch is not found,
     * or with status {@code 500 (Internal Server Error)} if the branch couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PatchMapping(value = "/branches/{id}", consumes = { "application/json", "application/merge-patch+json" })
    public Mono<ResponseEntity<Branch>> partialUpdateBranch(
        @PathVariable(value = "id", required = false) final Long id,
        @NotNull @RequestBody Branch branch
    ) throws URISyntaxException {
        log.debug("REST request to partial update Branch partially : {}, {}", id, branch);
        if (branch.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        if (!Objects.equals(id, branch.getId())) {
            throw new BadRequestAlertException("Invalid ID", ENTITY_NAME, "idinvalid");
        }

        return branchRepository
            .existsById(id)
            .flatMap(exists -> {
                if (!exists) {
                    return Mono.error(new BadRequestAlertException("Entity not found", ENTITY_NAME, "idnotfound"));
                }

                Mono<Branch> result = branchRepository
                    .findById(branch.getId())
                    .map(existingBranch -> {
                        if (branch.getAbsaTranRef() != null) {
                            existingBranch.setAbsaTranRef(branch.getAbsaTranRef());
                        }
                        if (branch.getRecordId() != null) {
                            existingBranch.setRecordId(branch.getRecordId());
                        }
                        if (branch.getAddressId() != null) {
                            existingBranch.setAddressId(branch.getAddressId());
                        }
                        if (branch.getBankId() != null) {
                            existingBranch.setBankId(branch.getBankId());
                        }
                        if (branch.getBranchId() != null) {
                            existingBranch.setBranchId(branch.getBranchId());
                        }
                        if (branch.getBranchName() != null) {
                            existingBranch.setBranchName(branch.getBranchName());
                        }
                        if (branch.getBranchSwiftCode() != null) {
                            existingBranch.setBranchSwiftCode(branch.getBranchSwiftCode());
                        }
                        if (branch.getBranchPhoneNumber() != null) {
                            existingBranch.setBranchPhoneNumber(branch.getBranchPhoneNumber());
                        }
                        if (branch.getBranchEmail() != null) {
                            existingBranch.setBranchEmail(branch.getBranchEmail());
                        }
                        if (branch.getBranchFreeField1() != null) {
                            existingBranch.setBranchFreeField1(branch.getBranchFreeField1());
                        }
                        if (branch.getBranchFreeField3() != null) {
                            existingBranch.setBranchFreeField3(branch.getBranchFreeField3());
                        }
                        if (branch.getBranchFreeField4() != null) {
                            existingBranch.setBranchFreeField4(branch.getBranchFreeField4());
                        }
                        if (branch.getBranchFreeField5() != null) {
                            existingBranch.setBranchFreeField5(branch.getBranchFreeField5());
                        }
                        if (branch.getBranchFreeField6() != null) {
                            existingBranch.setBranchFreeField6(branch.getBranchFreeField6());
                        }
                        if (branch.getBranchFreeField7() != null) {
                            existingBranch.setBranchFreeField7(branch.getBranchFreeField7());
                        }
                        if (branch.getBranchFreeField8() != null) {
                            existingBranch.setBranchFreeField8(branch.getBranchFreeField8());
                        }
                        if (branch.getBranchFreeField9() != null) {
                            existingBranch.setBranchFreeField9(branch.getBranchFreeField9());
                        }
                        if (branch.getBranchFreeField10() != null) {
                            existingBranch.setBranchFreeField10(branch.getBranchFreeField10());
                        }
                        if (branch.getBranchFreeField11() != null) {
                            existingBranch.setBranchFreeField11(branch.getBranchFreeField11());
                        }
                        if (branch.getBranchFreeField12() != null) {
                            existingBranch.setBranchFreeField12(branch.getBranchFreeField12());
                        }
                        if (branch.getBranchFreeField13() != null) {
                            existingBranch.setBranchFreeField13(branch.getBranchFreeField13());
                        }
                        if (branch.getBranchFreeField14() != null) {
                            existingBranch.setBranchFreeField14(branch.getBranchFreeField14());
                        }
                        if (branch.getBranchFreeField15() != null) {
                            existingBranch.setBranchFreeField15(branch.getBranchFreeField15());
                        }
                        if (branch.getBranchFreeField16() != null) {
                            existingBranch.setBranchFreeField16(branch.getBranchFreeField16());
                        }
                        if (branch.getBranchFreeField17() != null) {
                            existingBranch.setBranchFreeField17(branch.getBranchFreeField17());
                        }
                        if (branch.getBranchFreeField18() != null) {
                            existingBranch.setBranchFreeField18(branch.getBranchFreeField18());
                        }
                        if (branch.getBranchFreeField19() != null) {
                            existingBranch.setBranchFreeField19(branch.getBranchFreeField19());
                        }
                        if (branch.getBranchFreeField20() != null) {
                            existingBranch.setBranchFreeField20(branch.getBranchFreeField20());
                        }
                        if (branch.getBranchFreeField21() != null) {
                            existingBranch.setBranchFreeField21(branch.getBranchFreeField21());
                        }
                        if (branch.getBranchFreeField22() != null) {
                            existingBranch.setBranchFreeField22(branch.getBranchFreeField22());
                        }
                        if (branch.getBranchFreeField23() != null) {
                            existingBranch.setBranchFreeField23(branch.getBranchFreeField23());
                        }
                        if (branch.getBranchFreeField24() != null) {
                            existingBranch.setBranchFreeField24(branch.getBranchFreeField24());
                        }
                        if (branch.getCreatedAt() != null) {
                            existingBranch.setCreatedAt(branch.getCreatedAt());
                        }
                        if (branch.getCreatedBy() != null) {
                            existingBranch.setCreatedBy(branch.getCreatedBy());
                        }
                        if (branch.getUpdatedAt() != null) {
                            existingBranch.setUpdatedAt(branch.getUpdatedAt());
                        }
                        if (branch.getUpdatedBy() != null) {
                            existingBranch.setUpdatedBy(branch.getUpdatedBy());
                        }
                        if (branch.getTimestamp() != null) {
                            existingBranch.setTimestamp(branch.getTimestamp());
                        }
                        if (branch.getStatus() != null) {
                            existingBranch.setStatus(branch.getStatus());
                        }

                        return existingBranch;
                    })
                    .flatMap(branchRepository::save)
                    .flatMap(savedBranch -> {
                        branchSearchRepository.save(savedBranch);

                        return Mono.just(savedBranch);
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
     * {@code GET  /branches} : get all the branches.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of branches in body.
     */
    @GetMapping("/branches")
    public Mono<List<Branch>> getAllBranches() {
        log.debug("REST request to get all Branches");
        return branchRepository.findAll().collectList();
    }

    /**
     * {@code GET  /branches} : get all the branches as a stream.
     * @return the {@link Flux} of branches.
     */
    @GetMapping(value = "/branches", produces = MediaType.APPLICATION_NDJSON_VALUE)
    public Flux<Branch> getAllBranchesAsStream() {
        log.debug("REST request to get all Branches as a stream");
        return branchRepository.findAll();
    }

    /**
     * {@code GET  /branches/:id} : get the "id" branch.
     *
     * @param id the id of the branch to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the branch, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/branches/{id}")
    public Mono<ResponseEntity<Branch>> getBranch(@PathVariable Long id) {
        log.debug("REST request to get Branch : {}", id);
        Mono<Branch> branch = branchRepository.findById(id);
        return ResponseUtil.wrapOrNotFound(branch);
    }

    /**
     * {@code DELETE  /branches/:id} : delete the "id" branch.
     *
     * @param id the id of the branch to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/branches/{id}")
    public Mono<ResponseEntity<Void>> deleteBranch(@PathVariable Long id) {
        log.debug("REST request to delete Branch : {}", id);
        return branchRepository
            .deleteById(id)
            .then(branchSearchRepository.deleteById(id))
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
     * {@code SEARCH  /_search/branches?query=:query} : search for the branch corresponding
     * to the query.
     *
     * @param query the query of the branch search.
     * @return the result of the search.
     */
    @GetMapping("/_search/branches")
    public Mono<List<Branch>> searchBranches(@RequestParam String query) {
        log.debug("REST request to search Branches for query {}", query);
        return branchSearchRepository.search(query).collectList();
    }
}
