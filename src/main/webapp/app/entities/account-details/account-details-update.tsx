import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm, ValidatedBlobField } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IAccountDetails } from 'app/shared/model/account-details.model';
import { getEntity, updateEntity, createEntity, reset } from './account-details.reducer';

export const AccountDetailsUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const accountDetailsEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.accountDetails.entity);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.accountDetails.loading);
  const updating = useAppSelector(state => state.absaugdtmicrosrvcgateway.accountDetails.updating);
  const updateSuccess = useAppSelector(state => state.absaugdtmicrosrvcgateway.accountDetails.updateSuccess);

  const handleClose = () => {
    navigate('/account-details');
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
      ...accountDetailsEntity,
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
          ...accountDetailsEntity,
          transactionDate: convertDateTimeFromServer(accountDetailsEntity.transactionDate),
          tranFreeField24: convertDateTimeFromServer(accountDetailsEntity.tranFreeField24),
          createdAt: convertDateTimeFromServer(accountDetailsEntity.createdAt),
          updatedAt: convertDateTimeFromServer(accountDetailsEntity.updatedAt),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="absaUgdtMicrosrvcGatewayApp.accountDetails.home.createOrEditLabel" data-cy="AccountDetailsCreateUpdateHeading">
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.accountDetails.home.createOrEditLabel">
              Create or edit a AccountDetails
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
                  id="account-details-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.absaTranRef')}
                id="account-details-absaTranRef"
                name="absaTranRef"
                data-cy="absaTranRef"
                type="text"
                validate={{}}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.billerId')}
                id="account-details-billerId"
                name="billerId"
                data-cy="billerId"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.paymentItemCode')}
                id="account-details-paymentItemCode"
                name="paymentItemCode"
                data-cy="paymentItemCode"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.dtDTransactionId')}
                id="account-details-dtDTransactionId"
                name="dtDTransactionId"
                data-cy="dtDTransactionId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.amolDTransactionId')}
                id="account-details-amolDTransactionId"
                name="amolDTransactionId"
                data-cy="amolDTransactionId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.transactionReferenceNumber')}
                id="account-details-transactionReferenceNumber"
                name="transactionReferenceNumber"
                data-cy="transactionReferenceNumber"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.token')}
                id="account-details-token"
                name="token"
                data-cy="token"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.transferId')}
                id="account-details-transferId"
                name="transferId"
                data-cy="transferId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.requestId')}
                id="account-details-requestId"
                name="requestId"
                data-cy="requestId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.accountName')}
                id="account-details-accountName"
                name="accountName"
                data-cy="accountName"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.returnCode')}
                id="account-details-returnCode"
                name="returnCode"
                data-cy="returnCode"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.returnMessage')}
                id="account-details-returnMessage"
                name="returnMessage"
                data-cy="returnMessage"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.externalTXNid')}
                id="account-details-externalTXNid"
                name="externalTXNid"
                data-cy="externalTXNid"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.transactionDate')}
                id="account-details-transactionDate"
                name="transactionDate"
                data-cy="transactionDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.customerId')}
                id="account-details-customerId"
                name="customerId"
                data-cy="customerId"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.customerProduct')}
                id="account-details-customerProduct"
                name="customerProduct"
                data-cy="customerProduct"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.customerType')}
                id="account-details-customerType"
                name="customerType"
                data-cy="customerType"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.accountCategory')}
                id="account-details-accountCategory"
                name="accountCategory"
                data-cy="accountCategory"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.accountType')}
                id="account-details-accountType"
                name="accountType"
                data-cy="accountType"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.accountNumber')}
                id="account-details-accountNumber"
                name="accountNumber"
                data-cy="accountNumber"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.phoneNumber')}
                id="account-details-phoneNumber"
                name="phoneNumber"
                data-cy="phoneNumber"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.channel')}
                id="account-details-channel"
                name="channel"
                data-cy="channel"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField1')}
                id="account-details-tranFreeField1"
                name="tranFreeField1"
                data-cy="tranFreeField1"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField2')}
                id="account-details-tranFreeField2"
                name="tranFreeField2"
                data-cy="tranFreeField2"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField3')}
                id="account-details-tranFreeField3"
                name="tranFreeField3"
                data-cy="tranFreeField3"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField4')}
                id="account-details-tranFreeField4"
                name="tranFreeField4"
                data-cy="tranFreeField4"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField5')}
                id="account-details-tranFreeField5"
                name="tranFreeField5"
                data-cy="tranFreeField5"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField6')}
                id="account-details-tranFreeField6"
                name="tranFreeField6"
                data-cy="tranFreeField6"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField7')}
                id="account-details-tranFreeField7"
                name="tranFreeField7"
                data-cy="tranFreeField7"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField8')}
                id="account-details-tranFreeField8"
                name="tranFreeField8"
                data-cy="tranFreeField8"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField9')}
                id="account-details-tranFreeField9"
                name="tranFreeField9"
                data-cy="tranFreeField9"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField10')}
                id="account-details-tranFreeField10"
                name="tranFreeField10"
                data-cy="tranFreeField10"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField11')}
                id="account-details-tranFreeField11"
                name="tranFreeField11"
                data-cy="tranFreeField11"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField12')}
                id="account-details-tranFreeField12"
                name="tranFreeField12"
                data-cy="tranFreeField12"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField13')}
                id="account-details-tranFreeField13"
                name="tranFreeField13"
                data-cy="tranFreeField13"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField14')}
                id="account-details-tranFreeField14"
                name="tranFreeField14"
                data-cy="tranFreeField14"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField15')}
                id="account-details-tranFreeField15"
                name="tranFreeField15"
                data-cy="tranFreeField15"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField16')}
                id="account-details-tranFreeField16"
                name="tranFreeField16"
                data-cy="tranFreeField16"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField17')}
                id="account-details-tranFreeField17"
                name="tranFreeField17"
                data-cy="tranFreeField17"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField18')}
                id="account-details-tranFreeField18"
                name="tranFreeField18"
                data-cy="tranFreeField18"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField19')}
                id="account-details-tranFreeField19"
                name="tranFreeField19"
                data-cy="tranFreeField19"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField20')}
                id="account-details-tranFreeField20"
                name="tranFreeField20"
                data-cy="tranFreeField20"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField21')}
                id="account-details-tranFreeField21"
                name="tranFreeField21"
                data-cy="tranFreeField21"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField22')}
                id="account-details-tranFreeField22"
                name="tranFreeField22"
                data-cy="tranFreeField22"
                type="textarea"
              />
              <ValidatedBlobField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField23')}
                id="account-details-tranFreeField23"
                name="tranFreeField23"
                data-cy="tranFreeField23"
                openActionLabel={translate('entity.action.open')}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField24')}
                id="account-details-tranFreeField24"
                name="tranFreeField24"
                data-cy="tranFreeField24"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField25')}
                id="account-details-tranFreeField25"
                name="tranFreeField25"
                data-cy="tranFreeField25"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField26')}
                id="account-details-tranFreeField26"
                name="tranFreeField26"
                data-cy="tranFreeField26"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField27')}
                id="account-details-tranFreeField27"
                name="tranFreeField27"
                data-cy="tranFreeField27"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField28')}
                id="account-details-tranFreeField28"
                name="tranFreeField28"
                data-cy="tranFreeField28"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField29')}
                id="account-details-tranFreeField29"
                name="tranFreeField29"
                data-cy="tranFreeField29"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField30')}
                id="account-details-tranFreeField30"
                name="tranFreeField30"
                data-cy="tranFreeField30"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField31')}
                id="account-details-tranFreeField31"
                name="tranFreeField31"
                data-cy="tranFreeField31"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField32')}
                id="account-details-tranFreeField32"
                name="tranFreeField32"
                data-cy="tranFreeField32"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.tranFreeField33')}
                id="account-details-tranFreeField33"
                name="tranFreeField33"
                data-cy="tranFreeField33"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.createdAt')}
                id="account-details-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.createdBy')}
                id="account-details-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.updatedAt')}
                id="account-details-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.accountDetails.updatedBy')}
                id="account-details-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/account-details" replace color="info">
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

export default AccountDetailsUpdate;
