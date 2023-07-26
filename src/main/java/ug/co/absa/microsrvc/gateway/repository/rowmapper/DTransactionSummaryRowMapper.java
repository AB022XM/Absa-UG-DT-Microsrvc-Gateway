package ug.co.absa.microsrvc.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;
import ug.co.absa.microsrvc.gateway.domain.DTransactionSummary;
import ug.co.absa.microsrvc.gateway.domain.enumeration.ErrorCodes;
import ug.co.absa.microsrvc.gateway.domain.enumeration.ErrorMessage;
import ug.co.absa.microsrvc.gateway.domain.enumeration.TranStatus;

/**
 * Converter between {@link Row} to {@link DTransactionSummary}, with proper type conversions.
 */
@Service
public class DTransactionSummaryRowMapper implements BiFunction<Row, String, DTransactionSummary> {

    private final ColumnConverter converter;

    public DTransactionSummaryRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link DTransactionSummary} stored in the database.
     */
    @Override
    public DTransactionSummary apply(Row row, String prefix) {
        DTransactionSummary entity = new DTransactionSummary();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setAbsaTranRef(converter.fromRow(row, prefix + "_absa_tran_ref", UUID.class));
        entity.setBillerId(converter.fromRow(row, prefix + "_biller_id", String.class));
        entity.setPaymentItemCode(converter.fromRow(row, prefix + "_payment_item_code", String.class));
        entity.setDtDTransactionId(converter.fromRow(row, prefix + "_dt_d_transaction_id", String.class));
        entity.setTransactionReferenceNumber(converter.fromRow(row, prefix + "_transaction_reference_number", String.class));
        entity.setRecordId(converter.fromRow(row, prefix + "_record_id", String.class));
        entity.setTransactionId(converter.fromRow(row, prefix + "_transaction_id", String.class));
        entity.setTransactionType(converter.fromRow(row, prefix + "_transaction_type", String.class));
        entity.setTransactionAmount(converter.fromRow(row, prefix + "_transaction_amount", String.class));
        entity.setTransactionDate(converter.fromRow(row, prefix + "_transaction_date", ZonedDateTime.class));
        entity.setTransactionStatus(converter.fromRow(row, prefix + "_transaction_status", TranStatus.class));
        entity.setFreeField1(converter.fromRow(row, prefix + "_free_field_1", String.class));
        entity.setFreeField2(converter.fromRow(row, prefix + "_free_field_2", String.class));
        entity.setFreeField3(converter.fromRow(row, prefix + "_free_field_3", String.class));
        entity.setFreeField4(converter.fromRow(row, prefix + "_free_field_4", String.class));
        entity.setFreeField5(converter.fromRow(row, prefix + "_free_field_5", String.class));
        entity.setFreeField6ContentType(converter.fromRow(row, prefix + "_free_field_6_content_type", String.class));
        entity.setFreeField6(converter.fromRow(row, prefix + "_free_field_6", byte[].class));
        entity.setCreatedAt(converter.fromRow(row, prefix + "_created_at", ZonedDateTime.class));
        entity.setCreatedBy(converter.fromRow(row, prefix + "_created_by", String.class));
        entity.setUpdatedAt(converter.fromRow(row, prefix + "_updated_at", ZonedDateTime.class));
        entity.setUpdatedBy(converter.fromRow(row, prefix + "_updated_by", String.class));
        entity.setErrorCode(converter.fromRow(row, prefix + "_error_code", ErrorCodes.class));
        entity.setErrorMessage(converter.fromRow(row, prefix + "_error_message", ErrorMessage.class));
        entity.setCustomerId(converter.fromRow(row, prefix + "_customer_id", Long.class));
        return entity;
    }
}
