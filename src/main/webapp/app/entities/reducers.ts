import amolDTransactions from 'app/entities/amol-d-transactions/amol-d-transactions.reducer';
import customer from 'app/entities/customer/customer.reducer';
import accountDetails from 'app/entities/account-details/account-details.reducer';
import dTransaction from 'app/entities/d-transaction/d-transaction.reducer';
import blockedAccounts from 'app/entities/blocked-accounts/blocked-accounts.reducer';
import dTransactionDetails from 'app/entities/d-transaction-details/d-transaction-details.reducer';
import bank from 'app/entities/bank/bank.reducer';
import branch from 'app/entities/branch/branch.reducer';
import biller from 'app/entities/biller/biller.reducer';
import billerAccount from 'app/entities/biller-account/biller-account.reducer';
import productCategory from 'app/entities/product-category/product-category.reducer';
import paymentItems from 'app/entities/payment-items/payment-items.reducer';
import dTransactionChannel from 'app/entities/d-transaction-channel/d-transaction-channel.reducer';
import requestInfo from 'app/entities/request-info/request-info.reducer';
import responseInfo from 'app/entities/response-info/response-info.reducer';
import customAuditHistory from 'app/entities/custom-audit-history/custom-audit-history.reducer';
import customAudit from 'app/entities/custom-audit/custom-audit.reducer';
import liquidation from 'app/entities/liquidation/liquidation.reducer';
import dTransactionSummary from 'app/entities/d-transaction-summary/d-transaction-summary.reducer';
import errorCodes from 'app/entities/error-codes/error-codes.reducer';
import dTransactionHistory from 'app/entities/d-transaction-history/d-transaction-history.reducer';
import apps from 'app/entities/apps/apps.reducer';
import appConfig from 'app/entities/app-config/app-config.reducer';
import paymentConfig from 'app/entities/payment-config/payment-config.reducer';
import region from 'app/entities/region/region.reducer';
import country from 'app/entities/country/country.reducer';
import incomingJSONRequest from 'app/entities/incoming-json-request/incoming-json-request.reducer';
import outgoingJSONResponse from 'app/entities/outgoing-json-response/outgoing-json-response.reducer';
import incomingJSONResponse from 'app/entities/incoming-json-response/incoming-json-response.reducer';
import defaultSettings from 'app/entities/default-settings/default-settings.reducer';
import genericConfigs from 'app/entities/generic-configs/generic-configs.reducer';
import currency from 'app/entities/currency/currency.reducer';
import devices from 'app/entities/devices/devices.reducer';
/* jhipster-needle-add-reducer-import - JHipster will add reducer here */

const entitiesReducers = {
  amolDTransactions,
  customer,
  accountDetails,
  dTransaction,
  blockedAccounts,
  dTransactionDetails,
  bank,
  branch,
  biller,
  billerAccount,
  productCategory,
  paymentItems,
  dTransactionChannel,
  requestInfo,
  responseInfo,
  customAuditHistory,
  customAudit,
  liquidation,
  dTransactionSummary,
  errorCodes,
  dTransactionHistory,
  apps,
  appConfig,
  paymentConfig,
  region,
  country,
  incomingJSONRequest,
  outgoingJSONResponse,
  incomingJSONResponse,
  defaultSettings,
  genericConfigs,
  currency,
  devices,
  /* jhipster-needle-add-reducer-combine - JHipster will add reducer here */
};

export default entitiesReducers;
