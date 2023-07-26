package ug.co.absa.microsrvc.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;
import ug.co.absa.microsrvc.gateway.domain.DTransactionChannel;

/**
 * Converter between {@link Row} to {@link DTransactionChannel}, with proper type conversions.
 */
@Service
public class DTransactionChannelRowMapper implements BiFunction<Row, String, DTransactionChannel> {

    private final ColumnConverter converter;

    public DTransactionChannelRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link DTransactionChannel} stored in the database.
     */
    @Override
    public DTransactionChannel apply(Row row, String prefix) {
        DTransactionChannel entity = new DTransactionChannel();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setAbsaTranRef(converter.fromRow(row, prefix + "_absa_tran_ref", UUID.class));
        entity.setBillerId(converter.fromRow(row, prefix + "_biller_id", String.class));
        entity.setPaymentItemCode(converter.fromRow(row, prefix + "_payment_item_code", String.class));
        entity.setDtDTransactionId(converter.fromRow(row, prefix + "_dt_d_transaction_id", String.class));
        entity.setTransactionReferenceNumber(converter.fromRow(row, prefix + "_transaction_reference_number", String.class));
        entity.setExternalTranid(converter.fromRow(row, prefix + "_external_tranid", String.class));
        entity.setChannelCode(converter.fromRow(row, prefix + "_channel_code", String.class));
        entity.setChannelName(converter.fromRow(row, prefix + "_channel_name", String.class));
        entity.setChannelDescription(converter.fromRow(row, prefix + "_channel_description", String.class));
        entity.setTimestamp(converter.fromRow(row, prefix + "_timestamp", ZonedDateTime.class));
        entity.setChanelFreeText(converter.fromRow(row, prefix + "_chanel_free_text", String.class));
        entity.setChanelFreeText1(converter.fromRow(row, prefix + "_chanel_free_text_1", String.class));
        entity.setChanelFreeText2(converter.fromRow(row, prefix + "_chanel_free_text_2", String.class));
        entity.setChanelFreeText3(converter.fromRow(row, prefix + "_chanel_free_text_3", String.class));
        entity.setChanelFreeText4(converter.fromRow(row, prefix + "_chanel_free_text_4", String.class));
        entity.setChanelFreeText5(converter.fromRow(row, prefix + "_chanel_free_text_5", String.class));
        entity.setChanelFreeText6(converter.fromRow(row, prefix + "_chanel_free_text_6", String.class));
        entity.setChanelFreeText7(converter.fromRow(row, prefix + "_chanel_free_text_7", String.class));
        entity.setChanelFreeText8(converter.fromRow(row, prefix + "_chanel_free_text_8", String.class));
        entity.setChanelFreeText9(converter.fromRow(row, prefix + "_chanel_free_text_9", String.class));
        entity.setChanelFreeText10(converter.fromRow(row, prefix + "_chanel_free_text_10", String.class));
        entity.setChanelFreeText11(converter.fromRow(row, prefix + "_chanel_free_text_11", String.class));
        entity.setChanelFreeText12(converter.fromRow(row, prefix + "_chanel_free_text_12", String.class));
        entity.setChanelFreeText13(converter.fromRow(row, prefix + "_chanel_free_text_13", String.class));
        entity.setChanelFreeText14(converter.fromRow(row, prefix + "_chanel_free_text_14", String.class));
        entity.setChanelFreeText15(converter.fromRow(row, prefix + "_chanel_free_text_15", String.class));
        entity.setChanelFreeText16(converter.fromRow(row, prefix + "_chanel_free_text_16", String.class));
        entity.setChanelFreeText17(converter.fromRow(row, prefix + "_chanel_free_text_17", String.class));
        entity.setChanelFreeText18(converter.fromRow(row, prefix + "_chanel_free_text_18", String.class));
        entity.setChanelFreeText19(converter.fromRow(row, prefix + "_chanel_free_text_19", String.class));
        entity.setChanelFreeText20(converter.fromRow(row, prefix + "_chanel_free_text_20", String.class));
        entity.setChanelFreeText21(converter.fromRow(row, prefix + "_chanel_free_text_21", String.class));
        entity.setChanelFreeText22(converter.fromRow(row, prefix + "_chanel_free_text_22", String.class));
        entity.setChanelFreeText23(converter.fromRow(row, prefix + "_chanel_free_text_23", String.class));
        entity.setChanelFreeText24(converter.fromRow(row, prefix + "_chanel_free_text_24", String.class));
        entity.setCreatedAt(converter.fromRow(row, prefix + "_created_at", ZonedDateTime.class));
        entity.setCreatedBy(converter.fromRow(row, prefix + "_created_by", String.class));
        entity.setUpdatedAt(converter.fromRow(row, prefix + "_updated_at", ZonedDateTime.class));
        entity.setUpdatedBy(converter.fromRow(row, prefix + "_updated_by", String.class));
        entity.setDelflg(converter.fromRow(row, prefix + "_delflg", Boolean.class));
        entity.setDTransactionId(converter.fromRow(row, prefix + "_d_transaction_id", Long.class));
        return entity;
    }
}
