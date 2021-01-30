import React from 'react';
import { Switch } from 'react-router-dom';

import ErrorBoundaryRoute from 'app/shared/error/error-boundary-route';

import CheckoutCart from "app/checkout-cart/checkout-cart";

const Routes = ({ match }) =>{
  window.console.log(match.url)
  return(
    <>
      <Switch>
        <ErrorBoundaryRoute path={match.url} component={CheckoutCart} />
      </Switch>
    </>
  );
}

export default Routes;
