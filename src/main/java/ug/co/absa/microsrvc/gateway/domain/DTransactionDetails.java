package ug.co.absa.microsrvc.gateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import ug.co.absa.microsrvc.gateway.domain.enumeration.Channel;
import ug.co.absa.microsrvc.gateway.domain.enumeration.ModeOfPayment;
import ug.co.absa.microsrvc.gateway.domain.enumeration.TranStatus;

/**
 * A DTransactionDetails.
 */
@Table("d_transaction_details")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "dtransactiondetails")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DTransactionDetails implements Serializable {

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
    @Column("debit_account_number")
    private String debitAccountNumber;

    @NotNull(message = "must not be null")
    @Column("credit_account_number")
    private String creditAccountNumber;

    @NotNull(message = "must not be null")
    @Column("debit_amount")
    private Integer debitAmount;

    @Column("credit_amount")
    private Integer creditAmount;

    @Column("transaction_amount")
    private Integer transactionAmount;

    @NotNull(message = "must not be null")
    @Column("debit_date")
    private ZonedDateTime debitDate;

    @NotNull(message = "must not be null")
    @Column("credit_date")
    private ZonedDateTime creditDate;

    @Column("status")
    private TranStatus status;

    @Column("settlement_date")
    private ZonedDateTime settlementDate;

    @NotNull(message = "must not be null")
    @Column("debit_currency")
    private String debitCurrency;

    @Column("timestamp")
    private ZonedDateTime timestamp;

    @NotNull(message = "must not be null")
    @Column("phone_number")
    private String phoneNumber;

    @NotNull(message = "must not be null")
    @Column("email")
    private String email;

    @Column("payer_name")
    private String payerName;

    @Column("payer_address")
    private String payerAddress;

    @Column("payer_email")
    private String payerEmail;

    @Column("payer_phone_number")
    private String payerPhoneNumber;

    @Column("payer_narration")
    private String payerNarration;

    @Column("payer_branch_id")
    private String payerBranchId;

    @Column("beneficiary_name")
    private String beneficiaryName;

    @Column("beneficiary_account")
    private String beneficiaryAccount;

    @Column("beneficiary_branch_id")
    private String beneficiaryBranchId;

    @Column("beneficiary_phone_number")
    private String beneficiaryPhoneNumber;

    @Column("tran_status")
    private TranStatus tranStatus;

    @Column("narration_1")
    private String narration1;

    @Column("narration_2")
    private String narration2;

    @Column("narration_3")
    private String narration3;

    @Column("narration_4")
    private String narration4;

    @Column("narration_5")
    private String narration5;

    @Column("narration_6")
    private String narration6;

    @Column("narration_7")
    private String narration7;

    @Column("narration_8")
    private String narration8;

    @Column("narration_9")
    private String narration9;

    @Column("narration_10")
    private String narration10;

    @Column("narration_11")
    private String narration11;

    @Column("narration_12")
    private String narration12;

    @Column("mode_of_payment")
    private ModeOfPayment modeOfPayment;

    @Column("retries")
    private Integer retries;

    @Column("posted")
    private Boolean posted;

    @Column("api_id")
    private String apiId;

    @Column("api_version")
    private String apiVersion;

    @Column("posting_api")
    private String postingApi;

    @Column("is_posted")
    private Boolean isPosted;

    @Column("posted_by")
    private String postedBy;

    @Column("posted_date")
    private String postedDate;

    @Column("tran_free_field_1")
    private String tranFreeField1;

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
    private byte[] tranFreeField12;

    @Column("tran_free_field_12_content_type")
    private String tranFreeField12ContentType;

    @Column("tran_free_field_13")
    private String tranFreeField13;

    @Column("tran_free_field_14")
    private String tranFreeField14;

    @Column("tran_free_field_15")
    private byte[] tranFreeField15;

    @Column("tran_free_field_15_content_type")
    private String tranFreeField15ContentType;

    @Column("tran_free_field_16")
    private String tranFreeField16;

    @Column("tran_free_field_17")
    private String tranFreeField17;

    @Column("tran_free_field_18")
    private String tranFreeField18;

    @Column("tran_free_field_19")
    private String tranFreeField19;

    @Column("tran_free_field_20")
    private String tranFreeField20;

    @Column("tran_free_field_21")
    private String tranFreeField21;

    @Column("tran_free_field_22")
    private String tranFreeField22;

    @Column("tran_free_field_23")
    private String tranFreeField23;

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

    @Column("internal_error_code")
    private String internalErrorCode;

    @Column("external_error_code")
    private String externalErrorCode;

    @Transient
    @JsonIgnoreProperties(value = { "customers", "customers", "customers", "customers" }, allowSetters = true)
    private Customer customer;

    @Column("customer_id")
    private Long customerId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DTransactionDetails id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getAbsaTranRef() {
        return this.absaTranRef;
    }

    public DTransactionDetails absaTranRef(UUID absaTranRef) {
        this.setAbsaTranRef(absaTranRef);
        return this;
    }

    public void setAbsaTranRef(UUID absaTranRef) {
        this.absaTranRef = absaTranRef;
    }

    public String getBillerId() {
        return this.billerId;
    }

    public DTransactionDetails billerId(String billerId) {
        this.setBillerId(billerId);
        return this;
    }

    public void setBillerId(String billerId) {
        this.billerId = billerId;
    }

    public String getPaymentItemCode() {
        return this.paymentItemCode;
    }

    public DTransactionDetails paymentItemCode(String paymentItemCode) {
        this.setPaymentItemCode(paymentItemCode);
        return this;
    }

    public void setPaymentItemCode(String paymentItemCode) {
        this.paymentItemCode = paymentItemCode;
    }

    public String getDtDTransactionId() {
        return this.dtDTransactionId;
    }

    public DTransactionDetails dtDTransactionId(String dtDTransactionId) {
        this.setDtDTransactionId(dtDTransactionId);
        return this;
    }

    public void setDtDTransactionId(String dtDTransactionId) {
        this.dtDTransactionId = dtDTransactionId;
    }

    public String getAmolDTransactionId() {
        return this.amolDTransactionId;
    }

    public DTransactionDetails amolDTransactionId(String amolDTransactionId) {
        this.setAmolDTransactionId(amolDTransactionId);
        return this;
    }

    public void setAmolDTransactionId(String amolDTransactionId) {
        this.amolDTransactionId = amolDTransactionId;
    }

    public String getTransactionReferenceNumber() {
        return this.transactionReferenceNumber;
    }

    public DTransactionDetails transactionReferenceNumber(String transactionReferenceNumber) {
        this.setTransactionReferenceNumber(transactionReferenceNumber);
        return this;
    }

    public void setTransactionReferenceNumber(String transactionReferenceNumber) {
        this.transactionReferenceNumber = transactionReferenceNumber;
    }

    public String getToken() {
        return this.token;
    }

    public DTransactionDetails token(String token) {
        this.setToken(token);
        return this;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTransferId() {
        return this.transferId;
    }

    public DTransactionDetails transferId(String transferId) {
        this.setTransferId(transferId);
        return this;
    }

    public void setTransferId(String transferId) {
        this.transferId = transferId;
    }

    public String getProductCode() {
        return this.productCode;
    }

    public DTransactionDetails productCode(String productCode) {
        this.setProductCode(productCode);
        return this;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Channel getPaymentChannelCode() {
        return this.paymentChannelCode;
    }

    public DTransactionDetails paymentChannelCode(Channel paymentChannelCode) {
        this.setPaymentChannelCode(paymentChannelCode);
        return this;
    }

    public void setPaymentChannelCode(Channel paymentChannelCode) {
        this.paymentChannelCode = paymentChannelCode;
    }

    public String getDebitAccountNumber() {
        return this.debitAccountNumber;
    }

    public DTransactionDetails debitAccountNumber(String debitAccountNumber) {
        this.setDebitAccountNumber(debitAccountNumber);
        return this;
    }

    public void setDebitAccountNumber(String debitAccountNumber) {
        this.debitAccountNumber = debitAccountNumber;
    }

    public String getCreditAccountNumber() {
        return this.creditAccountNumber;
    }

    public DTransactionDetails creditAccountNumber(String creditAccountNumber) {
        this.setCreditAccountNumber(creditAccountNumber);
        return this;
    }

    public void setCreditAccountNumber(String creditAccountNumber) {
        this.creditAccountNumber = creditAccountNumber;
    }

    public Integer getDebitAmount() {
        return this.debitAmount;
    }

    public DTransactionDetails debitAmount(Integer debitAmount) {
        this.setDebitAmount(debitAmount);
        return this;
    }

    public void setDebitAmount(Integer debitAmount) {
        this.debitAmount = debitAmount;
    }

    public Integer getCreditAmount() {
        return this.creditAmount;
    }

    public DTransactionDetails creditAmount(Integer creditAmount) {
        this.setCreditAmount(creditAmount);
        return this;
    }

    public void setCreditAmount(Integer creditAmount) {
        this.creditAmount = creditAmount;
    }

    public Integer getTransactionAmount() {
        return this.transactionAmount;
    }

    public DTransactionDetails transactionAmount(Integer transactionAmount) {
        this.setTransactionAmount(transactionAmount);
        return this;
    }

    public void setTransactionAmount(Integer transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public ZonedDateTime getDebitDate() {
        return this.debitDate;
    }

    public DTransactionDetails debitDate(ZonedDateTime debitDate) {
        this.setDebitDate(debitDate);
        return this;
    }

    public void setDebitDate(ZonedDateTime debitDate) {
        this.debitDate = debitDate;
    }

    public ZonedDateTime getCreditDate() {
        return this.creditDate;
    }

    public DTransactionDetails creditDate(ZonedDateTime creditDate) {
        this.setCreditDate(creditDate);
        return this;
    }

    public void setCreditDate(ZonedDateTime creditDate) {
        this.creditDate = creditDate;
    }

    public TranStatus getStatus() {
        return this.status;
    }

    public DTransactionDetails status(TranStatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(TranStatus status) {
        this.status = status;
    }

    public ZonedDateTime getSettlementDate() {
        return this.settlementDate;
    }

    public DTransactionDetails settlementDate(ZonedDateTime settlementDate) {
        this.setSettlementDate(settlementDate);
        return this;
    }

    public void setSettlementDate(ZonedDateTime settlementDate) {
        this.settlementDate = settlementDate;
    }

    public String getDebitCurrency() {
        return this.debitCurrency;
    }

    public DTransactionDetails debitCurrency(String debitCurrency) {
        this.setDebitCurrency(debitCurrency);
        return this;
    }

    public void setDebitCurrency(String debitCurrency) {
        this.debitCurrency = debitCurrency;
    }

    public ZonedDateTime getTimestamp() {
        return this.timestamp;
    }

    public DTransactionDetails timestamp(ZonedDateTime timestamp) {
        this.setTimestamp(timestamp);
        return this;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public DTransactionDetails phoneNumber(String phoneNumber) {
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public DTransactionDetails email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPayerName() {
        return this.payerName;
    }

    public DTransactionDetails payerName(String payerName) {
        this.setPayerName(payerName);
        return this;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getPayerAddress() {
        return this.payerAddress;
    }

    public DTransactionDetails payerAddress(String payerAddress) {
        this.setPayerAddress(payerAddress);
        return this;
    }

    public void setPayerAddress(String payerAddress) {
        this.payerAddress = payerAddress;
    }

    public String getPayerEmail() {
        return this.payerEmail;
    }

    public DTransactionDetails payerEmail(String payerEmail) {
        this.setPayerEmail(payerEmail);
        return this;
    }

    public void setPayerEmail(String payerEmail) {
        this.payerEmail = payerEmail;
    }

    public String getPayerPhoneNumber() {
        return this.payerPhoneNumber;
    }

    public DTransactionDetails payerPhoneNumber(String payerPhoneNumber) {
        this.setPayerPhoneNumber(payerPhoneNumber);
        return this;
    }

    public void setPayerPhoneNumber(String payerPhoneNumber) {
        this.payerPhoneNumber = payerPhoneNumber;
    }

    public String getPayerNarration() {
        return this.payerNarration;
    }

    public DTransactionDetails payerNarration(String payerNarration) {
        this.setPayerNarration(payerNarration);
        return this;
    }

    public void setPayerNarration(String payerNarration) {
        this.payerNarration = payerNarration;
    }

    public String getPayerBranchId() {
        return this.payerBranchId;
    }

    public DTransactionDetails payerBranchId(String payerBranchId) {
        this.setPayerBranchId(payerBranchId);
        return this;
    }

    public void setPayerBranchId(String payerBranchId) {
        this.payerBranchId = payerBranchId;
    }

    public String getBeneficiaryName() {
        return this.beneficiaryName;
    }

    public DTransactionDetails beneficiaryName(String beneficiaryName) {
        this.setBeneficiaryName(beneficiaryName);
        return this;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public String getBeneficiaryAccount() {
        return this.beneficiaryAccount;
    }

    public DTransactionDetails beneficiaryAccount(String beneficiaryAccount) {
        this.setBeneficiaryAccount(beneficiaryAccount);
        return this;
    }

    public void setBeneficiaryAccount(String beneficiaryAccount) {
        this.beneficiaryAccount = beneficiaryAccount;
    }

    public String getBeneficiaryBranchId() {
        return this.beneficiaryBranchId;
    }

    public DTransactionDetails beneficiaryBranchId(String beneficiaryBranchId) {
        this.setBeneficiaryBranchId(beneficiaryBranchId);
        return this;
    }

    public void setBeneficiaryBranchId(String beneficiaryBranchId) {
        this.beneficiaryBranchId = beneficiaryBranchId;
    }

    public String getBeneficiaryPhoneNumber() {
        return this.beneficiaryPhoneNumber;
    }

    public DTransactionDetails beneficiaryPhoneNumber(String beneficiaryPhoneNumber) {
        this.setBeneficiaryPhoneNumber(beneficiaryPhoneNumber);
        return this;
    }

    public void setBeneficiaryPhoneNumber(String beneficiaryPhoneNumber) {
        this.beneficiaryPhoneNumber = beneficiaryPhoneNumber;
    }

    public TranStatus getTranStatus() {
        return this.tranStatus;
    }

    public DTransactionDetails tranStatus(TranStatus tranStatus) {
        this.setTranStatus(tranStatus);
        return this;
    }

    public void setTranStatus(TranStatus tranStatus) {
        this.tranStatus = tranStatus;
    }

    public String getNarration1() {
        return this.narration1;
    }

    public DTransactionDetails narration1(String narration1) {
        this.setNarration1(narration1);
        return this;
    }

    public void setNarration1(String narration1) {
        this.narration1 = narration1;
    }

    public String getNarration2() {
        return this.narration2;
    }

    public DTransactionDetails narration2(String narration2) {
        this.setNarration2(narration2);
        return this;
    }

    public void setNarration2(String narration2) {
        this.narration2 = narration2;
    }

    public String getNarration3() {
        return this.narration3;
    }

    public DTransactionDetails narration3(String narration3) {
        this.setNarration3(narration3);
        return this;
    }

    public void setNarration3(String narration3) {
        this.narration3 = narration3;
    }

    public String getNarration4() {
        return this.narration4;
    }

    public DTransactionDetails narration4(String narration4) {
        this.setNarration4(narration4);
        return this;
    }

    public void setNarration4(String narration4) {
        this.narration4 = narration4;
    }

    public String getNarration5() {
        return this.narration5;
    }

    public DTransactionDetails narration5(String narration5) {
        this.setNarration5(narration5);
        return this;
    }

    public void setNarration5(String narration5) {
        this.narration5 = narration5;
    }

    public String getNarration6() {
        return this.narration6;
    }

    public DTransactionDetails narration6(String narration6) {
        this.setNarration6(narration6);
        return this;
    }

    public void setNarration6(String narration6) {
        this.narration6 = narration6;
    }

    public String getNarration7() {
        return this.narration7;
    }

    public DTransactionDetails narration7(String narration7) {
        this.setNarration7(narration7);
        return this;
    }

    public void setNarration7(String narration7) {
        this.narration7 = narration7;
    }

    public String getNarration8() {
        return this.narration8;
    }

    public DTransactionDetails narration8(String narration8) {
        this.setNarration8(narration8);
        return this;
    }

    public void setNarration8(String narration8) {
        this.narration8 = narration8;
    }

    public String getNarration9() {
        return this.narration9;
    }

    public DTransactionDetails narration9(String narration9) {
        this.setNarration9(narration9);
        return this;
    }

    public void setNarration9(String narration9) {
        this.narration9 = narration9;
    }

    public String getNarration10() {
        return this.narration10;
    }

    public DTransactionDetails narration10(String narration10) {
        this.setNarration10(narration10);
        return this;
    }

    public void setNarration10(String narration10) {
        this.narration10 = narration10;
    }

    public String getNarration11() {
        return this.narration11;
    }

    public DTransactionDetails narration11(String narration11) {
        this.setNarration11(narration11);
        return this;
    }

    public void setNarration11(String narration11) {
        this.narration11 = narration11;
    }

    public String getNarration12() {
        return this.narration12;
    }

    public DTransactionDetails narration12(String narration12) {
        this.setNarration12(narration12);
        return this;
    }

    public void setNarration12(String narration12) {
        this.narration12 = narration12;
    }

    public ModeOfPayment getModeOfPayment() {
        return this.modeOfPayment;
    }

    public DTransactionDetails modeOfPayment(ModeOfPayment modeOfPayment) {
        this.setModeOfPayment(modeOfPayment);
        return this;
    }

    public void setModeOfPayment(ModeOfPayment modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }

    public Integer getRetries() {
        return this.retries;
    }

    public DTransactionDetails retries(Integer retries) {
        this.setRetries(retries);
        return this;
    }

    public void setRetries(Integer retries) {
        this.retries = retries;
    }

    public Boolean getPosted() {
        return this.posted;
    }

    public DTransactionDetails posted(Boolean posted) {
        this.setPosted(posted);
        return this;
    }

    public void setPosted(Boolean posted) {
        this.posted = posted;
    }

    public String getApiId() {
        return this.apiId;
    }

    public DTransactionDetails apiId(String apiId) {
        this.setApiId(apiId);
        return this;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getApiVersion() {
        return this.apiVersion;
    }

    public DTransactionDetails apiVersion(String apiVersion) {
        this.setApiVersion(apiVersion);
        return this;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getPostingApi() {
        return this.postingApi;
    }

    public DTransactionDetails postingApi(String postingApi) {
        this.setPostingApi(postingApi);
        return this;
    }

    public void setPostingApi(String postingApi) {
        this.postingApi = postingApi;
    }

    public Boolean getIsPosted() {
        return this.isPosted;
    }

    public DTransactionDetails isPosted(Boolean isPosted) {
        this.setIsPosted(isPosted);
        return this;
    }

    public void setIsPosted(Boolean isPosted) {
        this.isPosted = isPosted;
    }

    public String getPostedBy() {
        return this.postedBy;
    }

    public DTransactionDetails postedBy(String postedBy) {
        this.setPostedBy(postedBy);
        return this;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public String getPostedDate() {
        return this.postedDate;
    }

    public DTransactionDetails postedDate(String postedDate) {
        this.setPostedDate(postedDate);
        return this;
    }

    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }

    public String getTranFreeField1() {
        return this.tranFreeField1;
    }

    public DTransactionDetails tranFreeField1(String tranFreeField1) {
        this.setTranFreeField1(tranFreeField1);
        return this;
    }

    public void setTranFreeField1(String tranFreeField1) {
        this.tranFreeField1 = tranFreeField1;
    }

    public String getTranFreeField3() {
        return this.tranFreeField3;
    }

    public DTransactionDetails tranFreeField3(String tranFreeField3) {
        this.setTranFreeField3(tranFreeField3);
        return this;
    }

    public void setTranFreeField3(String tranFreeField3) {
        this.tranFreeField3 = tranFreeField3;
    }

    public String getTranFreeField4() {
        return this.tranFreeField4;
    }

    public DTransactionDetails tranFreeField4(String tranFreeField4) {
        this.setTranFreeField4(tranFreeField4);
        return this;
    }

    public void setTranFreeField4(String tranFreeField4) {
        this.tranFreeField4 = tranFreeField4;
    }

    public String getTranFreeField5() {
        return this.tranFreeField5;
    }

    public DTransactionDetails tranFreeField5(String tranFreeField5) {
        this.setTranFreeField5(tranFreeField5);
        return this;
    }

    public void setTranFreeField5(String tranFreeField5) {
        this.tranFreeField5 = tranFreeField5;
    }

    public String getTranFreeField6() {
        return this.tranFreeField6;
    }

    public DTransactionDetails tranFreeField6(String tranFreeField6) {
        this.setTranFreeField6(tranFreeField6);
        return this;
    }

    public void setTranFreeField6(String tranFreeField6) {
        this.tranFreeField6 = tranFreeField6;
    }

    public String getTranFreeField7() {
        return this.tranFreeField7;
    }

    public DTransactionDetails tranFreeField7(String tranFreeField7) {
        this.setTranFreeField7(tranFreeField7);
        return this;
    }

    public void setTranFreeField7(String tranFreeField7) {
        this.tranFreeField7 = tranFreeField7;
    }

    public String getTranFreeField8() {
        return this.tranFreeField8;
    }

    public DTransactionDetails tranFreeField8(String tranFreeField8) {
        this.setTranFreeField8(tranFreeField8);
        return this;
    }

    public void setTranFreeField8(String tranFreeField8) {
        this.tranFreeField8 = tranFreeField8;
    }

    public String getTranFreeField9() {
        return this.tranFreeField9;
    }

    public DTransactionDetails tranFreeField9(String tranFreeField9) {
        this.setTranFreeField9(tranFreeField9);
        return this;
    }

    public void setTranFreeField9(String tranFreeField9) {
        this.tranFreeField9 = tranFreeField9;
    }

    public String getTranFreeField10() {
        return this.tranFreeField10;
    }

    public DTransactionDetails tranFreeField10(String tranFreeField10) {
        this.setTranFreeField10(tranFreeField10);
        return this;
    }

    public void setTranFreeField10(String tranFreeField10) {
        this.tranFreeField10 = tranFreeField10;
    }

    public String getTranFreeField11() {
        return this.tranFreeField11;
    }

    public DTransactionDetails tranFreeField11(String tranFreeField11) {
        this.setTranFreeField11(tranFreeField11);
        return this;
    }

    public void setTranFreeField11(String tranFreeField11) {
        this.tranFreeField11 = tranFreeField11;
    }

    public byte[] getTranFreeField12() {
        return this.tranFreeField12;
    }

    public DTransactionDetails tranFreeField12(byte[] tranFreeField12) {
        this.setTranFreeField12(tranFreeField12);
        return this;
    }

    public void setTranFreeField12(byte[] tranFreeField12) {
        this.tranFreeField12 = tranFreeField12;
    }

    public String getTranFreeField12ContentType() {
        return this.tranFreeField12ContentType;
    }

    public DTransactionDetails tranFreeField12ContentType(String tranFreeField12ContentType) {
        this.tranFreeField12ContentType = tranFreeField12ContentType;
        return this;
    }

    public void setTranFreeField12ContentType(String tranFreeField12ContentType) {
        this.tranFreeField12ContentType = tranFreeField12ContentType;
    }

    public String getTranFreeField13() {
        return this.tranFreeField13;
    }

    public DTransactionDetails tranFreeField13(String tranFreeField13) {
        this.setTranFreeField13(tranFreeField13);
        return this;
    }

    public void setTranFreeField13(String tranFreeField13) {
        this.tranFreeField13 = tranFreeField13;
    }

    public String getTranFreeField14() {
        return this.tranFreeField14;
    }

    public DTransactionDetails tranFreeField14(String tranFreeField14) {
        this.setTranFreeField14(tranFreeField14);
        return this;
    }

    public void setTranFreeField14(String tranFreeField14) {
        this.tranFreeField14 = tranFreeField14;
    }

    public byte[] getTranFreeField15() {
        return this.tranFreeField15;
    }

    public DTransactionDetails tranFreeField15(byte[] tranFreeField15) {
        this.setTranFreeField15(tranFreeField15);
        return this;
    }

    public void setTranFreeField15(byte[] tranFreeField15) {
        this.tranFreeField15 = tranFreeField15;
    }

    public String getTranFreeField15ContentType() {
        return this.tranFreeField15ContentType;
    }

    public DTransactionDetails tranFreeField15ContentType(String tranFreeField15ContentType) {
        this.tranFreeField15ContentType = tranFreeField15ContentType;
        return this;
    }

    public void setTranFreeField15ContentType(String tranFreeField15ContentType) {
        this.tranFreeField15ContentType = tranFreeField15ContentType;
    }

    public String getTranFreeField16() {
        return this.tranFreeField16;
    }

    public DTransactionDetails tranFreeField16(String tranFreeField16) {
        this.setTranFreeField16(tranFreeField16);
        return this;
    }

    public void setTranFreeField16(String tranFreeField16) {
        this.tranFreeField16 = tranFreeField16;
    }

    public String getTranFreeField17() {
        return this.tranFreeField17;
    }

    public DTransactionDetails tranFreeField17(String tranFreeField17) {
        this.setTranFreeField17(tranFreeField17);
        return this;
    }

    public void setTranFreeField17(String tranFreeField17) {
        this.tranFreeField17 = tranFreeField17;
    }

    public String getTranFreeField18() {
        return this.tranFreeField18;
    }

    public DTransactionDetails tranFreeField18(String tranFreeField18) {
        this.setTranFreeField18(tranFreeField18);
        return this;
    }

    public void setTranFreeField18(String tranFreeField18) {
        this.tranFreeField18 = tranFreeField18;
    }

    public String getTranFreeField19() {
        return this.tranFreeField19;
    }

    public DTransactionDetails tranFreeField19(String tranFreeField19) {
        this.setTranFreeField19(tranFreeField19);
        return this;
    }

    public void setTranFreeField19(String tranFreeField19) {
        this.tranFreeField19 = tranFreeField19;
    }

    public String getTranFreeField20() {
        return this.tranFreeField20;
    }

    public DTransactionDetails tranFreeField20(String tranFreeField20) {
        this.setTranFreeField20(tranFreeField20);
        return this;
    }

    public void setTranFreeField20(String tranFreeField20) {
        this.tranFreeField20 = tranFreeField20;
    }

    public String getTranFreeField21() {
        return this.tranFreeField21;
    }

    public DTransactionDetails tranFreeField21(String tranFreeField21) {
        this.setTranFreeField21(tranFreeField21);
        return this;
    }

    public void setTranFreeField21(String tranFreeField21) {
        this.tranFreeField21 = tranFreeField21;
    }

    public String getTranFreeField22() {
        return this.tranFreeField22;
    }

    public DTransactionDetails tranFreeField22(String tranFreeField22) {
        this.setTranFreeField22(tranFreeField22);
        return this;
    }

    public void setTranFreeField22(String tranFreeField22) {
        this.tranFreeField22 = tranFreeField22;
    }

    public String getTranFreeField23() {
        return this.tranFreeField23;
    }

    public DTransactionDetails tranFreeField23(String tranFreeField23) {
        this.setTranFreeField23(tranFreeField23);
        return this;
    }

    public void setTranFreeField23(String tranFreeField23) {
        this.tranFreeField23 = tranFreeField23;
    }

    public String getTranFreeField24() {
        return this.tranFreeField24;
    }

    public DTransactionDetails tranFreeField24(String tranFreeField24) {
        this.setTranFreeField24(tranFreeField24);
        return this;
    }

    public void setTranFreeField24(String tranFreeField24) {
        this.tranFreeField24 = tranFreeField24;
    }

    public String getTranFreeField25() {
        return this.tranFreeField25;
    }

    public DTransactionDetails tranFreeField25(String tranFreeField25) {
        this.setTranFreeField25(tranFreeField25);
        return this;
    }

    public void setTranFreeField25(String tranFreeField25) {
        this.tranFreeField25 = tranFreeField25;
    }

    public String getTranFreeField26() {
        return this.tranFreeField26;
    }

    public DTransactionDetails tranFreeField26(String tranFreeField26) {
        this.setTranFreeField26(tranFreeField26);
        return this;
    }

    public void setTranFreeField26(String tranFreeField26) {
        this.tranFreeField26 = tranFreeField26;
    }

    public String getTranFreeField27() {
        return this.tranFreeField27;
    }

    public DTransactionDetails tranFreeField27(String tranFreeField27) {
        this.setTranFreeField27(tranFreeField27);
        return this;
    }

    public void setTranFreeField27(String tranFreeField27) {
        this.tranFreeField27 = tranFreeField27;
    }

    public String getTranFreeField28() {
        return this.tranFreeField28;
    }

    public DTransactionDetails tranFreeField28(String tranFreeField28) {
        this.setTranFreeField28(tranFreeField28);
        return this;
    }

    public void setTranFreeField28(String tranFreeField28) {
        this.tranFreeField28 = tranFreeField28;
    }

    public String getInternalErrorCode() {
        return this.internalErrorCode;
    }

    public DTransactionDetails internalErrorCode(String internalErrorCode) {
        this.setInternalErrorCode(internalErrorCode);
        return this;
    }

    public void setInternalErrorCode(String internalErrorCode) {
        this.internalErrorCode = internalErrorCode;
    }

    public String getExternalErrorCode() {
        return this.externalErrorCode;
    }

    public DTransactionDetails externalErrorCode(String externalErrorCode) {
        this.setExternalErrorCode(externalErrorCode);
        return this;
    }

    public void setExternalErrorCode(String externalErrorCode) {
        this.externalErrorCode = externalErrorCode;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        this.customerId = customer != null ? customer.getId() : null;
    }

    public DTransactionDetails customer(Customer customer) {
        this.setCustomer(customer);
        return this;
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
        if (!(o instanceof DTransactionDetails)) {
            return false;
        }
        return id != null && id.equals(((DTransactionDetails) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DTransactionDetails{" +
            "id=" + getId() +
            ", absaTranRef='" + getAbsaTranRef() + "'" +
            ", billerId='" + getBillerId() + "'" +
            ", paymentItemCode='" + getPaymentItemCode() + "'" +
            ", dtDTransactionId='" + getDtDTransactionId() + "'" +
            ", amolDTransactionId='" + getAmolDTransactionId() + "'" +
            ", transactionReferenceNumber='" + getTransactionReferenceNumber() + "'" +
            ", token='" + getToken() + "'" +
            ", transferId='" + getTransferId() + "'" +
            ", productCode='" + getProductCode() + "'" +
            ", paymentChannelCode='" + getPaymentChannelCode() + "'" +
            ", debitAccountNumber='" + getDebitAccountNumber() + "'" +
            ", creditAccountNumber='" + getCreditAccountNumber() + "'" +
            ", debitAmount=" + getDebitAmount() +
            ", creditAmount=" + getCreditAmount() +
            ", transactionAmount=" + getTransactionAmount() +
            ", debitDate='" + getDebitDate() + "'" +
            ", creditDate='" + getCreditDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", settlementDate='" + getSettlementDate() + "'" +
            ", debitCurrency='" + getDebitCurrency() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", email='" + getEmail() + "'" +
            ", payerName='" + getPayerName() + "'" +
            ", payerAddress='" + getPayerAddress() + "'" +
            ", payerEmail='" + getPayerEmail() + "'" +
            ", payerPhoneNumber='" + getPayerPhoneNumber() + "'" +
            ", payerNarration='" + getPayerNarration() + "'" +
            ", payerBranchId='" + getPayerBranchId() + "'" +
            ", beneficiaryName='" + getBeneficiaryName() + "'" +
            ", beneficiaryAccount='" + getBeneficiaryAccount() + "'" +
            ", beneficiaryBranchId='" + getBeneficiaryBranchId() + "'" +
            ", beneficiaryPhoneNumber='" + getBeneficiaryPhoneNumber() + "'" +
            ", tranStatus='" + getTranStatus() + "'" +
            ", narration1='" + getNarration1() + "'" +
            ", narration2='" + getNarration2() + "'" +
            ", narration3='" + getNarration3() + "'" +
            ", narration4='" + getNarration4() + "'" +
            ", narration5='" + getNarration5() + "'" +
            ", narration6='" + getNarration6() + "'" +
            ", narration7='" + getNarration7() + "'" +
            ", narration8='" + getNarration8() + "'" +
            ", narration9='" + getNarration9() + "'" +
            ", narration10='" + getNarration10() + "'" +
            ", narration11='" + getNarration11() + "'" +
            ", narration12='" + getNarration12() + "'" +
            ", modeOfPayment='" + getModeOfPayment() + "'" +
            ", retries=" + getRetries() +
            ", posted='" + getPosted() + "'" +
            ", apiId='" + getApiId() + "'" +
            ", apiVersion='" + getApiVersion() + "'" +
            ", postingApi='" + getPostingApi() + "'" +
            ", isPosted='" + getIsPosted() + "'" +
            ", postedBy='" + getPostedBy() + "'" +
            ", postedDate='" + getPostedDate() + "'" +
            ", tranFreeField1='" + getTranFreeField1() + "'" +
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
            ", tranFreeField12ContentType='" + getTranFreeField12ContentType() + "'" +
            ", tranFreeField13='" + getTranFreeField13() + "'" +
            ", tranFreeField14='" + getTranFreeField14() + "'" +
            ", tranFreeField15='" + getTranFreeField15() + "'" +
            ", tranFreeField15ContentType='" + getTranFreeField15ContentType() + "'" +
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
            ", internalErrorCode='" + getInternalErrorCode() + "'" +
            ", externalErrorCode='" + getExternalErrorCode() + "'" +
            "}";
    }
}
