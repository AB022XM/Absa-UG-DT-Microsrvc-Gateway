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
import ug.co.absa.microsrvc.gateway.domain.Devices;
import ug.co.absa.microsrvc.gateway.repository.DevicesRepository;

/**
 * Spring Data Elasticsearch repository for the {@link Devices} entity.
 */
public interface DevicesSearchRepository extends ReactiveElasticsearchRepository<Devices, Long>, DevicesSearchRepositoryInternal {}

interface DevicesSearchRepositoryInternal {
    Flux<Devices> search(String query);

    Flux<Devices> search(Query query);
}

class DevicesSearchRepositoryInternalImpl implements DevicesSearchRepositoryInternal {

    private final ReactiveElasticsearchTemplate reactiveElasticsearchTemplate;

    DevicesSearchRepositoryInternalImpl(ReactiveElasticsearchTemplate reactiveElasticsearchTemplate) {
        this.reactiveElasticsearchTemplate = reactiveElasticsearchTemplate;
    }

    @Override
    public Flux<Devices> search(String query) {
        NativeSearchQuery nativeSearchQuery = new NativeSearchQuery(queryStringQuery(query));
        return search(nativeSearchQuery);
    }

    @Override
    public Flux<Devices> search(Query query) {
        return reactiveElasticsearchTemplate.search(query, Devices.class).map(SearchHit::getContent);
    }
}
