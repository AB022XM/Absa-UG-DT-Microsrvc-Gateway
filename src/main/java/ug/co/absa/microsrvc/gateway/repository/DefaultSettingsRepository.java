package ug.co.absa.microsrvc.gateway.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ug.co.absa.microsrvc.gateway.domain.DefaultSettings;

/**
 * Spring Data R2DBC repository for the DefaultSettings entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DefaultSettingsRepository extends ReactiveCrudRepository<DefaultSettings, Long>, DefaultSettingsRepositoryInternal {
    @Override
    <S extends DefaultSettings> Mono<S> save(S entity);

    @Override
    Flux<DefaultSettings> findAll();

    @Override
    Mono<DefaultSettings> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface DefaultSettingsRepositoryInternal {
    <S extends DefaultSettings> Mono<S> save(S entity);

    Flux<DefaultSettings> findAllBy(Pageable pageable);

    Flux<DefaultSettings> findAll();

    Mono<DefaultSettings> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<DefaultSettings> findAllBy(Pageable pageable, Criteria criteria);

}
