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
import ug.co.absa.microsrvc.gateway.domain.ResponseInfo;
import ug.co.absa.microsrvc.gateway.repository.ResponseInfoRepository;

/**
 * Spring Data Elasticsearch repository for the {@link ResponseInfo} entity.
 */
public interface ResponseInfoSearchRepository
    extends ReactiveElasticsearchRepository<ResponseInfo, Long>, ResponseInfoSearchRepositoryInternal {}

interface ResponseInfoSearchRepositoryInternal {
    Flux<ResponseInfo> search(String query);

    Flux<ResponseInfo> search(Query query);
}

class ResponseInfoSearchRepositoryInternalImpl implements ResponseInfoSearchRepositoryInternal {

    private final ReactiveElasticsearchTemplate reactiveElasticsearchTemplate;

    ResponseInfoSearchRepositoryInternalImpl(ReactiveElasticsearchTemplate reactiveElasticsearchTemplate) {
        this.reactiveElasticsearchTemplate = reactiveElasticsearchTemplate;
    }

    @Override
    public Flux<ResponseInfo> search(String query) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
        return search(nativeSearchQuery);
    }

    @Override
    public Flux<ResponseInfo> search(Query query) {
        return reactiveElasticsearchTemplate.search(query, ResponseInfo.class).map(SearchHit::getContent);
    }
}
