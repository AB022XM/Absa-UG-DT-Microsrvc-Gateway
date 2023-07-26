import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import { ReducersMapObject, combineReducers } from '@reduxjs/toolkit';

import getStore from 'app/config/store';

import entitiesReducers from './reducers';

import AmolDTransactions from './amol-d-transactions';
import Customer from './customer';
import AccountDetails from './account-details';
import DTransaction from './d-transaction';
import BlockedAccounts from './blocked-accounts';
import DTransactionDetails from './d-transaction-details';
import Bank from './bank';
import Branch from './branch';
import Biller from './biller';
import BillerAccount from './biller-account';
import ProductCategory from './product-category';
import PaymentItems from './payment-items';
import DTransactionChannel from './d-transaction-channel';
import RequestInfo from './request-info';
import ResponseInfo from './response-info';
import CustomAuditHistory from './custom-audit-history';
import CustomAudit from './custom-audit';
import Liquidation from './liquidation';
import DTransactionSummary from './d-transaction-summary';
import ErrorCodes from './error-codes';
import DTransactionHistory from './d-transaction-history';
import Apps from './apps';
import AppConfig from './app-config';
import PaymentConfig from './payment-config';
import Region from './region';
import Country from './country';
import IncomingJSONRequest from './incoming-json-request';
import OutgoingJSONResponse from './outgoing-json-response';
import IncomingJSONResponse from './incoming-json-response';
import DefaultSettings from './default-settings';
import GenericConfigs from './generic-configs';
import Currency from './currency';
import Devices from './devices';
/* jhipster-needle-add-route-import - JHipster will add routes here */

export default () => {
  const store = getStore();
  store.injectReducer('absaugdtmicrosrvcgateway', combineReducers(entitiesReducers as ReducersMapObject));
  return (
    <div>
      <ErrorBoundaryRoutes>
        {/* prettier-ignore */}
        <Route path="amol-d-transactions/*" element={<AmolDTransactions />} />
        <Route path="customer/*" element={<Customer />} />
        <Route path="account-details/*" element={<AccountDetails />} />
        <Route path="d-transaction/*" element={<DTransaction />} />
        <Route path="blocked-accounts/*" element={<BlockedAccounts />} />
        <Route path="d-transaction-details/*" element={<DTransactionDetails />} />
        <Route path="bank/*" element={<Bank />} />
        <Route path="branch/*" element={<Branch />} />
        <Route path="biller/*" element={<Biller />} />
        <Route path="biller-account/*" element={<BillerAccount />} />
        <Route path="product-category/*" element={<ProductCategory />} />
        <Route path="payment-items/*" element={<PaymentItems />} />
        <Route path="d-transaction-channel/*" element={<DTransactionChannel />} />
        <Route path="request-info/*" element={<RequestInfo />} />
        <Route path="response-info/*" element={<ResponseInfo />} />
        <Route path="custom-audit-history/*" element={<CustomAuditHistory />} />
        <Route path="custom-audit/*" element={<CustomAudit />} />
        <Route path="liquidation/*" element={<Liquidation />} />
        <Route path="d-transaction-summary/*" element={<DTransactionSummary />} />
        <Route path="error-codes/*" element={<ErrorCodes />} />
        <Route path="d-transaction-history/*" element={<DTransactionHistory />} />
        <Route path="apps/*" element={<Apps />} />
        <Route path="app-config/*" element={<AppConfig />} />
        <Route path="payment-config/*" element={<PaymentConfig />} />
        <Route path="region/*" element={<Region />} />
        <Route path="country/*" element={<Country />} />
        <Route path="incoming-json-request/*" element={<IncomingJSONRequest />} />
        <Route path="outgoing-json-response/*" element={<OutgoingJSONResponse />} />
        <Route path="incoming-json-response/*" element={<IncomingJSONResponse />} />
        <Route path="default-settings/*" element={<DefaultSettings />} />
        <Route path="generic-configs/*" element={<GenericConfigs />} />
        <Route path="currency/*" element={<Currency />} />
        <Route path="devices/*" element={<Devices />} />
        {/* jhipster-needle-add-route-path - JHipster will add routes here */}
      </ErrorBoundaryRoutes>
    </div>
  );
};
