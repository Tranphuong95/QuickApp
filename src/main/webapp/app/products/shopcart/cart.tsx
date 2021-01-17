import React from 'react';
import PropTypes from 'prop-types';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Button, Row, Col, Label } from 'reactstrap';

function Cart(props)
{
  return (
    <div>
      <AvForm inline>
        <AvGroup inline>
          <Label id="editorLabel" for="editor-editor">
            <span>Số lượng</span>
          </Label>
          <AvField id="product-product-cart" type="number" name="product-cart" min={1} max={10}/>
        </AvGroup>
        <Button>Đăng Ký mua hàng</Button>
      </AvForm>
    </div>
  );
}

export default Cart;