import React from 'react';
import { Switch } from 'react-router-dom';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';
import Product1 from "./product1/product1"
import Product2 from "./product2/product2";
import Entities from "app/entities";
import {AUTHORITIES} from "app/config/constants";
import PrivateRoute from "app/shared/auth/private-route";
/* jhipster-needle-add-route-import - JHipster will add routes here */

const Routes = ({match}) => (
  <div>
      <Switch>
        <ErrorBoundaryRoute path={`${match.url}/product1`} component={Product1} />
        <ErrorBoundaryRoute path={`${match.url}/product2`} component={Product2} />
      </Switch>
  </div>
);

export default Routes;