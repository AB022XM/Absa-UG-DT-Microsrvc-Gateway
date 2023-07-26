import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IBiller } from 'app/shared/model/biller.model';
import { getEntities as getBillers } from 'app/entities/biller/biller.reducer';
import { IPaymentItems } from 'app/shared/model/payment-items.model';
import { getEntity, updateEntity, createEntity, reset } from './payment-items.reducer';

export const PaymentItemsUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const billers = useAppSelector(state => state.absaugdtmicrosrvcgateway.biller.entities);
  const paymentItemsEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.paymentItems.entity);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.paymentItems.loading);
  const updating = useAppSelector(state => state.absaugdtmicrosrvcgateway.paymentItems.updating);
  const updateSuccess = useAppSelector(state => state.absaugdtmicrosrvcgateway.paymentItems.updateSuccess);

  const handleClose = () => {
    navigate('/payment-items');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getBillers({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    values.createdAt = convertDateTimeToServer(values.createdAt);
    values.updatedAt = convertDateTimeToServer(values.updatedAt);
    values.deletedAt = convertDateTimeToServer(values.deletedAt);

    const entity = {
      ...paymentItemsEntity,
      ...values,
      biller: billers.find(it => it.id.toString() === values.biller.toString()),
    };

    if (isNew) {
      dispatch(createEntity(entity));
    } else {
      dispatch(updateEntity(entity));
    }
  };

  const defaultValues = () =>
    isNew
      ? {
          createdAt: displayDefaultDateTime(),
          updatedAt: displayDefaultDateTime(),
          deletedAt: displayDefaultDateTime(),
        }
      : {
          ...paymentItemsEntity,
          createdAt: convertDateTimeFromServer(paymentItemsEntity.createdAt),
          updatedAt: convertDateTimeFromServer(paymentItemsEntity.updatedAt),
          deletedAt: convertDateTimeFromServer(paymentItemsEntity.deletedAt),
          biller: paymentItemsEntity?.biller?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="absaUgdtMicrosrvcGatewayApp.paymentItems.home.createOrEditLabel" data-cy="PaymentItemsCreateUpdateHeading">
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentItems.home.createOrEditLabel">
              Create or edit a PaymentItems
            </Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <ValidatedForm defaultValues={defaultValues()} onSubmit={saveEntity}>
              {!isNew ? (
                <ValidatedField
                  name="id"
                  required
                  readOnly
                  id="payment-items-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.absaTranRef')}
                id="payment-items-absaTranRef"
                name="absaTranRef"
                data-cy="absaTranRef"
                type="text"
                validate={{}}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.recordId')}
                id="payment-items-recordId"
                name="recordId"
                data-cy="recordId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.productCategoryId')}
                id="payment-items-productCategoryId"
                name="productCategoryId"
                data-cy="productCategoryId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.billerId')}
                id="payment-items-billerId"
                name="billerId"
                data-cy="billerId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.paymentItemCode')}
                id="payment-items-paymentItemCode"
                name="paymentItemCode"
                data-cy="paymentItemCode"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.paymentItemId')}
                id="payment-items-paymentItemId"
                name="paymentItemId"
                data-cy="paymentItemId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.paymentItemName')}
                id="payment-items-paymentItemName"
                name="paymentItemName"
                data-cy="paymentItemName"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.paymentItemDescription')}
                id="payment-items-paymentItemDescription"
                name="paymentItemDescription"
                data-cy="paymentItemDescription"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.isActive')}
                id="payment-items-isActive"
                name="isActive"
                data-cy="isActive"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.hasFixedPrice')}
                id="payment-items-hasFixedPrice"
                name="hasFixedPrice"
                data-cy="hasFixedPrice"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.hasVariablePrice')}
                id="payment-items-hasVariablePrice"
                name="hasVariablePrice"
                data-cy="hasVariablePrice"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.hasDiscount')}
                id="payment-items-hasDiscount"
                name="hasDiscount"
                data-cy="hasDiscount"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.hasTax')}
                id="payment-items-hasTax"
                name="hasTax"
                data-cy="hasTax"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.amount')}
                id="payment-items-amount"
                name="amount"
                data-cy="amount"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.chargeAmount')}
                id="payment-items-chargeAmount"
                name="chargeAmount"
                data-cy="chargeAmount"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.hasChargeAmount')}
                id="payment-items-hasChargeAmount"
                name="hasChargeAmount"
                data-cy="hasChargeAmount"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.taxAmount')}
                id="payment-items-taxAmount"
                name="taxAmount"
                data-cy="taxAmount"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.freeText')}
                id="payment-items-freeText"
                name="freeText"
                data-cy="freeText"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.freeText1')}
                id="payment-items-freeText1"
                name="freeText1"
                data-cy="freeText1"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.freeText2')}
                id="payment-items-freeText2"
                name="freeText2"
                data-cy="freeText2"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.freeText3')}
                id="payment-items-freeText3"
                name="freeText3"
                data-cy="freeText3"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.freeText4')}
                id="payment-items-freeText4"
                name="freeText4"
                data-cy="freeText4"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.freeText5')}
                id="payment-items-freeText5"
                name="freeText5"
                data-cy="freeText5"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.freeText6')}
                id="payment-items-freeText6"
                name="freeText6"
                data-cy="freeText6"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.freeText7')}
                id="payment-items-freeText7"
                name="freeText7"
                data-cy="freeText7"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.freeText8')}
                id="payment-items-freeText8"
                name="freeText8"
                data-cy="freeText8"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.freeText9')}
                id="payment-items-freeText9"
                name="freeText9"
                data-cy="freeText9"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.freeText10')}
                id="payment-items-freeText10"
                name="freeText10"
                data-cy="freeText10"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.freeText11')}
                id="payment-items-freeText11"
                name="freeText11"
                data-cy="freeText11"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.freeText12')}
                id="payment-items-freeText12"
                name="freeText12"
                data-cy="freeText12"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.freeText13')}
                id="payment-items-freeText13"
                name="freeText13"
                data-cy="freeText13"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.freeText14')}
                id="payment-items-freeText14"
                name="freeText14"
                data-cy="freeText14"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.freeText15')}
                id="payment-items-freeText15"
                name="freeText15"
                data-cy="freeText15"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.freeText16')}
                id="payment-items-freeText16"
                name="freeText16"
                data-cy="freeText16"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.freeText17')}
                id="payment-items-freeText17"
                name="freeText17"
                data-cy="freeText17"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.freeText18')}
                id="payment-items-freeText18"
                name="freeText18"
                data-cy="freeText18"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.freeText19')}
                id="payment-items-freeText19"
                name="freeText19"
                data-cy="freeText19"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.delflg')}
                id="payment-items-delflg"
                name="delflg"
                data-cy="delflg"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.status')}
                id="payment-items-status"
                name="status"
                data-cy="status"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.createdAt')}
                id="payment-items-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.updatedAt')}
                id="payment-items-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.deletedAt')}
                id="payment-items-deletedAt"
                name="deletedAt"
                data-cy="deletedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.deletedBy')}
                id="payment-items-deletedBy"
                name="deletedBy"
                data-cy="deletedBy"
                type="text"
              />
              <ValidatedField
                id="payment-items-biller"
                name="biller"
                data-cy="biller"
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentItems.biller')}
                type="select"
              >
                <option value="" key="0" />
                {billers
                  ? billers.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/payment-items" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" data-cy="entityCreateSaveButton" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </ValidatedForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

export default PaymentItemsUpdate;
