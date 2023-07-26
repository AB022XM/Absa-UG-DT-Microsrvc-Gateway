import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Input, InputGroup, FormGroup, Form, Row, Col, Table } from 'reactstrap';
import { Translate, translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IDTransactionChannel } from 'app/shared/model/d-transaction-channel.model';
import { searchEntities, getEntities } from './d-transaction-channel.reducer';

export const DTransactionChannel = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [search, setSearch] = useState('');

  const dTransactionChannelList = useAppSelector(state => state.absaugdtmicrosrvcgateway.dTransactionChannel.entities);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.dTransactionChannel.loading);

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
      <h2 id="d-transaction-channel-heading" data-cy="DTransactionChannelHeading">
        <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.home.title">D Transaction Channels</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link
            to="/d-transaction-channel/new"
            className="btn btn-primary jh-create-entity"
            id="jh-create-entity"
            data-cy="entityCreateButton"
          >
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.home.createLabel">
              Create new D Transaction Channel
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
                  placeholder={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.home.search')}
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
        {dTransactionChannelList && dTransactionChannelList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.absaTranRef">Absa Tran Ref</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.billerId">Biller Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.paymentItemCode">Payment Item Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.dtDTransactionId">Dt D Transaction Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.transactionReferenceNumber">
                    Transaction Reference Number
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.externalTranid">External Tranid</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.channelCode">Channel Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.channelName">Channel Name</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.channelDescription">Channel Description</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.timestamp">Timestamp</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText">Chanel Free Text</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText1">Chanel Free Text 1</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText2">Chanel Free Text 2</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText3">Chanel Free Text 3</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText4">Chanel Free Text 4</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText5">Chanel Free Text 5</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText6">Chanel Free Text 6</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText7">Chanel Free Text 7</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText8">Chanel Free Text 8</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText9">Chanel Free Text 9</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText10">Chanel Free Text 10</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText11">Chanel Free Text 11</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText12">Chanel Free Text 12</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText13">Chanel Free Text 13</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText14">Chanel Free Text 14</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText15">Chanel Free Text 15</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText16">Chanel Free Text 16</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText17">Chanel Free Text 17</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText18">Chanel Free Text 18</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText19">Chanel Free Text 19</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText20">Chanel Free Text 20</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText21">Chanel Free Text 21</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText22">Chanel Free Text 22</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText23">Chanel Free Text 23</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText24">Chanel Free Text 24</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.createdAt">Created At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.createdBy">Created By</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.updatedAt">Updated At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.updatedBy">Updated By</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.delflg">Delflg</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.dTransaction">D Transaction</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {dTransactionChannelList.map((dTransactionChannel, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/d-transaction-channel/${dTransactionChannel.id}`} color="link" size="sm">
                      {dTransactionChannel.id}
                    </Button>
                  </td>
                  <td>{dTransactionChannel.absaTranRef}</td>
                  <td>{dTransactionChannel.billerId}</td>
                  <td>{dTransactionChannel.paymentItemCode}</td>
                  <td>{dTransactionChannel.dtDTransactionId}</td>
                  <td>{dTransactionChannel.transactionReferenceNumber}</td>
                  <td>{dTransactionChannel.externalTranid}</td>
                  <td>{dTransactionChannel.channelCode}</td>
                  <td>{dTransactionChannel.channelName}</td>
                  <td>{dTransactionChannel.channelDescription}</td>
                  <td>
                    {dTransactionChannel.timestamp ? (
                      <TextFormat type="date" value={dTransactionChannel.timestamp} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{dTransactionChannel.chanelFreeText}</td>
                  <td>{dTransactionChannel.chanelFreeText1}</td>
                  <td>{dTransactionChannel.chanelFreeText2}</td>
                  <td>{dTransactionChannel.chanelFreeText3}</td>
                  <td>{dTransactionChannel.chanelFreeText4}</td>
                  <td>{dTransactionChannel.chanelFreeText5}</td>
                  <td>{dTransactionChannel.chanelFreeText6}</td>
                  <td>{dTransactionChannel.chanelFreeText7}</td>
                  <td>{dTransactionChannel.chanelFreeText8}</td>
                  <td>{dTransactionChannel.chanelFreeText9}</td>
                  <td>{dTransactionChannel.chanelFreeText10}</td>
                  <td>{dTransactionChannel.chanelFreeText11}</td>
                  <td>{dTransactionChannel.chanelFreeText12}</td>
                  <td>{dTransactionChannel.chanelFreeText13}</td>
                  <td>{dTransactionChannel.chanelFreeText14}</td>
                  <td>{dTransactionChannel.chanelFreeText15}</td>
                  <td>{dTransactionChannel.chanelFreeText16}</td>
                  <td>{dTransactionChannel.chanelFreeText17}</td>
                  <td>{dTransactionChannel.chanelFreeText18}</td>
                  <td>{dTransactionChannel.chanelFreeText19}</td>
                  <td>{dTransactionChannel.chanelFreeText20}</td>
                  <td>{dTransactionChannel.chanelFreeText21}</td>
                  <td>{dTransactionChannel.chanelFreeText22}</td>
                  <td>{dTransactionChannel.chanelFreeText23}</td>
                  <td>{dTransactionChannel.chanelFreeText24}</td>
                  <td>
                    {dTransactionChannel.createdAt ? (
                      <TextFormat type="date" value={dTransactionChannel.createdAt} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{dTransactionChannel.createdBy}</td>
                  <td>
                    {dTransactionChannel.updatedAt ? (
                      <TextFormat type="date" value={dTransactionChannel.updatedAt} format={APP_DATE_FORMAT} />
                    ) : null}
                  </td>
                  <td>{dTransactionChannel.updatedBy}</td>
                  <td>{dTransactionChannel.delflg ? 'true' : 'false'}</td>
                  <td>
                    {dTransactionChannel.dTransaction ? (
                      <Link to={`/d-transaction/${dTransactionChannel.dTransaction.id}`}>{dTransactionChannel.dTransaction.id}</Link>
                    ) : (
                      ''
                    )}
                  </td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button
                        tag={Link}
                        to={`/d-transaction-channel/${dTransactionChannel.id}`}
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
                        to={`/d-transaction-channel/${dTransactionChannel.id}/edit`}
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
                        to={`/d-transaction-channel/${dTransactionChannel.id}/delete`}
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
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.home.notFound">
                No D Transaction Channels found
              </Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default DTransactionChannel;
