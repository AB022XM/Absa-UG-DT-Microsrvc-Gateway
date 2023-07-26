import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import Devices from './devices';
import DevicesDetail from './devices-detail';
import DevicesUpdate from './devices-update';
import DevicesDeleteDialog from './devices-delete-dialog';

const DevicesRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<Devices />} />
    <Route path="new" element={<DevicesUpdate />} />
    <Route path=":id">
      <Route index element={<DevicesDetail />} />
      <Route path="edit" element={<DevicesUpdate />} />
      <Route path="delete" element={<DevicesDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DevicesRoutes;
