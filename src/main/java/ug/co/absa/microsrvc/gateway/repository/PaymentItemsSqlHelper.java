package ug.co.absa.microsrvc.gateway.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class PaymentItemsSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("absa_tran_ref", table, columnPrefix + "_absa_tran_ref"));
        columns.add(Column.aliased("record_id", table, columnPrefix + "_record_id"));
        columns.add(Column.aliased("product_category_id", table, columnPrefix + "_product_category_id"));
        columns.add(Column.aliased("biller_id", table, columnPrefix + "_biller_id"));
        columns.add(Column.aliased("payment_item_code", table, columnPrefix + "_payment_item_code"));
        columns.add(Column.aliased("payment_item_id", table, columnPrefix + "_payment_item_id"));
        columns.add(Column.aliased("payment_item_name", table, columnPrefix + "_payment_item_name"));
        columns.add(Column.aliased("payment_item_description", table, columnPrefix + "_payment_item_description"));
        columns.add(Column.aliased("is_active", table, columnPrefix + "_is_active"));
        columns.add(Column.aliased("has_fixed_price", table, columnPrefix + "_has_fixed_price"));
        columns.add(Column.aliased("has_variable_price", table, columnPrefix + "_has_variable_price"));
        columns.add(Column.aliased("has_discount", table, columnPrefix + "_has_discount"));
        columns.add(Column.aliased("has_tax", table, columnPrefix + "_has_tax"));
        columns.add(Column.aliased("amount", table, columnPrefix + "_amount"));
        columns.add(Column.aliased("charge_amount", table, columnPrefix + "_charge_amount"));
        columns.add(Column.aliased("has_charge_amount", table, columnPrefix + "_has_charge_amount"));
        columns.add(Column.aliased("tax_amount", table, columnPrefix + "_tax_amount"));
        columns.add(Column.aliased("free_text", table, columnPrefix + "_free_text"));
        columns.add(Column.aliased("free_text_1", table, columnPrefix + "_free_text_1"));
        columns.add(Column.aliased("free_text_2", table, columnPrefix + "_free_text_2"));
        columns.add(Column.aliased("free_text_3", table, columnPrefix + "_free_text_3"));
        columns.add(Column.aliased("free_text_4", table, columnPrefix + "_free_text_4"));
        columns.add(Column.aliased("free_text_5", table, columnPrefix + "_free_text_5"));
        columns.add(Column.aliased("free_text_6", table, columnPrefix + "_free_text_6"));
        columns.add(Column.aliased("free_text_7", table, columnPrefix + "_free_text_7"));
        columns.add(Column.aliased("free_text_8", table, columnPrefix + "_free_text_8"));
        columns.add(Column.aliased("free_text_9", table, columnPrefix + "_free_text_9"));
        columns.add(Column.aliased("free_text_10", table, columnPrefix + "_free_text_10"));
        columns.add(Column.aliased("free_text_11", table, columnPrefix + "_free_text_11"));
        columns.add(Column.aliased("free_text_12", table, columnPrefix + "_free_text_12"));
        columns.add(Column.aliased("free_text_13", table, columnPrefix + "_free_text_13"));
        columns.add(Column.aliased("free_text_14", table, columnPrefix + "_free_text_14"));
        columns.add(Column.aliased("free_text_15", table, columnPrefix + "_free_text_15"));
        columns.add(Column.aliased("free_text_16", table, columnPrefix + "_free_text_16"));
        columns.add(Column.aliased("free_text_17", table, columnPrefix + "_free_text_17"));
        columns.add(Column.aliased("free_text_18", table, columnPrefix + "_free_text_18"));
        columns.add(Column.aliased("free_text_19", table, columnPrefix + "_free_text_19"));
        columns.add(Column.aliased("delflg", table, columnPrefix + "_delflg"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("created_at", table, columnPrefix + "_created_at"));
        columns.add(Column.aliased("updated_at", table, columnPrefix + "_updated_at"));
        columns.add(Column.aliased("deleted_at", table, columnPrefix + "_deleted_at"));
        columns.add(Column.aliased("deleted_by", table, columnPrefix + "_deleted_by"));

        columns.add(Column.aliased("biller_id", table, columnPrefix + "_biller_id"));
        return columns;
    }
}
