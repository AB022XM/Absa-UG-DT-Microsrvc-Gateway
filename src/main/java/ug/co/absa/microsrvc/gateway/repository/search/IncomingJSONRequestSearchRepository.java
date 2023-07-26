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
import ug.co.absa.microsrvc.gateway.domain.IncomingJSONRequest;
import ug.co.absa.microsrvc.gateway.repository.IncomingJSONRequestRepository;

/**
 * Spring Data Elasticsearch repository for the {@link IncomingJSONRequest} entity.
 */
public interface IncomingJSONRequestSearchRepository
    extends ReactiveElasticsearchRepository<IncomingJSONRequest, Long>, IncomingJSONRequestSearchRepositoryInternal {}

interface IncomingJSONRequestSearchRepositoryInternal {
    Flux<IncomingJSONRequest> search(String query);

    Flux<IncomingJSONRequest> search(Query query);
}

class IncomingJSONRequestSearchRepositoryInternalImpl implements IncomingJSONRequestSearchRepositoryInternal {

    private final ReactiveElasticsearchTemplate reactiveElasticsearchTemplate;

    IncomingJSONRequestSearchRepositoryInternalImpl(ReactiveElasticsearchTemplate reactiveElasticsearchTemplate) {
        this.reactiveElasticsearchTemplate = reactiveElasticsearchTemplate;
    }

    @Override
    public Flux<IncomingJSONRequest> search(String query) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
        return search(nativeSearchQuery);
    }

    @Override
    public Flux<IncomingJSONRequest> search(Query query) {
        return reactiveElasticsearchTemplate.search(query, IncomingJSONRequest.class).map(SearchHit::getContent);
    }
}
