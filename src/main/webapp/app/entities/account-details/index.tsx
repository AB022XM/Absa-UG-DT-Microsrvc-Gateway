import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import AccountDetails from './account-details';
import AccountDetailsDetail from './account-details-detail';
import AccountDetailsUpdate from './account-details-update';
import AccountDetailsDeleteDialog from './account-details-delete-dialog';

const AccountDetailsRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<AccountDetails />} />
    <Route path="new" element={<AccountDetailsUpdate />} />
    <Route path=":id">
      <Route index element={<AccountDetailsDetail />} />
      <Route path="edit" element={<AccountDetailsUpdate />} />
      <Route path="delete" element={<AccountDetailsDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default AccountDetailsRoutes;
