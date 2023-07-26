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
 * A GenericConfigs.
 */
@Table("generic_configs")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "genericconfigs")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class GenericConfigs implements Serializable {

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

    @Column("config_name")
    private String configName;

    @Column("configs_details")
    private String configsDetails;

    @Column("additional_configs")
    private String additionalConfigs;

    @Column("free_field")
    private Boolean freeField;

    @Column("free_field_1")
    private Boolean freeField1;

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

    @Column("free_field_20")
    private String freeField20;

    @Column("free_field_21")
    private String freeField21;

    @Column("free_field_22")
    private String freeField22;

    @Column("free_field_23")
    private String freeField23;

    @Column("free_field_24")
    private String freeField24;

    @Column("free_field_25")
    private byte[] freeField25;

    @Column("free_field_25_content_type")
    private String freeField25ContentType;

    @Column("free_field_26")
    private String freeField26;

    @Column("free_field_27")
    private String freeField27;

    @Column("free_field_28")
    private byte[] freeField28;

    @Column("free_field_28_content_type")
    private String freeField28ContentType;

    @Column("free_field_29")
    private String freeField29;

    @Column("free_field_30")
    private String freeField30;

    @Column("free_field_31")
    private String freeField31;

    @Column("free_field_32")
    private String freeField32;

    @Column("free_field_33")
    private String freeField33;

    @Column("free_field_34")
    private String freeField34;

    @NotNull(message = "must not be null")
    @Column("timestamp")
    private ZonedDateTime timestamp;

    @Column("record_status")
    private RecordStatus recordStatus;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public GenericConfigs id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getAbsaTranRef() {
        return this.absaTranRef;
    }

    public GenericConfigs absaTranRef(UUID absaTranRef) {
        this.setAbsaTranRef(absaTranRef);
        return this;
    }

    public void setAbsaTranRef(UUID absaTranRef) {
        this.absaTranRef = absaTranRef;
    }

    public String getRecordId() {
        return this.recordId;
    }

    public GenericConfigs recordId(String recordId) {
        this.setRecordId(recordId);
        return this;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getConfigId() {
        return this.configId;
    }

    public GenericConfigs configId(String configId) {
        this.setConfigId(configId);
        return this;
    }

    public void setConfigId(String configId) {
        this.configId = configId;
    }

    public String getConfigName() {
        return this.configName;
    }

    public GenericConfigs configName(String configName) {
        this.setConfigName(configName);
        return this;
    }

    public void setConfigName(String configName) {
        this.configName = configName;
    }

    public String getConfigsDetails() {
        return this.configsDetails;
    }

    public GenericConfigs configsDetails(String configsDetails) {
        this.setConfigsDetails(configsDetails);
        return this;
    }

    public void setConfigsDetails(String configsDetails) {
        this.configsDetails = configsDetails;
    }

    public String getAdditionalConfigs() {
        return this.additionalConfigs;
    }

    public GenericConfigs additionalConfigs(String additionalConfigs) {
        this.setAdditionalConfigs(additionalConfigs);
        return this;
    }

    public void setAdditionalConfigs(String additionalConfigs) {
        this.additionalConfigs = additionalConfigs;
    }

    public Boolean getFreeField() {
        return this.freeField;
    }

    public GenericConfigs freeField(Boolean freeField) {
        this.setFreeField(freeField);
        return this;
    }

    public void setFreeField(Boolean freeField) {
        this.freeField = freeField;
    }

    public Boolean getFreeField1() {
        return this.freeField1;
    }

    public GenericConfigs freeField1(Boolean freeField1) {
        this.setFreeField1(freeField1);
        return this;
    }

    public void setFreeField1(Boolean freeField1) {
        this.freeField1 = freeField1;
    }

    public String getFreeField2() {
        return this.freeField2;
    }

    public GenericConfigs freeField2(String freeField2) {
        this.setFreeField2(freeField2);
        return this;
    }

    public void setFreeField2(String freeField2) {
        this.freeField2 = freeField2;
    }

    public String getFreeField3() {
        return this.freeField3;
    }

    public GenericConfigs freeField3(String freeField3) {
        this.setFreeField3(freeField3);
        return this;
    }

    public void setFreeField3(String freeField3) {
        this.freeField3 = freeField3;
    }

    public String getFreeField4() {
        return this.freeField4;
    }

    public GenericConfigs freeField4(String freeField4) {
        this.setFreeField4(freeField4);
        return this;
    }

    public void setFreeField4(String freeField4) {
        this.freeField4 = freeField4;
    }

    public String getFreeField5() {
        return this.freeField5;
    }

    public GenericConfigs freeField5(String freeField5) {
        this.setFreeField5(freeField5);
        return this;
    }

    public void setFreeField5(String freeField5) {
        this.freeField5 = freeField5;
    }

    public String getFreeField6() {
        return this.freeField6;
    }

    public GenericConfigs freeField6(String freeField6) {
        this.setFreeField6(freeField6);
        return this;
    }

    public void setFreeField6(String freeField6) {
        this.freeField6 = freeField6;
    }

    public String getFreeField8() {
        return this.freeField8;
    }

    public GenericConfigs freeField8(String freeField8) {
        this.setFreeField8(freeField8);
        return this;
    }

    public void setFreeField8(String freeField8) {
        this.freeField8 = freeField8;
    }

    public String getFreeField9() {
        return this.freeField9;
    }

    public GenericConfigs freeField9(String freeField9) {
        this.setFreeField9(freeField9);
        return this;
    }

    public void setFreeField9(String freeField9) {
        this.freeField9 = freeField9;
    }

    public String getFreeField10() {
        return this.freeField10;
    }

    public GenericConfigs freeField10(String freeField10) {
        this.setFreeField10(freeField10);
        return this;
    }

    public void setFreeField10(String freeField10) {
        this.freeField10 = freeField10;
    }

    public String getFreeField11() {
        return this.freeField11;
    }

    public GenericConfigs freeField11(String freeField11) {
        this.setFreeField11(freeField11);
        return this;
    }

    public void setFreeField11(String freeField11) {
        this.freeField11 = freeField11;
    }

    public String getFreeField12() {
        return this.freeField12;
    }

    public GenericConfigs freeField12(String freeField12) {
        this.setFreeField12(freeField12);
        return this;
    }

    public void setFreeField12(String freeField12) {
        this.freeField12 = freeField12;
    }

    public String getFreeField13() {
        return this.freeField13;
    }

    public GenericConfigs freeField13(String freeField13) {
        this.setFreeField13(freeField13);
        return this;
    }

    public void setFreeField13(String freeField13) {
        this.freeField13 = freeField13;
    }

    public String getFreeField14() {
        return this.freeField14;
    }

    public GenericConfigs freeField14(String freeField14) {
        this.setFreeField14(freeField14);
        return this;
    }

    public void setFreeField14(String freeField14) {
        this.freeField14 = freeField14;
    }

    public byte[] getFreeField15() {
        return this.freeField15;
    }

    public GenericConfigs freeField15(byte[] freeField15) {
        this.setFreeField15(freeField15);
        return this;
    }

    public void setFreeField15(byte[] freeField15) {
        this.freeField15 = freeField15;
    }

    public String getFreeField15ContentType() {
        return this.freeField15ContentType;
    }

    public GenericConfigs freeField15ContentType(String freeField15ContentType) {
        this.freeField15ContentType = freeField15ContentType;
        return this;
    }

    public void setFreeField15ContentType(String freeField15ContentType) {
        this.freeField15ContentType = freeField15ContentType;
    }

    public String getFreeField16() {
        return this.freeField16;
    }

    public GenericConfigs freeField16(String freeField16) {
        this.setFreeField16(freeField16);
        return this;
    }

    public void setFreeField16(String freeField16) {
        this.freeField16 = freeField16;
    }

    public String getFreeField17() {
        return this.freeField17;
    }

    public GenericConfigs freeField17(String freeField17) {
        this.setFreeField17(freeField17);
        return this;
    }

    public void setFreeField17(String freeField17) {
        this.freeField17 = freeField17;
    }

    public byte[] getFreeField18() {
        return this.freeField18;
    }

    public GenericConfigs freeField18(byte[] freeField18) {
        this.setFreeField18(freeField18);
        return this;
    }

    public void setFreeField18(byte[] freeField18) {
        this.freeField18 = freeField18;
    }

    public String getFreeField18ContentType() {
        return this.freeField18ContentType;
    }

    public GenericConfigs freeField18ContentType(String freeField18ContentType) {
        this.freeField18ContentType = freeField18ContentType;
        return this;
    }

    public void setFreeField18ContentType(String freeField18ContentType) {
        this.freeField18ContentType = freeField18ContentType;
    }

    public String getFreeField19() {
        return this.freeField19;
    }

    public GenericConfigs freeField19(String freeField19) {
        this.setFreeField19(freeField19);
        return this;
    }

    public void setFreeField19(String freeField19) {
        this.freeField19 = freeField19;
    }

    public String getFreeField20() {
        return this.freeField20;
    }

    public GenericConfigs freeField20(String freeField20) {
        this.setFreeField20(freeField20);
        return this;
    }

    public void setFreeField20(String freeField20) {
        this.freeField20 = freeField20;
    }

    public String getFreeField21() {
        return this.freeField21;
    }

    public GenericConfigs freeField21(String freeField21) {
        this.setFreeField21(freeField21);
        return this;
    }

    public void setFreeField21(String freeField21) {
        this.freeField21 = freeField21;
    }

    public String getFreeField22() {
        return this.freeField22;
    }

    public GenericConfigs freeField22(String freeField22) {
        this.setFreeField22(freeField22);
        return this;
    }

    public void setFreeField22(String freeField22) {
        this.freeField22 = freeField22;
    }

    public String getFreeField23() {
        return this.freeField23;
    }

    public GenericConfigs freeField23(String freeField23) {
        this.setFreeField23(freeField23);
        return this;
    }

    public void setFreeField23(String freeField23) {
        this.freeField23 = freeField23;
    }

    public String getFreeField24() {
        return this.freeField24;
    }

    public GenericConfigs freeField24(String freeField24) {
        this.setFreeField24(freeField24);
        return this;
    }

    public void setFreeField24(String freeField24) {
        this.freeField24 = freeField24;
    }

    public byte[] getFreeField25() {
        return this.freeField25;
    }

    public GenericConfigs freeField25(byte[] freeField25) {
        this.setFreeField25(freeField25);
        return this;
    }

    public void setFreeField25(byte[] freeField25) {
        this.freeField25 = freeField25;
    }

    public String getFreeField25ContentType() {
        return this.freeField25ContentType;
    }

    public GenericConfigs freeField25ContentType(String freeField25ContentType) {
        this.freeField25ContentType = freeField25ContentType;
        return this;
    }

    public void setFreeField25ContentType(String freeField25ContentType) {
        this.freeField25ContentType = freeField25ContentType;
    }

    public String getFreeField26() {
        return this.freeField26;
    }

    public GenericConfigs freeField26(String freeField26) {
        this.setFreeField26(freeField26);
        return this;
    }

    public void setFreeField26(String freeField26) {
        this.freeField26 = freeField26;
    }

    public String getFreeField27() {
        return this.freeField27;
    }

    public GenericConfigs freeField27(String freeField27) {
        this.setFreeField27(freeField27);
        return this;
    }

    public void setFreeField27(String freeField27) {
        this.freeField27 = freeField27;
    }

    public byte[] getFreeField28() {
        return this.freeField28;
    }

    public GenericConfigs freeField28(byte[] freeField28) {
        this.setFreeField28(freeField28);
        return this;
    }

    public void setFreeField28(byte[] freeField28) {
        this.freeField28 = freeField28;
    }

    public String getFreeField28ContentType() {
        return this.freeField28ContentType;
    }

    public GenericConfigs freeField28ContentType(String freeField28ContentType) {
        this.freeField28ContentType = freeField28ContentType;
        return this;
    }

    public void setFreeField28ContentType(String freeField28ContentType) {
        this.freeField28ContentType = freeField28ContentType;
    }

    public String getFreeField29() {
        return this.freeField29;
    }

    public GenericConfigs freeField29(String freeField29) {
        this.setFreeField29(freeField29);
        return this;
    }

    public void setFreeField29(String freeField29) {
        this.freeField29 = freeField29;
    }

    public String getFreeField30() {
        return this.freeField30;
    }

    public GenericConfigs freeField30(String freeField30) {
        this.setFreeField30(freeField30);
        return this;
    }

    public void setFreeField30(String freeField30) {
        this.freeField30 = freeField30;
    }

    public String getFreeField31() {
        return this.freeField31;
    }

    public GenericConfigs freeField31(String freeField31) {
        this.setFreeField31(freeField31);
        return this;
    }

    public void setFreeField31(String freeField31) {
        this.freeField31 = freeField31;
    }

    public String getFreeField32() {
        return this.freeField32;
    }

    public GenericConfigs freeField32(String freeField32) {
        this.setFreeField32(freeField32);
        return this;
    }

    public void setFreeField32(String freeField32) {
        this.freeField32 = freeField32;
    }

    public String getFreeField33() {
        return this.freeField33;
    }

    public GenericConfigs freeField33(String freeField33) {
        this.setFreeField33(freeField33);
        return this;
    }

    public void setFreeField33(String freeField33) {
        this.freeField33 = freeField33;
    }

    public String getFreeField34() {
        return this.freeField34;
    }

    public GenericConfigs freeField34(String freeField34) {
        this.setFreeField34(freeField34);
        return this;
    }

    public void setFreeField34(String freeField34) {
        this.freeField34 = freeField34;
    }

    public ZonedDateTime getTimestamp() {
        return this.timestamp;
    }

    public GenericConfigs timestamp(ZonedDateTime timestamp) {
        this.setTimestamp(timestamp);
        return this;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public RecordStatus getRecordStatus() {
        return this.recordStatus;
    }

    public GenericConfigs recordStatus(RecordStatus recordStatus) {
        this.setRecordStatus(recordStatus);
        return this;
    }

    public void setRecordStatus(RecordStatus recordStatus) {
        this.recordStatus = recordStatus;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof GenericConfigs)) {
            return false;
        }
        return id != null && id.equals(((GenericConfigs) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "GenericConfigs{" +
            "id=" + getId() +
            ", absaTranRef='" + getAbsaTranRef() + "'" +
            ", recordId='" + getRecordId() + "'" +
            ", configId='" + getConfigId() + "'" +
            ", configName='" + getConfigName() + "'" +
            ", configsDetails='" + getConfigsDetails() + "'" +
            ", additionalConfigs='" + getAdditionalConfigs() + "'" +
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
            ", freeField20='" + getFreeField20() + "'" +
            ", freeField21='" + getFreeField21() + "'" +
            ", freeField22='" + getFreeField22() + "'" +
            ", freeField23='" + getFreeField23() + "'" +
            ", freeField24='" + getFreeField24() + "'" +
            ", freeField25='" + getFreeField25() + "'" +
            ", freeField25ContentType='" + getFreeField25ContentType() + "'" +
            ", freeField26='" + getFreeField26() + "'" +
            ", freeField27='" + getFreeField27() + "'" +
            ", freeField28='" + getFreeField28() + "'" +
            ", freeField28ContentType='" + getFreeField28ContentType() + "'" +
            ", freeField29='" + getFreeField29() + "'" +
            ", freeField30='" + getFreeField30() + "'" +
            ", freeField31='" + getFreeField31() + "'" +
            ", freeField32='" + getFreeField32() + "'" +
            ", freeField33='" + getFreeField33() + "'" +
            ", freeField34='" + getFreeField34() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            ", recordStatus='" + getRecordStatus() + "'" +
            "}";
    }
}
