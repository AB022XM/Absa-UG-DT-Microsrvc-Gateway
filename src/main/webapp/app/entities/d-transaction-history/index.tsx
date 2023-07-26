import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import DTransactionHistory from './d-transaction-history';
import DTransactionHistoryDetail from './d-transaction-history-detail';
import DTransactionHistoryUpdate from './d-transaction-history-update';
import DTransactionHistoryDeleteDialog from './d-transaction-history-delete-dialog';

const DTransactionHistoryRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<DTransactionHistory />} />
    <Route path="new" element={<DTransactionHistoryUpdate />} />
    <Route path=":id">
      <Route index element={<DTransactionHistoryDetail />} />
      <Route path="edit" element={<DTransactionHistoryUpdate />} />
      <Route path="delete" element={<DTransactionHistoryDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DTransactionHistoryRoutes;
