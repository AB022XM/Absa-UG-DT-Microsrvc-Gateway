import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { ICustomer } from 'app/shared/model/customer.model';
import { getEntities as getCustomers } from 'app/entities/customer/customer.reducer';
import { IDTransactionHistory } from 'app/shared/model/d-transaction-history.model';
import { Channel } from 'app/shared/model/enumerations/channel.model';
import { TranStatus } from 'app/shared/model/enumerations/tran-status.model';
import { Currency } from 'app/shared/model/enumerations/currency.model';
import { ModeOfPayment } from 'app/shared/model/enumerations/mode-of-payment.model';
import { getEntity, updateEntity, createEntity, reset } from './d-transaction-history.reducer';

export const DTransactionHistoryUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const customers = useAppSelector(state => state.absaugdtmicrosrvcgateway.customer.entities);
  const dTransactionHistoryEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.dTransactionHistory.entity);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.dTransactionHistory.loading);
  const updating = useAppSelector(state => state.absaugdtmicrosrvcgateway.dTransactionHistory.updating);
  const updateSuccess = useAppSelector(state => state.absaugdtmicrosrvcgateway.dTransactionHistory.updateSuccess);
  const channelValues = Object.keys(Channel);
  const tranStatusValues = Object.keys(TranStatus);
  const currencyValues = Object.keys(Currency);
  const modeOfPaymentValues = Object.keys(ModeOfPayment);

  const handleClose = () => {
    navigate('/d-transaction-history');
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
    values.debitDate = convertDateTimeToServer(values.debitDate);
    values.creditDate = convertDateTimeToServer(values.creditDate);
    values.settlementDate = convertDateTimeToServer(values.settlementDate);
    values.timestamp = convertDateTimeToServer(values.timestamp);
    values.createdAt = convertDateTimeToServer(values.createdAt);
    values.updatedAt = convertDateTimeToServer(values.updatedAt);

    const entity = {
      ...dTransactionHistoryEntity,
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
          debitDate: displayDefaultDateTime(),
          creditDate: displayDefaultDateTime(),
          settlementDate: displayDefaultDateTime(),
          timestamp: displayDefaultDateTime(),
          createdAt: displayDefaultDateTime(),
          updatedAt: displayDefaultDateTime(),
        }
      : {
          paymentChannelCode: 'ATM',
          status: 'PENDING',
          debitCurrency: 'UGX',
          tranStatus: 'PENDING',
          modeOfPayment: 'CASH',
          ...dTransactionHistoryEntity,
          debitDate: convertDateTimeFromServer(dTransactionHistoryEntity.debitDate),
          creditDate: convertDateTimeFromServer(dTransactionHistoryEntity.creditDate),
          settlementDate: convertDateTimeFromServer(dTransactionHistoryEntity.settlementDate),
          timestamp: convertDateTimeFromServer(dTransactionHistoryEntity.timestamp),
          createdAt: convertDateTimeFromServer(dTransactionHistoryEntity.createdAt),
          updatedAt: convertDateTimeFromServer(dTransactionHistoryEntity.updatedAt),
          customer: dTransactionHistoryEntity?.customer?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.home.createOrEditLabel" data-cy="DTransactionHistoryCreateUpdateHeading">
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionHistory.home.createOrEditLabel">
              Create or edit a DTransactionHistory
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
                  id="d-transaction-history-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.recordId')}
                id="d-transaction-history-recordId"
                name="recordId"
                data-cy="recordId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.transferId')}
                id="d-transaction-history-transferId"
                name="transferId"
                data-cy="transferId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.productCode')}
                id="d-transaction-history-productCode"
                name="productCode"
                data-cy="productCode"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.paymentChannelCode')}
                id="d-transaction-history-paymentChannelCode"
                name="paymentChannelCode"
                data-cy="paymentChannelCode"
                type="select"
              >
                {channelValues.map(channel => (
                  <option value={channel} key={channel}>
                    {translate('absaUgdtMicrosrvcGatewayApp.Channel.' + channel)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.debitAccountNumber')}
                id="d-transaction-history-debitAccountNumber"
                name="debitAccountNumber"
                data-cy="debitAccountNumber"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.creditAccountNumber')}
                id="d-transaction-history-creditAccountNumber"
                name="creditAccountNumber"
                data-cy="creditAccountNumber"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.debitAmount')}
                id="d-transaction-history-debitAmount"
                name="debitAmount"
                data-cy="debitAmount"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.creditAmount')}
                id="d-transaction-history-creditAmount"
                name="creditAmount"
                data-cy="creditAmount"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.transactionAmount')}
                id="d-transaction-history-transactionAmount"
                name="transactionAmount"
                data-cy="transactionAmount"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.debitDate')}
                id="d-transaction-history-debitDate"
                name="debitDate"
                data-cy="debitDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.creditDate')}
                id="d-transaction-history-creditDate"
                name="creditDate"
                data-cy="creditDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.status')}
                id="d-transaction-history-status"
                name="status"
                data-cy="status"
                type="select"
              >
                {tranStatusValues.map(tranStatus => (
                  <option value={tranStatus} key={tranStatus}>
                    {translate('absaUgdtMicrosrvcGatewayApp.TranStatus.' + tranStatus)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.settlementDate')}
                id="d-transaction-history-settlementDate"
                name="settlementDate"
                data-cy="settlementDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.debitCurrency')}
                id="d-transaction-history-debitCurrency"
                name="debitCurrency"
                data-cy="debitCurrency"
                type="select"
              >
                {currencyValues.map(currency => (
                  <option value={currency} key={currency}>
                    {translate('absaUgdtMicrosrvcGatewayApp.Currency.' + currency)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.timestamp')}
                id="d-transaction-history-timestamp"
                name="timestamp"
                data-cy="timestamp"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.phoneNumber')}
                id="d-transaction-history-phoneNumber"
                name="phoneNumber"
                data-cy="phoneNumber"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.email')}
                id="d-transaction-history-email"
                name="email"
                data-cy="email"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.payerName')}
                id="d-transaction-history-payerName"
                name="payerName"
                data-cy="payerName"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.payerAddress')}
                id="d-transaction-history-payerAddress"
                name="payerAddress"
                data-cy="payerAddress"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.payerEmail')}
                id="d-transaction-history-payerEmail"
                name="payerEmail"
                data-cy="payerEmail"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.payerPhoneNumber')}
                id="d-transaction-history-payerPhoneNumber"
                name="payerPhoneNumber"
                data-cy="payerPhoneNumber"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.payerNarration')}
                id="d-transaction-history-payerNarration"
                name="payerNarration"
                data-cy="payerNarration"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.payerBranchId')}
                id="d-transaction-history-payerBranchId"
                name="payerBranchId"
                data-cy="payerBranchId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.beneficiaryName')}
                id="d-transaction-history-beneficiaryName"
                name="beneficiaryName"
                data-cy="beneficiaryName"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.beneficiaryAccount')}
                id="d-transaction-history-beneficiaryAccount"
                name="beneficiaryAccount"
                data-cy="beneficiaryAccount"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.beneficiaryBranchId')}
                id="d-transaction-history-beneficiaryBranchId"
                name="beneficiaryBranchId"
                data-cy="beneficiaryBranchId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.beneficiaryPhoneNumber')}
                id="d-transaction-history-beneficiaryPhoneNumber"
                name="beneficiaryPhoneNumber"
                data-cy="beneficiaryPhoneNumber"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.tranStatus')}
                id="d-transaction-history-tranStatus"
                name="tranStatus"
                data-cy="tranStatus"
                type="select"
              >
                {tranStatusValues.map(tranStatus => (
                  <option value={tranStatus} key={tranStatus}>
                    {translate('absaUgdtMicrosrvcGatewayApp.TranStatus.' + tranStatus)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.narration1')}
                id="d-transaction-history-narration1"
                name="narration1"
                data-cy="narration1"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.narration2')}
                id="d-transaction-history-narration2"
                name="narration2"
                data-cy="narration2"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.narration3')}
                id="d-transaction-history-narration3"
                name="narration3"
                data-cy="narration3"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.modeOfPayment')}
                id="d-transaction-history-modeOfPayment"
                name="modeOfPayment"
                data-cy="modeOfPayment"
                type="select"
              >
                {modeOfPaymentValues.map(modeOfPayment => (
                  <option value={modeOfPayment} key={modeOfPayment}>
                    {translate('absaUgdtMicrosrvcGatewayApp.ModeOfPayment.' + modeOfPayment)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.retries')}
                id="d-transaction-history-retries"
                name="retries"
                data-cy="retries"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.posted')}
                id="d-transaction-history-posted"
                name="posted"
                data-cy="posted"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.apiId')}
                id="d-transaction-history-apiId"
                name="apiId"
                data-cy="apiId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.apiVersion')}
                id="d-transaction-history-apiVersion"
                name="apiVersion"
                data-cy="apiVersion"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.postingApi')}
                id="d-transaction-history-postingApi"
                name="postingApi"
                data-cy="postingApi"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.isPosted')}
                id="d-transaction-history-isPosted"
                name="isPosted"
                data-cy="isPosted"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.postedBy')}
                id="d-transaction-history-postedBy"
                name="postedBy"
                data-cy="postedBy"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.postedDate')}
                id="d-transaction-history-postedDate"
                name="postedDate"
                data-cy="postedDate"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.internalErrorCode')}
                id="d-transaction-history-internalErrorCode"
                name="internalErrorCode"
                data-cy="internalErrorCode"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.externalErrorCode')}
                id="d-transaction-history-externalErrorCode"
                name="externalErrorCode"
                data-cy="externalErrorCode"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.tranFreeField1')}
                id="d-transaction-history-tranFreeField1"
                name="tranFreeField1"
                data-cy="tranFreeField1"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.tranFreeField3')}
                id="d-transaction-history-tranFreeField3"
                name="tranFreeField3"
                data-cy="tranFreeField3"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.tranFreeField4')}
                id="d-transaction-history-tranFreeField4"
                name="tranFreeField4"
                data-cy="tranFreeField4"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.tranFreeField5')}
                id="d-transaction-history-tranFreeField5"
                name="tranFreeField5"
                data-cy="tranFreeField5"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.tranFreeField6')}
                id="d-transaction-history-tranFreeField6"
                name="tranFreeField6"
                data-cy="tranFreeField6"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.tranFreeField7')}
                id="d-transaction-history-tranFreeField7"
                name="tranFreeField7"
                data-cy="tranFreeField7"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.tranFreeField8')}
                id="d-transaction-history-tranFreeField8"
                name="tranFreeField8"
                data-cy="tranFreeField8"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.tranFreeField9')}
                id="d-transaction-history-tranFreeField9"
                name="tranFreeField9"
                data-cy="tranFreeField9"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.tranFreeField10')}
                id="d-transaction-history-tranFreeField10"
                name="tranFreeField10"
                data-cy="tranFreeField10"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.tranFreeField11')}
                id="d-transaction-history-tranFreeField11"
                name="tranFreeField11"
                data-cy="tranFreeField11"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.tranFreeField12')}
                id="d-transaction-history-tranFreeField12"
                name="tranFreeField12"
                data-cy="tranFreeField12"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.createdAt')}
                id="d-transaction-history-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.createdBy')}
                id="d-transaction-history-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.updatedAt')}
                id="d-transaction-history-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.updatedBy')}
                id="d-transaction-history-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <ValidatedField
                id="d-transaction-history-customer"
                name="customer"
                data-cy="customer"
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionHistory.customer')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/d-transaction-history" replace color="info">
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

export default DTransactionHistoryUpdate;
