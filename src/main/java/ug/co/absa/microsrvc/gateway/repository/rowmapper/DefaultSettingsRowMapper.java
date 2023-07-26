package ug.co.absa.microsrvc.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;
import ug.co.absa.microsrvc.gateway.domain.DefaultSettings;
import ug.co.absa.microsrvc.gateway.domain.enumeration.RecordStatus;

/**
 * Converter between {@link Row} to {@link DefaultSettings}, with proper type conversions.
 */
@Service
public class DefaultSettingsRowMapper implements BiFunction<Row, String, DefaultSettings> {

    private final ColumnConverter converter;

    public DefaultSettingsRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link DefaultSettings} stored in the database.
     */
    @Override
    public DefaultSettings apply(Row row, String prefix) {
        DefaultSettings entity = new DefaultSettings();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setAbsaTranRef(converter.fromRow(row, prefix + "_absa_tran_ref", UUID.class));
        entity.setDtDTransactionId(converter.fromRow(row, prefix + "_dt_d_transaction_id", String.class));
        entity.setAmolDTransactionId(converter.fromRow(row, prefix + "_amol_d_transaction_id", String.class));
        entity.setTransactionReferenceNumber(converter.fromRow(row, prefix + "_transaction_reference_number", String.class));
        entity.setToken(converter.fromRow(row, prefix + "_token", String.class));
        entity.setThirdPartyDTransactionId(converter.fromRow(row, prefix + "_third_party_d_transaction_id", String.class));
        entity.setDefaultSettingCode(converter.fromRow(row, prefix + "_default_setting_code", String.class));
        entity.setJsonSettings(converter.fromRow(row, prefix + "_json_settings", String.class));
        entity.setAppConfig(converter.fromRow(row, prefix + "_app_config", String.class));
        entity.setAppName(converter.fromRow(row, prefix + "_app_name", String.class));
        entity.setFreeField(converter.fromRow(row, prefix + "_free_field", String.class));
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
        entity.setTimestamp(converter.fromRow(row, prefix + "_timestamp", ZonedDateTime.class));
        entity.setRecordStatus(converter.fromRow(row, prefix + "_record_status", RecordStatus.class));
        entity.setCreatedAt(converter.fromRow(row, prefix + "_created_at", ZonedDateTime.class));
        entity.setCreatedBy(converter.fromRow(row, prefix + "_created_by", String.class));
        entity.setUpdatedAt(converter.fromRow(row, prefix + "_updated_at", ZonedDateTime.class));
        entity.setUpdatedby(converter.fromRow(row, prefix + "_updatedby", String.class));
        return entity;
    }
}
