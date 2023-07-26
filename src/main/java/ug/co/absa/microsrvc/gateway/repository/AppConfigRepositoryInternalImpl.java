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
import org.springframework.data.relational.core.sql.SelectBuilder.SelectFromAndJoinCondition;
import org.springframework.data.relational.core.sql.Table;
import org.springframework.data.relational.repository.support.MappingRelationalEntityInformation;
import org.springframework.r2dbc.core.DatabaseClient;
import org.springframework.r2dbc.core.RowsFetchSpec;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ug.co.absa.microsrvc.gateway.domain.AppConfig;
import ug.co.absa.microsrvc.gateway.repository.rowmapper.AppConfigRowMapper;
import ug.co.absa.microsrvc.gateway.repository.rowmapper.AppsRowMapper;

/**
 * Spring Data R2DBC custom repository implementation for the AppConfig entity.
 */
@SuppressWarnings("unused")
class AppConfigRepositoryInternalImpl extends SimpleR2dbcRepository<AppConfig, Long> implements AppConfigRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final AppsRowMapper appsMapper;
    private final AppConfigRowMapper appconfigMapper;

    private static final Table entityTable = Table.aliased("app_config", EntityManager.ENTITY_ALIAS);
    private static final Table appsTable = Table.aliased("apps", "apps");

    public AppConfigRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        AppsRowMapper appsMapper,
        AppConfigRowMapper appconfigMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(AppConfig.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.appsMapper = appsMapper;
        this.appconfigMapper = appconfigMapper;
    }

    @Override
    public Flux<AppConfig> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<AppConfig> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = AppConfigSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(AppsSqlHelper.getColumns(appsTable, "apps"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(appsTable)
            .on(Column.create("apps_id", entityTable))
            .equals(Column.create("id", appsTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, AppConfig.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<AppConfig> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<AppConfig> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private AppConfig process(Row row, RowMetadata metadata) {
        AppConfig entity = appconfigMapper.apply(row, "e");
        entity.setApps(appsMapper.apply(row, "apps"));
        return entity;
    }

    @Override
    public <S extends AppConfig> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
