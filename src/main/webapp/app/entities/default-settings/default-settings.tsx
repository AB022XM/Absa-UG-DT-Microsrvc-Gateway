import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Input, InputGroup, FormGroup, Form, Row, Col, Table } from 'reactstrap';
import { openFile, byteSize, Translate, translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IDefaultSettings } from 'app/shared/model/default-settings.model';
import { searchEntities, getEntities } from './default-settings.reducer';

export const DefaultSettings = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [search, setSearch] = useState('');

  const defaultSettingsList = useAppSelector(state => state.absaugdtmicrosrvcgateway.defaultSettings.entities);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.defaultSettings.loading);

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
      <h2 id="default-settings-heading" data-cy="DefaultSettingsHeading">
        <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.home.title">Default Settings</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/default-settings/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.home.createLabel">Create new Default Settings</Translate>
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
                  placeholder={translate('absaUgdtMicrosrvcGatewayApp.defaultSettings.home.search')}
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
        {defaultSettingsList && defaultSettingsList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.absaTranRef">Absa Tran Ref</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.dtDTransactionId">Dt D Transaction Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.amolDTransactionId">Amol D Transaction Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.transactionReferenceNumber">
                    Transaction Reference Number
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.token">Token</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.thirdPartyDTransactionId">
                    Third Party D Transaction Id
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.defaultSettingCode">Default Setting Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.jsonSettings">Json Settings</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.appConfig">App Config</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.appName">App Name</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField">Free Field</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField1">Free Field 1</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField2">Free Field 2</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField3">Free Field 3</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField4">Free Field 4</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField5">Free Field 5</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField6">Free Field 6</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField8">Free Field 8</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField9">Free Field 9</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField10">Free Field 10</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField11">Free Field 11</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField12">Free Field 12</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField13">Free Field 13</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField14">Free Field 14</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField15">Free Field 15</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField16">Free Field 16</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField17">Free Field 17</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField18">Free Field 18</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField19">Free Field 19</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.timestamp">Timestamp</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.recordStatus">Record Status</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.createdAt">Created At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.createdBy">Created By</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.updatedAt">Updated At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.updatedby">Updatedby</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {defaultSettingsList.map((defaultSettings, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/default-settings/${defaultSettings.id}`} color="link" size="sm">
                      {defaultSettings.id}
                    </Button>
                  </td>
                  <td>{defaultSettings.absaTranRef}</td>
                  <td>{defaultSettings.dtDTransactionId}</td>
                  <td>{defaultSettings.amolDTransactionId}</td>
                  <td>{defaultSettings.transactionReferenceNumber}</td>
                  <td>{defaultSettings.token}</td>
                  <td>{defaultSettings.thirdPartyDTransactionId}</td>
                  <td>{defaultSettings.defaultSettingCode}</td>
                  <td>{defaultSettings.jsonSettings}</td>
                  <td>{defaultSettings.appConfig}</td>
                  <td>{defaultSettings.appName}</td>
                  <td>{defaultSettings.freeField}</td>
                  <td>{defaultSettings.freeField1}</td>
                  <td>{defaultSettings.freeField2}</td>
                  <td>{defaultSettings.freeField3}</td>
                  <td>{defaultSettings.freeField4}</td>
                  <td>{defaultSettings.freeField5}</td>
                  <td>{defaultSettings.freeField6}</td>
                  <td>{defaultSettings.freeField8}</td>
                  <td>{defaultSettings.freeField9}</td>
                  <td>{defaultSettings.freeField10}</td>
                  <td>{defaultSettings.freeField11}</td>
                  <td>{defaultSettings.freeField12}</td>
                  <td>{defaultSettings.freeField13}</td>
                  <td>{defaultSettings.freeField14}</td>
                  <td>
                    {defaultSettings.freeField15 ? (
                      <div>
                        {defaultSettings.freeField15ContentType ? (
                          <a onClick={openFile(defaultSettings.freeField15ContentType, defaultSettings.freeField15)}>
                            <Translate contentKey="entity.action.open">Open</Translate>
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {defaultSettings.freeField15ContentType}, {byteSize(defaultSettings.freeField15)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{defaultSettings.freeField16}</td>
                  <td>{defaultSettings.freeField17}</td>
                  <td>
                    {defaultSettings.freeField18 ? (
                      <div>
                        {defaultSettings.freeField18ContentType ? (
                          <a onClick={openFile(defaultSettings.freeField18ContentType, defaultSettings.freeField18)}>
                            <Translate contentKey="entity.action.open">Open</Translate>
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {defaultSettings.freeField18ContentType}, {byteSize(defaultSettings.freeField18)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{defaultSettings.freeField19}</td>
                  <td>
                    {defaultSettings.timestamp ? (
                      <TextFormat type="date" value={defaultSettings.timestamp} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    <Translate contentKey={`absaUgdtMicrosrvcGatewayApp.RecordStatus.${defaultSettings.recordStatus}`} />
                  </td>
                  <td>
                    {defaultSettings.createdAt ? (
                      <TextFormat type="date" value={defaultSettings.createdAt} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{defaultSettings.createdBy}</td>
                  <td>
                    {defaultSettings.updatedAt ? (
                      <TextFormat type="date" value={defaultSettings.updatedAt} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{defaultSettings.updatedby}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/default-settings/${defaultSettings.id}`}
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
                        to={`/default-settings/${defaultSettings.id}/edit`}
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
                        to={`/default-settings/${defaultSettings.id}/delete`}
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
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.home.notFound">No Default Settings found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default DefaultSettings;
