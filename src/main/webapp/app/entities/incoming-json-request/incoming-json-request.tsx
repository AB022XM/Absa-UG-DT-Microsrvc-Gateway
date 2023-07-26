import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Input, InputGroup, FormGroup, Form, Row, Col, Table } from 'reactstrap';
import { openFile, byteSize, Translate, translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IIncomingJSONRequest } from 'app/shared/model/incoming-json-request.model';
import { searchEntities, getEntities } from './incoming-json-request.reducer';

export const IncomingJSONRequest = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [search, setSearch] = useState('');

  const incomingJSONRequestList = useAppSelector(state => state.absaugdtmicrosrvcgateway.incomingJSONRequest.entities);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.incomingJSONRequest.loading);

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
      <h2 id="incoming-json-request-heading" data-cy="IncomingJSONRequestHeading">
        <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.home.title">Incoming JSON Requests</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/incoming-json-request/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.home.createLabel">
              Create new Incoming JSON Request
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
                  placeholder={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.home.search')}
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
        {incomingJSONRequestList && incomingJSONRequestList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.absaTranRef">Absa Tran Ref</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.dtDTransactionId">Dt D Transaction Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.amolDTransactionId">
                    Amol D Transaction Id
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.transactionReferenceNumber">
                    Transaction Reference Number
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.token">Token</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.transactionId">Transaction Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.fromEndpoint">From Endpoint</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.toEndpoint">To Endpoint</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.requestJson">Request Json</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.requestType">Request Type</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.requestDescription">Request Description</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.requestStatus">Request Status</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.additionalInfo">Additional Info</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField1">Free Field 1</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField2">Free Field 2</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField3">Free Field 3</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField4">Free Field 4</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField5">Free Field 5</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField6">Free Field 6</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField7">Free Field 7</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField8">Free Field 8</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField9">Free Field 9</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField10">Free Field 10</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField11">Free Field 11</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField12">Free Field 12</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField13">Free Field 13</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField14">Free Field 14</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField15">Free Field 15</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField16">Free Field 16</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField17">Free Field 17</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField18">Free Field 18</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField19">Free Field 19</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField20">Free Field 20</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.timestamp">Timestamp</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.createdAt">Created At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.createdBy">Created By</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.updatedAt">Updated At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.updatedBy">Updated By</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {incomingJSONRequestList.map((incomingJSONRequest, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/incoming-json-request/${incomingJSONRequest.id}`} color="link" size="sm">
                      {incomingJSONRequest.id}
                    </Button>
                  </td>
                  <td>{incomingJSONRequest.absaTranRef}</td>
                  <td>{incomingJSONRequest.dtDTransactionId}</td>
                  <td>{incomingJSONRequest.amolDTransactionId}</td>
                  <td>{incomingJSONRequest.transactionReferenceNumber}</td>
                  <td>{incomingJSONRequest.token}</td>
                  <td>{incomingJSONRequest.transactionId}</td>
                  <td>{incomingJSONRequest.fromEndpoint}</td>
                  <td>{incomingJSONRequest.toEndpoint}</td>
                  <td>{incomingJSONRequest.requestJson}</td>
                  <td>{incomingJSONRequest.requestType}</td>
                  <td>{incomingJSONRequest.requestDescription}</td>
                  <td>{incomingJSONRequest.requestStatus}</td>
                  <td>{incomingJSONRequest.additionalInfo}</td>
                  <td>{incomingJSONRequest.freeField1}</td>
                  <td>{incomingJSONRequest.freeField2}</td>
                  <td>{incomingJSONRequest.freeField3}</td>
                  <td>{incomingJSONRequest.freeField4}</td>
                  <td>{incomingJSONRequest.freeField5}</td>
                  <td>
                    {incomingJSONRequest.freeField6 ? (
                      <div>
                        {incomingJSONRequest.freeField6ContentType ? (
                          <a onClick={openFile(incomingJSONRequest.freeField6ContentType, incomingJSONRequest.freeField6)}>
                            <Translate contentKey="entity.action.open">Open</Translate>
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {incomingJSONRequest.freeField6ContentType}, {byteSize(incomingJSONRequest.freeField6)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>
                    {incomingJSONRequest.freeField7 ? (
                      <div>
                        {incomingJSONRequest.freeField7ContentType ? (
                          <a onClick={openFile(incomingJSONRequest.freeField7ContentType, incomingJSONRequest.freeField7)}>
                            <Translate contentKey="entity.action.open">Open</Translate>
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {incomingJSONRequest.freeField7ContentType}, {byteSize(incomingJSONRequest.freeField7)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>
                    {incomingJSONRequest.freeField8 ? (
                      <div>
                        {incomingJSONRequest.freeField8ContentType ? (
                          <a onClick={openFile(incomingJSONRequest.freeField8ContentType, incomingJSONRequest.freeField8)}>
                            <Translate contentKey="entity.action.open">Open</Translate>
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {incomingJSONRequest.freeField8ContentType}, {byteSize(incomingJSONRequest.freeField8)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{incomingJSONRequest.freeField9}</td>
                  <td>{incomingJSONRequest.freeField10 ? 'true' : 'false'}</td>
                  <td>{incomingJSONRequest.freeField11 ? 'true' : 'false'}</td>
                  <td>{incomingJSONRequest.freeField12}</td>
                  <td>{incomingJSONRequest.freeField13}</td>
                  <td>{incomingJSONRequest.freeField14}</td>
                  <td>{incomingJSONRequest.freeField15}</td>
                  <td>{incomingJSONRequest.freeField16}</td>
                  <td>{incomingJSONRequest.freeField17}</td>
                  <td>{incomingJSONRequest.freeField18}</td>
                  <td>{incomingJSONRequest.freeField19}</td>
                  <td>{incomingJSONRequest.freeField20}</td>
                  <td>
                    {incomingJSONRequest.timestamp ? (
                      <TextFormat type="date" value={incomingJSONRequest.timestamp} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {incomingJSONRequest.createdAt ? (
                      <TextFormat type="date" value={incomingJSONRequest.createdAt} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{incomingJSONRequest.createdBy}</td>
                  <td>
                    {incomingJSONRequest.updatedAt ? (
                      <TextFormat type="date" value={incomingJSONRequest.updatedAt} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{incomingJSONRequest.updatedBy}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/incoming-json-request/${incomingJSONRequest.id}`}
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
                        to={`/incoming-json-request/${incomingJSONRequest.id}/edit`}
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
                        to={`/incoming-json-request/${incomingJSONRequest.id}/delete`}
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
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.home.notFound">
                No Incoming JSON Requests found
              </Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default IncomingJSONRequest;
