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
import ug.co.absa.microsrvc.gateway.domain.AppConfig;
import ug.co.absa.microsrvc.gateway.repository.AppConfigRepository;

/**
 * Spring Data Elasticsearch repository for the {@link AppConfig} entity.
 */
public interface AppConfigSearchRepository extends ReactiveElasticsearchRepository<AppConfig, Long>, AppConfigSearchRepositoryInternal {}

interface AppConfigSearchRepositoryInternal {
    Flux<AppConfig> search(String query);

    Flux<AppConfig> search(Query query);
}

class AppConfigSearchRepositoryInternalImpl implements AppConfigSearchRepositoryInternal {

    private final ReactiveElasticsearchTemplate reactiveElasticsearchTemplate;

    AppConfigSearchRepositoryInternalImpl(ReactiveElasticsearchTemplate reactiveElasticsearchTemplate) {
        this.reactiveElasticsearchTemplate = reactiveElasticsearchTemplate;
    }

    @Override
    public Flux<AppConfig> search(String query) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
        return search(nativeSearchQuery);
    }

    @Override
    public Flux<AppConfig> search(Query query) {
        return reactiveElasticsearchTemplate.search(query, AppConfig.class).map(SearchHit::getContent);
    }
}
