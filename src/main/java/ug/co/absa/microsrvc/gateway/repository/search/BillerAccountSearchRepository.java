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
import ug.co.absa.microsrvc.gateway.domain.BillerAccount;
import ug.co.absa.microsrvc.gateway.repository.BillerAccountRepository;

/**
 * Spring Data Elasticsearch repository for the {@link BillerAccount} entity.
 */
public interface BillerAccountSearchRepository
    extends ReactiveElasticsearchRepository<BillerAccount, Long>, BillerAccountSearchRepositoryInternal {}

interface BillerAccountSearchRepositoryInternal {
    Flux<BillerAccount> search(String query);

    Flux<BillerAccount> search(Query query);
}

class BillerAccountSearchRepositoryInternalImpl implements BillerAccountSearchRepositoryInternal {

    private final ReactiveElasticsearchTemplate reactiveElasticsearchTemplate;

    BillerAccountSearchRepositoryInternalImpl(ReactiveElasticsearchTemplate reactiveElasticsearchTemplate) {
        this.reactiveElasticsearchTemplate = reactiveElasticsearchTemplate;
    }

    @Override
    public Flux<BillerAccount> search(String query) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
        return search(nativeSearchQuery);
    }

    @Override
    public Flux<BillerAccount> search(Query query) {
        return reactiveElasticsearchTemplate.search(query, BillerAccount.class).map(SearchHit::getContent);
    }
}
