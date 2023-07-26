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
import ug.co.absa.microsrvc.gateway.domain.IncomingJSONResponse;
import ug.co.absa.microsrvc.gateway.repository.IncomingJSONResponseRepository;

/**
 * Spring Data Elasticsearch repository for the {@link IncomingJSONResponse} entity.
 */
public interface IncomingJSONResponseSearchRepository
    extends ReactiveElasticsearchRepository<IncomingJSONResponse, Long>, IncomingJSONResponseSearchRepositoryInternal {}

interface IncomingJSONResponseSearchRepositoryInternal {
    Flux<IncomingJSONResponse> search(String query);

    Flux<IncomingJSONResponse> search(Query query);
}

class IncomingJSONResponseSearchRepositoryInternalImpl implements IncomingJSONResponseSearchRepositoryInternal {

    private final ReactiveElasticsearchTemplate reactiveElasticsearchTemplate;

    IncomingJSONResponseSearchRepositoryInternalImpl(ReactiveElasticsearchTemplate reactiveElasticsearchTemplate) {
        this.reactiveElasticsearchTemplate = reactiveElasticsearchTemplate;
    }

    @Override
    public Flux<IncomingJSONResponse> search(String query) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
        return search(nativeSearchQuery);
    }

    @Override
    public Flux<IncomingJSONResponse> search(Query query) {
        return reactiveElasticsearchTemplate.search(query, IncomingJSONResponse.class).map(SearchHit::getContent);
    }
}
