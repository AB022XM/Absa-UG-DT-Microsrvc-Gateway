import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Input, InputGroup, FormGroup, Form, Row, Col, Table } from 'reactstrap';
import { openFile, byteSize, Translate, translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IDTransactionSummary } from 'app/shared/model/d-transaction-summary.model';
import { searchEntities, getEntities } from './d-transaction-summary.reducer';

export const DTransactionSummary = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [search, setSearch] = useState('');

  const dTransactionSummaryList = useAppSelector(state => state.absaugdtmicrosrvcgateway.dTransactionSummary.entities);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.dTransactionSummary.loading);

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
      <h2 id="d-transaction-summary-heading" data-cy="DTransactionSummaryHeading">
        <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.home.title">D Transaction Summaries</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/d-transaction-summary/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.home.createLabel">
              Create new D Transaction Summary
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
                  placeholder={translate('absaUgdtMicrosrvcGatewayApp.dTransactionSummary.home.search')}
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
        {dTransactionSummaryList && dTransactionSummaryList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.absaTranRef">Absa Tran Ref</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.billerId">Biller Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.paymentItemCode">Payment Item Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.dtDTransactionId">Dt D Transaction Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.transactionReferenceNumber">
                    Transaction Reference Number
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.recordId">Record Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.transactionId">Transaction Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.transactionType">Transaction Type</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.transactionAmount">Transaction Amount</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.transactionDate">Transaction Date</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.transactionStatus">Transaction Status</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.freeField1">Free Field 1</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.freeField2">Free Field 2</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.freeField3">Free Field 3</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.freeField4">Free Field 4</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.freeField5">Free Field 5</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.freeField6">Free Field 6</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.createdAt">Created At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.createdBy">Created By</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.updatedAt">Updated At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.updatedBy">Updated By</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.errorCode">Error Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.errorMessage">Error Message</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.customer">Customer</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {dTransactionSummaryList.map((dTransactionSummary, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/d-transaction-summary/${dTransactionSummary.id}`} color="link" size="sm">
                      {dTransactionSummary.id}
                    </Button>
                  </td>
                  <td>{dTransactionSummary.absaTranRef}</td>
                  <td>{dTransactionSummary.billerId}</td>
                  <td>{dTransactionSummary.paymentItemCode}</td>
                  <td>{dTransactionSummary.dtDTransactionId}</td>
                  <td>{dTransactionSummary.transactionReferenceNumber}</td>
                  <td>{dTransactionSummary.recordId}</td>
                  <td>{dTransactionSummary.transactionId}</td>
                  <td>{dTransactionSummary.transactionType}</td>
                  <td>{dTransactionSummary.transactionAmount}</td>
                  <td>
                    {dTransactionSummary.transactionDate ? (
                      <TextFormat type="date" value={dTransactionSummary.transactionDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    <Translate contentKey={`absaUgdtMicrosrvcGatewayApp.TranStatus.${dTransactionSummary.transactionStatus}`} />
                  </td>
                  <td>{dTransactionSummary.freeField1}</td>
                  <td>{dTransactionSummary.freeField2}</td>
                  <td>{dTransactionSummary.freeField3}</td>
                  <td>{dTransactionSummary.freeField4}</td>
                  <td>{dTransactionSummary.freeField5}</td>
                  <td>
                    {dTransactionSummary.freeField6 ? (
                      <div>
                        {dTransactionSummary.freeField6ContentType ? (
                          <a onClick={openFile(dTransactionSummary.freeField6ContentType, dTransactionSummary.freeField6)}>
                            <Translate contentKey="entity.action.open">Open</Translate>
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {dTransactionSummary.freeField6ContentType}, {byteSize(dTransactionSummary.freeField6)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>
                    {dTransactionSummary.createdAt ? (
                      <TextFormat type="date" value={dTransactionSummary.createdAt} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{dTransactionSummary.createdBy}</td>
                  <td>
                    {dTransactionSummary.updatedAt ? (
                      <TextFormat type="date" value={dTransactionSummary.updatedAt} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{dTransactionSummary.updatedBy}</td>
                  <td>
                    <Translate contentKey={`absaUgdtMicrosrvcGatewayApp.ErrorCodes.${dTransactionSummary.errorCode}`} />
                  </td>
                  <td>
                    <Translate contentKey={`absaUgdtMicrosrvcGatewayApp.ErrorMessage.${dTransactionSummary.errorMessage}`} />
                  </td>
                  <td>
                    {dTransactionSummary.customer ? (
                      <Link to={`/customer/${dTransactionSummary.customer.id}`}>{dTransactionSummary.customer.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/d-transaction-summary/${dTransactionSummary.id}`}
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
                        to={`/d-transaction-summary/${dTransactionSummary.id}/edit`}
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
                        to={`/d-transaction-summary/${dTransactionSummary.id}/delete`}
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
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.home.notFound">
                No D Transaction Summaries found
              </Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default DTransactionSummary;
