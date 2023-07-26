import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IRequestInfo } from 'app/shared/model/request-info.model';
import { TranStatus } from 'app/shared/model/enumerations/tran-status.model';
import { ErrorCodes } from 'app/shared/model/enumerations/error-codes.model';
import { ErrorMessage } from 'app/shared/model/enumerations/error-message.model';
import { getEntity, updateEntity, createEntity, reset } from './request-info.reducer';

export const RequestInfoUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const requestInfoEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.requestInfo.entity);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.requestInfo.loading);
  const updating = useAppSelector(state => state.absaugdtmicrosrvcgateway.requestInfo.updating);
  const updateSuccess = useAppSelector(state => state.absaugdtmicrosrvcgateway.requestInfo.updateSuccess);
  const tranStatusValues = Object.keys(TranStatus);
  const errorCodesValues = Object.keys(ErrorCodes);
  const errorMessageValues = Object.keys(ErrorMessage);

  const handleClose = () => {
    navigate('/request-info');
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
      ...requestInfoEntity,
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
          ...requestInfoEntity,
          timestamp: convertDateTimeFromServer(requestInfoEntity.timestamp),
          time: convertDateTimeFromServer(requestInfoEntity.time),
          createdAt: convertDateTimeFromServer(requestInfoEntity.createdAt),
          updatedAt: convertDateTimeFromServer(requestInfoEntity.updatedAt),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="absaUgdtMicrosrvcGatewayApp.requestInfo.home.createOrEditLabel" data-cy="RequestInfoCreateUpdateHeading">
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.requestInfo.home.createOrEditLabel">Create or edit a RequestInfo</Translate>
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
                  id="request-info-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.requestInfo.recordId')}
                id="request-info-recordId"
                name="recordId"
                data-cy="recordId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.requestInfo.transactionId')}
                id="request-info-transactionId"
                name="transactionId"
                data-cy="transactionId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.requestInfo.requestData')}
                id="request-info-requestData"
                name="requestData"
                data-cy="requestData"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.requestInfo.paramList')}
                id="request-info-paramList"
                name="paramList"
                data-cy="paramList"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.requestInfo.timestamp')}
                id="request-info-timestamp"
                name="timestamp"
                data-cy="timestamp"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.requestInfo.requestRef')}
                id="request-info-requestRef"
                name="requestRef"
                data-cy="requestRef"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.requestInfo.status')}
                id="request-info-status"
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
                label={translate('absaUgdtMicrosrvcGatewayApp.requestInfo.freeField1')}
                id="request-info-freeField1"
                name="freeField1"
                data-cy="freeField1"
                type="textarea"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.requestInfo.freeField2')}
                id="request-info-freeField2"
                name="freeField2"
                data-cy="freeField2"
                type="textarea"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.requestInfo.freeField3')}
                id="request-info-freeField3"
                name="freeField3"
                data-cy="freeField3"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.requestInfo.freeField4')}
                id="request-info-freeField4"
                name="freeField4"
                data-cy="freeField4"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.requestInfo.freeField5')}
                id="request-info-freeField5"
                name="freeField5"
                data-cy="freeField5"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.requestInfo.freeField6')}
                id="request-info-freeField6"
                name="freeField6"
                data-cy="freeField6"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.requestInfo.time')}
                id="request-info-time"
                name="time"
                data-cy="time"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.requestInfo.errorCode')}
                id="request-info-errorCode"
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
                label={translate('absaUgdtMicrosrvcGatewayApp.requestInfo.errorMessage')}
                id="request-info-errorMessage"
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
                label={translate('absaUgdtMicrosrvcGatewayApp.requestInfo.createdAt')}
                id="request-info-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.requestInfo.createdBy')}
                id="request-info-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.requestInfo.updatedAt')}
                id="request-info-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.requestInfo.updatedBy')}
                id="request-info-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/request-info" replace color="info">
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

export default RequestInfoUpdate;
