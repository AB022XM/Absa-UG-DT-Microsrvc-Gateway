import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, openFile, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './app-config.reducer';

export const AppConfigDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const appConfigEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.appConfig.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="appConfigDetailsHeading">
          <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.detail.title">AppConfig</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{appConfigEntity.id}</dd>
          <dt>
            <span id="absaTranRef">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.absaTranRef">Absa Tran Ref</Translate>
            </span>
          </dt>
          <dd>{appConfigEntity.absaTranRef}</dd>
          <dt>
            <span id="recordId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.recordId">Record Id</Translate>
            </span>
          </dt>
          <dd>{appConfigEntity.recordId}</dd>
          <dt>
            <span id="configId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.configId">Config Id</Translate>
            </span>
          </dt>
          <dd>{appConfigEntity.configId}</dd>
          <dt>
            <span id="configName">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.configName">Config Name</Translate>
            </span>
          </dt>
          <dd>{appConfigEntity.configName}</dd>
          <dt>
            <span id="configValue">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.configValue">Config Value</Translate>
            </span>
          </dt>
          <dd>{appConfigEntity.configValue}</dd>
          <dt>
            <span id="configType">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.configType">Config Type</Translate>
            </span>
          </dt>
          <dd>{appConfigEntity.configType}</dd>
          <dt>
            <span id="configDescription">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.configDescription">Config Description</Translate>
            </span>
          </dt>
          <dd>{appConfigEntity.configDescription}</dd>
          <dt>
            <span id="configStatus">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.configStatus">Config Status</Translate>
            </span>
          </dt>
          <dd>{appConfigEntity.configStatus}</dd>
          <dt>
            <span id="freeField1">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.freeField1">Free Field 1</Translate>
            </span>
          </dt>
          <dd>{appConfigEntity.freeField1}</dd>
          <dt>
            <span id="freeField2">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.freeField2">Free Field 2</Translate>
            </span>
          </dt>
          <dd>{appConfigEntity.freeField2}</dd>
          <dt>
            <span id="freeField3">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.freeField3">Free Field 3</Translate>
            </span>
          </dt>
          <dd>{appConfigEntity.freeField3}</dd>
          <dt>
            <span id="freeField4">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.freeField4">Free Field 4</Translate>
            </span>
          </dt>
          <dd>{appConfigEntity.freeField4}</dd>
          <dt>
            <span id="freeField5">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.freeField5">Free Field 5</Translate>
            </span>
          </dt>
          <dd>{appConfigEntity.freeField5}</dd>
          <dt>
            <span id="freeField6">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.freeField6">Free Field 6</Translate>
            </span>
          </dt>
          <dd>{appConfigEntity.freeField6}</dd>
          <dt>
            <span id="freeField7">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.freeField7">Free Field 7</Translate>
            </span>
          </dt>
          <dd>{appConfigEntity.freeField7}</dd>
          <dt>
            <span id="freeField8">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.freeField8">Free Field 8</Translate>
            </span>
          </dt>
          <dd>{appConfigEntity.freeField8}</dd>
          <dt>
            <span id="freeField9">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.freeField9">Free Field 9</Translate>
            </span>
          </dt>
          <dd>{appConfigEntity.freeField9}</dd>
          <dt>
            <span id="freeField10">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.freeField10">Free Field 10</Translate>
            </span>
          </dt>
          <dd>{appConfigEntity.freeField10}</dd>
          <dt>
            <span id="freeField11">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.freeField11">Free Field 11</Translate>
            </span>
          </dt>
          <dd>{appConfigEntity.freeField11}</dd>
          <dt>
            <span id="freeField12">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.freeField12">Free Field 12</Translate>
            </span>
          </dt>
          <dd>{appConfigEntity.freeField12}</dd>
          <dt>
            <span id="freeField13">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.freeField13">Free Field 13</Translate>
            </span>
          </dt>
          <dd>{appConfigEntity.freeField13}</dd>
          <dt>
            <span id="freeField14">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.freeField14">Free Field 14</Translate>
            </span>
          </dt>
          <dd>
            {appConfigEntity.freeField14 ? (
              <div>
                {appConfigEntity.freeField14ContentType ? (
                  <a onClick={openFile(appConfigEntity.freeField14ContentType, appConfigEntity.freeField14)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                ) : null}
                <span>
                  {appConfigEntity.freeField14ContentType}, {byteSize(appConfigEntity.freeField14)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="freeField15">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.freeField15">Free Field 15</Translate>
            </span>
          </dt>
          <dd>
            {appConfigEntity.freeField15 ? (
              <div>
                {appConfigEntity.freeField15ContentType ? (
                  <a onClick={openFile(appConfigEntity.freeField15ContentType, appConfigEntity.freeField15)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                ) : null}
                <span>
                  {appConfigEntity.freeField15ContentType}, {byteSize(appConfigEntity.freeField15)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="freeField16">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.freeField16">Free Field 16</Translate>
            </span>
          </dt>
          <dd>{appConfigEntity.freeField16}</dd>
          <dt>
            <span id="freeField17">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.freeField17">Free Field 17</Translate>
            </span>
          </dt>
          <dd>{appConfigEntity.freeField17}</dd>
          <dt>
            <span id="freeField18">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.freeField18">Free Field 18</Translate>
            </span>
          </dt>
          <dd>
            {appConfigEntity.freeField18 ? (
              <div>
                {appConfigEntity.freeField18ContentType ? (
                  <a onClick={openFile(appConfigEntity.freeField18ContentType, appConfigEntity.freeField18)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                ) : null}
                <span>
                  {appConfigEntity.freeField18ContentType}, {byteSize(appConfigEntity.freeField18)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="freeField19">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.freeField19">Free Field 19</Translate>
            </span>
          </dt>
          <dd>{appConfigEntity.freeField19}</dd>
          <dt>
            <span id="delflg">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.delflg">Delflg</Translate>
            </span>
          </dt>
          <dd>{appConfigEntity.delflg ? 'true' : 'false'}</dd>
          <dt>
            <span id="timestamp">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.timestamp">Timestamp</Translate>
            </span>
          </dt>
          <dd>
            {appConfigEntity.timestamp ? <TextFormat value={appConfigEntity.timestamp} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {appConfigEntity.createdAt ? <TextFormat value={appConfigEntity.createdAt} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{appConfigEntity.createdBy}</dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {appConfigEntity.updatedAt ? <TextFormat value={appConfigEntity.updatedAt} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{appConfigEntity.updatedBy}</dd>
          <dt>
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.apps">Apps</Translate>
          </dt>
          <dd>{appConfigEntity.apps ? appConfigEntity.apps.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/app-config" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/app-config/${appConfigEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default AppConfigDetail;
