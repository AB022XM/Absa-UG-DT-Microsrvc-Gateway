package ug.co.absa.microsrvc.gateway.repository.search;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import org.springframework.data.elasticsearch.core.ReactiveElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.SearchHit;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.Query;
import org.springframework.data.elasticsearch.repository.ReactiveElasticsearchRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import ug.co.absa.microsrvc.gateway.domain.Bank;
import ug.co.absa.microsrvc.gateway.repository.BankRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Bank} entity.
 */
public interface BankSearchRepository extends ReactiveElasticsearchRepository<Bank, Long>, BankSearchRepositoryInternal {}

interface BankSearchRepositoryInternal {
    Flux<Bank> search(String query);

    Flux<Bank> search(Query query);
}

class BankSearchRepositoryInternalImpl implements BankSearchRepositoryInternal {

    private final ReactiveElasticsearchTemplate reactiveElasticsearchTemplate;

    BankSearchRepositoryInternalImpl(ReactiveElasticsearchTemplate reactiveElasticsearchTemplate) {
        this.reactiveElasticsearchTemplate = reactiveElasticsearchTemplate;
    }

    @Override
    public Flux<Bank> search(String query) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
        return search(nativeSearchQuery);
    }

    @Override
    public Flux<Bank> search(Query query) {
        return reactiveElasticsearchTemplate.search(query, Bank.class).map(SearchHit::getContent);
    }
}
