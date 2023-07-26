package ug.co.absa.microsrvc.gateway.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ug.co.absa.microsrvc.gateway.domain.DTransactionChannel;

/**
 * Spring Data R2DBC repository for the DTransactionChannel entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DTransactionChannelRepository
    extends ReactiveCrudRepository<DTransactionChannel, Long>, DTransactionChannelRepositoryInternal {
    @Query("SELECT * FROM d_transaction_channel entity WHERE entity.d_transaction_id = :id")
    Flux<DTransactionChannel> findByDTransaction(Long id);

    @Query("SELECT * FROM d_transaction_channel entity WHERE entity.d_transaction_id IS NULL")
    Flux<DTransactionChannel> findAllWhereDTransactionIsNull();

    @Override
    <S extends DTransactionChannel> Mono<S> save(S entity);

    @Override
    Flux<DTransactionChannel> findAll();

    @Override
    Mono<DTransactionChannel> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface DTransactionChannelRepositoryInternal {
    <S extends DTransactionChannel> Mono<S> save(S entity);

    Flux<DTransactionChannel> findAllBy(Pageable pageable);

    Flux<DTransactionChannel> findAll();

    Mono<DTransactionChannel> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<DTransactionChannel> findAllBy(Pageable pageable, Criteria criteria);

}
