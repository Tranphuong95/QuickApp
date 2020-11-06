import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './message.reducer';
import { IMessage } from 'app/shared/model/message.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface IMessageUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const MessageUpdate = (props: IMessageUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { messageEntity, loading, updating } = props;

  useEffect(() => {
    props.reset()
  }, []);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        // ...messageEntity,
        ...values,
      };
      props.createEntity(entity);
    }
  };

  return (
    <div>
      <div className="chat-box">
        {loading ? (
          <p>Loading...</p>
        ) : (
          <AvForm onSubmit={saveEntity}>
            <AvGroup>
              <AvField id="message-message" type="text" name="message" />
            </AvGroup>
            <Button color="primary" id="save-entity" type="submit" disabled={updating}>
              <Translate contentKey="entity.action.save">Save</Translate>
            </Button>
          </AvForm>
        )}
      </div>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  messageEntity: storeState.message.entity,
  loading: storeState.message.loading,
  updating: storeState.message.updating,
  updateSuccess: storeState.message.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(MessageUpdate);
