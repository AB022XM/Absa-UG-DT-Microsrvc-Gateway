package ug.co.absa.microsrvc.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;
import ug.co.absa.microsrvc.gateway.domain.ResponseInfo;
import ug.co.absa.microsrvc.gateway.domain.enumeration.ErrorCodes;
import ug.co.absa.microsrvc.gateway.domain.enumeration.ErrorMessage;
import ug.co.absa.microsrvc.gateway.domain.enumeration.TranStatus;

/**
 * Converter between {@link Row} to {@link ResponseInfo}, with proper type conversions.
 */
@Service
public class ResponseInfoRowMapper implements BiFunction<Row, String, ResponseInfo> {

    private final ColumnConverter converter;

    public ResponseInfoRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link ResponseInfo} stored in the database.
     */
    @Override
    public ResponseInfo apply(Row row, String prefix) {
        ResponseInfo entity = new ResponseInfo();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setAbsaTranRef(converter.fromRow(row, prefix + "_absa_tran_ref", UUID.class));
        entity.setBillerId(converter.fromRow(row, prefix + "_biller_id", String.class));
        entity.setPaymentItemCode(converter.fromRow(row, prefix + "_payment_item_code", String.class));
        entity.setDtDTransactionId(converter.fromRow(row, prefix + "_dt_d_transaction_id", String.class));
        entity.setTransactionReferenceNumber(converter.fromRow(row, prefix + "_transaction_reference_number", String.class));
        entity.setExternalTranid(converter.fromRow(row, prefix + "_external_tranid", String.class));
        entity.setResponseCode(converter.fromRow(row, prefix + "_response_code", String.class));
        entity.setResponseMessage(converter.fromRow(row, prefix + "_response_message", String.class));
        entity.setResponseBody(converter.fromRow(row, prefix + "_response_body", String.class));
        entity.setTimestamp(converter.fromRow(row, prefix + "_timestamp", ZonedDateTime.class));
        entity.setRequestRef(converter.fromRow(row, prefix + "_request_ref", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", TranStatus.class));
        entity.setFreeField1(converter.fromRow(row, prefix + "_free_field_1", String.class));
        entity.setFreeField2ContentType(converter.fromRow(row, prefix + "_free_field_2_content_type", String.class));
        entity.setFreeField2(converter.fromRow(row, prefix + "_free_field_2", byte[].class));
        entity.setFreeField3(converter.fromRow(row, prefix + "_free_field_3", String.class));
        entity.setFreeField4(converter.fromRow(row, prefix + "_free_field_4", String.class));
        entity.setFreeField5(converter.fromRow(row, prefix + "_free_field_5", String.class));
        entity.setFreeField6(converter.fromRow(row, prefix + "_free_field_6", String.class));
        entity.setTime(converter.fromRow(row, prefix + "_time", ZonedDateTime.class));
        entity.setErrorCode(converter.fromRow(row, prefix + "_error_code", ErrorCodes.class));
        entity.setErrorMessage(converter.fromRow(row, prefix + "_error_message", ErrorMessage.class));
        entity.setCreatedAt(converter.fromRow(row, prefix + "_created_at", ZonedDateTime.class));
        entity.setCreatedBy(converter.fromRow(row, prefix + "_created_by", String.class));
        entity.setUpdatedAt(converter.fromRow(row, prefix + "_updated_at", ZonedDateTime.class));
        entity.setUpdatedBy(converter.fromRow(row, prefix + "_updated_by", String.class));
        return entity;
    }
}
