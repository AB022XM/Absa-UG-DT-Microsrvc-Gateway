package ug.co.absa.microsrvc.gateway.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A IncomingJSONRequest.
 */
@Table("incoming_json_request")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "incomingjsonrequest")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class IncomingJSONRequest implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("absa_tran_ref")
    private UUID absaTranRef;

    @Column("dt_d_transaction_id")
    private String dtDTransactionId;

    @Column("amol_d_transaction_id")
    private String amolDTransactionId;

    @Column("transaction_reference_number")
    private String transactionReferenceNumber;

    @Column("token")
    private String token;

    @Column("transaction_id")
    private String transactionId;

    @Column("from_endpoint")
    private String fromEndpoint;

    @Column("to_endpoint")
    private String toEndpoint;

    @Column("request_json")
    private String requestJson;

    @NotNull(message = "must not be null")
    @Column("request_type")
    private String requestType;

    @Column("request_description")
    private String requestDescription;

    @Column("request_status")
    private String requestStatus;

    @Column("additional_info")
    private String additionalInfo;

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

    @Column("free_field_7")
    private byte[] freeField7;

    @Column("free_field_7_content_type")
    private String freeField7ContentType;

    @Column("free_field_8")
    private byte[] freeField8;

    @Column("free_field_8_content_type")
    private String freeField8ContentType;

    @Column("free_field_9")
    private String freeField9;

    @Column("free_field_10")
    private Boolean freeField10;

    @Column("free_field_11")
    private Boolean freeField11;

    @Column("free_field_12")
    private Integer freeField12;

    @Column("free_field_13")
    private String freeField13;

    @Column("free_field_14")
    private String freeField14;

    @Column("free_field_15")
    private String freeField15;

    @Column("free_field_16")
    private String freeField16;

    @Column("free_field_17")
    private String freeField17;

    @Column("free_field_18")
    private String freeField18;

    @Column("free_field_19")
    private String freeField19;

    @Column("free_field_20")
    private String freeField20;

    @NotNull(message = "must not be null")
    @Column("timestamp")
    private ZonedDateTime timestamp;

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

    public IncomingJSONRequest id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getAbsaTranRef() {
        return this.absaTranRef;
    }

    public IncomingJSONRequest absaTranRef(UUID absaTranRef) {
        this.setAbsaTranRef(absaTranRef);
        return this;
    }

    public void setAbsaTranRef(UUID absaTranRef) {
        this.absaTranRef = absaTranRef;
    }

    public String getDtDTransactionId() {
        return this.dtDTransactionId;
    }

    public IncomingJSONRequest dtDTransactionId(String dtDTransactionId) {
        this.setDtDTransactionId(dtDTransactionId);
        return this;
    }

    public void setDtDTransactionId(String dtDTransactionId) {
        this.dtDTransactionId = dtDTransactionId;
    }

    public String getAmolDTransactionId() {
        return this.amolDTransactionId;
    }

    public IncomingJSONRequest amolDTransactionId(String amolDTransactionId) {
        this.setAmolDTransactionId(amolDTransactionId);
        return this;
    }

    public void setAmolDTransactionId(String amolDTransactionId) {
        this.amolDTransactionId = amolDTransactionId;
    }

    public String getTransactionReferenceNumber() {
        return this.transactionReferenceNumber;
    }

    public IncomingJSONRequest transactionReferenceNumber(String transactionReferenceNumber) {
        this.setTransactionReferenceNumber(transactionReferenceNumber);
        return this;
    }

    public void setTransactionReferenceNumber(String transactionReferenceNumber) {
        this.transactionReferenceNumber = transactionReferenceNumber;
    }

    public String getToken() {
        return this.token;
    }

    public IncomingJSONRequest token(String token) {
        this.setToken(token);
        return this;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    public IncomingJSONRequest transactionId(String transactionId) {
        this.setTransactionId(transactionId);
        return this;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getFromEndpoint() {
        return this.fromEndpoint;
    }

    public IncomingJSONRequest fromEndpoint(String fromEndpoint) {
        this.setFromEndpoint(fromEndpoint);
        return this;
    }

    public void setFromEndpoint(String fromEndpoint) {
        this.fromEndpoint = fromEndpoint;
    }

    public String getToEndpoint() {
        return this.toEndpoint;
    }

    public IncomingJSONRequest toEndpoint(String toEndpoint) {
        this.setToEndpoint(toEndpoint);
        return this;
    }

    public void setToEndpoint(String toEndpoint) {
        this.toEndpoint = toEndpoint;
    }

    public String getRequestJson() {
        return this.requestJson;
    }

    public IncomingJSONRequest requestJson(String requestJson) {
        this.setRequestJson(requestJson);
        return this;
    }

    public void setRequestJson(String requestJson) {
        this.requestJson = requestJson;
    }

    public String getRequestType() {
        return this.requestType;
    }

    public IncomingJSONRequest requestType(String requestType) {
        this.setRequestType(requestType);
        return this;
    }

    public void setRequestType(String requestType) {
        this.requestType = requestType;
    }

    public String getRequestDescription() {
        return this.requestDescription;
    }

    public IncomingJSONRequest requestDescription(String requestDescription) {
        this.setRequestDescription(requestDescription);
        return this;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }

    public String getRequestStatus() {
        return this.requestStatus;
    }

    public IncomingJSONRequest requestStatus(String requestStatus) {
        this.setRequestStatus(requestStatus);
        return this;
    }

    public void setRequestStatus(String requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getAdditionalInfo() {
        return this.additionalInfo;
    }

    public IncomingJSONRequest additionalInfo(String additionalInfo) {
        this.setAdditionalInfo(additionalInfo);
        return this;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public String getFreeField1() {
        return this.freeField1;
    }

    public IncomingJSONRequest freeField1(String freeField1) {
        this.setFreeField1(freeField1);
        return this;
    }

    public void setFreeField1(String freeField1) {
        this.freeField1 = freeField1;
    }

    public String getFreeField2() {
        return this.freeField2;
    }

    public IncomingJSONRequest freeField2(String freeField2) {
        this.setFreeField2(freeField2);
        return this;
    }

    public void setFreeField2(String freeField2) {
        this.freeField2 = freeField2;
    }

    public String getFreeField3() {
        return this.freeField3;
    }

    public IncomingJSONRequest freeField3(String freeField3) {
        this.setFreeField3(freeField3);
        return this;
    }

    public void setFreeField3(String freeField3) {
        this.freeField3 = freeField3;
    }

    public String getFreeField4() {
        return this.freeField4;
    }

    public IncomingJSONRequest freeField4(String freeField4) {
        this.setFreeField4(freeField4);
        return this;
    }

    public void setFreeField4(String freeField4) {
        this.freeField4 = freeField4;
    }

    public String getFreeField5() {
        return this.freeField5;
    }

    public IncomingJSONRequest freeField5(String freeField5) {
        this.setFreeField5(freeField5);
        return this;
    }

    public void setFreeField5(String freeField5) {
        this.freeField5 = freeField5;
    }

    public byte[] getFreeField6() {
        return this.freeField6;
    }

    public IncomingJSONRequest freeField6(byte[] freeField6) {
        this.setFreeField6(freeField6);
        return this;
    }

    public void setFreeField6(byte[] freeField6) {
        this.freeField6 = freeField6;
    }

    public String getFreeField6ContentType() {
        return this.freeField6ContentType;
    }

    public IncomingJSONRequest freeField6ContentType(String freeField6ContentType) {
        this.freeField6ContentType = freeField6ContentType;
        return this;
    }

    public void setFreeField6ContentType(String freeField6ContentType) {
        this.freeField6ContentType = freeField6ContentType;
    }

    public byte[] getFreeField7() {
        return this.freeField7;
    }

    public IncomingJSONRequest freeField7(byte[] freeField7) {
        this.setFreeField7(freeField7);
        return this;
    }

    public void setFreeField7(byte[] freeField7) {
        this.freeField7 = freeField7;
    }

    public String getFreeField7ContentType() {
        return this.freeField7ContentType;
    }

    public IncomingJSONRequest freeField7ContentType(String freeField7ContentType) {
        this.freeField7ContentType = freeField7ContentType;
        return this;
    }

    public void setFreeField7ContentType(String freeField7ContentType) {
        this.freeField7ContentType = freeField7ContentType;
    }

    public byte[] getFreeField8() {
        return this.freeField8;
    }

    public IncomingJSONRequest freeField8(byte[] freeField8) {
        this.setFreeField8(freeField8);
        return this;
    }

    public void setFreeField8(byte[] freeField8) {
        this.freeField8 = freeField8;
    }

    public String getFreeField8ContentType() {
        return this.freeField8ContentType;
    }

    public IncomingJSONRequest freeField8ContentType(String freeField8ContentType) {
        this.freeField8ContentType = freeField8ContentType;
        return this;
    }

    public void setFreeField8ContentType(String freeField8ContentType) {
        this.freeField8ContentType = freeField8ContentType;
    }

    public String getFreeField9() {
        return this.freeField9;
    }

    public IncomingJSONRequest freeField9(String freeField9) {
        this.setFreeField9(freeField9);
        return this;
    }

    public void setFreeField9(String freeField9) {
        this.freeField9 = freeField9;
    }

    public Boolean getFreeField10() {
        return this.freeField10;
    }

    public IncomingJSONRequest freeField10(Boolean freeField10) {
        this.setFreeField10(freeField10);
        return this;
    }

    public void setFreeField10(Boolean freeField10) {
        this.freeField10 = freeField10;
    }

    public Boolean getFreeField11() {
        return this.freeField11;
    }

    public IncomingJSONRequest freeField11(Boolean freeField11) {
        this.setFreeField11(freeField11);
        return this;
    }

    public void setFreeField11(Boolean freeField11) {
        this.freeField11 = freeField11;
    }

    public Integer getFreeField12() {
        return this.freeField12;
    }

    public IncomingJSONRequest freeField12(Integer freeField12) {
        this.setFreeField12(freeField12);
        return this;
    }

    public void setFreeField12(Integer freeField12) {
        this.freeField12 = freeField12;
    }

    public String getFreeField13() {
        return this.freeField13;
    }

    public IncomingJSONRequest freeField13(String freeField13) {
        this.setFreeField13(freeField13);
        return this;
    }

    public void setFreeField13(String freeField13) {
        this.freeField13 = freeField13;
    }

    public String getFreeField14() {
        return this.freeField14;
    }

    public IncomingJSONRequest freeField14(String freeField14) {
        this.setFreeField14(freeField14);
        return this;
    }

    public void setFreeField14(String freeField14) {
        this.freeField14 = freeField14;
    }

    public String getFreeField15() {
        return this.freeField15;
    }

    public IncomingJSONRequest freeField15(String freeField15) {
        this.setFreeField15(freeField15);
        return this;
    }

    public void setFreeField15(String freeField15) {
        this.freeField15 = freeField15;
    }

    public String getFreeField16() {
        return this.freeField16;
    }

    public IncomingJSONRequest freeField16(String freeField16) {
        this.setFreeField16(freeField16);
        return this;
    }

    public void setFreeField16(String freeField16) {
        this.freeField16 = freeField16;
    }

    public String getFreeField17() {
        return this.freeField17;
    }

    public IncomingJSONRequest freeField17(String freeField17) {
        this.setFreeField17(freeField17);
        return this;
    }

    public void setFreeField17(String freeField17) {
        this.freeField17 = freeField17;
    }

    public String getFreeField18() {
        return this.freeField18;
    }

    public IncomingJSONRequest freeField18(String freeField18) {
        this.setFreeField18(freeField18);
        return this;
    }

    public void setFreeField18(String freeField18) {
        this.freeField18 = freeField18;
    }

    public String getFreeField19() {
        return this.freeField19;
    }

    public IncomingJSONRequest freeField19(String freeField19) {
        this.setFreeField19(freeField19);
        return this;
    }

    public void setFreeField19(String freeField19) {
        this.freeField19 = freeField19;
    }

    public String getFreeField20() {
        return this.freeField20;
    }

    public IncomingJSONRequest freeField20(String freeField20) {
        this.setFreeField20(freeField20);
        return this;
    }

    public void setFreeField20(String freeField20) {
        this.freeField20 = freeField20;
    }

    public ZonedDateTime getTimestamp() {
        return this.timestamp;
    }

    public IncomingJSONRequest timestamp(ZonedDateTime timestamp) {
        this.setTimestamp(timestamp);
        return this;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public IncomingJSONRequest createdAt(ZonedDateTime createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public IncomingJSONRequest createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public IncomingJSONRequest updatedAt(ZonedDateTime updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public IncomingJSONRequest updatedBy(String updatedBy) {
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
        if (!(o instanceof IncomingJSONRequest)) {
            return false;
        }
        return id != null && id.equals(((IncomingJSONRequest) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "IncomingJSONRequest{" +
            "id=" + getId() +
            ", absaTranRef='" + getAbsaTranRef() + "'" +
            ", dtDTransactionId='" + getDtDTransactionId() + "'" +
            ", amolDTransactionId='" + getAmolDTransactionId() + "'" +
            ", transactionReferenceNumber='" + getTransactionReferenceNumber() + "'" +
            ", token='" + getToken() + "'" +
            ", transactionId='" + getTransactionId() + "'" +
            ", fromEndpoint='" + getFromEndpoint() + "'" +
            ", toEndpoint='" + getToEndpoint() + "'" +
            ", requestJson='" + getRequestJson() + "'" +
            ", requestType='" + getRequestType() + "'" +
            ", requestDescription='" + getRequestDescription() + "'" +
            ", requestStatus='" + getRequestStatus() + "'" +
            ", additionalInfo='" + getAdditionalInfo() + "'" +
            ", freeField1='" + getFreeField1() + "'" +
            ", freeField2='" + getFreeField2() + "'" +
            ", freeField3='" + getFreeField3() + "'" +
            ", freeField4='" + getFreeField4() + "'" +
            ", freeField5='" + getFreeField5() + "'" +
            ", freeField6='" + getFreeField6() + "'" +
            ", freeField6ContentType='" + getFreeField6ContentType() + "'" +
            ", freeField7='" + getFreeField7() + "'" +
            ", freeField7ContentType='" + getFreeField7ContentType() + "'" +
            ", freeField8='" + getFreeField8() + "'" +
            ", freeField8ContentType='" + getFreeField8ContentType() + "'" +
            ", freeField9='" + getFreeField9() + "'" +
            ", freeField10='" + getFreeField10() + "'" +
            ", freeField11='" + getFreeField11() + "'" +
            ", freeField12=" + getFreeField12() +
            ", freeField13='" + getFreeField13() + "'" +
            ", freeField14='" + getFreeField14() + "'" +
            ", freeField15='" + getFreeField15() + "'" +
            ", freeField16='" + getFreeField16() + "'" +
            ", freeField17='" + getFreeField17() + "'" +
            ", freeField18='" + getFreeField18() + "'" +
            ", freeField19='" + getFreeField19() + "'" +
            ", freeField20='" + getFreeField20() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            "}";
    }
}
