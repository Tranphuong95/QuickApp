import "./checkout.scss"
import React from 'react';
import {ProductInfor} from "app/checkout-cart/checkout-cart";

const  CompleteOrder=(props)=>
{
  return (
    <div className="complete-order col-9">
      <div className="col-8">
        <header>
          <h3>ĐẶT HÀNG THÀNH CÔNG</h3>
        </header>
        <div className="infor-connect">
          Cảm ơn Bạn ưeqeqe đã cho MẠNH HÙNG GROUP cơ hội được phục vụ. Nhân viên sẽ liên hệ lại với anh để xác nhận thông tin đặt hàng trong 5 phút.

          <div className="title">THÔNG TIN ĐẶT HÀNG</div>
          <div className="infor">

            <div><strong>Tên đầy đủ:</strong></div>

            <div><strong>Số điện thoại:</strong></div>

            <div><strong>Email:</strong></div>

            <div><strong>Địa chỉ nhận hàng:</strong></div>

            <div><strong>Ghi chú:</strong></div>

            <div><strong>Tổng tiền:</strong></div>
          </div>
        </div>
      </div>
      <ProductInfor/>

    </div>
  );
}

export default CompleteOrder;