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
import ug.co.absa.microsrvc.gateway.domain.PaymentItems;
import ug.co.absa.microsrvc.gateway.repository.PaymentItemsRepository;

/**
 * Spring Data Elasticsearch repository for the {@link PaymentItems} entity.
 */
public interface PaymentItemsSearchRepository
    extends ReactiveElasticsearchRepository<PaymentItems, Long>, PaymentItemsSearchRepositoryInternal {}

interface PaymentItemsSearchRepositoryInternal {
    Flux<PaymentItems> search(String query);

    Flux<PaymentItems> search(Query query);
}

class PaymentItemsSearchRepositoryInternalImpl implements PaymentItemsSearchRepositoryInternal {

    private final ReactiveElasticsearchTemplate reactiveElasticsearchTemplate;

    PaymentItemsSearchRepositoryInternalImpl(ReactiveElasticsearchTemplate reactiveElasticsearchTemplate) {
        this.reactiveElasticsearchTemplate = reactiveElasticsearchTemplate;
    }

    @Override
    public Flux<PaymentItems> search(String query) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
        return search(nativeSearchQuery);
    }

    @Override
    public Flux<PaymentItems> search(Query query) {
        return reactiveElasticsearchTemplate.search(query, PaymentItems.class).map(SearchHit::getContent);
    }
}
