import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Productest from './productest';
import ProductestDetail from './productest-detail';
import ProductestUpdate from './productest-update';
import ProductestDeleteDialog from './productest-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ProductestUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ProductestUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ProductestDetail} />
      <ErrorBoundaryRoute path={match.url} component={Productest} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ProductestDeleteDialog} />
  </>
);

export default Routes;
