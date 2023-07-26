package ug.co.absa.microsrvc.gateway.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class LiquidationSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("record_id", table, columnPrefix + "_record_id"));
        columns.add(Column.aliased("service_level", table, columnPrefix + "_service_level"));
        columns.add(Column.aliased("timestamp", table, columnPrefix + "_timestamp"));
        columns.add(Column.aliased("partner_code", table, columnPrefix + "_partner_code"));
        columns.add(Column.aliased("amount", table, columnPrefix + "_amount"));
        columns.add(Column.aliased("currency", table, columnPrefix + "_currency"));
        columns.add(Column.aliased("receiver_bankcode", table, columnPrefix + "_receiver_bankcode"));
        columns.add(Column.aliased("receiver_account_number", table, columnPrefix + "_receiver_account_number"));
        columns.add(Column.aliased("beneficiary_name", table, columnPrefix + "_beneficiary_name"));
        columns.add(Column.aliased("instruction_id", table, columnPrefix + "_instruction_id"));
        columns.add(Column.aliased("sender_to_receiver_info", table, columnPrefix + "_sender_to_receiver_info"));
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
