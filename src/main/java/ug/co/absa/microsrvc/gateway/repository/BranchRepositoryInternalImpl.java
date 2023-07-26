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
import ug.co.absa.microsrvc.gateway.domain.Branch;
import ug.co.absa.microsrvc.gateway.domain.enumeration.RecordStatus;
import ug.co.absa.microsrvc.gateway.repository.rowmapper.BankRowMapper;
import ug.co.absa.microsrvc.gateway.repository.rowmapper.BranchRowMapper;

/**
 * Spring Data R2DBC custom repository implementation for the Branch entity.
 */
@SuppressWarnings("unused")
class BranchRepositoryInternalImpl extends SimpleR2dbcRepository<Branch, Long> implements BranchRepositoryInternal {

    private final DatabaseClient db;
    private final R2dbcEntityTemplate r2dbcEntityTemplate;
    private final EntityManager entityManager;

    private final BankRowMapper bankMapper;
    private final BranchRowMapper branchMapper;

    private static final Table entityTable = Table.aliased("branch", EntityManager.ENTITY_ALIAS);
    private static final Table bankTable = Table.aliased("bank", "bank");

    public BranchRepositoryInternalImpl(
        R2dbcEntityTemplate template,
        EntityManager entityManager,
        BankRowMapper bankMapper,
        BranchRowMapper branchMapper,
        R2dbcEntityOperations entityOperations,
        R2dbcConverter converter
    ) {
        super(
            new MappingRelationalEntityInformation(converter.getMappingContext().getRequiredPersistentEntity(Branch.class)),
            entityOperations,
            converter
        );
        this.db = template.getDatabaseClient();
        this.r2dbcEntityTemplate = template;
        this.entityManager = entityManager;
        this.bankMapper = bankMapper;
        this.branchMapper = branchMapper;
    }

    @Override
    public Flux<Branch> findAllBy(Pageable pageable) {
        return createQuery(pageable, null).all();
    }

    RowsFetchSpec<Branch> createQuery(Pageable pageable, Condition whereClause) {
        List<Expression> columns = BranchSqlHelper.getColumns(entityTable, EntityManager.ENTITY_ALIAS);
        columns.addAll(BankSqlHelper.getColumns(bankTable, "bank"));
        SelectFromAndJoinCondition selectFrom = Select
            .builder()
            .select(columns)
            .from(entityTable)
            .leftOuterJoin(bankTable)
            .on(Column.create("bank_id", entityTable))
            .equals(Column.create("id", bankTable));
        // we do not support Criteria here for now as of https://github.com/jhipster/generator-jhipster/issues/18269
        String select = entityManager.createSelect(selectFrom, Branch.class, pageable, whereClause);
        return db.sql(select).map(this::process);
    }

    @Override
    public Flux<Branch> findAll() {
        return findAllBy(null);
    }

    @Override
    public Mono<Branch> findById(Long id) {
        Comparison whereClause = Conditions.isEqual(entityTable.column("id"), Conditions.just(id.toString()));
        return createQuery(null, whereClause).one();
    }

    private Branch process(Row row, RowMetadata metadata) {
        Branch entity = branchMapper.apply(row, "e");
        entity.setBank(bankMapper.apply(row, "bank"));
        return entity;
    }

    @Override
    public <S extends Branch> Mono<S> save(S entity) {
        return super.save(entity);
    }
}
