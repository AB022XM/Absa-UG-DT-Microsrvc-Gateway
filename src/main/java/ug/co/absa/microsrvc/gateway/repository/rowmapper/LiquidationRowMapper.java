package ug.co.absa.microsrvc.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.time.ZonedDateTime;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;
import ug.co.absa.microsrvc.gateway.domain.Liquidation;
import ug.co.absa.microsrvc.gateway.domain.enumeration.ServiceLevel;

/**
 * Converter between {@link Row} to {@link Liquidation}, with proper type conversions.
 */
@Service
public class LiquidationRowMapper implements BiFunction<Row, String, Liquidation> {

    private final ColumnConverter converter;

    public LiquidationRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link Liquidation} stored in the database.
     */
    @Override
    public Liquidation apply(Row row, String prefix) {
        Liquidation entity = new Liquidation();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setRecordId(converter.fromRow(row, prefix + "_record_id", String.class));
        entity.setServiceLevel(converter.fromRow(row, prefix + "_service_level", ServiceLevel.class));
        entity.setTimestamp(converter.fromRow(row, prefix + "_timestamp", ZonedDateTime.class));
        entity.setPartnerCode(converter.fromRow(row, prefix + "_partner_code", String.class));
        entity.setAmount(converter.fromRow(row, prefix + "_amount", String.class));
        entity.setCurrency(converter.fromRow(row, prefix + "_currency", String.class));
        entity.setReceiverBankcode(converter.fromRow(row, prefix + "_receiver_bankcode", String.class));
        entity.setReceiverAccountNumber(converter.fromRow(row, prefix + "_receiver_account_number", String.class));
        entity.setBeneficiaryName(converter.fromRow(row, prefix + "_beneficiary_name", String.class));
        entity.setInstructionId(converter.fromRow(row, prefix + "_instruction_id", String.class));
        entity.setSenderToReceiverInfo(converter.fromRow(row, prefix + "_sender_to_receiver_info", String.class));
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
