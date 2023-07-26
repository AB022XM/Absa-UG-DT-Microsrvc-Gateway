import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm, ValidatedBlobField } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IDefaultSettings } from 'app/shared/model/default-settings.model';
import { RecordStatus } from 'app/shared/model/enumerations/record-status.model';
import { getEntity, updateEntity, createEntity, reset } from './default-settings.reducer';

export const DefaultSettingsUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const defaultSettingsEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.defaultSettings.entity);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.defaultSettings.loading);
  const updating = useAppSelector(state => state.absaugdtmicrosrvcgateway.defaultSettings.updating);
  const updateSuccess = useAppSelector(state => state.absaugdtmicrosrvcgateway.defaultSettings.updateSuccess);
  const recordStatusValues = Object.keys(RecordStatus);

  const handleClose = () => {
    navigate('/default-settings');
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
      ...defaultSettingsEntity,
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
          recordStatus: 'ACTIVE',
          ...defaultSettingsEntity,
          timestamp: convertDateTimeFromServer(defaultSettingsEntity.timestamp),
          createdAt: convertDateTimeFromServer(defaultSettingsEntity.createdAt),
          updatedAt: convertDateTimeFromServer(defaultSettingsEntity.updatedAt),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="absaUgdtMicrosrvcGatewayApp.defaultSettings.home.createOrEditLabel" data-cy="DefaultSettingsCreateUpdateHeading">
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.defaultSettings.home.createOrEditLabel">
              Create or edit a DefaultSettings
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
                  id="default-settings-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.defaultSettings.absaTranRef')}
                id="default-settings-absaTranRef"
                name="absaTranRef"
                data-cy="absaTranRef"
                type="text"
                validate={{}}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.defaultSettings.dtDTransactionId')}
                id="default-settings-dtDTransactionId"
                name="dtDTransactionId"
                data-cy="dtDTransactionId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.defaultSettings.amolDTransactionId')}
                id="default-settings-amolDTransactionId"
                name="amolDTransactionId"
                data-cy="amolDTransactionId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.defaultSettings.transactionReferenceNumber')}
                id="default-settings-transactionReferenceNumber"
                name="transactionReferenceNumber"
                data-cy="transactionReferenceNumber"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.defaultSettings.token')}
                id="default-settings-token"
                name="token"
                data-cy="token"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.defaultSettings.thirdPartyDTransactionId')}
                id="default-settings-thirdPartyDTransactionId"
                name="thirdPartyDTransactionId"
                data-cy="thirdPartyDTransactionId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.defaultSettings.defaultSettingCode')}
                id="default-settings-defaultSettingCode"
                name="defaultSettingCode"
                data-cy="defaultSettingCode"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.defaultSettings.jsonSettings')}
                id="default-settings-jsonSettings"
                name="jsonSettings"
                data-cy="jsonSettings"
                type="textarea"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.defaultSettings.appConfig')}
                id="default-settings-appConfig"
                name="appConfig"
                data-cy="appConfig"
                type="textarea"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.defaultSettings.appName')}
                id="default-settings-appName"
                name="appName"
                data-cy="appName"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField')}
                id="default-settings-freeField"
                name="freeField"
                data-cy="freeField"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField1')}
                id="default-settings-freeField1"
                name="freeField1"
                data-cy="freeField1"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField2')}
                id="default-settings-freeField2"
                name="freeField2"
                data-cy="freeField2"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField3')}
                id="default-settings-freeField3"
                name="freeField3"
                data-cy="freeField3"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField4')}
                id="default-settings-freeField4"
                name="freeField4"
                data-cy="freeField4"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField5')}
                id="default-settings-freeField5"
                name="freeField5"
                data-cy="freeField5"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField6')}
                id="default-settings-freeField6"
                name="freeField6"
                data-cy="freeField6"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField8')}
                id="default-settings-freeField8"
                name="freeField8"
                data-cy="freeField8"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField9')}
                id="default-settings-freeField9"
                name="freeField9"
                data-cy="freeField9"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField10')}
                id="default-settings-freeField10"
                name="freeField10"
                data-cy="freeField10"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField11')}
                id="default-settings-freeField11"
                name="freeField11"
                data-cy="freeField11"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField12')}
                id="default-settings-freeField12"
                name="freeField12"
                data-cy="freeField12"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField13')}
                id="default-settings-freeField13"
                name="freeField13"
                data-cy="freeField13"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField14')}
                id="default-settings-freeField14"
                name="freeField14"
                data-cy="freeField14"
                type="text"
              />
              <ValidatedBlobField
                label={translate('absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField15')}
                id="default-settings-freeField15"
                name="freeField15"
                data-cy="freeField15"
                openActionLabel={translate('entity.action.open')}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField16')}
                id="default-settings-freeField16"
                name="freeField16"
                data-cy="freeField16"
                type="textarea"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField17')}
                id="default-settings-freeField17"
                name="freeField17"
                data-cy="freeField17"
                type="textarea"
              />
              <ValidatedBlobField
                label={translate('absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField18')}
                id="default-settings-freeField18"
                name="freeField18"
                data-cy="freeField18"
                openActionLabel={translate('entity.action.open')}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.defaultSettings.freeField19')}
                id="default-settings-freeField19"
                name="freeField19"
                data-cy="freeField19"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.defaultSettings.timestamp')}
                id="default-settings-timestamp"
                name="timestamp"
                data-cy="timestamp"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.defaultSettings.recordStatus')}
                id="default-settings-recordStatus"
                name="recordStatus"
                data-cy="recordStatus"
                type="select"
              >
                {recordStatusValues.map(recordStatus => (
                  <option value={recordStatus} key={recordStatus}>
                    {translate('absaUgdtMicrosrvcGatewayApp.RecordStatus.' + recordStatus)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.defaultSettings.createdAt')}
                id="default-settings-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.defaultSettings.createdBy')}
                id="default-settings-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.defaultSettings.updatedAt')}
                id="default-settings-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.defaultSettings.updatedby')}
                id="default-settings-updatedby"
                name="updatedby"
                data-cy="updatedby"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/default-settings" replace color="info">
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

export default DefaultSettingsUpdate;
