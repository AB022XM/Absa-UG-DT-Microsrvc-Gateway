import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Input, InputGroup, FormGroup, Form, Row, Col, Table } from 'reactstrap';
import { openFile, byteSize, Translate, translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IAccountDetails } from 'app/shared/model/account-details.model';
import { searchEntities, getEntities } from './account-details.reducer';

export const AccountDetails = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [search, setSearch] = useState('');

  const accountDetailsList = useAppSelector(state => state.absaugdtmicrosrvcgateway.accountDetails.entities);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.accountDetails.loading);

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
      <h2 id="account-details-heading" data-cy="AccountDetailsHeading">
        <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.home.title">Account Details</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/account-details/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.home.createLabel">Create new Account Details</Translate>
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
                  placeholder={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.home.search')}
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
        {accountDetailsList && accountDetailsList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.absaTranRef">Absa Tran Ref</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.billerId">Biller Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.paymentItemCode">Payment Item Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.dtDTransactionId">Dt D Transaction Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.amolDTransactionId">Amol D Transaction Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.transactionReferenceNumber">
                    Transaction Reference Number
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.token">Token</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.transferId">Transfer Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.requestId">Request Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.accountName">Account Name</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.returnCode">Return Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.returnMessage">Return Message</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.externalTXNid">External TX Nid</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.transactionDate">Transaction Date</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.customerId">Customer Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.customerProduct">Customer Product</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.customerType">Customer Type</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.accountCategory">Account Category</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.accountType">Account Type</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.accountNumber">Account Number</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.phoneNumber">Phone Number</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.channel">Channel</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField1">Tran Free Field 1</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField2">Tran Free Field 2</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField3">Tran Free Field 3</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField4">Tran Free Field 4</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField5">Tran Free Field 5</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField6">Tran Free Field 6</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField7">Tran Free Field 7</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField8">Tran Free Field 8</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField9">Tran Free Field 9</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField10">Tran Free Field 10</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField11">Tran Free Field 11</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField12">Tran Free Field 12</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField13">Tran Free Field 13</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField14">Tran Free Field 14</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField15">Tran Free Field 15</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField16">Tran Free Field 16</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField17">Tran Free Field 17</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField18">Tran Free Field 18</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField19">Tran Free Field 19</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField20">Tran Free Field 20</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField21">Tran Free Field 21</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField22">Tran Free Field 22</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField23">Tran Free Field 23</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField24">Tran Free Field 24</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField25">Tran Free Field 25</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField26">Tran Free Field 26</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField27">Tran Free Field 27</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField28">Tran Free Field 28</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField29">Tran Free Field 29</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField30">Tran Free Field 30</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField31">Tran Free Field 31</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField32">Tran Free Field 32</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField33">Tran Free Field 33</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.createdAt">Created At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.createdBy">Created By</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.updatedAt">Updated At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.updatedBy">Updated By</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {accountDetailsList.map((accountDetails, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/account-details/${accountDetails.id}`} color="link" size="sm">
                      {accountDetails.id}
                    </Button>
                  </td>
                  <td>{accountDetails.absaTranRef}</td>
                  <td>{accountDetails.billerId}</td>
                  <td>{accountDetails.paymentItemCode}</td>
                  <td>{accountDetails.dtDTransactionId}</td>
                  <td>{accountDetails.amolDTransactionId}</td>
                  <td>{accountDetails.transactionReferenceNumber}</td>
                  <td>{accountDetails.token}</td>
                  <td>{accountDetails.transferId}</td>
                  <td>{accountDetails.requestId}</td>
                  <td>{accountDetails.accountName}</td>
                  <td>{accountDetails.returnCode}</td>
                  <td>{accountDetails.returnMessage}</td>
                  <td>{accountDetails.externalTXNid}</td>
                  <td>
                    {accountDetails.transactionDate ? (
                      <TextFormat type="date" value={accountDetails.transactionDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{accountDetails.customerId}</td>
                  <td>{accountDetails.customerProduct}</td>
                  <td>{accountDetails.customerType}</td>
                  <td>{accountDetails.accountCategory}</td>
                  <td>{accountDetails.accountType}</td>
                  <td>{accountDetails.accountNumber}</td>
                  <td>{accountDetails.phoneNumber}</td>
                  <td>{accountDetails.channel}</td>
                  <td>{accountDetails.tranFreeField1}</td>
                  <td>{accountDetails.tranFreeField2}</td>
                  <td>{accountDetails.tranFreeField3}</td>
                  <td>{accountDetails.tranFreeField4}</td>
                  <td>{accountDetails.tranFreeField5}</td>
                  <td>{accountDetails.tranFreeField6}</td>
                  <td>{accountDetails.tranFreeField7}</td>
                  <td>{accountDetails.tranFreeField8}</td>
                  <td>{accountDetails.tranFreeField9}</td>
                  <td>{accountDetails.tranFreeField10}</td>
                  <td>{accountDetails.tranFreeField11}</td>
                  <td>{accountDetails.tranFreeField12}</td>
                  <td>{accountDetails.tranFreeField13}</td>
                  <td>{accountDetails.tranFreeField14}</td>
                  <td>{accountDetails.tranFreeField15}</td>
                  <td>{accountDetails.tranFreeField16}</td>
                  <td>{accountDetails.tranFreeField17}</td>
                  <td>{accountDetails.tranFreeField18}</td>
                  <td>{accountDetails.tranFreeField19}</td>
                  <td>{accountDetails.tranFreeField20 ? 'true' : 'false'}</td>
                  <td>{accountDetails.tranFreeField21}</td>
                  <td>{accountDetails.tranFreeField22}</td>
                  <td>
                    {accountDetails.tranFreeField23 ? (
                      <div>
                        {accountDetails.tranFreeField23ContentType ? (
                          <a onClick={openFile(accountDetails.tranFreeField23ContentType, accountDetails.tranFreeField23)}>
                            <Translate contentKey="entity.action.open">Open</Translate>
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {accountDetails.tranFreeField23ContentType}, {byteSize(accountDetails.tranFreeField23)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>
                    {accountDetails.tranFreeField24 ? (
                      <TextFormat type="date" value={accountDetails.tranFreeField24} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{accountDetails.tranFreeField25}</td>
                  <td>{accountDetails.tranFreeField26}</td>
                  <td>{accountDetails.tranFreeField27}</td>
                  <td>{accountDetails.tranFreeField28}</td>
                  <td>{accountDetails.tranFreeField29}</td>
                  <td>{accountDetails.tranFreeField30}</td>
                  <td>{accountDetails.tranFreeField31}</td>
                  <td>{accountDetails.tranFreeField32}</td>
                  <td>{accountDetails.tranFreeField33}</td>
                  <td>
                    {accountDetails.createdAt ? <TextFormat type="date" value={accountDetails.createdAt} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{accountDetails.createdBy}</td>
                  <td>
                    {accountDetails.updatedAt ? <TextFormat type="date" value={accountDetails.updatedAt} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{accountDetails.updatedBy}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/account-details/${accountDetails.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/account-details/${accountDetails.id}/edit`}
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
                        to={`/account-details/${accountDetails.id}/delete`}
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
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.home.notFound">No Account Details found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default AccountDetails;
