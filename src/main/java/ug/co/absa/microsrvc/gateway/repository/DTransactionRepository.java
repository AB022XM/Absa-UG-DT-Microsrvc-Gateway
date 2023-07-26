package ug.co.absa.microsrvc.gateway.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ug.co.absa.microsrvc.gateway.domain.DTransaction;

/**
 * Spring Data R2DBC repository for the DTransaction entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DTransactionRepository extends ReactiveCrudRepository<DTransaction, Long>, DTransactionRepositoryInternal {
    @Query("SELECT * FROM d_transaction entity WHERE entity.transaction_id = :id")
    Flux<DTransaction> findByTransaction(Long id);

    @Query("SELECT * FROM d_transaction entity WHERE entity.transaction_id IS NULL")
    Flux<DTransaction> findAllWhereTransactionIsNull();

    @Query("SELECT * FROM d_transaction entity WHERE entity.transaction_id = :id")
    Flux<DTransaction> findByTransaction(Long id);

    @Query("SELECT * FROM d_transaction entity WHERE entity.transaction_id IS NULL")
    Flux<DTransaction> findAllWhereTransactionIsNull();

    @Query("SELECT * FROM d_transaction entity WHERE entity.transaction_id = :id")
    Flux<DTransaction> findByTransaction(Long id);

    @Query("SELECT * FROM d_transaction entity WHERE entity.transaction_id IS NULL")
    Flux<DTransaction> findAllWhereTransactionIsNull();

    @Query("SELECT * FROM d_transaction entity WHERE entity.customer_id = :id")
    Flux<DTransaction> findByCustomer(Long id);

    @Query("SELECT * FROM d_transaction entity WHERE entity.customer_id IS NULL")
    Flux<DTransaction> findAllWhereCustomerIsNull();

    @Override
    <S extends DTransaction> Mono<S> save(S entity);

    @Override
    Flux<DTransaction> findAll();

    @Override
    Mono<DTransaction> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface DTransactionRepositoryInternal {
    <S extends DTransaction> Mono<S> save(S entity);

    Flux<DTransaction> findAllBy(Pageable pageable);

    Flux<DTransaction> findAll();

    Mono<DTransaction> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<DTransaction> findAllBy(Pageable pageable, Criteria criteria);

}
