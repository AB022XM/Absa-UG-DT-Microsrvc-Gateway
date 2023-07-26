import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Input, InputGroup, FormGroup, Form, Row, Col, Table } from 'reactstrap';
import { Translate, translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IBank } from 'app/shared/model/bank.model';
import { searchEntities, getEntities } from './bank.reducer';

export const Bank = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [search, setSearch] = useState('');

  const bankList = useAppSelector(state => state.absaugdtmicrosrvcgateway.bank.entities);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.bank.loading);

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
      <h2 id="bank-heading" data-cy="BankHeading">
        <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.home.title">Banks</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/bank/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.home.createLabel">Create new Bank</Translate>
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
                  placeholder={translate('absaUgdtMicrosrvcGatewayApp.bank.home.search')}
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
        {bankList && bankList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.absaTranRef">Absa Tran Ref</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.billerId">Biller Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.paymentItemCode">Payment Item Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.dtDTransactionId">Dt D Transaction Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.amolDTransactionId">Amol D Transaction Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankName">Bank Name</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankSwiftCode">Bank Swift Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankBranchId">Bank Branch Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankPhoneNumber">Bank Phone Number</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankEmail">Bank Email</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField1">Bank Free Field 1</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField3">Bank Free Field 3</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField4">Bank Free Field 4</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField5">Bank Free Field 5</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField6">Bank Free Field 6</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField7">Bank Free Field 7</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField8">Bank Free Field 8</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField9">Bank Free Field 9</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField10">Bank Free Field 10</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField11">Bank Free Field 11</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField12">Bank Free Field 12</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField13">Bank Free Field 13</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField14">Bank Free Field 14</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField15">Bank Free Field 15</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField16">Bank Free Field 16</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField17">Bank Free Field 17</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField18">Bank Free Field 18</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField19">Bank Free Field 19</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField20">Bank Free Field 20</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField21">Bank Free Field 21</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField22">Bank Free Field 22</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField23">Bank Free Field 23</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.bankFreeField24">Bank Free Field 24</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.createdAt">Created At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.createdBy">Created By</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.updatedAt">Updated At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.updatedBy">Updated By</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.delflg">Delflg</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.status">Status</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {bankList.map((bank, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/bank/${bank.id}`} color="link" size="sm">
                      {bank.id}
                    </Button>
                  </td>
                  <td>{bank.absaTranRef}</td>
                  <td>{bank.billerId}</td>
                  <td>{bank.paymentItemCode}</td>
                  <td>{bank.dtDTransactionId}</td>
                  <td>{bank.amolDTransactionId}</td>
                  <td>{bank.bankName}</td>
                  <td>{bank.bankSwiftCode}</td>
                  <td>{bank.bankBranchId}</td>
                  <td>{bank.bankPhoneNumber}</td>
                  <td>{bank.bankEmail}</td>
                  <td>{bank.bankFreeField1}</td>
                  <td>{bank.bankFreeField3}</td>
                  <td>{bank.bankFreeField4}</td>
                  <td>{bank.bankFreeField5}</td>
                  <td>{bank.bankFreeField6}</td>
                  <td>{bank.bankFreeField7}</td>
                  <td>{bank.bankFreeField8}</td>
                  <td>{bank.bankFreeField9}</td>
                  <td>{bank.bankFreeField10}</td>
                  <td>{bank.bankFreeField11}</td>
                  <td>{bank.bankFreeField12}</td>
                  <td>{bank.bankFreeField13}</td>
                  <td>{bank.bankFreeField14}</td>
                  <td>{bank.bankFreeField15}</td>
                  <td>{bank.bankFreeField16}</td>
                  <td>{bank.bankFreeField17}</td>
                  <td>{bank.bankFreeField18}</td>
                  <td>{bank.bankFreeField19}</td>
                  <td>{bank.bankFreeField20}</td>
                  <td>{bank.bankFreeField21}</td>
                  <td>{bank.bankFreeField22}</td>
                  <td>{bank.bankFreeField23}</td>
                  <td>{bank.bankFreeField24}</td>
                  <td>{bank.createdAt ? <TextFormat type="date" value={bank.createdAt} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{bank.createdBy}</td>
                  <td>{bank.updatedAt ? <TextFormat type="date" value={bank.updatedAt} format={APP_DATE_FORMAT} /> : null}</td>
                  <td>{bank.updatedBy}</td>
                  <td>{bank.delflg ? 'true' : 'false'}</td>
                  <td>
                    <Translate contentKey={`absaUgdtMicrosrvcGatewayApp.RecordStatus.${bank.status}`} />
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/bank/${bank.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/bank/${bank.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/bank/${bank.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
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
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.home.notFound">No Banks found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Bank;
