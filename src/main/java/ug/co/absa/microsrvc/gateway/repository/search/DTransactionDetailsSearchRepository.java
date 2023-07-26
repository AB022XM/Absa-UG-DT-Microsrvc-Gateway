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
import ug.co.absa.microsrvc.gateway.domain.DTransactionDetails;
import ug.co.absa.microsrvc.gateway.repository.DTransactionDetailsRepository;

/**
 * Spring Data Elasticsearch repository for the {@link DTransactionDetails} entity.
 */
public interface DTransactionDetailsSearchRepository
    extends ReactiveElasticsearchRepository<DTransactionDetails, Long>, DTransactionDetailsSearchRepositoryInternal {}

interface DTransactionDetailsSearchRepositoryInternal {
    Flux<DTransactionDetails> search(String query);

    Flux<DTransactionDetails> search(Query query);
}

class DTransactionDetailsSearchRepositoryInternalImpl implements DTransactionDetailsSearchRepositoryInternal {

    private final ReactiveElasticsearchTemplate reactiveElasticsearchTemplate;

    DTransactionDetailsSearchRepositoryInternalImpl(ReactiveElasticsearchTemplate reactiveElasticsearchTemplate) {
        this.reactiveElasticsearchTemplate = reactiveElasticsearchTemplate;
    }

    @Override
    public Flux<DTransactionDetails> search(String query) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
        return search(nativeSearchQuery);
    }

    @Override
    public Flux<DTransactionDetails> search(Query query) {
        return reactiveElasticsearchTemplate.search(query, DTransactionDetails.class).map(SearchHit::getContent);
    }
}
