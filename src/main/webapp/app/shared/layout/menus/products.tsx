import React from 'react';
import MenuItem from 'app/shared/layout/menus/menu-item';
import { DropdownItem } from 'reactstrap';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { Translate, translate } from 'react-jhipster';
import { NavLink as Link } from 'react-router-dom';
import { NavDropdown } from './menu-components';

export const ProductsMenu = props => (
  <NavDropdown
    icon="th-list"
    name="Products"
    id="entity-menu"
    style={{ maxHeight: '80vh', overflow: 'auto' }}
  >
    <MenuItem icon="asterisk" to="/product/product1">
      <span>Product1</span>
    </MenuItem>
    <MenuItem icon="asterisk" to="/product/product2">
      <span>Product2</span>
    </MenuItem>
  </NavDropdown>
);
