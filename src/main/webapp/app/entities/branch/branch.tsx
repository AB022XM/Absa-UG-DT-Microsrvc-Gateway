import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Input, InputGroup, FormGroup, Form, Row, Col, Table } from 'reactstrap';
import { Translate, translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IBranch } from 'app/shared/model/branch.model';
import { searchEntities, getEntities } from './branch.reducer';

export const Branch = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [search, setSearch] = useState('');

  const branchList = useAppSelector(state => state.absaugdtmicrosrvcgateway.branch.entities);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.branch.loading);

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
      <h2 id="branch-heading" data-cy="BranchHeading">
        <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.home.title">Branches</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/branch/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.home.createLabel">Create new Branch</Translate>
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
                  placeholder={translate('absaUgdtMicrosrvcGatewayApp.branch.home.search')}
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
        {branchList && branchList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.absaTranRef">Absa Tran Ref</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.recordId">Record Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.addressId">Address Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.bankId">Bank Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchId">Branch Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchName">Branch Name</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchSwiftCode">Branch Swift Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchPhoneNumber">Branch Phone Number</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchEmail">Branch Email</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField1">Branch Free Field 1</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField3">Branch Free Field 3</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField4">Branch Free Field 4</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField5">Branch Free Field 5</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField6">Branch Free Field 6</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField7">Branch Free Field 7</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField8">Branch Free Field 8</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField9">Branch Free Field 9</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField10">Branch Free Field 10</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField11">Branch Free Field 11</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField12">Branch Free Field 12</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField13">Branch Free Field 13</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField14">Branch Free Field 14</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField15">Branch Free Field 15</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField16">Branch Free Field 16</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField17">Branch Free Field 17</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField18">Branch Free Field 18</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField19">Branch Free Field 19</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField20">Branch Free Field 20</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField21">Branch Free Field 21</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField22">Branch Free Field 22</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField23">Branch Free Field 23</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.branchFreeField24">Branch Free Field 24</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.createdAt">Created At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.createdBy">Created By</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.updatedAt">Updated At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.updatedBy">Updated By</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.timestamp">Timestamp</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.status">Status</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.bank">Bank</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {branchList.map((branch, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/branch/${branch.id}`} color="link" size="sm">
                      {branch.id}
                    </Button>
                  </td>
                  <td>{branch.absaTranRef}</td>
                  <td>{branch.recordId}</td>
                  <td>{branch.addressId}</td>
                  <td>{branch.bankId}</td>
                  <td>{branch.branchId}</td>
                  <td>{branch.branchName}</td>
                  <td>{branch.branchSwiftCode}</td>
                  <td>{branch.branchPhoneNumber}</td>
                  <td>{branch.branchEmail}</td>
                  <td>{branch.branchFreeField1}</td>
                  <td>{branch.branchFreeField3}</td>
                  <td>{branch.branchFreeField4}</td>
                  <td>{branch.branchFreeField5}</td>
                  <td>{branch.branchFreeField6}</td>
                  <td>{branch.branchFreeField7}</td>
                  <td>{branch.branchFreeField8}</td>
                  <td>{branch.branchFreeField9}</td>
                  <td>{branch.branchFreeField10}</td>
                  <td>{branch.branchFreeField11}</td>
                  <td>{branch.branchFreeField12}</td>
                  <td>{branch.branchFreeField13}</td>
                  <td>{branch.branchFreeField14}</td>
                  <td>{branch.branchFreeField15}</td>
                  <td>{branch.branchFreeField16}</td>
                  <td>{branch.branchFreeField17}</td>
                  <td>{branch.branchFreeField18}</td>
                  <td>{branch.branchFreeField19}</td>
                  <td>{branch.branchFreeField20}</td>
                  <td>{branch.branchFreeField21}</td>
                  <td>{branch.branchFreeField22}</td>
                  <td>{branch.branchFreeField23}</td>
                  <td>{branch.branchFreeField24}</td>
                  <td>{branch.createdAt ? <TextFormat type="date" value={branch.createdAt} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{branch.createdBy}</td>
                  <td>{branch.updatedAt ? <TextFormat type="date" value={branch.updatedAt} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{branch.updatedBy}</td>
                  <td>{branch.timestamp ? <TextFormat type="date" value={branch.timestamp} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>
                    <Translate contentKey={`absaUgdtMicrosrvcGatewayApp.RecordStatus.${branch.status}`} />
                  </td>
                  <td>{branch.bank ? <Link to={`/bank/${branch.bank.id}`}>{branch.bank.id}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/branch/${branch.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/branch/${branch.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/branch/${branch.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
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
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.home.notFound">No Branches found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Branch;
