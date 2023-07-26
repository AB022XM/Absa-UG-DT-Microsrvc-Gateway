package ug.co.absa.microsrvc.gateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import ug.co.absa.microsrvc.gateway.domain.enumeration.Channel;
import ug.co.absa.microsrvc.gateway.domain.enumeration.Currency;
import ug.co.absa.microsrvc.gateway.domain.enumeration.ModeOfPayment;
import ug.co.absa.microsrvc.gateway.domain.enumeration.TranStatus;

/**
 * A DTransactionHistory.
 */
@Table("d_transaction_history")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "dtransactionhistory")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DTransactionHistory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("record_id")
    private String recordId;

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

    @Column("debit_currency")
    private Currency debitCurrency;

    @NotNull(message = "must not be null")
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

    @Column("internal_error_code")
    private String internalErrorCode;

    @Column("external_error_code")
    private String externalErrorCode;

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
    private String tranFreeField12;

    @Column("created_at")
    private ZonedDateTime createdAt;

    @Column("created_by")
    private String createdBy;

    @Column("updated_at")
    private ZonedDateTime updatedAt;

    @Column("updated_by")
    private String updatedBy;

    @Transient
    @JsonIgnoreProperties(value = { "customers", "customers", "customers", "customers" }, allowSetters = true)
    private Customer customer;

    @Column("customer_id")
    private Long customerId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DTransactionHistory id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecordId() {
        return this.recordId;
    }

    public DTransactionHistory recordId(String recordId) {
        this.setRecordId(recordId);
        return this;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getTransferId() {
        return this.transferId;
    }

    public DTransactionHistory transferId(String transferId) {
        this.setTransferId(transferId);
        return this;
    }

    public void setTransferId(String transferId) {
        this.transferId = transferId;
    }

    public String getProductCode() {
        return this.productCode;
    }

    public DTransactionHistory productCode(String productCode) {
        this.setProductCode(productCode);
        return this;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public Channel getPaymentChannelCode() {
        return this.paymentChannelCode;
    }

    public DTransactionHistory paymentChannelCode(Channel paymentChannelCode) {
        this.setPaymentChannelCode(paymentChannelCode);
        return this;
    }

    public void setPaymentChannelCode(Channel paymentChannelCode) {
        this.paymentChannelCode = paymentChannelCode;
    }

    public String getDebitAccountNumber() {
        return this.debitAccountNumber;
    }

    public DTransactionHistory debitAccountNumber(String debitAccountNumber) {
        this.setDebitAccountNumber(debitAccountNumber);
        return this;
    }

    public void setDebitAccountNumber(String debitAccountNumber) {
        this.debitAccountNumber = debitAccountNumber;
    }

    public String getCreditAccountNumber() {
        return this.creditAccountNumber;
    }

    public DTransactionHistory creditAccountNumber(String creditAccountNumber) {
        this.setCreditAccountNumber(creditAccountNumber);
        return this;
    }

    public void setCreditAccountNumber(String creditAccountNumber) {
        this.creditAccountNumber = creditAccountNumber;
    }

    public Integer getDebitAmount() {
        return this.debitAmount;
    }

    public DTransactionHistory debitAmount(Integer debitAmount) {
        this.setDebitAmount(debitAmount);
        return this;
    }

    public void setDebitAmount(Integer debitAmount) {
        this.debitAmount = debitAmount;
    }

    public Integer getCreditAmount() {
        return this.creditAmount;
    }

    public DTransactionHistory creditAmount(Integer creditAmount) {
        this.setCreditAmount(creditAmount);
        return this;
    }

    public void setCreditAmount(Integer creditAmount) {
        this.creditAmount = creditAmount;
    }

    public Integer getTransactionAmount() {
        return this.transactionAmount;
    }

    public DTransactionHistory transactionAmount(Integer transactionAmount) {
        this.setTransactionAmount(transactionAmount);
        return this;
    }

    public void setTransactionAmount(Integer transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public ZonedDateTime getDebitDate() {
        return this.debitDate;
    }

    public DTransactionHistory debitDate(ZonedDateTime debitDate) {
        this.setDebitDate(debitDate);
        return this;
    }

    public void setDebitDate(ZonedDateTime debitDate) {
        this.debitDate = debitDate;
    }

    public ZonedDateTime getCreditDate() {
        return this.creditDate;
    }

    public DTransactionHistory creditDate(ZonedDateTime creditDate) {
        this.setCreditDate(creditDate);
        return this;
    }

    public void setCreditDate(ZonedDateTime creditDate) {
        this.creditDate = creditDate;
    }

    public TranStatus getStatus() {
        return this.status;
    }

    public DTransactionHistory status(TranStatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(TranStatus status) {
        this.status = status;
    }

    public ZonedDateTime getSettlementDate() {
        return this.settlementDate;
    }

    public DTransactionHistory settlementDate(ZonedDateTime settlementDate) {
        this.setSettlementDate(settlementDate);
        return this;
    }

    public void setSettlementDate(ZonedDateTime settlementDate) {
        this.settlementDate = settlementDate;
    }

    public Currency getDebitCurrency() {
        return this.debitCurrency;
    }

    public DTransactionHistory debitCurrency(Currency debitCurrency) {
        this.setDebitCurrency(debitCurrency);
        return this;
    }

    public void setDebitCurrency(Currency debitCurrency) {
        this.debitCurrency = debitCurrency;
    }

    public ZonedDateTime getTimestamp() {
        return this.timestamp;
    }

    public DTransactionHistory timestamp(ZonedDateTime timestamp) {
        this.setTimestamp(timestamp);
        return this;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public DTransactionHistory phoneNumber(String phoneNumber) {
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public DTransactionHistory email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPayerName() {
        return this.payerName;
    }

    public DTransactionHistory payerName(String payerName) {
        this.setPayerName(payerName);
        return this;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getPayerAddress() {
        return this.payerAddress;
    }

    public DTransactionHistory payerAddress(String payerAddress) {
        this.setPayerAddress(payerAddress);
        return this;
    }

    public void setPayerAddress(String payerAddress) {
        this.payerAddress = payerAddress;
    }

    public String getPayerEmail() {
        return this.payerEmail;
    }

    public DTransactionHistory payerEmail(String payerEmail) {
        this.setPayerEmail(payerEmail);
        return this;
    }

    public void setPayerEmail(String payerEmail) {
        this.payerEmail = payerEmail;
    }

    public String getPayerPhoneNumber() {
        return this.payerPhoneNumber;
    }

    public DTransactionHistory payerPhoneNumber(String payerPhoneNumber) {
        this.setPayerPhoneNumber(payerPhoneNumber);
        return this;
    }

    public void setPayerPhoneNumber(String payerPhoneNumber) {
        this.payerPhoneNumber = payerPhoneNumber;
    }

    public String getPayerNarration() {
        return this.payerNarration;
    }

    public DTransactionHistory payerNarration(String payerNarration) {
        this.setPayerNarration(payerNarration);
        return this;
    }

    public void setPayerNarration(String payerNarration) {
        this.payerNarration = payerNarration;
    }

    public String getPayerBranchId() {
        return this.payerBranchId;
    }

    public DTransactionHistory payerBranchId(String payerBranchId) {
        this.setPayerBranchId(payerBranchId);
        return this;
    }

    public void setPayerBranchId(String payerBranchId) {
        this.payerBranchId = payerBranchId;
    }

    public String getBeneficiaryName() {
        return this.beneficiaryName;
    }

    public DTransactionHistory beneficiaryName(String beneficiaryName) {
        this.setBeneficiaryName(beneficiaryName);
        return this;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public String getBeneficiaryAccount() {
        return this.beneficiaryAccount;
    }

    public DTransactionHistory beneficiaryAccount(String beneficiaryAccount) {
        this.setBeneficiaryAccount(beneficiaryAccount);
        return this;
    }

    public void setBeneficiaryAccount(String beneficiaryAccount) {
        this.beneficiaryAccount = beneficiaryAccount;
    }

    public String getBeneficiaryBranchId() {
        return this.beneficiaryBranchId;
    }

    public DTransactionHistory beneficiaryBranchId(String beneficiaryBranchId) {
        this.setBeneficiaryBranchId(beneficiaryBranchId);
        return this;
    }

    public void setBeneficiaryBranchId(String beneficiaryBranchId) {
        this.beneficiaryBranchId = beneficiaryBranchId;
    }

    public String getBeneficiaryPhoneNumber() {
        return this.beneficiaryPhoneNumber;
    }

    public DTransactionHistory beneficiaryPhoneNumber(String beneficiaryPhoneNumber) {
        this.setBeneficiaryPhoneNumber(beneficiaryPhoneNumber);
        return this;
    }

    public void setBeneficiaryPhoneNumber(String beneficiaryPhoneNumber) {
        this.beneficiaryPhoneNumber = beneficiaryPhoneNumber;
    }

    public TranStatus getTranStatus() {
        return this.tranStatus;
    }

    public DTransactionHistory tranStatus(TranStatus tranStatus) {
        this.setTranStatus(tranStatus);
        return this;
    }

    public void setTranStatus(TranStatus tranStatus) {
        this.tranStatus = tranStatus;
    }

    public String getNarration1() {
        return this.narration1;
    }

    public DTransactionHistory narration1(String narration1) {
        this.setNarration1(narration1);
        return this;
    }

    public void setNarration1(String narration1) {
        this.narration1 = narration1;
    }

    public String getNarration2() {
        return this.narration2;
    }

    public DTransactionHistory narration2(String narration2) {
        this.setNarration2(narration2);
        return this;
    }

    public void setNarration2(String narration2) {
        this.narration2 = narration2;
    }

    public String getNarration3() {
        return this.narration3;
    }

    public DTransactionHistory narration3(String narration3) {
        this.setNarration3(narration3);
        return this;
    }

    public void setNarration3(String narration3) {
        this.narration3 = narration3;
    }

    public ModeOfPayment getModeOfPayment() {
        return this.modeOfPayment;
    }

    public DTransactionHistory modeOfPayment(ModeOfPayment modeOfPayment) {
        this.setModeOfPayment(modeOfPayment);
        return this;
    }

    public void setModeOfPayment(ModeOfPayment modeOfPayment) {
        this.modeOfPayment = modeOfPayment;
    }

    public Integer getRetries() {
        return this.retries;
    }

    public DTransactionHistory retries(Integer retries) {
        this.setRetries(retries);
        return this;
    }

    public void setRetries(Integer retries) {
        this.retries = retries;
    }

    public Boolean getPosted() {
        return this.posted;
    }

    public DTransactionHistory posted(Boolean posted) {
        this.setPosted(posted);
        return this;
    }

    public void setPosted(Boolean posted) {
        this.posted = posted;
    }

    public String getApiId() {
        return this.apiId;
    }

    public DTransactionHistory apiId(String apiId) {
        this.setApiId(apiId);
        return this;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
    }

    public String getApiVersion() {
        return this.apiVersion;
    }

    public DTransactionHistory apiVersion(String apiVersion) {
        this.setApiVersion(apiVersion);
        return this;
    }

    public void setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
    }

    public String getPostingApi() {
        return this.postingApi;
    }

    public DTransactionHistory postingApi(String postingApi) {
        this.setPostingApi(postingApi);
        return this;
    }

    public void setPostingApi(String postingApi) {
        this.postingApi = postingApi;
    }

    public Boolean getIsPosted() {
        return this.isPosted;
    }

    public DTransactionHistory isPosted(Boolean isPosted) {
        this.setIsPosted(isPosted);
        return this;
    }

    public void setIsPosted(Boolean isPosted) {
        this.isPosted = isPosted;
    }

    public String getPostedBy() {
        return this.postedBy;
    }

    public DTransactionHistory postedBy(String postedBy) {
        this.setPostedBy(postedBy);
        return this;
    }

    public void setPostedBy(String postedBy) {
        this.postedBy = postedBy;
    }

    public String getPostedDate() {
        return this.postedDate;
    }

    public DTransactionHistory postedDate(String postedDate) {
        this.setPostedDate(postedDate);
        return this;
    }

    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }

    public String getInternalErrorCode() {
        return this.internalErrorCode;
    }

    public DTransactionHistory internalErrorCode(String internalErrorCode) {
        this.setInternalErrorCode(internalErrorCode);
        return this;
    }

    public void setInternalErrorCode(String internalErrorCode) {
        this.internalErrorCode = internalErrorCode;
    }

    public String getExternalErrorCode() {
        return this.externalErrorCode;
    }

    public DTransactionHistory externalErrorCode(String externalErrorCode) {
        this.setExternalErrorCode(externalErrorCode);
        return this;
    }

    public void setExternalErrorCode(String externalErrorCode) {
        this.externalErrorCode = externalErrorCode;
    }

    public String getTranFreeField1() {
        return this.tranFreeField1;
    }

    public DTransactionHistory tranFreeField1(String tranFreeField1) {
        this.setTranFreeField1(tranFreeField1);
        return this;
    }

    public void setTranFreeField1(String tranFreeField1) {
        this.tranFreeField1 = tranFreeField1;
    }

    public String getTranFreeField3() {
        return this.tranFreeField3;
    }

    public DTransactionHistory tranFreeField3(String tranFreeField3) {
        this.setTranFreeField3(tranFreeField3);
        return this;
    }

    public void setTranFreeField3(String tranFreeField3) {
        this.tranFreeField3 = tranFreeField3;
    }

    public String getTranFreeField4() {
        return this.tranFreeField4;
    }

    public DTransactionHistory tranFreeField4(String tranFreeField4) {
        this.setTranFreeField4(tranFreeField4);
        return this;
    }

    public void setTranFreeField4(String tranFreeField4) {
        this.tranFreeField4 = tranFreeField4;
    }

    public String getTranFreeField5() {
        return this.tranFreeField5;
    }

    public DTransactionHistory tranFreeField5(String tranFreeField5) {
        this.setTranFreeField5(tranFreeField5);
        return this;
    }

    public void setTranFreeField5(String tranFreeField5) {
        this.tranFreeField5 = tranFreeField5;
    }

    public String getTranFreeField6() {
        return this.tranFreeField6;
    }

    public DTransactionHistory tranFreeField6(String tranFreeField6) {
        this.setTranFreeField6(tranFreeField6);
        return this;
    }

    public void setTranFreeField6(String tranFreeField6) {
        this.tranFreeField6 = tranFreeField6;
    }

    public String getTranFreeField7() {
        return this.tranFreeField7;
    }

    public DTransactionHistory tranFreeField7(String tranFreeField7) {
        this.setTranFreeField7(tranFreeField7);
        return this;
    }

    public void setTranFreeField7(String tranFreeField7) {
        this.tranFreeField7 = tranFreeField7;
    }

    public String getTranFreeField8() {
        return this.tranFreeField8;
    }

    public DTransactionHistory tranFreeField8(String tranFreeField8) {
        this.setTranFreeField8(tranFreeField8);
        return this;
    }

    public void setTranFreeField8(String tranFreeField8) {
        this.tranFreeField8 = tranFreeField8;
    }

    public String getTranFreeField9() {
        return this.tranFreeField9;
    }

    public DTransactionHistory tranFreeField9(String tranFreeField9) {
        this.setTranFreeField9(tranFreeField9);
        return this;
    }

    public void setTranFreeField9(String tranFreeField9) {
        this.tranFreeField9 = tranFreeField9;
    }

    public String getTranFreeField10() {
        return this.tranFreeField10;
    }

    public DTransactionHistory tranFreeField10(String tranFreeField10) {
        this.setTranFreeField10(tranFreeField10);
        return this;
    }

    public void setTranFreeField10(String tranFreeField10) {
        this.tranFreeField10 = tranFreeField10;
    }

    public String getTranFreeField11() {
        return this.tranFreeField11;
    }

    public DTransactionHistory tranFreeField11(String tranFreeField11) {
        this.setTranFreeField11(tranFreeField11);
        return this;
    }

    public void setTranFreeField11(String tranFreeField11) {
        this.tranFreeField11 = tranFreeField11;
    }

    public String getTranFreeField12() {
        return this.tranFreeField12;
    }

    public DTransactionHistory tranFreeField12(String tranFreeField12) {
        this.setTranFreeField12(tranFreeField12);
        return this;
    }

    public void setTranFreeField12(String tranFreeField12) {
        this.tranFreeField12 = tranFreeField12;
    }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public DTransactionHistory createdAt(ZonedDateTime createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public DTransactionHistory createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public DTransactionHistory updatedAt(ZonedDateTime updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public DTransactionHistory updatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        this.customerId = customer != null ? customer.getId() : null;
    }

    public DTransactionHistory customer(Customer customer) {
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
        if (!(o instanceof DTransactionHistory)) {
            return false;
        }
        return id != null && id.equals(((DTransactionHistory) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DTransactionHistory{" +
            "id=" + getId() +
            ", recordId='" + getRecordId() + "'" +
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
            ", modeOfPayment='" + getModeOfPayment() + "'" +
            ", retries=" + getRetries() +
            ", posted='" + getPosted() + "'" +
            ", apiId='" + getApiId() + "'" +
            ", apiVersion='" + getApiVersion() + "'" +
            ", postingApi='" + getPostingApi() + "'" +
            ", isPosted='" + getIsPosted() + "'" +
            ", postedBy='" + getPostedBy() + "'" +
            ", postedDate='" + getPostedDate() + "'" +
            ", internalErrorCode='" + getInternalErrorCode() + "'" +
            ", externalErrorCode='" + getExternalErrorCode() + "'" +
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
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            "}";
    }
}
