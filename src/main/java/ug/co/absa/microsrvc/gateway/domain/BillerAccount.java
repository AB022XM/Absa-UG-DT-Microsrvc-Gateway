package ug.co.absa.microsrvc.gateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A BillerAccount.
 */
@Table("biller_account")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "billeraccount")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BillerAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("absa_tran_ref")
    private UUID absaTranRef;

    @NotNull(message = "must not be null")
    @Column("record_id")
    private String recordId;

    @NotNull(message = "must not be null")
    @Column("biller_id")
    private String billerId;

    @NotNull(message = "must not be null")
    @Column("biller_name")
    private String billerName;

    @NotNull(message = "must not be null")
    @Column("biller_acc_number")
    private String billerAccNumber;

    @Column("biller_account_description")
    private String billerAccountDescription;

    @Column("timestamp")
    private ZonedDateTime timestamp;

    @Column("biller_free_field_1")
    private String billerFreeField1;

    @Column("biller_free_field_2")
    private String billerFreeField2;

    @Column("biller_free_field_3")
    private String billerFreeField3;

    @Column("biller_free_field_4")
    private String billerFreeField4;

    @Column("biller_free_field_5")
    private String billerFreeField5;

    @Column("biller_free_field_6")
    private String billerFreeField6;

    @Column("biller_free_field_7")
    private String billerFreeField7;

    @Column("biller_free_field_8")
    private String billerFreeField8;

    @Column("biller_free_field_9")
    private String billerFreeField9;

    @Column("biller_free_field_10")
    private String billerFreeField10;

    @Column("biller_free_field_11")
    private String billerFreeField11;

    @Column("biller_free_field_12")
    private String billerFreeField12;

    @Column("biller_free_field_13")
    private String billerFreeField13;

    @Column("delflg")
    private Boolean delflg;

    @NotNull(message = "must not be null")
    @Column("created_at")
    private ZonedDateTime createdAt;

    @Column("created_by")
    private String createdBy;

    @Column("updated_at")
    private ZonedDateTime updatedAt;

    @Column("updated_by")
    private String updatedBy;

    @Transient
    @JsonIgnoreProperties(value = { "billers", "billers", "billerAccount" }, allowSetters = true)
    private Set<Biller> billerAccounts = new HashSet<>();

    @Transient
    @JsonIgnoreProperties(value = { "billers", "billers", "billerAccount" }, allowSetters = true)
    private Biller biller;

    @Column("biller_id")
    private Long billerId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public BillerAccount id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getAbsaTranRef() {
        return this.absaTranRef;
    }

    public BillerAccount absaTranRef(UUID absaTranRef) {
        this.setAbsaTranRef(absaTranRef);
        return this;
    }

    public void setAbsaTranRef(UUID absaTranRef) {
        this.absaTranRef = absaTranRef;
    }

    public String getRecordId() {
        return this.recordId;
    }

    public BillerAccount recordId(String recordId) {
        this.setRecordId(recordId);
        return this;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getBillerId() {
        return this.billerId;
    }

    public BillerAccount billerId(String billerId) {
        this.setBillerId(billerId);
        return this;
    }

    public void setBillerId(String billerId) {
        this.billerId = billerId;
    }

    public String getBillerName() {
        return this.billerName;
    }

    public BillerAccount billerName(String billerName) {
        this.setBillerName(billerName);
        return this;
    }

    public void setBillerName(String billerName) {
        this.billerName = billerName;
    }

    public String getBillerAccNumber() {
        return this.billerAccNumber;
    }

    public BillerAccount billerAccNumber(String billerAccNumber) {
        this.setBillerAccNumber(billerAccNumber);
        return this;
    }

    public void setBillerAccNumber(String billerAccNumber) {
        this.billerAccNumber = billerAccNumber;
    }

    public String getBillerAccountDescription() {
        return this.billerAccountDescription;
    }

    public BillerAccount billerAccountDescription(String billerAccountDescription) {
        this.setBillerAccountDescription(billerAccountDescription);
        return this;
    }

    public void setBillerAccountDescription(String billerAccountDescription) {
        this.billerAccountDescription = billerAccountDescription;
    }

    public ZonedDateTime getTimestamp() {
        return this.timestamp;
    }

    public BillerAccount timestamp(ZonedDateTime timestamp) {
        this.setTimestamp(timestamp);
        return this;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getBillerFreeField1() {
        return this.billerFreeField1;
    }

    public BillerAccount billerFreeField1(String billerFreeField1) {
        this.setBillerFreeField1(billerFreeField1);
        return this;
    }

    public void setBillerFreeField1(String billerFreeField1) {
        this.billerFreeField1 = billerFreeField1;
    }

    public String getBillerFreeField2() {
        return this.billerFreeField2;
    }

    public BillerAccount billerFreeField2(String billerFreeField2) {
        this.setBillerFreeField2(billerFreeField2);
        return this;
    }

    public void setBillerFreeField2(String billerFreeField2) {
        this.billerFreeField2 = billerFreeField2;
    }

    public String getBillerFreeField3() {
        return this.billerFreeField3;
    }

    public BillerAccount billerFreeField3(String billerFreeField3) {
        this.setBillerFreeField3(billerFreeField3);
        return this;
    }

    public void setBillerFreeField3(String billerFreeField3) {
        this.billerFreeField3 = billerFreeField3;
    }

    public String getBillerFreeField4() {
        return this.billerFreeField4;
    }

    public BillerAccount billerFreeField4(String billerFreeField4) {
        this.setBillerFreeField4(billerFreeField4);
        return this;
    }

    public void setBillerFreeField4(String billerFreeField4) {
        this.billerFreeField4 = billerFreeField4;
    }

    public String getBillerFreeField5() {
        return this.billerFreeField5;
    }

    public BillerAccount billerFreeField5(String billerFreeField5) {
        this.setBillerFreeField5(billerFreeField5);
        return this;
    }

    public void setBillerFreeField5(String billerFreeField5) {
        this.billerFreeField5 = billerFreeField5;
    }

    public String getBillerFreeField6() {
        return this.billerFreeField6;
    }

    public BillerAccount billerFreeField6(String billerFreeField6) {
        this.setBillerFreeField6(billerFreeField6);
        return this;
    }

    public void setBillerFreeField6(String billerFreeField6) {
        this.billerFreeField6 = billerFreeField6;
    }

    public String getBillerFreeField7() {
        return this.billerFreeField7;
    }

    public BillerAccount billerFreeField7(String billerFreeField7) {
        this.setBillerFreeField7(billerFreeField7);
        return this;
    }

    public void setBillerFreeField7(String billerFreeField7) {
        this.billerFreeField7 = billerFreeField7;
    }

    public String getBillerFreeField8() {
        return this.billerFreeField8;
    }

    public BillerAccount billerFreeField8(String billerFreeField8) {
        this.setBillerFreeField8(billerFreeField8);
        return this;
    }

    public void setBillerFreeField8(String billerFreeField8) {
        this.billerFreeField8 = billerFreeField8;
    }

    public String getBillerFreeField9() {
        return this.billerFreeField9;
    }

    public BillerAccount billerFreeField9(String billerFreeField9) {
        this.setBillerFreeField9(billerFreeField9);
        return this;
    }

    public void setBillerFreeField9(String billerFreeField9) {
        this.billerFreeField9 = billerFreeField9;
    }

    public String getBillerFreeField10() {
        return this.billerFreeField10;
    }

    public BillerAccount billerFreeField10(String billerFreeField10) {
        this.setBillerFreeField10(billerFreeField10);
        return this;
    }

    public void setBillerFreeField10(String billerFreeField10) {
        this.billerFreeField10 = billerFreeField10;
    }

    public String getBillerFreeField11() {
        return this.billerFreeField11;
    }

    public BillerAccount billerFreeField11(String billerFreeField11) {
        this.setBillerFreeField11(billerFreeField11);
        return this;
    }

    public void setBillerFreeField11(String billerFreeField11) {
        this.billerFreeField11 = billerFreeField11;
    }

    public String getBillerFreeField12() {
        return this.billerFreeField12;
    }

    public BillerAccount billerFreeField12(String billerFreeField12) {
        this.setBillerFreeField12(billerFreeField12);
        return this;
    }

    public void setBillerFreeField12(String billerFreeField12) {
        this.billerFreeField12 = billerFreeField12;
    }

    public String getBillerFreeField13() {
        return this.billerFreeField13;
    }

    public BillerAccount billerFreeField13(String billerFreeField13) {
        this.setBillerFreeField13(billerFreeField13);
        return this;
    }

    public void setBillerFreeField13(String billerFreeField13) {
        this.billerFreeField13 = billerFreeField13;
    }

    public Boolean getDelflg() {
        return this.delflg;
    }

    public BillerAccount delflg(Boolean delflg) {
        this.setDelflg(delflg);
        return this;
    }

    public void setDelflg(Boolean delflg) {
        this.delflg = delflg;
    }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public BillerAccount createdAt(ZonedDateTime createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public BillerAccount createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public BillerAccount updatedAt(ZonedDateTime updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public BillerAccount updatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Set<Biller> getBillerAccounts() {
        return this.billerAccounts;
    }

    public void setBillerAccounts(Set<Biller> billers) {
        if (this.billerAccounts != null) {
            this.billerAccounts.forEach(i -> i.setBillerAccount(null));
        }
        if (billers != null) {
            billers.forEach(i -> i.setBillerAccount(this));
        }
        this.billerAccounts = billers;
    }

    public BillerAccount billerAccounts(Set<Biller> billers) {
        this.setBillerAccounts(billers);
        return this;
    }

    public BillerAccount addBillerAccount(Biller biller) {
        this.billerAccounts.add(biller);
        biller.setBillerAccount(this);
        return this;
    }

    public BillerAccount removeBillerAccount(Biller biller) {
        this.billerAccounts.remove(biller);
        biller.setBillerAccount(null);
        return this;
    }

    public Biller getBiller() {
        return this.biller;
    }

    public void setBiller(Biller biller) {
        this.biller = biller;
        this.billerId = biller != null ? biller.getId() : null;
    }

    public BillerAccount biller(Biller biller) {
        this.setBiller(biller);
        return this;
    }

    public Long getBillerId() {
        return this.billerId;
    }

    public void setBillerId(Long biller) {
        this.billerId = biller;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BillerAccount)) {
            return false;
        }
        return id != null && id.equals(((BillerAccount) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BillerAccount{" +
            "id=" + getId() +
            ", absaTranRef='" + getAbsaTranRef() + "'" +
            ", recordId='" + getRecordId() + "'" +
            ", billerId='" + getBillerId() + "'" +
            ", billerName='" + getBillerName() + "'" +
            ", billerAccNumber='" + getBillerAccNumber() + "'" +
            ", billerAccountDescription='" + getBillerAccountDescription() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            ", billerFreeField1='" + getBillerFreeField1() + "'" +
            ", billerFreeField2='" + getBillerFreeField2() + "'" +
            ", billerFreeField3='" + getBillerFreeField3() + "'" +
            ", billerFreeField4='" + getBillerFreeField4() + "'" +
            ", billerFreeField5='" + getBillerFreeField5() + "'" +
            ", billerFreeField6='" + getBillerFreeField6() + "'" +
            ", billerFreeField7='" + getBillerFreeField7() + "'" +
            ", billerFreeField8='" + getBillerFreeField8() + "'" +
            ", billerFreeField9='" + getBillerFreeField9() + "'" +
            ", billerFreeField10='" + getBillerFreeField10() + "'" +
            ", billerFreeField11='" + getBillerFreeField11() + "'" +
            ", billerFreeField12='" + getBillerFreeField12() + "'" +
            ", billerFreeField13='" + getBillerFreeField13() + "'" +
            ", delflg='" + getDelflg() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            "}";
    }
}
