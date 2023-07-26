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
import ug.co.absa.microsrvc.gateway.domain.Biller;
import ug.co.absa.microsrvc.gateway.repository.rowmapper.BillerAccountRowMapper;
import ug.co.absa.microsrvc.gateway.repository.rowmapper.BillerRowMapper;

/**
 * Spring Data R2DBC custom repository implementation for the Biller entity.
 */
@SuppressWarnings("unused")
class BillerRepositoryInternalImpl extends SimpleR2dbcRepository<Biller, Long> implements BillerRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final BillerAccountRowMapper billeraccountMapper;
    private final BillerRowMapper billerMapper;

    private static final Table entityTable = Table.aliased("biller", EntityManager.ENTITY_ALIAS);
    private static final Table billerAccountTable = Table.aliased("biller_account", "billerAccount");

    public BillerRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        BillerAccountRowMapper billeraccountMapper,
        BillerRowMapper billerMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(Biller.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.billeraccountMapper = billeraccountMapper;
        this.billerMapper = billerMapper;
    }

    @Override
    public Flux<Biller> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<Biller> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = BillerSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(BillerAccountSqlHelper.getColumns(billerAccountTable, "billerAccount"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(billerAccountTable)
            .on(Column.create("biller_account_id", entityTable))
            .equals(Column.create("id", billerAccountTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, Biller.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<Biller> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<Biller> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private Biller process(Row row, RowMetadata metadata) {
        Biller entity = billerMapper.apply(row, "e");
        entity.setBillerAccount(billeraccountMapper.apply(row, "billerAccount"));
        return entity;
    }

    @Override
    public <S extends Biller> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
