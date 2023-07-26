import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import CustomAudit from './custom-audit';
import CustomAuditDetail from './custom-audit-detail';
import CustomAuditUpdate from './custom-audit-update';
import CustomAuditDeleteDialog from './custom-audit-delete-dialog';

const CustomAuditRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<CustomAudit />} />
    <Route path="new" element={<CustomAuditUpdate />} />
    <Route path=":id">
      <Route index element={<CustomAuditDetail />} />
      <Route path="edit" element={<CustomAuditUpdate />} />
      <Route path="delete" element={<CustomAuditDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default CustomAuditRoutes;
