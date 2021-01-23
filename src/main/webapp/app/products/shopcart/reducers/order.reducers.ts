import { CREAT_ORDER, CLEAR_ORDER, FETCH_ORDER } from './../actions/types';
const initialState = {};
export type OrderState = Readonly<typeof initialState>;

// Reducer
export default (state: OrderState = initialState, action): OrderState => {
  switch (action.type) {
    case CREAT_ORDER:
      return { order: action.payload };
    case CLEAR_ORDER:
      return { order: null };
    case FETCH_ORDER:
      return { orders: action.payload };
    default:
      return state;
  }
};
