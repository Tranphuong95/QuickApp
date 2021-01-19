import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Testuuid from './testuuid';
import TestuuidDetail from './testuuid-detail';
import TestuuidUpdate from './testuuid-update';
import TestuuidDeleteDialog from './testuuid-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TestuuidUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TestuuidUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TestuuidDetail} />
      <ErrorBoundaryRoute path={match.url} component={Testuuid} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={TestuuidDeleteDialog} />
  </>
);

export default Routes;
