import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './branch.reducer';

export const BranchDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const branchEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.branch.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="branchDetailsHeading">
          <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.detail.title">Branch</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{branchEntity.id}</dd>
          <dt>
            <span id="absaTranRef">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.absaTranRef">Absa Tran Ref</Translate>
            </span>
          </dt>
          <dd>{branchEntity.absaTranRef}</dd>
          <dt>
            <span id="recordId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.recordId">Record Id</Translate>
            </span>
          </dt>
          <dd>{branchEntity.recordId}</dd>
          <dt>
            <span id="addressId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.addressId">Address Id</Translate>
            </span>
          </dt>
          <dd>{branchEntity.addressId}</dd>
          <dt>
            <span id="bankId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.bankId">Bank Id</Translate>
            </span>
          </dt>
          <dd>{branchEntity.bankId}</dd>
          <dt>
            <span id="branchId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchId">Branch Id</Translate>
            </span>
          </dt>
          <dd>{branchEntity.branchId}</dd>
          <dt>
            <span id="branchName">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchName">Branch Name</Translate>
            </span>
          </dt>
          <dd>{branchEntity.branchName}</dd>
          <dt>
            <span id="branchSwiftCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchSwiftCode">Branch Swift Code</Translate>
            </span>
          </dt>
          <dd>{branchEntity.branchSwiftCode}</dd>
          <dt>
            <span id="branchPhoneNumber">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchPhoneNumber">Branch Phone Number</Translate>
            </span>
          </dt>
          <dd>{branchEntity.branchPhoneNumber}</dd>
          <dt>
            <span id="branchEmail">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchEmail">Branch Email</Translate>
            </span>
          </dt>
          <dd>{branchEntity.branchEmail}</dd>
          <dt>
            <span id="branchFreeField1">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField1">Branch Free Field 1</Translate>
            </span>
          </dt>
          <dd>{branchEntity.branchFreeField1}</dd>
          <dt>
            <span id="branchFreeField3">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField3">Branch Free Field 3</Translate>
            </span>
          </dt>
          <dd>{branchEntity.branchFreeField3}</dd>
          <dt>
            <span id="branchFreeField4">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField4">Branch Free Field 4</Translate>
            </span>
          </dt>
          <dd>{branchEntity.branchFreeField4}</dd>
          <dt>
            <span id="branchFreeField5">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField5">Branch Free Field 5</Translate>
            </span>
          </dt>
          <dd>{branchEntity.branchFreeField5}</dd>
          <dt>
            <span id="branchFreeField6">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField6">Branch Free Field 6</Translate>
            </span>
          </dt>
          <dd>{branchEntity.branchFreeField6}</dd>
          <dt>
            <span id="branchFreeField7">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField7">Branch Free Field 7</Translate>
            </span>
          </dt>
          <dd>{branchEntity.branchFreeField7}</dd>
          <dt>
            <span id="branchFreeField8">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField8">Branch Free Field 8</Translate>
            </span>
          </dt>
          <dd>{branchEntity.branchFreeField8}</dd>
          <dt>
            <span id="branchFreeField9">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField9">Branch Free Field 9</Translate>
            </span>
          </dt>
          <dd>{branchEntity.branchFreeField9}</dd>
          <dt>
            <span id="branchFreeField10">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField10">Branch Free Field 10</Translate>
            </span>
          </dt>
          <dd>{branchEntity.branchFreeField10}</dd>
          <dt>
            <span id="branchFreeField11">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField11">Branch Free Field 11</Translate>
            </span>
          </dt>
          <dd>{branchEntity.branchFreeField11}</dd>
          <dt>
            <span id="branchFreeField12">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField12">Branch Free Field 12</Translate>
            </span>
          </dt>
          <dd>{branchEntity.branchFreeField12}</dd>
          <dt>
            <span id="branchFreeField13">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField13">Branch Free Field 13</Translate>
            </span>
          </dt>
          <dd>{branchEntity.branchFreeField13}</dd>
          <dt>
            <span id="branchFreeField14">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField14">Branch Free Field 14</Translate>
            </span>
          </dt>
          <dd>{branchEntity.branchFreeField14}</dd>
          <dt>
            <span id="branchFreeField15">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField15">Branch Free Field 15</Translate>
            </span>
          </dt>
          <dd>{branchEntity.branchFreeField15}</dd>
          <dt>
            <span id="branchFreeField16">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField16">Branch Free Field 16</Translate>
            </span>
          </dt>
          <dd>{branchEntity.branchFreeField16}</dd>
          <dt>
            <span id="branchFreeField17">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField17">Branch Free Field 17</Translate>
            </span>
          </dt>
          <dd>{branchEntity.branchFreeField17}</dd>
          <dt>
            <span id="branchFreeField18">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField18">Branch Free Field 18</Translate>
            </span>
          </dt>
          <dd>{branchEntity.branchFreeField18}</dd>
          <dt>
            <span id="branchFreeField19">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField19">Branch Free Field 19</Translate>
            </span>
          </dt>
          <dd>{branchEntity.branchFreeField19}</dd>
          <dt>
            <span id="branchFreeField20">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField20">Branch Free Field 20</Translate>
            </span>
          </dt>
          <dd>{branchEntity.branchFreeField20}</dd>
          <dt>
            <span id="branchFreeField21">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField21">Branch Free Field 21</Translate>
            </span>
          </dt>
          <dd>{branchEntity.branchFreeField21}</dd>
          <dt>
            <span id="branchFreeField22">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField22">Branch Free Field 22</Translate>
            </span>
          </dt>
          <dd>{branchEntity.branchFreeField22}</dd>
          <dt>
            <span id="branchFreeField23">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField23">Branch Free Field 23</Translate>
            </span>
          </dt>
          <dd>{branchEntity.branchFreeField23}</dd>
          <dt>
            <span id="branchFreeField24">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField24">Branch Free Field 24</Translate>
            </span>
          </dt>
          <dd>{branchEntity.branchFreeField24}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>{branchEntity.createdAt ? <TextFormat value={branchEntity.createdAt} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{branchEntity.createdBy}</dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>{branchEntity.updatedAt ? <TextFormat value={branchEntity.updatedAt} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{branchEntity.updatedBy}</dd>
          <dt>
            <span id="timestamp">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.timestamp">Timestamp</Translate>
            </span>
          </dt>
          <dd>{branchEntity.timestamp ? <TextFormat value={branchEntity.timestamp} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.status">Status</Translate>
            </span>
          </dt>
          <dd>{branchEntity.status}</dd>
          <dt>
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.bank">Bank</Translate>
          </dt>
          <dd>{branchEntity.bank ? branchEntity.bank.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/branch" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/branch/${branchEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default BranchDetail;
