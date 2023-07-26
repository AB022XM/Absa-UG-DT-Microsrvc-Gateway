package ug.co.absa.microsrvc.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;
import ug.co.absa.microsrvc.gateway.domain.Branch;
import ug.co.absa.microsrvc.gateway.domain.enumeration.RecordStatus;

/**
 * Converter between {@link Row} to {@link Branch}, with proper type conversions.
 */
@Service
public class BranchRowMapper implements BiFunction<Row, String, Branch> {

    private final ColumnConverter converter;

    public BranchRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link Branch} stored in the database.
     */
    @Override
    public Branch apply(Row row, String prefix) {
        Branch entity = new Branch();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setAbsaTranRef(converter.fromRow(row, prefix + "_absa_tran_ref", UUID.class));
        entity.setRecordId(converter.fromRow(row, prefix + "_record_id", String.class));
        entity.setAddressId(converter.fromRow(row, prefix + "_address_id", String.class));
        entity.setBankId(converter.fromRow(row, prefix + "_bank_id", String.class));
        entity.setBranchId(converter.fromRow(row, prefix + "_branch_id", String.class));
        entity.setBranchName(converter.fromRow(row, prefix + "_branch_name", String.class));
        entity.setBranchSwiftCode(converter.fromRow(row, prefix + "_branch_swift_code", String.class));
        entity.setBranchPhoneNumber(converter.fromRow(row, prefix + "_branch_phone_number", String.class));
        entity.setBranchEmail(converter.fromRow(row, prefix + "_branch_email", String.class));
        entity.setBranchFreeField1(converter.fromRow(row, prefix + "_branch_free_field_1", String.class));
        entity.setBranchFreeField3(converter.fromRow(row, prefix + "_branch_free_field_3", String.class));
        entity.setBranchFreeField4(converter.fromRow(row, prefix + "_branch_free_field_4", String.class));
        entity.setBranchFreeField5(converter.fromRow(row, prefix + "_branch_free_field_5", String.class));
        entity.setBranchFreeField6(converter.fromRow(row, prefix + "_branch_free_field_6", String.class));
        entity.setBranchFreeField7(converter.fromRow(row, prefix + "_branch_free_field_7", String.class));
        entity.setBranchFreeField8(converter.fromRow(row, prefix + "_branch_free_field_8", String.class));
        entity.setBranchFreeField9(converter.fromRow(row, prefix + "_branch_free_field_9", String.class));
        entity.setBranchFreeField10(converter.fromRow(row, prefix + "_branch_free_field_10", String.class));
        entity.setBranchFreeField11(converter.fromRow(row, prefix + "_branch_free_field_11", String.class));
        entity.setBranchFreeField12(converter.fromRow(row, prefix + "_branch_free_field_12", String.class));
        entity.setBranchFreeField13(converter.fromRow(row, prefix + "_branch_free_field_13", String.class));
        entity.setBranchFreeField14(converter.fromRow(row, prefix + "_branch_free_field_14", String.class));
        entity.setBranchFreeField15(converter.fromRow(row, prefix + "_branch_free_field_15", String.class));
        entity.setBranchFreeField16(converter.fromRow(row, prefix + "_branch_free_field_16", String.class));
        entity.setBranchFreeField17(converter.fromRow(row, prefix + "_branch_free_field_17", String.class));
        entity.setBranchFreeField18(converter.fromRow(row, prefix + "_branch_free_field_18", String.class));
        entity.setBranchFreeField19(converter.fromRow(row, prefix + "_branch_free_field_19", String.class));
        entity.setBranchFreeField20(converter.fromRow(row, prefix + "_branch_free_field_20", String.class));
        entity.setBranchFreeField21(converter.fromRow(row, prefix + "_branch_free_field_21", String.class));
        entity.setBranchFreeField22(converter.fromRow(row, prefix + "_branch_free_field_22", String.class));
        entity.setBranchFreeField23(converter.fromRow(row, prefix + "_branch_free_field_23", String.class));
        entity.setBranchFreeField24(converter.fromRow(row, prefix + "_branch_free_field_24", String.class));
        entity.setCreatedAt(converter.fromRow(row, prefix + "_created_at", ZonedDateTime.class));
        entity.setCreatedBy(converter.fromRow(row, prefix + "_created_by", String.class));
        entity.setUpdatedAt(converter.fromRow(row, prefix + "_updated_at", ZonedDateTime.class));
        entity.setUpdatedBy(converter.fromRow(row, prefix + "_updated_by", String.class));
        entity.setTimestamp(converter.fromRow(row, prefix + "_timestamp", ZonedDateTime.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", RecordStatus.class));
        entity.setBankId(converter.fromRow(row, prefix + "_bank_id", Long.class));
        return entity;
    }
}
