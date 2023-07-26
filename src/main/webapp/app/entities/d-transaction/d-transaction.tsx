import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Input, InputGroup, FormGroup, Form, Row, Col, Table } from 'reactstrap';
import { openFile, byteSize, Translate, translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IDTransaction } from 'app/shared/model/d-transaction.model';
import { searchEntities, getEntities } from './d-transaction.reducer';

export const DTransaction = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [search, setSearch] = useState('');

  const dTransactionList = useAppSelector(state => state.absaugdtmicrosrvcgateway.dTransaction.entities);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.dTransaction.loading);

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
      <h2 id="d-transaction-heading" data-cy="DTransactionHeading">
        <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.home.title">D Transactions</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/d-transaction/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.home.createLabel">Create new D Transaction</Translate>
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
                  placeholder={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.home.search')}
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
        {dTransactionList && dTransactionList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.absaTranRef">Absa Tran Ref</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.billerId">Biller Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.paymentItemCode">Payment Item Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.dtDTransactionId">Dt D Transaction Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.amolDTransactionId">Amol D Transaction Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.transactionReferenceNumber">
                    Transaction Reference Number
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.externalTranid">External Tranid</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.token">Token</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.transferId">Transfer Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.productCode">Product Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.paymentChannelCode">Payment Channel Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.accountNumber">Account Number</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.amount">Amount</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.debitAmount">Debit Amount</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.creditAmount">Credit Amount</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.settlementAmount">Settlement Amount</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.settlementDate">Settlement Date</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.transactionDate">Transaction Date</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.isRetried">Is Retried</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.lastRetryDate">Last Retry Date</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.firstRetryDate">First Retry Date</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.retryResposeCode">Retry Respose Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.retryResponseMessage">Retry Response Message</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.retryCount">Retry Count</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.isRetriableFlg">Is Retriable Flg</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.doNotRetryDTransaction">
                    Do Not Retry D Transaction
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.status">Status</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.statusCode">Status Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.statusDetails">Status Details</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.retries">Retries</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.timestamp">Timestamp</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.postedBy">Posted By</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.postedDate">Posted Date</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.internalErrorCode">Internal Error Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.externalErrorCode">External Error Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.isCrossCurrency">Is Cross Currency</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.isCrossBank">Is Cross Bank</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.currency">Currency</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.creditAccount">Credit Account</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.debitAccount">Debit Account</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.phoneNumber">Phone Number</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.customerNumber">Customer Number</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranStatus">Tran Status</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranStatusDetails">Tran Status Details</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField1">Tran Free Field 1</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField2">Tran Free Field 2</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField3">Tran Free Field 3</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField4">Tran Free Field 4</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField5">Tran Free Field 5</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField6">Tran Free Field 6</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField7">Tran Free Field 7</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField8">Tran Free Field 8</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField9">Tran Free Field 9</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField10">Tran Free Field 10</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField11">Tran Free Field 11</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField12">Tran Free Field 12</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField13">Tran Free Field 13</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField14">Tran Free Field 14</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField15">Tran Free Field 15</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField16">Tran Free Field 16</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField17">Tran Free Field 17</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField18">Tran Free Field 18</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField19">Tran Free Field 19</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField20">Tran Free Field 20</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField21">Tran Free Field 21</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField22">Tran Free Field 22</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField23">Tran Free Field 23</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField24">Tran Free Field 24</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField25">Tran Free Field 25</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField26">Tran Free Field 26</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField27">Tran Free Field 27</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField28">Tran Free Field 28</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.createdAt">Created At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.createdBy">Created By</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.updatedAt">Updated At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.updatedBy">Updated By</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.transaction">Transaction</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.transaction">Transaction</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.transaction">Transaction</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.customer">Customer</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {dTransactionList.map((dTransaction, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/d-transaction/${dTransaction.id}`} color="link" size="sm">
                      {dTransaction.id}
                    </Button>
                  </td>
                  <td>{dTransaction.absaTranRef}</td>
                  <td>{dTransaction.billerId}</td>
                  <td>{dTransaction.paymentItemCode}</td>
                  <td>{dTransaction.dtDTransactionId}</td>
                  <td>{dTransaction.amolDTransactionId}</td>
                  <td>{dTransaction.transactionReferenceNumber}</td>
                  <td>{dTransaction.externalTranid}</td>
                  <td>{dTransaction.token}</td>
                  <td>{dTransaction.transferId}</td>
                  <td>{dTransaction.productCode}</td>
                  <td>
                    <Translate contentKey={`absaUgdtMicrosrvcGatewayApp.Channel.${dTransaction.paymentChannelCode}`} />
                  </td>
                  <td>{dTransaction.accountNumber}</td>
                  <td>{dTransaction.amount}</td>
                  <td>{dTransaction.debitAmount}</td>
                  <td>{dTransaction.creditAmount}</td>
                  <td>{dTransaction.settlementAmount}</td>
                  <td>
                    {dTransaction.settlementDate ? (
                      <TextFormat type="date" value={dTransaction.settlementDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {dTransaction.transactionDate ? (
                      <TextFormat type="date" value={dTransaction.transactionDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{dTransaction.isRetried ? 'true' : 'false'}</td>
                  <td>
                    {dTransaction.lastRetryDate ? (
                      <TextFormat type="date" value={dTransaction.lastRetryDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {dTransaction.firstRetryDate ? (
                      <TextFormat type="date" value={dTransaction.firstRetryDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    <Translate contentKey={`absaUgdtMicrosrvcGatewayApp.ErrorCodes.${dTransaction.retryResposeCode}`} />
                  </td>
                  <td>
                    <Translate contentKey={`absaUgdtMicrosrvcGatewayApp.ErrorMessage.${dTransaction.retryResponseMessage}`} />
                  </td>
                  <td>{dTransaction.retryCount}</td>
                  <td>{dTransaction.isRetriableFlg ? 'true' : 'false'}</td>
                  <td>{dTransaction.doNotRetryDTransaction ? 'true' : 'false'}</td>
                  <td>
                    <Translate contentKey={`absaUgdtMicrosrvcGatewayApp.TranStatus.${dTransaction.status}`} />
                  </td>
                  <td>{dTransaction.statusCode}</td>
                  <td>{dTransaction.statusDetails}</td>
                  <td>{dTransaction.retries}</td>
                  <td>
                    {dTransaction.timestamp ? <TextFormat type="date" value={dTransaction.timestamp} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{dTransaction.postedBy}</td>
                  <td>{dTransaction.postedDate}</td>
                  <td>{dTransaction.internalErrorCode}</td>
                  <td>{dTransaction.externalErrorCode}</td>
                  <td>{dTransaction.isCrossCurrency ? 'true' : 'false'}</td>
                  <td>{dTransaction.isCrossBank ? 'true' : 'false'}</td>
                  <td>{dTransaction.currency}</td>
                  <td>{dTransaction.creditAccount}</td>
                  <td>{dTransaction.debitAccount}</td>
                  <td>{dTransaction.phoneNumber}</td>
                  <td>{dTransaction.customerNumber}</td>
                  <td>
                    <Translate contentKey={`absaUgdtMicrosrvcGatewayApp.TranStatus.${dTransaction.tranStatus}`} />
                  </td>
                  <td>{dTransaction.tranStatusDetails}</td>
                  <td>{dTransaction.tranFreeField1}</td>
                  <td>{dTransaction.tranFreeField2}</td>
                  <td>{dTransaction.tranFreeField3}</td>
                  <td>{dTransaction.tranFreeField4}</td>
                  <td>{dTransaction.tranFreeField5}</td>
                  <td>{dTransaction.tranFreeField6}</td>
                  <td>{dTransaction.tranFreeField7}</td>
                  <td>{dTransaction.tranFreeField8}</td>
                  <td>{dTransaction.tranFreeField9}</td>
                  <td>{dTransaction.tranFreeField10}</td>
                  <td>{dTransaction.tranFreeField11}</td>
                  <td>{dTransaction.tranFreeField12}</td>
                  <td>{dTransaction.tranFreeField13}</td>
                  <td>
                    {dTransaction.tranFreeField14 ? (
                      <div>
                        {dTransaction.tranFreeField14ContentType ? (
                          <a onClick={openFile(dTransaction.tranFreeField14ContentType, dTransaction.tranFreeField14)}>
                            <Translate contentKey="entity.action.open">Open</Translate>
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {dTransaction.tranFreeField14ContentType}, {byteSize(dTransaction.tranFreeField14)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{dTransaction.tranFreeField15}</td>
                  <td>
                    {dTransaction.tranFreeField16 ? (
                      <TextFormat type="date" value={dTransaction.tranFreeField16} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{dTransaction.tranFreeField17 ? 'true' : 'false'}</td>
                  <td>{dTransaction.tranFreeField18}</td>
                  <td>{dTransaction.tranFreeField19}</td>
                  <td>
                    {dTransaction.tranFreeField20 ? (
                      <TextFormat type="date" value={dTransaction.tranFreeField20} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {dTransaction.tranFreeField21 ? (
                      <TextFormat type="date" value={dTransaction.tranFreeField21} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{dTransaction.tranFreeField22 ? 'true' : 'false'}</td>
                  <td>
                    {dTransaction.tranFreeField23 ? (
                      <TextFormat type="date" value={dTransaction.tranFreeField23} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{dTransaction.tranFreeField24}</td>
                  <td>{dTransaction.tranFreeField25}</td>
                  <td>{dTransaction.tranFreeField26}</td>
                  <td>{dTransaction.tranFreeField27}</td>
                  <td>{dTransaction.tranFreeField28}</td>
                  <td>
                    {dTransaction.createdAt ? <TextFormat type="date" value={dTransaction.createdAt} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{dTransaction.createdBy}</td>
                  <td>
                    {dTransaction.updatedAt ? <TextFormat type="date" value={dTransaction.updatedAt} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{dTransaction.updatedBy}</td>
                  <td>
                    {dTransaction.transaction ? (
                      <Link to={`/d-transaction-history/${dTransaction.transaction.id}`}>{dTransaction.transaction.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {dTransaction.transaction ? (
                      <Link to={`/d-transaction-summary/${dTransaction.transaction.id}`}>{dTransaction.transaction.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {dTransaction.transaction ? (
                      <Link to={`/d-transaction-details/${dTransaction.transaction.id}`}>{dTransaction.transaction.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td>
                    {dTransaction.customer ? <Link to={`/customer/${dTransaction.customer.id}`}>{dTransaction.customer.id}</Link> : ''}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/d-transaction/${dTransaction.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/d-transaction/${dTransaction.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/d-transaction/${dTransaction.id}/delete`}
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
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.home.notFound">No D Transactions found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default DTransaction;
