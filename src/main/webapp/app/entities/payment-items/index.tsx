import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import PaymentItems from './payment-items';
import PaymentItemsDetail from './payment-items-detail';
import PaymentItemsUpdate from './payment-items-update';
import PaymentItemsDeleteDialog from './payment-items-delete-dialog';

const PaymentItemsRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<PaymentItems />} />
    <Route path="new" element={<PaymentItemsUpdate />} />
    <Route path=":id">
      <Route index element={<PaymentItemsDetail />} />
      <Route path="edit" element={<PaymentItemsUpdate />} />
      <Route path="delete" element={<PaymentItemsDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default PaymentItemsRoutes;
