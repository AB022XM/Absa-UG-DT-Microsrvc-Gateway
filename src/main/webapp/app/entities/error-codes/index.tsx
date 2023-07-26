import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import ErrorCodes from './error-codes';
import ErrorCodesDetail from './error-codes-detail';
import ErrorCodesUpdate from './error-codes-update';
import ErrorCodesDeleteDialog from './error-codes-delete-dialog';

const ErrorCodesRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<ErrorCodes />} />
    <Route path="new" element={<ErrorCodesUpdate />} />
    <Route path=":id">
      <Route index element={<ErrorCodesDetail />} />
      <Route path="edit" element={<ErrorCodesUpdate />} />
      <Route path="delete" element={<ErrorCodesDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default ErrorCodesRoutes;
