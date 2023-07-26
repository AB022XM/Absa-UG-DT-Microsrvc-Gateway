package ug.co.absa.microsrvc.gateway.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import ug.co.absa.microsrvc.gateway.domain.enumeration.ErrorCodes;
import ug.co.absa.microsrvc.gateway.domain.enumeration.ErrorMessage;
import ug.co.absa.microsrvc.gateway.domain.enumeration.TranStatus;

/**
 * A RequestInfo.
 */
@Table("request_info")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "requestinfo")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class RequestInfo implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("record_id")
    private String recordId;

    @Column("transaction_id")
    private String transactionId;

    @Column("request_data")
    private String requestData;

    @Column("param_list")
    private String paramList;

    @Column("timestamp")
    private ZonedDateTime timestamp;

    @Column("request_ref")
    private String requestRef;

    @Column("status")
    private TranStatus status;

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
    private String freeField6;

    @Column("time")
    private ZonedDateTime time;

    @Column("error_code")
    private ErrorCodes errorCode;

    @Column("error_message")
    private ErrorMessage errorMessage;

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

    public RequestInfo id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecordId() {
        return this.recordId;
    }

    public RequestInfo recordId(String recordId) {
        this.setRecordId(recordId);
        return this;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    public RequestInfo transactionId(String transactionId) {
        this.setTransactionId(transactionId);
        return this;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getRequestData() {
        return this.requestData;
    }

    public RequestInfo requestData(String requestData) {
        this.setRequestData(requestData);
        return this;
    }

    public void setRequestData(String requestData) {
        this.requestData = requestData;
    }

    public String getParamList() {
        return this.paramList;
    }

    public RequestInfo paramList(String paramList) {
        this.setParamList(paramList);
        return this;
    }

    public void setParamList(String paramList) {
        this.paramList = paramList;
    }

    public ZonedDateTime getTimestamp() {
        return this.timestamp;
    }

    public RequestInfo timestamp(ZonedDateTime timestamp) {
        this.setTimestamp(timestamp);
        return this;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getRequestRef() {
        return this.requestRef;
    }

    public RequestInfo requestRef(String requestRef) {
        this.setRequestRef(requestRef);
        return this;
    }

    public void setRequestRef(String requestRef) {
        this.requestRef = requestRef;
    }

    public TranStatus getStatus() {
        return this.status;
    }

    public RequestInfo status(TranStatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(TranStatus status) {
        this.status = status;
    }

    public String getFreeField1() {
        return this.freeField1;
    }

    public RequestInfo freeField1(String freeField1) {
        this.setFreeField1(freeField1);
        return this;
    }

    public void setFreeField1(String freeField1) {
        this.freeField1 = freeField1;
    }

    public String getFreeField2() {
        return this.freeField2;
    }

    public RequestInfo freeField2(String freeField2) {
        this.setFreeField2(freeField2);
        return this;
    }

    public void setFreeField2(String freeField2) {
        this.freeField2 = freeField2;
    }

    public String getFreeField3() {
        return this.freeField3;
    }

    public RequestInfo freeField3(String freeField3) {
        this.setFreeField3(freeField3);
        return this;
    }

    public void setFreeField3(String freeField3) {
        this.freeField3 = freeField3;
    }

    public String getFreeField4() {
        return this.freeField4;
    }

    public RequestInfo freeField4(String freeField4) {
        this.setFreeField4(freeField4);
        return this;
    }

    public void setFreeField4(String freeField4) {
        this.freeField4 = freeField4;
    }

    public String getFreeField5() {
        return this.freeField5;
    }

    public RequestInfo freeField5(String freeField5) {
        this.setFreeField5(freeField5);
        return this;
    }

    public void setFreeField5(String freeField5) {
        this.freeField5 = freeField5;
    }

    public String getFreeField6() {
        return this.freeField6;
    }

    public RequestInfo freeField6(String freeField6) {
        this.setFreeField6(freeField6);
        return this;
    }

    public void setFreeField6(String freeField6) {
        this.freeField6 = freeField6;
    }

    public ZonedDateTime getTime() {
        return this.time;
    }

    public RequestInfo time(ZonedDateTime time) {
        this.setTime(time);
        return this;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
    }

    public ErrorCodes getErrorCode() {
        return this.errorCode;
    }

    public RequestInfo errorCode(ErrorCodes errorCode) {
        this.setErrorCode(errorCode);
        return this;
    }

    public void setErrorCode(ErrorCodes errorCode) {
        this.errorCode = errorCode;
    }

    public ErrorMessage getErrorMessage() {
        return this.errorMessage;
    }

    public RequestInfo errorMessage(ErrorMessage errorMessage) {
        this.setErrorMessage(errorMessage);
        return this;
    }

    public void setErrorMessage(ErrorMessage errorMessage) {
        this.errorMessage = errorMessage;
    }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public RequestInfo createdAt(ZonedDateTime createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public RequestInfo createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public RequestInfo updatedAt(ZonedDateTime updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public RequestInfo updatedBy(String updatedBy) {
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
        if (!(o instanceof RequestInfo)) {
            return false;
        }
        return id != null && id.equals(((RequestInfo) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "RequestInfo{" +
            "id=" + getId() +
            ", recordId='" + getRecordId() + "'" +
            ", transactionId='" + getTransactionId() + "'" +
            ", requestData='" + getRequestData() + "'" +
            ", paramList='" + getParamList() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            ", requestRef='" + getRequestRef() + "'" +
            ", status='" + getStatus() + "'" +
            ", freeField1='" + getFreeField1() + "'" +
            ", freeField2='" + getFreeField2() + "'" +
            ", freeField3='" + getFreeField3() + "'" +
            ", freeField4='" + getFreeField4() + "'" +
            ", freeField5='" + getFreeField5() + "'" +
            ", freeField6='" + getFreeField6() + "'" +
            ", time='" + getTime() + "'" +
            ", errorCode='" + getErrorCode() + "'" +
            ", errorMessage='" + getErrorMessage() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            "}";
    }
}
