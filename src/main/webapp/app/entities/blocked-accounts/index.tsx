import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import BlockedAccounts from './blocked-accounts';
import BlockedAccountsDetail from './blocked-accounts-detail';
import BlockedAccountsUpdate from './blocked-accounts-update';
import BlockedAccountsDeleteDialog from './blocked-accounts-delete-dialog';

const BlockedAccountsRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<BlockedAccounts />} />
    <Route path="new" element={<BlockedAccountsUpdate />} />
    <Route path=":id">
      <Route index element={<BlockedAccountsDetail />} />
      <Route path="edit" element={<BlockedAccountsUpdate />} />
      <Route path="delete" element={<BlockedAccountsDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default BlockedAccountsRoutes;
