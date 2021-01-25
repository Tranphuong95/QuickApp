import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { openFile, byteSize, Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './imagex.reducer';
import { IImagex } from 'app/shared/model/imagex.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';

export interface IImagexProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Imagex = (props: IImagexProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);

  const { imagexList, match, loading } = props;
  return (
    <div>
      <h2 id="imagex-heading">
        <Translate contentKey="quicklyappApp.imagex.home.title">Imagexes</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="quicklyappApp.imagex.home.createLabel">Create new Imagex</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {imagexList && imagexList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="quicklyappApp.imagex.image">Image</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {imagexList.map((imagex, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${imagex.id}`} color="link" size="sm">
                      {imagex.id}
                    </Button>
                  </td>
                  <td>
                    {imagex.image ? (
                      <div>
                        {imagex.imageContentType ? (
                          <a onClick={openFile(imagex.imageContentType, imagex.image)}>
                            <img src={`data:${imagex.imageContentType};base64,${imagex.image}`} style={{ maxHeight: '30px' }} />
                            &nbsp;
                          </a>
                        ) : null}
                        <span>
                          {imagex.imageContentType}, {byteSize(imagex.image)}
                        </span>
                      </div>
                    ) : null}
                  </td>
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${imagex.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${imagex.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${imagex.id}/delete`} color="danger" size="sm">
                        <FontAwesomeIcon icon="trash" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.delete">Delete</Translate>
                        </span>
                      </Button>
                    </div>
                  </td>
                </tr>
              ))}
            </tbody>
          </Table>
        ) : (
          !loading && (
            <div className="alert alert-warning">
              <Translate contentKey="quicklyappApp.imagex.home.notFound">No Imagexes found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );
};

const mapStateToProps = ({ imagex }: IRootState) => ({
  imagexList: imagex.entities,
  loading: imagex.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Imagex);
