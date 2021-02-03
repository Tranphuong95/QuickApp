import "./checkout.scss"
import React,{useState,useEffect} from 'react';
import {ProductInfor} from "app/checkout-cart/checkout-cart";
import { Base64 } from 'js-base64';

const  CompleteOrder=(props)=> {
  const [cartInformation, setCartInformation]: any=useState('');

  function getCookie(name) {
    const value = "; " + document.cookie;
    const parts = value.split("; " + name + "=");

    if (parts.length === 2) {
      return parts.pop().split(";").shift();
    }
  }

  useEffect(()=>{
    const _cart=Base64.decode(getCookie("_cart"));
    const _cartInfor= decodeURIComponent(_cart)
    const _cartInformation=(JSON.parse(_cartInfor))
    setCartInformation(_cartInformation)
  },[])
  window.console.log( cartInformation.name)
  return (
    <div className="complete-order col-9">
      <div className="col-8">
        <header>
          <h3>ĐẶT HÀNG THÀNH CÔNG</h3>
        </header>
        <div className="infor-connect">
          <div className="notify">Cảm ơn Bạn ưeqeqe đã cho MẠNH HÙNG GROUP cơ hội được phục vụ. Nhân viên sẽ liên hệ lại với anh để xác nhận thông tin đặt hàng trong 5 phút.</div>

          <div className="title">THÔNG TIN ĐẶT HÀNG</div>
          <div className="infor">

            <div>Tên đầy đủ:<strong> {cartInformation.name}</strong></div>

            <div>Số điện thoại:<strong> {cartInformation.phoneNumber}</strong></div>

            <div>Email:<strong> {cartInformation.email}</strong></div>

            <div>Địa chỉ nhận hàng:<strong> {cartInformation.address + " " +cartInformation.districtName + " " + cartInformation.provinceName}</strong></div>

            <div>Ghi chú:<strong> {cartInformation.message}</strong></div>

            <div>Tổng tiền:<strong> {cartInformation.totalPrice?cartInformation.totalPrice.toLocaleString():null}đ</strong></div>
          </div>
          <div className="notify">Trước khi giao nhân viên sẽ gọi cho anh để xác nhận. Khi cần trợ giúp vui lòng gọi <span>0327247999</span> hoặc <span>0327247999</span> (7h30 - 22h)</div>
        </div>
      </div>
      <ProductInfor/>
    </div>
  )
}


export default CompleteOrder;