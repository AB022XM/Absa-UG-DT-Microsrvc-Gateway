package ug.co.absa.microsrvc.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;
import ug.co.absa.microsrvc.gateway.domain.Bank;
import ug.co.absa.microsrvc.gateway.domain.enumeration.RecordStatus;

/**
 * Converter between {@link Row} to {@link Bank}, with proper type conversions.
 */
@Service
public class BankRowMapper implements BiFunction<Row, String, Bank> {

    private final ColumnConverter converter;

    public BankRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link Bank} stored in the database.
     */
    @Override
    public Bank apply(Row row, String prefix) {
        Bank entity = new Bank();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setAbsaTranRef(converter.fromRow(row, prefix + "_absa_tran_ref", UUID.class));
        entity.setBillerId(converter.fromRow(row, prefix + "_biller_id", String.class));
        entity.setPaymentItemCode(converter.fromRow(row, prefix + "_payment_item_code", String.class));
        entity.setDtDTransactionId(converter.fromRow(row, prefix + "_dt_d_transaction_id", String.class));
        entity.setAmolDTransactionId(converter.fromRow(row, prefix + "_amol_d_transaction_id", String.class));
        entity.setBankName(converter.fromRow(row, prefix + "_bank_name", String.class));
        entity.setBankSwiftCode(converter.fromRow(row, prefix + "_bank_swift_code", String.class));
        entity.setBankBranchId(converter.fromRow(row, prefix + "_bank_branch_id", String.class));
        entity.setBankPhoneNumber(converter.fromRow(row, prefix + "_bank_phone_number", String.class));
        entity.setBankEmail(converter.fromRow(row, prefix + "_bank_email", String.class));
        entity.setBankFreeField1(converter.fromRow(row, prefix + "_bank_free_field_1", String.class));
        entity.setBankFreeField3(converter.fromRow(row, prefix + "_bank_free_field_3", String.class));
        entity.setBankFreeField4(converter.fromRow(row, prefix + "_bank_free_field_4", String.class));
        entity.setBankFreeField5(converter.fromRow(row, prefix + "_bank_free_field_5", String.class));
        entity.setBankFreeField6(converter.fromRow(row, prefix + "_bank_free_field_6", String.class));
        entity.setBankFreeField7(converter.fromRow(row, prefix + "_bank_free_field_7", String.class));
        entity.setBankFreeField8(converter.fromRow(row, prefix + "_bank_free_field_8", String.class));
        entity.setBankFreeField9(converter.fromRow(row, prefix + "_bank_free_field_9", String.class));
        entity.setBankFreeField10(converter.fromRow(row, prefix + "_bank_free_field_10", String.class));
        entity.setBankFreeField11(converter.fromRow(row, prefix + "_bank_free_field_11", String.class));
        entity.setBankFreeField12(converter.fromRow(row, prefix + "_bank_free_field_12", String.class));
        entity.setBankFreeField13(converter.fromRow(row, prefix + "_bank_free_field_13", String.class));
        entity.setBankFreeField14(converter.fromRow(row, prefix + "_bank_free_field_14", String.class));
        entity.setBankFreeField15(converter.fromRow(row, prefix + "_bank_free_field_15", String.class));
        entity.setBankFreeField16(converter.fromRow(row, prefix + "_bank_free_field_16", String.class));
        entity.setBankFreeField17(converter.fromRow(row, prefix + "_bank_free_field_17", String.class));
        entity.setBankFreeField18(converter.fromRow(row, prefix + "_bank_free_field_18", String.class));
        entity.setBankFreeField19(converter.fromRow(row, prefix + "_bank_free_field_19", String.class));
        entity.setBankFreeField20(converter.fromRow(row, prefix + "_bank_free_field_20", String.class));
        entity.setBankFreeField21(converter.fromRow(row, prefix + "_bank_free_field_21", String.class));
        entity.setBankFreeField22(converter.fromRow(row, prefix + "_bank_free_field_22", String.class));
        entity.setBankFreeField23(converter.fromRow(row, prefix + "_bank_free_field_23", String.class));
        entity.setBankFreeField24(converter.fromRow(row, prefix + "_bank_free_field_24", String.class));
        entity.setCreatedAt(converter.fromRow(row, prefix + "_created_at", ZonedDateTime.class));
        entity.setCreatedBy(converter.fromRow(row, prefix + "_created_by", String.class));
        entity.setUpdatedAt(converter.fromRow(row, prefix + "_updated_at", ZonedDateTime.class));
        entity.setUpdatedBy(converter.fromRow(row, prefix + "_updated_by", String.class));
        entity.setDelflg(converter.fromRow(row, prefix + "_delflg", Boolean.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", RecordStatus.class));
        return entity;
    }
}
