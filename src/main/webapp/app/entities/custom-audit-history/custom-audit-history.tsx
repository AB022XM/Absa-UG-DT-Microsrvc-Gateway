import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Input, InputGroup, FormGroup, Form, Row, Col, Table } from 'reactstrap';
import { Translate, translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ICustomAuditHistory } from 'app/shared/model/custom-audit-history.model';
import { searchEntities, getEntities } from './custom-audit-history.reducer';

export const CustomAuditHistory = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [search, setSearch] = useState('');

  const customAuditHistoryList = useAppSelector(state => state.absaugdtmicrosrvcgateway.customAuditHistory.entities);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.customAuditHistory.loading);

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
      <h2 id="custom-audit-history-heading" data-cy="CustomAuditHistoryHeading">
        <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.home.title">Custom Audit Histories</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/custom-audit-history/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.home.createLabel">
              Create new Custom Audit History
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
                  placeholder={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.home.search')}
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
        {customAuditHistoryList && customAuditHistoryList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.id">Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.recordId">Record Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.actionId">Action Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.timestamp">Timestamp</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.oldValue">Old Value</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.newValue">New Value</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.changeReason">Change Reason</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.description">Description</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.description1">Description 1</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.description2">Description 2</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.description3">Description 3</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.description4">Description 4</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.description5">Description 5</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.description6">Description 6</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.description7">Description 7</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.description8">Description 8</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.description9">Description 9</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText1">Free Text 1</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText2">Free Text 2</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText3">Free Text 3</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText4">Free Text 4</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText5">Free Text 5</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText6">Free Text 6</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText7">Free Text 7</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText8">Free Text 8</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText9">Free Text 9</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText10">Free Text 10</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText11">Free Text 11</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText12">Free Text 12</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText13">Free Text 13</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText14">Free Text 14</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText15">Free Text 15</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText16">Free Text 16</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText17">Free Text 17</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText18">Free Text 18</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText19">Free Text 19</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText20">Free Text 20</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText21">Free Text 21</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText22">Free Text 22</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText23">Free Text 23</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText24">Free Text 24</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText25">Free Text 25</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText26">Free Text 26</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText27">Free Text 27</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText28">Free Text 28</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.createdAt">Created At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.createdBy">Created By</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.updatedAt">Updated At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.updatedBy">Updated By</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {customAuditHistoryList.map((customAuditHistory, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/custom-audit-history/${customAuditHistory.id}`} color="link" size="sm">
                      {customAuditHistory.id}
                    </Button>
                  </td>
                  <td>{customAuditHistory.recordId}</td>
                  <td>
                    <Translate contentKey={`absaUgdtMicrosrvcGatewayApp.ServiceLevel.${customAuditHistory.actionId}`} />
                  </td>
                  <td>
                    {customAuditHistory.timestamp ? (
                      <TextFormat type="date" value={customAuditHistory.timestamp} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{customAuditHistory.oldValue}</td>
                  <td>{customAuditHistory.newValue}</td>
                  <td>{customAuditHistory.changeReason}</td>
                  <td>{customAuditHistory.description}</td>
                  <td>{customAuditHistory.description1}</td>
                  <td>{customAuditHistory.description2}</td>
                  <td>{customAuditHistory.description3}</td>
                  <td>{customAuditHistory.description4}</td>
                  <td>{customAuditHistory.description5}</td>
                  <td>{customAuditHistory.description6}</td>
                  <td>{customAuditHistory.description7}</td>
                  <td>{customAuditHistory.description8}</td>
                  <td>{customAuditHistory.description9}</td>
                  <td>{customAuditHistory.freeText1}</td>
                  <td>{customAuditHistory.freeText2}</td>
                  <td>{customAuditHistory.freeText3}</td>
                  <td>{customAuditHistory.freeText4}</td>
                  <td>{customAuditHistory.freeText5}</td>
                  <td>{customAuditHistory.freeText6}</td>
                  <td>{customAuditHistory.freeText7}</td>
                  <td>{customAuditHistory.freeText8}</td>
                  <td>{customAuditHistory.freeText9}</td>
                  <td>{customAuditHistory.freeText10}</td>
                  <td>{customAuditHistory.freeText11}</td>
                  <td>{customAuditHistory.freeText12}</td>
                  <td>{customAuditHistory.freeText13}</td>
                  <td>{customAuditHistory.freeText14}</td>
                  <td>{customAuditHistory.freeText15}</td>
                  <td>{customAuditHistory.freeText16}</td>
                  <td>{customAuditHistory.freeText17}</td>
                  <td>{customAuditHistory.freeText18}</td>
                  <td>{customAuditHistory.freeText19}</td>
                  <td>{customAuditHistory.freeText20}</td>
                  <td>{customAuditHistory.freeText21}</td>
                  <td>{customAuditHistory.freeText22}</td>
                  <td>{customAuditHistory.freeText23}</td>
                  <td>{customAuditHistory.freeText24}</td>
                  <td>{customAuditHistory.freeText25}</td>
                  <td>{customAuditHistory.freeText26}</td>
                  <td>{customAuditHistory.freeText27}</td>
                  <td>{customAuditHistory.freeText28}</td>
                  <td>
                    {customAuditHistory.createdAt ? (
                      <TextFormat type="date" value={customAuditHistory.createdAt} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{customAuditHistory.createdBy}</td>
                  <td>
                    {customAuditHistory.updatedAt ? (
                      <TextFormat type="date" value={customAuditHistory.updatedAt} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{customAuditHistory.updatedBy}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/custom-audit-history/${customAuditHistory.id}`}
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
                        to={`/custom-audit-history/${customAuditHistory.id}/edit`}
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
                        to={`/custom-audit-history/${customAuditHistory.id}/delete`}
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
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.home.notFound">
                No Custom Audit Histories found
              </Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default CustomAuditHistory;
