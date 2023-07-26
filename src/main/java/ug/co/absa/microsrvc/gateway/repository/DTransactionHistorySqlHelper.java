package ug.co.absa.microsrvc.gateway.repository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.data.relational.core.sql.Column;
import org.springframework.data.relational.core.sql.Expression;
import org.springframework.data.relational.core.sql.Table;

public class DTransactionHistorySqlHelper {

    public static List<Expression> getColumns(Table table, String columnPrefix) {
        List<Expression> columns = new ArrayList<>();
        columns.add(Column.aliased("id", table, columnPrefix + "_id"));
        columns.add(Column.aliased("record_id", table, columnPrefix + "_record_id"));
        columns.add(Column.aliased("transfer_id", table, columnPrefix + "_transfer_id"));
        columns.add(Column.aliased("product_code", table, columnPrefix + "_product_code"));
        columns.add(Column.aliased("payment_channel_code", table, columnPrefix + "_payment_channel_code"));
        columns.add(Column.aliased("debit_account_number", table, columnPrefix + "_debit_account_number"));
        columns.add(Column.aliased("credit_account_number", table, columnPrefix + "_credit_account_number"));
        columns.add(Column.aliased("debit_amount", table, columnPrefix + "_debit_amount"));
        columns.add(Column.aliased("credit_amount", table, columnPrefix + "_credit_amount"));
        columns.add(Column.aliased("transaction_amount", table, columnPrefix + "_transaction_amount"));
        columns.add(Column.aliased("debit_date", table, columnPrefix + "_debit_date"));
        columns.add(Column.aliased("credit_date", table, columnPrefix + "_credit_date"));
        columns.add(Column.aliased("status", table, columnPrefix + "_status"));
        columns.add(Column.aliased("settlement_date", table, columnPrefix + "_settlement_date"));
        columns.add(Column.aliased("debit_currency", table, columnPrefix + "_debit_currency"));
        columns.add(Column.aliased("timestamp", table, columnPrefix + "_timestamp"));
        columns.add(Column.aliased("phone_number", table, columnPrefix + "_phone_number"));
        columns.add(Column.aliased("email", table, columnPrefix + "_email"));
        columns.add(Column.aliased("payer_name", table, columnPrefix + "_payer_name"));
        columns.add(Column.aliased("payer_address", table, columnPrefix + "_payer_address"));
        columns.add(Column.aliased("payer_email", table, columnPrefix + "_payer_email"));
        columns.add(Column.aliased("payer_phone_number", table, columnPrefix + "_payer_phone_number"));
        columns.add(Column.aliased("payer_narration", table, columnPrefix + "_payer_narration"));
        columns.add(Column.aliased("payer_branch_id", table, columnPrefix + "_payer_branch_id"));
        columns.add(Column.aliased("beneficiary_name", table, columnPrefix + "_beneficiary_name"));
        columns.add(Column.aliased("beneficiary_account", table, columnPrefix + "_beneficiary_account"));
        columns.add(Column.aliased("beneficiary_branch_id", table, columnPrefix + "_beneficiary_branch_id"));
        columns.add(Column.aliased("beneficiary_phone_number", table, columnPrefix + "_beneficiary_phone_number"));
        columns.add(Column.aliased("tran_status", table, columnPrefix + "_tran_status"));
        columns.add(Column.aliased("narration_1", table, columnPrefix + "_narration_1"));
        columns.add(Column.aliased("narration_2", table, columnPrefix + "_narration_2"));
        columns.add(Column.aliased("narration_3", table, columnPrefix + "_narration_3"));
        columns.add(Column.aliased("mode_of_payment", table, columnPrefix + "_mode_of_payment"));
        columns.add(Column.aliased("retries", table, columnPrefix + "_retries"));
        columns.add(Column.aliased("posted", table, columnPrefix + "_posted"));
        columns.add(Column.aliased("api_id", table, columnPrefix + "_api_id"));
        columns.add(Column.aliased("api_version", table, columnPrefix + "_api_version"));
        columns.add(Column.aliased("posting_api", table, columnPrefix + "_posting_api"));
        columns.add(Column.aliased("is_posted", table, columnPrefix + "_is_posted"));
        columns.add(Column.aliased("posted_by", table, columnPrefix + "_posted_by"));
        columns.add(Column.aliased("posted_date", table, columnPrefix + "_posted_date"));
        columns.add(Column.aliased("internal_error_code", table, columnPrefix + "_internal_error_code"));
        columns.add(Column.aliased("external_error_code", table, columnPrefix + "_external_error_code"));
        columns.add(Column.aliased("tran_free_field_1", table, columnPrefix + "_tran_free_field_1"));
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
        columns.add(Column.aliased("created_at", table, columnPrefix + "_created_at"));
        columns.add(Column.aliased("created_by", table, columnPrefix + "_created_by"));
        columns.add(Column.aliased("updated_at", table, columnPrefix + "_updated_at"));
        columns.add(Column.aliased("updated_by", table, columnPrefix + "_updated_by"));

        columns.add(Column.aliased("customer_id", table, columnPrefix + "_customer_id"));
        return columns;
    }
}
