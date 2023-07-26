package ug.co.absa.microsrvc.gateway.web.rest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.awaitility.Awaitility.await;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.*;
import static ug.co.absa.microsrvc.gateway.web.rest.TestUtil.sameInstant;
import static ug.co.absa.microsrvc.gateway.web.rest.TestUtil.sameNumber;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import org.apache.commons.collections4.IterableUtils;
import org.assertj.core.util.IterableUtil;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.AutoConfigureWebTestClient;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.util.Base64Utils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import ug.co.absa.microsrvc.gateway.IntegrationTest;
import ug.co.absa.microsrvc.gateway.domain.AmolDTransactions;
import ug.co.absa.microsrvc.gateway.domain.enumeration.Channel;
import ug.co.absa.microsrvc.gateway.domain.enumeration.Currency;
import ug.co.absa.microsrvc.gateway.domain.enumeration.TranStatus;
import ug.co.absa.microsrvc.gateway.domain.enumeration.TranStatus;
import ug.co.absa.microsrvc.gateway.repository.AmolDTransactionsRepository;
import ug.co.absa.microsrvc.gateway.repository.EntityManager;
import ug.co.absa.microsrvc.gateway.repository.search.AmolDTransactionsSearchRepository;

/**
 * Integration tests for the {@link AmolDTransactionsResource} REST controller.
 */
@IntegrationTest
@AutoConfigureWebTestClient(timeout = IntegrationTest.DEFAULT_ENTITY_TIMEOUT)
@WithMockUser
class AmolDTransactionsResourceIT {

    private static final UUID DEFAULT_ABSA_TRAN_REF = UUID.randomUUID();
    private static final UUID UPDATED_ABSA_TRAN_REF = UUID.randomUUID();

    private static final String DEFAULT_BILLER_ID = "AAAAAAAAAA";
    private static final String UPDATED_BILLER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_DT_D_TRANSACTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_DT_D_TRANSACTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_AMOL_D_TRANSACTION_ID = "AAAAAAAAAA";
    private static final String UPDATED_AMOL_D_TRANSACTION_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSACTION_REFERENCE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_TRANSACTION_REFERENCE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_TOKEN = "AAAAAAAAAA";
    private static final String UPDATED_TOKEN = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSFER_ID = "AAAAAAAAAA";
    private static final String UPDATED_TRANSFER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_EXTERNAL_TXN_ID = "AAAAAAAAAA";
    private static final String UPDATED_EXTERNAL_TXN_ID = "BBBBBBBBBB";

    private static final String DEFAULT_CUSTOMER_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_CUSTOMER_REFERENCE = "BBBBBBBBBB";

    private static final String DEFAULT_DEBIT_ACCOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_DEBIT_ACCOUNT_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_CREDIT_ACCOUNT_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_CREDIT_ACCOUNT_NUMBER = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_DEBIT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_DEBIT_AMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_CREDIT_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_CREDIT_AMOUNT = new BigDecimal(2);

    private static final BigDecimal DEFAULT_TRANSACTION_AMOUNT = new BigDecimal(1);
    private static final BigDecimal UPDATED_TRANSACTION_AMOUNT = new BigDecimal(2);

    private static final ZonedDateTime DEFAULT_DEBIT_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_DEBIT_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_CREDIT_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREDIT_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final TranStatus DEFAULT_STATUS = TranStatus.PENDING;
    private static final TranStatus UPDATED_STATUS = TranStatus.SUCCESS;

    private static final ZonedDateTime DEFAULT_SETTLEMENT_DATE = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_SETTLEMENT_DATE = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final Currency DEFAULT_DEBIT_CURRENCY = Currency.UGX;
    private static final Currency UPDATED_DEBIT_CURRENCY = Currency.USD;

    private static final ZonedDateTime DEFAULT_TIMESTAMP = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TIMESTAMP = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_EMAIL = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_EMAIL = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_PHONE_NUMBER = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_NARRATION = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_NARRATION = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_BRANCH_ID = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_BRANCH_ID = "BBBBBBBBBB";

    private static final String DEFAULT_BENEFICIARY_NAME = "AAAAAAAAAA";
    private static final String UPDATED_BENEFICIARY_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_BENEFICIARY_ACCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_BENEFICIARY_ACCOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_BENEFICIARY_BRANCH_ID = "AAAAAAAAAA";
    private static final String UPDATED_BENEFICIARY_BRANCH_ID = "BBBBBBBBBB";

    private static final String DEFAULT_BENEFICIARY_PHONE_NUMBER = "AAAAAAAAAA";
    private static final String UPDATED_BENEFICIARY_PHONE_NUMBER = "BBBBBBBBBB";

    private static final TranStatus DEFAULT_TRAN_STATUS = TranStatus.PENDING;
    private static final TranStatus UPDATED_TRAN_STATUS = TranStatus.SUCCESS;

    private static final String DEFAULT_NARRATION_1 = "AAAAAAAAAA";
    private static final String UPDATED_NARRATION_1 = "BBBBBBBBBB";

    private static final String DEFAULT_NARRATION_2 = "AAAAAAAAAA";
    private static final String UPDATED_NARRATION_2 = "BBBBBBBBBB";

    private static final String DEFAULT_NARRATION_3 = "AAAAAAAAAA";
    private static final String UPDATED_NARRATION_3 = "BBBBBBBBBB";

    private static final Integer DEFAULT_TAX_AMOUNT = 1;
    private static final Integer UPDATED_TAX_AMOUNT = 2;

    private static final String DEFAULT_TAX_GL_ACCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_TAX_GL_ACCOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_TAX_NARRATION = "AAAAAAAAAA";
    private static final String UPDATED_TAX_NARRATION = "BBBBBBBBBB";

    private static final String DEFAULT_INTERNAL_ERROR_CODE = "AAAAAAAAAA";
    private static final String UPDATED_INTERNAL_ERROR_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_EXTERNAL_ERROR_CODE = "AAAAAAAAAA";
    private static final String UPDATED_EXTERNAL_ERROR_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PAYEE_BRANCH_ID = "AAAAAAAAAA";
    private static final String UPDATED_PAYEE_BRANCH_ID = "BBBBBBBBBB";

    private static final String DEFAULT_PAYEE_PRODUCT_INSTANCE_REFERENCE = "AAAAAAAAAA";
    private static final String UPDATED_PAYEE_PRODUCT_INSTANCE_REFERENCE = "BBBBBBBBBB";

    private static final String DEFAULT_PAYEE_PRODUCT_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PAYEE_PRODUCT_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PAYEE_PRODUCT_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PAYEE_PRODUCT_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PAYEE_PRODUCT_DESCRIPTION = "AAAAAAAAAA";
    private static final String UPDATED_PAYEE_PRODUCT_DESCRIPTION = "BBBBBBBBBB";

    private static final String DEFAULT_PAYEE_PRODUCT_UNIT_OF_MEASURE = "AAAAAAAAAA";
    private static final String UPDATED_PAYEE_PRODUCT_UNIT_OF_MEASURE = "BBBBBBBBBB";

    private static final String DEFAULT_PAYEE_PRODUCT_QUANTITY = "AAAAAAAAAA";
    private static final String UPDATED_PAYEE_PRODUCT_QUANTITY = "BBBBBBBBBB";

    private static final String DEFAULT_SUB_CATEGORY_CODE = "AAAAAAAAAA";
    private static final String UPDATED_SUB_CATEGORY_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_BANK_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_BANK_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_BANK_NAME = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_BANK_NAME = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_BANK_ADDRESS = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_BANK_ADDRESS = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_BANK_CITY = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_BANK_CITY = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_BANK_STATE = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_BANK_STATE = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_BANK_COUNTRY = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_BANK_COUNTRY = "BBBBBBBBBB";

    private static final String DEFAULT_PAYER_BANK_POSTAL_CODE = "AAAAAAAAAA";
    private static final String UPDATED_PAYER_BANK_POSTAL_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_CHECKER_ID = "AAAAAAAAAA";
    private static final String UPDATED_CHECKER_ID = "BBBBBBBBBB";

    private static final String DEFAULT_REMITTANCE_INFORMATION = "AAAAAAAAAA";
    private static final String UPDATED_REMITTANCE_INFORMATION = "BBBBBBBBBB";

    private static final Channel DEFAULT_PAYMENT_CHANNEL_CODE = Channel.ATM;
    private static final Channel UPDATED_PAYMENT_CHANNEL_CODE = Channel.CDM;

    private static final Integer DEFAULT_FEE_AMOUNT = 1;
    private static final Integer UPDATED_FEE_AMOUNT = 2;

    private static final String DEFAULT_FEE_GL_ACCOUNT = "AAAAAAAAAA";
    private static final String UPDATED_FEE_GL_ACCOUNT = "BBBBBBBBBB";

    private static final String DEFAULT_FEE_NARRATION = "AAAAAAAAAA";
    private static final String UPDATED_FEE_NARRATION = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_1 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_1 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_2 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_2 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_3 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_3 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_4 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_4 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_5 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_5 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_6 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_6 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_7 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_7 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_8 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_8 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_9 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_9 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_10 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_10 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_11 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_11 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_12 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_12 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_13 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_13 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_14 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_14 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_15 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_15 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_16 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_16 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_17 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_17 = "BBBBBBBBBB";

    private static final BigDecimal DEFAULT_TRAN_FREE_FIELD_18 = new BigDecimal(1);
    private static final BigDecimal UPDATED_TRAN_FREE_FIELD_18 = new BigDecimal(2);

    private static final Integer DEFAULT_TRAN_FREE_FIELD_19 = 1;
    private static final Integer UPDATED_TRAN_FREE_FIELD_19 = 2;

    private static final Boolean DEFAULT_TRAN_FREE_FIELD_20 = false;
    private static final Boolean UPDATED_TRAN_FREE_FIELD_20 = true;

    private static final String DEFAULT_TRAN_FREE_FIELD_21 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_21 = "BBBBBBBBBB";

    private static final String DEFAULT_TRAN_FREE_FIELD_22 = "AAAAAAAAAA";
    private static final String UPDATED_TRAN_FREE_FIELD_22 = "BBBBBBBBBB";

    private static final byte[] DEFAULT_TRAN_FREE_FIELD_23 = TestUtil.createByteArray(1, "0");
    private static final byte[] UPDATED_TRAN_FREE_FIELD_23 = TestUtil.createByteArray(1, "1");
    private static final String DEFAULT_TRAN_FREE_FIELD_23_CONTENT_TYPE = "image/jpg";
    private static final String UPDATED_TRAN_FREE_FIELD_23_CONTENT_TYPE = "image/png";

    private static final ZonedDateTime DEFAULT_TRAN_FREE_FIELD_24 = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_TRAN_FREE_FIELD_24 = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_RESPONSE_CODE = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_CODE = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_MESSAGE = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_MESSAGE = "BBBBBBBBBB";

    private static final String DEFAULT_RESPONSE_DETAILS = "AAAAAAAAAA";
    private static final String UPDATED_RESPONSE_DETAILS = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSFER_REFERENCE_ID = "AAAAAAAAAA";
    private static final String UPDATED_TRANSFER_REFERENCE_ID = "BBBBBBBBBB";

    private static final String DEFAULT_TRANSFER_STATUS = "AAAAAAAAAA";
    private static final String UPDATED_TRANSFER_STATUS = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_RESPONSE_DATE_TIME = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_RESPONSE_DATE_TIME = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final ZonedDateTime DEFAULT_CREATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_CREATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_CREATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_CREATED_BY = "BBBBBBBBBB";

    private static final ZonedDateTime DEFAULT_UPDATED_AT = ZonedDateTime.ofInstant(Instant.ofEpochMilli(0L), ZoneOffset.UTC);
    private static final ZonedDateTime UPDATED_UPDATED_AT = ZonedDateTime.now(ZoneId.systemDefault()).withNano(0);

    private static final String DEFAULT_UPDATED_BY = "AAAAAAAAAA";
    private static final String UPDATED_UPDATED_BY = "BBBBBBBBBB";

    private static final String ENTITY_API_URL = "/api/amol-d-transactions";
    private static final String ENTITY_API_URL_ID = ENTITY_API_URL + "/{id}";
    private static final String ENTITY_SEARCH_API_URL = "/api/_search/amol-d-transactions";

    private static Random random = new Random();
    private static AtomicLong count = new AtomicLong(random.nextInt() + (2 * Integer.MAX_VALUE));

    @Autowired
    private AmolDTransactionsRepository amolDTransactionsRepository;

    @Autowired
    private AmolDTransactionsSearchRepository amolDTransactionsSearchRepository;

    @Autowired
    private EntityManager em;

    @Autowired
    private WebTestClient webTestClient;

    private AmolDTransactions amolDTransactions;

    /**
     * Create an entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AmolDTransactions createEntity(EntityManager em) {
        AmolDTransactions amolDTransactions = new AmolDTransactions()
            .absaTranRef(DEFAULT_ABSA_TRAN_REF)
            .billerId(DEFAULT_BILLER_ID)
            .dtDTransactionId(DEFAULT_DT_D_TRANSACTION_ID)
            .amolDTransactionId(DEFAULT_AMOL_D_TRANSACTION_ID)
            .transactionReferenceNumber(DEFAULT_TRANSACTION_REFERENCE_NUMBER)
            .token(DEFAULT_TOKEN)
            .transferId(DEFAULT_TRANSFER_ID)
            .externalTxnId(DEFAULT_EXTERNAL_TXN_ID)
            .customerReference(DEFAULT_CUSTOMER_REFERENCE)
            .debitAccountNumber(DEFAULT_DEBIT_ACCOUNT_NUMBER)
            .creditAccountNumber(DEFAULT_CREDIT_ACCOUNT_NUMBER)
            .debitAmount(DEFAULT_DEBIT_AMOUNT)
            .creditAmount(DEFAULT_CREDIT_AMOUNT)
            .transactionAmount(DEFAULT_TRANSACTION_AMOUNT)
            .debitDate(DEFAULT_DEBIT_DATE)
            .creditDate(DEFAULT_CREDIT_DATE)
            .status(DEFAULT_STATUS)
            .settlementDate(DEFAULT_SETTLEMENT_DATE)
            .debitCurrency(DEFAULT_DEBIT_CURRENCY)
            .timestamp(DEFAULT_TIMESTAMP)
            .phoneNumber(DEFAULT_PHONE_NUMBER)
            .email(DEFAULT_EMAIL)
            .payerName(DEFAULT_PAYER_NAME)
            .payerAddress(DEFAULT_PAYER_ADDRESS)
            .payerEmail(DEFAULT_PAYER_EMAIL)
            .payerPhoneNumber(DEFAULT_PAYER_PHONE_NUMBER)
            .payerNarration(DEFAULT_PAYER_NARRATION)
            .payerBranchId(DEFAULT_PAYER_BRANCH_ID)
            .beneficiaryName(DEFAULT_BENEFICIARY_NAME)
            .beneficiaryAccount(DEFAULT_BENEFICIARY_ACCOUNT)
            .beneficiaryBranchId(DEFAULT_BENEFICIARY_BRANCH_ID)
            .beneficiaryPhoneNumber(DEFAULT_BENEFICIARY_PHONE_NUMBER)
            .tranStatus(DEFAULT_TRAN_STATUS)
            .narration1(DEFAULT_NARRATION_1)
            .narration2(DEFAULT_NARRATION_2)
            .narration3(DEFAULT_NARRATION_3)
            .taxAmount(DEFAULT_TAX_AMOUNT)
            .taxGLAccount(DEFAULT_TAX_GL_ACCOUNT)
            .taxNarration(DEFAULT_TAX_NARRATION)
            .internalErrorCode(DEFAULT_INTERNAL_ERROR_CODE)
            .externalErrorCode(DEFAULT_EXTERNAL_ERROR_CODE)
            .payeeBranchId(DEFAULT_PAYEE_BRANCH_ID)
            .payeeProductInstanceReference(DEFAULT_PAYEE_PRODUCT_INSTANCE_REFERENCE)
            .payeeProductCode(DEFAULT_PAYEE_PRODUCT_CODE)
            .payeeProductName(DEFAULT_PAYEE_PRODUCT_NAME)
            .payeeProductDescription(DEFAULT_PAYEE_PRODUCT_DESCRIPTION)
            .payeeProductUnitOfMeasure(DEFAULT_PAYEE_PRODUCT_UNIT_OF_MEASURE)
            .payeeProductQuantity(DEFAULT_PAYEE_PRODUCT_QUANTITY)
            .subCategoryCode(DEFAULT_SUB_CATEGORY_CODE)
            .payerBankCode(DEFAULT_PAYER_BANK_CODE)
            .payerBankName(DEFAULT_PAYER_BANK_NAME)
            .payerBankAddress(DEFAULT_PAYER_BANK_ADDRESS)
            .payerBankCity(DEFAULT_PAYER_BANK_CITY)
            .payerBankState(DEFAULT_PAYER_BANK_STATE)
            .payerBankCountry(DEFAULT_PAYER_BANK_COUNTRY)
            .payerBankPostalCode(DEFAULT_PAYER_BANK_POSTAL_CODE)
            .checkerId(DEFAULT_CHECKER_ID)
            .remittanceInformation(DEFAULT_REMITTANCE_INFORMATION)
            .paymentChannelCode(DEFAULT_PAYMENT_CHANNEL_CODE)
            .feeAmount(DEFAULT_FEE_AMOUNT)
            .feeGLAccount(DEFAULT_FEE_GL_ACCOUNT)
            .feeNarration(DEFAULT_FEE_NARRATION)
            .tranFreeField1(DEFAULT_TRAN_FREE_FIELD_1)
            .tranFreeField2(DEFAULT_TRAN_FREE_FIELD_2)
            .tranFreeField3(DEFAULT_TRAN_FREE_FIELD_3)
            .tranFreeField4(DEFAULT_TRAN_FREE_FIELD_4)
            .tranFreeField5(DEFAULT_TRAN_FREE_FIELD_5)
            .tranFreeField6(DEFAULT_TRAN_FREE_FIELD_6)
            .tranFreeField7(DEFAULT_TRAN_FREE_FIELD_7)
            .tranFreeField8(DEFAULT_TRAN_FREE_FIELD_8)
            .tranFreeField9(DEFAULT_TRAN_FREE_FIELD_9)
            .tranFreeField10(DEFAULT_TRAN_FREE_FIELD_10)
            .tranFreeField11(DEFAULT_TRAN_FREE_FIELD_11)
            .tranFreeField12(DEFAULT_TRAN_FREE_FIELD_12)
            .tranFreeField13(DEFAULT_TRAN_FREE_FIELD_13)
            .tranFreeField14(DEFAULT_TRAN_FREE_FIELD_14)
            .tranFreeField15(DEFAULT_TRAN_FREE_FIELD_15)
            .tranFreeField16(DEFAULT_TRAN_FREE_FIELD_16)
            .tranFreeField17(DEFAULT_TRAN_FREE_FIELD_17)
            .tranFreeField18(DEFAULT_TRAN_FREE_FIELD_18)
            .tranFreeField19(DEFAULT_TRAN_FREE_FIELD_19)
            .tranFreeField20(DEFAULT_TRAN_FREE_FIELD_20)
            .tranFreeField21(DEFAULT_TRAN_FREE_FIELD_21)
            .tranFreeField22(DEFAULT_TRAN_FREE_FIELD_22)
            .tranFreeField23(DEFAULT_TRAN_FREE_FIELD_23)
            .tranFreeField23ContentType(DEFAULT_TRAN_FREE_FIELD_23_CONTENT_TYPE)
            .tranFreeField24(DEFAULT_TRAN_FREE_FIELD_24)
            .responseCode(DEFAULT_RESPONSE_CODE)
            .responseMessage(DEFAULT_RESPONSE_MESSAGE)
            .responseDetails(DEFAULT_RESPONSE_DETAILS)
            .transferReferenceId(DEFAULT_TRANSFER_REFERENCE_ID)
            .transferStatus(DEFAULT_TRANSFER_STATUS)
            .responseDateTime(DEFAULT_RESPONSE_DATE_TIME)
            .createdAt(DEFAULT_CREATED_AT)
            .createdBy(DEFAULT_CREATED_BY)
            .updatedAt(DEFAULT_UPDATED_AT)
            .updatedBy(DEFAULT_UPDATED_BY);
        return amolDTransactions;
    }

    /**
     * Create an updated entity for this test.
     *
     * This is a static method, as tests for other entities might also need it,
     * if they test an entity which requires the current entity.
     */
    public static AmolDTransactions createUpdatedEntity(EntityManager em) {
        AmolDTransactions amolDTransactions = new AmolDTransactions()
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .billerId(UPDATED_BILLER_ID)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .amolDTransactionId(UPDATED_AMOL_D_TRANSACTION_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .token(UPDATED_TOKEN)
            .transferId(UPDATED_TRANSFER_ID)
            .externalTxnId(UPDATED_EXTERNAL_TXN_ID)
            .customerReference(UPDATED_CUSTOMER_REFERENCE)
            .debitAccountNumber(UPDATED_DEBIT_ACCOUNT_NUMBER)
            .creditAccountNumber(UPDATED_CREDIT_ACCOUNT_NUMBER)
            .debitAmount(UPDATED_DEBIT_AMOUNT)
            .creditAmount(UPDATED_CREDIT_AMOUNT)
            .transactionAmount(UPDATED_TRANSACTION_AMOUNT)
            .debitDate(UPDATED_DEBIT_DATE)
            .creditDate(UPDATED_CREDIT_DATE)
            .status(UPDATED_STATUS)
            .settlementDate(UPDATED_SETTLEMENT_DATE)
            .debitCurrency(UPDATED_DEBIT_CURRENCY)
            .timestamp(UPDATED_TIMESTAMP)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .email(UPDATED_EMAIL)
            .payerName(UPDATED_PAYER_NAME)
            .payerAddress(UPDATED_PAYER_ADDRESS)
            .payerEmail(UPDATED_PAYER_EMAIL)
            .payerPhoneNumber(UPDATED_PAYER_PHONE_NUMBER)
            .payerNarration(UPDATED_PAYER_NARRATION)
            .payerBranchId(UPDATED_PAYER_BRANCH_ID)
            .beneficiaryName(UPDATED_BENEFICIARY_NAME)
            .beneficiaryAccount(UPDATED_BENEFICIARY_ACCOUNT)
            .beneficiaryBranchId(UPDATED_BENEFICIARY_BRANCH_ID)
            .beneficiaryPhoneNumber(UPDATED_BENEFICIARY_PHONE_NUMBER)
            .tranStatus(UPDATED_TRAN_STATUS)
            .narration1(UPDATED_NARRATION_1)
            .narration2(UPDATED_NARRATION_2)
            .narration3(UPDATED_NARRATION_3)
            .taxAmount(UPDATED_TAX_AMOUNT)
            .taxGLAccount(UPDATED_TAX_GL_ACCOUNT)
            .taxNarration(UPDATED_TAX_NARRATION)
            .internalErrorCode(UPDATED_INTERNAL_ERROR_CODE)
            .externalErrorCode(UPDATED_EXTERNAL_ERROR_CODE)
            .payeeBranchId(UPDATED_PAYEE_BRANCH_ID)
            .payeeProductInstanceReference(UPDATED_PAYEE_PRODUCT_INSTANCE_REFERENCE)
            .payeeProductCode(UPDATED_PAYEE_PRODUCT_CODE)
            .payeeProductName(UPDATED_PAYEE_PRODUCT_NAME)
            .payeeProductDescription(UPDATED_PAYEE_PRODUCT_DESCRIPTION)
            .payeeProductUnitOfMeasure(UPDATED_PAYEE_PRODUCT_UNIT_OF_MEASURE)
            .payeeProductQuantity(UPDATED_PAYEE_PRODUCT_QUANTITY)
            .subCategoryCode(UPDATED_SUB_CATEGORY_CODE)
            .payerBankCode(UPDATED_PAYER_BANK_CODE)
            .payerBankName(UPDATED_PAYER_BANK_NAME)
            .payerBankAddress(UPDATED_PAYER_BANK_ADDRESS)
            .payerBankCity(UPDATED_PAYER_BANK_CITY)
            .payerBankState(UPDATED_PAYER_BANK_STATE)
            .payerBankCountry(UPDATED_PAYER_BANK_COUNTRY)
            .payerBankPostalCode(UPDATED_PAYER_BANK_POSTAL_CODE)
            .checkerId(UPDATED_CHECKER_ID)
            .remittanceInformation(UPDATED_REMITTANCE_INFORMATION)
            .paymentChannelCode(UPDATED_PAYMENT_CHANNEL_CODE)
            .feeAmount(UPDATED_FEE_AMOUNT)
            .feeGLAccount(UPDATED_FEE_GL_ACCOUNT)
            .feeNarration(UPDATED_FEE_NARRATION)
            .tranFreeField1(UPDATED_TRAN_FREE_FIELD_1)
            .tranFreeField2(UPDATED_TRAN_FREE_FIELD_2)
            .tranFreeField3(UPDATED_TRAN_FREE_FIELD_3)
            .tranFreeField4(UPDATED_TRAN_FREE_FIELD_4)
            .tranFreeField5(UPDATED_TRAN_FREE_FIELD_5)
            .tranFreeField6(UPDATED_TRAN_FREE_FIELD_6)
            .tranFreeField7(UPDATED_TRAN_FREE_FIELD_7)
            .tranFreeField8(UPDATED_TRAN_FREE_FIELD_8)
            .tranFreeField9(UPDATED_TRAN_FREE_FIELD_9)
            .tranFreeField10(UPDATED_TRAN_FREE_FIELD_10)
            .tranFreeField11(UPDATED_TRAN_FREE_FIELD_11)
            .tranFreeField12(UPDATED_TRAN_FREE_FIELD_12)
            .tranFreeField13(UPDATED_TRAN_FREE_FIELD_13)
            .tranFreeField14(UPDATED_TRAN_FREE_FIELD_14)
            .tranFreeField15(UPDATED_TRAN_FREE_FIELD_15)
            .tranFreeField16(UPDATED_TRAN_FREE_FIELD_16)
            .tranFreeField17(UPDATED_TRAN_FREE_FIELD_17)
            .tranFreeField18(UPDATED_TRAN_FREE_FIELD_18)
            .tranFreeField19(UPDATED_TRAN_FREE_FIELD_19)
            .tranFreeField20(UPDATED_TRAN_FREE_FIELD_20)
            .tranFreeField21(UPDATED_TRAN_FREE_FIELD_21)
            .tranFreeField22(UPDATED_TRAN_FREE_FIELD_22)
            .tranFreeField23(UPDATED_TRAN_FREE_FIELD_23)
            .tranFreeField23ContentType(UPDATED_TRAN_FREE_FIELD_23_CONTENT_TYPE)
            .tranFreeField24(UPDATED_TRAN_FREE_FIELD_24)
            .responseCode(UPDATED_RESPONSE_CODE)
            .responseMessage(UPDATED_RESPONSE_MESSAGE)
            .responseDetails(UPDATED_RESPONSE_DETAILS)
            .transferReferenceId(UPDATED_TRANSFER_REFERENCE_ID)
            .transferStatus(UPDATED_TRANSFER_STATUS)
            .responseDateTime(UPDATED_RESPONSE_DATE_TIME)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);
        return amolDTransactions;
    }

