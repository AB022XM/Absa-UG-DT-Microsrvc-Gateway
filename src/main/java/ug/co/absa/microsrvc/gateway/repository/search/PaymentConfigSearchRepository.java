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
import ug.co.absa.microsrvc.gateway.domain.PaymentConfig;
import ug.co.absa.microsrvc.gateway.repository.PaymentConfigRepository;

/**
 * Spring Data Elasticsearch repository for the {@link PaymentConfig} entity.
 */
public interface PaymentConfigSearchRepository
    extends ReactiveElasticsearchRepository<PaymentConfig, Long>, PaymentConfigSearchRepositoryInternal {}

interface PaymentConfigSearchRepositoryInternal {
    Flux<PaymentConfig> search(String query);

    Flux<PaymentConfig> search(Query query);
}

class PaymentConfigSearchRepositoryInternalImpl implements PaymentConfigSearchRepositoryInternal {

    private final ReactiveElasticsearchTemplate reactiveElasticsearchTemplate;

    PaymentConfigSearchRepositoryInternalImpl(ReactiveElasticsearchTemplate reactiveElasticsearchTemplate) {
        this.reactiveElasticsearchTemplate = reactiveElasticsearchTemplate;
    }

    @Override
    public Flux<PaymentConfig> search(String query) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
        return search(nativeSearchQuery);
    }

    @Override
    public Flux<PaymentConfig> search(Query query) {
        return reactiveElasticsearchTemplate.search(query, PaymentConfig.class).map(SearchHit::getContent);
    }
}
