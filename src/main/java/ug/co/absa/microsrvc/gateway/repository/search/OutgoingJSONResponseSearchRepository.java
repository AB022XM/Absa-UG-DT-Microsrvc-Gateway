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
import ug.co.absa.microsrvc.gateway.domain.OutgoingJSONResponse;
import ug.co.absa.microsrvc.gateway.repository.OutgoingJSONResponseRepository;

/**
 * Spring Data Elasticsearch repository for the {@link OutgoingJSONResponse} entity.
 */
public interface OutgoingJSONResponseSearchRepository
    extends ReactiveElasticsearchRepository<OutgoingJSONResponse, Long>, OutgoingJSONResponseSearchRepositoryInternal {}

interface OutgoingJSONResponseSearchRepositoryInternal {
    Flux<OutgoingJSONResponse> search(String query);

    Flux<OutgoingJSONResponse> search(Query query);
}

class OutgoingJSONResponseSearchRepositoryInternalImpl implements OutgoingJSONResponseSearchRepositoryInternal {

    private final ReactiveElasticsearchTemplate reactiveElasticsearchTemplate;

    OutgoingJSONResponseSearchRepositoryInternalImpl(ReactiveElasticsearchTemplate reactiveElasticsearchTemplate) {
        this.reactiveElasticsearchTemplate = reactiveElasticsearchTemplate;
    }

    @Override
    public Flux<OutgoingJSONResponse> search(String query) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
        return search(nativeSearchQuery);
    }

    @Override
    public Flux<OutgoingJSONResponse> search(Query query) {
        return reactiveElasticsearchTemplate.search(query, OutgoingJSONResponse.class).map(SearchHit::getContent);
    }
}
