import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Input, InputGroup, FormGroup, Form, Row, Col, Table } from 'reactstrap';
import { Translate, translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ILiquidation } from 'app/shared/model/liquidation.model';
import { searchEntities, getEntities } from './liquidation.reducer';

export const Liquidation = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [search, setSearch] = useState('');

  const liquidationList = useAppSelector(state => state.absaugdtmicrosrvcgateway.liquidation.entities);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.liquidation.loading);

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
      <h2 id="liquidation-heading" data-cy="LiquidationHeading">
        <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.home.title">Liquidations</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/liquidation/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.home.createLabel">Create new Liquidation</Translate>
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
                  placeholder={translate('absaUgdtMicrosrvcGatewayApp.liquidation.home.search')}
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
        {liquidationList && liquidationList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.recordId">Record Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.serviceLevel">Service Level</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.timestamp">Timestamp</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.partnerCode">Partner Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.amount">Amount</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.currency">Currency</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.receiverBankcode">Receiver Bankcode</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.receiverAccountNumber">Receiver Account Number</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.beneficiaryName">Beneficiary Name</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.instructionId">Instruction Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.senderToReceiverInfo">Sender To Receiver Info</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText1">Free Text 1</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText2">Free Text 2</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText3">Free Text 3</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText4">Free Text 4</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText5">Free Text 5</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText6">Free Text 6</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText7">Free Text 7</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText8">Free Text 8</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText9">Free Text 9</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText10">Free Text 10</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText11">Free Text 11</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText12">Free Text 12</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText13">Free Text 13</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText14">Free Text 14</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText15">Free Text 15</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText16">Free Text 16</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText17">Free Text 17</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText18">Free Text 18</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText19">Free Text 19</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText20">Free Text 20</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText21">Free Text 21</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText22">Free Text 22</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText23">Free Text 23</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText24">Free Text 24</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText25">Free Text 25</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText26">Free Text 26</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText27">Free Text 27</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.freeText28">Free Text 28</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.createdAt">Created At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.createdBy">Created By</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.updatedAt">Updated At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.updatedBy">Updated By</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {liquidationList.map((liquidation, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/liquidation/${liquidation.id}`} color="link" size="sm">
                      {liquidation.id}
                    </Button>
                  </td>
                  <td>{liquidation.recordId}</td>
                  <td>
                    <Translate contentKey={`absaUgdtMicrosrvcGatewayApp.ServiceLevel.${liquidation.serviceLevel}`} />
                  </td>
                  <td>
                    {liquidation.timestamp ? <TextFormat type="date" value={liquidation.timestamp} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{liquidation.partnerCode}</td>
                  <td>{liquidation.amount}</td>
                  <td>{liquidation.currency}</td>
                  <td>{liquidation.receiverBankcode}</td>
                  <td>{liquidation.receiverAccountNumber}</td>
                  <td>{liquidation.beneficiaryName}</td>
                  <td>{liquidation.instructionId}</td>
                  <td>{liquidation.senderToReceiverInfo}</td>
                  <td>{liquidation.freeText1}</td>
                  <td>{liquidation.freeText2}</td>
                  <td>{liquidation.freeText3}</td>
                  <td>{liquidation.freeText4}</td>
                  <td>{liquidation.freeText5}</td>
                  <td>{liquidation.freeText6}</td>
                  <td>{liquidation.freeText7}</td>
                  <td>{liquidation.freeText8}</td>
                  <td>{liquidation.freeText9}</td>
                  <td>{liquidation.freeText10}</td>
                  <td>{liquidation.freeText11}</td>
                  <td>{liquidation.freeText12}</td>
                  <td>{liquidation.freeText13}</td>
                  <td>{liquidation.freeText14}</td>
                  <td>{liquidation.freeText15}</td>
                  <td>{liquidation.freeText16}</td>
                  <td>{liquidation.freeText17}</td>
                  <td>{liquidation.freeText18}</td>
                  <td>{liquidation.freeText19}</td>
                  <td>{liquidation.freeText20}</td>
                  <td>{liquidation.freeText21}</td>
                  <td>{liquidation.freeText22}</td>
                  <td>{liquidation.freeText23}</td>
                  <td>{liquidation.freeText24}</td>
                  <td>{liquidation.freeText25}</td>
                  <td>{liquidation.freeText26}</td>
                  <td>{liquidation.freeText27}</td>
                  <td>{liquidation.freeText28}</td>
                  <td>
                    {liquidation.createdAt ? <TextFormat type="date" value={liquidation.createdAt} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{liquidation.createdBy}</td>
                  <td>
                    {liquidation.updatedAt ? <TextFormat type="date" value={liquidation.updatedAt} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{liquidation.updatedBy}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/liquidation/${liquidation.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/liquidation/${liquidation.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/liquidation/${liquidation.id}/delete`} color="danger" size="sm" data-cy="entityDeleteButton">
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
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.home.notFound">No Liquidations found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default Liquidation;
