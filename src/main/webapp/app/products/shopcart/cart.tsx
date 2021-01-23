// import React from 'react';
// import PropTypes from 'prop-types';
// import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// import { Button, Row, Col, Label } from 'reactstrap';
//
// function Cart(props)
// {
//   return (
//     <div>
//       <AvForm inline>
//         <AvGroup inline>
//           <Label id="editorLabel" for="editor-editor">
//             <span>Số lượng</span>
//           </Label>
//           <AvField id="product-product-cart" type="number" name="product-cart" min={1} max={10}/>
//         </AvGroup>
//         <Button>Đăng Ký mua hàng</Button>
//       </AvForm>
//     </div>
//   );
// }
//
// export default Cart;

import "./shop-cart.scss"
import React, { useState, useEffect } from 'react';
import { Button, Modal, ModalHeader, ModalBody, ModalFooter, Table, Label, Input } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {faTrashAlt, faPlus, faMinus} from '@fortawesome/free-solid-svg-icons';
import CartContent from "app/products/shopcart/cart-content";
import {connect} from "react-redux";
import {IRootState} from "app/shared/reducers";
import {RouteComponentProps } from 'react-router-dom';
import { useDispatch, useSelector } from 'react-redux';
import {addToCart} from "app/products/shopcart/actions/cart.action";
import {fetchProducts} from "app/products/shopcart/actions/product.action";

// export interface ICartShopProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {};
export const Cart = (props) => {
  const dispatch=useDispatch()
  const [quantity, setQuantity] = useState(1);
  useEffect(()=>{
    if(quantity>=100){
      setQuantity(100)
    }
    if(!quantity){
      setQuantity(1)
    }
  })
  useEffect(()=>{
    dispatch(props.fetchProducts)
  },[dispatch])
  const onChangeProductQuantity=(event)=>{
    setQuantity(event.target.value)
  }
  // const onUpQuantity=()=>{
  //   if(quantity<100){setQuantity(Number(quantity)+Number(1))}
  // }
  // const onDownQuantity=()=>{
  //   if(quantity>1) {setQuantity(Number(quantity)-Number(1))}
  // }
  const onChangeQuantity=(value)=>{
    setQuantity(Number(quantity)+Number(value))
  }
  const [modal, setModal] = useState(false);

  const toggle = () => setModal(!modal);

  const {cartItems, products}=props;

  const productShops:any=products;

  window.console.log(productShops.products)
  window.console.log(cartItems)
  window. console.log(JSON.parse(localStorage.getItem("cartItems")))

  const onAddToCart=(product)=>{
    toggle();
    window.console.log(quantity)
    props.addToCart(product, quantity)
  }

  const showAddToCart=()=>{
    let result=null;
    if(productShops && productShops.products.length>0){
      result=productShops.products.map((product, index)=>{
        return(
          <div key={index*101}>
            <div>{product.tensanpham}</div>
            <AvForm inline key={index*101}>
              <AvGroup inline>
                <Label id="editorLabel" for="editor-editor">
                  <span>Số lượng</span>
                </Label>
                <Button disabled={quantity<=1}color="link"><FontAwesomeIcon icon={faMinus} onClick={()=>onChangeQuantity(-1)}/></Button>
                <AvField id={`product-quantity${index}`} className="btn-product-quantity" type="text" name="quantity" onChange={onChangeProductQuantity} value={quantity}/>
                <Button disabled={quantity>=100}color="link"><FontAwesomeIcon icon={faPlus} onClick={()=>onChangeQuantity(1)}/></Button>
              </AvGroup>
              {/*<Button>Đăng Ký mua hàng</Button>*/}
              <Button className="add-to-cart" type="submit" color="danger"onClick={()=>onAddToCart(product)} >ĐĂNG KÝ MUA HÀNG</Button>
            </AvForm>
          </div>
        )
      })
    }
    return result;
    window.console.log(result)
  }
  return (
    <div className="shop-modal-content">
      {/*<AvForm inline>*/}
      {/*  <AvGroup inline>*/}
      {/*    <Label id="editorLabel" for="editor-editor">*/}
      {/*      <span>Số lượng</span>*/}
      {/*    </Label>*/}
      {/*    <Button disabled={quantity<=1}color="link"><FontAwesomeIcon icon={faMinus} onClick={()=>onChangeQuantity(-1)}/></Button>*/}
      {/*    <AvField id="cart-product-quantity" className="btn-product-quantity" type="text" name="quantity" onChange={onChangeProductQuantity} value={quantity}/>*/}
      {/*    <Button disabled={quantity>=100}color="link"><FontAwesomeIcon icon={faPlus} onClick={()=>onChangeQuantity(1)}/></Button>*/}
      {/*  </AvGroup>*/}
      {/*  /!*<Button>Đăng Ký mua hàng</Button>*!/*/}
      {/*  <Button className="add-to-cart" color="danger"onClick={()=>onAddToCart(quantity)} >ĐĂNG KÝ MUA HÀNG</Button>*/}
      {/*</AvForm>*/}
      {showAddToCart()}
      {/*<Modal isOpen={modal} toggle={toggle} className={className}>*/}
      <Modal isOpen={modal}> {/*todo change 20/1*/}
        {/*<Button color="danger" onClick={toggle}>ĐĂNG KÝ MUA HÀNG</Button>*/}
        <ModalHeader toggle={toggle}>Giỏ hàng của bạn ( sản phẩm)</ModalHeader>

        <ModalBody>
          <CartContent/>
        </ModalBody>
        <ModalFooter>
          <Button color="warning" onClick={toggle}>Tiếp tục mua hàng</Button>{' '}
          <Button color="primary" onClick={toggle}>Tiến hành thanh toán</Button>
        </ModalFooter>
      </Modal>
    </div>
  );
}
const mapStateToProps=({cartShop,productShop}:IRootState)=>{
  return{
    cartItems: cartShop,
    products: productShop
  }
}
const mapDispatchToProps={
    addToCart,fetchProducts
}
type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;
export default connect(mapStateToProps, mapDispatchToProps)(Cart);