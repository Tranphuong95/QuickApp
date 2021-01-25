import { ADD_TO_CART, UPDATE_IN_CART, REMOVE_FROM_CART } from 'app/products/shopcart/actions/types';

export const addToCart = (product, quantity) => (dispatch, getState) => {
  window.console.log('add gan thanh cong');
  window.console.log(getState().cartShop);
  const cartItems = getState().cartShop.cartItems.slice();
  window.console.log('add chua thanh cong');
  window.console.log(cartItems);
  let alreadyExist = false;
  cartItems.forEach(x => {
    if (x.product.id === product.id) {
      alreadyExist = true;
      x.count += quantity;
      x.product.tensanpham = product.tensanpham; //todo add 25/1
    }
  });
  if (!alreadyExist) {
    window.console.log(!alreadyExist);
    cartItems.push({ product, count: quantity });
  }
  dispatch({
    type: ADD_TO_CART,
    payload: { cartItems },
  });
  localStorage.setItem('cartItems', JSON.stringify(cartItems));
};

export const updateInCart = (product, quantity) => (dispatch, getState) => {
  window.console.log(product.tensanpham);
  const cartItems = getState().cartShop.cartItems.slice();
  window.console.log(product.id);
  cartItems.forEach(x => {
    window.console.log(x.product.id === product.id);
    if (x.product.id === product.id) {
      x.count = quantity;
    }
  });
  window.console.log('so sanh id');
  dispatch({
    type: UPDATE_IN_CART,
    payload: { cartItems },
  });
  window.console.log('dispatch thanh cong');
  localStorage.setItem('cartItems', JSON.stringify(cartItems));
  window.console.log('set thanh cong');
};

export const removeFromCart = product => (dispatch, getState, filter) => {
  window.console.log('chuan bi xoa');
  const cartItems = getState()
    .cartShop.cartItems.slice()
    .filter(x => x.product.id !== product.id);
  window.console.log('chuan bi dispatch');
  dispatch({
    type: REMOVE_FROM_CART,
    payload: { cartItems },
  });
  localStorage.setItem('cartItems', JSON.stringify(cartItems));
  window.console.log('set xong remove');
};
