package ug.co.absa.microsrvc.gateway.repository.rowmapper;

import io.r2dbc.spi.Row;
import java.time.ZonedDateTime;
import java.util.UUID;
import java.util.function.BiFunction;
import org.springframework.stereotype.Service;
import ug.co.absa.microsrvc.gateway.domain.BlockedAccounts;
import ug.co.absa.microsrvc.gateway.domain.enumeration.RecordStatus;

/**
 * Converter between {@link Row} to {@link BlockedAccounts}, with proper type conversions.
 */
@Service
public class BlockedAccountsRowMapper implements BiFunction<Row, String, BlockedAccounts> {

    private final ColumnConverter converter;

    public BlockedAccountsRowMapper(ColumnConverter converter) {
        this.converter = converter;
    }

    /**
     * Take a {@link Row} and a column prefix, and extract all the fields.
     * @return the {@link BlockedAccounts} stored in the database.
     */
    @Override
    public BlockedAccounts apply(Row row, String prefix) {
        BlockedAccounts entity = new BlockedAccounts();
        entity.setId(converter.fromRow(row, prefix + "_id", Long.class));
        entity.setAbsaTranRef(converter.fromRow(row, prefix + "_absa_tran_ref", UUID.class));
        entity.setCustomerId(converter.fromRow(row, prefix + "_customer_id", String.class));
        entity.setCustomerAccountNumber(converter.fromRow(row, prefix + "_customer_account_number", String.class));
        entity.setDtDTransactionId(converter.fromRow(row, prefix + "_dt_d_transaction_id", String.class));
        entity.setBlockId(converter.fromRow(row, prefix + "_block_id", String.class));
        entity.setBlockCode(converter.fromRow(row, prefix + "_block_code", String.class));
        entity.setBlockDate(converter.fromRow(row, prefix + "_block_date", ZonedDateTime.class));
        entity.setBlockType(converter.fromRow(row, prefix + "_block_type", String.class));
        entity.setBlockStatus(converter.fromRow(row, prefix + "_block_status", String.class));
        entity.setBlockReason(converter.fromRow(row, prefix + "_block_reason", String.class));
        entity.setBlockReasonCode1(converter.fromRow(row, prefix + "_block_reason_code_1", String.class));
        entity.setBlockReasonCode2(converter.fromRow(row, prefix + "_block_reason_code_2", String.class));
        entity.setBlockReason1(converter.fromRow(row, prefix + "_block_reason_1", String.class));
        entity.setBlockReasonCode3(converter.fromRow(row, prefix + "_block_reason_code_3", String.class));
        entity.setBlockReasonCode4(converter.fromRow(row, prefix + "_block_reason_code_4", String.class));
        entity.setStartDate(converter.fromRow(row, prefix + "_start_date", ZonedDateTime.class));
        entity.setEndDate(converter.fromRow(row, prefix + "_end_date", ZonedDateTime.class));
        entity.setIsTemp(converter.fromRow(row, prefix + "_is_temp", Boolean.class));
        entity.setBlockFreeText(converter.fromRow(row, prefix + "_block_free_text", String.class));
        entity.setBlockFreeText1(converter.fromRow(row, prefix + "_block_free_text_1", String.class));
        entity.setBlockFreeText2(converter.fromRow(row, prefix + "_block_free_text_2", String.class));
        entity.setBlockFreeText3(converter.fromRow(row, prefix + "_block_free_text_3", String.class));
        entity.setBlockFreeText4(converter.fromRow(row, prefix + "_block_free_text_4", String.class));
        entity.setBlockFreeText5(converter.fromRow(row, prefix + "_block_free_text_5", String.class));
        entity.setBlockFreeText6(converter.fromRow(row, prefix + "_block_free_text_6", String.class));
        entity.setBlockReasonCode5(converter.fromRow(row, prefix + "_block_reason_code_5", String.class));
        entity.setBlockReasonCode6(converter.fromRow(row, prefix + "_block_reason_code_6", String.class));
        entity.setBlockReasonCode7(converter.fromRow(row, prefix + "_block_reason_code_7", String.class));
        entity.setBlockReasonCode8(converter.fromRow(row, prefix + "_block_reason_code_8", String.class));
        entity.setBlockReasonCode9(converter.fromRow(row, prefix + "_block_reason_code_9", String.class));
        entity.setBlockReasonCode10(converter.fromRow(row, prefix + "_block_reason_code_10", String.class));
        entity.setBlockReasonCode11(converter.fromRow(row, prefix + "_block_reason_code_11", String.class));
        entity.setBlockReasonCode12(converter.fromRow(row, prefix + "_block_reason_code_12", String.class));
        entity.setBlockReasonCode13(converter.fromRow(row, prefix + "_block_reason_code_13", String.class));
        entity.setBlockReasonCode14(converter.fromRow(row, prefix + "_block_reason_code_14", String.class));
        entity.setBlockReasonCode15(converter.fromRow(row, prefix + "_block_reason_code_15", String.class));
        entity.setBlockReasonCode16(converter.fromRow(row, prefix + "_block_reason_code_16", String.class));
        entity.setCreatedAt(converter.fromRow(row, prefix + "_created_at", ZonedDateTime.class));
        entity.setCreatedBy(converter.fromRow(row, prefix + "_created_by", String.class));
        entity.setUpdatedAt(converter.fromRow(row, prefix + "_updated_at", ZonedDateTime.class));
        entity.setUpdatedBy(converter.fromRow(row, prefix + "_updated_by", String.class));
        entity.setDelflg(converter.fromRow(row, prefix + "_delflg", Boolean.class));
        entity.setStatus(converter.fromRow(row, prefix + "_status", RecordStatus.class));
        return entity;
    }
}
