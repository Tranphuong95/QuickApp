import { CREAT_ORDER, CLEAR_ORDER, FETCH_ORDER, CLEAR_CART } from 'app/products/shopcart/actions/types';

const createOrder = order => dispatch => {
  // axios.post(apiUrl,{
  //   data: order
  // })
  //   .then(function (res)
  //   {
  //
  //   })
  fetch('api/orders', {
    method: 'POST',
    headers: {
      'Content-Type': 'application/json;charset=UTF-8',
    },
    body: JSON.stringify({ order }),
  })
    .then(response => response.json())
    .then(data => {
      dispatch({
        type: CREAT_ORDER,
        payload: data,
      });
      localStorage.removeItem('cartItems');
      dispatch({
        type: CLEAR_CART,
      });
    });
};
export const clearOrder = () => dispatch => {
  dispatch({
    type: CLEAR_ORDER,
  });
};
export const fetchOrder = () => dispatch => {
  fetch('api/orders')
    .then(res => res.json())
    .then(data => {
      dispatch({
        type: FETCH_ORDER,
        payload: data,
      });
    });
};
