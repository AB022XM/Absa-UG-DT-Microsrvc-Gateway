package ug.co.absa.microsrvc.gateway.repository.search;

import static org.elasticsearch.index.query.QueryBuilders.queryStringQuery;

import java.util.UUID;
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
import ug.co.absa.microsrvc.gateway.domain.CustomAuditHistory;
import ug.co.absa.microsrvc.gateway.repository.CustomAuditHistoryRepository;

/**
 * Spring Data Elasticsearch repository for the {@link CustomAuditHistory} entity.
 */
public interface CustomAuditHistorySearchRepository
    extends ReactiveElasticsearchRepository<CustomAuditHistory, UUID>, CustomAuditHistorySearchRepositoryInternal {}

interface CustomAuditHistorySearchRepositoryInternal {
    Flux<CustomAuditHistory> search(String query);

    Flux<CustomAuditHistory> search(Query query);
}

class CustomAuditHistorySearchRepositoryInternalImpl implements CustomAuditHistorySearchRepositoryInternal {

    private final ReactiveElasticsearchTemplate reactiveElasticsearchTemplate;

    CustomAuditHistorySearchRepositoryInternalImpl(ReactiveElasticsearchTemplate reactiveElasticsearchTemplate) {
        this.reactiveElasticsearchTemplate = reactiveElasticsearchTemplate;
    }

    @Override
    public Flux<CustomAuditHistory> search(String query) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
        return search(nativeSearchQuery);
    }

    @Override
    public Flux<CustomAuditHistory> search(Query query) {
        return reactiveElasticsearchTemplate.search(query, CustomAuditHistory.class).map(SearchHit::getContent);
    }
}
