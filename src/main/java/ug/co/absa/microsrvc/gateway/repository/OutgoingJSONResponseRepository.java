package ug.co.absa.microsrvc.gateway.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ug.co.absa.microsrvc.gateway.domain.OutgoingJSONResponse;

/**
 * Spring Data R2DBC repository for the OutgoingJSONResponse entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OutgoingJSONResponseRepository
    extends ReactiveCrudRepository<OutgoingJSONResponse, Long>, OutgoingJSONResponseRepositoryInternal {
    @Override
    <S extends OutgoingJSONResponse> Mono<S> save(S entity);

    @Override
    Flux<OutgoingJSONResponse> findAll();

    @Override
    Mono<OutgoingJSONResponse> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface OutgoingJSONResponseRepositoryInternal {
    <S extends OutgoingJSONResponse> Mono<S> save(S entity);

    Flux<OutgoingJSONResponse> findAllBy(Pageable pageable);

    Flux<OutgoingJSONResponse> findAll();

    Mono<OutgoingJSONResponse> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<OutgoingJSONResponse> findAllBy(Pageable pageable, Criteria criteria);

}
