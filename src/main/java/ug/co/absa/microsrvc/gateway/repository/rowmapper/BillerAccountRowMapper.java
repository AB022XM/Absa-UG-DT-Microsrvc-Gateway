package ug.co.absa.microsrvc.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;
import ug.co.absa.microsrvc.gateway.domain.BillerAccount;

/**
 * Converter between {@link Row} to {@link BillerAccount}, with proper type conversions.
 */
@Service
public class BillerAccountRowMapper implements BiFunction<Row, String, BillerAccount> {

    private final ColumnConverter converter;

    public BillerAccountRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link BillerAccount} stored in the database.
     */
    @Override
    public BillerAccount apply(Row row, String prefix) {
        BillerAccount entity = new BillerAccount();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setAbsaTranRef(converter.fromRow(row, prefix + "_absa_tran_ref", UUID.class));
        entity.setRecordId(converter.fromRow(row, prefix + "_record_id", String.class));
        entity.setBillerId(converter.fromRow(row, prefix + "_biller_id", String.class));
        entity.setBillerName(converter.fromRow(row, prefix + "_biller_name", String.class));
        entity.setBillerAccNumber(converter.fromRow(row, prefix + "_biller_acc_number", String.class));
        entity.setBillerAccountDescription(converter.fromRow(row, prefix + "_biller_account_description", String.class));
        entity.setTimestamp(converter.fromRow(row, prefix + "_timestamp", ZonedDateTime.class));
        entity.setBillerFreeField1(converter.fromRow(row, prefix + "_biller_free_field_1", String.class));
        entity.setBillerFreeField2(converter.fromRow(row, prefix + "_biller_free_field_2", String.class));
        entity.setBillerFreeField3(converter.fromRow(row, prefix + "_biller_free_field_3", String.class));
        entity.setBillerFreeField4(converter.fromRow(row, prefix + "_biller_free_field_4", String.class));
        entity.setBillerFreeField5(converter.fromRow(row, prefix + "_biller_free_field_5", String.class));
        entity.setBillerFreeField6(converter.fromRow(row, prefix + "_biller_free_field_6", String.class));
        entity.setBillerFreeField7(converter.fromRow(row, prefix + "_biller_free_field_7", String.class));
        entity.setBillerFreeField8(converter.fromRow(row, prefix + "_biller_free_field_8", String.class));
        entity.setBillerFreeField9(converter.fromRow(row, prefix + "_biller_free_field_9", String.class));
        entity.setBillerFreeField10(converter.fromRow(row, prefix + "_biller_free_field_10", String.class));
        entity.setBillerFreeField11(converter.fromRow(row, prefix + "_biller_free_field_11", String.class));
        entity.setBillerFreeField12(converter.fromRow(row, prefix + "_biller_free_field_12", String.class));
        entity.setBillerFreeField13(converter.fromRow(row, prefix + "_biller_free_field_13", String.class));
        entity.setDelflg(converter.fromRow(row, prefix + "_delflg", Boolean.class));
        entity.setCreatedAt(converter.fromRow(row, prefix + "_created_at", ZonedDateTime.class));
        entity.setCreatedBy(converter.fromRow(row, prefix + "_created_by", String.class));
        entity.setUpdatedAt(converter.fromRow(row, prefix + "_updated_at", ZonedDateTime.class));
        entity.setUpdatedBy(converter.fromRow(row, prefix + "_updated_by", String.class));
        entity.setBillerId(converter.fromRow(row, prefix + "_biller_id", Long.class));
        return entity;
    }
}
