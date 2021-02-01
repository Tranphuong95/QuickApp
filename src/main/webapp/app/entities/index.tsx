import React from 'react';
import { Switch } from 'react-router-dom';

// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Message from './message';
import Editor from './editor';
import Productest from './productest';
import Device from './device';
import Testuuid from './testuuid';
import ProductId from './product-id';
import Imagex from './imagex';
import Testsdiachi from './testsdiachi';
/* jhipster-needle-add-route-import - JHipster will add routes here */
const Routes = ({ match }) => (
  <div>
    <Switch>
      {/* prettier-ignore */}
      <ErrorBoundaryRoute path={`${match.url}message`} component={Message} />
      <ErrorBoundaryRoute path={`${match.url}editor`} component={Editor} />
      <ErrorBoundaryRoute path={`${match.url}productest`} component={Productest} />
      <ErrorBoundaryRoute path={`${match.url}device`} component={Device} />
      <ErrorBoundaryRoute path={`${match.url}testuuid`} component={Testuuid} />
      <ErrorBoundaryRoute path={`${match.url}product-id`} component={ProductId} />
      <ErrorBoundaryRoute path={`${match.url}imagex`} component={Imagex} />
      <ErrorBoundaryRoute path={`${match.url}testsdiachi`} component={Testsdiachi} />
      {/* jhipster-needle-add-route-path - JHipster will add routes here */}
    </Switch>
  </div>
);

export default Routes;
