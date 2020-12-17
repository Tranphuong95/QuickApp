import './message.scss'
import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './message.reducer';
import { IMessage } from 'app/shared/model/message.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import MessageUpdate from "app/entities/message/message-update";
import ErrorBoundaryRoute from "app/shared/error/error-boundary-route";


export interface IMessageProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Message = (props: IMessageProps) => {

  useEffect(() => {
    props.getEntities();
  }, []);
  const { messageList, match, loading } = props;
  return (
    <div>
      <div className="contain">
        {/*<h2 id="message-heading">*/}
        {/*  <Translate contentKey="quicklyappApp.message.home.title">Messages</Translate>*/}
        {/*  <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">*/}
        {/*    <FontAwesomeIcon icon="plus" />*/}
        {/*    &nbsp;*/}
        {/*    <Translate contentKey="quicklyappApp.message.home.createLabel">Create new Message</Translate>*/}
        {/*  </Link>*/}
        {/*</h2>*/}
        <div className="chat-message">
          <div className="chat-body">
            {messageList && messageList.length > 0 ? (
              <ul className="chat-contain">
                {messageList.map((message, i) => (
                  <li key={`entity-${i}`}>
                  <span className="ks-avarta ks-online">
					          <img src="content/images/avarta/avarta1.png" alt="avarta"/>
				          </span>
                    <div className="ks-body">
                      <div className="ks-header">
                        phuong{/*{userName}*/}
                      </div>
                      <span className="ks-message">{message.message}</span>
                    </div>
                    <div className="text-right">
                      <div className="btn-group flex-btn-group-container">
                        <Button tag={Link} to={`${match.url}/${message.id}/delete`} color="danger" size="sm">
                          <FontAwesomeIcon icon="trash" />{' '}
                          <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                        </Button>
                      </div>
                    </div>
                  </li>
                ))}
              </ul>
            ) : (
              !loading && (
                <div className="alert alert-warning">
                  <Translate contentKey="quicklyappApp.message.home.notFound">No Messages found</Translate>
                </div>
              )
            )}
          </div>
          <ErrorBoundaryRoute  component={MessageUpdate} />
        </div>
      </div>
    </div>
  );
};

const mapStateToProps = ({ message }: IRootState) => ({
  messageList: message.entities,
  loading: message.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Message);
