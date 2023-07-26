import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './blocked-accounts.reducer';

export const BlockedAccountsDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const blockedAccountsEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.blockedAccounts.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="blockedAccountsDetailsHeading">
          <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.detail.title">BlockedAccounts</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{blockedAccountsEntity.id}</dd>
          <dt>
            <span id="absaTranRef">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.absaTranRef">Absa Tran Ref</Translate>
            </span>
          </dt>
          <dd>{blockedAccountsEntity.absaTranRef}</dd>
          <dt>
            <span id="customerId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.customerId">Customer Id</Translate>
            </span>
          </dt>
          <dd>{blockedAccountsEntity.customerId}</dd>
          <dt>
            <span id="customerAccountNumber">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.customerAccountNumber">Customer Account Number</Translate>
            </span>
          </dt>
          <dd>{blockedAccountsEntity.customerAccountNumber}</dd>
          <dt>
            <span id="dtDTransactionId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.dtDTransactionId">Dt D Transaction Id</Translate>
            </span>
          </dt>
          <dd>{blockedAccountsEntity.dtDTransactionId}</dd>
          <dt>
            <span id="blockId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockId">Block Id</Translate>
            </span>
          </dt>
          <dd>{blockedAccountsEntity.blockId}</dd>
          <dt>
            <span id="blockCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockCode">Block Code</Translate>
            </span>
          </dt>
          <dd>{blockedAccountsEntity.blockCode}</dd>
          <dt>
            <span id="blockDate">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockDate">Block Date</Translate>
            </span>
          </dt>
          <dd>
            {blockedAccountsEntity.blockDate ? (
              <TextFormat value={blockedAccountsEntity.blockDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="blockType">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockType">Block Type</Translate>
            </span>
          </dt>
          <dd>{blockedAccountsEntity.blockType}</dd>
          <dt>
            <span id="blockStatus">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockStatus">Block Status</Translate>
            </span>
          </dt>
          <dd>{blockedAccountsEntity.blockStatus}</dd>
          <dt>
            <span id="blockReason">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReason">Block Reason</Translate>
            </span>
          </dt>
          <dd>{blockedAccountsEntity.blockReason}</dd>
          <dt>
            <span id="blockReasonCode1">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode1">Block Reason Code 1</Translate>
            </span>
          </dt>
          <dd>{blockedAccountsEntity.blockReasonCode1}</dd>
          <dt>
            <span id="blockReasonCode2">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode2">Block Reason Code 2</Translate>
            </span>
          </dt>
          <dd>{blockedAccountsEntity.blockReasonCode2}</dd>
          <dt>
            <span id="blockReason1">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReason1">Block Reason 1</Translate>
            </span>
          </dt>
          <dd>{blockedAccountsEntity.blockReason1}</dd>
          <dt>
            <span id="blockReasonCode3">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode3">Block Reason Code 3</Translate>
            </span>
          </dt>
          <dd>{blockedAccountsEntity.blockReasonCode3}</dd>
          <dt>
            <span id="blockReasonCode4">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode4">Block Reason Code 4</Translate>
            </span>
          </dt>
          <dd>{blockedAccountsEntity.blockReasonCode4}</dd>
          <dt>
            <span id="startDate">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.startDate">Start Date</Translate>
            </span>
          </dt>
          <dd>
            {blockedAccountsEntity.startDate ? (
              <TextFormat value={blockedAccountsEntity.startDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="endDate">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.endDate">End Date</Translate>
            </span>
          </dt>
          <dd>
            {blockedAccountsEntity.endDate ? (
              <TextFormat value={blockedAccountsEntity.endDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="isTemp">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.isTemp">Is Temp</Translate>
            </span>
          </dt>
          <dd>{blockedAccountsEntity.isTemp ? 'true' : 'false'}</dd>
          <dt>
            <span id="blockFreeText">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockFreeText">Block Free Text</Translate>
            </span>
          </dt>
          <dd>{blockedAccountsEntity.blockFreeText}</dd>
          <dt>
            <span id="blockFreeText1">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockFreeText1">Block Free Text 1</Translate>
            </span>
          </dt>
          <dd>{blockedAccountsEntity.blockFreeText1}</dd>
          <dt>
            <span id="blockFreeText2">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockFreeText2">Block Free Text 2</Translate>
            </span>
          </dt>
          <dd>{blockedAccountsEntity.blockFreeText2}</dd>
          <dt>
            <span id="blockFreeText3">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockFreeText3">Block Free Text 3</Translate>
            </span>
          </dt>
          <dd>{blockedAccountsEntity.blockFreeText3}</dd>
          <dt>
            <span id="blockFreeText4">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockFreeText4">Block Free Text 4</Translate>
            </span>
          </dt>
          <dd>{blockedAccountsEntity.blockFreeText4}</dd>
          <dt>
            <span id="blockFreeText5">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockFreeText5">Block Free Text 5</Translate>
            </span>
          </dt>
          <dd>{blockedAccountsEntity.blockFreeText5}</dd>
          <dt>
            <span id="blockFreeText6">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockFreeText6">Block Free Text 6</Translate>
            </span>
          </dt>
          <dd>{blockedAccountsEntity.blockFreeText6}</dd>
          <dt>
            <span id="blockReasonCode5">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode5">Block Reason Code 5</Translate>
            </span>
          </dt>
          <dd>{blockedAccountsEntity.blockReasonCode5}</dd>
          <dt>
            <span id="blockReasonCode6">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode6">Block Reason Code 6</Translate>
            </span>
          </dt>
          <dd>{blockedAccountsEntity.blockReasonCode6}</dd>
          <dt>
            <span id="blockReasonCode7">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode7">Block Reason Code 7</Translate>
            </span>
          </dt>
          <dd>{blockedAccountsEntity.blockReasonCode7}</dd>
          <dt>
            <span id="blockReasonCode8">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode8">Block Reason Code 8</Translate>
            </span>
          </dt>
          <dd>{blockedAccountsEntity.blockReasonCode8}</dd>
          <dt>
            <span id="blockReasonCode9">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode9">Block Reason Code 9</Translate>
            </span>
          </dt>
          <dd>{blockedAccountsEntity.blockReasonCode9}</dd>
          <dt>
            <span id="blockReasonCode10">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode10">Block Reason Code 10</Translate>
            </span>
          </dt>
          <dd>{blockedAccountsEntity.blockReasonCode10}</dd>
          <dt>
            <span id="blockReasonCode11">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode11">Block Reason Code 11</Translate>
            </span>
          </dt>
          <dd>{blockedAccountsEntity.blockReasonCode11}</dd>
          <dt>
            <span id="blockReasonCode12">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode12">Block Reason Code 12</Translate>
            </span>
          </dt>
          <dd>{blockedAccountsEntity.blockReasonCode12}</dd>
          <dt>
            <span id="blockReasonCode13">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode13">Block Reason Code 13</Translate>
            </span>
          </dt>
          <dd>{blockedAccountsEntity.blockReasonCode13}</dd>
          <dt>
            <span id="blockReasonCode14">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode14">Block Reason Code 14</Translate>
            </span>
          </dt>
          <dd>{blockedAccountsEntity.blockReasonCode14}</dd>
          <dt>
            <span id="blockReasonCode15">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode15">Block Reason Code 15</Translate>
            </span>
          </dt>
          <dd>{blockedAccountsEntity.blockReasonCode15}</dd>
          <dt>
            <span id="blockReasonCode16">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode16">Block Reason Code 16</Translate>
            </span>
          </dt>
          <dd>{blockedAccountsEntity.blockReasonCode16}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {blockedAccountsEntity.createdAt ? (
              <TextFormat value={blockedAccountsEntity.createdAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{blockedAccountsEntity.createdBy}</dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {blockedAccountsEntity.updatedAt ? (
              <TextFormat value={blockedAccountsEntity.updatedAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{blockedAccountsEntity.updatedBy}</dd>
          <dt>
            <span id="delflg">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.delflg">Delflg</Translate>
            </span>
          </dt>
          <dd>{blockedAccountsEntity.delflg ? 'true' : 'false'}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.status">Status</Translate>
            </span>
          </dt>
          <dd>{blockedAccountsEntity.status}</dd>
        </dl>
        <Button tag={Link} to="/blocked-accounts" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/blocked-accounts/${blockedAccountsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default BlockedAccountsDetail;
