import React, {useState, useEffect}from 'react';
import { Translate } from 'react-jhipster';

import { NavItem, NavLink, NavbarBrand } from 'reactstrap';
import { NavLink as Link } from 'react-router-dom';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { AvFeedback, AvForm, AvGroup, AvInput, AvField ,AvBaseInput } from 'availity-reactstrap-validation';
import { Button} from 'reactstrap';
import {faSearch} from '@fortawesome/free-solid-svg-icons';
import axios from 'axios';
import qs from 'qs';
import PostFilterForm from'./../../../modules/post-filter-form/post-filter-form';

import appConfig from 'app/config/constants';

export const BrandIcon = props => (
  <div {...props} className="brand-icon">
    <img src="content/images/logo-jhipster.png" alt="Logo" />
  </div>
);

export const Brand = props => (
  <NavbarBrand tag={Link} to="/" className="brand-logo">
    <BrandIcon />
    <span className="brand-title">
      <Translate contentKey="global.title">Quicklyapp</Translate>
    </span>
    <span className="navbar-version">{appConfig.VERSION}</span>
  </NavbarBrand>
);

export const Home = props => (
  <NavItem>
    <NavLink tag={Link} to="/" className="d-flex align-items-center">
      <FontAwesomeIcon icon="home" />
      <span>
        <Translate contentKey="global.menu.home">Home</Translate>
      </span>
    </NavLink>
  </NavItem>
);
export const Products = props => (
  <NavItem>
    <NavLink tag={Link} to="/products" className="d-flex align-items-center">
      <span>
        Products
      </span>
    </NavLink>
  </NavItem>
);
export const FilterSearch = props =>{
  const [filters, setFilters]=useState({keyword:''});
  const [postFilter, setPostFilter]=useState([]);
  useEffect(()=>{
    window.console.log(filters.keyword.length)
    if(filters.keyword.length>0){
      axios({
        method: 'get',
        url: 'http://localhost:9000/api/product-search',
        params:filters,
        data: null
      }).then(res=> {
        window.console.log(res.data)
        setPostFilter(res.data)
        window.console.log(postFilter)
      });
    }
    return setPostFilter([])
  },[filters])
  function handleFiltersChange(newKeyword)
  {
    window.console.log(newKeyword)
    setFilters(newKeyword)
  }
  return (
    <NavItem>
      <PostFilterForm onSubmit={handleFiltersChange} postList={postFilter}/>
      {/*<AvForm>*/}
      {/*  <span>*/}
      {/*    */}
      {/*  </span>*/}
      {/*  /!*<Button type="submit"><FontAwesomeIcon icon={faSearch} size="1x"/></Button>*!/*/}
      {/*</AvForm>*/}
    </NavItem>
  );
}