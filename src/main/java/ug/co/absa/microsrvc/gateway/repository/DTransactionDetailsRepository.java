package ug.co.absa.microsrvc.gateway.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ug.co.absa.microsrvc.gateway.domain.DTransactionDetails;

/**
 * Spring Data R2DBC repository for the DTransactionDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DTransactionDetailsRepository
    extends ReactiveCrudRepository<DTransactionDetails, Long>, DTransactionDetailsRepositoryInternal {
    @Query("SELECT * FROM d_transaction_details entity WHERE entity.customer_id = :id")
    Flux<DTransactionDetails> findByCustomer(Long id);

    @Query("SELECT * FROM d_transaction_details entity WHERE entity.customer_id IS NULL")
    Flux<DTransactionDetails> findAllWhereCustomerIsNull();

    @Override
    <S extends DTransactionDetails> Mono<S> save(S entity);

    @Override
    Flux<DTransactionDetails> findAll();

    @Override
    Mono<DTransactionDetails> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface DTransactionDetailsRepositoryInternal {
    <S extends DTransactionDetails> Mono<S> save(S entity);

    Flux<DTransactionDetails> findAllBy(Pageable pageable);

    Flux<DTransactionDetails> findAll();

    Mono<DTransactionDetails> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<DTransactionDetails> findAllBy(Pageable pageable, Criteria criteria);

}
