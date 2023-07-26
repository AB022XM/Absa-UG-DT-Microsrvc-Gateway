import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IBank } from 'app/shared/model/bank.model';
import { getEntities as getBanks } from 'app/entities/bank/bank.reducer';
import { IBranch } from 'app/shared/model/branch.model';
import { RecordStatus } from 'app/shared/model/enumerations/record-status.model';
import { getEntity, updateEntity, createEntity, reset } from './branch.reducer';

export const BranchUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const banks = useAppSelector(state => state.absaugdtmicrosrvcgateway.bank.entities);
  const branchEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.branch.entity);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.branch.loading);
  const updating = useAppSelector(state => state.absaugdtmicrosrvcgateway.branch.updating);
  const updateSuccess = useAppSelector(state => state.absaugdtmicrosrvcgateway.branch.updateSuccess);
  const recordStatusValues = Object.keys(RecordStatus);

  const handleClose = () => {
    navigate('/branch');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getBanks({}));
  }, []);

  useEffect(() => {
    if (updateSuccess) {
      handleClose();
    }
  }, [updateSuccess]);

  const saveEntity = values => {
    values.createdAt = convertDateTimeToServer(values.createdAt);
    values.updatedAt = convertDateTimeToServer(values.updatedAt);
    values.timestamp = convertDateTimeToServer(values.timestamp);

    const entity = {
      ...branchEntity,
      ...values,
      bank: banks.find(it => it.id.toString() === values.bank.toString()),
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
          timestamp: displayDefaultDateTime(),
        }
      : {
          status: 'ACTIVE',
          ...branchEntity,
          createdAt: convertDateTimeFromServer(branchEntity.createdAt),
          updatedAt: convertDateTimeFromServer(branchEntity.updatedAt),
          timestamp: convertDateTimeFromServer(branchEntity.timestamp),
          bank: branchEntity?.bank?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="absaUgdtMicrosrvcGatewayApp.branch.home.createOrEditLabel" data-cy="BranchCreateUpdateHeading">
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.branch.home.createOrEditLabel">Create or edit a Branch</Translate>
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
                  id="branch-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.branch.absaTranRef')}
                id="branch-absaTranRef"
                name="absaTranRef"
                data-cy="absaTranRef"
                type="text"
                validate={{}}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.branch.recordId')}
                id="branch-recordId"
                name="recordId"
                data-cy="recordId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.branch.addressId')}
                id="branch-addressId"
                name="addressId"
                data-cy="addressId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.branch.bankId')}
                id="branch-bankId"
                name="bankId"
                data-cy="bankId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.branch.branchId')}
                id="branch-branchId"
                name="branchId"
                data-cy="branchId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.branch.branchName')}
                id="branch-branchName"
                name="branchName"
                data-cy="branchName"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.branch.branchSwiftCode')}
                id="branch-branchSwiftCode"
                name="branchSwiftCode"
                data-cy="branchSwiftCode"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.branch.branchPhoneNumber')}
                id="branch-branchPhoneNumber"
                name="branchPhoneNumber"
                data-cy="branchPhoneNumber"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.branch.branchEmail')}
                id="branch-branchEmail"
                name="branchEmail"
                data-cy="branchEmail"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.branch.branchFreeField1')}
                id="branch-branchFreeField1"
                name="branchFreeField1"
                data-cy="branchFreeField1"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.branch.branchFreeField3')}
                id="branch-branchFreeField3"
                name="branchFreeField3"
                data-cy="branchFreeField3"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.branch.branchFreeField4')}
                id="branch-branchFreeField4"
                name="branchFreeField4"
                data-cy="branchFreeField4"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.branch.branchFreeField5')}
                id="branch-branchFreeField5"
                name="branchFreeField5"
                data-cy="branchFreeField5"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.branch.branchFreeField6')}
                id="branch-branchFreeField6"
                name="branchFreeField6"
                data-cy="branchFreeField6"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.branch.branchFreeField7')}
                id="branch-branchFreeField7"
                name="branchFreeField7"
                data-cy="branchFreeField7"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.branch.branchFreeField8')}
                id="branch-branchFreeField8"
                name="branchFreeField8"
                data-cy="branchFreeField8"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.branch.branchFreeField9')}
                id="branch-branchFreeField9"
                name="branchFreeField9"
                data-cy="branchFreeField9"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.branch.branchFreeField10')}
                id="branch-branchFreeField10"
                name="branchFreeField10"
                data-cy="branchFreeField10"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.branch.branchFreeField11')}
                id="branch-branchFreeField11"
                name="branchFreeField11"
                data-cy="branchFreeField11"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.branch.branchFreeField12')}
                id="branch-branchFreeField12"
                name="branchFreeField12"
                data-cy="branchFreeField12"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.branch.branchFreeField13')}
                id="branch-branchFreeField13"
                name="branchFreeField13"
                data-cy="branchFreeField13"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.branch.branchFreeField14')}
                id="branch-branchFreeField14"
                name="branchFreeField14"
                data-cy="branchFreeField14"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.branch.branchFreeField15')}
                id="branch-branchFreeField15"
                name="branchFreeField15"
                data-cy="branchFreeField15"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.branch.branchFreeField16')}
                id="branch-branchFreeField16"
                name="branchFreeField16"
                data-cy="branchFreeField16"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.branch.branchFreeField17')}
                id="branch-branchFreeField17"
                name="branchFreeField17"
                data-cy="branchFreeField17"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.branch.branchFreeField18')}
                id="branch-branchFreeField18"
                name="branchFreeField18"
                data-cy="branchFreeField18"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.branch.branchFreeField19')}
                id="branch-branchFreeField19"
                name="branchFreeField19"
                data-cy="branchFreeField19"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.branch.branchFreeField20')}
                id="branch-branchFreeField20"
                name="branchFreeField20"
                data-cy="branchFreeField20"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.branch.branchFreeField21')}
                id="branch-branchFreeField21"
                name="branchFreeField21"
                data-cy="branchFreeField21"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.branch.branchFreeField22')}
                id="branch-branchFreeField22"
                name="branchFreeField22"
                data-cy="branchFreeField22"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.branch.branchFreeField23')}
                id="branch-branchFreeField23"
                name="branchFreeField23"
                data-cy="branchFreeField23"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.branch.branchFreeField24')}
                id="branch-branchFreeField24"
                name="branchFreeField24"
                data-cy="branchFreeField24"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.branch.createdAt')}
                id="branch-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.branch.createdBy')}
                id="branch-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.branch.updatedAt')}
                id="branch-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.branch.updatedBy')}
                id="branch-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.branch.timestamp')}
                id="branch-timestamp"
                name="timestamp"
                data-cy="timestamp"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.branch.status')}
                id="branch-status"
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
              <ValidatedField
                id="branch-bank"
                name="bank"
                data-cy="bank"
                label={translate('absaUgdtMicrosrvcGatewayApp.branch.bank')}
                type="select"
              >
                <option value="" key="0" />
                {banks
                  ? banks.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/branch" replace color="info">
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

export default BranchUpdate;
