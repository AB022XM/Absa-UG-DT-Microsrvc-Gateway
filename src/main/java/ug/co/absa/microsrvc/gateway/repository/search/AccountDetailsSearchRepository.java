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
import ug.co.absa.microsrvc.gateway.domain.AccountDetails;
import ug.co.absa.microsrvc.gateway.repository.AccountDetailsRepository;

/**
 * Spring Data Elasticsearch repository for the {@link AccountDetails} entity.
 */
public interface AccountDetailsSearchRepository
    extends ReactiveElasticsearchRepository<AccountDetails, Long>, AccountDetailsSearchRepositoryInternal {}

interface AccountDetailsSearchRepositoryInternal {
    Flux<AccountDetails> search(String query);

    Flux<AccountDetails> search(Query query);
}

class AccountDetailsSearchRepositoryInternalImpl implements AccountDetailsSearchRepositoryInternal {

    private final ReactiveElasticsearchTemplate reactiveElasticsearchTemplate;

    AccountDetailsSearchRepositoryInternalImpl(ReactiveElasticsearchTemplate reactiveElasticsearchTemplate) {
        this.reactiveElasticsearchTemplate = reactiveElasticsearchTemplate;
    }

    @Override
    public Flux<AccountDetails> search(String query) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
        return search(nativeSearchQuery);
    }

    @Override
    public Flux<AccountDetails> search(Query query) {
        return reactiveElasticsearchTemplate.search(query, AccountDetails.class).map(SearchHit::getContent);
    }
}
