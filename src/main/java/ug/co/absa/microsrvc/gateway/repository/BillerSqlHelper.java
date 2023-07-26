package ug.co.absa.microsrvc.gateway.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class BillerSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("absa_tran_ref", table, columnPrefix + "_absa_tran_ref"));
        columns.add(Column.aliased("biller_id", table, columnPrefix + "_biller_id"));
        columns.add(Column.aliased("biller_code", table, columnPrefix + "_biller_code"));
        columns.add(Column.aliased("biller_name", table, columnPrefix + "_biller_name"));
        columns.add(Column.aliased("biller_category_id", table, columnPrefix + "_biller_category_id"));
        columns.add(Column.aliased("address_id", table, columnPrefix + "_address_id"));
        columns.add(Column.aliased("narative", table, columnPrefix + "_narative"));
        columns.add(Column.aliased("narative_1", table, columnPrefix + "_narative_1"));
        columns.add(Column.aliased("narative_2", table, columnPrefix + "_narative_2"));
        columns.add(Column.aliased("narative_3", table, columnPrefix + "_narative_3"));
        columns.add(Column.aliased("narative_4", table, columnPrefix + "_narative_4"));
        columns.add(Column.aliased("narative_5", table, columnPrefix + "_narative_5"));
        columns.add(Column.aliased("narative_6", table, columnPrefix + "_narative_6"));
        columns.add(Column.aliased("narative_7", table, columnPrefix + "_narative_7"));
        columns.add(Column.aliased("timestamp", table, columnPrefix + "_timestamp"));
        columns.add(Column.aliased("free_field_1", table, columnPrefix + "_free_field_1"));
        columns.add(Column.aliased("free_field_2", table, columnPrefix + "_free_field_2"));
        columns.add(Column.aliased("free_field_3", table, columnPrefix + "_free_field_3"));
        columns.add(Column.aliased("free_field_4", table, columnPrefix + "_free_field_4"));
        columns.add(Column.aliased("free_field_5", table, columnPrefix + "_free_field_5"));
        columns.add(Column.aliased("free_field_6", table, columnPrefix + "_free_field_6"));
        columns.add(Column.aliased("free_field_7", table, columnPrefix + "_free_field_7"));
        columns.add(Column.aliased("free_field_8", table, columnPrefix + "_free_field_8"));
        columns.add(Column.aliased("free_field_9", table, columnPrefix + "_free_field_9"));
        columns.add(Column.aliased("free_field_10", table, columnPrefix + "_free_field_10"));
        columns.add(Column.aliased("free_field_11", table, columnPrefix + "_free_field_11"));
        columns.add(Column.aliased("free_field_12", table, columnPrefix + "_free_field_12"));
        columns.add(Column.aliased("free_field_13", table, columnPrefix + "_free_field_13"));
        columns.add(Column.aliased("delflg", table, columnPrefix + "_delflg"));
        columns.add(Column.aliased("created_at", table, columnPrefix + "_created_at"));
        columns.add(Column.aliased("created_by", table, columnPrefix + "_created_by"));
        columns.add(Column.aliased("updated_at", table, columnPrefix + "_updated_at"));
        columns.add(Column.aliased("updated_by", table, columnPrefix + "_updated_by"));

        columns.add(Column.aliased("biller_account_id", table, columnPrefix + "_biller_account_id"));
        return columns;
    }
}
