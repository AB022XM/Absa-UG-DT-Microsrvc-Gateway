package ug.co.absa.microsrvc.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;
import ug.co.absa.microsrvc.gateway.domain.Customer;

/**
 * Converter between {@link Row} to {@link Customer}, with proper type conversions.
 */
@Service
public class CustomerRowMapper implements BiFunction<Row, String, Customer> {

    private final ColumnConverter converter;

    public CustomerRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link Customer} stored in the database.
     */
    @Override
    public Customer apply(Row row, String prefix) {
        Customer entity = new Customer();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setAbsaTranRef(converter.fromRow(row, prefix + "_absa_tran_ref", UUID.class));
        entity.setBillerId(converter.fromRow(row, prefix + "_biller_id", String.class));
        entity.setPaymentItemCode(converter.fromRow(row, prefix + "_payment_item_code", String.class));
        entity.setDtDTransactionId(converter.fromRow(row, prefix + "_dt_d_transaction_id", String.class));
        entity.setAmolDTransactionId(converter.fromRow(row, prefix + "_amol_d_transaction_id", String.class));
        entity.setTransactionReferenceNumber(converter.fromRow(row, prefix + "_transaction_reference_number", String.class));
        entity.setToken(converter.fromRow(row, prefix + "_token", String.class));
        entity.setTransferId(converter.fromRow(row, prefix + "_transfer_id", String.class));
        entity.setRequestId(converter.fromRow(row, prefix + "_request_id", Integer.class));
        entity.setAccountName(converter.fromRow(row, prefix + "_account_name", String.class));
        entity.setReturnCode(converter.fromRow(row, prefix + "_return_code", String.class));
        entity.setReturnMessage(converter.fromRow(row, prefix + "_return_message", String.class));
        entity.setExternalTXNid(converter.fromRow(row, prefix + "_external_tx_nid", String.class));
        entity.setTransactionDate(converter.fromRow(row, prefix + "_transaction_date", ZonedDateTime.class));
        entity.setCustomerId(converter.fromRow(row, prefix + "_customer_id", String.class));
        entity.setCustomerProduct(converter.fromRow(row, prefix + "_customer_product", String.class));
        entity.setCustomerType(converter.fromRow(row, prefix + "_customer_type", String.class));
        entity.setAccountCategory(converter.fromRow(row, prefix + "_account_category", String.class));
        entity.setAccountType(converter.fromRow(row, prefix + "_account_type", String.class));
        entity.setAccountNumber(converter.fromRow(row, prefix + "_account_number", String.class));
        entity.setPhoneNumber(converter.fromRow(row, prefix + "_phone_number", String.class));
        entity.setChannel(converter.fromRow(row, prefix + "_channel", String.class));
        entity.setTranFreeField1(converter.fromRow(row, prefix + "_tran_free_field_1", String.class));
        entity.setTranFreeField2(converter.fromRow(row, prefix + "_tran_free_field_2", String.class));
        entity.setTranFreeField3(converter.fromRow(row, prefix + "_tran_free_field_3", String.class));
        entity.setTranFreeField4(converter.fromRow(row, prefix + "_tran_free_field_4", String.class));
        entity.setTranFreeField5(converter.fromRow(row, prefix + "_tran_free_field_5", String.class));
        entity.setTranFreeField6(converter.fromRow(row, prefix + "_tran_free_field_6", String.class));
        entity.setTranFreeField7(converter.fromRow(row, prefix + "_tran_free_field_7", String.class));
        entity.setTranFreeField8(converter.fromRow(row, prefix + "_tran_free_field_8", String.class));
        entity.setTranFreeField9(converter.fromRow(row, prefix + "_tran_free_field_9", String.class));
        entity.setTranFreeField10(converter.fromRow(row, prefix + "_tran_free_field_10", String.class));
        entity.setTranFreeField11(converter.fromRow(row, prefix + "_tran_free_field_11", String.class));
        entity.setTranFreeField12(converter.fromRow(row, prefix + "_tran_free_field_12", String.class));
        entity.setTranFreeField13(converter.fromRow(row, prefix + "_tran_free_field_13", String.class));
        entity.setTranFreeField14(converter.fromRow(row, prefix + "_tran_free_field_14", String.class));
        entity.setTranFreeField15(converter.fromRow(row, prefix + "_tran_free_field_15", String.class));
        entity.setTranFreeField16(converter.fromRow(row, prefix + "_tran_free_field_16", String.class));
        entity.setTranFreeField17(converter.fromRow(row, prefix + "_tran_free_field_17", String.class));
        entity.setTranFreeField18(converter.fromRow(row, prefix + "_tran_free_field_18", BigDecimal.class));
        entity.setTranFreeField19(converter.fromRow(row, prefix + "_tran_free_field_19", Integer.class));
        entity.setTranFreeField20(converter.fromRow(row, prefix + "_tran_free_field_20", Boolean.class));
        entity.setTranFreeField21(converter.fromRow(row, prefix + "_tran_free_field_21", String.class));
        entity.setTranFreeField22(converter.fromRow(row, prefix + "_tran_free_field_22", String.class));
        entity.setTranFreeField23ContentType(converter.fromRow(row, prefix + "_tran_free_field_23_content_type", String.class));
        entity.setTranFreeField23(converter.fromRow(row, prefix + "_tran_free_field_23", byte[].class));
        entity.setTranFreeField24(converter.fromRow(row, prefix + "_tran_free_field_24", ZonedDateTime.class));
        entity.setTranFreeField25(converter.fromRow(row, prefix + "_tran_free_field_25", String.class));
        entity.setTranFreeField26(converter.fromRow(row, prefix + "_tran_free_field_26", String.class));
        entity.setTranFreeField27(converter.fromRow(row, prefix + "_tran_free_field_27", String.class));
        entity.setTranFreeField28(converter.fromRow(row, prefix + "_tran_free_field_28", String.class));
        entity.setTranFreeField29(converter.fromRow(row, prefix + "_tran_free_field_29", String.class));
        entity.setTranFreeField30(converter.fromRow(row, prefix + "_tran_free_field_30", String.class));
        entity.setTranFreeField31(converter.fromRow(row, prefix + "_tran_free_field_31", String.class));
        entity.setTranFreeField32(converter.fromRow(row, prefix + "_tran_free_field_32", String.class));
        entity.setTranFreeField33(converter.fromRow(row, prefix + "_tran_free_field_33", String.class));
        entity.setCreatedAt(converter.fromRow(row, prefix + "_created_at", ZonedDateTime.class));
        entity.setCreatedBy(converter.fromRow(row, prefix + "_created_by", String.class));
        entity.setUpdatedAt(converter.fromRow(row, prefix + "_updated_at", ZonedDateTime.class));
        entity.setUpdatedBy(converter.fromRow(row, prefix + "_updated_by", String.class));
        return entity;
    }
}
