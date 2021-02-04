import { ADD_TO_CART, UPDATE_IN_CART, REMOVE_FROM_CART, DELETE_CART } from './../actions/types';

import { ICartShop, defaultValue } from 'app/shared/model/cart-shop.model';

const localState = JSON.parse(localStorage.getItem('cartItems'));

const dataState = { cartItems: localState };

const defaultCartState = {
  cartItems: [] as ReadonlyArray<ICartShop>,
  cartItem: defaultValue,
};

const initialState = dataState.cartItems && dataState.cartItems.cartItems !== null ? dataState : defaultCartState;
export type CartState = Readonly<typeof initialState>;
// Reducer
export default (state: CartState = initialState, action): CartState => {
  switch (action.type) {
    case ADD_TO_CART:
      return {
        cartItems: action.payload.cartItems,
      };
    case UPDATE_IN_CART:
      return {
        cartItems: action.payload.cartItems,
      };
    case REMOVE_FROM_CART:
      return {
        cartItems: action.payload.cartItems,
      };
    case DELETE_CART:
      return {
        cartItems: [],
      };
    default:
      return state;
  }
};
// import {ADD_TO_CART, REMOVE_FROM_CART} from "./../actions/types";
// const dataState=JSON.parse(localStorage.getItem("cartItems"));
// const initialState=dataState?dataState:[];
//
// const findProductInCart=(cartItems,product)=>{
//   let index=null;
//   if(cartItems.length>0){
//     index=index=cartItems.map((item, i)=>{
//       if(item.id===product.id)
//         return index=i
//     })
//   }
//   return index;
// }
// const CartState=(state=initialState, action)=>{
//   const {product,quantity}=action;
//   switch (action.type) {
//     case ADD_TO_CART:{
//       const id = findProductInCart(state,product);
//       if(id !== -1){
//         state[id].quantity += quantity;
//       }
//       else{
//         state.push({product, quantity});
//       }
//       localStorage.setItem('CartItems', JSON.stringify(state))
//       return [...state]}
//     // case types.DELETE_PRODUCT_IN_CART:
//     //   index=findProductInCart(state,product);
//     //   if(index!==-1){
//     //     state.splice(index,1);
//     //   }
//     //   localStorage.setItem('CART', JSON.stringify(state))
//     //   return [...state]
//     // case types.UPDATE_PRODUCT_IN_CART:
//     //   index=findProductInCart(state,product);
//     //   if(index!==-1){
//     //     state[index].quantity=quantity;
//     //   }
//     //   localStorage.setItem('CART', JSON.stringify(state))
//     //   return [...state]
//     default: return [...state]
//   }
// }
// export default CartState
