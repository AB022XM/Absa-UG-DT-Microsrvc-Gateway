package ug.co.absa.microsrvc.gateway.repository;

import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ug.co.absa.microsrvc.gateway.domain.AccountDetails;

/**
 * Spring Data R2DBC repository for the AccountDetails entity.
 */
@SuppressWarnings("unused")
@Repository
public interface AccountDetailsRepository extends ReactiveCrudRepository<AccountDetails, Long>, AccountDetailsRepositoryInternal {
    @Override
    <S extends AccountDetails> Mono<S> save(S entity);

    @Override
    Flux<AccountDetails> findAll();

    @Override
    Mono<AccountDetails> findById(Long id);

    @Override
    Mono<Void> deleteById(Long id);
}

interface AccountDetailsRepositoryInternal {
    <S extends AccountDetails> Mono<S> save(S entity);

    Flux<AccountDetails> findAllBy(Pageable pageable);

    Flux<AccountDetails> findAll();

    Mono<AccountDetails> findById(Long id);
    // this is not supported at the moment because of https://github.com/jhipster/generator-jhipster/issues/18269
    // Flux<AccountDetails> findAllBy(Pageable pageable, Criteria criteria);

}
