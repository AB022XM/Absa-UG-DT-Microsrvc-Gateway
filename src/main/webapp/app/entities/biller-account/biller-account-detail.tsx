import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './biller-account.reducer';

export const BillerAccountDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const billerAccountEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.billerAccount.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="billerAccountDetailsHeading">
          <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.detail.title">BillerAccount</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{billerAccountEntity.id}</dd>
          <dt>
            <span id="absaTranRef">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.absaTranRef">Absa Tran Ref</Translate>
            </span>
          </dt>
          <dd>{billerAccountEntity.absaTranRef}</dd>
          <dt>
            <span id="recordId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.recordId">Record Id</Translate>
            </span>
          </dt>
          <dd>{billerAccountEntity.recordId}</dd>
          <dt>
            <span id="billerId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.billerId">Biller Id</Translate>
            </span>
          </dt>
          <dd>{billerAccountEntity.billerId}</dd>
          <dt>
            <span id="billerName">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.billerName">Biller Name</Translate>
            </span>
          </dt>
          <dd>{billerAccountEntity.billerName}</dd>
          <dt>
            <span id="billerAccNumber">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.billerAccNumber">Biller Acc Number</Translate>
            </span>
          </dt>
          <dd>{billerAccountEntity.billerAccNumber}</dd>
          <dt>
            <span id="billerAccountDescription">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.billerAccountDescription">
                Biller Account Description
              </Translate>
            </span>
          </dt>
          <dd>{billerAccountEntity.billerAccountDescription}</dd>
          <dt>
            <span id="timestamp">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.timestamp">Timestamp</Translate>
            </span>
          </dt>
          <dd>
            {billerAccountEntity.timestamp ? (
              <TextFormat value={billerAccountEntity.timestamp} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="billerFreeField1">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.billerFreeField1">Biller Free Field 1</Translate>
            </span>
          </dt>
          <dd>{billerAccountEntity.billerFreeField1}</dd>
          <dt>
            <span id="billerFreeField2">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.billerFreeField2">Biller Free Field 2</Translate>
            </span>
          </dt>
          <dd>{billerAccountEntity.billerFreeField2}</dd>
          <dt>
            <span id="billerFreeField3">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.billerFreeField3">Biller Free Field 3</Translate>
            </span>
          </dt>
          <dd>{billerAccountEntity.billerFreeField3}</dd>
          <dt>
            <span id="billerFreeField4">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.billerFreeField4">Biller Free Field 4</Translate>
            </span>
          </dt>
          <dd>{billerAccountEntity.billerFreeField4}</dd>
          <dt>
            <span id="billerFreeField5">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.billerFreeField5">Biller Free Field 5</Translate>
            </span>
          </dt>
          <dd>{billerAccountEntity.billerFreeField5}</dd>
          <dt>
            <span id="billerFreeField6">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.billerFreeField6">Biller Free Field 6</Translate>
            </span>
          </dt>
          <dd>{billerAccountEntity.billerFreeField6}</dd>
          <dt>
            <span id="billerFreeField7">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.billerFreeField7">Biller Free Field 7</Translate>
            </span>
          </dt>
          <dd>{billerAccountEntity.billerFreeField7}</dd>
          <dt>
            <span id="billerFreeField8">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.billerFreeField8">Biller Free Field 8</Translate>
            </span>
          </dt>
          <dd>{billerAccountEntity.billerFreeField8}</dd>
          <dt>
            <span id="billerFreeField9">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.billerFreeField9">Biller Free Field 9</Translate>
            </span>
          </dt>
          <dd>{billerAccountEntity.billerFreeField9}</dd>
          <dt>
            <span id="billerFreeField10">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.billerFreeField10">Biller Free Field 10</Translate>
            </span>
          </dt>
          <dd>{billerAccountEntity.billerFreeField10}</dd>
          <dt>
            <span id="billerFreeField11">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.billerFreeField11">Biller Free Field 11</Translate>
            </span>
          </dt>
          <dd>{billerAccountEntity.billerFreeField11}</dd>
          <dt>
            <span id="billerFreeField12">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.billerFreeField12">Biller Free Field 12</Translate>
            </span>
          </dt>
          <dd>{billerAccountEntity.billerFreeField12}</dd>
          <dt>
            <span id="billerFreeField13">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.billerFreeField13">Biller Free Field 13</Translate>
            </span>
          </dt>
          <dd>{billerAccountEntity.billerFreeField13}</dd>
          <dt>
            <span id="delflg">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.delflg">Delflg</Translate>
            </span>
          </dt>
          <dd>{billerAccountEntity.delflg ? 'true' : 'false'}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {billerAccountEntity.createdAt ? (
              <TextFormat value={billerAccountEntity.createdAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{billerAccountEntity.createdBy}</dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {billerAccountEntity.updatedAt ? (
              <TextFormat value={billerAccountEntity.updatedAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{billerAccountEntity.updatedBy}</dd>
          <dt>
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.biller">Biller</Translate>
          </dt>
          <dd>{billerAccountEntity.biller ? billerAccountEntity.biller.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/biller-account" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/biller-account/${billerAccountEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default BillerAccountDetail;
