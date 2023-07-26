import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './request-info.reducer';

export const RequestInfoDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const requestInfoEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.requestInfo.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="requestInfoDetailsHeading">
          <Translate contentKey="absaUgdtMicrosrvcGatewayApp.requestInfo.detail.title">RequestInfo</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{requestInfoEntity.id}</dd>
          <dt>
            <span id="recordId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.requestInfo.recordId">Record Id</Translate>
            </span>
          </dt>
          <dd>{requestInfoEntity.recordId}</dd>
          <dt>
            <span id="transactionId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.requestInfo.transactionId">Transaction Id</Translate>
            </span>
          </dt>
          <dd>{requestInfoEntity.transactionId}</dd>
          <dt>
            <span id="requestData">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.requestInfo.requestData">Request Data</Translate>
            </span>
          </dt>
          <dd>{requestInfoEntity.requestData}</dd>
          <dt>
            <span id="paramList">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.requestInfo.paramList">Param List</Translate>
            </span>
          </dt>
          <dd>{requestInfoEntity.paramList}</dd>
          <dt>
            <span id="timestamp">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.requestInfo.timestamp">Timestamp</Translate>
            </span>
          </dt>
          <dd>
            {requestInfoEntity.timestamp ? <TextFormat value={requestInfoEntity.timestamp} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="requestRef">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.requestInfo.requestRef">Request Ref</Translate>
            </span>
          </dt>
          <dd>{requestInfoEntity.requestRef}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.requestInfo.status">Status</Translate>
            </span>
          </dt>
          <dd>{requestInfoEntity.status}</dd>
          <dt>
            <span id="freeField1">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.requestInfo.freeField1">Free Field 1</Translate>
            </span>
          </dt>
          <dd>{requestInfoEntity.freeField1}</dd>
          <dt>
            <span id="freeField2">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.requestInfo.freeField2">Free Field 2</Translate>
            </span>
          </dt>
          <dd>{requestInfoEntity.freeField2}</dd>
          <dt>
            <span id="freeField3">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.requestInfo.freeField3">Free Field 3</Translate>
            </span>
          </dt>
          <dd>{requestInfoEntity.freeField3}</dd>
          <dt>
            <span id="freeField4">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.requestInfo.freeField4">Free Field 4</Translate>
            </span>
          </dt>
          <dd>{requestInfoEntity.freeField4}</dd>
          <dt>
            <span id="freeField5">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.requestInfo.freeField5">Free Field 5</Translate>
            </span>
          </dt>
          <dd>{requestInfoEntity.freeField5}</dd>
          <dt>
            <span id="freeField6">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.requestInfo.freeField6">Free Field 6</Translate>
            </span>
          </dt>
          <dd>{requestInfoEntity.freeField6}</dd>
          <dt>
            <span id="time">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.requestInfo.time">Time</Translate>
            </span>
          </dt>
          <dd>{requestInfoEntity.time ? <TextFormat value={requestInfoEntity.time} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="errorCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.requestInfo.errorCode">Error Code</Translate>
            </span>
          </dt>
          <dd>{requestInfoEntity.errorCode}</dd>
          <dt>
            <span id="errorMessage">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.requestInfo.errorMessage">Error Message</Translate>
            </span>
          </dt>
          <dd>{requestInfoEntity.errorMessage}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.requestInfo.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {requestInfoEntity.createdAt ? <TextFormat value={requestInfoEntity.createdAt} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.requestInfo.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{requestInfoEntity.createdBy}</dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.requestInfo.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {requestInfoEntity.updatedAt ? <TextFormat value={requestInfoEntity.updatedAt} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.requestInfo.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{requestInfoEntity.updatedBy}</dd>
        </dl>
        <Button tag={Link} to="/request-info" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/request-info/${requestInfoEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default RequestInfoDetail;
