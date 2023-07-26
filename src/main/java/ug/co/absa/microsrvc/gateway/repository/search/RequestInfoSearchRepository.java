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
import ug.co.absa.microsrvc.gateway.domain.RequestInfo;
import ug.co.absa.microsrvc.gateway.repository.RequestInfoRepository;

/**
 * Spring Data Elasticsearch repository for the {@link RequestInfo} entity.
 */
public interface RequestInfoSearchRepository
    extends ReactiveElasticsearchRepository<RequestInfo, Long>, RequestInfoSearchRepositoryInternal {}

interface RequestInfoSearchRepositoryInternal {
    Flux<RequestInfo> search(String query);

    Flux<RequestInfo> search(Query query);
}

class RequestInfoSearchRepositoryInternalImpl implements RequestInfoSearchRepositoryInternal {

    private final ReactiveElasticsearchTemplate reactiveElasticsearchTemplate;

    RequestInfoSearchRepositoryInternalImpl(ReactiveElasticsearchTemplate reactiveElasticsearchTemplate) {
        this.reactiveElasticsearchTemplate = reactiveElasticsearchTemplate;
    }

    @Override
    public Flux<RequestInfo> search(String query) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
        return search(nativeSearchQuery);
    }

    @Override
    public Flux<RequestInfo> search(Query query) {
        return reactiveElasticsearchTemplate.search(query, RequestInfo.class).map(SearchHit::getContent);
    }
}
