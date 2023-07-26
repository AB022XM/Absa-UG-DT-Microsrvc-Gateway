import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, openFile, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './response-info.reducer';

export const ResponseInfoDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const responseInfoEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.responseInfo.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="responseInfoDetailsHeading">
          <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.detail.title">ResponseInfo</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{responseInfoEntity.id}</dd>
          <dt>
            <span id="absaTranRef">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.absaTranRef">Absa Tran Ref</Translate>
            </span>
          </dt>
          <dd>{responseInfoEntity.absaTranRef}</dd>
          <dt>
            <span id="billerId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.billerId">Biller Id</Translate>
            </span>
          </dt>
          <dd>{responseInfoEntity.billerId}</dd>
          <dt>
            <span id="paymentItemCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.paymentItemCode">Payment Item Code</Translate>
            </span>
          </dt>
          <dd>{responseInfoEntity.paymentItemCode}</dd>
          <dt>
            <span id="dtDTransactionId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.dtDTransactionId">Dt D Transaction Id</Translate>
            </span>
          </dt>
          <dd>{responseInfoEntity.dtDTransactionId}</dd>
          <dt>
            <span id="transactionReferenceNumber">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.transactionReferenceNumber">
                Transaction Reference Number
              </Translate>
            </span>
          </dt>
          <dd>{responseInfoEntity.transactionReferenceNumber}</dd>
          <dt>
            <span id="externalTranid">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.externalTranid">External Tranid</Translate>
            </span>
          </dt>
          <dd>{responseInfoEntity.externalTranid}</dd>
          <dt>
            <span id="responseCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.responseCode">Response Code</Translate>
            </span>
          </dt>
          <dd>{responseInfoEntity.responseCode}</dd>
          <dt>
            <span id="responseMessage">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.responseMessage">Response Message</Translate>
            </span>
          </dt>
          <dd>{responseInfoEntity.responseMessage}</dd>
          <dt>
            <span id="responseBody">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.responseBody">Response Body</Translate>
            </span>
          </dt>
          <dd>{responseInfoEntity.responseBody}</dd>
          <dt>
            <span id="timestamp">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.timestamp">Timestamp</Translate>
            </span>
          </dt>
          <dd>
            {responseInfoEntity.timestamp ? <TextFormat value={responseInfoEntity.timestamp} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="requestRef">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.requestRef">Request Ref</Translate>
            </span>
          </dt>
          <dd>{responseInfoEntity.requestRef}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.status">Status</Translate>
            </span>
          </dt>
          <dd>{responseInfoEntity.status}</dd>
          <dt>
            <span id="freeField1">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.freeField1">Free Field 1</Translate>
            </span>
          </dt>
          <dd>{responseInfoEntity.freeField1}</dd>
          <dt>
            <span id="freeField2">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.freeField2">Free Field 2</Translate>
            </span>
          </dt>
          <dd>
            {responseInfoEntity.freeField2 ? (
              <div>
                {responseInfoEntity.freeField2ContentType ? (
                  <a onClick={openFile(responseInfoEntity.freeField2ContentType, responseInfoEntity.freeField2)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                ) : null}
                <span>
                  {responseInfoEntity.freeField2ContentType}, {byteSize(responseInfoEntity.freeField2)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="freeField3">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.freeField3">Free Field 3</Translate>
            </span>
          </dt>
          <dd>{responseInfoEntity.freeField3}</dd>
          <dt>
            <span id="freeField4">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.freeField4">Free Field 4</Translate>
            </span>
          </dt>
          <dd>{responseInfoEntity.freeField4}</dd>
          <dt>
            <span id="freeField5">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.freeField5">Free Field 5</Translate>
            </span>
          </dt>
          <dd>{responseInfoEntity.freeField5}</dd>
          <dt>
            <span id="freeField6">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.freeField6">Free Field 6</Translate>
            </span>
          </dt>
          <dd>{responseInfoEntity.freeField6}</dd>
          <dt>
            <span id="time">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.time">Time</Translate>
            </span>
          </dt>
          <dd>{responseInfoEntity.time ? <TextFormat value={responseInfoEntity.time} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="errorCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.errorCode">Error Code</Translate>
            </span>
          </dt>
          <dd>{responseInfoEntity.errorCode}</dd>
          <dt>
            <span id="errorMessage">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.errorMessage">Error Message</Translate>
            </span>
          </dt>
          <dd>{responseInfoEntity.errorMessage}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {responseInfoEntity.createdAt ? <TextFormat value={responseInfoEntity.createdAt} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{responseInfoEntity.createdBy}</dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {responseInfoEntity.updatedAt ? <TextFormat value={responseInfoEntity.updatedAt} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{responseInfoEntity.updatedBy}</dd>
        </dl>
        <Button tag={Link} to="/response-info" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/response-info/${responseInfoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ResponseInfoDetail;
