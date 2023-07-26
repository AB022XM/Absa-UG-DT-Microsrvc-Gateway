import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import AmolDTransactions from './amol-d-transactions';
import AmolDTransactionsDetail from './amol-d-transactions-detail';
import AmolDTransactionsUpdate from './amol-d-transactions-update';
import AmolDTransactionsDeleteDialog from './amol-d-transactions-delete-dialog';

const AmolDTransactionsRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<AmolDTransactions />} />
    <Route path="new" element={<AmolDTransactionsUpdate />} />
    <Route path=":id">
      <Route index element={<AmolDTransactionsDetail />} />
      <Route path="edit" element={<AmolDTransactionsUpdate />} />
      <Route path="delete" element={<AmolDTransactionsDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default AmolDTransactionsRoutes;
