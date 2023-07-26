package ug.co.absa.microsrvc.gateway.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import ug.co.absa.microsrvc.gateway.domain.enumeration.ServiceLevel;

/**
 * A Liquidation.
 */
@Table("liquidation")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "liquidation")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Liquidation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("record_id")
    private String recordId;

    @NotNull(message = "must not be null")
    @Column("service_level")
    private ServiceLevel serviceLevel;

    @NotNull(message = "must not be null")
    @Column("timestamp")
    private ZonedDateTime timestamp;

    @NotNull(message = "must not be null")
    @Column("partner_code")
    private String partnerCode;

    @NotNull(message = "must not be null")
    @Column("amount")
    private String amount;

    @NotNull(message = "must not be null")
    @Column("currency")
    private String currency;

    @NotNull(message = "must not be null")
    @Column("receiver_bankcode")
    private String receiverBankcode;

    @NotNull(message = "must not be null")
    @Column("receiver_account_number")
    private String receiverAccountNumber;

    @NotNull(message = "must not be null")
    @Column("beneficiary_name")
    private String beneficiaryName;

    @NotNull(message = "must not be null")
    @Column("instruction_id")
    private String instructionId;

    @NotNull(message = "must not be null")
    @Column("sender_to_receiver_info")
    private String senderToReceiverInfo;

    @NotNull(message = "must not be null")
    @Column("free_text_1")
    private String freeText1;

    @NotNull(message = "must not be null")
    @Column("free_text_2")
    private String freeText2;

    @NotNull(message = "must not be null")
    @Column("free_text_3")
    private String freeText3;

    @NotNull(message = "must not be null")
    @Column("free_text_4")
    private String freeText4;

    @NotNull(message = "must not be null")
    @Column("free_text_5")
    private String freeText5;

    @NotNull(message = "must not be null")
    @Column("free_text_6")
    private String freeText6;

    @NotNull(message = "must not be null")
    @Column("free_text_7")
    private String freeText7;

    @NotNull(message = "must not be null")
    @Column("free_text_8")
    private String freeText8;

    @NotNull(message = "must not be null")
    @Column("free_text_9")
    private String freeText9;

    @NotNull(message = "must not be null")
    @Column("free_text_10")
    private String freeText10;

    @NotNull(message = "must not be null")
    @Column("free_text_11")
    private String freeText11;

    @NotNull(message = "must not be null")
    @Column("free_text_12")
    private String freeText12;

    @NotNull(message = "must not be null")
    @Column("free_text_13")
    private String freeText13;

    @NotNull(message = "must not be null")
    @Column("free_text_14")
    private String freeText14;

    @NotNull(message = "must not be null")
    @Column("free_text_15")
    private String freeText15;

    @NotNull(message = "must not be null")
    @Column("free_text_16")
    private String freeText16;

    @NotNull(message = "must not be null")
    @Column("free_text_17")
    private String freeText17;

    @NotNull(message = "must not be null")
    @Column("free_text_18")
    private String freeText18;

    @NotNull(message = "must not be null")
    @Column("free_text_19")
    private String freeText19;

    @NotNull(message = "must not be null")
    @Column("free_text_20")
    private String freeText20;

    @NotNull(message = "must not be null")
    @Column("free_text_21")
    private String freeText21;

    @NotNull(message = "must not be null")
    @Column("free_text_22")
    private String freeText22;

    @NotNull(message = "must not be null")
    @Column("free_text_23")
    private String freeText23;

    @NotNull(message = "must not be null")
    @Column("free_text_24")
    private String freeText24;

    @NotNull(message = "must not be null")
    @Column("free_text_25")
    private String freeText25;

    @NotNull(message = "must not be null")
    @Column("free_text_26")
    private String freeText26;

    @NotNull(message = "must not be null")
    @Column("free_text_27")
    private String freeText27;

    @NotNull(message = "must not be null")
    @Column("free_text_28")
    private String freeText28;

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

    public Liquidation id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRecordId() {
        return this.recordId;
    }

    public Liquidation recordId(String recordId) {
        this.setRecordId(recordId);
        return this;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public ServiceLevel getServiceLevel() {
        return this.serviceLevel;
    }

    public Liquidation serviceLevel(ServiceLevel serviceLevel) {
        this.setServiceLevel(serviceLevel);
        return this;
    }

    public void setServiceLevel(ServiceLevel serviceLevel) {
        this.serviceLevel = serviceLevel;
    }

    public ZonedDateTime getTimestamp() {
        return this.timestamp;
    }

    public Liquidation timestamp(ZonedDateTime timestamp) {
        this.setTimestamp(timestamp);
        return this;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getPartnerCode() {
        return this.partnerCode;
    }

    public Liquidation partnerCode(String partnerCode) {
        this.setPartnerCode(partnerCode);
        return this;
    }

    public void setPartnerCode(String partnerCode) {
        this.partnerCode = partnerCode;
    }

    public String getAmount() {
        return this.amount;
    }

    public Liquidation amount(String amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return this.currency;
    }

    public Liquidation currency(String currency) {
        this.setCurrency(currency);
        return this;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getReceiverBankcode() {
        return this.receiverBankcode;
    }

    public Liquidation receiverBankcode(String receiverBankcode) {
        this.setReceiverBankcode(receiverBankcode);
        return this;
    }

    public void setReceiverBankcode(String receiverBankcode) {
        this.receiverBankcode = receiverBankcode;
    }

    public String getReceiverAccountNumber() {
        return this.receiverAccountNumber;
    }

    public Liquidation receiverAccountNumber(String receiverAccountNumber) {
        this.setReceiverAccountNumber(receiverAccountNumber);
        return this;
    }

    public void setReceiverAccountNumber(String receiverAccountNumber) {
        this.receiverAccountNumber = receiverAccountNumber;
    }

    public String getBeneficiaryName() {
        return this.beneficiaryName;
    }

    public Liquidation beneficiaryName(String beneficiaryName) {
        this.setBeneficiaryName(beneficiaryName);
        return this;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public String getInstructionId() {
        return this.instructionId;
    }

    public Liquidation instructionId(String instructionId) {
        this.setInstructionId(instructionId);
        return this;
    }

    public void setInstructionId(String instructionId) {
        this.instructionId = instructionId;
    }

    public String getSenderToReceiverInfo() {
        return this.senderToReceiverInfo;
    }

    public Liquidation senderToReceiverInfo(String senderToReceiverInfo) {
        this.setSenderToReceiverInfo(senderToReceiverInfo);
        return this;
    }

    public void setSenderToReceiverInfo(String senderToReceiverInfo) {
        this.senderToReceiverInfo = senderToReceiverInfo;
    }

    public String getFreeText1() {
        return this.freeText1;
    }

    public Liquidation freeText1(String freeText1) {
        this.setFreeText1(freeText1);
        return this;
    }

    public void setFreeText1(String freeText1) {
        this.freeText1 = freeText1;
    }

    public String getFreeText2() {
        return this.freeText2;
    }

    public Liquidation freeText2(String freeText2) {
        this.setFreeText2(freeText2);
        return this;
    }

    public void setFreeText2(String freeText2) {
        this.freeText2 = freeText2;
    }

    public String getFreeText3() {
        return this.freeText3;
    }

    public Liquidation freeText3(String freeText3) {
        this.setFreeText3(freeText3);
        return this;
    }

    public void setFreeText3(String freeText3) {
        this.freeText3 = freeText3;
    }

    public String getFreeText4() {
        return this.freeText4;
    }

    public Liquidation freeText4(String freeText4) {
        this.setFreeText4(freeText4);
        return this;
    }

    public void setFreeText4(String freeText4) {
        this.freeText4 = freeText4;
    }

    public String getFreeText5() {
        return this.freeText5;
    }

    public Liquidation freeText5(String freeText5) {
        this.setFreeText5(freeText5);
        return this;
    }

    public void setFreeText5(String freeText5) {
        this.freeText5 = freeText5;
    }

    public String getFreeText6() {
        return this.freeText6;
    }

    public Liquidation freeText6(String freeText6) {
        this.setFreeText6(freeText6);
        return this;
    }

    public void setFreeText6(String freeText6) {
        this.freeText6 = freeText6;
    }

    public String getFreeText7() {
        return this.freeText7;
    }

    public Liquidation freeText7(String freeText7) {
        this.setFreeText7(freeText7);
        return this;
    }

    public void setFreeText7(String freeText7) {
        this.freeText7 = freeText7;
    }

    public String getFreeText8() {
        return this.freeText8;
    }

    public Liquidation freeText8(String freeText8) {
        this.setFreeText8(freeText8);
        return this;
    }

    public void setFreeText8(String freeText8) {
        this.freeText8 = freeText8;
    }

    public String getFreeText9() {
        return this.freeText9;
    }

    public Liquidation freeText9(String freeText9) {
        this.setFreeText9(freeText9);
        return this;
    }

    public void setFreeText9(String freeText9) {
        this.freeText9 = freeText9;
    }

    public String getFreeText10() {
        return this.freeText10;
    }

    public Liquidation freeText10(String freeText10) {
        this.setFreeText10(freeText10);
        return this;
    }

    public void setFreeText10(String freeText10) {
        this.freeText10 = freeText10;
    }

    public String getFreeText11() {
        return this.freeText11;
    }

    public Liquidation freeText11(String freeText11) {
        this.setFreeText11(freeText11);
        return this;
    }

    public void setFreeText11(String freeText11) {
        this.freeText11 = freeText11;
    }

    public String getFreeText12() {
        return this.freeText12;
    }

    public Liquidation freeText12(String freeText12) {
        this.setFreeText12(freeText12);
        return this;
    }

    public void setFreeText12(String freeText12) {
        this.freeText12 = freeText12;
    }

    public String getFreeText13() {
        return this.freeText13;
    }

    public Liquidation freeText13(String freeText13) {
        this.setFreeText13(freeText13);
        return this;
    }

    public void setFreeText13(String freeText13) {
        this.freeText13 = freeText13;
    }

    public String getFreeText14() {
        return this.freeText14;
    }

    public Liquidation freeText14(String freeText14) {
        this.setFreeText14(freeText14);
        return this;
    }

    public void setFreeText14(String freeText14) {
        this.freeText14 = freeText14;
    }

    public String getFreeText15() {
        return this.freeText15;
    }

    public Liquidation freeText15(String freeText15) {
        this.setFreeText15(freeText15);
        return this;
    }

    public void setFreeText15(String freeText15) {
        this.freeText15 = freeText15;
    }

    public String getFreeText16() {
        return this.freeText16;
    }

    public Liquidation freeText16(String freeText16) {
        this.setFreeText16(freeText16);
        return this;
    }

    public void setFreeText16(String freeText16) {
        this.freeText16 = freeText16;
    }

    public String getFreeText17() {
        return this.freeText17;
    }

    public Liquidation freeText17(String freeText17) {
        this.setFreeText17(freeText17);
        return this;
    }

    public void setFreeText17(String freeText17) {
        this.freeText17 = freeText17;
    }

    public String getFreeText18() {
        return this.freeText18;
    }

    public Liquidation freeText18(String freeText18) {
        this.setFreeText18(freeText18);
        return this;
    }

    public void setFreeText18(String freeText18) {
        this.freeText18 = freeText18;
    }

    public String getFreeText19() {
        return this.freeText19;
    }

    public Liquidation freeText19(String freeText19) {
        this.setFreeText19(freeText19);
        return this;
    }

    public void setFreeText19(String freeText19) {
        this.freeText19 = freeText19;
    }

    public String getFreeText20() {
        return this.freeText20;
    }

    public Liquidation freeText20(String freeText20) {
        this.setFreeText20(freeText20);
        return this;
    }

    public void setFreeText20(String freeText20) {
        this.freeText20 = freeText20;
    }

    public String getFreeText21() {
        return this.freeText21;
    }

    public Liquidation freeText21(String freeText21) {
        this.setFreeText21(freeText21);
        return this;
    }

    public void setFreeText21(String freeText21) {
        this.freeText21 = freeText21;
    }

    public String getFreeText22() {
        return this.freeText22;
    }

    public Liquidation freeText22(String freeText22) {
        this.setFreeText22(freeText22);
        return this;
    }

    public void setFreeText22(String freeText22) {
        this.freeText22 = freeText22;
    }

    public String getFreeText23() {
        return this.freeText23;
    }

    public Liquidation freeText23(String freeText23) {
        this.setFreeText23(freeText23);
        return this;
    }

    public void setFreeText23(String freeText23) {
        this.freeText23 = freeText23;
    }

    public String getFreeText24() {
        return this.freeText24;
    }

    public Liquidation freeText24(String freeText24) {
        this.setFreeText24(freeText24);
        return this;
    }

    public void setFreeText24(String freeText24) {
        this.freeText24 = freeText24;
    }

    public String getFreeText25() {
        return this.freeText25;
    }

    public Liquidation freeText25(String freeText25) {
        this.setFreeText25(freeText25);
        return this;
    }

    public void setFreeText25(String freeText25) {
        this.freeText25 = freeText25;
    }

    public String getFreeText26() {
        return this.freeText26;
    }

    public Liquidation freeText26(String freeText26) {
        this.setFreeText26(freeText26);
        return this;
    }

    public void setFreeText26(String freeText26) {
        this.freeText26 = freeText26;
    }

    public String getFreeText27() {
        return this.freeText27;
    }

    public Liquidation freeText27(String freeText27) {
        this.setFreeText27(freeText27);
        return this;
    }

    public void setFreeText27(String freeText27) {
        this.freeText27 = freeText27;
    }

    public String getFreeText28() {
        return this.freeText28;
    }

    public Liquidation freeText28(String freeText28) {
        this.setFreeText28(freeText28);
        return this;
    }

    public void setFreeText28(String freeText28) {
        this.freeText28 = freeText28;
    }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public Liquidation createdAt(ZonedDateTime createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Liquidation createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public Liquidation updatedAt(ZonedDateTime updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public Liquidation updatedBy(String updatedBy) {
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
        if (!(o instanceof Liquidation)) {
            return false;
        }
        return id != null && id.equals(((Liquidation) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Liquidation{" +
            "id=" + getId() +
            ", recordId='" + getRecordId() + "'" +
            ", serviceLevel='" + getServiceLevel() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            ", partnerCode='" + getPartnerCode() + "'" +
            ", amount='" + getAmount() + "'" +
            ", currency='" + getCurrency() + "'" +
            ", receiverBankcode='" + getReceiverBankcode() + "'" +
            ", receiverAccountNumber='" + getReceiverAccountNumber() + "'" +
            ", beneficiaryName='" + getBeneficiaryName() + "'" +
            ", instructionId='" + getInstructionId() + "'" +
            ", senderToReceiverInfo='" + getSenderToReceiverInfo() + "'" +
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
