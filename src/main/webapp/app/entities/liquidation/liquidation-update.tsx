import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ILiquidation } from 'app/shared/model/liquidation.model';
import { ServiceLevel } from 'app/shared/model/enumerations/service-level.model';
import { getEntity, updateEntity, createEntity, reset } from './liquidation.reducer';

export const LiquidationUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const liquidationEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.liquidation.entity);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.liquidation.loading);
  const updating = useAppSelector(state => state.absaugdtmicrosrvcgateway.liquidation.updating);
  const updateSuccess = useAppSelector(state => state.absaugdtmicrosrvcgateway.liquidation.updateSuccess);
  const serviceLevelValues = Object.keys(ServiceLevel);

  const handleClose = () => {
    navigate('/liquidation');
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
      ...liquidationEntity,
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
          serviceLevel: 'N',
          ...liquidationEntity,
          timestamp: convertDateTimeFromServer(liquidationEntity.timestamp),
          createdAt: convertDateTimeFromServer(liquidationEntity.createdAt),
          updatedAt: convertDateTimeFromServer(liquidationEntity.updatedAt),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="absaUgdtMicrosrvcGatewayApp.liquidation.home.createOrEditLabel" data-cy="LiquidationCreateUpdateHeading">
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.liquidation.home.createOrEditLabel">Create or edit a Liquidation</Translate>
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
                  id="liquidation-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.recordId')}
                id="liquidation-recordId"
                name="recordId"
                data-cy="recordId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.serviceLevel')}
                id="liquidation-serviceLevel"
                name="serviceLevel"
                data-cy="serviceLevel"
                type="select"
              >
                {serviceLevelValues.map(serviceLevel => (
                  <option value={serviceLevel} key={serviceLevel}>
                    {translate('absaUgdtMicrosrvcGatewayApp.ServiceLevel.' + serviceLevel)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.timestamp')}
                id="liquidation-timestamp"
                name="timestamp"
                data-cy="timestamp"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.partnerCode')}
                id="liquidation-partnerCode"
                name="partnerCode"
                data-cy="partnerCode"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.amount')}
                id="liquidation-amount"
                name="amount"
                data-cy="amount"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.currency')}
                id="liquidation-currency"
                name="currency"
                data-cy="currency"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.receiverBankcode')}
                id="liquidation-receiverBankcode"
                name="receiverBankcode"
                data-cy="receiverBankcode"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.receiverAccountNumber')}
                id="liquidation-receiverAccountNumber"
                name="receiverAccountNumber"
                data-cy="receiverAccountNumber"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.beneficiaryName')}
                id="liquidation-beneficiaryName"
                name="beneficiaryName"
                data-cy="beneficiaryName"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.instructionId')}
                id="liquidation-instructionId"
                name="instructionId"
                data-cy="instructionId"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.senderToReceiverInfo')}
                id="liquidation-senderToReceiverInfo"
                name="senderToReceiverInfo"
                data-cy="senderToReceiverInfo"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.freeText1')}
                id="liquidation-freeText1"
                name="freeText1"
                data-cy="freeText1"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.freeText2')}
                id="liquidation-freeText2"
                name="freeText2"
                data-cy="freeText2"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.freeText3')}
                id="liquidation-freeText3"
                name="freeText3"
                data-cy="freeText3"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.freeText4')}
                id="liquidation-freeText4"
                name="freeText4"
                data-cy="freeText4"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.freeText5')}
                id="liquidation-freeText5"
                name="freeText5"
                data-cy="freeText5"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.freeText6')}
                id="liquidation-freeText6"
                name="freeText6"
                data-cy="freeText6"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.freeText7')}
                id="liquidation-freeText7"
                name="freeText7"
                data-cy="freeText7"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.freeText8')}
                id="liquidation-freeText8"
                name="freeText8"
                data-cy="freeText8"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.freeText9')}
                id="liquidation-freeText9"
                name="freeText9"
                data-cy="freeText9"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.freeText10')}
                id="liquidation-freeText10"
                name="freeText10"
                data-cy="freeText10"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.freeText11')}
                id="liquidation-freeText11"
                name="freeText11"
                data-cy="freeText11"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.freeText12')}
                id="liquidation-freeText12"
                name="freeText12"
                data-cy="freeText12"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.freeText13')}
                id="liquidation-freeText13"
                name="freeText13"
                data-cy="freeText13"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.freeText14')}
                id="liquidation-freeText14"
                name="freeText14"
                data-cy="freeText14"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.freeText15')}
                id="liquidation-freeText15"
                name="freeText15"
                data-cy="freeText15"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.freeText16')}
                id="liquidation-freeText16"
                name="freeText16"
                data-cy="freeText16"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.freeText17')}
                id="liquidation-freeText17"
                name="freeText17"
                data-cy="freeText17"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.freeText18')}
                id="liquidation-freeText18"
                name="freeText18"
                data-cy="freeText18"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.freeText19')}
                id="liquidation-freeText19"
                name="freeText19"
                data-cy="freeText19"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.freeText20')}
                id="liquidation-freeText20"
                name="freeText20"
                data-cy="freeText20"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.freeText21')}
                id="liquidation-freeText21"
                name="freeText21"
                data-cy="freeText21"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.freeText22')}
                id="liquidation-freeText22"
                name="freeText22"
                data-cy="freeText22"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.freeText23')}
                id="liquidation-freeText23"
                name="freeText23"
                data-cy="freeText23"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.freeText24')}
                id="liquidation-freeText24"
                name="freeText24"
                data-cy="freeText24"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.freeText25')}
                id="liquidation-freeText25"
                name="freeText25"
                data-cy="freeText25"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.freeText26')}
                id="liquidation-freeText26"
                name="freeText26"
                data-cy="freeText26"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.freeText27')}
                id="liquidation-freeText27"
                name="freeText27"
                data-cy="freeText27"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.freeText28')}
                id="liquidation-freeText28"
                name="freeText28"
                data-cy="freeText28"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.createdAt')}
                id="liquidation-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.createdBy')}
                id="liquidation-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.updatedAt')}
                id="liquidation-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.liquidation.updatedBy')}
                id="liquidation-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/liquidation" replace color="info">
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

export default LiquidationUpdate;
