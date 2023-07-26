package ug.co.absa.microsrvc.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;
import ug.co.absa.microsrvc.gateway.domain.DTransactionDetails;
import ug.co.absa.microsrvc.gateway.domain.enumeration.Channel;
import ug.co.absa.microsrvc.gateway.domain.enumeration.ModeOfPayment;
import ug.co.absa.microsrvc.gateway.domain.enumeration.TranStatus;

/**
 * Converter between {@link Row} to {@link DTransactionDetails}, with proper type conversions.
 */
@Service
public class DTransactionDetailsRowMapper implements BiFunction<Row, String, DTransactionDetails> {

    private final ColumnConverter converter;

    public DTransactionDetailsRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link DTransactionDetails} stored in the database.
     */
    @Override
    public DTransactionDetails apply(Row row, String prefix) {
        DTransactionDetails entity = new DTransactionDetails();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setAbsaTranRef(converter.fromRow(row, prefix + "_absa_tran_ref", UUID.class));
        entity.setBillerId(converter.fromRow(row, prefix + "_biller_id", String.class));
        entity.setPaymentItemCode(converter.fromRow(row, prefix + "_payment_item_code", String.class));
        entity.setDtDTransactionId(converter.fromRow(row, prefix + "_dt_d_transaction_id", String.class));
        entity.setAmolDTransactionId(converter.fromRow(row, prefix + "_amol_d_transaction_id", String.class));
        entity.setTransactionReferenceNumber(converter.fromRow(row, prefix + "_transaction_reference_number", String.class));
        entity.setToken(converter.fromRow(row, prefix + "_token", String.class));
        entity.setTransferId(converter.fromRow(row, prefix + "_transfer_id", String.class));
        entity.setProductCode(converter.fromRow(row, prefix + "_product_code", String.class));
        entity.setPaymentChannelCode(converter.fromRow(row, prefix + "_payment_channel_code", Channel.class));
        entity.setDebitAccountNumber(converter.fromRow(row, prefix + "_debit_account_number", String.class));
        entity.setCreditAccountNumber(converter.fromRow(row, prefix + "_credit_account_number", String.class));
        entity.setDebitAmount(converter.fromRow(row, prefix + "_debit_amount", Integer.class));
        entity.setCreditAmount(converter.fromRow(row, prefix + "_credit_amount", Integer.class));
        entity.setTransactionAmount(converter.fromRow(row, prefix + "_transaction_amount", Integer.class));
        entity.setDebitDate(converter.fromRow(row, prefix + "_debit_date", ZonedDateTime.class));
        entity.setCreditDate(converter.fromRow(row, prefix + "_credit_date", ZonedDateTime.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", TranStatus.class));
        entity.setSettlementDate(converter.fromRow(row, prefix + "_settlement_date", ZonedDateTime.class));
        entity.setDebitCurrency(converter.fromRow(row, prefix + "_debit_currency", String.class));
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
        entity.setNarration4(converter.fromRow(row, prefix + "_narration_4", String.class));
        entity.setNarration5(converter.fromRow(row, prefix + "_narration_5", String.class));
        entity.setNarration6(converter.fromRow(row, prefix + "_narration_6", String.class));
        entity.setNarration7(converter.fromRow(row, prefix + "_narration_7", String.class));
        entity.setNarration8(converter.fromRow(row, prefix + "_narration_8", String.class));
        entity.setNarration9(converter.fromRow(row, prefix + "_narration_9", String.class));
        entity.setNarration10(converter.fromRow(row, prefix + "_narration_10", String.class));
        entity.setNarration11(converter.fromRow(row, prefix + "_narration_11", String.class));
        entity.setNarration12(converter.fromRow(row, prefix + "_narration_12", String.class));
        entity.setModeOfPayment(converter.fromRow(row, prefix + "_mode_of_payment", ModeOfPayment.class));
        entity.setRetries(converter.fromRow(row, prefix + "_retries", Integer.class));
        entity.setPosted(converter.fromRow(row, prefix + "_posted", Boolean.class));
        entity.setApiId(converter.fromRow(row, prefix + "_api_id", String.class));
        entity.setApiVersion(converter.fromRow(row, prefix + "_api_version", String.class));
        entity.setPostingApi(converter.fromRow(row, prefix + "_posting_api", String.class));
        entity.setIsPosted(converter.fromRow(row, prefix + "_is_posted", Boolean.class));
        entity.setPostedBy(converter.fromRow(row, prefix + "_posted_by", String.class));
        entity.setPostedDate(converter.fromRow(row, prefix + "_posted_date", String.class));
        entity.setTranFreeField1(converter.fromRow(row, prefix + "_tran_free_field_1", String.class));
        entity.setTranFreeField3(converter.fromRow(row, prefix + "_tran_free_field_3", String.class));
        entity.setTranFreeField4(converter.fromRow(row, prefix + "_tran_free_field_4", String.class));
        entity.setTranFreeField5(converter.fromRow(row, prefix + "_tran_free_field_5", String.class));
        entity.setTranFreeField6(converter.fromRow(row, prefix + "_tran_free_field_6", String.class));
        entity.setTranFreeField7(converter.fromRow(row, prefix + "_tran_free_field_7", String.class));
        entity.setTranFreeField8(converter.fromRow(row, prefix + "_tran_free_field_8", String.class));
        entity.setTranFreeField9(converter.fromRow(row, prefix + "_tran_free_field_9", String.class));
        entity.setTranFreeField10(converter.fromRow(row, prefix + "_tran_free_field_10", String.class));
        entity.setTranFreeField11(converter.fromRow(row, prefix + "_tran_free_field_11", String.class));
        entity.setTranFreeField12ContentType(converter.fromRow(row, prefix + "_tran_free_field_12_content_type", String.class));
        entity.setTranFreeField12(converter.fromRow(row, prefix + "_tran_free_field_12", byte[].class));
        entity.setTranFreeField13(converter.fromRow(row, prefix + "_tran_free_field_13", String.class));
        entity.setTranFreeField14(converter.fromRow(row, prefix + "_tran_free_field_14", String.class));
        entity.setTranFreeField15ContentType(converter.fromRow(row, prefix + "_tran_free_field_15_content_type", String.class));
        entity.setTranFreeField15(converter.fromRow(row, prefix + "_tran_free_field_15", byte[].class));
        entity.setTranFreeField16(converter.fromRow(row, prefix + "_tran_free_field_16", String.class));
        entity.setTranFreeField17(converter.fromRow(row, prefix + "_tran_free_field_17", String.class));
        entity.setTranFreeField18(converter.fromRow(row, prefix + "_tran_free_field_18", String.class));
        entity.setTranFreeField19(converter.fromRow(row, prefix + "_tran_free_field_19", String.class));
        entity.setTranFreeField20(converter.fromRow(row, prefix + "_tran_free_field_20", String.class));
        entity.setTranFreeField21(converter.fromRow(row, prefix + "_tran_free_field_21", String.class));
        entity.setTranFreeField22(converter.fromRow(row, prefix + "_tran_free_field_22", String.class));
        entity.setTranFreeField23(converter.fromRow(row, prefix + "_tran_free_field_23", String.class));
        entity.setTranFreeField24(converter.fromRow(row, prefix + "_tran_free_field_24", String.class));
        entity.setTranFreeField25(converter.fromRow(row, prefix + "_tran_free_field_25", String.class));
        entity.setTranFreeField26(converter.fromRow(row, prefix + "_tran_free_field_26", String.class));
        entity.setTranFreeField27(converter.fromRow(row, prefix + "_tran_free_field_27", String.class));
        entity.setTranFreeField28(converter.fromRow(row, prefix + "_tran_free_field_28", String.class));
        entity.setInternalErrorCode(converter.fromRow(row, prefix + "_internal_error_code", String.class));
        entity.setExternalErrorCode(converter.fromRow(row, prefix + "_external_error_code", String.class));
        entity.setCustomerId(converter.fromRow(row, prefix + "_customer_id", Long.class));
        return entity;
    }
}
