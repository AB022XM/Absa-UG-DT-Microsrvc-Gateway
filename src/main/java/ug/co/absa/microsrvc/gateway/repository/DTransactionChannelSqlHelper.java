package ug.co.absa.microsrvc.gateway.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class DTransactionChannelSqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("absa_tran_ref", table, columnPrefix + "_absa_tran_ref"));
        columns.add(Column.aliased("biller_id", table, columnPrefix + "_biller_id"));
        columns.add(Column.aliased("payment_item_code", table, columnPrefix + "_payment_item_code"));
        columns.add(Column.aliased("dt_d_transaction_id", table, columnPrefix + "_dt_d_transaction_id"));
        columns.add(Column.aliased("transaction_reference_number", table, columnPrefix + "_transaction_reference_number"));
        columns.add(Column.aliased("external_tranid", table, columnPrefix + "_external_tranid"));
        columns.add(Column.aliased("channel_code", table, columnPrefix + "_channel_code"));
        columns.add(Column.aliased("channel_name", table, columnPrefix + "_channel_name"));
        columns.add(Column.aliased("channel_description", table, columnPrefix + "_channel_description"));
        columns.add(Column.aliased("timestamp", table, columnPrefix + "_timestamp"));
        columns.add(Column.aliased("chanel_free_text", table, columnPrefix + "_chanel_free_text"));
        columns.add(Column.aliased("chanel_free_text_1", table, columnPrefix + "_chanel_free_text_1"));
        columns.add(Column.aliased("chanel_free_text_2", table, columnPrefix + "_chanel_free_text_2"));
        columns.add(Column.aliased("chanel_free_text_3", table, columnPrefix + "_chanel_free_text_3"));
        columns.add(Column.aliased("chanel_free_text_4", table, columnPrefix + "_chanel_free_text_4"));
        columns.add(Column.aliased("chanel_free_text_5", table, columnPrefix + "_chanel_free_text_5"));
        columns.add(Column.aliased("chanel_free_text_6", table, columnPrefix + "_chanel_free_text_6"));
        columns.add(Column.aliased("chanel_free_text_7", table, columnPrefix + "_chanel_free_text_7"));
        columns.add(Column.aliased("chanel_free_text_8", table, columnPrefix + "_chanel_free_text_8"));
        columns.add(Column.aliased("chanel_free_text_9", table, columnPrefix + "_chanel_free_text_9"));
        columns.add(Column.aliased("chanel_free_text_10", table, columnPrefix + "_chanel_free_text_10"));
        columns.add(Column.aliased("chanel_free_text_11", table, columnPrefix + "_chanel_free_text_11"));
        columns.add(Column.aliased("chanel_free_text_12", table, columnPrefix + "_chanel_free_text_12"));
        columns.add(Column.aliased("chanel_free_text_13", table, columnPrefix + "_chanel_free_text_13"));
        columns.add(Column.aliased("chanel_free_text_14", table, columnPrefix + "_chanel_free_text_14"));
        columns.add(Column.aliased("chanel_free_text_15", table, columnPrefix + "_chanel_free_text_15"));
        columns.add(Column.aliased("chanel_free_text_16", table, columnPrefix + "_chanel_free_text_16"));
        columns.add(Column.aliased("chanel_free_text_17", table, columnPrefix + "_chanel_free_text_17"));
        columns.add(Column.aliased("chanel_free_text_18", table, columnPrefix + "_chanel_free_text_18"));
        columns.add(Column.aliased("chanel_free_text_19", table, columnPrefix + "_chanel_free_text_19"));
        columns.add(Column.aliased("chanel_free_text_20", table, columnPrefix + "_chanel_free_text_20"));
        columns.add(Column.aliased("chanel_free_text_21", table, columnPrefix + "_chanel_free_text_21"));
        columns.add(Column.aliased("chanel_free_text_22", table, columnPrefix + "_chanel_free_text_22"));
        columns.add(Column.aliased("chanel_free_text_23", table, columnPrefix + "_chanel_free_text_23"));
        columns.add(Column.aliased("chanel_free_text_24", table, columnPrefix + "_chanel_free_text_24"));
        columns.add(Column.aliased("created_at", table, columnPrefix + "_created_at"));
        columns.add(Column.aliased("created_by", table, columnPrefix + "_created_by"));
        columns.add(Column.aliased("updated_at", table, columnPrefix + "_updated_at"));
        columns.add(Column.aliased("updated_by", table, columnPrefix + "_updated_by"));
        columns.add(Column.aliased("delflg", table, columnPrefix + "_delflg"));

        columns.add(Column.aliased("d_transaction_id", table, columnPrefix + "_d_transaction_id"));
        return columns;
    }
}
