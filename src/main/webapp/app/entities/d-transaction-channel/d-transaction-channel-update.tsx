import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IDTransaction } from 'app/shared/model/d-transaction.model';
import { getEntities as getDTransactions } from 'app/entities/d-transaction/d-transaction.reducer';
import { IDTransactionChannel } from 'app/shared/model/d-transaction-channel.model';
import { getEntity, updateEntity, createEntity, reset } from './d-transaction-channel.reducer';

export const DTransactionChannelUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const dTransactions = useAppSelector(state => state.absaugdtmicrosrvcgateway.dTransaction.entities);
  const dTransactionChannelEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.dTransactionChannel.entity);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.dTransactionChannel.loading);
  const updating = useAppSelector(state => state.absaugdtmicrosrvcgateway.dTransactionChannel.updating);
  const updateSuccess = useAppSelector(state => state.absaugdtmicrosrvcgateway.dTransactionChannel.updateSuccess);

  const handleClose = () => {
    navigate('/d-transaction-channel');
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
    values.timestamp = convertDateTimeToServer(values.timestamp);
    values.createdAt = convertDateTimeToServer(values.createdAt);
    values.updatedAt = convertDateTimeToServer(values.updatedAt);

    const entity = {
      ...dTransactionChannelEntity,
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
          timestamp: displayDefaultDateTime(),
          createdAt: displayDefaultDateTime(),
          updatedAt: displayDefaultDateTime(),
        }
      : {
          ...dTransactionChannelEntity,
          timestamp: convertDateTimeFromServer(dTransactionChannelEntity.timestamp),
          createdAt: convertDateTimeFromServer(dTransactionChannelEntity.createdAt),
          updatedAt: convertDateTimeFromServer(dTransactionChannelEntity.updatedAt),
          dTransaction: dTransactionChannelEntity?.dTransaction?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.home.createOrEditLabel" data-cy="DTransactionChannelCreateUpdateHeading">
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.home.createOrEditLabel">
              Create or edit a DTransactionChannel
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
                  id="d-transaction-channel-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.absaTranRef')}
                id="d-transaction-channel-absaTranRef"
                name="absaTranRef"
                data-cy="absaTranRef"
                type="text"
                validate={{}}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.billerId')}
                id="d-transaction-channel-billerId"
                name="billerId"
                data-cy="billerId"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.paymentItemCode')}
                id="d-transaction-channel-paymentItemCode"
                name="paymentItemCode"
                data-cy="paymentItemCode"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.dtDTransactionId')}
                id="d-transaction-channel-dtDTransactionId"
                name="dtDTransactionId"
                data-cy="dtDTransactionId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.transactionReferenceNumber')}
                id="d-transaction-channel-transactionReferenceNumber"
                name="transactionReferenceNumber"
                data-cy="transactionReferenceNumber"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.externalTranid')}
                id="d-transaction-channel-externalTranid"
                name="externalTranid"
                data-cy="externalTranid"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.channelCode')}
                id="d-transaction-channel-channelCode"
                name="channelCode"
                data-cy="channelCode"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.channelName')}
                id="d-transaction-channel-channelName"
                name="channelName"
                data-cy="channelName"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.channelDescription')}
                id="d-transaction-channel-channelDescription"
                name="channelDescription"
                data-cy="channelDescription"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.timestamp')}
                id="d-transaction-channel-timestamp"
                name="timestamp"
                data-cy="timestamp"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText')}
                id="d-transaction-channel-chanelFreeText"
                name="chanelFreeText"
                data-cy="chanelFreeText"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText1')}
                id="d-transaction-channel-chanelFreeText1"
                name="chanelFreeText1"
                data-cy="chanelFreeText1"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText2')}
                id="d-transaction-channel-chanelFreeText2"
                name="chanelFreeText2"
                data-cy="chanelFreeText2"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText3')}
                id="d-transaction-channel-chanelFreeText3"
                name="chanelFreeText3"
                data-cy="chanelFreeText3"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText4')}
                id="d-transaction-channel-chanelFreeText4"
                name="chanelFreeText4"
                data-cy="chanelFreeText4"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText5')}
                id="d-transaction-channel-chanelFreeText5"
                name="chanelFreeText5"
                data-cy="chanelFreeText5"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText6')}
                id="d-transaction-channel-chanelFreeText6"
                name="chanelFreeText6"
                data-cy="chanelFreeText6"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText7')}
                id="d-transaction-channel-chanelFreeText7"
                name="chanelFreeText7"
                data-cy="chanelFreeText7"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText8')}
                id="d-transaction-channel-chanelFreeText8"
                name="chanelFreeText8"
                data-cy="chanelFreeText8"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText9')}
                id="d-transaction-channel-chanelFreeText9"
                name="chanelFreeText9"
                data-cy="chanelFreeText9"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText10')}
                id="d-transaction-channel-chanelFreeText10"
                name="chanelFreeText10"
                data-cy="chanelFreeText10"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText11')}
                id="d-transaction-channel-chanelFreeText11"
                name="chanelFreeText11"
                data-cy="chanelFreeText11"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText12')}
                id="d-transaction-channel-chanelFreeText12"
                name="chanelFreeText12"
                data-cy="chanelFreeText12"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText13')}
                id="d-transaction-channel-chanelFreeText13"
                name="chanelFreeText13"
                data-cy="chanelFreeText13"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText14')}
                id="d-transaction-channel-chanelFreeText14"
                name="chanelFreeText14"
                data-cy="chanelFreeText14"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText15')}
                id="d-transaction-channel-chanelFreeText15"
                name="chanelFreeText15"
                data-cy="chanelFreeText15"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText16')}
                id="d-transaction-channel-chanelFreeText16"
                name="chanelFreeText16"
                data-cy="chanelFreeText16"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText17')}
                id="d-transaction-channel-chanelFreeText17"
                name="chanelFreeText17"
                data-cy="chanelFreeText17"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText18')}
                id="d-transaction-channel-chanelFreeText18"
                name="chanelFreeText18"
                data-cy="chanelFreeText18"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText19')}
                id="d-transaction-channel-chanelFreeText19"
                name="chanelFreeText19"
                data-cy="chanelFreeText19"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText20')}
                id="d-transaction-channel-chanelFreeText20"
                name="chanelFreeText20"
                data-cy="chanelFreeText20"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText21')}
                id="d-transaction-channel-chanelFreeText21"
                name="chanelFreeText21"
                data-cy="chanelFreeText21"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText22')}
                id="d-transaction-channel-chanelFreeText22"
                name="chanelFreeText22"
                data-cy="chanelFreeText22"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText23')}
                id="d-transaction-channel-chanelFreeText23"
                name="chanelFreeText23"
                data-cy="chanelFreeText23"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText24')}
                id="d-transaction-channel-chanelFreeText24"
                name="chanelFreeText24"
                data-cy="chanelFreeText24"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.createdAt')}
                id="d-transaction-channel-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.createdBy')}
                id="d-transaction-channel-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.updatedAt')}
                id="d-transaction-channel-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.updatedBy')}
                id="d-transaction-channel-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.delflg')}
                id="d-transaction-channel-delflg"
                name="delflg"
                data-cy="delflg"
                check
                type="checkbox"
              />
              <ValidatedField
                id="d-transaction-channel-dTransaction"
                name="dTransaction"
                data-cy="dTransaction"
                label={translate('absaUgdtMicrosrvcGatewayApp.dTransactionChannel.dTransaction')}
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/d-transaction-channel" replace color="info">
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

export default DTransactionChannelUpdate;
