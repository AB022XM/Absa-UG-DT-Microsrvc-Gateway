package ug.co.absa.microsrvc.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.time.ZonedDateTime;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;
import ug.co.absa.microsrvc.gateway.domain.DTransactionHistory;
import ug.co.absa.microsrvc.gateway.domain.enumeration.Channel;
import ug.co.absa.microsrvc.gateway.domain.enumeration.Currency;
import ug.co.absa.microsrvc.gateway.domain.enumeration.ModeOfPayment;
import ug.co.absa.microsrvc.gateway.domain.enumeration.TranStatus;

/**
 * Converter between {@link Row} to {@link DTransactionHistory}, with proper type conversions.
 */
@Service
public class DTransactionHistoryRowMapper implements BiFunction<Row, String, DTransactionHistory> {

    private final ColumnConverter converter;

    public DTransactionHistoryRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link DTransactionHistory} stored in the database.
     */
    @Override
    public DTransactionHistory apply(Row row, String prefix) {
        DTransactionHistory entity = new DTransactionHistory();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setRecordId(converter.fromRow(row, prefix + "_record_id", String.class));
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
        entity.setModeOfPayment(converter.fromRow(row, prefix + "_mode_of_payment", ModeOfPayment.class));
        entity.setRetries(converter.fromRow(row, prefix + "_retries", Integer.class));
        entity.setPosted(converter.fromRow(row, prefix + "_posted", Boolean.class));
        entity.setApiId(converter.fromRow(row, prefix + "_api_id", String.class));
        entity.setApiVersion(converter.fromRow(row, prefix + "_api_version", String.class));
        entity.setPostingApi(converter.fromRow(row, prefix + "_posting_api", String.class));
        entity.setIsPosted(converter.fromRow(row, prefix + "_is_posted", Boolean.class));
        entity.setPostedBy(converter.fromRow(row, prefix + "_posted_by", String.class));
        entity.setPostedDate(converter.fromRow(row, prefix + "_posted_date", String.class));
        entity.setInternalErrorCode(converter.fromRow(row, prefix + "_internal_error_code", String.class));
        entity.setExternalErrorCode(converter.fromRow(row, prefix + "_external_error_code", String.class));
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
        entity.setTranFreeField12(converter.fromRow(row, prefix + "_tran_free_field_12", String.class));
        entity.setCreatedAt(converter.fromRow(row, prefix + "_created_at", ZonedDateTime.class));
        entity.setCreatedBy(converter.fromRow(row, prefix + "_created_by", String.class));
        entity.setUpdatedAt(converter.fromRow(row, prefix + "_updated_at", ZonedDateTime.class));
        entity.setUpdatedBy(converter.fromRow(row, prefix + "_updated_by", String.class));
        entity.setCustomerId(converter.fromRow(row, prefix + "_customer_id", Long.class));
        return entity;
    }
}
