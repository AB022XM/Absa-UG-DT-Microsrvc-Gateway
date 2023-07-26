package ug.co.absa.microsrvc.gateway.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ug.co.absa.microsrvc.gateway.domain.DTransactionSummary;

/**
 * Spring Data R2DBC repository for the DTransactionSummary entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DTransactionSummaryRepository
    extends ReactiveCrudRepository<DTransactionSummary, Long>, DTransactionSummaryRepositoryInternal {
    @Query("SELECT * FROM d_transaction_summary entity WHERE entity.customer_id = :id")
    Flux<DTransactionSummary> findByCustomer(Long id);

    @Query("SELECT * FROM d_transaction_summary entity WHERE entity.customer_id IS NULL")
    Flux<DTransactionSummary> findAllWhereCustomerIsNull();

    @Override
    <S extends DTransactionSummary> Mono<S> save(S entity);

    @Override
    Flux<DTransactionSummary> findAll();

    @Override
    Mono<DTransactionSummary> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface DTransactionSummaryRepositoryInternal {
    <S extends DTransactionSummary> Mono<S> save(S entity);

    Flux<DTransactionSummary> findAllBy(Pageable pageable);

    Flux<DTransactionSummary> findAll();

    Mono<DTransactionSummary> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<DTransactionSummary> findAllBy(Pageable pageable, Criteria criteria);

}
