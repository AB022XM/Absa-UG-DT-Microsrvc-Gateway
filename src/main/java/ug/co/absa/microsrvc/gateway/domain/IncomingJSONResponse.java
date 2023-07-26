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
 * A IncomingJSONResponse.
 */
@Table("incoming_json_response")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "incomingjsonresponse")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class IncomingJSONResponse implements Serializable {

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

    @Column("response_id")
    private String responseId;

    @Column("transaction_id")
    private String transactionId;

    @Column("response_json")
    private String responseJson;

    @NotNull(message = "must not be null")
    @Column("response_type")
    private String responseType;

    @Column("response_description")
    private String responseDescription;

    @Column("response_status")
    private String responseStatus;

    @Column("additional_info")
    private String additionalInfo;

    @NotNull(message = "must not be null")
    @Column("timestamp")
    private ZonedDateTime timestamp;

    @Column("exception_message")
    private String exceptionMessage;

    @Column("free_field")
    private String freeField;

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

    @Column("free_field_8")
    private String freeField8;

    @Column("free_field_9")
    private String freeField9;

    @Column("free_field_10")
    private String freeField10;

    @Column("free_field_11")
    private String freeField11;

    @Column("free_field_12")
    private String freeField12;

    @Column("free_field_13")
    private String freeField13;

    @Column("free_field_14")
    private String freeField14;

    @Column("free_field_15")
    private byte[] freeField15;

    @Column("free_field_15_content_type")
    private String freeField15ContentType;

    @Column("free_field_16")
    private String freeField16;

    @Column("free_field_17")
    private String freeField17;

    @Column("free_field_18")
    private byte[] freeField18;

    @Column("free_field_18_content_type")
    private String freeField18ContentType;

    @Column("free_field_19")
    private String freeField19;

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

    public IncomingJSONResponse id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getAbsaTranRef() {
        return this.absaTranRef;
    }

    public IncomingJSONResponse absaTranRef(UUID absaTranRef) {
        this.setAbsaTranRef(absaTranRef);
        return this;
    }

    public void setAbsaTranRef(UUID absaTranRef) {
        this.absaTranRef = absaTranRef;
    }

    public String getDtDTransactionId() {
        return this.dtDTransactionId;
    }

    public IncomingJSONResponse dtDTransactionId(String dtDTransactionId) {
        this.setDtDTransactionId(dtDTransactionId);
        return this;
    }

    public void setDtDTransactionId(String dtDTransactionId) {
        this.dtDTransactionId = dtDTransactionId;
    }

    public String getAmolDTransactionId() {
        return this.amolDTransactionId;
    }

    public IncomingJSONResponse amolDTransactionId(String amolDTransactionId) {
        this.setAmolDTransactionId(amolDTransactionId);
        return this;
    }

    public void setAmolDTransactionId(String amolDTransactionId) {
        this.amolDTransactionId = amolDTransactionId;
    }

    public String getTransactionReferenceNumber() {
        return this.transactionReferenceNumber;
    }

    public IncomingJSONResponse transactionReferenceNumber(String transactionReferenceNumber) {
        this.setTransactionReferenceNumber(transactionReferenceNumber);
        return this;
    }

    public void setTransactionReferenceNumber(String transactionReferenceNumber) {
        this.transactionReferenceNumber = transactionReferenceNumber;
    }

    public String getToken() {
        return this.token;
    }

    public IncomingJSONResponse token(String token) {
        this.setToken(token);
        return this;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getResponseId() {
        return this.responseId;
    }

    public IncomingJSONResponse responseId(String responseId) {
        this.setResponseId(responseId);
        return this;
    }

    public void setResponseId(String responseId) {
        this.responseId = responseId;
    }

    public String getTransactionId() {
        return this.transactionId;
    }

    public IncomingJSONResponse transactionId(String transactionId) {
        this.setTransactionId(transactionId);
        return this;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getResponseJson() {
        return this.responseJson;
    }

    public IncomingJSONResponse responseJson(String responseJson) {
        this.setResponseJson(responseJson);
        return this;
    }

    public void setResponseJson(String responseJson) {
        this.responseJson = responseJson;
    }

    public String getResponseType() {
        return this.responseType;
    }

    public IncomingJSONResponse responseType(String responseType) {
        this.setResponseType(responseType);
        return this;
    }

    public void setResponseType(String responseType) {
        this.responseType = responseType;
    }

    public String getResponseDescription() {
        return this.responseDescription;
    }

    public IncomingJSONResponse responseDescription(String responseDescription) {
        this.setResponseDescription(responseDescription);
        return this;
    }

    public void setResponseDescription(String responseDescription) {
        this.responseDescription = responseDescription;
    }

    public String getResponseStatus() {
        return this.responseStatus;
    }

    public IncomingJSONResponse responseStatus(String responseStatus) {
        this.setResponseStatus(responseStatus);
        return this;
    }

    public void setResponseStatus(String responseStatus) {
        this.responseStatus = responseStatus;
    }

    public String getAdditionalInfo() {
        return this.additionalInfo;
    }

    public IncomingJSONResponse additionalInfo(String additionalInfo) {
        this.setAdditionalInfo(additionalInfo);
        return this;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public ZonedDateTime getTimestamp() {
        return this.timestamp;
    }

    public IncomingJSONResponse timestamp(ZonedDateTime timestamp) {
        this.setTimestamp(timestamp);
        return this;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getExceptionMessage() {
        return this.exceptionMessage;
    }

    public IncomingJSONResponse exceptionMessage(String exceptionMessage) {
        this.setExceptionMessage(exceptionMessage);
        return this;
    }

    public void setExceptionMessage(String exceptionMessage) {
        this.exceptionMessage = exceptionMessage;
    }

    public String getFreeField() {
        return this.freeField;
    }

    public IncomingJSONResponse freeField(String freeField) {
        this.setFreeField(freeField);
        return this;
    }

    public void setFreeField(String freeField) {
        this.freeField = freeField;
    }

    public String getFreeField1() {
        return this.freeField1;
    }

    public IncomingJSONResponse freeField1(String freeField1) {
        this.setFreeField1(freeField1);
        return this;
    }

    public void setFreeField1(String freeField1) {
        this.freeField1 = freeField1;
    }

    public String getFreeField2() {
        return this.freeField2;
    }

    public IncomingJSONResponse freeField2(String freeField2) {
        this.setFreeField2(freeField2);
        return this;
    }

    public void setFreeField2(String freeField2) {
        this.freeField2 = freeField2;
    }

    public String getFreeField3() {
        return this.freeField3;
    }

    public IncomingJSONResponse freeField3(String freeField3) {
        this.setFreeField3(freeField3);
        return this;
    }

    public void setFreeField3(String freeField3) {
        this.freeField3 = freeField3;
    }

    public String getFreeField4() {
        return this.freeField4;
    }

    public IncomingJSONResponse freeField4(String freeField4) {
        this.setFreeField4(freeField4);
        return this;
    }

    public void setFreeField4(String freeField4) {
        this.freeField4 = freeField4;
    }

    public String getFreeField5() {
        return this.freeField5;
    }

    public IncomingJSONResponse freeField5(String freeField5) {
        this.setFreeField5(freeField5);
        return this;
    }

    public void setFreeField5(String freeField5) {
        this.freeField5 = freeField5;
    }

    public String getFreeField6() {
        return this.freeField6;
    }

    public IncomingJSONResponse freeField6(String freeField6) {
        this.setFreeField6(freeField6);
        return this;
    }

    public void setFreeField6(String freeField6) {
        this.freeField6 = freeField6;
    }

    public String getFreeField8() {
        return this.freeField8;
    }

    public IncomingJSONResponse freeField8(String freeField8) {
        this.setFreeField8(freeField8);
        return this;
    }

    public void setFreeField8(String freeField8) {
        this.freeField8 = freeField8;
    }

    public String getFreeField9() {
        return this.freeField9;
    }

    public IncomingJSONResponse freeField9(String freeField9) {
        this.setFreeField9(freeField9);
        return this;
    }

    public void setFreeField9(String freeField9) {
        this.freeField9 = freeField9;
    }

    public String getFreeField10() {
        return this.freeField10;
    }

    public IncomingJSONResponse freeField10(String freeField10) {
        this.setFreeField10(freeField10);
        return this;
    }

    public void setFreeField10(String freeField10) {
        this.freeField10 = freeField10;
    }

    public String getFreeField11() {
        return this.freeField11;
    }

    public IncomingJSONResponse freeField11(String freeField11) {
        this.setFreeField11(freeField11);
        return this;
    }

    public void setFreeField11(String freeField11) {
        this.freeField11 = freeField11;
    }

    public String getFreeField12() {
        return this.freeField12;
    }

    public IncomingJSONResponse freeField12(String freeField12) {
        this.setFreeField12(freeField12);
        return this;
    }

    public void setFreeField12(String freeField12) {
        this.freeField12 = freeField12;
    }

    public String getFreeField13() {
        return this.freeField13;
    }

    public IncomingJSONResponse freeField13(String freeField13) {
        this.setFreeField13(freeField13);
        return this;
    }

    public void setFreeField13(String freeField13) {
        this.freeField13 = freeField13;
    }

    public String getFreeField14() {
        return this.freeField14;
    }

    public IncomingJSONResponse freeField14(String freeField14) {
        this.setFreeField14(freeField14);
        return this;
    }

    public void setFreeField14(String freeField14) {
        this.freeField14 = freeField14;
    }

    public byte[] getFreeField15() {
        return this.freeField15;
    }

    public IncomingJSONResponse freeField15(byte[] freeField15) {
        this.setFreeField15(freeField15);
        return this;
    }

    public void setFreeField15(byte[] freeField15) {
        this.freeField15 = freeField15;
    }

    public String getFreeField15ContentType() {
        return this.freeField15ContentType;
    }

    public IncomingJSONResponse freeField15ContentType(String freeField15ContentType) {
        this.freeField15ContentType = freeField15ContentType;
        return this;
    }

    public void setFreeField15ContentType(String freeField15ContentType) {
        this.freeField15ContentType = freeField15ContentType;
    }

    public String getFreeField16() {
        return this.freeField16;
    }

    public IncomingJSONResponse freeField16(String freeField16) {
        this.setFreeField16(freeField16);
        return this;
    }

    public void setFreeField16(String freeField16) {
        this.freeField16 = freeField16;
    }

    public String getFreeField17() {
        return this.freeField17;
    }

    public IncomingJSONResponse freeField17(String freeField17) {
        this.setFreeField17(freeField17);
        return this;
    }

    public void setFreeField17(String freeField17) {
        this.freeField17 = freeField17;
    }

    public byte[] getFreeField18() {
        return this.freeField18;
    }

    public IncomingJSONResponse freeField18(byte[] freeField18) {
        this.setFreeField18(freeField18);
        return this;
    }

    public void setFreeField18(byte[] freeField18) {
        this.freeField18 = freeField18;
    }

    public String getFreeField18ContentType() {
        return this.freeField18ContentType;
    }

    public IncomingJSONResponse freeField18ContentType(String freeField18ContentType) {
        this.freeField18ContentType = freeField18ContentType;
        return this;
    }

    public void setFreeField18ContentType(String freeField18ContentType) {
        this.freeField18ContentType = freeField18ContentType;
    }

    public String getFreeField19() {
        return this.freeField19;
    }

    public IncomingJSONResponse freeField19(String freeField19) {
        this.setFreeField19(freeField19);
        return this;
    }

    public void setFreeField19(String freeField19) {
        this.freeField19 = freeField19;
    }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public IncomingJSONResponse createdAt(ZonedDateTime createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public IncomingJSONResponse createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public IncomingJSONResponse updatedAt(ZonedDateTime updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public IncomingJSONResponse updatedBy(String updatedBy) {
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
        if (!(o instanceof IncomingJSONResponse)) {
            return false;
        }
        return id != null && id.equals(((IncomingJSONResponse) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "IncomingJSONResponse{" +
            "id=" + getId() +
            ", absaTranRef='" + getAbsaTranRef() + "'" +
            ", dtDTransactionId='" + getDtDTransactionId() + "'" +
            ", amolDTransactionId='" + getAmolDTransactionId() + "'" +
            ", transactionReferenceNumber='" + getTransactionReferenceNumber() + "'" +
            ", token='" + getToken() + "'" +
            ", responseId='" + getResponseId() + "'" +
            ", transactionId='" + getTransactionId() + "'" +
            ", responseJson='" + getResponseJson() + "'" +
            ", responseType='" + getResponseType() + "'" +
            ", responseDescription='" + getResponseDescription() + "'" +
            ", responseStatus='" + getResponseStatus() + "'" +
            ", additionalInfo='" + getAdditionalInfo() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            ", exceptionMessage='" + getExceptionMessage() + "'" +
            ", freeField='" + getFreeField() + "'" +
            ", freeField1='" + getFreeField1() + "'" +
            ", freeField2='" + getFreeField2() + "'" +
            ", freeField3='" + getFreeField3() + "'" +
            ", freeField4='" + getFreeField4() + "'" +
            ", freeField5='" + getFreeField5() + "'" +
            ", freeField6='" + getFreeField6() + "'" +
            ", freeField8='" + getFreeField8() + "'" +
            ", freeField9='" + getFreeField9() + "'" +
            ", freeField10='" + getFreeField10() + "'" +
            ", freeField11='" + getFreeField11() + "'" +
            ", freeField12='" + getFreeField12() + "'" +
            ", freeField13='" + getFreeField13() + "'" +
            ", freeField14='" + getFreeField14() + "'" +
            ", freeField15='" + getFreeField15() + "'" +
            ", freeField15ContentType='" + getFreeField15ContentType() + "'" +
            ", freeField16='" + getFreeField16() + "'" +
            ", freeField17='" + getFreeField17() + "'" +
            ", freeField18='" + getFreeField18() + "'" +
            ", freeField18ContentType='" + getFreeField18ContentType() + "'" +
            ", freeField19='" + getFreeField19() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            "}";
    }
}
