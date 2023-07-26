package ug.co.absa.microsrvc.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;
import ug.co.absa.microsrvc.gateway.domain.Currency;
import ug.co.absa.microsrvc.gateway.domain.enumeration.RecordStatus;

/**
 * Converter between {@link Row} to {@link Currency}, with proper type conversions.
 */
@Service
public class CurrencyRowMapper implements BiFunction<Row, String, Currency> {

    private final ColumnConverter converter;

    public CurrencyRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link Currency} stored in the database.
     */
    @Override
    public Currency apply(Row row, String prefix) {
        Currency entity = new Currency();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setAbsaTranRef(converter.fromRow(row, prefix + "_absa_tran_ref", UUID.class));
        entity.setDtDTransactionId(converter.fromRow(row, prefix + "_dt_d_transaction_id", String.class));
        entity.setAmolDTransactionId(converter.fromRow(row, prefix + "_amol_d_transaction_id", String.class));
        entity.setTransactionReferenceNumber(converter.fromRow(row, prefix + "_transaction_reference_number", String.class));
        entity.setToken(converter.fromRow(row, prefix + "_token", String.class));
        entity.setCurrencyUniqueId(converter.fromRow(row, prefix + "_currency_unique_id", String.class));
        entity.setCurrencyCode(converter.fromRow(row, prefix + "_currency_code", String.class));
        entity.setCurrencyName(converter.fromRow(row, prefix + "_currency_name", String.class));
        entity.setFreeField1(converter.fromRow(row, prefix + "_free_field_1", String.class));
        entity.setFreeField2(converter.fromRow(row, prefix + "_free_field_2", String.class));
        entity.setFreeField3(converter.fromRow(row, prefix + "_free_field_3", String.class));
        entity.setFreeField4(converter.fromRow(row, prefix + "_free_field_4", String.class));
        entity.setFreeField5(converter.fromRow(row, prefix + "_free_field_5", String.class));
        entity.setFreeField6(converter.fromRow(row, prefix + "_free_field_6", String.class));
        entity.setTimestamp(converter.fromRow(row, prefix + "_timestamp", ZonedDateTime.class));
        entity.setRecordStatus(converter.fromRow(row, prefix + "_record_status", RecordStatus.class));
        entity.setCreatedAt(converter.fromRow(row, prefix + "_created_at", ZonedDateTime.class));
        entity.setCreatedBy(converter.fromRow(row, prefix + "_created_by", String.class));
        entity.setUpdatedAt(converter.fromRow(row, prefix + "_updated_at", ZonedDateTime.class));
        entity.setUpdatedBy(converter.fromRow(row, prefix + "_updated_by", String.class));
        return entity;
    }
}
