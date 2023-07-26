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
import ug.co.absa.microsrvc.gateway.domain.enumeration.ErrorCodes;
import ug.co.absa.microsrvc.gateway.domain.enumeration.ErrorMessage;
import ug.co.absa.microsrvc.gateway.domain.enumeration.TranStatus;

/**
 * A DTransactionSummary.
 */
@Table("d_transaction_summary")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "dtransactionsummary")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DTransactionSummary implements Serializable {

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

    @Column("transaction_reference_number")
    private String transactionReferenceNumber;

    @Column("record_id")
    private String recordId;

    @Column("transaction_id")
    private String transactionId;

    @NotNull(message = "must not be null")
    @Column("transaction_type")
    private String transactionType;

    @NotNull(message = "must not be null")
    @Column("transaction_amount")
    private String transactionAmount;

    @Column("transaction_date")
    private ZonedDateTime transactionDate;

    @Column("transaction_status")
    private TranStatus transactionStatus;

    @Column("free_field_1")
    private String freeField1;

    @Column("free_field_2")
    private String freeField2;

    @Column("free_field_3")
    private String freeField3;

    @Column("free_field_4")
    private String freeField4;

    @Column("free_field_5")
    private String freeField5;

    @Column("free_field_6")
    private byte[] freeField6;

    @Column("free_field_6_content_type")
    private String freeField6ContentType;

    @NotNull(message = "must not be null")
    @Column("created_at")
    private ZonedDateTime createdAt;

    @Column("created_by")
    private String createdBy;

    @Column("updated_at")
    private ZonedDateTime updatedAt;

    @Column("updated_by")
    private String updatedBy;

    @Column("error_code")
    private ErrorCodes errorCode;

    @Column("error_message")
    private ErrorMessage errorMessage;

    @Transient
    @JsonIgnoreProperties(value = { "customers", "customers", "customers", "customers" }, allowSetters = true)
    private Customer customer;

    @Column("customer_id")
    private Long customerId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DTransactionSummary id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getAbsaTranRef() {
        return this.absaTranRef;
    }

    public DTransactionSummary absaTranRef(UUID absaTranRef) {
        this.setAbsaTranRef(absaTranRef);
        return this;
    }

    public void setAbsaTranRef(UUID absaTranRef) {
        this.absaTranRef = absaTranRef;
    }

    public String getBillerId() {
        return this.billerId;
    }

    public DTransactionSummary billerId(String billerId) {
        this.setBillerId(billerId);
        return this;
    }

    public void setBillerId(String billerId) {
        this.billerId = billerId;
    }

    public String getPaymentItemCode() {
        return this.paymentItemCode;
    }

    public DTransactionSummary paymentItemCode(String paymentItemCode) {
        this.setPaymentItemCode(paymentItemCode);
        return this;
    }

    public void setPaymentItemCode(String paymentItemCode) {
        this.paymentItemCode = paymentItemCode;
    }

    public String getDtDTransactionId() {
        return this.dtDTransactionId;
    }

    public DTransactionSummary dtDTransactionId(String dtDTransactionId) {
        this.setDtDTransactionId(dtDTransactionId);
        return this;
    }

    public void setDtDTransactionId(String dtDTransactionId) {
        this.dtDTransactionId = dtDTransactionId;
    }

    public String getTransactionReferenceNumber() {
        return this.transactionReferenceNumber;
    }

    public DTransactionSummary transactionReferenceNumber(String transactionReferenceNumber) {
        this.setTransactionReferenceNumber(transactionReferenceNumber);
        return this;
    }

    public void setTransactionReferenceNumber(String transactionReferenceNumber) {
        this.transactionReferenceNumber = transactionReferenceNumber;
    }

    public String getRecordId() {
        return this.recordId;
    }

    public DTransactionSummary recordId(String recordId) {
        this.setRecordId(recordId);
        return this;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    public DTransactionSummary transactionId(String transactionId) {
        this.setTransactionId(transactionId);
        return this;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getTransactionType() {
        return this.transactionType;
    }

    public DTransactionSummary transactionType(String transactionType) {
        this.setTransactionType(transactionType);
        return this;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getTransactionAmount() {
        return this.transactionAmount;
    }

    public DTransactionSummary transactionAmount(String transactionAmount) {
        this.setTransactionAmount(transactionAmount);
        return this;
    }

    public void setTransactionAmount(String transactionAmount) {
        this.transactionAmount = transactionAmount;
    }

    public ZonedDateTime getTransactionDate() {
        return this.transactionDate;
    }

    public DTransactionSummary transactionDate(ZonedDateTime transactionDate) {
        this.setTransactionDate(transactionDate);
        return this;
    }

    public void setTransactionDate(ZonedDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public TranStatus getTransactionStatus() {
        return this.transactionStatus;
    }

    public DTransactionSummary transactionStatus(TranStatus transactionStatus) {
        this.setTransactionStatus(transactionStatus);
        return this;
    }

    public void setTransactionStatus(TranStatus transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    public String getFreeField1() {
        return this.freeField1;
    }

    public DTransactionSummary freeField1(String freeField1) {
        this.setFreeField1(freeField1);
        return this;
    }

    public void setFreeField1(String freeField1) {
        this.freeField1 = freeField1;
    }

    public String getFreeField2() {
        return this.freeField2;
    }

    public DTransactionSummary freeField2(String freeField2) {
        this.setFreeField2(freeField2);
        return this;
    }

    public void setFreeField2(String freeField2) {
        this.freeField2 = freeField2;
    }

    public String getFreeField3() {
        return this.freeField3;
    }

    public DTransactionSummary freeField3(String freeField3) {
        this.setFreeField3(freeField3);
        return this;
    }

    public void setFreeField3(String freeField3) {
        this.freeField3 = freeField3;
    }

    public String getFreeField4() {
        return this.freeField4;
    }

    public DTransactionSummary freeField4(String freeField4) {
        this.setFreeField4(freeField4);
        return this;
    }

    public void setFreeField4(String freeField4) {
        this.freeField4 = freeField4;
    }

    public String getFreeField5() {
        return this.freeField5;
    }

    public DTransactionSummary freeField5(String freeField5) {
        this.setFreeField5(freeField5);
        return this;
    }

    public void setFreeField5(String freeField5) {
        this.freeField5 = freeField5;
    }

    public byte[] getFreeField6() {
        return this.freeField6;
    }

    public DTransactionSummary freeField6(byte[] freeField6) {
        this.setFreeField6(freeField6);
        return this;
    }

    public void setFreeField6(byte[] freeField6) {
        this.freeField6 = freeField6;
    }

    public String getFreeField6ContentType() {
        return this.freeField6ContentType;
    }

    public DTransactionSummary freeField6ContentType(String freeField6ContentType) {
        this.freeField6ContentType = freeField6ContentType;
        return this;
    }

    public void setFreeField6ContentType(String freeField6ContentType) {
        this.freeField6ContentType = freeField6ContentType;
    }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public DTransactionSummary createdAt(ZonedDateTime createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public DTransactionSummary createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public DTransactionSummary updatedAt(ZonedDateTime updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public DTransactionSummary updatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public ErrorCodes getErrorCode() {
        return this.errorCode;
    }

    public DTransactionSummary errorCode(ErrorCodes errorCode) {
        this.setErrorCode(errorCode);
        return this;
    }

    public void setErrorCode(ErrorCodes errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorMessage getErrorMessage() {
        return this.errorMessage;
    }

    public DTransactionSummary errorMessage(ErrorMessage errorMessage) {
        this.setErrorMessage(errorMessage);
        return this;
    }

    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        this.customerId = customer != null ? customer.getId() : null;
    }

    public DTransactionSummary customer(Customer customer) {
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
        if (!(o instanceof DTransactionSummary)) {
            return false;
        }
        return id != null && id.equals(((DTransactionSummary) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DTransactionSummary{" +
            "id=" + getId() +
            ", absaTranRef='" + getAbsaTranRef() + "'" +
            ", billerId='" + getBillerId() + "'" +
            ", paymentItemCode='" + getPaymentItemCode() + "'" +
            ", dtDTransactionId='" + getDtDTransactionId() + "'" +
            ", transactionReferenceNumber='" + getTransactionReferenceNumber() + "'" +
            ", recordId='" + getRecordId() + "'" +
            ", transactionId='" + getTransactionId() + "'" +
            ", transactionType='" + getTransactionType() + "'" +
            ", transactionAmount='" + getTransactionAmount() + "'" +
            ", transactionDate='" + getTransactionDate() + "'" +
            ", transactionStatus='" + getTransactionStatus() + "'" +
            ", freeField1='" + getFreeField1() + "'" +
            ", freeField2='" + getFreeField2() + "'" +
            ", freeField3='" + getFreeField3() + "'" +
            ", freeField4='" + getFreeField4() + "'" +
            ", freeField5='" + getFreeField5() + "'" +
            ", freeField6='" + getFreeField6() + "'" +
            ", freeField6ContentType='" + getFreeField6ContentType() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", errorCode='" + getErrorCode() + "'" +
            ", errorMessage='" + getErrorMessage() + "'" +
            "}";
    }
}
