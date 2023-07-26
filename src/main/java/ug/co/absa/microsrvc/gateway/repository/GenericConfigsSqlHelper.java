package ug.co.absa.microsrvc.gateway.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class GenericConfigsSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("absa_tran_ref", table, columnPrefix + "_absa_tran_ref"));
        columns.add(Column.aliased("record_id", table, columnPrefix + "_record_id"));
        columns.add(Column.aliased("config_id", table, columnPrefix + "_config_id"));
        columns.add(Column.aliased("config_name", table, columnPrefix + "_config_name"));
        columns.add(Column.aliased("configs_details", table, columnPrefix + "_configs_details"));
        columns.add(Column.aliased("additional_configs", table, columnPrefix + "_additional_configs"));
        columns.add(Column.aliased("free_field", table, columnPrefix + "_free_field"));
        columns.add(Column.aliased("free_field_1", table, columnPrefix + "_free_field_1"));
        columns.add(Column.aliased("free_field_2", table, columnPrefix + "_free_field_2"));
        columns.add(Column.aliased("free_field_3", table, columnPrefix + "_free_field_3"));
        columns.add(Column.aliased("free_field_4", table, columnPrefix + "_free_field_4"));
        columns.add(Column.aliased("free_field_5", table, columnPrefix + "_free_field_5"));
        columns.add(Column.aliased("free_field_6", table, columnPrefix + "_free_field_6"));
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
        columns.add(Column.aliased("free_field_20", table, columnPrefix + "_free_field_20"));
        columns.add(Column.aliased("free_field_21", table, columnPrefix + "_free_field_21"));
        columns.add(Column.aliased("free_field_22", table, columnPrefix + "_free_field_22"));
        columns.add(Column.aliased("free_field_23", table, columnPrefix + "_free_field_23"));
        columns.add(Column.aliased("free_field_24", table, columnPrefix + "_free_field_24"));
        columns.add(Column.aliased("free_field_25", table, columnPrefix + "_free_field_25"));
        columns.add(Column.aliased("free_field_25_content_type", table, columnPrefix + "_free_field_25_content_type"));
        columns.add(Column.aliased("free_field_26", table, columnPrefix + "_free_field_26"));
        columns.add(Column.aliased("free_field_27", table, columnPrefix + "_free_field_27"));
        columns.add(Column.aliased("free_field_28", table, columnPrefix + "_free_field_28"));
        columns.add(Column.aliased("free_field_28_content_type", table, columnPrefix + "_free_field_28_content_type"));
        columns.add(Column.aliased("free_field_29", table, columnPrefix + "_free_field_29"));
        columns.add(Column.aliased("free_field_30", table, columnPrefix + "_free_field_30"));
        columns.add(Column.aliased("free_field_31", table, columnPrefix + "_free_field_31"));
        columns.add(Column.aliased("free_field_32", table, columnPrefix + "_free_field_32"));
        columns.add(Column.aliased("free_field_33", table, columnPrefix + "_free_field_33"));
        columns.add(Column.aliased("free_field_34", table, columnPrefix + "_free_field_34"));
        columns.add(Column.aliased("timestamp", table, columnPrefix + "_timestamp"));
        columns.add(Column.aliased("record_status", table, columnPrefix + "_record_status"));

        return columns;
    }
}
