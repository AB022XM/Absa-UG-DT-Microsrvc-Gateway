import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Input, InputGroup, FormGroup, Form, Row, Col, Table } from 'reactstrap';
import { openFile, byteSize, Translate, translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IResponseInfo } from 'app/shared/model/response-info.model';
import { searchEntities, getEntities } from './response-info.reducer';

export const ResponseInfo = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [search, setSearch] = useState('');

  const responseInfoList = useAppSelector(state => state.absaugdtmicrosrvcgateway.responseInfo.entities);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.responseInfo.loading);

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
      <h2 id="response-info-heading" data-cy="ResponseInfoHeading">
        <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.home.title">Response Infos</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/response-info/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.home.createLabel">Create new Response Info</Translate>
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
                  placeholder={translate('absaUgdtMicrosrvcGatewayApp.responseInfo.home.search')}
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
        {responseInfoList && responseInfoList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.absaTranRef">Absa Tran Ref</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.billerId">Biller Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.paymentItemCode">Payment Item Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.dtDTransactionId">Dt D Transaction Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.transactionReferenceNumber">
                    Transaction Reference Number
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.externalTranid">External Tranid</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.responseCode">Response Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.responseMessage">Response Message</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.responseBody">Response Body</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.timestamp">Timestamp</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.requestRef">Request Ref</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.status">Status</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.freeField1">Free Field 1</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.freeField2">Free Field 2</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.freeField3">Free Field 3</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.freeField4">Free Field 4</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.freeField5">Free Field 5</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.freeField6">Free Field 6</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.time">Time</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.errorCode">Error Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.errorMessage">Error Message</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.createdAt">Created At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.createdBy">Created By</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.updatedAt">Updated At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.updatedBy">Updated By</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {responseInfoList.map((responseInfo, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/response-info/${responseInfo.id}`} color="link" size="sm">
                      {responseInfo.id}
                    </Button>
                  </td>
                  <td>{responseInfo.absaTranRef}</td>
                  <td>{responseInfo.billerId}</td>
                  <td>{responseInfo.paymentItemCode}</td>
                  <td>{responseInfo.dtDTransactionId}</td>
                  <td>{responseInfo.transactionReferenceNumber}</td>
                  <td>{responseInfo.externalTranid}</td>
                  <td>{responseInfo.responseCode}</td>
                  <td>{responseInfo.responseMessage}</td>
                  <td>{responseInfo.responseBody}</td>
                  <td>
                    {responseInfo.timestamp ? <TextFormat type="date" value={responseInfo.timestamp} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{responseInfo.requestRef}</td>
                  <td>
                    <Translate contentKey={`absaUgdtMicrosrvcGatewayApp.TranStatus.${responseInfo.status}`} />
                  </td>
                  <td>{responseInfo.freeField1}</td>
                  <td>
                    {responseInfo.freeField2 ? (
                      <div>
                        {responseInfo.freeField2ContentType ? (
                          <a onClick={openFile(responseInfo.freeField2ContentType, responseInfo.freeField2)}>
                            <Translate contentKey="entity.action.open">Open</Translate>
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {responseInfo.freeField2ContentType}, {byteSize(responseInfo.freeField2)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{responseInfo.freeField3}</td>
                  <td>{responseInfo.freeField4}</td>
                  <td>{responseInfo.freeField5}</td>
                  <td>{responseInfo.freeField6}</td>
                  <td>{responseInfo.time ? <TextFormat type="date" value={responseInfo.time} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>
                    <Translate contentKey={`absaUgdtMicrosrvcGatewayApp.ErrorCodes.${responseInfo.errorCode}`} />
                  </td>
                  <td>
                    <Translate contentKey={`absaUgdtMicrosrvcGatewayApp.ErrorMessage.${responseInfo.errorMessage}`} />
                  </td>
                  <td>
                    {responseInfo.createdAt ? <TextFormat type="date" value={responseInfo.createdAt} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{responseInfo.createdBy}</td>
                  <td>
                    {responseInfo.updatedAt ? <TextFormat type="date" value={responseInfo.updatedAt} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{responseInfo.updatedBy}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/response-info/${responseInfo.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/response-info/${responseInfo.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/response-info/${responseInfo.id}/delete`}
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
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.home.notFound">No Response Infos found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default ResponseInfo;
