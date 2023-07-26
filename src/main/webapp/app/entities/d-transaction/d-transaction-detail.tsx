import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, openFile, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './d-transaction.reducer';

export const DTransactionDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const dTransactionEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.dTransaction.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="dTransactionDetailsHeading">
          <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.detail.title">DTransaction</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.id}</dd>
          <dt>
            <span id="absaTranRef">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.absaTranRef">Absa Tran Ref</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.absaTranRef}</dd>
          <dt>
            <span id="billerId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.billerId">Biller Id</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.billerId}</dd>
          <dt>
            <span id="paymentItemCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.paymentItemCode">Payment Item Code</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.paymentItemCode}</dd>
          <dt>
            <span id="dtDTransactionId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.dtDTransactionId">Dt D Transaction Id</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.dtDTransactionId}</dd>
          <dt>
            <span id="amolDTransactionId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.amolDTransactionId">Amol D Transaction Id</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.amolDTransactionId}</dd>
          <dt>
            <span id="transactionReferenceNumber">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.transactionReferenceNumber">
                Transaction Reference Number
              </Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.transactionReferenceNumber}</dd>
          <dt>
            <span id="externalTranid">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.externalTranid">External Tranid</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.externalTranid}</dd>
          <dt>
            <span id="token">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.token">Token</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.token}</dd>
          <dt>
            <span id="transferId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.transferId">Transfer Id</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.transferId}</dd>
          <dt>
            <span id="productCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.productCode">Product Code</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.productCode}</dd>
          <dt>
            <span id="paymentChannelCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.paymentChannelCode">Payment Channel Code</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.paymentChannelCode}</dd>
          <dt>
            <span id="accountNumber">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.accountNumber">Account Number</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.accountNumber}</dd>
          <dt>
            <span id="amount">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.amount">Amount</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.amount}</dd>
          <dt>
            <span id="debitAmount">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.debitAmount">Debit Amount</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.debitAmount}</dd>
          <dt>
            <span id="creditAmount">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.creditAmount">Credit Amount</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.creditAmount}</dd>
          <dt>
            <span id="settlementAmount">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.settlementAmount">Settlement Amount</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.settlementAmount}</dd>
          <dt>
            <span id="settlementDate">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.settlementDate">Settlement Date</Translate>
            </span>
          </dt>
          <dd>
            {dTransactionEntity.settlementDate ? (
              <TextFormat value={dTransactionEntity.settlementDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="transactionDate">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.transactionDate">Transaction Date</Translate>
            </span>
          </dt>
          <dd>
            {dTransactionEntity.transactionDate ? (
              <TextFormat value={dTransactionEntity.transactionDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="isRetried">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.isRetried">Is Retried</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.isRetried ? 'true' : 'false'}</dd>
          <dt>
            <span id="lastRetryDate">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.lastRetryDate">Last Retry Date</Translate>
            </span>
          </dt>
          <dd>
            {dTransactionEntity.lastRetryDate ? (
              <TextFormat value={dTransactionEntity.lastRetryDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="firstRetryDate">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.firstRetryDate">First Retry Date</Translate>
            </span>
          </dt>
          <dd>
            {dTransactionEntity.firstRetryDate ? (
              <TextFormat value={dTransactionEntity.firstRetryDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="retryResposeCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.retryResposeCode">Retry Respose Code</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.retryResposeCode}</dd>
          <dt>
            <span id="retryResponseMessage">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.retryResponseMessage">Retry Response Message</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.retryResponseMessage}</dd>
          <dt>
            <span id="retryCount">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.retryCount">Retry Count</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.retryCount}</dd>
          <dt>
            <span id="isRetriableFlg">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.isRetriableFlg">Is Retriable Flg</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.isRetriableFlg ? 'true' : 'false'}</dd>
          <dt>
            <span id="doNotRetryDTransaction">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.doNotRetryDTransaction">Do Not Retry D Transaction</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.doNotRetryDTransaction ? 'true' : 'false'}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.status">Status</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.status}</dd>
          <dt>
            <span id="statusCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.statusCode">Status Code</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.statusCode}</dd>
          <dt>
            <span id="statusDetails">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.statusDetails">Status Details</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.statusDetails}</dd>
          <dt>
            <span id="retries">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.retries">Retries</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.retries}</dd>
          <dt>
            <span id="timestamp">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.timestamp">Timestamp</Translate>
            </span>
          </dt>
          <dd>
            {dTransactionEntity.timestamp ? <TextFormat value={dTransactionEntity.timestamp} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="postedBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.postedBy">Posted By</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.postedBy}</dd>
          <dt>
            <span id="postedDate">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.postedDate">Posted Date</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.postedDate}</dd>
          <dt>
            <span id="internalErrorCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.internalErrorCode">Internal Error Code</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.internalErrorCode}</dd>
          <dt>
            <span id="externalErrorCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.externalErrorCode">External Error Code</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.externalErrorCode}</dd>
          <dt>
            <span id="isCrossCurrency">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.isCrossCurrency">Is Cross Currency</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.isCrossCurrency ? 'true' : 'false'}</dd>
          <dt>
            <span id="isCrossBank">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.isCrossBank">Is Cross Bank</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.isCrossBank ? 'true' : 'false'}</dd>
          <dt>
            <span id="currency">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.currency">Currency</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.currency}</dd>
          <dt>
            <span id="creditAccount">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.creditAccount">Credit Account</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.creditAccount}</dd>
          <dt>
            <span id="debitAccount">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.debitAccount">Debit Account</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.debitAccount}</dd>
          <dt>
            <span id="phoneNumber">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.phoneNumber">Phone Number</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.phoneNumber}</dd>
          <dt>
            <span id="customerNumber">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.customerNumber">Customer Number</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.customerNumber}</dd>
          <dt>
            <span id="tranStatus">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranStatus">Tran Status</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.tranStatus}</dd>
          <dt>
            <span id="tranStatusDetails">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranStatusDetails">Tran Status Details</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.tranStatusDetails}</dd>
          <dt>
            <span id="tranFreeField1">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField1">Tran Free Field 1</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.tranFreeField1}</dd>
          <dt>
            <span id="tranFreeField2">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField2">Tran Free Field 2</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.tranFreeField2}</dd>
          <dt>
            <span id="tranFreeField3">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField3">Tran Free Field 3</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.tranFreeField3}</dd>
          <dt>
            <span id="tranFreeField4">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField4">Tran Free Field 4</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.tranFreeField4}</dd>
          <dt>
            <span id="tranFreeField5">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField5">Tran Free Field 5</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.tranFreeField5}</dd>
          <dt>
            <span id="tranFreeField6">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField6">Tran Free Field 6</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.tranFreeField6}</dd>
          <dt>
            <span id="tranFreeField7">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField7">Tran Free Field 7</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.tranFreeField7}</dd>
          <dt>
            <span id="tranFreeField8">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField8">Tran Free Field 8</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.tranFreeField8}</dd>
          <dt>
            <span id="tranFreeField9">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField9">Tran Free Field 9</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.tranFreeField9}</dd>
          <dt>
            <span id="tranFreeField10">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField10">Tran Free Field 10</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.tranFreeField10}</dd>
          <dt>
            <span id="tranFreeField11">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField11">Tran Free Field 11</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.tranFreeField11}</dd>
          <dt>
            <span id="tranFreeField12">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField12">Tran Free Field 12</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.tranFreeField12}</dd>
          <dt>
            <span id="tranFreeField13">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField13">Tran Free Field 13</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.tranFreeField13}</dd>
          <dt>
            <span id="tranFreeField14">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField14">Tran Free Field 14</Translate>
            </span>
          </dt>
          <dd>
            {dTransactionEntity.tranFreeField14 ? (
              <div>
                {dTransactionEntity.tranFreeField14ContentType ? (
                  <a onClick={openFile(dTransactionEntity.tranFreeField14ContentType, dTransactionEntity.tranFreeField14)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                ) : null}
                <span>
                  {dTransactionEntity.tranFreeField14ContentType}, {byteSize(dTransactionEntity.tranFreeField14)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="tranFreeField15">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField15">Tran Free Field 15</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.tranFreeField15}</dd>
          <dt>
            <span id="tranFreeField16">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField16">Tran Free Field 16</Translate>
            </span>
          </dt>
          <dd>
            {dTransactionEntity.tranFreeField16 ? (
              <TextFormat value={dTransactionEntity.tranFreeField16} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="tranFreeField17">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField17">Tran Free Field 17</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.tranFreeField17 ? 'true' : 'false'}</dd>
          <dt>
            <span id="tranFreeField18">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField18">Tran Free Field 18</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.tranFreeField18}</dd>
          <dt>
            <span id="tranFreeField19">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField19">Tran Free Field 19</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.tranFreeField19}</dd>
          <dt>
            <span id="tranFreeField20">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField20">Tran Free Field 20</Translate>
            </span>
          </dt>
          <dd>
            {dTransactionEntity.tranFreeField20 ? (
              <TextFormat value={dTransactionEntity.tranFreeField20} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="tranFreeField21">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField21">Tran Free Field 21</Translate>
            </span>
          </dt>
          <dd>
            {dTransactionEntity.tranFreeField21 ? (
              <TextFormat value={dTransactionEntity.tranFreeField21} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="tranFreeField22">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField22">Tran Free Field 22</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.tranFreeField22 ? 'true' : 'false'}</dd>
          <dt>
            <span id="tranFreeField23">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField23">Tran Free Field 23</Translate>
            </span>
          </dt>
          <dd>
            {dTransactionEntity.tranFreeField23 ? (
              <TextFormat value={dTransactionEntity.tranFreeField23} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="tranFreeField24">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField24">Tran Free Field 24</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.tranFreeField24}</dd>
          <dt>
            <span id="tranFreeField25">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField25">Tran Free Field 25</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.tranFreeField25}</dd>
          <dt>
            <span id="tranFreeField26">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField26">Tran Free Field 26</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.tranFreeField26}</dd>
          <dt>
            <span id="tranFreeField27">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField27">Tran Free Field 27</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.tranFreeField27}</dd>
          <dt>
            <span id="tranFreeField28">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField28">Tran Free Field 28</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.tranFreeField28}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {dTransactionEntity.createdAt ? <TextFormat value={dTransactionEntity.createdAt} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.createdBy}</dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {dTransactionEntity.updatedAt ? <TextFormat value={dTransactionEntity.updatedAt} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{dTransactionEntity.updatedBy}</dd>
          <dt>
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.transaction">Transaction</Translate>
          </dt>
          <dd>{dTransactionEntity.transaction ? dTransactionEntity.transaction.id : ''}</dd>
          <dt>
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.transaction">Transaction</Translate>
          </dt>
          <dd>{dTransactionEntity.transaction ? dTransactionEntity.transaction.id : ''}</dd>
          <dt>
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.transaction">Transaction</Translate>
          </dt>
          <dd>{dTransactionEntity.transaction ? dTransactionEntity.transaction.id : ''}</dd>
          <dt>
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.customer">Customer</Translate>
          </dt>
          <dd>{dTransactionEntity.customer ? dTransactionEntity.customer.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/d-transaction" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/d-transaction/${dTransactionEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DTransactionDetail;
