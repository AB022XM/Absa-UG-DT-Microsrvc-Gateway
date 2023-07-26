package ug.co.absa.microsrvc.gateway.domain;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.UUID;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A ProductCategory.
 */
@Table("product_category")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "productcategory")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class ProductCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("absa_tran_ref")
    private UUID absaTranRef;

    @Column("biller_id")
    private String billerId;

    @Column("record_id")
    private String recordId;

    @NotNull(message = "must not be null")
    @Column("product_category_code")
    private String productCategoryCode;

    @NotNull(message = "must not be null")
    @Column("product_category_name")
    private String productCategoryName;

    @Column("product_category_description")
    private String productCategoryDescription;

    @Column("product_category_image")
    private String productCategoryImage;

    @Column("product_free_field_1")
    private String productFreeField1;

    @Column("product_free_field_2")
    private String productFreeField2;

    @Column("product_free_field_3")
    private String productFreeField3;

    @Column("product_free_field_4")
    private String productFreeField4;

    @Column("product_free_field_5")
    private String productFreeField5;

    @Column("product_free_field_6")
    private String productFreeField6;

    @Column("product_free_field_7")
    private String productFreeField7;

    @Column("product_free_field_8")
    private String productFreeField8;

    @Column("product_free_field_9")
    private String productFreeField9;

    @Column("product_free_field_10")
    private String productFreeField10;

    @Column("product_free_field_11")
    private String productFreeField11;

    @Column("delflg")
    private Boolean delflg;

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

    public ProductCategory id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getAbsaTranRef() {
        return this.absaTranRef;
    }

    public ProductCategory absaTranRef(UUID absaTranRef) {
        this.setAbsaTranRef(absaTranRef);
        return this;
    }

    public void setAbsaTranRef(UUID absaTranRef) {
        this.absaTranRef = absaTranRef;
    }

    public String getBillerId() {
        return this.billerId;
    }

    public ProductCategory billerId(String billerId) {
        this.setBillerId(billerId);
        return this;
    }

    public void setBillerId(String billerId) {
        this.billerId = billerId;
    }

    public String getRecordId() {
        return this.recordId;
    }

    public ProductCategory recordId(String recordId) {
        this.setRecordId(recordId);
        return this;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getProductCategoryCode() {
        return this.productCategoryCode;
    }

    public ProductCategory productCategoryCode(String productCategoryCode) {
        this.setProductCategoryCode(productCategoryCode);
        return this;
    }

    public void setProductCategoryCode(String productCategoryCode) {
        this.productCategoryCode = productCategoryCode;
    }

    public String getProductCategoryName() {
        return this.productCategoryName;
    }

    public ProductCategory productCategoryName(String productCategoryName) {
        this.setProductCategoryName(productCategoryName);
        return this;
    }

    public void setProductCategoryName(String productCategoryName) {
        this.productCategoryName = productCategoryName;
    }

    public String getProductCategoryDescription() {
        return this.productCategoryDescription;
    }

    public ProductCategory productCategoryDescription(String productCategoryDescription) {
        this.setProductCategoryDescription(productCategoryDescription);
        return this;
    }

    public void setProductCategoryDescription(String productCategoryDescription) {
        this.productCategoryDescription = productCategoryDescription;
    }

    public String getProductCategoryImage() {
        return this.productCategoryImage;
    }

    public ProductCategory productCategoryImage(String productCategoryImage) {
        this.setProductCategoryImage(productCategoryImage);
        return this;
    }

    public void setProductCategoryImage(String productCategoryImage) {
        this.productCategoryImage = productCategoryImage;
    }

    public String getProductFreeField1() {
        return this.productFreeField1;
    }

    public ProductCategory productFreeField1(String productFreeField1) {
        this.setProductFreeField1(productFreeField1);
        return this;
    }

    public void setProductFreeField1(String productFreeField1) {
        this.productFreeField1 = productFreeField1;
    }

    public String getProductFreeField2() {
        return this.productFreeField2;
    }

    public ProductCategory productFreeField2(String productFreeField2) {
        this.setProductFreeField2(productFreeField2);
        return this;
    }

    public void setProductFreeField2(String productFreeField2) {
        this.productFreeField2 = productFreeField2;
    }

    public String getProductFreeField3() {
        return this.productFreeField3;
    }

    public ProductCategory productFreeField3(String productFreeField3) {
        this.setProductFreeField3(productFreeField3);
        return this;
    }

    public void setProductFreeField3(String productFreeField3) {
        this.productFreeField3 = productFreeField3;
    }

    public String getProductFreeField4() {
        return this.productFreeField4;
    }

    public ProductCategory productFreeField4(String productFreeField4) {
        this.setProductFreeField4(productFreeField4);
        return this;
    }

    public void setProductFreeField4(String productFreeField4) {
        this.productFreeField4 = productFreeField4;
    }

    public String getProductFreeField5() {
        return this.productFreeField5;
    }

    public ProductCategory productFreeField5(String productFreeField5) {
        this.setProductFreeField5(productFreeField5);
        return this;
    }

    public void setProductFreeField5(String productFreeField5) {
        this.productFreeField5 = productFreeField5;
    }

    public String getProductFreeField6() {
        return this.productFreeField6;
    }

    public ProductCategory productFreeField6(String productFreeField6) {
        this.setProductFreeField6(productFreeField6);
        return this;
    }

    public void setProductFreeField6(String productFreeField6) {
        this.productFreeField6 = productFreeField6;
    }

    public String getProductFreeField7() {
        return this.productFreeField7;
    }

    public ProductCategory productFreeField7(String productFreeField7) {
        this.setProductFreeField7(productFreeField7);
        return this;
    }

    public void setProductFreeField7(String productFreeField7) {
        this.productFreeField7 = productFreeField7;
    }

    public String getProductFreeField8() {
        return this.productFreeField8;
    }

    public ProductCategory productFreeField8(String productFreeField8) {
        this.setProductFreeField8(productFreeField8);
        return this;
    }

    public void setProductFreeField8(String productFreeField8) {
        this.productFreeField8 = productFreeField8;
    }

    public String getProductFreeField9() {
        return this.productFreeField9;
    }

    public ProductCategory productFreeField9(String productFreeField9) {
        this.setProductFreeField9(productFreeField9);
        return this;
    }

    public void setProductFreeField9(String productFreeField9) {
        this.productFreeField9 = productFreeField9;
    }

    public String getProductFreeField10() {
        return this.productFreeField10;
    }

    public ProductCategory productFreeField10(String productFreeField10) {
        this.setProductFreeField10(productFreeField10);
        return this;
    }

    public void setProductFreeField10(String productFreeField10) {
        this.productFreeField10 = productFreeField10;
    }

    public String getProductFreeField11() {
        return this.productFreeField11;
    }

    public ProductCategory productFreeField11(String productFreeField11) {
        this.setProductFreeField11(productFreeField11);
        return this;
    }

    public void setProductFreeField11(String productFreeField11) {
        this.productFreeField11 = productFreeField11;
    }

    public Boolean getDelflg() {
        return this.delflg;
    }

    public ProductCategory delflg(Boolean delflg) {
        this.setDelflg(delflg);
        return this;
    }

    public void setDelflg(Boolean delflg) {
        this.delflg = delflg;
    }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public ProductCategory createdAt(ZonedDateTime createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public ProductCategory createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public ProductCategory updatedAt(ZonedDateTime updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public ProductCategory updatedBy(String updatedBy) {
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
        if (!(o instanceof ProductCategory)) {
            return false;
        }
        return id != null && id.equals(((ProductCategory) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "ProductCategory{" +
            "id=" + getId() +
            ", absaTranRef='" + getAbsaTranRef() + "'" +
            ", billerId='" + getBillerId() + "'" +
            ", recordId='" + getRecordId() + "'" +
            ", productCategoryCode='" + getProductCategoryCode() + "'" +
            ", productCategoryName='" + getProductCategoryName() + "'" +
            ", productCategoryDescription='" + getProductCategoryDescription() + "'" +
            ", productCategoryImage='" + getProductCategoryImage() + "'" +
            ", productFreeField1='" + getProductFreeField1() + "'" +
            ", productFreeField2='" + getProductFreeField2() + "'" +
            ", productFreeField3='" + getProductFreeField3() + "'" +
            ", productFreeField4='" + getProductFreeField4() + "'" +
            ", productFreeField5='" + getProductFreeField5() + "'" +
            ", productFreeField6='" + getProductFreeField6() + "'" +
            ", productFreeField7='" + getProductFreeField7() + "'" +
            ", productFreeField8='" + getProductFreeField8() + "'" +
            ", productFreeField9='" + getProductFreeField9() + "'" +
            ", productFreeField10='" + getProductFreeField10() + "'" +
            ", productFreeField11='" + getProductFreeField11() + "'" +
            ", delflg='" + getDelflg() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            "}";
    }
}
