import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import BillerAccount from './biller-account';
import BillerAccountDetail from './biller-account-detail';
import BillerAccountUpdate from './biller-account-update';
import BillerAccountDeleteDialog from './biller-account-delete-dialog';

const BillerAccountRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<BillerAccount />} />
    <Route path="new" element={<BillerAccountUpdate />} />
    <Route path=":id">
      <Route index element={<BillerAccountDetail />} />
      <Route path="edit" element={<BillerAccountUpdate />} />
      <Route path="delete" element={<BillerAccountDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default BillerAccountRoutes;
