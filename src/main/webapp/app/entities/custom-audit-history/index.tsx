import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import CustomAuditHistory from './custom-audit-history';
import CustomAuditHistoryDetail from './custom-audit-history-detail';
import CustomAuditHistoryUpdate from './custom-audit-history-update';
import CustomAuditHistoryDeleteDialog from './custom-audit-history-delete-dialog';

const CustomAuditHistoryRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<CustomAuditHistory />} />
    <Route path="new" element={<CustomAuditHistoryUpdate />} />
    <Route path=":id">
      <Route index element={<CustomAuditHistoryDetail />} />
      <Route path="edit" element={<CustomAuditHistoryUpdate />} />
      <Route path="delete" element={<CustomAuditHistoryDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default CustomAuditHistoryRoutes;
