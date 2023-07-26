import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm, ValidatedBlobField } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IIncomingJSONRequest } from 'app/shared/model/incoming-json-request.model';
import { getEntity, updateEntity, createEntity, reset } from './incoming-json-request.reducer';

export const IncomingJSONRequestUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const incomingJSONRequestEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.incomingJSONRequest.entity);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.incomingJSONRequest.loading);
  const updating = useAppSelector(state => state.absaugdtmicrosrvcgateway.incomingJSONRequest.updating);
  const updateSuccess = useAppSelector(state => state.absaugdtmicrosrvcgateway.incomingJSONRequest.updateSuccess);

  const handleClose = () => {
    navigate('/incoming-json-request');
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
      ...incomingJSONRequestEntity,
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
          ...incomingJSONRequestEntity,
          timestamp: convertDateTimeFromServer(incomingJSONRequestEntity.timestamp),
          createdAt: convertDateTimeFromServer(incomingJSONRequestEntity.createdAt),
          updatedAt: convertDateTimeFromServer(incomingJSONRequestEntity.updatedAt),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.home.createOrEditLabel" data-cy="IncomingJSONRequestCreateUpdateHeading">
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.home.createOrEditLabel">
              Create or edit a IncomingJSONRequest
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
                  id="incoming-json-request-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.absaTranRef')}
                id="incoming-json-request-absaTranRef"
                name="absaTranRef"
                data-cy="absaTranRef"
                type="text"
                validate={{}}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.dtDTransactionId')}
                id="incoming-json-request-dtDTransactionId"
                name="dtDTransactionId"
                data-cy="dtDTransactionId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.amolDTransactionId')}
                id="incoming-json-request-amolDTransactionId"
                name="amolDTransactionId"
                data-cy="amolDTransactionId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.transactionReferenceNumber')}
                id="incoming-json-request-transactionReferenceNumber"
                name="transactionReferenceNumber"
                data-cy="transactionReferenceNumber"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.token')}
                id="incoming-json-request-token"
                name="token"
                data-cy="token"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.transactionId')}
                id="incoming-json-request-transactionId"
                name="transactionId"
                data-cy="transactionId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.fromEndpoint')}
                id="incoming-json-request-fromEndpoint"
                name="fromEndpoint"
                data-cy="fromEndpoint"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.toEndpoint')}
                id="incoming-json-request-toEndpoint"
                name="toEndpoint"
                data-cy="toEndpoint"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.requestJson')}
                id="incoming-json-request-requestJson"
                name="requestJson"
                data-cy="requestJson"
                type="textarea"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.requestType')}
                id="incoming-json-request-requestType"
                name="requestType"
                data-cy="requestType"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.requestDescription')}
                id="incoming-json-request-requestDescription"
                name="requestDescription"
                data-cy="requestDescription"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.requestStatus')}
                id="incoming-json-request-requestStatus"
                name="requestStatus"
                data-cy="requestStatus"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.additionalInfo')}
                id="incoming-json-request-additionalInfo"
                name="additionalInfo"
                data-cy="additionalInfo"
                type="textarea"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField1')}
                id="incoming-json-request-freeField1"
                name="freeField1"
                data-cy="freeField1"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField2')}
                id="incoming-json-request-freeField2"
                name="freeField2"
                data-cy="freeField2"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField3')}
                id="incoming-json-request-freeField3"
                name="freeField3"
                data-cy="freeField3"
                type="textarea"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField4')}
                id="incoming-json-request-freeField4"
                name="freeField4"
                data-cy="freeField4"
                type="textarea"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField5')}
                id="incoming-json-request-freeField5"
                name="freeField5"
                data-cy="freeField5"
                type="textarea"
              />
              <ValidatedBlobField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField6')}
                id="incoming-json-request-freeField6"
                name="freeField6"
                data-cy="freeField6"
                openActionLabel={translate('entity.action.open')}
              />
              <ValidatedBlobField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField7')}
                id="incoming-json-request-freeField7"
                name="freeField7"
                data-cy="freeField7"
                openActionLabel={translate('entity.action.open')}
              />
              <ValidatedBlobField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField8')}
                id="incoming-json-request-freeField8"
                name="freeField8"
                data-cy="freeField8"
                openActionLabel={translate('entity.action.open')}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField9')}
                id="incoming-json-request-freeField9"
                name="freeField9"
                data-cy="freeField9"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField10')}
                id="incoming-json-request-freeField10"
                name="freeField10"
                data-cy="freeField10"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField11')}
                id="incoming-json-request-freeField11"
                name="freeField11"
                data-cy="freeField11"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField12')}
                id="incoming-json-request-freeField12"
                name="freeField12"
                data-cy="freeField12"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField13')}
                id="incoming-json-request-freeField13"
                name="freeField13"
                data-cy="freeField13"
                type="textarea"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField14')}
                id="incoming-json-request-freeField14"
                name="freeField14"
                data-cy="freeField14"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField15')}
                id="incoming-json-request-freeField15"
                name="freeField15"
                data-cy="freeField15"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField16')}
                id="incoming-json-request-freeField16"
                name="freeField16"
                data-cy="freeField16"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField17')}
                id="incoming-json-request-freeField17"
                name="freeField17"
                data-cy="freeField17"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField18')}
                id="incoming-json-request-freeField18"
                name="freeField18"
                data-cy="freeField18"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField19')}
                id="incoming-json-request-freeField19"
                name="freeField19"
                data-cy="freeField19"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.freeField20')}
                id="incoming-json-request-freeField20"
                name="freeField20"
                data-cy="freeField20"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.timestamp')}
                id="incoming-json-request-timestamp"
                name="timestamp"
                data-cy="timestamp"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.createdAt')}
                id="incoming-json-request-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.createdBy')}
                id="incoming-json-request-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.updatedAt')}
                id="incoming-json-request-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONRequest.updatedBy')}
                id="incoming-json-request-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/incoming-json-request" replace color="info">
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

export default IncomingJSONRequestUpdate;
