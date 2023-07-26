package ug.co.absa.microsrvc.gateway.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ug.co.absa.microsrvc.gateway.domain.Liquidation;

/**
 * Spring Data R2DBC repository for the Liquidation entity.
 */
@SuppressWarnings("unused")
@Repository
public interface LiquidationRepository extends ReactiveCrudRepository<Liquidation, Long>, LiquidationRepositoryInternal {
    @Override
    <S extends Liquidation> Mono<S> save(S entity);

    @Override
    Flux<Liquidation> findAll();

    @Override
    Mono<Liquidation> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface LiquidationRepositoryInternal {
    <S extends Liquidation> Mono<S> save(S entity);

    Flux<Liquidation> findAllBy(Pageable pageable);

    Flux<Liquidation> findAll();

    Mono<Liquidation> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<Liquidation> findAllBy(Pageable pageable, Criteria criteria);

}
