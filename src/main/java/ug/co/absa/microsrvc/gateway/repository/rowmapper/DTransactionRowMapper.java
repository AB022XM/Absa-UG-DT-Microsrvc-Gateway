package ug.co.absa.microsrvc.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;
import ug.co.absa.microsrvc.gateway.domain.DTransaction;
import ug.co.absa.microsrvc.gateway.domain.enumeration.Channel;
import ug.co.absa.microsrvc.gateway.domain.enumeration.ErrorCodes;
import ug.co.absa.microsrvc.gateway.domain.enumeration.ErrorMessage;
import ug.co.absa.microsrvc.gateway.domain.enumeration.TranStatus;

/**
 * Converter between {@link Row} to {@link DTransaction}, with proper type conversions.
 */
@Service
public class DTransactionRowMapper implements BiFunction<Row, String, DTransaction> {

    private final ColumnConverter converter;

    public DTransactionRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link DTransaction} stored in the database.
     */
    @Override
    public DTransaction apply(Row row, String prefix) {
        DTransaction entity = new DTransaction();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setAbsaTranRef(converter.fromRow(row, prefix + "_absa_tran_ref", UUID.class));
        entity.setBillerId(converter.fromRow(row, prefix + "_biller_id", String.class));
        entity.setPaymentItemCode(converter.fromRow(row, prefix + "_payment_item_code", String.class));
        entity.setDtDTransactionId(converter.fromRow(row, prefix + "_dt_d_transaction_id", String.class));
        entity.setAmolDTransactionId(converter.fromRow(row, prefix + "_amol_d_transaction_id", String.class));
        entity.setTransactionReferenceNumber(converter.fromRow(row, prefix + "_transaction_reference_number", String.class));
        entity.setExternalTranid(converter.fromRow(row, prefix + "_external_tranid", String.class));
        entity.setToken(converter.fromRow(row, prefix + "_token", String.class));
        entity.setTransferId(converter.fromRow(row, prefix + "_transfer_id", String.class));
        entity.setProductCode(converter.fromRow(row, prefix + "_product_code", String.class));
        entity.setPaymentChannelCode(converter.fromRow(row, prefix + "_payment_channel_code", Channel.class));
        entity.setAccountNumber(converter.fromRow(row, prefix + "_account_number", String.class));
        entity.setAmount(converter.fromRow(row, prefix + "_amount", BigDecimal.class));
        entity.setDebitAmount(converter.fromRow(row, prefix + "_debit_amount", BigDecimal.class));
        entity.setCreditAmount(converter.fromRow(row, prefix + "_credit_amount", BigDecimal.class));
        entity.setSettlementAmount(converter.fromRow(row, prefix + "_settlement_amount", BigDecimal.class));
        entity.setSettlementDate(converter.fromRow(row, prefix + "_settlement_date", ZonedDateTime.class));
        entity.setTransactionDate(converter.fromRow(row, prefix + "_transaction_date", ZonedDateTime.class));
        entity.setIsRetried(converter.fromRow(row, prefix + "_is_retried", Boolean.class));
        entity.setLastRetryDate(converter.fromRow(row, prefix + "_last_retry_date", ZonedDateTime.class));
        entity.setFirstRetryDate(converter.fromRow(row, prefix + "_first_retry_date", ZonedDateTime.class));
        entity.setRetryResposeCode(converter.fromRow(row, prefix + "_retry_respose_code", ErrorCodes.class));
        entity.setRetryResponseMessage(converter.fromRow(row, prefix + "_retry_response_message", ErrorMessage.class));
        entity.setRetryCount(converter.fromRow(row, prefix + "_retry_count", Integer.class));
        entity.setIsRetriableFlg(converter.fromRow(row, prefix + "_is_retriable_flg", Boolean.class));
        entity.setDoNotRetryDTransaction(converter.fromRow(row, prefix + "_do_not_retry_d_transaction", Boolean.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", TranStatus.class));
        entity.setStatusCode(converter.fromRow(row, prefix + "_status_code", String.class));
        entity.setStatusDetails(converter.fromRow(row, prefix + "_status_details", String.class));
        entity.setRetries(converter.fromRow(row, prefix + "_retries", Integer.class));
        entity.setTimestamp(converter.fromRow(row, prefix + "_timestamp", ZonedDateTime.class));
        entity.setPostedBy(converter.fromRow(row, prefix + "_posted_by", String.class));
        entity.setPostedDate(converter.fromRow(row, prefix + "_posted_date", String.class));
        entity.setInternalErrorCode(converter.fromRow(row, prefix + "_internal_error_code", String.class));
        entity.setExternalErrorCode(converter.fromRow(row, prefix + "_external_error_code", String.class));
        entity.setIsCrossCurrency(converter.fromRow(row, prefix + "_is_cross_currency", Boolean.class));
        entity.setIsCrossBank(converter.fromRow(row, prefix + "_is_cross_bank", Boolean.class));
        entity.setCurrency(converter.fromRow(row, prefix + "_currency", String.class));
        entity.setCreditAccount(converter.fromRow(row, prefix + "_credit_account", String.class));
        entity.setDebitAccount(converter.fromRow(row, prefix + "_debit_account", String.class));
        entity.setPhoneNumber(converter.fromRow(row, prefix + "_phone_number", String.class));
        entity.setCustomerNumber(converter.fromRow(row, prefix + "_customer_number", String.class));
        entity.setTranStatus(converter.fromRow(row, prefix + "_tran_status", TranStatus.class));
        entity.setTranStatusDetails(converter.fromRow(row, prefix + "_tran_status_details", String.class));
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
        entity.setTranFreeField13(converter.fromRow(row, prefix + "_tran_free_field_13", Integer.class));
        entity.setTranFreeField14ContentType(converter.fromRow(row, prefix + "_tran_free_field_14_content_type", String.class));
        entity.setTranFreeField14(converter.fromRow(row, prefix + "_tran_free_field_14", byte[].class));
        entity.setTranFreeField15(converter.fromRow(row, prefix + "_tran_free_field_15", String.class));
        entity.setTranFreeField16(converter.fromRow(row, prefix + "_tran_free_field_16", ZonedDateTime.class));
        entity.setTranFreeField17(converter.fromRow(row, prefix + "_tran_free_field_17", Boolean.class));
        entity.setTranFreeField18(converter.fromRow(row, prefix + "_tran_free_field_18", String.class));
        entity.setTranFreeField19(converter.fromRow(row, prefix + "_tran_free_field_19", String.class));
        entity.setTranFreeField20(converter.fromRow(row, prefix + "_tran_free_field_20", ZonedDateTime.class));
        entity.setTranFreeField21(converter.fromRow(row, prefix + "_tran_free_field_21", Instant.class));
        entity.setTranFreeField22(converter.fromRow(row, prefix + "_tran_free_field_22", Boolean.class));
        entity.setTranFreeField23(converter.fromRow(row, prefix + "_tran_free_field_23", Instant.class));
        entity.setTranFreeField24(converter.fromRow(row, prefix + "_tran_free_field_24", String.class));
        entity.setTranFreeField25(converter.fromRow(row, prefix + "_tran_free_field_25", String.class));
        entity.setTranFreeField26(converter.fromRow(row, prefix + "_tran_free_field_26", String.class));
        entity.setTranFreeField27(converter.fromRow(row, prefix + "_tran_free_field_27", String.class));
        entity.setTranFreeField28(converter.fromRow(row, prefix + "_tran_free_field_28", String.class));
        entity.setCreatedAt(converter.fromRow(row, prefix + "_created_at", ZonedDateTime.class));
        entity.setCreatedBy(converter.fromRow(row, prefix + "_created_by", String.class));
        entity.setUpdatedAt(converter.fromRow(row, prefix + "_updated_at", ZonedDateTime.class));
        entity.setUpdatedBy(converter.fromRow(row, prefix + "_updated_by", String.class));
        entity.setTransactionId(converter.fromRow(row, prefix + "_transaction_id", Long.class));
        entity.setTransactionId(converter.fromRow(row, prefix + "_transaction_id", Long.class));
        entity.setTransactionId(converter.fromRow(row, prefix + "_transaction_id", Long.class));
        entity.setCustomerId(converter.fromRow(row, prefix + "_customer_id", Long.class));
        return entity;
    }
}
