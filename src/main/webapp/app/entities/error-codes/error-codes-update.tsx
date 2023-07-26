import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IErrorCodes } from 'app/shared/model/error-codes.model';
import { ErrorMessage } from 'app/shared/model/enumerations/error-message.model';
import { TranStatus } from 'app/shared/model/enumerations/tran-status.model';
import { getEntity, updateEntity, createEntity, reset } from './error-codes.reducer';

export const ErrorCodesUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const errorCodesEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.errorCodes.entity);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.errorCodes.loading);
  const updating = useAppSelector(state => state.absaugdtmicrosrvcgateway.errorCodes.updating);
  const updateSuccess = useAppSelector(state => state.absaugdtmicrosrvcgateway.errorCodes.updateSuccess);
  const errorMessageValues = Object.keys(ErrorMessage);
  const tranStatusValues = Object.keys(TranStatus);

  const handleClose = () => {
    navigate('/error-codes');
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
    values.timestamp = convertDateTimeToServer(values.timestamp);
    values.createdAt = convertDateTimeToServer(values.createdAt);
    values.updatedAt = convertDateTimeToServer(values.updatedAt);

    const entity = {
      ...errorCodesEntity,
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
          timestamp: displayDefaultDateTime(),
          createdAt: displayDefaultDateTime(),
          updatedAt: displayDefaultDateTime(),
        }
      : {
          errorMessage: 'TRANSACTIONSUCCESS',
          status: 'PENDING',
          ...errorCodesEntity,
          timestamp: convertDateTimeFromServer(errorCodesEntity.timestamp),
          createdAt: convertDateTimeFromServer(errorCodesEntity.createdAt),
          updatedAt: convertDateTimeFromServer(errorCodesEntity.updatedAt),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="absaUgdtMicrosrvcGatewayApp.errorCodes.home.createOrEditLabel" data-cy="ErrorCodesCreateUpdateHeading">
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.errorCodes.home.createOrEditLabel">Create or edit a ErrorCodes</Translate>
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
                  id="error-codes-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.errorCodes.absaTranRef')}
                id="error-codes-absaTranRef"
                name="absaTranRef"
                data-cy="absaTranRef"
                type="text"
                validate={{}}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.errorCodes.recordId')}
                id="error-codes-recordId"
                name="recordId"
                data-cy="recordId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.errorCodes.paymentItemCode')}
                id="error-codes-paymentItemCode"
                name="paymentItemCode"
                data-cy="paymentItemCode"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.errorCodes.dtDTransactionId')}
                id="error-codes-dtDTransactionId"
                name="dtDTransactionId"
                data-cy="dtDTransactionId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.errorCodes.transactionReferenceNumber')}
                id="error-codes-transactionReferenceNumber"
                name="transactionReferenceNumber"
                data-cy="transactionReferenceNumber"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.errorCodes.externalTranid')}
                id="error-codes-externalTranid"
                name="externalTranid"
                data-cy="externalTranid"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.errorCodes.errorCode')}
                id="error-codes-errorCode"
                name="errorCode"
                data-cy="errorCode"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.errorCodes.errorMessage')}
                id="error-codes-errorMessage"
                name="errorMessage"
                data-cy="errorMessage"
                type="select"
              >
                {errorMessageValues.map(errorMessage => (
                  <option value={errorMessage} key={errorMessage}>
                    {translate('absaUgdtMicrosrvcGatewayApp.ErrorMessage.' + errorMessage)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.errorCodes.responseCode')}
                id="error-codes-responseCode"
                name="responseCode"
                data-cy="responseCode"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.errorCodes.responseMessage')}
                id="error-codes-responseMessage"
                name="responseMessage"
                data-cy="responseMessage"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.errorCodes.responseBody')}
                id="error-codes-responseBody"
                name="responseBody"
                data-cy="responseBody"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.errorCodes.timestamp')}
                id="error-codes-timestamp"
                name="timestamp"
                data-cy="timestamp"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.errorCodes.requestRef')}
                id="error-codes-requestRef"
                name="requestRef"
                data-cy="requestRef"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.errorCodes.status')}
                id="error-codes-status"
                name="status"
                data-cy="status"
                type="select"
              >
                {tranStatusValues.map(tranStatus => (
                  <option value={tranStatus} key={tranStatus}>
                    {translate('absaUgdtMicrosrvcGatewayApp.TranStatus.' + tranStatus)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.errorCodes.customerField1')}
                id="error-codes-customerField1"
                name="customerField1"
                data-cy="customerField1"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.errorCodes.customerField2')}
                id="error-codes-customerField2"
                name="customerField2"
                data-cy="customerField2"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.errorCodes.customerField3')}
                id="error-codes-customerField3"
                name="customerField3"
                data-cy="customerField3"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.errorCodes.customerField4')}
                id="error-codes-customerField4"
                name="customerField4"
                data-cy="customerField4"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.errorCodes.customerField5')}
                id="error-codes-customerField5"
                name="customerField5"
                data-cy="customerField5"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.errorCodes.customerField6')}
                id="error-codes-customerField6"
                name="customerField6"
                data-cy="customerField6"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.errorCodes.customerField7')}
                id="error-codes-customerField7"
                name="customerField7"
                data-cy="customerField7"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.errorCodes.customerField8')}
                id="error-codes-customerField8"
                name="customerField8"
                data-cy="customerField8"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.errorCodes.createdAt')}
                id="error-codes-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.errorCodes.createdBy')}
                id="error-codes-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.errorCodes.updatedAt')}
                id="error-codes-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.errorCodes.updatedBy')}
                id="error-codes-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/error-codes" replace color="info">
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

export default ErrorCodesUpdate;
