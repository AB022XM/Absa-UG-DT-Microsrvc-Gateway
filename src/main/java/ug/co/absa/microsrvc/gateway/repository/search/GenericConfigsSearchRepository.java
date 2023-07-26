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
import ug.co.absa.microsrvc.gateway.domain.GenericConfigs;
import ug.co.absa.microsrvc.gateway.repository.GenericConfigsRepository;

/**
 * Spring Data Elasticsearch repository for the {@link GenericConfigs} entity.
 */
public interface GenericConfigsSearchRepository
    extends ReactiveElasticsearchRepository<GenericConfigs, Long>, GenericConfigsSearchRepositoryInternal {}

interface GenericConfigsSearchRepositoryInternal {
    Flux<GenericConfigs> search(String query);

    Flux<GenericConfigs> search(Query query);
}

class GenericConfigsSearchRepositoryInternalImpl implements GenericConfigsSearchRepositoryInternal {

    private final ReactiveElasticsearchTemplate reactiveElasticsearchTemplate;

    GenericConfigsSearchRepositoryInternalImpl(ReactiveElasticsearchTemplate reactiveElasticsearchTemplate) {
        this.reactiveElasticsearchTemplate = reactiveElasticsearchTemplate;
    }

    @Override
    public Flux<GenericConfigs> search(String query) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
        return search(nativeSearchQuery);
    }

    @Override
    public Flux<GenericConfigs> search(Query query) {
        return reactiveElasticsearchTemplate.search(query, GenericConfigs.class).map(SearchHit::getContent);
    }
}
