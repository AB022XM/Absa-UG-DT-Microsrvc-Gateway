import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import IncomingJSONResponse from './incoming-json-response';
import IncomingJSONResponseDetail from './incoming-json-response-detail';
import IncomingJSONResponseUpdate from './incoming-json-response-update';
import IncomingJSONResponseDeleteDialog from './incoming-json-response-delete-dialog';

const IncomingJSONResponseRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<IncomingJSONResponse />} />
    <Route path="new" element={<IncomingJSONResponseUpdate />} />
    <Route path=":id">
      <Route index element={<IncomingJSONResponseDetail />} />
      <Route path="edit" element={<IncomingJSONResponseUpdate />} />
      <Route path="delete" element={<IncomingJSONResponseDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default IncomingJSONResponseRoutes;
