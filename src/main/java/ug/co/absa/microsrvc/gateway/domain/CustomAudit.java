package ug.co.absa.microsrvc.gateway.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import ug.co.absa.microsrvc.gateway.domain.enumeration.ServiceLevel;

/**
 * A CustomAudit.
 */
@Table("custom_audit")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "customaudit")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class CustomAudit implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("absa_tran_ref")
    private UUID absaTranRef;

    @Column("record_id")
    private String recordId;

    @NotNull(message = "must not be null")
    @Column("action_id")
    private ServiceLevel actionId;

    @Column("timestamp")
    private ZonedDateTime timestamp;

    @Column("old_value")
    private String oldValue;

    @Column("new_value")
    private String newValue;

    @Column("change_resaon")
    private String changeResaon;

    @Column("description")
    private String description;

    @Column("description_1")
    private String description1;

    @Column("description_2")
    private String description2;

    @Column("description_3")
    private String description3;

    @Column("description_4")
    private String description4;

    @Column("description_5")
    private String description5;

    @Column("description_6")
    private String description6;

    @Column("description_7")
    private String description7;

    @Column("description_8")
    private String description8;

    @Column("description_9")
    private String description9;

    @Column("free_text_1")
    private String freeText1;

    @Column("free_text_2")
    private String freeText2;

    @Column("free_text_3")
    private String freeText3;

    @Column("free_text_4")
    private String freeText4;

    @Column("free_text_5")
    private String freeText5;

    @Column("free_text_6")
    private String freeText6;

    @Column("free_text_7")
    private String freeText7;

    @Column("free_text_8")
    private String freeText8;

    @Column("free_text_9")
    private String freeText9;

    @Column("free_text_10")
    private String freeText10;

    @Column("free_text_11")
    private String freeText11;

    @Column("free_text_12")
    private String freeText12;

    @Column("free_text_13")
    private String freeText13;

    @Column("free_text_14")
    private String freeText14;

    @Column("free_text_15")
    private String freeText15;

    @Column("free_text_16")
    private String freeText16;

    @Column("free_text_17")
    private String freeText17;

    @Column("free_text_18")
    private String freeText18;

    @Column("free_text_19")
    private String freeText19;

    @Column("free_text_20")
    private String freeText20;

    @Column("free_text_21")
    private String freeText21;

    @Column("free_text_22")
    private String freeText22;

    @Column("free_text_23")
    private String freeText23;

    @Column("free_text_24")
    private String freeText24;

    @Column("free_text_25")
    private String freeText25;

    @Column("free_text_26")
    private String freeText26;

    @Column("free_text_27")
    private String freeText27;

    @Column("free_text_28")
    private String freeText28;

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

    public CustomAudit id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getAbsaTranRef() {
        return this.absaTranRef;
    }

    public CustomAudit absaTranRef(UUID absaTranRef) {
        this.setAbsaTranRef(absaTranRef);
        return this;
    }

    public void setAbsaTranRef(UUID absaTranRef) {
        this.absaTranRef = absaTranRef;
    }

    public String getRecordId() {
        return this.recordId;
    }

    public CustomAudit recordId(String recordId) {
        this.setRecordId(recordId);
        return this;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public ServiceLevel getActionId() {
        return this.actionId;
    }

    public CustomAudit actionId(ServiceLevel actionId) {
        this.setActionId(actionId);
        return this;
    }

    public void setActionId(ServiceLevel actionId) {
        this.actionId = actionId;
    }

    public ZonedDateTime getTimestamp() {
        return this.timestamp;
    }

    public CustomAudit timestamp(ZonedDateTime timestamp) {
        this.setTimestamp(timestamp);
        return this;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getOldValue() {
        return this.oldValue;
    }

    public CustomAudit oldValue(String oldValue) {
        this.setOldValue(oldValue);
        return this;
    }

    public void setOldValue(String oldValue) {
        this.oldValue = oldValue;
    }

    public String getNewValue() {
        return this.newValue;
    }

    public CustomAudit newValue(String newValue) {
        this.setNewValue(newValue);
        return this;
    }

    public void setNewValue(String newValue) {
        this.newValue = newValue;
    }

    public String getChangeResaon() {
        return this.changeResaon;
    }

    public CustomAudit changeResaon(String changeResaon) {
        this.setChangeResaon(changeResaon);
        return this;
    }

    public void setChangeResaon(String changeResaon) {
        this.changeResaon = changeResaon;
    }

    public String getDescription() {
        return this.description;
    }

    public CustomAudit description(String description) {
        this.setDescription(description);
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription1() {
        return this.description1;
    }

    public CustomAudit description1(String description1) {
        this.setDescription1(description1);
        return this;
    }

    public void setDescription1(String description1) {
        this.description1 = description1;
    }

    public String getDescription2() {
        return this.description2;
    }

    public CustomAudit description2(String description2) {
        this.setDescription2(description2);
        return this;
    }

    public void setDescription2(String description2) {
        this.description2 = description2;
    }

    public String getDescription3() {
        return this.description3;
    }

    public CustomAudit description3(String description3) {
        this.setDescription3(description3);
        return this;
    }

    public void setDescription3(String description3) {
        this.description3 = description3;
    }

    public String getDescription4() {
        return this.description4;
    }

    public CustomAudit description4(String description4) {
        this.setDescription4(description4);
        return this;
    }

    public void setDescription4(String description4) {
        this.description4 = description4;
    }

    public String getDescription5() {
        return this.description5;
    }

    public CustomAudit description5(String description5) {
        this.setDescription5(description5);
        return this;
    }

    public void setDescription5(String description5) {
        this.description5 = description5;
    }

    public String getDescription6() {
        return this.description6;
    }

    public CustomAudit description6(String description6) {
        this.setDescription6(description6);
        return this;
    }

    public void setDescription6(String description6) {
        this.description6 = description6;
    }

    public String getDescription7() {
        return this.description7;
    }

    public CustomAudit description7(String description7) {
        this.setDescription7(description7);
        return this;
    }

    public void setDescription7(String description7) {
        this.description7 = description7;
    }

    public String getDescription8() {
        return this.description8;
    }

    public CustomAudit description8(String description8) {
        this.setDescription8(description8);
        return this;
    }

    public void setDescription8(String description8) {
        this.description8 = description8;
    }

    public String getDescription9() {
        return this.description9;
    }

    public CustomAudit description9(String description9) {
        this.setDescription9(description9);
        return this;
    }

    public void setDescription9(String description9) {
        this.description9 = description9;
    }

    public String getFreeText1() {
        return this.freeText1;
    }

    public CustomAudit freeText1(String freeText1) {
        this.setFreeText1(freeText1);
        return this;
    }

    public void setFreeText1(String freeText1) {
        this.freeText1 = freeText1;
    }

    public String getFreeText2() {
        return this.freeText2;
    }

    public CustomAudit freeText2(String freeText2) {
        this.setFreeText2(freeText2);
        return this;
    }

    public void setFreeText2(String freeText2) {
        this.freeText2 = freeText2;
    }

    public String getFreeText3() {
        return this.freeText3;
    }

    public CustomAudit freeText3(String freeText3) {
        this.setFreeText3(freeText3);
        return this;
    }

    public void setFreeText3(String freeText3) {
        this.freeText3 = freeText3;
    }

    public String getFreeText4() {
        return this.freeText4;
    }

    public CustomAudit freeText4(String freeText4) {
        this.setFreeText4(freeText4);
        return this;
    }

    public void setFreeText4(String freeText4) {
        this.freeText4 = freeText4;
    }

    public String getFreeText5() {
        return this.freeText5;
    }

    public CustomAudit freeText5(String freeText5) {
        this.setFreeText5(freeText5);
        return this;
    }

    public void setFreeText5(String freeText5) {
        this.freeText5 = freeText5;
    }

    public String getFreeText6() {
        return this.freeText6;
    }

    public CustomAudit freeText6(String freeText6) {
        this.setFreeText6(freeText6);
        return this;
    }

    public void setFreeText6(String freeText6) {
        this.freeText6 = freeText6;
    }

    public String getFreeText7() {
        return this.freeText7;
    }

    public CustomAudit freeText7(String freeText7) {
        this.setFreeText7(freeText7);
        return this;
    }

    public void setFreeText7(String freeText7) {
        this.freeText7 = freeText7;
    }

    public String getFreeText8() {
        return this.freeText8;
    }

    public CustomAudit freeText8(String freeText8) {
        this.setFreeText8(freeText8);
        return this;
    }

    public void setFreeText8(String freeText8) {
        this.freeText8 = freeText8;
    }

    public String getFreeText9() {
        return this.freeText9;
    }

    public CustomAudit freeText9(String freeText9) {
        this.setFreeText9(freeText9);
        return this;
    }

    public void setFreeText9(String freeText9) {
        this.freeText9 = freeText9;
    }

    public String getFreeText10() {
        return this.freeText10;
    }

    public CustomAudit freeText10(String freeText10) {
        this.setFreeText10(freeText10);
        return this;
    }

    public void setFreeText10(String freeText10) {
        this.freeText10 = freeText10;
    }

    public String getFreeText11() {
        return this.freeText11;
    }

    public CustomAudit freeText11(String freeText11) {
        this.setFreeText11(freeText11);
        return this;
    }

    public void setFreeText11(String freeText11) {
        this.freeText11 = freeText11;
    }

    public String getFreeText12() {
        return this.freeText12;
    }

    public CustomAudit freeText12(String freeText12) {
        this.setFreeText12(freeText12);
        return this;
    }

    public void setFreeText12(String freeText12) {
        this.freeText12 = freeText12;
    }

    public String getFreeText13() {
        return this.freeText13;
    }

    public CustomAudit freeText13(String freeText13) {
        this.setFreeText13(freeText13);
        return this;
    }

    public void setFreeText13(String freeText13) {
        this.freeText13 = freeText13;
    }

    public String getFreeText14() {
        return this.freeText14;
    }

    public CustomAudit freeText14(String freeText14) {
        this.setFreeText14(freeText14);
        return this;
    }

    public void setFreeText14(String freeText14) {
        this.freeText14 = freeText14;
    }

    public String getFreeText15() {
        return this.freeText15;
    }

    public CustomAudit freeText15(String freeText15) {
        this.setFreeText15(freeText15);
        return this;
    }

    public void setFreeText15(String freeText15) {
        this.freeText15 = freeText15;
    }

    public String getFreeText16() {
        return this.freeText16;
    }

    public CustomAudit freeText16(String freeText16) {
        this.setFreeText16(freeText16);
        return this;
    }

    public void setFreeText16(String freeText16) {
        this.freeText16 = freeText16;
    }

    public String getFreeText17() {
        return this.freeText17;
    }

    public CustomAudit freeText17(String freeText17) {
        this.setFreeText17(freeText17);
        return this;
    }

    public void setFreeText17(String freeText17) {
        this.freeText17 = freeText17;
    }

    public String getFreeText18() {
        return this.freeText18;
    }

    public CustomAudit freeText18(String freeText18) {
        this.setFreeText18(freeText18);
        return this;
    }

    public void setFreeText18(String freeText18) {
        this.freeText18 = freeText18;
    }

    public String getFreeText19() {
        return this.freeText19;
    }

    public CustomAudit freeText19(String freeText19) {
        this.setFreeText19(freeText19);
        return this;
    }

    public void setFreeText19(String freeText19) {
        this.freeText19 = freeText19;
    }

    public String getFreeText20() {
        return this.freeText20;
    }

    public CustomAudit freeText20(String freeText20) {
        this.setFreeText20(freeText20);
        return this;
    }

    public void setFreeText20(String freeText20) {
        this.freeText20 = freeText20;
    }

    public String getFreeText21() {
        return this.freeText21;
    }

    public CustomAudit freeText21(String freeText21) {
        this.setFreeText21(freeText21);
        return this;
    }

    public void setFreeText21(String freeText21) {
        this.freeText21 = freeText21;
    }

    public String getFreeText22() {
        return this.freeText22;
    }

    public CustomAudit freeText22(String freeText22) {
        this.setFreeText22(freeText22);
        return this;
    }

    public void setFreeText22(String freeText22) {
        this.freeText22 = freeText22;
    }

    public String getFreeText23() {
        return this.freeText23;
    }

    public CustomAudit freeText23(String freeText23) {
        this.setFreeText23(freeText23);
        return this;
    }

    public void setFreeText23(String freeText23) {
        this.freeText23 = freeText23;
    }

    public String getFreeText24() {
        return this.freeText24;
    }

    public CustomAudit freeText24(String freeText24) {
        this.setFreeText24(freeText24);
        return this;
    }

    public void setFreeText24(String freeText24) {
        this.freeText24 = freeText24;
    }

    public String getFreeText25() {
        return this.freeText25;
    }

    public CustomAudit freeText25(String freeText25) {
        this.setFreeText25(freeText25);
        return this;
    }

    public void setFreeText25(String freeText25) {
        this.freeText25 = freeText25;
    }

    public String getFreeText26() {
        return this.freeText26;
    }

    public CustomAudit freeText26(String freeText26) {
        this.setFreeText26(freeText26);
        return this;
    }

    public void setFreeText26(String freeText26) {
        this.freeText26 = freeText26;
    }

    public String getFreeText27() {
        return this.freeText27;
    }

    public CustomAudit freeText27(String freeText27) {
        this.setFreeText27(freeText27);
        return this;
    }

    public void setFreeText27(String freeText27) {
        this.freeText27 = freeText27;
    }

    public String getFreeText28() {
        return this.freeText28;
    }

    public CustomAudit freeText28(String freeText28) {
        this.setFreeText28(freeText28);
        return this;
    }

    public void setFreeText28(String freeText28) {
        this.freeText28 = freeText28;
    }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public CustomAudit createdAt(ZonedDateTime createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public CustomAudit createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public CustomAudit updatedAt(ZonedDateTime updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public CustomAudit updatedBy(String updatedBy) {
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
        if (!(o instanceof CustomAudit)) {
            return false;
        }
        return id != null && id.equals(((CustomAudit) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "CustomAudit{" +
            "id=" + getId() +
            ", absaTranRef='" + getAbsaTranRef() + "'" +
            ", recordId='" + getRecordId() + "'" +
            ", actionId='" + getActionId() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            ", oldValue='" + getOldValue() + "'" +
            ", newValue='" + getNewValue() + "'" +
            ", changeResaon='" + getChangeResaon() + "'" +
            ", description='" + getDescription() + "'" +
            ", description1='" + getDescription1() + "'" +
            ", description2='" + getDescription2() + "'" +
            ", description3='" + getDescription3() + "'" +
            ", description4='" + getDescription4() + "'" +
            ", description5='" + getDescription5() + "'" +
            ", description6='" + getDescription6() + "'" +
            ", description7='" + getDescription7() + "'" +
            ", description8='" + getDescription8() + "'" +
            ", description9='" + getDescription9() + "'" +
            ", freeText1='" + getFreeText1() + "'" +
            ", freeText2='" + getFreeText2() + "'" +
            ", freeText3='" + getFreeText3() + "'" +
            ", freeText4='" + getFreeText4() + "'" +
            ", freeText5='" + getFreeText5() + "'" +
            ", freeText6='" + getFreeText6() + "'" +
            ", freeText7='" + getFreeText7() + "'" +
            ", freeText8='" + getFreeText8() + "'" +
            ", freeText9='" + getFreeText9() + "'" +
            ", freeText10='" + getFreeText10() + "'" +
            ", freeText11='" + getFreeText11() + "'" +
            ", freeText12='" + getFreeText12() + "'" +
            ", freeText13='" + getFreeText13() + "'" +
            ", freeText14='" + getFreeText14() + "'" +
            ", freeText15='" + getFreeText15() + "'" +
            ", freeText16='" + getFreeText16() + "'" +
            ", freeText17='" + getFreeText17() + "'" +
            ", freeText18='" + getFreeText18() + "'" +
            ", freeText19='" + getFreeText19() + "'" +
            ", freeText20='" + getFreeText20() + "'" +
            ", freeText21='" + getFreeText21() + "'" +
            ", freeText22='" + getFreeText22() + "'" +
            ", freeText23='" + getFreeText23() + "'" +
            ", freeText24='" + getFreeText24() + "'" +
            ", freeText25='" + getFreeText25() + "'" +
            ", freeText26='" + getFreeText26() + "'" +
            ", freeText27='" + getFreeText27() + "'" +
            ", freeText28='" + getFreeText28() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            "}";
    }
}
