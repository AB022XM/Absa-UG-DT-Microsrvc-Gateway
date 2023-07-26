package ug.co.absa.microsrvc.gateway.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class CustomAuditSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("absa_tran_ref", table, columnPrefix + "_absa_tran_ref"));
        columns.add(Column.aliased("record_id", table, columnPrefix + "_record_id"));
        columns.add(Column.aliased("action_id", table, columnPrefix + "_action_id"));
        columns.add(Column.aliased("timestamp", table, columnPrefix + "_timestamp"));
        columns.add(Column.aliased("old_value", table, columnPrefix + "_old_value"));
        columns.add(Column.aliased("new_value", table, columnPrefix + "_new_value"));
        columns.add(Column.aliased("change_resaon", table, columnPrefix + "_change_resaon"));
        columns.add(Column.aliased("description", table, columnPrefix + "_description"));
        columns.add(Column.aliased("description_1", table, columnPrefix + "_description_1"));
        columns.add(Column.aliased("description_2", table, columnPrefix + "_description_2"));
        columns.add(Column.aliased("description_3", table, columnPrefix + "_description_3"));
        columns.add(Column.aliased("description_4", table, columnPrefix + "_description_4"));
        columns.add(Column.aliased("description_5", table, columnPrefix + "_description_5"));
        columns.add(Column.aliased("description_6", table, columnPrefix + "_description_6"));
        columns.add(Column.aliased("description_7", table, columnPrefix + "_description_7"));
        columns.add(Column.aliased("description_8", table, columnPrefix + "_description_8"));
        columns.add(Column.aliased("description_9", table, columnPrefix + "_description_9"));
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
        columns.add(Column.aliased("free_text_20", table, columnPrefix + "_free_text_20"));
        columns.add(Column.aliased("free_text_21", table, columnPrefix + "_free_text_21"));
        columns.add(Column.aliased("free_text_22", table, columnPrefix + "_free_text_22"));
        columns.add(Column.aliased("free_text_23", table, columnPrefix + "_free_text_23"));
        columns.add(Column.aliased("free_text_24", table, columnPrefix + "_free_text_24"));
        columns.add(Column.aliased("free_text_25", table, columnPrefix + "_free_text_25"));
        columns.add(Column.aliased("free_text_26", table, columnPrefix + "_free_text_26"));
        columns.add(Column.aliased("free_text_27", table, columnPrefix + "_free_text_27"));
        columns.add(Column.aliased("free_text_28", table, columnPrefix + "_free_text_28"));
        columns.add(Column.aliased("created_at", table, columnPrefix + "_created_at"));
        columns.add(Column.aliased("created_by", table, columnPrefix + "_created_by"));
        columns.add(Column.aliased("updated_at", table, columnPrefix + "_updated_at"));
        columns.add(Column.aliased("updated_by", table, columnPrefix + "_updated_by"));

        return columns;
    }
}
