import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './currency.reducer';

export const CurrencyDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const currencyEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.currency.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="currencyDetailsHeading">
          <Translate contentKey="absaUgdtMicrosrvcGatewayApp.currency.detail.title">Currency</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{currencyEntity.id}</dd>
          <dt>
            <span id="absaTranRef">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.currency.absaTranRef">Absa Tran Ref</Translate>
            </span>
          </dt>
          <dd>{currencyEntity.absaTranRef}</dd>
          <dt>
            <span id="dtDTransactionId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.currency.dtDTransactionId">Dt D Transaction Id</Translate>
            </span>
          </dt>
          <dd>{currencyEntity.dtDTransactionId}</dd>
          <dt>
            <span id="amolDTransactionId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.currency.amolDTransactionId">Amol D Transaction Id</Translate>
            </span>
          </dt>
          <dd>{currencyEntity.amolDTransactionId}</dd>
          <dt>
            <span id="transactionReferenceNumber">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.currency.transactionReferenceNumber">
                Transaction Reference Number
              </Translate>
            </span>
          </dt>
          <dd>{currencyEntity.transactionReferenceNumber}</dd>
          <dt>
            <span id="token">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.currency.token">Token</Translate>
            </span>
          </dt>
          <dd>{currencyEntity.token}</dd>
          <dt>
            <span id="currencyUniqueId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.currency.currencyUniqueId">Currency Unique Id</Translate>
            </span>
          </dt>
          <dd>{currencyEntity.currencyUniqueId}</dd>
          <dt>
            <span id="currencyCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.currency.currencyCode">Currency Code</Translate>
            </span>
          </dt>
          <dd>{currencyEntity.currencyCode}</dd>
          <dt>
            <span id="currencyName">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.currency.currencyName">Currency Name</Translate>
            </span>
          </dt>
          <dd>{currencyEntity.currencyName}</dd>
          <dt>
            <span id="freeField1">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.currency.freeField1">Free Field 1</Translate>
            </span>
          </dt>
          <dd>{currencyEntity.freeField1}</dd>
          <dt>
            <span id="freeField2">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.currency.freeField2">Free Field 2</Translate>
            </span>
          </dt>
          <dd>{currencyEntity.freeField2}</dd>
          <dt>
            <span id="freeField3">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.currency.freeField3">Free Field 3</Translate>
            </span>
          </dt>
          <dd>{currencyEntity.freeField3}</dd>
          <dt>
            <span id="freeField4">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.currency.freeField4">Free Field 4</Translate>
            </span>
          </dt>
          <dd>{currencyEntity.freeField4}</dd>
          <dt>
            <span id="freeField5">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.currency.freeField5">Free Field 5</Translate>
            </span>
          </dt>
          <dd>{currencyEntity.freeField5}</dd>
          <dt>
            <span id="freeField6">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.currency.freeField6">Free Field 6</Translate>
            </span>
          </dt>
          <dd>{currencyEntity.freeField6}</dd>
          <dt>
            <span id="timestamp">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.currency.timestamp">Timestamp</Translate>
            </span>
          </dt>
          <dd>{currencyEntity.timestamp ? <TextFormat value={currencyEntity.timestamp} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="recordStatus">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.currency.recordStatus">Record Status</Translate>
            </span>
          </dt>
          <dd>{currencyEntity.recordStatus}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.currency.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>{currencyEntity.createdAt ? <TextFormat value={currencyEntity.createdAt} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.currency.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{currencyEntity.createdBy}</dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.currency.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>{currencyEntity.updatedAt ? <TextFormat value={currencyEntity.updatedAt} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.currency.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{currencyEntity.updatedBy}</dd>
        </dl>
        <Button tag={Link} to="/currency" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/currency/${currencyEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CurrencyDetail;
