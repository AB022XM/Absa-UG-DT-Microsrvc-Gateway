import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, openFile, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './d-transaction-details.reducer';

export const DTransactionDetailsDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const dTransactionDetailsEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.dTransactionDetails.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="dTransactionDetailsDetailsHeading">
          <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.detail.title">DTransactionDetails</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.id}</dd>
          <dt>
            <span id="absaTranRef">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.absaTranRef">Absa Tran Ref</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.absaTranRef}</dd>
          <dt>
            <span id="billerId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.billerId">Biller Id</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.billerId}</dd>
          <dt>
            <span id="paymentItemCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.paymentItemCode">Payment Item Code</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.paymentItemCode}</dd>
          <dt>
            <span id="dtDTransactionId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.dtDTransactionId">Dt D Transaction Id</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.dtDTransactionId}</dd>
          <dt>
            <span id="amolDTransactionId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.amolDTransactionId">Amol D Transaction Id</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.amolDTransactionId}</dd>
          <dt>
            <span id="transactionReferenceNumber">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.transactionReferenceNumber">
                Transaction Reference Number
              </Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.transactionReferenceNumber}</dd>
          <dt>
            <span id="token">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.token">Token</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.token}</dd>
          <dt>
            <span id="transferId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.transferId">Transfer Id</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.transferId}</dd>
          <dt>
            <span id="productCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.productCode">Product Code</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.productCode}</dd>
          <dt>
            <span id="paymentChannelCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.paymentChannelCode">Payment Channel Code</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.paymentChannelCode}</dd>
          <dt>
            <span id="debitAccountNumber">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.debitAccountNumber">Debit Account Number</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.debitAccountNumber}</dd>
          <dt>
            <span id="creditAccountNumber">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.creditAccountNumber">Credit Account Number</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.creditAccountNumber}</dd>
          <dt>
            <span id="debitAmount">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.debitAmount">Debit Amount</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.debitAmount}</dd>
          <dt>
            <span id="creditAmount">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.creditAmount">Credit Amount</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.creditAmount}</dd>
          <dt>
            <span id="transactionAmount">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.transactionAmount">Transaction Amount</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.transactionAmount}</dd>
          <dt>
            <span id="debitDate">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.debitDate">Debit Date</Translate>
            </span>
          </dt>
          <dd>
            {dTransactionDetailsEntity.debitDate ? (
              <TextFormat value={dTransactionDetailsEntity.debitDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="creditDate">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.creditDate">Credit Date</Translate>
            </span>
          </dt>
          <dd>
            {dTransactionDetailsEntity.creditDate ? (
              <TextFormat value={dTransactionDetailsEntity.creditDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="status">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.status">Status</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.status}</dd>
          <dt>
            <span id="settlementDate">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.settlementDate">Settlement Date</Translate>
            </span>
          </dt>
          <dd>
            {dTransactionDetailsEntity.settlementDate ? (
              <TextFormat value={dTransactionDetailsEntity.settlementDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="debitCurrency">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.debitCurrency">Debit Currency</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.debitCurrency}</dd>
          <dt>
            <span id="timestamp">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.timestamp">Timestamp</Translate>
            </span>
          </dt>
          <dd>
            {dTransactionDetailsEntity.timestamp ? (
              <TextFormat value={dTransactionDetailsEntity.timestamp} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="phoneNumber">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.phoneNumber">Phone Number</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.phoneNumber}</dd>
          <dt>
            <span id="email">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.email">Email</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.email}</dd>
          <dt>
            <span id="payerName">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.payerName">Payer Name</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.payerName}</dd>
          <dt>
            <span id="payerAddress">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.payerAddress">Payer Address</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.payerAddress}</dd>
          <dt>
            <span id="payerEmail">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.payerEmail">Payer Email</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.payerEmail}</dd>
          <dt>
            <span id="payerPhoneNumber">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.payerPhoneNumber">Payer Phone Number</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.payerPhoneNumber}</dd>
          <dt>
            <span id="payerNarration">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.payerNarration">Payer Narration</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.payerNarration}</dd>
          <dt>
            <span id="payerBranchId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.payerBranchId">Payer Branch Id</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.payerBranchId}</dd>
          <dt>
            <span id="beneficiaryName">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.beneficiaryName">Beneficiary Name</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.beneficiaryName}</dd>
          <dt>
            <span id="beneficiaryAccount">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.beneficiaryAccount">Beneficiary Account</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.beneficiaryAccount}</dd>
          <dt>
            <span id="beneficiaryBranchId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.beneficiaryBranchId">Beneficiary Branch Id</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.beneficiaryBranchId}</dd>
          <dt>
            <span id="beneficiaryPhoneNumber">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.beneficiaryPhoneNumber">
                Beneficiary Phone Number
              </Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.beneficiaryPhoneNumber}</dd>
          <dt>
            <span id="tranStatus">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranStatus">Tran Status</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.tranStatus}</dd>
          <dt>
            <span id="narration1">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.narration1">Narration 1</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.narration1}</dd>
          <dt>
            <span id="narration2">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.narration2">Narration 2</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.narration2}</dd>
          <dt>
            <span id="narration3">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.narration3">Narration 3</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.narration3}</dd>
          <dt>
            <span id="narration4">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.narration4">Narration 4</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.narration4}</dd>
          <dt>
            <span id="narration5">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.narration5">Narration 5</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.narration5}</dd>
          <dt>
            <span id="narration6">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.narration6">Narration 6</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.narration6}</dd>
          <dt>
            <span id="narration7">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.narration7">Narration 7</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.narration7}</dd>
          <dt>
            <span id="narration8">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.narration8">Narration 8</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.narration8}</dd>
          <dt>
            <span id="narration9">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.narration9">Narration 9</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.narration9}</dd>
          <dt>
            <span id="narration10">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.narration10">Narration 10</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.narration10}</dd>
          <dt>
            <span id="narration11">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.narration11">Narration 11</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.narration11}</dd>
          <dt>
            <span id="narration12">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.narration12">Narration 12</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.narration12}</dd>
          <dt>
            <span id="modeOfPayment">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.modeOfPayment">Mode Of Payment</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.modeOfPayment}</dd>
          <dt>
            <span id="retries">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.retries">Retries</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.retries}</dd>
          <dt>
            <span id="posted">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.posted">Posted</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.posted ? 'true' : 'false'}</dd>
          <dt>
            <span id="apiId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.apiId">Api Id</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.apiId}</dd>
          <dt>
            <span id="apiVersion">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.apiVersion">Api Version</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.apiVersion}</dd>
          <dt>
            <span id="postingApi">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.postingApi">Posting Api</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.postingApi}</dd>
          <dt>
            <span id="isPosted">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.isPosted">Is Posted</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.isPosted ? 'true' : 'false'}</dd>
          <dt>
            <span id="postedBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.postedBy">Posted By</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.postedBy}</dd>
          <dt>
            <span id="postedDate">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.postedDate">Posted Date</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.postedDate}</dd>
          <dt>
            <span id="tranFreeField1">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField1">Tran Free Field 1</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.tranFreeField1}</dd>
          <dt>
            <span id="tranFreeField3">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField3">Tran Free Field 3</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.tranFreeField3}</dd>
          <dt>
            <span id="tranFreeField4">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField4">Tran Free Field 4</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.tranFreeField4}</dd>
          <dt>
            <span id="tranFreeField5">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField5">Tran Free Field 5</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.tranFreeField5}</dd>
          <dt>
            <span id="tranFreeField6">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField6">Tran Free Field 6</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.tranFreeField6}</dd>
          <dt>
            <span id="tranFreeField7">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField7">Tran Free Field 7</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.tranFreeField7}</dd>
          <dt>
            <span id="tranFreeField8">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField8">Tran Free Field 8</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.tranFreeField8}</dd>
          <dt>
            <span id="tranFreeField9">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField9">Tran Free Field 9</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.tranFreeField9}</dd>
          <dt>
            <span id="tranFreeField10">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField10">Tran Free Field 10</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.tranFreeField10}</dd>
          <dt>
            <span id="tranFreeField11">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField11">Tran Free Field 11</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.tranFreeField11}</dd>
          <dt>
            <span id="tranFreeField12">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField12">Tran Free Field 12</Translate>
            </span>
          </dt>
          <dd>
            {dTransactionDetailsEntity.tranFreeField12 ? (
              <div>
                {dTransactionDetailsEntity.tranFreeField12ContentType ? (
                  <a onClick={openFile(dTransactionDetailsEntity.tranFreeField12ContentType, dTransactionDetailsEntity.tranFreeField12)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                ) : null}
                <span>
                  {dTransactionDetailsEntity.tranFreeField12ContentType}, {byteSize(dTransactionDetailsEntity.tranFreeField12)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="tranFreeField13">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField13">Tran Free Field 13</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.tranFreeField13}</dd>
          <dt>
            <span id="tranFreeField14">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField14">Tran Free Field 14</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.tranFreeField14}</dd>
          <dt>
            <span id="tranFreeField15">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField15">Tran Free Field 15</Translate>
            </span>
          </dt>
          <dd>
            {dTransactionDetailsEntity.tranFreeField15 ? (
              <div>
                {dTransactionDetailsEntity.tranFreeField15ContentType ? (
                  <a onClick={openFile(dTransactionDetailsEntity.tranFreeField15ContentType, dTransactionDetailsEntity.tranFreeField15)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                ) : null}
                <span>
                  {dTransactionDetailsEntity.tranFreeField15ContentType}, {byteSize(dTransactionDetailsEntity.tranFreeField15)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="tranFreeField16">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField16">Tran Free Field 16</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.tranFreeField16}</dd>
          <dt>
            <span id="tranFreeField17">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField17">Tran Free Field 17</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.tranFreeField17}</dd>
          <dt>
            <span id="tranFreeField18">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField18">Tran Free Field 18</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.tranFreeField18}</dd>
          <dt>
            <span id="tranFreeField19">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField19">Tran Free Field 19</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.tranFreeField19}</dd>
          <dt>
            <span id="tranFreeField20">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField20">Tran Free Field 20</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.tranFreeField20}</dd>
          <dt>
            <span id="tranFreeField21">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField21">Tran Free Field 21</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.tranFreeField21}</dd>
          <dt>
            <span id="tranFreeField22">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField22">Tran Free Field 22</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.tranFreeField22}</dd>
          <dt>
            <span id="tranFreeField23">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField23">Tran Free Field 23</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.tranFreeField23}</dd>
          <dt>
            <span id="tranFreeField24">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField24">Tran Free Field 24</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.tranFreeField24}</dd>
          <dt>
            <span id="tranFreeField25">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField25">Tran Free Field 25</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.tranFreeField25}</dd>
          <dt>
            <span id="tranFreeField26">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField26">Tran Free Field 26</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.tranFreeField26}</dd>
          <dt>
            <span id="tranFreeField27">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField27">Tran Free Field 27</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.tranFreeField27}</dd>
          <dt>
            <span id="tranFreeField28">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField28">Tran Free Field 28</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.tranFreeField28}</dd>
          <dt>
            <span id="internalErrorCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.internalErrorCode">Internal Error Code</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.internalErrorCode}</dd>
          <dt>
            <span id="externalErrorCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.externalErrorCode">External Error Code</Translate>
            </span>
          </dt>
          <dd>{dTransactionDetailsEntity.externalErrorCode}</dd>
          <dt>
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.customer">Customer</Translate>
          </dt>
          <dd>{dTransactionDetailsEntity.customer ? dTransactionDetailsEntity.customer.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/d-transaction-details" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/d-transaction-details/${dTransactionDetailsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DTransactionDetailsDetail;
