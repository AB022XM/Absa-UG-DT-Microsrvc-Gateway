import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IBlockedAccounts } from 'app/shared/model/blocked-accounts.model';
import { RecordStatus } from 'app/shared/model/enumerations/record-status.model';
import { getEntity, updateEntity, createEntity, reset } from './blocked-accounts.reducer';

export const BlockedAccountsUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const blockedAccountsEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.blockedAccounts.entity);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.blockedAccounts.loading);
  const updating = useAppSelector(state => state.absaugdtmicrosrvcgateway.blockedAccounts.updating);
  const updateSuccess = useAppSelector(state => state.absaugdtmicrosrvcgateway.blockedAccounts.updateSuccess);
  const recordStatusValues = Object.keys(RecordStatus);

  const handleClose = () => {
    navigate('/blocked-accounts');
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
    values.blockDate = convertDateTimeToServer(values.blockDate);
    values.startDate = convertDateTimeToServer(values.startDate);
    values.endDate = convertDateTimeToServer(values.endDate);
    values.createdAt = convertDateTimeToServer(values.createdAt);
    values.updatedAt = convertDateTimeToServer(values.updatedAt);

    const entity = {
      ...blockedAccountsEntity,
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
          blockDate: displayDefaultDateTime(),
          startDate: displayDefaultDateTime(),
          endDate: displayDefaultDateTime(),
          createdAt: displayDefaultDateTime(),
          updatedAt: displayDefaultDateTime(),
        }
      : {
          status: 'ACTIVE',
          ...blockedAccountsEntity,
          blockDate: convertDateTimeFromServer(blockedAccountsEntity.blockDate),
          startDate: convertDateTimeFromServer(blockedAccountsEntity.startDate),
          endDate: convertDateTimeFromServer(blockedAccountsEntity.endDate),
          createdAt: convertDateTimeFromServer(blockedAccountsEntity.createdAt),
          updatedAt: convertDateTimeFromServer(blockedAccountsEntity.updatedAt),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="absaUgdtMicrosrvcGatewayApp.blockedAccounts.home.createOrEditLabel" data-cy="BlockedAccountsCreateUpdateHeading">
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.blockedAccounts.home.createOrEditLabel">
              Create or edit a BlockedAccounts
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
                  id="blocked-accounts-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.absaTranRef')}
                id="blocked-accounts-absaTranRef"
                name="absaTranRef"
                data-cy="absaTranRef"
                type="text"
                validate={{}}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.customerId')}
                id="blocked-accounts-customerId"
                name="customerId"
                data-cy="customerId"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.customerAccountNumber')}
                id="blocked-accounts-customerAccountNumber"
                name="customerAccountNumber"
                data-cy="customerAccountNumber"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.dtDTransactionId')}
                id="blocked-accounts-dtDTransactionId"
                name="dtDTransactionId"
                data-cy="dtDTransactionId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockId')}
                id="blocked-accounts-blockId"
                name="blockId"
                data-cy="blockId"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockCode')}
                id="blocked-accounts-blockCode"
                name="blockCode"
                data-cy="blockCode"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockDate')}
                id="blocked-accounts-blockDate"
                name="blockDate"
                data-cy="blockDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockType')}
                id="blocked-accounts-blockType"
                name="blockType"
                data-cy="blockType"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockStatus')}
                id="blocked-accounts-blockStatus"
                name="blockStatus"
                data-cy="blockStatus"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReason')}
                id="blocked-accounts-blockReason"
                name="blockReason"
                data-cy="blockReason"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode1')}
                id="blocked-accounts-blockReasonCode1"
                name="blockReasonCode1"
                data-cy="blockReasonCode1"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode2')}
                id="blocked-accounts-blockReasonCode2"
                name="blockReasonCode2"
                data-cy="blockReasonCode2"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReason1')}
                id="blocked-accounts-blockReason1"
                name="blockReason1"
                data-cy="blockReason1"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode3')}
                id="blocked-accounts-blockReasonCode3"
                name="blockReasonCode3"
                data-cy="blockReasonCode3"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode4')}
                id="blocked-accounts-blockReasonCode4"
                name="blockReasonCode4"
                data-cy="blockReasonCode4"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.startDate')}
                id="blocked-accounts-startDate"
                name="startDate"
                data-cy="startDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.endDate')}
                id="blocked-accounts-endDate"
                name="endDate"
                data-cy="endDate"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.isTemp')}
                id="blocked-accounts-isTemp"
                name="isTemp"
                data-cy="isTemp"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockFreeText')}
                id="blocked-accounts-blockFreeText"
                name="blockFreeText"
                data-cy="blockFreeText"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockFreeText1')}
                id="blocked-accounts-blockFreeText1"
                name="blockFreeText1"
                data-cy="blockFreeText1"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockFreeText2')}
                id="blocked-accounts-blockFreeText2"
                name="blockFreeText2"
                data-cy="blockFreeText2"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockFreeText3')}
                id="blocked-accounts-blockFreeText3"
                name="blockFreeText3"
                data-cy="blockFreeText3"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockFreeText4')}
                id="blocked-accounts-blockFreeText4"
                name="blockFreeText4"
                data-cy="blockFreeText4"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockFreeText5')}
                id="blocked-accounts-blockFreeText5"
                name="blockFreeText5"
                data-cy="blockFreeText5"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockFreeText6')}
                id="blocked-accounts-blockFreeText6"
                name="blockFreeText6"
                data-cy="blockFreeText6"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode5')}
                id="blocked-accounts-blockReasonCode5"
                name="blockReasonCode5"
                data-cy="blockReasonCode5"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode6')}
                id="blocked-accounts-blockReasonCode6"
                name="blockReasonCode6"
                data-cy="blockReasonCode6"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode7')}
                id="blocked-accounts-blockReasonCode7"
                name="blockReasonCode7"
                data-cy="blockReasonCode7"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode8')}
                id="blocked-accounts-blockReasonCode8"
                name="blockReasonCode8"
                data-cy="blockReasonCode8"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode9')}
                id="blocked-accounts-blockReasonCode9"
                name="blockReasonCode9"
                data-cy="blockReasonCode9"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode10')}
                id="blocked-accounts-blockReasonCode10"
                name="blockReasonCode10"
                data-cy="blockReasonCode10"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode11')}
                id="blocked-accounts-blockReasonCode11"
                name="blockReasonCode11"
                data-cy="blockReasonCode11"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode12')}
                id="blocked-accounts-blockReasonCode12"
                name="blockReasonCode12"
                data-cy="blockReasonCode12"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode13')}
                id="blocked-accounts-blockReasonCode13"
                name="blockReasonCode13"
                data-cy="blockReasonCode13"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode14')}
                id="blocked-accounts-blockReasonCode14"
                name="blockReasonCode14"
                data-cy="blockReasonCode14"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode15')}
                id="blocked-accounts-blockReasonCode15"
                name="blockReasonCode15"
                data-cy="blockReasonCode15"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.blockReasonCode16')}
                id="blocked-accounts-blockReasonCode16"
                name="blockReasonCode16"
                data-cy="blockReasonCode16"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.createdAt')}
                id="blocked-accounts-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.createdBy')}
                id="blocked-accounts-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.updatedAt')}
                id="blocked-accounts-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.updatedBy')}
                id="blocked-accounts-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.delflg')}
                id="blocked-accounts-delflg"
                name="delflg"
                data-cy="delflg"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.blockedAccounts.status')}
                id="blocked-accounts-status"
                name="status"
                data-cy="status"
                type="select"
              >
                {recordStatusValues.map(recordStatus => (
                  <option value={recordStatus} key={recordStatus}>
                    {translate('absaUgdtMicrosrvcGatewayApp.RecordStatus.' + recordStatus)}
                  </option>
                ))}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/blocked-accounts" replace color="info">
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

export default BlockedAccountsUpdate;
