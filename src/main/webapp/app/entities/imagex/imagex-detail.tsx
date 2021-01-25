import React, { useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col } from 'reactstrap';
import { Translate, ICrudGetAction, openFile, byteSize } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntity } from './imagex.reducer';
import { IImagex } from 'app/shared/model/imagex.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IImagexDetailProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ImagexDetail = (props: IImagexDetailProps) => {
  useEffect(() => {
    props.getEntity(props.match.params.id);
  }, []);

  const { imagexEntity } = props;
  return (
    <Row>
      <Col md="8">
        <h2>
          <Translate contentKey="quicklyappApp.imagex.detail.title">Imagex</Translate> [<b>{imagexEntity.id}</b>]
        </h2>
        <dl className="jh-entity-details">
          <dt>
            <span id="image">
              <Translate contentKey="quicklyappApp.imagex.image">Image</Translate>
            </span>
          </dt>
          <dd>
            {imagexEntity.image ? (
              <div>
                {imagexEntity.imageContentType ? (
                  <a onClick={openFile(imagexEntity.imageContentType, imagexEntity.image)}>
                    <img src={`data:${imagexEntity.imageContentType};base64,${imagexEntity.image}`} style={{ maxHeight: '30px' }} />
                  </a>
                ) : null}
                <span>
                  {imagexEntity.imageContentType}, {byteSize(imagexEntity.image)}
                </span>
              </div>
            ) : null}
          </dd>
        </dl>
        <Button tag={Link} to="/imagex" replace color="info">
          <FontAwesomeIcon icon="arrow-left" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.back">Back</Translate>
          </span>
        </Button>
        &nbsp;
        <Button tag={Link} to={`/imagex/${imagexEntity.id}/edit`} replace color="primary">
          <FontAwesomeIcon icon="pencil-alt" />{' '}
          <span className="d-none d-md-inline">
            <Translate contentKey="entity.action.edit">Edit</Translate>
          </span>
        </Button>
      </Col>
    </Row>
  );
};

const mapStateToProps = ({ imagex }: IRootState) => ({
  imagexEntity: imagex.entity,
});

const mapDispatchToProps = { getEntity };

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ImagexDetail);
