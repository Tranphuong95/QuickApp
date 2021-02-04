import "./checkout.scss"
import React,{useState,useEffect} from 'react';
import { Base64 } from 'js-base64';
import {Redirect} from 'react-router-dom'

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
    const a = getCookie("_cart");
    const b = a?Base64.decode(a):null;
    const c= decodeURIComponent(b);
    const d: any =c;
    const _cartInformation=JSON.parse(d);
    setCartInformation(_cartInformation)
  },[])
  window.console.log( cartInformation)
  const Products=cartInformation?cartInformation.product: null;
  const totalProduct=()=>{
    let total=0;
    if(Products && Products.length>0){
      Products.map(item=>{
        total+=item.count;
      })
    }
    return total
  }
  if(cartInformation===null || cartInformation===undefined){
    return (<Redirect to="/"/>)
  }

  return (
    <div className="complete-order col-9">
      <div className="col-8">
        <header>
          <h3>ĐẶT HÀNG THÀNH CÔNG</h3>
        </header>
        <div className="infor-connect">
          <div className="notify">Cảm ơn Bạn {cartInformation.name} đã cho MẠNH HÙNG GROUP cơ hội được phục vụ. Nhân viên sẽ liên hệ lại với anh để xác nhận thông tin đặt hàng trong 5 phút.</div>

          <div className="title">THÔNG TIN ĐẶT HÀNG</div>
          <div className="infor">

            <div>Tên đầy đủ:<strong> {cartInformation.name}</strong></div>

            <div>Số điện thoại:<strong> {cartInformation.phoneNumber}</strong></div>

            <div>Email:<strong> {cartInformation.email}</strong></div>

            <div>Địa chỉ nhận hàng:<strong> {cartInformation.address + "-" +cartInformation.districtName + "-" + cartInformation.provinceName}</strong></div>

            <div>Ghi chú:<strong> {cartInformation.message}</strong></div>

            <div>Tổng tiền:<strong> {cartInformation.totalPrice?cartInformation.totalPrice.toLocaleString():null}đ</strong></div>
          </div>
          <div className="notify">Trước khi giao nhân viên sẽ gọi cho anh để xác nhận. Khi cần trợ giúp vui lòng gọi <span>0327247999</span> hoặc <span>0327247999</span> (7h30 - 22h)</div>
        </div>
      </div>
      <div className="product-customer col-4">
      <div className="title"><h5>Đơn hàng ({totalProduct()} sản phẩm)</h5></div>
      <div className="infor-content">
        <div className="content-product">
          {Products && Products.length>0?Products.map((item, index)=>{
            return(
              <div className="product" key={index*11011}>
                <div className="image col-4"><img src="/api/fileanhs/do_go_san_pham_1c&f02862f8-b054-44f2-9054-b2187d59b970.png"/></div>
                <div className="title-product col-4">{item.product.tensanpham}</div>
                <div className="price col-4">
                  <div className="tt-price">{Number(1500000).toLocaleString()}₫</div>
                  <div className="quantity">x{item.count}</div>
                  <div className="tt-price"><strong>{(item.count*1500000).toLocaleString()}₫</strong></div>
                </div>
              </div>
            )}):(<strong>Bạn chưa đăng ký mua sản phẩm nào</strong>)}
        </div>
        <div className="total-price">
          <div>Tổng tiền:<strong>{cartInformation.totalPrice?cartInformation.totalPrice.toLocaleString():null}đ</strong></div>
          <div>Cước vận chuyển</div>
          <div>Số tiền cần thanh toán:<strong>{cartInformation.totalPrice?cartInformation.totalPrice.toLocaleString():null}đ</strong></div>
        </div>
      </div>
    </div>
    </div>
  )
}


export default CompleteOrder;