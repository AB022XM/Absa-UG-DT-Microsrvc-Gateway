import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './custom-audit-history.reducer';

export const CustomAuditHistoryDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const customAuditHistoryEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.customAuditHistory.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="customAuditHistoryDetailsHeading">
          <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.detail.title">CustomAuditHistory</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.id">Id</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.id}</dd>
          <dt>
            <span id="recordId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.recordId">Record Id</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.recordId}</dd>
          <dt>
            <span id="actionId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.actionId">Action Id</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.actionId}</dd>
          <dt>
            <span id="timestamp">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.timestamp">Timestamp</Translate>
            </span>
          </dt>
          <dd>
            {customAuditHistoryEntity.timestamp ? (
              <TextFormat value={customAuditHistoryEntity.timestamp} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="oldValue">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.oldValue">Old Value</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.oldValue}</dd>
          <dt>
            <span id="newValue">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.newValue">New Value</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.newValue}</dd>
          <dt>
            <span id="changeReason">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.changeReason">Change Reason</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.changeReason}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.description">Description</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.description}</dd>
          <dt>
            <span id="description1">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.description1">Description 1</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.description1}</dd>
          <dt>
            <span id="description2">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.description2">Description 2</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.description2}</dd>
          <dt>
            <span id="description3">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.description3">Description 3</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.description3}</dd>
          <dt>
            <span id="description4">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.description4">Description 4</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.description4}</dd>
          <dt>
            <span id="description5">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.description5">Description 5</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.description5}</dd>
          <dt>
            <span id="description6">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.description6">Description 6</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.description6}</dd>
          <dt>
            <span id="description7">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.description7">Description 7</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.description7}</dd>
          <dt>
            <span id="description8">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.description8">Description 8</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.description8}</dd>
          <dt>
            <span id="description9">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.description9">Description 9</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.description9}</dd>
          <dt>
            <span id="freeText1">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText1">Free Text 1</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.freeText1}</dd>
          <dt>
            <span id="freeText2">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText2">Free Text 2</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.freeText2}</dd>
          <dt>
            <span id="freeText3">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText3">Free Text 3</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.freeText3}</dd>
          <dt>
            <span id="freeText4">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText4">Free Text 4</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.freeText4}</dd>
          <dt>
            <span id="freeText5">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText5">Free Text 5</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.freeText5}</dd>
          <dt>
            <span id="freeText6">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText6">Free Text 6</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.freeText6}</dd>
          <dt>
            <span id="freeText7">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText7">Free Text 7</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.freeText7}</dd>
          <dt>
            <span id="freeText8">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText8">Free Text 8</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.freeText8}</dd>
          <dt>
            <span id="freeText9">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText9">Free Text 9</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.freeText9}</dd>
          <dt>
            <span id="freeText10">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText10">Free Text 10</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.freeText10}</dd>
          <dt>
            <span id="freeText11">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText11">Free Text 11</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.freeText11}</dd>
          <dt>
            <span id="freeText12">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText12">Free Text 12</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.freeText12}</dd>
          <dt>
            <span id="freeText13">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText13">Free Text 13</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.freeText13}</dd>
          <dt>
            <span id="freeText14">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText14">Free Text 14</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.freeText14}</dd>
          <dt>
            <span id="freeText15">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText15">Free Text 15</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.freeText15}</dd>
          <dt>
            <span id="freeText16">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText16">Free Text 16</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.freeText16}</dd>
          <dt>
            <span id="freeText17">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText17">Free Text 17</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.freeText17}</dd>
          <dt>
            <span id="freeText18">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText18">Free Text 18</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.freeText18}</dd>
          <dt>
            <span id="freeText19">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText19">Free Text 19</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.freeText19}</dd>
          <dt>
            <span id="freeText20">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText20">Free Text 20</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.freeText20}</dd>
          <dt>
            <span id="freeText21">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText21">Free Text 21</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.freeText21}</dd>
          <dt>
            <span id="freeText22">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText22">Free Text 22</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.freeText22}</dd>
          <dt>
            <span id="freeText23">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText23">Free Text 23</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.freeText23}</dd>
          <dt>
            <span id="freeText24">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText24">Free Text 24</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.freeText24}</dd>
          <dt>
            <span id="freeText25">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText25">Free Text 25</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.freeText25}</dd>
          <dt>
            <span id="freeText26">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText26">Free Text 26</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.freeText26}</dd>
          <dt>
            <span id="freeText27">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText27">Free Text 27</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.freeText27}</dd>
          <dt>
            <span id="freeText28">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText28">Free Text 28</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.freeText28}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {customAuditHistoryEntity.createdAt ? (
              <TextFormat value={customAuditHistoryEntity.createdAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.createdBy}</dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {customAuditHistoryEntity.updatedAt ? (
              <TextFormat value={customAuditHistoryEntity.updatedAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{customAuditHistoryEntity.updatedBy}</dd>
        </dl>
        <Button tag={Link} to="/custom-audit-history" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/custom-audit-history/${customAuditHistoryEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CustomAuditHistoryDetail;
