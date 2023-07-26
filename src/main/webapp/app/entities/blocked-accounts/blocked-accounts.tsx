import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Input, InputGroup, FormGroup, Form, Row, Col, Table } from 'reactstrap';
import { Translate, translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IBlockedAccounts } from 'app/shared/model/blocked-accounts.model';
import { searchEntities, getEntities } from './blocked-accounts.reducer';

export const BlockedAccounts = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [search, setSearch] = useState('');

  const blockedAccountsList = useAppSelector(state => state.absaugdtmicrosrvcgateway.blockedAccounts.entities);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.blockedAccounts.loading);

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
      <h2 id="blocked-accounts-heading" data-cy="BlockedAccountsHeading">
        <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.home.title">Blocked Accounts</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/blocked-accounts/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.home.createLabel">Create new Blocked Accounts</Translate>
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
                  placeholder={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.home.search')}
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
        {blockedAccountsList && blockedAccountsList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.absaTranRef">Absa Tran Ref</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.customerId">Customer Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.customerAccountNumber">
                    Customer Account Number
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.dtDTransactionId">Dt D Transaction Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockId">Block Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockCode">Block Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockDate">Block Date</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockType">Block Type</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockStatus">Block Status</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReason">Block Reason</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode1">Block Reason Code 1</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode2">Block Reason Code 2</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReason1">Block Reason 1</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode3">Block Reason Code 3</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode4">Block Reason Code 4</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.startDate">Start Date</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.endDate">End Date</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.isTemp">Is Temp</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockFreeText">Block Free Text</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockFreeText1">Block Free Text 1</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockFreeText2">Block Free Text 2</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockFreeText3">Block Free Text 3</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockFreeText4">Block Free Text 4</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockFreeText5">Block Free Text 5</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockFreeText6">Block Free Text 6</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode5">Block Reason Code 5</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode6">Block Reason Code 6</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode7">Block Reason Code 7</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode8">Block Reason Code 8</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode9">Block Reason Code 9</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode10">Block Reason Code 10</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode11">Block Reason Code 11</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode12">Block Reason Code 12</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode13">Block Reason Code 13</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode14">Block Reason Code 14</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode15">Block Reason Code 15</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode16">Block Reason Code 16</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.createdAt">Created At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.createdBy">Created By</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.updatedAt">Updated At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.updatedBy">Updated By</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.delflg">Delflg</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.status">Status</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {blockedAccountsList.map((blockedAccounts, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/blocked-accounts/${blockedAccounts.id}`} color="link" size="sm">
                      {blockedAccounts.id}
                    </Button>
                  </td>
                  <td>{blockedAccounts.absaTranRef}</td>
                  <td>{blockedAccounts.customerId}</td>
                  <td>{blockedAccounts.customerAccountNumber}</td>
                  <td>{blockedAccounts.dtDTransactionId}</td>
                  <td>{blockedAccounts.blockId}</td>
                  <td>{blockedAccounts.blockCode}</td>
                  <td>
                    {blockedAccounts.blockDate ? (
                      <TextFormat type="date" value={blockedAccounts.blockDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{blockedAccounts.blockType}</td>
                  <td>{blockedAccounts.blockStatus}</td>
                  <td>{blockedAccounts.blockReason}</td>
                  <td>{blockedAccounts.blockReasonCode1}</td>
                  <td>{blockedAccounts.blockReasonCode2}</td>
                  <td>{blockedAccounts.blockReason1}</td>
                  <td>{blockedAccounts.blockReasonCode3}</td>
                  <td>{blockedAccounts.blockReasonCode4}</td>
                  <td>
                    {blockedAccounts.startDate ? (
                      <TextFormat type="date" value={blockedAccounts.startDate} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>
                    {blockedAccounts.endDate ? <TextFormat type="date" value={blockedAccounts.endDate} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{blockedAccounts.isTemp ? 'true' : 'false'}</td>
                  <td>{blockedAccounts.blockFreeText}</td>
                  <td>{blockedAccounts.blockFreeText1}</td>
                  <td>{blockedAccounts.blockFreeText2}</td>
                  <td>{blockedAccounts.blockFreeText3}</td>
                  <td>{blockedAccounts.blockFreeText4}</td>
                  <td>{blockedAccounts.blockFreeText5}</td>
                  <td>{blockedAccounts.blockFreeText6}</td>
                  <td>{blockedAccounts.blockReasonCode5}</td>
                  <td>{blockedAccounts.blockReasonCode6}</td>
                  <td>{blockedAccounts.blockReasonCode7}</td>
                  <td>{blockedAccounts.blockReasonCode8}</td>
                  <td>{blockedAccounts.blockReasonCode9}</td>
                  <td>{blockedAccounts.blockReasonCode10}</td>
                  <td>{blockedAccounts.blockReasonCode11}</td>
                  <td>{blockedAccounts.blockReasonCode12}</td>
                  <td>{blockedAccounts.blockReasonCode13}</td>
                  <td>{blockedAccounts.blockReasonCode14}</td>
                  <td>{blockedAccounts.blockReasonCode15}</td>
                  <td>{blockedAccounts.blockReasonCode16}</td>
                  <td>
                    {blockedAccounts.createdAt ? (
                      <TextFormat type="date" value={blockedAccounts.createdAt} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{blockedAccounts.createdBy}</td>
                  <td>
                    {blockedAccounts.updatedAt ? (
                      <TextFormat type="date" value={blockedAccounts.updatedAt} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{blockedAccounts.updatedBy}</td>
                  <td>{blockedAccounts.delflg ? 'true' : 'false'}</td>
                  <td>
                    <Translate contentKey={`absaUgdtMicrosrvcGatewayApp.RecordStatus.${blockedAccounts.status}`} />
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/blocked-accounts/${blockedAccounts.id}`}
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
                        to={`/blocked-accounts/${blockedAccounts.id}/edit`}
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
                        to={`/blocked-accounts/${blockedAccounts.id}/delete`}
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
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.home.notFound">No Blocked Accounts found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default BlockedAccounts;
