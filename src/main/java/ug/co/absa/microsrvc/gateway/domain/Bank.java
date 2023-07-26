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
import ug.co.absa.microsrvc.gateway.domain.enumeration.RecordStatus;

/**
 * A Bank.
 */
@Table("bank")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "bank")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Bank implements Serializable {

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
    @Column("payment_item_code")
    private String paymentItemCode;

    @Column("dt_d_transaction_id")
    private String dtDTransactionId;

    @Column("amol_d_transaction_id")
    private String amolDTransactionId;

    @NotNull(message = "must not be null")
    @Column("bank_name")
    private String bankName;

    @NotNull(message = "must not be null")
    @Column("bank_swift_code")
    private String bankSwiftCode;

    @Column("bank_branch_id")
    private String bankBranchId;

    @Column("bank_phone_number")
    private String bankPhoneNumber;

    @Column("bank_email")
    private String bankEmail;

    @Column("bank_free_field_1")
    private String bankFreeField1;

    @Column("bank_free_field_3")
    private String bankFreeField3;

    @Column("bank_free_field_4")
    private String bankFreeField4;

    @Column("bank_free_field_5")
    private String bankFreeField5;

    @Column("bank_free_field_6")
    private String bankFreeField6;

    @Column("bank_free_field_7")
    private String bankFreeField7;

    @Column("bank_free_field_8")
    private String bankFreeField8;

    @Column("bank_free_field_9")
    private String bankFreeField9;

    @Column("bank_free_field_10")
    private String bankFreeField10;

    @Column("bank_free_field_11")
    private String bankFreeField11;

    @Column("bank_free_field_12")
    private String bankFreeField12;

    @Column("bank_free_field_13")
    private String bankFreeField13;

    @Column("bank_free_field_14")
    private String bankFreeField14;

    @Column("bank_free_field_15")
    private String bankFreeField15;

    @Column("bank_free_field_16")
    private String bankFreeField16;

    @Column("bank_free_field_17")
    private String bankFreeField17;

    @Column("bank_free_field_18")
    private String bankFreeField18;

    @Column("bank_free_field_19")
    private String bankFreeField19;

    @Column("bank_free_field_20")
    private String bankFreeField20;

    @Column("bank_free_field_21")
    private String bankFreeField21;

    @Column("bank_free_field_22")
    private String bankFreeField22;

    @Column("bank_free_field_23")
    private String bankFreeField23;

    @Column("bank_free_field_24")
    private String bankFreeField24;

    @NotNull(message = "must not be null")
    @Column("created_at")
    private ZonedDateTime createdAt;

    @Column("created_by")
    private String createdBy;

    @Column("updated_at")
    private ZonedDateTime updatedAt;

    @Column("updated_by")
    private String updatedBy;

    @Column("delflg")
    private Boolean delflg;

    @Column("status")
    private RecordStatus status;

    @Transient
    @JsonIgnoreProperties(value = { "bank" }, allowSetters = true)
    private Set<Branch> banks = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Bank id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getAbsaTranRef() {
        return this.absaTranRef;
    }

    public Bank absaTranRef(UUID absaTranRef) {
        this.setAbsaTranRef(absaTranRef);
        return this;
    }

    public void setAbsaTranRef(UUID absaTranRef) {
        this.absaTranRef = absaTranRef;
    }

    public String getBillerId() {
        return this.billerId;
    }

    public Bank billerId(String billerId) {
        this.setBillerId(billerId);
        return this;
    }

    public void setBillerId(String billerId) {
        this.billerId = billerId;
    }

    public String getPaymentItemCode() {
        return this.paymentItemCode;
    }

    public Bank paymentItemCode(String paymentItemCode) {
        this.setPaymentItemCode(paymentItemCode);
        return this;
    }

    public void setPaymentItemCode(String paymentItemCode) {
        this.paymentItemCode = paymentItemCode;
    }

    public String getDtDTransactionId() {
        return this.dtDTransactionId;
    }

    public Bank dtDTransactionId(String dtDTransactionId) {
        this.setDtDTransactionId(dtDTransactionId);
        return this;
    }

    public void setDtDTransactionId(String dtDTransactionId) {
        this.dtDTransactionId = dtDTransactionId;
    }

    public String getAmolDTransactionId() {
        return this.amolDTransactionId;
    }

    public Bank amolDTransactionId(String amolDTransactionId) {
        this.setAmolDTransactionId(amolDTransactionId);
        return this;
    }

    public void setAmolDTransactionId(String amolDTransactionId) {
        this.amolDTransactionId = amolDTransactionId;
    }

    public String getBankName() {
        return this.bankName;
    }

    public Bank bankName(String bankName) {
        this.setBankName(bankName);
        return this;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankSwiftCode() {
        return this.bankSwiftCode;
    }

    public Bank bankSwiftCode(String bankSwiftCode) {
        this.setBankSwiftCode(bankSwiftCode);
        return this;
    }

    public void setBankSwiftCode(String bankSwiftCode) {
        this.bankSwiftCode = bankSwiftCode;
    }

    public String getBankBranchId() {
        return this.bankBranchId;
    }

    public Bank bankBranchId(String bankBranchId) {
        this.setBankBranchId(bankBranchId);
        return this;
    }

    public void setBankBranchId(String bankBranchId) {
        this.bankBranchId = bankBranchId;
    }

    public String getBankPhoneNumber() {
        return this.bankPhoneNumber;
    }

    public Bank bankPhoneNumber(String bankPhoneNumber) {
        this.setBankPhoneNumber(bankPhoneNumber);
        return this;
    }

    public void setBankPhoneNumber(String bankPhoneNumber) {
        this.bankPhoneNumber = bankPhoneNumber;
    }

    public String getBankEmail() {
        return this.bankEmail;
    }

    public Bank bankEmail(String bankEmail) {
        this.setBankEmail(bankEmail);
        return this;
    }

    public void setBankEmail(String bankEmail) {
        this.bankEmail = bankEmail;
    }

    public String getBankFreeField1() {
        return this.bankFreeField1;
    }

    public Bank bankFreeField1(String bankFreeField1) {
        this.setBankFreeField1(bankFreeField1);
        return this;
    }

    public void setBankFreeField1(String bankFreeField1) {
        this.bankFreeField1 = bankFreeField1;
    }

    public String getBankFreeField3() {
        return this.bankFreeField3;
    }

    public Bank bankFreeField3(String bankFreeField3) {
        this.setBankFreeField3(bankFreeField3);
        return this;
    }

    public void setBankFreeField3(String bankFreeField3) {
        this.bankFreeField3 = bankFreeField3;
    }

    public String getBankFreeField4() {
        return this.bankFreeField4;
    }

    public Bank bankFreeField4(String bankFreeField4) {
        this.setBankFreeField4(bankFreeField4);
        return this;
    }

    public void setBankFreeField4(String bankFreeField4) {
        this.bankFreeField4 = bankFreeField4;
    }

    public String getBankFreeField5() {
        return this.bankFreeField5;
    }

    public Bank bankFreeField5(String bankFreeField5) {
        this.setBankFreeField5(bankFreeField5);
        return this;
    }

    public void setBankFreeField5(String bankFreeField5) {
        this.bankFreeField5 = bankFreeField5;
    }

    public String getBankFreeField6() {
        return this.bankFreeField6;
    }

    public Bank bankFreeField6(String bankFreeField6) {
        this.setBankFreeField6(bankFreeField6);
        return this;
    }

    public void setBankFreeField6(String bankFreeField6) {
        this.bankFreeField6 = bankFreeField6;
    }

    public String getBankFreeField7() {
        return this.bankFreeField7;
    }

    public Bank bankFreeField7(String bankFreeField7) {
        this.setBankFreeField7(bankFreeField7);
        return this;
    }

    public void setBankFreeField7(String bankFreeField7) {
        this.bankFreeField7 = bankFreeField7;
    }

    public String getBankFreeField8() {
        return this.bankFreeField8;
    }

    public Bank bankFreeField8(String bankFreeField8) {
        this.setBankFreeField8(bankFreeField8);
        return this;
    }

    public void setBankFreeField8(String bankFreeField8) {
        this.bankFreeField8 = bankFreeField8;
    }

    public String getBankFreeField9() {
        return this.bankFreeField9;
    }

    public Bank bankFreeField9(String bankFreeField9) {
        this.setBankFreeField9(bankFreeField9);
        return this;
    }

    public void setBankFreeField9(String bankFreeField9) {
        this.bankFreeField9 = bankFreeField9;
    }

    public String getBankFreeField10() {
        return this.bankFreeField10;
    }

    public Bank bankFreeField10(String bankFreeField10) {
        this.setBankFreeField10(bankFreeField10);
        return this;
    }

    public void setBankFreeField10(String bankFreeField10) {
        this.bankFreeField10 = bankFreeField10;
    }

    public String getBankFreeField11() {
        return this.bankFreeField11;
    }

    public Bank bankFreeField11(String bankFreeField11) {
        this.setBankFreeField11(bankFreeField11);
        return this;
    }

    public void setBankFreeField11(String bankFreeField11) {
        this.bankFreeField11 = bankFreeField11;
    }

    public String getBankFreeField12() {
        return this.bankFreeField12;
    }

    public Bank bankFreeField12(String bankFreeField12) {
        this.setBankFreeField12(bankFreeField12);
        return this;
    }

    public void setBankFreeField12(String bankFreeField12) {
        this.bankFreeField12 = bankFreeField12;
    }

    public String getBankFreeField13() {
        return this.bankFreeField13;
    }

    public Bank bankFreeField13(String bankFreeField13) {
        this.setBankFreeField13(bankFreeField13);
        return this;
    }

    public void setBankFreeField13(String bankFreeField13) {
        this.bankFreeField13 = bankFreeField13;
    }

    public String getBankFreeField14() {
        return this.bankFreeField14;
    }

    public Bank bankFreeField14(String bankFreeField14) {
        this.setBankFreeField14(bankFreeField14);
        return this;
    }

    public void setBankFreeField14(String bankFreeField14) {
        this.bankFreeField14 = bankFreeField14;
    }

    public String getBankFreeField15() {
        return this.bankFreeField15;
    }

    public Bank bankFreeField15(String bankFreeField15) {
        this.setBankFreeField15(bankFreeField15);
        return this;
    }

    public void setBankFreeField15(String bankFreeField15) {
        this.bankFreeField15 = bankFreeField15;
    }

    public String getBankFreeField16() {
        return this.bankFreeField16;
    }

    public Bank bankFreeField16(String bankFreeField16) {
        this.setBankFreeField16(bankFreeField16);
        return this;
    }

    public void setBankFreeField16(String bankFreeField16) {
        this.bankFreeField16 = bankFreeField16;
    }

    public String getBankFreeField17() {
        return this.bankFreeField17;
    }

    public Bank bankFreeField17(String bankFreeField17) {
        this.setBankFreeField17(bankFreeField17);
        return this;
    }

    public void setBankFreeField17(String bankFreeField17) {
        this.bankFreeField17 = bankFreeField17;
    }

    public String getBankFreeField18() {
        return this.bankFreeField18;
    }

    public Bank bankFreeField18(String bankFreeField18) {
        this.setBankFreeField18(bankFreeField18);
        return this;
    }

    public void setBankFreeField18(String bankFreeField18) {
        this.bankFreeField18 = bankFreeField18;
    }

    public String getBankFreeField19() {
        return this.bankFreeField19;
    }

    public Bank bankFreeField19(String bankFreeField19) {
        this.setBankFreeField19(bankFreeField19);
        return this;
    }

    public void setBankFreeField19(String bankFreeField19) {
        this.bankFreeField19 = bankFreeField19;
    }

    public String getBankFreeField20() {
        return this.bankFreeField20;
    }

    public Bank bankFreeField20(String bankFreeField20) {
        this.setBankFreeField20(bankFreeField20);
        return this;
    }

    public void setBankFreeField20(String bankFreeField20) {
        this.bankFreeField20 = bankFreeField20;
    }

    public String getBankFreeField21() {
        return this.bankFreeField21;
    }

    public Bank bankFreeField21(String bankFreeField21) {
        this.setBankFreeField21(bankFreeField21);
        return this;
    }

    public void setBankFreeField21(String bankFreeField21) {
        this.bankFreeField21 = bankFreeField21;
    }

    public String getBankFreeField22() {
        return this.bankFreeField22;
    }

    public Bank bankFreeField22(String bankFreeField22) {
        this.setBankFreeField22(bankFreeField22);
        return this;
    }

    public void setBankFreeField22(String bankFreeField22) {
        this.bankFreeField22 = bankFreeField22;
    }

    public String getBankFreeField23() {
        return this.bankFreeField23;
    }

    public Bank bankFreeField23(String bankFreeField23) {
        this.setBankFreeField23(bankFreeField23);
        return this;
    }

    public void setBankFreeField23(String bankFreeField23) {
        this.bankFreeField23 = bankFreeField23;
    }

    public String getBankFreeField24() {
        return this.bankFreeField24;
    }

    public Bank bankFreeField24(String bankFreeField24) {
        this.setBankFreeField24(bankFreeField24);
        return this;
    }

    public void setBankFreeField24(String bankFreeField24) {
        this.bankFreeField24 = bankFreeField24;
    }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public Bank createdAt(ZonedDateTime createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Bank createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public Bank updatedAt(ZonedDateTime updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public Bank updatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Boolean getDelflg() {
        return this.delflg;
    }

    public Bank delflg(Boolean delflg) {
        this.setDelflg(delflg);
        return this;
    }

    public void setDelflg(Boolean delflg) {
        this.delflg = delflg;
    }

    public RecordStatus getStatus() {
        return this.status;
    }

    public Bank status(RecordStatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(RecordStatus status) {
        this.status = status;
    }

    public Set<Branch> getBanks() {
        return this.banks;
    }

    public void setBanks(Set<Branch> branches) {
        if (this.banks != null) {
            this.banks.forEach(i -> i.setBank(null));
        }
        if (branches != null) {
            branches.forEach(i -> i.setBank(this));
        }
        this.banks = branches;
    }

    public Bank banks(Set<Branch> branches) {
        this.setBanks(branches);
        return this;
    }

    public Bank addBank(Branch branch) {
        this.banks.add(branch);
        branch.setBank(this);
        return this;
    }

    public Bank removeBank(Branch branch) {
        this.banks.remove(branch);
        branch.setBank(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Bank)) {
            return false;
        }
        return id != null && id.equals(((Bank) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Bank{" +
            "id=" + getId() +
            ", absaTranRef='" + getAbsaTranRef() + "'" +
            ", billerId='" + getBillerId() + "'" +
            ", paymentItemCode='" + getPaymentItemCode() + "'" +
            ", dtDTransactionId='" + getDtDTransactionId() + "'" +
            ", amolDTransactionId='" + getAmolDTransactionId() + "'" +
            ", bankName='" + getBankName() + "'" +
            ", bankSwiftCode='" + getBankSwiftCode() + "'" +
            ", bankBranchId='" + getBankBranchId() + "'" +
            ", bankPhoneNumber='" + getBankPhoneNumber() + "'" +
            ", bankEmail='" + getBankEmail() + "'" +
            ", bankFreeField1='" + getBankFreeField1() + "'" +
            ", bankFreeField3='" + getBankFreeField3() + "'" +
            ", bankFreeField4='" + getBankFreeField4() + "'" +
            ", bankFreeField5='" + getBankFreeField5() + "'" +
            ", bankFreeField6='" + getBankFreeField6() + "'" +
            ", bankFreeField7='" + getBankFreeField7() + "'" +
            ", bankFreeField8='" + getBankFreeField8() + "'" +
            ", bankFreeField9='" + getBankFreeField9() + "'" +
            ", bankFreeField10='" + getBankFreeField10() + "'" +
            ", bankFreeField11='" + getBankFreeField11() + "'" +
            ", bankFreeField12='" + getBankFreeField12() + "'" +
            ", bankFreeField13='" + getBankFreeField13() + "'" +
            ", bankFreeField14='" + getBankFreeField14() + "'" +
            ", bankFreeField15='" + getBankFreeField15() + "'" +
            ", bankFreeField16='" + getBankFreeField16() + "'" +
            ", bankFreeField17='" + getBankFreeField17() + "'" +
            ", bankFreeField18='" + getBankFreeField18() + "'" +
            ", bankFreeField19='" + getBankFreeField19() + "'" +
            ", bankFreeField20='" + getBankFreeField20() + "'" +
            ", bankFreeField21='" + getBankFreeField21() + "'" +
            ", bankFreeField22='" + getBankFreeField22() + "'" +
            ", bankFreeField23='" + getBankFreeField23() + "'" +
            ", bankFreeField24='" + getBankFreeField24() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", delflg='" + getDelflg() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
