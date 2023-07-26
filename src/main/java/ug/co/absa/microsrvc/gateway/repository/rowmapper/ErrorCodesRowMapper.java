package ug.co.absa.microsrvc.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;
import ug.co.absa.microsrvc.gateway.domain.ErrorCodes;
import ug.co.absa.microsrvc.gateway.domain.enumeration.ErrorMessage;
import ug.co.absa.microsrvc.gateway.domain.enumeration.TranStatus;

/**
 * Converter between {@link Row} to {@link ErrorCodes}, with proper type conversions.
 */
@Service
public class ErrorCodesRowMapper implements BiFunction<Row, String, ErrorCodes> {

    private final ColumnConverter converter;

    public ErrorCodesRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link ErrorCodes} stored in the database.
     */
    @Override
    public ErrorCodes apply(Row row, String prefix) {
        ErrorCodes entity = new ErrorCodes();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setAbsaTranRef(converter.fromRow(row, prefix + "_absa_tran_ref", UUID.class));
        entity.setRecordId(converter.fromRow(row, prefix + "_record_id", String.class));
        entity.setPaymentItemCode(converter.fromRow(row, prefix + "_payment_item_code", String.class));
        entity.setDtDTransactionId(converter.fromRow(row, prefix + "_dt_d_transaction_id", String.class));
        entity.setTransactionReferenceNumber(converter.fromRow(row, prefix + "_transaction_reference_number", String.class));
        entity.setExternalTranid(converter.fromRow(row, prefix + "_external_tranid", String.class));
        entity.setErrorCode(converter.fromRow(row, prefix + "_error_code", String.class));
        entity.setErrorMessage(converter.fromRow(row, prefix + "_error_message", ErrorMessage.class));
        entity.setResponseCode(converter.fromRow(row, prefix + "_response_code", String.class));
        entity.setResponseMessage(converter.fromRow(row, prefix + "_response_message", String.class));
        entity.setResponseBody(converter.fromRow(row, prefix + "_response_body", String.class));
        entity.setTimestamp(converter.fromRow(row, prefix + "_timestamp", ZonedDateTime.class));
        entity.setRequestRef(converter.fromRow(row, prefix + "_request_ref", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", TranStatus.class));
        entity.setCustomerField1(converter.fromRow(row, prefix + "_customer_field_1", String.class));
        entity.setCustomerField2(converter.fromRow(row, prefix + "_customer_field_2", String.class));
        entity.setCustomerField3(converter.fromRow(row, prefix + "_customer_field_3", String.class));
        entity.setCustomerField4(converter.fromRow(row, prefix + "_customer_field_4", String.class));
        entity.setCustomerField5(converter.fromRow(row, prefix + "_customer_field_5", String.class));
        entity.setCustomerField6(converter.fromRow(row, prefix + "_customer_field_6", String.class));
        entity.setCustomerField7(converter.fromRow(row, prefix + "_customer_field_7", String.class));
        entity.setCustomerField8(converter.fromRow(row, prefix + "_customer_field_8", String.class));
        entity.setCreatedAt(converter.fromRow(row, prefix + "_created_at", ZonedDateTime.class));
        entity.setCreatedBy(converter.fromRow(row, prefix + "_created_by", String.class));
        entity.setUpdatedAt(converter.fromRow(row, prefix + "_updated_at", ZonedDateTime.class));
        entity.setUpdatedBy(converter.fromRow(row, prefix + "_updated_by", String.class));
        return entity;
    }
}
