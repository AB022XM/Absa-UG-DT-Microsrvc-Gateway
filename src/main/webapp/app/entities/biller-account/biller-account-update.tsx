import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IBiller } from 'app/shared/model/biller.model';
import { getEntities as getBillers } from 'app/entities/biller/biller.reducer';
import { IBillerAccount } from 'app/shared/model/biller-account.model';
import { getEntity, updateEntity, createEntity, reset } from './biller-account.reducer';

export const BillerAccountUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const billers = useAppSelector(state => state.absaugdtmicrosrvcgateway.biller.entities);
  const billerAccountEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.billerAccount.entity);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.billerAccount.loading);
  const updating = useAppSelector(state => state.absaugdtmicrosrvcgateway.billerAccount.updating);
  const updateSuccess = useAppSelector(state => state.absaugdtmicrosrvcgateway.billerAccount.updateSuccess);

  const handleClose = () => {
    navigate('/biller-account');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getBillers({}));
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
      ...billerAccountEntity,
      ...values,
      biller: billers.find(it => it.id.toString() === values.biller.toString()),
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
          ...billerAccountEntity,
          timestamp: convertDateTimeFromServer(billerAccountEntity.timestamp),
          createdAt: convertDateTimeFromServer(billerAccountEntity.createdAt),
          updatedAt: convertDateTimeFromServer(billerAccountEntity.updatedAt),
          biller: billerAccountEntity?.biller?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="absaUgdtMicrosrvcGatewayApp.billerAccount.home.createOrEditLabel" data-cy="BillerAccountCreateUpdateHeading">
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.billerAccount.home.createOrEditLabel">
              Create or edit a BillerAccount
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
                  id="biller-account-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.billerAccount.absaTranRef')}
                id="biller-account-absaTranRef"
                name="absaTranRef"
                data-cy="absaTranRef"
                type="text"
                validate={{}}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.billerAccount.recordId')}
                id="biller-account-recordId"
                name="recordId"
                data-cy="recordId"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.billerAccount.billerId')}
                id="biller-account-billerId"
                name="billerId"
                data-cy="billerId"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.billerAccount.billerName')}
                id="biller-account-billerName"
                name="billerName"
                data-cy="billerName"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.billerAccount.billerAccNumber')}
                id="biller-account-billerAccNumber"
                name="billerAccNumber"
                data-cy="billerAccNumber"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.billerAccount.billerAccountDescription')}
                id="biller-account-billerAccountDescription"
                name="billerAccountDescription"
                data-cy="billerAccountDescription"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.billerAccount.timestamp')}
                id="biller-account-timestamp"
                name="timestamp"
                data-cy="timestamp"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.billerAccount.billerFreeField1')}
                id="biller-account-billerFreeField1"
                name="billerFreeField1"
                data-cy="billerFreeField1"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.billerAccount.billerFreeField2')}
                id="biller-account-billerFreeField2"
                name="billerFreeField2"
                data-cy="billerFreeField2"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.billerAccount.billerFreeField3')}
                id="biller-account-billerFreeField3"
                name="billerFreeField3"
                data-cy="billerFreeField3"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.billerAccount.billerFreeField4')}
                id="biller-account-billerFreeField4"
                name="billerFreeField4"
                data-cy="billerFreeField4"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.billerAccount.billerFreeField5')}
                id="biller-account-billerFreeField5"
                name="billerFreeField5"
                data-cy="billerFreeField5"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.billerAccount.billerFreeField6')}
                id="biller-account-billerFreeField6"
                name="billerFreeField6"
                data-cy="billerFreeField6"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.billerAccount.billerFreeField7')}
                id="biller-account-billerFreeField7"
                name="billerFreeField7"
                data-cy="billerFreeField7"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.billerAccount.billerFreeField8')}
                id="biller-account-billerFreeField8"
                name="billerFreeField8"
                data-cy="billerFreeField8"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.billerAccount.billerFreeField9')}
                id="biller-account-billerFreeField9"
                name="billerFreeField9"
                data-cy="billerFreeField9"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.billerAccount.billerFreeField10')}
                id="biller-account-billerFreeField10"
                name="billerFreeField10"
                data-cy="billerFreeField10"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.billerAccount.billerFreeField11')}
                id="biller-account-billerFreeField11"
                name="billerFreeField11"
                data-cy="billerFreeField11"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.billerAccount.billerFreeField12')}
                id="biller-account-billerFreeField12"
                name="billerFreeField12"
                data-cy="billerFreeField12"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.billerAccount.billerFreeField13')}
                id="biller-account-billerFreeField13"
                name="billerFreeField13"
                data-cy="billerFreeField13"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.billerAccount.delflg')}
                id="biller-account-delflg"
                name="delflg"
                data-cy="delflg"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.billerAccount.createdAt')}
                id="biller-account-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.billerAccount.createdBy')}
                id="biller-account-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.billerAccount.updatedAt')}
                id="biller-account-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.billerAccount.updatedBy')}
                id="biller-account-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <ValidatedField
                id="biller-account-biller"
                name="biller"
                data-cy="biller"
                label={translate('absaUgdtMicrosrvcGatewayApp.billerAccount.biller')}
                type="select"
              >
                <option value="" key="0" />
                {billers
                  ? billers.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/biller-account" replace color="info">
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

export default BillerAccountUpdate;
