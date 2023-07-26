import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IBank } from 'app/shared/model/bank.model';
import { RecordStatus } from 'app/shared/model/enumerations/record-status.model';
import { getEntity, updateEntity, createEntity, reset } from './bank.reducer';

export const BankUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const bankEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.bank.entity);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.bank.loading);
  const updating = useAppSelector(state => state.absaugdtmicrosrvcgateway.bank.updating);
  const updateSuccess = useAppSelector(state => state.absaugdtmicrosrvcgateway.bank.updateSuccess);
  const recordStatusValues = Object.keys(RecordStatus);

  const handleClose = () => {
    navigate('/bank');
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
    values.createdAt = convertDateTimeToServer(values.createdAt);
    values.updatedAt = convertDateTimeToServer(values.updatedAt);

    const entity = {
      ...bankEntity,
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
          createdAt: displayDefaultDateTime(),
          updatedAt: displayDefaultDateTime(),
        }
      : {
          status: 'ACTIVE',
          ...bankEntity,
          createdAt: convertDateTimeFromServer(bankEntity.createdAt),
          updatedAt: convertDateTimeFromServer(bankEntity.updatedAt),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="absaUgdtMicrosrvcGatewayApp.bank.home.createOrEditLabel" data-cy="BankCreateUpdateHeading">
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.bank.home.createOrEditLabel">Create or edit a Bank</Translate>
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
                  id="bank-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.bank.absaTranRef')}
                id="bank-absaTranRef"
                name="absaTranRef"
                data-cy="absaTranRef"
                type="text"
                validate={{}}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.bank.billerId')}
                id="bank-billerId"
                name="billerId"
                data-cy="billerId"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.bank.paymentItemCode')}
                id="bank-paymentItemCode"
                name="paymentItemCode"
                data-cy="paymentItemCode"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.bank.dtDTransactionId')}
                id="bank-dtDTransactionId"
                name="dtDTransactionId"
                data-cy="dtDTransactionId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.bank.amolDTransactionId')}
                id="bank-amolDTransactionId"
                name="amolDTransactionId"
                data-cy="amolDTransactionId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.bank.bankName')}
                id="bank-bankName"
                name="bankName"
                data-cy="bankName"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.bank.bankSwiftCode')}
                id="bank-bankSwiftCode"
                name="bankSwiftCode"
                data-cy="bankSwiftCode"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.bank.bankBranchId')}
                id="bank-bankBranchId"
                name="bankBranchId"
                data-cy="bankBranchId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.bank.bankPhoneNumber')}
                id="bank-bankPhoneNumber"
                name="bankPhoneNumber"
                data-cy="bankPhoneNumber"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.bank.bankEmail')}
                id="bank-bankEmail"
                name="bankEmail"
                data-cy="bankEmail"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.bank.bankFreeField1')}
                id="bank-bankFreeField1"
                name="bankFreeField1"
                data-cy="bankFreeField1"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.bank.bankFreeField3')}
                id="bank-bankFreeField3"
                name="bankFreeField3"
                data-cy="bankFreeField3"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.bank.bankFreeField4')}
                id="bank-bankFreeField4"
                name="bankFreeField4"
                data-cy="bankFreeField4"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.bank.bankFreeField5')}
                id="bank-bankFreeField5"
                name="bankFreeField5"
                data-cy="bankFreeField5"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.bank.bankFreeField6')}
                id="bank-bankFreeField6"
                name="bankFreeField6"
                data-cy="bankFreeField6"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.bank.bankFreeField7')}
                id="bank-bankFreeField7"
                name="bankFreeField7"
                data-cy="bankFreeField7"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.bank.bankFreeField8')}
                id="bank-bankFreeField8"
                name="bankFreeField8"
                data-cy="bankFreeField8"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.bank.bankFreeField9')}
                id="bank-bankFreeField9"
                name="bankFreeField9"
                data-cy="bankFreeField9"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.bank.bankFreeField10')}
                id="bank-bankFreeField10"
                name="bankFreeField10"
                data-cy="bankFreeField10"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.bank.bankFreeField11')}
                id="bank-bankFreeField11"
                name="bankFreeField11"
                data-cy="bankFreeField11"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.bank.bankFreeField12')}
                id="bank-bankFreeField12"
                name="bankFreeField12"
                data-cy="bankFreeField12"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.bank.bankFreeField13')}
                id="bank-bankFreeField13"
                name="bankFreeField13"
                data-cy="bankFreeField13"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.bank.bankFreeField14')}
                id="bank-bankFreeField14"
                name="bankFreeField14"
                data-cy="bankFreeField14"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.bank.bankFreeField15')}
                id="bank-bankFreeField15"
                name="bankFreeField15"
                data-cy="bankFreeField15"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.bank.bankFreeField16')}
                id="bank-bankFreeField16"
                name="bankFreeField16"
                data-cy="bankFreeField16"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.bank.bankFreeField17')}
                id="bank-bankFreeField17"
                name="bankFreeField17"
                data-cy="bankFreeField17"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.bank.bankFreeField18')}
                id="bank-bankFreeField18"
                name="bankFreeField18"
                data-cy="bankFreeField18"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.bank.bankFreeField19')}
                id="bank-bankFreeField19"
                name="bankFreeField19"
                data-cy="bankFreeField19"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.bank.bankFreeField20')}
                id="bank-bankFreeField20"
                name="bankFreeField20"
                data-cy="bankFreeField20"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.bank.bankFreeField21')}
                id="bank-bankFreeField21"
                name="bankFreeField21"
                data-cy="bankFreeField21"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.bank.bankFreeField22')}
                id="bank-bankFreeField22"
                name="bankFreeField22"
                data-cy="bankFreeField22"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.bank.bankFreeField23')}
                id="bank-bankFreeField23"
                name="bankFreeField23"
                data-cy="bankFreeField23"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.bank.bankFreeField24')}
                id="bank-bankFreeField24"
                name="bankFreeField24"
                data-cy="bankFreeField24"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.bank.createdAt')}
                id="bank-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.bank.createdBy')}
                id="bank-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.bank.updatedAt')}
                id="bank-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.bank.updatedBy')}
                id="bank-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.bank.delflg')}
                id="bank-delflg"
                name="delflg"
                data-cy="delflg"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.bank.status')}
                id="bank-status"
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
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/bank" replace color="info">
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

export default BankUpdate;
