import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm, ValidatedBlobField } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IResponseInfo } from 'app/shared/model/response-info.model';
import { TranStatus } from 'app/shared/model/enumerations/tran-status.model';
import { ErrorCodes } from 'app/shared/model/enumerations/error-codes.model';
import { ErrorMessage } from 'app/shared/model/enumerations/error-message.model';
import { getEntity, updateEntity, createEntity, reset } from './response-info.reducer';

export const ResponseInfoUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const responseInfoEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.responseInfo.entity);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.responseInfo.loading);
  const updating = useAppSelector(state => state.absaugdtmicrosrvcgateway.responseInfo.updating);
  const updateSuccess = useAppSelector(state => state.absaugdtmicrosrvcgateway.responseInfo.updateSuccess);
  const tranStatusValues = Object.keys(TranStatus);
  const errorCodesValues = Object.keys(ErrorCodes);
  const errorMessageValues = Object.keys(ErrorMessage);

  const handleClose = () => {
    navigate('/response-info');
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
    values.time = convertDateTimeToServer(values.time);
    values.createdAt = convertDateTimeToServer(values.createdAt);
    values.updatedAt = convertDateTimeToServer(values.updatedAt);

    const entity = {
      ...responseInfoEntity,
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
          time: displayDefaultDateTime(),
          createdAt: displayDefaultDateTime(),
          updatedAt: displayDefaultDateTime(),
        }
      : {
          status: 'PENDING',
          errorCode: 'SUCCESS',
          errorMessage: 'TRANSACTIONSUCCESS',
          ...responseInfoEntity,
          timestamp: convertDateTimeFromServer(responseInfoEntity.timestamp),
          time: convertDateTimeFromServer(responseInfoEntity.time),
          createdAt: convertDateTimeFromServer(responseInfoEntity.createdAt),
          updatedAt: convertDateTimeFromServer(responseInfoEntity.updatedAt),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="absaUgdtMicrosrvcGatewayApp.responseInfo.home.createOrEditLabel" data-cy="ResponseInfoCreateUpdateHeading">
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.responseInfo.home.createOrEditLabel">
              Create or edit a ResponseInfo
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
                  id="response-info-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.responseInfo.absaTranRef')}
                id="response-info-absaTranRef"
                name="absaTranRef"
                data-cy="absaTranRef"
                type="text"
                validate={{}}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.responseInfo.billerId')}
                id="response-info-billerId"
                name="billerId"
                data-cy="billerId"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.responseInfo.paymentItemCode')}
                id="response-info-paymentItemCode"
                name="paymentItemCode"
                data-cy="paymentItemCode"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.responseInfo.dtDTransactionId')}
                id="response-info-dtDTransactionId"
                name="dtDTransactionId"
                data-cy="dtDTransactionId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.responseInfo.transactionReferenceNumber')}
                id="response-info-transactionReferenceNumber"
                name="transactionReferenceNumber"
                data-cy="transactionReferenceNumber"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.responseInfo.externalTranid')}
                id="response-info-externalTranid"
                name="externalTranid"
                data-cy="externalTranid"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.responseInfo.responseCode')}
                id="response-info-responseCode"
                name="responseCode"
                data-cy="responseCode"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.responseInfo.responseMessage')}
                id="response-info-responseMessage"
                name="responseMessage"
                data-cy="responseMessage"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.responseInfo.responseBody')}
                id="response-info-responseBody"
                name="responseBody"
                data-cy="responseBody"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.responseInfo.timestamp')}
                id="response-info-timestamp"
                name="timestamp"
                data-cy="timestamp"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.responseInfo.requestRef')}
                id="response-info-requestRef"
                name="requestRef"
                data-cy="requestRef"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.responseInfo.status')}
                id="response-info-status"
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
                label={translate('absaUgdtMicrosrvcGatewayApp.responseInfo.freeField1')}
                id="response-info-freeField1"
                name="freeField1"
                data-cy="freeField1"
                type="textarea"
              />
              <ValidatedBlobField
                label={translate('absaUgdtMicrosrvcGatewayApp.responseInfo.freeField2')}
                id="response-info-freeField2"
                name="freeField2"
                data-cy="freeField2"
                openActionLabel={translate('entity.action.open')}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.responseInfo.freeField3')}
                id="response-info-freeField3"
                name="freeField3"
                data-cy="freeField3"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.responseInfo.freeField4')}
                id="response-info-freeField4"
                name="freeField4"
                data-cy="freeField4"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.responseInfo.freeField5')}
                id="response-info-freeField5"
                name="freeField5"
                data-cy="freeField5"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.responseInfo.freeField6')}
                id="response-info-freeField6"
                name="freeField6"
                data-cy="freeField6"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.responseInfo.time')}
                id="response-info-time"
                name="time"
                data-cy="time"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.responseInfo.errorCode')}
                id="response-info-errorCode"
                name="errorCode"
                data-cy="errorCode"
                type="select"
              >
                {errorCodesValues.map(errorCodes => (
                  <option value={errorCodes} key={errorCodes}>
                    {translate('absaUgdtMicrosrvcGatewayApp.ErrorCodes.' + errorCodes)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.responseInfo.errorMessage')}
                id="response-info-errorMessage"
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
                label={translate('absaUgdtMicrosrvcGatewayApp.responseInfo.createdAt')}
                id="response-info-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.responseInfo.createdBy')}
                id="response-info-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.responseInfo.updatedAt')}
                id="response-info-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.responseInfo.updatedBy')}
                id="response-info-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/response-info" replace color="info">
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

export default ResponseInfoUpdate;
