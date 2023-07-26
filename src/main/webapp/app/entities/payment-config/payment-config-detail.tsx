import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, openFile, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './payment-config.reducer';

export const PaymentConfigDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const paymentConfigEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.paymentConfig.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="paymentConfigDetailsHeading">
          <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.detail.title">PaymentConfig</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{paymentConfigEntity.id}</dd>
          <dt>
            <span id="recordId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.recordId">Record Id</Translate>
            </span>
          </dt>
          <dd>{paymentConfigEntity.recordId}</dd>
          <dt>
            <span id="paymentId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.paymentId">Payment Id</Translate>
            </span>
          </dt>
          <dd>{paymentConfigEntity.paymentId}</dd>
          <dt>
            <span id="paymentName">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.paymentName">Payment Name</Translate>
            </span>
          </dt>
          <dd>{paymentConfigEntity.paymentName}</dd>
          <dt>
            <span id="paymentType">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.paymentType">Payment Type</Translate>
            </span>
          </dt>
          <dd>{paymentConfigEntity.paymentType}</dd>
          <dt>
            <span id="paymentDescription">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.paymentDescription">Payment Description</Translate>
            </span>
          </dt>
          <dd>{paymentConfigEntity.paymentDescription}</dd>
          <dt>
            <span id="paymentStatus">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.paymentStatus">Payment Status</Translate>
            </span>
          </dt>
          <dd>{paymentConfigEntity.paymentStatus}</dd>
          <dt>
            <span id="paymentMethod">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.paymentMethod">Payment Method</Translate>
            </span>
          </dt>
          <dd>{paymentConfigEntity.paymentMethod}</dd>
          <dt>
            <span id="paymentAmount">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.paymentAmount">Payment Amount</Translate>
            </span>
          </dt>
          <dd>{paymentConfigEntity.paymentAmount}</dd>
          <dt>
            <span id="additionalConfig">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.additionalConfig">Additional Config</Translate>
            </span>
          </dt>
          <dd>{paymentConfigEntity.additionalConfig}</dd>
          <dt>
            <span id="freeField1">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField1">Free Field 1</Translate>
            </span>
          </dt>
          <dd>{paymentConfigEntity.freeField1}</dd>
          <dt>
            <span id="freeField2">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField2">Free Field 2</Translate>
            </span>
          </dt>
          <dd>{paymentConfigEntity.freeField2}</dd>
          <dt>
            <span id="freeField3">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField3">Free Field 3</Translate>
            </span>
          </dt>
          <dd>{paymentConfigEntity.freeField3}</dd>
          <dt>
            <span id="freeField4">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField4">Free Field 4</Translate>
            </span>
          </dt>
          <dd>{paymentConfigEntity.freeField4}</dd>
          <dt>
            <span id="freeField5">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField5">Free Field 5</Translate>
            </span>
          </dt>
          <dd>{paymentConfigEntity.freeField5}</dd>
          <dt>
            <span id="freeField6">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField6">Free Field 6</Translate>
            </span>
          </dt>
          <dd>{paymentConfigEntity.freeField6}</dd>
          <dt>
            <span id="freeField8">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField8">Free Field 8</Translate>
            </span>
          </dt>
          <dd>{paymentConfigEntity.freeField8}</dd>
          <dt>
            <span id="freeField9">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField9">Free Field 9</Translate>
            </span>
          </dt>
          <dd>{paymentConfigEntity.freeField9}</dd>
          <dt>
            <span id="freeField10">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField10">Free Field 10</Translate>
            </span>
          </dt>
          <dd>{paymentConfigEntity.freeField10}</dd>
          <dt>
            <span id="freeField11">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField11">Free Field 11</Translate>
            </span>
          </dt>
          <dd>{paymentConfigEntity.freeField11}</dd>
          <dt>
            <span id="freeField12">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField12">Free Field 12</Translate>
            </span>
          </dt>
          <dd>{paymentConfigEntity.freeField12}</dd>
          <dt>
            <span id="freeField13">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField13">Free Field 13</Translate>
            </span>
          </dt>
          <dd>{paymentConfigEntity.freeField13}</dd>
          <dt>
            <span id="freeField14">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField14">Free Field 14</Translate>
            </span>
          </dt>
          <dd>{paymentConfigEntity.freeField14}</dd>
          <dt>
            <span id="freeField15">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField15">Free Field 15</Translate>
            </span>
          </dt>
          <dd>
            {paymentConfigEntity.freeField15 ? (
              <div>
                {paymentConfigEntity.freeField15ContentType ? (
                  <a onClick={openFile(paymentConfigEntity.freeField15ContentType, paymentConfigEntity.freeField15)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                ) : null}
                <span>
                  {paymentConfigEntity.freeField15ContentType}, {byteSize(paymentConfigEntity.freeField15)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="freeField16">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField16">Free Field 16</Translate>
            </span>
          </dt>
          <dd>{paymentConfigEntity.freeField16}</dd>
          <dt>
            <span id="freeField17">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField17">Free Field 17</Translate>
            </span>
          </dt>
          <dd>{paymentConfigEntity.freeField17}</dd>
          <dt>
            <span id="freeField18">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField18">Free Field 18</Translate>
            </span>
          </dt>
          <dd>
            {paymentConfigEntity.freeField18 ? (
              <div>
                {paymentConfigEntity.freeField18ContentType ? (
                  <a onClick={openFile(paymentConfigEntity.freeField18ContentType, paymentConfigEntity.freeField18)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                ) : null}
                <span>
                  {paymentConfigEntity.freeField18ContentType}, {byteSize(paymentConfigEntity.freeField18)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="freeField19">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField19">Free Field 19</Translate>
            </span>
          </dt>
          <dd>{paymentConfigEntity.freeField19}</dd>
          <dt>
            <span id="freeField20">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField20">Free Field 20</Translate>
            </span>
          </dt>
          <dd>{paymentConfigEntity.freeField20}</dd>
          <dt>
            <span id="timestamp">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.timestamp">Timestamp</Translate>
            </span>
          </dt>
          <dd>
            {paymentConfigEntity.timestamp ? (
              <TextFormat value={paymentConfigEntity.timestamp} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {paymentConfigEntity.createdAt ? (
              <TextFormat value={paymentConfigEntity.createdAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{paymentConfigEntity.createdBy}</dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {paymentConfigEntity.updatedAt ? (
              <TextFormat value={paymentConfigEntity.updatedAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{paymentConfigEntity.updatedBy}</dd>
        </dl>
        <Button tag={Link} to="/payment-config" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/payment-config/${paymentConfigEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default PaymentConfigDetail;
