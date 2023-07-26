import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Input, InputGroup, FormGroup, Form, Row, Col, Table } from 'reactstrap';
import { openFile, byteSize, Translate, translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ICustomer } from 'app/shared/model/customer.model';
import { searchEntities, getEntities } from './customer.reducer';

export const Customer = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [search, setSearch] = useState('');

  const customerList = useAppSelector(state => state.absaugdtmicrosrvcgateway.customer.entities);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.customer.loading);

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
      <h2 id="customer-heading" data-cy="CustomerHeading">
        <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.home.title">Customers</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/customer/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.home.createLabel">Create new Customer</Translate>
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
                  placeholder={translate('absaUgdtMicrosrvcGatewayApp.customer.home.search')}
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
        {customerList && customerList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.absaTranRef">Absa Tran Ref</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.billerId">Biller Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.paymentItemCode">Payment Item Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.dtDTransactionId">Dt D Transaction Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.amolDTransactionId">Amol D Transaction Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.transactionReferenceNumber">
                    Transaction Reference Number
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.token">Token</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.transferId">Transfer Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.requestId">Request Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.accountName">Account Name</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.returnCode">Return Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.returnMessage">Return Message</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.externalTXNid">External TX Nid</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.transactionDate">Transaction Date</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.customerId">Customer Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.customerProduct">Customer Product</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.customerType">Customer Type</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.accountCategory">Account Category</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.accountType">Account Type</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.accountNumber">Account Number</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.phoneNumber">Phone Number</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.channel">Channel</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField1">Tran Free Field 1</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField2">Tran Free Field 2</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField3">Tran Free Field 3</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField4">Tran Free Field 4</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField5">Tran Free Field 5</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField6">Tran Free Field 6</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField7">Tran Free Field 7</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField8">Tran Free Field 8</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField9">Tran Free Field 9</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField10">Tran Free Field 10</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField11">Tran Free Field 11</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField12">Tran Free Field 12</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField13">Tran Free Field 13</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField14">Tran Free Field 14</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField15">Tran Free Field 15</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField16">Tran Free Field 16</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField17">Tran Free Field 17</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField18">Tran Free Field 18</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField19">Tran Free Field 19</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField20">Tran Free Field 20</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField21">Tran Free Field 21</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField22">Tran Free Field 22</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField23">Tran Free Field 23</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField24">Tran Free Field 24</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField25">Tran Free Field 25</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField26">Tran Free Field 26</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField27">Tran Free Field 27</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField28">Tran Free Field 28</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField29">Tran Free Field 29</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField30">Tran Free Field 30</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField31">Tran Free Field 31</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField32">Tran Free Field 32</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.tranFreeField33">Tran Free Field 33</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.createdAt">Created At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.createdBy">Created By</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.updatedAt">Updated At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.updatedBy">Updated By</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {customerList.map((customer, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/customer/${customer.id}`} color="link" size="sm">
                      {customer.id}
                    </Button>
                  </td>
                  <td>{customer.absaTranRef}</td>
                  <td>{customer.billerId}</td>
                  <td>{customer.paymentItemCode}</td>
                  <td>{customer.dtDTransactionId}</td>
                  <td>{customer.amolDTransactionId}</td>
                  <td>{customer.transactionReferenceNumber}</td>
                  <td>{customer.token}</td>
                  <td>{customer.transferId}</td>
                  <td>{customer.requestId}</td>
                  <td>{customer.accountName}</td>
                  <td>{customer.returnCode}</td>
                  <td>{customer.returnMessage}</td>
                  <td>{customer.externalTXNid}</td>
                  <td>
                    {customer.transactionDate ? <TextFormat type="date" value={customer.transactionDate} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{customer.customerId}</td>
                  <td>{customer.customerProduct}</td>
                  <td>{customer.customerType}</td>
                  <td>{customer.accountCategory}</td>
                  <td>{customer.accountType}</td>
                  <td>{customer.accountNumber}</td>
                  <td>{customer.phoneNumber}</td>
                  <td>{customer.channel}</td>
                  <td>{customer.tranFreeField1}</td>
                  <td>{customer.tranFreeField2}</td>
                  <td>{customer.tranFreeField3}</td>
                  <td>{customer.tranFreeField4}</td>
                  <td>{customer.tranFreeField5}</td>
                  <td>{customer.tranFreeField6}</td>
                  <td>{customer.tranFreeField7}</td>
                  <td>{customer.tranFreeField8}</td>
                  <td>{customer.tranFreeField9}</td>
                  <td>{customer.tranFreeField10}</td>
                  <td>{customer.tranFreeField11}</td>
                  <td>{customer.tranFreeField12}</td>
                  <td>{customer.tranFreeField13}</td>
                  <td>{customer.tranFreeField14}</td>
                  <td>{customer.tranFreeField15}</td>
                  <td>{customer.tranFreeField16}</td>
                  <td>{customer.tranFreeField17}</td>
                  <td>{customer.tranFreeField18}</td>
                  <td>{customer.tranFreeField19}</td>
                  <td>{customer.tranFreeField20 ? 'true' : 'false'}</td>
                  <td>{customer.tranFreeField21}</td>
                  <td>{customer.tranFreeField22}</td>
                  <td>
                    {customer.tranFreeField23 ? (
                      <div>
                        {customer.tranFreeField23ContentType ? (
                          <a onClick={openFile(customer.tranFreeField23ContentType, customer.tranFreeField23)}>
                            <Translate contentKey="entity.action.open">Open</Translate>
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {customer.tranFreeField23ContentType}, {byteSize(customer.tranFreeField23)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>
                    {customer.tranFreeField24 ? <TextFormat type="date" value={customer.tranFreeField24} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{customer.tranFreeField25}</td>
                  <td>{customer.tranFreeField26}</td>
                  <td>{customer.tranFreeField27}</td>
                  <td>{customer.tranFreeField28}</td>
                  <td>{customer.tranFreeField29}</td>
                  <td>{customer.tranFreeField30}</td>
                  <td>{customer.tranFreeField31}</td>
                  <td>{customer.tranFreeField32}</td>
                  <td>{customer.tranFreeField33}</td>
                  <td>{customer.createdAt ? <TextFormat type="date" value={customer.createdAt} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{customer.createdBy}</td>
                  <td>{customer.updatedAt ? <TextFormat type="date" value={customer.updatedAt} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{customer.updatedBy}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/customer/${customer.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/customer/${customer.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/customer/${customer.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
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
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.home.notFound">No Customers found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Customer;
