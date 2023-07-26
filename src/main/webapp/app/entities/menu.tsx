import React from 'react';
import { Translate } from 'react-jhipster';

import MenuItem from 'app/shared/layout/menus/menu-item';

const EntitiesMenu = () => {
  return (
    <>
      {/* prettier-ignore */}
      <MenuItem icon="asterisk" to="/amol-d-transactions">
        <Translate contentKey="global.menu.entities.amolDTransactions" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/customer">
        <Translate contentKey="global.menu.entities.customer" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/account-details">
        <Translate contentKey="global.menu.entities.accountDetails" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/d-transaction">
        <Translate contentKey="global.menu.entities.dTransaction" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/blocked-accounts">
        <Translate contentKey="global.menu.entities.blockedAccounts" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/d-transaction-details">
        <Translate contentKey="global.menu.entities.dTransactionDetails" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/bank">
        <Translate contentKey="global.menu.entities.bank" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/branch">
        <Translate contentKey="global.menu.entities.branch" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/biller">
        <Translate contentKey="global.menu.entities.biller" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/biller-account">
        <Translate contentKey="global.menu.entities.billerAccount" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/product-category">
        <Translate contentKey="global.menu.entities.productCategory" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/payment-items">
        <Translate contentKey="global.menu.entities.paymentItems" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/d-transaction-channel">
        <Translate contentKey="global.menu.entities.dTransactionChannel" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/request-info">
        <Translate contentKey="global.menu.entities.requestInfo" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/response-info">
        <Translate contentKey="global.menu.entities.responseInfo" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/custom-audit-history">
        <Translate contentKey="global.menu.entities.customAuditHistory" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/custom-audit">
        <Translate contentKey="global.menu.entities.customAudit" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/liquidation">
        <Translate contentKey="global.menu.entities.liquidation" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/d-transaction-summary">
        <Translate contentKey="global.menu.entities.dTransactionSummary" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/error-codes">
        <Translate contentKey="global.menu.entities.errorCodes" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/d-transaction-history">
        <Translate contentKey="global.menu.entities.dTransactionHistory" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/apps">
        <Translate contentKey="global.menu.entities.apps" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/app-config">
        <Translate contentKey="global.menu.entities.appConfig" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/payment-config">
        <Translate contentKey="global.menu.entities.paymentConfig" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/region">
        <Translate contentKey="global.menu.entities.region" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/country">
        <Translate contentKey="global.menu.entities.country" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/incoming-json-request">
        <Translate contentKey="global.menu.entities.incomingJsonRequest" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/outgoing-json-response">
        <Translate contentKey="global.menu.entities.outgoingJsonResponse" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/incoming-json-response">
        <Translate contentKey="global.menu.entities.incomingJsonResponse" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/default-settings">
        <Translate contentKey="global.menu.entities.defaultSettings" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/generic-configs">
        <Translate contentKey="global.menu.entities.genericConfigs" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/currency">
        <Translate contentKey="global.menu.entities.currency" />
      </MenuItem>
      <MenuItem icon="asterisk" to="/devices">
        <Translate contentKey="global.menu.entities.devices" />
      </MenuItem>
      {/* jhipster-needle-add-entity-to-menu - JHipster will add entities to the menu here */}
    </>
  );
};

export default EntitiesMenu;
