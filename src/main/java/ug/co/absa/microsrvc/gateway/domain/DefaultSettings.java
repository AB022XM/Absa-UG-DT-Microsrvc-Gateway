package ug.co.absa.microsrvc.gateway.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import ug.co.absa.microsrvc.gateway.domain.enumeration.RecordStatus;

/**
 * A DefaultSettings.
 */
@Table("default_settings")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "defaultsettings")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DefaultSettings implements Serializable {

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

    @Column("third_party_d_transaction_id")
    private String thirdPartyDTransactionId;

    @Column("default_setting_code")
    private String defaultSettingCode;

    @Column("json_settings")
    private String jsonSettings;

    @Column("app_config")
    private String appConfig;

    @Column("app_name")
    private String appName;

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

    @Column("timestamp")
    private ZonedDateTime timestamp;

    @Column("record_status")
    private RecordStatus recordStatus;

    @Column("created_at")
    private ZonedDateTime createdAt;

    @Column("created_by")
    private String createdBy;

    @Column("updated_at")
    private ZonedDateTime updatedAt;

    @Column("updatedby")
    private String updatedby;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DefaultSettings id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getAbsaTranRef() {
        return this.absaTranRef;
    }

    public DefaultSettings absaTranRef(UUID absaTranRef) {
        this.setAbsaTranRef(absaTranRef);
        return this;
    }

    public void setAbsaTranRef(UUID absaTranRef) {
        this.absaTranRef = absaTranRef;
    }

    public String getDtDTransactionId() {
        return this.dtDTransactionId;
    }

    public DefaultSettings dtDTransactionId(String dtDTransactionId) {
        this.setDtDTransactionId(dtDTransactionId);
        return this;
    }

    public void setDtDTransactionId(String dtDTransactionId) {
        this.dtDTransactionId = dtDTransactionId;
    }

    public String getAmolDTransactionId() {
        return this.amolDTransactionId;
    }

    public DefaultSettings amolDTransactionId(String amolDTransactionId) {
        this.setAmolDTransactionId(amolDTransactionId);
        return this;
    }

    public void setAmolDTransactionId(String amolDTransactionId) {
        this.amolDTransactionId = amolDTransactionId;
    }

    public String getTransactionReferenceNumber() {
        return this.transactionReferenceNumber;
    }

    public DefaultSettings transactionReferenceNumber(String transactionReferenceNumber) {
        this.setTransactionReferenceNumber(transactionReferenceNumber);
        return this;
    }

    public void setTransactionReferenceNumber(String transactionReferenceNumber) {
        this.transactionReferenceNumber = transactionReferenceNumber;
    }

    public String getToken() {
        return this.token;
    }

    public DefaultSettings token(String token) {
        this.setToken(token);
        return this;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getThirdPartyDTransactionId() {
        return this.thirdPartyDTransactionId;
    }

    public DefaultSettings thirdPartyDTransactionId(String thirdPartyDTransactionId) {
        this.setThirdPartyDTransactionId(thirdPartyDTransactionId);
        return this;
    }

    public void setThirdPartyDTransactionId(String thirdPartyDTransactionId) {
        this.thirdPartyDTransactionId = thirdPartyDTransactionId;
    }

    public String getDefaultSettingCode() {
        return this.defaultSettingCode;
    }

    public DefaultSettings defaultSettingCode(String defaultSettingCode) {
        this.setDefaultSettingCode(defaultSettingCode);
        return this;
    }

    public void setDefaultSettingCode(String defaultSettingCode) {
        this.defaultSettingCode = defaultSettingCode;
    }

    public String getJsonSettings() {
        return this.jsonSettings;
    }

    public DefaultSettings jsonSettings(String jsonSettings) {
        this.setJsonSettings(jsonSettings);
        return this;
    }

    public void setJsonSettings(String jsonSettings) {
        this.jsonSettings = jsonSettings;
    }

    public String getAppConfig() {
        return this.appConfig;
    }

    public DefaultSettings appConfig(String appConfig) {
        this.setAppConfig(appConfig);
        return this;
    }

    public void setAppConfig(String appConfig) {
        this.appConfig = appConfig;
    }

    public String getAppName() {
        return this.appName;
    }

    public DefaultSettings appName(String appName) {
        this.setAppName(appName);
        return this;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getFreeField() {
        return this.freeField;
    }

    public DefaultSettings freeField(String freeField) {
        this.setFreeField(freeField);
        return this;
    }

    public void setFreeField(String freeField) {
        this.freeField = freeField;
    }

    public String getFreeField1() {
        return this.freeField1;
    }

    public DefaultSettings freeField1(String freeField1) {
        this.setFreeField1(freeField1);
        return this;
    }

    public void setFreeField1(String freeField1) {
        this.freeField1 = freeField1;
    }

    public String getFreeField2() {
        return this.freeField2;
    }

    public DefaultSettings freeField2(String freeField2) {
        this.setFreeField2(freeField2);
        return this;
    }

    public void setFreeField2(String freeField2) {
        this.freeField2 = freeField2;
    }

    public String getFreeField3() {
        return this.freeField3;
    }

    public DefaultSettings freeField3(String freeField3) {
        this.setFreeField3(freeField3);
        return this;
    }

    public void setFreeField3(String freeField3) {
        this.freeField3 = freeField3;
    }

    public String getFreeField4() {
        return this.freeField4;
    }

    public DefaultSettings freeField4(String freeField4) {
        this.setFreeField4(freeField4);
        return this;
    }

    public void setFreeField4(String freeField4) {
        this.freeField4 = freeField4;
    }

    public String getFreeField5() {
        return this.freeField5;
    }

    public DefaultSettings freeField5(String freeField5) {
        this.setFreeField5(freeField5);
        return this;
    }

    public void setFreeField5(String freeField5) {
        this.freeField5 = freeField5;
    }

    public String getFreeField6() {
        return this.freeField6;
    }

    public DefaultSettings freeField6(String freeField6) {
        this.setFreeField6(freeField6);
        return this;
    }

    public void setFreeField6(String freeField6) {
        this.freeField6 = freeField6;
    }

    public String getFreeField8() {
        return this.freeField8;
    }

    public DefaultSettings freeField8(String freeField8) {
        this.setFreeField8(freeField8);
        return this;
    }

    public void setFreeField8(String freeField8) {
        this.freeField8 = freeField8;
    }

    public String getFreeField9() {
        return this.freeField9;
    }

    public DefaultSettings freeField9(String freeField9) {
        this.setFreeField9(freeField9);
        return this;
    }

    public void setFreeField9(String freeField9) {
        this.freeField9 = freeField9;
    }

    public String getFreeField10() {
        return this.freeField10;
    }

    public DefaultSettings freeField10(String freeField10) {
        this.setFreeField10(freeField10);
        return this;
    }

    public void setFreeField10(String freeField10) {
        this.freeField10 = freeField10;
    }

    public String getFreeField11() {
        return this.freeField11;
    }

    public DefaultSettings freeField11(String freeField11) {
        this.setFreeField11(freeField11);
        return this;
    }

    public void setFreeField11(String freeField11) {
        this.freeField11 = freeField11;
    }

    public String getFreeField12() {
        return this.freeField12;
    }

    public DefaultSettings freeField12(String freeField12) {
        this.setFreeField12(freeField12);
        return this;
    }

    public void setFreeField12(String freeField12) {
        this.freeField12 = freeField12;
    }

    public String getFreeField13() {
        return this.freeField13;
    }

    public DefaultSettings freeField13(String freeField13) {
        this.setFreeField13(freeField13);
        return this;
    }

    public void setFreeField13(String freeField13) {
        this.freeField13 = freeField13;
    }

    public String getFreeField14() {
        return this.freeField14;
    }

    public DefaultSettings freeField14(String freeField14) {
        this.setFreeField14(freeField14);
        return this;
    }

    public void setFreeField14(String freeField14) {
        this.freeField14 = freeField14;
    }

    public byte[] getFreeField15() {
        return this.freeField15;
    }

    public DefaultSettings freeField15(byte[] freeField15) {
        this.setFreeField15(freeField15);
        return this;
    }

    public void setFreeField15(byte[] freeField15) {
        this.freeField15 = freeField15;
    }

    public String getFreeField15ContentType() {
        return this.freeField15ContentType;
    }

    public DefaultSettings freeField15ContentType(String freeField15ContentType) {
        this.freeField15ContentType = freeField15ContentType;
        return this;
    }

    public void setFreeField15ContentType(String freeField15ContentType) {
        this.freeField15ContentType = freeField15ContentType;
    }

    public String getFreeField16() {
        return this.freeField16;
    }

    public DefaultSettings freeField16(String freeField16) {
        this.setFreeField16(freeField16);
        return this;
    }

    public void setFreeField16(String freeField16) {
        this.freeField16 = freeField16;
    }

    public String getFreeField17() {
        return this.freeField17;
    }

    public DefaultSettings freeField17(String freeField17) {
        this.setFreeField17(freeField17);
        return this;
    }

    public void setFreeField17(String freeField17) {
        this.freeField17 = freeField17;
    }

    public byte[] getFreeField18() {
        return this.freeField18;
    }

    public DefaultSettings freeField18(byte[] freeField18) {
        this.setFreeField18(freeField18);
        return this;
    }

    public void setFreeField18(byte[] freeField18) {
        this.freeField18 = freeField18;
    }

    public String getFreeField18ContentType() {
        return this.freeField18ContentType;
    }

    public DefaultSettings freeField18ContentType(String freeField18ContentType) {
        this.freeField18ContentType = freeField18ContentType;
        return this;
    }

    public void setFreeField18ContentType(String freeField18ContentType) {
        this.freeField18ContentType = freeField18ContentType;
    }

    public String getFreeField19() {
        return this.freeField19;
    }

    public DefaultSettings freeField19(String freeField19) {
        this.setFreeField19(freeField19);
        return this;
    }

    public void setFreeField19(String freeField19) {
        this.freeField19 = freeField19;
    }

    public ZonedDateTime getTimestamp() {
        return this.timestamp;
    }

    public DefaultSettings timestamp(ZonedDateTime timestamp) {
        this.setTimestamp(timestamp);
        return this;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public RecordStatus getRecordStatus() {
        return this.recordStatus;
    }

    public DefaultSettings recordStatus(RecordStatus recordStatus) {
        this.setRecordStatus(recordStatus);
        return this;
    }

    public void setRecordStatus(RecordStatus recordStatus) {
        this.recordStatus = recordStatus;
    }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public DefaultSettings createdAt(ZonedDateTime createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public DefaultSettings createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public DefaultSettings updatedAt(ZonedDateTime updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedby() {
        return this.updatedby;
    }

    public DefaultSettings updatedby(String updatedby) {
        this.setUpdatedby(updatedby);
        return this;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DefaultSettings)) {
            return false;
        }
        return id != null && id.equals(((DefaultSettings) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DefaultSettings{" +
            "id=" + getId() +
            ", absaTranRef='" + getAbsaTranRef() + "'" +
            ", dtDTransactionId='" + getDtDTransactionId() + "'" +
            ", amolDTransactionId='" + getAmolDTransactionId() + "'" +
            ", transactionReferenceNumber='" + getTransactionReferenceNumber() + "'" +
            ", token='" + getToken() + "'" +
            ", thirdPartyDTransactionId='" + getThirdPartyDTransactionId() + "'" +
            ", defaultSettingCode='" + getDefaultSettingCode() + "'" +
            ", jsonSettings='" + getJsonSettings() + "'" +
            ", appConfig='" + getAppConfig() + "'" +
            ", appName='" + getAppName() + "'" +
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
            ", timestamp='" + getTimestamp() + "'" +
            ", recordStatus='" + getRecordStatus() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", updatedby='" + getUpdatedby() + "'" +
            "}";
    }
}
