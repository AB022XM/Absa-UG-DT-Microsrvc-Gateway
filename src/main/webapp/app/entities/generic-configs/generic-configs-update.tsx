import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm, ValidatedBlobField } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IGenericConfigs } from 'app/shared/model/generic-configs.model';
import { RecordStatus } from 'app/shared/model/enumerations/record-status.model';
import { getEntity, updateEntity, createEntity, reset } from './generic-configs.reducer';

export const GenericConfigsUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const genericConfigsEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.genericConfigs.entity);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.genericConfigs.loading);
  const updating = useAppSelector(state => state.absaugdtmicrosrvcgateway.genericConfigs.updating);
  const updateSuccess = useAppSelector(state => state.absaugdtmicrosrvcgateway.genericConfigs.updateSuccess);
  const recordStatusValues = Object.keys(RecordStatus);

  const handleClose = () => {
    navigate('/generic-configs');
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

    const entity = {
      ...genericConfigsEntity,
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
        }
      : {
          recordStatus: 'ACTIVE',
          ...genericConfigsEntity,
          timestamp: convertDateTimeFromServer(genericConfigsEntity.timestamp),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="absaUgdtMicrosrvcGatewayApp.genericConfigs.home.createOrEditLabel" data-cy="GenericConfigsCreateUpdateHeading">
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.genericConfigs.home.createOrEditLabel">
              Create or edit a GenericConfigs
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
                  id="generic-configs-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.absaTranRef')}
                id="generic-configs-absaTranRef"
                name="absaTranRef"
                data-cy="absaTranRef"
                type="text"
                validate={{}}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.recordId')}
                id="generic-configs-recordId"
                name="recordId"
                data-cy="recordId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.configId')}
                id="generic-configs-configId"
                name="configId"
                data-cy="configId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.configName')}
                id="generic-configs-configName"
                name="configName"
                data-cy="configName"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.configsDetails')}
                id="generic-configs-configsDetails"
                name="configsDetails"
                data-cy="configsDetails"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.additionalConfigs')}
                id="generic-configs-additionalConfigs"
                name="additionalConfigs"
                data-cy="additionalConfigs"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField')}
                id="generic-configs-freeField"
                name="freeField"
                data-cy="freeField"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField1')}
                id="generic-configs-freeField1"
                name="freeField1"
                data-cy="freeField1"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField2')}
                id="generic-configs-freeField2"
                name="freeField2"
                data-cy="freeField2"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField3')}
                id="generic-configs-freeField3"
                name="freeField3"
                data-cy="freeField3"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField4')}
                id="generic-configs-freeField4"
                name="freeField4"
                data-cy="freeField4"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField5')}
                id="generic-configs-freeField5"
                name="freeField5"
                data-cy="freeField5"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField6')}
                id="generic-configs-freeField6"
                name="freeField6"
                data-cy="freeField6"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField8')}
                id="generic-configs-freeField8"
                name="freeField8"
                data-cy="freeField8"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField9')}
                id="generic-configs-freeField9"
                name="freeField9"
                data-cy="freeField9"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField10')}
                id="generic-configs-freeField10"
                name="freeField10"
                data-cy="freeField10"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField11')}
                id="generic-configs-freeField11"
                name="freeField11"
                data-cy="freeField11"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField12')}
                id="generic-configs-freeField12"
                name="freeField12"
                data-cy="freeField12"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField13')}
                id="generic-configs-freeField13"
                name="freeField13"
                data-cy="freeField13"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField14')}
                id="generic-configs-freeField14"
                name="freeField14"
                data-cy="freeField14"
                type="text"
              />
              <ValidatedBlobField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField15')}
                id="generic-configs-freeField15"
                name="freeField15"
                data-cy="freeField15"
                openActionLabel={translate('entity.action.open')}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField16')}
                id="generic-configs-freeField16"
                name="freeField16"
                data-cy="freeField16"
                type="textarea"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField17')}
                id="generic-configs-freeField17"
                name="freeField17"
                data-cy="freeField17"
                type="textarea"
              />
              <ValidatedBlobField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField18')}
                id="generic-configs-freeField18"
                name="freeField18"
                data-cy="freeField18"
                openActionLabel={translate('entity.action.open')}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField19')}
                id="generic-configs-freeField19"
                name="freeField19"
                data-cy="freeField19"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField20')}
                id="generic-configs-freeField20"
                name="freeField20"
                data-cy="freeField20"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField21')}
                id="generic-configs-freeField21"
                name="freeField21"
                data-cy="freeField21"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField22')}
                id="generic-configs-freeField22"
                name="freeField22"
                data-cy="freeField22"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField23')}
                id="generic-configs-freeField23"
                name="freeField23"
                data-cy="freeField23"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField24')}
                id="generic-configs-freeField24"
                name="freeField24"
                data-cy="freeField24"
                type="text"
              />
              <ValidatedBlobField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField25')}
                id="generic-configs-freeField25"
                name="freeField25"
                data-cy="freeField25"
                openActionLabel={translate('entity.action.open')}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField26')}
                id="generic-configs-freeField26"
                name="freeField26"
                data-cy="freeField26"
                type="textarea"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField27')}
                id="generic-configs-freeField27"
                name="freeField27"
                data-cy="freeField27"
                type="textarea"
              />
              <ValidatedBlobField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField28')}
                id="generic-configs-freeField28"
                name="freeField28"
                data-cy="freeField28"
                openActionLabel={translate('entity.action.open')}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField29')}
                id="generic-configs-freeField29"
                name="freeField29"
                data-cy="freeField29"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField30')}
                id="generic-configs-freeField30"
                name="freeField30"
                data-cy="freeField30"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField31')}
                id="generic-configs-freeField31"
                name="freeField31"
                data-cy="freeField31"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField32')}
                id="generic-configs-freeField32"
                name="freeField32"
                data-cy="freeField32"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField33')}
                id="generic-configs-freeField33"
                name="freeField33"
                data-cy="freeField33"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.freeField34')}
                id="generic-configs-freeField34"
                name="freeField34"
                data-cy="freeField34"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.timestamp')}
                id="generic-configs-timestamp"
                name="timestamp"
                data-cy="timestamp"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.genericConfigs.recordStatus')}
                id="generic-configs-recordStatus"
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/generic-configs" replace color="info">
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

export default GenericConfigsUpdate;
