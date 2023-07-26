package ug.co.absa.microsrvc.gateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import ug.co.absa.microsrvc.gateway.domain.enumeration.Channel;
import ug.co.absa.microsrvc.gateway.domain.enumeration.ErrorCodes;
import ug.co.absa.microsrvc.gateway.domain.enumeration.ErrorMessage;
import ug.co.absa.microsrvc.gateway.domain.enumeration.TranStatus;

/**
 * A DTransaction.
 */
@Table("d_transaction")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "dtransaction")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DTransaction implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("absa_tran_ref")
    private UUID absaTranRef;

    @NotNull(message = "must not be null")
    @Column("biller_id")
    private String billerId;

    @NotNull(message = "must not be null")
    @Column("payment_item_code")
    private String paymentItemCode;

    @Column("dt_d_transaction_id")
    private String dtDTransactionId;

    @Column("amol_d_transaction_id")
    private String amolDTransactionId;

    @Column("transaction_reference_number")
    private String transactionReferenceNumber;

    @Column("external_tranid")
    private String externalTranid;

    @Column("token")
    private String token;

    @Column("transfer_id")
    private String transferId;

    @NotNull(message = "must not be null")
    @Column("product_code")
    private String productCode;

    @NotNull(message = "must not be null")
    @Column("payment_channel_code")
    private Channel paymentChannelCode;

    @NotNull(message = "must not be null")
    @Column("account_number")
    private String accountNumber;

    @NotNull(message = "must not be null")
    @Column("amount")
    private BigDecimal amount;

    @NotNull(message = "must not be null")
    @Column("debit_amount")
    private BigDecimal debitAmount;

    @Column("credit_amount")
    private BigDecimal creditAmount;

    @Column("settlement_amount")
    private BigDecimal settlementAmount;

    @Column("settlement_date")
    private ZonedDateTime settlementDate;

    @Column("transaction_date")
    private ZonedDateTime transactionDate;

    @Column("is_retried")
    private Boolean isRetried;

    @Column("last_retry_date")
    private ZonedDateTime lastRetryDate;

    @Column("first_retry_date")
    private ZonedDateTime firstRetryDate;

    @Column("retry_respose_code")
    private ErrorCodes retryResposeCode;

    @Column("retry_response_message")
    private ErrorMessage retryResponseMessage;

    @Column("retry_count")
    private Integer retryCount;

    @Column("is_retriable_flg")
    private Boolean isRetriableFlg;

    @Column("do_not_retry_d_transaction")
    private Boolean doNotRetryDTransaction;

    @Column("status")
    private TranStatus status;

    @Column("status_code")
    private String statusCode;

    @Column("status_details")
    private String statusDetails;

    @Column("retries")
    private Integer retries;

    @Column("timestamp")
    private ZonedDateTime timestamp;

    @Column("posted_by")
    private String postedBy;

    @Column("posted_date")
    private String postedDate;

    @Column("internal_error_code")
    private String internalErrorCode;

    @Column("external_error_code")
    private String externalErrorCode;

    @Column("is_cross_currency")
    private Boolean isCrossCurrency;

    @Column("is_cross_bank")
    private Boolean isCrossBank;

    @NotNull(message = "must not be null")
    @Column("currency")
    private String currency;

    @Column("credit_account")
    private String creditAccount;

    @Column("debit_account")
    private String debitAccount;

    @Column("phone_number")
    private String phoneNumber;

    @Column("customer_number")
    private String customerNumber;

    @Column("tran_status")
    private TranStatus tranStatus;

    @Column("tran_status_details")
    private String tranStatusDetails;

    @Column("tran_free_field_1")
    private String tranFreeField1;

    @Column("tran_free_field_2")
    private String tranFreeField2;

    @Column("tran_free_field_3")
    private String tranFreeField3;

    @Column("tran_free_field_4")
    private String tranFreeField4;

    @Column("tran_free_field_5")
    private String tranFreeField5;

    @Column("tran_free_field_6")
    private String tranFreeField6;

    @Column("tran_free_field_7")
    private String tranFreeField7;

    @Column("tran_free_field_8")
    private String tranFreeField8;

    @Column("tran_free_field_9")
    private String tranFreeField9;

    @Column("tran_free_field_10")
    private String tranFreeField10;

    @Column("tran_free_field_11")
    private String tranFreeField11;

    @Column("tran_free_field_12")
    private String tranFreeField12;

    @Column("tran_free_field_13")
    private Integer tranFreeField13;

    @Column("tran_free_field_14")
    private byte[] tranFreeField14;

    @Column("tran_free_field_14_content_type")
    private String tranFreeField14ContentType;

    @Column("tran_free_field_15")
    private String tranFreeField15;

    @Column("tran_free_field_16")
    private ZonedDateTime tranFreeField16;

    @Column("tran_free_field_17")
    private Boolean tranFreeField17;

    @Column("tran_free_field_18")
    private String tranFreeField18;

    @Column("tran_free_field_19")
    private String tranFreeField19;

    @Column("tran_free_field_20")
    private ZonedDateTime tranFreeField20;

    @Column("tran_free_field_21")
    private Instant tranFreeField21;

    @Column("tran_free_field_22")
    private Boolean tranFreeField22;

    @Column("tran_free_field_23")
    private Instant tranFreeField23;

    @Column("tran_free_field_24")
    private String tranFreeField24;

    @Column("tran_free_field_25")
    private String tranFreeField25;

    @Column("tran_free_field_26")
    private String tranFreeField26;

    @Column("tran_free_field_27")
    private String tranFreeField27;

    @Column("tran_free_field_28")
    private String tranFreeField28;

    @NotNull(message = "must not be null")
    @Column("created_at")
    private ZonedDateTime createdAt;

    @Column("created_by")
    private String createdBy;

    @Column("updated_at")
    private ZonedDateTime updatedAt;

    @Column("updated_by")
    private String updatedBy;

    @Transient
    @Transient
    @Transient
    @Transient
    @Transient
    private DTransactionHistory transaction;

    @Transient
    @Transient
    @Transient
    @Transient
    @Transient
    private DTransactionSummary transaction;

    @Transient
    @Transient
    @Transient
    @Transient
    @Transient
    private DTransactionDetails transaction;

    @Transient
    @Transient
    @Transient
    @Transient
    @Transient
    @JsonIgnoreProperties(value = { "dTransaction" }, allowSetters = true)
    private Set<AmolDTransactions> transactions = new HashSet<>();

    @Transient
    @Transient
    @Transient
    @Transient
    @Transient
    @JsonIgnoreProperties(value = { "dTransaction" }, allowSetters = true)
    private Set<DTransactionChannel> transactions = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "customers", "customers", "customers", "customers" }, allowSetters = true)
    private Customer customer;

    @Column("transaction_id")
    private Long transactionId;

    @Column("transaction_id")
    private Long transactionId;

    @Column("transaction_id")
    private Long transactionId;

    @Column("customer_id")
    private Long customerId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DTransaction id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getAbsaTranRef() {
        return this.absaTranRef;
    }

    public DTransaction absaTranRef(UUID absaTranRef) {
        this.setAbsaTranRef(absaTranRef);
        return this;
    }

    public void setAbsaTranRef(UUID absaTranRef) {
        this.absaTranRef = absaTranRef;
    }

    public String getBillerId() {
        return this.billerId;
    }

    public DTransaction billerId(String billerId) {
        this.setBillerId(billerId);
        return this;
    }

    public void setBillerId(String billerId) {
        this.billerId = billerId;
    }

    public String getPaymentItemCode() {
        return this.paymentItemCode;
    }

    public DTransaction paymentItemCode(String paymentItemCode) {
        this.setPaymentItemCode(paymentItemCode);
        return this;
    }

    public void setPaymentItemCode(String paymentItemCode) {
        this.paymentItemCode = paymentItemCode;
    }

    public String getDtDTransactionId() {
        return this.dtDTransactionId;
    }

    public DTransaction dtDTransactionId(String dtDTransactionId) {
        this.setDtDTransactionId(dtDTransactionId);
        return this;
    }

    public void setDtDTransactionId(String dtDTransactionId) {
        this.dtDTransactionId = dtDTransactionId;
    }

    public String getAmolDTransactionId() {
        return this.amolDTransactionId;
    }

    public DTransaction amolDTransactionId(String amolDTransactionId) {
        this.setAmolDTransactionId(amolDTransactionId);
        return this;
    }

    public void setAmolDTransactionId(String amolDTransactionId) {
        this.amolDTransactionId = amolDTransactionId;
    }

    public String getTransactionReferenceNumber() {
        return this.transactionReferenceNumber;
    }

    public DTransaction transactionReferenceNumber(String transactionReferenceNumber) {
        this.setTransactionReferenceNumber(transactionReferenceNumber);
        return this;
    }

    public void setTransactionReferenceNumber(String transactionReferenceNumber) {
        this.transactionReferenceNumber = transactionReferenceNumber;
    }

    public String getExternalTranid() {
        return this.externalTranid;
    }

    public DTransaction externalTranid(String externalTranid) {
        this.setExternalTranid(externalTranid);
        return this;
    }

    public void setExternalTranid(String externalTranid) {
        this.externalTranid = externalTranid;
    }

    public String getToken() {
        return this.token;
    }

    public DTransaction token(String token) {
        this.setToken(token);
        return this;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTransferId() {
        return this.transferId;
    }

    public DTransaction transferId(String transferId) {
        this.setTransferId(transferId);
        return this;
    }

    public void setTransferId(String transferId) {
        this.transferId = transferId;
    }

    public String getProductCode() {
        return this.productCode;
    }

    public DTransaction productCode(String productCode) {
        this.setProductCode(productCode);
        return this;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Channel getPaymentChannelCode() {
        return this.paymentChannelCode;
    }

    public DTransaction paymentChannelCode(Channel paymentChannelCode) {
        this.setPaymentChannelCode(paymentChannelCode);
        return this;
    }

    public void setPaymentChannelCode(Channel paymentChannelCode) {
        this.paymentChannelCode = paymentChannelCode;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public DTransaction accountNumber(String accountNumber) {
        this.setAccountNumber(accountNumber);
        return this;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public DTransaction amount(BigDecimal amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount != null ? amount.stripTrailingZeros() : null;
    }

    public BigDecimal getDebitAmount() {
        return this.debitAmount;
    }

    public DTransaction debitAmount(BigDecimal debitAmount) {
        this.setDebitAmount(debitAmount);
        return this;
    }

    public void setDebitAmount(BigDecimal debitAmount) {
        this.debitAmount = debitAmount != null ? debitAmount.stripTrailingZeros() : null;
    }

    public BigDecimal getCreditAmount() {
        return this.creditAmount;
    }

    public DTransaction creditAmount(BigDecimal creditAmount) {
        this.setCreditAmount(creditAmount);
        return this;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount != null ? creditAmount.stripTrailingZeros() : null;
    }

    public BigDecimal getSettlementAmount() {
        return this.settlementAmount;
    }

    public DTransaction settlementAmount(BigDecimal settlementAmount) {
        this.setSettlementAmount(settlementAmount);
        return this;
    }

    public void setSettlementAmount(BigDecimal settlementAmount) {
        this.settlementAmount = settlementAmount != null ? settlementAmount.stripTrailingZeros() : null;
    }

    public ZonedDateTime getSettlementDate() {
        return this.settlementDate;
    }

    public DTransaction settlementDate(ZonedDateTime settlementDate) {
        this.setSettlementDate(settlementDate);
        return this;
    }

    public void setSettlementDate(ZonedDateTime settlementDate) {
        this.settlementDate = settlementDate;
    }

    public ZonedDateTime getTransactionDate() {
        return this.transactionDate;
    }

    public DTransaction transactionDate(ZonedDateTime transactionDate) {
        this.setTransactionDate(transactionDate);
        return this;
    }

    public void setTransactionDate(ZonedDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public Boolean getIsRetried() {
        return this.isRetried;
    }

    public DTransaction isRetried(Boolean isRetried) {
        this.setIsRetried(isRetried);
        return this;
    }

    public void setIsRetried(Boolean isRetried) {
        this.isRetried = isRetried;
    }

    public ZonedDateTime getLastRetryDate() {
        return this.lastRetryDate;
    }

    public DTransaction lastRetryDate(ZonedDateTime lastRetryDate) {
        this.setLastRetryDate(lastRetryDate);
        return this;
    }

    public void setLastRetryDate(ZonedDateTime lastRetryDate) {
        this.lastRetryDate = lastRetryDate;
    }

    public ZonedDateTime getFirstRetryDate() {
        return this.firstRetryDate;
    }

    public DTransaction firstRetryDate(ZonedDateTime firstRetryDate) {
        this.setFirstRetryDate(firstRetryDate);
        return this;
    }

    public void setFirstRetryDate(ZonedDateTime firstRetryDate) {
        this.firstRetryDate = firstRetryDate;
    }

    public ErrorCodes getRetryResposeCode() {
        return this.retryResposeCode;
    }

    public DTransaction retryResposeCode(ErrorCodes retryResposeCode) {
        this.setRetryResposeCode(retryResposeCode);
        return this;
    }

    public void setRetryResposeCode(ErrorCodes retryResposeCode) {
        this.retryResposeCode = retryResposeCode;
    }

    public ErrorMessage getRetryResponseMessage() {
        return this.retryResponseMessage;
    }

    public DTransaction retryResponseMessage(ErrorMessage retryResponseMessage) {
        this.setRetryResponseMessage(retryResponseMessage);
        return this;
    }

    public void setRetryResponseMessage(ErrorMessage retryResponseMessage) {
        this.retryResponseMessage = retryResponseMessage;
    }

    public Integer getRetryCount() {
        return this.retryCount;
    }

    public DTransaction retryCount(Integer retryCount) {
        this.setRetryCount(retryCount);
        return this;
    }

    public void setRetryCount(Integer retryCount) {
        this.retryCount = retryCount;
    }

    public Boolean getIsRetriableFlg() {
        return this.isRetriableFlg;
    }

    public DTransaction isRetriableFlg(Boolean isRetriableFlg) {
        this.setIsRetriableFlg(isRetriableFlg);
        return this;
    }

    public void setIsRetriableFlg(Boolean isRetriableFlg) {
        this.isRetriableFlg = isRetriableFlg;
    }

    public Boolean getDoNotRetryDTransaction() {
        return this.doNotRetryDTransaction;
    }

    public DTransaction doNotRetryDTransaction(Boolean doNotRetryDTransaction) {
        this.setDoNotRetryDTransaction(doNotRetryDTransaction);
        return this;
    }

    public void setDoNotRetryDTransaction(Boolean doNotRetryDTransaction) {
        this.doNotRetryDTransaction = doNotRetryDTransaction;
    }

    public TranStatus getStatus() {
        return this.status;
    }

    public DTransaction status(TranStatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(TranStatus status) {
        this.status = status;
    }

    public String getStatusCode() {
        return this.statusCode;
    }

    public DTransaction statusCode(String statusCode) {
        this.setStatusCode(statusCode);
        return this;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public String getStatusDetails() {
        return this.statusDetails;
    }

    public DTransaction statusDetails(String statusDetails) {
        this.setStatusDetails(statusDetails);
        return this;
    }

    public void setStatusDetails(String statusDetails) {
        this.statusDetails = statusDetails;
    }

    public Integer getRetries() {
        return this.retries;
    }

    public DTransaction retries(Integer retries) {
        this.setRetries(retries);
        return this;
    }

    public void setRetries(Integer retries) {
        this.retries = retries;
    }

    public ZonedDateTime getTimestamp() {
        return this.timestamp;
    }

    public DTransaction timestamp(ZonedDateTime timestamp) {
        this.setTimestamp(timestamp);
        return this;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getPostedBy() {
        return this.postedBy;
    }

    public DTransaction postedBy(String postedBy) {
        this.setPostedBy(postedBy);
        return this;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public String getPostedDate() {
        return this.postedDate;
    }

    public DTransaction postedDate(String postedDate) {
        this.setPostedDate(postedDate);
        return this;
    }

    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }

    public String getInternalErrorCode() {
        return this.internalErrorCode;
    }

    public DTransaction internalErrorCode(String internalErrorCode) {
        this.setInternalErrorCode(internalErrorCode);
        return this;
    }

    public void setInternalErrorCode(String internalErrorCode) {
        this.internalErrorCode = internalErrorCode;
    }

    public String getExternalErrorCode() {
        return this.externalErrorCode;
    }

    public DTransaction externalErrorCode(String externalErrorCode) {
        this.setExternalErrorCode(externalErrorCode);
        return this;
    }

    public void setExternalErrorCode(String externalErrorCode) {
        this.externalErrorCode = externalErrorCode;
    }

    public Boolean getIsCrossCurrency() {
        return this.isCrossCurrency;
    }

    public DTransaction isCrossCurrency(Boolean isCrossCurrency) {
        this.setIsCrossCurrency(isCrossCurrency);
        return this;
    }

    public void setIsCrossCurrency(Boolean isCrossCurrency) {
        this.isCrossCurrency = isCrossCurrency;
    }

    public Boolean getIsCrossBank() {
        return this.isCrossBank;
    }

    public DTransaction isCrossBank(Boolean isCrossBank) {
        this.setIsCrossBank(isCrossBank);
        return this;
    }

    public void setIsCrossBank(Boolean isCrossBank) {
        this.isCrossBank = isCrossBank;
    }

    public String getCurrency() {
        return this.currency;
    }

    public DTransaction currency(String currency) {
        this.setCurrency(currency);
        return this;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCreditAccount() {
        return this.creditAccount;
    }

    public DTransaction creditAccount(String creditAccount) {
        this.setCreditAccount(creditAccount);
        return this;
    }

    public void setCreditAccount(String creditAccount) {
        this.creditAccount = creditAccount;
    }

    public String getDebitAccount() {
        return this.debitAccount;
    }

    public DTransaction debitAccount(String debitAccount) {
        this.setDebitAccount(debitAccount);
        return this;
    }

    public void setDebitAccount(String debitAccount) {
        this.debitAccount = debitAccount;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public DTransaction phoneNumber(String phoneNumber) {
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getCustomerNumber() {
        return this.customerNumber;
    }

    public DTransaction customerNumber(String customerNumber) {
        this.setCustomerNumber(customerNumber);
        return this;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public TranStatus getTranStatus() {
        return this.tranStatus;
    }

    public DTransaction tranStatus(TranStatus tranStatus) {
        this.setTranStatus(tranStatus);
        return this;
    }

    public void setTranStatus(TranStatus tranStatus) {
        this.tranStatus = tranStatus;
    }

    public String getTranStatusDetails() {
        return this.tranStatusDetails;
    }

    public DTransaction tranStatusDetails(String tranStatusDetails) {
        this.setTranStatusDetails(tranStatusDetails);
        return this;
    }

    public void setTranStatusDetails(String tranStatusDetails) {
        this.tranStatusDetails = tranStatusDetails;
    }

    public String getTranFreeField1() {
        return this.tranFreeField1;
    }

    public DTransaction tranFreeField1(String tranFreeField1) {
        this.setTranFreeField1(tranFreeField1);
        return this;
    }

    public void setTranFreeField1(String tranFreeField1) {
        this.tranFreeField1 = tranFreeField1;
    }

    public String getTranFreeField2() {
        return this.tranFreeField2;
    }

    public DTransaction tranFreeField2(String tranFreeField2) {
        this.setTranFreeField2(tranFreeField2);
        return this;
    }

    public void setTranFreeField2(String tranFreeField2) {
        this.tranFreeField2 = tranFreeField2;
    }

    public String getTranFreeField3() {
        return this.tranFreeField3;
    }

    public DTransaction tranFreeField3(String tranFreeField3) {
        this.setTranFreeField3(tranFreeField3);
        return this;
    }

    public void setTranFreeField3(String tranFreeField3) {
        this.tranFreeField3 = tranFreeField3;
    }

    public String getTranFreeField4() {
        return this.tranFreeField4;
    }

    public DTransaction tranFreeField4(String tranFreeField4) {
        this.setTranFreeField4(tranFreeField4);
        return this;
    }

    public void setTranFreeField4(String tranFreeField4) {
        this.tranFreeField4 = tranFreeField4;
    }

    public String getTranFreeField5() {
        return this.tranFreeField5;
    }

    public DTransaction tranFreeField5(String tranFreeField5) {
        this.setTranFreeField5(tranFreeField5);
        return this;
    }

    public void setTranFreeField5(String tranFreeField5) {
        this.tranFreeField5 = tranFreeField5;
    }

    public String getTranFreeField6() {
        return this.tranFreeField6;
    }

    public DTransaction tranFreeField6(String tranFreeField6) {
        this.setTranFreeField6(tranFreeField6);
        return this;
    }

    public void setTranFreeField6(String tranFreeField6) {
        this.tranFreeField6 = tranFreeField6;
    }

    public String getTranFreeField7() {
        return this.tranFreeField7;
    }

    public DTransaction tranFreeField7(String tranFreeField7) {
        this.setTranFreeField7(tranFreeField7);
        return this;
    }

    public void setTranFreeField7(String tranFreeField7) {
        this.tranFreeField7 = tranFreeField7;
    }

    public String getTranFreeField8() {
        return this.tranFreeField8;
    }

    public DTransaction tranFreeField8(String tranFreeField8) {
        this.setTranFreeField8(tranFreeField8);
        return this;
    }

    public void setTranFreeField8(String tranFreeField8) {
        this.tranFreeField8 = tranFreeField8;
    }

    public String getTranFreeField9() {
        return this.tranFreeField9;
    }

    public DTransaction tranFreeField9(String tranFreeField9) {
        this.setTranFreeField9(tranFreeField9);
        return this;
    }

    public void setTranFreeField9(String tranFreeField9) {
        this.tranFreeField9 = tranFreeField9;
    }

    public String getTranFreeField10() {
        return this.tranFreeField10;
    }

    public DTransaction tranFreeField10(String tranFreeField10) {
        this.setTranFreeField10(tranFreeField10);
        return this;
    }

    public void setTranFreeField10(String tranFreeField10) {
        this.tranFreeField10 = tranFreeField10;
    }

    public String getTranFreeField11() {
        return this.tranFreeField11;
    }

    public DTransaction tranFreeField11(String tranFreeField11) {
        this.setTranFreeField11(tranFreeField11);
        return this;
    }

    public void setTranFreeField11(String tranFreeField11) {
        this.tranFreeField11 = tranFreeField11;
    }

    public String getTranFreeField12() {
        return this.tranFreeField12;
    }

    public DTransaction tranFreeField12(String tranFreeField12) {
        this.setTranFreeField12(tranFreeField12);
        return this;
    }

    public void setTranFreeField12(String tranFreeField12) {
        this.tranFreeField12 = tranFreeField12;
    }

    public Integer getTranFreeField13() {
        return this.tranFreeField13;
    }

    public DTransaction tranFreeField13(Integer tranFreeField13) {
        this.setTranFreeField13(tranFreeField13);
        return this;
    }

    public void setTranFreeField13(Integer tranFreeField13) {
        this.tranFreeField13 = tranFreeField13;
    }

    public byte[] getTranFreeField14() {
        return this.tranFreeField14;
    }

    public DTransaction tranFreeField14(byte[] tranFreeField14) {
        this.setTranFreeField14(tranFreeField14);
        return this;
    }

    public void setTranFreeField14(byte[] tranFreeField14) {
        this.tranFreeField14 = tranFreeField14;
    }

    public String getTranFreeField14ContentType() {
        return this.tranFreeField14ContentType;
    }

    public DTransaction tranFreeField14ContentType(String tranFreeField14ContentType) {
        this.tranFreeField14ContentType = tranFreeField14ContentType;
        return this;
    }

    public void setTranFreeField14ContentType(String tranFreeField14ContentType) {
        this.tranFreeField14ContentType = tranFreeField14ContentType;
    }

    public String getTranFreeField15() {
        return this.tranFreeField15;
    }

    public DTransaction tranFreeField15(String tranFreeField15) {
        this.setTranFreeField15(tranFreeField15);
        return this;
    }

    public void setTranFreeField15(String tranFreeField15) {
        this.tranFreeField15 = tranFreeField15;
    }

    public ZonedDateTime getTranFreeField16() {
        return this.tranFreeField16;
    }

    public DTransaction tranFreeField16(ZonedDateTime tranFreeField16) {
        this.setTranFreeField16(tranFreeField16);
        return this;
    }

    public void setTranFreeField16(ZonedDateTime tranFreeField16) {
        this.tranFreeField16 = tranFreeField16;
    }

    public Boolean getTranFreeField17() {
        return this.tranFreeField17;
    }

    public DTransaction tranFreeField17(Boolean tranFreeField17) {
        this.setTranFreeField17(tranFreeField17);
        return this;
    }

    public void setTranFreeField17(Boolean tranFreeField17) {
        this.tranFreeField17 = tranFreeField17;
    }

    public String getTranFreeField18() {
        return this.tranFreeField18;
    }

    public DTransaction tranFreeField18(String tranFreeField18) {
        this.setTranFreeField18(tranFreeField18);
        return this;
    }

    public void setTranFreeField18(String tranFreeField18) {
        this.tranFreeField18 = tranFreeField18;
    }

    public String getTranFreeField19() {
        return this.tranFreeField19;
    }

    public DTransaction tranFreeField19(String tranFreeField19) {
        this.setTranFreeField19(tranFreeField19);
        return this;
    }

    public void setTranFreeField19(String tranFreeField19) {
        this.tranFreeField19 = tranFreeField19;
    }

    public ZonedDateTime getTranFreeField20() {
        return this.tranFreeField20;
    }

    public DTransaction tranFreeField20(ZonedDateTime tranFreeField20) {
        this.setTranFreeField20(tranFreeField20);
        return this;
    }

    public void setTranFreeField20(ZonedDateTime tranFreeField20) {
        this.tranFreeField20 = tranFreeField20;
    }

    public Instant getTranFreeField21() {
        return this.tranFreeField21;
    }

    public DTransaction tranFreeField21(Instant tranFreeField21) {
        this.setTranFreeField21(tranFreeField21);
        return this;
    }

    public void setTranFreeField21(Instant tranFreeField21) {
        this.tranFreeField21 = tranFreeField21;
    }

    public Boolean getTranFreeField22() {
        return this.tranFreeField22;
    }

    public DTransaction tranFreeField22(Boolean tranFreeField22) {
        this.setTranFreeField22(tranFreeField22);
        return this;
    }

    public void setTranFreeField22(Boolean tranFreeField22) {
        this.tranFreeField22 = tranFreeField22;
    }

    public Instant getTranFreeField23() {
        return this.tranFreeField23;
    }

    public DTransaction tranFreeField23(Instant tranFreeField23) {
        this.setTranFreeField23(tranFreeField23);
        return this;
    }

    public void setTranFreeField23(Instant tranFreeField23) {
        this.tranFreeField23 = tranFreeField23;
    }

    public String getTranFreeField24() {
        return this.tranFreeField24;
    }

    public DTransaction tranFreeField24(String tranFreeField24) {
        this.setTranFreeField24(tranFreeField24);
        return this;
    }

    public void setTranFreeField24(String tranFreeField24) {
        this.tranFreeField24 = tranFreeField24;
    }

    public String getTranFreeField25() {
        return this.tranFreeField25;
    }

    public DTransaction tranFreeField25(String tranFreeField25) {
        this.setTranFreeField25(tranFreeField25);
        return this;
    }

    public void setTranFreeField25(String tranFreeField25) {
        this.tranFreeField25 = tranFreeField25;
    }

    public String getTranFreeField26() {
        return this.tranFreeField26;
    }

    public DTransaction tranFreeField26(String tranFreeField26) {
        this.setTranFreeField26(tranFreeField26);
        return this;
    }

    public void setTranFreeField26(String tranFreeField26) {
        this.tranFreeField26 = tranFreeField26;
    }

    public String getTranFreeField27() {
        return this.tranFreeField27;
    }

    public DTransaction tranFreeField27(String tranFreeField27) {
        this.setTranFreeField27(tranFreeField27);
        return this;
    }

    public void setTranFreeField27(String tranFreeField27) {
        this.tranFreeField27 = tranFreeField27;
    }

    public String getTranFreeField28() {
        return this.tranFreeField28;
    }

    public DTransaction tranFreeField28(String tranFreeField28) {
        this.setTranFreeField28(tranFreeField28);
        return this;
    }

    public void setTranFreeField28(String tranFreeField28) {
        this.tranFreeField28 = tranFreeField28;
    }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public DTransaction createdAt(ZonedDateTime createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public DTransaction createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public DTransaction updatedAt(ZonedDateTime updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public DTransaction updatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public DTransactionHistory getTransaction() {
        return this.transaction;
    }

    public void setTransaction(DTransactionHistory dTransactionHistory) {
        this.transaction = dTransactionHistory;
        this.transactionId = dTransactionHistory != null ? dTransactionHistory.getId() : null;
    }

    public void setTransaction(DTransactionSummary dTransactionSummary) {
        this.transaction = dTransactionSummary;
        this.transactionId = dTransactionSummary != null ? dTransactionSummary.getId() : null;
    }

    public void setTransaction(DTransactionDetails dTransactionDetails) {
        this.transaction = dTransactionDetails;
        this.transactionId = dTransactionDetails != null ? dTransactionDetails.getId() : null;
    }

    public DTransaction transaction(DTransactionHistory dTransactionHistory) {
        this.setTransaction(dTransactionHistory);
        return this;
    }

    public DTransactionSummary getTransaction() {
        return this.transaction;
    }

    public void setTransaction(DTransactionHistory dTransactionHistory) {
        this.transaction = dTransactionHistory;
        this.transactionId = dTransactionHistory != null ? dTransactionHistory.getId() : null;
    }

    public void setTransaction(DTransactionSummary dTransactionSummary) {
        this.transaction = dTransactionSummary;
        this.transactionId = dTransactionSummary != null ? dTransactionSummary.getId() : null;
    }

    public void setTransaction(DTransactionDetails dTransactionDetails) {
        this.transaction = dTransactionDetails;
        this.transactionId = dTransactionDetails != null ? dTransactionDetails.getId() : null;
    }

    public DTransaction transaction(DTransactionSummary dTransactionSummary) {
        this.setTransaction(dTransactionSummary);
        return this;
    }

    public DTransactionDetails getTransaction() {
        return this.transaction;
    }

    public void setTransaction(DTransactionHistory dTransactionHistory) {
        this.transaction = dTransactionHistory;
        this.transactionId = dTransactionHistory != null ? dTransactionHistory.getId() : null;
    }

    public void setTransaction(DTransactionSummary dTransactionSummary) {
        this.transaction = dTransactionSummary;
        this.transactionId = dTransactionSummary != null ? dTransactionSummary.getId() : null;
    }

    public void setTransaction(DTransactionDetails dTransactionDetails) {
        this.transaction = dTransactionDetails;
        this.transactionId = dTransactionDetails != null ? dTransactionDetails.getId() : null;
    }

    public DTransaction transaction(DTransactionDetails dTransactionDetails) {
        this.setTransaction(dTransactionDetails);
        return this;
    }

    public Set<AmolDTransactions> getTransactions() {
        return this.transactions;
    }

    public void setTransaction(DTransactionHistory dTransactionHistory) {
        this.transaction = dTransactionHistory;
        this.transactionId = dTransactionHistory != null ? dTransactionHistory.getId() : null;
    }

    public void setTransaction(DTransactionSummary dTransactionSummary) {
        this.transaction = dTransactionSummary;
        this.transactionId = dTransactionSummary != null ? dTransactionSummary.getId() : null;
    }

    public void setTransaction(DTransactionDetails dTransactionDetails) {
        this.transaction = dTransactionDetails;
        this.transactionId = dTransactionDetails != null ? dTransactionDetails.getId() : null;
    }

    public DTransaction transactions(Set<AmolDTransactions> amolDTransactions) {
        this.setTransactions(amolDTransactions);
        return this;
    }

    public DTransaction addTransaction(AmolDTransactions amolDTransactions) {
        this.transactions.add(amolDTransactions);
        amolDTransactions.setDTransaction(this);
        return this;
    }

    public DTransaction removeTransaction(AmolDTransactions amolDTransactions) {
        this.transactions.remove(amolDTransactions);
        amolDTransactions.setDTransaction(null);
        return this;
    }

    public Set<DTransactionChannel> getTransactions() {
        return this.transactions;
    }

    public void setTransaction(DTransactionHistory dTransactionHistory) {
        this.transaction = dTransactionHistory;
        this.transactionId = dTransactionHistory != null ? dTransactionHistory.getId() : null;
    }

    public void setTransaction(DTransactionSummary dTransactionSummary) {
        this.transaction = dTransactionSummary;
        this.transactionId = dTransactionSummary != null ? dTransactionSummary.getId() : null;
    }

    public void setTransaction(DTransactionDetails dTransactionDetails) {
        this.transaction = dTransactionDetails;
        this.transactionId = dTransactionDetails != null ? dTransactionDetails.getId() : null;
    }

    public DTransaction transactions(Set<DTransactionChannel> dTransactionChannels) {
        this.setTransactions(dTransactionChannels);
        return this;
    }

    public DTransaction addTransaction(DTransactionChannel dTransactionChannel) {
        this.transactions.add(dTransactionChannel);
        dTransactionChannel.setDTransaction(this);
        return this;
    }

    public DTransaction removeTransaction(DTransactionChannel dTransactionChannel) {
        this.transactions.remove(dTransactionChannel);
        dTransactionChannel.setDTransaction(null);
        return this;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        this.customerId = customer != null ? customer.getId() : null;
    }

    public DTransaction customer(Customer customer) {
        this.setCustomer(customer);
        return this;
    }

    public Long getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(Long dTransactionHistory) {
        this.transactionId = dTransactionHistory;
    }

    public Long getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(Long dTransactionSummary) {
        this.transactionId = dTransactionSummary;
    }

    public Long getTransactionId() {
        return this.transactionId;
    }

    public void setTransactionId(Long dTransactionDetails) {
        this.transactionId = dTransactionDetails;
    }

    public Long getCustomerId() {
        return this.customerId;
    }

    public void setCustomerId(Long customer) {
        this.customerId = customer;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DTransaction)) {
            return false;
        }
        return id != null && id.equals(((DTransaction) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DTransaction{" +
            "id=" + getId() +
            ", absaTranRef='" + getAbsaTranRef() + "'" +
            ", billerId='" + getBillerId() + "'" +
            ", paymentItemCode='" + getPaymentItemCode() + "'" +
            ", dtDTransactionId='" + getDtDTransactionId() + "'" +
            ", amolDTransactionId='" + getAmolDTransactionId() + "'" +
            ", transactionReferenceNumber='" + getTransactionReferenceNumber() + "'" +
            ", externalTranid='" + getExternalTranid() + "'" +
            ", token='" + getToken() + "'" +
            ", transferId='" + getTransferId() + "'" +
            ", productCode='" + getProductCode() + "'" +
            ", paymentChannelCode='" + getPaymentChannelCode() + "'" +
            ", accountNumber='" + getAccountNumber() + "'" +
            ", amount=" + getAmount() +
            ", debitAmount=" + getDebitAmount() +
            ", creditAmount=" + getCreditAmount() +
            ", settlementAmount=" + getSettlementAmount() +
            ", settlementDate='" + getSettlementDate() + "'" +
            ", transactionDate='" + getTransactionDate() + "'" +
            ", isRetried='" + getIsRetried() + "'" +
            ", lastRetryDate='" + getLastRetryDate() + "'" +
            ", firstRetryDate='" + getFirstRetryDate() + "'" +
            ", retryResposeCode='" + getRetryResposeCode() + "'" +
            ", retryResponseMessage='" + getRetryResponseMessage() + "'" +
            ", retryCount=" + getRetryCount() +
            ", isRetriableFlg='" + getIsRetriableFlg() + "'" +
            ", doNotRetryDTransaction='" + getDoNotRetryDTransaction() + "'" +
            ", status='" + getStatus() + "'" +
            ", statusCode='" + getStatusCode() + "'" +
            ", statusDetails='" + getStatusDetails() + "'" +
            ", retries=" + getRetries() +
            ", timestamp='" + getTimestamp() + "'" +
            ", postedBy='" + getPostedBy() + "'" +
            ", postedDate='" + getPostedDate() + "'" +
            ", internalErrorCode='" + getInternalErrorCode() + "'" +
            ", externalErrorCode='" + getExternalErrorCode() + "'" +
            ", isCrossCurrency='" + getIsCrossCurrency() + "'" +
            ", isCrossBank='" + getIsCrossBank() + "'" +
            ", currency='" + getCurrency() + "'" +
            ", creditAccount='" + getCreditAccount() + "'" +
            ", debitAccount='" + getDebitAccount() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", customerNumber='" + getCustomerNumber() + "'" +
            ", tranStatus='" + getTranStatus() + "'" +
            ", tranStatusDetails='" + getTranStatusDetails() + "'" +
            ", tranFreeField1='" + getTranFreeField1() + "'" +
            ", tranFreeField2='" + getTranFreeField2() + "'" +
            ", tranFreeField3='" + getTranFreeField3() + "'" +
            ", tranFreeField4='" + getTranFreeField4() + "'" +
            ", tranFreeField5='" + getTranFreeField5() + "'" +
            ", tranFreeField6='" + getTranFreeField6() + "'" +
            ", tranFreeField7='" + getTranFreeField7() + "'" +
            ", tranFreeField8='" + getTranFreeField8() + "'" +
            ", tranFreeField9='" + getTranFreeField9() + "'" +
            ", tranFreeField10='" + getTranFreeField10() + "'" +
            ", tranFreeField11='" + getTranFreeField11() + "'" +
            ", tranFreeField12='" + getTranFreeField12() + "'" +
            ", tranFreeField13=" + getTranFreeField13() +
            ", tranFreeField14='" + getTranFreeField14() + "'" +
            ", tranFreeField14ContentType='" + getTranFreeField14ContentType() + "'" +
            ", tranFreeField15='" + getTranFreeField15() + "'" +
            ", tranFreeField16='" + getTranFreeField16() + "'" +
            ", tranFreeField17='" + getTranFreeField17() + "'" +
            ", tranFreeField18='" + getTranFreeField18() + "'" +
            ", tranFreeField19='" + getTranFreeField19() + "'" +
            ", tranFreeField20='" + getTranFreeField20() + "'" +
            ", tranFreeField21='" + getTranFreeField21() + "'" +
            ", tranFreeField22='" + getTranFreeField22() + "'" +
            ", tranFreeField23='" + getTranFreeField23() + "'" +
            ", tranFreeField24='" + getTranFreeField24() + "'" +
            ", tranFreeField25='" + getTranFreeField25() + "'" +
            ", tranFreeField26='" + getTranFreeField26() + "'" +
            ", tranFreeField27='" + getTranFreeField27() + "'" +
            ", tranFreeField28='" + getTranFreeField28() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            "}";
    }
}
