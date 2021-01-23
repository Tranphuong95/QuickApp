import { ADD_TO_CART, UPDATE_IN_CART, REMOVE_FROM_CART } from 'app/products/shopcart/actions/types';

export const addToCart = (product, quantity) => (dispatch, getState) => {
  window.console.log('add gan thanh cong');
  window.console.log(getState().cartShop.cartItems.slice());
  const cartItems = getState().cartShop.cartItems.slice();
  window.console.log('add chua thanh cong');
  window.console.log(cartItems);
  let alreadyExist = false;
  cartItems.forEach(x => {
    if (x.product.id === product.id) {
      alreadyExist = true;
      x.count += quantity;
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

export const updateInCart = () => {
  //da dua len addTocart
};

export const removeFromCart = product => (dispatch, getState) => {
  const cartItems = getState()
    .cart.cartItems.slice()
    .filter(x => x._id !== product.id);
  dispatch({
    type: REMOVE_FROM_CART,
    payload: { cartItems },
  });
  localStorage.setItem('cartItem', JSON.stringify(cartItems));
};
