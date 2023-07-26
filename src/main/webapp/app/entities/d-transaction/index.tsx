import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import DTransaction from './d-transaction';
import DTransactionDetail from './d-transaction-detail';
import DTransactionUpdate from './d-transaction-update';
import DTransactionDeleteDialog from './d-transaction-delete-dialog';

const DTransactionRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<DTransaction />} />
    <Route path="new" element={<DTransactionUpdate />} />
    <Route path=":id">
      <Route index element={<DTransactionDetail />} />
      <Route path="edit" element={<DTransactionUpdate />} />
      <Route path="delete" element={<DTransactionDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DTransactionRoutes;
