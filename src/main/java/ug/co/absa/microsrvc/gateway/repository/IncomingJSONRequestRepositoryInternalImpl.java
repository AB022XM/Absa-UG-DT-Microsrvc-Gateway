package ug.co.absa.microsrvc.gateway.repository;

import static org.springframework.data.relational.core.query.Criteria.where;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.UUID;
import java.util.function.BiFunction;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Pageable;
import org.springframework.data.r2dbc.convert.R2dbcConverter;
import org.springframework.data.r2dbc.core.R2dbcEntityOperations;
import org.springframework.data.r2dbc.core.R2dbcEntityTemplate;
import org.springframework.data.r2dbc.repository.support.SimpleR2dbcRepository;
import org.springframework.data.relational.core.query.Criteria;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Comparison;
import org.springframework.data.relational.core.sql.Condition;
import org.springframework.data.relational.core.sql.Conditions;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Select;
import org.springframework.data.relational.core.sql.SelectBuilder.SelectFromAndJoin;
import org.springframework.data.relational.core.sql.Table;
import org.springframework.data.relational.repository.support.MappingRelationalEntityInformation;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.r2dbc.core.RowsFetchSpec;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ug.co.absa.microsrvc.gateway.domain.IncomingJSONRequest;
import ug.co.absa.microsrvc.gateway.repository.rowmapper.IncomingJSONRequestRowMapper;

/**
 * Spring Data R2DBC custom repository implementation for the IncomingJSONRequest entity.
 */
@SuppressWarnings("unused")
class IncomingJSONRequestRepositoryInternalImpl
    extends SimpleR2dbcRepository<IncomingJSONRequest, Long>
    implements IncomingJSONRequestRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final IncomingJSONRequestRowMapper incomingjsonrequestMapper;

    private static final Table entityTable = Table.aliased("incoming_json_request", EntityManager.ENTITY_ALIAS);

    public IncomingJSONRequestRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        IncomingJSONRequestRowMapper incomingjsonrequestMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(IncomingJSONRequest.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.incomingjsonrequestMapper = incomingjsonrequestMapper;
    }

    @Override
    public Flux<IncomingJSONRequest> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<IncomingJSONRequest> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = IncomingJSONRequestSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        SelectFromAndJoin selectFrom = Select.builder().select(columns).from(entityTable);
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, IncomingJSONRequest.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<IncomingJSONRequest> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<IncomingJSONRequest> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private IncomingJSONRequest process(Row row, RowMetadata metadata) {
        IncomingJSONRequest entity = incomingjsonrequestMapper.apply(row, "e");
        return entity;
    }

    @Override
    public <S extends IncomingJSONRequest> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
