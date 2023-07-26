package ug.co.absa.microsrvc.gateway.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ug.co.absa.microsrvc.gateway.domain.IncomingJSONResponse;

/**
 * Spring Data R2DBC repository for the IncomingJSONResponse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface IncomingJSONResponseRepository
    extends ReactiveCrudRepository<IncomingJSONResponse, Long>, IncomingJSONResponseRepositoryInternal {
    @Override
    <S extends IncomingJSONResponse> Mono<S> save(S entity);

    @Override
    Flux<IncomingJSONResponse> findAll();

    @Override
    Mono<IncomingJSONResponse> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface IncomingJSONResponseRepositoryInternal {
    <S extends IncomingJSONResponse> Mono<S> save(S entity);

    Flux<IncomingJSONResponse> findAllBy(Pageable pageable);

    Flux<IncomingJSONResponse> findAll();

    Mono<IncomingJSONResponse> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<IncomingJSONResponse> findAllBy(Pageable pageable, Criteria criteria);

}
