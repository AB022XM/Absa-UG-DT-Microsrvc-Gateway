import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, openFile, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './incoming-json-response.reducer';

export const IncomingJSONResponseDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const incomingJSONResponseEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.incomingJSONResponse.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="incomingJSONResponseDetailsHeading">
          <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.detail.title">IncomingJSONResponse</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{incomingJSONResponseEntity.id}</dd>
          <dt>
            <span id="absaTranRef">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.absaTranRef">Absa Tran Ref</Translate>
            </span>
          </dt>
          <dd>{incomingJSONResponseEntity.absaTranRef}</dd>
          <dt>
            <span id="dtDTransactionId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.dtDTransactionId">Dt D Transaction Id</Translate>
            </span>
          </dt>
          <dd>{incomingJSONResponseEntity.dtDTransactionId}</dd>
          <dt>
            <span id="amolDTransactionId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.amolDTransactionId">Amol D Transaction Id</Translate>
            </span>
          </dt>
          <dd>{incomingJSONResponseEntity.amolDTransactionId}</dd>
          <dt>
            <span id="transactionReferenceNumber">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.transactionReferenceNumber">
                Transaction Reference Number
              </Translate>
            </span>
          </dt>
          <dd>{incomingJSONResponseEntity.transactionReferenceNumber}</dd>
          <dt>
            <span id="token">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.token">Token</Translate>
            </span>
          </dt>
          <dd>{incomingJSONResponseEntity.token}</dd>
          <dt>
            <span id="responseId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.responseId">Response Id</Translate>
            </span>
          </dt>
          <dd>{incomingJSONResponseEntity.responseId}</dd>
          <dt>
            <span id="transactionId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.transactionId">Transaction Id</Translate>
            </span>
          </dt>
          <dd>{incomingJSONResponseEntity.transactionId}</dd>
          <dt>
            <span id="responseJson">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.responseJson">Response Json</Translate>
            </span>
          </dt>
          <dd>{incomingJSONResponseEntity.responseJson}</dd>
          <dt>
            <span id="responseType">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.responseType">Response Type</Translate>
            </span>
          </dt>
          <dd>{incomingJSONResponseEntity.responseType}</dd>
          <dt>
            <span id="responseDescription">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.responseDescription">Response Description</Translate>
            </span>
          </dt>
          <dd>{incomingJSONResponseEntity.responseDescription}</dd>
          <dt>
            <span id="responseStatus">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.responseStatus">Response Status</Translate>
            </span>
          </dt>
          <dd>{incomingJSONResponseEntity.responseStatus}</dd>
          <dt>
            <span id="additionalInfo">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.additionalInfo">Additional Info</Translate>
            </span>
          </dt>
          <dd>{incomingJSONResponseEntity.additionalInfo}</dd>
          <dt>
            <span id="timestamp">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.timestamp">Timestamp</Translate>
            </span>
          </dt>
          <dd>
            {incomingJSONResponseEntity.timestamp ? (
              <TextFormat value={incomingJSONResponseEntity.timestamp} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="exceptionMessage">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.exceptionMessage">Exception Message</Translate>
            </span>
          </dt>
          <dd>{incomingJSONResponseEntity.exceptionMessage}</dd>
          <dt>
            <span id="freeField">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField">Free Field</Translate>
            </span>
          </dt>
          <dd>{incomingJSONResponseEntity.freeField}</dd>
          <dt>
            <span id="freeField1">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField1">Free Field 1</Translate>
            </span>
          </dt>
          <dd>{incomingJSONResponseEntity.freeField1}</dd>
          <dt>
            <span id="freeField2">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField2">Free Field 2</Translate>
            </span>
          </dt>
          <dd>{incomingJSONResponseEntity.freeField2}</dd>
          <dt>
            <span id="freeField3">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField3">Free Field 3</Translate>
            </span>
          </dt>
          <dd>{incomingJSONResponseEntity.freeField3}</dd>
          <dt>
            <span id="freeField4">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField4">Free Field 4</Translate>
            </span>
          </dt>
          <dd>{incomingJSONResponseEntity.freeField4}</dd>
          <dt>
            <span id="freeField5">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField5">Free Field 5</Translate>
            </span>
          </dt>
          <dd>{incomingJSONResponseEntity.freeField5}</dd>
          <dt>
            <span id="freeField6">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField6">Free Field 6</Translate>
            </span>
          </dt>
          <dd>{incomingJSONResponseEntity.freeField6}</dd>
          <dt>
            <span id="freeField8">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField8">Free Field 8</Translate>
            </span>
          </dt>
          <dd>{incomingJSONResponseEntity.freeField8}</dd>
          <dt>
            <span id="freeField9">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField9">Free Field 9</Translate>
            </span>
          </dt>
          <dd>{incomingJSONResponseEntity.freeField9}</dd>
          <dt>
            <span id="freeField10">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField10">Free Field 10</Translate>
            </span>
          </dt>
          <dd>{incomingJSONResponseEntity.freeField10}</dd>
          <dt>
            <span id="freeField11">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField11">Free Field 11</Translate>
            </span>
          </dt>
          <dd>{incomingJSONResponseEntity.freeField11}</dd>
          <dt>
            <span id="freeField12">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField12">Free Field 12</Translate>
            </span>
          </dt>
          <dd>{incomingJSONResponseEntity.freeField12}</dd>
          <dt>
            <span id="freeField13">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField13">Free Field 13</Translate>
            </span>
          </dt>
          <dd>{incomingJSONResponseEntity.freeField13}</dd>
          <dt>
            <span id="freeField14">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField14">Free Field 14</Translate>
            </span>
          </dt>
          <dd>{incomingJSONResponseEntity.freeField14}</dd>
          <dt>
            <span id="freeField15">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField15">Free Field 15</Translate>
            </span>
          </dt>
          <dd>
            {incomingJSONResponseEntity.freeField15 ? (
              <div>
                {incomingJSONResponseEntity.freeField15ContentType ? (
                  <a onClick={openFile(incomingJSONResponseEntity.freeField15ContentType, incomingJSONResponseEntity.freeField15)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                ) : null}
                <span>
                  {incomingJSONResponseEntity.freeField15ContentType}, {byteSize(incomingJSONResponseEntity.freeField15)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="freeField16">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField16">Free Field 16</Translate>
            </span>
          </dt>
          <dd>{incomingJSONResponseEntity.freeField16}</dd>
          <dt>
            <span id="freeField17">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField17">Free Field 17</Translate>
            </span>
          </dt>
          <dd>{incomingJSONResponseEntity.freeField17}</dd>
          <dt>
            <span id="freeField18">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField18">Free Field 18</Translate>
            </span>
          </dt>
          <dd>
            {incomingJSONResponseEntity.freeField18 ? (
              <div>
                {incomingJSONResponseEntity.freeField18ContentType ? (
                  <a onClick={openFile(incomingJSONResponseEntity.freeField18ContentType, incomingJSONResponseEntity.freeField18)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                ) : null}
                <span>
                  {incomingJSONResponseEntity.freeField18ContentType}, {byteSize(incomingJSONResponseEntity.freeField18)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="freeField19">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField19">Free Field 19</Translate>
            </span>
          </dt>
          <dd>{incomingJSONResponseEntity.freeField19}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {incomingJSONResponseEntity.createdAt ? (
              <TextFormat value={incomingJSONResponseEntity.createdAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{incomingJSONResponseEntity.createdBy}</dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {incomingJSONResponseEntity.updatedAt ? (
              <TextFormat value={incomingJSONResponseEntity.updatedAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{incomingJSONResponseEntity.updatedBy}</dd>
        </dl>
        <Button tag={Link} to="/incoming-json-response" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/incoming-json-response/${incomingJSONResponseEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default IncomingJSONResponseDetail;
