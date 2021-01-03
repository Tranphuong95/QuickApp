import React, { useState, useEffect } from 'react';
import {NavItem,NavLink} from 'reactstrap';
import {NavLink as Link, Redirect} from 'react-router-dom';
import { connect } from 'react-redux';

export type ProductProps=StateProps;
const Product2=(props:ProductProps)=>{
  window.console.log(props.isAuthenticated)
  return(
   <NavItem>
     <NavLink tag={Link} to={props.isAuthenticated===true?'/product/product1':'/login'}>Thêm vào giỏ hàng</NavLink>
   </NavItem>
  )
}
const mapStateToProps = storeState => ({
  account: storeState.authentication.account,
  isAuthenticated: storeState.authentication.isAuthenticated,
});
type StateProps = ReturnType<typeof mapStateToProps>;
export default connect(mapStateToProps)(Product2);