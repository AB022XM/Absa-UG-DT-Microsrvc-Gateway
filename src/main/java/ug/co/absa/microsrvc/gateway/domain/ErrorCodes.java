package ug.co.absa.microsrvc.gateway.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import ug.co.absa.microsrvc.gateway.domain.enumeration.ErrorMessage;
import ug.co.absa.microsrvc.gateway.domain.enumeration.TranStatus;

/**
 * A ErrorCodes.
 */
@Table("error_codes")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "errorcodes")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ErrorCodes implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("absa_tran_ref")
    private UUID absaTranRef;

    @Column("record_id")
    private String recordId;

    @NotNull(message = "must not be null")
    @Column("payment_item_code")
    private String paymentItemCode;

    @Column("dt_d_transaction_id")
    private String dtDTransactionId;

    @Column("transaction_reference_number")
    private String transactionReferenceNumber;

    @Column("external_tranid")
    private String externalTranid;

    @NotNull(message = "must not be null")
    @Column("error_code")
    private String errorCode;

    @Column("error_message")
    private ErrorMessage errorMessage;

    @Column("response_code")
    private String responseCode;

    @Column("response_message")
    private String responseMessage;

    @Column("response_body")
    private String responseBody;

    @Column("timestamp")
    private ZonedDateTime timestamp;

    @Column("request_ref")
    private String requestRef;

    @Column("status")
    private TranStatus status;

    @Column("customer_field_1")
    private String customerField1;

    @Column("customer_field_2")
    private String customerField2;

    @Column("customer_field_3")
    private String customerField3;

    @Column("customer_field_4")
    private String customerField4;

    @Column("customer_field_5")
    private String customerField5;

    @Column("customer_field_6")
    private String customerField6;

    @Column("customer_field_7")
    private String customerField7;

    @Column("customer_field_8")
    private String customerField8;

    @NotNull(message = "must not be null")
    @Column("created_at")
    private ZonedDateTime createdAt;

    @Column("created_by")
    private String createdBy;

    @Column("updated_at")
    private ZonedDateTime updatedAt;

    @Column("updated_by")
    private String updatedBy;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public ErrorCodes id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getAbsaTranRef() {
        return this.absaTranRef;
    }

    public ErrorCodes absaTranRef(UUID absaTranRef) {
        this.setAbsaTranRef(absaTranRef);
        return this;
    }

    public void setAbsaTranRef(UUID absaTranRef) {
        this.absaTranRef = absaTranRef;
    }

    public String getRecordId() {
        return this.recordId;
    }

    public ErrorCodes recordId(String recordId) {
        this.setRecordId(recordId);
        return this;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getPaymentItemCode() {
        return this.paymentItemCode;
    }

    public ErrorCodes paymentItemCode(String paymentItemCode) {
        this.setPaymentItemCode(paymentItemCode);
        return this;
    }

    public void setPaymentItemCode(String paymentItemCode) {
        this.paymentItemCode = paymentItemCode;
    }

    public String getDtDTransactionId() {
        return this.dtDTransactionId;
    }

    public ErrorCodes dtDTransactionId(String dtDTransactionId) {
        this.setDtDTransactionId(dtDTransactionId);
        return this;
    }

    public void setDtDTransactionId(String dtDTransactionId) {
        this.dtDTransactionId = dtDTransactionId;
    }

    public String getTransactionReferenceNumber() {
        return this.transactionReferenceNumber;
    }

    public ErrorCodes transactionReferenceNumber(String transactionReferenceNumber) {
        this.setTransactionReferenceNumber(transactionReferenceNumber);
        return this;
    }

    public void setTransactionReferenceNumber(String transactionReferenceNumber) {
        this.transactionReferenceNumber = transactionReferenceNumber;
    }

    public String getExternalTranid() {
        return this.externalTranid;
    }

    public ErrorCodes externalTranid(String externalTranid) {
        this.setExternalTranid(externalTranid);
        return this;
    }

    public void setExternalTranid(String externalTranid) {
        this.externalTranid = externalTranid;
    }

    public String getErrorCode() {
        return this.errorCode;
    }

    public ErrorCodes errorCode(String errorCode) {
        this.setErrorCode(errorCode);
        return this;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorMessage getErrorMessage() {
        return this.errorMessage;
    }

    public ErrorCodes errorMessage(ErrorMessage errorMessage) {
        this.setErrorMessage(errorMessage);
        return this;
    }

    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    public String getResponseCode() {
        return this.responseCode;
    }

    public ErrorCodes responseCode(String responseCode) {
        this.setResponseCode(responseCode);
        return this;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return this.responseMessage;
    }

    public ErrorCodes responseMessage(String responseMessage) {
        this.setResponseMessage(responseMessage);
        return this;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseBody() {
        return this.responseBody;
    }

    public ErrorCodes responseBody(String responseBody) {
        this.setResponseBody(responseBody);
        return this;
    }

    public void setResponseBody(String responseBody) {
        this.responseBody = responseBody;
    }

    public ZonedDateTime getTimestamp() {
        return this.timestamp;
    }

    public ErrorCodes timestamp(ZonedDateTime timestamp) {
        this.setTimestamp(timestamp);
        return this;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getRequestRef() {
        return this.requestRef;
    }

    public ErrorCodes requestRef(String requestRef) {
        this.setRequestRef(requestRef);
        return this;
    }

    public void setRequestRef(String requestRef) {
        this.requestRef = requestRef;
    }

    public TranStatus getStatus() {
        return this.status;
    }

    public ErrorCodes status(TranStatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(TranStatus status) {
        this.status = status;
    }

    public String getCustomerField1() {
        return this.customerField1;
    }

    public ErrorCodes customerField1(String customerField1) {
        this.setCustomerField1(customerField1);
        return this;
    }

    public void setCustomerField1(String customerField1) {
        this.customerField1 = customerField1;
    }

    public String getCustomerField2() {
        return this.customerField2;
    }

    public ErrorCodes customerField2(String customerField2) {
        this.setCustomerField2(customerField2);
        return this;
    }

    public void setCustomerField2(String customerField2) {
        this.customerField2 = customerField2;
    }

    public String getCustomerField3() {
        return this.customerField3;
    }

    public ErrorCodes customerField3(String customerField3) {
        this.setCustomerField3(customerField3);
        return this;
    }

    public void setCustomerField3(String customerField3) {
        this.customerField3 = customerField3;
    }

    public String getCustomerField4() {
        return this.customerField4;
    }

    public ErrorCodes customerField4(String customerField4) {
        this.setCustomerField4(customerField4);
        return this;
    }

    public void setCustomerField4(String customerField4) {
        this.customerField4 = customerField4;
    }

    public String getCustomerField5() {
        return this.customerField5;
    }

    public ErrorCodes customerField5(String customerField5) {
        this.setCustomerField5(customerField5);
        return this;
    }

    public void setCustomerField5(String customerField5) {
        this.customerField5 = customerField5;
    }

    public String getCustomerField6() {
        return this.customerField6;
    }

    public ErrorCodes customerField6(String customerField6) {
        this.setCustomerField6(customerField6);
        return this;
    }

    public void setCustomerField6(String customerField6) {
        this.customerField6 = customerField6;
    }

    public String getCustomerField7() {
        return this.customerField7;
    }

    public ErrorCodes customerField7(String customerField7) {
        this.setCustomerField7(customerField7);
        return this;
    }

    public void setCustomerField7(String customerField7) {
        this.customerField7 = customerField7;
    }

    public String getCustomerField8() {
        return this.customerField8;
    }

    public ErrorCodes customerField8(String customerField8) {
        this.setCustomerField8(customerField8);
        return this;
    }

    public void setCustomerField8(String customerField8) {
        this.customerField8 = customerField8;
    }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public ErrorCodes createdAt(ZonedDateTime createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public ErrorCodes createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public ErrorCodes updatedAt(ZonedDateTime updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public ErrorCodes updatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ErrorCodes)) {
            return false;
        }
        return id != null && id.equals(((ErrorCodes) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ErrorCodes{" +
            "id=" + getId() +
            ", absaTranRef='" + getAbsaTranRef() + "'" +
            ", recordId='" + getRecordId() + "'" +
            ", paymentItemCode='" + getPaymentItemCode() + "'" +
            ", dtDTransactionId='" + getDtDTransactionId() + "'" +
            ", transactionReferenceNumber='" + getTransactionReferenceNumber() + "'" +
            ", externalTranid='" + getExternalTranid() + "'" +
            ", errorCode='" + getErrorCode() + "'" +
            ", errorMessage='" + getErrorMessage() + "'" +
            ", responseCode='" + getResponseCode() + "'" +
            ", responseMessage='" + getResponseMessage() + "'" +
            ", responseBody='" + getResponseBody() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            ", requestRef='" + getRequestRef() + "'" +
            ", status='" + getStatus() + "'" +
            ", customerField1='" + getCustomerField1() + "'" +
            ", customerField2='" + getCustomerField2() + "'" +
            ", customerField3='" + getCustomerField3() + "'" +
            ", customerField4='" + getCustomerField4() + "'" +
            ", customerField5='" + getCustomerField5() + "'" +
            ", customerField6='" + getCustomerField6() + "'" +
            ", customerField7='" + getCustomerField7() + "'" +
            ", customerField8='" + getCustomerField8() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            "}";
    }
}
