import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Col, Row, Table } from 'reactstrap';
import { Translate, ICrudGetAllAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';

import { IRootState } from 'app/shared/reducers';
import { getEntities } from './editor.reducer';
import { IEditor } from 'app/shared/model/editor.model';
import { APP_DATE_FORMAT, APP_LOCAL_DATE_FORMAT } from 'app/config/constants';
import draftToHtml from 'draftjs-to-html';
export interface IEditorProps extends StateProps, DispatchProps, RouteComponentProps<{ url: string }> {}

export const Editor = (props: IEditorProps) => {
  useEffect(() => {
    props.getEntities();
  }, []);


  const { editorList, match, loading } = props;
  window.console.log(editorList)
  // const showData=()=>{
  //   let result=null;
  //   if(editorList.length>0){
  //     result=editorList.map((editor, i)=>{
  //       // const a = JSON.parse(editor.editor);
  //       const a = editor.editor;
  //       const b = "{\"blocks\":[{\"key\":\"6bcn8\",\"text\":\"aaaa\",\"type\":\"unstyled\",\"depth\":0,\"inlineStyleRanges\":[],\"entityRanges\":[],\"data\":{}}],\"entityMap\":{}}";
  //       const c = JSON.parse(b);
  //       return(
  //         <div key={i+10101}>
  //           {c.blocks[0].text}
  //         </div>
  //       );
  //     })
  //   }
  //   return result;
  // }
  return (
    <div>
      <h2 id="editor-heading">
        <Translate contentKey="quicklyappApp.editor.home.title">Editors</Translate>
        <Link to={`${match.url}/new`} className="btn btn-primary float-right jh-create-entity" id="jh-create-entity">
          <FontAwesomeIcon icon="plus" />
          &nbsp;
          <Translate contentKey="quicklyappApp.editor.home.createLabel">Create new Editor</Translate>
        </Link>
      </h2>
      <div className="table-responsive">
        {editorList && editorList.length > 0 ? (
          <Table responsive>
            <thead>
              <tr>
                <th>
                  <Translate contentKey="global.field.id">ID</Translate>
                </th>
                <th>
                  <Translate contentKey="quicklyappApp.editor.editor">Editor</Translate>
                </th>
                <th />
              </tr>
            </thead>
            <tbody>
              {editorList.map((editor, i) => (
                <tr key={`entity-${i}`}>
                  <td>
                    <Button tag={Link} to={`${match.url}/${editor.id}`} color="link" size="sm">
                      {editor.id}
                    </Button>
                  </td>
                  {/*<td>{editor.editor}</td>*/}

                  {/*<td>{showDataJson()}</td>*/}
                  {/*<td><div dangerouslySetInnerHTML={{__html: draftToHtml(showDataJson())}}></div></td>*/}
                  <td dangerouslySetInnerHTML={{__html: draftToHtml(JSON.parse(editor.editor))}}></td>
                  {/*{showData()}*/}
                  <td className="text-right">
                    <div className="btn-group flex-btn-group-container">
                      <Button tag={Link} to={`${match.url}/${editor.id}`} color="info" size="sm">
                        <FontAwesomeIcon icon="eye" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.view">View</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${editor.id}/edit`} color="primary" size="sm">
                        <FontAwesomeIcon icon="pencil-alt" />{' '}
                        <span className="d-none d-md-inline">
                          <Translate contentKey="entity.action.edit">Edit</Translate>
                        </span>
                      </Button>
                      <Button tag={Link} to={`${match.url}/${editor.id}/delete`} color="danger" size="sm">
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
              <Translate contentKey="quicklyappApp.editor.home.notFound">No Editors found</Translate>
            </div>
          )
        )}
      </div>
    </div>
  );

};

const mapStateToProps = ({ editor }: IRootState) => ({
  editorList: editor.entities,
  loading: editor.loading,
});

const mapDispatchToProps = {
  getEntities,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(Editor);
