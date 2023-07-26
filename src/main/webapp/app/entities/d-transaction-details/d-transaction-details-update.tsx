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
import { IDTransactionDetails } from 'app/shared/model/d-transaction-details.model';
import { Channel } from 'app/shared/model/enumerations/channel.model';
import { TranStatus } from 'app/shared/model/enumerations/tran-status.model';
import { ModeOfPayment } from 'app/shared/model/enumerations/mode-of-payment.model';
import { getEntity, updateEntity, createEntity, reset } from './d-transaction-details.reducer';

export const DTransactionDetailsUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const customers = useAppSelector(state => state.absaugdtmicrosrvcgateway.customer.entities);
  const dTransactionDetailsEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.dTransactionDetails.entity);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.dTransactionDetails.loading);
  const updating = useAppSelector(state => state.absaugdtmicrosrvcgateway.dTransactionDetails.updating);
  const updateSuccess = useAppSelector(state => state.absaugdtmicrosrvcgateway.dTransactionDetails.updateSuccess);
  const channelValues = Object.keys(Channel);
  const tranStatusValues = Object.keys(TranStatus);
  const modeOfPaymentValues = Object.keys(ModeOfPayment);

  const handleClose = () => {
    navigate('/d-transaction-details');
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

    const entity = {
      ...dTransactionDetailsEntity,
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
        }
      : {
          paymentChannelCode: 'ATM',
          status: 'PENDING',
          tranStatus: 'PENDING',
          modeOfPayment: 'CASH',
          ...dTransactionDetailsEntity,
          debitDate: convertDateTimeFromServer(dTransactionDetailsEntity.debitDate),
          creditDate: convertDateTimeFromServer(dTransactionDetailsEntity.creditDate),
          settlementDate: convertDateTimeFromServer(dTransactionDetailsEntity.settlementDate),
          timestamp: convertDateTimeFromServer(dTransactionDetailsEntity.timestamp),
          customer: dTransactionDetailsEntity?.customer?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.home.createOrEditLabel" data-cy="DTransactionDetailsCreateUpdateHeading">
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionDetails.home.createOrEditLabel">
              Create or edit a DTransactionDetails
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
                  id="d-transaction-details-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.absaTranRef')}
                id="d-transaction-details-absaTranRef"
                name="absaTranRef"
                data-cy="absaTranRef"
                type="text"
                validate={{}}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.billerId')}
                id="d-transaction-details-billerId"
                name="billerId"
                data-cy="billerId"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.paymentItemCode')}
                id="d-transaction-details-paymentItemCode"
                name="paymentItemCode"
                data-cy="paymentItemCode"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.dtDTransactionId')}
                id="d-transaction-details-dtDTransactionId"
                name="dtDTransactionId"
                data-cy="dtDTransactionId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.amolDTransactionId')}
                id="d-transaction-details-amolDTransactionId"
                name="amolDTransactionId"
                data-cy="amolDTransactionId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.transactionReferenceNumber')}
                id="d-transaction-details-transactionReferenceNumber"
                name="transactionReferenceNumber"
                data-cy="transactionReferenceNumber"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.token')}
                id="d-transaction-details-token"
                name="token"
                data-cy="token"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.transferId')}
                id="d-transaction-details-transferId"
                name="transferId"
                data-cy="transferId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.productCode')}
                id="d-transaction-details-productCode"
                name="productCode"
                data-cy="productCode"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.paymentChannelCode')}
                id="d-transaction-details-paymentChannelCode"
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
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.debitAccountNumber')}
                id="d-transaction-details-debitAccountNumber"
                name="debitAccountNumber"
                data-cy="debitAccountNumber"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.creditAccountNumber')}
                id="d-transaction-details-creditAccountNumber"
                name="creditAccountNumber"
                data-cy="creditAccountNumber"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.debitAmount')}
                id="d-transaction-details-debitAmount"
                name="debitAmount"
                data-cy="debitAmount"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                  validate: v => isNumber(v) || translate('entity.validation.number'),
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.creditAmount')}
                id="d-transaction-details-creditAmount"
                name="creditAmount"
                data-cy="creditAmount"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.transactionAmount')}
                id="d-transaction-details-transactionAmount"
                name="transactionAmount"
                data-cy="transactionAmount"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.debitDate')}
                id="d-transaction-details-debitDate"
                name="debitDate"
                data-cy="debitDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.creditDate')}
                id="d-transaction-details-creditDate"
                name="creditDate"
                data-cy="creditDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.status')}
                id="d-transaction-details-status"
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
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.settlementDate')}
                id="d-transaction-details-settlementDate"
                name="settlementDate"
                data-cy="settlementDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.debitCurrency')}
                id="d-transaction-details-debitCurrency"
                name="debitCurrency"
                data-cy="debitCurrency"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.timestamp')}
                id="d-transaction-details-timestamp"
                name="timestamp"
                data-cy="timestamp"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.phoneNumber')}
                id="d-transaction-details-phoneNumber"
                name="phoneNumber"
                data-cy="phoneNumber"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.email')}
                id="d-transaction-details-email"
                name="email"
                data-cy="email"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.payerName')}
                id="d-transaction-details-payerName"
                name="payerName"
                data-cy="payerName"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.payerAddress')}
                id="d-transaction-details-payerAddress"
                name="payerAddress"
                data-cy="payerAddress"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.payerEmail')}
                id="d-transaction-details-payerEmail"
                name="payerEmail"
                data-cy="payerEmail"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.payerPhoneNumber')}
                id="d-transaction-details-payerPhoneNumber"
                name="payerPhoneNumber"
                data-cy="payerPhoneNumber"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.payerNarration')}
                id="d-transaction-details-payerNarration"
                name="payerNarration"
                data-cy="payerNarration"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.payerBranchId')}
                id="d-transaction-details-payerBranchId"
                name="payerBranchId"
                data-cy="payerBranchId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.beneficiaryName')}
                id="d-transaction-details-beneficiaryName"
                name="beneficiaryName"
                data-cy="beneficiaryName"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.beneficiaryAccount')}
                id="d-transaction-details-beneficiaryAccount"
                name="beneficiaryAccount"
                data-cy="beneficiaryAccount"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.beneficiaryBranchId')}
                id="d-transaction-details-beneficiaryBranchId"
                name="beneficiaryBranchId"
                data-cy="beneficiaryBranchId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.beneficiaryPhoneNumber')}
                id="d-transaction-details-beneficiaryPhoneNumber"
                name="beneficiaryPhoneNumber"
                data-cy="beneficiaryPhoneNumber"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranStatus')}
                id="d-transaction-details-tranStatus"
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
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.narration1')}
                id="d-transaction-details-narration1"
                name="narration1"
                data-cy="narration1"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.narration2')}
                id="d-transaction-details-narration2"
                name="narration2"
                data-cy="narration2"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.narration3')}
                id="d-transaction-details-narration3"
                name="narration3"
                data-cy="narration3"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.narration4')}
                id="d-transaction-details-narration4"
                name="narration4"
                data-cy="narration4"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.narration5')}
                id="d-transaction-details-narration5"
                name="narration5"
                data-cy="narration5"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.narration6')}
                id="d-transaction-details-narration6"
                name="narration6"
                data-cy="narration6"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.narration7')}
                id="d-transaction-details-narration7"
                name="narration7"
                data-cy="narration7"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.narration8')}
                id="d-transaction-details-narration8"
                name="narration8"
                data-cy="narration8"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.narration9')}
                id="d-transaction-details-narration9"
                name="narration9"
                data-cy="narration9"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.narration10')}
                id="d-transaction-details-narration10"
                name="narration10"
                data-cy="narration10"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.narration11')}
                id="d-transaction-details-narration11"
                name="narration11"
                data-cy="narration11"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.narration12')}
                id="d-transaction-details-narration12"
                name="narration12"
                data-cy="narration12"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.modeOfPayment')}
                id="d-transaction-details-modeOfPayment"
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
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.retries')}
                id="d-transaction-details-retries"
                name="retries"
                data-cy="retries"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.posted')}
                id="d-transaction-details-posted"
                name="posted"
                data-cy="posted"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.apiId')}
                id="d-transaction-details-apiId"
                name="apiId"
                data-cy="apiId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.apiVersion')}
                id="d-transaction-details-apiVersion"
                name="apiVersion"
                data-cy="apiVersion"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.postingApi')}
                id="d-transaction-details-postingApi"
                name="postingApi"
                data-cy="postingApi"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.isPosted')}
                id="d-transaction-details-isPosted"
                name="isPosted"
                data-cy="isPosted"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.postedBy')}
                id="d-transaction-details-postedBy"
                name="postedBy"
                data-cy="postedBy"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.postedDate')}
                id="d-transaction-details-postedDate"
                name="postedDate"
                data-cy="postedDate"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField1')}
                id="d-transaction-details-tranFreeField1"
                name="tranFreeField1"
                data-cy="tranFreeField1"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField3')}
                id="d-transaction-details-tranFreeField3"
                name="tranFreeField3"
                data-cy="tranFreeField3"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField4')}
                id="d-transaction-details-tranFreeField4"
                name="tranFreeField4"
                data-cy="tranFreeField4"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField5')}
                id="d-transaction-details-tranFreeField5"
                name="tranFreeField5"
                data-cy="tranFreeField5"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField6')}
                id="d-transaction-details-tranFreeField6"
                name="tranFreeField6"
                data-cy="tranFreeField6"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField7')}
                id="d-transaction-details-tranFreeField7"
                name="tranFreeField7"
                data-cy="tranFreeField7"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField8')}
                id="d-transaction-details-tranFreeField8"
                name="tranFreeField8"
                data-cy="tranFreeField8"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField9')}
                id="d-transaction-details-tranFreeField9"
                name="tranFreeField9"
                data-cy="tranFreeField9"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField10')}
                id="d-transaction-details-tranFreeField10"
                name="tranFreeField10"
                data-cy="tranFreeField10"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField11')}
                id="d-transaction-details-tranFreeField11"
                name="tranFreeField11"
                data-cy="tranFreeField11"
                type="text"
              />
              <ValidatedBlobField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField12')}
                id="d-transaction-details-tranFreeField12"
                name="tranFreeField12"
                data-cy="tranFreeField12"
                openActionLabel={translate('entity.action.open')}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField13')}
                id="d-transaction-details-tranFreeField13"
                name="tranFreeField13"
                data-cy="tranFreeField13"
                type="textarea"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField14')}
                id="d-transaction-details-tranFreeField14"
                name="tranFreeField14"
                data-cy="tranFreeField14"
                type="textarea"
              />
              <ValidatedBlobField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField15')}
                id="d-transaction-details-tranFreeField15"
                name="tranFreeField15"
                data-cy="tranFreeField15"
                openActionLabel={translate('entity.action.open')}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField16')}
                id="d-transaction-details-tranFreeField16"
                name="tranFreeField16"
                data-cy="tranFreeField16"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField17')}
                id="d-transaction-details-tranFreeField17"
                name="tranFreeField17"
                data-cy="tranFreeField17"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField18')}
                id="d-transaction-details-tranFreeField18"
                name="tranFreeField18"
                data-cy="tranFreeField18"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField19')}
                id="d-transaction-details-tranFreeField19"
                name="tranFreeField19"
                data-cy="tranFreeField19"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField20')}
                id="d-transaction-details-tranFreeField20"
                name="tranFreeField20"
                data-cy="tranFreeField20"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField21')}
                id="d-transaction-details-tranFreeField21"
                name="tranFreeField21"
                data-cy="tranFreeField21"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField22')}
                id="d-transaction-details-tranFreeField22"
                name="tranFreeField22"
                data-cy="tranFreeField22"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField23')}
                id="d-transaction-details-tranFreeField23"
                name="tranFreeField23"
                data-cy="tranFreeField23"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField24')}
                id="d-transaction-details-tranFreeField24"
                name="tranFreeField24"
                data-cy="tranFreeField24"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField25')}
                id="d-transaction-details-tranFreeField25"
                name="tranFreeField25"
                data-cy="tranFreeField25"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField26')}
                id="d-transaction-details-tranFreeField26"
                name="tranFreeField26"
                data-cy="tranFreeField26"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField27')}
                id="d-transaction-details-tranFreeField27"
                name="tranFreeField27"
                data-cy="tranFreeField27"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.tranFreeField28')}
                id="d-transaction-details-tranFreeField28"
                name="tranFreeField28"
                data-cy="tranFreeField28"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.internalErrorCode')}
                id="d-transaction-details-internalErrorCode"
                name="internalErrorCode"
                data-cy="internalErrorCode"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.externalErrorCode')}
                id="d-transaction-details-externalErrorCode"
                name="externalErrorCode"
                data-cy="externalErrorCode"
                type="text"
              />
              <ValidatedField
                id="d-transaction-details-customer"
                name="customer"
                data-cy="customer"
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionDetails.customer')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/d-transaction-details" replace color="info">
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

export default DTransactionDetailsUpdate;
