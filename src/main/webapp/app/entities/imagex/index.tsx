import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Imagex from './imagex';
import ImagexDetail from './imagex-detail';
import ImagexUpdate from './imagex-update';
import ImagexDeleteDialog from './imagex-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ImagexUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ImagexUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ImagexDetail} />
      <ErrorBoundaryRoute path={match.url} component={Imagex} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ImagexDeleteDialog} />
  </>
);

export default Routes;
