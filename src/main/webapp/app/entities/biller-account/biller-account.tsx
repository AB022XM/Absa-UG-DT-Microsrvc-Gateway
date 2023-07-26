import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Input, InputGroup, FormGroup, Form, Row, Col, Table } from 'reactstrap';
import { Translate, translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IBillerAccount } from 'app/shared/model/biller-account.model';
import { searchEntities, getEntities } from './biller-account.reducer';

export const BillerAccount = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [search, setSearch] = useState('');

  const billerAccountList = useAppSelector(state => state.absaugdtmicrosrvcgateway.billerAccount.entities);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.billerAccount.loading);

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
      <h2 id="biller-account-heading" data-cy="BillerAccountHeading">
        <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.home.title">Biller Accounts</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/biller-account/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.home.createLabel">Create new Biller Account</Translate>
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
                  placeholder={translate('absaUgdtMicrosrvcGatewayApp.billerAccount.home.search')}
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
        {billerAccountList && billerAccountList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.absaTranRef">Absa Tran Ref</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.recordId">Record Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.billerId">Biller Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.billerName">Biller Name</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.billerAccNumber">Biller Acc Number</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.billerAccountDescription">
                    Biller Account Description
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.timestamp">Timestamp</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.billerFreeField1">Biller Free Field 1</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.billerFreeField2">Biller Free Field 2</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.billerFreeField3">Biller Free Field 3</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.billerFreeField4">Biller Free Field 4</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.billerFreeField5">Biller Free Field 5</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.billerFreeField6">Biller Free Field 6</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.billerFreeField7">Biller Free Field 7</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.billerFreeField8">Biller Free Field 8</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.billerFreeField9">Biller Free Field 9</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.billerFreeField10">Biller Free Field 10</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.billerFreeField11">Biller Free Field 11</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.billerFreeField12">Biller Free Field 12</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.billerFreeField13">Biller Free Field 13</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.delflg">Delflg</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.createdAt">Created At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.createdBy">Created By</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.updatedAt">Updated At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.updatedBy">Updated By</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.biller">Biller</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {billerAccountList.map((billerAccount, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/biller-account/${billerAccount.id}`} color="link" size="sm">
                      {billerAccount.id}
                    </Button>
                  </td>
                  <td>{billerAccount.absaTranRef}</td>
                  <td>{billerAccount.recordId}</td>
                  <td>{billerAccount.billerId}</td>
                  <td>{billerAccount.billerName}</td>
                  <td>{billerAccount.billerAccNumber}</td>
                  <td>{billerAccount.billerAccountDescription}</td>
                  <td>
                    {billerAccount.timestamp ? <TextFormat type="date" value={billerAccount.timestamp} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{billerAccount.billerFreeField1}</td>
                  <td>{billerAccount.billerFreeField2}</td>
                  <td>{billerAccount.billerFreeField3}</td>
                  <td>{billerAccount.billerFreeField4}</td>
                  <td>{billerAccount.billerFreeField5}</td>
                  <td>{billerAccount.billerFreeField6}</td>
                  <td>{billerAccount.billerFreeField7}</td>
                  <td>{billerAccount.billerFreeField8}</td>
                  <td>{billerAccount.billerFreeField9}</td>
                  <td>{billerAccount.billerFreeField10}</td>
                  <td>{billerAccount.billerFreeField11}</td>
                  <td>{billerAccount.billerFreeField12}</td>
                  <td>{billerAccount.billerFreeField13}</td>
                  <td>{billerAccount.delflg ? 'true' : 'false'}</td>
                  <td>
                    {billerAccount.createdAt ? <TextFormat type="date" value={billerAccount.createdAt} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{billerAccount.createdBy}</td>
                  <td>
                    {billerAccount.updatedAt ? <TextFormat type="date" value={billerAccount.updatedAt} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{billerAccount.updatedBy}</td>
                  <td>{billerAccount.biller ? <Link to={`/biller/${billerAccount.biller.id}`}>{billerAccount.biller.id}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/biller-account/${billerAccount.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/biller-account/${billerAccount.id}/edit`}
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
                        to={`/biller-account/${billerAccount.id}/delete`}
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
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.home.notFound">No Biller Accounts found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default BillerAccount;
