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
 * A Biller.
 */
@Table("biller")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "biller")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Biller implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("absa_tran_ref")
    private UUID absaTranRef;

    @NotNull(message = "must not be null")
    @Column("biller_id")
    private String billerId;

    @NotNull(message = "must not be null")
    @Column("biller_code")
    private String billerCode;

    @Column("biller_name")
    private String billerName;

    @Column("biller_category_id")
    private String billerCategoryId;

    @Column("address_id")
    private String addressId;

    @Column("narative")
    private String narative;

    @Column("narative_1")
    private String narative1;

    @Column("narative_2")
    private String narative2;

    @Column("narative_3")
    private String narative3;

    @Column("narative_4")
    private String narative4;

    @Column("narative_5")
    private String narative5;

    @Column("narative_6")
    private String narative6;

    @Column("narative_7")
    private String narative7;

    @Column("timestamp")
    private ZonedDateTime timestamp;

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
    @JsonIgnoreProperties(value = { "billerAccounts", "biller" }, allowSetters = true)
    private BillerAccount billerAccount;

    @Column("biller_account_id")
    private Long billerAccountId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Biller id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getAbsaTranRef() {
        return this.absaTranRef;
    }

    public Biller absaTranRef(UUID absaTranRef) {
        this.setAbsaTranRef(absaTranRef);
        return this;
    }

    public void setAbsaTranRef(UUID absaTranRef) {
        this.absaTranRef = absaTranRef;
    }

    public String getBillerId() {
        return this.billerId;
    }

    public Biller billerId(String billerId) {
        this.setBillerId(billerId);
        return this;
    }

    public void setBillerId(String billerId) {
        this.billerId = billerId;
    }

    public String getBillerCode() {
        return this.billerCode;
    }

    public Biller billerCode(String billerCode) {
        this.setBillerCode(billerCode);
        return this;
    }

    public void setBillerCode(String billerCode) {
        this.billerCode = billerCode;
    }

    public String getBillerName() {
        return this.billerName;
    }

    public Biller billerName(String billerName) {
        this.setBillerName(billerName);
        return this;
    }

    public void setBillerName(String billerName) {
        this.billerName = billerName;
    }

    public String getBillerCategoryId() {
        return this.billerCategoryId;
    }

    public Biller billerCategoryId(String billerCategoryId) {
        this.setBillerCategoryId(billerCategoryId);
        return this;
    }

    public void setBillerCategoryId(String billerCategoryId) {
        this.billerCategoryId = billerCategoryId;
    }

    public String getAddressId() {
        return this.addressId;
    }

    public Biller addressId(String addressId) {
        this.setAddressId(addressId);
        return this;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getNarative() {
        return this.narative;
    }

    public Biller narative(String narative) {
        this.setNarative(narative);
        return this;
    }

    public void setNarative(String narative) {
        this.narative = narative;
    }

    public String getNarative1() {
        return this.narative1;
    }

    public Biller narative1(String narative1) {
        this.setNarative1(narative1);
        return this;
    }

    public void setNarative1(String narative1) {
        this.narative1 = narative1;
    }

    public String getNarative2() {
        return this.narative2;
    }

    public Biller narative2(String narative2) {
        this.setNarative2(narative2);
        return this;
    }

    public void setNarative2(String narative2) {
        this.narative2 = narative2;
    }

    public String getNarative3() {
        return this.narative3;
    }

    public Biller narative3(String narative3) {
        this.setNarative3(narative3);
        return this;
    }

    public void setNarative3(String narative3) {
        this.narative3 = narative3;
    }

    public String getNarative4() {
        return this.narative4;
    }

    public Biller narative4(String narative4) {
        this.setNarative4(narative4);
        return this;
    }

    public void setNarative4(String narative4) {
        this.narative4 = narative4;
    }

    public String getNarative5() {
        return this.narative5;
    }

    public Biller narative5(String narative5) {
        this.setNarative5(narative5);
        return this;
    }

    public void setNarative5(String narative5) {
        this.narative5 = narative5;
    }

    public String getNarative6() {
        return this.narative6;
    }

    public Biller narative6(String narative6) {
        this.setNarative6(narative6);
        return this;
    }

    public void setNarative6(String narative6) {
        this.narative6 = narative6;
    }

    public String getNarative7() {
        return this.narative7;
    }

    public Biller narative7(String narative7) {
        this.setNarative7(narative7);
        return this;
    }

    public void setNarative7(String narative7) {
        this.narative7 = narative7;
    }

    public ZonedDateTime getTimestamp() {
        return this.timestamp;
    }

    public Biller timestamp(ZonedDateTime timestamp) {
        this.setTimestamp(timestamp);
        return this;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getFreeField1() {
        return this.freeField1;
    }

    public Biller freeField1(String freeField1) {
        this.setFreeField1(freeField1);
        return this;
    }

    public void setFreeField1(String freeField1) {
        this.freeField1 = freeField1;
    }

    public String getFreeField2() {
        return this.freeField2;
    }

    public Biller freeField2(String freeField2) {
        this.setFreeField2(freeField2);
        return this;
    }

    public void setFreeField2(String freeField2) {
        this.freeField2 = freeField2;
    }

    public String getFreeField3() {
        return this.freeField3;
    }

    public Biller freeField3(String freeField3) {
        this.setFreeField3(freeField3);
        return this;
    }

    public void setFreeField3(String freeField3) {
        this.freeField3 = freeField3;
    }

    public String getFreeField4() {
        return this.freeField4;
    }

    public Biller freeField4(String freeField4) {
        this.setFreeField4(freeField4);
        return this;
    }

    public void setFreeField4(String freeField4) {
        this.freeField4 = freeField4;
    }

    public String getFreeField5() {
        return this.freeField5;
    }

    public Biller freeField5(String freeField5) {
        this.setFreeField5(freeField5);
        return this;
    }

    public void setFreeField5(String freeField5) {
        this.freeField5 = freeField5;
    }

    public String getFreeField6() {
        return this.freeField6;
    }

    public Biller freeField6(String freeField6) {
        this.setFreeField6(freeField6);
        return this;
    }

    public void setFreeField6(String freeField6) {
        this.freeField6 = freeField6;
    }

    public String getFreeField7() {
        return this.freeField7;
    }

    public Biller freeField7(String freeField7) {
        this.setFreeField7(freeField7);
        return this;
    }

    public void setFreeField7(String freeField7) {
        this.freeField7 = freeField7;
    }

    public String getFreeField8() {
        return this.freeField8;
    }

    public Biller freeField8(String freeField8) {
        this.setFreeField8(freeField8);
        return this;
    }

    public void setFreeField8(String freeField8) {
        this.freeField8 = freeField8;
    }

    public String getFreeField9() {
        return this.freeField9;
    }

    public Biller freeField9(String freeField9) {
        this.setFreeField9(freeField9);
        return this;
    }

    public void setFreeField9(String freeField9) {
        this.freeField9 = freeField9;
    }

    public String getFreeField10() {
        return this.freeField10;
    }

    public Biller freeField10(String freeField10) {
        this.setFreeField10(freeField10);
        return this;
    }

    public void setFreeField10(String freeField10) {
        this.freeField10 = freeField10;
    }

    public String getFreeField11() {
        return this.freeField11;
    }

    public Biller freeField11(String freeField11) {
        this.setFreeField11(freeField11);
        return this;
    }

    public void setFreeField11(String freeField11) {
        this.freeField11 = freeField11;
    }

    public String getFreeField12() {
        return this.freeField12;
    }

    public Biller freeField12(String freeField12) {
        this.setFreeField12(freeField12);
        return this;
    }

    public void setFreeField12(String freeField12) {
        this.freeField12 = freeField12;
    }

    public String getFreeField13() {
        return this.freeField13;
    }

    public Biller freeField13(String freeField13) {
        this.setFreeField13(freeField13);
        return this;
    }

    public void setFreeField13(String freeField13) {
        this.freeField13 = freeField13;
    }

    public Boolean getDelflg() {
        return this.delflg;
    }

    public Biller delflg(Boolean delflg) {
        this.setDelflg(delflg);
        return this;
    }

    public void setDelflg(Boolean delflg) {
        this.delflg = delflg;
    }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public Biller createdAt(ZonedDateTime createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Biller createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public Biller updatedAt(ZonedDateTime updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public Biller updatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }


    public BillerAccount getBillerAccount() {
        return this.billerAccount;
    }

    public void setBillerAccount(BillerAccount billerAccount) {
        this.billerAccount = billerAccount;
        this.billerAccountId = billerAccount != null ? billerAccount.getId() : null;
    }

    public Biller billerAccount(BillerAccount billerAccount) {
        this.setBillerAccount(billerAccount);
        return this;
    }

    public Long getBillerAccountId() {
        return this.billerAccountId;
    }

    public void setBillerAccountId(Long billerAccount) {
        this.billerAccountId = billerAccount;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Biller)) {
            return false;
        }
        return id != null && id.equals(((Biller) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Biller{" +
            "id=" + getId() +
            ", absaTranRef='" + getAbsaTranRef() + "'" +
            ", billerId='" + getBillerId() + "'" +
            ", billerCode='" + getBillerCode() + "'" +
            ", billerName='" + getBillerName() + "'" +
            ", billerCategoryId='" + getBillerCategoryId() + "'" +
            ", addressId='" + getAddressId() + "'" +
            ", narative='" + getNarative() + "'" +
            ", narative1='" + getNarative1() + "'" +
            ", narative2='" + getNarative2() + "'" +
            ", narative3='" + getNarative3() + "'" +
            ", narative4='" + getNarative4() + "'" +
            ", narative5='" + getNarative5() + "'" +
            ", narative6='" + getNarative6() + "'" +
            ", narative7='" + getNarative7() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
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
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            "}";
    }
}
