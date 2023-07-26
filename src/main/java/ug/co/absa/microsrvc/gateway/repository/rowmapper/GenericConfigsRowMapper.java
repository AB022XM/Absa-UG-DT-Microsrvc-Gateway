package ug.co.absa.microsrvc.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;
import ug.co.absa.microsrvc.gateway.domain.GenericConfigs;
import ug.co.absa.microsrvc.gateway.domain.enumeration.RecordStatus;

/**
 * Converter between {@link Row} to {@link GenericConfigs}, with proper type conversions.
 */
@Service
public class GenericConfigsRowMapper implements BiFunction<Row, String, GenericConfigs> {

    private final ColumnConverter converter;

    public GenericConfigsRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link GenericConfigs} stored in the database.
     */
    @Override
    public GenericConfigs apply(Row row, String prefix) {
        GenericConfigs entity = new GenericConfigs();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setAbsaTranRef(converter.fromRow(row, prefix + "_absa_tran_ref", UUID.class));
        entity.setRecordId(converter.fromRow(row, prefix + "_record_id", String.class));
        entity.setConfigId(converter.fromRow(row, prefix + "_config_id", String.class));
        entity.setConfigName(converter.fromRow(row, prefix + "_config_name", String.class));
        entity.setConfigsDetails(converter.fromRow(row, prefix + "_configs_details", String.class));
        entity.setAdditionalConfigs(converter.fromRow(row, prefix + "_additional_configs", String.class));
        entity.setFreeField(converter.fromRow(row, prefix + "_free_field", Boolean.class));
        entity.setFreeField1(converter.fromRow(row, prefix + "_free_field_1", Boolean.class));
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
        entity.setFreeField21(converter.fromRow(row, prefix + "_free_field_21", String.class));
        entity.setFreeField22(converter.fromRow(row, prefix + "_free_field_22", String.class));
        entity.setFreeField23(converter.fromRow(row, prefix + "_free_field_23", String.class));
        entity.setFreeField24(converter.fromRow(row, prefix + "_free_field_24", String.class));
        entity.setFreeField25ContentType(converter.fromRow(row, prefix + "_free_field_25_content_type", String.class));
        entity.setFreeField25(converter.fromRow(row, prefix + "_free_field_25", byte[].class));
        entity.setFreeField26(converter.fromRow(row, prefix + "_free_field_26", String.class));
        entity.setFreeField27(converter.fromRow(row, prefix + "_free_field_27", String.class));
        entity.setFreeField28ContentType(converter.fromRow(row, prefix + "_free_field_28_content_type", String.class));
        entity.setFreeField28(converter.fromRow(row, prefix + "_free_field_28", byte[].class));
        entity.setFreeField29(converter.fromRow(row, prefix + "_free_field_29", String.class));
        entity.setFreeField30(converter.fromRow(row, prefix + "_free_field_30", String.class));
        entity.setFreeField31(converter.fromRow(row, prefix + "_free_field_31", String.class));
        entity.setFreeField32(converter.fromRow(row, prefix + "_free_field_32", String.class));
        entity.setFreeField33(converter.fromRow(row, prefix + "_free_field_33", String.class));
        entity.setFreeField34(converter.fromRow(row, prefix + "_free_field_34", String.class));
        entity.setTimestamp(converter.fromRow(row, prefix + "_timestamp", ZonedDateTime.class));
        entity.setRecordStatus(converter.fromRow(row, prefix + "_record_status", RecordStatus.class));
        return entity;
    }
}
