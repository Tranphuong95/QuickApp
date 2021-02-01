import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './testsdiachi.reducer';
import { ITestsdiachi } from 'app/shared/model/testsdiachi.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

export interface ITestsdiachiUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TestsdiachiUpdate = (props: ITestsdiachiUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { testsdiachiEntity, loading, updating } = props;

  const handleClose = () => {
    props.history.push('/testsdiachi' + props.location.search);
  };

  useEffect(() => {
    if (isNew) {
      props.reset();
    } else {
      props.getEntity(props.match.params.id);
    }
  }, []);

  useEffect(() => {
    if (props.updateSuccess) {
      handleClose();
    }
  }, [props.updateSuccess]);

  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
        ...testsdiachiEntity,
        ...values,
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };

  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="quicklyappApp.testsdiachi.home.createOrEditLabel">
            <Translate contentKey="quicklyappApp.testsdiachi.home.createOrEditLabel">Create or edit a Testsdiachi</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : testsdiachiEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="testsdiachi-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="testsdiachi-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="tinhLabel" for="testsdiachi-tinh">
                  <Translate contentKey="quicklyappApp.testsdiachi.tinh">Tinh</Translate>
                </Label>
                <AvField id="testsdiachi-tinh" type="text" name="tinh" />
              </AvGroup>
              <AvGroup>
                <Label id="matinhLabel" for="testsdiachi-matinh">
                  <Translate contentKey="quicklyappApp.testsdiachi.matinh">Matinh</Translate>
                </Label>
                <AvField id="testsdiachi-matinh" type="string" className="form-control" name="matinh" />
              </AvGroup>
              <AvGroup>
                <Label id="quanhuyenLabel" for="testsdiachi-quanhuyen">
                  <Translate contentKey="quicklyappApp.testsdiachi.quanhuyen">Quanhuyen</Translate>
                </Label>
                <AvField id="testsdiachi-quanhuyen" type="text" name="quanhuyen" />
              </AvGroup>
              <AvGroup>
                <Label id="maquanhuyenLabel" for="testsdiachi-maquanhuyen">
                  <Translate contentKey="quicklyappApp.testsdiachi.maquanhuyen">Maquanhuyen</Translate>
                </Label>
                <AvField id="testsdiachi-maquanhuyen" type="string" className="form-control" name="maquanhuyen" />
              </AvGroup>
              <AvGroup>
                <Label id="phuongxaLabel" for="testsdiachi-phuongxa">
                  <Translate contentKey="quicklyappApp.testsdiachi.phuongxa">Phuongxa</Translate>
                </Label>
                <AvField id="testsdiachi-phuongxa" type="text" name="phuongxa" />
              </AvGroup>
              <AvGroup>
                <Label id="maphuongxaLabel" for="testsdiachi-maphuongxa">
                  <Translate contentKey="quicklyappApp.testsdiachi.maphuongxa">Maphuongxa</Translate>
                </Label>
                <AvField id="testsdiachi-maphuongxa" type="string" className="form-control" name="maphuongxa" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/testsdiachi" replace color="info">
                <FontAwesomeIcon icon="arrow-left" />
                &nbsp;
                <span className="d-none d-md-inline">
                  <Translate contentKey="entity.action.back">Back</Translate>
                </span>
              </Button>
              &nbsp;
              <Button color="primary" id="save-entity" type="submit" disabled={updating}>
                <FontAwesomeIcon icon="save" />
                &nbsp;
                <Translate contentKey="entity.action.save">Save</Translate>
              </Button>
            </AvForm>
          )}
        </Col>
      </Row>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  testsdiachiEntity: storeState.testsdiachi.entity,
  loading: storeState.testsdiachi.loading,
  updating: storeState.testsdiachi.updating,
  updateSuccess: storeState.testsdiachi.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TestsdiachiUpdate);
