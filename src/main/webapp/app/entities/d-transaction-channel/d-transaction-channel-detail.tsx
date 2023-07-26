import React, { useEffect } from 'react';
import { Link, useParams } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, TextFormat } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import { useAppDispatch, useAppSelector } from 'app/config/store';

import { getEntity } from './d-transaction-channel.reducer';

export const DTransactionChannelDetail = () => {
  const dispatch = useAppDispatch();

  const { id } = useParams<'id'>();

  useEffect(() => {
    dispatch(getEntity(id));
  }, []);

  const dTransactionChannelEntity = useAppSelector(state => state.absaugdtmicrosrvcgateway.dTransactionChannel.entity);
  return (
    <Row>
      <Col md="8">
        <h2 data-cy="dTransactionChannelDetailsHeading">
          <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.detail.title">DTransactionChannel</Translate>
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="id">
              <Translate contentKey="global.field.id">ID</Translate>
            </span>
          </dt>
          <dd>{dTransactionChannelEntity.id}</dd>
          <dt>
            <span id="absaTranRef">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.absaTranRef">Absa Tran Ref</Translate>
            </span>
          </dt>
          <dd>{dTransactionChannelEntity.absaTranRef}</dd>
          <dt>
            <span id="billerId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.billerId">Biller Id</Translate>
            </span>
          </dt>
          <dd>{dTransactionChannelEntity.billerId}</dd>
          <dt>
            <span id="paymentItemCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.paymentItemCode">Payment Item Code</Translate>
            </span>
          </dt>
          <dd>{dTransactionChannelEntity.paymentItemCode}</dd>
          <dt>
            <span id="dtDTransactionId">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.dtDTransactionId">Dt D Transaction Id</Translate>
            </span>
          </dt>
          <dd>{dTransactionChannelEntity.dtDTransactionId}</dd>
          <dt>
            <span id="transactionReferenceNumber">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.transactionReferenceNumber">
                Transaction Reference Number
              </Translate>
            </span>
          </dt>
          <dd>{dTransactionChannelEntity.transactionReferenceNumber}</dd>
          <dt>
            <span id="externalTranid">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.externalTranid">External Tranid</Translate>
            </span>
          </dt>
          <dd>{dTransactionChannelEntity.externalTranid}</dd>
          <dt>
            <span id="channelCode">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.channelCode">Channel Code</Translate>
            </span>
          </dt>
          <dd>{dTransactionChannelEntity.channelCode}</dd>
          <dt>
            <span id="channelName">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.channelName">Channel Name</Translate>
            </span>
          </dt>
          <dd>{dTransactionChannelEntity.channelName}</dd>
          <dt>
            <span id="channelDescription">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.channelDescription">Channel Description</Translate>
            </span>
          </dt>
          <dd>{dTransactionChannelEntity.channelDescription}</dd>
          <dt>
            <span id="timestamp">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.timestamp">Timestamp</Translate>
            </span>
          </dt>
          <dd>
            {dTransactionChannelEntity.timestamp ? (
              <TextFormat value={dTransactionChannelEntity.timestamp} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="chanelFreeText">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText">Chanel Free Text</Translate>
            </span>
          </dt>
          <dd>{dTransactionChannelEntity.chanelFreeText}</dd>
          <dt>
            <span id="chanelFreeText1">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText1">Chanel Free Text 1</Translate>
            </span>
          </dt>
          <dd>{dTransactionChannelEntity.chanelFreeText1}</dd>
          <dt>
            <span id="chanelFreeText2">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText2">Chanel Free Text 2</Translate>
            </span>
          </dt>
          <dd>{dTransactionChannelEntity.chanelFreeText2}</dd>
          <dt>
            <span id="chanelFreeText3">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText3">Chanel Free Text 3</Translate>
            </span>
          </dt>
          <dd>{dTransactionChannelEntity.chanelFreeText3}</dd>
          <dt>
            <span id="chanelFreeText4">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText4">Chanel Free Text 4</Translate>
            </span>
          </dt>
          <dd>{dTransactionChannelEntity.chanelFreeText4}</dd>
          <dt>
            <span id="chanelFreeText5">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText5">Chanel Free Text 5</Translate>
            </span>
          </dt>
          <dd>{dTransactionChannelEntity.chanelFreeText5}</dd>
          <dt>
            <span id="chanelFreeText6">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText6">Chanel Free Text 6</Translate>
            </span>
          </dt>
          <dd>{dTransactionChannelEntity.chanelFreeText6}</dd>
          <dt>
            <span id="chanelFreeText7">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText7">Chanel Free Text 7</Translate>
            </span>
          </dt>
          <dd>{dTransactionChannelEntity.chanelFreeText7}</dd>
          <dt>
            <span id="chanelFreeText8">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText8">Chanel Free Text 8</Translate>
            </span>
          </dt>
          <dd>{dTransactionChannelEntity.chanelFreeText8}</dd>
          <dt>
            <span id="chanelFreeText9">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText9">Chanel Free Text 9</Translate>
            </span>
          </dt>
          <dd>{dTransactionChannelEntity.chanelFreeText9}</dd>
          <dt>
            <span id="chanelFreeText10">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText10">Chanel Free Text 10</Translate>
            </span>
          </dt>
          <dd>{dTransactionChannelEntity.chanelFreeText10}</dd>
          <dt>
            <span id="chanelFreeText11">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText11">Chanel Free Text 11</Translate>
            </span>
          </dt>
          <dd>{dTransactionChannelEntity.chanelFreeText11}</dd>
          <dt>
            <span id="chanelFreeText12">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText12">Chanel Free Text 12</Translate>
            </span>
          </dt>
          <dd>{dTransactionChannelEntity.chanelFreeText12}</dd>
          <dt>
            <span id="chanelFreeText13">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText13">Chanel Free Text 13</Translate>
            </span>
          </dt>
          <dd>{dTransactionChannelEntity.chanelFreeText13}</dd>
          <dt>
            <span id="chanelFreeText14">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText14">Chanel Free Text 14</Translate>
            </span>
          </dt>
          <dd>{dTransactionChannelEntity.chanelFreeText14}</dd>
          <dt>
            <span id="chanelFreeText15">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText15">Chanel Free Text 15</Translate>
            </span>
          </dt>
          <dd>{dTransactionChannelEntity.chanelFreeText15}</dd>
          <dt>
            <span id="chanelFreeText16">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText16">Chanel Free Text 16</Translate>
            </span>
          </dt>
          <dd>{dTransactionChannelEntity.chanelFreeText16}</dd>
          <dt>
            <span id="chanelFreeText17">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText17">Chanel Free Text 17</Translate>
            </span>
          </dt>
          <dd>{dTransactionChannelEntity.chanelFreeText17}</dd>
          <dt>
            <span id="chanelFreeText18">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText18">Chanel Free Text 18</Translate>
            </span>
          </dt>
          <dd>{dTransactionChannelEntity.chanelFreeText18}</dd>
          <dt>
            <span id="chanelFreeText19">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText19">Chanel Free Text 19</Translate>
            </span>
          </dt>
          <dd>{dTransactionChannelEntity.chanelFreeText19}</dd>
          <dt>
            <span id="chanelFreeText20">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText20">Chanel Free Text 20</Translate>
            </span>
          </dt>
          <dd>{dTransactionChannelEntity.chanelFreeText20}</dd>
          <dt>
            <span id="chanelFreeText21">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText21">Chanel Free Text 21</Translate>
            </span>
          </dt>
          <dd>{dTransactionChannelEntity.chanelFreeText21}</dd>
          <dt>
            <span id="chanelFreeText22">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText22">Chanel Free Text 22</Translate>
            </span>
          </dt>
          <dd>{dTransactionChannelEntity.chanelFreeText22}</dd>
          <dt>
            <span id="chanelFreeText23">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText23">Chanel Free Text 23</Translate>
            </span>
          </dt>
          <dd>{dTransactionChannelEntity.chanelFreeText23}</dd>
          <dt>
            <span id="chanelFreeText24">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.chanelFreeText24">Chanel Free Text 24</Translate>
            </span>
          </dt>
          <dd>{dTransactionChannelEntity.chanelFreeText24}</dd>
          <dt>
            <span id="createdAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.createdAt">Created At</Translate>
            </span>
          </dt>
          <dd>
            {dTransactionChannelEntity.createdAt ? (
              <TextFormat value={dTransactionChannelEntity.createdAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="createdBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.createdBy">Created By</Translate>
            </span>
          </dt>
          <dd>{dTransactionChannelEntity.createdBy}</dd>
          <dt>
            <span id="updatedAt">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.updatedAt">Updated At</Translate>
            </span>
          </dt>
          <dd>
            {dTransactionChannelEntity.updatedAt ? (
              <TextFormat value={dTransactionChannelEntity.updatedAt} type="date" format={APP_DATE_FORMAT} />
            ) : null}
          </dd>
          <dt>
            <span id="updatedBy">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.updatedBy">Updated By</Translate>
            </span>
          </dt>
          <dd>{dTransactionChannelEntity.updatedBy}</dd>
          <dt>
            <span id="delflg">
              <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.delflg">Delflg</Translate>
            </span>
          </dt>
          <dd>{dTransactionChannelEntity.delflg ? 'true' : 'false'}</dd>
          <dt>
            <Translate contentKey="absaUgdtMicrosrvcGatewayApp.dTransactionChannel.dTransaction">D Transaction</Translate>
          </dt>
          <dd>{dTransactionChannelEntity.dTransaction ? dTransactionChannelEntity.dTransaction.id : ''}</dd>
        </dl>
        <Button tag={Link} to="/d-transaction-channel" replace color="info" data-cy="entityDetailsBackButton">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/d-transaction-channel/${dTransactionChannelEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

export default DTransactionChannelDetail;
