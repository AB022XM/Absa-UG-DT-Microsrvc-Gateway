import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm, ValidatedBlobField } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IOutgoingJSONResponse } from 'app/shared/model/outgoing-json-response.model';
import { getEntity, updateEntity, createEntity, reset } from './outgoing-json-response.reducer';

export const OutgoingJSONResponseUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const outgoingJSONResponseEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.outgoingJSONResponse.entity);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.outgoingJSONResponse.loading);
  const updating = useAppSelector(state => state.absaugdtmicrosrvcgateway.outgoingJSONResponse.updating);
  const updateSuccess = useAppSelector(state => state.absaugdtmicrosrvcgateway.outgoingJSONResponse.updateSuccess);

  const handleClose = () => {
    navigate('/outgoing-json-response');
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
      ...outgoingJSONResponseEntity,
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
          ...outgoingJSONResponseEntity,
          timestamp: convertDateTimeFromServer(outgoingJSONResponseEntity.timestamp),
          createdAt: convertDateTimeFromServer(outgoingJSONResponseEntity.createdAt),
          updatedAt: convertDateTimeFromServer(outgoingJSONResponseEntity.updatedAt),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2
            id="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.home.createOrEditLabel"
            data-cy="OutgoingJSONResponseCreateUpdateHeading"
          >
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.home.createOrEditLabel">
              Create or edit a OutgoingJSONResponse
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
                  id="outgoing-json-response-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.recordId')}
                id="outgoing-json-response-recordId"
                name="recordId"
                data-cy="recordId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.responseId')}
                id="outgoing-json-response-responseId"
                name="responseId"
                data-cy="responseId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.transactionId')}
                id="outgoing-json-response-transactionId"
                name="transactionId"
                data-cy="transactionId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.responseJson')}
                id="outgoing-json-response-responseJson"
                name="responseJson"
                data-cy="responseJson"
                type="textarea"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.responseType')}
                id="outgoing-json-response-responseType"
                name="responseType"
                data-cy="responseType"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.responseDescription')}
                id="outgoing-json-response-responseDescription"
                name="responseDescription"
                data-cy="responseDescription"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.toEndpoint')}
                id="outgoing-json-response-toEndpoint"
                name="toEndpoint"
                data-cy="toEndpoint"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.fromEndpoint')}
                id="outgoing-json-response-fromEndpoint"
                name="fromEndpoint"
                data-cy="fromEndpoint"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.responseStatus')}
                id="outgoing-json-response-responseStatus"
                name="responseStatus"
                data-cy="responseStatus"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.additionalInfo')}
                id="outgoing-json-response-additionalInfo"
                name="additionalInfo"
                data-cy="additionalInfo"
                type="textarea"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.timestamp')}
                id="outgoing-json-response-timestamp"
                name="timestamp"
                data-cy="timestamp"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.exceptionMessage')}
                id="outgoing-json-response-exceptionMessage"
                name="exceptionMessage"
                data-cy="exceptionMessage"
                type="textarea"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField')}
                id="outgoing-json-response-freeField"
                name="freeField"
                data-cy="freeField"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField1')}
                id="outgoing-json-response-freeField1"
                name="freeField1"
                data-cy="freeField1"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField2')}
                id="outgoing-json-response-freeField2"
                name="freeField2"
                data-cy="freeField2"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField3')}
                id="outgoing-json-response-freeField3"
                name="freeField3"
                data-cy="freeField3"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField4')}
                id="outgoing-json-response-freeField4"
                name="freeField4"
                data-cy="freeField4"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField5')}
                id="outgoing-json-response-freeField5"
                name="freeField5"
                data-cy="freeField5"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField6')}
                id="outgoing-json-response-freeField6"
                name="freeField6"
                data-cy="freeField6"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField8')}
                id="outgoing-json-response-freeField8"
                name="freeField8"
                data-cy="freeField8"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField9')}
                id="outgoing-json-response-freeField9"
                name="freeField9"
                data-cy="freeField9"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField10')}
                id="outgoing-json-response-freeField10"
                name="freeField10"
                data-cy="freeField10"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField11')}
                id="outgoing-json-response-freeField11"
                name="freeField11"
                data-cy="freeField11"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField12')}
                id="outgoing-json-response-freeField12"
                name="freeField12"
                data-cy="freeField12"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField13')}
                id="outgoing-json-response-freeField13"
                name="freeField13"
                data-cy="freeField13"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField14')}
                id="outgoing-json-response-freeField14"
                name="freeField14"
                data-cy="freeField14"
                type="text"
              />
              <ValidatedBlobField
                label={translate('absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField15')}
                id="outgoing-json-response-freeField15"
                name="freeField15"
                data-cy="freeField15"
                openActionLabel={translate('entity.action.open')}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField16')}
                id="outgoing-json-response-freeField16"
                name="freeField16"
                data-cy="freeField16"
                type="textarea"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField17')}
                id="outgoing-json-response-freeField17"
                name="freeField17"
                data-cy="freeField17"
                type="textarea"
              />
              <ValidatedBlobField
                label={translate('absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField18')}
                id="outgoing-json-response-freeField18"
                name="freeField18"
                data-cy="freeField18"
                openActionLabel={translate('entity.action.open')}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.freeField19')}
                id="outgoing-json-response-freeField19"
                name="freeField19"
                data-cy="freeField19"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.createdAt')}
                id="outgoing-json-response-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.createdBy')}
                id="outgoing-json-response-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.updatedAt')}
                id="outgoing-json-response-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.outgoingJSONResponse.updatedBy')}
                id="outgoing-json-response-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/outgoing-json-response" replace color="info">
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

export default OutgoingJSONResponseUpdate;
