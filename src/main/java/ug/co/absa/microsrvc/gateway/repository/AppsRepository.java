package ug.co.absa.microsrvc.gateway.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ug.co.absa.microsrvc.gateway.domain.Apps;

/**
 * Spring Data R2DBC repository for the Apps entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AppsRepository extends ReactiveCrudRepository<Apps, Long>, AppsRepositoryInternal {
    @Override
    <S extends Apps> Mono<S> save(S entity);

    @Override
    Flux<Apps> findAll();

    @Override
    Mono<Apps> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface AppsRepositoryInternal {
    <S extends Apps> Mono<S> save(S entity);

    Flux<Apps> findAllBy(Pageable pageable);

    Flux<Apps> findAll();

    Mono<Apps> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<Apps> findAllBy(Pageable pageable, Criteria criteria);

}
