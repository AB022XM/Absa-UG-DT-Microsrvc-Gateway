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

/**
 * A DTransactionChannel.
 */
@Table("d_transaction_channel")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "dtransactionchannel")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class DTransactionChannel implements Serializable {

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

    @Column("transaction_reference_number")
    private String transactionReferenceNumber;

    @Column("external_tranid")
    private String externalTranid;

    @NotNull(message = "must not be null")
    @Column("channel_code")
    private String channelCode;

    @NotNull(message = "must not be null")
    @Column("channel_name")
    private String channelName;

    @Column("channel_description")
    private String channelDescription;

    @Column("timestamp")
    private ZonedDateTime timestamp;

    @Column("chanel_free_text")
    private String chanelFreeText;

    @Column("chanel_free_text_1")
    private String chanelFreeText1;

    @Column("chanel_free_text_2")
    private String chanelFreeText2;

    @Column("chanel_free_text_3")
    private String chanelFreeText3;

    @Column("chanel_free_text_4")
    private String chanelFreeText4;

    @Column("chanel_free_text_5")
    private String chanelFreeText5;

    @Column("chanel_free_text_6")
    private String chanelFreeText6;

    @Column("chanel_free_text_7")
    private String chanelFreeText7;

    @Column("chanel_free_text_8")
    private String chanelFreeText8;

    @Column("chanel_free_text_9")
    private String chanelFreeText9;

    @Column("chanel_free_text_10")
    private String chanelFreeText10;

    @Column("chanel_free_text_11")
    private String chanelFreeText11;

    @Column("chanel_free_text_12")
    private String chanelFreeText12;

    @Column("chanel_free_text_13")
    private String chanelFreeText13;

    @Column("chanel_free_text_14")
    private String chanelFreeText14;

    @Column("chanel_free_text_15")
    private String chanelFreeText15;

    @Column("chanel_free_text_16")
    private String chanelFreeText16;

    @Column("chanel_free_text_17")
    private String chanelFreeText17;

    @Column("chanel_free_text_18")
    private String chanelFreeText18;

    @Column("chanel_free_text_19")
    private String chanelFreeText19;

    @Column("chanel_free_text_20")
    private String chanelFreeText20;

    @Column("chanel_free_text_21")
    private String chanelFreeText21;

    @Column("chanel_free_text_22")
    private String chanelFreeText22;

    @Column("chanel_free_text_23")
    private String chanelFreeText23;

    @Column("chanel_free_text_24")
    private String chanelFreeText24;

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

    @Transient
    @JsonIgnoreProperties(
        value = { "transaction", "transaction", "transaction", "transactions", "transactions", "customer" },
        allowSetters = true
    )
    private DTransaction dTransaction;

    @Column("d_transaction_id")
    private Long dTransactionId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public DTransactionChannel id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getAbsaTranRef() {
        return this.absaTranRef;
    }

    public DTransactionChannel absaTranRef(UUID absaTranRef) {
        this.setAbsaTranRef(absaTranRef);
        return this;
    }

    public void setAbsaTranRef(UUID absaTranRef) {
        this.absaTranRef = absaTranRef;
    }

    public String getBillerId() {
        return this.billerId;
    }

    public DTransactionChannel billerId(String billerId) {
        this.setBillerId(billerId);
        return this;
    }

    public void setBillerId(String billerId) {
        this.billerId = billerId;
    }

    public String getPaymentItemCode() {
        return this.paymentItemCode;
    }

    public DTransactionChannel paymentItemCode(String paymentItemCode) {
        this.setPaymentItemCode(paymentItemCode);
        return this;
    }

    public void setPaymentItemCode(String paymentItemCode) {
        this.paymentItemCode = paymentItemCode;
    }

    public String getDtDTransactionId() {
        return this.dtDTransactionId;
    }

    public DTransactionChannel dtDTransactionId(String dtDTransactionId) {
        this.setDtDTransactionId(dtDTransactionId);
        return this;
    }

    public void setDtDTransactionId(String dtDTransactionId) {
        this.dtDTransactionId = dtDTransactionId;
    }

    public String getTransactionReferenceNumber() {
        return this.transactionReferenceNumber;
    }

    public DTransactionChannel transactionReferenceNumber(String transactionReferenceNumber) {
        this.setTransactionReferenceNumber(transactionReferenceNumber);
        return this;
    }

    public void setTransactionReferenceNumber(String transactionReferenceNumber) {
        this.transactionReferenceNumber = transactionReferenceNumber;
    }

    public String getExternalTranid() {
        return this.externalTranid;
    }

    public DTransactionChannel externalTranid(String externalTranid) {
        this.setExternalTranid(externalTranid);
        return this;
    }

    public void setExternalTranid(String externalTranid) {
        this.externalTranid = externalTranid;
    }

    public String getChannelCode() {
        return this.channelCode;
    }

    public DTransactionChannel channelCode(String channelCode) {
        this.setChannelCode(channelCode);
        return this;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getChannelName() {
        return this.channelName;
    }

    public DTransactionChannel channelName(String channelName) {
        this.setChannelName(channelName);
        return this;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getChannelDescription() {
        return this.channelDescription;
    }

    public DTransactionChannel channelDescription(String channelDescription) {
        this.setChannelDescription(channelDescription);
        return this;
    }

    public void setChannelDescription(String channelDescription) {
        this.channelDescription = channelDescription;
    }

    public ZonedDateTime getTimestamp() {
        return this.timestamp;
    }

    public DTransactionChannel timestamp(ZonedDateTime timestamp) {
        this.setTimestamp(timestamp);
        return this;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getChanelFreeText() {
        return this.chanelFreeText;
    }

    public DTransactionChannel chanelFreeText(String chanelFreeText) {
        this.setChanelFreeText(chanelFreeText);
        return this;
    }

    public void setChanelFreeText(String chanelFreeText) {
        this.chanelFreeText = chanelFreeText;
    }

    public String getChanelFreeText1() {
        return this.chanelFreeText1;
    }

    public DTransactionChannel chanelFreeText1(String chanelFreeText1) {
        this.setChanelFreeText1(chanelFreeText1);
        return this;
    }

    public void setChanelFreeText1(String chanelFreeText1) {
        this.chanelFreeText1 = chanelFreeText1;
    }

    public String getChanelFreeText2() {
        return this.chanelFreeText2;
    }

    public DTransactionChannel chanelFreeText2(String chanelFreeText2) {
        this.setChanelFreeText2(chanelFreeText2);
        return this;
    }

    public void setChanelFreeText2(String chanelFreeText2) {
        this.chanelFreeText2 = chanelFreeText2;
    }

    public String getChanelFreeText3() {
        return this.chanelFreeText3;
    }

    public DTransactionChannel chanelFreeText3(String chanelFreeText3) {
        this.setChanelFreeText3(chanelFreeText3);
        return this;
    }

    public void setChanelFreeText3(String chanelFreeText3) {
        this.chanelFreeText3 = chanelFreeText3;
    }

    public String getChanelFreeText4() {
        return this.chanelFreeText4;
    }

    public DTransactionChannel chanelFreeText4(String chanelFreeText4) {
        this.setChanelFreeText4(chanelFreeText4);
        return this;
    }

    public void setChanelFreeText4(String chanelFreeText4) {
        this.chanelFreeText4 = chanelFreeText4;
    }

    public String getChanelFreeText5() {
        return this.chanelFreeText5;
    }

    public DTransactionChannel chanelFreeText5(String chanelFreeText5) {
        this.setChanelFreeText5(chanelFreeText5);
        return this;
    }

    public void setChanelFreeText5(String chanelFreeText5) {
        this.chanelFreeText5 = chanelFreeText5;
    }

    public String getChanelFreeText6() {
        return this.chanelFreeText6;
    }

    public DTransactionChannel chanelFreeText6(String chanelFreeText6) {
        this.setChanelFreeText6(chanelFreeText6);
        return this;
    }

    public void setChanelFreeText6(String chanelFreeText6) {
        this.chanelFreeText6 = chanelFreeText6;
    }

    public String getChanelFreeText7() {
        return this.chanelFreeText7;
    }

    public DTransactionChannel chanelFreeText7(String chanelFreeText7) {
        this.setChanelFreeText7(chanelFreeText7);
        return this;
    }

    public void setChanelFreeText7(String chanelFreeText7) {
        this.chanelFreeText7 = chanelFreeText7;
    }

    public String getChanelFreeText8() {
        return this.chanelFreeText8;
    }

    public DTransactionChannel chanelFreeText8(String chanelFreeText8) {
        this.setChanelFreeText8(chanelFreeText8);
        return this;
    }

    public void setChanelFreeText8(String chanelFreeText8) {
        this.chanelFreeText8 = chanelFreeText8;
    }

    public String getChanelFreeText9() {
        return this.chanelFreeText9;
    }

    public DTransactionChannel chanelFreeText9(String chanelFreeText9) {
        this.setChanelFreeText9(chanelFreeText9);
        return this;
    }

    public void setChanelFreeText9(String chanelFreeText9) {
        this.chanelFreeText9 = chanelFreeText9;
    }

    public String getChanelFreeText10() {
        return this.chanelFreeText10;
    }

    public DTransactionChannel chanelFreeText10(String chanelFreeText10) {
        this.setChanelFreeText10(chanelFreeText10);
        return this;
    }

    public void setChanelFreeText10(String chanelFreeText10) {
        this.chanelFreeText10 = chanelFreeText10;
    }

    public String getChanelFreeText11() {
        return this.chanelFreeText11;
    }

    public DTransactionChannel chanelFreeText11(String chanelFreeText11) {
        this.setChanelFreeText11(chanelFreeText11);
        return this;
    }

    public void setChanelFreeText11(String chanelFreeText11) {
        this.chanelFreeText11 = chanelFreeText11;
    }

    public String getChanelFreeText12() {
        return this.chanelFreeText12;
    }

    public DTransactionChannel chanelFreeText12(String chanelFreeText12) {
        this.setChanelFreeText12(chanelFreeText12);
        return this;
    }

    public void setChanelFreeText12(String chanelFreeText12) {
        this.chanelFreeText12 = chanelFreeText12;
    }

    public String getChanelFreeText13() {
        return this.chanelFreeText13;
    }

    public DTransactionChannel chanelFreeText13(String chanelFreeText13) {
        this.setChanelFreeText13(chanelFreeText13);
        return this;
    }

    public void setChanelFreeText13(String chanelFreeText13) {
        this.chanelFreeText13 = chanelFreeText13;
    }

    public String getChanelFreeText14() {
        return this.chanelFreeText14;
    }

    public DTransactionChannel chanelFreeText14(String chanelFreeText14) {
        this.setChanelFreeText14(chanelFreeText14);
        return this;
    }

    public void setChanelFreeText14(String chanelFreeText14) {
        this.chanelFreeText14 = chanelFreeText14;
    }

    public String getChanelFreeText15() {
        return this.chanelFreeText15;
    }

    public DTransactionChannel chanelFreeText15(String chanelFreeText15) {
        this.setChanelFreeText15(chanelFreeText15);
        return this;
    }

    public void setChanelFreeText15(String chanelFreeText15) {
        this.chanelFreeText15 = chanelFreeText15;
    }

    public String getChanelFreeText16() {
        return this.chanelFreeText16;
    }

    public DTransactionChannel chanelFreeText16(String chanelFreeText16) {
        this.setChanelFreeText16(chanelFreeText16);
        return this;
    }

    public void setChanelFreeText16(String chanelFreeText16) {
        this.chanelFreeText16 = chanelFreeText16;
    }

    public String getChanelFreeText17() {
        return this.chanelFreeText17;
    }

    public DTransactionChannel chanelFreeText17(String chanelFreeText17) {
        this.setChanelFreeText17(chanelFreeText17);
        return this;
    }

    public void setChanelFreeText17(String chanelFreeText17) {
        this.chanelFreeText17 = chanelFreeText17;
    }

    public String getChanelFreeText18() {
        return this.chanelFreeText18;
    }

    public DTransactionChannel chanelFreeText18(String chanelFreeText18) {
        this.setChanelFreeText18(chanelFreeText18);
        return this;
    }

    public void setChanelFreeText18(String chanelFreeText18) {
        this.chanelFreeText18 = chanelFreeText18;
    }

    public String getChanelFreeText19() {
        return this.chanelFreeText19;
    }

    public DTransactionChannel chanelFreeText19(String chanelFreeText19) {
        this.setChanelFreeText19(chanelFreeText19);
        return this;
    }

    public void setChanelFreeText19(String chanelFreeText19) {
        this.chanelFreeText19 = chanelFreeText19;
    }

    public String getChanelFreeText20() {
        return this.chanelFreeText20;
    }

    public DTransactionChannel chanelFreeText20(String chanelFreeText20) {
        this.setChanelFreeText20(chanelFreeText20);
        return this;
    }

    public void setChanelFreeText20(String chanelFreeText20) {
        this.chanelFreeText20 = chanelFreeText20;
    }

    public String getChanelFreeText21() {
        return this.chanelFreeText21;
    }

    public DTransactionChannel chanelFreeText21(String chanelFreeText21) {
        this.setChanelFreeText21(chanelFreeText21);
        return this;
    }

    public void setChanelFreeText21(String chanelFreeText21) {
        this.chanelFreeText21 = chanelFreeText21;
    }

    public String getChanelFreeText22() {
        return this.chanelFreeText22;
    }

    public DTransactionChannel chanelFreeText22(String chanelFreeText22) {
        this.setChanelFreeText22(chanelFreeText22);
        return this;
    }

    public void setChanelFreeText22(String chanelFreeText22) {
        this.chanelFreeText22 = chanelFreeText22;
    }

    public String getChanelFreeText23() {
        return this.chanelFreeText23;
    }

    public DTransactionChannel chanelFreeText23(String chanelFreeText23) {
        this.setChanelFreeText23(chanelFreeText23);
        return this;
    }

    public void setChanelFreeText23(String chanelFreeText23) {
        this.chanelFreeText23 = chanelFreeText23;
    }

    public String getChanelFreeText24() {
        return this.chanelFreeText24;
    }

    public DTransactionChannel chanelFreeText24(String chanelFreeText24) {
        this.setChanelFreeText24(chanelFreeText24);
        return this;
    }

    public void setChanelFreeText24(String chanelFreeText24) {
        this.chanelFreeText24 = chanelFreeText24;
    }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public DTransactionChannel createdAt(ZonedDateTime createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public DTransactionChannel createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public DTransactionChannel updatedAt(ZonedDateTime updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public DTransactionChannel updatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Boolean getDelflg() {
        return this.delflg;
    }

    public DTransactionChannel delflg(Boolean delflg) {
        this.setDelflg(delflg);
        return this;
    }

    public void setDelflg(Boolean delflg) {
        this.delflg = delflg;
    }

    public DTransaction getDTransaction() {
        return this.dTransaction;
    }

    public void setDTransaction(DTransaction dTransaction) {
        this.dTransaction = dTransaction;
        this.dTransactionId = dTransaction != null ? dTransaction.getId() : null;
    }

    public DTransactionChannel dTransaction(DTransaction dTransaction) {
        this.setDTransaction(dTransaction);
        return this;
    }

    public Long getDTransactionId() {
        return this.dTransactionId;
    }

    public void setDTransactionId(Long dTransaction) {
        this.dTransactionId = dTransaction;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DTransactionChannel)) {
            return false;
        }
        return id != null && id.equals(((DTransactionChannel) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "DTransactionChannel{" +
            "id=" + getId() +
            ", absaTranRef='" + getAbsaTranRef() + "'" +
            ", billerId='" + getBillerId() + "'" +
            ", paymentItemCode='" + getPaymentItemCode() + "'" +
            ", dtDTransactionId='" + getDtDTransactionId() + "'" +
            ", transactionReferenceNumber='" + getTransactionReferenceNumber() + "'" +
            ", externalTranid='" + getExternalTranid() + "'" +
            ", channelCode='" + getChannelCode() + "'" +
            ", channelName='" + getChannelName() + "'" +
            ", channelDescription='" + getChannelDescription() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            ", chanelFreeText='" + getChanelFreeText() + "'" +
            ", chanelFreeText1='" + getChanelFreeText1() + "'" +
            ", chanelFreeText2='" + getChanelFreeText2() + "'" +
            ", chanelFreeText3='" + getChanelFreeText3() + "'" +
            ", chanelFreeText4='" + getChanelFreeText4() + "'" +
            ", chanelFreeText5='" + getChanelFreeText5() + "'" +
            ", chanelFreeText6='" + getChanelFreeText6() + "'" +
            ", chanelFreeText7='" + getChanelFreeText7() + "'" +
            ", chanelFreeText8='" + getChanelFreeText8() + "'" +
            ", chanelFreeText9='" + getChanelFreeText9() + "'" +
            ", chanelFreeText10='" + getChanelFreeText10() + "'" +
            ", chanelFreeText11='" + getChanelFreeText11() + "'" +
            ", chanelFreeText12='" + getChanelFreeText12() + "'" +
            ", chanelFreeText13='" + getChanelFreeText13() + "'" +
            ", chanelFreeText14='" + getChanelFreeText14() + "'" +
            ", chanelFreeText15='" + getChanelFreeText15() + "'" +
            ", chanelFreeText16='" + getChanelFreeText16() + "'" +
            ", chanelFreeText17='" + getChanelFreeText17() + "'" +
            ", chanelFreeText18='" + getChanelFreeText18() + "'" +
            ", chanelFreeText19='" + getChanelFreeText19() + "'" +
            ", chanelFreeText20='" + getChanelFreeText20() + "'" +
            ", chanelFreeText21='" + getChanelFreeText21() + "'" +
            ", chanelFreeText22='" + getChanelFreeText22() + "'" +
            ", chanelFreeText23='" + getChanelFreeText23() + "'" +
            ", chanelFreeText24='" + getChanelFreeText24() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            ", delflg='" + getDelflg() + "'" +
            "}";
    }
}
