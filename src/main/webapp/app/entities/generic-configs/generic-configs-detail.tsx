import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, openFile, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './generic-configs.reducer';

export const GenericConfigsDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const genericConfigsEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.genericConfigs.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="genericConfigsDetailsHeading">
          <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.detail.title">GenericConfigs</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{genericConfigsEntity.id}</dd>
          <dt>
            <span id="absaTranRef">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.absaTranRef">Absa Tran Ref</Translate>
            </span>
          </dt>
          <dd>{genericConfigsEntity.absaTranRef}</dd>
          <dt>
            <span id="recordId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.recordId">Record Id</Translate>
            </span>
          </dt>
          <dd>{genericConfigsEntity.recordId}</dd>
          <dt>
            <span id="configId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.configId">Config Id</Translate>
            </span>
          </dt>
          <dd>{genericConfigsEntity.configId}</dd>
          <dt>
            <span id="configName">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.configName">Config Name</Translate>
            </span>
          </dt>
          <dd>{genericConfigsEntity.configName}</dd>
          <dt>
            <span id="configsDetails">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.configsDetails">Configs Details</Translate>
            </span>
          </dt>
          <dd>{genericConfigsEntity.configsDetails}</dd>
          <dt>
            <span id="additionalConfigs">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.additionalConfigs">Additional Configs</Translate>
            </span>
          </dt>
          <dd>{genericConfigsEntity.additionalConfigs}</dd>
          <dt>
            <span id="freeField">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField">Free Field</Translate>
            </span>
          </dt>
          <dd>{genericConfigsEntity.freeField ? 'true' : 'false'}</dd>
          <dt>
            <span id="freeField1">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField1">Free Field 1</Translate>
            </span>
          </dt>
          <dd>{genericConfigsEntity.freeField1 ? 'true' : 'false'}</dd>
          <dt>
            <span id="freeField2">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField2">Free Field 2</Translate>
            </span>
          </dt>
          <dd>{genericConfigsEntity.freeField2}</dd>
          <dt>
            <span id="freeField3">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField3">Free Field 3</Translate>
            </span>
          </dt>
          <dd>{genericConfigsEntity.freeField3}</dd>
          <dt>
            <span id="freeField4">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField4">Free Field 4</Translate>
            </span>
          </dt>
          <dd>{genericConfigsEntity.freeField4}</dd>
          <dt>
            <span id="freeField5">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField5">Free Field 5</Translate>
            </span>
          </dt>
          <dd>{genericConfigsEntity.freeField5}</dd>
          <dt>
            <span id="freeField6">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField6">Free Field 6</Translate>
            </span>
          </dt>
          <dd>{genericConfigsEntity.freeField6}</dd>
          <dt>
            <span id="freeField8">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField8">Free Field 8</Translate>
            </span>
          </dt>
          <dd>{genericConfigsEntity.freeField8}</dd>
          <dt>
            <span id="freeField9">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField9">Free Field 9</Translate>
            </span>
          </dt>
          <dd>{genericConfigsEntity.freeField9}</dd>
          <dt>
            <span id="freeField10">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField10">Free Field 10</Translate>
            </span>
          </dt>
          <dd>{genericConfigsEntity.freeField10}</dd>
          <dt>
            <span id="freeField11">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField11">Free Field 11</Translate>
            </span>
          </dt>
          <dd>{genericConfigsEntity.freeField11}</dd>
          <dt>
            <span id="freeField12">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField12">Free Field 12</Translate>
            </span>
          </dt>
          <dd>{genericConfigsEntity.freeField12}</dd>
          <dt>
            <span id="freeField13">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField13">Free Field 13</Translate>
            </span>
          </dt>
          <dd>{genericConfigsEntity.freeField13}</dd>
          <dt>
            <span id="freeField14">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField14">Free Field 14</Translate>
            </span>
          </dt>
          <dd>{genericConfigsEntity.freeField14}</dd>
          <dt>
            <span id="freeField15">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField15">Free Field 15</Translate>
            </span>
          </dt>
          <dd>
            {genericConfigsEntity.freeField15 ? (
              <div>
                {genericConfigsEntity.freeField15ContentType ? (
                  <a onClick={openFile(genericConfigsEntity.freeField15ContentType, genericConfigsEntity.freeField15)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                ) : null}
                <span>
                  {genericConfigsEntity.freeField15ContentType}, {byteSize(genericConfigsEntity.freeField15)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="freeField16">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField16">Free Field 16</Translate>
            </span>
          </dt>
          <dd>{genericConfigsEntity.freeField16}</dd>
          <dt>
            <span id="freeField17">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField17">Free Field 17</Translate>
            </span>
          </dt>
          <dd>{genericConfigsEntity.freeField17}</dd>
          <dt>
            <span id="freeField18">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField18">Free Field 18</Translate>
            </span>
          </dt>
          <dd>
            {genericConfigsEntity.freeField18 ? (
              <div>
                {genericConfigsEntity.freeField18ContentType ? (
                  <a onClick={openFile(genericConfigsEntity.freeField18ContentType, genericConfigsEntity.freeField18)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                ) : null}
                <span>
                  {genericConfigsEntity.freeField18ContentType}, {byteSize(genericConfigsEntity.freeField18)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="freeField19">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField19">Free Field 19</Translate>
            </span>
          </dt>
          <dd>{genericConfigsEntity.freeField19}</dd>
          <dt>
            <span id="freeField20">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField20">Free Field 20</Translate>
            </span>
          </dt>
          <dd>{genericConfigsEntity.freeField20}</dd>
          <dt>
            <span id="freeField21">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField21">Free Field 21</Translate>
            </span>
          </dt>
          <dd>{genericConfigsEntity.freeField21}</dd>
          <dt>
            <span id="freeField22">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField22">Free Field 22</Translate>
            </span>
          </dt>
          <dd>{genericConfigsEntity.freeField22}</dd>
          <dt>
            <span id="freeField23">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField23">Free Field 23</Translate>
            </span>
          </dt>
          <dd>{genericConfigsEntity.freeField23}</dd>
          <dt>
            <span id="freeField24">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField24">Free Field 24</Translate>
            </span>
          </dt>
          <dd>{genericConfigsEntity.freeField24}</dd>
          <dt>
            <span id="freeField25">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField25">Free Field 25</Translate>
            </span>
          </dt>
          <dd>
            {genericConfigsEntity.freeField25 ? (
              <div>
                {genericConfigsEntity.freeField25ContentType ? (
                  <a onClick={openFile(genericConfigsEntity.freeField25ContentType, genericConfigsEntity.freeField25)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                ) : null}
                <span>
                  {genericConfigsEntity.freeField25ContentType}, {byteSize(genericConfigsEntity.freeField25)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="freeField26">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField26">Free Field 26</Translate>
            </span>
          </dt>
          <dd>{genericConfigsEntity.freeField26}</dd>
          <dt>
            <span id="freeField27">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField27">Free Field 27</Translate>
            </span>
          </dt>
          <dd>{genericConfigsEntity.freeField27}</dd>
          <dt>
            <span id="freeField28">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField28">Free Field 28</Translate>
            </span>
          </dt>
          <dd>
            {genericConfigsEntity.freeField28 ? (
              <div>
                {genericConfigsEntity.freeField28ContentType ? (
                  <a onClick={openFile(genericConfigsEntity.freeField28ContentType, genericConfigsEntity.freeField28)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                ) : null}
                <span>
                  {genericConfigsEntity.freeField28ContentType}, {byteSize(genericConfigsEntity.freeField28)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="freeField29">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField29">Free Field 29</Translate>
            </span>
          </dt>
          <dd>{genericConfigsEntity.freeField29}</dd>
          <dt>
            <span id="freeField30">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField30">Free Field 30</Translate>
            </span>
          </dt>
          <dd>{genericConfigsEntity.freeField30}</dd>
          <dt>
            <span id="freeField31">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField31">Free Field 31</Translate>
            </span>
          </dt>
          <dd>{genericConfigsEntity.freeField31}</dd>
          <dt>
            <span id="freeField32">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField32">Free Field 32</Translate>
            </span>
          </dt>
          <dd>{genericConfigsEntity.freeField32}</dd>
          <dt>
            <span id="freeField33">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField33">Free Field 33</Translate>
            </span>
          </dt>
          <dd>{genericConfigsEntity.freeField33}</dd>
          <dt>
            <span id="freeField34">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField34">Free Field 34</Translate>
            </span>
          </dt>
          <dd>{genericConfigsEntity.freeField34}</dd>
          <dt>
            <span id="timestamp">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.timestamp">Timestamp</Translate>
            </span>
          </dt>
          <dd>
            {genericConfigsEntity.timestamp ? (
              <TextFormat value={genericConfigsEntity.timestamp} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="recordStatus">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.recordStatus">Record Status</Translate>
            </span>
          </dt>
          <dd>{genericConfigsEntity.recordStatus}</dd>
        </dl>
        <Button tag={Link} to="/generic-configs" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/generic-configs/${genericConfigsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default GenericConfigsDetail;