    public static void deleteEntities(EntityManager em) {
        try {
            em.deleteAll(AmolDTransactions.class).block();
        } catch (Exception e) {
            // It can fail, if other entities are still referring this - it will be removed later.
        }
    }

    @AfterEach
    public void cleanup() {
        deleteEntities(em);
    }

    @AfterEach
    public void cleanupElasticSearchRepository() {
        amolDTransactionsSearchRepository.deleteAll().block();
        assertThat(amolDTransactionsSearchRepository.count().block()).isEqualTo(0);
    }

    @BeforeEach
    public void initTest() {
        deleteEntities(em);
        amolDTransactions = createEntity(em);
    }

    @Test
    void createAmolDTransactions() throws Exception {
        int databaseSizeBeforeCreate = amolDTransactionsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(amolDTransactionsSearchRepository.findAll().collectList().block());
        // Create the AmolDTransactions
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(amolDTransactions))
            .exchange()
            .expectStatus()
            .isCreated();

        // Validate the AmolDTransactions in the database
        List<AmolDTransactions> amolDTransactionsList = amolDTransactionsRepository.findAll().collectList().block();
        assertThat(amolDTransactionsList).hasSize(databaseSizeBeforeCreate + 1);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(amolDTransactionsSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore + 1);
            });
        AmolDTransactions testAmolDTransactions = amolDTransactionsList.get(amolDTransactionsList.size() - 1);
        assertThat(testAmolDTransactions.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testAmolDTransactions.getBillerId()).isEqualTo(DEFAULT_BILLER_ID);
        assertThat(testAmolDTransactions.getDtDTransactionId()).isEqualTo(DEFAULT_DT_D_TRANSACTION_ID);
        assertThat(testAmolDTransactions.getAmolDTransactionId()).isEqualTo(DEFAULT_AMOL_D_TRANSACTION_ID);
        assertThat(testAmolDTransactions.getTransactionReferenceNumber()).isEqualTo(DEFAULT_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testAmolDTransactions.getToken()).isEqualTo(DEFAULT_TOKEN);
        assertThat(testAmolDTransactions.getTransferId()).isEqualTo(DEFAULT_TRANSFER_ID);
        assertThat(testAmolDTransactions.getExternalTxnId()).isEqualTo(DEFAULT_EXTERNAL_TXN_ID);
        assertThat(testAmolDTransactions.getCustomerReference()).isEqualTo(DEFAULT_CUSTOMER_REFERENCE);
        assertThat(testAmolDTransactions.getDebitAccountNumber()).isEqualTo(DEFAULT_DEBIT_ACCOUNT_NUMBER);
        assertThat(testAmolDTransactions.getCreditAccountNumber()).isEqualTo(DEFAULT_CREDIT_ACCOUNT_NUMBER);
        assertThat(testAmolDTransactions.getDebitAmount()).isEqualByComparingTo(DEFAULT_DEBIT_AMOUNT);
        assertThat(testAmolDTransactions.getCreditAmount()).isEqualByComparingTo(DEFAULT_CREDIT_AMOUNT);
        assertThat(testAmolDTransactions.getTransactionAmount()).isEqualByComparingTo(DEFAULT_TRANSACTION_AMOUNT);
        assertThat(testAmolDTransactions.getDebitDate()).isEqualTo(DEFAULT_DEBIT_DATE);
        assertThat(testAmolDTransactions.getCreditDate()).isEqualTo(DEFAULT_CREDIT_DATE);
        assertThat(testAmolDTransactions.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testAmolDTransactions.getSettlementDate()).isEqualTo(DEFAULT_SETTLEMENT_DATE);
        assertThat(testAmolDTransactions.getDebitCurrency()).isEqualTo(DEFAULT_DEBIT_CURRENCY);
        assertThat(testAmolDTransactions.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testAmolDTransactions.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testAmolDTransactions.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testAmolDTransactions.getPayerName()).isEqualTo(DEFAULT_PAYER_NAME);
        assertThat(testAmolDTransactions.getPayerAddress()).isEqualTo(DEFAULT_PAYER_ADDRESS);
        assertThat(testAmolDTransactions.getPayerEmail()).isEqualTo(DEFAULT_PAYER_EMAIL);
        assertThat(testAmolDTransactions.getPayerPhoneNumber()).isEqualTo(DEFAULT_PAYER_PHONE_NUMBER);
        assertThat(testAmolDTransactions.getPayerNarration()).isEqualTo(DEFAULT_PAYER_NARRATION);
        assertThat(testAmolDTransactions.getPayerBranchId()).isEqualTo(DEFAULT_PAYER_BRANCH_ID);
        assertThat(testAmolDTransactions.getBeneficiaryName()).isEqualTo(DEFAULT_BENEFICIARY_NAME);
        assertThat(testAmolDTransactions.getBeneficiaryAccount()).isEqualTo(DEFAULT_BENEFICIARY_ACCOUNT);
        assertThat(testAmolDTransactions.getBeneficiaryBranchId()).isEqualTo(DEFAULT_BENEFICIARY_BRANCH_ID);
        assertThat(testAmolDTransactions.getBeneficiaryPhoneNumber()).isEqualTo(DEFAULT_BENEFICIARY_PHONE_NUMBER);
        assertThat(testAmolDTransactions.getTranStatus()).isEqualTo(DEFAULT_TRAN_STATUS);
        assertThat(testAmolDTransactions.getNarration1()).isEqualTo(DEFAULT_NARRATION_1);
        assertThat(testAmolDTransactions.getNarration2()).isEqualTo(DEFAULT_NARRATION_2);
        assertThat(testAmolDTransactions.getNarration3()).isEqualTo(DEFAULT_NARRATION_3);
        assertThat(testAmolDTransactions.getTaxAmount()).isEqualTo(DEFAULT_TAX_AMOUNT);
        assertThat(testAmolDTransactions.getTaxGLAccount()).isEqualTo(DEFAULT_TAX_GL_ACCOUNT);
        assertThat(testAmolDTransactions.getTaxNarration()).isEqualTo(DEFAULT_TAX_NARRATION);
        assertThat(testAmolDTransactions.getInternalErrorCode()).isEqualTo(DEFAULT_INTERNAL_ERROR_CODE);
        assertThat(testAmolDTransactions.getExternalErrorCode()).isEqualTo(DEFAULT_EXTERNAL_ERROR_CODE);
        assertThat(testAmolDTransactions.getPayeeBranchId()).isEqualTo(DEFAULT_PAYEE_BRANCH_ID);
        assertThat(testAmolDTransactions.getPayeeProductInstanceReference()).isEqualTo(DEFAULT_PAYEE_PRODUCT_INSTANCE_REFERENCE);
        assertThat(testAmolDTransactions.getPayeeProductCode()).isEqualTo(DEFAULT_PAYEE_PRODUCT_CODE);
        assertThat(testAmolDTransactions.getPayeeProductName()).isEqualTo(DEFAULT_PAYEE_PRODUCT_NAME);
        assertThat(testAmolDTransactions.getPayeeProductDescription()).isEqualTo(DEFAULT_PAYEE_PRODUCT_DESCRIPTION);
        assertThat(testAmolDTransactions.getPayeeProductUnitOfMeasure()).isEqualTo(DEFAULT_PAYEE_PRODUCT_UNIT_OF_MEASURE);
        assertThat(testAmolDTransactions.getPayeeProductQuantity()).isEqualTo(DEFAULT_PAYEE_PRODUCT_QUANTITY);
        assertThat(testAmolDTransactions.getSubCategoryCode()).isEqualTo(DEFAULT_SUB_CATEGORY_CODE);
        assertThat(testAmolDTransactions.getPayerBankCode()).isEqualTo(DEFAULT_PAYER_BANK_CODE);
        assertThat(testAmolDTransactions.getPayerBankName()).isEqualTo(DEFAULT_PAYER_BANK_NAME);
        assertThat(testAmolDTransactions.getPayerBankAddress()).isEqualTo(DEFAULT_PAYER_BANK_ADDRESS);
        assertThat(testAmolDTransactions.getPayerBankCity()).isEqualTo(DEFAULT_PAYER_BANK_CITY);
        assertThat(testAmolDTransactions.getPayerBankState()).isEqualTo(DEFAULT_PAYER_BANK_STATE);
        assertThat(testAmolDTransactions.getPayerBankCountry()).isEqualTo(DEFAULT_PAYER_BANK_COUNTRY);
        assertThat(testAmolDTransactions.getPayerBankPostalCode()).isEqualTo(DEFAULT_PAYER_BANK_POSTAL_CODE);
        assertThat(testAmolDTransactions.getCheckerId()).isEqualTo(DEFAULT_CHECKER_ID);
        assertThat(testAmolDTransactions.getRemittanceInformation()).isEqualTo(DEFAULT_REMITTANCE_INFORMATION);
        assertThat(testAmolDTransactions.getPaymentChannelCode()).isEqualTo(DEFAULT_PAYMENT_CHANNEL_CODE);
        assertThat(testAmolDTransactions.getFeeAmount()).isEqualTo(DEFAULT_FEE_AMOUNT);
        assertThat(testAmolDTransactions.getFeeGLAccount()).isEqualTo(DEFAULT_FEE_GL_ACCOUNT);
        assertThat(testAmolDTransactions.getFeeNarration()).isEqualTo(DEFAULT_FEE_NARRATION);
        assertThat(testAmolDTransactions.getTranFreeField1()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_1);
        assertThat(testAmolDTransactions.getTranFreeField2()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_2);
        assertThat(testAmolDTransactions.getTranFreeField3()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_3);
        assertThat(testAmolDTransactions.getTranFreeField4()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_4);
        assertThat(testAmolDTransactions.getTranFreeField5()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_5);
        assertThat(testAmolDTransactions.getTranFreeField6()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_6);
        assertThat(testAmolDTransactions.getTranFreeField7()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_7);
        assertThat(testAmolDTransactions.getTranFreeField8()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_8);
        assertThat(testAmolDTransactions.getTranFreeField9()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_9);
        assertThat(testAmolDTransactions.getTranFreeField10()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_10);
        assertThat(testAmolDTransactions.getTranFreeField11()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_11);
        assertThat(testAmolDTransactions.getTranFreeField12()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_12);
        assertThat(testAmolDTransactions.getTranFreeField13()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_13);
        assertThat(testAmolDTransactions.getTranFreeField14()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_14);
        assertThat(testAmolDTransactions.getTranFreeField15()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_15);
        assertThat(testAmolDTransactions.getTranFreeField16()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_16);
        assertThat(testAmolDTransactions.getTranFreeField17()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_17);
        assertThat(testAmolDTransactions.getTranFreeField18()).isEqualByComparingTo(DEFAULT_TRAN_FREE_FIELD_18);
        assertThat(testAmolDTransactions.getTranFreeField19()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_19);
        assertThat(testAmolDTransactions.getTranFreeField20()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_20);
        assertThat(testAmolDTransactions.getTranFreeField21()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_21);
        assertThat(testAmolDTransactions.getTranFreeField22()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_22);
        assertThat(testAmolDTransactions.getTranFreeField23()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_23);
        assertThat(testAmolDTransactions.getTranFreeField23ContentType()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_23_CONTENT_TYPE);
        assertThat(testAmolDTransactions.getTranFreeField24()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_24);
        assertThat(testAmolDTransactions.getResponseCode()).isEqualTo(DEFAULT_RESPONSE_CODE);
        assertThat(testAmolDTransactions.getResponseMessage()).isEqualTo(DEFAULT_RESPONSE_MESSAGE);
        assertThat(testAmolDTransactions.getResponseDetails()).isEqualTo(DEFAULT_RESPONSE_DETAILS);
        assertThat(testAmolDTransactions.getTransferReferenceId()).isEqualTo(DEFAULT_TRANSFER_REFERENCE_ID);
        assertThat(testAmolDTransactions.getTransferStatus()).isEqualTo(DEFAULT_TRANSFER_STATUS);
        assertThat(testAmolDTransactions.getResponseDateTime()).isEqualTo(DEFAULT_RESPONSE_DATE_TIME);
        assertThat(testAmolDTransactions.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testAmolDTransactions.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testAmolDTransactions.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testAmolDTransactions.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void createAmolDTransactionsWithExistingId() throws Exception {
        // Create the AmolDTransactions with an existing ID
        amolDTransactions.setId(1L);

        int databaseSizeBeforeCreate = amolDTransactionsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(amolDTransactionsSearchRepository.findAll().collectList().block());

        // An entity with an existing ID cannot be created, so this API call must fail
        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(amolDTransactions))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the AmolDTransactions in the database
        List<AmolDTransactions> amolDTransactionsList = amolDTransactionsRepository.findAll().collectList().block();
        assertThat(amolDTransactionsList).hasSize(databaseSizeBeforeCreate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(amolDTransactionsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkDebitAccountNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = amolDTransactionsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(amolDTransactionsSearchRepository.findAll().collectList().block());
        // set the field null
        amolDTransactions.setDebitAccountNumber(null);

        // Create the AmolDTransactions, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(amolDTransactions))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AmolDTransactions> amolDTransactionsList = amolDTransactionsRepository.findAll().collectList().block();
        assertThat(amolDTransactionsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(amolDTransactionsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkCreditAccountNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = amolDTransactionsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(amolDTransactionsSearchRepository.findAll().collectList().block());
        // set the field null
        amolDTransactions.setCreditAccountNumber(null);

        // Create the AmolDTransactions, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(amolDTransactions))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AmolDTransactions> amolDTransactionsList = amolDTransactionsRepository.findAll().collectList().block();
        assertThat(amolDTransactionsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(amolDTransactionsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkDebitAmountIsRequired() throws Exception {
        int databaseSizeBeforeTest = amolDTransactionsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(amolDTransactionsSearchRepository.findAll().collectList().block());
        // set the field null
        amolDTransactions.setDebitAmount(null);

        // Create the AmolDTransactions, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(amolDTransactions))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AmolDTransactions> amolDTransactionsList = amolDTransactionsRepository.findAll().collectList().block();
        assertThat(amolDTransactionsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(amolDTransactionsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkDebitCurrencyIsRequired() throws Exception {
        int databaseSizeBeforeTest = amolDTransactionsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(amolDTransactionsSearchRepository.findAll().collectList().block());
        // set the field null
        amolDTransactions.setDebitCurrency(null);

        // Create the AmolDTransactions, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(amolDTransactions))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AmolDTransactions> amolDTransactionsList = amolDTransactionsRepository.findAll().collectList().block();
        assertThat(amolDTransactionsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(amolDTransactionsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkTimestampIsRequired() throws Exception {
        int databaseSizeBeforeTest = amolDTransactionsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(amolDTransactionsSearchRepository.findAll().collectList().block());
        // set the field null
        amolDTransactions.setTimestamp(null);

        // Create the AmolDTransactions, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(amolDTransactions))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AmolDTransactions> amolDTransactionsList = amolDTransactionsRepository.findAll().collectList().block();
        assertThat(amolDTransactionsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(amolDTransactionsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkPhoneNumberIsRequired() throws Exception {
        int databaseSizeBeforeTest = amolDTransactionsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(amolDTransactionsSearchRepository.findAll().collectList().block());
        // set the field null
        amolDTransactions.setPhoneNumber(null);

        // Create the AmolDTransactions, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(amolDTransactions))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AmolDTransactions> amolDTransactionsList = amolDTransactionsRepository.findAll().collectList().block();
        assertThat(amolDTransactionsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(amolDTransactionsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void checkEmailIsRequired() throws Exception {
        int databaseSizeBeforeTest = amolDTransactionsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(amolDTransactionsSearchRepository.findAll().collectList().block());
        // set the field null
        amolDTransactions.setEmail(null);

        // Create the AmolDTransactions, which fails.

        webTestClient
            .post()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(amolDTransactions))
            .exchange()
            .expectStatus()
            .isBadRequest();

        List<AmolDTransactions> amolDTransactionsList = amolDTransactionsRepository.findAll().collectList().block();
        assertThat(amolDTransactionsList).hasSize(databaseSizeBeforeTest);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(amolDTransactionsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void getAllAmolDTransactionsAsStream() {
        // Initialize the database
        amolDTransactionsRepository.save(amolDTransactions).block();

        List<AmolDTransactions> amolDTransactionsList = webTestClient
            .get()
            .uri(ENTITY_API_URL)
            .accept(MediaType.APPLICATION_NDJSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentTypeCompatibleWith(MediaType.APPLICATION_NDJSON)
            .returnResult(AmolDTransactions.class)
            .getResponseBody()
            .filter(amolDTransactions::equals)
            .collectList()
            .block(Duration.ofSeconds(5));

        assertThat(amolDTransactionsList).isNotNull();
        assertThat(amolDTransactionsList).hasSize(1);
        AmolDTransactions testAmolDTransactions = amolDTransactionsList.get(0);
        assertThat(testAmolDTransactions.getAbsaTranRef()).isEqualTo(DEFAULT_ABSA_TRAN_REF);
        assertThat(testAmolDTransactions.getBillerId()).isEqualTo(DEFAULT_BILLER_ID);
        assertThat(testAmolDTransactions.getDtDTransactionId()).isEqualTo(DEFAULT_DT_D_TRANSACTION_ID);
        assertThat(testAmolDTransactions.getAmolDTransactionId()).isEqualTo(DEFAULT_AMOL_D_TRANSACTION_ID);
        assertThat(testAmolDTransactions.getTransactionReferenceNumber()).isEqualTo(DEFAULT_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testAmolDTransactions.getToken()).isEqualTo(DEFAULT_TOKEN);
        assertThat(testAmolDTransactions.getTransferId()).isEqualTo(DEFAULT_TRANSFER_ID);
        assertThat(testAmolDTransactions.getExternalTxnId()).isEqualTo(DEFAULT_EXTERNAL_TXN_ID);
        assertThat(testAmolDTransactions.getCustomerReference()).isEqualTo(DEFAULT_CUSTOMER_REFERENCE);
        assertThat(testAmolDTransactions.getDebitAccountNumber()).isEqualTo(DEFAULT_DEBIT_ACCOUNT_NUMBER);
        assertThat(testAmolDTransactions.getCreditAccountNumber()).isEqualTo(DEFAULT_CREDIT_ACCOUNT_NUMBER);
        assertThat(testAmolDTransactions.getDebitAmount()).isEqualByComparingTo(DEFAULT_DEBIT_AMOUNT);
        assertThat(testAmolDTransactions.getCreditAmount()).isEqualByComparingTo(DEFAULT_CREDIT_AMOUNT);
        assertThat(testAmolDTransactions.getTransactionAmount()).isEqualByComparingTo(DEFAULT_TRANSACTION_AMOUNT);
        assertThat(testAmolDTransactions.getDebitDate()).isEqualTo(DEFAULT_DEBIT_DATE);
        assertThat(testAmolDTransactions.getCreditDate()).isEqualTo(DEFAULT_CREDIT_DATE);
        assertThat(testAmolDTransactions.getStatus()).isEqualTo(DEFAULT_STATUS);
        assertThat(testAmolDTransactions.getSettlementDate()).isEqualTo(DEFAULT_SETTLEMENT_DATE);
        assertThat(testAmolDTransactions.getDebitCurrency()).isEqualTo(DEFAULT_DEBIT_CURRENCY);
        assertThat(testAmolDTransactions.getTimestamp()).isEqualTo(DEFAULT_TIMESTAMP);
        assertThat(testAmolDTransactions.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testAmolDTransactions.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testAmolDTransactions.getPayerName()).isEqualTo(DEFAULT_PAYER_NAME);
        assertThat(testAmolDTransactions.getPayerAddress()).isEqualTo(DEFAULT_PAYER_ADDRESS);
        assertThat(testAmolDTransactions.getPayerEmail()).isEqualTo(DEFAULT_PAYER_EMAIL);
        assertThat(testAmolDTransactions.getPayerPhoneNumber()).isEqualTo(DEFAULT_PAYER_PHONE_NUMBER);
        assertThat(testAmolDTransactions.getPayerNarration()).isEqualTo(DEFAULT_PAYER_NARRATION);
        assertThat(testAmolDTransactions.getPayerBranchId()).isEqualTo(DEFAULT_PAYER_BRANCH_ID);
        assertThat(testAmolDTransactions.getBeneficiaryName()).isEqualTo(DEFAULT_BENEFICIARY_NAME);
        assertThat(testAmolDTransactions.getBeneficiaryAccount()).isEqualTo(DEFAULT_BENEFICIARY_ACCOUNT);
        assertThat(testAmolDTransactions.getBeneficiaryBranchId()).isEqualTo(DEFAULT_BENEFICIARY_BRANCH_ID);
        assertThat(testAmolDTransactions.getBeneficiaryPhoneNumber()).isEqualTo(DEFAULT_BENEFICIARY_PHONE_NUMBER);
        assertThat(testAmolDTransactions.getTranStatus()).isEqualTo(DEFAULT_TRAN_STATUS);
        assertThat(testAmolDTransactions.getNarration1()).isEqualTo(DEFAULT_NARRATION_1);
        assertThat(testAmolDTransactions.getNarration2()).isEqualTo(DEFAULT_NARRATION_2);
        assertThat(testAmolDTransactions.getNarration3()).isEqualTo(DEFAULT_NARRATION_3);
        assertThat(testAmolDTransactions.getTaxAmount()).isEqualTo(DEFAULT_TAX_AMOUNT);
        assertThat(testAmolDTransactions.getTaxGLAccount()).isEqualTo(DEFAULT_TAX_GL_ACCOUNT);
        assertThat(testAmolDTransactions.getTaxNarration()).isEqualTo(DEFAULT_TAX_NARRATION);
        assertThat(testAmolDTransactions.getInternalErrorCode()).isEqualTo(DEFAULT_INTERNAL_ERROR_CODE);
        assertThat(testAmolDTransactions.getExternalErrorCode()).isEqualTo(DEFAULT_EXTERNAL_ERROR_CODE);
        assertThat(testAmolDTransactions.getPayeeBranchId()).isEqualTo(DEFAULT_PAYEE_BRANCH_ID);
        assertThat(testAmolDTransactions.getPayeeProductInstanceReference()).isEqualTo(DEFAULT_PAYEE_PRODUCT_INSTANCE_REFERENCE);
        assertThat(testAmolDTransactions.getPayeeProductCode()).isEqualTo(DEFAULT_PAYEE_PRODUCT_CODE);
        assertThat(testAmolDTransactions.getPayeeProductName()).isEqualTo(DEFAULT_PAYEE_PRODUCT_NAME);
        assertThat(testAmolDTransactions.getPayeeProductDescription()).isEqualTo(DEFAULT_PAYEE_PRODUCT_DESCRIPTION);
        assertThat(testAmolDTransactions.getPayeeProductUnitOfMeasure()).isEqualTo(DEFAULT_PAYEE_PRODUCT_UNIT_OF_MEASURE);
        assertThat(testAmolDTransactions.getPayeeProductQuantity()).isEqualTo(DEFAULT_PAYEE_PRODUCT_QUANTITY);
        assertThat(testAmolDTransactions.getSubCategoryCode()).isEqualTo(DEFAULT_SUB_CATEGORY_CODE);
        assertThat(testAmolDTransactions.getPayerBankCode()).isEqualTo(DEFAULT_PAYER_BANK_CODE);
        assertThat(testAmolDTransactions.getPayerBankName()).isEqualTo(DEFAULT_PAYER_BANK_NAME);
        assertThat(testAmolDTransactions.getPayerBankAddress()).isEqualTo(DEFAULT_PAYER_BANK_ADDRESS);
        assertThat(testAmolDTransactions.getPayerBankCity()).isEqualTo(DEFAULT_PAYER_BANK_CITY);
        assertThat(testAmolDTransactions.getPayerBankState()).isEqualTo(DEFAULT_PAYER_BANK_STATE);
        assertThat(testAmolDTransactions.getPayerBankCountry()).isEqualTo(DEFAULT_PAYER_BANK_COUNTRY);
        assertThat(testAmolDTransactions.getPayerBankPostalCode()).isEqualTo(DEFAULT_PAYER_BANK_POSTAL_CODE);
        assertThat(testAmolDTransactions.getCheckerId()).isEqualTo(DEFAULT_CHECKER_ID);
        assertThat(testAmolDTransactions.getRemittanceInformation()).isEqualTo(DEFAULT_REMITTANCE_INFORMATION);
        assertThat(testAmolDTransactions.getPaymentChannelCode()).isEqualTo(DEFAULT_PAYMENT_CHANNEL_CODE);
        assertThat(testAmolDTransactions.getFeeAmount()).isEqualTo(DEFAULT_FEE_AMOUNT);
        assertThat(testAmolDTransactions.getFeeGLAccount()).isEqualTo(DEFAULT_FEE_GL_ACCOUNT);
        assertThat(testAmolDTransactions.getFeeNarration()).isEqualTo(DEFAULT_FEE_NARRATION);
        assertThat(testAmolDTransactions.getTranFreeField1()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_1);
        assertThat(testAmolDTransactions.getTranFreeField2()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_2);
        assertThat(testAmolDTransactions.getTranFreeField3()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_3);
        assertThat(testAmolDTransactions.getTranFreeField4()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_4);
        assertThat(testAmolDTransactions.getTranFreeField5()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_5);
        assertThat(testAmolDTransactions.getTranFreeField6()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_6);
        assertThat(testAmolDTransactions.getTranFreeField7()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_7);
        assertThat(testAmolDTransactions.getTranFreeField8()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_8);
        assertThat(testAmolDTransactions.getTranFreeField9()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_9);
        assertThat(testAmolDTransactions.getTranFreeField10()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_10);
        assertThat(testAmolDTransactions.getTranFreeField11()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_11);
        assertThat(testAmolDTransactions.getTranFreeField12()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_12);
        assertThat(testAmolDTransactions.getTranFreeField13()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_13);
        assertThat(testAmolDTransactions.getTranFreeField14()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_14);
        assertThat(testAmolDTransactions.getTranFreeField15()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_15);
        assertThat(testAmolDTransactions.getTranFreeField16()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_16);
        assertThat(testAmolDTransactions.getTranFreeField17()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_17);
        assertThat(testAmolDTransactions.getTranFreeField18()).isEqualByComparingTo(DEFAULT_TRAN_FREE_FIELD_18);
        assertThat(testAmolDTransactions.getTranFreeField19()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_19);
        assertThat(testAmolDTransactions.getTranFreeField20()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_20);
        assertThat(testAmolDTransactions.getTranFreeField21()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_21);
        assertThat(testAmolDTransactions.getTranFreeField22()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_22);
        assertThat(testAmolDTransactions.getTranFreeField23()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_23);
        assertThat(testAmolDTransactions.getTranFreeField23ContentType()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_23_CONTENT_TYPE);
        assertThat(testAmolDTransactions.getTranFreeField24()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_24);
        assertThat(testAmolDTransactions.getResponseCode()).isEqualTo(DEFAULT_RESPONSE_CODE);
        assertThat(testAmolDTransactions.getResponseMessage()).isEqualTo(DEFAULT_RESPONSE_MESSAGE);
        assertThat(testAmolDTransactions.getResponseDetails()).isEqualTo(DEFAULT_RESPONSE_DETAILS);
        assertThat(testAmolDTransactions.getTransferReferenceId()).isEqualTo(DEFAULT_TRANSFER_REFERENCE_ID);
        assertThat(testAmolDTransactions.getTransferStatus()).isEqualTo(DEFAULT_TRANSFER_STATUS);
        assertThat(testAmolDTransactions.getResponseDateTime()).isEqualTo(DEFAULT_RESPONSE_DATE_TIME);
        assertThat(testAmolDTransactions.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testAmolDTransactions.getCreatedBy()).isEqualTo(DEFAULT_CREATED_BY);
        assertThat(testAmolDTransactions.getUpdatedAt()).isEqualTo(DEFAULT_UPDATED_AT);
        assertThat(testAmolDTransactions.getUpdatedBy()).isEqualTo(DEFAULT_UPDATED_BY);
    }

    @Test
    void getAllAmolDTransactions() {
        // Initialize the database
        amolDTransactionsRepository.save(amolDTransactions).block();

        // Get all the amolDTransactionsList
        webTestClient
            .get()
            .uri(ENTITY_API_URL + "?sort=id,desc")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(amolDTransactions.getId().intValue()))
            .jsonPath("$.[*].absaTranRef")
            .value(hasItem(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.[*].billerId")
            .value(hasItem(DEFAULT_BILLER_ID))
            .jsonPath("$.[*].dtDTransactionId")
            .value(hasItem(DEFAULT_DT_D_TRANSACTION_ID))
            .jsonPath("$.[*].amolDTransactionId")
            .value(hasItem(DEFAULT_AMOL_D_TRANSACTION_ID))
            .jsonPath("$.[*].transactionReferenceNumber")
            .value(hasItem(DEFAULT_TRANSACTION_REFERENCE_NUMBER))
            .jsonPath("$.[*].token")
            .value(hasItem(DEFAULT_TOKEN))
            .jsonPath("$.[*].transferId")
            .value(hasItem(DEFAULT_TRANSFER_ID))
            .jsonPath("$.[*].externalTxnId")
            .value(hasItem(DEFAULT_EXTERNAL_TXN_ID))
            .jsonPath("$.[*].customerReference")
            .value(hasItem(DEFAULT_CUSTOMER_REFERENCE))
            .jsonPath("$.[*].debitAccountNumber")
            .value(hasItem(DEFAULT_DEBIT_ACCOUNT_NUMBER))
            .jsonPath("$.[*].creditAccountNumber")
            .value(hasItem(DEFAULT_CREDIT_ACCOUNT_NUMBER))
            .jsonPath("$.[*].debitAmount")
            .value(hasItem(sameNumber(DEFAULT_DEBIT_AMOUNT)))
            .jsonPath("$.[*].creditAmount")
            .value(hasItem(sameNumber(DEFAULT_CREDIT_AMOUNT)))
            .jsonPath("$.[*].transactionAmount")
            .value(hasItem(sameNumber(DEFAULT_TRANSACTION_AMOUNT)))
            .jsonPath("$.[*].debitDate")
            .value(hasItem(sameInstant(DEFAULT_DEBIT_DATE)))
            .jsonPath("$.[*].creditDate")
            .value(hasItem(sameInstant(DEFAULT_CREDIT_DATE)))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS.toString()))
            .jsonPath("$.[*].settlementDate")
            .value(hasItem(sameInstant(DEFAULT_SETTLEMENT_DATE)))
            .jsonPath("$.[*].debitCurrency")
            .value(hasItem(DEFAULT_DEBIT_CURRENCY.toString()))
            .jsonPath("$.[*].timestamp")
            .value(hasItem(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.[*].phoneNumber")
            .value(hasItem(DEFAULT_PHONE_NUMBER))
            .jsonPath("$.[*].email")
            .value(hasItem(DEFAULT_EMAIL))
            .jsonPath("$.[*].payerName")
            .value(hasItem(DEFAULT_PAYER_NAME))
            .jsonPath("$.[*].payerAddress")
            .value(hasItem(DEFAULT_PAYER_ADDRESS))
            .jsonPath("$.[*].payerEmail")
            .value(hasItem(DEFAULT_PAYER_EMAIL))
            .jsonPath("$.[*].payerPhoneNumber")
            .value(hasItem(DEFAULT_PAYER_PHONE_NUMBER))
            .jsonPath("$.[*].payerNarration")
            .value(hasItem(DEFAULT_PAYER_NARRATION))
            .jsonPath("$.[*].payerBranchId")
            .value(hasItem(DEFAULT_PAYER_BRANCH_ID))
            .jsonPath("$.[*].beneficiaryName")
            .value(hasItem(DEFAULT_BENEFICIARY_NAME))
            .jsonPath("$.[*].beneficiaryAccount")
            .value(hasItem(DEFAULT_BENEFICIARY_ACCOUNT))
            .jsonPath("$.[*].beneficiaryBranchId")
            .value(hasItem(DEFAULT_BENEFICIARY_BRANCH_ID))
            .jsonPath("$.[*].beneficiaryPhoneNumber")
            .value(hasItem(DEFAULT_BENEFICIARY_PHONE_NUMBER))
            .jsonPath("$.[*].tranStatus")
            .value(hasItem(DEFAULT_TRAN_STATUS.toString()))
            .jsonPath("$.[*].narration1")
            .value(hasItem(DEFAULT_NARRATION_1))
            .jsonPath("$.[*].narration2")
            .value(hasItem(DEFAULT_NARRATION_2))
            .jsonPath("$.[*].narration3")
            .value(hasItem(DEFAULT_NARRATION_3))
            .jsonPath("$.[*].taxAmount")
            .value(hasItem(DEFAULT_TAX_AMOUNT))
            .jsonPath("$.[*].taxGLAccount")
            .value(hasItem(DEFAULT_TAX_GL_ACCOUNT))
            .jsonPath("$.[*].taxNarration")
            .value(hasItem(DEFAULT_TAX_NARRATION))
            .jsonPath("$.[*].internalErrorCode")
            .value(hasItem(DEFAULT_INTERNAL_ERROR_CODE))
            .jsonPath("$.[*].externalErrorCode")
            .value(hasItem(DEFAULT_EXTERNAL_ERROR_CODE))
            .jsonPath("$.[*].payeeBranchId")
            .value(hasItem(DEFAULT_PAYEE_BRANCH_ID))
            .jsonPath("$.[*].payeeProductInstanceReference")
            .value(hasItem(DEFAULT_PAYEE_PRODUCT_INSTANCE_REFERENCE))
            .jsonPath("$.[*].payeeProductCode")
            .value(hasItem(DEFAULT_PAYEE_PRODUCT_CODE))
            .jsonPath("$.[*].payeeProductName")
            .value(hasItem(DEFAULT_PAYEE_PRODUCT_NAME))
            .jsonPath("$.[*].payeeProductDescription")
            .value(hasItem(DEFAULT_PAYEE_PRODUCT_DESCRIPTION))
            .jsonPath("$.[*].payeeProductUnitOfMeasure")
            .value(hasItem(DEFAULT_PAYEE_PRODUCT_UNIT_OF_MEASURE))
            .jsonPath("$.[*].payeeProductQuantity")
            .value(hasItem(DEFAULT_PAYEE_PRODUCT_QUANTITY))
            .jsonPath("$.[*].subCategoryCode")
            .value(hasItem(DEFAULT_SUB_CATEGORY_CODE))
            .jsonPath("$.[*].payerBankCode")
            .value(hasItem(DEFAULT_PAYER_BANK_CODE))
            .jsonPath("$.[*].payerBankName")
            .value(hasItem(DEFAULT_PAYER_BANK_NAME))
            .jsonPath("$.[*].payerBankAddress")
            .value(hasItem(DEFAULT_PAYER_BANK_ADDRESS))
            .jsonPath("$.[*].payerBankCity")
            .value(hasItem(DEFAULT_PAYER_BANK_CITY))
            .jsonPath("$.[*].payerBankState")
            .value(hasItem(DEFAULT_PAYER_BANK_STATE))
            .jsonPath("$.[*].payerBankCountry")
            .value(hasItem(DEFAULT_PAYER_BANK_COUNTRY))
            .jsonPath("$.[*].payerBankPostalCode")
            .value(hasItem(DEFAULT_PAYER_BANK_POSTAL_CODE))
            .jsonPath("$.[*].checkerId")
            .value(hasItem(DEFAULT_CHECKER_ID))
            .jsonPath("$.[*].remittanceInformation")
            .value(hasItem(DEFAULT_REMITTANCE_INFORMATION))
            .jsonPath("$.[*].paymentChannelCode")
            .value(hasItem(DEFAULT_PAYMENT_CHANNEL_CODE.toString()))
            .jsonPath("$.[*].feeAmount")
            .value(hasItem(DEFAULT_FEE_AMOUNT))
            .jsonPath("$.[*].feeGLAccount")
            .value(hasItem(DEFAULT_FEE_GL_ACCOUNT))
            .jsonPath("$.[*].feeNarration")
            .value(hasItem(DEFAULT_FEE_NARRATION))
            .jsonPath("$.[*].tranFreeField1")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_1))
            .jsonPath("$.[*].tranFreeField2")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_2))
            .jsonPath("$.[*].tranFreeField3")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_3))
            .jsonPath("$.[*].tranFreeField4")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_4))
            .jsonPath("$.[*].tranFreeField5")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_5))
            .jsonPath("$.[*].tranFreeField6")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_6))
            .jsonPath("$.[*].tranFreeField7")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_7))
            .jsonPath("$.[*].tranFreeField8")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_8))
            .jsonPath("$.[*].tranFreeField9")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_9))
            .jsonPath("$.[*].tranFreeField10")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_10))
            .jsonPath("$.[*].tranFreeField11")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_11))
            .jsonPath("$.[*].tranFreeField12")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_12))
            .jsonPath("$.[*].tranFreeField13")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_13))
            .jsonPath("$.[*].tranFreeField14")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_14))
            .jsonPath("$.[*].tranFreeField15")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_15))
            .jsonPath("$.[*].tranFreeField16")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_16))
            .jsonPath("$.[*].tranFreeField17")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_17))
            .jsonPath("$.[*].tranFreeField18")
            .value(hasItem(sameNumber(DEFAULT_TRAN_FREE_FIELD_18)))
            .jsonPath("$.[*].tranFreeField19")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_19))
            .jsonPath("$.[*].tranFreeField20")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_20.booleanValue()))
            .jsonPath("$.[*].tranFreeField21")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_21))
            .jsonPath("$.[*].tranFreeField22")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_22.toString()))
            .jsonPath("$.[*].tranFreeField23ContentType")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_23_CONTENT_TYPE))
            .jsonPath("$.[*].tranFreeField23")
            .value(hasItem(Base64Utils.encodeToString(DEFAULT_TRAN_FREE_FIELD_23)))
            .jsonPath("$.[*].tranFreeField24")
            .value(hasItem(sameInstant(DEFAULT_TRAN_FREE_FIELD_24)))
            .jsonPath("$.[*].responseCode")
            .value(hasItem(DEFAULT_RESPONSE_CODE))
            .jsonPath("$.[*].responseMessage")
            .value(hasItem(DEFAULT_RESPONSE_MESSAGE))
            .jsonPath("$.[*].responseDetails")
            .value(hasItem(DEFAULT_RESPONSE_DETAILS.toString()))
            .jsonPath("$.[*].transferReferenceId")
            .value(hasItem(DEFAULT_TRANSFER_REFERENCE_ID))
            .jsonPath("$.[*].transferStatus")
            .value(hasItem(DEFAULT_TRANSFER_STATUS))
            .jsonPath("$.[*].responseDateTime")
            .value(hasItem(sameInstant(DEFAULT_RESPONSE_DATE_TIME)))
            .jsonPath("$.[*].createdAt")
            .value(hasItem(sameInstant(DEFAULT_CREATED_AT)))
            .jsonPath("$.[*].createdBy")
            .value(hasItem(DEFAULT_CREATED_BY))
            .jsonPath("$.[*].updatedAt")
            .value(hasItem(sameInstant(DEFAULT_UPDATED_AT)))
            .jsonPath("$.[*].updatedBy")
            .value(hasItem(DEFAULT_UPDATED_BY));
    }

    @Test
    void getAmolDTransactions() {
        // Initialize the database
        amolDTransactionsRepository.save(amolDTransactions).block();

        // Get the amolDTransactions
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, amolDTransactions.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id")
            .value(is(amolDTransactions.getId().intValue()))
            .jsonPath("$.absaTranRef")
            .value(is(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.billerId")
            .value(is(DEFAULT_BILLER_ID))
            .jsonPath("$.dtDTransactionId")
            .value(is(DEFAULT_DT_D_TRANSACTION_ID))
            .jsonPath("$.amolDTransactionId")
            .value(is(DEFAULT_AMOL_D_TRANSACTION_ID))
            .jsonPath("$.transactionReferenceNumber")
            .value(is(DEFAULT_TRANSACTION_REFERENCE_NUMBER))
            .jsonPath("$.token")
            .value(is(DEFAULT_TOKEN))
            .jsonPath("$.transferId")
            .value(is(DEFAULT_TRANSFER_ID))
            .jsonPath("$.externalTxnId")
            .value(is(DEFAULT_EXTERNAL_TXN_ID))
            .jsonPath("$.customerReference")
            .value(is(DEFAULT_CUSTOMER_REFERENCE))
            .jsonPath("$.debitAccountNumber")
            .value(is(DEFAULT_DEBIT_ACCOUNT_NUMBER))
            .jsonPath("$.creditAccountNumber")
            .value(is(DEFAULT_CREDIT_ACCOUNT_NUMBER))
            .jsonPath("$.debitAmount")
            .value(is(sameNumber(DEFAULT_DEBIT_AMOUNT)))
            .jsonPath("$.creditAmount")
            .value(is(sameNumber(DEFAULT_CREDIT_AMOUNT)))
            .jsonPath("$.transactionAmount")
            .value(is(sameNumber(DEFAULT_TRANSACTION_AMOUNT)))
            .jsonPath("$.debitDate")
            .value(is(sameInstant(DEFAULT_DEBIT_DATE)))
            .jsonPath("$.creditDate")
            .value(is(sameInstant(DEFAULT_CREDIT_DATE)))
            .jsonPath("$.status")
            .value(is(DEFAULT_STATUS.toString()))
            .jsonPath("$.settlementDate")
            .value(is(sameInstant(DEFAULT_SETTLEMENT_DATE)))
            .jsonPath("$.debitCurrency")
            .value(is(DEFAULT_DEBIT_CURRENCY.toString()))
            .jsonPath("$.timestamp")
            .value(is(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.phoneNumber")
            .value(is(DEFAULT_PHONE_NUMBER))
            .jsonPath("$.email")
            .value(is(DEFAULT_EMAIL))
            .jsonPath("$.payerName")
            .value(is(DEFAULT_PAYER_NAME))
            .jsonPath("$.payerAddress")
            .value(is(DEFAULT_PAYER_ADDRESS))
            .jsonPath("$.payerEmail")
            .value(is(DEFAULT_PAYER_EMAIL))
            .jsonPath("$.payerPhoneNumber")
            .value(is(DEFAULT_PAYER_PHONE_NUMBER))
            .jsonPath("$.payerNarration")
            .value(is(DEFAULT_PAYER_NARRATION))
            .jsonPath("$.payerBranchId")
            .value(is(DEFAULT_PAYER_BRANCH_ID))
            .jsonPath("$.beneficiaryName")
            .value(is(DEFAULT_BENEFICIARY_NAME))
            .jsonPath("$.beneficiaryAccount")
            .value(is(DEFAULT_BENEFICIARY_ACCOUNT))
            .jsonPath("$.beneficiaryBranchId")
            .value(is(DEFAULT_BENEFICIARY_BRANCH_ID))
            .jsonPath("$.beneficiaryPhoneNumber")
            .value(is(DEFAULT_BENEFICIARY_PHONE_NUMBER))
            .jsonPath("$.tranStatus")
            .value(is(DEFAULT_TRAN_STATUS.toString()))
            .jsonPath("$.narration1")
            .value(is(DEFAULT_NARRATION_1))
            .jsonPath("$.narration2")
            .value(is(DEFAULT_NARRATION_2))
            .jsonPath("$.narration3")
            .value(is(DEFAULT_NARRATION_3))
            .jsonPath("$.taxAmount")
            .value(is(DEFAULT_TAX_AMOUNT))
            .jsonPath("$.taxGLAccount")
            .value(is(DEFAULT_TAX_GL_ACCOUNT))
            .jsonPath("$.taxNarration")
            .value(is(DEFAULT_TAX_NARRATION))
            .jsonPath("$.internalErrorCode")
            .value(is(DEFAULT_INTERNAL_ERROR_CODE))
            .jsonPath("$.externalErrorCode")
            .value(is(DEFAULT_EXTERNAL_ERROR_CODE))
            .jsonPath("$.payeeBranchId")
            .value(is(DEFAULT_PAYEE_BRANCH_ID))
            .jsonPath("$.payeeProductInstanceReference")
            .value(is(DEFAULT_PAYEE_PRODUCT_INSTANCE_REFERENCE))
            .jsonPath("$.payeeProductCode")
            .value(is(DEFAULT_PAYEE_PRODUCT_CODE))
            .jsonPath("$.payeeProductName")
            .value(is(DEFAULT_PAYEE_PRODUCT_NAME))
            .jsonPath("$.payeeProductDescription")
            .value(is(DEFAULT_PAYEE_PRODUCT_DESCRIPTION))
            .jsonPath("$.payeeProductUnitOfMeasure")
            .value(is(DEFAULT_PAYEE_PRODUCT_UNIT_OF_MEASURE))
            .jsonPath("$.payeeProductQuantity")
            .value(is(DEFAULT_PAYEE_PRODUCT_QUANTITY))
            .jsonPath("$.subCategoryCode")
            .value(is(DEFAULT_SUB_CATEGORY_CODE))
            .jsonPath("$.payerBankCode")
            .value(is(DEFAULT_PAYER_BANK_CODE))
            .jsonPath("$.payerBankName")
            .value(is(DEFAULT_PAYER_BANK_NAME))
            .jsonPath("$.payerBankAddress")
            .value(is(DEFAULT_PAYER_BANK_ADDRESS))
            .jsonPath("$.payerBankCity")
            .value(is(DEFAULT_PAYER_BANK_CITY))
            .jsonPath("$.payerBankState")
            .value(is(DEFAULT_PAYER_BANK_STATE))
            .jsonPath("$.payerBankCountry")
            .value(is(DEFAULT_PAYER_BANK_COUNTRY))
            .jsonPath("$.payerBankPostalCode")
            .value(is(DEFAULT_PAYER_BANK_POSTAL_CODE))
            .jsonPath("$.checkerId")
            .value(is(DEFAULT_CHECKER_ID))
            .jsonPath("$.remittanceInformation")
            .value(is(DEFAULT_REMITTANCE_INFORMATION))
            .jsonPath("$.paymentChannelCode")
            .value(is(DEFAULT_PAYMENT_CHANNEL_CODE.toString()))
            .jsonPath("$.feeAmount")
            .value(is(DEFAULT_FEE_AMOUNT))
            .jsonPath("$.feeGLAccount")
            .value(is(DEFAULT_FEE_GL_ACCOUNT))
            .jsonPath("$.feeNarration")
            .value(is(DEFAULT_FEE_NARRATION))
            .jsonPath("$.tranFreeField1")
            .value(is(DEFAULT_TRAN_FREE_FIELD_1))
            .jsonPath("$.tranFreeField2")
            .value(is(DEFAULT_TRAN_FREE_FIELD_2))
            .jsonPath("$.tranFreeField3")
            .value(is(DEFAULT_TRAN_FREE_FIELD_3))
            .jsonPath("$.tranFreeField4")
            .value(is(DEFAULT_TRAN_FREE_FIELD_4))
            .jsonPath("$.tranFreeField5")
            .value(is(DEFAULT_TRAN_FREE_FIELD_5))
            .jsonPath("$.tranFreeField6")
            .value(is(DEFAULT_TRAN_FREE_FIELD_6))
            .jsonPath("$.tranFreeField7")
            .value(is(DEFAULT_TRAN_FREE_FIELD_7))
            .jsonPath("$.tranFreeField8")
            .value(is(DEFAULT_TRAN_FREE_FIELD_8))
            .jsonPath("$.tranFreeField9")
            .value(is(DEFAULT_TRAN_FREE_FIELD_9))
            .jsonPath("$.tranFreeField10")
            .value(is(DEFAULT_TRAN_FREE_FIELD_10))
            .jsonPath("$.tranFreeField11")
            .value(is(DEFAULT_TRAN_FREE_FIELD_11))
            .jsonPath("$.tranFreeField12")
            .value(is(DEFAULT_TRAN_FREE_FIELD_12))
            .jsonPath("$.tranFreeField13")
            .value(is(DEFAULT_TRAN_FREE_FIELD_13))
            .jsonPath("$.tranFreeField14")
            .value(is(DEFAULT_TRAN_FREE_FIELD_14))
            .jsonPath("$.tranFreeField15")
            .value(is(DEFAULT_TRAN_FREE_FIELD_15))
            .jsonPath("$.tranFreeField16")
            .value(is(DEFAULT_TRAN_FREE_FIELD_16))
            .jsonPath("$.tranFreeField17")
            .value(is(DEFAULT_TRAN_FREE_FIELD_17))
            .jsonPath("$.tranFreeField18")
            .value(is(sameNumber(DEFAULT_TRAN_FREE_FIELD_18)))
            .jsonPath("$.tranFreeField19")
            .value(is(DEFAULT_TRAN_FREE_FIELD_19))
            .jsonPath("$.tranFreeField20")
            .value(is(DEFAULT_TRAN_FREE_FIELD_20.booleanValue()))
            .jsonPath("$.tranFreeField21")
            .value(is(DEFAULT_TRAN_FREE_FIELD_21))
            .jsonPath("$.tranFreeField22")
            .value(is(DEFAULT_TRAN_FREE_FIELD_22.toString()))
            .jsonPath("$.tranFreeField23ContentType")
            .value(is(DEFAULT_TRAN_FREE_FIELD_23_CONTENT_TYPE))
            .jsonPath("$.tranFreeField23")
            .value(is(Base64Utils.encodeToString(DEFAULT_TRAN_FREE_FIELD_23)))
            .jsonPath("$.tranFreeField24")
            .value(is(sameInstant(DEFAULT_TRAN_FREE_FIELD_24)))
            .jsonPath("$.responseCode")
            .value(is(DEFAULT_RESPONSE_CODE))
            .jsonPath("$.responseMessage")
            .value(is(DEFAULT_RESPONSE_MESSAGE))
            .jsonPath("$.responseDetails")
            .value(is(DEFAULT_RESPONSE_DETAILS.toString()))
            .jsonPath("$.transferReferenceId")
            .value(is(DEFAULT_TRANSFER_REFERENCE_ID))
            .jsonPath("$.transferStatus")
            .value(is(DEFAULT_TRANSFER_STATUS))
            .jsonPath("$.responseDateTime")
            .value(is(sameInstant(DEFAULT_RESPONSE_DATE_TIME)))
            .jsonPath("$.createdAt")
            .value(is(sameInstant(DEFAULT_CREATED_AT)))
            .jsonPath("$.createdBy")
            .value(is(DEFAULT_CREATED_BY))
            .jsonPath("$.updatedAt")
            .value(is(sameInstant(DEFAULT_UPDATED_AT)))
            .jsonPath("$.updatedBy")
            .value(is(DEFAULT_UPDATED_BY));
    }

    @Test
    void getNonExistingAmolDTransactions() {
        // Get the amolDTransactions
        webTestClient
            .get()
            .uri(ENTITY_API_URL_ID, Long.MAX_VALUE)
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNotFound();
    }

    @Test
    void putExistingAmolDTransactions() throws Exception {
        // Initialize the database
        amolDTransactionsRepository.save(amolDTransactions).block();

        int databaseSizeBeforeUpdate = amolDTransactionsRepository.findAll().collectList().block().size();
        amolDTransactionsSearchRepository.save(amolDTransactions).block();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(amolDTransactionsSearchRepository.findAll().collectList().block());

        // Update the amolDTransactions
        AmolDTransactions updatedAmolDTransactions = amolDTransactionsRepository.findById(amolDTransactions.getId()).block();
        updatedAmolDTransactions
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .billerId(UPDATED_BILLER_ID)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .amolDTransactionId(UPDATED_AMOL_D_TRANSACTION_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .token(UPDATED_TOKEN)
            .transferId(UPDATED_TRANSFER_ID)
            .externalTxnId(UPDATED_EXTERNAL_TXN_ID)
            .customerReference(UPDATED_CUSTOMER_REFERENCE)
            .debitAccountNumber(UPDATED_DEBIT_ACCOUNT_NUMBER)
            .creditAccountNumber(UPDATED_CREDIT_ACCOUNT_NUMBER)
            .debitAmount(UPDATED_DEBIT_AMOUNT)
            .creditAmount(UPDATED_CREDIT_AMOUNT)
            .transactionAmount(UPDATED_TRANSACTION_AMOUNT)
            .debitDate(UPDATED_DEBIT_DATE)
            .creditDate(UPDATED_CREDIT_DATE)
            .status(UPDATED_STATUS)
            .settlementDate(UPDATED_SETTLEMENT_DATE)
            .debitCurrency(UPDATED_DEBIT_CURRENCY)
            .timestamp(UPDATED_TIMESTAMP)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .email(UPDATED_EMAIL)
            .payerName(UPDATED_PAYER_NAME)
            .payerAddress(UPDATED_PAYER_ADDRESS)
            .payerEmail(UPDATED_PAYER_EMAIL)
            .payerPhoneNumber(UPDATED_PAYER_PHONE_NUMBER)
            .payerNarration(UPDATED_PAYER_NARRATION)
            .payerBranchId(UPDATED_PAYER_BRANCH_ID)
            .beneficiaryName(UPDATED_BENEFICIARY_NAME)
            .beneficiaryAccount(UPDATED_BENEFICIARY_ACCOUNT)
            .beneficiaryBranchId(UPDATED_BENEFICIARY_BRANCH_ID)
            .beneficiaryPhoneNumber(UPDATED_BENEFICIARY_PHONE_NUMBER)
            .tranStatus(UPDATED_TRAN_STATUS)
            .narration1(UPDATED_NARRATION_1)
            .narration2(UPDATED_NARRATION_2)
            .narration3(UPDATED_NARRATION_3)
            .taxAmount(UPDATED_TAX_AMOUNT)
            .taxGLAccount(UPDATED_TAX_GL_ACCOUNT)
            .taxNarration(UPDATED_TAX_NARRATION)
            .internalErrorCode(UPDATED_INTERNAL_ERROR_CODE)
            .externalErrorCode(UPDATED_EXTERNAL_ERROR_CODE)
            .payeeBranchId(UPDATED_PAYEE_BRANCH_ID)
            .payeeProductInstanceReference(UPDATED_PAYEE_PRODUCT_INSTANCE_REFERENCE)
            .payeeProductCode(UPDATED_PAYEE_PRODUCT_CODE)
            .payeeProductName(UPDATED_PAYEE_PRODUCT_NAME)
            .payeeProductDescription(UPDATED_PAYEE_PRODUCT_DESCRIPTION)
            .payeeProductUnitOfMeasure(UPDATED_PAYEE_PRODUCT_UNIT_OF_MEASURE)
            .payeeProductQuantity(UPDATED_PAYEE_PRODUCT_QUANTITY)
            .subCategoryCode(UPDATED_SUB_CATEGORY_CODE)
            .payerBankCode(UPDATED_PAYER_BANK_CODE)
            .payerBankName(UPDATED_PAYER_BANK_NAME)
            .payerBankAddress(UPDATED_PAYER_BANK_ADDRESS)
            .payerBankCity(UPDATED_PAYER_BANK_CITY)
            .payerBankState(UPDATED_PAYER_BANK_STATE)
            .payerBankCountry(UPDATED_PAYER_BANK_COUNTRY)
            .payerBankPostalCode(UPDATED_PAYER_BANK_POSTAL_CODE)
            .checkerId(UPDATED_CHECKER_ID)
            .remittanceInformation(UPDATED_REMITTANCE_INFORMATION)
            .paymentChannelCode(UPDATED_PAYMENT_CHANNEL_CODE)
            .feeAmount(UPDATED_FEE_AMOUNT)
            .feeGLAccount(UPDATED_FEE_GL_ACCOUNT)
            .feeNarration(UPDATED_FEE_NARRATION)
            .tranFreeField1(UPDATED_TRAN_FREE_FIELD_1)
            .tranFreeField2(UPDATED_TRAN_FREE_FIELD_2)
            .tranFreeField3(UPDATED_TRAN_FREE_FIELD_3)
            .tranFreeField4(UPDATED_TRAN_FREE_FIELD_4)
            .tranFreeField5(UPDATED_TRAN_FREE_FIELD_5)
            .tranFreeField6(UPDATED_TRAN_FREE_FIELD_6)
            .tranFreeField7(UPDATED_TRAN_FREE_FIELD_7)
            .tranFreeField8(UPDATED_TRAN_FREE_FIELD_8)
            .tranFreeField9(UPDATED_TRAN_FREE_FIELD_9)
            .tranFreeField10(UPDATED_TRAN_FREE_FIELD_10)
            .tranFreeField11(UPDATED_TRAN_FREE_FIELD_11)
            .tranFreeField12(UPDATED_TRAN_FREE_FIELD_12)
            .tranFreeField13(UPDATED_TRAN_FREE_FIELD_13)
            .tranFreeField14(UPDATED_TRAN_FREE_FIELD_14)
            .tranFreeField15(UPDATED_TRAN_FREE_FIELD_15)
            .tranFreeField16(UPDATED_TRAN_FREE_FIELD_16)
            .tranFreeField17(UPDATED_TRAN_FREE_FIELD_17)
            .tranFreeField18(UPDATED_TRAN_FREE_FIELD_18)
            .tranFreeField19(UPDATED_TRAN_FREE_FIELD_19)
            .tranFreeField20(UPDATED_TRAN_FREE_FIELD_20)
            .tranFreeField21(UPDATED_TRAN_FREE_FIELD_21)
            .tranFreeField22(UPDATED_TRAN_FREE_FIELD_22)
            .tranFreeField23(UPDATED_TRAN_FREE_FIELD_23)
            .tranFreeField23ContentType(UPDATED_TRAN_FREE_FIELD_23_CONTENT_TYPE)
            .tranFreeField24(UPDATED_TRAN_FREE_FIELD_24)
            .responseCode(UPDATED_RESPONSE_CODE)
            .responseMessage(UPDATED_RESPONSE_MESSAGE)
            .responseDetails(UPDATED_RESPONSE_DETAILS)
            .transferReferenceId(UPDATED_TRANSFER_REFERENCE_ID)
            .transferStatus(UPDATED_TRANSFER_STATUS)
            .responseDateTime(UPDATED_RESPONSE_DATE_TIME)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, updatedAmolDTransactions.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(updatedAmolDTransactions))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the AmolDTransactions in the database
        List<AmolDTransactions> amolDTransactionsList = amolDTransactionsRepository.findAll().collectList().block();
        assertThat(amolDTransactionsList).hasSize(databaseSizeBeforeUpdate);
        AmolDTransactions testAmolDTransactions = amolDTransactionsList.get(amolDTransactionsList.size() - 1);
        assertThat(testAmolDTransactions.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testAmolDTransactions.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
        assertThat(testAmolDTransactions.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
        assertThat(testAmolDTransactions.getAmolDTransactionId()).isEqualTo(UPDATED_AMOL_D_TRANSACTION_ID);
        assertThat(testAmolDTransactions.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testAmolDTransactions.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testAmolDTransactions.getTransferId()).isEqualTo(UPDATED_TRANSFER_ID);
        assertThat(testAmolDTransactions.getExternalTxnId()).isEqualTo(UPDATED_EXTERNAL_TXN_ID);
        assertThat(testAmolDTransactions.getCustomerReference()).isEqualTo(UPDATED_CUSTOMER_REFERENCE);
        assertThat(testAmolDTransactions.getDebitAccountNumber()).isEqualTo(UPDATED_DEBIT_ACCOUNT_NUMBER);
        assertThat(testAmolDTransactions.getCreditAccountNumber()).isEqualTo(UPDATED_CREDIT_ACCOUNT_NUMBER);
        assertThat(testAmolDTransactions.getDebitAmount()).isEqualByComparingTo(UPDATED_DEBIT_AMOUNT);
        assertThat(testAmolDTransactions.getCreditAmount()).isEqualByComparingTo(UPDATED_CREDIT_AMOUNT);
        assertThat(testAmolDTransactions.getTransactionAmount()).isEqualByComparingTo(UPDATED_TRANSACTION_AMOUNT);
        assertThat(testAmolDTransactions.getDebitDate()).isEqualTo(UPDATED_DEBIT_DATE);
        assertThat(testAmolDTransactions.getCreditDate()).isEqualTo(UPDATED_CREDIT_DATE);
        assertThat(testAmolDTransactions.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testAmolDTransactions.getSettlementDate()).isEqualTo(UPDATED_SETTLEMENT_DATE);
        assertThat(testAmolDTransactions.getDebitCurrency()).isEqualTo(UPDATED_DEBIT_CURRENCY);
        assertThat(testAmolDTransactions.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testAmolDTransactions.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testAmolDTransactions.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testAmolDTransactions.getPayerName()).isEqualTo(UPDATED_PAYER_NAME);
        assertThat(testAmolDTransactions.getPayerAddress()).isEqualTo(UPDATED_PAYER_ADDRESS);
        assertThat(testAmolDTransactions.getPayerEmail()).isEqualTo(UPDATED_PAYER_EMAIL);
        assertThat(testAmolDTransactions.getPayerPhoneNumber()).isEqualTo(UPDATED_PAYER_PHONE_NUMBER);
        assertThat(testAmolDTransactions.getPayerNarration()).isEqualTo(UPDATED_PAYER_NARRATION);
        assertThat(testAmolDTransactions.getPayerBranchId()).isEqualTo(UPDATED_PAYER_BRANCH_ID);
        assertThat(testAmolDTransactions.getBeneficiaryName()).isEqualTo(UPDATED_BENEFICIARY_NAME);
        assertThat(testAmolDTransactions.getBeneficiaryAccount()).isEqualTo(UPDATED_BENEFICIARY_ACCOUNT);
        assertThat(testAmolDTransactions.getBeneficiaryBranchId()).isEqualTo(UPDATED_BENEFICIARY_BRANCH_ID);
        assertThat(testAmolDTransactions.getBeneficiaryPhoneNumber()).isEqualTo(UPDATED_BENEFICIARY_PHONE_NUMBER);
        assertThat(testAmolDTransactions.getTranStatus()).isEqualTo(UPDATED_TRAN_STATUS);
        assertThat(testAmolDTransactions.getNarration1()).isEqualTo(UPDATED_NARRATION_1);
        assertThat(testAmolDTransactions.getNarration2()).isEqualTo(UPDATED_NARRATION_2);
        assertThat(testAmolDTransactions.getNarration3()).isEqualTo(UPDATED_NARRATION_3);
        assertThat(testAmolDTransactions.getTaxAmount()).isEqualTo(UPDATED_TAX_AMOUNT);
        assertThat(testAmolDTransactions.getTaxGLAccount()).isEqualTo(UPDATED_TAX_GL_ACCOUNT);
        assertThat(testAmolDTransactions.getTaxNarration()).isEqualTo(UPDATED_TAX_NARRATION);
        assertThat(testAmolDTransactions.getInternalErrorCode()).isEqualTo(UPDATED_INTERNAL_ERROR_CODE);
        assertThat(testAmolDTransactions.getExternalErrorCode()).isEqualTo(UPDATED_EXTERNAL_ERROR_CODE);
        assertThat(testAmolDTransactions.getPayeeBranchId()).isEqualTo(UPDATED_PAYEE_BRANCH_ID);
        assertThat(testAmolDTransactions.getPayeeProductInstanceReference()).isEqualTo(UPDATED_PAYEE_PRODUCT_INSTANCE_REFERENCE);
        assertThat(testAmolDTransactions.getPayeeProductCode()).isEqualTo(UPDATED_PAYEE_PRODUCT_CODE);
        assertThat(testAmolDTransactions.getPayeeProductName()).isEqualTo(UPDATED_PAYEE_PRODUCT_NAME);
        assertThat(testAmolDTransactions.getPayeeProductDescription()).isEqualTo(UPDATED_PAYEE_PRODUCT_DESCRIPTION);
        assertThat(testAmolDTransactions.getPayeeProductUnitOfMeasure()).isEqualTo(UPDATED_PAYEE_PRODUCT_UNIT_OF_MEASURE);
        assertThat(testAmolDTransactions.getPayeeProductQuantity()).isEqualTo(UPDATED_PAYEE_PRODUCT_QUANTITY);
        assertThat(testAmolDTransactions.getSubCategoryCode()).isEqualTo(UPDATED_SUB_CATEGORY_CODE);
        assertThat(testAmolDTransactions.getPayerBankCode()).isEqualTo(UPDATED_PAYER_BANK_CODE);
        assertThat(testAmolDTransactions.getPayerBankName()).isEqualTo(UPDATED_PAYER_BANK_NAME);
        assertThat(testAmolDTransactions.getPayerBankAddress()).isEqualTo(UPDATED_PAYER_BANK_ADDRESS);
        assertThat(testAmolDTransactions.getPayerBankCity()).isEqualTo(UPDATED_PAYER_BANK_CITY);
        assertThat(testAmolDTransactions.getPayerBankState()).isEqualTo(UPDATED_PAYER_BANK_STATE);
        assertThat(testAmolDTransactions.getPayerBankCountry()).isEqualTo(UPDATED_PAYER_BANK_COUNTRY);
        assertThat(testAmolDTransactions.getPayerBankPostalCode()).isEqualTo(UPDATED_PAYER_BANK_POSTAL_CODE);
        assertThat(testAmolDTransactions.getCheckerId()).isEqualTo(UPDATED_CHECKER_ID);
        assertThat(testAmolDTransactions.getRemittanceInformation()).isEqualTo(UPDATED_REMITTANCE_INFORMATION);
        assertThat(testAmolDTransactions.getPaymentChannelCode()).isEqualTo(UPDATED_PAYMENT_CHANNEL_CODE);
        assertThat(testAmolDTransactions.getFeeAmount()).isEqualTo(UPDATED_FEE_AMOUNT);
        assertThat(testAmolDTransactions.getFeeGLAccount()).isEqualTo(UPDATED_FEE_GL_ACCOUNT);
        assertThat(testAmolDTransactions.getFeeNarration()).isEqualTo(UPDATED_FEE_NARRATION);
        assertThat(testAmolDTransactions.getTranFreeField1()).isEqualTo(UPDATED_TRAN_FREE_FIELD_1);
        assertThat(testAmolDTransactions.getTranFreeField2()).isEqualTo(UPDATED_TRAN_FREE_FIELD_2);
        assertThat(testAmolDTransactions.getTranFreeField3()).isEqualTo(UPDATED_TRAN_FREE_FIELD_3);
        assertThat(testAmolDTransactions.getTranFreeField4()).isEqualTo(UPDATED_TRAN_FREE_FIELD_4);
        assertThat(testAmolDTransactions.getTranFreeField5()).isEqualTo(UPDATED_TRAN_FREE_FIELD_5);
        assertThat(testAmolDTransactions.getTranFreeField6()).isEqualTo(UPDATED_TRAN_FREE_FIELD_6);
        assertThat(testAmolDTransactions.getTranFreeField7()).isEqualTo(UPDATED_TRAN_FREE_FIELD_7);
        assertThat(testAmolDTransactions.getTranFreeField8()).isEqualTo(UPDATED_TRAN_FREE_FIELD_8);
        assertThat(testAmolDTransactions.getTranFreeField9()).isEqualTo(UPDATED_TRAN_FREE_FIELD_9);
        assertThat(testAmolDTransactions.getTranFreeField10()).isEqualTo(UPDATED_TRAN_FREE_FIELD_10);
        assertThat(testAmolDTransactions.getTranFreeField11()).isEqualTo(UPDATED_TRAN_FREE_FIELD_11);
        assertThat(testAmolDTransactions.getTranFreeField12()).isEqualTo(UPDATED_TRAN_FREE_FIELD_12);
        assertThat(testAmolDTransactions.getTranFreeField13()).isEqualTo(UPDATED_TRAN_FREE_FIELD_13);
        assertThat(testAmolDTransactions.getTranFreeField14()).isEqualTo(UPDATED_TRAN_FREE_FIELD_14);
        assertThat(testAmolDTransactions.getTranFreeField15()).isEqualTo(UPDATED_TRAN_FREE_FIELD_15);
        assertThat(testAmolDTransactions.getTranFreeField16()).isEqualTo(UPDATED_TRAN_FREE_FIELD_16);
        assertThat(testAmolDTransactions.getTranFreeField17()).isEqualTo(UPDATED_TRAN_FREE_FIELD_17);
        assertThat(testAmolDTransactions.getTranFreeField18()).isEqualByComparingTo(UPDATED_TRAN_FREE_FIELD_18);
        assertThat(testAmolDTransactions.getTranFreeField19()).isEqualTo(UPDATED_TRAN_FREE_FIELD_19);
        assertThat(testAmolDTransactions.getTranFreeField20()).isEqualTo(UPDATED_TRAN_FREE_FIELD_20);
        assertThat(testAmolDTransactions.getTranFreeField21()).isEqualTo(UPDATED_TRAN_FREE_FIELD_21);
        assertThat(testAmolDTransactions.getTranFreeField22()).isEqualTo(UPDATED_TRAN_FREE_FIELD_22);
        assertThat(testAmolDTransactions.getTranFreeField23()).isEqualTo(UPDATED_TRAN_FREE_FIELD_23);
        assertThat(testAmolDTransactions.getTranFreeField23ContentType()).isEqualTo(UPDATED_TRAN_FREE_FIELD_23_CONTENT_TYPE);
        assertThat(testAmolDTransactions.getTranFreeField24()).isEqualTo(UPDATED_TRAN_FREE_FIELD_24);
        assertThat(testAmolDTransactions.getResponseCode()).isEqualTo(UPDATED_RESPONSE_CODE);
        assertThat(testAmolDTransactions.getResponseMessage()).isEqualTo(UPDATED_RESPONSE_MESSAGE);
        assertThat(testAmolDTransactions.getResponseDetails()).isEqualTo(UPDATED_RESPONSE_DETAILS);
        assertThat(testAmolDTransactions.getTransferReferenceId()).isEqualTo(UPDATED_TRANSFER_REFERENCE_ID);
        assertThat(testAmolDTransactions.getTransferStatus()).isEqualTo(UPDATED_TRANSFER_STATUS);
        assertThat(testAmolDTransactions.getResponseDateTime()).isEqualTo(UPDATED_RESPONSE_DATE_TIME);
        assertThat(testAmolDTransactions.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testAmolDTransactions.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testAmolDTransactions.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testAmolDTransactions.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
        await()
            .atMost(5, TimeUnit.SECONDS)
            .untilAsserted(() -> {
                int searchDatabaseSizeAfter = IterableUtil.sizeOf(amolDTransactionsSearchRepository.findAll().collectList().block());
                assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
                List<AmolDTransactions> amolDTransactionsSearchList = IterableUtils.toList(
                    amolDTransactionsSearchRepository.findAll().collectList().block()
                );
                AmolDTransactions testAmolDTransactionsSearch = amolDTransactionsSearchList.get(searchDatabaseSizeAfter - 1);
                assertThat(testAmolDTransactionsSearch.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
                assertThat(testAmolDTransactionsSearch.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
                assertThat(testAmolDTransactionsSearch.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
                assertThat(testAmolDTransactionsSearch.getAmolDTransactionId()).isEqualTo(UPDATED_AMOL_D_TRANSACTION_ID);
                assertThat(testAmolDTransactionsSearch.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
                assertThat(testAmolDTransactionsSearch.getToken()).isEqualTo(UPDATED_TOKEN);
                assertThat(testAmolDTransactionsSearch.getTransferId()).isEqualTo(UPDATED_TRANSFER_ID);
                assertThat(testAmolDTransactionsSearch.getExternalTxnId()).isEqualTo(UPDATED_EXTERNAL_TXN_ID);
                assertThat(testAmolDTransactionsSearch.getCustomerReference()).isEqualTo(UPDATED_CUSTOMER_REFERENCE);
                assertThat(testAmolDTransactionsSearch.getDebitAccountNumber()).isEqualTo(UPDATED_DEBIT_ACCOUNT_NUMBER);
                assertThat(testAmolDTransactionsSearch.getCreditAccountNumber()).isEqualTo(UPDATED_CREDIT_ACCOUNT_NUMBER);
                assertThat(testAmolDTransactionsSearch.getDebitAmount()).isEqualByComparingTo(UPDATED_DEBIT_AMOUNT);
                assertThat(testAmolDTransactionsSearch.getCreditAmount()).isEqualByComparingTo(UPDATED_CREDIT_AMOUNT);
                assertThat(testAmolDTransactionsSearch.getTransactionAmount()).isEqualByComparingTo(UPDATED_TRANSACTION_AMOUNT);
                assertThat(testAmolDTransactionsSearch.getDebitDate()).isEqualTo(UPDATED_DEBIT_DATE);
                assertThat(testAmolDTransactionsSearch.getCreditDate()).isEqualTo(UPDATED_CREDIT_DATE);
                assertThat(testAmolDTransactionsSearch.getStatus()).isEqualTo(UPDATED_STATUS);
                assertThat(testAmolDTransactionsSearch.getSettlementDate()).isEqualTo(UPDATED_SETTLEMENT_DATE);
                assertThat(testAmolDTransactionsSearch.getDebitCurrency()).isEqualTo(UPDATED_DEBIT_CURRENCY);
                assertThat(testAmolDTransactionsSearch.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
                assertThat(testAmolDTransactionsSearch.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
                assertThat(testAmolDTransactionsSearch.getEmail()).isEqualTo(UPDATED_EMAIL);
                assertThat(testAmolDTransactionsSearch.getPayerName()).isEqualTo(UPDATED_PAYER_NAME);
                assertThat(testAmolDTransactionsSearch.getPayerAddress()).isEqualTo(UPDATED_PAYER_ADDRESS);
                assertThat(testAmolDTransactionsSearch.getPayerEmail()).isEqualTo(UPDATED_PAYER_EMAIL);
                assertThat(testAmolDTransactionsSearch.getPayerPhoneNumber()).isEqualTo(UPDATED_PAYER_PHONE_NUMBER);
                assertThat(testAmolDTransactionsSearch.getPayerNarration()).isEqualTo(UPDATED_PAYER_NARRATION);
                assertThat(testAmolDTransactionsSearch.getPayerBranchId()).isEqualTo(UPDATED_PAYER_BRANCH_ID);
                assertThat(testAmolDTransactionsSearch.getBeneficiaryName()).isEqualTo(UPDATED_BENEFICIARY_NAME);
                assertThat(testAmolDTransactionsSearch.getBeneficiaryAccount()).isEqualTo(UPDATED_BENEFICIARY_ACCOUNT);
                assertThat(testAmolDTransactionsSearch.getBeneficiaryBranchId()).isEqualTo(UPDATED_BENEFICIARY_BRANCH_ID);
                assertThat(testAmolDTransactionsSearch.getBeneficiaryPhoneNumber()).isEqualTo(UPDATED_BENEFICIARY_PHONE_NUMBER);
                assertThat(testAmolDTransactionsSearch.getTranStatus()).isEqualTo(UPDATED_TRAN_STATUS);
                assertThat(testAmolDTransactionsSearch.getNarration1()).isEqualTo(UPDATED_NARRATION_1);
                assertThat(testAmolDTransactionsSearch.getNarration2()).isEqualTo(UPDATED_NARRATION_2);
                assertThat(testAmolDTransactionsSearch.getNarration3()).isEqualTo(UPDATED_NARRATION_3);
                assertThat(testAmolDTransactionsSearch.getTaxAmount()).isEqualTo(UPDATED_TAX_AMOUNT);
                assertThat(testAmolDTransactionsSearch.getTaxGLAccount()).isEqualTo(UPDATED_TAX_GL_ACCOUNT);
                assertThat(testAmolDTransactionsSearch.getTaxNarration()).isEqualTo(UPDATED_TAX_NARRATION);
                assertThat(testAmolDTransactionsSearch.getInternalErrorCode()).isEqualTo(UPDATED_INTERNAL_ERROR_CODE);
                assertThat(testAmolDTransactionsSearch.getExternalErrorCode()).isEqualTo(UPDATED_EXTERNAL_ERROR_CODE);
                assertThat(testAmolDTransactionsSearch.getPayeeBranchId()).isEqualTo(UPDATED_PAYEE_BRANCH_ID);
                assertThat(testAmolDTransactionsSearch.getPayeeProductInstanceReference())
                    .isEqualTo(UPDATED_PAYEE_PRODUCT_INSTANCE_REFERENCE);
                assertThat(testAmolDTransactionsSearch.getPayeeProductCode()).isEqualTo(UPDATED_PAYEE_PRODUCT_CODE);
                assertThat(testAmolDTransactionsSearch.getPayeeProductName()).isEqualTo(UPDATED_PAYEE_PRODUCT_NAME);
                assertThat(testAmolDTransactionsSearch.getPayeeProductDescription()).isEqualTo(UPDATED_PAYEE_PRODUCT_DESCRIPTION);
                assertThat(testAmolDTransactionsSearch.getPayeeProductUnitOfMeasure()).isEqualTo(UPDATED_PAYEE_PRODUCT_UNIT_OF_MEASURE);
                assertThat(testAmolDTransactionsSearch.getPayeeProductQuantity()).isEqualTo(UPDATED_PAYEE_PRODUCT_QUANTITY);
                assertThat(testAmolDTransactionsSearch.getSubCategoryCode()).isEqualTo(UPDATED_SUB_CATEGORY_CODE);
                assertThat(testAmolDTransactionsSearch.getPayerBankCode()).isEqualTo(UPDATED_PAYER_BANK_CODE);
                assertThat(testAmolDTransactionsSearch.getPayerBankName()).isEqualTo(UPDATED_PAYER_BANK_NAME);
                assertThat(testAmolDTransactionsSearch.getPayerBankAddress()).isEqualTo(UPDATED_PAYER_BANK_ADDRESS);
                assertThat(testAmolDTransactionsSearch.getPayerBankCity()).isEqualTo(UPDATED_PAYER_BANK_CITY);
                assertThat(testAmolDTransactionsSearch.getPayerBankState()).isEqualTo(UPDATED_PAYER_BANK_STATE);
                assertThat(testAmolDTransactionsSearch.getPayerBankCountry()).isEqualTo(UPDATED_PAYER_BANK_COUNTRY);
                assertThat(testAmolDTransactionsSearch.getPayerBankPostalCode()).isEqualTo(UPDATED_PAYER_BANK_POSTAL_CODE);
                assertThat(testAmolDTransactionsSearch.getCheckerId()).isEqualTo(UPDATED_CHECKER_ID);
                assertThat(testAmolDTransactionsSearch.getRemittanceInformation()).isEqualTo(UPDATED_REMITTANCE_INFORMATION);
                assertThat(testAmolDTransactionsSearch.getPaymentChannelCode()).isEqualTo(UPDATED_PAYMENT_CHANNEL_CODE);
                assertThat(testAmolDTransactionsSearch.getFeeAmount()).isEqualTo(UPDATED_FEE_AMOUNT);
                assertThat(testAmolDTransactionsSearch.getFeeGLAccount()).isEqualTo(UPDATED_FEE_GL_ACCOUNT);
                assertThat(testAmolDTransactionsSearch.getFeeNarration()).isEqualTo(UPDATED_FEE_NARRATION);
                assertThat(testAmolDTransactionsSearch.getTranFreeField1()).isEqualTo(UPDATED_TRAN_FREE_FIELD_1);
                assertThat(testAmolDTransactionsSearch.getTranFreeField2()).isEqualTo(UPDATED_TRAN_FREE_FIELD_2);
                assertThat(testAmolDTransactionsSearch.getTranFreeField3()).isEqualTo(UPDATED_TRAN_FREE_FIELD_3);
                assertThat(testAmolDTransactionsSearch.getTranFreeField4()).isEqualTo(UPDATED_TRAN_FREE_FIELD_4);
                assertThat(testAmolDTransactionsSearch.getTranFreeField5()).isEqualTo(UPDATED_TRAN_FREE_FIELD_5);
                assertThat(testAmolDTransactionsSearch.getTranFreeField6()).isEqualTo(UPDATED_TRAN_FREE_FIELD_6);
                assertThat(testAmolDTransactionsSearch.getTranFreeField7()).isEqualTo(UPDATED_TRAN_FREE_FIELD_7);
                assertThat(testAmolDTransactionsSearch.getTranFreeField8()).isEqualTo(UPDATED_TRAN_FREE_FIELD_8);
                assertThat(testAmolDTransactionsSearch.getTranFreeField9()).isEqualTo(UPDATED_TRAN_FREE_FIELD_9);
                assertThat(testAmolDTransactionsSearch.getTranFreeField10()).isEqualTo(UPDATED_TRAN_FREE_FIELD_10);
                assertThat(testAmolDTransactionsSearch.getTranFreeField11()).isEqualTo(UPDATED_TRAN_FREE_FIELD_11);
                assertThat(testAmolDTransactionsSearch.getTranFreeField12()).isEqualTo(UPDATED_TRAN_FREE_FIELD_12);
                assertThat(testAmolDTransactionsSearch.getTranFreeField13()).isEqualTo(UPDATED_TRAN_FREE_FIELD_13);
                assertThat(testAmolDTransactionsSearch.getTranFreeField14()).isEqualTo(UPDATED_TRAN_FREE_FIELD_14);
                assertThat(testAmolDTransactionsSearch.getTranFreeField15()).isEqualTo(UPDATED_TRAN_FREE_FIELD_15);
                assertThat(testAmolDTransactionsSearch.getTranFreeField16()).isEqualTo(UPDATED_TRAN_FREE_FIELD_16);
                assertThat(testAmolDTransactionsSearch.getTranFreeField17()).isEqualTo(UPDATED_TRAN_FREE_FIELD_17);
                assertThat(testAmolDTransactionsSearch.getTranFreeField18()).isEqualByComparingTo(UPDATED_TRAN_FREE_FIELD_18);
                assertThat(testAmolDTransactionsSearch.getTranFreeField19()).isEqualTo(UPDATED_TRAN_FREE_FIELD_19);
                assertThat(testAmolDTransactionsSearch.getTranFreeField20()).isEqualTo(UPDATED_TRAN_FREE_FIELD_20);
                assertThat(testAmolDTransactionsSearch.getTranFreeField21()).isEqualTo(UPDATED_TRAN_FREE_FIELD_21);
                assertThat(testAmolDTransactionsSearch.getTranFreeField22()).isEqualTo(UPDATED_TRAN_FREE_FIELD_22);
                assertThat(testAmolDTransactionsSearch.getTranFreeField23()).isEqualTo(UPDATED_TRAN_FREE_FIELD_23);
                assertThat(testAmolDTransactionsSearch.getTranFreeField23ContentType()).isEqualTo(UPDATED_TRAN_FREE_FIELD_23_CONTENT_TYPE);
                assertThat(testAmolDTransactionsSearch.getTranFreeField24()).isEqualTo(UPDATED_TRAN_FREE_FIELD_24);
                assertThat(testAmolDTransactionsSearch.getResponseCode()).isEqualTo(UPDATED_RESPONSE_CODE);
                assertThat(testAmolDTransactionsSearch.getResponseMessage()).isEqualTo(UPDATED_RESPONSE_MESSAGE);
                assertThat(testAmolDTransactionsSearch.getResponseDetails()).isEqualTo(UPDATED_RESPONSE_DETAILS);
                assertThat(testAmolDTransactionsSearch.getTransferReferenceId()).isEqualTo(UPDATED_TRANSFER_REFERENCE_ID);
                assertThat(testAmolDTransactionsSearch.getTransferStatus()).isEqualTo(UPDATED_TRANSFER_STATUS);
                assertThat(testAmolDTransactionsSearch.getResponseDateTime()).isEqualTo(UPDATED_RESPONSE_DATE_TIME);
                assertThat(testAmolDTransactionsSearch.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
                assertThat(testAmolDTransactionsSearch.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
                assertThat(testAmolDTransactionsSearch.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
                assertThat(testAmolDTransactionsSearch.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
            });
    }

    @Test
    void putNonExistingAmolDTransactions() throws Exception {
        int databaseSizeBeforeUpdate = amolDTransactionsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(amolDTransactionsSearchRepository.findAll().collectList().block());
        amolDTransactions.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, amolDTransactions.getId())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(amolDTransactions))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the AmolDTransactions in the database
        List<AmolDTransactions> amolDTransactionsList = amolDTransactionsRepository.findAll().collectList().block();
        assertThat(amolDTransactionsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(amolDTransactionsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithIdMismatchAmolDTransactions() throws Exception {
        int databaseSizeBeforeUpdate = amolDTransactionsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(amolDTransactionsSearchRepository.findAll().collectList().block());
        amolDTransactions.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(amolDTransactions))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the AmolDTransactions in the database
        List<AmolDTransactions> amolDTransactionsList = amolDTransactionsRepository.findAll().collectList().block();
        assertThat(amolDTransactionsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(amolDTransactionsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void putWithMissingIdPathParamAmolDTransactions() throws Exception {
        int databaseSizeBeforeUpdate = amolDTransactionsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(amolDTransactionsSearchRepository.findAll().collectList().block());
        amolDTransactions.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .put()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(TestUtil.convertObjectToJsonBytes(amolDTransactions))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the AmolDTransactions in the database
        List<AmolDTransactions> amolDTransactionsList = amolDTransactionsRepository.findAll().collectList().block();
        assertThat(amolDTransactionsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(amolDTransactionsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void partialUpdateAmolDTransactionsWithPatch() throws Exception {
        // Initialize the database
        amolDTransactionsRepository.save(amolDTransactions).block();

        int databaseSizeBeforeUpdate = amolDTransactionsRepository.findAll().collectList().block().size();

        // Update the amolDTransactions using partial update
        AmolDTransactions partialUpdatedAmolDTransactions = new AmolDTransactions();
        partialUpdatedAmolDTransactions.setId(amolDTransactions.getId());

        partialUpdatedAmolDTransactions
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .amolDTransactionId(UPDATED_AMOL_D_TRANSACTION_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .transferId(UPDATED_TRANSFER_ID)
            .externalTxnId(UPDATED_EXTERNAL_TXN_ID)
            .customerReference(UPDATED_CUSTOMER_REFERENCE)
            .debitAmount(UPDATED_DEBIT_AMOUNT)
            .creditAmount(UPDATED_CREDIT_AMOUNT)
            .debitDate(UPDATED_DEBIT_DATE)
            .creditDate(UPDATED_CREDIT_DATE)
            .status(UPDATED_STATUS)
            .timestamp(UPDATED_TIMESTAMP)
            .payerNarration(UPDATED_PAYER_NARRATION)
            .beneficiaryName(UPDATED_BENEFICIARY_NAME)
            .beneficiaryAccount(UPDATED_BENEFICIARY_ACCOUNT)
            .beneficiaryBranchId(UPDATED_BENEFICIARY_BRANCH_ID)
            .tranStatus(UPDATED_TRAN_STATUS)
            .narration1(UPDATED_NARRATION_1)
            .taxAmount(UPDATED_TAX_AMOUNT)
            .taxGLAccount(UPDATED_TAX_GL_ACCOUNT)
            .taxNarration(UPDATED_TAX_NARRATION)
            .internalErrorCode(UPDATED_INTERNAL_ERROR_CODE)
            .payeeProductCode(UPDATED_PAYEE_PRODUCT_CODE)
            .payeeProductName(UPDATED_PAYEE_PRODUCT_NAME)
            .payeeProductDescription(UPDATED_PAYEE_PRODUCT_DESCRIPTION)
            .payeeProductQuantity(UPDATED_PAYEE_PRODUCT_QUANTITY)
            .payerBankName(UPDATED_PAYER_BANK_NAME)
            .payerBankAddress(UPDATED_PAYER_BANK_ADDRESS)
            .payerBankState(UPDATED_PAYER_BANK_STATE)
            .payerBankCountry(UPDATED_PAYER_BANK_COUNTRY)
            .payerBankPostalCode(UPDATED_PAYER_BANK_POSTAL_CODE)
            .checkerId(UPDATED_CHECKER_ID)
            .feeAmount(UPDATED_FEE_AMOUNT)
            .tranFreeField1(UPDATED_TRAN_FREE_FIELD_1)
            .tranFreeField4(UPDATED_TRAN_FREE_FIELD_4)
            .tranFreeField5(UPDATED_TRAN_FREE_FIELD_5)
            .tranFreeField11(UPDATED_TRAN_FREE_FIELD_11)
            .tranFreeField13(UPDATED_TRAN_FREE_FIELD_13)
            .tranFreeField14(UPDATED_TRAN_FREE_FIELD_14)
            .tranFreeField15(UPDATED_TRAN_FREE_FIELD_15)
            .tranFreeField17(UPDATED_TRAN_FREE_FIELD_17)
            .tranFreeField20(UPDATED_TRAN_FREE_FIELD_20)
            .tranFreeField22(UPDATED_TRAN_FREE_FIELD_22)
            .tranFreeField24(UPDATED_TRAN_FREE_FIELD_24)
            .responseCode(UPDATED_RESPONSE_CODE)
            .transferReferenceId(UPDATED_TRANSFER_REFERENCE_ID)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedAmolDTransactions.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedAmolDTransactions))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the AmolDTransactions in the database
        List<AmolDTransactions> amolDTransactionsList = amolDTransactionsRepository.findAll().collectList().block();
        assertThat(amolDTransactionsList).hasSize(databaseSizeBeforeUpdate);
        AmolDTransactions testAmolDTransactions = amolDTransactionsList.get(amolDTransactionsList.size() - 1);
        assertThat(testAmolDTransactions.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testAmolDTransactions.getBillerId()).isEqualTo(DEFAULT_BILLER_ID);
        assertThat(testAmolDTransactions.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
        assertThat(testAmolDTransactions.getAmolDTransactionId()).isEqualTo(UPDATED_AMOL_D_TRANSACTION_ID);
        assertThat(testAmolDTransactions.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testAmolDTransactions.getToken()).isEqualTo(DEFAULT_TOKEN);
        assertThat(testAmolDTransactions.getTransferId()).isEqualTo(UPDATED_TRANSFER_ID);
        assertThat(testAmolDTransactions.getExternalTxnId()).isEqualTo(UPDATED_EXTERNAL_TXN_ID);
        assertThat(testAmolDTransactions.getCustomerReference()).isEqualTo(UPDATED_CUSTOMER_REFERENCE);
        assertThat(testAmolDTransactions.getDebitAccountNumber()).isEqualTo(DEFAULT_DEBIT_ACCOUNT_NUMBER);
        assertThat(testAmolDTransactions.getCreditAccountNumber()).isEqualTo(DEFAULT_CREDIT_ACCOUNT_NUMBER);
        assertThat(testAmolDTransactions.getDebitAmount()).isEqualByComparingTo(UPDATED_DEBIT_AMOUNT);
        assertThat(testAmolDTransactions.getCreditAmount()).isEqualByComparingTo(UPDATED_CREDIT_AMOUNT);
        assertThat(testAmolDTransactions.getTransactionAmount()).isEqualByComparingTo(DEFAULT_TRANSACTION_AMOUNT);
        assertThat(testAmolDTransactions.getDebitDate()).isEqualTo(UPDATED_DEBIT_DATE);
        assertThat(testAmolDTransactions.getCreditDate()).isEqualTo(UPDATED_CREDIT_DATE);
        assertThat(testAmolDTransactions.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testAmolDTransactions.getSettlementDate()).isEqualTo(DEFAULT_SETTLEMENT_DATE);
        assertThat(testAmolDTransactions.getDebitCurrency()).isEqualTo(DEFAULT_DEBIT_CURRENCY);
        assertThat(testAmolDTransactions.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testAmolDTransactions.getPhoneNumber()).isEqualTo(DEFAULT_PHONE_NUMBER);
        assertThat(testAmolDTransactions.getEmail()).isEqualTo(DEFAULT_EMAIL);
        assertThat(testAmolDTransactions.getPayerName()).isEqualTo(DEFAULT_PAYER_NAME);
        assertThat(testAmolDTransactions.getPayerAddress()).isEqualTo(DEFAULT_PAYER_ADDRESS);
        assertThat(testAmolDTransactions.getPayerEmail()).isEqualTo(DEFAULT_PAYER_EMAIL);
        assertThat(testAmolDTransactions.getPayerPhoneNumber()).isEqualTo(DEFAULT_PAYER_PHONE_NUMBER);
        assertThat(testAmolDTransactions.getPayerNarration()).isEqualTo(UPDATED_PAYER_NARRATION);
        assertThat(testAmolDTransactions.getPayerBranchId()).isEqualTo(DEFAULT_PAYER_BRANCH_ID);
        assertThat(testAmolDTransactions.getBeneficiaryName()).isEqualTo(UPDATED_BENEFICIARY_NAME);
        assertThat(testAmolDTransactions.getBeneficiaryAccount()).isEqualTo(UPDATED_BENEFICIARY_ACCOUNT);
        assertThat(testAmolDTransactions.getBeneficiaryBranchId()).isEqualTo(UPDATED_BENEFICIARY_BRANCH_ID);
        assertThat(testAmolDTransactions.getBeneficiaryPhoneNumber()).isEqualTo(DEFAULT_BENEFICIARY_PHONE_NUMBER);
        assertThat(testAmolDTransactions.getTranStatus()).isEqualTo(UPDATED_TRAN_STATUS);
        assertThat(testAmolDTransactions.getNarration1()).isEqualTo(UPDATED_NARRATION_1);
        assertThat(testAmolDTransactions.getNarration2()).isEqualTo(DEFAULT_NARRATION_2);
        assertThat(testAmolDTransactions.getNarration3()).isEqualTo(DEFAULT_NARRATION_3);
        assertThat(testAmolDTransactions.getTaxAmount()).isEqualTo(UPDATED_TAX_AMOUNT);
        assertThat(testAmolDTransactions.getTaxGLAccount()).isEqualTo(UPDATED_TAX_GL_ACCOUNT);
        assertThat(testAmolDTransactions.getTaxNarration()).isEqualTo(UPDATED_TAX_NARRATION);
        assertThat(testAmolDTransactions.getInternalErrorCode()).isEqualTo(UPDATED_INTERNAL_ERROR_CODE);
        assertThat(testAmolDTransactions.getExternalErrorCode()).isEqualTo(DEFAULT_EXTERNAL_ERROR_CODE);
        assertThat(testAmolDTransactions.getPayeeBranchId()).isEqualTo(DEFAULT_PAYEE_BRANCH_ID);
        assertThat(testAmolDTransactions.getPayeeProductInstanceReference()).isEqualTo(DEFAULT_PAYEE_PRODUCT_INSTANCE_REFERENCE);
        assertThat(testAmolDTransactions.getPayeeProductCode()).isEqualTo(UPDATED_PAYEE_PRODUCT_CODE);
        assertThat(testAmolDTransactions.getPayeeProductName()).isEqualTo(UPDATED_PAYEE_PRODUCT_NAME);
        assertThat(testAmolDTransactions.getPayeeProductDescription()).isEqualTo(UPDATED_PAYEE_PRODUCT_DESCRIPTION);
        assertThat(testAmolDTransactions.getPayeeProductUnitOfMeasure()).isEqualTo(DEFAULT_PAYEE_PRODUCT_UNIT_OF_MEASURE);
        assertThat(testAmolDTransactions.getPayeeProductQuantity()).isEqualTo(UPDATED_PAYEE_PRODUCT_QUANTITY);
        assertThat(testAmolDTransactions.getSubCategoryCode()).isEqualTo(DEFAULT_SUB_CATEGORY_CODE);
        assertThat(testAmolDTransactions.getPayerBankCode()).isEqualTo(DEFAULT_PAYER_BANK_CODE);
        assertThat(testAmolDTransactions.getPayerBankName()).isEqualTo(UPDATED_PAYER_BANK_NAME);
        assertThat(testAmolDTransactions.getPayerBankAddress()).isEqualTo(UPDATED_PAYER_BANK_ADDRESS);
        assertThat(testAmolDTransactions.getPayerBankCity()).isEqualTo(DEFAULT_PAYER_BANK_CITY);
        assertThat(testAmolDTransactions.getPayerBankState()).isEqualTo(UPDATED_PAYER_BANK_STATE);
        assertThat(testAmolDTransactions.getPayerBankCountry()).isEqualTo(UPDATED_PAYER_BANK_COUNTRY);
        assertThat(testAmolDTransactions.getPayerBankPostalCode()).isEqualTo(UPDATED_PAYER_BANK_POSTAL_CODE);
        assertThat(testAmolDTransactions.getCheckerId()).isEqualTo(UPDATED_CHECKER_ID);
        assertThat(testAmolDTransactions.getRemittanceInformation()).isEqualTo(DEFAULT_REMITTANCE_INFORMATION);
        assertThat(testAmolDTransactions.getPaymentChannelCode()).isEqualTo(DEFAULT_PAYMENT_CHANNEL_CODE);
        assertThat(testAmolDTransactions.getFeeAmount()).isEqualTo(UPDATED_FEE_AMOUNT);
        assertThat(testAmolDTransactions.getFeeGLAccount()).isEqualTo(DEFAULT_FEE_GL_ACCOUNT);
        assertThat(testAmolDTransactions.getFeeNarration()).isEqualTo(DEFAULT_FEE_NARRATION);
        assertThat(testAmolDTransactions.getTranFreeField1()).isEqualTo(UPDATED_TRAN_FREE_FIELD_1);
        assertThat(testAmolDTransactions.getTranFreeField2()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_2);
        assertThat(testAmolDTransactions.getTranFreeField3()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_3);
        assertThat(testAmolDTransactions.getTranFreeField4()).isEqualTo(UPDATED_TRAN_FREE_FIELD_4);
        assertThat(testAmolDTransactions.getTranFreeField5()).isEqualTo(UPDATED_TRAN_FREE_FIELD_5);
        assertThat(testAmolDTransactions.getTranFreeField6()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_6);
        assertThat(testAmolDTransactions.getTranFreeField7()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_7);
        assertThat(testAmolDTransactions.getTranFreeField8()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_8);
        assertThat(testAmolDTransactions.getTranFreeField9()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_9);
        assertThat(testAmolDTransactions.getTranFreeField10()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_10);
        assertThat(testAmolDTransactions.getTranFreeField11()).isEqualTo(UPDATED_TRAN_FREE_FIELD_11);
        assertThat(testAmolDTransactions.getTranFreeField12()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_12);
        assertThat(testAmolDTransactions.getTranFreeField13()).isEqualTo(UPDATED_TRAN_FREE_FIELD_13);
        assertThat(testAmolDTransactions.getTranFreeField14()).isEqualTo(UPDATED_TRAN_FREE_FIELD_14);
        assertThat(testAmolDTransactions.getTranFreeField15()).isEqualTo(UPDATED_TRAN_FREE_FIELD_15);
        assertThat(testAmolDTransactions.getTranFreeField16()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_16);
        assertThat(testAmolDTransactions.getTranFreeField17()).isEqualTo(UPDATED_TRAN_FREE_FIELD_17);
        assertThat(testAmolDTransactions.getTranFreeField18()).isEqualByComparingTo(DEFAULT_TRAN_FREE_FIELD_18);
        assertThat(testAmolDTransactions.getTranFreeField19()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_19);
        assertThat(testAmolDTransactions.getTranFreeField20()).isEqualTo(UPDATED_TRAN_FREE_FIELD_20);
        assertThat(testAmolDTransactions.getTranFreeField21()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_21);
        assertThat(testAmolDTransactions.getTranFreeField22()).isEqualTo(UPDATED_TRAN_FREE_FIELD_22);
        assertThat(testAmolDTransactions.getTranFreeField23()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_23);
        assertThat(testAmolDTransactions.getTranFreeField23ContentType()).isEqualTo(DEFAULT_TRAN_FREE_FIELD_23_CONTENT_TYPE);
        assertThat(testAmolDTransactions.getTranFreeField24()).isEqualTo(UPDATED_TRAN_FREE_FIELD_24);
        assertThat(testAmolDTransactions.getResponseCode()).isEqualTo(UPDATED_RESPONSE_CODE);
        assertThat(testAmolDTransactions.getResponseMessage()).isEqualTo(DEFAULT_RESPONSE_MESSAGE);
        assertThat(testAmolDTransactions.getResponseDetails()).isEqualTo(DEFAULT_RESPONSE_DETAILS);
        assertThat(testAmolDTransactions.getTransferReferenceId()).isEqualTo(UPDATED_TRANSFER_REFERENCE_ID);
        assertThat(testAmolDTransactions.getTransferStatus()).isEqualTo(DEFAULT_TRANSFER_STATUS);
        assertThat(testAmolDTransactions.getResponseDateTime()).isEqualTo(DEFAULT_RESPONSE_DATE_TIME);
        assertThat(testAmolDTransactions.getCreatedAt()).isEqualTo(DEFAULT_CREATED_AT);
        assertThat(testAmolDTransactions.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testAmolDTransactions.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testAmolDTransactions.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    void fullUpdateAmolDTransactionsWithPatch() throws Exception {
        // Initialize the database
        amolDTransactionsRepository.save(amolDTransactions).block();

        int databaseSizeBeforeUpdate = amolDTransactionsRepository.findAll().collectList().block().size();

        // Update the amolDTransactions using partial update
        AmolDTransactions partialUpdatedAmolDTransactions = new AmolDTransactions();
        partialUpdatedAmolDTransactions.setId(amolDTransactions.getId());

        partialUpdatedAmolDTransactions
            .absaTranRef(UPDATED_ABSA_TRAN_REF)
            .billerId(UPDATED_BILLER_ID)
            .dtDTransactionId(UPDATED_DT_D_TRANSACTION_ID)
            .amolDTransactionId(UPDATED_AMOL_D_TRANSACTION_ID)
            .transactionReferenceNumber(UPDATED_TRANSACTION_REFERENCE_NUMBER)
            .token(UPDATED_TOKEN)
            .transferId(UPDATED_TRANSFER_ID)
            .externalTxnId(UPDATED_EXTERNAL_TXN_ID)
            .customerReference(UPDATED_CUSTOMER_REFERENCE)
            .debitAccountNumber(UPDATED_DEBIT_ACCOUNT_NUMBER)
            .creditAccountNumber(UPDATED_CREDIT_ACCOUNT_NUMBER)
            .debitAmount(UPDATED_DEBIT_AMOUNT)
            .creditAmount(UPDATED_CREDIT_AMOUNT)
            .transactionAmount(UPDATED_TRANSACTION_AMOUNT)
            .debitDate(UPDATED_DEBIT_DATE)
            .creditDate(UPDATED_CREDIT_DATE)
            .status(UPDATED_STATUS)
            .settlementDate(UPDATED_SETTLEMENT_DATE)
            .debitCurrency(UPDATED_DEBIT_CURRENCY)
            .timestamp(UPDATED_TIMESTAMP)
            .phoneNumber(UPDATED_PHONE_NUMBER)
            .email(UPDATED_EMAIL)
            .payerName(UPDATED_PAYER_NAME)
            .payerAddress(UPDATED_PAYER_ADDRESS)
            .payerEmail(UPDATED_PAYER_EMAIL)
            .payerPhoneNumber(UPDATED_PAYER_PHONE_NUMBER)
            .payerNarration(UPDATED_PAYER_NARRATION)
            .payerBranchId(UPDATED_PAYER_BRANCH_ID)
            .beneficiaryName(UPDATED_BENEFICIARY_NAME)
            .beneficiaryAccount(UPDATED_BENEFICIARY_ACCOUNT)
            .beneficiaryBranchId(UPDATED_BENEFICIARY_BRANCH_ID)
            .beneficiaryPhoneNumber(UPDATED_BENEFICIARY_PHONE_NUMBER)
            .tranStatus(UPDATED_TRAN_STATUS)
            .narration1(UPDATED_NARRATION_1)
            .narration2(UPDATED_NARRATION_2)
            .narration3(UPDATED_NARRATION_3)
            .taxAmount(UPDATED_TAX_AMOUNT)
            .taxGLAccount(UPDATED_TAX_GL_ACCOUNT)
            .taxNarration(UPDATED_TAX_NARRATION)
            .internalErrorCode(UPDATED_INTERNAL_ERROR_CODE)
            .externalErrorCode(UPDATED_EXTERNAL_ERROR_CODE)
            .payeeBranchId(UPDATED_PAYEE_BRANCH_ID)
            .payeeProductInstanceReference(UPDATED_PAYEE_PRODUCT_INSTANCE_REFERENCE)
            .payeeProductCode(UPDATED_PAYEE_PRODUCT_CODE)
            .payeeProductName(UPDATED_PAYEE_PRODUCT_NAME)
            .payeeProductDescription(UPDATED_PAYEE_PRODUCT_DESCRIPTION)
            .payeeProductUnitOfMeasure(UPDATED_PAYEE_PRODUCT_UNIT_OF_MEASURE)
            .payeeProductQuantity(UPDATED_PAYEE_PRODUCT_QUANTITY)
            .subCategoryCode(UPDATED_SUB_CATEGORY_CODE)
            .payerBankCode(UPDATED_PAYER_BANK_CODE)
            .payerBankName(UPDATED_PAYER_BANK_NAME)
            .payerBankAddress(UPDATED_PAYER_BANK_ADDRESS)
            .payerBankCity(UPDATED_PAYER_BANK_CITY)
            .payerBankState(UPDATED_PAYER_BANK_STATE)
            .payerBankCountry(UPDATED_PAYER_BANK_COUNTRY)
            .payerBankPostalCode(UPDATED_PAYER_BANK_POSTAL_CODE)
            .checkerId(UPDATED_CHECKER_ID)
            .remittanceInformation(UPDATED_REMITTANCE_INFORMATION)
            .paymentChannelCode(UPDATED_PAYMENT_CHANNEL_CODE)
            .feeAmount(UPDATED_FEE_AMOUNT)
            .feeGLAccount(UPDATED_FEE_GL_ACCOUNT)
            .feeNarration(UPDATED_FEE_NARRATION)
            .tranFreeField1(UPDATED_TRAN_FREE_FIELD_1)
            .tranFreeField2(UPDATED_TRAN_FREE_FIELD_2)
            .tranFreeField3(UPDATED_TRAN_FREE_FIELD_3)
            .tranFreeField4(UPDATED_TRAN_FREE_FIELD_4)
            .tranFreeField5(UPDATED_TRAN_FREE_FIELD_5)
            .tranFreeField6(UPDATED_TRAN_FREE_FIELD_6)
            .tranFreeField7(UPDATED_TRAN_FREE_FIELD_7)
            .tranFreeField8(UPDATED_TRAN_FREE_FIELD_8)
            .tranFreeField9(UPDATED_TRAN_FREE_FIELD_9)
            .tranFreeField10(UPDATED_TRAN_FREE_FIELD_10)
            .tranFreeField11(UPDATED_TRAN_FREE_FIELD_11)
            .tranFreeField12(UPDATED_TRAN_FREE_FIELD_12)
            .tranFreeField13(UPDATED_TRAN_FREE_FIELD_13)
            .tranFreeField14(UPDATED_TRAN_FREE_FIELD_14)
            .tranFreeField15(UPDATED_TRAN_FREE_FIELD_15)
            .tranFreeField16(UPDATED_TRAN_FREE_FIELD_16)
            .tranFreeField17(UPDATED_TRAN_FREE_FIELD_17)
            .tranFreeField18(UPDATED_TRAN_FREE_FIELD_18)
            .tranFreeField19(UPDATED_TRAN_FREE_FIELD_19)
            .tranFreeField20(UPDATED_TRAN_FREE_FIELD_20)
            .tranFreeField21(UPDATED_TRAN_FREE_FIELD_21)
            .tranFreeField22(UPDATED_TRAN_FREE_FIELD_22)
            .tranFreeField23(UPDATED_TRAN_FREE_FIELD_23)
            .tranFreeField23ContentType(UPDATED_TRAN_FREE_FIELD_23_CONTENT_TYPE)
            .tranFreeField24(UPDATED_TRAN_FREE_FIELD_24)
            .responseCode(UPDATED_RESPONSE_CODE)
            .responseMessage(UPDATED_RESPONSE_MESSAGE)
            .responseDetails(UPDATED_RESPONSE_DETAILS)
            .transferReferenceId(UPDATED_TRANSFER_REFERENCE_ID)
            .transferStatus(UPDATED_TRANSFER_STATUS)
            .responseDateTime(UPDATED_RESPONSE_DATE_TIME)
            .createdAt(UPDATED_CREATED_AT)
            .createdBy(UPDATED_CREATED_BY)
            .updatedAt(UPDATED_UPDATED_AT)
            .updatedBy(UPDATED_UPDATED_BY);

        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, partialUpdatedAmolDTransactions.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(partialUpdatedAmolDTransactions))
            .exchange()
            .expectStatus()
            .isOk();

        // Validate the AmolDTransactions in the database
        List<AmolDTransactions> amolDTransactionsList = amolDTransactionsRepository.findAll().collectList().block();
        assertThat(amolDTransactionsList).hasSize(databaseSizeBeforeUpdate);
        AmolDTransactions testAmolDTransactions = amolDTransactionsList.get(amolDTransactionsList.size() - 1);
        assertThat(testAmolDTransactions.getAbsaTranRef()).isEqualTo(UPDATED_ABSA_TRAN_REF);
        assertThat(testAmolDTransactions.getBillerId()).isEqualTo(UPDATED_BILLER_ID);
        assertThat(testAmolDTransactions.getDtDTransactionId()).isEqualTo(UPDATED_DT_D_TRANSACTION_ID);
        assertThat(testAmolDTransactions.getAmolDTransactionId()).isEqualTo(UPDATED_AMOL_D_TRANSACTION_ID);
        assertThat(testAmolDTransactions.getTransactionReferenceNumber()).isEqualTo(UPDATED_TRANSACTION_REFERENCE_NUMBER);
        assertThat(testAmolDTransactions.getToken()).isEqualTo(UPDATED_TOKEN);
        assertThat(testAmolDTransactions.getTransferId()).isEqualTo(UPDATED_TRANSFER_ID);
        assertThat(testAmolDTransactions.getExternalTxnId()).isEqualTo(UPDATED_EXTERNAL_TXN_ID);
        assertThat(testAmolDTransactions.getCustomerReference()).isEqualTo(UPDATED_CUSTOMER_REFERENCE);
        assertThat(testAmolDTransactions.getDebitAccountNumber()).isEqualTo(UPDATED_DEBIT_ACCOUNT_NUMBER);
        assertThat(testAmolDTransactions.getCreditAccountNumber()).isEqualTo(UPDATED_CREDIT_ACCOUNT_NUMBER);
        assertThat(testAmolDTransactions.getDebitAmount()).isEqualByComparingTo(UPDATED_DEBIT_AMOUNT);
        assertThat(testAmolDTransactions.getCreditAmount()).isEqualByComparingTo(UPDATED_CREDIT_AMOUNT);
        assertThat(testAmolDTransactions.getTransactionAmount()).isEqualByComparingTo(UPDATED_TRANSACTION_AMOUNT);
        assertThat(testAmolDTransactions.getDebitDate()).isEqualTo(UPDATED_DEBIT_DATE);
        assertThat(testAmolDTransactions.getCreditDate()).isEqualTo(UPDATED_CREDIT_DATE);
        assertThat(testAmolDTransactions.getStatus()).isEqualTo(UPDATED_STATUS);
        assertThat(testAmolDTransactions.getSettlementDate()).isEqualTo(UPDATED_SETTLEMENT_DATE);
        assertThat(testAmolDTransactions.getDebitCurrency()).isEqualTo(UPDATED_DEBIT_CURRENCY);
        assertThat(testAmolDTransactions.getTimestamp()).isEqualTo(UPDATED_TIMESTAMP);
        assertThat(testAmolDTransactions.getPhoneNumber()).isEqualTo(UPDATED_PHONE_NUMBER);
        assertThat(testAmolDTransactions.getEmail()).isEqualTo(UPDATED_EMAIL);
        assertThat(testAmolDTransactions.getPayerName()).isEqualTo(UPDATED_PAYER_NAME);
        assertThat(testAmolDTransactions.getPayerAddress()).isEqualTo(UPDATED_PAYER_ADDRESS);
        assertThat(testAmolDTransactions.getPayerEmail()).isEqualTo(UPDATED_PAYER_EMAIL);
        assertThat(testAmolDTransactions.getPayerPhoneNumber()).isEqualTo(UPDATED_PAYER_PHONE_NUMBER);
        assertThat(testAmolDTransactions.getPayerNarration()).isEqualTo(UPDATED_PAYER_NARRATION);
        assertThat(testAmolDTransactions.getPayerBranchId()).isEqualTo(UPDATED_PAYER_BRANCH_ID);
        assertThat(testAmolDTransactions.getBeneficiaryName()).isEqualTo(UPDATED_BENEFICIARY_NAME);
        assertThat(testAmolDTransactions.getBeneficiaryAccount()).isEqualTo(UPDATED_BENEFICIARY_ACCOUNT);
        assertThat(testAmolDTransactions.getBeneficiaryBranchId()).isEqualTo(UPDATED_BENEFICIARY_BRANCH_ID);
        assertThat(testAmolDTransactions.getBeneficiaryPhoneNumber()).isEqualTo(UPDATED_BENEFICIARY_PHONE_NUMBER);
        assertThat(testAmolDTransactions.getTranStatus()).isEqualTo(UPDATED_TRAN_STATUS);
        assertThat(testAmolDTransactions.getNarration1()).isEqualTo(UPDATED_NARRATION_1);
        assertThat(testAmolDTransactions.getNarration2()).isEqualTo(UPDATED_NARRATION_2);
        assertThat(testAmolDTransactions.getNarration3()).isEqualTo(UPDATED_NARRATION_3);
        assertThat(testAmolDTransactions.getTaxAmount()).isEqualTo(UPDATED_TAX_AMOUNT);
        assertThat(testAmolDTransactions.getTaxGLAccount()).isEqualTo(UPDATED_TAX_GL_ACCOUNT);
        assertThat(testAmolDTransactions.getTaxNarration()).isEqualTo(UPDATED_TAX_NARRATION);
        assertThat(testAmolDTransactions.getInternalErrorCode()).isEqualTo(UPDATED_INTERNAL_ERROR_CODE);
        assertThat(testAmolDTransactions.getExternalErrorCode()).isEqualTo(UPDATED_EXTERNAL_ERROR_CODE);
        assertThat(testAmolDTransactions.getPayeeBranchId()).isEqualTo(UPDATED_PAYEE_BRANCH_ID);
        assertThat(testAmolDTransactions.getPayeeProductInstanceReference()).isEqualTo(UPDATED_PAYEE_PRODUCT_INSTANCE_REFERENCE);
        assertThat(testAmolDTransactions.getPayeeProductCode()).isEqualTo(UPDATED_PAYEE_PRODUCT_CODE);
        assertThat(testAmolDTransactions.getPayeeProductName()).isEqualTo(UPDATED_PAYEE_PRODUCT_NAME);
        assertThat(testAmolDTransactions.getPayeeProductDescription()).isEqualTo(UPDATED_PAYEE_PRODUCT_DESCRIPTION);
        assertThat(testAmolDTransactions.getPayeeProductUnitOfMeasure()).isEqualTo(UPDATED_PAYEE_PRODUCT_UNIT_OF_MEASURE);
        assertThat(testAmolDTransactions.getPayeeProductQuantity()).isEqualTo(UPDATED_PAYEE_PRODUCT_QUANTITY);
        assertThat(testAmolDTransactions.getSubCategoryCode()).isEqualTo(UPDATED_SUB_CATEGORY_CODE);
        assertThat(testAmolDTransactions.getPayerBankCode()).isEqualTo(UPDATED_PAYER_BANK_CODE);
        assertThat(testAmolDTransactions.getPayerBankName()).isEqualTo(UPDATED_PAYER_BANK_NAME);
        assertThat(testAmolDTransactions.getPayerBankAddress()).isEqualTo(UPDATED_PAYER_BANK_ADDRESS);
        assertThat(testAmolDTransactions.getPayerBankCity()).isEqualTo(UPDATED_PAYER_BANK_CITY);
        assertThat(testAmolDTransactions.getPayerBankState()).isEqualTo(UPDATED_PAYER_BANK_STATE);
        assertThat(testAmolDTransactions.getPayerBankCountry()).isEqualTo(UPDATED_PAYER_BANK_COUNTRY);
        assertThat(testAmolDTransactions.getPayerBankPostalCode()).isEqualTo(UPDATED_PAYER_BANK_POSTAL_CODE);
        assertThat(testAmolDTransactions.getCheckerId()).isEqualTo(UPDATED_CHECKER_ID);
        assertThat(testAmolDTransactions.getRemittanceInformation()).isEqualTo(UPDATED_REMITTANCE_INFORMATION);
        assertThat(testAmolDTransactions.getPaymentChannelCode()).isEqualTo(UPDATED_PAYMENT_CHANNEL_CODE);
        assertThat(testAmolDTransactions.getFeeAmount()).isEqualTo(UPDATED_FEE_AMOUNT);
        assertThat(testAmolDTransactions.getFeeGLAccount()).isEqualTo(UPDATED_FEE_GL_ACCOUNT);
        assertThat(testAmolDTransactions.getFeeNarration()).isEqualTo(UPDATED_FEE_NARRATION);
        assertThat(testAmolDTransactions.getTranFreeField1()).isEqualTo(UPDATED_TRAN_FREE_FIELD_1);
        assertThat(testAmolDTransactions.getTranFreeField2()).isEqualTo(UPDATED_TRAN_FREE_FIELD_2);
        assertThat(testAmolDTransactions.getTranFreeField3()).isEqualTo(UPDATED_TRAN_FREE_FIELD_3);
        assertThat(testAmolDTransactions.getTranFreeField4()).isEqualTo(UPDATED_TRAN_FREE_FIELD_4);
        assertThat(testAmolDTransactions.getTranFreeField5()).isEqualTo(UPDATED_TRAN_FREE_FIELD_5);
        assertThat(testAmolDTransactions.getTranFreeField6()).isEqualTo(UPDATED_TRAN_FREE_FIELD_6);
        assertThat(testAmolDTransactions.getTranFreeField7()).isEqualTo(UPDATED_TRAN_FREE_FIELD_7);
        assertThat(testAmolDTransactions.getTranFreeField8()).isEqualTo(UPDATED_TRAN_FREE_FIELD_8);
        assertThat(testAmolDTransactions.getTranFreeField9()).isEqualTo(UPDATED_TRAN_FREE_FIELD_9);
        assertThat(testAmolDTransactions.getTranFreeField10()).isEqualTo(UPDATED_TRAN_FREE_FIELD_10);
        assertThat(testAmolDTransactions.getTranFreeField11()).isEqualTo(UPDATED_TRAN_FREE_FIELD_11);
        assertThat(testAmolDTransactions.getTranFreeField12()).isEqualTo(UPDATED_TRAN_FREE_FIELD_12);
        assertThat(testAmolDTransactions.getTranFreeField13()).isEqualTo(UPDATED_TRAN_FREE_FIELD_13);
        assertThat(testAmolDTransactions.getTranFreeField14()).isEqualTo(UPDATED_TRAN_FREE_FIELD_14);
        assertThat(testAmolDTransactions.getTranFreeField15()).isEqualTo(UPDATED_TRAN_FREE_FIELD_15);
        assertThat(testAmolDTransactions.getTranFreeField16()).isEqualTo(UPDATED_TRAN_FREE_FIELD_16);
        assertThat(testAmolDTransactions.getTranFreeField17()).isEqualTo(UPDATED_TRAN_FREE_FIELD_17);
        assertThat(testAmolDTransactions.getTranFreeField18()).isEqualByComparingTo(UPDATED_TRAN_FREE_FIELD_18);
        assertThat(testAmolDTransactions.getTranFreeField19()).isEqualTo(UPDATED_TRAN_FREE_FIELD_19);
        assertThat(testAmolDTransactions.getTranFreeField20()).isEqualTo(UPDATED_TRAN_FREE_FIELD_20);
        assertThat(testAmolDTransactions.getTranFreeField21()).isEqualTo(UPDATED_TRAN_FREE_FIELD_21);
        assertThat(testAmolDTransactions.getTranFreeField22()).isEqualTo(UPDATED_TRAN_FREE_FIELD_22);
        assertThat(testAmolDTransactions.getTranFreeField23()).isEqualTo(UPDATED_TRAN_FREE_FIELD_23);
        assertThat(testAmolDTransactions.getTranFreeField23ContentType()).isEqualTo(UPDATED_TRAN_FREE_FIELD_23_CONTENT_TYPE);
        assertThat(testAmolDTransactions.getTranFreeField24()).isEqualTo(UPDATED_TRAN_FREE_FIELD_24);
        assertThat(testAmolDTransactions.getResponseCode()).isEqualTo(UPDATED_RESPONSE_CODE);
        assertThat(testAmolDTransactions.getResponseMessage()).isEqualTo(UPDATED_RESPONSE_MESSAGE);
        assertThat(testAmolDTransactions.getResponseDetails()).isEqualTo(UPDATED_RESPONSE_DETAILS);
        assertThat(testAmolDTransactions.getTransferReferenceId()).isEqualTo(UPDATED_TRANSFER_REFERENCE_ID);
        assertThat(testAmolDTransactions.getTransferStatus()).isEqualTo(UPDATED_TRANSFER_STATUS);
        assertThat(testAmolDTransactions.getResponseDateTime()).isEqualTo(UPDATED_RESPONSE_DATE_TIME);
        assertThat(testAmolDTransactions.getCreatedAt()).isEqualTo(UPDATED_CREATED_AT);
        assertThat(testAmolDTransactions.getCreatedBy()).isEqualTo(UPDATED_CREATED_BY);
        assertThat(testAmolDTransactions.getUpdatedAt()).isEqualTo(UPDATED_UPDATED_AT);
        assertThat(testAmolDTransactions.getUpdatedBy()).isEqualTo(UPDATED_UPDATED_BY);
    }

    @Test
    void patchNonExistingAmolDTransactions() throws Exception {
        int databaseSizeBeforeUpdate = amolDTransactionsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(amolDTransactionsSearchRepository.findAll().collectList().block());
        amolDTransactions.setId(count.incrementAndGet());

        // If the entity doesn't have an ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, amolDTransactions.getId())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(amolDTransactions))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the AmolDTransactions in the database
        List<AmolDTransactions> amolDTransactionsList = amolDTransactionsRepository.findAll().collectList().block();
        assertThat(amolDTransactionsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(amolDTransactionsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithIdMismatchAmolDTransactions() throws Exception {
        int databaseSizeBeforeUpdate = amolDTransactionsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(amolDTransactionsSearchRepository.findAll().collectList().block());
        amolDTransactions.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL_ID, count.incrementAndGet())
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(amolDTransactions))
            .exchange()
            .expectStatus()
            .isBadRequest();

        // Validate the AmolDTransactions in the database
        List<AmolDTransactions> amolDTransactionsList = amolDTransactionsRepository.findAll().collectList().block();
        assertThat(amolDTransactionsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(amolDTransactionsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void patchWithMissingIdPathParamAmolDTransactions() throws Exception {
        int databaseSizeBeforeUpdate = amolDTransactionsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(amolDTransactionsSearchRepository.findAll().collectList().block());
        amolDTransactions.setId(count.incrementAndGet());

        // If url ID doesn't match entity ID, it will throw BadRequestAlertException
        webTestClient
            .patch()
            .uri(ENTITY_API_URL)
            .contentType(MediaType.valueOf("application/merge-patch+json"))
            .bodyValue(TestUtil.convertObjectToJsonBytes(amolDTransactions))
            .exchange()
            .expectStatus()
            .isEqualTo(405);

        // Validate the AmolDTransactions in the database
        List<AmolDTransactions> amolDTransactionsList = amolDTransactionsRepository.findAll().collectList().block();
        assertThat(amolDTransactionsList).hasSize(databaseSizeBeforeUpdate);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(amolDTransactionsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore);
    }

    @Test
    void deleteAmolDTransactions() {
        // Initialize the database
        amolDTransactionsRepository.save(amolDTransactions).block();
        amolDTransactionsRepository.save(amolDTransactions).block();
        amolDTransactionsSearchRepository.save(amolDTransactions).block();

        int databaseSizeBeforeDelete = amolDTransactionsRepository.findAll().collectList().block().size();
        int searchDatabaseSizeBefore = IterableUtil.sizeOf(amolDTransactionsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeBefore).isEqualTo(databaseSizeBeforeDelete);

        // Delete the amolDTransactions
        webTestClient
            .delete()
            .uri(ENTITY_API_URL_ID, amolDTransactions.getId())
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus()
            .isNoContent();

        // Validate the database contains one less item
        List<AmolDTransactions> amolDTransactionsList = amolDTransactionsRepository.findAll().collectList().block();
        assertThat(amolDTransactionsList).hasSize(databaseSizeBeforeDelete - 1);
        int searchDatabaseSizeAfter = IterableUtil.sizeOf(amolDTransactionsSearchRepository.findAll().collectList().block());
        assertThat(searchDatabaseSizeAfter).isEqualTo(searchDatabaseSizeBefore - 1);
    }

    @Test
    void searchAmolDTransactions() {
        // Initialize the database
        amolDTransactions = amolDTransactionsRepository.save(amolDTransactions).block();
        amolDTransactionsSearchRepository.save(amolDTransactions).block();

        // Search the amolDTransactions
        webTestClient
            .get()
            .uri(ENTITY_SEARCH_API_URL + "?query=id:" + amolDTransactions.getId())
            .exchange()
            .expectStatus()
            .isOk()
            .expectHeader()
            .contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.[*].id")
            .value(hasItem(amolDTransactions.getId().intValue()))
            .jsonPath("$.[*].absaTranRef")
            .value(hasItem(DEFAULT_ABSA_TRAN_REF.toString()))
            .jsonPath("$.[*].billerId")
            .value(hasItem(DEFAULT_BILLER_ID))
            .jsonPath("$.[*].dtDTransactionId")
            .value(hasItem(DEFAULT_DT_D_TRANSACTION_ID))
            .jsonPath("$.[*].amolDTransactionId")
            .value(hasItem(DEFAULT_AMOL_D_TRANSACTION_ID))
            .jsonPath("$.[*].transactionReferenceNumber")
            .value(hasItem(DEFAULT_TRANSACTION_REFERENCE_NUMBER))
            .jsonPath("$.[*].token")
            .value(hasItem(DEFAULT_TOKEN))
            .jsonPath("$.[*].transferId")
            .value(hasItem(DEFAULT_TRANSFER_ID))
            .jsonPath("$.[*].externalTxnId")
            .value(hasItem(DEFAULT_EXTERNAL_TXN_ID))
            .jsonPath("$.[*].customerReference")
            .value(hasItem(DEFAULT_CUSTOMER_REFERENCE))
            .jsonPath("$.[*].debitAccountNumber")
            .value(hasItem(DEFAULT_DEBIT_ACCOUNT_NUMBER))
            .jsonPath("$.[*].creditAccountNumber")
            .value(hasItem(DEFAULT_CREDIT_ACCOUNT_NUMBER))
            .jsonPath("$.[*].debitAmount")
            .value(hasItem(sameNumber(DEFAULT_DEBIT_AMOUNT)))
            .jsonPath("$.[*].creditAmount")
            .value(hasItem(sameNumber(DEFAULT_CREDIT_AMOUNT)))
            .jsonPath("$.[*].transactionAmount")
            .value(hasItem(sameNumber(DEFAULT_TRANSACTION_AMOUNT)))
            .jsonPath("$.[*].debitDate")
            .value(hasItem(sameInstant(DEFAULT_DEBIT_DATE)))
            .jsonPath("$.[*].creditDate")
            .value(hasItem(sameInstant(DEFAULT_CREDIT_DATE)))
            .jsonPath("$.[*].status")
            .value(hasItem(DEFAULT_STATUS.toString()))
            .jsonPath("$.[*].settlementDate")
            .value(hasItem(sameInstant(DEFAULT_SETTLEMENT_DATE)))
            .jsonPath("$.[*].debitCurrency")
            .value(hasItem(DEFAULT_DEBIT_CURRENCY.toString()))
            .jsonPath("$.[*].timestamp")
            .value(hasItem(sameInstant(DEFAULT_TIMESTAMP)))
            .jsonPath("$.[*].phoneNumber")
            .value(hasItem(DEFAULT_PHONE_NUMBER))
            .jsonPath("$.[*].email")
            .value(hasItem(DEFAULT_EMAIL))
            .jsonPath("$.[*].payerName")
            .value(hasItem(DEFAULT_PAYER_NAME))
            .jsonPath("$.[*].payerAddress")
            .value(hasItem(DEFAULT_PAYER_ADDRESS))
            .jsonPath("$.[*].payerEmail")
            .value(hasItem(DEFAULT_PAYER_EMAIL))
            .jsonPath("$.[*].payerPhoneNumber")
            .value(hasItem(DEFAULT_PAYER_PHONE_NUMBER))
            .jsonPath("$.[*].payerNarration")
            .value(hasItem(DEFAULT_PAYER_NARRATION))
            .jsonPath("$.[*].payerBranchId")
            .value(hasItem(DEFAULT_PAYER_BRANCH_ID))
            .jsonPath("$.[*].beneficiaryName")
            .value(hasItem(DEFAULT_BENEFICIARY_NAME))
            .jsonPath("$.[*].beneficiaryAccount")
            .value(hasItem(DEFAULT_BENEFICIARY_ACCOUNT))
            .jsonPath("$.[*].beneficiaryBranchId")
            .value(hasItem(DEFAULT_BENEFICIARY_BRANCH_ID))
            .jsonPath("$.[*].beneficiaryPhoneNumber")
            .value(hasItem(DEFAULT_BENEFICIARY_PHONE_NUMBER))
            .jsonPath("$.[*].tranStatus")
            .value(hasItem(DEFAULT_TRAN_STATUS.toString()))
            .jsonPath("$.[*].narration1")
            .value(hasItem(DEFAULT_NARRATION_1))
            .jsonPath("$.[*].narration2")
            .value(hasItem(DEFAULT_NARRATION_2))
            .jsonPath("$.[*].narration3")
            .value(hasItem(DEFAULT_NARRATION_3))
            .jsonPath("$.[*].taxAmount")
            .value(hasItem(DEFAULT_TAX_AMOUNT))
            .jsonPath("$.[*].taxGLAccount")
            .value(hasItem(DEFAULT_TAX_GL_ACCOUNT))
            .jsonPath("$.[*].taxNarration")
            .value(hasItem(DEFAULT_TAX_NARRATION))
            .jsonPath("$.[*].internalErrorCode")
            .value(hasItem(DEFAULT_INTERNAL_ERROR_CODE))
            .jsonPath("$.[*].externalErrorCode")
            .value(hasItem(DEFAULT_EXTERNAL_ERROR_CODE))
            .jsonPath("$.[*].payeeBranchId")
            .value(hasItem(DEFAULT_PAYEE_BRANCH_ID))
            .jsonPath("$.[*].payeeProductInstanceReference")
            .value(hasItem(DEFAULT_PAYEE_PRODUCT_INSTANCE_REFERENCE))
            .jsonPath("$.[*].payeeProductCode")
            .value(hasItem(DEFAULT_PAYEE_PRODUCT_CODE))
            .jsonPath("$.[*].payeeProductName")
            .value(hasItem(DEFAULT_PAYEE_PRODUCT_NAME))
            .jsonPath("$.[*].payeeProductDescription")
            .value(hasItem(DEFAULT_PAYEE_PRODUCT_DESCRIPTION))
            .jsonPath("$.[*].payeeProductUnitOfMeasure")
            .value(hasItem(DEFAULT_PAYEE_PRODUCT_UNIT_OF_MEASURE))
            .jsonPath("$.[*].payeeProductQuantity")
            .value(hasItem(DEFAULT_PAYEE_PRODUCT_QUANTITY))
            .jsonPath("$.[*].subCategoryCode")
            .value(hasItem(DEFAULT_SUB_CATEGORY_CODE))
            .jsonPath("$.[*].payerBankCode")
            .value(hasItem(DEFAULT_PAYER_BANK_CODE))
            .jsonPath("$.[*].payerBankName")
            .value(hasItem(DEFAULT_PAYER_BANK_NAME))
            .jsonPath("$.[*].payerBankAddress")
            .value(hasItem(DEFAULT_PAYER_BANK_ADDRESS))
            .jsonPath("$.[*].payerBankCity")
            .value(hasItem(DEFAULT_PAYER_BANK_CITY))
            .jsonPath("$.[*].payerBankState")
            .value(hasItem(DEFAULT_PAYER_BANK_STATE))
            .jsonPath("$.[*].payerBankCountry")
            .value(hasItem(DEFAULT_PAYER_BANK_COUNTRY))
            .jsonPath("$.[*].payerBankPostalCode")
            .value(hasItem(DEFAULT_PAYER_BANK_POSTAL_CODE))
            .jsonPath("$.[*].checkerId")
            .value(hasItem(DEFAULT_CHECKER_ID))
            .jsonPath("$.[*].remittanceInformation")
            .value(hasItem(DEFAULT_REMITTANCE_INFORMATION))
            .jsonPath("$.[*].paymentChannelCode")
            .value(hasItem(DEFAULT_PAYMENT_CHANNEL_CODE.toString()))
            .jsonPath("$.[*].feeAmount")
            .value(hasItem(DEFAULT_FEE_AMOUNT))
            .jsonPath("$.[*].feeGLAccount")
            .value(hasItem(DEFAULT_FEE_GL_ACCOUNT))
            .jsonPath("$.[*].feeNarration")
            .value(hasItem(DEFAULT_FEE_NARRATION))
            .jsonPath("$.[*].tranFreeField1")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_1))
            .jsonPath("$.[*].tranFreeField2")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_2))
            .jsonPath("$.[*].tranFreeField3")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_3))
            .jsonPath("$.[*].tranFreeField4")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_4))
            .jsonPath("$.[*].tranFreeField5")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_5))
            .jsonPath("$.[*].tranFreeField6")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_6))
            .jsonPath("$.[*].tranFreeField7")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_7))
            .jsonPath("$.[*].tranFreeField8")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_8))
            .jsonPath("$.[*].tranFreeField9")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_9))
            .jsonPath("$.[*].tranFreeField10")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_10))
            .jsonPath("$.[*].tranFreeField11")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_11))
            .jsonPath("$.[*].tranFreeField12")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_12))
            .jsonPath("$.[*].tranFreeField13")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_13))
            .jsonPath("$.[*].tranFreeField14")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_14))
            .jsonPath("$.[*].tranFreeField15")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_15))
            .jsonPath("$.[*].tranFreeField16")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_16))
            .jsonPath("$.[*].tranFreeField17")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_17))
            .jsonPath("$.[*].tranFreeField18")
            .value(hasItem(sameNumber(DEFAULT_TRAN_FREE_FIELD_18)))
            .jsonPath("$.[*].tranFreeField19")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_19))
            .jsonPath("$.[*].tranFreeField20")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_20.booleanValue()))
            .jsonPath("$.[*].tranFreeField21")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_21))
            .jsonPath("$.[*].tranFreeField22")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_22.toString()))
            .jsonPath("$.[*].tranFreeField23ContentType")
            .value(hasItem(DEFAULT_TRAN_FREE_FIELD_23_CONTENT_TYPE))
            .jsonPath("$.[*].tranFreeField23")
            .value(hasItem(Base64Utils.encodeToString(DEFAULT_TRAN_FREE_FIELD_23)))
            .jsonPath("$.[*].tranFreeField24")
            .value(hasItem(sameInstant(DEFAULT_TRAN_FREE_FIELD_24)))
            .jsonPath("$.[*].responseCode")
            .value(hasItem(DEFAULT_RESPONSE_CODE))
            .jsonPath("$.[*].responseMessage")
            .value(hasItem(DEFAULT_RESPONSE_MESSAGE))
            .jsonPath("$.[*].responseDetails")
            .value(hasItem(DEFAULT_RESPONSE_DETAILS.toString()))
            .jsonPath("$.[*].transferReferenceId")
            .value(hasItem(DEFAULT_TRANSFER_REFERENCE_ID))
            .jsonPath("$.[*].transferStatus")
            .value(hasItem(DEFAULT_TRANSFER_STATUS))
            .jsonPath("$.[*].responseDateTime")
            .value(hasItem(sameInstant(DEFAULT_RESPONSE_DATE_TIME)))
            .jsonPath("$.[*].createdAt")
            .value(hasItem(sameInstant(DEFAULT_CREATED_AT)))
            .jsonPath("$.[*].createdBy")
            .value(hasItem(DEFAULT_CREATED_BY))
            .jsonPath("$.[*].updatedAt")
            .value(hasItem(sameInstant(DEFAULT_UPDATED_AT)))
            .jsonPath("$.[*].updatedBy")
            .value(hasItem(DEFAULT_UPDATED_BY));
    }
}
