import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './device.reducer';
import { IDevice } from 'app/shared/model/device.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IDeviceDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DeviceDetail = (props: IDeviceDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { deviceEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="quicklyappApp.device.detail.title">Device</Translate> [<b>{deviceEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="tensanpham">
              <Translate contentKey="quicklyappApp.device.tensanpham">Tensanpham</Translate>
            </span>
          </dt>
          <dd>{deviceEntity.tensanpham}</dd>
          <dt>
            <span id="kichthuocmatthung">
              <Translate contentKey="quicklyappApp.device.kichthuocmatthung">Kichthuocmatthung</Translate>
            </span>
          </dt>
          <dd>{deviceEntity.kichthuocmatthung}</dd>
          <dt>
            <span id="kichthuocthanthung">
              <Translate contentKey="quicklyappApp.device.kichthuocthanthung">Kichthuocthanthung</Translate>
            </span>
          </dt>
          <dd>{deviceEntity.kichthuocthanthung}</dd>
          <dt>
            <span id="phukien">
              <Translate contentKey="quicklyappApp.device.phukien">Phukien</Translate>
            </span>
          </dt>
          <dd>{deviceEntity.phukien}</dd>
          <dt>
            <span id="chatlieu">
              <Translate contentKey="quicklyappApp.device.chatlieu">Chatlieu</Translate>
            </span>
          </dt>
          <dd>{deviceEntity.chatlieu}</dd>
          <dt>
            <span id="baohanh">
              <Translate contentKey="quicklyappApp.device.baohanh">Baohanh</Translate>
            </span>
          </dt>
          <dd>{deviceEntity.baohanh}</dd>
          <dt>
            <span id="diachi">
              <Translate contentKey="quicklyappApp.device.diachi">Diachi</Translate>
            </span>
          </dt>
          <dd>{deviceEntity.diachi}</dd>
          <dt>
            <span id="hotline">
              <Translate contentKey="quicklyappApp.device.hotline">Hotline</Translate>
            </span>
          </dt>
          <dd>{deviceEntity.hotline}</dd>
        </dl>
        <Button tag={Link} to="/device" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/device/${deviceEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ device }: IRootState) => ({
  deviceEntity: device.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DeviceDetail);
