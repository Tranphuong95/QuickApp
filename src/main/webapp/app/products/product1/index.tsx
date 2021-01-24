import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import Product1 from "./product1";
import Product1Detail from "./product1-detail";

const Routes = ({ match }) =>{
  window.console.log(match.url)
return(
  <>
    <Switch>
      <ErrorBoundaryRoute exact path={`${match.url}/:id`} component={Product1Detail} />
      <ErrorBoundaryRoute path={match.url} component={Product1} />
    </Switch>
  </>
);
}

export default Routes;
