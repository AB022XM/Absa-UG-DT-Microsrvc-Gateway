import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IBillerAccount } from 'app/shared/model/biller-account.model';
import { getEntities as getBillerAccounts } from 'app/entities/biller-account/biller-account.reducer';
import { IBiller } from 'app/shared/model/biller.model';
import { getEntity, updateEntity, createEntity, reset } from './biller.reducer';

export const BillerUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const billerAccounts = useAppSelector(state => state.absaugdtmicrosrvcgateway.billerAccount.entities);
  const billerEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.biller.entity);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.biller.loading);
  const updating = useAppSelector(state => state.absaugdtmicrosrvcgateway.biller.updating);
  const updateSuccess = useAppSelector(state => state.absaugdtmicrosrvcgateway.biller.updateSuccess);

  const handleClose = () => {
    navigate('/biller');
  };

  useEffect(() => {
    if (isNew) {
      dispatch(reset());
    } else {
      dispatch(getEntity(id));
    }

    dispatch(getBillerAccounts({}));
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
      ...billerEntity,
      ...values,
      billerAccount: billerAccounts.find(it => it.id.toString() === values.billerAccount.toString()),
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
          ...billerEntity,
          timestamp: convertDateTimeFromServer(billerEntity.timestamp),
          createdAt: convertDateTimeFromServer(billerEntity.createdAt),
          updatedAt: convertDateTimeFromServer(billerEntity.updatedAt),
          billerAccount: billerEntity?.billerAccount?.id,
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="absaUgdtMicrosrvcGatewayApp.biller.home.createOrEditLabel" data-cy="BillerCreateUpdateHeading">
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.biller.home.createOrEditLabel">Create or edit a Biller</Translate>
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
                  id="biller-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.biller.absaTranRef')}
                id="biller-absaTranRef"
                name="absaTranRef"
                data-cy="absaTranRef"
                type="text"
                validate={{}}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.biller.billerId')}
                id="biller-billerId"
                name="billerId"
                data-cy="billerId"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.biller.billerCode')}
                id="biller-billerCode"
                name="billerCode"
                data-cy="billerCode"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.biller.billerName')}
                id="biller-billerName"
                name="billerName"
                data-cy="billerName"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.biller.billerCategoryId')}
                id="biller-billerCategoryId"
                name="billerCategoryId"
                data-cy="billerCategoryId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.biller.addressId')}
                id="biller-addressId"
                name="addressId"
                data-cy="addressId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.biller.narative')}
                id="biller-narative"
                name="narative"
                data-cy="narative"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.biller.narative1')}
                id="biller-narative1"
                name="narative1"
                data-cy="narative1"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.biller.narative2')}
                id="biller-narative2"
                name="narative2"
                data-cy="narative2"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.biller.narative3')}
                id="biller-narative3"
                name="narative3"
                data-cy="narative3"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.biller.narative4')}
                id="biller-narative4"
                name="narative4"
                data-cy="narative4"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.biller.narative5')}
                id="biller-narative5"
                name="narative5"
                data-cy="narative5"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.biller.narative6')}
                id="biller-narative6"
                name="narative6"
                data-cy="narative6"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.biller.narative7')}
                id="biller-narative7"
                name="narative7"
                data-cy="narative7"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.biller.timestamp')}
                id="biller-timestamp"
                name="timestamp"
                data-cy="timestamp"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.biller.freeField1')}
                id="biller-freeField1"
                name="freeField1"
                data-cy="freeField1"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.biller.freeField2')}
                id="biller-freeField2"
                name="freeField2"
                data-cy="freeField2"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.biller.freeField3')}
                id="biller-freeField3"
                name="freeField3"
                data-cy="freeField3"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.biller.freeField4')}
                id="biller-freeField4"
                name="freeField4"
                data-cy="freeField4"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.biller.freeField5')}
                id="biller-freeField5"
                name="freeField5"
                data-cy="freeField5"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.biller.freeField6')}
                id="biller-freeField6"
                name="freeField6"
                data-cy="freeField6"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.biller.freeField7')}
                id="biller-freeField7"
                name="freeField7"
                data-cy="freeField7"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.biller.freeField8')}
                id="biller-freeField8"
                name="freeField8"
                data-cy="freeField8"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.biller.freeField9')}
                id="biller-freeField9"
                name="freeField9"
                data-cy="freeField9"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.biller.freeField10')}
                id="biller-freeField10"
                name="freeField10"
                data-cy="freeField10"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.biller.freeField11')}
                id="biller-freeField11"
                name="freeField11"
                data-cy="freeField11"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.biller.freeField12')}
                id="biller-freeField12"
                name="freeField12"
                data-cy="freeField12"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.biller.freeField13')}
                id="biller-freeField13"
                name="freeField13"
                data-cy="freeField13"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.biller.delflg')}
                id="biller-delflg"
                name="delflg"
                data-cy="delflg"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.biller.createdAt')}
                id="biller-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.biller.createdBy')}
                id="biller-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.biller.updatedAt')}
                id="biller-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.biller.updatedBy')}
                id="biller-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <ValidatedField
                id="biller-billerAccount"
                name="billerAccount"
                data-cy="billerAccount"
                label={translate('absaUgdtMicrosrvcGatewayApp.biller.billerAccount')}
                type="select"
              >
                <option value="" key="0" />
                {billerAccounts
                  ? billerAccounts.map(otherEntity => (
                      <option value={otherEntity.id} key={otherEntity.id}>
                        {otherEntity.id}
                      </option>
                    ))
                  : null}
              </ValidatedField>
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/biller" replace color="info">
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

export default BillerUpdate;
