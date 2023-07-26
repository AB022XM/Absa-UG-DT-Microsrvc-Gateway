import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Input, InputGroup, FormGroup, Form, Row, Col, Table } from 'reactstrap';
import { openFile, byteSize, Translate, translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IIncomingJSONResponse } from 'app/shared/model/incoming-json-response.model';
import { searchEntities, getEntities } from './incoming-json-response.reducer';

export const IncomingJSONResponse = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [search, setSearch] = useState('');

  const incomingJSONResponseList = useAppSelector(state => state.absaugdtmicrosrvcgateway.incomingJSONResponse.entities);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.incomingJSONResponse.loading);

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
      <h2 id="incoming-json-response-heading" data-cy="IncomingJSONResponseHeading">
        <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.home.title">Incoming JSON Responses</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/incoming-json-response/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.home.createLabel">
              Create new Incoming JSON Response
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
                  placeholder={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.home.search')}
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
        {incomingJSONResponseList && incomingJSONResponseList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.absaTranRef">Absa Tran Ref</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.dtDTransactionId">Dt D Transaction Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.amolDTransactionId">
                    Amol D Transaction Id
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.transactionReferenceNumber">
                    Transaction Reference Number
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.token">Token</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.responseId">Response Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.transactionId">Transaction Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.responseJson">Response Json</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.responseType">Response Type</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.responseDescription">
                    Response Description
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.responseStatus">Response Status</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.additionalInfo">Additional Info</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.timestamp">Timestamp</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.exceptionMessage">Exception Message</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField">Free Field</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField1">Free Field 1</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField2">Free Field 2</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField3">Free Field 3</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField4">Free Field 4</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField5">Free Field 5</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField6">Free Field 6</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField8">Free Field 8</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField9">Free Field 9</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField10">Free Field 10</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField11">Free Field 11</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField12">Free Field 12</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField13">Free Field 13</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField14">Free Field 14</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField15">Free Field 15</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField16">Free Field 16</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField17">Free Field 17</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField18">Free Field 18</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField19">Free Field 19</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.createdAt">Created At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.createdBy">Created By</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.updatedAt">Updated At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.updatedBy">Updated By</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {incomingJSONResponseList.map((incomingJSONResponse, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/incoming-json-response/${incomingJSONResponse.id}`} color="link" size="sm">
                      {incomingJSONResponse.id}
                    </Button>
                  </td>
                  <td>{incomingJSONResponse.absaTranRef}</td>
                  <td>{incomingJSONResponse.dtDTransactionId}</td>
                  <td>{incomingJSONResponse.amolDTransactionId}</td>
                  <td>{incomingJSONResponse.transactionReferenceNumber}</td>
                  <td>{incomingJSONResponse.token}</td>
                  <td>{incomingJSONResponse.responseId}</td>
                  <td>{incomingJSONResponse.transactionId}</td>
                  <td>{incomingJSONResponse.responseJson}</td>
                  <td>{incomingJSONResponse.responseType}</td>
                  <td>{incomingJSONResponse.responseDescription}</td>
                  <td>{incomingJSONResponse.responseStatus}</td>
                  <td>{incomingJSONResponse.additionalInfo}</td>
                  <td>
                    {incomingJSONResponse.timestamp ? (
                      <TextFormat type="date" value={incomingJSONResponse.timestamp} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{incomingJSONResponse.exceptionMessage}</td>
                  <td>{incomingJSONResponse.freeField}</td>
                  <td>{incomingJSONResponse.freeField1}</td>
                  <td>{incomingJSONResponse.freeField2}</td>
                  <td>{incomingJSONResponse.freeField3}</td>
                  <td>{incomingJSONResponse.freeField4}</td>
                  <td>{incomingJSONResponse.freeField5}</td>
                  <td>{incomingJSONResponse.freeField6}</td>
                  <td>{incomingJSONResponse.freeField8}</td>
                  <td>{incomingJSONResponse.freeField9}</td>
                  <td>{incomingJSONResponse.freeField10}</td>
                  <td>{incomingJSONResponse.freeField11}</td>
                  <td>{incomingJSONResponse.freeField12}</td>
                  <td>{incomingJSONResponse.freeField13}</td>
                  <td>{incomingJSONResponse.freeField14}</td>
                  <td>
                    {incomingJSONResponse.freeField15 ? (
                      <div>
                        {incomingJSONResponse.freeField15ContentType ? (
                          <a onClick={openFile(incomingJSONResponse.freeField15ContentType, incomingJSONResponse.freeField15)}>
                            <Translate contentKey="entity.action.open">Open</Translate>
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {incomingJSONResponse.freeField15ContentType}, {byteSize(incomingJSONResponse.freeField15)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{incomingJSONResponse.freeField16}</td>
                  <td>{incomingJSONResponse.freeField17}</td>
                  <td>
                    {incomingJSONResponse.freeField18 ? (
                      <div>
                        {incomingJSONResponse.freeField18ContentType ? (
                          <a onClick={openFile(incomingJSONResponse.freeField18ContentType, incomingJSONResponse.freeField18)}>
                            <Translate contentKey="entity.action.open">Open</Translate>
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {incomingJSONResponse.freeField18ContentType}, {byteSize(incomingJSONResponse.freeField18)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{incomingJSONResponse.freeField19}</td>
                  <td>
                    {incomingJSONResponse.createdAt ? (
                      <TextFormat type="date" value={incomingJSONResponse.createdAt} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{incomingJSONResponse.createdBy}</td>
                  <td>
                    {incomingJSONResponse.updatedAt ? (
                      <TextFormat type="date" value={incomingJSONResponse.updatedAt} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{incomingJSONResponse.updatedBy}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/incoming-json-response/${incomingJSONResponse.id}`}
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
                        to={`/incoming-json-response/${incomingJSONResponse.id}/edit`}
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
                        to={`/incoming-json-response/${incomingJSONResponse.id}/delete`}
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
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.home.notFound">
                No Incoming JSON Responses found
              </Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default IncomingJSONResponse;
