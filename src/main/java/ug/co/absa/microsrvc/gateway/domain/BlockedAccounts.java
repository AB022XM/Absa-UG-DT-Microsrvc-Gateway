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
 * A BlockedAccounts.
 */
@Table("blocked_accounts")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "blockedaccounts")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class BlockedAccounts implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("absa_tran_ref")
    private UUID absaTranRef;

    @NotNull(message = "must not be null")
    @Column("customer_id")
    private String customerId;

    @NotNull(message = "must not be null")
    @Column("customer_account_number")
    private String customerAccountNumber;

    @Column("dt_d_transaction_id")
    private String dtDTransactionId;

    @NotNull(message = "must not be null")
    @Column("block_id")
    private String blockId;

    @NotNull(message = "must not be null")
    @Column("block_code")
    private String blockCode;

    @NotNull(message = "must not be null")
    @Column("block_date")
    private ZonedDateTime blockDate;

    @NotNull(message = "must not be null")
    @Column("block_type")
    private String blockType;

    @NotNull(message = "must not be null")
    @Column("block_status")
    private String blockStatus;

    @NotNull(message = "must not be null")
    @Column("block_reason")
    private String blockReason;

    @NotNull(message = "must not be null")
    @Column("block_reason_code_1")
    private String blockReasonCode1;

    @NotNull(message = "must not be null")
    @Column("block_reason_code_2")
    private String blockReasonCode2;

    @NotNull(message = "must not be null")
    @Column("block_reason_1")
    private String blockReason1;

    @NotNull(message = "must not be null")
    @Column("block_reason_code_3")
    private String blockReasonCode3;

    @NotNull(message = "must not be null")
    @Column("block_reason_code_4")
    private String blockReasonCode4;

    @NotNull(message = "must not be null")
    @Column("start_date")
    private ZonedDateTime startDate;

    @NotNull(message = "must not be null")
    @Column("end_date")
    private ZonedDateTime endDate;

    @Column("is_temp")
    private Boolean isTemp;

    @Column("block_free_text")
    private String blockFreeText;

    @Column("block_free_text_1")
    private String blockFreeText1;

    @Column("block_free_text_2")
    private String blockFreeText2;

    @Column("block_free_text_3")
    private String blockFreeText3;

    @Column("block_free_text_4")
    private String blockFreeText4;

    @Column("block_free_text_5")
    private String blockFreeText5;

    @Column("block_free_text_6")
    private String blockFreeText6;

    @Column("block_reason_code_5")
    private String blockReasonCode5;

    @Column("block_reason_code_6")
    private String blockReasonCode6;

    @Column("block_reason_code_7")
    private String blockReasonCode7;

    @Column("block_reason_code_8")
    private String blockReasonCode8;

    @Column("block_reason_code_9")
    private String blockReasonCode9;

    @Column("block_reason_code_10")
    private String blockReasonCode10;

    @Column("block_reason_code_11")
    private String blockReasonCode11;

    @Column("block_reason_code_12")
    private String blockReasonCode12;

    @Column("block_reason_code_13")
    private String blockReasonCode13;

    @Column("block_reason_code_14")
    private String blockReasonCode14;

    @Column("block_reason_code_15")
    private String blockReasonCode15;

    @Column("block_reason_code_16")
    private String blockReasonCode16;

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

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public BlockedAccounts id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getAbsaTranRef() {
        return this.absaTranRef;
    }

    public BlockedAccounts absaTranRef(UUID absaTranRef) {
        this.setAbsaTranRef(absaTranRef);
        return this;
    }

    public void setAbsaTranRef(UUID absaTranRef) {
        this.absaTranRef = absaTranRef;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public BlockedAccounts customerId(String customerId) {
        this.setCustomerId(customerId);
        return this;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerAccountNumber() {
        return this.customerAccountNumber;
    }

    public BlockedAccounts customerAccountNumber(String customerAccountNumber) {
        this.setCustomerAccountNumber(customerAccountNumber);
        return this;
    }

    public void setCustomerAccountNumber(String customerAccountNumber) {
        this.customerAccountNumber = customerAccountNumber;
    }

    public String getDtDTransactionId() {
        return this.dtDTransactionId;
    }

    public BlockedAccounts dtDTransactionId(String dtDTransactionId) {
        this.setDtDTransactionId(dtDTransactionId);
        return this;
    }

    public void setDtDTransactionId(String dtDTransactionId) {
        this.dtDTransactionId = dtDTransactionId;
    }

    public String getBlockId() {
        return this.blockId;
    }

    public BlockedAccounts blockId(String blockId) {
        this.setBlockId(blockId);
        return this;
    }

    public void setBlockId(String blockId) {
        this.blockId = blockId;
    }

    public String getBlockCode() {
        return this.blockCode;
    }

    public BlockedAccounts blockCode(String blockCode) {
        this.setBlockCode(blockCode);
        return this;
    }

    public void setBlockCode(String blockCode) {
        this.blockCode = blockCode;
    }

    public ZonedDateTime getBlockDate() {
        return this.blockDate;
    }

    public BlockedAccounts blockDate(ZonedDateTime blockDate) {
        this.setBlockDate(blockDate);
        return this;
    }

    public void setBlockDate(ZonedDateTime blockDate) {
        this.blockDate = blockDate;
    }

    public String getBlockType() {
        return this.blockType;
    }

    public BlockedAccounts blockType(String blockType) {
        this.setBlockType(blockType);
        return this;
    }

    public void setBlockType(String blockType) {
        this.blockType = blockType;
    }

    public String getBlockStatus() {
        return this.blockStatus;
    }

    public BlockedAccounts blockStatus(String blockStatus) {
        this.setBlockStatus(blockStatus);
        return this;
    }

    public void setBlockStatus(String blockStatus) {
        this.blockStatus = blockStatus;
    }

    public String getBlockReason() {
        return this.blockReason;
    }

    public BlockedAccounts blockReason(String blockReason) {
        this.setBlockReason(blockReason);
        return this;
    }

    public void setBlockReason(String blockReason) {
        this.blockReason = blockReason;
    }

    public String getBlockReasonCode1() {
        return this.blockReasonCode1;
    }

    public BlockedAccounts blockReasonCode1(String blockReasonCode1) {
        this.setBlockReasonCode1(blockReasonCode1);
        return this;
    }

    public void setBlockReasonCode1(String blockReasonCode1) {
        this.blockReasonCode1 = blockReasonCode1;
    }

    public String getBlockReasonCode2() {
        return this.blockReasonCode2;
    }

    public BlockedAccounts blockReasonCode2(String blockReasonCode2) {
        this.setBlockReasonCode2(blockReasonCode2);
        return this;
    }

    public void setBlockReasonCode2(String blockReasonCode2) {
        this.blockReasonCode2 = blockReasonCode2;
    }

    public String getBlockReason1() {
        return this.blockReason1;
    }

    public BlockedAccounts blockReason1(String blockReason1) {
        this.setBlockReason1(blockReason1);
        return this;
    }

    public void setBlockReason1(String blockReason1) {
        this.blockReason1 = blockReason1;
    }

    public String getBlockReasonCode3() {
        return this.blockReasonCode3;
    }

    public BlockedAccounts blockReasonCode3(String blockReasonCode3) {
        this.setBlockReasonCode3(blockReasonCode3);
        return this;
    }

    public void setBlockReasonCode3(String blockReasonCode3) {
        this.blockReasonCode3 = blockReasonCode3;
    }

    public String getBlockReasonCode4() {
        return this.blockReasonCode4;
    }

    public BlockedAccounts blockReasonCode4(String blockReasonCode4) {
        this.setBlockReasonCode4(blockReasonCode4);
        return this;
    }

    public void setBlockReasonCode4(String blockReasonCode4) {
        this.blockReasonCode4 = blockReasonCode4;
    }

    public ZonedDateTime getStartDate() {
        return this.startDate;
    }

    public BlockedAccounts startDate(ZonedDateTime startDate) {
        this.setStartDate(startDate);
        return this;
    }

    public void setStartDate(ZonedDateTime startDate) {
        this.startDate = startDate;
    }

    public ZonedDateTime getEndDate() {
        return this.endDate;
    }

    public BlockedAccounts endDate(ZonedDateTime endDate) {
        this.setEndDate(endDate);
        return this;
    }

    public void setEndDate(ZonedDateTime endDate) {
        this.endDate = endDate;
    }

    public Boolean getIsTemp() {
        return this.isTemp;
    }

    public BlockedAccounts isTemp(Boolean isTemp) {
        this.setIsTemp(isTemp);
        return this;
    }

    public void setIsTemp(Boolean isTemp) {
        this.isTemp = isTemp;
    }

    public String getBlockFreeText() {
        return this.blockFreeText;
    }

    public BlockedAccounts blockFreeText(String blockFreeText) {
        this.setBlockFreeText(blockFreeText);
        return this;
    }

    public void setBlockFreeText(String blockFreeText) {
        this.blockFreeText = blockFreeText;
    }

    public String getBlockFreeText1() {
        return this.blockFreeText1;
    }

    public BlockedAccounts blockFreeText1(String blockFreeText1) {
        this.setBlockFreeText1(blockFreeText1);
        return this;
    }

    public void setBlockFreeText1(String blockFreeText1) {
        this.blockFreeText1 = blockFreeText1;
    }

    public String getBlockFreeText2() {
        return this.blockFreeText2;
    }

    public BlockedAccounts blockFreeText2(String blockFreeText2) {
        this.setBlockFreeText2(blockFreeText2);
        return this;
    }

    public void setBlockFreeText2(String blockFreeText2) {
        this.blockFreeText2 = blockFreeText2;
    }

    public String getBlockFreeText3() {
        return this.blockFreeText3;
    }

    public BlockedAccounts blockFreeText3(String blockFreeText3) {
        this.setBlockFreeText3(blockFreeText3);
        return this;
    }

    public void setBlockFreeText3(String blockFreeText3) {
        this.blockFreeText3 = blockFreeText3;
    }

    public String getBlockFreeText4() {
        return this.blockFreeText4;
    }

    public BlockedAccounts blockFreeText4(String blockFreeText4) {
        this.setBlockFreeText4(blockFreeText4);
        return this;
    }

    public void setBlockFreeText4(String blockFreeText4) {
        this.blockFreeText4 = blockFreeText4;
    }

    public String getBlockFreeText5() {
        return this.blockFreeText5;
    }

    public BlockedAccounts blockFreeText5(String blockFreeText5) {
        this.setBlockFreeText5(blockFreeText5);
        return this;
    }

    public void setBlockFreeText5(String blockFreeText5) {
        this.blockFreeText5 = blockFreeText5;
    }

    public String getBlockFreeText6() {
        return this.blockFreeText6;
    }

    public BlockedAccounts blockFreeText6(String blockFreeText6) {
        this.setBlockFreeText6(blockFreeText6);
        return this;
    }

    public void setBlockFreeText6(String blockFreeText6) {
        this.blockFreeText6 = blockFreeText6;
    }

    public String getBlockReasonCode5() {
        return this.blockReasonCode5;
    }

    public BlockedAccounts blockReasonCode5(String blockReasonCode5) {
        this.setBlockReasonCode5(blockReasonCode5);
        return this;
    }

    public void setBlockReasonCode5(String blockReasonCode5) {
        this.blockReasonCode5 = blockReasonCode5;
    }

    public String getBlockReasonCode6() {
        return this.blockReasonCode6;
    }

    public BlockedAccounts blockReasonCode6(String blockReasonCode6) {
        this.setBlockReasonCode6(blockReasonCode6);
        return this;
    }

    public void setBlockReasonCode6(String blockReasonCode6) {
        this.blockReasonCode6 = blockReasonCode6;
    }

    public String getBlockReasonCode7() {
        return this.blockReasonCode7;
    }

    public BlockedAccounts blockReasonCode7(String blockReasonCode7) {
        this.setBlockReasonCode7(blockReasonCode7);
        return this;
    }

    public void setBlockReasonCode7(String blockReasonCode7) {
        this.blockReasonCode7 = blockReasonCode7;
    }

    public String getBlockReasonCode8() {
        return this.blockReasonCode8;
    }

    public BlockedAccounts blockReasonCode8(String blockReasonCode8) {
        this.setBlockReasonCode8(blockReasonCode8);
        return this;
    }

    public void setBlockReasonCode8(String blockReasonCode8) {
        this.blockReasonCode8 = blockReasonCode8;
    }

    public String getBlockReasonCode9() {
        return this.blockReasonCode9;
    }

    public BlockedAccounts blockReasonCode9(String blockReasonCode9) {
        this.setBlockReasonCode9(blockReasonCode9);
        return this;
    }

    public void setBlockReasonCode9(String blockReasonCode9) {
        this.blockReasonCode9 = blockReasonCode9;
    }

    public String getBlockReasonCode10() {
        return this.blockReasonCode10;
    }

    public BlockedAccounts blockReasonCode10(String blockReasonCode10) {
        this.setBlockReasonCode10(blockReasonCode10);
        return this;
    }

    public void setBlockReasonCode10(String blockReasonCode10) {
        this.blockReasonCode10 = blockReasonCode10;
    }

    public String getBlockReasonCode11() {
        return this.blockReasonCode11;
    }

    public BlockedAccounts blockReasonCode11(String blockReasonCode11) {
        this.setBlockReasonCode11(blockReasonCode11);
        return this;
    }

    public void setBlockReasonCode11(String blockReasonCode11) {
        this.blockReasonCode11 = blockReasonCode11;
    }

    public String getBlockReasonCode12() {
        return this.blockReasonCode12;
    }

    public BlockedAccounts blockReasonCode12(String blockReasonCode12) {
        this.setBlockReasonCode12(blockReasonCode12);
        return this;
    }

    public void setBlockReasonCode12(String blockReasonCode12) {
        this.blockReasonCode12 = blockReasonCode12;
    }

    public String getBlockReasonCode13() {
        return this.blockReasonCode13;
    }

    public BlockedAccounts blockReasonCode13(String blockReasonCode13) {
        this.setBlockReasonCode13(blockReasonCode13);
        return this;
    }

    public void setBlockReasonCode13(String blockReasonCode13) {
        this.blockReasonCode13 = blockReasonCode13;
    }

    public String getBlockReasonCode14() {
        return this.blockReasonCode14;
    }

    public BlockedAccounts blockReasonCode14(String blockReasonCode14) {
        this.setBlockReasonCode14(blockReasonCode14);
        return this;
    }

    public void setBlockReasonCode14(String blockReasonCode14) {
        this.blockReasonCode14 = blockReasonCode14;
    }

    public String getBlockReasonCode15() {
        return this.blockReasonCode15;
    }

    public BlockedAccounts blockReasonCode15(String blockReasonCode15) {
        this.setBlockReasonCode15(blockReasonCode15);
        return this;
    }

    public void setBlockReasonCode15(String blockReasonCode15) {
        this.blockReasonCode15 = blockReasonCode15;
    }

    public String getBlockReasonCode16() {
        return this.blockReasonCode16;
    }

    public BlockedAccounts blockReasonCode16(String blockReasonCode16) {
        this.setBlockReasonCode16(blockReasonCode16);
        return this;
    }

    public void setBlockReasonCode16(String blockReasonCode16) {
        this.blockReasonCode16 = blockReasonCode16;
    }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public BlockedAccounts createdAt(ZonedDateTime createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public BlockedAccounts createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public BlockedAccounts updatedAt(ZonedDateTime updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public BlockedAccounts updatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Boolean getDelflg() {
        return this.delflg;
    }

    public BlockedAccounts delflg(Boolean delflg) {
        this.setDelflg(delflg);
        return this;
    }

    public void setDelflg(Boolean delflg) {
        this.delflg = delflg;
    }

    public RecordStatus getStatus() {
        return this.status;
    }

    public BlockedAccounts status(RecordStatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(RecordStatus status) {
        this.status = status;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BlockedAccounts)) {
            return false;
        }
        return id != null && id.equals(((BlockedAccounts) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "BlockedAccounts{" +
            "id=" + getId() +
            ", absaTranRef='" + getAbsaTranRef() + "'" +
            ", customerId='" + getCustomerId() + "'" +
            ", customerAccountNumber='" + getCustomerAccountNumber() + "'" +
            ", dtDTransactionId='" + getDtDTransactionId() + "'" +
            ", blockId='" + getBlockId() + "'" +
            ", blockCode='" + getBlockCode() + "'" +
            ", blockDate='" + getBlockDate() + "'" +
            ", blockType='" + getBlockType() + "'" +
            ", blockStatus='" + getBlockStatus() + "'" +
            ", blockReason='" + getBlockReason() + "'" +
            ", blockReasonCode1='" + getBlockReasonCode1() + "'" +
            ", blockReasonCode2='" + getBlockReasonCode2() + "'" +
            ", blockReason1='" + getBlockReason1() + "'" +
            ", blockReasonCode3='" + getBlockReasonCode3() + "'" +
            ", blockReasonCode4='" + getBlockReasonCode4() + "'" +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", isTemp='" + getIsTemp() + "'" +
            ", blockFreeText='" + getBlockFreeText() + "'" +
            ", blockFreeText1='" + getBlockFreeText1() + "'" +
            ", blockFreeText2='" + getBlockFreeText2() + "'" +
            ", blockFreeText3='" + getBlockFreeText3() + "'" +
            ", blockFreeText4='" + getBlockFreeText4() + "'" +
            ", blockFreeText5='" + getBlockFreeText5() + "'" +
            ", blockFreeText6='" + getBlockFreeText6() + "'" +
            ", blockReasonCode5='" + getBlockReasonCode5() + "'" +
            ", blockReasonCode6='" + getBlockReasonCode6() + "'" +
            ", blockReasonCode7='" + getBlockReasonCode7() + "'" +
            ", blockReasonCode8='" + getBlockReasonCode8() + "'" +
            ", blockReasonCode9='" + getBlockReasonCode9() + "'" +
            ", blockReasonCode10='" + getBlockReasonCode10() + "'" +
            ", blockReasonCode11='" + getBlockReasonCode11() + "'" +
            ", blockReasonCode12='" + getBlockReasonCode12() + "'" +
            ", blockReasonCode13='" + getBlockReasonCode13() + "'" +
            ", blockReasonCode14='" + getBlockReasonCode14() + "'" +
            ", blockReasonCode15='" + getBlockReasonCode15() + "'" +
            ", blockReasonCode16='" + getBlockReasonCode16() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", delflg='" + getDelflg() + "'" +
            ", status='" + getStatus() + "'" +
            "}";
    }
}
