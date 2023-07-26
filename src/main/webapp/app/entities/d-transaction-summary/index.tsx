import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import DTransactionSummary from './d-transaction-summary';
import DTransactionSummaryDetail from './d-transaction-summary-detail';
import DTransactionSummaryUpdate from './d-transaction-summary-update';
import DTransactionSummaryDeleteDialog from './d-transaction-summary-delete-dialog';

const DTransactionSummaryRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<DTransactionSummary />} />
    <Route path="new" element={<DTransactionSummaryUpdate />} />
    <Route path=":id">
      <Route index element={<DTransactionSummaryDetail />} />
      <Route path="edit" element={<DTransactionSummaryUpdate />} />
      <Route path="delete" element={<DTransactionSummaryDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DTransactionSummaryRoutes;
