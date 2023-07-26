import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, byteSize, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './product-category.reducer';

export const ProductCategoryDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const productCategoryEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.productCategory.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="productCategoryDetailsHeading">
          <Translate contentKey="absaUgdtMicrosrvcGatewayApp.productCategory.detail.title">ProductCategory</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{productCategoryEntity.id}</dd>
          <dt>
            <span id="absaTranRef">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.productCategory.absaTranRef">Absa Tran Ref</Translate>
            </span>
          </dt>
          <dd>{productCategoryEntity.absaTranRef}</dd>
          <dt>
            <span id="billerId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.productCategory.billerId">Biller Id</Translate>
            </span>
          </dt>
          <dd>{productCategoryEntity.billerId}</dd>
          <dt>
            <span id="recordId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.productCategory.recordId">Record Id</Translate>
            </span>
          </dt>
          <dd>{productCategoryEntity.recordId}</dd>
          <dt>
            <span id="productCategoryCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.productCategory.productCategoryCode">Product Category Code</Translate>
            </span>
          </dt>
          <dd>{productCategoryEntity.productCategoryCode}</dd>
          <dt>
            <span id="productCategoryName">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.productCategory.productCategoryName">Product Category Name</Translate>
            </span>
          </dt>
          <dd>{productCategoryEntity.productCategoryName}</dd>
          <dt>
            <span id="productCategoryDescription">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.productCategory.productCategoryDescription">
                Product Category Description
              </Translate>
            </span>
          </dt>
          <dd>{productCategoryEntity.productCategoryDescription}</dd>
          <dt>
            <span id="productCategoryImage">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.productCategory.productCategoryImage">Product Category Image</Translate>
            </span>
          </dt>
          <dd>{productCategoryEntity.productCategoryImage}</dd>
          <dt>
            <span id="productFreeField1">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.productCategory.productFreeField1">Product Free Field 1</Translate>
            </span>
          </dt>
          <dd>{productCategoryEntity.productFreeField1}</dd>
          <dt>
            <span id="productFreeField2">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.productCategory.productFreeField2">Product Free Field 2</Translate>
            </span>
          </dt>
          <dd>{productCategoryEntity.productFreeField2}</dd>
          <dt>
            <span id="productFreeField3">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.productCategory.productFreeField3">Product Free Field 3</Translate>
            </span>
          </dt>
          <dd>{productCategoryEntity.productFreeField3}</dd>
          <dt>
            <span id="productFreeField4">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.productCategory.productFreeField4">Product Free Field 4</Translate>
            </span>
          </dt>
          <dd>{productCategoryEntity.productFreeField4}</dd>
          <dt>
            <span id="productFreeField5">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.productCategory.productFreeField5">Product Free Field 5</Translate>
            </span>
          </dt>
          <dd>{productCategoryEntity.productFreeField5}</dd>
          <dt>
            <span id="productFreeField6">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.productCategory.productFreeField6">Product Free Field 6</Translate>
            </span>
          </dt>
          <dd>{productCategoryEntity.productFreeField6}</dd>
          <dt>
            <span id="productFreeField7">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.productCategory.productFreeField7">Product Free Field 7</Translate>
            </span>
          </dt>
          <dd>{productCategoryEntity.productFreeField7}</dd>
          <dt>
            <span id="productFreeField8">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.productCategory.productFreeField8">Product Free Field 8</Translate>
            </span>
          </dt>
          <dd>{productCategoryEntity.productFreeField8}</dd>
          <dt>
            <span id="productFreeField9">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.productCategory.productFreeField9">Product Free Field 9</Translate>
            </span>
          </dt>
          <dd>{productCategoryEntity.productFreeField9}</dd>
          <dt>
            <span id="productFreeField10">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.productCategory.productFreeField10">Product Free Field 10</Translate>
            </span>
          </dt>
          <dd>{productCategoryEntity.productFreeField10}</dd>
          <dt>
            <span id="productFreeField11">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.productCategory.productFreeField11">Product Free Field 11</Translate>
            </span>
          </dt>
          <dd>{productCategoryEntity.productFreeField11}</dd>
          <dt>
            <span id="delflg">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.productCategory.delflg">Delflg</Translate>
            </span>
          </dt>
          <dd>{productCategoryEntity.delflg ? 'true' : 'false'}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.productCategory.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {productCategoryEntity.createdAt ? (
              <TextFormat value={productCategoryEntity.createdAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.productCategory.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{productCategoryEntity.createdBy}</dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.productCategory.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {productCategoryEntity.updatedAt ? (
              <TextFormat value={productCategoryEntity.updatedAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.productCategory.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{productCategoryEntity.updatedBy}</dd>
        </dl>
        <Button tag={Link} to="/product-category" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/product-category/${productCategoryEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default ProductCategoryDetail;
