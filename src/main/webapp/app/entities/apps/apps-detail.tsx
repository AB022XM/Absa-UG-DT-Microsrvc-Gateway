import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './apps.reducer';

export const AppsDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const appsEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.apps.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="appsDetailsHeading">
          <Translate contentKey="absaUgdtMicrosrvcGatewayApp.apps.detail.title">Apps</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{appsEntity.id}</dd>
          <dt>
            <span id="recordId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.apps.recordId">Record Id</Translate>
            </span>
          </dt>
          <dd>{appsEntity.recordId}</dd>
          <dt>
            <span id="configId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.apps.configId">Config Id</Translate>
            </span>
          </dt>
          <dd>{appsEntity.configId}</dd>
          <dt>
            <span id="appId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.apps.appId">App Id</Translate>
            </span>
          </dt>
          <dd>{appsEntity.appId}</dd>
          <dt>
            <span id="appCurrentVersion">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.apps.appCurrentVersion">App Current Version</Translate>
            </span>
          </dt>
          <dd>{appsEntity.appCurrentVersion}</dd>
          <dt>
            <span id="appName">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.apps.appName">App Name</Translate>
            </span>
          </dt>
          <dd>{appsEntity.appName}</dd>
          <dt>
            <span id="appDescription">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.apps.appDescription">App Description</Translate>
            </span>
          </dt>
          <dd>{appsEntity.appDescription}</dd>
          <dt>
            <span id="appStatus">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.apps.appStatus">App Status</Translate>
            </span>
          </dt>
          <dd>{appsEntity.appStatus}</dd>
          <dt>
            <span id="freeField1">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.apps.freeField1">Free Field 1</Translate>
            </span>
          </dt>
          <dd>{appsEntity.freeField1}</dd>
          <dt>
            <span id="freeField2">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.apps.freeField2">Free Field 2</Translate>
            </span>
          </dt>
          <dd>{appsEntity.freeField2}</dd>
          <dt>
            <span id="freeField3">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.apps.freeField3">Free Field 3</Translate>
            </span>
          </dt>
          <dd>{appsEntity.freeField3}</dd>
          <dt>
            <span id="freeField4">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.apps.freeField4">Free Field 4</Translate>
            </span>
          </dt>
          <dd>{appsEntity.freeField4}</dd>
          <dt>
            <span id="freeField5">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.apps.freeField5">Free Field 5</Translate>
            </span>
          </dt>
          <dd>{appsEntity.freeField5}</dd>
          <dt>
            <span id="freeField6">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.apps.freeField6">Free Field 6</Translate>
            </span>
          </dt>
          <dd>{appsEntity.freeField6}</dd>
          <dt>
            <span id="freeField7">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.apps.freeField7">Free Field 7</Translate>
            </span>
          </dt>
          <dd>{appsEntity.freeField7}</dd>
          <dt>
            <span id="freeField8">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.apps.freeField8">Free Field 8</Translate>
            </span>
          </dt>
          <dd>{appsEntity.freeField8}</dd>
          <dt>
            <span id="freeField9">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.apps.freeField9">Free Field 9</Translate>
            </span>
          </dt>
          <dd>{appsEntity.freeField9}</dd>
          <dt>
            <span id="freeField10">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.apps.freeField10">Free Field 10</Translate>
            </span>
          </dt>
          <dd>{appsEntity.freeField10}</dd>
          <dt>
            <span id="freeField11">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.apps.freeField11">Free Field 11</Translate>
            </span>
          </dt>
          <dd>{appsEntity.freeField11}</dd>
          <dt>
            <span id="freeField12">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.apps.freeField12">Free Field 12</Translate>
            </span>
          </dt>
          <dd>{appsEntity.freeField12}</dd>
          <dt>
            <span id="freeField13">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.apps.freeField13">Free Field 13</Translate>
            </span>
          </dt>
          <dd>{appsEntity.freeField13}</dd>
          <dt>
            <span id="delflg">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.apps.delflg">Delflg</Translate>
            </span>
          </dt>
          <dd>{appsEntity.delflg ? 'true' : 'false'}</dd>
          <dt>
            <span id="timestamp">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.apps.timestamp">Timestamp</Translate>
            </span>
          </dt>
          <dd>{appsEntity.timestamp ? <TextFormat value={appsEntity.timestamp} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.apps.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>{appsEntity.createdAt ? <TextFormat value={appsEntity.createdAt} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.apps.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{appsEntity.createdBy}</dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.apps.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>{appsEntity.updatedAt ? <TextFormat value={appsEntity.updatedAt} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="updatedby">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.apps.updatedby">Updatedby</Translate>
            </span>
          </dt>
          <dd>{appsEntity.updatedby}</dd>
        </dl>
        <Button tag={Link} to="/apps" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/apps/${appsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default AppsDetail;
