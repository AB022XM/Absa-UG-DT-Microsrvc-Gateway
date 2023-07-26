import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Input, InputGroup, FormGroup, Form, Row, Col, Table } from 'reactstrap';
import { openFile, byteSize, Translate, translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IGenericConfigs } from 'app/shared/model/generic-configs.model';
import { searchEntities, getEntities } from './generic-configs.reducer';

export const GenericConfigs = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [search, setSearch] = useState('');

  const genericConfigsList = useAppSelector(state => state.absaugdtmicrosrvcgateway.genericConfigs.entities);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.genericConfigs.loading);

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
      <h2 id="generic-configs-heading" data-cy="GenericConfigsHeading">
        <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.home.title">Generic Configs</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/generic-configs/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.home.createLabel">Create new Generic Configs</Translate>
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
                  placeholder={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.home.search')}
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
        {genericConfigsList && genericConfigsList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.absaTranRef">Absa Tran Ref</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.recordId">Record Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.configId">Config Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.configName">Config Name</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.configsDetails">Configs Details</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.additionalConfigs">Additional Configs</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField">Free Field</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField1">Free Field 1</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField2">Free Field 2</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField3">Free Field 3</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField4">Free Field 4</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField5">Free Field 5</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField6">Free Field 6</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField8">Free Field 8</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField9">Free Field 9</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField10">Free Field 10</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField11">Free Field 11</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField12">Free Field 12</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField13">Free Field 13</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField14">Free Field 14</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField15">Free Field 15</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField16">Free Field 16</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField17">Free Field 17</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField18">Free Field 18</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField19">Free Field 19</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField20">Free Field 20</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField21">Free Field 21</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField22">Free Field 22</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField23">Free Field 23</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField24">Free Field 24</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField25">Free Field 25</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField26">Free Field 26</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField27">Free Field 27</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField28">Free Field 28</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField29">Free Field 29</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField30">Free Field 30</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField31">Free Field 31</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField32">Free Field 32</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField33">Free Field 33</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField34">Free Field 34</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.timestamp">Timestamp</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.recordStatus">Record Status</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {genericConfigsList.map((genericConfigs, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/generic-configs/${genericConfigs.id}`} color="link" size="sm">
                      {genericConfigs.id}
                    </Button>
                  </td>
                  <td>{genericConfigs.absaTranRef}</td>
                  <td>{genericConfigs.recordId}</td>
                  <td>{genericConfigs.configId}</td>
                  <td>{genericConfigs.configName}</td>
                  <td>{genericConfigs.configsDetails}</td>
                  <td>{genericConfigs.additionalConfigs}</td>
                  <td>{genericConfigs.freeField ? 'true' : 'false'}</td>
                  <td>{genericConfigs.freeField1 ? 'true' : 'false'}</td>
                  <td>{genericConfigs.freeField2}</td>
                  <td>{genericConfigs.freeField3}</td>
                  <td>{genericConfigs.freeField4}</td>
                  <td>{genericConfigs.freeField5}</td>
                  <td>{genericConfigs.freeField6}</td>
                  <td>{genericConfigs.freeField8}</td>
                  <td>{genericConfigs.freeField9}</td>
                  <td>{genericConfigs.freeField10}</td>
                  <td>{genericConfigs.freeField11}</td>
                  <td>{genericConfigs.freeField12}</td>
                  <td>{genericConfigs.freeField13}</td>
                  <td>{genericConfigs.freeField14}</td>
                  <td>
                    {genericConfigs.freeField15 ? (
                      <div>
                        {genericConfigs.freeField15ContentType ? (
                          <a onClick={openFile(genericConfigs.freeField15ContentType, genericConfigs.freeField15)}>
                            <Translate contentKey="entity.action.open">Open</Translate>
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {genericConfigs.freeField15ContentType}, {byteSize(genericConfigs.freeField15)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{genericConfigs.freeField16}</td>
                  <td>{genericConfigs.freeField17}</td>
                  <td>
                    {genericConfigs.freeField18 ? (
                      <div>
                        {genericConfigs.freeField18ContentType ? (
                          <a onClick={openFile(genericConfigs.freeField18ContentType, genericConfigs.freeField18)}>
                            <Translate contentKey="entity.action.open">Open</Translate>
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {genericConfigs.freeField18ContentType}, {byteSize(genericConfigs.freeField18)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{genericConfigs.freeField19}</td>
                  <td>{genericConfigs.freeField20}</td>
                  <td>{genericConfigs.freeField21}</td>
                  <td>{genericConfigs.freeField22}</td>
                  <td>{genericConfigs.freeField23}</td>
                  <td>{genericConfigs.freeField24}</td>
                  <td>
                    {genericConfigs.freeField25 ? (
                      <div>
                        {genericConfigs.freeField25ContentType ? (
                          <a onClick={openFile(genericConfigs.freeField25ContentType, genericConfigs.freeField25)}>
                            <Translate contentKey="entity.action.open">Open</Translate>
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {genericConfigs.freeField25ContentType}, {byteSize(genericConfigs.freeField25)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{genericConfigs.freeField26}</td>
                  <td>{genericConfigs.freeField27}</td>
                  <td>
                    {genericConfigs.freeField28 ? (
                      <div>
                        {genericConfigs.freeField28ContentType ? (
                          <a onClick={openFile(genericConfigs.freeField28ContentType, genericConfigs.freeField28)}>
                            <Translate contentKey="entity.action.open">Open</Translate>
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {genericConfigs.freeField28ContentType}, {byteSize(genericConfigs.freeField28)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td>{genericConfigs.freeField29}</td>
                  <td>{genericConfigs.freeField30}</td>
                  <td>{genericConfigs.freeField31}</td>
                  <td>{genericConfigs.freeField32}</td>
                  <td>{genericConfigs.freeField33}</td>
                  <td>{genericConfigs.freeField34}</td>
                  <td>
                    {genericConfigs.timestamp ? <TextFormat type="date" value={genericConfigs.timestamp} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>
                    <Translate contentKey={`absaUgdtMicrosrvcGatewayApp.RecordStatus.${genericConfigs.recordStatus}`} />
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/generic-configs/${genericConfigs.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/generic-configs/${genericConfigs.id}/edit`}
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
                        to={`/generic-configs/${genericConfigs.id}/delete`}
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
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.home.notFound">No Generic Configs found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default GenericConfigs;
