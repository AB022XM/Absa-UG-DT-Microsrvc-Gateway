import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Input, InputGroup, FormGroup, Form, Row, Col, Table } from 'reactstrap';
import { openFile, byteSize, Translate, translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IPaymentConfig } from 'app/shared/model/payment-config.model';
import { searchEntities, getEntities } from './payment-config.reducer';

export const PaymentConfig = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [search, setSearch] = useState('');

  const paymentConfigList = useAppSelector(state => state.absaugdtmicrosrvcgateway.paymentConfig.entities);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.paymentConfig.loading);

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
      <h2 id="payment-config-heading" data-cy="PaymentConfigHeading">
        <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.home.title">Payment Configs</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/payment-config/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.home.createLabel">Create new Payment Config</Translate>
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
                  placeholder={translate('absaUgdtMicrosrvcGatewayApp.paymentConfig.home.search')}
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
        {paymentConfigList && paymentConfigList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.recordId">Record Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.paymentId">Payment Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.paymentName">Payment Name</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.paymentType">Payment Type</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.paymentDescription">Payment Description</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.paymentStatus">Payment Status</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.paymentMethod">Payment Method</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.paymentAmount">Payment Amount</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.additionalConfig">Additional Config</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField1">Free Field 1</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField2">Free Field 2</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField3">Free Field 3</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField4">Free Field 4</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField5">Free Field 5</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField6">Free Field 6</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField8">Free Field 8</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField9">Free Field 9</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField10">Free Field 10</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField11">Free Field 11</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField12">Free Field 12</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField13">Free Field 13</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField14">Free Field 14</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField15">Free Field 15</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField16">Free Field 16</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField17">Free Field 17</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField18">Free Field 18</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField19">Free Field 19</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField20">Free Field 20</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.timestamp">Timestamp</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.createdAt">Created At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.createdBy">Created By</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.updatedAt">Updated At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.updatedBy">Updated By</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {paymentConfigList.map((paymentConfig, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/payment-config/${paymentConfig.id}`} color="link" size="sm">
                      {paymentConfig.id}
                    </Button>
                  </td>
                  <td>{paymentConfig.recordId}</td>
                  <td>{paymentConfig.paymentId}</td>
                  <td>{paymentConfig.paymentName}</td>
                  <td>{paymentConfig.paymentType}</td>
                  <td>{paymentConfig.paymentDescription}</td>
                  <td>{paymentConfig.paymentStatus}</td>
                  <td>
                    <Translate contentKey={`absaUgdtMicrosrvcGatewayApp.ModeOfPayment.${paymentConfig.paymentMethod}`} />
                  </td>
                  <td>{paymentConfig.paymentAmount}</td>
                  <td>{paymentConfig.additionalConfig}</td>
                  <td>{paymentConfig.freeField1}</td>
                  <td>{paymentConfig.freeField2}</td>
                  <td>{paymentConfig.freeField3}</td>
                  <td>{paymentConfig.freeField4}</td>
                  <td>{paymentConfig.freeField5}</td>
                  <td>{paymentConfig.freeField6}</td>
                  <td>{paymentConfig.freeField8}</td>
                  <td>{paymentConfig.freeField9}</td>
                  <td>{paymentConfig.freeField10}</td>
                  <td>{paymentConfig.freeField11}</td>
                  <td>{paymentConfig.freeField12}</td>
                  <td>{paymentConfig.freeField13}</td>
                  <td>{paymentConfig.freeField14}</td>
                  <td>
                    {paymentConfig.freeField15 ? (
                      <div>
                        {paymentConfig.freeField15ContentType ? (
                          <a onClick={openFile(paymentConfig.freeField15ContentType, paymentConfig.freeField15)}>
                            <Translate contentKey="entity.action.open">Open</Translate>
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {paymentConfig.freeField15ContentType}, {byteSize(paymentConfig.freeField15)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{paymentConfig.freeField16}</td>
                  <td>{paymentConfig.freeField17}</td>
                  <td>
                    {paymentConfig.freeField18 ? (
                      <div>
                        {paymentConfig.freeField18ContentType ? (
                          <a onClick={openFile(paymentConfig.freeField18ContentType, paymentConfig.freeField18)}>
                            <Translate contentKey="entity.action.open">Open</Translate>
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {paymentConfig.freeField18ContentType}, {byteSize(paymentConfig.freeField18)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{paymentConfig.freeField19}</td>
                  <td>{paymentConfig.freeField20}</td>
                  <td>
                    {paymentConfig.timestamp ? <TextFormat type="date" value={paymentConfig.timestamp} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>
                    {paymentConfig.createdAt ? <TextFormat type="date" value={paymentConfig.createdAt} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{paymentConfig.createdBy}</td>
                  <td>
                    {paymentConfig.updatedAt ? <TextFormat type="date" value={paymentConfig.updatedAt} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{paymentConfig.updatedBy}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/payment-config/${paymentConfig.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/payment-config/${paymentConfig.id}/edit`}
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
                        to={`/payment-config/${paymentConfig.id}/delete`}
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
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.home.notFound">No Payment Configs found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default PaymentConfig;
