import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, openFile, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './d-transaction-summary.reducer';

export const DTransactionSummaryDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const dTransactionSummaryEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.dTransactionSummary.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="dTransactionSummaryDetailsHeading">
          <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.detail.title">DTransactionSummary</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{dTransactionSummaryEntity.id}</dd>
          <dt>
            <span id="absaTranRef">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.absaTranRef">Absa Tran Ref</Translate>
            </span>
          </dt>
          <dd>{dTransactionSummaryEntity.absaTranRef}</dd>
          <dt>
            <span id="billerId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.billerId">Biller Id</Translate>
            </span>
          </dt>
          <dd>{dTransactionSummaryEntity.billerId}</dd>
          <dt>
            <span id="paymentItemCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.paymentItemCode">Payment Item Code</Translate>
            </span>
          </dt>
          <dd>{dTransactionSummaryEntity.paymentItemCode}</dd>
          <dt>
            <span id="dtDTransactionId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.dtDTransactionId">Dt D Transaction Id</Translate>
            </span>
          </dt>
          <dd>{dTransactionSummaryEntity.dtDTransactionId}</dd>
          <dt>
            <span id="transactionReferenceNumber">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.transactionReferenceNumber">
                Transaction Reference Number
              </Translate>
            </span>
          </dt>
          <dd>{dTransactionSummaryEntity.transactionReferenceNumber}</dd>
          <dt>
            <span id="recordId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.recordId">Record Id</Translate>
            </span>
          </dt>
          <dd>{dTransactionSummaryEntity.recordId}</dd>
          <dt>
            <span id="transactionId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.transactionId">Transaction Id</Translate>
            </span>
          </dt>
          <dd>{dTransactionSummaryEntity.transactionId}</dd>
          <dt>
            <span id="transactionType">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.transactionType">Transaction Type</Translate>
            </span>
          </dt>
          <dd>{dTransactionSummaryEntity.transactionType}</dd>
          <dt>
            <span id="transactionAmount">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.transactionAmount">Transaction Amount</Translate>
            </span>
          </dt>
          <dd>{dTransactionSummaryEntity.transactionAmount}</dd>
          <dt>
            <span id="transactionDate">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.transactionDate">Transaction Date</Translate>
            </span>
          </dt>
          <dd>
            {dTransactionSummaryEntity.transactionDate ? (
              <TextFormat value={dTransactionSummaryEntity.transactionDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="transactionStatus">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.transactionStatus">Transaction Status</Translate>
            </span>
          </dt>
          <dd>{dTransactionSummaryEntity.transactionStatus}</dd>
          <dt>
            <span id="freeField1">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.freeField1">Free Field 1</Translate>
            </span>
          </dt>
          <dd>{dTransactionSummaryEntity.freeField1}</dd>
          <dt>
            <span id="freeField2">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.freeField2">Free Field 2</Translate>
            </span>
          </dt>
          <dd>{dTransactionSummaryEntity.freeField2}</dd>
          <dt>
            <span id="freeField3">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.freeField3">Free Field 3</Translate>
            </span>
          </dt>
          <dd>{dTransactionSummaryEntity.freeField3}</dd>
          <dt>
            <span id="freeField4">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.freeField4">Free Field 4</Translate>
            </span>
          </dt>
          <dd>{dTransactionSummaryEntity.freeField4}</dd>
          <dt>
            <span id="freeField5">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.freeField5">Free Field 5</Translate>
            </span>
          </dt>
          <dd>{dTransactionSummaryEntity.freeField5}</dd>
          <dt>
            <span id="freeField6">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.freeField6">Free Field 6</Translate>
            </span>
          </dt>
          <dd>
            {dTransactionSummaryEntity.freeField6 ? (
              <div>
                {dTransactionSummaryEntity.freeField6ContentType ? (
                  <a onClick={openFile(dTransactionSummaryEntity.freeField6ContentType, dTransactionSummaryEntity.freeField6)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                ) : null}
                <span>
                  {dTransactionSummaryEntity.freeField6ContentType}, {byteSize(dTransactionSummaryEntity.freeField6)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {dTransactionSummaryEntity.createdAt ? (
              <TextFormat value={dTransactionSummaryEntity.createdAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{dTransactionSummaryEntity.createdBy}</dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {dTransactionSummaryEntity.updatedAt ? (
              <TextFormat value={dTransactionSummaryEntity.updatedAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{dTransactionSummaryEntity.updatedBy}</dd>
          <dt>
            <span id="errorCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.errorCode">Error Code</Translate>
            </span>
          </dt>
          <dd>{dTransactionSummaryEntity.errorCode}</dd>
          <dt>
            <span id="errorMessage">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.errorMessage">Error Message</Translate>
            </span>
          </dt>
          <dd>{dTransactionSummaryEntity.errorMessage}</dd>
          <dt>
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.customer">Customer</Translate>
          </dt>
          <dd>{dTransactionSummaryEntity.customer ? dTransactionSummaryEntity.customer.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/d-transaction-summary" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/d-transaction-summary/${dTransactionSummaryEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DTransactionSummaryDetail;
