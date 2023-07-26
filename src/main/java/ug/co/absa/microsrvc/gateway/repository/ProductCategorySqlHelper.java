package ug.co.absa.microsrvc.gateway.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class ProductCategorySqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("absa_tran_ref", table, columnPrefix + "_absa_tran_ref"));
        columns.add(Column.aliased("biller_id", table, columnPrefix + "_biller_id"));
        columns.add(Column.aliased("record_id", table, columnPrefix + "_record_id"));
        columns.add(Column.aliased("product_category_code", table, columnPrefix + "_product_category_code"));
        columns.add(Column.aliased("product_category_name", table, columnPrefix + "_product_category_name"));
        columns.add(Column.aliased("product_category_description", table, columnPrefix + "_product_category_description"));
        columns.add(Column.aliased("product_category_image", table, columnPrefix + "_product_category_image"));
        columns.add(Column.aliased("product_free_field_1", table, columnPrefix + "_product_free_field_1"));
        columns.add(Column.aliased("product_free_field_2", table, columnPrefix + "_product_free_field_2"));
        columns.add(Column.aliased("product_free_field_3", table, columnPrefix + "_product_free_field_3"));
        columns.add(Column.aliased("product_free_field_4", table, columnPrefix + "_product_free_field_4"));
        columns.add(Column.aliased("product_free_field_5", table, columnPrefix + "_product_free_field_5"));
        columns.add(Column.aliased("product_free_field_6", table, columnPrefix + "_product_free_field_6"));
        columns.add(Column.aliased("product_free_field_7", table, columnPrefix + "_product_free_field_7"));
        columns.add(Column.aliased("product_free_field_8", table, columnPrefix + "_product_free_field_8"));
        columns.add(Column.aliased("product_free_field_9", table, columnPrefix + "_product_free_field_9"));
        columns.add(Column.aliased("product_free_field_10", table, columnPrefix + "_product_free_field_10"));
        columns.add(Column.aliased("product_free_field_11", table, columnPrefix + "_product_free_field_11"));
        columns.add(Column.aliased("delflg", table, columnPrefix + "_delflg"));
        columns.add(Column.aliased("created_at", table, columnPrefix + "_created_at"));
        columns.add(Column.aliased("created_by", table, columnPrefix + "_created_by"));
        columns.add(Column.aliased("updated_at", table, columnPrefix + "_updated_at"));
        columns.add(Column.aliased("updated_by", table, columnPrefix + "_updated_by"));

        return columns;
    }
}
