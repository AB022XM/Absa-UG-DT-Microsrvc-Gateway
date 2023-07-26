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
import ug.co.absa.microsrvc.gateway.domain.CustomAudit;
import ug.co.absa.microsrvc.gateway.repository.CustomAuditRepository;

/**
 * Spring Data Elasticsearch repository for the {@link CustomAudit} entity.
 */
public interface CustomAuditSearchRepository
    extends ReactiveElasticsearchRepository<CustomAudit, Long>, CustomAuditSearchRepositoryInternal {}

interface CustomAuditSearchRepositoryInternal {
    Flux<CustomAudit> search(String query);

    Flux<CustomAudit> search(Query query);
}

class CustomAuditSearchRepositoryInternalImpl implements CustomAuditSearchRepositoryInternal {

    private final ReactiveElasticsearchTemplate reactiveElasticsearchTemplate;

    CustomAuditSearchRepositoryInternalImpl(ReactiveElasticsearchTemplate reactiveElasticsearchTemplate) {
        this.reactiveElasticsearchTemplate = reactiveElasticsearchTemplate;
    }

    @Override
    public Flux<CustomAudit> search(String query) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
        return search(nativeSearchQuery);
    }

    @Override
    public Flux<CustomAudit> search(Query query) {
        return reactiveElasticsearchTemplate.search(query, CustomAudit.class).map(SearchHit::getContent);
    }
}
