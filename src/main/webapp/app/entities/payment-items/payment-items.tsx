import React, { useState, useEffect } from 'react';
import { Link, useLocation, useNavigate } from 'react-router-dom';
import { Button, Input, InputGroup, FormGroup, Form, Row, Col, Table } from 'reactstrap';
import { Translate, translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IPaymentItems } from 'app/shared/model/payment-items.model';
import { searchEntities, getEntities } from './payment-items.reducer';

export const PaymentItems = () => {
  const dispatch = useAppDispatch();

  const location = useLocation();
  const navigate = useNavigate();

  const [search, setSearch] = useState('');

  const paymentItemsList = useAppSelector(state => state.absaugdtmicrosrvcgateway.paymentItems.entities);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.paymentItems.loading);

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
      <h2 id="payment-items-heading" data-cy="PaymentItemsHeading">
        <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.home.title">Payment Items</Translate>
        <div className="d-flex justify-content-end">
          <Button className="me-2" color="info" onClick={handleSyncList} disabled={loading}>
            <FontAwesomeIcon icon="sync" spin={loading} />{' '}
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.home.refreshListLabel">Refresh List</Translate>
          </Button>
          <Link to="/payment-items/new" className="btn btn-primary jh-create-entity" id="jh-create-entity" data-cy="entityCreateButton">
            <FontAwesomeIcon icon="plus" />
            &nbsp;
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.home.createLabel">Create new Payment Items</Translate>
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
                  placeholder={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.home.search')}
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
        {paymentItemsList && paymentItemsList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.absaTranRef">Absa Tran Ref</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.recordId">Record Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.productCategoryId">Product Category Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.billerId">Biller Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.paymentItemCode">Payment Item Code</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.paymentItemId">Payment Item Id</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.paymentItemName">Payment Item Name</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.paymentItemDescription">
                    Payment Item Description
                  </Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.isActive">Is Active</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.hasFixedPrice">Has Fixed Price</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.hasVariablePrice">Has Variable Price</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.hasDiscount">Has Discount</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.hasTax">Has Tax</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.amount">Amount</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.chargeAmount">Charge Amount</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.hasChargeAmount">Has Charge Amount</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.taxAmount">Tax Amount</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.freeText">Free Text</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.freeText1">Free Text 1</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.freeText2">Free Text 2</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.freeText3">Free Text 3</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.freeText4">Free Text 4</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.freeText5">Free Text 5</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.freeText6">Free Text 6</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.freeText7">Free Text 7</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.freeText8">Free Text 8</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.freeText9">Free Text 9</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.freeText10">Free Text 10</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.freeText11">Free Text 11</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.freeText12">Free Text 12</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.freeText13">Free Text 13</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.freeText14">Free Text 14</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.freeText15">Free Text 15</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.freeText16">Free Text 16</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.freeText17">Free Text 17</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.freeText18">Free Text 18</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.freeText19">Free Text 19</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.delflg">Delflg</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.status">Status</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.createdAt">Created At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.updatedAt">Updated At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.deletedAt">Deleted At</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.deletedBy">Deleted By</Translate>
                </th>
                <th>
                  <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.biller">Biller</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {paymentItemsList.map((paymentItems, i) => (
                <tr key={`entity-${i}`} data-cy="entityTable">
                  <td>
                    <Button tag={Link} to={`/payment-items/${paymentItems.id}`} color="link" size="sm">
                      {paymentItems.id}
                    </Button>
                  </td>
                  <td>{paymentItems.absaTranRef}</td>
                  <td>{paymentItems.recordId}</td>
                  <td>{paymentItems.productCategoryId}</td>
                  <td>{paymentItems.billerId}</td>
                  <td>{paymentItems.paymentItemCode}</td>
                  <td>{paymentItems.paymentItemId}</td>
                  <td>{paymentItems.paymentItemName}</td>
                  <td>{paymentItems.paymentItemDescription}</td>
                  <td>{paymentItems.isActive ? 'true' : 'false'}</td>
                  <td>{paymentItems.hasFixedPrice ? 'true' : 'false'}</td>
                  <td>{paymentItems.hasVariablePrice ? 'true' : 'false'}</td>
                  <td>{paymentItems.hasDiscount ? 'true' : 'false'}</td>
                  <td>{paymentItems.hasTax ? 'true' : 'false'}</td>
                  <td>{paymentItems.amount}</td>
                  <td>{paymentItems.chargeAmount}</td>
                  <td>{paymentItems.hasChargeAmount ? 'true' : 'false'}</td>
                  <td>{paymentItems.taxAmount}</td>
                  <td>{paymentItems.freeText}</td>
                  <td>{paymentItems.freeText1}</td>
                  <td>{paymentItems.freeText2}</td>
                  <td>{paymentItems.freeText3}</td>
                  <td>{paymentItems.freeText4}</td>
                  <td>{paymentItems.freeText5}</td>
                  <td>{paymentItems.freeText6}</td>
                  <td>{paymentItems.freeText7}</td>
                  <td>{paymentItems.freeText8}</td>
                  <td>{paymentItems.freeText9}</td>
                  <td>{paymentItems.freeText10}</td>
                  <td>{paymentItems.freeText11}</td>
                  <td>{paymentItems.freeText12}</td>
                  <td>{paymentItems.freeText13}</td>
                  <td>{paymentItems.freeText14}</td>
                  <td>{paymentItems.freeText15}</td>
                  <td>{paymentItems.freeText16}</td>
                  <td>{paymentItems.freeText17}</td>
                  <td>{paymentItems.freeText18}</td>
                  <td>{paymentItems.freeText19}</td>
                  <td>{paymentItems.delflg ? 'true' : 'false'}</td>
                  <td>{paymentItems.status}</td>
                  <td>
                    {paymentItems.createdAt ? <TextFormat type="date" value={paymentItems.createdAt} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>
                    {paymentItems.updatedAt ? <TextFormat type="date" value={paymentItems.updatedAt} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>
                    {paymentItems.deletedAt ? <TextFormat type="date" value={paymentItems.deletedAt} format={APP_DATE_FORMAT} /> : null}
                  </td>
                  <td>{paymentItems.deletedBy}</td>
                  <td>{paymentItems.biller ? <Link to={`/biller/${paymentItems.biller.id}`}>{paymentItems.biller.id}</Link> : ''}</td>
                  <td className="text-end">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`/payment-items/${paymentItems.id}`} color="info" size="sm" data-cy="entityDetailsButton">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`/payment-items/${paymentItems.id}/edit`} color="primary" size="sm" data-cy="entityEditButton">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button
                        tag={Link}
                        to={`/payment-items/${paymentItems.id}/delete`}
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
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.home.notFound">No Payment Items found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

export default PaymentItems;
