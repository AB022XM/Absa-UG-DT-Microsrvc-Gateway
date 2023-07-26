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
import ug.co.absa.microsrvc.gateway.domain.Apps;
import ug.co.absa.microsrvc.gateway.repository.AppsRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Apps} entity.
 */
public interface AppsSearchRepository extends ReactiveElasticsearchRepository<Apps, Long>, AppsSearchRepositoryInternal {}

interface AppsSearchRepositoryInternal {
    Flux<Apps> search(String query);

    Flux<Apps> search(Query query);
}

class AppsSearchRepositoryInternalImpl implements AppsSearchRepositoryInternal {

    private final ReactiveElasticsearchTemplate reactiveElasticsearchTemplate;

    AppsSearchRepositoryInternalImpl(ReactiveElasticsearchTemplate reactiveElasticsearchTemplate) {
        this.reactiveElasticsearchTemplate = reactiveElasticsearchTemplate;
    }

    @Override
    public Flux<Apps> search(String query) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
        return search(nativeSearchQuery);
    }

    @Override
    public Flux<Apps> search(Query query) {
        return reactiveElasticsearchTemplate.search(query, Apps.class).map(SearchHit::getContent);
    }
}
