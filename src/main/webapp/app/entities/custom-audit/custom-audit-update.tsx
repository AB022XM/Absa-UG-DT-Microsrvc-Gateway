import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ICustomAudit } from 'app/shared/model/custom-audit.model';
import { ServiceLevel } from 'app/shared/model/enumerations/service-level.model';
import { getEntity, updateEntity, createEntity, reset } from './custom-audit.reducer';

export const CustomAuditUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const customAuditEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.customAudit.entity);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.customAudit.loading);
  const updating = useAppSelector(state => state.absaugdtmicrosrvcgateway.customAudit.updating);
  const updateSuccess = useAppSelector(state => state.absaugdtmicrosrvcgateway.customAudit.updateSuccess);
  const serviceLevelValues = Object.keys(ServiceLevel);

  const handleClose = () => {
    navigate('/custom-audit');
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
      ...customAuditEntity,
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
          actionId: 'N',
          ...customAuditEntity,
          timestamp: convertDateTimeFromServer(customAuditEntity.timestamp),
          createdAt: convertDateTimeFromServer(customAuditEntity.createdAt),
          updatedAt: convertDateTimeFromServer(customAuditEntity.updatedAt),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="absaUgdtMicrosrvcGatewayApp.customAudit.home.createOrEditLabel" data-cy="CustomAuditCreateUpdateHeading">
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAudit.home.createOrEditLabel">Create or edit a CustomAudit</Translate>
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
                  id="custom-audit-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.absaTranRef')}
                id="custom-audit-absaTranRef"
                name="absaTranRef"
                data-cy="absaTranRef"
                type="text"
                validate={{}}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.recordId')}
                id="custom-audit-recordId"
                name="recordId"
                data-cy="recordId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.actionId')}
                id="custom-audit-actionId"
                name="actionId"
                data-cy="actionId"
                type="select"
              >
                {serviceLevelValues.map(serviceLevel => (
                  <option value={serviceLevel} key={serviceLevel}>
                    {translate('absaUgdtMicrosrvcGatewayApp.ServiceLevel.' + serviceLevel)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.timestamp')}
                id="custom-audit-timestamp"
                name="timestamp"
                data-cy="timestamp"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.oldValue')}
                id="custom-audit-oldValue"
                name="oldValue"
                data-cy="oldValue"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.newValue')}
                id="custom-audit-newValue"
                name="newValue"
                data-cy="newValue"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.changeResaon')}
                id="custom-audit-changeResaon"
                name="changeResaon"
                data-cy="changeResaon"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.description')}
                id="custom-audit-description"
                name="description"
                data-cy="description"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.description1')}
                id="custom-audit-description1"
                name="description1"
                data-cy="description1"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.description2')}
                id="custom-audit-description2"
                name="description2"
                data-cy="description2"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.description3')}
                id="custom-audit-description3"
                name="description3"
                data-cy="description3"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.description4')}
                id="custom-audit-description4"
                name="description4"
                data-cy="description4"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.description5')}
                id="custom-audit-description5"
                name="description5"
                data-cy="description5"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.description6')}
                id="custom-audit-description6"
                name="description6"
                data-cy="description6"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.description7')}
                id="custom-audit-description7"
                name="description7"
                data-cy="description7"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.description8')}
                id="custom-audit-description8"
                name="description8"
                data-cy="description8"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.description9')}
                id="custom-audit-description9"
                name="description9"
                data-cy="description9"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.freeText1')}
                id="custom-audit-freeText1"
                name="freeText1"
                data-cy="freeText1"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.freeText2')}
                id="custom-audit-freeText2"
                name="freeText2"
                data-cy="freeText2"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.freeText3')}
                id="custom-audit-freeText3"
                name="freeText3"
                data-cy="freeText3"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.freeText4')}
                id="custom-audit-freeText4"
                name="freeText4"
                data-cy="freeText4"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.freeText5')}
                id="custom-audit-freeText5"
                name="freeText5"
                data-cy="freeText5"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.freeText6')}
                id="custom-audit-freeText6"
                name="freeText6"
                data-cy="freeText6"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.freeText7')}
                id="custom-audit-freeText7"
                name="freeText7"
                data-cy="freeText7"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.freeText8')}
                id="custom-audit-freeText8"
                name="freeText8"
                data-cy="freeText8"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.freeText9')}
                id="custom-audit-freeText9"
                name="freeText9"
                data-cy="freeText9"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.freeText10')}
                id="custom-audit-freeText10"
                name="freeText10"
                data-cy="freeText10"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.freeText11')}
                id="custom-audit-freeText11"
                name="freeText11"
                data-cy="freeText11"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.freeText12')}
                id="custom-audit-freeText12"
                name="freeText12"
                data-cy="freeText12"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.freeText13')}
                id="custom-audit-freeText13"
                name="freeText13"
                data-cy="freeText13"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.freeText14')}
                id="custom-audit-freeText14"
                name="freeText14"
                data-cy="freeText14"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.freeText15')}
                id="custom-audit-freeText15"
                name="freeText15"
                data-cy="freeText15"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.freeText16')}
                id="custom-audit-freeText16"
                name="freeText16"
                data-cy="freeText16"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.freeText17')}
                id="custom-audit-freeText17"
                name="freeText17"
                data-cy="freeText17"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.freeText18')}
                id="custom-audit-freeText18"
                name="freeText18"
                data-cy="freeText18"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.freeText19')}
                id="custom-audit-freeText19"
                name="freeText19"
                data-cy="freeText19"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.freeText20')}
                id="custom-audit-freeText20"
                name="freeText20"
                data-cy="freeText20"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.freeText21')}
                id="custom-audit-freeText21"
                name="freeText21"
                data-cy="freeText21"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.freeText22')}
                id="custom-audit-freeText22"
                name="freeText22"
                data-cy="freeText22"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.freeText23')}
                id="custom-audit-freeText23"
                name="freeText23"
                data-cy="freeText23"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.freeText24')}
                id="custom-audit-freeText24"
                name="freeText24"
                data-cy="freeText24"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.freeText25')}
                id="custom-audit-freeText25"
                name="freeText25"
                data-cy="freeText25"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.freeText26')}
                id="custom-audit-freeText26"
                name="freeText26"
                data-cy="freeText26"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.freeText27')}
                id="custom-audit-freeText27"
                name="freeText27"
                data-cy="freeText27"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.freeText28')}
                id="custom-audit-freeText28"
                name="freeText28"
                data-cy="freeText28"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.createdAt')}
                id="custom-audit-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.createdBy')}
                id="custom-audit-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.updatedAt')}
                id="custom-audit-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAudit.updatedBy')}
                id="custom-audit-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/custom-audit" replace color="info">
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

export default CustomAuditUpdate;
