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
import ug.co.absa.microsrvc.gateway.domain.ErrorCodes;
import ug.co.absa.microsrvc.gateway.repository.ErrorCodesRepository;

/**
 * Spring Data Elasticsearch repository for the {@link ErrorCodes} entity.
 */
public interface ErrorCodesSearchRepository extends ReactiveElasticsearchRepository<ErrorCodes, Long>, ErrorCodesSearchRepositoryInternal {}

interface ErrorCodesSearchRepositoryInternal {
    Flux<ErrorCodes> search(String query);

    Flux<ErrorCodes> search(Query query);
}

class ErrorCodesSearchRepositoryInternalImpl implements ErrorCodesSearchRepositoryInternal {

    private final ReactiveElasticsearchTemplate reactiveElasticsearchTemplate;

    ErrorCodesSearchRepositoryInternalImpl(ReactiveElasticsearchTemplate reactiveElasticsearchTemplate) {
        this.reactiveElasticsearchTemplate = reactiveElasticsearchTemplate;
    }

    @Override
    public Flux<ErrorCodes> search(String query) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
        return search(nativeSearchQuery);
    }

    @Override
    public Flux<ErrorCodes> search(Query query) {
        return reactiveElasticsearchTemplate.search(query, ErrorCodes.class).map(SearchHit::getContent);
    }
}
