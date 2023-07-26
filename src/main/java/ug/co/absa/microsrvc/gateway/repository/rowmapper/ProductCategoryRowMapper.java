package ug.co.absa.microsrvc.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;
import ug.co.absa.microsrvc.gateway.domain.ProductCategory;

/**
 * Converter between {@link Row} to {@link ProductCategory}, with proper type conversions.
 */
@Service
public class ProductCategoryRowMapper implements BiFunction<Row, String, ProductCategory> {

    private final ColumnConverter converter;

    public ProductCategoryRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link ProductCategory} stored in the database.
     */
    @Override
    public ProductCategory apply(Row row, String prefix) {
        ProductCategory entity = new ProductCategory();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setAbsaTranRef(converter.fromRow(row, prefix + "_absa_tran_ref", UUID.class));
        entity.setBillerId(converter.fromRow(row, prefix + "_biller_id", String.class));
        entity.setRecordId(converter.fromRow(row, prefix + "_record_id", String.class));
        entity.setProductCategoryCode(converter.fromRow(row, prefix + "_product_category_code", String.class));
        entity.setProductCategoryName(converter.fromRow(row, prefix + "_product_category_name", String.class));
        entity.setProductCategoryDescription(converter.fromRow(row, prefix + "_product_category_description", String.class));
        entity.setProductCategoryImage(converter.fromRow(row, prefix + "_product_category_image", String.class));
        entity.setProductFreeField1(converter.fromRow(row, prefix + "_product_free_field_1", String.class));
        entity.setProductFreeField2(converter.fromRow(row, prefix + "_product_free_field_2", String.class));
        entity.setProductFreeField3(converter.fromRow(row, prefix + "_product_free_field_3", String.class));
        entity.setProductFreeField4(converter.fromRow(row, prefix + "_product_free_field_4", String.class));
        entity.setProductFreeField5(converter.fromRow(row, prefix + "_product_free_field_5", String.class));
        entity.setProductFreeField6(converter.fromRow(row, prefix + "_product_free_field_6", String.class));
        entity.setProductFreeField7(converter.fromRow(row, prefix + "_product_free_field_7", String.class));
        entity.setProductFreeField8(converter.fromRow(row, prefix + "_product_free_field_8", String.class));
        entity.setProductFreeField9(converter.fromRow(row, prefix + "_product_free_field_9", String.class));
        entity.setProductFreeField10(converter.fromRow(row, prefix + "_product_free_field_10", String.class));
        entity.setProductFreeField11(converter.fromRow(row, prefix + "_product_free_field_11", String.class));
        entity.setDelflg(converter.fromRow(row, prefix + "_delflg", Boolean.class));
        entity.setCreatedAt(converter.fromRow(row, prefix + "_created_at", ZonedDateTime.class));
        entity.setCreatedBy(converter.fromRow(row, prefix + "_created_by", String.class));
        entity.setUpdatedAt(converter.fromRow(row, prefix + "_updated_at", ZonedDateTime.class));
        entity.setUpdatedBy(converter.fromRow(row, prefix + "_updated_by", String.class));
        return entity;
    }
}
