import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm, ValidatedBlobField } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IIncomingJSONResponse } from 'app/shared/model/incoming-json-response.model';
import { getEntity, updateEntity, createEntity, reset } from './incoming-json-response.reducer';

export const IncomingJSONResponseUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const incomingJSONResponseEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.incomingJSONResponse.entity);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.incomingJSONResponse.loading);
  const updating = useAppSelector(state => state.absaugdtmicrosrvcgateway.incomingJSONResponse.updating);
  const updateSuccess = useAppSelector(state => state.absaugdtmicrosrvcgateway.incomingJSONResponse.updateSuccess);

  const handleClose = () => {
    navigate('/incoming-json-response');
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
      ...incomingJSONResponseEntity,
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
          ...incomingJSONResponseEntity,
          timestamp: convertDateTimeFromServer(incomingJSONResponseEntity.timestamp),
          createdAt: convertDateTimeFromServer(incomingJSONResponseEntity.createdAt),
          updatedAt: convertDateTimeFromServer(incomingJSONResponseEntity.updatedAt),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2
            id="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.home.createOrEditLabel"
            data-cy="IncomingJSONResponseCreateUpdateHeading"
          >
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.home.createOrEditLabel">
              Create or edit a IncomingJSONResponse
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
                  id="incoming-json-response-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.absaTranRef')}
                id="incoming-json-response-absaTranRef"
                name="absaTranRef"
                data-cy="absaTranRef"
                type="text"
                validate={{}}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.dtDTransactionId')}
                id="incoming-json-response-dtDTransactionId"
                name="dtDTransactionId"
                data-cy="dtDTransactionId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.amolDTransactionId')}
                id="incoming-json-response-amolDTransactionId"
                name="amolDTransactionId"
                data-cy="amolDTransactionId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.transactionReferenceNumber')}
                id="incoming-json-response-transactionReferenceNumber"
                name="transactionReferenceNumber"
                data-cy="transactionReferenceNumber"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.token')}
                id="incoming-json-response-token"
                name="token"
                data-cy="token"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.responseId')}
                id="incoming-json-response-responseId"
                name="responseId"
                data-cy="responseId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.transactionId')}
                id="incoming-json-response-transactionId"
                name="transactionId"
                data-cy="transactionId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.responseJson')}
                id="incoming-json-response-responseJson"
                name="responseJson"
                data-cy="responseJson"
                type="textarea"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.responseType')}
                id="incoming-json-response-responseType"
                name="responseType"
                data-cy="responseType"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.responseDescription')}
                id="incoming-json-response-responseDescription"
                name="responseDescription"
                data-cy="responseDescription"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.responseStatus')}
                id="incoming-json-response-responseStatus"
                name="responseStatus"
                data-cy="responseStatus"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.additionalInfo')}
                id="incoming-json-response-additionalInfo"
                name="additionalInfo"
                data-cy="additionalInfo"
                type="textarea"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.timestamp')}
                id="incoming-json-response-timestamp"
                name="timestamp"
                data-cy="timestamp"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.exceptionMessage')}
                id="incoming-json-response-exceptionMessage"
                name="exceptionMessage"
                data-cy="exceptionMessage"
                type="textarea"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField')}
                id="incoming-json-response-freeField"
                name="freeField"
                data-cy="freeField"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField1')}
                id="incoming-json-response-freeField1"
                name="freeField1"
                data-cy="freeField1"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField2')}
                id="incoming-json-response-freeField2"
                name="freeField2"
                data-cy="freeField2"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField3')}
                id="incoming-json-response-freeField3"
                name="freeField3"
                data-cy="freeField3"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField4')}
                id="incoming-json-response-freeField4"
                name="freeField4"
                data-cy="freeField4"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField5')}
                id="incoming-json-response-freeField5"
                name="freeField5"
                data-cy="freeField5"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField6')}
                id="incoming-json-response-freeField6"
                name="freeField6"
                data-cy="freeField6"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField8')}
                id="incoming-json-response-freeField8"
                name="freeField8"
                data-cy="freeField8"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField9')}
                id="incoming-json-response-freeField9"
                name="freeField9"
                data-cy="freeField9"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField10')}
                id="incoming-json-response-freeField10"
                name="freeField10"
                data-cy="freeField10"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField11')}
                id="incoming-json-response-freeField11"
                name="freeField11"
                data-cy="freeField11"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField12')}
                id="incoming-json-response-freeField12"
                name="freeField12"
                data-cy="freeField12"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField13')}
                id="incoming-json-response-freeField13"
                name="freeField13"
                data-cy="freeField13"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField14')}
                id="incoming-json-response-freeField14"
                name="freeField14"
                data-cy="freeField14"
                type="text"
              />
              <ValidatedBlobField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField15')}
                id="incoming-json-response-freeField15"
                name="freeField15"
                data-cy="freeField15"
                openActionLabel={translate('entity.action.open')}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField16')}
                id="incoming-json-response-freeField16"
                name="freeField16"
                data-cy="freeField16"
                type="textarea"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField17')}
                id="incoming-json-response-freeField17"
                name="freeField17"
                data-cy="freeField17"
                type="textarea"
              />
              <ValidatedBlobField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField18')}
                id="incoming-json-response-freeField18"
                name="freeField18"
                data-cy="freeField18"
                openActionLabel={translate('entity.action.open')}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.freeField19')}
                id="incoming-json-response-freeField19"
                name="freeField19"
                data-cy="freeField19"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.createdAt')}
                id="incoming-json-response-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.createdBy')}
                id="incoming-json-response-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.updatedAt')}
                id="incoming-json-response-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.incomingJSONResponse.updatedBy')}
                id="incoming-json-response-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/incoming-json-response" replace color="info">
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

export default IncomingJSONResponseUpdate;
