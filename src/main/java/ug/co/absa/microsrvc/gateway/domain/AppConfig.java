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

/**
 * A AppConfig.
 */
@Table("app_config")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "appconfig")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AppConfig implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("absa_tran_ref")
    private UUID absaTranRef;

    @Column("record_id")
    private String recordId;

    @Column("config_id")
    private String configId;

    @NotNull(message = "must not be null")
    @Column("config_name")
    private String configName;

    @Column("config_value")
    private String configValue;

    @NotNull(message = "must not be null")
    @Column("config_type")
    private String configType;

    @Column("config_description")
    private String configDescription;

    @Column("config_status")
    private String configStatus;

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

    @Column("free_field_7")
    private String freeField7;

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
    private byte[] freeField14;

    @Column("free_field_14_content_type")
    private String freeField14ContentType;

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

    @Column("delflg")
    private Boolean delflg;

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

    @Transient
    @JsonIgnoreProperties(value = { "apps" }, allowSetters = true)
    private Apps apps;

    @Column("apps_id")
    private Long appsId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public AppConfig id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getAbsaTranRef() {
        return this.absaTranRef;
    }

    public AppConfig absaTranRef(UUID absaTranRef) {
        this.setAbsaTranRef(absaTranRef);
        return this;
    }

    public void setAbsaTranRef(UUID absaTranRef) {
        this.absaTranRef = absaTranRef;
    }

    public String getRecordId() {
        return this.recordId;
    }

    public AppConfig recordId(String recordId) {
        this.setRecordId(recordId);
        return this;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getConfigId() {
        return this.configId;
    }

    public AppConfig configId(String configId) {
        this.setConfigId(configId);
        return this;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public String getConfigName() {
        return this.configName;
    }

    public AppConfig configName(String configName) {
        this.setConfigName(configName);
        return this;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigValue() {
        return this.configValue;
    }

    public AppConfig configValue(String configValue) {
        this.setConfigValue(configValue);
        return this;
    }

    public void setConfigValue(String configValue) {
        this.configValue = configValue;
    }

    public String getConfigType() {
        return this.configType;
    }

    public AppConfig configType(String configType) {
        this.setConfigType(configType);
        return this;
    }

    public void setConfigType(String configType) {
        this.configType = configType;
    }

    public String getConfigDescription() {
        return this.configDescription;
    }

    public AppConfig configDescription(String configDescription) {
        this.setConfigDescription(configDescription);
        return this;
    }

    public void setConfigDescription(String configDescription) {
        this.configDescription = configDescription;
    }

    public String getConfigStatus() {
        return this.configStatus;
    }

    public AppConfig configStatus(String configStatus) {
        this.setConfigStatus(configStatus);
        return this;
    }

    public void setConfigStatus(String configStatus) {
        this.configStatus = configStatus;
    }

    public String getFreeField1() {
        return this.freeField1;
    }

    public AppConfig freeField1(String freeField1) {
        this.setFreeField1(freeField1);
        return this;
    }

    public void setFreeField1(String freeField1) {
        this.freeField1 = freeField1;
    }

    public String getFreeField2() {
        return this.freeField2;
    }

    public AppConfig freeField2(String freeField2) {
        this.setFreeField2(freeField2);
        return this;
    }

    public void setFreeField2(String freeField2) {
        this.freeField2 = freeField2;
    }

    public String getFreeField3() {
        return this.freeField3;
    }

    public AppConfig freeField3(String freeField3) {
        this.setFreeField3(freeField3);
        return this;
    }

    public void setFreeField3(String freeField3) {
        this.freeField3 = freeField3;
    }

    public String getFreeField4() {
        return this.freeField4;
    }

    public AppConfig freeField4(String freeField4) {
        this.setFreeField4(freeField4);
        return this;
    }

    public void setFreeField4(String freeField4) {
        this.freeField4 = freeField4;
    }

    public String getFreeField5() {
        return this.freeField5;
    }

    public AppConfig freeField5(String freeField5) {
        this.setFreeField5(freeField5);
        return this;
    }

    public void setFreeField5(String freeField5) {
        this.freeField5 = freeField5;
    }

    public String getFreeField6() {
        return this.freeField6;
    }

    public AppConfig freeField6(String freeField6) {
        this.setFreeField6(freeField6);
        return this;
    }

    public void setFreeField6(String freeField6) {
        this.freeField6 = freeField6;
    }

    public String getFreeField7() {
        return this.freeField7;
    }

    public AppConfig freeField7(String freeField7) {
        this.setFreeField7(freeField7);
        return this;
    }

    public void setFreeField7(String freeField7) {
        this.freeField7 = freeField7;
    }

    public String getFreeField8() {
        return this.freeField8;
    }

    public AppConfig freeField8(String freeField8) {
        this.setFreeField8(freeField8);
        return this;
    }

    public void setFreeField8(String freeField8) {
        this.freeField8 = freeField8;
    }

    public String getFreeField9() {
        return this.freeField9;
    }

    public AppConfig freeField9(String freeField9) {
        this.setFreeField9(freeField9);
        return this;
    }

    public void setFreeField9(String freeField9) {
        this.freeField9 = freeField9;
    }

    public String getFreeField10() {
        return this.freeField10;
    }

    public AppConfig freeField10(String freeField10) {
        this.setFreeField10(freeField10);
        return this;
    }

    public void setFreeField10(String freeField10) {
        this.freeField10 = freeField10;
    }

    public String getFreeField11() {
        return this.freeField11;
    }

    public AppConfig freeField11(String freeField11) {
        this.setFreeField11(freeField11);
        return this;
    }

    public void setFreeField11(String freeField11) {
        this.freeField11 = freeField11;
    }

    public String getFreeField12() {
        return this.freeField12;
    }

    public AppConfig freeField12(String freeField12) {
        this.setFreeField12(freeField12);
        return this;
    }

    public void setFreeField12(String freeField12) {
        this.freeField12 = freeField12;
    }

    public String getFreeField13() {
        return this.freeField13;
    }

    public AppConfig freeField13(String freeField13) {
        this.setFreeField13(freeField13);
        return this;
    }

    public void setFreeField13(String freeField13) {
        this.freeField13 = freeField13;
    }

    public byte[] getFreeField14() {
        return this.freeField14;
    }

    public AppConfig freeField14(byte[] freeField14) {
        this.setFreeField14(freeField14);
        return this;
    }

    public void setFreeField14(byte[] freeField14) {
        this.freeField14 = freeField14;
    }

    public String getFreeField14ContentType() {
        return this.freeField14ContentType;
    }

    public AppConfig freeField14ContentType(String freeField14ContentType) {
        this.freeField14ContentType = freeField14ContentType;
        return this;
    }

    public void setFreeField14ContentType(String freeField14ContentType) {
        this.freeField14ContentType = freeField14ContentType;
    }

    public byte[] getFreeField15() {
        return this.freeField15;
    }

    public AppConfig freeField15(byte[] freeField15) {
        this.setFreeField15(freeField15);
        return this;
    }

    public void setFreeField15(byte[] freeField15) {
        this.freeField15 = freeField15;
    }

    public String getFreeField15ContentType() {
        return this.freeField15ContentType;
    }

    public AppConfig freeField15ContentType(String freeField15ContentType) {
        this.freeField15ContentType = freeField15ContentType;
        return this;
    }

    public void setFreeField15ContentType(String freeField15ContentType) {
        this.freeField15ContentType = freeField15ContentType;
    }

    public String getFreeField16() {
        return this.freeField16;
    }

    public AppConfig freeField16(String freeField16) {
        this.setFreeField16(freeField16);
        return this;
    }

    public void setFreeField16(String freeField16) {
        this.freeField16 = freeField16;
    }

    public String getFreeField17() {
        return this.freeField17;
    }

    public AppConfig freeField17(String freeField17) {
        this.setFreeField17(freeField17);
        return this;
    }

    public void setFreeField17(String freeField17) {
        this.freeField17 = freeField17;
    }

    public byte[] getFreeField18() {
        return this.freeField18;
    }

    public AppConfig freeField18(byte[] freeField18) {
        this.setFreeField18(freeField18);
        return this;
    }

    public void setFreeField18(byte[] freeField18) {
        this.freeField18 = freeField18;
    }

    public String getFreeField18ContentType() {
        return this.freeField18ContentType;
    }

    public AppConfig freeField18ContentType(String freeField18ContentType) {
        this.freeField18ContentType = freeField18ContentType;
        return this;
    }

    public void setFreeField18ContentType(String freeField18ContentType) {
        this.freeField18ContentType = freeField18ContentType;
    }

    public String getFreeField19() {
        return this.freeField19;
    }

    public AppConfig freeField19(String freeField19) {
        this.setFreeField19(freeField19);
        return this;
    }

    public void setFreeField19(String freeField19) {
        this.freeField19 = freeField19;
    }

    public Boolean getDelflg() {
        return this.delflg;
    }

    public AppConfig delflg(Boolean delflg) {
        this.setDelflg(delflg);
        return this;
    }

    public void setDelflg(Boolean delflg) {
        this.delflg = delflg;
    }

    public ZonedDateTime getTimestamp() {
        return this.timestamp;
    }

    public AppConfig timestamp(ZonedDateTime timestamp) {
        this.setTimestamp(timestamp);
        return this;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public AppConfig createdAt(ZonedDateTime createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public AppConfig createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public AppConfig updatedAt(ZonedDateTime updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public AppConfig updatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Apps getApps() {
        return this.apps;
    }

    public void setApps(Apps apps) {
        this.apps = apps;
        this.appsId = apps != null ? apps.getId() : null;
    }

    public AppConfig apps(Apps apps) {
        this.setApps(apps);
        return this;
    }

    public Long getAppsId() {
        return this.appsId;
    }

    public void setAppsId(Long apps) {
        this.appsId = apps;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AppConfig)) {
            return false;
        }
        return id != null && id.equals(((AppConfig) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AppConfig{" +
            "id=" + getId() +
            ", absaTranRef='" + getAbsaTranRef() + "'" +
            ", recordId='" + getRecordId() + "'" +
            ", configId='" + getConfigId() + "'" +
            ", configName='" + getConfigName() + "'" +
            ", configValue='" + getConfigValue() + "'" +
            ", configType='" + getConfigType() + "'" +
            ", configDescription='" + getConfigDescription() + "'" +
            ", configStatus='" + getConfigStatus() + "'" +
            ", freeField1='" + getFreeField1() + "'" +
            ", freeField2='" + getFreeField2() + "'" +
            ", freeField3='" + getFreeField3() + "'" +
            ", freeField4='" + getFreeField4() + "'" +
            ", freeField5='" + getFreeField5() + "'" +
            ", freeField6='" + getFreeField6() + "'" +
            ", freeField7='" + getFreeField7() + "'" +
            ", freeField8='" + getFreeField8() + "'" +
            ", freeField9='" + getFreeField9() + "'" +
            ", freeField10='" + getFreeField10() + "'" +
            ", freeField11='" + getFreeField11() + "'" +
            ", freeField12='" + getFreeField12() + "'" +
            ", freeField13='" + getFreeField13() + "'" +
            ", freeField14='" + getFreeField14() + "'" +
            ", freeField14ContentType='" + getFreeField14ContentType() + "'" +
            ", freeField15='" + getFreeField15() + "'" +
            ", freeField15ContentType='" + getFreeField15ContentType() + "'" +
            ", freeField16='" + getFreeField16() + "'" +
            ", freeField17='" + getFreeField17() + "'" +
            ", freeField18='" + getFreeField18() + "'" +
            ", freeField18ContentType='" + getFreeField18ContentType() + "'" +
            ", freeField19='" + getFreeField19() + "'" +
            ", delflg='" + getDelflg() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            "}";
    }
}
