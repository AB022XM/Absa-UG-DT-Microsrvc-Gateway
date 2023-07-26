package ug.co.absa.microsrvc.gateway.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ug.co.absa.microsrvc.gateway.domain.PaymentItems;

/**
 * Spring Data R2DBC repository for the PaymentItems entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaymentItemsRepository extends ReactiveCrudRepository<PaymentItems, Long>, PaymentItemsRepositoryInternal {
    @Query("SELECT * FROM payment_items entity WHERE entity.biller_id = :id")
    Flux<PaymentItems> findByBiller(Long id);

    @Query("SELECT * FROM payment_items entity WHERE entity.biller_id IS NULL")
    Flux<PaymentItems> findAllWhereBillerIsNull();

    @Override
    <S extends PaymentItems> Mono<S> save(S entity);

    @Override
    Flux<PaymentItems> findAll();

    @Override
    Mono<PaymentItems> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface PaymentItemsRepositoryInternal {
    <S extends PaymentItems> Mono<S> save(S entity);

    Flux<PaymentItems> findAllBy(Pageable pageable);

    Flux<PaymentItems> findAll();

    Mono<PaymentItems> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<PaymentItems> findAllBy(Pageable pageable, Criteria criteria);

}
