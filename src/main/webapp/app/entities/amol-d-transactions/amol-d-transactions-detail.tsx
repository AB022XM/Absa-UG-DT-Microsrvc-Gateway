import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, openFile, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './amol-d-transactions.reducer';

export const AmolDTransactionsDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const amolDTransactionsEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.amolDTransactions.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="amolDTransactionsDetailsHeading">
          <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.detail.title">AmolDTransactions</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.id}</dd>
          <dt>
            <span id="absaTranRef">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.absaTranRef">Absa Tran Ref</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.absaTranRef}</dd>
          <dt>
            <span id="billerId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.billerId">Biller Id</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.billerId}</dd>
          <dt>
            <span id="dtDTransactionId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.dtDTransactionId">Dt D Transaction Id</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.dtDTransactionId}</dd>
          <dt>
            <span id="amolDTransactionId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.amolDTransactionId">Amol D Transaction Id</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.amolDTransactionId}</dd>
          <dt>
            <span id="transactionReferenceNumber">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.transactionReferenceNumber">
                Transaction Reference Number
              </Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.transactionReferenceNumber}</dd>
          <dt>
            <span id="token">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.token">Token</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.token}</dd>
          <dt>
            <span id="transferId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.transferId">Transfer Id</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.transferId}</dd>
          <dt>
            <span id="externalTxnId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.externalTxnId">External Txn Id</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.externalTxnId}</dd>
          <dt>
            <span id="customerReference">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.customerReference">Customer Reference</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.customerReference}</dd>
          <dt>
            <span id="debitAccountNumber">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.debitAccountNumber">Debit Account Number</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.debitAccountNumber}</dd>
          <dt>
            <span id="creditAccountNumber">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.creditAccountNumber">Credit Account Number</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.creditAccountNumber}</dd>
          <dt>
            <span id="debitAmount">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.debitAmount">Debit Amount</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.debitAmount}</dd>
          <dt>
            <span id="creditAmount">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.creditAmount">Credit Amount</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.creditAmount}</dd>
          <dt>
            <span id="transactionAmount">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.transactionAmount">Transaction Amount</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.transactionAmount}</dd>
          <dt>
            <span id="debitDate">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.debitDate">Debit Date</Translate>
            </span>
          </dt>
          <dd>
            {amolDTransactionsEntity.debitDate ? (
              <TextFormat value={amolDTransactionsEntity.debitDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="creditDate">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.creditDate">Credit Date</Translate>
            </span>
          </dt>
          <dd>
            {amolDTransactionsEntity.creditDate ? (
              <TextFormat value={amolDTransactionsEntity.creditDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="status">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.status">Status</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.status}</dd>
          <dt>
            <span id="settlementDate">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.settlementDate">Settlement Date</Translate>
            </span>
          </dt>
          <dd>
            {amolDTransactionsEntity.settlementDate ? (
              <TextFormat value={amolDTransactionsEntity.settlementDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="debitCurrency">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.debitCurrency">Debit Currency</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.debitCurrency}</dd>
          <dt>
            <span id="timestamp">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.timestamp">Timestamp</Translate>
            </span>
          </dt>
          <dd>
            {amolDTransactionsEntity.timestamp ? (
              <TextFormat value={amolDTransactionsEntity.timestamp} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="phoneNumber">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.phoneNumber">Phone Number</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.phoneNumber}</dd>
          <dt>
            <span id="email">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.email">Email</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.email}</dd>
          <dt>
            <span id="payerName">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.payerName">Payer Name</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.payerName}</dd>
          <dt>
            <span id="payerAddress">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.payerAddress">Payer Address</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.payerAddress}</dd>
          <dt>
            <span id="payerEmail">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.payerEmail">Payer Email</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.payerEmail}</dd>
          <dt>
            <span id="payerPhoneNumber">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.payerPhoneNumber">Payer Phone Number</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.payerPhoneNumber}</dd>
          <dt>
            <span id="payerNarration">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.payerNarration">Payer Narration</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.payerNarration}</dd>
          <dt>
            <span id="payerBranchId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.payerBranchId">Payer Branch Id</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.payerBranchId}</dd>
          <dt>
            <span id="beneficiaryName">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.beneficiaryName">Beneficiary Name</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.beneficiaryName}</dd>
          <dt>
            <span id="beneficiaryAccount">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.beneficiaryAccount">Beneficiary Account</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.beneficiaryAccount}</dd>
          <dt>
            <span id="beneficiaryBranchId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.beneficiaryBranchId">Beneficiary Branch Id</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.beneficiaryBranchId}</dd>
          <dt>
            <span id="beneficiaryPhoneNumber">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.beneficiaryPhoneNumber">
                Beneficiary Phone Number
              </Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.beneficiaryPhoneNumber}</dd>
          <dt>
            <span id="tranStatus">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranStatus">Tran Status</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.tranStatus}</dd>
          <dt>
            <span id="narration1">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.narration1">Narration 1</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.narration1}</dd>
          <dt>
            <span id="narration2">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.narration2">Narration 2</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.narration2}</dd>
          <dt>
            <span id="narration3">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.narration3">Narration 3</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.narration3}</dd>
          <dt>
            <span id="taxAmount">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.taxAmount">Tax Amount</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.taxAmount}</dd>
          <dt>
            <span id="taxGLAccount">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.taxGLAccount">Tax GL Account</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.taxGLAccount}</dd>
          <dt>
            <span id="taxNarration">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.taxNarration">Tax Narration</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.taxNarration}</dd>
          <dt>
            <span id="internalErrorCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.internalErrorCode">Internal Error Code</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.internalErrorCode}</dd>
          <dt>
            <span id="externalErrorCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.externalErrorCode">External Error Code</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.externalErrorCode}</dd>
          <dt>
            <span id="payeeBranchId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.payeeBranchId">Payee Branch Id</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.payeeBranchId}</dd>
          <dt>
            <span id="payeeProductInstanceReference">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.payeeProductInstanceReference">
                Payee Product Instance Reference
              </Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.payeeProductInstanceReference}</dd>
          <dt>
            <span id="payeeProductCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.payeeProductCode">Payee Product Code</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.payeeProductCode}</dd>
          <dt>
            <span id="payeeProductName">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.payeeProductName">Payee Product Name</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.payeeProductName}</dd>
          <dt>
            <span id="payeeProductDescription">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.payeeProductDescription">
                Payee Product Description
              </Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.payeeProductDescription}</dd>
          <dt>
            <span id="payeeProductUnitOfMeasure">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.payeeProductUnitOfMeasure">
                Payee Product Unit Of Measure
              </Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.payeeProductUnitOfMeasure}</dd>
          <dt>
            <span id="payeeProductQuantity">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.payeeProductQuantity">Payee Product Quantity</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.payeeProductQuantity}</dd>
          <dt>
            <span id="subCategoryCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.subCategoryCode">Sub Category Code</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.subCategoryCode}</dd>
          <dt>
            <span id="payerBankCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.payerBankCode">Payer Bank Code</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.payerBankCode}</dd>
          <dt>
            <span id="payerBankName">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.payerBankName">Payer Bank Name</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.payerBankName}</dd>
          <dt>
            <span id="payerBankAddress">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.payerBankAddress">Payer Bank Address</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.payerBankAddress}</dd>
          <dt>
            <span id="payerBankCity">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.payerBankCity">Payer Bank City</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.payerBankCity}</dd>
          <dt>
            <span id="payerBankState">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.payerBankState">Payer Bank State</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.payerBankState}</dd>
          <dt>
            <span id="payerBankCountry">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.payerBankCountry">Payer Bank Country</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.payerBankCountry}</dd>
          <dt>
            <span id="payerBankPostalCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.payerBankPostalCode">Payer Bank Postal Code</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.payerBankPostalCode}</dd>
          <dt>
            <span id="checkerId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.checkerId">Checker Id</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.checkerId}</dd>
          <dt>
            <span id="remittanceInformation">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.remittanceInformation">Remittance Information</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.remittanceInformation}</dd>
          <dt>
            <span id="paymentChannelCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.paymentChannelCode">Payment Channel Code</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.paymentChannelCode}</dd>
          <dt>
            <span id="feeAmount">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.feeAmount">Fee Amount</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.feeAmount}</dd>
          <dt>
            <span id="feeGLAccount">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.feeGLAccount">Fee GL Account</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.feeGLAccount}</dd>
          <dt>
            <span id="feeNarration">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.feeNarration">Fee Narration</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.feeNarration}</dd>
          <dt>
            <span id="tranFreeField1">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField1">Tran Free Field 1</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.tranFreeField1}</dd>
          <dt>
            <span id="tranFreeField2">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField2">Tran Free Field 2</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.tranFreeField2}</dd>
          <dt>
            <span id="tranFreeField3">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField3">Tran Free Field 3</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.tranFreeField3}</dd>
          <dt>
            <span id="tranFreeField4">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField4">Tran Free Field 4</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.tranFreeField4}</dd>
          <dt>
            <span id="tranFreeField5">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField5">Tran Free Field 5</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.tranFreeField5}</dd>
          <dt>
            <span id="tranFreeField6">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField6">Tran Free Field 6</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.tranFreeField6}</dd>
          <dt>
            <span id="tranFreeField7">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField7">Tran Free Field 7</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.tranFreeField7}</dd>
          <dt>
            <span id="tranFreeField8">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField8">Tran Free Field 8</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.tranFreeField8}</dd>
          <dt>
            <span id="tranFreeField9">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField9">Tran Free Field 9</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.tranFreeField9}</dd>
          <dt>
            <span id="tranFreeField10">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField10">Tran Free Field 10</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.tranFreeField10}</dd>
          <dt>
            <span id="tranFreeField11">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField11">Tran Free Field 11</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.tranFreeField11}</dd>
          <dt>
            <span id="tranFreeField12">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField12">Tran Free Field 12</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.tranFreeField12}</dd>
          <dt>
            <span id="tranFreeField13">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField13">Tran Free Field 13</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.tranFreeField13}</dd>
          <dt>
            <span id="tranFreeField14">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField14">Tran Free Field 14</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.tranFreeField14}</dd>
          <dt>
            <span id="tranFreeField15">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField15">Tran Free Field 15</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.tranFreeField15}</dd>
          <dt>
            <span id="tranFreeField16">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField16">Tran Free Field 16</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.tranFreeField16}</dd>
          <dt>
            <span id="tranFreeField17">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField17">Tran Free Field 17</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.tranFreeField17}</dd>
          <dt>
            <span id="tranFreeField18">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField18">Tran Free Field 18</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.tranFreeField18}</dd>
          <dt>
            <span id="tranFreeField19">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField19">Tran Free Field 19</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.tranFreeField19}</dd>
          <dt>
            <span id="tranFreeField20">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField20">Tran Free Field 20</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.tranFreeField20 ? 'true' : 'false'}</dd>
          <dt>
            <span id="tranFreeField21">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField21">Tran Free Field 21</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.tranFreeField21}</dd>
          <dt>
            <span id="tranFreeField22">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField22">Tran Free Field 22</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.tranFreeField22}</dd>
          <dt>
            <span id="tranFreeField23">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField23">Tran Free Field 23</Translate>
            </span>
          </dt>
          <dd>
            {amolDTransactionsEntity.tranFreeField23 ? (
              <div>
                {amolDTransactionsEntity.tranFreeField23ContentType ? (
                  <a onClick={openFile(amolDTransactionsEntity.tranFreeField23ContentType, amolDTransactionsEntity.tranFreeField23)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                ) : null}
                <span>
                  {amolDTransactionsEntity.tranFreeField23ContentType}, {byteSize(amolDTransactionsEntity.tranFreeField23)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="tranFreeField24">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField24">Tran Free Field 24</Translate>
            </span>
          </dt>
          <dd>
            {amolDTransactionsEntity.tranFreeField24 ? (
              <TextFormat value={amolDTransactionsEntity.tranFreeField24} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="responseCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.responseCode">Response Code</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.responseCode}</dd>
          <dt>
            <span id="responseMessage">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.responseMessage">Response Message</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.responseMessage}</dd>
          <dt>
            <span id="responseDetails">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.responseDetails">Response Details</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.responseDetails}</dd>
          <dt>
            <span id="transferReferenceId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.transferReferenceId">Transfer Reference Id</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.transferReferenceId}</dd>
          <dt>
            <span id="transferStatus">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.transferStatus">Transfer Status</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.transferStatus}</dd>
          <dt>
            <span id="responseDateTime">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.responseDateTime">Response Date Time</Translate>
            </span>
          </dt>
          <dd>
            {amolDTransactionsEntity.responseDateTime ? (
              <TextFormat value={amolDTransactionsEntity.responseDateTime} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {amolDTransactionsEntity.createdAt ? (
              <TextFormat value={amolDTransactionsEntity.createdAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.createdBy}</dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {amolDTransactionsEntity.updatedAt ? (
              <TextFormat value={amolDTransactionsEntity.updatedAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{amolDTransactionsEntity.updatedBy}</dd>
          <dt>
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.dTransaction">D Transaction</Translate>
          </dt>
          <dd>{amolDTransactionsEntity.dTransaction ? amolDTransactionsEntity.dTransaction.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/amol-d-transactions" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/amol-d-transactions/${amolDTransactionsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default AmolDTransactionsDetail;
