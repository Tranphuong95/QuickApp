import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './testsdiachi.reducer';
import { ITestsdiachi } from 'app/shared/model/testsdiachi.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface ITestsdiachiDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const TestsdiachiDetail = (props: ITestsdiachiDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { testsdiachiEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="quicklyappApp.testsdiachi.detail.title">Testsdiachi</Translate> [<b>{testsdiachiEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="tinh">
              <Translate contentKey="quicklyappApp.testsdiachi.tinh">Tinh</Translate>
            </span>
          </dt>
          <dd>{testsdiachiEntity.tinh}</dd>
          <dt>
            <span id="matinh">
              <Translate contentKey="quicklyappApp.testsdiachi.matinh">Matinh</Translate>
            </span>
          </dt>
          <dd>{testsdiachiEntity.matinh}</dd>
          <dt>
            <span id="quanhuyen">
              <Translate contentKey="quicklyappApp.testsdiachi.quanhuyen">Quanhuyen</Translate>
            </span>
          </dt>
          <dd>{testsdiachiEntity.quanhuyen}</dd>
          <dt>
            <span id="maquanhuyen">
              <Translate contentKey="quicklyappApp.testsdiachi.maquanhuyen">Maquanhuyen</Translate>
            </span>
          </dt>
          <dd>{testsdiachiEntity.maquanhuyen}</dd>
          <dt>
            <span id="phuongxa">
              <Translate contentKey="quicklyappApp.testsdiachi.phuongxa">Phuongxa</Translate>
            </span>
          </dt>
          <dd>{testsdiachiEntity.phuongxa}</dd>
          <dt>
            <span id="maphuongxa">
              <Translate contentKey="quicklyappApp.testsdiachi.maphuongxa">Maphuongxa</Translate>
            </span>
          </dt>
          <dd>{testsdiachiEntity.maphuongxa}</dd>
        </dl>
        <Button tag={Link} to="/testsdiachi" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/testsdiachi/${testsdiachiEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ testsdiachi }: IRootState) => ({
  testsdiachiEntity: testsdiachi.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(TestsdiachiDetail);
