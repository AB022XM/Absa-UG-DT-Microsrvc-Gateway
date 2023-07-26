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
import ug.co.absa.microsrvc.gateway.domain.Biller;
import ug.co.absa.microsrvc.gateway.repository.BillerRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Biller} entity.
 */
public interface BillerSearchRepository extends ReactiveElasticsearchRepository<Biller, Long>, BillerSearchRepositoryInternal {}

interface BillerSearchRepositoryInternal {
    Flux<Biller> search(String query);

    Flux<Biller> search(Query query);
}

class BillerSearchRepositoryInternalImpl implements BillerSearchRepositoryInternal {

    private final ReactiveElasticsearchTemplate reactiveElasticsearchTemplate;

    BillerSearchRepositoryInternalImpl(ReactiveElasticsearchTemplate reactiveElasticsearchTemplate) {
        this.reactiveElasticsearchTemplate = reactiveElasticsearchTemplate;
    }

    @Override
    public Flux<Biller> search(String query) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
        return search(nativeSearchQuery);
    }

    @Override
    public Flux<Biller> search(Query query) {
        return reactiveElasticsearchTemplate.search(query, Biller.class).map(SearchHit::getContent);
    }
}
