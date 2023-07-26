package ug.co.absa.microsrvc.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;
import ug.co.absa.microsrvc.gateway.domain.PaymentItems;

/**
 * Converter between {@link Row} to {@link PaymentItems}, with proper type conversions.
 */
@Service
public class PaymentItemsRowMapper implements BiFunction<Row, String, PaymentItems> {

    private final ColumnConverter converter;

    public PaymentItemsRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link PaymentItems} stored in the database.
     */
    @Override
    public PaymentItems apply(Row row, String prefix) {
        PaymentItems entity = new PaymentItems();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setAbsaTranRef(converter.fromRow(row, prefix + "_absa_tran_ref", UUID.class));
        entity.setRecordId(converter.fromRow(row, prefix + "_record_id", String.class));
        entity.setProductCategoryId(converter.fromRow(row, prefix + "_product_category_id", Integer.class));
        entity.setBillerId(converter.fromRow(row, prefix + "_biller_id", Integer.class));
        entity.setPaymentItemCode(converter.fromRow(row, prefix + "_payment_item_code", String.class));
        entity.setPaymentItemId(converter.fromRow(row, prefix + "_payment_item_id", Integer.class));
        entity.setPaymentItemName(converter.fromRow(row, prefix + "_payment_item_name", String.class));
        entity.setPaymentItemDescription(converter.fromRow(row, prefix + "_payment_item_description", String.class));
        entity.setIsActive(converter.fromRow(row, prefix + "_is_active", Boolean.class));
        entity.setHasFixedPrice(converter.fromRow(row, prefix + "_has_fixed_price", Boolean.class));
        entity.setHasVariablePrice(converter.fromRow(row, prefix + "_has_variable_price", Boolean.class));
        entity.setHasDiscount(converter.fromRow(row, prefix + "_has_discount", Boolean.class));
        entity.setHasTax(converter.fromRow(row, prefix + "_has_tax", Boolean.class));
        entity.setAmount(converter.fromRow(row, prefix + "_amount", Double.class));
        entity.setChargeAmount(converter.fromRow(row, prefix + "_charge_amount", Double.class));
        entity.setHasChargeAmount(converter.fromRow(row, prefix + "_has_charge_amount", Boolean.class));
        entity.setTaxAmount(converter.fromRow(row, prefix + "_tax_amount", Double.class));
        entity.setFreeText(converter.fromRow(row, prefix + "_free_text", String.class));
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
        entity.setDelflg(converter.fromRow(row, prefix + "_delflg", Boolean.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", String.class));
        entity.setCreatedAt(converter.fromRow(row, prefix + "_created_at", ZonedDateTime.class));
        entity.setUpdatedAt(converter.fromRow(row, prefix + "_updated_at", ZonedDateTime.class));
        entity.setDeletedAt(converter.fromRow(row, prefix + "_deleted_at", ZonedDateTime.class));
        entity.setDeletedBy(converter.fromRow(row, prefix + "_deleted_by", String.class));
        entity.setBillerId(converter.fromRow(row, prefix + "_biller_id", Long.class));
        return entity;
    }
}
