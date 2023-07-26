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
import ug.co.absa.microsrvc.gateway.domain.Liquidation;
import ug.co.absa.microsrvc.gateway.repository.LiquidationRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Liquidation} entity.
 */
public interface LiquidationSearchRepository
    extends ReactiveElasticsearchRepository<Liquidation, Long>, LiquidationSearchRepositoryInternal {}

interface LiquidationSearchRepositoryInternal {
    Flux<Liquidation> search(String query);

    Flux<Liquidation> search(Query query);
}

class LiquidationSearchRepositoryInternalImpl implements LiquidationSearchRepositoryInternal {

    private final ReactiveElasticsearchTemplate reactiveElasticsearchTemplate;

    LiquidationSearchRepositoryInternalImpl(ReactiveElasticsearchTemplate reactiveElasticsearchTemplate) {
        this.reactiveElasticsearchTemplate = reactiveElasticsearchTemplate;
    }

    @Override
    public Flux<Liquidation> search(String query) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
        return search(nativeSearchQuery);
    }

    @Override
    public Flux<Liquidation> search(Query query) {
        return reactiveElasticsearchTemplate.search(query, Liquidation.class).map(SearchHit::getContent);
    }
}
