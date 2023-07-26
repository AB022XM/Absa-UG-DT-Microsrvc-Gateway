package ug.co.absa.microsrvc.gateway.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.validation.constraints.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

/**
 * A Customer.
 */
@Table("customer")
@org.springframework.data.elasticsearch.annotations.Document(indexName = "customer")
@SuppressWarnings("common-java:DuplicatedBlocks")
public class Customer implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @Column("id")
    private Long id;

    @Column("absa_tran_ref")
    private UUID absaTranRef;

    @NotNull(message = "must not be null")
    @Column("biller_id")
    private String billerId;

    @NotNull(message = "must not be null")
    @Column("payment_item_code")
    private String paymentItemCode;

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

    @Column("request_id")
    private Integer requestId;

    @NotNull(message = "must not be null")
    @Column("account_name")
    private String accountName;

    @NotNull(message = "must not be null")
    @Column("return_code")
    private String returnCode;

    @NotNull(message = "must not be null")
    @Column("return_message")
    private String returnMessage;

    @NotNull(message = "must not be null")
    @Column("external_tx_nid")
    private String externalTXNid;

    @NotNull(message = "must not be null")
    @Column("transaction_date")
    private ZonedDateTime transactionDate;

    @NotNull(message = "must not be null")
    @Column("customer_id")
    private String customerId;

    @NotNull(message = "must not be null")
    @Column("customer_product")
    private String customerProduct;

    @NotNull(message = "must not be null")
    @Column("customer_type")
    private String customerType;

    @NotNull(message = "must not be null")
    @Column("account_category")
    private String accountCategory;

    @NotNull(message = "must not be null")
    @Column("account_type")
    private String accountType;

    @NotNull(message = "must not be null")
    @Column("account_number")
    private String accountNumber;

    @NotNull(message = "must not be null")
    @Column("phone_number")
    private String phoneNumber;

    @NotNull(message = "must not be null")
    @Column("channel")
    private String channel;

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

    @Column("tran_free_field_25")
    private String tranFreeField25;

    @Column("tran_free_field_26")
    private String tranFreeField26;

    @Column("tran_free_field_27")
    private String tranFreeField27;

    @Column("tran_free_field_28")
    private String tranFreeField28;

    @Column("tran_free_field_29")
    private String tranFreeField29;

    @Column("tran_free_field_30")
    private String tranFreeField30;

    @Column("tran_free_field_31")
    private String tranFreeField31;

    @Column("tran_free_field_32")
    private String tranFreeField32;

    @Column("tran_free_field_33")
    private String tranFreeField33;

    @Column("created_at")
    private ZonedDateTime createdAt;

    @Column("created_by")
    private String createdBy;

    @Column("updated_at")
    private ZonedDateTime updatedAt;

    @Column("updated_by")
    private String updatedBy;

    @Transient
    @Transient
    @Transient
    @Transient
    @JsonIgnoreProperties(
        value = { "transaction", "transaction", "transaction", "transactions", "transactions", "customer" },
        allowSetters = true
    )
    private Set<DTransaction> customers = new HashSet<>();

    @Transient
    @Transient
    @Transient
    @Transient
    @JsonIgnoreProperties(value = { "customer" }, allowSetters = true)
    private Set<DTransactionSummary> customers = new HashSet<>();

    @Transient
    @Transient
    @Transient
    @Transient
    @JsonIgnoreProperties(value = { "customer" }, allowSetters = true)
    private Set<DTransactionDetails> customers = new HashSet<>();

    @Transient
    @Transient
    @Transient
    @Transient
    @JsonIgnoreProperties(value = { "customer" }, allowSetters = true)
    private Set<DTransactionHistory> customers = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here

    public Long getId() {
        return this.id;
    }

    public Customer id(Long id) {
        this.setId(id);
        return this;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getAbsaTranRef() {
        return this.absaTranRef;
    }

    public Customer absaTranRef(UUID absaTranRef) {
        this.setAbsaTranRef(absaTranRef);
        return this;
    }

    public void setAbsaTranRef(UUID absaTranRef) {
        this.absaTranRef = absaTranRef;
    }

    public String getBillerId() {
        return this.billerId;
    }

    public Customer billerId(String billerId) {
        this.setBillerId(billerId);
        return this;
    }

    public void setBillerId(String billerId) {
        this.billerId = billerId;
    }

    public String getPaymentItemCode() {
        return this.paymentItemCode;
    }

    public Customer paymentItemCode(String paymentItemCode) {
        this.setPaymentItemCode(paymentItemCode);
        return this;
    }

    public void setPaymentItemCode(String paymentItemCode) {
        this.paymentItemCode = paymentItemCode;
    }

    public String getDtDTransactionId() {
        return this.dtDTransactionId;
    }

    public Customer dtDTransactionId(String dtDTransactionId) {
        this.setDtDTransactionId(dtDTransactionId);
        return this;
    }

    public void setDtDTransactionId(String dtDTransactionId) {
        this.dtDTransactionId = dtDTransactionId;
    }

    public String getAmolDTransactionId() {
        return this.amolDTransactionId;
    }

    public Customer amolDTransactionId(String amolDTransactionId) {
        this.setAmolDTransactionId(amolDTransactionId);
        return this;
    }

    public void setAmolDTransactionId(String amolDTransactionId) {
        this.amolDTransactionId = amolDTransactionId;
    }

    public String getTransactionReferenceNumber() {
        return this.transactionReferenceNumber;
    }

    public Customer transactionReferenceNumber(String transactionReferenceNumber) {
        this.setTransactionReferenceNumber(transactionReferenceNumber);
        return this;
    }

    public void setTransactionReferenceNumber(String transactionReferenceNumber) {
        this.transactionReferenceNumber = transactionReferenceNumber;
    }

    public String getToken() {
        return this.token;
    }

    public Customer token(String token) {
        this.setToken(token);
        return this;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTransferId() {
        return this.transferId;
    }

    public Customer transferId(String transferId) {
        this.setTransferId(transferId);
        return this;
    }

    public void setTransferId(String transferId) {
        this.transferId = transferId;
    }

    public Integer getRequestId() {
        return this.requestId;
    }

    public Customer requestId(Integer requestId) {
        this.setRequestId(requestId);
        return this;
    }

    public void setRequestId(Integer requestId) {
        this.requestId = requestId;
    }

    public String getAccountName() {
        return this.accountName;
    }

    public Customer accountName(String accountName) {
        this.setAccountName(accountName);
        return this;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public String getReturnCode() {
        return this.returnCode;
    }

    public Customer returnCode(String returnCode) {
        this.setReturnCode(returnCode);
        return this;
    }

    public void setReturnCode(String returnCode) {
        this.returnCode = returnCode;
    }

    public String getReturnMessage() {
        return this.returnMessage;
    }

    public Customer returnMessage(String returnMessage) {
        this.setReturnMessage(returnMessage);
        return this;
    }

    public void setReturnMessage(String returnMessage) {
        this.returnMessage = returnMessage;
    }

    public String getExternalTXNid() {
        return this.externalTXNid;
    }

    public Customer externalTXNid(String externalTXNid) {
        this.setExternalTXNid(externalTXNid);
        return this;
    }

    public void setExternalTXNid(String externalTXNid) {
        this.externalTXNid = externalTXNid;
    }

    public ZonedDateTime getTransactionDate() {
        return this.transactionDate;
    }

    public Customer transactionDate(ZonedDateTime transactionDate) {
        this.setTransactionDate(transactionDate);
        return this;
    }

    public void setTransactionDate(ZonedDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public Customer customerId(String customerId) {
        this.setCustomerId(customerId);
        return this;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerProduct() {
        return this.customerProduct;
    }

    public Customer customerProduct(String customerProduct) {
        this.setCustomerProduct(customerProduct);
        return this;
    }

    public void setCustomerProduct(String customerProduct) {
        this.customerProduct = customerProduct;
    }

    public String getCustomerType() {
        return this.customerType;
    }

    public Customer customerType(String customerType) {
        this.setCustomerType(customerType);
        return this;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getAccountCategory() {
        return this.accountCategory;
    }

    public Customer accountCategory(String accountCategory) {
        this.setAccountCategory(accountCategory);
        return this;
    }

    public void setAccountCategory(String accountCategory) {
        this.accountCategory = accountCategory;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public Customer accountType(String accountType) {
        this.setAccountType(accountType);
        return this;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public Customer accountNumber(String accountNumber) {
        this.setAccountNumber(accountNumber);
        return this;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public Customer phoneNumber(String phoneNumber) {
        this.setPhoneNumber(phoneNumber);
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getChannel() {
        return this.channel;
    }

    public Customer channel(String channel) {
        this.setChannel(channel);
        return this;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getTranFreeField1() {
        return this.tranFreeField1;
    }

    public Customer tranFreeField1(String tranFreeField1) {
        this.setTranFreeField1(tranFreeField1);
        return this;
    }

    public void setTranFreeField1(String tranFreeField1) {
        this.tranFreeField1 = tranFreeField1;
    }

    public String getTranFreeField2() {
        return this.tranFreeField2;
    }

    public Customer tranFreeField2(String tranFreeField2) {
        this.setTranFreeField2(tranFreeField2);
        return this;
    }

    public void setTranFreeField2(String tranFreeField2) {
        this.tranFreeField2 = tranFreeField2;
    }

    public String getTranFreeField3() {
        return this.tranFreeField3;
    }

    public Customer tranFreeField3(String tranFreeField3) {
        this.setTranFreeField3(tranFreeField3);
        return this;
    }

    public void setTranFreeField3(String tranFreeField3) {
        this.tranFreeField3 = tranFreeField3;
    }

    public String getTranFreeField4() {
        return this.tranFreeField4;
    }

    public Customer tranFreeField4(String tranFreeField4) {
        this.setTranFreeField4(tranFreeField4);
        return this;
    }

    public void setTranFreeField4(String tranFreeField4) {
        this.tranFreeField4 = tranFreeField4;
    }

    public String getTranFreeField5() {
        return this.tranFreeField5;
    }

    public Customer tranFreeField5(String tranFreeField5) {
        this.setTranFreeField5(tranFreeField5);
        return this;
    }

    public void setTranFreeField5(String tranFreeField5) {
        this.tranFreeField5 = tranFreeField5;
    }

    public String getTranFreeField6() {
        return this.tranFreeField6;
    }

    public Customer tranFreeField6(String tranFreeField6) {
        this.setTranFreeField6(tranFreeField6);
        return this;
    }

    public void setTranFreeField6(String tranFreeField6) {
        this.tranFreeField6 = tranFreeField6;
    }

    public String getTranFreeField7() {
        return this.tranFreeField7;
    }

    public Customer tranFreeField7(String tranFreeField7) {
        this.setTranFreeField7(tranFreeField7);
        return this;
    }

    public void setTranFreeField7(String tranFreeField7) {
        this.tranFreeField7 = tranFreeField7;
    }

    public String getTranFreeField8() {
        return this.tranFreeField8;
    }

    public Customer tranFreeField8(String tranFreeField8) {
        this.setTranFreeField8(tranFreeField8);
        return this;
    }

    public void setTranFreeField8(String tranFreeField8) {
        this.tranFreeField8 = tranFreeField8;
    }

    public String getTranFreeField9() {
        return this.tranFreeField9;
    }

    public Customer tranFreeField9(String tranFreeField9) {
        this.setTranFreeField9(tranFreeField9);
        return this;
    }

    public void setTranFreeField9(String tranFreeField9) {
        this.tranFreeField9 = tranFreeField9;
    }

    public String getTranFreeField10() {
        return this.tranFreeField10;
    }

    public Customer tranFreeField10(String tranFreeField10) {
        this.setTranFreeField10(tranFreeField10);
        return this;
    }

    public void setTranFreeField10(String tranFreeField10) {
        this.tranFreeField10 = tranFreeField10;
    }

    public String getTranFreeField11() {
        return this.tranFreeField11;
    }

    public Customer tranFreeField11(String tranFreeField11) {
        this.setTranFreeField11(tranFreeField11);
        return this;
    }

    public void setTranFreeField11(String tranFreeField11) {
        this.tranFreeField11 = tranFreeField11;
    }

    public String getTranFreeField12() {
        return this.tranFreeField12;
    }

    public Customer tranFreeField12(String tranFreeField12) {
        this.setTranFreeField12(tranFreeField12);
        return this;
    }

    public void setTranFreeField12(String tranFreeField12) {
        this.tranFreeField12 = tranFreeField12;
    }

    public String getTranFreeField13() {
        return this.tranFreeField13;
    }

    public Customer tranFreeField13(String tranFreeField13) {
        this.setTranFreeField13(tranFreeField13);
        return this;
    }

    public void setTranFreeField13(String tranFreeField13) {
        this.tranFreeField13 = tranFreeField13;
    }

    public String getTranFreeField14() {
        return this.tranFreeField14;
    }

    public Customer tranFreeField14(String tranFreeField14) {
        this.setTranFreeField14(tranFreeField14);
        return this;
    }

    public void setTranFreeField14(String tranFreeField14) {
        this.tranFreeField14 = tranFreeField14;
    }

    public String getTranFreeField15() {
        return this.tranFreeField15;
    }

    public Customer tranFreeField15(String tranFreeField15) {
        this.setTranFreeField15(tranFreeField15);
        return this;
    }

    public void setTranFreeField15(String tranFreeField15) {
        this.tranFreeField15 = tranFreeField15;
    }

    public String getTranFreeField16() {
        return this.tranFreeField16;
    }

    public Customer tranFreeField16(String tranFreeField16) {
        this.setTranFreeField16(tranFreeField16);
        return this;
    }

    public void setTranFreeField16(String tranFreeField16) {
        this.tranFreeField16 = tranFreeField16;
    }

    public String getTranFreeField17() {
        return this.tranFreeField17;
    }

    public Customer tranFreeField17(String tranFreeField17) {
        this.setTranFreeField17(tranFreeField17);
        return this;
    }

    public void setTranFreeField17(String tranFreeField17) {
        this.tranFreeField17 = tranFreeField17;
    }

    public BigDecimal getTranFreeField18() {
        return this.tranFreeField18;
    }

    public Customer tranFreeField18(BigDecimal tranFreeField18) {
        this.setTranFreeField18(tranFreeField18);
        return this;
    }

    public void setTranFreeField18(BigDecimal tranFreeField18) {
        this.tranFreeField18 = tranFreeField18 != null ? tranFreeField18.stripTrailingZeros() : null;
    }

    public Integer getTranFreeField19() {
        return this.tranFreeField19;
    }

    public Customer tranFreeField19(Integer tranFreeField19) {
        this.setTranFreeField19(tranFreeField19);
        return this;
    }

    public void setTranFreeField19(Integer tranFreeField19) {
        this.tranFreeField19 = tranFreeField19;
    }

    public Boolean getTranFreeField20() {
        return this.tranFreeField20;
    }

    public Customer tranFreeField20(Boolean tranFreeField20) {
        this.setTranFreeField20(tranFreeField20);
        return this;
    }

    public void setTranFreeField20(Boolean tranFreeField20) {
        this.tranFreeField20 = tranFreeField20;
    }

    public String getTranFreeField21() {
        return this.tranFreeField21;
    }

    public Customer tranFreeField21(String tranFreeField21) {
        this.setTranFreeField21(tranFreeField21);
        return this;
    }

    public void setTranFreeField21(String tranFreeField21) {
        this.tranFreeField21 = tranFreeField21;
    }

    public String getTranFreeField22() {
        return this.tranFreeField22;
    }

    public Customer tranFreeField22(String tranFreeField22) {
        this.setTranFreeField22(tranFreeField22);
        return this;
    }

    public void setTranFreeField22(String tranFreeField22) {
        this.tranFreeField22 = tranFreeField22;
    }

    public byte[] getTranFreeField23() {
        return this.tranFreeField23;
    }

    public Customer tranFreeField23(byte[] tranFreeField23) {
        this.setTranFreeField23(tranFreeField23);
        return this;
    }

    public void setTranFreeField23(byte[] tranFreeField23) {
        this.tranFreeField23 = tranFreeField23;
    }

    public String getTranFreeField23ContentType() {
        return this.tranFreeField23ContentType;
    }

    public Customer tranFreeField23ContentType(String tranFreeField23ContentType) {
        this.tranFreeField23ContentType = tranFreeField23ContentType;
        return this;
    }

    public void setTranFreeField23ContentType(String tranFreeField23ContentType) {
        this.tranFreeField23ContentType = tranFreeField23ContentType;
    }

    public ZonedDateTime getTranFreeField24() {
        return this.tranFreeField24;
    }

    public Customer tranFreeField24(ZonedDateTime tranFreeField24) {
        this.setTranFreeField24(tranFreeField24);
        return this;
    }

    public void setTranFreeField24(ZonedDateTime tranFreeField24) {
        this.tranFreeField24 = tranFreeField24;
    }

    public String getTranFreeField25() {
        return this.tranFreeField25;
    }

    public Customer tranFreeField25(String tranFreeField25) {
        this.setTranFreeField25(tranFreeField25);
        return this;
    }

    public void setTranFreeField25(String tranFreeField25) {
        this.tranFreeField25 = tranFreeField25;
    }

    public String getTranFreeField26() {
        return this.tranFreeField26;
    }

    public Customer tranFreeField26(String tranFreeField26) {
        this.setTranFreeField26(tranFreeField26);
        return this;
    }

    public void setTranFreeField26(String tranFreeField26) {
        this.tranFreeField26 = tranFreeField26;
    }

    public String getTranFreeField27() {
        return this.tranFreeField27;
    }

    public Customer tranFreeField27(String tranFreeField27) {
        this.setTranFreeField27(tranFreeField27);
        return this;
    }

    public void setTranFreeField27(String tranFreeField27) {
        this.tranFreeField27 = tranFreeField27;
    }

    public String getTranFreeField28() {
        return this.tranFreeField28;
    }

    public Customer tranFreeField28(String tranFreeField28) {
        this.setTranFreeField28(tranFreeField28);
        return this;
    }

    public void setTranFreeField28(String tranFreeField28) {
        this.tranFreeField28 = tranFreeField28;
    }

    public String getTranFreeField29() {
        return this.tranFreeField29;
    }

    public Customer tranFreeField29(String tranFreeField29) {
        this.setTranFreeField29(tranFreeField29);
        return this;
    }

    public void setTranFreeField29(String tranFreeField29) {
        this.tranFreeField29 = tranFreeField29;
    }

    public String getTranFreeField30() {
        return this.tranFreeField30;
    }

    public Customer tranFreeField30(String tranFreeField30) {
        this.setTranFreeField30(tranFreeField30);
        return this;
    }

    public void setTranFreeField30(String tranFreeField30) {
        this.tranFreeField30 = tranFreeField30;
    }

    public String getTranFreeField31() {
        return this.tranFreeField31;
    }

    public Customer tranFreeField31(String tranFreeField31) {
        this.setTranFreeField31(tranFreeField31);
        return this;
    }

    public void setTranFreeField31(String tranFreeField31) {
        this.tranFreeField31 = tranFreeField31;
    }

    public String getTranFreeField32() {
        return this.tranFreeField32;
    }

    public Customer tranFreeField32(String tranFreeField32) {
        this.setTranFreeField32(tranFreeField32);
        return this;
    }

    public void setTranFreeField32(String tranFreeField32) {
        this.tranFreeField32 = tranFreeField32;
    }

    public String getTranFreeField33() {
        return this.tranFreeField33;
    }

    public Customer tranFreeField33(String tranFreeField33) {
        this.setTranFreeField33(tranFreeField33);
        return this;
    }

    public void setTranFreeField33(String tranFreeField33) {
        this.tranFreeField33 = tranFreeField33;
    }

    public ZonedDateTime getCreatedAt() {
        return this.createdAt;
    }

    public Customer createdAt(ZonedDateTime createdAt) {
        this.setCreatedAt(createdAt);
        return this;
    }

    public void setCreatedAt(ZonedDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        return this.createdBy;
    }

    public Customer createdBy(String createdBy) {
        this.setCreatedBy(createdBy);
        return this;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    public ZonedDateTime getUpdatedAt() {
        return this.updatedAt;
    }

    public Customer updatedAt(ZonedDateTime updatedAt) {
        this.setUpdatedAt(updatedAt);
        return this;
    }

    public void setUpdatedAt(ZonedDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getUpdatedBy() {
        return this.updatedBy;
    }

    public Customer updatedBy(String updatedBy) {
        this.setUpdatedBy(updatedBy);
        return this;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Set<DTransaction> getCustomers() {
        return this.customers;
    }

    public void setCustomers(Set<DTransaction> dTransactions) {
        if (this.customers != null) {
            this.customers.forEach(i -> i.setCustomer(null));
        }
        if (dTransactions != null) {
            dTransactions.forEach(i -> i.setCustomer(this));
        }
        this.customers = dTransactions;
    }

    public Customer customers(Set<DTransaction> dTransactions) {
        this.setCustomers(dTransactions);
        return this;
    }

    public Customer addCustomer(DTransaction dTransaction) {
        this.customers.add(dTransaction);
        dTransaction.setCustomer(this);
        return this;
    }

    public Customer removeCustomer(DTransaction dTransaction) {
        this.customers.remove(dTransaction);
        dTransaction.setCustomer(null);
        return this;
    }

    public Set<DTransactionSummary> getCustomers() {
        return this.customers;
    }

    public void setCustomers(Set<DTransactionSummary> dTransactionSummaries) {
        if (this.customers != null) {
            this.customers.forEach(i -> i.setCustomer(null));
        }
        if (dTransactionSummaries != null) {
            dTransactionSummaries.forEach(i -> i.setCustomer(this));
        }
        this.customers = dTransactionSummaries;
    }

    public Customer customers(Set<DTransactionSummary> dTransactionSummaries) {
        this.setCustomers(dTransactionSummaries);
        return this;
    }

    public Customer addCustomer(DTransactionSummary dTransactionSummary) {
        this.customers.add(dTransactionSummary);
        dTransactionSummary.setCustomer(this);
        return this;
    }

    public Customer removeCustomer(DTransactionSummary dTransactionSummary) {
        this.customers.remove(dTransactionSummary);
        dTransactionSummary.setCustomer(null);
        return this;
    }

    public Set<DTransactionDetails> getCustomers() {
        return this.customers;
    }

    public void setCustomers(Set<DTransactionDetails> dTransactionDetails) {
        if (this.customers != null) {
            this.customers.forEach(i -> i.setCustomer(null));
        }
        if (dTransactionDetails != null) {
            dTransactionDetails.forEach(i -> i.setCustomer(this));
        }
        this.customers = dTransactionDetails;
    }

    public Customer customers(Set<DTransactionDetails> dTransactionDetails) {
        this.setCustomers(dTransactionDetails);
        return this;
    }

    public Customer addCustomer(DTransactionDetails dTransactionDetails) {
        this.customers.add(dTransactionDetails);
        dTransactionDetails.setCustomer(this);
        return this;
    }

    public Customer removeCustomer(DTransactionDetails dTransactionDetails) {
        this.customers.remove(dTransactionDetails);
        dTransactionDetails.setCustomer(null);
        return this;
    }

    public Set<DTransactionHistory> getCustomers() {
        return this.customers;
    }

    public void setCustomers(Set<DTransactionHistory> dTransactionHistories) {
        if (this.customers != null) {
            this.customers.forEach(i -> i.setCustomer(null));
        }
        if (dTransactionHistories != null) {
            dTransactionHistories.forEach(i -> i.setCustomer(this));
        }
        this.customers = dTransactionHistories;
    }

    public Customer customers(Set<DTransactionHistory> dTransactionHistories) {
        this.setCustomers(dTransactionHistories);
        return this;
    }

    public Customer addCustomer(DTransactionHistory dTransactionHistory) {
        this.customers.add(dTransactionHistory);
        dTransactionHistory.setCustomer(this);
        return this;
    }

    public Customer removeCustomer(DTransactionHistory dTransactionHistory) {
        this.customers.remove(dTransactionHistory);
        dTransactionHistory.setCustomer(null);
        return this;
    }

    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Customer)) {
            return false;
        }
        return id != null && id.equals(((Customer) o).id);
    }

    @Override
    public int hashCode() {
        // see https://vladmihalcea.com/how-to-implement-equals-and-hashcode-using-the-jpa-entity-identifier/
        return getClass().hashCode();
    }

    // prettier-ignore
    @Override
    public String toString() {
        return "Customer{" +
            "id=" + getId() +
            ", absaTranRef='" + getAbsaTranRef() + "'" +
            ", billerId='" + getBillerId() + "'" +
            ", paymentItemCode='" + getPaymentItemCode() + "'" +
            ", dtDTransactionId='" + getDtDTransactionId() + "'" +
            ", amolDTransactionId='" + getAmolDTransactionId() + "'" +
            ", transactionReferenceNumber='" + getTransactionReferenceNumber() + "'" +
            ", token='" + getToken() + "'" +
            ", transferId='" + getTransferId() + "'" +
            ", requestId=" + getRequestId() +
            ", accountName='" + getAccountName() + "'" +
            ", returnCode='" + getReturnCode() + "'" +
            ", returnMessage='" + getReturnMessage() + "'" +
            ", externalTXNid='" + getExternalTXNid() + "'" +
            ", transactionDate='" + getTransactionDate() + "'" +
            ", customerId='" + getCustomerId() + "'" +
            ", customerProduct='" + getCustomerProduct() + "'" +
            ", customerType='" + getCustomerType() + "'" +
            ", accountCategory='" + getAccountCategory() + "'" +
            ", accountType='" + getAccountType() + "'" +
            ", accountNumber='" + getAccountNumber() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", channel='" + getChannel() + "'" +
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
            ", tranFreeField25='" + getTranFreeField25() + "'" +
            ", tranFreeField26='" + getTranFreeField26() + "'" +
            ", tranFreeField27='" + getTranFreeField27() + "'" +
            ", tranFreeField28='" + getTranFreeField28() + "'" +
            ", tranFreeField29='" + getTranFreeField29() + "'" +
            ", tranFreeField30='" + getTranFreeField30() + "'" +
            ", tranFreeField31='" + getTranFreeField31() + "'" +
            ", tranFreeField32='" + getTranFreeField32() + "'" +
            ", tranFreeField33='" + getTranFreeField33() + "'" +
            ", createdAt='" + getCreatedAt() + "'" +
            ", createdBy='" + getCreatedBy() + "'" +
            ", updatedAt='" + getUpdatedAt() + "'" +
            ", updatedBy='" + getUpdatedBy() + "'" +
            "}";
    }
}
