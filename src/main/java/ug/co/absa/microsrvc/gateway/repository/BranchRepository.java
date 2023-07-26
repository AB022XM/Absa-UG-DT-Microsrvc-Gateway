package ug.co.absa.microsrvc.gateway.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ug.co.absa.microsrvc.gateway.domain.Branch;

/**
 * Spring Data R2DBC repository for the Branch entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BranchRepository extends ReactiveCrudRepository<Branch, Long>, BranchRepositoryInternal {
    @Query("SELECT * FROM branch entity WHERE entity.bank_id = :id")
    Flux<Branch> findByBank(Long id);

    @Query("SELECT * FROM branch entity WHERE entity.bank_id IS NULL")
    Flux<Branch> findAllWhereBankIsNull();

    @Override
    <S extends Branch> Mono<S> save(S entity);

    @Override
    Flux<Branch> findAll();

    @Override
    Mono<Branch> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface BranchRepositoryInternal {
    <S extends Branch> Mono<S> save(S entity);

    Flux<Branch> findAllBy(Pageable pageable);

    Flux<Branch> findAll();

    Mono<Branch> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<Branch> findAllBy(Pageable pageable, Criteria criteria);

}
