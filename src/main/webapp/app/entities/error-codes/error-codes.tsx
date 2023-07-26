import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Input, InputGroup, FormGroup, Form, Row, Col, Table } from 'reactstrap';
import { Translate, translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IErrorCodes } from 'app/shared/model/error-codes.model';
import { searchEntities, getEntities } from './error-codes.reducer';

export const ErrorCodes = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [search, setSearch] = useState('');

  const errorCodesList = useAppSelector(state => state.absaugdtmicrosrvcgateway.errorCodes.entities);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.errorCodes.loading);

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
      <h2 id="error-codes-heading" data-cy="ErrorCodesHeading">
        <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.home.title">Error Codes</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/error-codes/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.home.createLabel">Create new Error Codes</Translate>
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
                  placeholder={translate('absaUgdtMicrosrvcGatewayApp.errorCodes.home.search')}
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
        {errorCodesList && errorCodesList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.absaTranRef">Absa Tran Ref</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.recordId">Record Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.paymentItemCode">Payment Item Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.dtDTransactionId">Dt D Transaction Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.transactionReferenceNumber">
                    Transaction Reference Number
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.externalTranid">External Tranid</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.errorCode">Error Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.errorMessage">Error Message</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.responseCode">Response Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.responseMessage">Response Message</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.responseBody">Response Body</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.timestamp">Timestamp</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.requestRef">Request Ref</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.status">Status</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.customerField1">Customer Field 1</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.customerField2">Customer Field 2</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.customerField3">Customer Field 3</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.customerField4">Customer Field 4</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.customerField5">Customer Field 5</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.customerField6">Customer Field 6</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.customerField7">Customer Field 7</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.customerField8">Customer Field 8</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.createdAt">Created At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.createdBy">Created By</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.updatedAt">Updated At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.updatedBy">Updated By</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {errorCodesList.map((errorCodes, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/error-codes/${errorCodes.id}`} color="link" size="sm">
                      {errorCodes.id}
                    </Button>
                  </td>
                  <td>{errorCodes.absaTranRef}</td>
                  <td>{errorCodes.recordId}</td>
                  <td>{errorCodes.paymentItemCode}</td>
                  <td>{errorCodes.dtDTransactionId}</td>
                  <td>{errorCodes.transactionReferenceNumber}</td>
                  <td>{errorCodes.externalTranid}</td>
                  <td>{errorCodes.errorCode}</td>
                  <td>
                    <Translate contentKey={`absaUgdtMicrosrvcGatewayApp.ErrorMessage.${errorCodes.errorMessage}`} />
                  </td>
                  <td>{errorCodes.responseCode}</td>
                  <td>{errorCodes.responseMessage}</td>
                  <td>{errorCodes.responseBody}</td>
                  <td>{errorCodes.timestamp ? <TextFormat type="date" value={errorCodes.timestamp} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{errorCodes.requestRef}</td>
                  <td>
                    <Translate contentKey={`absaUgdtMicrosrvcGatewayApp.TranStatus.${errorCodes.status}`} />
                  </td>
                  <td>{errorCodes.customerField1}</td>
                  <td>{errorCodes.customerField2}</td>
                  <td>{errorCodes.customerField3}</td>
                  <td>{errorCodes.customerField4}</td>
                  <td>{errorCodes.customerField5}</td>
                  <td>{errorCodes.customerField6}</td>
                  <td>{errorCodes.customerField7}</td>
                  <td>{errorCodes.customerField8}</td>
                  <td>{errorCodes.createdAt ? <TextFormat type="date" value={errorCodes.createdAt} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{errorCodes.createdBy}</td>
                  <td>{errorCodes.updatedAt ? <TextFormat type="date" value={errorCodes.updatedAt} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{errorCodes.updatedBy}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/error-codes/${errorCodes.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/error-codes/${errorCodes.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/error-codes/${errorCodes.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
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
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.home.notFound">No Error Codes found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default ErrorCodes;
