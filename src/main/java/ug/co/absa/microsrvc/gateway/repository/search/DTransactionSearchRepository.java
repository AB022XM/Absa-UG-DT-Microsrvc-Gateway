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
import ug.co.absa.microsrvc.gateway.domain.DTransaction;
import ug.co.absa.microsrvc.gateway.repository.DTransactionRepository;

/**
 * Spring Data Elasticsearch repository for the {@link DTransaction} entity.
 */
public interface DTransactionSearchRepository
    extends ReactiveElasticsearchRepository<DTransaction, Long>, DTransactionSearchRepositoryInternal {}

interface DTransactionSearchRepositoryInternal {
    Flux<DTransaction> search(String query);

    Flux<DTransaction> search(Query query);
}

class DTransactionSearchRepositoryInternalImpl implements DTransactionSearchRepositoryInternal {

    private final ReactiveElasticsearchTemplate reactiveElasticsearchTemplate;

    DTransactionSearchRepositoryInternalImpl(ReactiveElasticsearchTemplate reactiveElasticsearchTemplate) {
        this.reactiveElasticsearchTemplate = reactiveElasticsearchTemplate;
    }

    @Override
    public Flux<DTransaction> search(String query) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
        return search(nativeSearchQuery);
    }

    @Override
    public Flux<DTransaction> search(Query query) {
        return reactiveElasticsearchTemplate.search(query, DTransaction.class).map(SearchHit::getContent);
    }
}
