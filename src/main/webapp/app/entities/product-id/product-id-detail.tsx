import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './product-id.reducer';
import { IProductId } from 'app/shared/model/product-id.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IProductIdDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ProductIdDetail = (props: IProductIdDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { productIdEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="quicklyappApp.productId.detail.title">ProductId</Translate> [<b>{productIdEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="uuid">
              <Translate contentKey="quicklyappApp.productId.uuid">Uuid</Translate>
            </span>
          </dt>
          <dd>{productIdEntity.uuid}</dd>
        </dl>
        <Button tag={Link} to="/product-id" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/product-id/${productIdEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ productId }: IRootState) => ({
  productIdEntity: productId.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ProductIdDetail);
