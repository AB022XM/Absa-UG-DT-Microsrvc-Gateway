package ug.co.absa.microsrvc.gateway.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ug.co.absa.microsrvc.gateway.domain.Bank;

/**
 * Spring Data R2DBC repository for the Bank entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BankRepository extends ReactiveCrudRepository<Bank, Long>, BankRepositoryInternal {
    @Override
    <S extends Bank> Mono<S> save(S entity);

    @Override
    Flux<Bank> findAll();

    @Override
    Mono<Bank> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface BankRepositoryInternal {
    <S extends Bank> Mono<S> save(S entity);

    Flux<Bank> findAllBy(Pageable pageable);

    Flux<Bank> findAll();

    Mono<Bank> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<Bank> findAllBy(Pageable pageable, Criteria criteria);

}
