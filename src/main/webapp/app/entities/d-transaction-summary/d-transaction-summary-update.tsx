import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm, ValidatedBlobField } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ICustomer } from 'app/shared/model/customer.model';
import { getEntities as getCustomers } from 'app/entities/customer/customer.reducer';
import { IDTransactionSummary } from 'app/shared/model/d-transaction-summary.model';
import { TranStatus } from 'app/shared/model/enumerations/tran-status.model';
import { ErrorCodes } from 'app/shared/model/enumerations/error-codes.model';
import { ErrorMessage } from 'app/shared/model/enumerations/error-message.model';
import { getEntity, updateEntity, createEntity, reset } from './d-transaction-summary.reducer';

export const DTransactionSummaryUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const customers = useAppSelector(state => state.absaugdtmicrosrvcgateway.customer.entities);
  const dTransactionSummaryEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.dTransactionSummary.entity);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.dTransactionSummary.loading);
  const updating = useAppSelector(state => state.absaugdtmicrosrvcgateway.dTransactionSummary.updating);
  const updateSuccess = useAppSelector(state => state.absaugdtmicrosrvcgateway.dTransactionSummary.updateSuccess);
  const tranStatusValues = Object.keys(TranStatus);
  const errorCodesValues = Object.keys(ErrorCodes);
  const errorMessageValues = Object.keys(ErrorMessage);

  const handleClose = () => {
    navigate('/d-transaction-summary');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getCustomers({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    values.transactionDate = convertDateTimeToServer(values.transactionDate);
    values.createdAt = convertDateTimeToServer(values.createdAt);
    values.updatedAt = convertDateTimeToServer(values.updatedAt);

    const entity = {
      ...dTransactionSummaryEntity,
      ...values,
      customer: customers.find(it => it.id.toString() === values.customer.toString()),
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
          transactionDate: displayDefaultDateTime(),
          createdAt: displayDefaultDateTime(),
          updatedAt: displayDefaultDateTime(),
        }
      : {
          transactionStatus: 'PENDING',
          errorCode: 'SUCCESS',
          errorMessage: 'TRANSACTIONSUCCESS',
          ...dTransactionSummaryEntity,
          transactionDate: convertDateTimeFromServer(dTransactionSummaryEntity.transactionDate),
          createdAt: convertDateTimeFromServer(dTransactionSummaryEntity.createdAt),
          updatedAt: convertDateTimeFromServer(dTransactionSummaryEntity.updatedAt),
          customer: dTransactionSummaryEntity?.customer?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.home.createOrEditLabel" data-cy="DTransactionSummaryCreateUpdateHeading">
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionSummary.home.createOrEditLabel">
              Create or edit a DTransactionSummary
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
                  id="d-transaction-summary-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionSummary.absaTranRef')}
                id="d-transaction-summary-absaTranRef"
                name="absaTranRef"
                data-cy="absaTranRef"
                type="text"
                validate={{}}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionSummary.billerId')}
                id="d-transaction-summary-billerId"
                name="billerId"
                data-cy="billerId"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionSummary.paymentItemCode')}
                id="d-transaction-summary-paymentItemCode"
                name="paymentItemCode"
                data-cy="paymentItemCode"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionSummary.dtDTransactionId')}
                id="d-transaction-summary-dtDTransactionId"
                name="dtDTransactionId"
                data-cy="dtDTransactionId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionSummary.transactionReferenceNumber')}
                id="d-transaction-summary-transactionReferenceNumber"
                name="transactionReferenceNumber"
                data-cy="transactionReferenceNumber"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionSummary.recordId')}
                id="d-transaction-summary-recordId"
                name="recordId"
                data-cy="recordId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionSummary.transactionId')}
                id="d-transaction-summary-transactionId"
                name="transactionId"
                data-cy="transactionId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionSummary.transactionType')}
                id="d-transaction-summary-transactionType"
                name="transactionType"
                data-cy="transactionType"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionSummary.transactionAmount')}
                id="d-transaction-summary-transactionAmount"
                name="transactionAmount"
                data-cy="transactionAmount"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionSummary.transactionDate')}
                id="d-transaction-summary-transactionDate"
                name="transactionDate"
                data-cy="transactionDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionSummary.transactionStatus')}
                id="d-transaction-summary-transactionStatus"
                name="transactionStatus"
                data-cy="transactionStatus"
                type="select"
              >
                {tranStatusValues.map(tranStatus => (
                  <option value={tranStatus} key={tranStatus}>
                    {translate('absaUgdtMicrosrvcGatewayApp.TranStatus.' + tranStatus)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionSummary.freeField1')}
                id="d-transaction-summary-freeField1"
                name="freeField1"
                data-cy="freeField1"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionSummary.freeField2')}
                id="d-transaction-summary-freeField2"
                name="freeField2"
                data-cy="freeField2"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionSummary.freeField3')}
                id="d-transaction-summary-freeField3"
                name="freeField3"
                data-cy="freeField3"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionSummary.freeField4')}
                id="d-transaction-summary-freeField4"
                name="freeField4"
                data-cy="freeField4"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionSummary.freeField5')}
                id="d-transaction-summary-freeField5"
                name="freeField5"
                data-cy="freeField5"
                type="textarea"
              />
              <ValidatedBlobField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionSummary.freeField6')}
                id="d-transaction-summary-freeField6"
                name="freeField6"
                data-cy="freeField6"
                openActionLabel={translate('entity.action.open')}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionSummary.createdAt')}
                id="d-transaction-summary-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionSummary.createdBy')}
                id="d-transaction-summary-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionSummary.updatedAt')}
                id="d-transaction-summary-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionSummary.updatedBy')}
                id="d-transaction-summary-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionSummary.errorCode')}
                id="d-transaction-summary-errorCode"
                name="errorCode"
                data-cy="errorCode"
                type="select"
              >
                {errorCodesValues.map(errorCodes => (
                  <option value={errorCodes} key={errorCodes}>
                    {translate('absaUgdtMicrosrvcGatewayApp.ErrorCodes.' + errorCodes)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionSummary.errorMessage')}
                id="d-transaction-summary-errorMessage"
                name="errorMessage"
                data-cy="errorMessage"
                type="select"
              >
                {errorMessageValues.map(errorMessage => (
                  <option value={errorMessage} key={errorMessage}>
                    {translate('absaUgdtMicrosrvcGatewayApp.ErrorMessage.' + errorMessage)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                id="d-transaction-summary-customer"
                name="customer"
                data-cy="customer"
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionSummary.customer')}
                type="select"
              >
                <option value="" key="0" />
                {customers
                  ? customers.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/d-transaction-summary" replace color="info">
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

export default DTransactionSummaryUpdate;
