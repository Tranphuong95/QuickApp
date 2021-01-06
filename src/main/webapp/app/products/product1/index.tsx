import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Product1 from "app/products/product1/product1";
import Product1Detail from "app/products/product1/product1-detail";

const Routes = ({ match }) => (

  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={Product1Detail} />
      <ErrorBoundaryRoute path={match.url} component={Product1} />
    </Switch>
  </>
);

export default Routes;
