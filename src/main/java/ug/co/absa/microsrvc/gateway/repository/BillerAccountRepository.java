package ug.co.absa.microsrvc.gateway.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ug.co.absa.microsrvc.gateway.domain.BillerAccount;

/**
 * Spring Data R2DBC repository for the BillerAccount entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BillerAccountRepository extends ReactiveCrudRepository<BillerAccount, Long>, BillerAccountRepositoryInternal {
    @Query("SELECT * FROM biller_account entity WHERE entity.biller_id = :id")
    Flux<BillerAccount> findByBiller(Long id);

    @Query("SELECT * FROM biller_account entity WHERE entity.biller_id IS NULL")
    Flux<BillerAccount> findAllWhereBillerIsNull();

    @Override
    <S extends BillerAccount> Mono<S> save(S entity);

    @Override
    Flux<BillerAccount> findAll();

    @Override
    Mono<BillerAccount> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface BillerAccountRepositoryInternal {
    <S extends BillerAccount> Mono<S> save(S entity);

    Flux<BillerAccount> findAllBy(Pageable pageable);

    Flux<BillerAccount> findAll();

    Mono<BillerAccount> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<BillerAccount> findAllBy(Pageable pageable, Criteria criteria);

}
