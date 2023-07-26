package ug.co.absa.microsrvc.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.time.ZonedDateTime;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;
import ug.co.absa.microsrvc.gateway.domain.RequestInfo;
import ug.co.absa.microsrvc.gateway.domain.enumeration.ErrorCodes;
import ug.co.absa.microsrvc.gateway.domain.enumeration.ErrorMessage;
import ug.co.absa.microsrvc.gateway.domain.enumeration.TranStatus;

/**
 * Converter between {@link Row} to {@link RequestInfo}, with proper type conversions.
 */
@Service
public class RequestInfoRowMapper implements BiFunction<Row, String, RequestInfo> {

    private final ColumnConverter converter;

    public RequestInfoRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link RequestInfo} stored in the database.
     */
    @Override
    public RequestInfo apply(Row row, String prefix) {
        RequestInfo entity = new RequestInfo();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setRecordId(converter.fromRow(row, prefix + "_record_id", String.class));
        entity.setTransactionId(converter.fromRow(row, prefix + "_transaction_id", String.class));
        entity.setRequestData(converter.fromRow(row, prefix + "_request_data", String.class));
        entity.setParamList(converter.fromRow(row, prefix + "_param_list", String.class));
        entity.setTimestamp(converter.fromRow(row, prefix + "_timestamp", ZonedDateTime.class));
        entity.setRequestRef(converter.fromRow(row, prefix + "_request_ref", String.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", TranStatus.class));
        entity.setFreeField1(converter.fromRow(row, prefix + "_free_field_1", String.class));
        entity.setFreeField2(converter.fromRow(row, prefix + "_free_field_2", String.class));
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
