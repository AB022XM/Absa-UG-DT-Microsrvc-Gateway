package ug.co.absa.microsrvc.gateway.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ug.co.absa.microsrvc.gateway.domain.Devices;

/**
 * Spring Data R2DBC repository for the Devices entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DevicesRepository extends ReactiveCrudRepository<Devices, Long>, DevicesRepositoryInternal {
    @Override
    <S extends Devices> Mono<S> save(S entity);

    @Override
    Flux<Devices> findAll();

    @Override
    Mono<Devices> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface DevicesRepositoryInternal {
    <S extends Devices> Mono<S> save(S entity);

    Flux<Devices> findAllBy(Pageable pageable);

    Flux<Devices> findAll();

    Mono<Devices> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<Devices> findAllBy(Pageable pageable, Criteria criteria);

}
