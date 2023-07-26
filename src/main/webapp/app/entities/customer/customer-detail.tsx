import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, openFile, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './customer.reducer';

export const CustomerDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const customerEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.customer.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="customerDetailsHeading">
          <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.detail.title">Customer</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{customerEntity.id}</dd>
          <dt>
            <span id="absaTranRef">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.absaTranRef">Absa Tran Ref</Translate>
            </span>
          </dt>
          <dd>{customerEntity.absaTranRef}</dd>
          <dt>
            <span id="billerId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.billerId">Biller Id</Translate>
            </span>
          </dt>
          <dd>{customerEntity.billerId}</dd>
          <dt>
            <span id="paymentItemCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.paymentItemCode">Payment Item Code</Translate>
            </span>
          </dt>
          <dd>{customerEntity.paymentItemCode}</dd>
          <dt>
            <span id="dtDTransactionId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.dtDTransactionId">Dt D Transaction Id</Translate>
            </span>
          </dt>
          <dd>{customerEntity.dtDTransactionId}</dd>
          <dt>
            <span id="amolDTransactionId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.amolDTransactionId">Amol D Transaction Id</Translate>
            </span>
          </dt>
          <dd>{customerEntity.amolDTransactionId}</dd>
          <dt>
            <span id="transactionReferenceNumber">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.transactionReferenceNumber">
                Transaction Reference Number
              </Translate>
            </span>
          </dt>
          <dd>{customerEntity.transactionReferenceNumber}</dd>
          <dt>
            <span id="token">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.token">Token</Translate>
            </span>
          </dt>
          <dd>{customerEntity.token}</dd>
          <dt>
            <span id="transferId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.transferId">Transfer Id</Translate>
            </span>
          </dt>
          <dd>{customerEntity.transferId}</dd>
          <dt>
            <span id="requestId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.requestId">Request Id</Translate>
            </span>
          </dt>
          <dd>{customerEntity.requestId}</dd>
          <dt>
            <span id="accountName">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.accountName">Account Name</Translate>
            </span>
          </dt>
          <dd>{customerEntity.accountName}</dd>
          <dt>
            <span id="returnCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.returnCode">Return Code</Translate>
            </span>
          </dt>
          <dd>{customerEntity.returnCode}</dd>
          <dt>
            <span id="returnMessage">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.returnMessage">Return Message</Translate>
            </span>
          </dt>
          <dd>{customerEntity.returnMessage}</dd>
          <dt>
            <span id="externalTXNid">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.externalTXNid">External TX Nid</Translate>
            </span>
          </dt>
          <dd>{customerEntity.externalTXNid}</dd>
          <dt>
            <span id="transactionDate">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.transactionDate">Transaction Date</Translate>
            </span>
          </dt>
          <dd>
            {customerEntity.transactionDate ? (
              <TextFormat value={customerEntity.transactionDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="customerId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.customerId">Customer Id</Translate>
            </span>
          </dt>
          <dd>{customerEntity.customerId}</dd>
          <dt>
            <span id="customerProduct">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.customerProduct">Customer Product</Translate>
            </span>
          </dt>
          <dd>{customerEntity.customerProduct}</dd>
          <dt>
            <span id="customerType">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.customerType">Customer Type</Translate>
            </span>
          </dt>
          <dd>{customerEntity.customerType}</dd>
          <dt>
            <span id="accountCategory">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.accountCategory">Account Category</Translate>
            </span>
          </dt>
          <dd>{customerEntity.accountCategory}</dd>
          <dt>
            <span id="accountType">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.accountType">Account Type</Translate>
            </span>
          </dt>
          <dd>{customerEntity.accountType}</dd>
          <dt>
            <span id="accountNumber">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.accountNumber">Account Number</Translate>
            </span>
          </dt>
          <dd>{customerEntity.accountNumber}</dd>
          <dt>
            <span id="phoneNumber">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.phoneNumber">Phone Number</Translate>
            </span>
          </dt>
          <dd>{customerEntity.phoneNumber}</dd>
          <dt>
            <span id="channel">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.channel">Channel</Translate>
            </span>
          </dt>
          <dd>{customerEntity.channel}</dd>
          <dt>
            <span id="tranFreeField1">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField1">Tran Free Field 1</Translate>
            </span>
          </dt>
          <dd>{customerEntity.tranFreeField1}</dd>
          <dt>
            <span id="tranFreeField2">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField2">Tran Free Field 2</Translate>
            </span>
          </dt>
          <dd>{customerEntity.tranFreeField2}</dd>
          <dt>
            <span id="tranFreeField3">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField3">Tran Free Field 3</Translate>
            </span>
          </dt>
          <dd>{customerEntity.tranFreeField3}</dd>
          <dt>
            <span id="tranFreeField4">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField4">Tran Free Field 4</Translate>
            </span>
          </dt>
          <dd>{customerEntity.tranFreeField4}</dd>
          <dt>
            <span id="tranFreeField5">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField5">Tran Free Field 5</Translate>
            </span>
          </dt>
          <dd>{customerEntity.tranFreeField5}</dd>
          <dt>
            <span id="tranFreeField6">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField6">Tran Free Field 6</Translate>
            </span>
          </dt>
          <dd>{customerEntity.tranFreeField6}</dd>
          <dt>
            <span id="tranFreeField7">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField7">Tran Free Field 7</Translate>
            </span>
          </dt>
          <dd>{customerEntity.tranFreeField7}</dd>
          <dt>
            <span id="tranFreeField8">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField8">Tran Free Field 8</Translate>
            </span>
          </dt>
          <dd>{customerEntity.tranFreeField8}</dd>
          <dt>
            <span id="tranFreeField9">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField9">Tran Free Field 9</Translate>
            </span>
          </dt>
          <dd>{customerEntity.tranFreeField9}</dd>
          <dt>
            <span id="tranFreeField10">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField10">Tran Free Field 10</Translate>
            </span>
          </dt>
          <dd>{customerEntity.tranFreeField10}</dd>
          <dt>
            <span id="tranFreeField11">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField11">Tran Free Field 11</Translate>
            </span>
          </dt>
          <dd>{customerEntity.tranFreeField11}</dd>
          <dt>
            <span id="tranFreeField12">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField12">Tran Free Field 12</Translate>
            </span>
          </dt>
          <dd>{customerEntity.tranFreeField12}</dd>
          <dt>
            <span id="tranFreeField13">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField13">Tran Free Field 13</Translate>
            </span>
          </dt>
          <dd>{customerEntity.tranFreeField13}</dd>
          <dt>
            <span id="tranFreeField14">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField14">Tran Free Field 14</Translate>
            </span>
          </dt>
          <dd>{customerEntity.tranFreeField14}</dd>
          <dt>
            <span id="tranFreeField15">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField15">Tran Free Field 15</Translate>
            </span>
          </dt>
          <dd>{customerEntity.tranFreeField15}</dd>
          <dt>
            <span id="tranFreeField16">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField16">Tran Free Field 16</Translate>
            </span>
          </dt>
          <dd>{customerEntity.tranFreeField16}</dd>
          <dt>
            <span id="tranFreeField17">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField17">Tran Free Field 17</Translate>
            </span>
          </dt>
          <dd>{customerEntity.tranFreeField17}</dd>
          <dt>
            <span id="tranFreeField18">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField18">Tran Free Field 18</Translate>
            </span>
          </dt>
          <dd>{customerEntity.tranFreeField18}</dd>
          <dt>
            <span id="tranFreeField19">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField19">Tran Free Field 19</Translate>
            </span>
          </dt>
          <dd>{customerEntity.tranFreeField19}</dd>
          <dt>
            <span id="tranFreeField20">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField20">Tran Free Field 20</Translate>
            </span>
          </dt>
          <dd>{customerEntity.tranFreeField20 ? 'true' : 'false'}</dd>
          <dt>
            <span id="tranFreeField21">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField21">Tran Free Field 21</Translate>
            </span>
          </dt>
          <dd>{customerEntity.tranFreeField21}</dd>
          <dt>
            <span id="tranFreeField22">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField22">Tran Free Field 22</Translate>
            </span>
          </dt>
          <dd>{customerEntity.tranFreeField22}</dd>
          <dt>
            <span id="tranFreeField23">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField23">Tran Free Field 23</Translate>
            </span>
          </dt>
          <dd>
            {customerEntity.tranFreeField23 ? (
              <div>
                {customerEntity.tranFreeField23ContentType ? (
                  <a onClick={openFile(customerEntity.tranFreeField23ContentType, customerEntity.tranFreeField23)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                ) : null}
                <span>
                  {customerEntity.tranFreeField23ContentType}, {byteSize(customerEntity.tranFreeField23)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="tranFreeField24">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField24">Tran Free Field 24</Translate>
            </span>
          </dt>
          <dd>
            {customerEntity.tranFreeField24 ? (
              <TextFormat value={customerEntity.tranFreeField24} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="tranFreeField25">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField25">Tran Free Field 25</Translate>
            </span>
          </dt>
          <dd>{customerEntity.tranFreeField25}</dd>
          <dt>
            <span id="tranFreeField26">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField26">Tran Free Field 26</Translate>
            </span>
          </dt>
          <dd>{customerEntity.tranFreeField26}</dd>
          <dt>
            <span id="tranFreeField27">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField27">Tran Free Field 27</Translate>
            </span>
          </dt>
          <dd>{customerEntity.tranFreeField27}</dd>
          <dt>
            <span id="tranFreeField28">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField28">Tran Free Field 28</Translate>
            </span>
          </dt>
          <dd>{customerEntity.tranFreeField28}</dd>
          <dt>
            <span id="tranFreeField29">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField29">Tran Free Field 29</Translate>
            </span>
          </dt>
          <dd>{customerEntity.tranFreeField29}</dd>
          <dt>
            <span id="tranFreeField30">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField30">Tran Free Field 30</Translate>
            </span>
          </dt>
          <dd>{customerEntity.tranFreeField30}</dd>
          <dt>
            <span id="tranFreeField31">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField31">Tran Free Field 31</Translate>
            </span>
          </dt>
          <dd>{customerEntity.tranFreeField31}</dd>
          <dt>
            <span id="tranFreeField32">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField32">Tran Free Field 32</Translate>
            </span>
          </dt>
          <dd>{customerEntity.tranFreeField32}</dd>
          <dt>
            <span id="tranFreeField33">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField33">Tran Free Field 33</Translate>
            </span>
          </dt>
          <dd>{customerEntity.tranFreeField33}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>{customerEntity.createdAt ? <TextFormat value={customerEntity.createdAt} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{customerEntity.createdBy}</dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>{customerEntity.updatedAt ? <TextFormat value={customerEntity.updatedAt} type="date" format={APP_DATE_FORMAT} /> : null}</dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{customerEntity.updatedBy}</dd>
        </dl>
        <Button tag={Link} to="/customer" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/customer/${customerEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default CustomerDetail;
