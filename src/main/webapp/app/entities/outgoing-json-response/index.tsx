import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import OutgoingJSONResponse from './outgoing-json-response';
import OutgoingJSONResponseDetail from './outgoing-json-response-detail';
import OutgoingJSONResponseUpdate from './outgoing-json-response-update';
import OutgoingJSONResponseDeleteDialog from './outgoing-json-response-delete-dialog';

const OutgoingJSONResponseRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<OutgoingJSONResponse />} />
    <Route path="new" element={<OutgoingJSONResponseUpdate />} />
    <Route path=":id">
      <Route index element={<OutgoingJSONResponseDetail />} />
      <Route path="edit" element={<OutgoingJSONResponseUpdate />} />
      <Route path="delete" element={<OutgoingJSONResponseDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default OutgoingJSONResponseRoutes;
