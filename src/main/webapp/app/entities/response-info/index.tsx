import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import ResponseInfo from './response-info';
import ResponseInfoDetail from './response-info-detail';
import ResponseInfoUpdate from './response-info-update';
import ResponseInfoDeleteDialog from './response-info-delete-dialog';

const ResponseInfoRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<ResponseInfo />} />
    <Route path="new" element={<ResponseInfoUpdate />} />
    <Route path=":id">
      <Route index element={<ResponseInfoDetail />} />
      <Route path="edit" element={<ResponseInfoUpdate />} />
      <Route path="delete" element={<ResponseInfoDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default ResponseInfoRoutes;
