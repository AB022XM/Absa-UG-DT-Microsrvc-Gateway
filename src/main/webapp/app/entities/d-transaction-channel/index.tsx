import React from 'react';
import { Route } from 'react-router-dom';

import ErrorBoundaryRoutes from 'app/shared/error/error-boundary-routes';

import DTransactionChannel from './d-transaction-channel';
import DTransactionChannelDetail from './d-transaction-channel-detail';
import DTransactionChannelUpdate from './d-transaction-channel-update';
import DTransactionChannelDeleteDialog from './d-transaction-channel-delete-dialog';

const DTransactionChannelRoutes = () => (
  <ErrorBoundaryRoutes>
    <Route index element={<DTransactionChannel />} />
    <Route path="new" element={<DTransactionChannelUpdate />} />
    <Route path=":id">
      <Route index element={<DTransactionChannelDetail />} />
      <Route path="edit" element={<DTransactionChannelUpdate />} />
      <Route path="delete" element={<DTransactionChannelDeleteDialog />} />
    </Route>
  </ErrorBoundaryRoutes>
);

export default DTransactionChannelRoutes;
