package ug.co.absa.microsrvc.gateway.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class ErrorCodesSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("absa_tran_ref", table, columnPrefix + "_absa_tran_ref"));
        columns.add(Column.aliased("record_id", table, columnPrefix + "_record_id"));
        columns.add(Column.aliased("payment_item_code", table, columnPrefix + "_payment_item_code"));
        columns.add(Column.aliased("dt_d_transaction_id", table, columnPrefix + "_dt_d_transaction_id"));
        columns.add(Column.aliased("transaction_reference_number", table, columnPrefix + "_transaction_reference_number"));
        columns.add(Column.aliased("external_tranid", table, columnPrefix + "_external_tranid"));
        columns.add(Column.aliased("error_code", table, columnPrefix + "_error_code"));
        columns.add(Column.aliased("error_message", table, columnPrefix + "_error_message"));
        columns.add(Column.aliased("response_code", table, columnPrefix + "_response_code"));
        columns.add(Column.aliased("response_message", table, columnPrefix + "_response_message"));
        columns.add(Column.aliased("response_body", table, columnPrefix + "_response_body"));
        columns.add(Column.aliased("timestamp", table, columnPrefix + "_timestamp"));
        columns.add(Column.aliased("request_ref", table, columnPrefix + "_request_ref"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("customer_field_1", table, columnPrefix + "_customer_field_1"));
        columns.add(Column.aliased("customer_field_2", table, columnPrefix + "_customer_field_2"));
        columns.add(Column.aliased("customer_field_3", table, columnPrefix + "_customer_field_3"));
        columns.add(Column.aliased("customer_field_4", table, columnPrefix + "_customer_field_4"));
        columns.add(Column.aliased("customer_field_5", table, columnPrefix + "_customer_field_5"));
        columns.add(Column.aliased("customer_field_6", table, columnPrefix + "_customer_field_6"));
        columns.add(Column.aliased("customer_field_7", table, columnPrefix + "_customer_field_7"));
        columns.add(Column.aliased("customer_field_8", table, columnPrefix + "_customer_field_8"));
        columns.add(Column.aliased("created_at", table, columnPrefix + "_created_at"));
        columns.add(Column.aliased("created_by", table, columnPrefix + "_created_by"));
        columns.add(Column.aliased("updated_at", table, columnPrefix + "_updated_at"));
        columns.add(Column.aliased("updated_by", table, columnPrefix + "_updated_by"));

        return columns;
    }
}
