// import axios from 'axios';
// import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';
// import {SUCCESS, FAILURE, REQUEST} from "app/shared/reducers/action-type.util";
// import {IEditor} from "app/shared/model/editor.model";
// import {cleanEntity} from "app/shared/util/entity-utils";
// import {getEntities} from "app/entities/editor/editor.reducer";
//
// export const ACTION_TYPES={
//   FETCH_CART:'FETCH_CART',
//   ADD_TO_CART:'ADD_TO_CART',
//   CHANGE_MESSAGE:'CHANGE_MESSAGE',
//   DELETE_PRODUCT_IN_CART:'DELETE_PRODUCT_IN_CART',
//   UPDATE_PRODUCT_IN_CART:'UPDATE_PRODUCT_IN_CART',
//   RESET: 'shopcart/RESET',
// }
// const initialCarts={
//   loading: false,
//   errorMessage: null,
//   productCart:{},
//   carts:[],
//   quantity: 1,
//   updating:false,
//   updateSuccess:false
// }
//
// export type CartShopState= Readonly<typeof initialCarts>
//
// //reducer
// export default (state:CartShopState=initialCarts, action):CartShopState=>{
//   switch(action.type){
//     case REQUEST(ACTION_TYPES.FETCH_CART):
//       return {
//         ...state,
//         errorMessage: null,
//         updateSuccess: false,
//         loading: true
//       };
//     case REQUEST(ACTION_TYPES.ADD_TO_CART):
//     case REQUEST(ACTION_TYPES.UPDATE_PRODUCT_IN_CART):
//     case REQUEST(ACTION_TYPES.DELETE_PRODUCT_IN_CART):
//       return {
//         ...state,
//         errorMessage: null,
//         updateSuccess: false,
//         loading: true
//
//       }
//     case FAILURE(ACTION_TYPES.FETCH_CART):
//     case FAILURE(ACTION_TYPES.ADD_TO_CART):
//     case FAILURE(ACTION_TYPES.UPDATE_PRODUCT_IN_CART):
//     case FAILURE(ACTION_TYPES.DELETE_PRODUCT_IN_CART):
//       return{
//         ...state,
//         loading: false,
//         updating: false,
//         updateSuccess: false,
//         errorMessage: action.payload
//       }
//     case SUCCESS(ACTION_TYPES.FETCH_CART):
//     case SUCCESS(ACTION_TYPES.ADD_TO_CART):
//     case SUCCESS(ACTION_TYPES.UPDATE_PRODUCT_IN_CART):
//     case SUCCESS(ACTION_TYPES.DELETE_PRODUCT_IN_CART):
//       return{
//         ...state,
//         loading: false,
//         carts:action.payload.data
//       }
//     case ACTION_TYPES.RESET:
//       return {
//         ...initialCarts
//       }
//     default:
//       return state
//   }
//
// }
//
// const initialProducts:[
//   {
//     id:1,
//     image:"https://trongmanhhung.com/uploads/images/product/thung-ruou-go-soi/thung-go-soi-moc.jpg",
//     name:"Sản phẩm 1",
//     price: 2000000,
//     inventory: 5
//   },
//   {
//     id:2,
//     name:"Sản phẩm 2",
//     image:"https://trongmanhhung.com/uploads/images/product/thung-ruou-go-soi/thung-go-soi(5).jpg",
//     price: 1500000,
//     inventory: 7
//   },
//   {
//     id: 3,
//     name: "Sản phẩm 3",
//     image: "https://trongmanhhung.com/uploads/images/product/thung-ruou-go-soi/thung-dung-ruou-vang.jpg",
//     price: 2200000,
//     inventory: 6
//   }
// ]
// export const productCarts=(state=initialProducts, action)=>{
//   switch (action.type){
//     default:
//       return [...state]
//   }
// }
