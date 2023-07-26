import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ICurrency } from 'app/shared/model/currency.model';
import { RecordStatus } from 'app/shared/model/enumerations/record-status.model';
import { getEntity, updateEntity, createEntity, reset } from './currency.reducer';

export const CurrencyUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const currencyEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.currency.entity);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.currency.loading);
  const updating = useAppSelector(state => state.absaugdtmicrosrvcgateway.currency.updating);
  const updateSuccess = useAppSelector(state => state.absaugdtmicrosrvcgateway.currency.updateSuccess);
  const recordStatusValues = Object.keys(RecordStatus);

  const handleClose = () => {
    navigate('/currency');
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
      ...currencyEntity,
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
          ...currencyEntity,
          timestamp: convertDateTimeFromServer(currencyEntity.timestamp),
          createdAt: convertDateTimeFromServer(currencyEntity.createdAt),
          updatedAt: convertDateTimeFromServer(currencyEntity.updatedAt),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="absaUgdtMicrosrvcGatewayApp.currency.home.createOrEditLabel" data-cy="CurrencyCreateUpdateHeading">
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.currency.home.createOrEditLabel">Create or edit a Currency</Translate>
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
                  id="currency-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.currency.absaTranRef')}
                id="currency-absaTranRef"
                name="absaTranRef"
                data-cy="absaTranRef"
                type="text"
                validate={{}}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.currency.dtDTransactionId')}
                id="currency-dtDTransactionId"
                name="dtDTransactionId"
                data-cy="dtDTransactionId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.currency.amolDTransactionId')}
                id="currency-amolDTransactionId"
                name="amolDTransactionId"
                data-cy="amolDTransactionId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.currency.transactionReferenceNumber')}
                id="currency-transactionReferenceNumber"
                name="transactionReferenceNumber"
                data-cy="transactionReferenceNumber"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.currency.token')}
                id="currency-token"
                name="token"
                data-cy="token"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.currency.currencyUniqueId')}
                id="currency-currencyUniqueId"
                name="currencyUniqueId"
                data-cy="currencyUniqueId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.currency.currencyCode')}
                id="currency-currencyCode"
                name="currencyCode"
                data-cy="currencyCode"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.currency.currencyName')}
                id="currency-currencyName"
                name="currencyName"
                data-cy="currencyName"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.currency.freeField1')}
                id="currency-freeField1"
                name="freeField1"
                data-cy="freeField1"
                type="textarea"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.currency.freeField2')}
                id="currency-freeField2"
                name="freeField2"
                data-cy="freeField2"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.currency.freeField3')}
                id="currency-freeField3"
                name="freeField3"
                data-cy="freeField3"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.currency.freeField4')}
                id="currency-freeField4"
                name="freeField4"
                data-cy="freeField4"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.currency.freeField5')}
                id="currency-freeField5"
                name="freeField5"
                data-cy="freeField5"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.currency.freeField6')}
                id="currency-freeField6"
                name="freeField6"
                data-cy="freeField6"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.currency.timestamp')}
                id="currency-timestamp"
                name="timestamp"
                data-cy="timestamp"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.currency.recordStatus')}
                id="currency-recordStatus"
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
                label={translate('absaUgdtMicrosrvcGatewayApp.currency.createdAt')}
                id="currency-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.currency.createdBy')}
                id="currency-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.currency.updatedAt')}
                id="currency-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.currency.updatedBy')}
                id="currency-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/currency" replace color="info">
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

export default CurrencyUpdate;
