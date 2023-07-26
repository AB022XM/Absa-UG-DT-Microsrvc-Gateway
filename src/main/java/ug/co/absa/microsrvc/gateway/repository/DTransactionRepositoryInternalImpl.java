package ug.co.absa.microsrvc.gateway.repository;

import static org.springframework.data.relational.core.query.Criteria.where;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import java.math.BigDecimal;
import java.time.Instant;
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
import ug.co.absa.microsrvc.gateway.domain.DTransaction;
import ug.co.absa.microsrvc.gateway.domain.enumeration.Channel;
import ug.co.absa.microsrvc.gateway.domain.enumeration.ErrorCodes;
import ug.co.absa.microsrvc.gateway.domain.enumeration.ErrorMessage;
import ug.co.absa.microsrvc.gateway.domain.enumeration.TranStatus;
import ug.co.absa.microsrvc.gateway.repository.rowmapper.CustomerRowMapper;
import ug.co.absa.microsrvc.gateway.repository.rowmapper.DTransactionDetailsRowMapper;
import ug.co.absa.microsrvc.gateway.repository.rowmapper.DTransactionHistoryRowMapper;
import ug.co.absa.microsrvc.gateway.repository.rowmapper.DTransactionRowMapper;
import ug.co.absa.microsrvc.gateway.repository.rowmapper.DTransactionSummaryRowMapper;

/**
 * Spring Data R2DBC custom repository implementation for the DTransaction entity.
 */
@SuppressWarnings("unused")
class DTransactionRepositoryInternalImpl extends SimpleR2dbcRepository<DTransaction, Long> implements DTransactionRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final DTransactionHistoryRowMapper dtransactionhistoryMapper;
    private final DTransactionSummaryRowMapper dtransactionsummaryMapper;
    private final DTransactionDetailsRowMapper dtransactiondetailsMapper;
    private final CustomerRowMapper customerMapper;
    private final DTransactionRowMapper dtransactionMapper;

    private static final Table entityTable = Table.aliased("d_transaction", EntityManager.ENTITY_ALIAS);
    private static final Table transactionTable = Table.aliased("d_transaction_history", "e_transaction");
    private static final Table transactionTable = Table.aliased("d_transaction_summary", "e_transaction");
    private static final Table transactionTable = Table.aliased("d_transaction_details", "e_transaction");
    private static final Table customerTable = Table.aliased("customer", "customer");

    public DTransactionRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        DTransactionHistoryRowMapper dtransactionhistoryMapper,
        DTransactionSummaryRowMapper dtransactionsummaryMapper,
        DTransactionDetailsRowMapper dtransactiondetailsMapper,
        CustomerRowMapper customerMapper,
        DTransactionRowMapper dtransactionMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(DTransaction.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.dtransactionhistoryMapper = dtransactionhistoryMapper;
        this.dtransactionsummaryMapper = dtransactionsummaryMapper;
        this.dtransactiondetailsMapper = dtransactiondetailsMapper;
        this.customerMapper = customerMapper;
        this.dtransactionMapper = dtransactionMapper;
    }

    @Override
    public Flux<DTransaction> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<DTransaction> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = DTransactionSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(DTransactionHistorySqlHelper.getColumns(transactionTable, "transaction"));
        columns.addAll(DTransactionSummarySqlHelper.getColumns(transactionTable, "transaction"));
        columns.addAll(DTransactionDetailsSqlHelper.getColumns(transactionTable, "transaction"));
        columns.addAll(CustomerSqlHelper.getColumns(customerTable, "customer"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(transactionTable)
            .on(Column.create("transaction_id", entityTable))
            .equals(Column.create("id", transactionTable))
            .leftOuterJoin(transactionTable)
            .on(Column.create("transaction_id", entityTable))
            .equals(Column.create("id", transactionTable))
            .leftOuterJoin(transactionTable)
            .on(Column.create("transaction_id", entityTable))
            .equals(Column.create("id", transactionTable))
            .leftOuterJoin(customerTable)
            .on(Column.create("customer_id", entityTable))
            .equals(Column.create("id", customerTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, DTransaction.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<DTransaction> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<DTransaction> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private DTransaction process(Row row, RowMetadata metadata) {
        DTransaction entity = dtransactionMapper.apply(row, "e");
        entity.setTransaction(dtransactionhistoryMapper.apply(row, "transaction"));
        entity.setTransaction(dtransactionsummaryMapper.apply(row, "transaction"));
        entity.setTransaction(dtransactiondetailsMapper.apply(row, "transaction"));
        entity.setCustomer(customerMapper.apply(row, "customer"));
        return entity;
    }

    @Override
    public <S extends DTransaction> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
