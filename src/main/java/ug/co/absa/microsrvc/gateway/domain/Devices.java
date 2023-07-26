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
 * A Devices.
 */
@Table("devices")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "devices")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Devices implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("absa_tran_ref")
    private UUID absaTranRef;

    @Column("record_id")
    private String recordId;

    @Column("device_id")
    private String deviceId;

    @NotNull(message = "must not be null")
    @Column("device_name")
    private String deviceName;

    @NotNull(message = "must not be null")
    @Column("device_type")
    private String deviceType;

    @Column("device_description")
    private String deviceDescription;

    @NotNull(message = "must not be null")
    @Column("timestamp")
    private ZonedDateTime timestamp;

    @Column("delflg")
    private Boolean delflg;

    @Column("record_status")
    private RecordStatus recordStatus;

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

    public Devices id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getAbsaTranRef() {
        return this.absaTranRef;
    }

    public Devices absaTranRef(UUID absaTranRef) {
        this.setAbsaTranRef(absaTranRef);
        return this;
    }

    public void setAbsaTranRef(UUID absaTranRef) {
        this.absaTranRef = absaTranRef;
    }

    public String getRecordId() {
        return this.recordId;
    }

    public Devices recordId(String recordId) {
        this.setRecordId(recordId);
        return this;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public Devices deviceId(String deviceId) {
        this.setDeviceId(deviceId);
        return this;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public String getDeviceName() {
        return this.deviceName;
    }

    public Devices deviceName(String deviceName) {
        this.setDeviceName(deviceName);
        return this;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public Devices deviceType(String deviceType) {
        this.setDeviceType(deviceType);
        return this;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getDeviceDescription() {
        return this.deviceDescription;
    }

    public Devices deviceDescription(String deviceDescription) {
        this.setDeviceDescription(deviceDescription);
        return this;
    }

    public void setDeviceDescription(String deviceDescription) {
        this.deviceDescription = deviceDescription;
    }

    public ZonedDateTime getTimestamp() {
        return this.timestamp;
    }

    public Devices timestamp(ZonedDateTime timestamp) {
        this.setTimestamp(timestamp);
        return this;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Boolean getDelflg() {
        return this.delflg;
    }

    public Devices delflg(Boolean delflg) {
        this.setDelflg(delflg);
        return this;
    }

    public void setDelflg(Boolean delflg) {
        this.delflg = delflg;
    }

    public RecordStatus getRecordStatus() {
        return this.recordStatus;
    }

    public Devices recordStatus(RecordStatus recordStatus) {
        this.setRecordStatus(recordStatus);
        return this;
    }

    public void setRecordStatus(RecordStatus recordStatus) {
        this.recordStatus = recordStatus;
    }

    public String getFreeField() {
        return this.freeField;
    }

    public Devices freeField(String freeField) {
        this.setFreeField(freeField);
        return this;
    }

    public void setFreeField(String freeField) {
        this.freeField = freeField;
    }

    public String getFreeField1() {
        return this.freeField1;
    }

    public Devices freeField1(String freeField1) {
        this.setFreeField1(freeField1);
        return this;
    }

    public void setFreeField1(String freeField1) {
        this.freeField1 = freeField1;
    }

    public String getFreeField2() {
        return this.freeField2;
    }

    public Devices freeField2(String freeField2) {
        this.setFreeField2(freeField2);
        return this;
    }

    public void setFreeField2(String freeField2) {
        this.freeField2 = freeField2;
    }

    public String getFreeField3() {
        return this.freeField3;
    }

    public Devices freeField3(String freeField3) {
        this.setFreeField3(freeField3);
        return this;
    }

    public void setFreeField3(String freeField3) {
        this.freeField3 = freeField3;
    }

    public String getFreeField4() {
        return this.freeField4;
    }

    public Devices freeField4(String freeField4) {
        this.setFreeField4(freeField4);
        return this;
    }

    public void setFreeField4(String freeField4) {
        this.freeField4 = freeField4;
    }

    public String getFreeField5() {
        return this.freeField5;
    }

    public Devices freeField5(String freeField5) {
        this.setFreeField5(freeField5);
        return this;
    }

    public void setFreeField5(String freeField5) {
        this.freeField5 = freeField5;
    }

    public String getFreeField6() {
        return this.freeField6;
    }

    public Devices freeField6(String freeField6) {
        this.setFreeField6(freeField6);
        return this;
    }

    public void setFreeField6(String freeField6) {
        this.freeField6 = freeField6;
    }

    public String getFreeField7() {
        return this.freeField7;
    }

    public Devices freeField7(String freeField7) {
        this.setFreeField7(freeField7);
        return this;
    }

    public void setFreeField7(String freeField7) {
        this.freeField7 = freeField7;
    }

    public String getFreeField8() {
        return this.freeField8;
    }

    public Devices freeField8(String freeField8) {
        this.setFreeField8(freeField8);
        return this;
    }

    public void setFreeField8(String freeField8) {
        this.freeField8 = freeField8;
    }

    public String getFreeField9() {
        return this.freeField9;
    }

    public Devices freeField9(String freeField9) {
        this.setFreeField9(freeField9);
        return this;
    }

    public void setFreeField9(String freeField9) {
        this.freeField9 = freeField9;
    }

    public String getFreeField10() {
        return this.freeField10;
    }

    public Devices freeField10(String freeField10) {
        this.setFreeField10(freeField10);
        return this;
    }

    public void setFreeField10(String freeField10) {
        this.freeField10 = freeField10;
    }

    public String getFreeField11() {
        return this.freeField11;
    }

    public Devices freeField11(String freeField11) {
        this.setFreeField11(freeField11);
        return this;
    }

    public void setFreeField11(String freeField11) {
        this.freeField11 = freeField11;
    }

    public String getFreeField12() {
        return this.freeField12;
    }

    public Devices freeField12(String freeField12) {
        this.setFreeField12(freeField12);
        return this;
    }

    public void setFreeField12(String freeField12) {
        this.freeField12 = freeField12;
    }

    public String getFreeField13() {
        return this.freeField13;
    }

    public Devices freeField13(String freeField13) {
        this.setFreeField13(freeField13);
        return this;
    }

    public void setFreeField13(String freeField13) {
        this.freeField13 = freeField13;
    }

    public String getFreeField14() {
        return this.freeField14;
    }

    public Devices freeField14(String freeField14) {
        this.setFreeField14(freeField14);
        return this;
    }

    public void setFreeField14(String freeField14) {
        this.freeField14 = freeField14;
    }

    public byte[] getFreeField15() {
        return this.freeField15;
    }

    public Devices freeField15(byte[] freeField15) {
        this.setFreeField15(freeField15);
        return this;
    }

    public void setFreeField15(byte[] freeField15) {
        this.freeField15 = freeField15;
    }

    public String getFreeField15ContentType() {
        return this.freeField15ContentType;
    }

    public Devices freeField15ContentType(String freeField15ContentType) {
        this.freeField15ContentType = freeField15ContentType;
        return this;
    }

    public void setFreeField15ContentType(String freeField15ContentType) {
        this.freeField15ContentType = freeField15ContentType;
    }

    public String getFreeField16() {
        return this.freeField16;
    }

    public Devices freeField16(String freeField16) {
        this.setFreeField16(freeField16);
        return this;
    }

    public void setFreeField16(String freeField16) {
        this.freeField16 = freeField16;
    }

    public String getFreeField17() {
        return this.freeField17;
    }

    public Devices freeField17(String freeField17) {
        this.setFreeField17(freeField17);
        return this;
    }

    public void setFreeField17(String freeField17) {
        this.freeField17 = freeField17;
    }

    public byte[] getFreeField18() {
        return this.freeField18;
    }

    public Devices freeField18(byte[] freeField18) {
        this.setFreeField18(freeField18);
        return this;
    }

    public void setFreeField18(byte[] freeField18) {
        this.freeField18 = freeField18;
    }

    public String getFreeField18ContentType() {
        return this.freeField18ContentType;
    }

    public Devices freeField18ContentType(String freeField18ContentType) {
        this.freeField18ContentType = freeField18ContentType;
        return this;
    }

    public void setFreeField18ContentType(String freeField18ContentType) {
        this.freeField18ContentType = freeField18ContentType;
    }

    public String getFreeField19() {
        return this.freeField19;
    }

    public Devices freeField19(String freeField19) {
        this.setFreeField19(freeField19);
        return this;
    }

    public void setFreeField19(String freeField19) {
        this.freeField19 = freeField19;
    }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public Devices createdAt(ZonedDateTime createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Devices createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public Devices updatedAt(ZonedDateTime updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public Devices updatedBy(String updatedBy) {
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
        if (!(o instanceof Devices)) {
            return false;
        }
        return id != null && id.equals(((Devices) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Devices{" +
            "id=" + getId() +
            ", absaTranRef='" + getAbsaTranRef() + "'" +
            ", recordId='" + getRecordId() + "'" +
            ", deviceId='" + getDeviceId() + "'" +
            ", deviceName='" + getDeviceName() + "'" +
            ", deviceType='" + getDeviceType() + "'" +
            ", deviceDescription='" + getDeviceDescription() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            ", delflg='" + getDelflg() + "'" +
            ", recordStatus='" + getRecordStatus() + "'" +
            ", freeField='" + getFreeField() + "'" +
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
