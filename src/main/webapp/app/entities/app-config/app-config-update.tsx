import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm, ValidatedBlobField } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IApps } from 'app/shared/model/apps.model';
import { getEntities as getApps } from 'app/entities/apps/apps.reducer';
import { IAppConfig } from 'app/shared/model/app-config.model';
import { getEntity, updateEntity, createEntity, reset } from './app-config.reducer';

export const AppConfigUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const apps = useAppSelector(state => state.absaugdtmicrosrvcgateway.apps.entities);
  const appConfigEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.appConfig.entity);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.appConfig.loading);
  const updating = useAppSelector(state => state.absaugdtmicrosrvcgateway.appConfig.updating);
  const updateSuccess = useAppSelector(state => state.absaugdtmicrosrvcgateway.appConfig.updateSuccess);

  const handleClose = () => {
    navigate('/app-config');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getApps({}));
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
      ...appConfigEntity,
      ...values,
      apps: apps.find(it => it.id.toString() === values.apps.toString()),
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
          ...appConfigEntity,
          timestamp: convertDateTimeFromServer(appConfigEntity.timestamp),
          createdAt: convertDateTimeFromServer(appConfigEntity.createdAt),
          updatedAt: convertDateTimeFromServer(appConfigEntity.updatedAt),
          apps: appConfigEntity?.apps?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="absaUgdtMicrosrvcGatewayApp.appConfig.home.createOrEditLabel" data-cy="AppConfigCreateUpdateHeading">
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.appConfig.home.createOrEditLabel">Create or edit a AppConfig</Translate>
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
                  id="app-config-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.appConfig.absaTranRef')}
                id="app-config-absaTranRef"
                name="absaTranRef"
                data-cy="absaTranRef"
                type="text"
                validate={{}}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.appConfig.recordId')}
                id="app-config-recordId"
                name="recordId"
                data-cy="recordId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.appConfig.configId')}
                id="app-config-configId"
                name="configId"
                data-cy="configId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.appConfig.configName')}
                id="app-config-configName"
                name="configName"
                data-cy="configName"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.appConfig.configValue')}
                id="app-config-configValue"
                name="configValue"
                data-cy="configValue"
                type="textarea"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.appConfig.configType')}
                id="app-config-configType"
                name="configType"
                data-cy="configType"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.appConfig.configDescription')}
                id="app-config-configDescription"
                name="configDescription"
                data-cy="configDescription"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.appConfig.configStatus')}
                id="app-config-configStatus"
                name="configStatus"
                data-cy="configStatus"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.appConfig.freeField1')}
                id="app-config-freeField1"
                name="freeField1"
                data-cy="freeField1"
                type="textarea"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.appConfig.freeField2')}
                id="app-config-freeField2"
                name="freeField2"
                data-cy="freeField2"
                type="textarea"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.appConfig.freeField3')}
                id="app-config-freeField3"
                name="freeField3"
                data-cy="freeField3"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.appConfig.freeField4')}
                id="app-config-freeField4"
                name="freeField4"
                data-cy="freeField4"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.appConfig.freeField5')}
                id="app-config-freeField5"
                name="freeField5"
                data-cy="freeField5"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.appConfig.freeField6')}
                id="app-config-freeField6"
                name="freeField6"
                data-cy="freeField6"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.appConfig.freeField7')}
                id="app-config-freeField7"
                name="freeField7"
                data-cy="freeField7"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.appConfig.freeField8')}
                id="app-config-freeField8"
                name="freeField8"
                data-cy="freeField8"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.appConfig.freeField9')}
                id="app-config-freeField9"
                name="freeField9"
                data-cy="freeField9"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.appConfig.freeField10')}
                id="app-config-freeField10"
                name="freeField10"
                data-cy="freeField10"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.appConfig.freeField11')}
                id="app-config-freeField11"
                name="freeField11"
                data-cy="freeField11"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.appConfig.freeField12')}
                id="app-config-freeField12"
                name="freeField12"
                data-cy="freeField12"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.appConfig.freeField13')}
                id="app-config-freeField13"
                name="freeField13"
                data-cy="freeField13"
                type="text"
              />
              <ValidatedBlobField
                label={translate('absaUgdtMicrosrvcGatewayApp.appConfig.freeField14')}
                id="app-config-freeField14"
                name="freeField14"
                data-cy="freeField14"
                openActionLabel={translate('entity.action.open')}
              />
              <ValidatedBlobField
                label={translate('absaUgdtMicrosrvcGatewayApp.appConfig.freeField15')}
                id="app-config-freeField15"
                name="freeField15"
                data-cy="freeField15"
                openActionLabel={translate('entity.action.open')}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.appConfig.freeField16')}
                id="app-config-freeField16"
                name="freeField16"
                data-cy="freeField16"
                type="textarea"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.appConfig.freeField17')}
                id="app-config-freeField17"
                name="freeField17"
                data-cy="freeField17"
                type="textarea"
              />
              <ValidatedBlobField
                label={translate('absaUgdtMicrosrvcGatewayApp.appConfig.freeField18')}
                id="app-config-freeField18"
                name="freeField18"
                data-cy="freeField18"
                openActionLabel={translate('entity.action.open')}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.appConfig.freeField19')}
                id="app-config-freeField19"
                name="freeField19"
                data-cy="freeField19"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.appConfig.delflg')}
                id="app-config-delflg"
                name="delflg"
                data-cy="delflg"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.appConfig.timestamp')}
                id="app-config-timestamp"
                name="timestamp"
                data-cy="timestamp"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.appConfig.createdAt')}
                id="app-config-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.appConfig.createdBy')}
                id="app-config-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.appConfig.updatedAt')}
                id="app-config-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.appConfig.updatedBy')}
                id="app-config-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <ValidatedField
                id="app-config-apps"
                name="apps"
                data-cy="apps"
                label={translate('absaUgdtMicrosrvcGatewayApp.appConfig.apps')}
                type="select"
              >
                <option value="" key="0" />
                {apps
                  ? apps.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/app-config" replace color="info">
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

export default AppConfigUpdate;
