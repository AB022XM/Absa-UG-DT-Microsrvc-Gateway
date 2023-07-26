import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Input, InputGroup, FormGroup, Form, Row, Col, Table } from 'reactstrap';
import { Translate, translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ICustomAudit } from 'app/shared/model/custom-audit.model';
import { searchEntities, getEntities } from './custom-audit.reducer';

export const CustomAudit = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [search, setSearch] = useState('');

  const customAuditList = useAppSelector(state => state.absaugdtmicrosrvcgateway.customAudit.entities);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.customAudit.loading);

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
      <h2 id="custom-audit-heading" data-cy="CustomAuditHeading">
        <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.home.title">Custom Audits</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/custom-audit/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.home.createLabel">Create new Custom Audit</Translate>
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
                  placeholder={translate('absaUgdtMicrosrvcGatewayApp.customAudit.home.search')}
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
        {customAuditList && customAuditList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.absaTranRef">Absa Tran Ref</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.recordId">Record Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.actionId">Action Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.timestamp">Timestamp</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.oldValue">Old Value</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.newValue">New Value</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.changeResaon">Change Resaon</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.description">Description</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.description1">Description 1</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.description2">Description 2</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.description3">Description 3</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.description4">Description 4</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.description5">Description 5</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.description6">Description 6</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.description7">Description 7</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.description8">Description 8</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.description9">Description 9</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText1">Free Text 1</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText2">Free Text 2</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText3">Free Text 3</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText4">Free Text 4</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText5">Free Text 5</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText6">Free Text 6</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText7">Free Text 7</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText8">Free Text 8</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText9">Free Text 9</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText10">Free Text 10</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText11">Free Text 11</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText12">Free Text 12</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText13">Free Text 13</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText14">Free Text 14</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText15">Free Text 15</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText16">Free Text 16</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText17">Free Text 17</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText18">Free Text 18</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText19">Free Text 19</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText20">Free Text 20</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText21">Free Text 21</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText22">Free Text 22</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText23">Free Text 23</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText24">Free Text 24</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText25">Free Text 25</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText26">Free Text 26</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText27">Free Text 27</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.freeText28">Free Text 28</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.createdAt">Created At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.createdBy">Created By</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.updatedAt">Updated At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.updatedBy">Updated By</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {customAuditList.map((customAudit, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/custom-audit/${customAudit.id}`} color="link" size="sm">
                      {customAudit.id}
                    </Button>
                  </td>
                  <td>{customAudit.absaTranRef}</td>
                  <td>{customAudit.recordId}</td>
                  <td>
                    <Translate contentKey={`absaUgdtMicrosrvcGatewayApp.ServiceLevel.${customAudit.actionId}`} />
                  </td>
                  <td>
                    {customAudit.timestamp ? <TextFormat type="date" value={customAudit.timestamp} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{customAudit.oldValue}</td>
                  <td>{customAudit.newValue}</td>
                  <td>{customAudit.changeResaon}</td>
                  <td>{customAudit.description}</td>
                  <td>{customAudit.description1}</td>
                  <td>{customAudit.description2}</td>
                  <td>{customAudit.description3}</td>
                  <td>{customAudit.description4}</td>
                  <td>{customAudit.description5}</td>
                  <td>{customAudit.description6}</td>
                  <td>{customAudit.description7}</td>
                  <td>{customAudit.description8}</td>
                  <td>{customAudit.description9}</td>
                  <td>{customAudit.freeText1}</td>
                  <td>{customAudit.freeText2}</td>
                  <td>{customAudit.freeText3}</td>
                  <td>{customAudit.freeText4}</td>
                  <td>{customAudit.freeText5}</td>
                  <td>{customAudit.freeText6}</td>
                  <td>{customAudit.freeText7}</td>
                  <td>{customAudit.freeText8}</td>
                  <td>{customAudit.freeText9}</td>
                  <td>{customAudit.freeText10}</td>
                  <td>{customAudit.freeText11}</td>
                  <td>{customAudit.freeText12}</td>
                  <td>{customAudit.freeText13}</td>
                  <td>{customAudit.freeText14}</td>
                  <td>{customAudit.freeText15}</td>
                  <td>{customAudit.freeText16}</td>
                  <td>{customAudit.freeText17}</td>
                  <td>{customAudit.freeText18}</td>
                  <td>{customAudit.freeText19}</td>
                  <td>{customAudit.freeText20}</td>
                  <td>{customAudit.freeText21}</td>
                  <td>{customAudit.freeText22}</td>
                  <td>{customAudit.freeText23}</td>
                  <td>{customAudit.freeText24}</td>
                  <td>{customAudit.freeText25}</td>
                  <td>{customAudit.freeText26}</td>
                  <td>{customAudit.freeText27}</td>
                  <td>{customAudit.freeText28}</td>
                  <td>
                    {customAudit.createdAt ? <TextFormat type="date" value={customAudit.createdAt} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{customAudit.createdBy}</td>
                  <td>
                    {customAudit.updatedAt ? <TextFormat type="date" value={customAudit.updatedAt} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{customAudit.updatedBy}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/custom-audit/${customAudit.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/custom-audit/${customAudit.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/custom-audit/${customAudit.id}/delete`}
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
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.home.notFound">No Custom Audits found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default CustomAudit;
