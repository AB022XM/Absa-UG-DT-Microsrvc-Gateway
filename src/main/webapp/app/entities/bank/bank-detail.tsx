import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './bank.reducer';

export const BankDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const bankEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.bank.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="bankDetailsHeading">
          <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.detail.title">Bank</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{bankEntity.id}</dd>
          <dt>
            <span id="absaTranRef">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.absaTranRef">Absa Tran Ref</Translate>
            </span>
          </dt>
          <dd>{bankEntity.absaTranRef}</dd>
          <dt>
            <span id="billerId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.billerId">Biller Id</Translate>
            </span>
          </dt>
          <dd>{bankEntity.billerId}</dd>
          <dt>
            <span id="paymentItemCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.paymentItemCode">Payment Item Code</Translate>
            </span>
          </dt>
          <dd>{bankEntity.paymentItemCode}</dd>
          <dt>
            <span id="dtDTransactionId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.dtDTransactionId">Dt D Transaction Id</Translate>
            </span>
          </dt>
          <dd>{bankEntity.dtDTransactionId}</dd>
          <dt>
            <span id="amolDTransactionId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.amolDTransactionId">Amol D Transaction Id</Translate>
            </span>
          </dt>
          <dd>{bankEntity.amolDTransactionId}</dd>
          <dt>
            <span id="bankName">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankName">Bank Name</Translate>
            </span>
          </dt>
          <dd>{bankEntity.bankName}</dd>
          <dt>
            <span id="bankSwiftCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankSwiftCode">Bank Swift Code</Translate>
            </span>
          </dt>
          <dd>{bankEntity.bankSwiftCode}</dd>
          <dt>
            <span id="bankBranchId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankBranchId">Bank Branch Id</Translate>
            </span>
          </dt>
          <dd>{bankEntity.bankBranchId}</dd>
          <dt>
            <span id="bankPhoneNumber">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankPhoneNumber">Bank Phone Number</Translate>
            </span>
          </dt>
          <dd>{bankEntity.bankPhoneNumber}</dd>
          <dt>
            <span id="bankEmail">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankEmail">Bank Email</Translate>
            </span>
          </dt>
          <dd>{bankEntity.bankEmail}</dd>
          <dt>
            <span id="bankFreeField1">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField1">Bank Free Field 1</Translate>
            </span>
          </dt>
          <dd>{bankEntity.bankFreeField1}</dd>
          <dt>
            <span id="bankFreeField3">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField3">Bank Free Field 3</Translate>
            </span>
          </dt>
          <dd>{bankEntity.bankFreeField3}</dd>
          <dt>
            <span id="bankFreeField4">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField4">Bank Free Field 4</Translate>
            </span>
          </dt>
          <dd>{bankEntity.bankFreeField4}</dd>
          <dt>
            <span id="bankFreeField5">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField5">Bank Free Field 5</Translate>
            </span>
          </dt>
          <dd>{bankEntity.bankFreeField5}</dd>
          <dt>
            <span id="bankFreeField6">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField6">Bank Free Field 6</Translate>
            </span>
          </dt>
          <dd>{bankEntity.bankFreeField6}</dd>
          <dt>
            <span id="bankFreeField7">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField7">Bank Free Field 7</Translate>
            </span>
          </dt>
          <dd>{bankEntity.bankFreeField7}</dd>
          <dt>
            <span id="bankFreeField8">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField8">Bank Free Field 8</Translate>
            </span>
          </dt>
          <dd>{bankEntity.bankFreeField8}</dd>
          <dt>
            <span id="bankFreeField9">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField9">Bank Free Field 9</Translate>
            </span>
          </dt>
          <dd>{bankEntity.bankFreeField9}</dd>
          <dt>
            <span id="bankFreeField10">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField10">Bank Free Field 10</Translate>
            </span>
          </dt>
          <dd>{bankEntity.bankFreeField10}</dd>
          <dt>
            <span id="bankFreeField11">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField11">Bank Free Field 11</Translate>
            </span>
          </dt>
          <dd>{bankEntity.bankFreeField11}</dd>
          <dt>
            <span id="bankFreeField12">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField12">Bank Free Field 12</Translate>
            </span>
          </dt>
          <dd>{bankEntity.bankFreeField12}</dd>
          <dt>
            <span id="bankFreeField13">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField13">Bank Free Field 13</Translate>
            </span>
          </dt>
          <dd>{bankEntity.bankFreeField13}</dd>
          <dt>
            <span id="bankFreeField14">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField14">Bank Free Field 14</Translate>
            </span>
          </dt>
          <dd>{bankEntity.bankFreeField14}</dd>
          <dt>
            <span id="bankFreeField15">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField15">Bank Free Field 15</Translate>
            </span>
          </dt>
          <dd>{bankEntity.bankFreeField15}</dd>
          <dt>
            <span id="bankFreeField16">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField16">Bank Free Field 16</Translate>
            </span>
          </dt>
          <dd>{bankEntity.bankFreeField16}</dd>
          <dt>
            <span id="bankFreeField17">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField17">Bank Free Field 17</Translate>
            </span>
          </dt>
          <dd>{bankEntity.bankFreeField17}</dd>
          <dt>
            <span id="bankFreeField18">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField18">Bank Free Field 18</Translate>
            </span>
          </dt>
          <dd>{bankEntity.bankFreeField18}</dd>
          <dt>
            <span id="bankFreeField19">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField19">Bank Free Field 19</Translate>
            </span>
          </dt>
          <dd>{bankEntity.bankFreeField19}</dd>
          <dt>
            <span id="bankFreeField20">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField20">Bank Free Field 20</Translate>
            </span>
          </dt>
          <dd>{bankEntity.bankFreeField20}</dd>
          <dt>
            <span id="bankFreeField21">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField21">Bank Free Field 21</Translate>
            </span>
          </dt>
          <dd>{bankEntity.bankFreeField21}</dd>
          <dt>
            <span id="bankFreeField22">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField22">Bank Free Field 22</Translate>
            </span>
          </dt>
          <dd>{bankEntity.bankFreeField22}</dd>
          <dt>
            <span id="bankFreeField23">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField23">Bank Free Field 23</Translate>
            </span>
          </dt>
          <dd>{bankEntity.bankFreeField23}</dd>
          <dt>
            <span id="bankFreeField24">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField24">Bank Free Field 24</Translate>
            </span>
          </dt>
          <dd>{bankEntity.bankFreeField24}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>{bankEntity.createdAt ? <TextFormat value={bankEntity.createdAt} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{bankEntity.createdBy}</dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>{bankEntity.updatedAt ? <TextFormat value={bankEntity.updatedAt} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{bankEntity.updatedBy}</dd>
          <dt>
            <span id="delflg">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.delflg">Delflg</Translate>
            </span>
          </dt>
          <dd>{bankEntity.delflg ? 'true' : 'false'}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.status">Status</Translate>
            </span>
          </dt>
          <dd>{bankEntity.status}</dd>
        </dl>
        <Button tag={Link} to="/bank" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/bank/${bankEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default BankDetail;
