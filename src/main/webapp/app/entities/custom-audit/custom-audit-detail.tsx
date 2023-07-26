import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './custom-audit.reducer';

export const CustomAuditDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const customAuditEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.customAudit.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="customAuditDetailsHeading">
          <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.detail.title">CustomAudit</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.id}</dd>
          <dt>
            <span id="absaTranRef">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.absaTranRef">Absa Tran Ref</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.absaTranRef}</dd>
          <dt>
            <span id="recordId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.recordId">Record Id</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.recordId}</dd>
          <dt>
            <span id="actionId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.actionId">Action Id</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.actionId}</dd>
          <dt>
            <span id="timestamp">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.timestamp">Timestamp</Translate>
            </span>
          </dt>
          <dd>
            {customAuditEntity.timestamp ? <TextFormat value={customAuditEntity.timestamp} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="oldValue">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.oldValue">Old Value</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.oldValue}</dd>
          <dt>
            <span id="newValue">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.newValue">New Value</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.newValue}</dd>
          <dt>
            <span id="changeResaon">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.changeResaon">Change Resaon</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.changeResaon}</dd>
          <dt>
            <span id="description">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.description">Description</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.description}</dd>
          <dt>
            <span id="description1">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.description1">Description 1</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.description1}</dd>
          <dt>
            <span id="description2">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.description2">Description 2</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.description2}</dd>
          <dt>
            <span id="description3">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.description3">Description 3</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.description3}</dd>
          <dt>
            <span id="description4">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.description4">Description 4</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.description4}</dd>
          <dt>
            <span id="description5">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.description5">Description 5</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.description5}</dd>
          <dt>
            <span id="description6">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.description6">Description 6</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.description6}</dd>
          <dt>
            <span id="description7">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.description7">Description 7</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.description7}</dd>
          <dt>
            <span id="description8">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.description8">Description 8</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.description8}</dd>
          <dt>
            <span id="description9">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.description9">Description 9</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.description9}</dd>
          <dt>
            <span id="freeText1">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText1">Free Text 1</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.freeText1}</dd>
          <dt>
            <span id="freeText2">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText2">Free Text 2</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.freeText2}</dd>
          <dt>
            <span id="freeText3">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText3">Free Text 3</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.freeText3}</dd>
          <dt>
            <span id="freeText4">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText4">Free Text 4</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.freeText4}</dd>
          <dt>
            <span id="freeText5">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText5">Free Text 5</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.freeText5}</dd>
          <dt>
            <span id="freeText6">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText6">Free Text 6</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.freeText6}</dd>
          <dt>
            <span id="freeText7">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText7">Free Text 7</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.freeText7}</dd>
          <dt>
            <span id="freeText8">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText8">Free Text 8</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.freeText8}</dd>
          <dt>
            <span id="freeText9">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText9">Free Text 9</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.freeText9}</dd>
          <dt>
            <span id="freeText10">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText10">Free Text 10</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.freeText10}</dd>
          <dt>
            <span id="freeText11">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText11">Free Text 11</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.freeText11}</dd>
          <dt>
            <span id="freeText12">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText12">Free Text 12</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.freeText12}</dd>
          <dt>
            <span id="freeText13">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText13">Free Text 13</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.freeText13}</dd>
          <dt>
            <span id="freeText14">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText14">Free Text 14</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.freeText14}</dd>
          <dt>
            <span id="freeText15">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText15">Free Text 15</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.freeText15}</dd>
          <dt>
            <span id="freeText16">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText16">Free Text 16</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.freeText16}</dd>
          <dt>
            <span id="freeText17">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText17">Free Text 17</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.freeText17}</dd>
          <dt>
            <span id="freeText18">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText18">Free Text 18</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.freeText18}</dd>
          <dt>
            <span id="freeText19">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText19">Free Text 19</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.freeText19}</dd>
          <dt>
            <span id="freeText20">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText20">Free Text 20</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.freeText20}</dd>
          <dt>
            <span id="freeText21">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText21">Free Text 21</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.freeText21}</dd>
          <dt>
            <span id="freeText22">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText22">Free Text 22</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.freeText22}</dd>
          <dt>
            <span id="freeText23">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText23">Free Text 23</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.freeText23}</dd>
          <dt>
            <span id="freeText24">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText24">Free Text 24</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.freeText24}</dd>
          <dt>
            <span id="freeText25">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText25">Free Text 25</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.freeText25}</dd>
          <dt>
            <span id="freeText26">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText26">Free Text 26</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.freeText26}</dd>
          <dt>
            <span id="freeText27">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText27">Free Text 27</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.freeText27}</dd>
          <dt>
            <span id="freeText28">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText28">Free Text 28</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.freeText28}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {customAuditEntity.createdAt ? <TextFormat value={customAuditEntity.createdAt} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.createdBy}</dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {customAuditEntity.updatedAt ? <TextFormat value={customAuditEntity.updatedAt} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{customAuditEntity.updatedBy}</dd>
        </dl>
        <Button tag={Link} to="/custom-audit" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/custom-audit/${customAuditEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CustomAuditDetail;
