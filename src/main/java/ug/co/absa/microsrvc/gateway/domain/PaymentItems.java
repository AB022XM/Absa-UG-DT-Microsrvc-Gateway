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
 * A PaymentItems.
 */
@Table("payment_items")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "paymentitems")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class PaymentItems implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("absa_tran_ref")
    private UUID absaTranRef;

    @Column("record_id")
    private String recordId;

    @Column("product_category_id")
    private Integer productCategoryId;

    @Column("biller_id")
    private Integer billerId;

    @Column("payment_item_code")
    private String paymentItemCode;

    @Column("payment_item_id")
    private Integer paymentItemId;

    @Column("payment_item_name")
    private String paymentItemName;

    @Column("payment_item_description")
    private String paymentItemDescription;

    @Column("is_active")
    private Boolean isActive;

    @Column("has_fixed_price")
    private Boolean hasFixedPrice;

    @Column("has_variable_price")
    private Boolean hasVariablePrice;

    @Column("has_discount")
    private Boolean hasDiscount;

    @Column("has_tax")
    private Boolean hasTax;

    @Column("amount")
    private Double amount;

    @Column("charge_amount")
    private Double chargeAmount;

    @Column("has_charge_amount")
    private Boolean hasChargeAmount;

    @Column("tax_amount")
    private Double taxAmount;

    @Column("free_text")
    private String freeText;

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

    @Column("delflg")
    private Boolean delflg;

    @Column("status")
    private String status;

    @Column("created_at")
    private ZonedDateTime createdAt;

    @Column("updated_at")
    private ZonedDateTime updatedAt;

    @Column("deleted_at")
    private ZonedDateTime deletedAt;

    @Column("deleted_by")
    private String deletedBy;

    @Transient
    @JsonIgnoreProperties(value = { "billers", "billers", "billerAccount" }, allowSetters = true)
    private Biller biller;

    @Column("biller_id")
    private Long billerId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public PaymentItems id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getAbsaTranRef() {
        return this.absaTranRef;
    }

    public PaymentItems absaTranRef(UUID absaTranRef) {
        this.setAbsaTranRef(absaTranRef);
        return this;
    }

    public void setAbsaTranRef(UUID absaTranRef) {
        this.absaTranRef = absaTranRef;
    }

    public String getRecordId() {
        return this.recordId;
    }

    public PaymentItems recordId(String recordId) {
        this.setRecordId(recordId);
        return this;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public Integer getProductCategoryId() {
        return this.productCategoryId;
    }

    public PaymentItems productCategoryId(Integer productCategoryId) {
        this.setProductCategoryId(productCategoryId);
        return this;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Integer getBillerId() {
        return this.billerId;
    }

    public PaymentItems billerId(Integer billerId) {
        this.setBillerId(billerId);
        return this;
    }

    public void setBillerId(Integer billerId) {
        this.billerId = billerId;
    }

    public String getPaymentItemCode() {
        return this.paymentItemCode;
    }

    public PaymentItems paymentItemCode(String paymentItemCode) {
        this.setPaymentItemCode(paymentItemCode);
        return this;
    }

    public void setPaymentItemCode(String paymentItemCode) {
        this.paymentItemCode = paymentItemCode;
    }

    public Integer getPaymentItemId() {
        return this.paymentItemId;
    }

    public PaymentItems paymentItemId(Integer paymentItemId) {
        this.setPaymentItemId(paymentItemId);
        return this;
    }

    public void setPaymentItemId(Integer paymentItemId) {
        this.paymentItemId = paymentItemId;
    }

    public String getPaymentItemName() {
        return this.paymentItemName;
    }

    public PaymentItems paymentItemName(String paymentItemName) {
        this.setPaymentItemName(paymentItemName);
        return this;
    }

    public void setPaymentItemName(String paymentItemName) {
        this.paymentItemName = paymentItemName;
    }

    public String getPaymentItemDescription() {
        return this.paymentItemDescription;
    }

    public PaymentItems paymentItemDescription(String paymentItemDescription) {
        this.setPaymentItemDescription(paymentItemDescription);
        return this;
    }

    public void setPaymentItemDescription(String paymentItemDescription) {
        this.paymentItemDescription = paymentItemDescription;
    }

    public Boolean getIsActive() {
        return this.isActive;
    }

    public PaymentItems isActive(Boolean isActive) {
        this.setIsActive(isActive);
        return this;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public Boolean getHasFixedPrice() {
        return this.hasFixedPrice;
    }

    public PaymentItems hasFixedPrice(Boolean hasFixedPrice) {
        this.setHasFixedPrice(hasFixedPrice);
        return this;
    }

    public void setHasFixedPrice(Boolean hasFixedPrice) {
        this.hasFixedPrice = hasFixedPrice;
    }

    public Boolean getHasVariablePrice() {
        return this.hasVariablePrice;
    }

    public PaymentItems hasVariablePrice(Boolean hasVariablePrice) {
        this.setHasVariablePrice(hasVariablePrice);
        return this;
    }

    public void setHasVariablePrice(Boolean hasVariablePrice) {
        this.hasVariablePrice = hasVariablePrice;
    }

    public Boolean getHasDiscount() {
        return this.hasDiscount;
    }

    public PaymentItems hasDiscount(Boolean hasDiscount) {
        this.setHasDiscount(hasDiscount);
        return this;
    }

    public void setHasDiscount(Boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    public Boolean getHasTax() {
        return this.hasTax;
    }

    public PaymentItems hasTax(Boolean hasTax) {
        this.setHasTax(hasTax);
        return this;
    }

    public void setHasTax(Boolean hasTax) {
        this.hasTax = hasTax;
    }

    public Double getAmount() {
        return this.amount;
    }

    public PaymentItems amount(Double amount) {
        this.setAmount(amount);
        return this;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public Double getChargeAmount() {
        return this.chargeAmount;
    }

    public PaymentItems chargeAmount(Double chargeAmount) {
        this.setChargeAmount(chargeAmount);
        return this;
    }

    public void setChargeAmount(Double chargeAmount) {
        this.chargeAmount = chargeAmount;
    }

    public Boolean getHasChargeAmount() {
        return this.hasChargeAmount;
    }

    public PaymentItems hasChargeAmount(Boolean hasChargeAmount) {
        this.setHasChargeAmount(hasChargeAmount);
        return this;
    }

    public void setHasChargeAmount(Boolean hasChargeAmount) {
        this.hasChargeAmount = hasChargeAmount;
    }

    public Double getTaxAmount() {
        return this.taxAmount;
    }

    public PaymentItems taxAmount(Double taxAmount) {
        this.setTaxAmount(taxAmount);
        return this;
    }

    public void setTaxAmount(Double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getFreeText() {
        return this.freeText;
    }

    public PaymentItems freeText(String freeText) {
        this.setFreeText(freeText);
        return this;
    }

    public void setFreeText(String freeText) {
        this.freeText = freeText;
    }

    public String getFreeText1() {
        return this.freeText1;
    }

    public PaymentItems freeText1(String freeText1) {
        this.setFreeText1(freeText1);
        return this;
    }

    public void setFreeText1(String freeText1) {
        this.freeText1 = freeText1;
    }

    public String getFreeText2() {
        return this.freeText2;
    }

    public PaymentItems freeText2(String freeText2) {
        this.setFreeText2(freeText2);
        return this;
    }

    public void setFreeText2(String freeText2) {
        this.freeText2 = freeText2;
    }

    public String getFreeText3() {
        return this.freeText3;
    }

    public PaymentItems freeText3(String freeText3) {
        this.setFreeText3(freeText3);
        return this;
    }

    public void setFreeText3(String freeText3) {
        this.freeText3 = freeText3;
    }

    public String getFreeText4() {
        return this.freeText4;
    }

    public PaymentItems freeText4(String freeText4) {
        this.setFreeText4(freeText4);
        return this;
    }

    public void setFreeText4(String freeText4) {
        this.freeText4 = freeText4;
    }

    public String getFreeText5() {
        return this.freeText5;
    }

    public PaymentItems freeText5(String freeText5) {
        this.setFreeText5(freeText5);
        return this;
    }

    public void setFreeText5(String freeText5) {
        this.freeText5 = freeText5;
    }

    public String getFreeText6() {
        return this.freeText6;
    }

    public PaymentItems freeText6(String freeText6) {
        this.setFreeText6(freeText6);
        return this;
    }

    public void setFreeText6(String freeText6) {
        this.freeText6 = freeText6;
    }

    public String getFreeText7() {
        return this.freeText7;
    }

    public PaymentItems freeText7(String freeText7) {
        this.setFreeText7(freeText7);
        return this;
    }

    public void setFreeText7(String freeText7) {
        this.freeText7 = freeText7;
    }

    public String getFreeText8() {
        return this.freeText8;
    }

    public PaymentItems freeText8(String freeText8) {
        this.setFreeText8(freeText8);
        return this;
    }

    public void setFreeText8(String freeText8) {
        this.freeText8 = freeText8;
    }

    public String getFreeText9() {
        return this.freeText9;
    }

    public PaymentItems freeText9(String freeText9) {
        this.setFreeText9(freeText9);
        return this;
    }

    public void setFreeText9(String freeText9) {
        this.freeText9 = freeText9;
    }

    public String getFreeText10() {
        return this.freeText10;
    }

    public PaymentItems freeText10(String freeText10) {
        this.setFreeText10(freeText10);
        return this;
    }

    public void setFreeText10(String freeText10) {
        this.freeText10 = freeText10;
    }

    public String getFreeText11() {
        return this.freeText11;
    }

    public PaymentItems freeText11(String freeText11) {
        this.setFreeText11(freeText11);
        return this;
    }

    public void setFreeText11(String freeText11) {
        this.freeText11 = freeText11;
    }

    public String getFreeText12() {
        return this.freeText12;
    }

    public PaymentItems freeText12(String freeText12) {
        this.setFreeText12(freeText12);
        return this;
    }

    public void setFreeText12(String freeText12) {
        this.freeText12 = freeText12;
    }

    public String getFreeText13() {
        return this.freeText13;
    }

    public PaymentItems freeText13(String freeText13) {
        this.setFreeText13(freeText13);
        return this;
    }

    public void setFreeText13(String freeText13) {
        this.freeText13 = freeText13;
    }

    public String getFreeText14() {
        return this.freeText14;
    }

    public PaymentItems freeText14(String freeText14) {
        this.setFreeText14(freeText14);
        return this;
    }

    public void setFreeText14(String freeText14) {
        this.freeText14 = freeText14;
    }

    public String getFreeText15() {
        return this.freeText15;
    }

    public PaymentItems freeText15(String freeText15) {
        this.setFreeText15(freeText15);
        return this;
    }

    public void setFreeText15(String freeText15) {
        this.freeText15 = freeText15;
    }

    public String getFreeText16() {
        return this.freeText16;
    }

    public PaymentItems freeText16(String freeText16) {
        this.setFreeText16(freeText16);
        return this;
    }

    public void setFreeText16(String freeText16) {
        this.freeText16 = freeText16;
    }

    public String getFreeText17() {
        return this.freeText17;
    }

    public PaymentItems freeText17(String freeText17) {
        this.setFreeText17(freeText17);
        return this;
    }

    public void setFreeText17(String freeText17) {
        this.freeText17 = freeText17;
    }

    public String getFreeText18() {
        return this.freeText18;
    }

    public PaymentItems freeText18(String freeText18) {
        this.setFreeText18(freeText18);
        return this;
    }

    public void setFreeText18(String freeText18) {
        this.freeText18 = freeText18;
    }

    public String getFreeText19() {
        return this.freeText19;
    }

    public PaymentItems freeText19(String freeText19) {
        this.setFreeText19(freeText19);
        return this;
    }

    public void setFreeText19(String freeText19) {
        this.freeText19 = freeText19;
    }

    public Boolean getDelflg() {
        return this.delflg;
    }

    public PaymentItems delflg(Boolean delflg) {
        this.setDelflg(delflg);
        return this;
    }

    public void setDelflg(Boolean delflg) {
        this.delflg = delflg;
    }

    public String getStatus() {
        return this.status;
    }

    public PaymentItems status(String status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public PaymentItems createdAt(ZonedDateTime createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public ZonedDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public PaymentItems updatedAt(ZonedDateTime updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public ZonedDateTime getDeletedAt() {
        return this.deletedAt;
    }

    public PaymentItems deletedAt(ZonedDateTime deletedAt) {
        this.setDeletedAt(deletedAt);
        return this;
    }

    public void setDeletedAt(ZonedDateTime deletedAt) {
        this.deletedAt = deletedAt;
    }

    public String getDeletedBy() {
        return this.deletedBy;
    }

    public PaymentItems deletedBy(String deletedBy) {
        this.setDeletedBy(deletedBy);
        return this;
    }

    public void setDeletedBy(String deletedBy) {
        this.deletedBy = deletedBy;
    }

    public Biller getBiller() {
        return this.biller;
    }

    public void setBiller(Biller biller) {
        this.biller = biller;
        this.billerId = biller != null ? biller.getId() : null;
    }

    public PaymentItems biller(Biller biller) {
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
        if (!(o instanceof PaymentItems)) {
            return false;
        }
        return id != null && id.equals(((PaymentItems) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "PaymentItems{" +
            "id=" + getId() +
            ", absaTranRef='" + getAbsaTranRef() + "'" +
            ", recordId='" + getRecordId() + "'" +
            ", productCategoryId=" + getProductCategoryId() +
            ", billerId=" + getBillerId() +
            ", paymentItemCode='" + getPaymentItemCode() + "'" +
            ", paymentItemId=" + getPaymentItemId() +
            ", paymentItemName='" + getPaymentItemName() + "'" +
            ", paymentItemDescription='" + getPaymentItemDescription() + "'" +
            ", isActive='" + getIsActive() + "'" +
            ", hasFixedPrice='" + getHasFixedPrice() + "'" +
            ", hasVariablePrice='" + getHasVariablePrice() + "'" +
            ", hasDiscount='" + getHasDiscount() + "'" +
            ", hasTax='" + getHasTax() + "'" +
            ", amount=" + getAmount() +
            ", chargeAmount=" + getChargeAmount() +
            ", hasChargeAmount='" + getHasChargeAmount() + "'" +
            ", taxAmount=" + getTaxAmount() +
            ", freeText='" + getFreeText() + "'" +
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
            ", delflg='" + getDelflg() + "'" +
            ", status='" + getStatus() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", deletedAt='" + getDeletedAt() + "'" +
            ", deletedBy='" + getDeletedBy() + "'" +
            "}";
    }
}
