package ug.co.absa.microsrvc.gateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.UUID;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import ug.co.absa.microsrvc.gateway.domain.enumeration.Channel;
import ug.co.absa.microsrvc.gateway.domain.enumeration.Currency;
import ug.co.absa.microsrvc.gateway.domain.enumeration.TranStatus;

/**
 * A AmolDTransactions.
 */
@Table("amol_d_transactions")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "amoldtransactions")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class AmolDTransactions implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("absa_tran_ref")
    private UUID absaTranRef;

    @Column("biller_id")
    private String billerId;

    @Column("dt_d_transaction_id")
    private String dtDTransactionId;

    @Column("amol_d_transaction_id")
    private String amolDTransactionId;

    @Column("transaction_reference_number")
    private String transactionReferenceNumber;

    @Column("token")
    private String token;

    @Column("transfer_id")
    private String transferId;

    @Column("external_txn_id")
    private String externalTxnId;

    @Column("customer_reference")
    private String customerReference;

    @NotNull(message = "must not be null")
    @Column("debit_account_number")
    private String debitAccountNumber;

    @NotNull(message = "must not be null")
    @Column("credit_account_number")
    private String creditAccountNumber;

    @NotNull(message = "must not be null")
    @Column("debit_amount")
    private BigDecimal debitAmount;

    @Column("credit_amount")
    private BigDecimal creditAmount;

    @Column("transaction_amount")
    private BigDecimal transactionAmount;

    @Column("debit_date")
    private ZonedDateTime debitDate;

    @Column("credit_date")
    private ZonedDateTime creditDate;

    @Column("status")
    private TranStatus status;

    @Column("settlement_date")
    private ZonedDateTime settlementDate;

    @NotNull(message = "must not be null")
    @Column("debit_currency")
    private Currency debitCurrency;

    @NotNull(message = "must not be null")
    @Column("timestamp")
    private ZonedDateTime timestamp;

    @NotNull(message = "must not be null")
    @Column("phone_number")
    private String phoneNumber;

    @NotNull(message = "must not be null")
    @Column("email")
    private String email;

    @Column("payer_name")
    private String payerName;

    @Column("payer_address")
    private String payerAddress;

    @Column("payer_email")
    private String payerEmail;

    @Column("payer_phone_number")
    private String payerPhoneNumber;

    @Column("payer_narration")
    private String payerNarration;

    @Column("payer_branch_id")
    private String payerBranchId;

    @Column("beneficiary_name")
    private String beneficiaryName;

    @Column("beneficiary_account")
    private String beneficiaryAccount;

    @Column("beneficiary_branch_id")
    private String beneficiaryBranchId;

    @Column("beneficiary_phone_number")
    private String beneficiaryPhoneNumber;

    @Column("tran_status")
    private TranStatus tranStatus;

    @Column("narration_1")
    private String narration1;

    @Column("narration_2")
    private String narration2;

    @Column("narration_3")
    private String narration3;

    @Column("tax_amount")
    private Integer taxAmount;

    @Column("tax_gl_account")
    private String taxGLAccount;

    @Column("tax_narration")
    private String taxNarration;

    @Column("internal_error_code")
    private String internalErrorCode;

    @Column("external_error_code")
    private String externalErrorCode;

    @Column("payee_branch_id")
    private String payeeBranchId;

    @Column("payee_product_instance_reference")
    private String payeeProductInstanceReference;

    @Column("payee_product_code")
    private String payeeProductCode;

    @Column("payee_product_name")
    private String payeeProductName;

    @Column("payee_product_description")
    private String payeeProductDescription;

    @Column("payee_product_unit_of_measure")
    private String payeeProductUnitOfMeasure;

    @Column("payee_product_quantity")
    private String payeeProductQuantity;

    @Column("sub_category_code")
    private String subCategoryCode;

    @Column("payer_bank_code")
    private String payerBankCode;

    @Column("payer_bank_name")
    private String payerBankName;

    @Column("payer_bank_address")
    private String payerBankAddress;

    @Column("payer_bank_city")
    private String payerBankCity;

    @Column("payer_bank_state")
    private String payerBankState;

    @Column("payer_bank_country")
    private String payerBankCountry;

    @Column("payer_bank_postal_code")
    private String payerBankPostalCode;

    @Column("checker_id")
    private String checkerId;

    @Column("remittance_information")
    private String remittanceInformation;

    @Column("payment_channel_code")
    private Channel paymentChannelCode;

    @Column("fee_amount")
    private Integer feeAmount;

    @Column("fee_gl_account")
    private String feeGLAccount;

    @Column("fee_narration")
    private String feeNarration;

    @Column("tran_free_field_1")
    private String tranFreeField1;

    @Column("tran_free_field_2")
    private String tranFreeField2;

    @Column("tran_free_field_3")
    private String tranFreeField3;

    @Column("tran_free_field_4")
    private String tranFreeField4;

    @Column("tran_free_field_5")
    private String tranFreeField5;

    @Column("tran_free_field_6")
    private String tranFreeField6;

    @Column("tran_free_field_7")
    private String tranFreeField7;

    @Column("tran_free_field_8")
    private String tranFreeField8;

    @Column("tran_free_field_9")
    private String tranFreeField9;

    @Column("tran_free_field_10")
    private String tranFreeField10;

    @Column("tran_free_field_11")
    private String tranFreeField11;

    @Column("tran_free_field_12")
    private String tranFreeField12;

    @Column("tran_free_field_13")
    private String tranFreeField13;

    @Column("tran_free_field_14")
    private String tranFreeField14;

    @Column("tran_free_field_15")
    private String tranFreeField15;

    @Column("tran_free_field_16")
    private String tranFreeField16;

    @Column("tran_free_field_17")
    private String tranFreeField17;

    @Column("tran_free_field_18")
    private BigDecimal tranFreeField18;

    @Column("tran_free_field_19")
    private Integer tranFreeField19;

    @Column("tran_free_field_20")
    private Boolean tranFreeField20;

    @Column("tran_free_field_21")
    private String tranFreeField21;

    @Column("tran_free_field_22")
    private String tranFreeField22;

    @Column("tran_free_field_23")
    private byte[] tranFreeField23;

    @Column("tran_free_field_23_content_type")
    private String tranFreeField23ContentType;

    @Column("tran_free_field_24")
    private ZonedDateTime tranFreeField24;

    @Column("response_code")
    private String responseCode;

    @Column("response_message")
    private String responseMessage;

    @Column("response_details")
    private String responseDetails;

    @Column("transfer_reference_id")
    private String transferReferenceId;

    @Column("transfer_status")
    private String transferStatus;

    @Column("response_date_time")
    private ZonedDateTime responseDateTime;

    @Column("created_at")
    private ZonedDateTime createdAt;

    @Column("created_by")
    private String createdBy;

    @Column("updated_at")
    private ZonedDateTime updatedAt;

    @Column("updated_by")
    private String updatedBy;

    @Transient
    @JsonIgnoreProperties(
        value = { "transaction", "transaction", "transaction", "transactions", "transactions", "customer" },
        allowSetters = true
    )
    private DTransaction dTransaction;

    @Column("d_transaction_id")
    private Long dTransactionId;

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public AmolDTransactions id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getAbsaTranRef() {
        return this.absaTranRef;
    }

    public AmolDTransactions absaTranRef(UUID absaTranRef) {
        this.setAbsaTranRef(absaTranRef);
        return this;
    }

    public void setAbsaTranRef(UUID absaTranRef) {
        this.absaTranRef = absaTranRef;
    }

    public String getBillerId() {
        return this.billerId;
    }

    public AmolDTransactions billerId(String billerId) {
        this.setBillerId(billerId);
        return this;
    }

    public void setBillerId(String billerId) {
        this.billerId = billerId;
    }

    public String getDtDTransactionId() {
        return this.dtDTransactionId;
    }

    public AmolDTransactions dtDTransactionId(String dtDTransactionId) {
        this.setDtDTransactionId(dtDTransactionId);
        return this;
    }

    public void setDtDTransactionId(String dtDTransactionId) {
        this.dtDTransactionId = dtDTransactionId;
    }

    public String getAmolDTransactionId() {
        return this.amolDTransactionId;
    }

    public AmolDTransactions amolDTransactionId(String amolDTransactionId) {
        this.setAmolDTransactionId(amolDTransactionId);
        return this;
    }

    public void setAmolDTransactionId(String amolDTransactionId) {
        this.amolDTransactionId = amolDTransactionId;
    }

    public String getTransactionReferenceNumber() {
        return this.transactionReferenceNumber;
    }

    public AmolDTransactions transactionReferenceNumber(String transactionReferenceNumber) {
        this.setTransactionReferenceNumber(transactionReferenceNumber);
        return this;
    }

    public void setTransactionReferenceNumber(String transactionReferenceNumber) {
        this.transactionReferenceNumber = transactionReferenceNumber;
    }

    public String getToken() {
        return this.token;
    }

    public AmolDTransactions token(String token) {
        this.setToken(token);
        return this;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTransferId() {
        return this.transferId;
    }

    public AmolDTransactions transferId(String transferId) {
        this.setTransferId(transferId);
        return this;
    }

    public void setTransferId(String transferId) {
        this.transferId = transferId;
    }

    public String getExternalTxnId() {
        return this.externalTxnId;
    }

    public AmolDTransactions externalTxnId(String externalTxnId) {
        this.setExternalTxnId(externalTxnId);
        return this;
    }

    public void setExternalTxnId(String externalTxnId) {
        this.externalTxnId = externalTxnId;
    }

    public String getCustomerReference() {
        return this.customerReference;
    }

    public AmolDTransactions customerReference(String customerReference) {
        this.setCustomerReference(customerReference);
        return this;
    }

    public void setCustomerReference(String customerReference) {
        this.customerReference = customerReference;
    }

    public String getDebitAccountNumber() {
        return this.debitAccountNumber;
    }

    public AmolDTransactions debitAccountNumber(String debitAccountNumber) {
        this.setDebitAccountNumber(debitAccountNumber);
        return this;
    }

    public void setDebitAccountNumber(String debitAccountNumber) {
        this.debitAccountNumber = debitAccountNumber;
    }

    public String getCreditAccountNumber() {
        return this.creditAccountNumber;
    }

    public AmolDTransactions creditAccountNumber(String creditAccountNumber) {
        this.setCreditAccountNumber(creditAccountNumber);
        return this;
    }

    public void setCreditAccountNumber(String creditAccountNumber) {
        this.creditAccountNumber = creditAccountNumber;
    }

    public BigDecimal getDebitAmount() {
        return this.debitAmount;
    }

    public AmolDTransactions debitAmount(BigDecimal debitAmount) {
        this.setDebitAmount(debitAmount);
        return this;
    }

    public void setDebitAmount(BigDecimal debitAmount) {
        this.debitAmount = debitAmount != null ? debitAmount.stripTrailingZeros() : null;
    }

    public BigDecimal getCreditAmount() {
        return this.creditAmount;
    }

    public AmolDTransactions creditAmount(BigDecimal creditAmount) {
        this.setCreditAmount(creditAmount);
        return this;
    }

    public void setCreditAmount(BigDecimal creditAmount) {
        this.creditAmount = creditAmount != null ? creditAmount.stripTrailingZeros() : null;
    }

    public BigDecimal getTransactionAmount() {
        return this.transactionAmount;
    }

    public AmolDTransactions transactionAmount(BigDecimal transactionAmount) {
        this.setTransactionAmount(transactionAmount);
        return this;
    }

    public void setTransactionAmount(BigDecimal transactionAmount) {
        this.transactionAmount = transactionAmount != null ? transactionAmount.stripTrailingZeros() : null;
    }

    public ZonedDateTime getDebitDate() {
        return this.debitDate;
    }

    public AmolDTransactions debitDate(ZonedDateTime debitDate) {
        this.setDebitDate(debitDate);
        return this;
    }

    public void setDebitDate(ZonedDateTime debitDate) {
        this.debitDate = debitDate;
    }

    public ZonedDateTime getCreditDate() {
        return this.creditDate;
    }

    public AmolDTransactions creditDate(ZonedDateTime creditDate) {
        this.setCreditDate(creditDate);
        return this;
    }

    public void setCreditDate(ZonedDateTime creditDate) {
        this.creditDate = creditDate;
    }

    public TranStatus getStatus() {
        return this.status;
    }

    public AmolDTransactions status(TranStatus status) {
        this.setStatus(status);
        return this;
    }

    public void setStatus(TranStatus status) {
        this.status = status;
    }

    public ZonedDateTime getSettlementDate() {
        return this.settlementDate;
    }

    public AmolDTransactions settlementDate(ZonedDateTime settlementDate) {
        this.setSettlementDate(settlementDate);
        return this;
    }

    public void setSettlementDate(ZonedDateTime settlementDate) {
        this.settlementDate = settlementDate;
    }

    public Currency getDebitCurrency() {
        return this.debitCurrency;
    }

    public AmolDTransactions debitCurrency(Currency debitCurrency) {
        this.setDebitCurrency(debitCurrency);
        return this;
    }

    public void setDebitCurrency(Currency debitCurrency) {
        this.debitCurrency = debitCurrency;
    }

    public ZonedDateTime getTimestamp() {
        return this.timestamp;
    }

    public AmolDTransactions timestamp(ZonedDateTime timestamp) {
        this.setTimestamp(timestamp);
        return this;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public AmolDTransactions phoneNumber(String phoneNumber) {
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public AmolDTransactions email(String email) {
        this.setEmail(email);
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPayerName() {
        return this.payerName;
    }

    public AmolDTransactions payerName(String payerName) {
        this.setPayerName(payerName);
        return this;
    }

    public void setPayerName(String payerName) {
        this.payerName = payerName;
    }

    public String getPayerAddress() {
        return this.payerAddress;
    }

    public AmolDTransactions payerAddress(String payerAddress) {
        this.setPayerAddress(payerAddress);
        return this;
    }

    public void setPayerAddress(String payerAddress) {
        this.payerAddress = payerAddress;
    }

    public String getPayerEmail() {
        return this.payerEmail;
    }

    public AmolDTransactions payerEmail(String payerEmail) {
        this.setPayerEmail(payerEmail);
        return this;
    }

    public void setPayerEmail(String payerEmail) {
        this.payerEmail = payerEmail;
    }

    public String getPayerPhoneNumber() {
        return this.payerPhoneNumber;
    }

    public AmolDTransactions payerPhoneNumber(String payerPhoneNumber) {
        this.setPayerPhoneNumber(payerPhoneNumber);
        return this;
    }

    public void setPayerPhoneNumber(String payerPhoneNumber) {
        this.payerPhoneNumber = payerPhoneNumber;
    }

    public String getPayerNarration() {
        return this.payerNarration;
    }

    public AmolDTransactions payerNarration(String payerNarration) {
        this.setPayerNarration(payerNarration);
        return this;
    }

    public void setPayerNarration(String payerNarration) {
        this.payerNarration = payerNarration;
    }

    public String getPayerBranchId() {
        return this.payerBranchId;
    }

    public AmolDTransactions payerBranchId(String payerBranchId) {
        this.setPayerBranchId(payerBranchId);
        return this;
    }

    public void setPayerBranchId(String payerBranchId) {
        this.payerBranchId = payerBranchId;
    }

    public String getBeneficiaryName() {
        return this.beneficiaryName;
    }

    public AmolDTransactions beneficiaryName(String beneficiaryName) {
        this.setBeneficiaryName(beneficiaryName);
        return this;
    }

    public void setBeneficiaryName(String beneficiaryName) {
        this.beneficiaryName = beneficiaryName;
    }

    public String getBeneficiaryAccount() {
        return this.beneficiaryAccount;
    }

    public AmolDTransactions beneficiaryAccount(String beneficiaryAccount) {
        this.setBeneficiaryAccount(beneficiaryAccount);
        return this;
    }

    public void setBeneficiaryAccount(String beneficiaryAccount) {
        this.beneficiaryAccount = beneficiaryAccount;
    }

    public String getBeneficiaryBranchId() {
        return this.beneficiaryBranchId;
    }

    public AmolDTransactions beneficiaryBranchId(String beneficiaryBranchId) {
        this.setBeneficiaryBranchId(beneficiaryBranchId);
        return this;
    }

    public void setBeneficiaryBranchId(String beneficiaryBranchId) {
        this.beneficiaryBranchId = beneficiaryBranchId;
    }

    public String getBeneficiaryPhoneNumber() {
        return this.beneficiaryPhoneNumber;
    }

    public AmolDTransactions beneficiaryPhoneNumber(String beneficiaryPhoneNumber) {
        this.setBeneficiaryPhoneNumber(beneficiaryPhoneNumber);
        return this;
    }

    public void setBeneficiaryPhoneNumber(String beneficiaryPhoneNumber) {
        this.beneficiaryPhoneNumber = beneficiaryPhoneNumber;
    }

    public TranStatus getTranStatus() {
        return this.tranStatus;
    }

    public AmolDTransactions tranStatus(TranStatus tranStatus) {
        this.setTranStatus(tranStatus);
        return this;
    }

    public void setTranStatus(TranStatus tranStatus) {
        this.tranStatus = tranStatus;
    }

    public String getNarration1() {
        return this.narration1;
    }

    public AmolDTransactions narration1(String narration1) {
        this.setNarration1(narration1);
        return this;
    }

    public void setNarration1(String narration1) {
        this.narration1 = narration1;
    }

    public String getNarration2() {
        return this.narration2;
    }

    public AmolDTransactions narration2(String narration2) {
        this.setNarration2(narration2);
        return this;
    }

    public void setNarration2(String narration2) {
        this.narration2 = narration2;
    }

    public String getNarration3() {
        return this.narration3;
    }

    public AmolDTransactions narration3(String narration3) {
        this.setNarration3(narration3);
        return this;
    }

    public void setNarration3(String narration3) {
        this.narration3 = narration3;
    }

    public Integer getTaxAmount() {
        return this.taxAmount;
    }

    public AmolDTransactions taxAmount(Integer taxAmount) {
        this.setTaxAmount(taxAmount);
        return this;
    }

    public void setTaxAmount(Integer taxAmount) {
        this.taxAmount = taxAmount;
    }

    public String getTaxGLAccount() {
        return this.taxGLAccount;
    }

    public AmolDTransactions taxGLAccount(String taxGLAccount) {
        this.setTaxGLAccount(taxGLAccount);
        return this;
    }

    public void setTaxGLAccount(String taxGLAccount) {
        this.taxGLAccount = taxGLAccount;
    }

    public String getTaxNarration() {
        return this.taxNarration;
    }

    public AmolDTransactions taxNarration(String taxNarration) {
        this.setTaxNarration(taxNarration);
        return this;
    }

    public void setTaxNarration(String taxNarration) {
        this.taxNarration = taxNarration;
    }

    public String getInternalErrorCode() {
        return this.internalErrorCode;
    }

    public AmolDTransactions internalErrorCode(String internalErrorCode) {
        this.setInternalErrorCode(internalErrorCode);
        return this;
    }

    public void setInternalErrorCode(String internalErrorCode) {
        this.internalErrorCode = internalErrorCode;
    }

    public String getExternalErrorCode() {
        return this.externalErrorCode;
    }

    public AmolDTransactions externalErrorCode(String externalErrorCode) {
        this.setExternalErrorCode(externalErrorCode);
        return this;
    }

    public void setExternalErrorCode(String externalErrorCode) {
        this.externalErrorCode = externalErrorCode;
    }

    public String getPayeeBranchId() {
        return this.payeeBranchId;
    }

    public AmolDTransactions payeeBranchId(String payeeBranchId) {
        this.setPayeeBranchId(payeeBranchId);
        return this;
    }

    public void setPayeeBranchId(String payeeBranchId) {
        this.payeeBranchId = payeeBranchId;
    }

    public String getPayeeProductInstanceReference() {
        return this.payeeProductInstanceReference;
    }

    public AmolDTransactions payeeProductInstanceReference(String payeeProductInstanceReference) {
        this.setPayeeProductInstanceReference(payeeProductInstanceReference);
        return this;
    }

    public void setPayeeProductInstanceReference(String payeeProductInstanceReference) {
        this.payeeProductInstanceReference = payeeProductInstanceReference;
    }

    public String getPayeeProductCode() {
        return this.payeeProductCode;
    }

    public AmolDTransactions payeeProductCode(String payeeProductCode) {
        this.setPayeeProductCode(payeeProductCode);
        return this;
    }

    public void setPayeeProductCode(String payeeProductCode) {
        this.payeeProductCode = payeeProductCode;
    }

    public String getPayeeProductName() {
        return this.payeeProductName;
    }

    public AmolDTransactions payeeProductName(String payeeProductName) {
        this.setPayeeProductName(payeeProductName);
        return this;
    }

    public void setPayeeProductName(String payeeProductName) {
        this.payeeProductName = payeeProductName;
    }

    public String getPayeeProductDescription() {
        return this.payeeProductDescription;
    }

    public AmolDTransactions payeeProductDescription(String payeeProductDescription) {
        this.setPayeeProductDescription(payeeProductDescription);
        return this;
    }

    public void setPayeeProductDescription(String payeeProductDescription) {
        this.payeeProductDescription = payeeProductDescription;
    }

    public String getPayeeProductUnitOfMeasure() {
        return this.payeeProductUnitOfMeasure;
    }

    public AmolDTransactions payeeProductUnitOfMeasure(String payeeProductUnitOfMeasure) {
        this.setPayeeProductUnitOfMeasure(payeeProductUnitOfMeasure);
        return this;
    }

    public void setPayeeProductUnitOfMeasure(String payeeProductUnitOfMeasure) {
        this.payeeProductUnitOfMeasure = payeeProductUnitOfMeasure;
    }

    public String getPayeeProductQuantity() {
        return this.payeeProductQuantity;
    }

    public AmolDTransactions payeeProductQuantity(String payeeProductQuantity) {
        this.setPayeeProductQuantity(payeeProductQuantity);
        return this;
    }

    public void setPayeeProductQuantity(String payeeProductQuantity) {
        this.payeeProductQuantity = payeeProductQuantity;
    }

    public String getSubCategoryCode() {
        return this.subCategoryCode;
    }

    public AmolDTransactions subCategoryCode(String subCategoryCode) {
        this.setSubCategoryCode(subCategoryCode);
        return this;
    }

    public void setSubCategoryCode(String subCategoryCode) {
        this.subCategoryCode = subCategoryCode;
    }

    public String getPayerBankCode() {
        return this.payerBankCode;
    }

    public AmolDTransactions payerBankCode(String payerBankCode) {
        this.setPayerBankCode(payerBankCode);
        return this;
    }

    public void setPayerBankCode(String payerBankCode) {
        this.payerBankCode = payerBankCode;
    }

    public String getPayerBankName() {
        return this.payerBankName;
    }

    public AmolDTransactions payerBankName(String payerBankName) {
        this.setPayerBankName(payerBankName);
        return this;
    }

    public void setPayerBankName(String payerBankName) {
        this.payerBankName = payerBankName;
    }

    public String getPayerBankAddress() {
        return this.payerBankAddress;
    }

    public AmolDTransactions payerBankAddress(String payerBankAddress) {
        this.setPayerBankAddress(payerBankAddress);
        return this;
    }

    public void setPayerBankAddress(String payerBankAddress) {
        this.payerBankAddress = payerBankAddress;
    }

    public String getPayerBankCity() {
        return this.payerBankCity;
    }

    public AmolDTransactions payerBankCity(String payerBankCity) {
        this.setPayerBankCity(payerBankCity);
        return this;
    }

    public void setPayerBankCity(String payerBankCity) {
        this.payerBankCity = payerBankCity;
    }

    public String getPayerBankState() {
        return this.payerBankState;
    }

    public AmolDTransactions payerBankState(String payerBankState) {
        this.setPayerBankState(payerBankState);
        return this;
    }

    public void setPayerBankState(String payerBankState) {
        this.payerBankState = payerBankState;
    }

    public String getPayerBankCountry() {
        return this.payerBankCountry;
    }

    public AmolDTransactions payerBankCountry(String payerBankCountry) {
        this.setPayerBankCountry(payerBankCountry);
        return this;
    }

    public void setPayerBankCountry(String payerBankCountry) {
        this.payerBankCountry = payerBankCountry;
    }

    public String getPayerBankPostalCode() {
        return this.payerBankPostalCode;
    }

    public AmolDTransactions payerBankPostalCode(String payerBankPostalCode) {
        this.setPayerBankPostalCode(payerBankPostalCode);
        return this;
    }

    public void setPayerBankPostalCode(String payerBankPostalCode) {
        this.payerBankPostalCode = payerBankPostalCode;
    }

    public String getCheckerId() {
        return this.checkerId;
    }

    public AmolDTransactions checkerId(String checkerId) {
        this.setCheckerId(checkerId);
        return this;
    }

    public void setCheckerId(String checkerId) {
        this.checkerId = checkerId;
    }

    public String getRemittanceInformation() {
        return this.remittanceInformation;
    }

    public AmolDTransactions remittanceInformation(String remittanceInformation) {
        this.setRemittanceInformation(remittanceInformation);
        return this;
    }

    public void setRemittanceInformation(String remittanceInformation) {
        this.remittanceInformation = remittanceInformation;
    }

    public Channel getPaymentChannelCode() {
        return this.paymentChannelCode;
    }

    public AmolDTransactions paymentChannelCode(Channel paymentChannelCode) {
        this.setPaymentChannelCode(paymentChannelCode);
        return this;
    }

    public void setPaymentChannelCode(Channel paymentChannelCode) {
        this.paymentChannelCode = paymentChannelCode;
    }

    public Integer getFeeAmount() {
        return this.feeAmount;
    }

    public AmolDTransactions feeAmount(Integer feeAmount) {
        this.setFeeAmount(feeAmount);
        return this;
    }

    public void setFeeAmount(Integer feeAmount) {
        this.feeAmount = feeAmount;
    }

    public String getFeeGLAccount() {
        return this.feeGLAccount;
    }

    public AmolDTransactions feeGLAccount(String feeGLAccount) {
        this.setFeeGLAccount(feeGLAccount);
        return this;
    }

    public void setFeeGLAccount(String feeGLAccount) {
        this.feeGLAccount = feeGLAccount;
    }

    public String getFeeNarration() {
        return this.feeNarration;
    }

    public AmolDTransactions feeNarration(String feeNarration) {
        this.setFeeNarration(feeNarration);
        return this;
    }

    public void setFeeNarration(String feeNarration) {
        this.feeNarration = feeNarration;
    }

    public String getTranFreeField1() {
        return this.tranFreeField1;
    }

    public AmolDTransactions tranFreeField1(String tranFreeField1) {
        this.setTranFreeField1(tranFreeField1);
        return this;
    }

    public void setTranFreeField1(String tranFreeField1) {
        this.tranFreeField1 = tranFreeField1;
    }

    public String getTranFreeField2() {
        return this.tranFreeField2;
    }

    public AmolDTransactions tranFreeField2(String tranFreeField2) {
        this.setTranFreeField2(tranFreeField2);
        return this;
    }

    public void setTranFreeField2(String tranFreeField2) {
        this.tranFreeField2 = tranFreeField2;
    }

    public String getTranFreeField3() {
        return this.tranFreeField3;
    }

    public AmolDTransactions tranFreeField3(String tranFreeField3) {
        this.setTranFreeField3(tranFreeField3);
        return this;
    }

    public void setTranFreeField3(String tranFreeField3) {
        this.tranFreeField3 = tranFreeField3;
    }

    public String getTranFreeField4() {
        return this.tranFreeField4;
    }

    public AmolDTransactions tranFreeField4(String tranFreeField4) {
        this.setTranFreeField4(tranFreeField4);
        return this;
    }

    public void setTranFreeField4(String tranFreeField4) {
        this.tranFreeField4 = tranFreeField4;
    }

    public String getTranFreeField5() {
        return this.tranFreeField5;
    }

    public AmolDTransactions tranFreeField5(String tranFreeField5) {
        this.setTranFreeField5(tranFreeField5);
        return this;
    }

    public void setTranFreeField5(String tranFreeField5) {
        this.tranFreeField5 = tranFreeField5;
    }

    public String getTranFreeField6() {
        return this.tranFreeField6;
    }

    public AmolDTransactions tranFreeField6(String tranFreeField6) {
        this.setTranFreeField6(tranFreeField6);
        return this;
    }

    public void setTranFreeField6(String tranFreeField6) {
        this.tranFreeField6 = tranFreeField6;
    }

    public String getTranFreeField7() {
        return this.tranFreeField7;
    }

    public AmolDTransactions tranFreeField7(String tranFreeField7) {
        this.setTranFreeField7(tranFreeField7);
        return this;
    }

    public void setTranFreeField7(String tranFreeField7) {
        this.tranFreeField7 = tranFreeField7;
    }

    public String getTranFreeField8() {
        return this.tranFreeField8;
    }

    public AmolDTransactions tranFreeField8(String tranFreeField8) {
        this.setTranFreeField8(tranFreeField8);
        return this;
    }

    public void setTranFreeField8(String tranFreeField8) {
        this.tranFreeField8 = tranFreeField8;
    }

    public String getTranFreeField9() {
        return this.tranFreeField9;
    }

    public AmolDTransactions tranFreeField9(String tranFreeField9) {
        this.setTranFreeField9(tranFreeField9);
        return this;
    }

    public void setTranFreeField9(String tranFreeField9) {
        this.tranFreeField9 = tranFreeField9;
    }

    public String getTranFreeField10() {
        return this.tranFreeField10;
    }

    public AmolDTransactions tranFreeField10(String tranFreeField10) {
        this.setTranFreeField10(tranFreeField10);
        return this;
    }

    public void setTranFreeField10(String tranFreeField10) {
        this.tranFreeField10 = tranFreeField10;
    }

    public String getTranFreeField11() {
        return this.tranFreeField11;
    }

    public AmolDTransactions tranFreeField11(String tranFreeField11) {
        this.setTranFreeField11(tranFreeField11);
        return this;
    }

    public void setTranFreeField11(String tranFreeField11) {
        this.tranFreeField11 = tranFreeField11;
    }

    public String getTranFreeField12() {
        return this.tranFreeField12;
    }

    public AmolDTransactions tranFreeField12(String tranFreeField12) {
        this.setTranFreeField12(tranFreeField12);
        return this;
    }

    public void setTranFreeField12(String tranFreeField12) {
        this.tranFreeField12 = tranFreeField12;
    }

    public String getTranFreeField13() {
        return this.tranFreeField13;
    }

    public AmolDTransactions tranFreeField13(String tranFreeField13) {
        this.setTranFreeField13(tranFreeField13);
        return this;
    }

    public void setTranFreeField13(String tranFreeField13) {
        this.tranFreeField13 = tranFreeField13;
    }

    public String getTranFreeField14() {
        return this.tranFreeField14;
    }

    public AmolDTransactions tranFreeField14(String tranFreeField14) {
        this.setTranFreeField14(tranFreeField14);
        return this;
    }

    public void setTranFreeField14(String tranFreeField14) {
        this.tranFreeField14 = tranFreeField14;
    }

    public String getTranFreeField15() {
        return this.tranFreeField15;
    }

    public AmolDTransactions tranFreeField15(String tranFreeField15) {
        this.setTranFreeField15(tranFreeField15);
        return this;
    }

    public void setTranFreeField15(String tranFreeField15) {
        this.tranFreeField15 = tranFreeField15;
    }

    public String getTranFreeField16() {
        return this.tranFreeField16;
    }

    public AmolDTransactions tranFreeField16(String tranFreeField16) {
        this.setTranFreeField16(tranFreeField16);
        return this;
    }

    public void setTranFreeField16(String tranFreeField16) {
        this.tranFreeField16 = tranFreeField16;
    }

    public String getTranFreeField17() {
        return this.tranFreeField17;
    }

    public AmolDTransactions tranFreeField17(String tranFreeField17) {
        this.setTranFreeField17(tranFreeField17);
        return this;
    }

    public void setTranFreeField17(String tranFreeField17) {
        this.tranFreeField17 = tranFreeField17;
    }

    public BigDecimal getTranFreeField18() {
        return this.tranFreeField18;
    }

    public AmolDTransactions tranFreeField18(BigDecimal tranFreeField18) {
        this.setTranFreeField18(tranFreeField18);
        return this;
    }

    public void setTranFreeField18(BigDecimal tranFreeField18) {
        this.tranFreeField18 = tranFreeField18 != null ? tranFreeField18.stripTrailingZeros() : null;
    }

    public Integer getTranFreeField19() {
        return this.tranFreeField19;
    }

    public AmolDTransactions tranFreeField19(Integer tranFreeField19) {
        this.setTranFreeField19(tranFreeField19);
        return this;
    }

    public void setTranFreeField19(Integer tranFreeField19) {
        this.tranFreeField19 = tranFreeField19;
    }

    public Boolean getTranFreeField20() {
        return this.tranFreeField20;
    }

    public AmolDTransactions tranFreeField20(Boolean tranFreeField20) {
        this.setTranFreeField20(tranFreeField20);
        return this;
    }

    public void setTranFreeField20(Boolean tranFreeField20) {
        this.tranFreeField20 = tranFreeField20;
    }

    public String getTranFreeField21() {
        return this.tranFreeField21;
    }

    public AmolDTransactions tranFreeField21(String tranFreeField21) {
        this.setTranFreeField21(tranFreeField21);
        return this;
    }

    public void setTranFreeField21(String tranFreeField21) {
        this.tranFreeField21 = tranFreeField21;
    }

    public String getTranFreeField22() {
        return this.tranFreeField22;
    }

    public AmolDTransactions tranFreeField22(String tranFreeField22) {
        this.setTranFreeField22(tranFreeField22);
        return this;
    }

    public void setTranFreeField22(String tranFreeField22) {
        this.tranFreeField22 = tranFreeField22;
    }

    public byte[] getTranFreeField23() {
        return this.tranFreeField23;
    }

    public AmolDTransactions tranFreeField23(byte[] tranFreeField23) {
        this.setTranFreeField23(tranFreeField23);
        return this;
    }

    public void setTranFreeField23(byte[] tranFreeField23) {
        this.tranFreeField23 = tranFreeField23;
    }

    public String getTranFreeField23ContentType() {
        return this.tranFreeField23ContentType;
    }

    public AmolDTransactions tranFreeField23ContentType(String tranFreeField23ContentType) {
        this.tranFreeField23ContentType = tranFreeField23ContentType;
        return this;
    }

    public void setTranFreeField23ContentType(String tranFreeField23ContentType) {
        this.tranFreeField23ContentType = tranFreeField23ContentType;
    }

    public ZonedDateTime getTranFreeField24() {
        return this.tranFreeField24;
    }

    public AmolDTransactions tranFreeField24(ZonedDateTime tranFreeField24) {
        this.setTranFreeField24(tranFreeField24);
        return this;
    }

    public void setTranFreeField24(ZonedDateTime tranFreeField24) {
        this.tranFreeField24 = tranFreeField24;
    }

    public String getResponseCode() {
        return this.responseCode;
    }

    public AmolDTransactions responseCode(String responseCode) {
        this.setResponseCode(responseCode);
        return this;
    }

    public void setResponseCode(String responseCode) {
        this.responseCode = responseCode;
    }

    public String getResponseMessage() {
        return this.responseMessage;
    }

    public AmolDTransactions responseMessage(String responseMessage) {
        this.setResponseMessage(responseMessage);
        return this;
    }

    public void setResponseMessage(String responseMessage) {
        this.responseMessage = responseMessage;
    }

    public String getResponseDetails() {
        return this.responseDetails;
    }

    public AmolDTransactions responseDetails(String responseDetails) {
        this.setResponseDetails(responseDetails);
        return this;
    }

    public void setResponseDetails(String responseDetails) {
        this.responseDetails = responseDetails;
    }

    public String getTransferReferenceId() {
        return this.transferReferenceId;
    }

    public AmolDTransactions transferReferenceId(String transferReferenceId) {
        this.setTransferReferenceId(transferReferenceId);
        return this;
    }

    public void setTransferReferenceId(String transferReferenceId) {
        this.transferReferenceId = transferReferenceId;
    }

    public String getTransferStatus() {
        return this.transferStatus;
    }

    public AmolDTransactions transferStatus(String transferStatus) {
        this.setTransferStatus(transferStatus);
        return this;
    }

    public void setTransferStatus(String transferStatus) {
        this.transferStatus = transferStatus;
    }

    public ZonedDateTime getResponseDateTime() {
        return this.responseDateTime;
    }

    public AmolDTransactions responseDateTime(ZonedDateTime responseDateTime) {
        this.setResponseDateTime(responseDateTime);
        return this;
    }

    public void setResponseDateTime(ZonedDateTime responseDateTime) {
        this.responseDateTime = responseDateTime;
    }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public AmolDTransactions createdAt(ZonedDateTime createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public AmolDTransactions createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public AmolDTransactions updatedAt(ZonedDateTime updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public AmolDTransactions updatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public DTransaction getDTransaction() {
        return this.dTransaction;
    }

    public void setDTransaction(DTransaction dTransaction) {
        this.dTransaction = dTransaction;
        this.dTransactionId = dTransaction != null ? dTransaction.getId() : null;
    }

    public AmolDTransactions dTransaction(DTransaction dTransaction) {
        this.setDTransaction(dTransaction);
        return this;
    }

    public Long getDTransactionId() {
        return this.dTransactionId;
    }

    public void setDTransactionId(Long dTransaction) {
        this.dTransactionId = dTransaction;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AmolDTransactions)) {
            return false;
        }
        return id != null && id.equals(((AmolDTransactions) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "AmolDTransactions{" +
            "id=" + getId() +
            ", absaTranRef='" + getAbsaTranRef() + "'" +
            ", billerId='" + getBillerId() + "'" +
            ", dtDTransactionId='" + getDtDTransactionId() + "'" +
            ", amolDTransactionId='" + getAmolDTransactionId() + "'" +
            ", transactionReferenceNumber='" + getTransactionReferenceNumber() + "'" +
            ", token='" + getToken() + "'" +
            ", transferId='" + getTransferId() + "'" +
            ", externalTxnId='" + getExternalTxnId() + "'" +
            ", customerReference='" + getCustomerReference() + "'" +
            ", debitAccountNumber='" + getDebitAccountNumber() + "'" +
            ", creditAccountNumber='" + getCreditAccountNumber() + "'" +
            ", debitAmount=" + getDebitAmount() +
            ", creditAmount=" + getCreditAmount() +
            ", transactionAmount=" + getTransactionAmount() +
            ", debitDate='" + getDebitDate() + "'" +
            ", creditDate='" + getCreditDate() + "'" +
            ", status='" + getStatus() + "'" +
            ", settlementDate='" + getSettlementDate() + "'" +
            ", debitCurrency='" + getDebitCurrency() + "'" +
            ", timestamp='" + getTimestamp() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", email='" + getEmail() + "'" +
            ", payerName='" + getPayerName() + "'" +
            ", payerAddress='" + getPayerAddress() + "'" +
            ", payerEmail='" + getPayerEmail() + "'" +
            ", payerPhoneNumber='" + getPayerPhoneNumber() + "'" +
            ", payerNarration='" + getPayerNarration() + "'" +
            ", payerBranchId='" + getPayerBranchId() + "'" +
            ", beneficiaryName='" + getBeneficiaryName() + "'" +
            ", beneficiaryAccount='" + getBeneficiaryAccount() + "'" +
            ", beneficiaryBranchId='" + getBeneficiaryBranchId() + "'" +
            ", beneficiaryPhoneNumber='" + getBeneficiaryPhoneNumber() + "'" +
            ", tranStatus='" + getTranStatus() + "'" +
            ", narration1='" + getNarration1() + "'" +
            ", narration2='" + getNarration2() + "'" +
            ", narration3='" + getNarration3() + "'" +
            ", taxAmount=" + getTaxAmount() +
            ", taxGLAccount='" + getTaxGLAccount() + "'" +
            ", taxNarration='" + getTaxNarration() + "'" +
            ", internalErrorCode='" + getInternalErrorCode() + "'" +
            ", externalErrorCode='" + getExternalErrorCode() + "'" +
            ", payeeBranchId='" + getPayeeBranchId() + "'" +
            ", payeeProductInstanceReference='" + getPayeeProductInstanceReference() + "'" +
            ", payeeProductCode='" + getPayeeProductCode() + "'" +
            ", payeeProductName='" + getPayeeProductName() + "'" +
            ", payeeProductDescription='" + getPayeeProductDescription() + "'" +
            ", payeeProductUnitOfMeasure='" + getPayeeProductUnitOfMeasure() + "'" +
            ", payeeProductQuantity='" + getPayeeProductQuantity() + "'" +
            ", subCategoryCode='" + getSubCategoryCode() + "'" +
            ", payerBankCode='" + getPayerBankCode() + "'" +
            ", payerBankName='" + getPayerBankName() + "'" +
            ", payerBankAddress='" + getPayerBankAddress() + "'" +
            ", payerBankCity='" + getPayerBankCity() + "'" +
            ", payerBankState='" + getPayerBankState() + "'" +
            ", payerBankCountry='" + getPayerBankCountry() + "'" +
            ", payerBankPostalCode='" + getPayerBankPostalCode() + "'" +
            ", checkerId='" + getCheckerId() + "'" +
            ", remittanceInformation='" + getRemittanceInformation() + "'" +
            ", paymentChannelCode='" + getPaymentChannelCode() + "'" +
            ", feeAmount=" + getFeeAmount() +
            ", feeGLAccount='" + getFeeGLAccount() + "'" +
            ", feeNarration='" + getFeeNarration() + "'" +
            ", tranFreeField1='" + getTranFreeField1() + "'" +
            ", tranFreeField2='" + getTranFreeField2() + "'" +
            ", tranFreeField3='" + getTranFreeField3() + "'" +
            ", tranFreeField4='" + getTranFreeField4() + "'" +
            ", tranFreeField5='" + getTranFreeField5() + "'" +
            ", tranFreeField6='" + getTranFreeField6() + "'" +
            ", tranFreeField7='" + getTranFreeField7() + "'" +
            ", tranFreeField8='" + getTranFreeField8() + "'" +
            ", tranFreeField9='" + getTranFreeField9() + "'" +
            ", tranFreeField10='" + getTranFreeField10() + "'" +
            ", tranFreeField11='" + getTranFreeField11() + "'" +
            ", tranFreeField12='" + getTranFreeField12() + "'" +
            ", tranFreeField13='" + getTranFreeField13() + "'" +
            ", tranFreeField14='" + getTranFreeField14() + "'" +
            ", tranFreeField15='" + getTranFreeField15() + "'" +
            ", tranFreeField16='" + getTranFreeField16() + "'" +
            ", tranFreeField17='" + getTranFreeField17() + "'" +
            ", tranFreeField18=" + getTranFreeField18() +
            ", tranFreeField19=" + getTranFreeField19() +
            ", tranFreeField20='" + getTranFreeField20() + "'" +
            ", tranFreeField21='" + getTranFreeField21() + "'" +
            ", tranFreeField22='" + getTranFreeField22() + "'" +
            ", tranFreeField23='" + getTranFreeField23() + "'" +
            ", tranFreeField23ContentType='" + getTranFreeField23ContentType() + "'" +
            ", tranFreeField24='" + getTranFreeField24() + "'" +
            ", responseCode='" + getResponseCode() + "'" +
            ", responseMessage='" + getResponseMessage() + "'" +
            ", responseDetails='" + getResponseDetails() + "'" +
            ", transferReferenceId='" + getTransferReferenceId() + "'" +
            ", transferStatus='" + getTransferStatus() + "'" +
            ", responseDateTime='" + getResponseDateTime() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            "}";
    }
}
