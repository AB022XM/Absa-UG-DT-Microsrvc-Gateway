import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import PaymentConfig from './payment-config';
import PaymentConfigDetail from './payment-config-detail';
import PaymentConfigUpdate from './payment-config-update';
import PaymentConfigDeleteDialog from './payment-config-delete-dialog';

const PaymentConfigRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<PaymentConfig />} />
    <Route path="new" element={<PaymentConfigUpdate />} />
    <Route path=":id">
      <Route index element={<PaymentConfigDetail />} />
      <Route path="edit" element={<PaymentConfigUpdate />} />
      <Route path="delete" element={<PaymentConfigDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default PaymentConfigRoutes;
