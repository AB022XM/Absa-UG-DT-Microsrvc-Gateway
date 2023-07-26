package ug.co.absa.microsrvc.gateway.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ug.co.absa.microsrvc.gateway.domain.PaymentConfig;

/**
 * Spring Data R2DBC repository for the PaymentConfig entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PaymentConfigRepository extends ReactiveCrudRepository<PaymentConfig, Long>, PaymentConfigRepositoryInternal {
    @Override
    <S extends PaymentConfig> Mono<S> save(S entity);

    @Override
    Flux<PaymentConfig> findAll();

    @Override
    Mono<PaymentConfig> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface PaymentConfigRepositoryInternal {
    <S extends PaymentConfig> Mono<S> save(S entity);

    Flux<PaymentConfig> findAllBy(Pageable pageable);

    Flux<PaymentConfig> findAll();

    Mono<PaymentConfig> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<PaymentConfig> findAllBy(Pageable pageable, Criteria criteria);

}
