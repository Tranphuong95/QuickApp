// import "./shop-cart.scss"
// import React, { useState, useEffect } from 'react';
// import { Button, Modal, ModalHeader, ModalBody, ModalFooter, Table, Label, Input } from 'reactstrap';
// import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
// import {faTrashAlt, faMinus, faPlus} from '@fortawesome/free-solid-svg-icons'
// // import { updateInCart} from "app/products/shopcart/actions/cart.action";
// import {IRootState} from "app/shared/reducers";
// import {connect} from "react-redux"
// import {removeFromCart, updateInCart} from "app/products/shopcart/actions/cart.action";
// import {fetchProducts} from "app/products/shopcart/actions/product.action";
// import {Cart} from "app/products/shopcart/cart";
// const CartContent = (props) => {
//
//
//   const {products, cartItems}=props;
//
//   const onUpdateInCart=(product, quantity)=>{
//     window.console.log(quantity)
//     if(quantity>0){
//       props.updateInCart(product, quantity)
//     }
//   }
//   const onRemoveFromCart=(product, quantity)=>{
//     if(quantity>0){
//       props.removeFromCart(product)
//     }
//   }
//   const onTotalPrice=()=>{
//     let total=0;
//     if(cartItems.cartItems && cartItems.cartItems.length>0){
//       cartItems.cartItems.map(item=>{
//         total+=item.count*1500000;
//       })
//     }
//     return total
//   }
//   const showCartItems=()=>{
//     let result=null;
//     if(cartItems.cartItems && cartItems.cartItems.length>0){
//       result= cartItems.cartItems.map((item, index)=>{
//         return(
//           <tr key={index*107}>
//             <td scope="row" className="infor-product">
//               <div>
//                 <img src="./../../../content/images/thung_go.png"/>
//                 <div className="cart-product-content">
//                   <div>{item.product.tensanpham}</div>
//                   <Button color="link" onClick={()=>onRemoveFromCart(item.product,item.count)}><FontAwesomeIcon icon={faTrashAlt} size="1x"/>Xóa sản phẩm</Button>
//                 </div>
//               </div>
//             </td>
//             <td>1500000 vnd</td>
//             <td>
//               <div className="input-group" key={index*1001}>
//                 <Label id="editorLabel" for="editor-editor">
//                 </Label>
//                 <Button disabled={item.count<=1}color="link"><FontAwesomeIcon icon={faMinus} onClick={()=>onUpdateInCart( item.product,item.count- 1)}/></Button>
//                 <span>{item.count}</span>
//                 <Button disabled={item.count>=100}color="link"><FontAwesomeIcon icon={faPlus} onClick={()=>onUpdateInCart(item.product,item.count + 1)}/></Button>
//               </div>
//             </td>
//             <td>{item.count*1500000} vnđ</td>
//           </tr>
//         )
//       })
//     }
//     return result
//   }
//
//
//   return (
//     <div>
//       <Table>
//         <thead>
//         <tr>
//           <th>Sản phẩm</th>
//           <th>Đơn giá</th>
//           <th>Số lượng</th>
//           <th>Thành tiền</th>
//         </tr>
//         </thead>
//         <tbody>
//         {showCartItems()}
//         {/*<tr>*/}
//         {/*  <td scope="row">*/}
//         {/*    <div>*/}
//         {/*      <img src="./../../../content/images/thung_go.png"/>*/}
//         {/*      <div className="cart-product-content">*/}
//         {/*        <div>THÙNG GỖ</div>*/}
//         {/*        <Button color="link"><FontAwesomeIcon icon={faTrashAlt} size="1x"/>Xóa sản phẩm</Button>*/}
//         {/*      </div>*/}
//         {/*    </div>*/}
//         {/*  </td>*/}
//         {/*  <td>(price, old price, sell)</td>*/}
//         {/*  <td>*/}
//         {/*    <div className="input-group">*/}
//         {/*      <Label id="editorLabel" for="editor-editor">*/}
//         {/*      </Label>*/}
//         {/*      <Button disabled={quantity<=1}color="link"><FontAwesomeIcon icon={faMinus} onClick={()=>updateQuantity(-1)}/></Button>*/}
//         {/*      <Input  className="btn-product-quantity" type="text" name="product-quantity" onChange={onChangeProductQuantity} value={quantity}/>*/}
//         {/*      <Button disabled={quantity>=100}color="link"><FontAwesomeIcon icon={faPlus} onClick={()=>updateQuantity(1)}/></Button>*/}
//         {/*    </div>*/}
//         {/*  </td>*/}
//         {/*  <td>2000000 vnđ</td>*/}
//         {/*</tr>*/}
//         </tbody>
//       </Table>
//       <div className="total-price">Tổng tiền: {onTotalPrice()} vnđ</div>
//     </div>
//   );
// }
// // export default CartContent;
// const mapDispatchToProps={
//   updateInCart, removeFromCart
// }
// type DispatchProps = typeof mapDispatchToProps;
// export default connect(null, mapDispatchToProps)(CartContent)


