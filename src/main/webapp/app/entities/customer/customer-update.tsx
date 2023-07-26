import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm, ValidatedBlobField } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ICustomer } from 'app/shared/model/customer.model';
import { getEntity, updateEntity, createEntity, reset } from './customer.reducer';

export const CustomerUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const customerEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.customer.entity);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.customer.loading);
  const updating = useAppSelector(state => state.absaugdtmicrosrvcgateway.customer.updating);
  const updateSuccess = useAppSelector(state => state.absaugdtmicrosrvcgateway.customer.updateSuccess);

  const handleClose = () => {
    navigate('/customer');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    values.transactionDate = convertDateTimeToServer(values.transactionDate);
    values.tranFreeField24 = convertDateTimeToServer(values.tranFreeField24);
    values.createdAt = convertDateTimeToServer(values.createdAt);
    values.updatedAt = convertDateTimeToServer(values.updatedAt);

    const entity = {
      ...customerEntity,
      ...values,
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
          transactionDate: displayDefaultDateTime(),
          tranFreeField24: displayDefaultDateTime(),
          createdAt: displayDefaultDateTime(),
          updatedAt: displayDefaultDateTime(),
        }
      : {
          ...customerEntity,
          transactionDate: convertDateTimeFromServer(customerEntity.transactionDate),
          tranFreeField24: convertDateTimeFromServer(customerEntity.tranFreeField24),
          createdAt: convertDateTimeFromServer(customerEntity.createdAt),
          updatedAt: convertDateTimeFromServer(customerEntity.updatedAt),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="absaUgdtMicrosrvcGatewayApp.customer.home.createOrEditLabel" data-cy="CustomerCreateUpdateHeading">
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customer.home.createOrEditLabel">Create or edit a Customer</Translate>
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
                  id="customer-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.absaTranRef')}
                id="customer-absaTranRef"
                name="absaTranRef"
                data-cy="absaTranRef"
                type="text"
                validate={{}}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.billerId')}
                id="customer-billerId"
                name="billerId"
                data-cy="billerId"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.paymentItemCode')}
                id="customer-paymentItemCode"
                name="paymentItemCode"
                data-cy="paymentItemCode"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.dtDTransactionId')}
                id="customer-dtDTransactionId"
                name="dtDTransactionId"
                data-cy="dtDTransactionId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.amolDTransactionId')}
                id="customer-amolDTransactionId"
                name="amolDTransactionId"
                data-cy="amolDTransactionId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.transactionReferenceNumber')}
                id="customer-transactionReferenceNumber"
                name="transactionReferenceNumber"
                data-cy="transactionReferenceNumber"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.token')}
                id="customer-token"
                name="token"
                data-cy="token"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.transferId')}
                id="customer-transferId"
                name="transferId"
                data-cy="transferId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.requestId')}
                id="customer-requestId"
                name="requestId"
                data-cy="requestId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.accountName')}
                id="customer-accountName"
                name="accountName"
                data-cy="accountName"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.returnCode')}
                id="customer-returnCode"
                name="returnCode"
                data-cy="returnCode"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.returnMessage')}
                id="customer-returnMessage"
                name="returnMessage"
                data-cy="returnMessage"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.externalTXNid')}
                id="customer-externalTXNid"
                name="externalTXNid"
                data-cy="externalTXNid"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.transactionDate')}
                id="customer-transactionDate"
                name="transactionDate"
                data-cy="transactionDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.customerId')}
                id="customer-customerId"
                name="customerId"
                data-cy="customerId"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.customerProduct')}
                id="customer-customerProduct"
                name="customerProduct"
                data-cy="customerProduct"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.customerType')}
                id="customer-customerType"
                name="customerType"
                data-cy="customerType"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.accountCategory')}
                id="customer-accountCategory"
                name="accountCategory"
                data-cy="accountCategory"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.accountType')}
                id="customer-accountType"
                name="accountType"
                data-cy="accountType"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.accountNumber')}
                id="customer-accountNumber"
                name="accountNumber"
                data-cy="accountNumber"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.phoneNumber')}
                id="customer-phoneNumber"
                name="phoneNumber"
                data-cy="phoneNumber"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.channel')}
                id="customer-channel"
                name="channel"
                data-cy="channel"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.tranFreeField1')}
                id="customer-tranFreeField1"
                name="tranFreeField1"
                data-cy="tranFreeField1"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.tranFreeField2')}
                id="customer-tranFreeField2"
                name="tranFreeField2"
                data-cy="tranFreeField2"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.tranFreeField3')}
                id="customer-tranFreeField3"
                name="tranFreeField3"
                data-cy="tranFreeField3"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.tranFreeField4')}
                id="customer-tranFreeField4"
                name="tranFreeField4"
                data-cy="tranFreeField4"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.tranFreeField5')}
                id="customer-tranFreeField5"
                name="tranFreeField5"
                data-cy="tranFreeField5"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.tranFreeField6')}
                id="customer-tranFreeField6"
                name="tranFreeField6"
                data-cy="tranFreeField6"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.tranFreeField7')}
                id="customer-tranFreeField7"
                name="tranFreeField7"
                data-cy="tranFreeField7"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.tranFreeField8')}
                id="customer-tranFreeField8"
                name="tranFreeField8"
                data-cy="tranFreeField8"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.tranFreeField9')}
                id="customer-tranFreeField9"
                name="tranFreeField9"
                data-cy="tranFreeField9"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.tranFreeField10')}
                id="customer-tranFreeField10"
                name="tranFreeField10"
                data-cy="tranFreeField10"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.tranFreeField11')}
                id="customer-tranFreeField11"
                name="tranFreeField11"
                data-cy="tranFreeField11"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.tranFreeField12')}
                id="customer-tranFreeField12"
                name="tranFreeField12"
                data-cy="tranFreeField12"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.tranFreeField13')}
                id="customer-tranFreeField13"
                name="tranFreeField13"
                data-cy="tranFreeField13"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.tranFreeField14')}
                id="customer-tranFreeField14"
                name="tranFreeField14"
                data-cy="tranFreeField14"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.tranFreeField15')}
                id="customer-tranFreeField15"
                name="tranFreeField15"
                data-cy="tranFreeField15"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.tranFreeField16')}
                id="customer-tranFreeField16"
                name="tranFreeField16"
                data-cy="tranFreeField16"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.tranFreeField17')}
                id="customer-tranFreeField17"
                name="tranFreeField17"
                data-cy="tranFreeField17"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.tranFreeField18')}
                id="customer-tranFreeField18"
                name="tranFreeField18"
                data-cy="tranFreeField18"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.tranFreeField19')}
                id="customer-tranFreeField19"
                name="tranFreeField19"
                data-cy="tranFreeField19"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.tranFreeField20')}
                id="customer-tranFreeField20"
                name="tranFreeField20"
                data-cy="tranFreeField20"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.tranFreeField21')}
                id="customer-tranFreeField21"
                name="tranFreeField21"
                data-cy="tranFreeField21"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.tranFreeField22')}
                id="customer-tranFreeField22"
                name="tranFreeField22"
                data-cy="tranFreeField22"
                type="textarea"
              />
              <ValidatedBlobField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.tranFreeField23')}
                id="customer-tranFreeField23"
                name="tranFreeField23"
                data-cy="tranFreeField23"
                openActionLabel={translate('entity.action.open')}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.tranFreeField24')}
                id="customer-tranFreeField24"
                name="tranFreeField24"
                data-cy="tranFreeField24"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.tranFreeField25')}
                id="customer-tranFreeField25"
                name="tranFreeField25"
                data-cy="tranFreeField25"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.tranFreeField26')}
                id="customer-tranFreeField26"
                name="tranFreeField26"
                data-cy="tranFreeField26"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.tranFreeField27')}
                id="customer-tranFreeField27"
                name="tranFreeField27"
                data-cy="tranFreeField27"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.tranFreeField28')}
                id="customer-tranFreeField28"
                name="tranFreeField28"
                data-cy="tranFreeField28"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.tranFreeField29')}
                id="customer-tranFreeField29"
                name="tranFreeField29"
                data-cy="tranFreeField29"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.tranFreeField30')}
                id="customer-tranFreeField30"
                name="tranFreeField30"
                data-cy="tranFreeField30"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.tranFreeField31')}
                id="customer-tranFreeField31"
                name="tranFreeField31"
                data-cy="tranFreeField31"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.tranFreeField32')}
                id="customer-tranFreeField32"
                name="tranFreeField32"
                data-cy="tranFreeField32"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.tranFreeField33')}
                id="customer-tranFreeField33"
                name="tranFreeField33"
                data-cy="tranFreeField33"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.createdAt')}
                id="customer-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.createdBy')}
                id="customer-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.updatedAt')}
                id="customer-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customer.updatedBy')}
                id="customer-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/customer" replace color="info">
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

export default CustomerUpdate;
