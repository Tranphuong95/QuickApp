// import React, { useEffect } from 'react';
// import { connect } from 'react-redux';
// import { Link, RouteComponentProps } from 'react-router-dom';
// import { Button, Row, Col } from 'reactstrap';
// import { Translate, ICrudGetAction } from 'react-jhipster';
// import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
//
// import { IRootState } from 'app/shared/reducers';
// import { getEntity } from './message.reducer';
// import { IMessage } from 'app/shared/model/message.model';
// import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
//
// export interface IMessageDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}
//
// export const MessageDetail = (props: IMessageDetailProps) => {
//   useEffect(() => {
//     props.getEntity(props.match.params.id);
//   }, []);
//
//   const { messageEntity } = props;
//   return (
//     <Row>
//       <Col md="8">
//         <h2>
//           <Translate contentKey="quicklyappApp.message.detail.title">Message</Translate> [<b>{messageEntity.id}</b>]
//         </h2>
//         <dl className="jh-entity-details">
//           <dt>
//             <span id="message">
//               <Translate contentKey="quicklyappApp.message.message">Message</Translate>
//             </span>
//           </dt>
//           <dd>{messageEntity.message}</dd>
//         </dl>
//         <Button tag={Link} to="/message" replace color="info">
//           <FontAwesomeIcon icon="arrow-left" />{' '}
//           <span className="d-none d-md-inline">
//             <Translate contentKey="entity.action.back">Back</Translate>
//           </span>
//         </Button>
//         &nbsp;
//         <Button tag={Link} to={`/message/${messageEntity.id}/edit`} replace color="primary">
//           <FontAwesomeIcon icon="pencil-alt" />{' '}
//           <span className="d-none d-md-inline">
//             <Translate contentKey="entity.action.edit">Edit</Translate>
//           </span>
//         </Button>
//       </Col>
//     </Row>
//   );
// };
//
// const mapStateToProps = ({ message }: IRootState) => ({
//   messageEntity: message.entity,
// });
//
// const mapDispatchToProps = { getEntity };
//
// type StateProps = ReturnType<typeof mapStateToProps>;
// type DispatchProps = typeof mapDispatchToProps;
//
// export default connect(mapStateToProps, mapDispatchToProps)(MessageDetail);
