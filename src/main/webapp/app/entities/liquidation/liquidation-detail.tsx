import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './liquidation.reducer';

export const LiquidationDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const liquidationEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.liquidation.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="liquidationDetailsHeading">
          <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.detail.title">Liquidation</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{liquidationEntity.id}</dd>
          <dt>
            <span id="recordId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.recordId">Record Id</Translate>
            </span>
          </dt>
          <dd>{liquidationEntity.recordId}</dd>
          <dt>
            <span id="serviceLevel">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.serviceLevel">Service Level</Translate>
            </span>
          </dt>
          <dd>{liquidationEntity.serviceLevel}</dd>
          <dt>
            <span id="timestamp">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.timestamp">Timestamp</Translate>
            </span>
          </dt>
          <dd>
            {liquidationEntity.timestamp ? <TextFormat value={liquidationEntity.timestamp} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="partnerCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.partnerCode">Partner Code</Translate>
            </span>
          </dt>
          <dd>{liquidationEntity.partnerCode}</dd>
          <dt>
            <span id="amount">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.amount">Amount</Translate>
            </span>
          </dt>
          <dd>{liquidationEntity.amount}</dd>
          <dt>
            <span id="currency">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.currency">Currency</Translate>
            </span>
          </dt>
          <dd>{liquidationEntity.currency}</dd>
          <dt>
            <span id="receiverBankcode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.receiverBankcode">Receiver Bankcode</Translate>
            </span>
          </dt>
          <dd>{liquidationEntity.receiverBankcode}</dd>
          <dt>
            <span id="receiverAccountNumber">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.receiverAccountNumber">Receiver Account Number</Translate>
            </span>
          </dt>
          <dd>{liquidationEntity.receiverAccountNumber}</dd>
          <dt>
            <span id="beneficiaryName">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.beneficiaryName">Beneficiary Name</Translate>
            </span>
          </dt>
          <dd>{liquidationEntity.beneficiaryName}</dd>
          <dt>
            <span id="instructionId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.instructionId">Instruction Id</Translate>
            </span>
          </dt>
          <dd>{liquidationEntity.instructionId}</dd>
          <dt>
            <span id="senderToReceiverInfo">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.senderToReceiverInfo">Sender To Receiver Info</Translate>
            </span>
          </dt>
          <dd>{liquidationEntity.senderToReceiverInfo}</dd>
          <dt>
            <span id="freeText1">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText1">Free Text 1</Translate>
            </span>
          </dt>
          <dd>{liquidationEntity.freeText1}</dd>
          <dt>
            <span id="freeText2">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText2">Free Text 2</Translate>
            </span>
          </dt>
          <dd>{liquidationEntity.freeText2}</dd>
          <dt>
            <span id="freeText3">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText3">Free Text 3</Translate>
            </span>
          </dt>
          <dd>{liquidationEntity.freeText3}</dd>
          <dt>
            <span id="freeText4">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText4">Free Text 4</Translate>
            </span>
          </dt>
          <dd>{liquidationEntity.freeText4}</dd>
          <dt>
            <span id="freeText5">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText5">Free Text 5</Translate>
            </span>
          </dt>
          <dd>{liquidationEntity.freeText5}</dd>
          <dt>
            <span id="freeText6">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText6">Free Text 6</Translate>
            </span>
          </dt>
          <dd>{liquidationEntity.freeText6}</dd>
          <dt>
            <span id="freeText7">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText7">Free Text 7</Translate>
            </span>
          </dt>
          <dd>{liquidationEntity.freeText7}</dd>
          <dt>
            <span id="freeText8">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText8">Free Text 8</Translate>
            </span>
          </dt>
          <dd>{liquidationEntity.freeText8}</dd>
          <dt>
            <span id="freeText9">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText9">Free Text 9</Translate>
            </span>
          </dt>
          <dd>{liquidationEntity.freeText9}</dd>
          <dt>
            <span id="freeText10">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText10">Free Text 10</Translate>
            </span>
          </dt>
          <dd>{liquidationEntity.freeText10}</dd>
          <dt>
            <span id="freeText11">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText11">Free Text 11</Translate>
            </span>
          </dt>
          <dd>{liquidationEntity.freeText11}</dd>
          <dt>
            <span id="freeText12">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText12">Free Text 12</Translate>
            </span>
          </dt>
          <dd>{liquidationEntity.freeText12}</dd>
          <dt>
            <span id="freeText13">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText13">Free Text 13</Translate>
            </span>
          </dt>
          <dd>{liquidationEntity.freeText13}</dd>
          <dt>
            <span id="freeText14">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText14">Free Text 14</Translate>
            </span>
          </dt>
          <dd>{liquidationEntity.freeText14}</dd>
          <dt>
            <span id="freeText15">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText15">Free Text 15</Translate>
            </span>
          </dt>
          <dd>{liquidationEntity.freeText15}</dd>
          <dt>
            <span id="freeText16">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText16">Free Text 16</Translate>
            </span>
          </dt>
          <dd>{liquidationEntity.freeText16}</dd>
          <dt>
            <span id="freeText17">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText17">Free Text 17</Translate>
            </span>
          </dt>
          <dd>{liquidationEntity.freeText17}</dd>
          <dt>
            <span id="freeText18">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText18">Free Text 18</Translate>
            </span>
          </dt>
          <dd>{liquidationEntity.freeText18}</dd>
          <dt>
            <span id="freeText19">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText19">Free Text 19</Translate>
            </span>
          </dt>
          <dd>{liquidationEntity.freeText19}</dd>
          <dt>
            <span id="freeText20">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText20">Free Text 20</Translate>
            </span>
          </dt>
          <dd>{liquidationEntity.freeText20}</dd>
          <dt>
            <span id="freeText21">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText21">Free Text 21</Translate>
            </span>
          </dt>
          <dd>{liquidationEntity.freeText21}</dd>
          <dt>
            <span id="freeText22">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText22">Free Text 22</Translate>
            </span>
          </dt>
          <dd>{liquidationEntity.freeText22}</dd>
          <dt>
            <span id="freeText23">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText23">Free Text 23</Translate>
            </span>
          </dt>
          <dd>{liquidationEntity.freeText23}</dd>
          <dt>
            <span id="freeText24">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText24">Free Text 24</Translate>
            </span>
          </dt>
          <dd>{liquidationEntity.freeText24}</dd>
          <dt>
            <span id="freeText25">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText25">Free Text 25</Translate>
            </span>
          </dt>
          <dd>{liquidationEntity.freeText25}</dd>
          <dt>
            <span id="freeText26">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText26">Free Text 26</Translate>
            </span>
          </dt>
          <dd>{liquidationEntity.freeText26}</dd>
          <dt>
            <span id="freeText27">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText27">Free Text 27</Translate>
            </span>
          </dt>
          <dd>{liquidationEntity.freeText27}</dd>
          <dt>
            <span id="freeText28">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText28">Free Text 28</Translate>
            </span>
          </dt>
          <dd>{liquidationEntity.freeText28}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {liquidationEntity.createdAt ? <TextFormat value={liquidationEntity.createdAt} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{liquidationEntity.createdBy}</dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {liquidationEntity.updatedAt ? <TextFormat value={liquidationEntity.updatedAt} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{liquidationEntity.updatedBy}</dd>
        </dl>
        <Button tag={Link} to="/liquidation" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/liquidation/${liquidationEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default LiquidationDetail;