import "./shop-cart.scss"
import React, { useState, useEffect } from 'react';
import { Button, Modal, ModalHeader, ModalBody, ModalFooter, Table, Label, Input } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {faTrashAlt, faMinus, faPlus} from '@fortawesome/free-solid-svg-icons'
// import { updateInCart} from "app/products/shopcart/actions/cart.action";
import {IRootState} from "app/shared/reducers";
import {connect} from "react-redux"
import {removeFromCart, updateInCart} from "app/products/shopcart/actions/cart.action";
import {Link} from "react-router-dom";
import FroalaEditorView from 'react-froala-wysiwyg/FroalaEditorView';

const CartContent = (props) => {


  const {cartItems}=props;

  const onUpdateInCart=(product, quantity)=>{
    window.console.log(quantity)
    if(quantity>0){
      props.updateInCart(product, quantity)
    }
  }
  const onRemoveFromCart=(product, quantity)=>{
    if(quantity>0){
      props.removeFromCart(product)
    }
  }
  const onTotalPrice=()=>{
    let total=0;
    if(cartItems.cartItems && cartItems.cartItems.length>0){
      cartItems.cartItems.map(item=>{
        total+=item.count*1500000;
      })
    }
    return total
  }
  const showCartItems=()=>{
    let result=null;
    if(cartItems.cartItems && cartItems.cartItems.length>0){
      result= cartItems.cartItems.map((item, index)=>{
        return(
          <tr key={index*107}>
            <td scope="row" className="infor-product">
              <div>
                <Link to={`/product/product1/${item.product.id}`} target="_blank">
                  <img src="./../../../content/images/thung_go.png"/>
                </Link>
                  <div className="cart-product-content">
                    {/*<div>{item.product.tensanpham}</div>*/}
                    <Link to={`/product/product1/${item.product.id}`} target="_blank">
                      <FroalaEditorView
                        model={item.product.tensanpham}
                      />
                    </Link>
                    <Button color="link" onClick={()=>onRemoveFromCart(item.product,item.count)}><FontAwesomeIcon icon={faTrashAlt} size="1x"/>Xóa sản phẩm</Button>
                  </div>

              </div>
            </td>
            <td>1500000 vnd</td>
            <td>
              <div className="input-group" key={index*1001}>
                <Label id="editorLabel" for="editor-editor">
                </Label>
                <Button disabled={item.count<=1}color="link"><FontAwesomeIcon icon={faMinus} onClick={()=>onUpdateInCart( item.product,item.count- 1)}/></Button>
                <span>{item.count}</span>
                <Button disabled={item.count>=100}color="link"><FontAwesomeIcon icon={faPlus} onClick={()=>onUpdateInCart(item.product,item.count + 1)}/></Button>
              </div>
            </td>
            <td>{item.count*1500000} vnđ</td>
          </tr>
        )
      })
    }
    return result
  }


  return (
    <div>
      <Table>
        <thead>
        <tr>
          <th>Sản phẩm</th>
          <th>Đơn giá</th>
          <th>Số lượng</th>
          <th>Thành tiền</th>
        </tr>
        </thead>
        <tbody>
        {showCartItems()}
        {/*<tr>*/}
        {/*  <td scope="row">*/}
        {/*    <div>*/}
        {/*      <img src="./../../../content/images/thung_go.png"/>*/}
        {/*      <div className="cart-product-content">*/}
        {/*        <div>THÙNG GỖ</div>*/}
        {/*        <Button color="link"><FontAwesomeIcon icon={faTrashAlt} size="1x"/>Xóa sản phẩm</Button>*/}
        {/*      </div>*/}
        {/*    </div>*/}
        {/*  </td>*/}
        {/*  <td>(price, old price, sell)</td>*/}
        {/*  <td>*/}
        {/*    <div className="input-group">*/}
        {/*      <Label id="editorLabel" for="editor-editor">*/}
        {/*      </Label>*/}
        {/*      <Button disabled={quantity<=1}color="link"><FontAwesomeIcon icon={faMinus} onClick={()=>updateQuantity(-1)}/></Button>*/}
        {/*      <Input  className="btn-product-quantity" type="text" name="product-quantity" onChange={onChangeProductQuantity} value={quantity}/>*/}
        {/*      <Button disabled={quantity>=100}color="link"><FontAwesomeIcon icon={faPlus} onClick={()=>updateQuantity(1)}/></Button>*/}
        {/*    </div>*/}
        {/*  </td>*/}
        {/*  <td>2000000 vnđ</td>*/}
        {/*</tr>*/}
        </tbody>
      </Table>
      <div className="total-price">Tổng tiền: {onTotalPrice()} vnđ</div>
    </div>
  );
}
// export default CartContent;
const mapDispatchToProps={
  updateInCart, removeFromCart
}
type DispatchProps = typeof mapDispatchToProps;
export default connect(null, mapDispatchToProps)(CartContent)

