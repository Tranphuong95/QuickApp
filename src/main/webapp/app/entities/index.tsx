import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Message from './message';
import Editor from './editor';
import Productest from './productest';
import Device from './device';
/* jhipster-needle-add-route-import - JHipster will add routes here */
const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}message`} component={Message} />
      <ErrorBoundaryRoute path={`${match.url}editor`} component={Editor} />
      <ErrorBoundaryRoute path={`${match.url}productest`} component={Productest} />
      <ErrorBoundaryRoute path={`${match.url}device`} component={Device} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
