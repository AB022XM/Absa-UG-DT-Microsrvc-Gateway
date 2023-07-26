package ug.co.absa.microsrvc.gateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A Apps.
 */
@Table("apps")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "apps")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Apps implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("record_id")
    private String recordId;

    @Column("config_id")
    private String configId;

    @NotNull(message = "must not be null")
    @Column("app_id")
    private String appId;

    @NotNull(message = "must not be null")
    @Column("app_current_version")
    private String appCurrentVersion;

    @NotNull(message = "must not be null")
    @Column("app_name")
    private String appName;

    @Column("app_description")
    private String appDescription;

    @Column("app_status")
    private String appStatus;

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

    @Column("updatedby")
    private String updatedby;

    @Transient
    @JsonIgnoreProperties(value = { "apps" }, allowSetters = true)
    private Set<AppConfig> apps = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Apps id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecordId() {
        return this.recordId;
    }

    public Apps recordId(String recordId) {
        this.setRecordId(recordId);
        return this;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getConfigId() {
        return this.configId;
    }

    public Apps configId(String configId) {
        this.setConfigId(configId);
        return this;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public String getAppId() {
        return this.appId;
    }

    public Apps appId(String appId) {
        this.setAppId(appId);
        return this;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getAppCurrentVersion() {
        return this.appCurrentVersion;
    }

    public Apps appCurrentVersion(String appCurrentVersion) {
        this.setAppCurrentVersion(appCurrentVersion);
        return this;
    }

    public void setAppCurrentVersion(String appCurrentVersion) {
        this.appCurrentVersion = appCurrentVersion;
    }

    public String getAppName() {
        return this.appName;
    }

    public Apps appName(String appName) {
        this.setAppName(appName);
        return this;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppDescription() {
        return this.appDescription;
    }

    public Apps appDescription(String appDescription) {
        this.setAppDescription(appDescription);
        return this;
    }

    public void setAppDescription(String appDescription) {
        this.appDescription = appDescription;
    }

    public String getAppStatus() {
        return this.appStatus;
    }

    public Apps appStatus(String appStatus) {
        this.setAppStatus(appStatus);
        return this;
    }

    public void setAppStatus(String appStatus) {
        this.appStatus = appStatus;
    }

    public String getFreeField1() {
        return this.freeField1;
    }

    public Apps freeField1(String freeField1) {
        this.setFreeField1(freeField1);
        return this;
    }

    public void setFreeField1(String freeField1) {
        this.freeField1 = freeField1;
    }

    public String getFreeField2() {
        return this.freeField2;
    }

    public Apps freeField2(String freeField2) {
        this.setFreeField2(freeField2);
        return this;
    }

    public void setFreeField2(String freeField2) {
        this.freeField2 = freeField2;
    }

    public String getFreeField3() {
        return this.freeField3;
    }

    public Apps freeField3(String freeField3) {
        this.setFreeField3(freeField3);
        return this;
    }

    public void setFreeField3(String freeField3) {
        this.freeField3 = freeField3;
    }

    public String getFreeField4() {
        return this.freeField4;
    }

    public Apps freeField4(String freeField4) {
        this.setFreeField4(freeField4);
        return this;
    }

    public void setFreeField4(String freeField4) {
        this.freeField4 = freeField4;
    }

    public String getFreeField5() {
        return this.freeField5;
    }

    public Apps freeField5(String freeField5) {
        this.setFreeField5(freeField5);
        return this;
    }

    public void setFreeField5(String freeField5) {
        this.freeField5 = freeField5;
    }

    public String getFreeField6() {
        return this.freeField6;
    }

    public Apps freeField6(String freeField6) {
        this.setFreeField6(freeField6);
        return this;
    }

    public void setFreeField6(String freeField6) {
        this.freeField6 = freeField6;
    }

    public String getFreeField7() {
        return this.freeField7;
    }

    public Apps freeField7(String freeField7) {
        this.setFreeField7(freeField7);
        return this;
    }

    public void setFreeField7(String freeField7) {
        this.freeField7 = freeField7;
    }

    public String getFreeField8() {
        return this.freeField8;
    }

    public Apps freeField8(String freeField8) {
        this.setFreeField8(freeField8);
        return this;
    }

    public void setFreeField8(String freeField8) {
        this.freeField8 = freeField8;
    }

    public String getFreeField9() {
        return this.freeField9;
    }

    public Apps freeField9(String freeField9) {
        this.setFreeField9(freeField9);
        return this;
    }

    public void setFreeField9(String freeField9) {
        this.freeField9 = freeField9;
    }

    public String getFreeField10() {
        return this.freeField10;
    }

    public Apps freeField10(String freeField10) {
        this.setFreeField10(freeField10);
        return this;
    }

    public void setFreeField10(String freeField10) {
        this.freeField10 = freeField10;
    }

    public String getFreeField11() {
        return this.freeField11;
    }

    public Apps freeField11(String freeField11) {
        this.setFreeField11(freeField11);
        return this;
    }

    public void setFreeField11(String freeField11) {
        this.freeField11 = freeField11;
    }

    public String getFreeField12() {
        return this.freeField12;
    }

    public Apps freeField12(String freeField12) {
        this.setFreeField12(freeField12);
        return this;
    }

    public void setFreeField12(String freeField12) {
        this.freeField12 = freeField12;
    }

    public String getFreeField13() {
        return this.freeField13;
    }

    public Apps freeField13(String freeField13) {
        this.setFreeField13(freeField13);
        return this;
    }

    public void setFreeField13(String freeField13) {
        this.freeField13 = freeField13;
    }

    public Boolean getDelflg() {
        return this.delflg;
    }

    public Apps delflg(Boolean delflg) {
        this.setDelflg(delflg);
        return this;
    }

    public void setDelflg(Boolean delflg) {
        this.delflg = delflg;
    }

    public ZonedDateTime getTimestamp() {
        return this.timestamp;
    }

    public Apps timestamp(ZonedDateTime timestamp) {
        this.setTimestamp(timestamp);
        return this;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public Apps createdAt(ZonedDateTime createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Apps createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public Apps updatedAt(ZonedDateTime updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedby() {
        return this.updatedby;
    }

    public Apps updatedby(String updatedby) {
        this.setUpdatedby(updatedby);
        return this;
    }

    public void setUpdatedby(String updatedby) {
        this.updatedby = updatedby;
    }

    public Set<AppConfig> getApps() {
        return this.apps;
    }

    public void setApps(Set<AppConfig> appConfigs) {
        if (this.apps != null) {
            this.apps.forEach(i -> i.setApps(null));
        }
        if (appConfigs != null) {
            appConfigs.forEach(i -> i.setApps(this));
        }
        this.apps = appConfigs;
    }

    public Apps apps(Set<AppConfig> appConfigs) {
        this.setApps(appConfigs);
        return this;
    }

    public Apps addApp(AppConfig appConfig) {
        this.apps.add(appConfig);
        appConfig.setApps(this);
        return this;
    }

    public Apps removeApp(AppConfig appConfig) {
        this.apps.remove(appConfig);
        appConfig.setApps(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Apps)) {
            return false;
        }
        return id != null && id.equals(((Apps) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Apps{" +
            "id=" + getId() +
            ", recordId='" + getRecordId() + "'" +
            ", configId='" + getConfigId() + "'" +
            ", appId='" + getAppId() + "'" +
            ", appCurrentVersion='" + getAppCurrentVersion() + "'" +
            ", appName='" + getAppName() + "'" +
            ", appDescription='" + getAppDescription() + "'" +
            ", appStatus='" + getAppStatus() + "'" +
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
            ", delflg='" + getDelflg() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", updatedby='" + getUpdatedby() + "'" +
            "}";
    }
}
