package ug.co.absa.microsrvc.gateway.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ug.co.absa.microsrvc.gateway.domain.AmolDTransactions;

/**
 * Spring Data R2DBC repository for the AmolDTransactions entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AmolDTransactionsRepository extends ReactiveCrudRepository<AmolDTransactions, Long>, AmolDTransactionsRepositoryInternal {
    @Query("SELECT * FROM amol_d_transactions entity WHERE entity.d_transaction_id = :id")
    Flux<AmolDTransactions> findByDTransaction(Long id);

    @Query("SELECT * FROM amol_d_transactions entity WHERE entity.d_transaction_id IS NULL")
    Flux<AmolDTransactions> findAllWhereDTransactionIsNull();

    @Override
    <S extends AmolDTransactions> Mono<S> save(S entity);

    @Override
    Flux<AmolDTransactions> findAll();

    @Override
    Mono<AmolDTransactions> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface AmolDTransactionsRepositoryInternal {
    <S extends AmolDTransactions> Mono<S> save(S entity);

    Flux<AmolDTransactions> findAllBy(Pageable pageable);

    Flux<AmolDTransactions> findAll();

    Mono<AmolDTransactions> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<AmolDTransactions> findAllBy(Pageable pageable, Criteria criteria);

}
