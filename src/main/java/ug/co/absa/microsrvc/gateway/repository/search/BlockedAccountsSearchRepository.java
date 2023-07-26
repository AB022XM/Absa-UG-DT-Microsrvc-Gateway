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
import ug.co.absa.microsrvc.gateway.domain.BlockedAccounts;
import ug.co.absa.microsrvc.gateway.repository.BlockedAccountsRepository;

/**
 * Spring Data Elasticsearch repository for the {@link BlockedAccounts} entity.
 */
public interface BlockedAccountsSearchRepository
    extends ReactiveElasticsearchRepository<BlockedAccounts, Long>, BlockedAccountsSearchRepositoryInternal {}

interface BlockedAccountsSearchRepositoryInternal {
    Flux<BlockedAccounts> search(String query);

    Flux<BlockedAccounts> search(Query query);
}

class BlockedAccountsSearchRepositoryInternalImpl implements BlockedAccountsSearchRepositoryInternal {

    private final ReactiveElasticsearchTemplate reactiveElasticsearchTemplate;

    BlockedAccountsSearchRepositoryInternalImpl(ReactiveElasticsearchTemplate reactiveElasticsearchTemplate) {
        this.reactiveElasticsearchTemplate = reactiveElasticsearchTemplate;
    }

    @Override
    public Flux<BlockedAccounts> search(String query) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
        return search(nativeSearchQuery);
    }

    @Override
    public Flux<BlockedAccounts> search(Query query) {
        return reactiveElasticsearchTemplate.search(query, BlockedAccounts.class).map(SearchHit::getContent);
    }
}
