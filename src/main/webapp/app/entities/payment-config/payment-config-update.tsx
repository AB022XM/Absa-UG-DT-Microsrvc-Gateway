import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm, ValidatedBlobField } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IPaymentConfig } from 'app/shared/model/payment-config.model';
import { ModeOfPayment } from 'app/shared/model/enumerations/mode-of-payment.model';
import { getEntity, updateEntity, createEntity, reset } from './payment-config.reducer';

export const PaymentConfigUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const paymentConfigEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.paymentConfig.entity);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.paymentConfig.loading);
  const updating = useAppSelector(state => state.absaugdtmicrosrvcgateway.paymentConfig.updating);
  const updateSuccess = useAppSelector(state => state.absaugdtmicrosrvcgateway.paymentConfig.updateSuccess);
  const modeOfPaymentValues = Object.keys(ModeOfPayment);

  const handleClose = () => {
    navigate('/payment-config');
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
      ...paymentConfigEntity,
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
          paymentMethod: 'CASH',
          ...paymentConfigEntity,
          timestamp: convertDateTimeFromServer(paymentConfigEntity.timestamp),
          createdAt: convertDateTimeFromServer(paymentConfigEntity.createdAt),
          updatedAt: convertDateTimeFromServer(paymentConfigEntity.updatedAt),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="absaUgdtMicrosrvcGatewayApp.paymentConfig.home.createOrEditLabel" data-cy="PaymentConfigCreateUpdateHeading">
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.paymentConfig.home.createOrEditLabel">
              Create or edit a PaymentConfig
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
                  id="payment-config-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentConfig.recordId')}
                id="payment-config-recordId"
                name="recordId"
                data-cy="recordId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentConfig.paymentId')}
                id="payment-config-paymentId"
                name="paymentId"
                data-cy="paymentId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentConfig.paymentName')}
                id="payment-config-paymentName"
                name="paymentName"
                data-cy="paymentName"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentConfig.paymentType')}
                id="payment-config-paymentType"
                name="paymentType"
                data-cy="paymentType"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentConfig.paymentDescription')}
                id="payment-config-paymentDescription"
                name="paymentDescription"
                data-cy="paymentDescription"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentConfig.paymentStatus')}
                id="payment-config-paymentStatus"
                name="paymentStatus"
                data-cy="paymentStatus"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentConfig.paymentMethod')}
                id="payment-config-paymentMethod"
                name="paymentMethod"
                data-cy="paymentMethod"
                type="select"
              >
                {modeOfPaymentValues.map(modeOfPayment => (
                  <option value={modeOfPayment} key={modeOfPayment}>
                    {translate('absaUgdtMicrosrvcGatewayApp.ModeOfPayment.' + modeOfPayment)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentConfig.paymentAmount')}
                id="payment-config-paymentAmount"
                name="paymentAmount"
                data-cy="paymentAmount"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentConfig.additionalConfig')}
                id="payment-config-additionalConfig"
                name="additionalConfig"
                data-cy="additionalConfig"
                type="textarea"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField1')}
                id="payment-config-freeField1"
                name="freeField1"
                data-cy="freeField1"
                type="textarea"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField2')}
                id="payment-config-freeField2"
                name="freeField2"
                data-cy="freeField2"
                type="textarea"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField3')}
                id="payment-config-freeField3"
                name="freeField3"
                data-cy="freeField3"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField4')}
                id="payment-config-freeField4"
                name="freeField4"
                data-cy="freeField4"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField5')}
                id="payment-config-freeField5"
                name="freeField5"
                data-cy="freeField5"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField6')}
                id="payment-config-freeField6"
                name="freeField6"
                data-cy="freeField6"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField8')}
                id="payment-config-freeField8"
                name="freeField8"
                data-cy="freeField8"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField9')}
                id="payment-config-freeField9"
                name="freeField9"
                data-cy="freeField9"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField10')}
                id="payment-config-freeField10"
                name="freeField10"
                data-cy="freeField10"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField11')}
                id="payment-config-freeField11"
                name="freeField11"
                data-cy="freeField11"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField12')}
                id="payment-config-freeField12"
                name="freeField12"
                data-cy="freeField12"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField13')}
                id="payment-config-freeField13"
                name="freeField13"
                data-cy="freeField13"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField14')}
                id="payment-config-freeField14"
                name="freeField14"
                data-cy="freeField14"
                type="text"
              />
              <ValidatedBlobField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField15')}
                id="payment-config-freeField15"
                name="freeField15"
                data-cy="freeField15"
                openActionLabel={translate('entity.action.open')}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField16')}
                id="payment-config-freeField16"
                name="freeField16"
                data-cy="freeField16"
                type="textarea"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField17')}
                id="payment-config-freeField17"
                name="freeField17"
                data-cy="freeField17"
                type="textarea"
              />
              <ValidatedBlobField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField18')}
                id="payment-config-freeField18"
                name="freeField18"
                data-cy="freeField18"
                openActionLabel={translate('entity.action.open')}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField19')}
                id="payment-config-freeField19"
                name="freeField19"
                data-cy="freeField19"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentConfig.freeField20')}
                id="payment-config-freeField20"
                name="freeField20"
                data-cy="freeField20"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentConfig.timestamp')}
                id="payment-config-timestamp"
                name="timestamp"
                data-cy="timestamp"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentConfig.createdAt')}
                id="payment-config-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentConfig.createdBy')}
                id="payment-config-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentConfig.updatedAt')}
                id="payment-config-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.paymentConfig.updatedBy')}
                id="payment-config-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/payment-config" replace color="info">
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

export default PaymentConfigUpdate;
