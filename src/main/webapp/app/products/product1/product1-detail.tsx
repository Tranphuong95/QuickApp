import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './../../entities/editor/editor.reducer';
import { IEditor } from 'app/shared/model/editor.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import Cart from "app/products/shopcart/cart";

export interface IEditorDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

const Product1Detail = (props: IEditorDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { editorEntity } = props;
  window.console.log(editorEntity)
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="quicklyappApp.editor.detail.title">Editor</Translate> [<b>{editorEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="editor">
              <Translate contentKey="quicklyappApp.editor.editor">Editor</Translate>
            </span>
          </dt>
          <dd>{editorEntity.editor}</dd>
        </dl>
        <Button tag={Link} to="/product/product1" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/product/product1/${editorEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
        {/*<Cart/>*/}
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ editor }: IRootState) => ({
  editorEntity: editor.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Product1Detail);

