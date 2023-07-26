package ug.co.absa.microsrvc.gateway.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ug.co.absa.microsrvc.gateway.domain.CustomAudit;

/**
 * Spring Data R2DBC repository for the CustomAudit entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomAuditRepository extends ReactiveCrudRepository<CustomAudit, Long>, CustomAuditRepositoryInternal {
    @Override
    <S extends CustomAudit> Mono<S> save(S entity);

    @Override
    Flux<CustomAudit> findAll();

    @Override
    Mono<CustomAudit> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface CustomAuditRepositoryInternal {
    <S extends CustomAudit> Mono<S> save(S entity);

    Flux<CustomAudit> findAllBy(Pageable pageable);

    Flux<CustomAudit> findAll();

    Mono<CustomAudit> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<CustomAudit> findAllBy(Pageable pageable, Criteria criteria);

}
