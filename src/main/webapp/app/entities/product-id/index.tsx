import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import ProductId from './product-id';
import ProductIdDetail from './product-id-detail';
import ProductIdUpdate from './product-id-update';
import ProductIdDeleteDialog from './product-id-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={ProductIdUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={ProductIdUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={ProductIdDetail} />
      <ErrorBoundaryRoute path={match.url} component={ProductId} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={ProductIdDeleteDialog} />
  </>
);

export default Routes;
