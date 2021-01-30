import React from 'react';
import {AvForm} from 'availity-reactstrap-validation'
import {Form, Input, Button, FormGroup, Label,FormFeedback, FormText} from 'reactstrap';

const CheckoutCart=(props)=>
{
  return (
    <section>
      <div></div>
      <Form inline>
        <div className="left">
          <div>Thông tin thanh toán</div>
          <FormGroup >
            <Label>Họ tên</Label>
            <Input type="text"></Input>
          </FormGroup>
          <FormGroup>
            <Label>Điện thoại</Label>
            <Input type="text"></Input>
          </FormGroup>
          <FormGroup>
            <Label>Email</Label>
            <FormText>(Không bắt buộc)</FormText>
            <Input type="email"></Input>
          </FormGroup>
          <FormGroup>
            <Label>Tỉnh/Thành phố</Label>
            <Input></Input>
          </FormGroup>
          <FormGroup>
            <Label>Quận/huyện</Label>
            <Input></Input>
          </FormGroup>
          <FormGroup>
            <Label>Địa chỉ chi tiết</Label>
            <Input type="text"></Input>
          </FormGroup>
          <FormGroup>
            <Label>Lời nhắn</Label>
            <Input type="textarea"></Input>
          </FormGroup>
          <FormGroup>
            <FormText>This is form text</FormText>
          </FormGroup>
          <Button>Tiếp tục</Button>

        </div>
        <div className="right">Sản phẩm</div>
      </Form>
    </section>
  );
}

export default CheckoutCart;