package ug.co.absa.microsrvc.gateway.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class DevicesSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("absa_tran_ref", table, columnPrefix + "_absa_tran_ref"));
        columns.add(Column.aliased("record_id", table, columnPrefix + "_record_id"));
        columns.add(Column.aliased("device_id", table, columnPrefix + "_device_id"));
        columns.add(Column.aliased("device_name", table, columnPrefix + "_device_name"));
        columns.add(Column.aliased("device_type", table, columnPrefix + "_device_type"));
        columns.add(Column.aliased("device_description", table, columnPrefix + "_device_description"));
        columns.add(Column.aliased("timestamp", table, columnPrefix + "_timestamp"));
        columns.add(Column.aliased("delflg", table, columnPrefix + "_delflg"));
        columns.add(Column.aliased("record_status", table, columnPrefix + "_record_status"));
        columns.add(Column.aliased("free_field", table, columnPrefix + "_free_field"));
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
        columns.add(Column.aliased("free_field_14", table, columnPrefix + "_free_field_14"));
        columns.add(Column.aliased("free_field_15", table, columnPrefix + "_free_field_15"));
        columns.add(Column.aliased("free_field_15_content_type", table, columnPrefix + "_free_field_15_content_type"));
        columns.add(Column.aliased("free_field_16", table, columnPrefix + "_free_field_16"));
        columns.add(Column.aliased("free_field_17", table, columnPrefix + "_free_field_17"));
        columns.add(Column.aliased("free_field_18", table, columnPrefix + "_free_field_18"));
        columns.add(Column.aliased("free_field_18_content_type", table, columnPrefix + "_free_field_18_content_type"));
        columns.add(Column.aliased("free_field_19", table, columnPrefix + "_free_field_19"));
        columns.add(Column.aliased("created_at", table, columnPrefix + "_created_at"));
        columns.add(Column.aliased("created_by", table, columnPrefix + "_created_by"));
        columns.add(Column.aliased("updated_at", table, columnPrefix + "_updated_at"));
        columns.add(Column.aliased("updated_by", table, columnPrefix + "_updated_by"));

        return columns;
    }
}
