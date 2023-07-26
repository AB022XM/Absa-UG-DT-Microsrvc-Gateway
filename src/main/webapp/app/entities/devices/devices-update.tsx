import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm, ValidatedBlobField } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IDevices } from 'app/shared/model/devices.model';
import { RecordStatus } from 'app/shared/model/enumerations/record-status.model';
import { getEntity, updateEntity, createEntity, reset } from './devices.reducer';

export const DevicesUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const devicesEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.devices.entity);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.devices.loading);
  const updating = useAppSelector(state => state.absaugdtmicrosrvcgateway.devices.updating);
  const updateSuccess = useAppSelector(state => state.absaugdtmicrosrvcgateway.devices.updateSuccess);
  const recordStatusValues = Object.keys(RecordStatus);

  const handleClose = () => {
    navigate('/devices');
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
      ...devicesEntity,
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
          ...devicesEntity,
          timestamp: convertDateTimeFromServer(devicesEntity.timestamp),
          createdAt: convertDateTimeFromServer(devicesEntity.createdAt),
          updatedAt: convertDateTimeFromServer(devicesEntity.updatedAt),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="absaUgdtMicrosrvcGatewayApp.devices.home.createOrEditLabel" data-cy="DevicesCreateUpdateHeading">
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.devices.home.createOrEditLabel">Create or edit a Devices</Translate>
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
                  id="devices-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.devices.absaTranRef')}
                id="devices-absaTranRef"
                name="absaTranRef"
                data-cy="absaTranRef"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.devices.recordId')}
                id="devices-recordId"
                name="recordId"
                data-cy="recordId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.devices.deviceId')}
                id="devices-deviceId"
                name="deviceId"
                data-cy="deviceId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.devices.deviceName')}
                id="devices-deviceName"
                name="deviceName"
                data-cy="deviceName"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.devices.deviceType')}
                id="devices-deviceType"
                name="deviceType"
                data-cy="deviceType"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.devices.deviceDescription')}
                id="devices-deviceDescription"
                name="deviceDescription"
                data-cy="deviceDescription"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.devices.timestamp')}
                id="devices-timestamp"
                name="timestamp"
                data-cy="timestamp"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.devices.delflg')}
                id="devices-delflg"
                name="delflg"
                data-cy="delflg"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.devices.recordStatus')}
                id="devices-recordStatus"
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
                label={translate('absaUgdtMicrosrvcGatewayApp.devices.freeField')}
                id="devices-freeField"
                name="freeField"
                data-cy="freeField"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.devices.freeField1')}
                id="devices-freeField1"
                name="freeField1"
                data-cy="freeField1"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.devices.freeField2')}
                id="devices-freeField2"
                name="freeField2"
                data-cy="freeField2"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.devices.freeField3')}
                id="devices-freeField3"
                name="freeField3"
                data-cy="freeField3"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.devices.freeField4')}
                id="devices-freeField4"
                name="freeField4"
                data-cy="freeField4"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.devices.freeField5')}
                id="devices-freeField5"
                name="freeField5"
                data-cy="freeField5"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.devices.freeField6')}
                id="devices-freeField6"
                name="freeField6"
                data-cy="freeField6"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.devices.freeField7')}
                id="devices-freeField7"
                name="freeField7"
                data-cy="freeField7"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.devices.freeField8')}
                id="devices-freeField8"
                name="freeField8"
                data-cy="freeField8"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.devices.freeField9')}
                id="devices-freeField9"
                name="freeField9"
                data-cy="freeField9"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.devices.freeField10')}
                id="devices-freeField10"
                name="freeField10"
                data-cy="freeField10"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.devices.freeField11')}
                id="devices-freeField11"
                name="freeField11"
                data-cy="freeField11"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.devices.freeField12')}
                id="devices-freeField12"
                name="freeField12"
                data-cy="freeField12"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.devices.freeField13')}
                id="devices-freeField13"
                name="freeField13"
                data-cy="freeField13"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.devices.freeField14')}
                id="devices-freeField14"
                name="freeField14"
                data-cy="freeField14"
                type="text"
              />
              <ValidatedBlobField
                label={translate('absaUgdtMicrosrvcGatewayApp.devices.freeField15')}
                id="devices-freeField15"
                name="freeField15"
                data-cy="freeField15"
                openActionLabel={translate('entity.action.open')}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.devices.freeField16')}
                id="devices-freeField16"
                name="freeField16"
                data-cy="freeField16"
                type="textarea"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.devices.freeField17')}
                id="devices-freeField17"
                name="freeField17"
                data-cy="freeField17"
                type="textarea"
              />
              <ValidatedBlobField
                label={translate('absaUgdtMicrosrvcGatewayApp.devices.freeField18')}
                id="devices-freeField18"
                name="freeField18"
                data-cy="freeField18"
                openActionLabel={translate('entity.action.open')}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.devices.freeField19')}
                id="devices-freeField19"
                name="freeField19"
                data-cy="freeField19"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.devices.createdAt')}
                id="devices-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.devices.createdBy')}
                id="devices-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.devices.updatedAt')}
                id="devices-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.devices.updatedBy')}
                id="devices-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/devices" replace color="info">
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

export default DevicesUpdate;
