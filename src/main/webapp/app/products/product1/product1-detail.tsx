import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './../../entities/productest/productest.reducer';
import { IProductest } from 'app/shared/model/productest.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import FroalaEditorView from 'react-froala-wysiwyg/FroalaEditorView';
import Cart from "app/products/shopcart/cart";

export interface IProductestDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ProductestDetail = (props: IProductestDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { productestEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="quicklyappApp.productest.detail.title">Productest</Translate> [<b>{productestEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="tensanpham">
              <Translate contentKey="quicklyappApp.productest.tensanpham">Tensanpham</Translate>
            </span>
          </dt>
          {/*<dd>{productestEntity.tensanpham}</dd>*/}
          <FroalaEditorView
            model={productestEntity.tensanpham}
          />
        </dl>
        {/*<Button tag={Link} to="/productest" replace color="info">*/}
        {/*  <FontAwesomeIcon icon="arrow-left" />{' '}*/}
        {/*  <span className="d-none d-md-inline">*/}
        {/*    <Translate contentKey="entity.action.back">Back</Translate>*/}
        {/*  </span>*/}
        {/*</Button>*/}
        {/*&nbsp;*/}
        {/*<Button tag={Link} to={`/productest/${productestEntity.id}/edit`} replace color="primary">*/}
        {/*  <FontAwesomeIcon icon="pencil-alt" />{' '}*/}
        {/*  <span className="d-none d-md-inline">*/}
        {/*    <Translate contentKey="entity.action.edit">Edit</Translate>*/}
        {/*  </span>*/}
        {/*</Button>*/}
        <Cart productestEntity={productestEntity}/>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ productest }: IRootState) => ({
  productestEntity: productest.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ProductestDetail);
