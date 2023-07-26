import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ICustomAuditHistory } from 'app/shared/model/custom-audit-history.model';
import { ServiceLevel } from 'app/shared/model/enumerations/service-level.model';
import { getEntity, updateEntity, createEntity, reset } from './custom-audit-history.reducer';

export const CustomAuditHistoryUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const customAuditHistoryEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.customAuditHistory.entity);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.customAuditHistory.loading);
  const updating = useAppSelector(state => state.absaugdtmicrosrvcgateway.customAuditHistory.updating);
  const updateSuccess = useAppSelector(state => state.absaugdtmicrosrvcgateway.customAuditHistory.updateSuccess);
  const serviceLevelValues = Object.keys(ServiceLevel);

  const handleClose = () => {
    navigate('/custom-audit-history');
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
      ...customAuditHistoryEntity,
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
          ...customAuditHistoryEntity,
          timestamp: convertDateTimeFromServer(customAuditHistoryEntity.timestamp),
          createdAt: convertDateTimeFromServer(customAuditHistoryEntity.createdAt),
          updatedAt: convertDateTimeFromServer(customAuditHistoryEntity.updatedAt),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="absaUgdtMicrosrvcGatewayApp.customAuditHistory.home.createOrEditLabel" data-cy="CustomAuditHistoryCreateUpdateHeading">
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.customAuditHistory.home.createOrEditLabel">
              Create or edit a CustomAuditHistory
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
                  id="custom-audit-history-id"
                  label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.recordId')}
                id="custom-audit-history-recordId"
                name="recordId"
                data-cy="recordId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.actionId')}
                id="custom-audit-history-actionId"
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
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.timestamp')}
                id="custom-audit-history-timestamp"
                name="timestamp"
                data-cy="timestamp"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.oldValue')}
                id="custom-audit-history-oldValue"
                name="oldValue"
                data-cy="oldValue"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.newValue')}
                id="custom-audit-history-newValue"
                name="newValue"
                data-cy="newValue"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.changeReason')}
                id="custom-audit-history-changeReason"
                name="changeReason"
                data-cy="changeReason"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.description')}
                id="custom-audit-history-description"
                name="description"
                data-cy="description"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.description1')}
                id="custom-audit-history-description1"
                name="description1"
                data-cy="description1"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.description2')}
                id="custom-audit-history-description2"
                name="description2"
                data-cy="description2"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.description3')}
                id="custom-audit-history-description3"
                name="description3"
                data-cy="description3"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.description4')}
                id="custom-audit-history-description4"
                name="description4"
                data-cy="description4"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.description5')}
                id="custom-audit-history-description5"
                name="description5"
                data-cy="description5"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.description6')}
                id="custom-audit-history-description6"
                name="description6"
                data-cy="description6"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.description7')}
                id="custom-audit-history-description7"
                name="description7"
                data-cy="description7"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.description8')}
                id="custom-audit-history-description8"
                name="description8"
                data-cy="description8"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.description9')}
                id="custom-audit-history-description9"
                name="description9"
                data-cy="description9"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText1')}
                id="custom-audit-history-freeText1"
                name="freeText1"
                data-cy="freeText1"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText2')}
                id="custom-audit-history-freeText2"
                name="freeText2"
                data-cy="freeText2"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText3')}
                id="custom-audit-history-freeText3"
                name="freeText3"
                data-cy="freeText3"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText4')}
                id="custom-audit-history-freeText4"
                name="freeText4"
                data-cy="freeText4"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText5')}
                id="custom-audit-history-freeText5"
                name="freeText5"
                data-cy="freeText5"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText6')}
                id="custom-audit-history-freeText6"
                name="freeText6"
                data-cy="freeText6"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText7')}
                id="custom-audit-history-freeText7"
                name="freeText7"
                data-cy="freeText7"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText8')}
                id="custom-audit-history-freeText8"
                name="freeText8"
                data-cy="freeText8"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText9')}
                id="custom-audit-history-freeText9"
                name="freeText9"
                data-cy="freeText9"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText10')}
                id="custom-audit-history-freeText10"
                name="freeText10"
                data-cy="freeText10"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText11')}
                id="custom-audit-history-freeText11"
                name="freeText11"
                data-cy="freeText11"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText12')}
                id="custom-audit-history-freeText12"
                name="freeText12"
                data-cy="freeText12"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText13')}
                id="custom-audit-history-freeText13"
                name="freeText13"
                data-cy="freeText13"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText14')}
                id="custom-audit-history-freeText14"
                name="freeText14"
                data-cy="freeText14"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText15')}
                id="custom-audit-history-freeText15"
                name="freeText15"
                data-cy="freeText15"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText16')}
                id="custom-audit-history-freeText16"
                name="freeText16"
                data-cy="freeText16"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText17')}
                id="custom-audit-history-freeText17"
                name="freeText17"
                data-cy="freeText17"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText18')}
                id="custom-audit-history-freeText18"
                name="freeText18"
                data-cy="freeText18"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText19')}
                id="custom-audit-history-freeText19"
                name="freeText19"
                data-cy="freeText19"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText20')}
                id="custom-audit-history-freeText20"
                name="freeText20"
                data-cy="freeText20"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText21')}
                id="custom-audit-history-freeText21"
                name="freeText21"
                data-cy="freeText21"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText22')}
                id="custom-audit-history-freeText22"
                name="freeText22"
                data-cy="freeText22"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText23')}
                id="custom-audit-history-freeText23"
                name="freeText23"
                data-cy="freeText23"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText24')}
                id="custom-audit-history-freeText24"
                name="freeText24"
                data-cy="freeText24"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText25')}
                id="custom-audit-history-freeText25"
                name="freeText25"
                data-cy="freeText25"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText26')}
                id="custom-audit-history-freeText26"
                name="freeText26"
                data-cy="freeText26"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText27')}
                id="custom-audit-history-freeText27"
                name="freeText27"
                data-cy="freeText27"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.freeText28')}
                id="custom-audit-history-freeText28"
                name="freeText28"
                data-cy="freeText28"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.createdAt')}
                id="custom-audit-history-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.createdBy')}
                id="custom-audit-history-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.updatedAt')}
                id="custom-audit-history-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.customAuditHistory.updatedBy')}
                id="custom-audit-history-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/custom-audit-history" replace color="info">
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

export default CustomAuditHistoryUpdate;
