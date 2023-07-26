import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Biller from './biller';
import BillerDetail from './biller-detail';
import BillerUpdate from './biller-update';
import BillerDeleteDialog from './biller-delete-dialog';

const BillerRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Biller />} />
    <Route path="new" element={<BillerUpdate />} />
    <Route path=":id">
      <Route index element={<BillerDetail />} />
      <Route path="edit" element={<BillerUpdate />} />
      <Route path="delete" element={<BillerDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default BillerRoutes;
