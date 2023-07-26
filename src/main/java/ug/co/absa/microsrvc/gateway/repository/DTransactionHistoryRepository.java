package ug.co.absa.microsrvc.gateway.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ug.co.absa.microsrvc.gateway.domain.DTransactionHistory;

/**
 * Spring Data R2DBC repository for the DTransactionHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DTransactionHistoryRepository
    extends ReactiveCrudRepository<DTransactionHistory, Long>, DTransactionHistoryRepositoryInternal {
    @Query("SELECT * FROM d_transaction_history entity WHERE entity.customer_id = :id")
    Flux<DTransactionHistory> findByCustomer(Long id);

    @Query("SELECT * FROM d_transaction_history entity WHERE entity.customer_id IS NULL")
    Flux<DTransactionHistory> findAllWhereCustomerIsNull();

    @Override
    <S extends DTransactionHistory> Mono<S> save(S entity);

    @Override
    Flux<DTransactionHistory> findAll();

    @Override
    Mono<DTransactionHistory> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface DTransactionHistoryRepositoryInternal {
    <S extends DTransactionHistory> Mono<S> save(S entity);

    Flux<DTransactionHistory> findAllBy(Pageable pageable);

    Flux<DTransactionHistory> findAll();

    Mono<DTransactionHistory> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<DTransactionHistory> findAllBy(Pageable pageable, Criteria criteria);

}
