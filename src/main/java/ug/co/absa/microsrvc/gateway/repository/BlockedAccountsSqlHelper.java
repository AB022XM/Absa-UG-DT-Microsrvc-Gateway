package ug.co.absa.microsrvc.gateway.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class BlockedAccountsSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("absa_tran_ref", table, columnPrefix + "_absa_tran_ref"));
        columns.add(Column.aliased("customer_id", table, columnPrefix + "_customer_id"));
        columns.add(Column.aliased("customer_account_number", table, columnPrefix + "_customer_account_number"));
        columns.add(Column.aliased("dt_d_transaction_id", table, columnPrefix + "_dt_d_transaction_id"));
        columns.add(Column.aliased("block_id", table, columnPrefix + "_block_id"));
        columns.add(Column.aliased("block_code", table, columnPrefix + "_block_code"));
        columns.add(Column.aliased("block_date", table, columnPrefix + "_block_date"));
        columns.add(Column.aliased("block_type", table, columnPrefix + "_block_type"));
        columns.add(Column.aliased("block_status", table, columnPrefix + "_block_status"));
        columns.add(Column.aliased("block_reason", table, columnPrefix + "_block_reason"));
        columns.add(Column.aliased("block_reason_code_1", table, columnPrefix + "_block_reason_code_1"));
        columns.add(Column.aliased("block_reason_code_2", table, columnPrefix + "_block_reason_code_2"));
        columns.add(Column.aliased("block_reason_1", table, columnPrefix + "_block_reason_1"));
        columns.add(Column.aliased("block_reason_code_3", table, columnPrefix + "_block_reason_code_3"));
        columns.add(Column.aliased("block_reason_code_4", table, columnPrefix + "_block_reason_code_4"));
        columns.add(Column.aliased("start_date", table, columnPrefix + "_start_date"));
        columns.add(Column.aliased("end_date", table, columnPrefix + "_end_date"));
        columns.add(Column.aliased("is_temp", table, columnPrefix + "_is_temp"));
        columns.add(Column.aliased("block_free_text", table, columnPrefix + "_block_free_text"));
        columns.add(Column.aliased("block_free_text_1", table, columnPrefix + "_block_free_text_1"));
        columns.add(Column.aliased("block_free_text_2", table, columnPrefix + "_block_free_text_2"));
        columns.add(Column.aliased("block_free_text_3", table, columnPrefix + "_block_free_text_3"));
        columns.add(Column.aliased("block_free_text_4", table, columnPrefix + "_block_free_text_4"));
        columns.add(Column.aliased("block_free_text_5", table, columnPrefix + "_block_free_text_5"));
        columns.add(Column.aliased("block_free_text_6", table, columnPrefix + "_block_free_text_6"));
        columns.add(Column.aliased("block_reason_code_5", table, columnPrefix + "_block_reason_code_5"));
        columns.add(Column.aliased("block_reason_code_6", table, columnPrefix + "_block_reason_code_6"));
        columns.add(Column.aliased("block_reason_code_7", table, columnPrefix + "_block_reason_code_7"));
        columns.add(Column.aliased("block_reason_code_8", table, columnPrefix + "_block_reason_code_8"));
        columns.add(Column.aliased("block_reason_code_9", table, columnPrefix + "_block_reason_code_9"));
        columns.add(Column.aliased("block_reason_code_10", table, columnPrefix + "_block_reason_code_10"));
        columns.add(Column.aliased("block_reason_code_11", table, columnPrefix + "_block_reason_code_11"));
        columns.add(Column.aliased("block_reason_code_12", table, columnPrefix + "_block_reason_code_12"));
        columns.add(Column.aliased("block_reason_code_13", table, columnPrefix + "_block_reason_code_13"));
        columns.add(Column.aliased("block_reason_code_14", table, columnPrefix + "_block_reason_code_14"));
        columns.add(Column.aliased("block_reason_code_15", table, columnPrefix + "_block_reason_code_15"));
        columns.add(Column.aliased("block_reason_code_16", table, columnPrefix + "_block_reason_code_16"));
        columns.add(Column.aliased("created_at", table, columnPrefix + "_created_at"));
        columns.add(Column.aliased("created_by", table, columnPrefix + "_created_by"));
        columns.add(Column.aliased("updated_at", table, columnPrefix + "_updated_at"));
        columns.add(Column.aliased("updated_by", table, columnPrefix + "_updated_by"));
        columns.add(Column.aliased("delflg", table, columnPrefix + "_delflg"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));

        return columns;
    }
}
