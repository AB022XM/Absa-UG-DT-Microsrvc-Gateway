import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './d-transaction-history.reducer';

export const DTransactionHistoryDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const dTransactionHistoryEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.dTransactionHistory.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="dTransactionHistoryDetailsHeading">
          <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.detail.title">DTransactionHistory</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.id}</dd>
          <dt>
            <span id="recordId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.recordId">Record Id</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.recordId}</dd>
          <dt>
            <span id="transferId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.transferId">Transfer Id</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.transferId}</dd>
          <dt>
            <span id="productCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.productCode">Product Code</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.productCode}</dd>
          <dt>
            <span id="paymentChannelCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.paymentChannelCode">Payment Channel Code</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.paymentChannelCode}</dd>
          <dt>
            <span id="debitAccountNumber">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.debitAccountNumber">Debit Account Number</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.debitAccountNumber}</dd>
          <dt>
            <span id="creditAccountNumber">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.creditAccountNumber">Credit Account Number</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.creditAccountNumber}</dd>
          <dt>
            <span id="debitAmount">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.debitAmount">Debit Amount</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.debitAmount}</dd>
          <dt>
            <span id="creditAmount">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.creditAmount">Credit Amount</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.creditAmount}</dd>
          <dt>
            <span id="transactionAmount">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.transactionAmount">Transaction Amount</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.transactionAmount}</dd>
          <dt>
            <span id="debitDate">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.debitDate">Debit Date</Translate>
            </span>
          </dt>
          <dd>
            {dTransactionHistoryEntity.debitDate ? (
              <TextFormat value={dTransactionHistoryEntity.debitDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="creditDate">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.creditDate">Credit Date</Translate>
            </span>
          </dt>
          <dd>
            {dTransactionHistoryEntity.creditDate ? (
              <TextFormat value={dTransactionHistoryEntity.creditDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="status">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.status">Status</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.status}</dd>
          <dt>
            <span id="settlementDate">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.settlementDate">Settlement Date</Translate>
            </span>
          </dt>
          <dd>
            {dTransactionHistoryEntity.settlementDate ? (
              <TextFormat value={dTransactionHistoryEntity.settlementDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="debitCurrency">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.debitCurrency">Debit Currency</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.debitCurrency}</dd>
          <dt>
            <span id="timestamp">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.timestamp">Timestamp</Translate>
            </span>
          </dt>
          <dd>
            {dTransactionHistoryEntity.timestamp ? (
              <TextFormat value={dTransactionHistoryEntity.timestamp} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="phoneNumber">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.phoneNumber">Phone Number</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.phoneNumber}</dd>
          <dt>
            <span id="email">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.email">Email</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.email}</dd>
          <dt>
            <span id="payerName">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.payerName">Payer Name</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.payerName}</dd>
          <dt>
            <span id="payerAddress">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.payerAddress">Payer Address</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.payerAddress}</dd>
          <dt>
            <span id="payerEmail">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.payerEmail">Payer Email</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.payerEmail}</dd>
          <dt>
            <span id="payerPhoneNumber">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.payerPhoneNumber">Payer Phone Number</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.payerPhoneNumber}</dd>
          <dt>
            <span id="payerNarration">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.payerNarration">Payer Narration</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.payerNarration}</dd>
          <dt>
            <span id="payerBranchId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.payerBranchId">Payer Branch Id</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.payerBranchId}</dd>
          <dt>
            <span id="beneficiaryName">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.beneficiaryName">Beneficiary Name</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.beneficiaryName}</dd>
          <dt>
            <span id="beneficiaryAccount">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.beneficiaryAccount">Beneficiary Account</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.beneficiaryAccount}</dd>
          <dt>
            <span id="beneficiaryBranchId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.beneficiaryBranchId">Beneficiary Branch Id</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.beneficiaryBranchId}</dd>
          <dt>
            <span id="beneficiaryPhoneNumber">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.beneficiaryPhoneNumber">
                Beneficiary Phone Number
              </Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.beneficiaryPhoneNumber}</dd>
          <dt>
            <span id="tranStatus">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.tranStatus">Tran Status</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.tranStatus}</dd>
          <dt>
            <span id="narration1">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.narration1">Narration 1</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.narration1}</dd>
          <dt>
            <span id="narration2">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.narration2">Narration 2</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.narration2}</dd>
          <dt>
            <span id="narration3">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.narration3">Narration 3</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.narration3}</dd>
          <dt>
            <span id="modeOfPayment">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.modeOfPayment">Mode Of Payment</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.modeOfPayment}</dd>
          <dt>
            <span id="retries">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.retries">Retries</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.retries}</dd>
          <dt>
            <span id="posted">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.posted">Posted</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.posted ? 'true' : 'false'}</dd>
          <dt>
            <span id="apiId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.apiId">Api Id</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.apiId}</dd>
          <dt>
            <span id="apiVersion">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.apiVersion">Api Version</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.apiVersion}</dd>
          <dt>
            <span id="postingApi">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.postingApi">Posting Api</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.postingApi}</dd>
          <dt>
            <span id="isPosted">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.isPosted">Is Posted</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.isPosted ? 'true' : 'false'}</dd>
          <dt>
            <span id="postedBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.postedBy">Posted By</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.postedBy}</dd>
          <dt>
            <span id="postedDate">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.postedDate">Posted Date</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.postedDate}</dd>
          <dt>
            <span id="internalErrorCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.internalErrorCode">Internal Error Code</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.internalErrorCode}</dd>
          <dt>
            <span id="externalErrorCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.externalErrorCode">External Error Code</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.externalErrorCode}</dd>
          <dt>
            <span id="tranFreeField1">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.tranFreeField1">Tran Free Field 1</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.tranFreeField1}</dd>
          <dt>
            <span id="tranFreeField3">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.tranFreeField3">Tran Free Field 3</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.tranFreeField3}</dd>
          <dt>
            <span id="tranFreeField4">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.tranFreeField4">Tran Free Field 4</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.tranFreeField4}</dd>
          <dt>
            <span id="tranFreeField5">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.tranFreeField5">Tran Free Field 5</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.tranFreeField5}</dd>
          <dt>
            <span id="tranFreeField6">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.tranFreeField6">Tran Free Field 6</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.tranFreeField6}</dd>
          <dt>
            <span id="tranFreeField7">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.tranFreeField7">Tran Free Field 7</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.tranFreeField7}</dd>
          <dt>
            <span id="tranFreeField8">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.tranFreeField8">Tran Free Field 8</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.tranFreeField8}</dd>
          <dt>
            <span id="tranFreeField9">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.tranFreeField9">Tran Free Field 9</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.tranFreeField9}</dd>
          <dt>
            <span id="tranFreeField10">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.tranFreeField10">Tran Free Field 10</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.tranFreeField10}</dd>
          <dt>
            <span id="tranFreeField11">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.tranFreeField11">Tran Free Field 11</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.tranFreeField11}</dd>
          <dt>
            <span id="tranFreeField12">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.tranFreeField12">Tran Free Field 12</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.tranFreeField12}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {dTransactionHistoryEntity.createdAt ? (
              <TextFormat value={dTransactionHistoryEntity.createdAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.createdBy}</dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {dTransactionHistoryEntity.updatedAt ? (
              <TextFormat value={dTransactionHistoryEntity.updatedAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{dTransactionHistoryEntity.updatedBy}</dd>
          <dt>
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.customer">Customer</Translate>
          </dt>
          <dd>{dTransactionHistoryEntity.customer ? dTransactionHistoryEntity.customer.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/d-transaction-history" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/d-transaction-history/${dTransactionHistoryEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DTransactionHistoryDetail;
