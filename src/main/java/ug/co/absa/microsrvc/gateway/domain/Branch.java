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
import ug.co.absa.microsrvc.gateway.domain.enumeration.RecordStatus;

/**
 * A Branch.
 */
@Table("branch")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "branch")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Branch implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("absa_tran_ref")
    private UUID absaTranRef;

    @Column("record_id")
    private String recordId;

    @Column("address_id")
    private String addressId;

    @Column("bank_id")
    private String bankId;

    @Column("branch_id")
    private String branchId;

    @NotNull(message = "must not be null")
    @Column("branch_name")
    private String branchName;

    @NotNull(message = "must not be null")
    @Column("branch_swift_code")
    private String branchSwiftCode;

    @Column("branch_phone_number")
    private String branchPhoneNumber;

    @Column("branch_email")
    private String branchEmail;

    @Column("branch_free_field_1")
    private String branchFreeField1;

    @Column("branch_free_field_3")
    private String branchFreeField3;

    @Column("branch_free_field_4")
    private String branchFreeField4;

    @Column("branch_free_field_5")
    private String branchFreeField5;

    @Column("branch_free_field_6")
    private String branchFreeField6;

    @Column("branch_free_field_7")
    private String branchFreeField7;

    @Column("branch_free_field_8")
    private String branchFreeField8;

    @Column("branch_free_field_9")
    private String branchFreeField9;

    @Column("branch_free_field_10")
    private String branchFreeField10;

    @Column("branch_free_field_11")
    private String branchFreeField11;

    @Column("branch_free_field_12")
    private String branchFreeField12;

    @Column("branch_free_field_13")
    private String branchFreeField13;

    @Column("branch_free_field_14")
    private String branchFreeField14;

    @Column("branch_free_field_15")
    private String branchFreeField15;

    @Column("branch_free_field_16")
    private String branchFreeField16;

    @Column("branch_free_field_17")
    private String branchFreeField17;

    @Column("branch_free_field_18")
    private String branchFreeField18;

    @Column("branch_free_field_19")
    private String branchFreeField19;

    @Column("branch_free_field_20")
    private String branchFreeField20;

    @Column("branch_free_field_21")
    private String branchFreeField21;

    @Column("branch_free_field_22")
    private String branchFreeField22;

    @Column("branch_free_field_23")
    private String branchFreeField23;

    @Column("branch_free_field_24")
    private String branchFreeField24;

    @NotNull(message = "must not be null")
    @Column("created_at")
    private ZonedDateTime createdAt;

    @Column("created_by")
    private String createdBy;

    @Column("updated_at")
    private ZonedDateTime updatedAt;

    @Column("updated_by")
    private String updatedBy;

    @Column("timestamp")
    private ZonedDateTime timestamp;

    @Column("status")
    private RecordStatus status;

    @Transient
    @JsonIgnoreProperties(value = { "banks" }, allowSetters = true)
    private Bank bank;

    @Column("bank_id")
    private Long bankId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Branch id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getAbsaTranRef() {
        return this.absaTranRef;
    }

    public Branch absaTranRef(UUID absaTranRef) {
        this.setAbsaTranRef(absaTranRef);
        return this;
    }

    public void setAbsaTranRef(UUID absaTranRef) {
        this.absaTranRef = absaTranRef;
    }

    public String getRecordId() {
        return this.recordId;
    }

    public Branch recordId(String recordId) {
        this.setRecordId(recordId);
        return this;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getAddressId() {
        return this.addressId;
    }

    public Branch addressId(String addressId) {
        this.setAddressId(addressId);
        return this;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getBankId() {
        return this.bankId;
    }

    public Branch bankId(String bankId) {
        this.setBankId(bankId);
        return this;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getBranchId() {
        return this.branchId;
    }

    public Branch branchId(String branchId) {
        this.setBranchId(branchId);
        return this;
    }

    public void setBranchId(String branchId) {
        this.branchId = branchId;
    }

    public String getBranchName() {
        return this.branchName;
    }

    public Branch branchName(String branchName) {
        this.setBranchName(branchName);
        return this;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getBranchSwiftCode() {
        return this.branchSwiftCode;
    }

    public Branch branchSwiftCode(String branchSwiftCode) {
        this.setBranchSwiftCode(branchSwiftCode);
        return this;
    }

    public void setBranchSwiftCode(String branchSwiftCode) {
        this.branchSwiftCode = branchSwiftCode;
    }

    public String getBranchPhoneNumber() {
        return this.branchPhoneNumber;
    }

    public Branch branchPhoneNumber(String branchPhoneNumber) {
        this.setBranchPhoneNumber(branchPhoneNumber);
        return this;
    }

    public void setBranchPhoneNumber(String branchPhoneNumber) {
        this.branchPhoneNumber = branchPhoneNumber;
    }

    public String getBranchEmail() {
        return this.branchEmail;
    }

    public Branch branchEmail(String branchEmail) {
        this.setBranchEmail(branchEmail);
        return this;
    }

    public void setBranchEmail(String branchEmail) {
        this.branchEmail = branchEmail;
    }

    public String getBranchFreeField1() {
        return this.branchFreeField1;
    }

    public Branch branchFreeField1(String branchFreeField1) {
        this.setBranchFreeField1(branchFreeField1);
        return this;
    }

    public void setBranchFreeField1(String branchFreeField1) {
        this.branchFreeField1 = branchFreeField1;
    }

    public String getBranchFreeField3() {
        return this.branchFreeField3;
    }

    public Branch branchFreeField3(String branchFreeField3) {
        this.setBranchFreeField3(branchFreeField3);
        return this;
    }

    public void setBranchFreeField3(String branchFreeField3) {
        this.branchFreeField3 = branchFreeField3;
    }

    public String getBranchFreeField4() {
        return this.branchFreeField4;
    }

    public Branch branchFreeField4(String branchFreeField4) {
        this.setBranchFreeField4(branchFreeField4);
        return this;
    }

    public void setBranchFreeField4(String branchFreeField4) {
        this.branchFreeField4 = branchFreeField4;
    }

    public String getBranchFreeField5() {
        return this.branchFreeField5;
    }

    public Branch branchFreeField5(String branchFreeField5) {
        this.setBranchFreeField5(branchFreeField5);
        return this;
    }

    public void setBranchFreeField5(String branchFreeField5) {
        this.branchFreeField5 = branchFreeField5;
    }

    public String getBranchFreeField6() {
        return this.branchFreeField6;
    }

    public Branch branchFreeField6(String branchFreeField6) {
        this.setBranchFreeField6(branchFreeField6);
        return this;
    }

    public void setBranchFreeField6(String branchFreeField6) {
        this.branchFreeField6 = branchFreeField6;
    }

    public String getBranchFreeField7() {
        return this.branchFreeField7;
    }

    public Branch branchFreeField7(String branchFreeField7) {
        this.setBranchFreeField7(branchFreeField7);
        return this;
    }

    public void setBranchFreeField7(String branchFreeField7) {
        this.branchFreeField7 = branchFreeField7;
    }

    public String getBranchFreeField8() {
        return this.branchFreeField8;
    }

    public Branch branchFreeField8(String branchFreeField8) {
        this.setBranchFreeField8(branchFreeField8);
        return this;
    }

    public void setBranchFreeField8(String branchFreeField8) {
        this.branchFreeField8 = branchFreeField8;
    }

    public String getBranchFreeField9() {
        return this.branchFreeField9;
    }

    public Branch branchFreeField9(String branchFreeField9) {
        this.setBranchFreeField9(branchFreeField9);
        return this;
    }

    public void setBranchFreeField9(String branchFreeField9) {
        this.branchFreeField9 = branchFreeField9;
    }

    public String getBranchFreeField10() {
        return this.branchFreeField10;
    }

    public Branch branchFreeField10(String branchFreeField10) {
        this.setBranchFreeField10(branchFreeField10);
        return this;
    }

    public void setBranchFreeField10(String branchFreeField10) {
        this.branchFreeField10 = branchFreeField10;
    }

    public String getBranchFreeField11() {
        return this.branchFreeField11;
    }

    public Branch branchFreeField11(String branchFreeField11) {
        this.setBranchFreeField11(branchFreeField11);
        return this;
    }

    public void setBranchFreeField11(String branchFreeField11) {
        this.branchFreeField11 = branchFreeField11;
    }

    public String getBranchFreeField12() {
        return this.branchFreeField12;
    }

    public Branch branchFreeField12(String branchFreeField12) {
        this.setBranchFreeField12(branchFreeField12);
        return this;
    }

    public void setBranchFreeField12(String branchFreeField12) {
        this.branchFreeField12 = branchFreeField12;
    }

    public String getBranchFreeField13() {
        return this.branchFreeField13;
    }

    public Branch branchFreeField13(String branchFreeField13) {
        this.setBranchFreeField13(branchFreeField13);
        return this;
    }

    public void setBranchFreeField13(String branchFreeField13) {
        this.branchFreeField13 = branchFreeField13;
    }

    public String getBranchFreeField14() {
        return this.branchFreeField14;
    }

    public Branch branchFreeField14(String branchFreeField14) {
        this.setBranchFreeField14(branchFreeField14);
        return this;
    }

    public void setBranchFreeField14(String branchFreeField14) {
        this.branchFreeField14 = branchFreeField14;
    }

    public String getBranchFreeField15() {
        return this.branchFreeField15;
    }

    public Branch branchFreeField15(String branchFreeField15) {
        this.setBranchFreeField15(branchFreeField15);
        return this;
    }

    public void setBranchFreeField15(String branchFreeField15) {
        this.branchFreeField15 = branchFreeField15;
    }

    public String getBranchFreeField16() {
        return this.branchFreeField16;
    }

    public Branch branchFreeField16(String branchFreeField16) {
        this.setBranchFreeField16(branchFreeField16);
        return this;
    }

    public void setBranchFreeField16(String branchFreeField16) {
        this.branchFreeField16 = branchFreeField16;
    }

    public String getBranchFreeField17() {
        return this.branchFreeField17;
    }

    public Branch branchFreeField17(String branchFreeField17) {
        this.setBranchFreeField17(branchFreeField17);
        return this;
    }

    public void setBranchFreeField17(String branchFreeField17) {
        this.branchFreeField17 = branchFreeField17;
    }

    public String getBranchFreeField18() {
        return this.branchFreeField18;
    }

    public Branch branchFreeField18(String branchFreeField18) {
        this.setBranchFreeField18(branchFreeField18);
        return this;
    }

    public void setBranchFreeField18(String branchFreeField18) {
        this.branchFreeField18 = branchFreeField18;
    }

    public String getBranchFreeField19() {
        return this.branchFreeField19;
    }

    public Branch branchFreeField19(String branchFreeField19) {
        this.setBranchFreeField19(branchFreeField19);
        return this;
    }

    public void setBranchFreeField19(String branchFreeField19) {
        this.branchFreeField19 = branchFreeField19;
    }

    public String getBranchFreeField20() {
        return this.branchFreeField20;
    }

    public Branch branchFreeField20(String branchFreeField20) {
        this.setBranchFreeField20(branchFreeField20);
        return this;
    }

    public void setBranchFreeField20(String branchFreeField20) {
        this.branchFreeField20 = branchFreeField20;
    }

    public String getBranchFreeField21() {
        return this.branchFreeField21;
    }

    public Branch branchFreeField21(String branchFreeField21) {
        this.setBranchFreeField21(branchFreeField21);
        return this;
    }

    public void setBranchFreeField21(String branchFreeField21) {
        this.branchFreeField21 = branchFreeField21;
    }

    public String getBranchFreeField22() {
        return this.branchFreeField22;
    }

    public Branch branchFreeField22(String branchFreeField22) {
        this.setBranchFreeField22(branchFreeField22);
        return this;
    }

    public void setBranchFreeField22(String branchFreeField22) {
        this.branchFreeField22 = branchFreeField22;
    }

    public String getBranchFreeField23() {
        return this.branchFreeField23;
    }

    public Branch branchFreeField23(String branchFreeField23) {
        this.setBranchFreeField23(branchFreeField23);
        return this;
    }

    public void setBranchFreeField23(String branchFreeField23) {
        this.branchFreeField23 = branchFreeField23;
    }

    public String getBranchFreeField24() {
        return this.branchFreeField24;
    }

    public Branch branchFreeField24(String branchFreeField24) {
        this.setBranchFreeField24(branchFreeField24);
        return this;
    }

    public void setBranchFreeField24(String branchFreeField24) {
        this.branchFreeField24 = branchFreeField24;
    }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public Branch createdAt(ZonedDateTime createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Branch createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public Branch updatedAt(ZonedDateTime updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public Branch updatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public ZonedDateTime getTimestamp() {
        return this.timestamp;
    }

    public Branch timestamp(ZonedDateTime timestamp) {
        this.setTimestamp(timestamp);
        return this;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public RecordStatus getStatus() {
        return this.status;
    }

    public Branch status(RecordStatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(RecordStatus status) {
        this.status = status;
    }

    public Bank getBank() {
        return this.bank;
    }

    public void setBank(Bank bank) {
        this.bank = bank;
        this.bankId = bank != null ? bank.getId() : null;
    }

    public Branch bank(Bank bank) {
        this.setBank(bank);
        return this;
    }

    public Long getBankId() {
        return this.bankId;
    }

    public void setBankId(Long bank) {
        this.bankId = bank;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Branch)) {
            return false;
        }
        return id != null && id.equals(((Branch) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Branch{" +
            "id=" + getId() +
            ", absaTranRef='" + getAbsaTranRef() + "'" +
            ", recordId='" + getRecordId() + "'" +
            ", addressId='" + getAddressId() + "'" +
            ", bankId='" + getBankId() + "'" +
            ", branchId='" + getBranchId() + "'" +
            ", branchName='" + getBranchName() + "'" +
            ", branchSwiftCode='" + getBranchSwiftCode() + "'" +
            ", branchPhoneNumber='" + getBranchPhoneNumber() + "'" +
            ", branchEmail='" + getBranchEmail() + "'" +
            ", branchFreeField1='" + getBranchFreeField1() + "'" +
            ", branchFreeField3='" + getBranchFreeField3() + "'" +
            ", branchFreeField4='" + getBranchFreeField4() + "'" +
            ", branchFreeField5='" + getBranchFreeField5() + "'" +
            ", branchFreeField6='" + getBranchFreeField6() + "'" +
            ", branchFreeField7='" + getBranchFreeField7() + "'" +
            ", branchFreeField8='" + getBranchFreeField8() + "'" +
            ", branchFreeField9='" + getBranchFreeField9() + "'" +
            ", branchFreeField10='" + getBranchFreeField10() + "'" +
            ", branchFreeField11='" + getBranchFreeField11() + "'" +
            ", branchFreeField12='" + getBranchFreeField12() + "'" +
            ", branchFreeField13='" + getBranchFreeField13() + "'" +
            ", branchFreeField14='" + getBranchFreeField14() + "'" +
            ", branchFreeField15='" + getBranchFreeField15() + "'" +
            ", branchFreeField16='" + getBranchFreeField16() + "'" +
            ", branchFreeField17='" + getBranchFreeField17() + "'" +
            ", branchFreeField18='" + getBranchFreeField18() + "'" +
            ", branchFreeField19='" + getBranchFreeField19() + "'" +
            ", branchFreeField20='" + getBranchFreeField20() + "'" +
            ", branchFreeField21='" + getBranchFreeField21() + "'" +
            ", branchFreeField22='" + getBranchFreeField22() + "'" +
            ", branchFreeField23='" + getBranchFreeField23() + "'" +
            ", branchFreeField24='" + getBranchFreeField24() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
