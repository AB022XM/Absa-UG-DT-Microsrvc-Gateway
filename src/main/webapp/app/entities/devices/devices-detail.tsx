import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, openFile, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './devices.reducer';

export const DevicesDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const devicesEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.devices.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="devicesDetailsHeading">
          <Translate contentKey="absaUgdtMicrosrvcGatewayApp.devices.detail.title">Devices</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{devicesEntity.id}</dd>
          <dt>
            <span id="absaTranRef">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.devices.absaTranRef">Absa Tran Ref</Translate>
            </span>
          </dt>
          <dd>{devicesEntity.absaTranRef}</dd>
          <dt>
            <span id="recordId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.devices.recordId">Record Id</Translate>
            </span>
          </dt>
          <dd>{devicesEntity.recordId}</dd>
          <dt>
            <span id="deviceId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.devices.deviceId">Device Id</Translate>
            </span>
          </dt>
          <dd>{devicesEntity.deviceId}</dd>
          <dt>
            <span id="deviceName">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.devices.deviceName">Device Name</Translate>
            </span>
          </dt>
          <dd>{devicesEntity.deviceName}</dd>
          <dt>
            <span id="deviceType">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.devices.deviceType">Device Type</Translate>
            </span>
          </dt>
          <dd>{devicesEntity.deviceType}</dd>
          <dt>
            <span id="deviceDescription">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.devices.deviceDescription">Device Description</Translate>
            </span>
          </dt>
          <dd>{devicesEntity.deviceDescription}</dd>
          <dt>
            <span id="timestamp">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.devices.timestamp">Timestamp</Translate>
            </span>
          </dt>
          <dd>{devicesEntity.timestamp ? <TextFormat value={devicesEntity.timestamp} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="delflg">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.devices.delflg">Delflg</Translate>
            </span>
          </dt>
          <dd>{devicesEntity.delflg ? 'true' : 'false'}</dd>
          <dt>
            <span id="recordStatus">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.devices.recordStatus">Record Status</Translate>
            </span>
          </dt>
          <dd>{devicesEntity.recordStatus}</dd>
          <dt>
            <span id="freeField">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.devices.freeField">Free Field</Translate>
            </span>
          </dt>
          <dd>{devicesEntity.freeField}</dd>
          <dt>
            <span id="freeField1">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.devices.freeField1">Free Field 1</Translate>
            </span>
          </dt>
          <dd>{devicesEntity.freeField1}</dd>
          <dt>
            <span id="freeField2">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.devices.freeField2">Free Field 2</Translate>
            </span>
          </dt>
          <dd>{devicesEntity.freeField2}</dd>
          <dt>
            <span id="freeField3">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.devices.freeField3">Free Field 3</Translate>
            </span>
          </dt>
          <dd>{devicesEntity.freeField3}</dd>
          <dt>
            <span id="freeField4">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.devices.freeField4">Free Field 4</Translate>
            </span>
          </dt>
          <dd>{devicesEntity.freeField4}</dd>
          <dt>
            <span id="freeField5">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.devices.freeField5">Free Field 5</Translate>
            </span>
          </dt>
          <dd>{devicesEntity.freeField5}</dd>
          <dt>
            <span id="freeField6">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.devices.freeField6">Free Field 6</Translate>
            </span>
          </dt>
          <dd>{devicesEntity.freeField6}</dd>
          <dt>
            <span id="freeField7">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.devices.freeField7">Free Field 7</Translate>
            </span>
          </dt>
          <dd>{devicesEntity.freeField7}</dd>
          <dt>
            <span id="freeField8">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.devices.freeField8">Free Field 8</Translate>
            </span>
          </dt>
          <dd>{devicesEntity.freeField8}</dd>
          <dt>
            <span id="freeField9">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.devices.freeField9">Free Field 9</Translate>
            </span>
          </dt>
          <dd>{devicesEntity.freeField9}</dd>
          <dt>
            <span id="freeField10">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.devices.freeField10">Free Field 10</Translate>
            </span>
          </dt>
          <dd>{devicesEntity.freeField10}</dd>
          <dt>
            <span id="freeField11">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.devices.freeField11">Free Field 11</Translate>
            </span>
          </dt>
          <dd>{devicesEntity.freeField11}</dd>
          <dt>
            <span id="freeField12">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.devices.freeField12">Free Field 12</Translate>
            </span>
          </dt>
          <dd>{devicesEntity.freeField12}</dd>
          <dt>
            <span id="freeField13">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.devices.freeField13">Free Field 13</Translate>
            </span>
          </dt>
          <dd>{devicesEntity.freeField13}</dd>
          <dt>
            <span id="freeField14">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.devices.freeField14">Free Field 14</Translate>
            </span>
          </dt>
          <dd>{devicesEntity.freeField14}</dd>
          <dt>
            <span id="freeField15">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.devices.freeField15">Free Field 15</Translate>
            </span>
          </dt>
          <dd>
            {devicesEntity.freeField15 ? (
              <div>
                {devicesEntity.freeField15ContentType ? (
                  <a onClick={openFile(devicesEntity.freeField15ContentType, devicesEntity.freeField15)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                ) : null}
                <span>
                  {devicesEntity.freeField15ContentType}, {byteSize(devicesEntity.freeField15)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="freeField16">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.devices.freeField16">Free Field 16</Translate>
            </span>
          </dt>
          <dd>{devicesEntity.freeField16}</dd>
          <dt>
            <span id="freeField17">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.devices.freeField17">Free Field 17</Translate>
            </span>
          </dt>
          <dd>{devicesEntity.freeField17}</dd>
          <dt>
            <span id="freeField18">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.devices.freeField18">Free Field 18</Translate>
            </span>
          </dt>
          <dd>
            {devicesEntity.freeField18 ? (
              <div>
                {devicesEntity.freeField18ContentType ? (
                  <a onClick={openFile(devicesEntity.freeField18ContentType, devicesEntity.freeField18)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                ) : null}
                <span>
                  {devicesEntity.freeField18ContentType}, {byteSize(devicesEntity.freeField18)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="freeField19">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.devices.freeField19">Free Field 19</Translate>
            </span>
          </dt>
          <dd>{devicesEntity.freeField19}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.devices.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>{devicesEntity.createdAt ? <TextFormat value={devicesEntity.createdAt} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.devices.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{devicesEntity.createdBy}</dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.devices.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>{devicesEntity.updatedAt ? <TextFormat value={devicesEntity.updatedAt} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.devices.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{devicesEntity.updatedBy}</dd>
        </dl>
        <Button tag={Link} to="/devices" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/devices/${devicesEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DevicesDetail;
