import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Input, InputGroup, FormGroup, Form, Row, Col, Table } from 'reactstrap';
import { Translate, translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IDTransactionHistory } from 'app/shared/model/d-transaction-history.model';
import { searchEntities, getEntities } from './d-transaction-history.reducer';

export const DTransactionHistory = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [search, setSearch] = useState('');

  const dTransactionHistoryList = useAppSelector(state => state.absaugdtmicrosrvcgateway.dTransactionHistory.entities);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.dTransactionHistory.loading);

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
      <h2 id="d-transaction-history-heading" data-cy="DTransactionHistoryHeading">
        <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.home.title">D Transaction Histories</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/d-transaction-history/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.home.createLabel">
              Create new D Transaction History
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
                  placeholder={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.home.search')}
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
        {dTransactionHistoryList && dTransactionHistoryList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.recordId">Record Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.transferId">Transfer Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.productCode">Product Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.paymentChannelCode">
                    Payment Channel Code
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.debitAccountNumber">
                    Debit Account Number
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.creditAccountNumber">
                    Credit Account Number
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.debitAmount">Debit Amount</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.creditAmount">Credit Amount</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.transactionAmount">Transaction Amount</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.debitDate">Debit Date</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.creditDate">Credit Date</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.status">Status</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.settlementDate">Settlement Date</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.debitCurrency">Debit Currency</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.timestamp">Timestamp</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.phoneNumber">Phone Number</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.email">Email</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.payerName">Payer Name</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.payerAddress">Payer Address</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.payerEmail">Payer Email</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.payerPhoneNumber">Payer Phone Number</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.payerNarration">Payer Narration</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.payerBranchId">Payer Branch Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.beneficiaryName">Beneficiary Name</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.beneficiaryAccount">Beneficiary Account</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.beneficiaryBranchId">
                    Beneficiary Branch Id
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.beneficiaryPhoneNumber">
                    Beneficiary Phone Number
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.tranStatus">Tran Status</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.narration1">Narration 1</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.narration2">Narration 2</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.narration3">Narration 3</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.modeOfPayment">Mode Of Payment</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.retries">Retries</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.posted">Posted</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.apiId">Api Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.apiVersion">Api Version</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.postingApi">Posting Api</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.isPosted">Is Posted</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.postedBy">Posted By</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.postedDate">Posted Date</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.internalErrorCode">Internal Error Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.externalErrorCode">External Error Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.tranFreeField1">Tran Free Field 1</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.tranFreeField3">Tran Free Field 3</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.tranFreeField4">Tran Free Field 4</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.tranFreeField5">Tran Free Field 5</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.tranFreeField6">Tran Free Field 6</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.tranFreeField7">Tran Free Field 7</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.tranFreeField8">Tran Free Field 8</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.tranFreeField9">Tran Free Field 9</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.tranFreeField10">Tran Free Field 10</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.tranFreeField11">Tran Free Field 11</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.tranFreeField12">Tran Free Field 12</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.createdAt">Created At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.createdBy">Created By</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.updatedAt">Updated At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.updatedBy">Updated By</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.customer">Customer</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {dTransactionHistoryList.map((dTransactionHistory, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/d-transaction-history/${dTransactionHistory.id}`} color="link" size="sm">
                      {dTransactionHistory.id}
                    </Button>
                  </td>
                  <td>{dTransactionHistory.recordId}</td>
                  <td>{dTransactionHistory.transferId}</td>
                  <td>{dTransactionHistory.productCode}</td>
                  <td>
                    <Translate contentKey={`absaUgdtMicrosrvcGatewayApp.Channel.${dTransactionHistory.paymentChannelCode}`} />
                  </td>
                  <td>{dTransactionHistory.debitAccountNumber}</td>
                  <td>{dTransactionHistory.creditAccountNumber}</td>
                  <td>{dTransactionHistory.debitAmount}</td>
                  <td>{dTransactionHistory.creditAmount}</td>
                  <td>{dTransactionHistory.transactionAmount}</td>
                  <td>
                    {dTransactionHistory.debitDate ? (
                      <TextFormat type="date" value={dTransactionHistory.debitDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {dTransactionHistory.creditDate ? (
                      <TextFormat type="date" value={dTransactionHistory.creditDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    <Translate contentKey={`absaUgdtMicrosrvcGatewayApp.TranStatus.${dTransactionHistory.status}`} />
                  </td>
                  <td>
                    {dTransactionHistory.settlementDate ? (
                      <TextFormat type="date" value={dTransactionHistory.settlementDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    <Translate contentKey={`absaUgdtMicrosrvcGatewayApp.Currency.${dTransactionHistory.debitCurrency}`} />
                  </td>
                  <td>
                    {dTransactionHistory.timestamp ? (
                      <TextFormat type="date" value={dTransactionHistory.timestamp} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{dTransactionHistory.phoneNumber}</td>
                  <td>{dTransactionHistory.email}</td>
                  <td>{dTransactionHistory.payerName}</td>
                  <td>{dTransactionHistory.payerAddress}</td>
                  <td>{dTransactionHistory.payerEmail}</td>
                  <td>{dTransactionHistory.payerPhoneNumber}</td>
                  <td>{dTransactionHistory.payerNarration}</td>
                  <td>{dTransactionHistory.payerBranchId}</td>
                  <td>{dTransactionHistory.beneficiaryName}</td>
                  <td>{dTransactionHistory.beneficiaryAccount}</td>
                  <td>{dTransactionHistory.beneficiaryBranchId}</td>
                  <td>{dTransactionHistory.beneficiaryPhoneNumber}</td>
                  <td>
                    <Translate contentKey={`absaUgdtMicrosrvcGatewayApp.TranStatus.${dTransactionHistory.tranStatus}`} />
                  </td>
                  <td>{dTransactionHistory.narration1}</td>
                  <td>{dTransactionHistory.narration2}</td>
                  <td>{dTransactionHistory.narration3}</td>
                  <td>
                    <Translate contentKey={`absaUgdtMicrosrvcGatewayApp.ModeOfPayment.${dTransactionHistory.modeOfPayment}`} />
                  </td>
                  <td>{dTransactionHistory.retries}</td>
                  <td>{dTransactionHistory.posted ? 'true' : 'false'}</td>
                  <td>{dTransactionHistory.apiId}</td>
                  <td>{dTransactionHistory.apiVersion}</td>
                  <td>{dTransactionHistory.postingApi}</td>
                  <td>{dTransactionHistory.isPosted ? 'true' : 'false'}</td>
                  <td>{dTransactionHistory.postedBy}</td>
                  <td>{dTransactionHistory.postedDate}</td>
                  <td>{dTransactionHistory.internalErrorCode}</td>
                  <td>{dTransactionHistory.externalErrorCode}</td>
                  <td>{dTransactionHistory.tranFreeField1}</td>
                  <td>{dTransactionHistory.tranFreeField3}</td>
                  <td>{dTransactionHistory.tranFreeField4}</td>
                  <td>{dTransactionHistory.tranFreeField5}</td>
                  <td>{dTransactionHistory.tranFreeField6}</td>
                  <td>{dTransactionHistory.tranFreeField7}</td>
                  <td>{dTransactionHistory.tranFreeField8}</td>
                  <td>{dTransactionHistory.tranFreeField9}</td>
                  <td>{dTransactionHistory.tranFreeField10}</td>
                  <td>{dTransactionHistory.tranFreeField11}</td>
                  <td>{dTransactionHistory.tranFreeField12}</td>
                  <td>
                    {dTransactionHistory.createdAt ? (
                      <TextFormat type="date" value={dTransactionHistory.createdAt} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{dTransactionHistory.createdBy}</td>
                  <td>
                    {dTransactionHistory.updatedAt ? (
                      <TextFormat type="date" value={dTransactionHistory.updatedAt} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{dTransactionHistory.updatedBy}</td>
                  <td>
                    {dTransactionHistory.customer ? (
                      <Link to={`/customer/${dTransactionHistory.customer.id}`}>{dTransactionHistory.customer.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/d-transaction-history/${dTransactionHistory.id}`}
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
                        to={`/d-transaction-history/${dTransactionHistory.id}/edit`}
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
                        to={`/d-transaction-history/${dTransactionHistory.id}/delete`}
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
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.home.notFound">
                No D Transaction Histories found
              </Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default DTransactionHistory;
