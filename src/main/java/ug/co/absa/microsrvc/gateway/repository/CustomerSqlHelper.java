package ug.co.absa.microsrvc.gateway.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class CustomerSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("absa_tran_ref", table, columnPrefix + "_absa_tran_ref"));
        columns.add(Column.aliased("biller_id", table, columnPrefix + "_biller_id"));
        columns.add(Column.aliased("payment_item_code", table, columnPrefix + "_payment_item_code"));
        columns.add(Column.aliased("dt_d_transaction_id", table, columnPrefix + "_dt_d_transaction_id"));
        columns.add(Column.aliased("amol_d_transaction_id", table, columnPrefix + "_amol_d_transaction_id"));
        columns.add(Column.aliased("transaction_reference_number", table, columnPrefix + "_transaction_reference_number"));
        columns.add(Column.aliased("token", table, columnPrefix + "_token"));
        columns.add(Column.aliased("transfer_id", table, columnPrefix + "_transfer_id"));
        columns.add(Column.aliased("request_id", table, columnPrefix + "_request_id"));
        columns.add(Column.aliased("account_name", table, columnPrefix + "_account_name"));
        columns.add(Column.aliased("return_code", table, columnPrefix + "_return_code"));
        columns.add(Column.aliased("return_message", table, columnPrefix + "_return_message"));
        columns.add(Column.aliased("external_tx_nid", table, columnPrefix + "_external_tx_nid"));
        columns.add(Column.aliased("transaction_date", table, columnPrefix + "_transaction_date"));
        columns.add(Column.aliased("customer_id", table, columnPrefix + "_customer_id"));
        columns.add(Column.aliased("customer_product", table, columnPrefix + "_customer_product"));
        columns.add(Column.aliased("customer_type", table, columnPrefix + "_customer_type"));
        columns.add(Column.aliased("account_category", table, columnPrefix + "_account_category"));
        columns.add(Column.aliased("account_type", table, columnPrefix + "_account_type"));
        columns.add(Column.aliased("account_number", table, columnPrefix + "_account_number"));
        columns.add(Column.aliased("phone_number", table, columnPrefix + "_phone_number"));
        columns.add(Column.aliased("channel", table, columnPrefix + "_channel"));
        columns.add(Column.aliased("tran_free_field_1", table, columnPrefix + "_tran_free_field_1"));
        columns.add(Column.aliased("tran_free_field_2", table, columnPrefix + "_tran_free_field_2"));
        columns.add(Column.aliased("tran_free_field_3", table, columnPrefix + "_tran_free_field_3"));
        columns.add(Column.aliased("tran_free_field_4", table, columnPrefix + "_tran_free_field_4"));
        columns.add(Column.aliased("tran_free_field_5", table, columnPrefix + "_tran_free_field_5"));
        columns.add(Column.aliased("tran_free_field_6", table, columnPrefix + "_tran_free_field_6"));
        columns.add(Column.aliased("tran_free_field_7", table, columnPrefix + "_tran_free_field_7"));
        columns.add(Column.aliased("tran_free_field_8", table, columnPrefix + "_tran_free_field_8"));
        columns.add(Column.aliased("tran_free_field_9", table, columnPrefix + "_tran_free_field_9"));
        columns.add(Column.aliased("tran_free_field_10", table, columnPrefix + "_tran_free_field_10"));
        columns.add(Column.aliased("tran_free_field_11", table, columnPrefix + "_tran_free_field_11"));
        columns.add(Column.aliased("tran_free_field_12", table, columnPrefix + "_tran_free_field_12"));
        columns.add(Column.aliased("tran_free_field_13", table, columnPrefix + "_tran_free_field_13"));
        columns.add(Column.aliased("tran_free_field_14", table, columnPrefix + "_tran_free_field_14"));
        columns.add(Column.aliased("tran_free_field_15", table, columnPrefix + "_tran_free_field_15"));
        columns.add(Column.aliased("tran_free_field_16", table, columnPrefix + "_tran_free_field_16"));
        columns.add(Column.aliased("tran_free_field_17", table, columnPrefix + "_tran_free_field_17"));
        columns.add(Column.aliased("tran_free_field_18", table, columnPrefix + "_tran_free_field_18"));
        columns.add(Column.aliased("tran_free_field_19", table, columnPrefix + "_tran_free_field_19"));
        columns.add(Column.aliased("tran_free_field_20", table, columnPrefix + "_tran_free_field_20"));
        columns.add(Column.aliased("tran_free_field_21", table, columnPrefix + "_tran_free_field_21"));
        columns.add(Column.aliased("tran_free_field_22", table, columnPrefix + "_tran_free_field_22"));
        columns.add(Column.aliased("tran_free_field_23", table, columnPrefix + "_tran_free_field_23"));
        columns.add(Column.aliased("tran_free_field_23_content_type", table, columnPrefix + "_tran_free_field_23_content_type"));
        columns.add(Column.aliased("tran_free_field_24", table, columnPrefix + "_tran_free_field_24"));
        columns.add(Column.aliased("tran_free_field_25", table, columnPrefix + "_tran_free_field_25"));
        columns.add(Column.aliased("tran_free_field_26", table, columnPrefix + "_tran_free_field_26"));
        columns.add(Column.aliased("tran_free_field_27", table, columnPrefix + "_tran_free_field_27"));
        columns.add(Column.aliased("tran_free_field_28", table, columnPrefix + "_tran_free_field_28"));
        columns.add(Column.aliased("tran_free_field_29", table, columnPrefix + "_tran_free_field_29"));
        columns.add(Column.aliased("tran_free_field_30", table, columnPrefix + "_tran_free_field_30"));
        columns.add(Column.aliased("tran_free_field_31", table, columnPrefix + "_tran_free_field_31"));
        columns.add(Column.aliased("tran_free_field_32", table, columnPrefix + "_tran_free_field_32"));
        columns.add(Column.aliased("tran_free_field_33", table, columnPrefix + "_tran_free_field_33"));
        columns.add(Column.aliased("created_at", table, columnPrefix + "_created_at"));
        columns.add(Column.aliased("created_by", table, columnPrefix + "_created_by"));
        columns.add(Column.aliased("updated_at", table, columnPrefix + "_updated_at"));
        columns.add(Column.aliased("updated_by", table, columnPrefix + "_updated_by"));

        return columns;
    }
}
