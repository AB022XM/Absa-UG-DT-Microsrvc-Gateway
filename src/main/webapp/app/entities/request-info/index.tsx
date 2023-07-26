import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import RequestInfo from './request-info';
import RequestInfoDetail from './request-info-detail';
import RequestInfoUpdate from './request-info-update';
import RequestInfoDeleteDialog from './request-info-delete-dialog';

const RequestInfoRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<RequestInfo />} />
    <Route path="new" element={<RequestInfoUpdate />} />
    <Route path=":id">
      <Route index element={<RequestInfoDetail />} />
      <Route path="edit" element={<RequestInfoUpdate />} />
      <Route path="delete" element={<RequestInfoDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default RequestInfoRoutes;
