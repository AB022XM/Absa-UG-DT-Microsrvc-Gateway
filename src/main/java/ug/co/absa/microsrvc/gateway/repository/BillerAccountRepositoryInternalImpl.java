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
import ug.co.absa.microsrvc.gateway.domain.BillerAccount;
import ug.co.absa.microsrvc.gateway.repository.rowmapper.BillerAccountRowMapper;
import ug.co.absa.microsrvc.gateway.repository.rowmapper.BillerRowMapper;

/**
 * Spring Data R2DBC custom repository implementation for the BillerAccount entity.
 */
@SuppressWarnings("unused")
class BillerAccountRepositoryInternalImpl extends SimpleR2dbcRepository<BillerAccount, Long> implements BillerAccountRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final BillerRowMapper billerMapper;
    private final BillerAccountRowMapper billeraccountMapper;

    private static final Table entityTable = Table.aliased("biller_account", EntityManager.ENTITY_ALIAS);
    private static final Table billerTable = Table.aliased("biller", "biller");

    public BillerAccountRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        BillerRowMapper billerMapper,
        BillerAccountRowMapper billeraccountMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(BillerAccount.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.billerMapper = billerMapper;
        this.billeraccountMapper = billeraccountMapper;
    }

    @Override
    public Flux<BillerAccount> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<BillerAccount> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = BillerAccountSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(BillerSqlHelper.getColumns(billerTable, "biller"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(billerTable)
            .on(Column.create("biller_id", entityTable))
            .equals(Column.create("id", billerTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, BillerAccount.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<BillerAccount> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<BillerAccount> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private BillerAccount process(Row row, RowMetadata metadata) {
        BillerAccount entity = billeraccountMapper.apply(row, "e");
        entity.setBiller(billerMapper.apply(row, "biller"));
        return entity;
    }

    @Override
    public <S extends BillerAccount> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
