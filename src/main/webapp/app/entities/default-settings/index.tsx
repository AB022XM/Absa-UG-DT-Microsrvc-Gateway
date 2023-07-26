import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import DefaultSettings from './default-settings';
import DefaultSettingsDetail from './default-settings-detail';
import DefaultSettingsUpdate from './default-settings-update';
import DefaultSettingsDeleteDialog from './default-settings-delete-dialog';

const DefaultSettingsRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<DefaultSettings />} />
    <Route path="new" element={<DefaultSettingsUpdate />} />
    <Route path=":id">
      <Route index element={<DefaultSettingsDetail />} />
      <Route path="edit" element={<DefaultSettingsUpdate />} />
      <Route path="delete" element={<DefaultSettingsDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DefaultSettingsRoutes;
