import "./shop-cart.scss"
import React, { useState, useEffect } from 'react';
import { Button, Modal, ModalHeader, ModalBody, ModalFooter, Table, Label, Input } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import {faTrashAlt, faMinus, faPlus} from '@fortawesome/free-solid-svg-icons'
import {addToCart} from "app/products/shopcart/actions/cart.action";
import {IRootState} from "app/shared/reducers";
import {connect} from "react-redux"
const CartContent = (props) => {
  const [quantity, setQuantity] = useState(1);
  useEffect(()=>{
    if(quantity>=100){
      setQuantity(100)
    }
    if(!quantity){
      setQuantity(1)
    }
  })
  const onChangeProductQuantity=(value)=>{
    setQuantity(value)
  }
  const onUpdateQuantity=(value)=>{
    if(quantity>=1 && quantity<=100){
      setQuantity(Number(quantity)+Number(value))
    }
  }

  //
  // const {cartItems}=props;
  // window.console.log(cartItems)
  //
  // const showCartItems=()=>{
  //   let result=null;
  //   if(cartItems && cartItems.length>0){
  //     result= cartItems.map((item, index)=>{
  //       return(
  //         <tr key={index*107}>
  //           <td scope="row">
  //             <div>
  //               <img src="./../../../content/images/thung_go.png"/>
  //               <div className="cart-product-content">
  //                 <div>{item.tensanpham}</div>
  //                 <Button color="link"><FontAwesomeIcon icon={faTrashAlt} size="1x"/>Xóa sản phẩm</Button>
  //               </div>
  //             </div>
  //           </td>
  //           <td>(price, old price, sell)</td>
  //           <td>
  //             <div className="input-group">
  //               <Label id="editorLabel" for="editor-editor">
  //               </Label>
  //               <Button disabled={item.count<=1}color="link"><FontAwesomeIcon icon={faMinus} onClick={()=>addToCart( {id:item.id, tensanpham:item.tensanpham},item.count-1)}/></Button>
  //               {/*<Input  className="btn-product-quantity" type="text" name="product-quantity" value={item.count} onChange={onChangeProductQuantity}/>*/}
  //               <span>{item.count}</span>
  //               <Button disabled={item.count>=100}color="link"><FontAwesomeIcon icon={faPlus} onClick={()=>addToCart({id: item.id, tensanpham: item.tensanpham}, item.count+1)}/></Button>
  //             </div>
  //           </td>
  //           <td>2000000 vnđ</td>
  //         </tr>
  //       )
  //     })
  //   }
  //   return result
  // }
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
        {/*{showCartItems()}*/}
        <tr>
          <td scope="row">
            <div>
              <img src="./../../../content/images/thung_go.png"/>
              <div className="cart-product-content">
                <div>THÙNG GỖ</div>
                <Button color="link"><FontAwesomeIcon icon={faTrashAlt} size="1x"/>Xóa sản phẩm</Button>
              </div>
            </div>
          </td>
          <td>(price, old price, sell)</td>
          <td>
            <div className="input-group">
              <Label id="editorLabel" for="editor-editor">
              </Label>
              <Button disabled={quantity<=1}color="link"><FontAwesomeIcon icon={faMinus} onClick={()=>onUpdateQuantity(-1)}/></Button>
              <Input  className="btn-product-quantity" type="text" name="product-quantity" onChange={onChangeProductQuantity} value={quantity}/>
              <Button disabled={quantity>=100}color="link"><FontAwesomeIcon icon={faPlus} onClick={()=>onUpdateQuantity(1)}/></Button>
            </div>
          </td>
          <td>2000000 vnđ</td>
        </tr>
        </tbody>
      </Table>
      <div className="total-price">Tổng tiền: 2000000 vnđ</div>
    </div>
  );
}
export default CartContent;
