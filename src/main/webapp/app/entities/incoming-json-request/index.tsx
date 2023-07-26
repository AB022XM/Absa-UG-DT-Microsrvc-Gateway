import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import IncomingJSONRequest from './incoming-json-request';
import IncomingJSONRequestDetail from './incoming-json-request-detail';
import IncomingJSONRequestUpdate from './incoming-json-request-update';
import IncomingJSONRequestDeleteDialog from './incoming-json-request-delete-dialog';

const IncomingJSONRequestRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<IncomingJSONRequest />} />
    <Route path="new" element={<IncomingJSONRequestUpdate />} />
    <Route path=":id">
      <Route index element={<IncomingJSONRequestDetail />} />
      <Route path="edit" element={<IncomingJSONRequestUpdate />} />
      <Route path="delete" element={<IncomingJSONRequestDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default IncomingJSONRequestRoutes;
