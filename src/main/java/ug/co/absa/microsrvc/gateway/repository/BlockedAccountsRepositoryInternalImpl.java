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
import ug.co.absa.microsrvc.gateway.domain.BlockedAccounts;
import ug.co.absa.microsrvc.gateway.domain.enumeration.RecordStatus;
import ug.co.absa.microsrvc.gateway.repository.rowmapper.BlockedAccountsRowMapper;

/**
 * Spring Data R2DBC custom repository implementation for the BlockedAccounts entity.
 */
@SuppressWarnings("unused")
class BlockedAccountsRepositoryInternalImpl
    extends SimpleR2dbcRepository<BlockedAccounts, Long>
    implements BlockedAccountsRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final BlockedAccountsRowMapper blockedaccountsMapper;

    private static final Table entityTable = Table.aliased("blocked_accounts", EntityManager.ENTITY_ALIAS);

    public BlockedAccountsRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        BlockedAccountsRowMapper blockedaccountsMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(BlockedAccounts.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.blockedaccountsMapper = blockedaccountsMapper;
    }

    @Override
    public Flux<BlockedAccounts> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<BlockedAccounts> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = BlockedAccountsSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        SelectFromAndJoin selectFrom = Select.builder().select(columns).from(entityTable);
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, BlockedAccounts.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<BlockedAccounts> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<BlockedAccounts> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private BlockedAccounts process(Row row, RowMetadata metadata) {
        BlockedAccounts entity = blockedaccountsMapper.apply(row, "e");
        return entity;
    }

    @Override
    public <S extends BlockedAccounts> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
