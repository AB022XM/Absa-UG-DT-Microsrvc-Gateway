package ug.co.absa.microsrvc.gateway.repository;

import static org.springframework.data.relational.core.query.Criteria.where;

import io.r2dbc.spi.Row;
import io.r2dbc.spi.RowMetadata;
import java.math.BigDecimal;
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
import ug.co.absa.microsrvc.gateway.domain.AmolDTransactions;
import ug.co.absa.microsrvc.gateway.domain.enumeration.Channel;
import ug.co.absa.microsrvc.gateway.domain.enumeration.Currency;
import ug.co.absa.microsrvc.gateway.domain.enumeration.TranStatus;
import ug.co.absa.microsrvc.gateway.repository.rowmapper.AmolDTransactionsRowMapper;
import ug.co.absa.microsrvc.gateway.repository.rowmapper.DTransactionRowMapper;

/**
 * Spring Data R2DBC custom repository implementation for the AmolDTransactions entity.
 */
@SuppressWarnings("unused")
class AmolDTransactionsRepositoryInternalImpl
    extends SimpleR2dbcRepository<AmolDTransactions, Long>
    implements AmolDTransactionsRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final DTransactionRowMapper dtransactionMapper;
    private final AmolDTransactionsRowMapper amoldtransactionsMapper;

    private static final Table entityTable = Table.aliased("amol_d_transactions", EntityManager.ENTITY_ALIAS);
    private static final Table dTransactionTable = Table.aliased("d_transaction", "dTransaction");

    public AmolDTransactionsRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        DTransactionRowMapper dtransactionMapper,
        AmolDTransactionsRowMapper amoldtransactionsMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(AmolDTransactions.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.dtransactionMapper = dtransactionMapper;
        this.amoldtransactionsMapper = amoldtransactionsMapper;
    }

    @Override
    public Flux<AmolDTransactions> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<AmolDTransactions> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = AmolDTransactionsSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(DTransactionSqlHelper.getColumns(dTransactionTable, "dTransaction"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(dTransactionTable)
            .on(Column.create("d_transaction_id", entityTable))
            .equals(Column.create("id", dTransactionTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, AmolDTransactions.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<AmolDTransactions> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<AmolDTransactions> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private AmolDTransactions process(Row row, RowMetadata metadata) {
        AmolDTransactions entity = amoldtransactionsMapper.apply(row, "e");
        entity.setDTransaction(dtransactionMapper.apply(row, "dTransaction"));
        return entity;
    }

    @Override
    public <S extends AmolDTransactions> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
