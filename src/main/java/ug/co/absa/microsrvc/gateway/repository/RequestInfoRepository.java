package ug.co.absa.microsrvc.gateway.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ug.co.absa.microsrvc.gateway.domain.RequestInfo;

/**
 * Spring Data R2DBC repository for the RequestInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface RequestInfoRepository extends ReactiveCrudRepository<RequestInfo, Long>, RequestInfoRepositoryInternal {
    @Override
    <S extends RequestInfo> Mono<S> save(S entity);

    @Override
    Flux<RequestInfo> findAll();

    @Override
    Mono<RequestInfo> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface RequestInfoRepositoryInternal {
    <S extends RequestInfo> Mono<S> save(S entity);

    Flux<RequestInfo> findAllBy(Pageable pageable);

    Flux<RequestInfo> findAll();

    Mono<RequestInfo> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<RequestInfo> findAllBy(Pageable pageable, Criteria criteria);

}
