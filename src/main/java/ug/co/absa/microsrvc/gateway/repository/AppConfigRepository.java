package ug.co.absa.microsrvc.gateway.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ug.co.absa.microsrvc.gateway.domain.AppConfig;

/**
 * Spring Data R2DBC repository for the AppConfig entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AppConfigRepository extends ReactiveCrudRepository<AppConfig, Long>, AppConfigRepositoryInternal {
    @Query("SELECT * FROM app_config entity WHERE entity.apps_id = :id")
    Flux<AppConfig> findByApps(Long id);

    @Query("SELECT * FROM app_config entity WHERE entity.apps_id IS NULL")
    Flux<AppConfig> findAllWhereAppsIsNull();

    @Override
    <S extends AppConfig> Mono<S> save(S entity);

    @Override
    Flux<AppConfig> findAll();

    @Override
    Mono<AppConfig> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface AppConfigRepositoryInternal {
    <S extends AppConfig> Mono<S> save(S entity);

    Flux<AppConfig> findAllBy(Pageable pageable);

    Flux<AppConfig> findAll();

    Mono<AppConfig> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<AppConfig> findAllBy(Pageable pageable, Criteria criteria);

}
