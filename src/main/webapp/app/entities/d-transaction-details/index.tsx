import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import DTransactionDetails from './d-transaction-details';
import DTransactionDetailsDetail from './d-transaction-details-detail';
import DTransactionDetailsUpdate from './d-transaction-details-update';
import DTransactionDetailsDeleteDialog from './d-transaction-details-delete-dialog';

const DTransactionDetailsRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<DTransactionDetails />} />
    <Route path="new" element={<DTransactionDetailsUpdate />} />
    <Route path=":id">
      <Route index element={<DTransactionDetailsDetail />} />
      <Route path="edit" element={<DTransactionDetailsUpdate />} />
      <Route path="delete" element={<DTransactionDetailsDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DTransactionDetailsRoutes;
