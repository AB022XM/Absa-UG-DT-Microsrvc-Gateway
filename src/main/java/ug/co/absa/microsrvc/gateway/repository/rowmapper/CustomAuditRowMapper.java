package ug.co.absa.microsrvc.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;
import ug.co.absa.microsrvc.gateway.domain.CustomAudit;
import ug.co.absa.microsrvc.gateway.domain.enumeration.ServiceLevel;

/**
 * Converter between {@link Row} to {@link CustomAudit}, with proper type conversions.
 */
@Service
public class CustomAuditRowMapper implements BiFunction<Row, String, CustomAudit> {

    private final ColumnConverter converter;

    public CustomAuditRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link CustomAudit} stored in the database.
     */
    @Override
    public CustomAudit apply(Row row, String prefix) {
        CustomAudit entity = new CustomAudit();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setAbsaTranRef(converter.fromRow(row, prefix + "_absa_tran_ref", UUID.class));
        entity.setRecordId(converter.fromRow(row, prefix + "_record_id", String.class));
        entity.setActionId(converter.fromRow(row, prefix + "_action_id", ServiceLevel.class));
        entity.setTimestamp(converter.fromRow(row, prefix + "_timestamp", ZonedDateTime.class));
        entity.setOldValue(converter.fromRow(row, prefix + "_old_value", String.class));
        entity.setNewValue(converter.fromRow(row, prefix + "_new_value", String.class));
        entity.setChangeResaon(converter.fromRow(row, prefix + "_change_resaon", String.class));
        entity.setDescription(converter.fromRow(row, prefix + "_description", String.class));
        entity.setDescription1(converter.fromRow(row, prefix + "_description_1", String.class));
        entity.setDescription2(converter.fromRow(row, prefix + "_description_2", String.class));
        entity.setDescription3(converter.fromRow(row, prefix + "_description_3", String.class));
        entity.setDescription4(converter.fromRow(row, prefix + "_description_4", String.class));
        entity.setDescription5(converter.fromRow(row, prefix + "_description_5", String.class));
        entity.setDescription6(converter.fromRow(row, prefix + "_description_6", String.class));
        entity.setDescription7(converter.fromRow(row, prefix + "_description_7", String.class));
        entity.setDescription8(converter.fromRow(row, prefix + "_description_8", String.class));
        entity.setDescription9(converter.fromRow(row, prefix + "_description_9", String.class));
        entity.setFreeText1(converter.fromRow(row, prefix + "_free_text_1", String.class));
        entity.setFreeText2(converter.fromRow(row, prefix + "_free_text_2", String.class));
        entity.setFreeText3(converter.fromRow(row, prefix + "_free_text_3", String.class));
        entity.setFreeText4(converter.fromRow(row, prefix + "_free_text_4", String.class));
        entity.setFreeText5(converter.fromRow(row, prefix + "_free_text_5", String.class));
        entity.setFreeText6(converter.fromRow(row, prefix + "_free_text_6", String.class));
        entity.setFreeText7(converter.fromRow(row, prefix + "_free_text_7", String.class));
        entity.setFreeText8(converter.fromRow(row, prefix + "_free_text_8", String.class));
        entity.setFreeText9(converter.fromRow(row, prefix + "_free_text_9", String.class));
        entity.setFreeText10(converter.fromRow(row, prefix + "_free_text_10", String.class));
        entity.setFreeText11(converter.fromRow(row, prefix + "_free_text_11", String.class));
        entity.setFreeText12(converter.fromRow(row, prefix + "_free_text_12", String.class));
        entity.setFreeText13(converter.fromRow(row, prefix + "_free_text_13", String.class));
        entity.setFreeText14(converter.fromRow(row, prefix + "_free_text_14", String.class));
        entity.setFreeText15(converter.fromRow(row, prefix + "_free_text_15", String.class));
        entity.setFreeText16(converter.fromRow(row, prefix + "_free_text_16", String.class));
        entity.setFreeText17(converter.fromRow(row, prefix + "_free_text_17", String.class));
        entity.setFreeText18(converter.fromRow(row, prefix + "_free_text_18", String.class));
        entity.setFreeText19(converter.fromRow(row, prefix + "_free_text_19", String.class));
        entity.setFreeText20(converter.fromRow(row, prefix + "_free_text_20", String.class));
        entity.setFreeText21(converter.fromRow(row, prefix + "_free_text_21", String.class));
        entity.setFreeText22(converter.fromRow(row, prefix + "_free_text_22", String.class));
        entity.setFreeText23(converter.fromRow(row, prefix + "_free_text_23", String.class));
        entity.setFreeText24(converter.fromRow(row, prefix + "_free_text_24", String.class));
        entity.setFreeText25(converter.fromRow(row, prefix + "_free_text_25", String.class));
        entity.setFreeText26(converter.fromRow(row, prefix + "_free_text_26", String.class));
        entity.setFreeText27(converter.fromRow(row, prefix + "_free_text_27", String.class));
        entity.setFreeText28(converter.fromRow(row, prefix + "_free_text_28", String.class));
        entity.setCreatedAt(converter.fromRow(row, prefix + "_created_at", ZonedDateTime.class));
        entity.setCreatedBy(converter.fromRow(row, prefix + "_created_by", String.class));
        entity.setUpdatedAt(converter.fromRow(row, prefix + "_updated_at", ZonedDateTime.class));
        entity.setUpdatedBy(converter.fromRow(row, prefix + "_updated_by", String.class));
        return entity;
    }
}
