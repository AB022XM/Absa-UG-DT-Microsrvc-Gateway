package ug.co.absa.microsrvc.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;
import ug.co.absa.microsrvc.gateway.domain.IncomingJSONRequest;

/**
 * Converter between {@link Row} to {@link IncomingJSONRequest}, with proper type conversions.
 */
@Service
public class IncomingJSONRequestRowMapper implements BiFunction<Row, String, IncomingJSONRequest> {

    private final ColumnConverter converter;

    public IncomingJSONRequestRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link IncomingJSONRequest} stored in the database.
     */
    @Override
    public IncomingJSONRequest apply(Row row, String prefix) {
        IncomingJSONRequest entity = new IncomingJSONRequest();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setAbsaTranRef(converter.fromRow(row, prefix + "_absa_tran_ref", UUID.class));
        entity.setDtDTransactionId(converter.fromRow(row, prefix + "_dt_d_transaction_id", String.class));
        entity.setAmolDTransactionId(converter.fromRow(row, prefix + "_amol_d_transaction_id", String.class));
        entity.setTransactionReferenceNumber(converter.fromRow(row, prefix + "_transaction_reference_number", String.class));
        entity.setToken(converter.fromRow(row, prefix + "_token", String.class));
        entity.setTransactionId(converter.fromRow(row, prefix + "_transaction_id", String.class));
        entity.setFromEndpoint(converter.fromRow(row, prefix + "_from_endpoint", String.class));
        entity.setToEndpoint(converter.fromRow(row, prefix + "_to_endpoint", String.class));
        entity.setRequestJson(converter.fromRow(row, prefix + "_request_json", String.class));
        entity.setRequestType(converter.fromRow(row, prefix + "_request_type", String.class));
        entity.setRequestDescription(converter.fromRow(row, prefix + "_request_description", String.class));
        entity.setRequestStatus(converter.fromRow(row, prefix + "_request_status", String.class));
        entity.setAdditionalInfo(converter.fromRow(row, prefix + "_additional_info", String.class));
        entity.setFreeField1(converter.fromRow(row, prefix + "_free_field_1", String.class));
        entity.setFreeField2(converter.fromRow(row, prefix + "_free_field_2", String.class));
        entity.setFreeField3(converter.fromRow(row, prefix + "_free_field_3", String.class));
        entity.setFreeField4(converter.fromRow(row, prefix + "_free_field_4", String.class));
        entity.setFreeField5(converter.fromRow(row, prefix + "_free_field_5", String.class));
        entity.setFreeField6ContentType(converter.fromRow(row, prefix + "_free_field_6_content_type", String.class));
        entity.setFreeField6(converter.fromRow(row, prefix + "_free_field_6", byte[].class));
        entity.setFreeField7ContentType(converter.fromRow(row, prefix + "_free_field_7_content_type", String.class));
        entity.setFreeField7(converter.fromRow(row, prefix + "_free_field_7", byte[].class));
        entity.setFreeField8ContentType(converter.fromRow(row, prefix + "_free_field_8_content_type", String.class));
        entity.setFreeField8(converter.fromRow(row, prefix + "_free_field_8", byte[].class));
        entity.setFreeField9(converter.fromRow(row, prefix + "_free_field_9", String.class));
        entity.setFreeField10(converter.fromRow(row, prefix + "_free_field_10", Boolean.class));
        entity.setFreeField11(converter.fromRow(row, prefix + "_free_field_11", Boolean.class));
        entity.setFreeField12(converter.fromRow(row, prefix + "_free_field_12", Integer.class));
        entity.setFreeField13(converter.fromRow(row, prefix + "_free_field_13", String.class));
        entity.setFreeField14(converter.fromRow(row, prefix + "_free_field_14", String.class));
        entity.setFreeField15(converter.fromRow(row, prefix + "_free_field_15", String.class));
        entity.setFreeField16(converter.fromRow(row, prefix + "_free_field_16", String.class));
        entity.setFreeField17(converter.fromRow(row, prefix + "_free_field_17", String.class));
        entity.setFreeField18(converter.fromRow(row, prefix + "_free_field_18", String.class));
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
