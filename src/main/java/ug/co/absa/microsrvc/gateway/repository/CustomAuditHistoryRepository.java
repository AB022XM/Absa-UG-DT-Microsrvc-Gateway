package ug.co.absa.microsrvc.gateway.repository;

import java.util.UUID;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ug.co.absa.microsrvc.gateway.domain.CustomAuditHistory;

/**
 * Spring Data R2DBC repository for the CustomAuditHistory entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CustomAuditHistoryRepository
    extends ReactiveCrudRepository<CustomAuditHistory, UUID>, CustomAuditHistoryRepositoryInternal {
    @Override
    <S extends CustomAuditHistory> Mono<S> save(S entity);

    @Override
    Flux<CustomAuditHistory> findAll();

    @Override
    Mono<CustomAuditHistory> findById(UUID id);

    @Override
    Mono<Void> deleteById(UUID id);
}

interface CustomAuditHistoryRepositoryInternal {
    <S extends CustomAuditHistory> Mono<S> save(S entity);

    Flux<CustomAuditHistory> findAllBy(Pageable pageable);

    Flux<CustomAuditHistory> findAll();

    Mono<CustomAuditHistory> findById(UUID id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<CustomAuditHistory> findAllBy(Pageable pageable, Criteria criteria);

}
