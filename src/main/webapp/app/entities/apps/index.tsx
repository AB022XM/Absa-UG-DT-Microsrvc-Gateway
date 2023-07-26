import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Apps from './apps';
import AppsDetail from './apps-detail';
import AppsUpdate from './apps-update';
import AppsDeleteDialog from './apps-delete-dialog';

const AppsRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Apps />} />
    <Route path="new" element={<AppsUpdate />} />
    <Route path=":id">
      <Route index element={<AppsDetail />} />
      <Route path="edit" element={<AppsUpdate />} />
      <Route path="delete" element={<AppsDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default AppsRoutes;
