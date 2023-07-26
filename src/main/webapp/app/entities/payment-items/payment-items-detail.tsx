import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './payment-items.reducer';

export const PaymentItemsDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const paymentItemsEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.paymentItems.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="paymentItemsDetailsHeading">
          <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.detail.title">PaymentItems</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{paymentItemsEntity.id}</dd>
          <dt>
            <span id="absaTranRef">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.absaTranRef">Absa Tran Ref</Translate>
            </span>
          </dt>
          <dd>{paymentItemsEntity.absaTranRef}</dd>
          <dt>
            <span id="recordId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.recordId">Record Id</Translate>
            </span>
          </dt>
          <dd>{paymentItemsEntity.recordId}</dd>
          <dt>
            <span id="productCategoryId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.productCategoryId">Product Category Id</Translate>
            </span>
          </dt>
          <dd>{paymentItemsEntity.productCategoryId}</dd>
          <dt>
            <span id="billerId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.billerId">Biller Id</Translate>
            </span>
          </dt>
          <dd>{paymentItemsEntity.billerId}</dd>
          <dt>
            <span id="paymentItemCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.paymentItemCode">Payment Item Code</Translate>
            </span>
          </dt>
          <dd>{paymentItemsEntity.paymentItemCode}</dd>
          <dt>
            <span id="paymentItemId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.paymentItemId">Payment Item Id</Translate>
            </span>
          </dt>
          <dd>{paymentItemsEntity.paymentItemId}</dd>
          <dt>
            <span id="paymentItemName">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.paymentItemName">Payment Item Name</Translate>
            </span>
          </dt>
          <dd>{paymentItemsEntity.paymentItemName}</dd>
          <dt>
            <span id="paymentItemDescription">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.paymentItemDescription">Payment Item Description</Translate>
            </span>
          </dt>
          <dd>{paymentItemsEntity.paymentItemDescription}</dd>
          <dt>
            <span id="isActive">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.isActive">Is Active</Translate>
            </span>
          </dt>
          <dd>{paymentItemsEntity.isActive ? 'true' : 'false'}</dd>
          <dt>
            <span id="hasFixedPrice">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.hasFixedPrice">Has Fixed Price</Translate>
            </span>
          </dt>
          <dd>{paymentItemsEntity.hasFixedPrice ? 'true' : 'false'}</dd>
          <dt>
            <span id="hasVariablePrice">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.hasVariablePrice">Has Variable Price</Translate>
            </span>
          </dt>
          <dd>{paymentItemsEntity.hasVariablePrice ? 'true' : 'false'}</dd>
          <dt>
            <span id="hasDiscount">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.hasDiscount">Has Discount</Translate>
            </span>
          </dt>
          <dd>{paymentItemsEntity.hasDiscount ? 'true' : 'false'}</dd>
          <dt>
            <span id="hasTax">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.hasTax">Has Tax</Translate>
            </span>
          </dt>
          <dd>{paymentItemsEntity.hasTax ? 'true' : 'false'}</dd>
          <dt>
            <span id="amount">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.amount">Amount</Translate>
            </span>
          </dt>
          <dd>{paymentItemsEntity.amount}</dd>
          <dt>
            <span id="chargeAmount">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.chargeAmount">Charge Amount</Translate>
            </span>
          </dt>
          <dd>{paymentItemsEntity.chargeAmount}</dd>
          <dt>
            <span id="hasChargeAmount">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.hasChargeAmount">Has Charge Amount</Translate>
            </span>
          </dt>
          <dd>{paymentItemsEntity.hasChargeAmount ? 'true' : 'false'}</dd>
          <dt>
            <span id="taxAmount">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.taxAmount">Tax Amount</Translate>
            </span>
          </dt>
          <dd>{paymentItemsEntity.taxAmount}</dd>
          <dt>
            <span id="freeText">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.freeText">Free Text</Translate>
            </span>
          </dt>
          <dd>{paymentItemsEntity.freeText}</dd>
          <dt>
            <span id="freeText1">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.freeText1">Free Text 1</Translate>
            </span>
          </dt>
          <dd>{paymentItemsEntity.freeText1}</dd>
          <dt>
            <span id="freeText2">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.freeText2">Free Text 2</Translate>
            </span>
          </dt>
          <dd>{paymentItemsEntity.freeText2}</dd>
          <dt>
            <span id="freeText3">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.freeText3">Free Text 3</Translate>
            </span>
          </dt>
          <dd>{paymentItemsEntity.freeText3}</dd>
          <dt>
            <span id="freeText4">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.freeText4">Free Text 4</Translate>
            </span>
          </dt>
          <dd>{paymentItemsEntity.freeText4}</dd>
          <dt>
            <span id="freeText5">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.freeText5">Free Text 5</Translate>
            </span>
          </dt>
          <dd>{paymentItemsEntity.freeText5}</dd>
          <dt>
            <span id="freeText6">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.freeText6">Free Text 6</Translate>
            </span>
          </dt>
          <dd>{paymentItemsEntity.freeText6}</dd>
          <dt>
            <span id="freeText7">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.freeText7">Free Text 7</Translate>
            </span>
          </dt>
          <dd>{paymentItemsEntity.freeText7}</dd>
          <dt>
            <span id="freeText8">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.freeText8">Free Text 8</Translate>
            </span>
          </dt>
          <dd>{paymentItemsEntity.freeText8}</dd>
          <dt>
            <span id="freeText9">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.freeText9">Free Text 9</Translate>
            </span>
          </dt>
          <dd>{paymentItemsEntity.freeText9}</dd>
          <dt>
            <span id="freeText10">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.freeText10">Free Text 10</Translate>
            </span>
          </dt>
          <dd>{paymentItemsEntity.freeText10}</dd>
          <dt>
            <span id="freeText11">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.freeText11">Free Text 11</Translate>
            </span>
          </dt>
          <dd>{paymentItemsEntity.freeText11}</dd>
          <dt>
            <span id="freeText12">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.freeText12">Free Text 12</Translate>
            </span>
          </dt>
          <dd>{paymentItemsEntity.freeText12}</dd>
          <dt>
            <span id="freeText13">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.freeText13">Free Text 13</Translate>
            </span>
          </dt>
          <dd>{paymentItemsEntity.freeText13}</dd>
          <dt>
            <span id="freeText14">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.freeText14">Free Text 14</Translate>
            </span>
          </dt>
          <dd>{paymentItemsEntity.freeText14}</dd>
          <dt>
            <span id="freeText15">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.freeText15">Free Text 15</Translate>
            </span>
          </dt>
          <dd>{paymentItemsEntity.freeText15}</dd>
          <dt>
            <span id="freeText16">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.freeText16">Free Text 16</Translate>
            </span>
          </dt>
          <dd>{paymentItemsEntity.freeText16}</dd>
          <dt>
            <span id="freeText17">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.freeText17">Free Text 17</Translate>
            </span>
          </dt>
          <dd>{paymentItemsEntity.freeText17}</dd>
          <dt>
            <span id="freeText18">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.freeText18">Free Text 18</Translate>
            </span>
          </dt>
          <dd>{paymentItemsEntity.freeText18}</dd>
          <dt>
            <span id="freeText19">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.freeText19">Free Text 19</Translate>
            </span>
          </dt>
          <dd>{paymentItemsEntity.freeText19}</dd>
          <dt>
            <span id="delflg">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.delflg">Delflg</Translate>
            </span>
          </dt>
          <dd>{paymentItemsEntity.delflg ? 'true' : 'false'}</dd>
          <dt>
            <span id="status">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.status">Status</Translate>
            </span>
          </dt>
          <dd>{paymentItemsEntity.status}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {paymentItemsEntity.createdAt ? <TextFormat value={paymentItemsEntity.createdAt} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {paymentItemsEntity.updatedAt ? <TextFormat value={paymentItemsEntity.updatedAt} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="deletedAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.deletedAt">Deleted At</Translate>
            </span>
          </dt>
          <dd>
            {paymentItemsEntity.deletedAt ? <TextFormat value={paymentItemsEntity.deletedAt} type="date" format={APP_DATE_FORMAT} /> : null}
          </dd>
          <dt>
            <span id="deletedBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.deletedBy">Deleted By</Translate>
            </span>
          </dt>
          <dd>{paymentItemsEntity.deletedBy}</dd>
          <dt>
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.biller">Biller</Translate>
          </dt>
          <dd>{paymentItemsEntity.biller ? paymentItemsEntity.biller.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/payment-items" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/payment-items/${paymentItemsEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default PaymentItemsDetail;
