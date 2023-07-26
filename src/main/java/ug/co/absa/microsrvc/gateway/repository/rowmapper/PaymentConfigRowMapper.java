package ug.co.absa.microsrvc.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.time.ZonedDateTime;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;
import ug.co.absa.microsrvc.gateway.domain.PaymentConfig;
import ug.co.absa.microsrvc.gateway.domain.enumeration.ModeOfPayment;

/**
 * Converter between {@link Row} to {@link PaymentConfig}, with proper type conversions.
 */
@Service
public class PaymentConfigRowMapper implements BiFunction<Row, String, PaymentConfig> {

    private final ColumnConverter converter;

    public PaymentConfigRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link PaymentConfig} stored in the database.
     */
    @Override
    public PaymentConfig apply(Row row, String prefix) {
        PaymentConfig entity = new PaymentConfig();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setRecordId(converter.fromRow(row, prefix + "_record_id", String.class));
        entity.setPaymentId(converter.fromRow(row, prefix + "_payment_id", String.class));
        entity.setPaymentName(converter.fromRow(row, prefix + "_payment_name", String.class));
        entity.setPaymentType(converter.fromRow(row, prefix + "_payment_type", String.class));
        entity.setPaymentDescription(converter.fromRow(row, prefix + "_payment_description", String.class));
        entity.setPaymentStatus(converter.fromRow(row, prefix + "_payment_status", String.class));
        entity.setPaymentMethod(converter.fromRow(row, prefix + "_payment_method", ModeOfPayment.class));
        entity.setPaymentAmount(converter.fromRow(row, prefix + "_payment_amount", Double.class));
        entity.setAdditionalConfig(converter.fromRow(row, prefix + "_additional_config", String.class));
        entity.setFreeField1(converter.fromRow(row, prefix + "_free_field_1", String.class));
        entity.setFreeField2(converter.fromRow(row, prefix + "_free_field_2", String.class));
        entity.setFreeField3(converter.fromRow(row, prefix + "_free_field_3", String.class));
        entity.setFreeField4(converter.fromRow(row, prefix + "_free_field_4", String.class));
        entity.setFreeField5(converter.fromRow(row, prefix + "_free_field_5", String.class));
        entity.setFreeField6(converter.fromRow(row, prefix + "_free_field_6", String.class));
        entity.setFreeField8(converter.fromRow(row, prefix + "_free_field_8", String.class));
        entity.setFreeField9(converter.fromRow(row, prefix + "_free_field_9", String.class));
        entity.setFreeField10(converter.fromRow(row, prefix + "_free_field_10", String.class));
        entity.setFreeField11(converter.fromRow(row, prefix + "_free_field_11", String.class));
        entity.setFreeField12(converter.fromRow(row, prefix + "_free_field_12", String.class));
        entity.setFreeField13(converter.fromRow(row, prefix + "_free_field_13", String.class));
        entity.setFreeField14(converter.fromRow(row, prefix + "_free_field_14", String.class));
        entity.setFreeField15ContentType(converter.fromRow(row, prefix + "_free_field_15_content_type", String.class));
        entity.setFreeField15(converter.fromRow(row, prefix + "_free_field_15", byte[].class));
        entity.setFreeField16(converter.fromRow(row, prefix + "_free_field_16", String.class));
        entity.setFreeField17(converter.fromRow(row, prefix + "_free_field_17", String.class));
        entity.setFreeField18ContentType(converter.fromRow(row, prefix + "_free_field_18_content_type", String.class));
        entity.setFreeField18(converter.fromRow(row, prefix + "_free_field_18", byte[].class));
        entity.setFreeField19(converter.fromRow(row, prefix + "_free_field_19", String.class));
        entity.setFreeField20(converter.fromRow(row, prefix + "_free_field_20", String.class));
        entity.setTimestamp(converter.fromRow(row, prefix + "_timestamp", ZonedDateTime.class));
        entity.setCreatedAt(converter.fromRow(row, prefix + "_created_at", ZonedDateTime.class));
        entity.setCreatedBy(converter.fromRow(row, prefix + "_created_by", String.class));
        entity.setUpdatedAt(converter.fromRow(row, prefix + "_updated_at", ZonedDateTime.class));
        entity.setUpdatedBy(converter.fromRow(row, prefix + "_updated_by", String.class));
        return entity;
    }
}
