import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import GenericConfigs from './generic-configs';
import GenericConfigsDetail from './generic-configs-detail';
import GenericConfigsUpdate from './generic-configs-update';
import GenericConfigsDeleteDialog from './generic-configs-delete-dialog';

const GenericConfigsRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<GenericConfigs />} />
    <Route path="new" element={<GenericConfigsUpdate />} />
    <Route path=":id">
      <Route index element={<GenericConfigsDetail />} />
      <Route path="edit" element={<GenericConfigsUpdate />} />
      <Route path="delete" element={<GenericConfigsDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default GenericConfigsRoutes;
