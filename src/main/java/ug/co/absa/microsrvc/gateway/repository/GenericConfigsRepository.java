package ug.co.absa.microsrvc.gateway.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ug.co.absa.microsrvc.gateway.domain.GenericConfigs;

/**
 * Spring Data R2DBC repository for the GenericConfigs entity.
 */
@SuppressWarnings("unused")
@Repository
public interface GenericConfigsRepository extends ReactiveCrudRepository<GenericConfigs, Long>, GenericConfigsRepositoryInternal {
    @Override
    <S extends GenericConfigs> Mono<S> save(S entity);

    @Override
    Flux<GenericConfigs> findAll();

    @Override
    Mono<GenericConfigs> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface GenericConfigsRepositoryInternal {
    <S extends GenericConfigs> Mono<S> save(S entity);

    Flux<GenericConfigs> findAllBy(Pageable pageable);

    Flux<GenericConfigs> findAll();

    Mono<GenericConfigs> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<GenericConfigs> findAllBy(Pageable pageable, Criteria criteria);

}
