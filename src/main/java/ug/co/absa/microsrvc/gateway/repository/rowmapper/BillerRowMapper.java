package ug.co.absa.microsrvc.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;
import ug.co.absa.microsrvc.gateway.domain.Biller;

/**
 * Converter between {@link Row} to {@link Biller}, with proper type conversions.
 */
@Service
public class BillerRowMapper implements BiFunction<Row, String, Biller> {

    private final ColumnConverter converter;

    public BillerRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link Biller} stored in the database.
     */
    @Override
    public Biller apply(Row row, String prefix) {
        Biller entity = new Biller();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setAbsaTranRef(converter.fromRow(row, prefix + "_absa_tran_ref", UUID.class));
        entity.setBillerId(converter.fromRow(row, prefix + "_biller_id", String.class));
        entity.setBillerCode(converter.fromRow(row, prefix + "_biller_code", String.class));
        entity.setBillerName(converter.fromRow(row, prefix + "_biller_name", String.class));
        entity.setBillerCategoryId(converter.fromRow(row, prefix + "_biller_category_id", String.class));
        entity.setAddressId(converter.fromRow(row, prefix + "_address_id", String.class));
        entity.setNarative(converter.fromRow(row, prefix + "_narative", String.class));
        entity.setNarative1(converter.fromRow(row, prefix + "_narative_1", String.class));
        entity.setNarative2(converter.fromRow(row, prefix + "_narative_2", String.class));
        entity.setNarative3(converter.fromRow(row, prefix + "_narative_3", String.class));
        entity.setNarative4(converter.fromRow(row, prefix + "_narative_4", String.class));
        entity.setNarative5(converter.fromRow(row, prefix + "_narative_5", String.class));
        entity.setNarative6(converter.fromRow(row, prefix + "_narative_6", String.class));
        entity.setNarative7(converter.fromRow(row, prefix + "_narative_7", String.class));
        entity.setTimestamp(converter.fromRow(row, prefix + "_timestamp", ZonedDateTime.class));
        entity.setFreeField1(converter.fromRow(row, prefix + "_free_field_1", String.class));
        entity.setFreeField2(converter.fromRow(row, prefix + "_free_field_2", String.class));
        entity.setFreeField3(converter.fromRow(row, prefix + "_free_field_3", String.class));
        entity.setFreeField4(converter.fromRow(row, prefix + "_free_field_4", String.class));
        entity.setFreeField5(converter.fromRow(row, prefix + "_free_field_5", String.class));
        entity.setFreeField6(converter.fromRow(row, prefix + "_free_field_6", String.class));
        entity.setFreeField7(converter.fromRow(row, prefix + "_free_field_7", String.class));
        entity.setFreeField8(converter.fromRow(row, prefix + "_free_field_8", String.class));
        entity.setFreeField9(converter.fromRow(row, prefix + "_free_field_9", String.class));
        entity.setFreeField10(converter.fromRow(row, prefix + "_free_field_10", String.class));
        entity.setFreeField11(converter.fromRow(row, prefix + "_free_field_11", String.class));
        entity.setFreeField12(converter.fromRow(row, prefix + "_free_field_12", String.class));
        entity.setFreeField13(converter.fromRow(row, prefix + "_free_field_13", String.class));
        entity.setDelflg(converter.fromRow(row, prefix + "_delflg", Boolean.class));
        entity.setCreatedAt(converter.fromRow(row, prefix + "_created_at", ZonedDateTime.class));
        entity.setCreatedBy(converter.fromRow(row, prefix + "_created_by", String.class));
        entity.setUpdatedAt(converter.fromRow(row, prefix + "_updated_at", ZonedDateTime.class));
        entity.setUpdatedBy(converter.fromRow(row, prefix + "_updated_by", String.class));
        entity.setBillerAccountId(converter.fromRow(row, prefix + "_biller_account_id", Long.class));
        return entity;
    }
}
