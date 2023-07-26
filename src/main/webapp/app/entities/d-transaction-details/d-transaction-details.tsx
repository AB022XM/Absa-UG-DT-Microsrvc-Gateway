import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Input, InputGroup, FormGroup, Form, Row, Col, Table } from 'reactstrap';
import { openFile, byteSize, Translate, translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IDTransactionDetails } from 'app/shared/model/d-transaction-details.model';
import { searchEntities, getEntities } from './d-transaction-details.reducer';

export const DTransactionDetails = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [search, setSearch] = useState('');

  const dTransactionDetailsList = useAppSelector(state => state.absaugdtmicrosrvcgateway.dTransactionDetails.entities);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.dTransactionDetails.loading);

  useEffect(() => {
    dispatch(getEntities({}));
  }, []);

  const startSearching = e => {
    if (search) {
      dispatch(searchEntities({ query: search }));
    }
    e.preventDefault();
  };

  const clear = () => {
    setSearch('');
    dispatch(getEntities({}));
  };

  const handleSearch = event => setSearch(event.target.value);

  const handleSyncList = () => {
    dispatch(getEntities({}));
  };

  return (
    <div>
      <h2 id="d-transaction-details-heading" data-cy="DTransactionDetailsHeading">
        <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.home.title">D Transaction Details</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/d-transaction-details/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.home.createLabel">
              Create new D Transaction Details
            </Translate>
          </Link>
        </div>
      </h2>
      <Row>
        <Col sm="12">
          <Form onSubmit={startSearching}>
            <FormGroup>
              <InputGroup>
                <Input
                  type="text"
                  name="search"
                  defaultValue={search}
                  onChange={handleSearch}
                  placeholder={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.home.search')}
                />
                <Button className="input-group-addon">
                  <FontAwesomeIcon icon="search" />
                </Button>
                <Button type="reset" className="input-group-addon" onClick={clear}>
                  <FontAwesomeIcon icon="trash" />
                </Button>
              </InputGroup>
            </FormGroup>
          </Form>
        </Col>
      </Row>
      <div className="table-responsive">
        {dTransactionDetailsList && dTransactionDetailsList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.absaTranRef">Absa Tran Ref</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.billerId">Biller Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.paymentItemCode">Payment Item Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.dtDTransactionId">Dt D Transaction Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.amolDTransactionId">
                    Amol D Transaction Id
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.transactionReferenceNumber">
                    Transaction Reference Number
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.token">Token</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.transferId">Transfer Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.productCode">Product Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.paymentChannelCode">
                    Payment Channel Code
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.debitAccountNumber">
                    Debit Account Number
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.creditAccountNumber">
                    Credit Account Number
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.debitAmount">Debit Amount</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.creditAmount">Credit Amount</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.transactionAmount">Transaction Amount</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.debitDate">Debit Date</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.creditDate">Credit Date</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.status">Status</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.settlementDate">Settlement Date</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.debitCurrency">Debit Currency</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.timestamp">Timestamp</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.phoneNumber">Phone Number</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.email">Email</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.payerName">Payer Name</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.payerAddress">Payer Address</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.payerEmail">Payer Email</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.payerPhoneNumber">Payer Phone Number</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.payerNarration">Payer Narration</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.payerBranchId">Payer Branch Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.beneficiaryName">Beneficiary Name</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.beneficiaryAccount">Beneficiary Account</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.beneficiaryBranchId">
                    Beneficiary Branch Id
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.beneficiaryPhoneNumber">
                    Beneficiary Phone Number
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranStatus">Tran Status</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.narration1">Narration 1</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.narration2">Narration 2</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.narration3">Narration 3</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.narration4">Narration 4</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.narration5">Narration 5</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.narration6">Narration 6</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.narration7">Narration 7</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.narration8">Narration 8</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.narration9">Narration 9</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.narration10">Narration 10</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.narration11">Narration 11</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.narration12">Narration 12</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.modeOfPayment">Mode Of Payment</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.retries">Retries</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.posted">Posted</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.apiId">Api Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.apiVersion">Api Version</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.postingApi">Posting Api</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.isPosted">Is Posted</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.postedBy">Posted By</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.postedDate">Posted Date</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField1">Tran Free Field 1</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField3">Tran Free Field 3</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField4">Tran Free Field 4</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField5">Tran Free Field 5</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField6">Tran Free Field 6</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField7">Tran Free Field 7</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField8">Tran Free Field 8</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField9">Tran Free Field 9</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField10">Tran Free Field 10</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField11">Tran Free Field 11</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField12">Tran Free Field 12</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField13">Tran Free Field 13</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField14">Tran Free Field 14</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField15">Tran Free Field 15</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField16">Tran Free Field 16</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField17">Tran Free Field 17</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField18">Tran Free Field 18</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField19">Tran Free Field 19</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField20">Tran Free Field 20</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField21">Tran Free Field 21</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField22">Tran Free Field 22</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField23">Tran Free Field 23</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField24">Tran Free Field 24</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField25">Tran Free Field 25</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField26">Tran Free Field 26</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField27">Tran Free Field 27</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField28">Tran Free Field 28</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.internalErrorCode">Internal Error Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.externalErrorCode">External Error Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.customer">Customer</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {dTransactionDetailsList.map((dTransactionDetails, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/d-transaction-details/${dTransactionDetails.id}`} color="link" size="sm">
                      {dTransactionDetails.id}
                    </Button>
                  </td>
                  <td>{dTransactionDetails.absaTranRef}</td>
                  <td>{dTransactionDetails.billerId}</td>
                  <td>{dTransactionDetails.paymentItemCode}</td>
                  <td>{dTransactionDetails.dtDTransactionId}</td>
                  <td>{dTransactionDetails.amolDTransactionId}</td>
                  <td>{dTransactionDetails.transactionReferenceNumber}</td>
                  <td>{dTransactionDetails.token}</td>
                  <td>{dTransactionDetails.transferId}</td>
                  <td>{dTransactionDetails.productCode}</td>
                  <td>
                    <Translate contentKey={`absaUgdtMicrosrvcGatewayApp.Channel.${dTransactionDetails.paymentChannelCode}`} />
                  </td>
                  <td>{dTransactionDetails.debitAccountNumber}</td>
                  <td>{dTransactionDetails.creditAccountNumber}</td>
                  <td>{dTransactionDetails.debitAmount}</td>
                  <td>{dTransactionDetails.creditAmount}</td>
                  <td>{dTransactionDetails.transactionAmount}</td>
                  <td>
                    {dTransactionDetails.debitDate ? (
                      <TextFormat type="date" value={dTransactionDetails.debitDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {dTransactionDetails.creditDate ? (
                      <TextFormat type="date" value={dTransactionDetails.creditDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    <Translate contentKey={`absaUgdtMicrosrvcGatewayApp.TranStatus.${dTransactionDetails.status}`} />
                  </td>
                  <td>
                    {dTransactionDetails.settlementDate ? (
                      <TextFormat type="date" value={dTransactionDetails.settlementDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{dTransactionDetails.debitCurrency}</td>
                  <td>
                    {dTransactionDetails.timestamp ? (
                      <TextFormat type="date" value={dTransactionDetails.timestamp} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{dTransactionDetails.phoneNumber}</td>
                  <td>{dTransactionDetails.email}</td>
                  <td>{dTransactionDetails.payerName}</td>
                  <td>{dTransactionDetails.payerAddress}</td>
                  <td>{dTransactionDetails.payerEmail}</td>
                  <td>{dTransactionDetails.payerPhoneNumber}</td>
                  <td>{dTransactionDetails.payerNarration}</td>
                  <td>{dTransactionDetails.payerBranchId}</td>
                  <td>{dTransactionDetails.beneficiaryName}</td>
                  <td>{dTransactionDetails.beneficiaryAccount}</td>
                  <td>{dTransactionDetails.beneficiaryBranchId}</td>
                  <td>{dTransactionDetails.beneficiaryPhoneNumber}</td>
                  <td>
                    <Translate contentKey={`absaUgdtMicrosrvcGatewayApp.TranStatus.${dTransactionDetails.tranStatus}`} />
                  </td>
                  <td>{dTransactionDetails.narration1}</td>
                  <td>{dTransactionDetails.narration2}</td>
                  <td>{dTransactionDetails.narration3}</td>
                  <td>{dTransactionDetails.narration4}</td>
                  <td>{dTransactionDetails.narration5}</td>
                  <td>{dTransactionDetails.narration6}</td>
                  <td>{dTransactionDetails.narration7}</td>
                  <td>{dTransactionDetails.narration8}</td>
                  <td>{dTransactionDetails.narration9}</td>
                  <td>{dTransactionDetails.narration10}</td>
                  <td>{dTransactionDetails.narration11}</td>
                  <td>{dTransactionDetails.narration12}</td>
                  <td>
                    <Translate contentKey={`absaUgdtMicrosrvcGatewayApp.ModeOfPayment.${dTransactionDetails.modeOfPayment}`} />
                  </td>
                  <td>{dTransactionDetails.retries}</td>
                  <td>{dTransactionDetails.posted ? 'true' : 'false'}</td>
                  <td>{dTransactionDetails.apiId}</td>
                  <td>{dTransactionDetails.apiVersion}</td>
                  <td>{dTransactionDetails.postingApi}</td>
                  <td>{dTransactionDetails.isPosted ? 'true' : 'false'}</td>
                  <td>{dTransactionDetails.postedBy}</td>
                  <td>{dTransactionDetails.postedDate}</td>
                  <td>{dTransactionDetails.tranFreeField1}</td>
                  <td>{dTransactionDetails.tranFreeField3}</td>
                  <td>{dTransactionDetails.tranFreeField4}</td>
                  <td>{dTransactionDetails.tranFreeField5}</td>
                  <td>{dTransactionDetails.tranFreeField6}</td>
                  <td>{dTransactionDetails.tranFreeField7}</td>
                  <td>{dTransactionDetails.tranFreeField8}</td>
                  <td>{dTransactionDetails.tranFreeField9}</td>
                  <td>{dTransactionDetails.tranFreeField10}</td>
                  <td>{dTransactionDetails.tranFreeField11}</td>
                  <td>
                    {dTransactionDetails.tranFreeField12 ? (
                      <div>
                        {dTransactionDetails.tranFreeField12ContentType ? (
                          <a onClick={openFile(dTransactionDetails.tranFreeField12ContentType, dTransactionDetails.tranFreeField12)}>
                            <Translate contentKey="entity.action.open">Open</Translate>
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {dTransactionDetails.tranFreeField12ContentType}, {byteSize(dTransactionDetails.tranFreeField12)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{dTransactionDetails.tranFreeField13}</td>
                  <td>{dTransactionDetails.tranFreeField14}</td>
                  <td>
                    {dTransactionDetails.tranFreeField15 ? (
                      <div>
                        {dTransactionDetails.tranFreeField15ContentType ? (
                          <a onClick={openFile(dTransactionDetails.tranFreeField15ContentType, dTransactionDetails.tranFreeField15)}>
                            <Translate contentKey="entity.action.open">Open</Translate>
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {dTransactionDetails.tranFreeField15ContentType}, {byteSize(dTransactionDetails.tranFreeField15)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{dTransactionDetails.tranFreeField16}</td>
                  <td>{dTransactionDetails.tranFreeField17}</td>
                  <td>{dTransactionDetails.tranFreeField18}</td>
                  <td>{dTransactionDetails.tranFreeField19}</td>
                  <td>{dTransactionDetails.tranFreeField20}</td>
                  <td>{dTransactionDetails.tranFreeField21}</td>
                  <td>{dTransactionDetails.tranFreeField22}</td>
                  <td>{dTransactionDetails.tranFreeField23}</td>
                  <td>{dTransactionDetails.tranFreeField24}</td>
                  <td>{dTransactionDetails.tranFreeField25}</td>
                  <td>{dTransactionDetails.tranFreeField26}</td>
                  <td>{dTransactionDetails.tranFreeField27}</td>
                  <td>{dTransactionDetails.tranFreeField28}</td>
                  <td>{dTransactionDetails.internalErrorCode}</td>
                  <td>{dTransactionDetails.externalErrorCode}</td>
                  <td>
                    {dTransactionDetails.customer ? (
                      <Link to={`/customer/${dTransactionDetails.customer.id}`}>{dTransactionDetails.customer.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/d-transaction-details/${dTransactionDetails.id}`}
                        color="info"
                        size="sm"
                        data-cy="entityDetailsButton"
                      >
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/d-transaction-details/${dTransactionDetails.id}/edit`}
                        color="primary"
                        size="sm"
                        data-cy="entityEditButton"
                      >
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/d-transaction-details/${dTransactionDetails.id}/delete`}
                        color="danger"
                        size="sm"
                        data-cy="entityDeleteButton"
                      >
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.home.notFound">
                No D Transaction Details found
              </Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default DTransactionDetails;
