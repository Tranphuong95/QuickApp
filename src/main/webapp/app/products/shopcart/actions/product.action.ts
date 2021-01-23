import { FETCH_PRODUCTS } from 'app/products/shopcart/actions/types';

export const fetchProducts = () => async dispatch => {
  const res = await fetch('http://localhost:8080/api/productests');
  const data = await res.json();
  window.console.log('fetch thanh cong');
  dispatch({
    type: FETCH_PRODUCTS,
    payload: data,
  });
  // fetch('http://localhost:8080/api/productests', {
  //     method: 'GET',
  //     headers: {
  //       'Accept': 'application/json',
  //       'Content-Type': 'application/json'
  //     },
  //   })
  //       .then(response => response.json())
  //       .then(data=>{dispatch({
  //         type:FETCH_PRODUCTS,
  //         payload: data
  //       })})
};
