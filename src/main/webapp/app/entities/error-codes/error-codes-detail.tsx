import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './error-codes.reducer';

export const ErrorCodesDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const errorCodesEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.errorCodes.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="errorCodesDetailsHeading">
          <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.detail.title">ErrorCodes</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{errorCodesEntity.id}</dd>
          <dt>
            <span id="absaTranRef">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.absaTranRef">Absa Tran Ref</Translate>
            </span>
          </dt>
          <dd>{errorCodesEntity.absaTranRef}</dd>
          <dt>
            <span id="recordId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.recordId">Record Id</Translate>
            </span>
          </dt>
          <dd>{errorCodesEntity.recordId}</dd>
          <dt>
            <span id="paymentItemCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.paymentItemCode">Payment Item Code</Translate>
            </span>
          </dt>
          <dd>{errorCodesEntity.paymentItemCode}</dd>
          <dt>
            <span id="dtDTransactionId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.dtDTransactionId">Dt D Transaction Id</Translate>
            </span>
          </dt>
          <dd>{errorCodesEntity.dtDTransactionId}</dd>
          <dt>
            <span id="transactionReferenceNumber">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.transactionReferenceNumber">
                Transaction Reference Number
              </Translate>
            </span>
          </dt>
          <dd>{errorCodesEntity.transactionReferenceNumber}</dd>
          <dt>
            <span id="externalTranid">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.externalTranid">External Tranid</Translate>
            </span>
          </dt>
          <dd>{errorCodesEntity.externalTranid}</dd>
          <dt>
            <span id="errorCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.errorCode">Error Code</Translate>
            </span>
          </dt>
          <dd>{errorCodesEntity.errorCode}</dd>
          <dt>
            <span id="errorMessage">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.errorMessage">Error Message</Translate>
            </span>
          </dt>
          <dd>{errorCodesEntity.errorMessage}</dd>
          <dt>
            <span id="responseCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.responseCode">Response Code</Translate>
            </span>
          </dt>
          <dd>{errorCodesEntity.responseCode}</dd>
          <dt>
            <span id="responseMessage">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.responseMessage">Response Message</Translate>
            </span>
          </dt>
          <dd>{errorCodesEntity.responseMessage}</dd>
          <dt>
            <span id="responseBody">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.responseBody">Response Body</Translate>
            </span>
          </dt>
          <dd>{errorCodesEntity.responseBody}</dd>
          <dt>
            <span id="timestamp">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.timestamp">Timestamp</Translate>
            </span>
          </dt>
          <dd>
            {errorCodesEntity.timestamp ? <TextFormat value={errorCodesEntity.timestamp} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="requestRef">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.requestRef">Request Ref</Translate>
            </span>
          </dt>
          <dd>{errorCodesEntity.requestRef}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.status">Status</Translate>
            </span>
          </dt>
          <dd>{errorCodesEntity.status}</dd>
          <dt>
            <span id="customerField1">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.customerField1">Customer Field 1</Translate>
            </span>
          </dt>
          <dd>{errorCodesEntity.customerField1}</dd>
          <dt>
            <span id="customerField2">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.customerField2">Customer Field 2</Translate>
            </span>
          </dt>
          <dd>{errorCodesEntity.customerField2}</dd>
          <dt>
            <span id="customerField3">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.customerField3">Customer Field 3</Translate>
            </span>
          </dt>
          <dd>{errorCodesEntity.customerField3}</dd>
          <dt>
            <span id="customerField4">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.customerField4">Customer Field 4</Translate>
            </span>
          </dt>
          <dd>{errorCodesEntity.customerField4}</dd>
          <dt>
            <span id="customerField5">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.customerField5">Customer Field 5</Translate>
            </span>
          </dt>
          <dd>{errorCodesEntity.customerField5}</dd>
          <dt>
            <span id="customerField6">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.customerField6">Customer Field 6</Translate>
            </span>
          </dt>
          <dd>{errorCodesEntity.customerField6}</dd>
          <dt>
            <span id="customerField7">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.customerField7">Customer Field 7</Translate>
            </span>
          </dt>
          <dd>{errorCodesEntity.customerField7}</dd>
          <dt>
            <span id="customerField8">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.customerField8">Customer Field 8</Translate>
            </span>
          </dt>
          <dd>{errorCodesEntity.customerField8}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {errorCodesEntity.createdAt ? <TextFormat value={errorCodesEntity.createdAt} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{errorCodesEntity.createdBy}</dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {errorCodesEntity.updatedAt ? <TextFormat value={errorCodesEntity.updatedAt} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{errorCodesEntity.updatedBy}</dd>
        </dl>
        <Button tag={Link} to="/error-codes" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/error-codes/${errorCodesEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ErrorCodesDetail;
