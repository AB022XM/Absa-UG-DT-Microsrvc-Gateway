import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm, ValidatedBlobField } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IDTransactionHistory } from 'app/shared/model/d-transaction-history.model';
import { getEntities as getDTransactionHistories } from 'app/entities/d-transaction-history/d-transaction-history.reducer';
import { IDTransactionSummary } from 'app/shared/model/d-transaction-summary.model';
import { getEntities as getDTransactionSummaries } from 'app/entities/d-transaction-summary/d-transaction-summary.reducer';
import { IDTransactionDetails } from 'app/shared/model/d-transaction-details.model';
import { getEntities as getDTransactionDetails } from 'app/entities/d-transaction-details/d-transaction-details.reducer';
import { ICustomer } from 'app/shared/model/customer.model';
import { getEntities as getCustomers } from 'app/entities/customer/customer.reducer';
import { IDTransaction } from 'app/shared/model/d-transaction.model';
import { Channel } from 'app/shared/model/enumerations/channel.model';
import { ErrorCodes } from 'app/shared/model/enumerations/error-codes.model';
import { ErrorMessage } from 'app/shared/model/enumerations/error-message.model';
import { TranStatus } from 'app/shared/model/enumerations/tran-status.model';
import { getEntity, updateEntity, createEntity, reset } from './d-transaction.reducer';

