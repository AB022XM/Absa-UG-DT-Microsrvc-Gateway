import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, openFile, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './default-settings.reducer';

export const DefaultSettingsDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const defaultSettingsEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.defaultSettings.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="defaultSettingsDetailsHeading">
          <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.detail.title">DefaultSettings</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{defaultSettingsEntity.id}</dd>
          <dt>
            <span id="absaTranRef">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.absaTranRef">Absa Tran Ref</Translate>
            </span>
          </dt>
          <dd>{defaultSettingsEntity.absaTranRef}</dd>
          <dt>
            <span id="dtDTransactionId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.dtDTransactionId">Dt D Transaction Id</Translate>
            </span>
          </dt>
          <dd>{defaultSettingsEntity.dtDTransactionId}</dd>
          <dt>
            <span id="amolDTransactionId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.amolDTransactionId">Amol D Transaction Id</Translate>
            </span>
          </dt>
          <dd>{defaultSettingsEntity.amolDTransactionId}</dd>
          <dt>
            <span id="transactionReferenceNumber">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.transactionReferenceNumber">
                Transaction Reference Number
              </Translate>
            </span>
          </dt>
          <dd>{defaultSettingsEntity.transactionReferenceNumber}</dd>
          <dt>
            <span id="token">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.token">Token</Translate>
            </span>
          </dt>
          <dd>{defaultSettingsEntity.token}</dd>
          <dt>
            <span id="thirdPartyDTransactionId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.thirdPartyDTransactionId">
                Third Party D Transaction Id
              </Translate>
            </span>
          </dt>
          <dd>{defaultSettingsEntity.thirdPartyDTransactionId}</dd>
          <dt>
            <span id="defaultSettingCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.defaultSettingCode">Default Setting Code</Translate>
            </span>
          </dt>
          <dd>{defaultSettingsEntity.defaultSettingCode}</dd>
          <dt>
            <span id="jsonSettings">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.jsonSettings">Json Settings</Translate>
            </span>
          </dt>
          <dd>{defaultSettingsEntity.jsonSettings}</dd>
          <dt>
            <span id="appConfig">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.appConfig">App Config</Translate>
            </span>
          </dt>
          <dd>{defaultSettingsEntity.appConfig}</dd>
          <dt>
            <span id="appName">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.appName">App Name</Translate>
            </span>
          </dt>
          <dd>{defaultSettingsEntity.appName}</dd>
          <dt>
            <span id="freeField">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField">Free Field</Translate>
            </span>
          </dt>
          <dd>{defaultSettingsEntity.freeField}</dd>
          <dt>
            <span id="freeField1">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField1">Free Field 1</Translate>
            </span>
          </dt>
          <dd>{defaultSettingsEntity.freeField1}</dd>
          <dt>
            <span id="freeField2">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField2">Free Field 2</Translate>
            </span>
          </dt>
          <dd>{defaultSettingsEntity.freeField2}</dd>
          <dt>
            <span id="freeField3">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField3">Free Field 3</Translate>
            </span>
          </dt>
          <dd>{defaultSettingsEntity.freeField3}</dd>
          <dt>
            <span id="freeField4">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField4">Free Field 4</Translate>
            </span>
          </dt>
          <dd>{defaultSettingsEntity.freeField4}</dd>
          <dt>
            <span id="freeField5">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField5">Free Field 5</Translate>
            </span>
          </dt>
          <dd>{defaultSettingsEntity.freeField5}</dd>
          <dt>
            <span id="freeField6">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField6">Free Field 6</Translate>
            </span>
          </dt>
          <dd>{defaultSettingsEntity.freeField6}</dd>
          <dt>
            <span id="freeField8">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField8">Free Field 8</Translate>
            </span>
          </dt>
          <dd>{defaultSettingsEntity.freeField8}</dd>
          <dt>
            <span id="freeField9">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField9">Free Field 9</Translate>
            </span>
          </dt>
          <dd>{defaultSettingsEntity.freeField9}</dd>
          <dt>
            <span id="freeField10">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField10">Free Field 10</Translate>
            </span>
          </dt>
          <dd>{defaultSettingsEntity.freeField10}</dd>
          <dt>
            <span id="freeField11">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField11">Free Field 11</Translate>
            </span>
          </dt>
          <dd>{defaultSettingsEntity.freeField11}</dd>
          <dt>
            <span id="freeField12">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField12">Free Field 12</Translate>
            </span>
          </dt>
          <dd>{defaultSettingsEntity.freeField12}</dd>
          <dt>
            <span id="freeField13">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField13">Free Field 13</Translate>
            </span>
          </dt>
          <dd>{defaultSettingsEntity.freeField13}</dd>
          <dt>
            <span id="freeField14">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField14">Free Field 14</Translate>
            </span>
          </dt>
          <dd>{defaultSettingsEntity.freeField14}</dd>
          <dt>
            <span id="freeField15">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField15">Free Field 15</Translate>
            </span>
          </dt>
          <dd>
            {defaultSettingsEntity.freeField15 ? (
              <div>
                {defaultSettingsEntity.freeField15ContentType ? (
                  <a onClick={openFile(defaultSettingsEntity.freeField15ContentType, defaultSettingsEntity.freeField15)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                ) : null}
                <span>
                  {defaultSettingsEntity.freeField15ContentType}, {byteSize(defaultSettingsEntity.freeField15)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="freeField16">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField16">Free Field 16</Translate>
            </span>
          </dt>
          <dd>{defaultSettingsEntity.freeField16}</dd>
          <dt>
            <span id="freeField17">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField17">Free Field 17</Translate>
            </span>
          </dt>
          <dd>{defaultSettingsEntity.freeField17}</dd>
          <dt>
            <span id="freeField18">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField18">Free Field 18</Translate>
            </span>
          </dt>
          <dd>
            {defaultSettingsEntity.freeField18 ? (
              <div>
                {defaultSettingsEntity.freeField18ContentType ? (
                  <a onClick={openFile(defaultSettingsEntity.freeField18ContentType, defaultSettingsEntity.freeField18)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                ) : null}
                <span>
                  {defaultSettingsEntity.freeField18ContentType}, {byteSize(defaultSettingsEntity.freeField18)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="freeField19">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField19">Free Field 19</Translate>
            </span>
          </dt>
          <dd>{defaultSettingsEntity.freeField19}</dd>
          <dt>
            <span id="timestamp">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.timestamp">Timestamp</Translate>
            </span>
          </dt>
          <dd>
            {defaultSettingsEntity.timestamp ? (
              <TextFormat value={defaultSettingsEntity.timestamp} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="recordStatus">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.recordStatus">Record Status</Translate>
            </span>
          </dt>
          <dd>{defaultSettingsEntity.recordStatus}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {defaultSettingsEntity.createdAt ? (
              <TextFormat value={defaultSettingsEntity.createdAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{defaultSettingsEntity.createdBy}</dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {defaultSettingsEntity.updatedAt ? (
              <TextFormat value={defaultSettingsEntity.updatedAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedby">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.updatedby">Updatedby</Translate>
            </span>
          </dt>
          <dd>{defaultSettingsEntity.updatedby}</dd>
        </dl>
        <Button tag={Link} to="/default-settings" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/default-settings/${defaultSettingsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DefaultSettingsDetail;
