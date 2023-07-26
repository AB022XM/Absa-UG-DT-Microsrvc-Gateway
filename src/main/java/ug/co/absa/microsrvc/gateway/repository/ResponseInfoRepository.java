package ug.co.absa.microsrvc.gateway.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ug.co.absa.microsrvc.gateway.domain.ResponseInfo;

/**
 * Spring Data R2DBC repository for the ResponseInfo entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ResponseInfoRepository extends ReactiveCrudRepository<ResponseInfo, Long>, ResponseInfoRepositoryInternal {
    @Override
    <S extends ResponseInfo> Mono<S> save(S entity);

    @Override
    Flux<ResponseInfo> findAll();

    @Override
    Mono<ResponseInfo> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface ResponseInfoRepositoryInternal {
    <S extends ResponseInfo> Mono<S> save(S entity);

    Flux<ResponseInfo> findAllBy(Pageable pageable);

    Flux<ResponseInfo> findAll();

    Mono<ResponseInfo> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<ResponseInfo> findAllBy(Pageable pageable, Criteria criteria);

}
