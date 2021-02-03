import "./checkout.scss"
import React from 'react';
import { Progress } from 'reactstrap';

const VerifieOrder=(props)=>{
  const {statusApi}=props;
    return (
        <div className='progress-multi'>
          <Progress multi>
            <Progress bar color="success" value={100/3}><span>Đặt hàng</span></Progress>
            <Progress bar color={statusApi==="Created"?"success":"secondary"} value={100/3}>Thông tin giao hàng</Progress>
            <Progress bar color="secondary" value={100/3}>Hoàn tất giao hàng</Progress>
          </Progress>
        </div>
    );
}

export default VerifieOrder;