export const DTransactionUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const dTransactionHistories = useAppSelector(state => state.absaugdtmicrosrvcgateway.dTransactionHistory.entities);
  const dTransactionSummaries = useAppSelector(state => state.absaugdtmicrosrvcgateway.dTransactionSummary.entities);
  const dTransactionDetails = useAppSelector(state => state.absaugdtmicrosrvcgateway.dTransactionDetails.entities);
  const customers = useAppSelector(state => state.absaugdtmicrosrvcgateway.customer.entities);
  const dTransactionEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.dTransaction.entity);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.dTransaction.loading);
  const updating = useAppSelector(state => state.absaugdtmicrosrvcgateway.dTransaction.updating);
  const updateSuccess = useAppSelector(state => state.absaugdtmicrosrvcgateway.dTransaction.updateSuccess);
  const channelValues = Object.keys(Channel);
  const errorCodesValues = Object.keys(ErrorCodes);
  const errorMessageValues = Object.keys(ErrorMessage);
  const tranStatusValues = Object.keys(TranStatus);

  const handleClose = () => {
    navigate('/d-transaction');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getDTransactionHistories({}));
    dispatch(getDTransactionSummaries({}));
    dispatch(getDTransactionDetails({}));
    dispatch(getCustomers({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    values.settlementDate = convertDateTimeToServer(values.settlementDate);
    values.transactionDate = convertDateTimeToServer(values.transactionDate);
    values.lastRetryDate = convertDateTimeToServer(values.lastRetryDate);
    values.firstRetryDate = convertDateTimeToServer(values.firstRetryDate);
    values.timestamp = convertDateTimeToServer(values.timestamp);
    values.tranFreeField16 = convertDateTimeToServer(values.tranFreeField16);
    values.tranFreeField20 = convertDateTimeToServer(values.tranFreeField20);
    values.tranFreeField21 = convertDateTimeToServer(values.tranFreeField21);
    values.tranFreeField23 = convertDateTimeToServer(values.tranFreeField23);
    values.createdAt = convertDateTimeToServer(values.createdAt);
    values.updatedAt = convertDateTimeToServer(values.updatedAt);

    const entity = {
      ...dTransactionEntity,
      ...values,
      transaction: dTransactionHistories.find(it => it.id.toString() === values.transaction.toString()),
      transaction: dTransactionSummaries.find(it => it.id.toString() === values.transaction.toString()),
      transaction: dTransactionDetails.find(it => it.id.toString() === values.transaction.toString()),
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
          settlementDate: displayDefaultDateTime(),
          transactionDate: displayDefaultDateTime(),
          lastRetryDate: displayDefaultDateTime(),
          firstRetryDate: displayDefaultDateTime(),
          timestamp: displayDefaultDateTime(),
          tranFreeField16: displayDefaultDateTime(),
          tranFreeField20: displayDefaultDateTime(),
          tranFreeField21: displayDefaultDateTime(),
          tranFreeField23: displayDefaultDateTime(),
          createdAt: displayDefaultDateTime(),
          updatedAt: displayDefaultDateTime(),
        }
      : {
          paymentChannelCode: 'ATM',
          retryResposeCode: 'SUCCESS',
          retryResponseMessage: 'TRANSACTIONSUCCESS',
          status: 'PENDING',
          tranStatus: 'PENDING',
          ...dTransactionEntity,
          settlementDate: convertDateTimeFromServer(dTransactionEntity.settlementDate),
          transactionDate: convertDateTimeFromServer(dTransactionEntity.transactionDate),
          lastRetryDate: convertDateTimeFromServer(dTransactionEntity.lastRetryDate),
          firstRetryDate: convertDateTimeFromServer(dTransactionEntity.firstRetryDate),
          timestamp: convertDateTimeFromServer(dTransactionEntity.timestamp),
          tranFreeField16: convertDateTimeFromServer(dTransactionEntity.tranFreeField16),
          tranFreeField20: convertDateTimeFromServer(dTransactionEntity.tranFreeField20),
          tranFreeField21: convertDateTimeFromServer(dTransactionEntity.tranFreeField21),
          tranFreeField23: convertDateTimeFromServer(dTransactionEntity.tranFreeField23),
          createdAt: convertDateTimeFromServer(dTransactionEntity.createdAt),
          updatedAt: convertDateTimeFromServer(dTransactionEntity.updatedAt),
          transaction: dTransactionEntity?.transaction?.id,
          transaction: dTransactionEntity?.transaction?.id,
          transaction: dTransactionEntity?.transaction?.id,
          customer: dTransactionEntity?.customer?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="absaUgdtMicrosrvcGatewayApp.dTransaction.home.createOrEditLabel" data-cy="DTransactionCreateUpdateHeading">
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransaction.home.createOrEditLabel">
              Create or edit a DTransaction
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
                  id="d-transaction-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.absaTranRef')}
                id="d-transaction-absaTranRef"
                name="absaTranRef"
                data-cy="absaTranRef"
                type="text"
                validate={{}}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.billerId')}
                id="d-transaction-billerId"
                name="billerId"
                data-cy="billerId"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.paymentItemCode')}
                id="d-transaction-paymentItemCode"
                name="paymentItemCode"
                data-cy="paymentItemCode"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.dtDTransactionId')}
                id="d-transaction-dtDTransactionId"
                name="dtDTransactionId"
                data-cy="dtDTransactionId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.amolDTransactionId')}
                id="d-transaction-amolDTransactionId"
                name="amolDTransactionId"
                data-cy="amolDTransactionId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.transactionReferenceNumber')}
                id="d-transaction-transactionReferenceNumber"
                name="transactionReferenceNumber"
                data-cy="transactionReferenceNumber"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.externalTranid')}
                id="d-transaction-externalTranid"
                name="externalTranid"
                data-cy="externalTranid"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.token')}
                id="d-transaction-token"
                name="token"
                data-cy="token"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.transferId')}
                id="d-transaction-transferId"
                name="transferId"
                data-cy="transferId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.productCode')}
                id="d-transaction-productCode"
                name="productCode"
                data-cy="productCode"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.paymentChannelCode')}
                id="d-transaction-paymentChannelCode"
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
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.accountNumber')}
                id="d-transaction-accountNumber"
                name="accountNumber"
                data-cy="accountNumber"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.amount')}
                id="d-transaction-amount"
                name="amount"
                data-cy="amount"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.debitAmount')}
                id="d-transaction-debitAmount"
                name="debitAmount"
                data-cy="debitAmount"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.creditAmount')}
                id="d-transaction-creditAmount"
                name="creditAmount"
                data-cy="creditAmount"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.settlementAmount')}
                id="d-transaction-settlementAmount"
                name="settlementAmount"
                data-cy="settlementAmount"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.settlementDate')}
                id="d-transaction-settlementDate"
                name="settlementDate"
                data-cy="settlementDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.transactionDate')}
                id="d-transaction-transactionDate"
                name="transactionDate"
                data-cy="transactionDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.isRetried')}
                id="d-transaction-isRetried"
                name="isRetried"
                data-cy="isRetried"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.lastRetryDate')}
                id="d-transaction-lastRetryDate"
                name="lastRetryDate"
                data-cy="lastRetryDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.firstRetryDate')}
                id="d-transaction-firstRetryDate"
                name="firstRetryDate"
                data-cy="firstRetryDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.retryResposeCode')}
                id="d-transaction-retryResposeCode"
                name="retryResposeCode"
                data-cy="retryResposeCode"
                type="select"
              >
                {errorCodesValues.map(errorCodes => (
                  <option value={errorCodes} key={errorCodes}>
                    {translate('absaUgdtMicrosrvcGatewayApp.ErrorCodes.' + errorCodes)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.retryResponseMessage')}
                id="d-transaction-retryResponseMessage"
                name="retryResponseMessage"
                data-cy="retryResponseMessage"
                type="select"
              >
                {errorMessageValues.map(errorMessage => (
                  <option value={errorMessage} key={errorMessage}>
                    {translate('absaUgdtMicrosrvcGatewayApp.ErrorMessage.' + errorMessage)}
                  </option>
                ))}
              </ValidatedField>
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.retryCount')}
                id="d-transaction-retryCount"
                name="retryCount"
                data-cy="retryCount"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.isRetriableFlg')}
                id="d-transaction-isRetriableFlg"
                name="isRetriableFlg"
                data-cy="isRetriableFlg"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.doNotRetryDTransaction')}
                id="d-transaction-doNotRetryDTransaction"
                name="doNotRetryDTransaction"
                data-cy="doNotRetryDTransaction"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.status')}
                id="d-transaction-status"
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
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.statusCode')}
                id="d-transaction-statusCode"
                name="statusCode"
                data-cy="statusCode"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.statusDetails')}
                id="d-transaction-statusDetails"
                name="statusDetails"
                data-cy="statusDetails"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.retries')}
                id="d-transaction-retries"
                name="retries"
                data-cy="retries"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.timestamp')}
                id="d-transaction-timestamp"
                name="timestamp"
                data-cy="timestamp"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.postedBy')}
                id="d-transaction-postedBy"
                name="postedBy"
                data-cy="postedBy"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.postedDate')}
                id="d-transaction-postedDate"
                name="postedDate"
                data-cy="postedDate"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.internalErrorCode')}
                id="d-transaction-internalErrorCode"
                name="internalErrorCode"
                data-cy="internalErrorCode"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.externalErrorCode')}
                id="d-transaction-externalErrorCode"
                name="externalErrorCode"
                data-cy="externalErrorCode"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.isCrossCurrency')}
                id="d-transaction-isCrossCurrency"
                name="isCrossCurrency"
                data-cy="isCrossCurrency"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.isCrossBank')}
                id="d-transaction-isCrossBank"
                name="isCrossBank"
                data-cy="isCrossBank"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.currency')}
                id="d-transaction-currency"
                name="currency"
                data-cy="currency"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.creditAccount')}
                id="d-transaction-creditAccount"
                name="creditAccount"
                data-cy="creditAccount"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.debitAccount')}
                id="d-transaction-debitAccount"
                name="debitAccount"
                data-cy="debitAccount"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.phoneNumber')}
                id="d-transaction-phoneNumber"
                name="phoneNumber"
                data-cy="phoneNumber"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.customerNumber')}
                id="d-transaction-customerNumber"
                name="customerNumber"
                data-cy="customerNumber"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.tranStatus')}
                id="d-transaction-tranStatus"
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
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.tranStatusDetails')}
                id="d-transaction-tranStatusDetails"
                name="tranStatusDetails"
                data-cy="tranStatusDetails"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField1')}
                id="d-transaction-tranFreeField1"
                name="tranFreeField1"
                data-cy="tranFreeField1"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField2')}
                id="d-transaction-tranFreeField2"
                name="tranFreeField2"
                data-cy="tranFreeField2"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField3')}
                id="d-transaction-tranFreeField3"
                name="tranFreeField3"
                data-cy="tranFreeField3"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField4')}
                id="d-transaction-tranFreeField4"
                name="tranFreeField4"
                data-cy="tranFreeField4"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField5')}
                id="d-transaction-tranFreeField5"
                name="tranFreeField5"
                data-cy="tranFreeField5"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField6')}
                id="d-transaction-tranFreeField6"
                name="tranFreeField6"
                data-cy="tranFreeField6"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField7')}
                id="d-transaction-tranFreeField7"
                name="tranFreeField7"
                data-cy="tranFreeField7"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField8')}
                id="d-transaction-tranFreeField8"
                name="tranFreeField8"
                data-cy="tranFreeField8"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField9')}
                id="d-transaction-tranFreeField9"
                name="tranFreeField9"
                data-cy="tranFreeField9"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField10')}
                id="d-transaction-tranFreeField10"
                name="tranFreeField10"
                data-cy="tranFreeField10"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField11')}
                id="d-transaction-tranFreeField11"
                name="tranFreeField11"
                data-cy="tranFreeField11"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField12')}
                id="d-transaction-tranFreeField12"
                name="tranFreeField12"
                data-cy="tranFreeField12"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField13')}
                id="d-transaction-tranFreeField13"
                name="tranFreeField13"
                data-cy="tranFreeField13"
                type="text"
              />
              <ValidatedBlobField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField14')}
                id="d-transaction-tranFreeField14"
                name="tranFreeField14"
                data-cy="tranFreeField14"
                openActionLabel={translate('entity.action.open')}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField15')}
                id="d-transaction-tranFreeField15"
                name="tranFreeField15"
                data-cy="tranFreeField15"
                type="textarea"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField16')}
                id="d-transaction-tranFreeField16"
                name="tranFreeField16"
                data-cy="tranFreeField16"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField17')}
                id="d-transaction-tranFreeField17"
                name="tranFreeField17"
                data-cy="tranFreeField17"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField18')}
                id="d-transaction-tranFreeField18"
                name="tranFreeField18"
                data-cy="tranFreeField18"
                type="textarea"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField19')}
                id="d-transaction-tranFreeField19"
                name="tranFreeField19"
                data-cy="tranFreeField19"
                type="textarea"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField20')}
                id="d-transaction-tranFreeField20"
                name="tranFreeField20"
                data-cy="tranFreeField20"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField21')}
                id="d-transaction-tranFreeField21"
                name="tranFreeField21"
                data-cy="tranFreeField21"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField22')}
                id="d-transaction-tranFreeField22"
                name="tranFreeField22"
                data-cy="tranFreeField22"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField23')}
                id="d-transaction-tranFreeField23"
                name="tranFreeField23"
                data-cy="tranFreeField23"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField24')}
                id="d-transaction-tranFreeField24"
                name="tranFreeField24"
                data-cy="tranFreeField24"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField25')}
                id="d-transaction-tranFreeField25"
                name="tranFreeField25"
                data-cy="tranFreeField25"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField26')}
                id="d-transaction-tranFreeField26"
                name="tranFreeField26"
                data-cy="tranFreeField26"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField27')}
                id="d-transaction-tranFreeField27"
                name="tranFreeField27"
                data-cy="tranFreeField27"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.tranFreeField28')}
                id="d-transaction-tranFreeField28"
                name="tranFreeField28"
                data-cy="tranFreeField28"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.createdAt')}
                id="d-transaction-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.createdBy')}
                id="d-transaction-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.updatedAt')}
                id="d-transaction-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.updatedBy')}
                id="d-transaction-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <ValidatedField
                id="d-transaction-transaction"
                name="transaction"
                data-cy="transaction"
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.transaction')}
                type="select"
              >
                <option value="" key="0" />
                {dTransactionHistories
                  ? dTransactionHistories.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="d-transaction-transaction"
                name="transaction"
                data-cy="transaction"
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.transaction')}
                type="select"
              >
                <option value="" key="0" />
                {dTransactionSummaries
                  ? dTransactionSummaries.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="d-transaction-transaction"
                name="transaction"
                data-cy="transaction"
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.transaction')}
                type="select"
              >
                <option value="" key="0" />
                {dTransactionDetails
                  ? dTransactionDetails.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <ValidatedField
                id="d-transaction-customer"
                name="customer"
                data-cy="customer"
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransaction.customer')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/d-transaction" replace color="info">
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

export default DTransactionUpdate;
