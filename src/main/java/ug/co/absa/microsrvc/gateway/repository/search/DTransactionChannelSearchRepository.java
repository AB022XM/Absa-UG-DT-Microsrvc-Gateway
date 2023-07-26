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
import ug.co.absa.microsrvc.gateway.domain.DTransactionChannel;
import ug.co.absa.microsrvc.gateway.repository.DTransactionChannelRepository;

/**
 * Spring Data Elasticsearch repository for the {@link DTransactionChannel} entity.
 */
public interface DTransactionChannelSearchRepository
    extends ReactiveElasticsearchRepository<DTransactionChannel, Long>, DTransactionChannelSearchRepositoryInternal {}

interface DTransactionChannelSearchRepositoryInternal {
    Flux<DTransactionChannel> search(String query);

    Flux<DTransactionChannel> search(Query query);
}

class DTransactionChannelSearchRepositoryInternalImpl implements DTransactionChannelSearchRepositoryInternal {

    private final ReactiveElasticsearchTemplate reactiveElasticsearchTemplate;

    DTransactionChannelSearchRepositoryInternalImpl(ReactiveElasticsearchTemplate reactiveElasticsearchTemplate) {
        this.reactiveElasticsearchTemplate = reactiveElasticsearchTemplate;
    }

    @Override
    public Flux<DTransactionChannel> search(String query) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
        return search(nativeSearchQuery);
    }

    @Override
    public Flux<DTransactionChannel> search(Query query) {
        return reactiveElasticsearchTemplate.search(query, DTransactionChannel.class).map(SearchHit::getContent);
    }
}
