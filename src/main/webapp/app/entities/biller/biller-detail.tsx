import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './biller.reducer';

export const BillerDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const billerEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.biller.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="billerDetailsHeading">
          <Translate contentKey="absaUgdtMicrosrvcGatewayApp.biller.detail.title">Biller</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{billerEntity.id}</dd>
          <dt>
            <span id="absaTranRef">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.biller.absaTranRef">Absa Tran Ref</Translate>
            </span>
          </dt>
          <dd>{billerEntity.absaTranRef}</dd>
          <dt>
            <span id="billerId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.biller.billerId">Biller Id</Translate>
            </span>
          </dt>
          <dd>{billerEntity.billerId}</dd>
          <dt>
            <span id="billerCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.biller.billerCode">Biller Code</Translate>
            </span>
          </dt>
          <dd>{billerEntity.billerCode}</dd>
          <dt>
            <span id="billerName">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.biller.billerName">Biller Name</Translate>
            </span>
          </dt>
          <dd>{billerEntity.billerName}</dd>
          <dt>
            <span id="billerCategoryId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.biller.billerCategoryId">Biller Category Id</Translate>
            </span>
          </dt>
          <dd>{billerEntity.billerCategoryId}</dd>
          <dt>
            <span id="addressId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.biller.addressId">Address Id</Translate>
            </span>
          </dt>
          <dd>{billerEntity.addressId}</dd>
          <dt>
            <span id="narative">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.biller.narative">Narative</Translate>
            </span>
          </dt>
          <dd>{billerEntity.narative}</dd>
          <dt>
            <span id="narative1">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.biller.narative1">Narative 1</Translate>
            </span>
          </dt>
          <dd>{billerEntity.narative1}</dd>
          <dt>
            <span id="narative2">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.biller.narative2">Narative 2</Translate>
            </span>
          </dt>
          <dd>{billerEntity.narative2}</dd>
          <dt>
            <span id="narative3">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.biller.narative3">Narative 3</Translate>
            </span>
          </dt>
          <dd>{billerEntity.narative3}</dd>
          <dt>
            <span id="narative4">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.biller.narative4">Narative 4</Translate>
            </span>
          </dt>
          <dd>{billerEntity.narative4}</dd>
          <dt>
            <span id="narative5">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.biller.narative5">Narative 5</Translate>
            </span>
          </dt>
          <dd>{billerEntity.narative5}</dd>
          <dt>
            <span id="narative6">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.biller.narative6">Narative 6</Translate>
            </span>
          </dt>
          <dd>{billerEntity.narative6}</dd>
          <dt>
            <span id="narative7">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.biller.narative7">Narative 7</Translate>
            </span>
          </dt>
          <dd>{billerEntity.narative7}</dd>
          <dt>
            <span id="timestamp">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.biller.timestamp">Timestamp</Translate>
            </span>
          </dt>
          <dd>{billerEntity.timestamp ? <TextFormat value={billerEntity.timestamp} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="freeField1">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.biller.freeField1">Free Field 1</Translate>
            </span>
          </dt>
          <dd>{billerEntity.freeField1}</dd>
          <dt>
            <span id="freeField2">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.biller.freeField2">Free Field 2</Translate>
            </span>
          </dt>
          <dd>{billerEntity.freeField2}</dd>
          <dt>
            <span id="freeField3">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.biller.freeField3">Free Field 3</Translate>
            </span>
          </dt>
          <dd>{billerEntity.freeField3}</dd>
          <dt>
            <span id="freeField4">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.biller.freeField4">Free Field 4</Translate>
            </span>
          </dt>
          <dd>{billerEntity.freeField4}</dd>
          <dt>
            <span id="freeField5">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.biller.freeField5">Free Field 5</Translate>
            </span>
          </dt>
          <dd>{billerEntity.freeField5}</dd>
          <dt>
            <span id="freeField6">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.biller.freeField6">Free Field 6</Translate>
            </span>
          </dt>
          <dd>{billerEntity.freeField6}</dd>
          <dt>
            <span id="freeField7">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.biller.freeField7">Free Field 7</Translate>
            </span>
          </dt>
          <dd>{billerEntity.freeField7}</dd>
          <dt>
            <span id="freeField8">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.biller.freeField8">Free Field 8</Translate>
            </span>
          </dt>
          <dd>{billerEntity.freeField8}</dd>
          <dt>
            <span id="freeField9">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.biller.freeField9">Free Field 9</Translate>
            </span>
          </dt>
          <dd>{billerEntity.freeField9}</dd>
          <dt>
            <span id="freeField10">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.biller.freeField10">Free Field 10</Translate>
            </span>
          </dt>
          <dd>{billerEntity.freeField10}</dd>
          <dt>
            <span id="freeField11">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.biller.freeField11">Free Field 11</Translate>
            </span>
          </dt>
          <dd>{billerEntity.freeField11}</dd>
          <dt>
            <span id="freeField12">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.biller.freeField12">Free Field 12</Translate>
            </span>
          </dt>
          <dd>{billerEntity.freeField12}</dd>
          <dt>
            <span id="freeField13">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.biller.freeField13">Free Field 13</Translate>
            </span>
          </dt>
          <dd>{billerEntity.freeField13}</dd>
          <dt>
            <span id="delflg">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.biller.delflg">Delflg</Translate>
            </span>
          </dt>
          <dd>{billerEntity.delflg ? 'true' : 'false'}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.biller.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>{billerEntity.createdAt ? <TextFormat value={billerEntity.createdAt} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.biller.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{billerEntity.createdBy}</dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.biller.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>{billerEntity.updatedAt ? <TextFormat value={billerEntity.updatedAt} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.biller.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{billerEntity.updatedBy}</dd>
          <dt>
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.biller.billerAccount">Biller Account</Translate>
          </dt>
          <dd>{billerEntity.billerAccount ? billerEntity.billerAccount.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/biller" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/biller/${billerEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default BillerDetail;
