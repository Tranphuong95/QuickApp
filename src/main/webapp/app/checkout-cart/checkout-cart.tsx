import "./checkout.scss"
import React,{useState, useEffect}from 'react';
import {AvForm} from 'availity-reactstrap-validation'
import {Form, Input, Button, FormGroup, Label,FormFeedback, FormText} from 'reactstrap';
import axios from 'axios';

const CheckoutCart=()=>
{
  const [testsdiachiList, setTestsdiachiList]=useState([]);
  const [testsTenQuan, setTestsTenQuan]=useState([]);
  useEffect(() => {
    axios({
      method: "get",
      url: "https://online-gateway.ghn.vn/shiip/public-api/master-data/province",
      headers:{Token:"e1266833-639e-11eb-86b9-8a61086fe5fd"}
    })
      .then(response=>setTestsdiachiList(response.data.data))
  }, []);
  // useEffect(()=>{
  //   axios({
  //     method: "post",
  //     url: " https://online-gateway.ghn.vn/shiip/public-api/master-data/district",
  //     headers:{
  //       'X-Requested-With': 'XMLHttpRequest',
  //       Token:"e1266833-639e-11eb-86b9-8a61086fe5fd"
  //     },
  //     data:{
  //       "province_id": 201
  //     }
  //   })
  //     .then(response=>setTestsTenQuan(response.data.data))
  // },[])


  const FindProvinceId=(event)=>{
    window.console.log(event.target.value)
    axios({
      method: "post",
      url: " https://online-gateway.ghn.vn/shiip/public-api/master-data/district",
      headers:{
        'X-Requested-With': 'XMLHttpRequest',
        Token:"e1266833-639e-11eb-86b9-8a61086fe5fd"
      },
      data:{
        "province_id":JSON.parse(event.target.value)
      }
    })
      .then(response=>setTestsTenQuan(response.data.data))
  }

  window.console.log(testsdiachiList)
  window.console.log(testsTenQuan)
  const CartItems = JSON.parse(localStorage.getItem("cartItems"));
  const totalProduct=()=>{
    let total=0;
    if(CartItems && CartItems.length>0){
      CartItems.map(item=>{
        total+=item.count;
      })
    }
    return total
  }
  const totalPrice=()=>{
    let total=0;
    if(CartItems && CartItems.length>0){
      CartItems.map(item=>{
        total+=item.count*1500000;
      })
    }
    return total
  }
  window.console.log(CartItems.length)
  return (
      <section className="checkout-custom col-9">
          <Form>
              <div className="checkout-information">
                  <div className="infor-customer col-8">
                    <div className="title"><h5>Thông tin thanh toán</h5></div>
                    <div className="infor-content">
                      <FormGroup className="checkout-group">
                        <Label className="col-3 left">Họ tên</Label>
                        <Input className="col-9 right" type="text" placeholder="Ví dụ: Nguyễn Văn A" required></Input>
                      </FormGroup>
                      <FormGroup className="checkout-group">
                        <Label className="col-3 left">Điện thoại</Label>
                        <Input className="col-9 right" type="text" placeholder="Ví dụ: 0387654321" required></Input>
                      </FormGroup>
                      <FormGroup className="checkout-group">
                        <Label className="col-3 left">
                          Email
                          <FormText>(Không bắt buộc)</FormText>
                        </Label>
                        <Input className="col-9 right" type="email"  placeholder="supportxyz@gmail.com"></Input>
                      </FormGroup>
                      <FormGroup className="checkout-group">
                        <Label className="col-3 left">Tỉnh/Thành phố</Label>
                        {/*<Input  type="select" name="cityName" value={cityName} onChange={onChangeCity}>*/}
                        <Input  className="col-9 right" type="select" name="cityName"  onChange={FindProvinceId} required>
                          <option value="">--Chọn thành phố--</option>
                          {testsdiachiList && testsdiachiList.length>0?testsdiachiList.map((item, index)=>{
                            return(
                              <option key={index*111} value={item.ProvinceID} >{item.ProvinceName}</option>
                            )
                          }): ('')}
                        </Input>
                      </FormGroup>
                      <FormGroup className="checkout-group">
                        <Label className="col-3 left">Quận/huyện</Label>
                        {/*<Input  type="select" name="districName" value={districName} onChange={onChangeDistric}>*/}
                        <Input  className="col-9 right" type="select" name="districtName" required>
                          <option value="">--Chọn quận huyện--</option>
                          {testsTenQuan && testsTenQuan.length>0?testsTenQuan.map((item, index)=>{
                            return(
                              <option key={index*1103}>{item.DistrictName}</option>
                            )
                          }): ('')}
                        </Input>
                      </FormGroup>
                      <FormGroup className="checkout-group">
                        <Label className="col-3 left">Địa chỉ chi tiết</Label>
                        <Input className="col-9 right" type="text" placeholder="Ví dụ: Số 64, Ngõ 2, Đường ABC"></Input>
                      </FormGroup>
                      <FormGroup className="checkout-group">
                        <Label className="col-3 left">Lời nhắn</Label>
                        <Input className="col-9 right" type="textarea"  placeholder="Ví dụ: Chuyển ngoài giờ hành chính"></Input>
                      </FormGroup>
                      <FormGroup>
                        <FormText>This is form text</FormText>
                      </FormGroup>
                    </div>
                    <div className="btn-right"><Button type="submit" className="btn btn-checkout">Tiếp tục</Button></div>
                  </div>
                  <div className="product-customer col-4">
                    <div className="title"><h5>Đơn hàng ({totalProduct()} sản phẩm)</h5></div>
                    <div className="infor-content">
                      <div className="content-product">
                        {CartItems && CartItems.length>0?CartItems.map((item, index)=>{
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
                          )}):("không thấy dữ liệu")}
                      </div>
                      <div className="total-price">
                        <div>Tổng tiền: <strong>{totalPrice().toLocaleString()}đ</strong></div>
                        <div>Cước vận chuyển</div>
                        <div>Số tiền cần thanh toán:<strong>{totalPrice().toLocaleString()}đ</strong></div>
                      </div>
                    </div>
                  </div>
              </div>
          </Form>
      </section>
  );
}

export default CheckoutCart;

