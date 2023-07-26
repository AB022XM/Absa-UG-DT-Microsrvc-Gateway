import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm, ValidatedBlobField } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IDTransaction } from 'app/shared/model/d-transaction.model';
import { getEntities as getDTransactions } from 'app/entities/d-transaction/d-transaction.reducer';
import { IAmolDTransactions } from 'app/shared/model/amol-d-transactions.model';
import { TranStatus } from 'app/shared/model/enumerations/tran-status.model';
import { Currency } from 'app/shared/model/enumerations/currency.model';
import { Channel } from 'app/shared/model/enumerations/channel.model';
import { getEntity, updateEntity, createEntity, reset } from './amol-d-transactions.reducer';

export const AmolDTransactionsUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const dTransactions = useAppSelector(state => state.absaugdtmicrosrvcgateway.dTransaction.entities);
  const amolDTransactionsEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.amolDTransactions.entity);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.amolDTransactions.loading);
  const updating = useAppSelector(state => state.absaugdtmicrosrvcgateway.amolDTransactions.updating);
  const updateSuccess = useAppSelector(state => state.absaugdtmicrosrvcgateway.amolDTransactions.updateSuccess);
  const tranStatusValues = Object.keys(TranStatus);
  const currencyValues = Object.keys(Currency);
  const channelValues = Object.keys(Channel);

  const handleClose = () => {
    navigate('/amol-d-transactions');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getDTransactions({}));
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
    values.tranFreeField24 = convertDateTimeToServer(values.tranFreeField24);
    values.responseDateTime = convertDateTimeToServer(values.responseDateTime);
    values.createdAt = convertDateTimeToServer(values.createdAt);
    values.updatedAt = convertDateTimeToServer(values.updatedAt);

    const entity = {
      ...amolDTransactionsEntity,
      ...values,
      dTransaction: dTransactions.find(it => it.id.toString() === values.dTransaction.toString()),
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
          tranFreeField24: displayDefaultDateTime(),
          responseDateTime: displayDefaultDateTime(),
          createdAt: displayDefaultDateTime(),
          updatedAt: displayDefaultDateTime(),
        }
      : {
          status: 'PENDING',
          debitCurrency: 'UGX',
          tranStatus: 'PENDING',
          paymentChannelCode: 'ATM',
          ...amolDTransactionsEntity,
          debitDate: convertDateTimeFromServer(amolDTransactionsEntity.debitDate),
          creditDate: convertDateTimeFromServer(amolDTransactionsEntity.creditDate),
          settlementDate: convertDateTimeFromServer(amolDTransactionsEntity.settlementDate),
          timestamp: convertDateTimeFromServer(amolDTransactionsEntity.timestamp),
          tranFreeField24: convertDateTimeFromServer(amolDTransactionsEntity.tranFreeField24),
          responseDateTime: convertDateTimeFromServer(amolDTransactionsEntity.responseDateTime),
          createdAt: convertDateTimeFromServer(amolDTransactionsEntity.createdAt),
          updatedAt: convertDateTimeFromServer(amolDTransactionsEntity.updatedAt),
          dTransaction: amolDTransactionsEntity?.dTransaction?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="absaUgdtMicrosrvcGatewayApp.amolDTransactions.home.createOrEditLabel" data-cy="AmolDTransactionsCreateUpdateHeading">
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.amolDTransactions.home.createOrEditLabel">
              Create or edit a AmolDTransactions
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
                  id="amol-d-transactions-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.absaTranRef')}
                id="amol-d-transactions-absaTranRef"
                name="absaTranRef"
                data-cy="absaTranRef"
                type="text"
                validate={{}}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.billerId')}
                id="amol-d-transactions-billerId"
                name="billerId"
                data-cy="billerId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.dtDTransactionId')}
                id="amol-d-transactions-dtDTransactionId"
                name="dtDTransactionId"
                data-cy="dtDTransactionId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.amolDTransactionId')}
                id="amol-d-transactions-amolDTransactionId"
                name="amolDTransactionId"
                data-cy="amolDTransactionId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.transactionReferenceNumber')}
                id="amol-d-transactions-transactionReferenceNumber"
                name="transactionReferenceNumber"
                data-cy="transactionReferenceNumber"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.token')}
                id="amol-d-transactions-token"
                name="token"
                data-cy="token"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.transferId')}
                id="amol-d-transactions-transferId"
                name="transferId"
                data-cy="transferId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.externalTxnId')}
                id="amol-d-transactions-externalTxnId"
                name="externalTxnId"
                data-cy="externalTxnId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.customerReference')}
                id="amol-d-transactions-customerReference"
                name="customerReference"
                data-cy="customerReference"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.debitAccountNumber')}
                id="amol-d-transactions-debitAccountNumber"
                name="debitAccountNumber"
                data-cy="debitAccountNumber"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.creditAccountNumber')}
                id="amol-d-transactions-creditAccountNumber"
                name="creditAccountNumber"
                data-cy="creditAccountNumber"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.debitAmount')}
                id="amol-d-transactions-debitAmount"
                name="debitAmount"
                data-cy="debitAmount"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.creditAmount')}
                id="amol-d-transactions-creditAmount"
                name="creditAmount"
                data-cy="creditAmount"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.transactionAmount')}
                id="amol-d-transactions-transactionAmount"
                name="transactionAmount"
                data-cy="transactionAmount"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.debitDate')}
                id="amol-d-transactions-debitDate"
                name="debitDate"
                data-cy="debitDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.creditDate')}
                id="amol-d-transactions-creditDate"
                name="creditDate"
                data-cy="creditDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.status')}
                id="amol-d-transactions-status"
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
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.settlementDate')}
                id="amol-d-transactions-settlementDate"
                name="settlementDate"
                data-cy="settlementDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.debitCurrency')}
                id="amol-d-transactions-debitCurrency"
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
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.timestamp')}
                id="amol-d-transactions-timestamp"
                name="timestamp"
                data-cy="timestamp"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.phoneNumber')}
                id="amol-d-transactions-phoneNumber"
                name="phoneNumber"
                data-cy="phoneNumber"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.email')}
                id="amol-d-transactions-email"
                name="email"
                data-cy="email"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.payerName')}
                id="amol-d-transactions-payerName"
                name="payerName"
                data-cy="payerName"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.payerAddress')}
                id="amol-d-transactions-payerAddress"
                name="payerAddress"
                data-cy="payerAddress"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.payerEmail')}
                id="amol-d-transactions-payerEmail"
                name="payerEmail"
                data-cy="payerEmail"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.payerPhoneNumber')}
                id="amol-d-transactions-payerPhoneNumber"
                name="payerPhoneNumber"
                data-cy="payerPhoneNumber"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.payerNarration')}
                id="amol-d-transactions-payerNarration"
                name="payerNarration"
                data-cy="payerNarration"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.payerBranchId')}
                id="amol-d-transactions-payerBranchId"
                name="payerBranchId"
                data-cy="payerBranchId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.beneficiaryName')}
                id="amol-d-transactions-beneficiaryName"
                name="beneficiaryName"
                data-cy="beneficiaryName"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.beneficiaryAccount')}
                id="amol-d-transactions-beneficiaryAccount"
                name="beneficiaryAccount"
                data-cy="beneficiaryAccount"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.beneficiaryBranchId')}
                id="amol-d-transactions-beneficiaryBranchId"
                name="beneficiaryBranchId"
                data-cy="beneficiaryBranchId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.beneficiaryPhoneNumber')}
                id="amol-d-transactions-beneficiaryPhoneNumber"
                name="beneficiaryPhoneNumber"
                data-cy="beneficiaryPhoneNumber"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranStatus')}
                id="amol-d-transactions-tranStatus"
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
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.narration1')}
                id="amol-d-transactions-narration1"
                name="narration1"
                data-cy="narration1"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.narration2')}
                id="amol-d-transactions-narration2"
                name="narration2"
                data-cy="narration2"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.narration3')}
                id="amol-d-transactions-narration3"
                name="narration3"
                data-cy="narration3"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.taxAmount')}
                id="amol-d-transactions-taxAmount"
                name="taxAmount"
                data-cy="taxAmount"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.taxGLAccount')}
                id="amol-d-transactions-taxGLAccount"
                name="taxGLAccount"
                data-cy="taxGLAccount"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.taxNarration')}
                id="amol-d-transactions-taxNarration"
                name="taxNarration"
                data-cy="taxNarration"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.internalErrorCode')}
                id="amol-d-transactions-internalErrorCode"
                name="internalErrorCode"
                data-cy="internalErrorCode"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.externalErrorCode')}
                id="amol-d-transactions-externalErrorCode"
                name="externalErrorCode"
                data-cy="externalErrorCode"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.payeeBranchId')}
                id="amol-d-transactions-payeeBranchId"
                name="payeeBranchId"
                data-cy="payeeBranchId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.payeeProductInstanceReference')}
                id="amol-d-transactions-payeeProductInstanceReference"
                name="payeeProductInstanceReference"
                data-cy="payeeProductInstanceReference"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.payeeProductCode')}
                id="amol-d-transactions-payeeProductCode"
                name="payeeProductCode"
                data-cy="payeeProductCode"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.payeeProductName')}
                id="amol-d-transactions-payeeProductName"
                name="payeeProductName"
                data-cy="payeeProductName"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.payeeProductDescription')}
                id="amol-d-transactions-payeeProductDescription"
                name="payeeProductDescription"
                data-cy="payeeProductDescription"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.payeeProductUnitOfMeasure')}
                id="amol-d-transactions-payeeProductUnitOfMeasure"
                name="payeeProductUnitOfMeasure"
                data-cy="payeeProductUnitOfMeasure"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.payeeProductQuantity')}
                id="amol-d-transactions-payeeProductQuantity"
                name="payeeProductQuantity"
                data-cy="payeeProductQuantity"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.subCategoryCode')}
                id="amol-d-transactions-subCategoryCode"
                name="subCategoryCode"
                data-cy="subCategoryCode"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.payerBankCode')}
                id="amol-d-transactions-payerBankCode"
                name="payerBankCode"
                data-cy="payerBankCode"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.payerBankName')}
                id="amol-d-transactions-payerBankName"
                name="payerBankName"
                data-cy="payerBankName"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.payerBankAddress')}
                id="amol-d-transactions-payerBankAddress"
                name="payerBankAddress"
                data-cy="payerBankAddress"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.payerBankCity')}
                id="amol-d-transactions-payerBankCity"
                name="payerBankCity"
                data-cy="payerBankCity"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.payerBankState')}
                id="amol-d-transactions-payerBankState"
                name="payerBankState"
                data-cy="payerBankState"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.payerBankCountry')}
                id="amol-d-transactions-payerBankCountry"
                name="payerBankCountry"
                data-cy="payerBankCountry"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.payerBankPostalCode')}
                id="amol-d-transactions-payerBankPostalCode"
                name="payerBankPostalCode"
                data-cy="payerBankPostalCode"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.checkerId')}
                id="amol-d-transactions-checkerId"
                name="checkerId"
                data-cy="checkerId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.remittanceInformation')}
                id="amol-d-transactions-remittanceInformation"
                name="remittanceInformation"
                data-cy="remittanceInformation"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.paymentChannelCode')}
                id="amol-d-transactions-paymentChannelCode"
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
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.feeAmount')}
                id="amol-d-transactions-feeAmount"
                name="feeAmount"
                data-cy="feeAmount"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.feeGLAccount')}
                id="amol-d-transactions-feeGLAccount"
                name="feeGLAccount"
                data-cy="feeGLAccount"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.feeNarration')}
                id="amol-d-transactions-feeNarration"
                name="feeNarration"
                data-cy="feeNarration"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField1')}
                id="amol-d-transactions-tranFreeField1"
                name="tranFreeField1"
                data-cy="tranFreeField1"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField2')}
                id="amol-d-transactions-tranFreeField2"
                name="tranFreeField2"
                data-cy="tranFreeField2"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField3')}
                id="amol-d-transactions-tranFreeField3"
                name="tranFreeField3"
                data-cy="tranFreeField3"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField4')}
                id="amol-d-transactions-tranFreeField4"
                name="tranFreeField4"
                data-cy="tranFreeField4"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField5')}
                id="amol-d-transactions-tranFreeField5"
                name="tranFreeField5"
                data-cy="tranFreeField5"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField6')}
                id="amol-d-transactions-tranFreeField6"
                name="tranFreeField6"
                data-cy="tranFreeField6"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField7')}
                id="amol-d-transactions-tranFreeField7"
                name="tranFreeField7"
                data-cy="tranFreeField7"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField8')}
                id="amol-d-transactions-tranFreeField8"
                name="tranFreeField8"
                data-cy="tranFreeField8"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField9')}
                id="amol-d-transactions-tranFreeField9"
                name="tranFreeField9"
                data-cy="tranFreeField9"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField10')}
                id="amol-d-transactions-tranFreeField10"
                name="tranFreeField10"
                data-cy="tranFreeField10"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField11')}
                id="amol-d-transactions-tranFreeField11"
                name="tranFreeField11"
                data-cy="tranFreeField11"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField12')}
                id="amol-d-transactions-tranFreeField12"
                name="tranFreeField12"
                data-cy="tranFreeField12"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField13')}
                id="amol-d-transactions-tranFreeField13"
                name="tranFreeField13"
                data-cy="tranFreeField13"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField14')}
                id="amol-d-transactions-tranFreeField14"
                name="tranFreeField14"
                data-cy="tranFreeField14"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField15')}
                id="amol-d-transactions-tranFreeField15"
                name="tranFreeField15"
                data-cy="tranFreeField15"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField16')}
                id="amol-d-transactions-tranFreeField16"
                name="tranFreeField16"
                data-cy="tranFreeField16"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField17')}
                id="amol-d-transactions-tranFreeField17"
                name="tranFreeField17"
                data-cy="tranFreeField17"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField18')}
                id="amol-d-transactions-tranFreeField18"
                name="tranFreeField18"
                data-cy="tranFreeField18"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField19')}
                id="amol-d-transactions-tranFreeField19"
                name="tranFreeField19"
                data-cy="tranFreeField19"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField20')}
                id="amol-d-transactions-tranFreeField20"
                name="tranFreeField20"
                data-cy="tranFreeField20"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField21')}
                id="amol-d-transactions-tranFreeField21"
                name="tranFreeField21"
                data-cy="tranFreeField21"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField22')}
                id="amol-d-transactions-tranFreeField22"
                name="tranFreeField22"
                data-cy="tranFreeField22"
                type="textarea"
              />
              <ValidatedBlobField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField23')}
                id="amol-d-transactions-tranFreeField23"
                name="tranFreeField23"
                data-cy="tranFreeField23"
                openActionLabel={translate('entity.action.open')}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.tranFreeField24')}
                id="amol-d-transactions-tranFreeField24"
                name="tranFreeField24"
                data-cy="tranFreeField24"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.responseCode')}
                id="amol-d-transactions-responseCode"
                name="responseCode"
                data-cy="responseCode"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.responseMessage')}
                id="amol-d-transactions-responseMessage"
                name="responseMessage"
                data-cy="responseMessage"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.responseDetails')}
                id="amol-d-transactions-responseDetails"
                name="responseDetails"
                data-cy="responseDetails"
                type="textarea"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.transferReferenceId')}
                id="amol-d-transactions-transferReferenceId"
                name="transferReferenceId"
                data-cy="transferReferenceId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.transferStatus')}
                id="amol-d-transactions-transferStatus"
                name="transferStatus"
                data-cy="transferStatus"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.responseDateTime')}
                id="amol-d-transactions-responseDateTime"
                name="responseDateTime"
                data-cy="responseDateTime"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.createdAt')}
                id="amol-d-transactions-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.createdBy')}
                id="amol-d-transactions-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.updatedAt')}
                id="amol-d-transactions-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.updatedBy')}
                id="amol-d-transactions-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <ValidatedField
                id="amol-d-transactions-dTransaction"
                name="dTransaction"
                data-cy="dTransaction"
                label={translate('absaUgdtMicrosrvcGatewayApp.amolDTransactions.dTransaction')}
                type="select"
              >
                <option value="" key="0" />
                {dTransactions
                  ? dTransactions.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/amol-d-transactions" replace color="info">
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

export default AmolDTransactionsUpdate;
