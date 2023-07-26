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
import ug.co.absa.microsrvc.gateway.domain.Branch;
import ug.co.absa.microsrvc.gateway.repository.BranchRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Branch} entity.
 */
public interface BranchSearchRepository extends ReactiveElasticsearchRepository<Branch, Long>, BranchSearchRepositoryInternal {}

interface BranchSearchRepositoryInternal {
    Flux<Branch> search(String query);

    Flux<Branch> search(Query query);
}

class BranchSearchRepositoryInternalImpl implements BranchSearchRepositoryInternal {

    private final ReactiveElasticsearchTemplate reactiveElasticsearchTemplate;

    BranchSearchRepositoryInternalImpl(ReactiveElasticsearchTemplate reactiveElasticsearchTemplate) {
        this.reactiveElasticsearchTemplate = reactiveElasticsearchTemplate;
    }

    @Override
    public Flux<Branch> search(String query) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
        return search(nativeSearchQuery);
    }

    @Override
    public Flux<Branch> search(Query query) {
        return reactiveElasticsearchTemplate.search(query, Branch.class).map(SearchHit::getContent);
    }
}
