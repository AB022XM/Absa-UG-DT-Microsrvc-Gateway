import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, openFile, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './outgoing-json-response.reducer';

export const OutgoingJSONResponseDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const outgoingJSONResponseEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.outgoingJSONResponse.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="outgoingJSONResponseDetailsHeading">
          <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.detail.title">OutgoingJSONResponse</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{outgoingJSONResponseEntity.id}</dd>
          <dt>
            <span id="recordId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.recordId">Record Id</Translate>
            </span>
          </dt>
          <dd>{outgoingJSONResponseEntity.recordId}</dd>
          <dt>
            <span id="responseId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.responseId">Response Id</Translate>
            </span>
          </dt>
          <dd>{outgoingJSONResponseEntity.responseId}</dd>
          <dt>
            <span id="transactionId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.transactionId">Transaction Id</Translate>
            </span>
          </dt>
          <dd>{outgoingJSONResponseEntity.transactionId}</dd>
          <dt>
            <span id="responseJson">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.responseJson">Response Json</Translate>
            </span>
          </dt>
          <dd>{outgoingJSONResponseEntity.responseJson}</dd>
          <dt>
            <span id="responseType">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.responseType">Response Type</Translate>
            </span>
          </dt>
          <dd>{outgoingJSONResponseEntity.responseType}</dd>
          <dt>
            <span id="responseDescription">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.responseDescription">Response Description</Translate>
            </span>
          </dt>
          <dd>{outgoingJSONResponseEntity.responseDescription}</dd>
          <dt>
            <span id="toEndpoint">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.toEndpoint">To Endpoint</Translate>
            </span>
          </dt>
          <dd>{outgoingJSONResponseEntity.toEndpoint}</dd>
          <dt>
            <span id="fromEndpoint">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.fromEndpoint">From Endpoint</Translate>
            </span>
          </dt>
          <dd>{outgoingJSONResponseEntity.fromEndpoint}</dd>
          <dt>
            <span id="responseStatus">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.responseStatus">Response Status</Translate>
            </span>
          </dt>
          <dd>{outgoingJSONResponseEntity.responseStatus}</dd>
          <dt>
            <span id="additionalInfo">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.additionalInfo">Additional Info</Translate>
            </span>
          </dt>
          <dd>{outgoingJSONResponseEntity.additionalInfo}</dd>
          <dt>
            <span id="timestamp">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.timestamp">Timestamp</Translate>
            </span>
          </dt>
          <dd>
            {outgoingJSONResponseEntity.timestamp ? (
              <TextFormat value={outgoingJSONResponseEntity.timestamp} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="exceptionMessage">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.exceptionMessage">Exception Message</Translate>
            </span>
          </dt>
          <dd>{outgoingJSONResponseEntity.exceptionMessage}</dd>
          <dt>
            <span id="freeField">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField">Free Field</Translate>
            </span>
          </dt>
          <dd>{outgoingJSONResponseEntity.freeField}</dd>
          <dt>
            <span id="freeField1">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField1">Free Field 1</Translate>
            </span>
          </dt>
          <dd>{outgoingJSONResponseEntity.freeField1}</dd>
          <dt>
            <span id="freeField2">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField2">Free Field 2</Translate>
            </span>
          </dt>
          <dd>{outgoingJSONResponseEntity.freeField2}</dd>
          <dt>
            <span id="freeField3">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField3">Free Field 3</Translate>
            </span>
          </dt>
          <dd>{outgoingJSONResponseEntity.freeField3}</dd>
          <dt>
            <span id="freeField4">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField4">Free Field 4</Translate>
            </span>
          </dt>
          <dd>{outgoingJSONResponseEntity.freeField4}</dd>
          <dt>
            <span id="freeField5">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField5">Free Field 5</Translate>
            </span>
          </dt>
          <dd>{outgoingJSONResponseEntity.freeField5}</dd>
          <dt>
            <span id="freeField6">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField6">Free Field 6</Translate>
            </span>
          </dt>
          <dd>{outgoingJSONResponseEntity.freeField6}</dd>
          <dt>
            <span id="freeField8">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField8">Free Field 8</Translate>
            </span>
          </dt>
          <dd>{outgoingJSONResponseEntity.freeField8}</dd>
          <dt>
            <span id="freeField9">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField9">Free Field 9</Translate>
            </span>
          </dt>
          <dd>{outgoingJSONResponseEntity.freeField9}</dd>
          <dt>
            <span id="freeField10">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField10">Free Field 10</Translate>
            </span>
          </dt>
          <dd>{outgoingJSONResponseEntity.freeField10}</dd>
          <dt>
            <span id="freeField11">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField11">Free Field 11</Translate>
            </span>
          </dt>
          <dd>{outgoingJSONResponseEntity.freeField11}</dd>
          <dt>
            <span id="freeField12">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField12">Free Field 12</Translate>
            </span>
          </dt>
          <dd>{outgoingJSONResponseEntity.freeField12}</dd>
          <dt>
            <span id="freeField13">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField13">Free Field 13</Translate>
            </span>
          </dt>
          <dd>{outgoingJSONResponseEntity.freeField13}</dd>
          <dt>
            <span id="freeField14">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField14">Free Field 14</Translate>
            </span>
          </dt>
          <dd>{outgoingJSONResponseEntity.freeField14}</dd>
          <dt>
            <span id="freeField15">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField15">Free Field 15</Translate>
            </span>
          </dt>
          <dd>
            {outgoingJSONResponseEntity.freeField15 ? (
              <div>
                {outgoingJSONResponseEntity.freeField15ContentType ? (
                  <a onClick={openFile(outgoingJSONResponseEntity.freeField15ContentType, outgoingJSONResponseEntity.freeField15)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                ) : null}
                <span>
                  {outgoingJSONResponseEntity.freeField15ContentType}, {byteSize(outgoingJSONResponseEntity.freeField15)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="freeField16">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField16">Free Field 16</Translate>
            </span>
          </dt>
          <dd>{outgoingJSONResponseEntity.freeField16}</dd>
          <dt>
            <span id="freeField17">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField17">Free Field 17</Translate>
            </span>
          </dt>
          <dd>{outgoingJSONResponseEntity.freeField17}</dd>
          <dt>
            <span id="freeField18">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField18">Free Field 18</Translate>
            </span>
          </dt>
          <dd>
            {outgoingJSONResponseEntity.freeField18 ? (
              <div>
                {outgoingJSONResponseEntity.freeField18ContentType ? (
                  <a onClick={openFile(outgoingJSONResponseEntity.freeField18ContentType, outgoingJSONResponseEntity.freeField18)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                ) : null}
                <span>
                  {outgoingJSONResponseEntity.freeField18ContentType}, {byteSize(outgoingJSONResponseEntity.freeField18)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="freeField19">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField19">Free Field 19</Translate>
            </span>
          </dt>
          <dd>{outgoingJSONResponseEntity.freeField19}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {outgoingJSONResponseEntity.createdAt ? (
              <TextFormat value={outgoingJSONResponseEntity.createdAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{outgoingJSONResponseEntity.createdBy}</dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {outgoingJSONResponseEntity.updatedAt ? (
              <TextFormat value={outgoingJSONResponseEntity.updatedAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{outgoingJSONResponseEntity.updatedBy}</dd>
        </dl>
        <Button tag={Link} to="/outgoing-json-response" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/outgoing-json-response/${outgoingJSONResponseEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default OutgoingJSONResponseDetail;
