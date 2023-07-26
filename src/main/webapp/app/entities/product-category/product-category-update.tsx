import React, { useState, useEffect } from 'react';
import { Link, useNavigate, useParams } from 'react-router-dom';
import { Button, Row, Col, FormText } from 'reactstrap';
import { isNumber, Translate, translate, ValidatedField, ValidatedForm } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { IProductCategory } from 'app/shared/model/product-category.model';
import { getEntity, updateEntity, createEntity, reset } from './product-category.reducer';

export const ProductCategoryUpdate = () => {
  const dispatch = useAppDispatch();

  const navigate = useNavigate();

  const { id } = useParams<'id'>();
  const isNew = id === undefined;

  const productCategoryEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.productCategory.entity);
  const loading = useAppSelector(state => state.absaugdtmicrosrvcgateway.productCategory.loading);
  const updating = useAppSelector(state => state.absaugdtmicrosrvcgateway.productCategory.updating);
  const updateSuccess = useAppSelector(state => state.absaugdtmicrosrvcgateway.productCategory.updateSuccess);

  const handleClose = () => {
    navigate('/product-category');
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
      ...productCategoryEntity,
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
          ...productCategoryEntity,
          createdAt: convertDateTimeFromServer(productCategoryEntity.createdAt),
          updatedAt: convertDateTimeFromServer(productCategoryEntity.updatedAt),
        };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="absaUgdtMicrosrvcGatewayApp.productCategory.home.createOrEditLabel" data-cy="ProductCategoryCreateUpdateHeading">
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.productCategory.home.createOrEditLabel">
              Create or edit a ProductCategory
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
                  id="product-category-id"
                  label={translate('global.field.id')}
                  validate={{ required: true }}
                />
              ) : null}
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.productCategory.absaTranRef')}
                id="product-category-absaTranRef"
                name="absaTranRef"
                data-cy="absaTranRef"
                type="text"
                validate={{}}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.productCategory.billerId')}
                id="product-category-billerId"
                name="billerId"
                data-cy="billerId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.productCategory.recordId')}
                id="product-category-recordId"
                name="recordId"
                data-cy="recordId"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.productCategory.productCategoryCode')}
                id="product-category-productCategoryCode"
                name="productCategoryCode"
                data-cy="productCategoryCode"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.productCategory.productCategoryName')}
                id="product-category-productCategoryName"
                name="productCategoryName"
                data-cy="productCategoryName"
                type="text"
                validate={{
                  required: { value: true, message: translate('entity.validation.required') },
                }}
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.productCategory.productCategoryDescription')}
                id="product-category-productCategoryDescription"
                name="productCategoryDescription"
                data-cy="productCategoryDescription"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.productCategory.productCategoryImage')}
                id="product-category-productCategoryImage"
                name="productCategoryImage"
                data-cy="productCategoryImage"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.productCategory.productFreeField1')}
                id="product-category-productFreeField1"
                name="productFreeField1"
                data-cy="productFreeField1"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.productCategory.productFreeField2')}
                id="product-category-productFreeField2"
                name="productFreeField2"
                data-cy="productFreeField2"
                type="textarea"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.productCategory.productFreeField3')}
                id="product-category-productFreeField3"
                name="productFreeField3"
                data-cy="productFreeField3"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.productCategory.productFreeField4')}
                id="product-category-productFreeField4"
                name="productFreeField4"
                data-cy="productFreeField4"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.productCategory.productFreeField5')}
                id="product-category-productFreeField5"
                name="productFreeField5"
                data-cy="productFreeField5"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.productCategory.productFreeField6')}
                id="product-category-productFreeField6"
                name="productFreeField6"
                data-cy="productFreeField6"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.productCategory.productFreeField7')}
                id="product-category-productFreeField7"
                name="productFreeField7"
                data-cy="productFreeField7"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.productCategory.productFreeField8')}
                id="product-category-productFreeField8"
                name="productFreeField8"
                data-cy="productFreeField8"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.productCategory.productFreeField9')}
                id="product-category-productFreeField9"
                name="productFreeField9"
                data-cy="productFreeField9"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.productCategory.productFreeField10')}
                id="product-category-productFreeField10"
                name="productFreeField10"
                data-cy="productFreeField10"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.productCategory.productFreeField11')}
                id="product-category-productFreeField11"
                name="productFreeField11"
                data-cy="productFreeField11"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.productCategory.delflg')}
                id="product-category-delflg"
                name="delflg"
                data-cy="delflg"
                check
                type="checkbox"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.productCategory.createdAt')}
                id="product-category-createdAt"
                name="createdAt"
                data-cy="createdAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.productCategory.createdBy')}
                id="product-category-createdBy"
                name="createdBy"
                data-cy="createdBy"
                type="text"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.productCategory.updatedAt')}
                id="product-category-updatedAt"
                name="updatedAt"
                data-cy="updatedAt"
                type="datetime-local"
                placeholder="YYYY-MM-DD HH:mm"
              />
              <ValidatedField
                label={translate('absaUgdtMicrosrvcGatewayApp.productCategory.updatedBy')}
                id="product-category-updatedBy"
                name="updatedBy"
                data-cy="updatedBy"
                type="text"
              />
              <Button tag={Link} id="cancel-save" data-cy="entityCreateCancelButton" to="/product-category" replace color="info">
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

export default ProductCategoryUpdate;
