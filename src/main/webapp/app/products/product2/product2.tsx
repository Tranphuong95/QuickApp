import React, { useState, useEffect } from 'react';
import {NavItem,NavLink} from 'reactstrap';
import {NavLink as Link, Redirect} from 'react-router-dom';
import { connect } from 'react-redux';

export type ProductProps=StateProps;
const Product2=(props:ProductProps)=>{
  window.console.log(props.isAuthenticated)
  return(
   <NavItem>
     {/*<NavLink tag={Link} to={props.isAuthenticated===true?'/product/product1':'/login'}>Thêm vào giỏ hàng</NavLink>*/}
     <NavLink tag={Link} to={'/product/product1'}>Thêm vào giỏ hàng</NavLink>
   </NavItem>
  )
}
const mapStateToProps = storeState => ({
  account: storeState.authentication.account,
  isAuthenticated: storeState.authentication.isAuthenticated,
});
type StateProps = ReturnType<typeof mapStateToProps>;
export default connect(mapStateToProps)(Product2);
// import React from 'react'
// import Carousel from "react-multi-carousel";
// import "react-multi-carousel/lib/styles.css";
// import {WithStyles} from '@material-ui/core/styles';
// import Toolbar from "@material-ui/core/Toolbar";
//
//
// const Product2=()=>
// {
//   // const responsive = {
//   //   desktop: {
//   //     breakpoint: {max: 3000, min: 1024},
//   //     items: 3,
//   //     slidesToSlide: 3 // optional, default to 1.
//   //   },
//   //   tablet: {
//   //     breakpoint: {max: 1024, min: 464},
//   //     items: 2,
//   //     slidesToSlide: 2 // optional, default to 1.
//   //   },
//   //   mobile: {
//   //     breakpoint: {max: 464, min: 0},
//   //     items: 1,
//   //     slidesToSlide: 1 // optional, default to 1.
//   //   }
//   // };
//   // return (
//   //   <Carousel
//   //     swipeable={false}
//   //     draggable={false}
//   //     showDots={true}
//   //     responsive={responsive}
//   //     ssr={true} // means to render carousel on server-side.
//   //     infinite={true}
//   //     autoPlay={true }
//   //     autoPlaySpeed={1000}
//   //     keyBoardControl={true}
//   //     customTransition="all .5"
//   //     transitionDuration={500}
//   //     containerClass="carousel-container"
//   //     removeArrowOnDeviceType={["tablet", "mobile"]}
//   //     // deviceType={this.props.deviceType}
//   //     dotListClass="custom-dot-list-style"
//   //     itemClass="carousel-item-padding-40-px"
//   //   >
//   //     <img src="./../../content/images/jhipster_family_member_0_head-192.png"/>
//   //     <img src="./../../content/images/jhipster_family_member_1_head-192.png"/>
//   //     <img src="./../../content/images/jhipster_family_member_2_head-192.png"/>
//   //     <img src="./../../content/images/jhipster_family_member_3_head-192.png"/>
//   //   </Carousel>
//   // )
//   return(
//     <Carousel
//       additionalTransfrom={0}
//       arrows
//       autoPlay
//       autoPlaySpeed={1000}
//       centerMode={false}
//       className=""
//       containerClass="container-with-dots"
//       dotListClass=""
//       draggable
//       focusOnSelect={false}
//       infinite
//       itemClass=""
//       keyBoardControl
//       minimumTouchDrag={80}
//       renderButtonGroupOutside={false}
//       renderDotsOutside={false}
//       responsive={{
//         desktop: {
//           breakpoint: {
//             max: 3000,
//             min: 1024
//           },
//           items: 3,
//           partialVisibilityGutter: 40
//         },
//         mobile: {
//           breakpoint: {
//             max: 464,
//             min: 0
//           },
//           items: 1,
//           partialVisibilityGutter: 30
//         },
//         tablet: {
//           breakpoint: {
//             max: 1024,
//             min: 464
//           },
//           items: 2,
//           partialVisibilityGutter: 30
//         }
//       }}
//       showDots={false}
//       sliderClass=""
//       slidesToSlide={2}
//       swipeable
//     >
//       <img
//         src="https://images.unsplash.com/photo-1549989476-69a92fa57c36?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60"
//       />
//       <img
//         src="https://images.unsplash.com/photo-1549396535-c11d5c55b9df?ixlib=rb-1.2.1&auto=format&fit=crop&w=800&q=60"
//       />
//       <img
//         src="https://images.unsplash.com/photo-1550133730-695473e544be?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60"
//       />
//       <img
//         src="https://images.unsplash.com/photo-1550167164-1b67c2be3973?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60"
//       />
//       <img
//         src="https://images.unsplash.com/photo-1550338861-b7cfeaf8ffd8?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60"
//       />
//       <img
//         src="https://images.unsplash.com/photo-1550223640-23097fc71cb2?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60"
//       />
//       <img
//         src="https://images.unsplash.com/photo-1550353175-a3611868086b?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60"
//       />
//       <img
//         src="https://images.unsplash.com/photo-1550330039-a54e15ed9d33?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60"
//       />
//       <img
//         src="https://images.unsplash.com/photo-1549737328-8b9f3252b927?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60"
//       />
//       <img
//         src="https://images.unsplash.com/photo-1549833284-6a7df91c1f65?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60"
//       />
//       <img
//         src="https://images.unsplash.com/photo-1549985908-597a09ef0a7c?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60"
//       />
//       <img
//         src="https://images.unsplash.com/photo-1550064824-8f993041ffd3?ixlib=rb-1.2.1&ixid=eyJhcHBfaWQiOjEyMDd9&auto=format&fit=crop&w=800&q=60"
//       />
//     </Carousel>
//
//   )
// };
// export default Product2