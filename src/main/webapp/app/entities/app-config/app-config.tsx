import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Input, InputGroup, FormGroup, Form, Row, Col, Table } from 'reactstrap';
import { openFile, byteSize, Translate, translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IAppConfig } from 'app/shared/model/app-config.model';
import { searchEntities, getEntities } from './app-config.reducer';

export const AppConfig = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [search, setSearch] = useState('');

  const appConfigList = useAppSelector(state => state.absaugdtmicrosrvcgateway.appConfig.entities);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.appConfig.loading);

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
      <h2 id="app-config-heading" data-cy="AppConfigHeading">
        <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.home.title">App Configs</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/app-config/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.home.createLabel">Create new App Config</Translate>
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
                  placeholder={translate('absaUgdtMicrosrvcGatewayApp.appConfig.home.search')}
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
        {appConfigList && appConfigList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.absaTranRef">Absa Tran Ref</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.recordId">Record Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.configId">Config Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.configName">Config Name</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.configValue">Config Value</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.configType">Config Type</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.configDescription">Config Description</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.configStatus">Config Status</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.freeField1">Free Field 1</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.freeField2">Free Field 2</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.freeField3">Free Field 3</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.freeField4">Free Field 4</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.freeField5">Free Field 5</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.freeField6">Free Field 6</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.freeField7">Free Field 7</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.freeField8">Free Field 8</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.freeField9">Free Field 9</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.freeField10">Free Field 10</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.freeField11">Free Field 11</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.freeField12">Free Field 12</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.freeField13">Free Field 13</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.freeField14">Free Field 14</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.freeField15">Free Field 15</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.freeField16">Free Field 16</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.freeField17">Free Field 17</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.freeField18">Free Field 18</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.freeField19">Free Field 19</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.delflg">Delflg</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.timestamp">Timestamp</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.createdAt">Created At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.createdBy">Created By</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.updatedAt">Updated At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.updatedBy">Updated By</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.apps">Apps</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {appConfigList.map((appConfig, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/app-config/${appConfig.id}`} color="link" size="sm">
                      {appConfig.id}
                    </Button>
                  </td>
                  <td>{appConfig.absaTranRef}</td>
                  <td>{appConfig.recordId}</td>
                  <td>{appConfig.configId}</td>
                  <td>{appConfig.configName}</td>
                  <td>{appConfig.configValue}</td>
                  <td>{appConfig.configType}</td>
                  <td>{appConfig.configDescription}</td>
                  <td>{appConfig.configStatus}</td>
                  <td>{appConfig.freeField1}</td>
                  <td>{appConfig.freeField2}</td>
                  <td>{appConfig.freeField3}</td>
                  <td>{appConfig.freeField4}</td>
                  <td>{appConfig.freeField5}</td>
                  <td>{appConfig.freeField6}</td>
                  <td>{appConfig.freeField7}</td>
                  <td>{appConfig.freeField8}</td>
                  <td>{appConfig.freeField9}</td>
                  <td>{appConfig.freeField10}</td>
                  <td>{appConfig.freeField11}</td>
                  <td>{appConfig.freeField12}</td>
                  <td>{appConfig.freeField13}</td>
                  <td>
                    {appConfig.freeField14 ? (
                      <div>
                        {appConfig.freeField14ContentType ? (
                          <a onClick={openFile(appConfig.freeField14ContentType, appConfig.freeField14)}>
                            <Translate contentKey="entity.action.open">Open</Translate>
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {appConfig.freeField14ContentType}, {byteSize(appConfig.freeField14)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>
                    {appConfig.freeField15 ? (
                      <div>
                        {appConfig.freeField15ContentType ? (
                          <a onClick={openFile(appConfig.freeField15ContentType, appConfig.freeField15)}>
                            <Translate contentKey="entity.action.open">Open</Translate>
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {appConfig.freeField15ContentType}, {byteSize(appConfig.freeField15)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{appConfig.freeField16}</td>
                  <td>{appConfig.freeField17}</td>
                  <td>
                    {appConfig.freeField18 ? (
                      <div>
                        {appConfig.freeField18ContentType ? (
                          <a onClick={openFile(appConfig.freeField18ContentType, appConfig.freeField18)}>
                            <Translate contentKey="entity.action.open">Open</Translate>
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {appConfig.freeField18ContentType}, {byteSize(appConfig.freeField18)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{appConfig.freeField19}</td>
                  <td>{appConfig.delflg ? 'true' : 'false'}</td>
                  <td>{appConfig.timestamp ? <TextFormat type="date" value={appConfig.timestamp} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{appConfig.createdAt ? <TextFormat type="date" value={appConfig.createdAt} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{appConfig.createdBy}</td>
                  <td>{appConfig.updatedAt ? <TextFormat type="date" value={appConfig.updatedAt} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{appConfig.updatedBy}</td>
                  <td>{appConfig.apps ? <Link to={`/apps/${appConfig.apps.id}`}>{appConfig.apps.id}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/app-config/${appConfig.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/app-config/${appConfig.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/app-config/${appConfig.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
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
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.home.notFound">No App Configs found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default AppConfig;
