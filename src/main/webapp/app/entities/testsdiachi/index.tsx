import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Testsdiachi from './testsdiachi';
import TestsdiachiDetail from './testsdiachi-detail';
import TestsdiachiUpdate from './testsdiachi-update';
import TestsdiachiDeleteDialog from './testsdiachi-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={TestsdiachiUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={TestsdiachiUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={TestsdiachiDetail} />
      <ErrorBoundaryRoute path={match.url} component={Testsdiachi} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={TestsdiachiDeleteDialog} />
  </>
);

export default Routes;
