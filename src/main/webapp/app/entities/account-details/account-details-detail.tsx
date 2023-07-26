import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, openFile, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './account-details.reducer';

export const AccountDetailsDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const accountDetailsEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.accountDetails.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="accountDetailsDetailsHeading">
          <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.detail.title">AccountDetails</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.id}</dd>
          <dt>
            <span id="absaTranRef">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.absaTranRef">Absa Tran Ref</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.absaTranRef}</dd>
          <dt>
            <span id="billerId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.billerId">Biller Id</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.billerId}</dd>
          <dt>
            <span id="paymentItemCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.paymentItemCode">Payment Item Code</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.paymentItemCode}</dd>
          <dt>
            <span id="dtDTransactionId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.dtDTransactionId">Dt D Transaction Id</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.dtDTransactionId}</dd>
          <dt>
            <span id="amolDTransactionId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.amolDTransactionId">Amol D Transaction Id</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.amolDTransactionId}</dd>
          <dt>
            <span id="transactionReferenceNumber">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.transactionReferenceNumber">
                Transaction Reference Number
              </Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.transactionReferenceNumber}</dd>
          <dt>
            <span id="token">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.token">Token</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.token}</dd>
          <dt>
            <span id="transferId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.transferId">Transfer Id</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.transferId}</dd>
          <dt>
            <span id="requestId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.requestId">Request Id</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.requestId}</dd>
          <dt>
            <span id="accountName">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.accountName">Account Name</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.accountName}</dd>
          <dt>
            <span id="returnCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.returnCode">Return Code</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.returnCode}</dd>
          <dt>
            <span id="returnMessage">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.returnMessage">Return Message</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.returnMessage}</dd>
          <dt>
            <span id="externalTXNid">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.externalTXNid">External TX Nid</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.externalTXNid}</dd>
          <dt>
            <span id="transactionDate">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.transactionDate">Transaction Date</Translate>
            </span>
          </dt>
          <dd>
            {accountDetailsEntity.transactionDate ? (
              <TextFormat value={accountDetailsEntity.transactionDate} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="customerId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.customerId">Customer Id</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.customerId}</dd>
          <dt>
            <span id="customerProduct">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.customerProduct">Customer Product</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.customerProduct}</dd>
          <dt>
            <span id="customerType">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.customerType">Customer Type</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.customerType}</dd>
          <dt>
            <span id="accountCategory">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.accountCategory">Account Category</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.accountCategory}</dd>
          <dt>
            <span id="accountType">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.accountType">Account Type</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.accountType}</dd>
          <dt>
            <span id="accountNumber">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.accountNumber">Account Number</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.accountNumber}</dd>
          <dt>
            <span id="phoneNumber">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.phoneNumber">Phone Number</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.phoneNumber}</dd>
          <dt>
            <span id="channel">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.channel">Channel</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.channel}</dd>
          <dt>
            <span id="tranFreeField1">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField1">Tran Free Field 1</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.tranFreeField1}</dd>
          <dt>
            <span id="tranFreeField2">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField2">Tran Free Field 2</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.tranFreeField2}</dd>
          <dt>
            <span id="tranFreeField3">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField3">Tran Free Field 3</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.tranFreeField3}</dd>
          <dt>
            <span id="tranFreeField4">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField4">Tran Free Field 4</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.tranFreeField4}</dd>
          <dt>
            <span id="tranFreeField5">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField5">Tran Free Field 5</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.tranFreeField5}</dd>
          <dt>
            <span id="tranFreeField6">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField6">Tran Free Field 6</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.tranFreeField6}</dd>
          <dt>
            <span id="tranFreeField7">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField7">Tran Free Field 7</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.tranFreeField7}</dd>
          <dt>
            <span id="tranFreeField8">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField8">Tran Free Field 8</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.tranFreeField8}</dd>
          <dt>
            <span id="tranFreeField9">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField9">Tran Free Field 9</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.tranFreeField9}</dd>
          <dt>
            <span id="tranFreeField10">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField10">Tran Free Field 10</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.tranFreeField10}</dd>
          <dt>
            <span id="tranFreeField11">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField11">Tran Free Field 11</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.tranFreeField11}</dd>
          <dt>
            <span id="tranFreeField12">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField12">Tran Free Field 12</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.tranFreeField12}</dd>
          <dt>
            <span id="tranFreeField13">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField13">Tran Free Field 13</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.tranFreeField13}</dd>
          <dt>
            <span id="tranFreeField14">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField14">Tran Free Field 14</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.tranFreeField14}</dd>
          <dt>
            <span id="tranFreeField15">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField15">Tran Free Field 15</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.tranFreeField15}</dd>
          <dt>
            <span id="tranFreeField16">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField16">Tran Free Field 16</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.tranFreeField16}</dd>
          <dt>
            <span id="tranFreeField17">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField17">Tran Free Field 17</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.tranFreeField17}</dd>
          <dt>
            <span id="tranFreeField18">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField18">Tran Free Field 18</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.tranFreeField18}</dd>
          <dt>
            <span id="tranFreeField19">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField19">Tran Free Field 19</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.tranFreeField19}</dd>
          <dt>
            <span id="tranFreeField20">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField20">Tran Free Field 20</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.tranFreeField20 ? 'true' : 'false'}</dd>
          <dt>
            <span id="tranFreeField21">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField21">Tran Free Field 21</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.tranFreeField21}</dd>
          <dt>
            <span id="tranFreeField22">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField22">Tran Free Field 22</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.tranFreeField22}</dd>
          <dt>
            <span id="tranFreeField23">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField23">Tran Free Field 23</Translate>
            </span>
          </dt>
          <dd>
            {accountDetailsEntity.tranFreeField23 ? (
              <div>
                {accountDetailsEntity.tranFreeField23ContentType ? (
                  <a onClick={openFile(accountDetailsEntity.tranFreeField23ContentType, accountDetailsEntity.tranFreeField23)}>
                    <Translate contentKey="entity.action.open">Open</Translate>&nbsp;
                  </a>
                ) : null}
                <span>
                  {accountDetailsEntity.tranFreeField23ContentType}, {byteSize(accountDetailsEntity.tranFreeField23)}
                </span>
              </div>
            ) : null}
          </dd>
          <dt>
            <span id="tranFreeField24">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField24">Tran Free Field 24</Translate>
            </span>
          </dt>
          <dd>
            {accountDetailsEntity.tranFreeField24 ? (
              <TextFormat value={accountDetailsEntity.tranFreeField24} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="tranFreeField25">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField25">Tran Free Field 25</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.tranFreeField25}</dd>
          <dt>
            <span id="tranFreeField26">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField26">Tran Free Field 26</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.tranFreeField26}</dd>
          <dt>
            <span id="tranFreeField27">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField27">Tran Free Field 27</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.tranFreeField27}</dd>
          <dt>
            <span id="tranFreeField28">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField28">Tran Free Field 28</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.tranFreeField28}</dd>
          <dt>
            <span id="tranFreeField29">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField29">Tran Free Field 29</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.tranFreeField29}</dd>
          <dt>
            <span id="tranFreeField30">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField30">Tran Free Field 30</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.tranFreeField30}</dd>
          <dt>
            <span id="tranFreeField31">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField31">Tran Free Field 31</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.tranFreeField31}</dd>
          <dt>
            <span id="tranFreeField32">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField32">Tran Free Field 32</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.tranFreeField32}</dd>
          <dt>
            <span id="tranFreeField33">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField33">Tran Free Field 33</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.tranFreeField33}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {accountDetailsEntity.createdAt ? (
              <TextFormat value={accountDetailsEntity.createdAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.createdBy}</dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {accountDetailsEntity.updatedAt ? (
              <TextFormat value={accountDetailsEntity.updatedAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{accountDetailsEntity.updatedBy}</dd>
        </dl>
        <Button tag={Link} to="/account-details" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/account-details/${accountDetailsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default AccountDetailsDetail;
