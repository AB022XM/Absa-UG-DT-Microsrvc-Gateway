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
import ug.co.absa.microsrvc.gateway.domain.PaymentItems;
import ug.co.absa.microsrvc.gateway.repository.rowmapper.BillerRowMapper;
import ug.co.absa.microsrvc.gateway.repository.rowmapper.PaymentItemsRowMapper;

/**
 * Spring Data R2DBC custom repository implementation for the PaymentItems entity.
 */
@SuppressWarnings("unused")
class PaymentItemsRepositoryInternalImpl extends SimpleR2dbcRepository<PaymentItems, Long> implements PaymentItemsRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final BillerRowMapper billerMapper;
    private final PaymentItemsRowMapper paymentitemsMapper;

    private static final Table entityTable = Table.aliased("payment_items", EntityManager.ENTITY_ALIAS);
    private static final Table billerTable = Table.aliased("biller", "biller");

    public PaymentItemsRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        BillerRowMapper billerMapper,
        PaymentItemsRowMapper paymentitemsMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(PaymentItems.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.billerMapper = billerMapper;
        this.paymentitemsMapper = paymentitemsMapper;
    }

    @Override
    public Flux<PaymentItems> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<PaymentItems> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = PaymentItemsSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(BillerSqlHelper.getColumns(billerTable, "biller"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(billerTable)
            .on(Column.create("biller_id", entityTable))
            .equals(Column.create("id", billerTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, PaymentItems.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<PaymentItems> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<PaymentItems> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private PaymentItems process(Row row, RowMetadata metadata) {
        PaymentItems entity = paymentitemsMapper.apply(row, "e");
        entity.setBiller(billerMapper.apply(row, "biller"));
        return entity;
    }

    @Override
    public <S extends PaymentItems> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
