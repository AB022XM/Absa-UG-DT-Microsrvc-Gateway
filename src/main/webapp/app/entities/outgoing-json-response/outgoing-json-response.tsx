import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Input, InputGroup, FormGroup, Form, Row, Col, Table } from 'reactstrap';
import { openFile, byteSize, Translate, translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IOutgoingJSONResponse } from 'app/shared/model/outgoing-json-response.model';
import { searchEntities, getEntities } from './outgoing-json-response.reducer';

export const OutgoingJSONResponse = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [search, setSearch] = useState('');

  const outgoingJSONResponseList = useAppSelector(state => state.absaugdtmicrosrvcgateway.outgoingJSONResponse.entities);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.outgoingJSONResponse.loading);

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
      <h2 id="outgoing-json-response-heading" data-cy="OutgoingJSONResponseHeading">
        <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.home.title">Outgoing JSON Responses</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/outgoing-json-response/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.home.createLabel">
              Create new Outgoing JSON Response
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
                  placeholder={translate('absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.home.search')}
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
        {outgoingJSONResponseList && outgoingJSONResponseList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.recordId">Record Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.responseId">Response Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.transactionId">Transaction Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.responseJson">Response Json</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.responseType">Response Type</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.responseDescription">
                    Response Description
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.toEndpoint">To Endpoint</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.fromEndpoint">From Endpoint</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.responseStatus">Response Status</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.additionalInfo">Additional Info</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.timestamp">Timestamp</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.exceptionMessage">Exception Message</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField">Free Field</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField1">Free Field 1</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField2">Free Field 2</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField3">Free Field 3</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField4">Free Field 4</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField5">Free Field 5</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField6">Free Field 6</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField8">Free Field 8</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField9">Free Field 9</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField10">Free Field 10</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField11">Free Field 11</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField12">Free Field 12</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField13">Free Field 13</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField14">Free Field 14</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField15">Free Field 15</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField16">Free Field 16</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField17">Free Field 17</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField18">Free Field 18</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField19">Free Field 19</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.createdAt">Created At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.createdBy">Created By</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.updatedAt">Updated At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.updatedBy">Updated By</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {outgoingJSONResponseList.map((outgoingJSONResponse, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/outgoing-json-response/${outgoingJSONResponse.id}`} color="link" size="sm">
                      {outgoingJSONResponse.id}
                    </Button>
                  </td>
                  <td>{outgoingJSONResponse.recordId}</td>
                  <td>{outgoingJSONResponse.responseId}</td>
                  <td>{outgoingJSONResponse.transactionId}</td>
                  <td>{outgoingJSONResponse.responseJson}</td>
                  <td>{outgoingJSONResponse.responseType}</td>
                  <td>{outgoingJSONResponse.responseDescription}</td>
                  <td>{outgoingJSONResponse.toEndpoint}</td>
                  <td>{outgoingJSONResponse.fromEndpoint}</td>
                  <td>{outgoingJSONResponse.responseStatus}</td>
                  <td>{outgoingJSONResponse.additionalInfo}</td>
                  <td>
                    {outgoingJSONResponse.timestamp ? (
                      <TextFormat type="date" value={outgoingJSONResponse.timestamp} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{outgoingJSONResponse.exceptionMessage}</td>
                  <td>{outgoingJSONResponse.freeField}</td>
                  <td>{outgoingJSONResponse.freeField1}</td>
                  <td>{outgoingJSONResponse.freeField2}</td>
                  <td>{outgoingJSONResponse.freeField3}</td>
                  <td>{outgoingJSONResponse.freeField4}</td>
                  <td>{outgoingJSONResponse.freeField5}</td>
                  <td>{outgoingJSONResponse.freeField6}</td>
                  <td>{outgoingJSONResponse.freeField8}</td>
                  <td>{outgoingJSONResponse.freeField9}</td>
                  <td>{outgoingJSONResponse.freeField10}</td>
                  <td>{outgoingJSONResponse.freeField11}</td>
                  <td>{outgoingJSONResponse.freeField12}</td>
                  <td>{outgoingJSONResponse.freeField13}</td>
                  <td>{outgoingJSONResponse.freeField14}</td>
                  <td>
                    {outgoingJSONResponse.freeField15 ? (
                      <div>
                        {outgoingJSONResponse.freeField15ContentType ? (
                          <a onClick={openFile(outgoingJSONResponse.freeField15ContentType, outgoingJSONResponse.freeField15)}>
                            <Translate contentKey="entity.action.open">Open</Translate>
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {outgoingJSONResponse.freeField15ContentType}, {byteSize(outgoingJSONResponse.freeField15)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{outgoingJSONResponse.freeField16}</td>
                  <td>{outgoingJSONResponse.freeField17}</td>
                  <td>
                    {outgoingJSONResponse.freeField18 ? (
                      <div>
                        {outgoingJSONResponse.freeField18ContentType ? (
                          <a onClick={openFile(outgoingJSONResponse.freeField18ContentType, outgoingJSONResponse.freeField18)}>
                            <Translate contentKey="entity.action.open">Open</Translate>
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {outgoingJSONResponse.freeField18ContentType}, {byteSize(outgoingJSONResponse.freeField18)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{outgoingJSONResponse.freeField19}</td>
                  <td>
                    {outgoingJSONResponse.createdAt ? (
                      <TextFormat type="date" value={outgoingJSONResponse.createdAt} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{outgoingJSONResponse.createdBy}</td>
                  <td>
                    {outgoingJSONResponse.updatedAt ? (
                      <TextFormat type="date" value={outgoingJSONResponse.updatedAt} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{outgoingJSONResponse.updatedBy}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/outgoing-json-response/${outgoingJSONResponse.id}`}
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
                        to={`/outgoing-json-response/${outgoingJSONResponse.id}/edit`}
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
                        to={`/outgoing-json-response/${outgoingJSONResponse.id}/delete`}
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
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.home.notFound">
                No Outgoing JSON Responses found
              </Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default OutgoingJSONResponse;
