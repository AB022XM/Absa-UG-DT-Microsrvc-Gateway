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
import ug.co.absa.microsrvc.gateway.domain.AmolDTransactions;
import ug.co.absa.microsrvc.gateway.repository.AmolDTransactionsRepository;

/**
 * Spring Data Elasticsearch repository for the {@link AmolDTransactions} entity.
 */
public interface AmolDTransactionsSearchRepository
    extends ReactiveElasticsearchRepository<AmolDTransactions, Long>, AmolDTransactionsSearchRepositoryInternal {}

interface AmolDTransactionsSearchRepositoryInternal {
    Flux<AmolDTransactions> search(String query);

    Flux<AmolDTransactions> search(Query query);
}

class AmolDTransactionsSearchRepositoryInternalImpl implements AmolDTransactionsSearchRepositoryInternal {

    private final ReactiveElasticsearchTemplate reactiveElasticsearchTemplate;

    AmolDTransactionsSearchRepositoryInternalImpl(ReactiveElasticsearchTemplate reactiveElasticsearchTemplate) {
        this.reactiveElasticsearchTemplate = reactiveElasticsearchTemplate;
    }

    @Override
    public Flux<AmolDTransactions> search(String query) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
        return search(nativeSearchQuery);
    }

    @Override
    public Flux<AmolDTransactions> search(Query query) {
        return reactiveElasticsearchTemplate.search(query, AmolDTransactions.class).map(SearchHit::getContent);
    }
}
