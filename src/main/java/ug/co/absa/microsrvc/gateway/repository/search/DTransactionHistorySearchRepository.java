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
import ug.co.absa.microsrvc.gateway.domain.DTransactionHistory;
import ug.co.absa.microsrvc.gateway.repository.DTransactionHistoryRepository;

/**
 * Spring Data Elasticsearch repository for the {@link DTransactionHistory} entity.
 */
public interface DTransactionHistorySearchRepository
    extends ReactiveElasticsearchRepository<DTransactionHistory, Long>, DTransactionHistorySearchRepositoryInternal {}

interface DTransactionHistorySearchRepositoryInternal {
    Flux<DTransactionHistory> search(String query);

    Flux<DTransactionHistory> search(Query query);
}

class DTransactionHistorySearchRepositoryInternalImpl implements DTransactionHistorySearchRepositoryInternal {

    private final ReactiveElasticsearchTemplate reactiveElasticsearchTemplate;

    DTransactionHistorySearchRepositoryInternalImpl(ReactiveElasticsearchTemplate reactiveElasticsearchTemplate) {
        this.reactiveElasticsearchTemplate = reactiveElasticsearchTemplate;
    }

    @Override
    public Flux<DTransactionHistory> search(String query) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
        return search(nativeSearchQuery);
    }

    @Override
    public Flux<DTransactionHistory> search(Query query) {
        return reactiveElasticsearchTemplate.search(query, DTransactionHistory.class).map(SearchHit::getContent);
    }
}
