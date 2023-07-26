import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, openFile, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './incoming-json-request.reducer';

export const IncomingJSONRequestDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const incomingJSONRequestEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.incomingJSONRequest.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="incomingJSONRequestDetailsHeading">
          <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.detail.title">IncomingJSONRequest</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{incomingJSONRequestEntity.id}</dd>
          <dt>
            <span id="absaTranRef">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.absaTranRef">Absa Tran Ref</Translate>
            </span>
          </dt>
          <dd>{incomingJSONRequestEntity.absaTranRef}</dd>
          <dt>
            <span id="dtDTransactionId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.dtDTransactionId">Dt D Transaction Id</Translate>
            </span>
          </dt>
          <dd>{incomingJSONRequestEntity.dtDTransactionId}</dd>
          <dt>
            <span id="amolDTransactionId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.amolDTransactionId">Amol D Transaction Id</Translate>
            </span>
          </dt>
          <dd>{incomingJSONRequestEntity.amolDTransactionId}</dd>
          <dt>
            <span id="transactionReferenceNumber">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.transactionReferenceNumber">
                Transaction Reference Number
              </Translate>
            </span>
          </dt>
          <dd>{incomingJSONRequestEntity.transactionReferenceNumber}</dd>
          <dt>
            <span id="token">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.token">Token</Translate>
            </span>
          </dt>
          <dd>{incomingJSONRequestEntity.token}</dd>
          <dt>
            <span id="transactionId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.transactionId">Transaction Id</Translate>
            </span>
          </dt>
          <dd>{incomingJSONRequestEntity.transactionId}</dd>
          <dt>
            <span id="fromEndpoint">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.fromEndpoint">From Endpoint</Translate>
            </span>
          </dt>
          <dd>{incomingJSONRequestEntity.fromEndpoint}</dd>
          <dt>
            <span id="toEndpoint">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.toEndpoint">To Endpoint</Translate>
            </span>
          </dt>
          <dd>{incomingJSONRequestEntity.toEndpoint}</dd>
          <dt>
            <span id="requestJson">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.requestJson">Request Json</Translate>
            </span>
          </dt>
          <dd>{incomingJSONRequestEntity.requestJson}</dd>
          <dt>
            <span id="requestType">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.requestType">Request Type</Translate>
            </span>
          </dt>
          <dd>{incomingJSONRequestEntity.requestType}</dd>
          <dt>
            <span id="requestDescription">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.requestDescription">Request Description</Translate>
            </span>
          </dt>
          <dd>{incomingJSONRequestEntity.requestDescription}</dd>
          <dt>
            <span id="requestStatus">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.requestStatus">Request Status</Translate>
            </span>
          </dt>
          <dd>{incomingJSONRequestEntity.requestStatus}</dd>
          <dt>
            <span id="additionalInfo">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.additionalInfo">Additional Info</Translate>
            </span>
          </dt>
          <dd>{incomingJSONRequestEntity.additionalInfo}</dd>
          <dt>
            <span id="freeField1">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField1">Free Field 1</Translate>
            </span>
          </dt>
          <dd>{incomingJSONRequestEntity.freeField1}</dd>
          <dt>
            <span id="freeField2">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField2">Free Field 2</Translate>
            </span>
          </dt>
          <dd>{incomingJSONRequestEntity.freeField2}</dd>
          <dt>
            <span id="freeField3">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField3">Free Field 3</Translate>
            </span>
          </dt>
          <dd>{incomingJSONRequestEntity.freeField3}</dd>
          <dt>
            <span id="freeField4">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField4">Free Field 4</Translate>
            </span>
          </dt>
          <dd>{incomingJSONRequestEntity.freeField4}</dd>
          <dt>
            <span id="freeField5">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField5">Free Field 5</Translate>
            </span>
          </dt>
          <dd>{incomingJSONRequestEntity.freeField5}</dd>
          <dt>
            <span id="freeField6">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField6">Free Field 6</Translate>
            </span>
          </dt>
          <dd>
            {incomingJSONRequestEntity.freeField6 ? (
              <div>
                {incomingJSONRequestEntity.freeField6ContentType ? (
                  <a onClick={openFile(incomingJSONRequestEntity.freeField6ContentType, incomingJSONRequestEntity.freeField6)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                ) : null}
                <span>
                  {incomingJSONRequestEntity.freeField6ContentType}, {byteSize(incomingJSONRequestEntity.freeField6)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="freeField7">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField7">Free Field 7</Translate>
            </span>
          </dt>
          <dd>
            {incomingJSONRequestEntity.freeField7 ? (
              <div>
                {incomingJSONRequestEntity.freeField7ContentType ? (
                  <a onClick={openFile(incomingJSONRequestEntity.freeField7ContentType, incomingJSONRequestEntity.freeField7)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                ) : null}
                <span>
                  {incomingJSONRequestEntity.freeField7ContentType}, {byteSize(incomingJSONRequestEntity.freeField7)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="freeField8">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField8">Free Field 8</Translate>
            </span>
          </dt>
          <dd>
            {incomingJSONRequestEntity.freeField8 ? (
              <div>
                {incomingJSONRequestEntity.freeField8ContentType ? (
                  <a onClick={openFile(incomingJSONRequestEntity.freeField8ContentType, incomingJSONRequestEntity.freeField8)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                ) : null}
                <span>
                  {incomingJSONRequestEntity.freeField8ContentType}, {byteSize(incomingJSONRequestEntity.freeField8)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="freeField9">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField9">Free Field 9</Translate>
            </span>
          </dt>
          <dd>{incomingJSONRequestEntity.freeField9}</dd>
          <dt>
            <span id="freeField10">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField10">Free Field 10</Translate>
            </span>
          </dt>
          <dd>{incomingJSONRequestEntity.freeField10 ? 'true' : 'false'}</dd>
          <dt>
            <span id="freeField11">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField11">Free Field 11</Translate>
            </span>
          </dt>
          <dd>{incomingJSONRequestEntity.freeField11 ? 'true' : 'false'}</dd>
          <dt>
            <span id="freeField12">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField12">Free Field 12</Translate>
            </span>
          </dt>
          <dd>{incomingJSONRequestEntity.freeField12}</dd>
          <dt>
            <span id="freeField13">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField13">Free Field 13</Translate>
            </span>
          </dt>
          <dd>{incomingJSONRequestEntity.freeField13}</dd>
          <dt>
            <span id="freeField14">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField14">Free Field 14</Translate>
            </span>
          </dt>
          <dd>{incomingJSONRequestEntity.freeField14}</dd>
          <dt>
            <span id="freeField15">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField15">Free Field 15</Translate>
            </span>
          </dt>
          <dd>{incomingJSONRequestEntity.freeField15}</dd>
          <dt>
            <span id="freeField16">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField16">Free Field 16</Translate>
            </span>
          </dt>
          <dd>{incomingJSONRequestEntity.freeField16}</dd>
          <dt>
            <span id="freeField17">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField17">Free Field 17</Translate>
            </span>
          </dt>
          <dd>{incomingJSONRequestEntity.freeField17}</dd>
          <dt>
            <span id="freeField18">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField18">Free Field 18</Translate>
            </span>
          </dt>
          <dd>{incomingJSONRequestEntity.freeField18}</dd>
          <dt>
            <span id="freeField19">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField19">Free Field 19</Translate>
            </span>
          </dt>
          <dd>{incomingJSONRequestEntity.freeField19}</dd>
          <dt>
            <span id="freeField20">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField20">Free Field 20</Translate>
            </span>
          </dt>
          <dd>{incomingJSONRequestEntity.freeField20}</dd>
          <dt>
            <span id="timestamp">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.timestamp">Timestamp</Translate>
            </span>
          </dt>
          <dd>
            {incomingJSONRequestEntity.timestamp ? (
              <TextFormat value={incomingJSONRequestEntity.timestamp} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {incomingJSONRequestEntity.createdAt ? (
              <TextFormat value={incomingJSONRequestEntity.createdAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{incomingJSONRequestEntity.createdBy}</dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {incomingJSONRequestEntity.updatedAt ? (
              <TextFormat value={incomingJSONRequestEntity.updatedAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{incomingJSONRequestEntity.updatedBy}</dd>
        </dl>
        <Button tag={Link} to="/incoming-json-request" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/incoming-json-request/${incomingJSONRequestEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default IncomingJSONRequestDetail;
