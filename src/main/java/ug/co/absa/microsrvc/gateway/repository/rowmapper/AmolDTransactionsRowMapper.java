package ug.co.absa.microsrvc.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;
import ug.co.absa.microsrvc.gateway.domain.AmolDTransactions;
import ug.co.absa.microsrvc.gateway.domain.enumeration.Channel;
import ug.co.absa.microsrvc.gateway.domain.enumeration.Currency;
import ug.co.absa.microsrvc.gateway.domain.enumeration.TranStatus;

/**
 * Converter between {@link Row} to {@link AmolDTransactions}, with proper type conversions.
 */
@Service
public class AmolDTransactionsRowMapper implements BiFunction<Row, String, AmolDTransactions> {

    private final ColumnConverter converter;

    public AmolDTransactionsRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link AmolDTransactions} stored in the database.
     */
    @Override
    public AmolDTransactions apply(Row row, String prefix) {
        AmolDTransactions entity = new AmolDTransactions();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setAbsaTranRef(converter.fromRow(row, prefix + "_absa_tran_ref", UUID.class));
        entity.setBillerId(converter.fromRow(row, prefix + "_biller_id", String.class));
        entity.setDtDTransactionId(converter.fromRow(row, prefix + "_dt_d_transaction_id", String.class));
        entity.setAmolDTransactionId(converter.fromRow(row, prefix + "_amol_d_transaction_id", String.class));
        entity.setTransactionReferenceNumber(converter.fromRow(row, prefix + "_transaction_reference_number", String.class));
        entity.setToken(converter.fromRow(row, prefix + "_token", String.class));
        entity.setTransferId(converter.fromRow(row, prefix + "_transfer_id", String.class));
        entity.setExternalTxnId(converter.fromRow(row, prefix + "_external_txn_id", String.class));
        entity.setCustomerReference(converter.fromRow(row, prefix + "_customer_reference", String.class));
        entity.setDebitAccountNumber(converter.fromRow(row, prefix + "_debit_account_number", String.class));
        entity.setCreditAccountNumber(converter.fromRow(row, prefix + "_credit_account_number", String.class));
        entity.setDebitAmount(converter.fromRow(row, prefix + "_debit_amount", BigDecimal.class));
        entity.setCreditAmount(converter.fromRow(row, prefix + "_credit_amount", BigDecimal.class));
        entity.setTransactionAmount(converter.fromRow(row, prefix + "_transaction_amount", BigDecimal.class));
        entity.setDebitDate(converter.fromRow(row, prefix + "_debit_date", ZonedDateTime.class));
        entity.setCreditDate(converter.fromRow(row, prefix + "_credit_date", ZonedDateTime.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", TranStatus.class));
        entity.setSettlementDate(converter.fromRow(row, prefix + "_settlement_date", ZonedDateTime.class));
        entity.setDebitCurrency(converter.fromRow(row, prefix + "_debit_currency", Currency.class));
        entity.setTimestamp(converter.fromRow(row, prefix + "_timestamp", ZonedDateTime.class));
        entity.setPhoneNumber(converter.fromRow(row, prefix + "_phone_number", String.class));
        entity.setEmail(converter.fromRow(row, prefix + "_email", String.class));
        entity.setPayerName(converter.fromRow(row, prefix + "_payer_name", String.class));
        entity.setPayerAddress(converter.fromRow(row, prefix + "_payer_address", String.class));
        entity.setPayerEmail(converter.fromRow(row, prefix + "_payer_email", String.class));
        entity.setPayerPhoneNumber(converter.fromRow(row, prefix + "_payer_phone_number", String.class));
        entity.setPayerNarration(converter.fromRow(row, prefix + "_payer_narration", String.class));
        entity.setPayerBranchId(converter.fromRow(row, prefix + "_payer_branch_id", String.class));
        entity.setBeneficiaryName(converter.fromRow(row, prefix + "_beneficiary_name", String.class));
        entity.setBeneficiaryAccount(converter.fromRow(row, prefix + "_beneficiary_account", String.class));
        entity.setBeneficiaryBranchId(converter.fromRow(row, prefix + "_beneficiary_branch_id", String.class));
        entity.setBeneficiaryPhoneNumber(converter.fromRow(row, prefix + "_beneficiary_phone_number", String.class));
        entity.setTranStatus(converter.fromRow(row, prefix + "_tran_status", TranStatus.class));
        entity.setNarration1(converter.fromRow(row, prefix + "_narration_1", String.class));
        entity.setNarration2(converter.fromRow(row, prefix + "_narration_2", String.class));
        entity.setNarration3(converter.fromRow(row, prefix + "_narration_3", String.class));
        entity.setTaxAmount(converter.fromRow(row, prefix + "_tax_amount", Integer.class));
        entity.setTaxGLAccount(converter.fromRow(row, prefix + "_tax_gl_account", String.class));
        entity.setTaxNarration(converter.fromRow(row, prefix + "_tax_narration", String.class));
        entity.setInternalErrorCode(converter.fromRow(row, prefix + "_internal_error_code", String.class));
        entity.setExternalErrorCode(converter.fromRow(row, prefix + "_external_error_code", String.class));
        entity.setPayeeBranchId(converter.fromRow(row, prefix + "_payee_branch_id", String.class));
        entity.setPayeeProductInstanceReference(converter.fromRow(row, prefix + "_payee_product_instance_reference", String.class));
        entity.setPayeeProductCode(converter.fromRow(row, prefix + "_payee_product_code", String.class));
        entity.setPayeeProductName(converter.fromRow(row, prefix + "_payee_product_name", String.class));
        entity.setPayeeProductDescription(converter.fromRow(row, prefix + "_payee_product_description", String.class));
        entity.setPayeeProductUnitOfMeasure(converter.fromRow(row, prefix + "_payee_product_unit_of_measure", String.class));
        entity.setPayeeProductQuantity(converter.fromRow(row, prefix + "_payee_product_quantity", String.class));
        entity.setSubCategoryCode(converter.fromRow(row, prefix + "_sub_category_code", String.class));
        entity.setPayerBankCode(converter.fromRow(row, prefix + "_payer_bank_code", String.class));
        entity.setPayerBankName(converter.fromRow(row, prefix + "_payer_bank_name", String.class));
        entity.setPayerBankAddress(converter.fromRow(row, prefix + "_payer_bank_address", String.class));
        entity.setPayerBankCity(converter.fromRow(row, prefix + "_payer_bank_city", String.class));
        entity.setPayerBankState(converter.fromRow(row, prefix + "_payer_bank_state", String.class));
        entity.setPayerBankCountry(converter.fromRow(row, prefix + "_payer_bank_country", String.class));
        entity.setPayerBankPostalCode(converter.fromRow(row, prefix + "_payer_bank_postal_code", String.class));
        entity.setCheckerId(converter.fromRow(row, prefix + "_checker_id", String.class));
        entity.setRemittanceInformation(converter.fromRow(row, prefix + "_remittance_information", String.class));
        entity.setPaymentChannelCode(converter.fromRow(row, prefix + "_payment_channel_code", Channel.class));
        entity.setFeeAmount(converter.fromRow(row, prefix + "_fee_amount", Integer.class));
        entity.setFeeGLAccount(converter.fromRow(row, prefix + "_fee_gl_account", String.class));
        entity.setFeeNarration(converter.fromRow(row, prefix + "_fee_narration", String.class));
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
        entity.setResponseCode(converter.fromRow(row, prefix + "_response_code", String.class));
        entity.setResponseMessage(converter.fromRow(row, prefix + "_response_message", String.class));
        entity.setResponseDetails(converter.fromRow(row, prefix + "_response_details", String.class));
        entity.setTransferReferenceId(converter.fromRow(row, prefix + "_transfer_reference_id", String.class));
        entity.setTransferStatus(converter.fromRow(row, prefix + "_transfer_status", String.class));
        entity.setResponseDateTime(converter.fromRow(row, prefix + "_response_date_time", ZonedDateTime.class));
        entity.setCreatedAt(converter.fromRow(row, prefix + "_created_at", ZonedDateTime.class));
        entity.setCreatedBy(converter.fromRow(row, prefix + "_created_by", String.class));
        entity.setUpdatedAt(converter.fromRow(row, prefix + "_updated_at", ZonedDateTime.class));
        entity.setUpdatedBy(converter.fromRow(row, prefix + "_updated_by", String.class));
        entity.setDTransactionId(converter.fromRow(row, prefix + "_d_transaction_id", Long.class));
        return entity;
    }
}
