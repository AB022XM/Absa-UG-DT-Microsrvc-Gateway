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
import ug.co.absa.microsrvc.gateway.domain.DTransactionSummary;
import ug.co.absa.microsrvc.gateway.repository.DTransactionSummaryRepository;

/**
 * Spring Data Elasticsearch repository for the {@link DTransactionSummary} entity.
 */
public interface DTransactionSummarySearchRepository
    extends ReactiveElasticsearchRepository<DTransactionSummary, Long>, DTransactionSummarySearchRepositoryInternal {}

interface DTransactionSummarySearchRepositoryInternal {
    Flux<DTransactionSummary> search(String query);

    Flux<DTransactionSummary> search(Query query);
}

class DTransactionSummarySearchRepositoryInternalImpl implements DTransactionSummarySearchRepositoryInternal {

    private final ReactiveElasticsearchTemplate reactiveElasticsearchTemplate;

    DTransactionSummarySearchRepositoryInternalImpl(ReactiveElasticsearchTemplate reactiveElasticsearchTemplate) {
        this.reactiveElasticsearchTemplate = reactiveElasticsearchTemplate;
    }

    @Override
    public Flux<DTransactionSummary> search(String query) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
        return search(nativeSearchQuery);
    }

    @Override
    public Flux<DTransactionSummary> search(Query query) {
        return reactiveElasticsearchTemplate.search(query, DTransactionSummary.class).map(SearchHit::getContent);
    }
}
