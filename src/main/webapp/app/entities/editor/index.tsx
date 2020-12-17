import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Editor from './editor';
import EditorDetail from './editor-detail';
import EditorUpdate from './editor-update';
import EditorDeleteDialog from './editor-delete-dialog';

const Routes = ({ match }) => (
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/new`} component={EditorUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id/edit`} component={EditorUpdate} />
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={EditorDetail} />
      <ErrorBoundaryRoute path={match.url} component={Editor} />
    </Switch>
    <ErrorBoundaryRoute exact path={`${match.url}/:id/delete`} component={EditorDeleteDialog} />
  </>
);

export default Routes;
