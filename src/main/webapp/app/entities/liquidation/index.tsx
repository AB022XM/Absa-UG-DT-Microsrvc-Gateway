import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Liquidation from './liquidation';
import LiquidationDetail from './liquidation-detail';
import LiquidationUpdate from './liquidation-update';
import LiquidationDeleteDialog from './liquidation-delete-dialog';

const LiquidationRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Liquidation />} />
    <Route path="new" element={<LiquidationUpdate />} />
    <Route path=":id">
      <Route index element={<LiquidationDetail />} />
      <Route path="edit" element={<LiquidationUpdate />} />
      <Route path="delete" element={<LiquidationDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default LiquidationRoutes;
