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
import ug.co.absa.microsrvc.gateway.domain.DefaultSettings;
import ug.co.absa.microsrvc.gateway.repository.DefaultSettingsRepository;

/**
 * Spring Data Elasticsearch repository for the {@link DefaultSettings} entity.
 */
public interface DefaultSettingsSearchRepository
    extends ReactiveElasticsearchRepository<DefaultSettings, Long>, DefaultSettingsSearchRepositoryInternal {}

interface DefaultSettingsSearchRepositoryInternal {
    Flux<DefaultSettings> search(String query);

    Flux<DefaultSettings> search(Query query);
}

class DefaultSettingsSearchRepositoryInternalImpl implements DefaultSettingsSearchRepositoryInternal {

    private final ReactiveElasticsearchTemplate reactiveElasticsearchTemplate;

    DefaultSettingsSearchRepositoryInternalImpl(ReactiveElasticsearchTemplate reactiveElasticsearchTemplate) {
        this.reactiveElasticsearchTemplate = reactiveElasticsearchTemplate;
    }

    @Override
    public Flux<DefaultSettings> search(String query) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
        return search(nativeSearchQuery);
    }

    @Override
    public Flux<DefaultSettings> search(Query query) {
        return reactiveElasticsearchTemplate.search(query, DefaultSettings.class).map(SearchHit::getContent);
    }
}
