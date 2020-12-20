// import React, { useState, useEffect } from 'react';
// import { connect } from 'react-redux';
// import { Link, RouteComponentProps } from 'react-router-dom';
// import { Button, Row, Col, Label } from 'reactstrap';
// import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
// import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
// import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
// import { IRootState } from 'app/shared/reducers';
//
// import { getEntity, updateEntity, createEntity, reset } from './editor.reducer';
// import { IEditor } from 'app/shared/model/editor.model';
// import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
// import { mapIdList } from 'app/shared/util/entity-utils';
//
// export interface IEditorUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}
//
// export const EditorUpdate = (props: IEditorUpdateProps) => {
//   const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);
//
//   const { editorEntity, loading, updating } = props;
//
//   const handleClose = () => {
//     props.history.push('/editor');
//   };
//
//   useEffect(() => {
//     if (isNew) {
//       props.reset();
//     } else {
//       props.getEntity(props.match.params.id);
//     }
//   }, []);
//
//   useEffect(() => {
//     if (props.updateSuccess) {
//       handleClose();
//     }
//   }, [props.updateSuccess]);
//
//   const saveEntity = (event, errors, values) => {
//     if (errors.length === 0) {
//       const entity = {
//         ...editorEntity,
//         ...values,
//       };
//
//       if (isNew) {
//         props.createEntity(entity);
//       } else {
//         props.updateEntity(entity);
//       }
//     }
//   };
//
//   return (
//     <div>
//       <Row className="justify-content-center">
//         <Col md="8">
//           <h2 id="quicklyappApp.editor.home.createOrEditLabel">
//             <Translate contentKey="quicklyappApp.editor.home.createOrEditLabel">Create or edit a Editor</Translate>
//           </h2>
//         </Col>
//       </Row>
//       <Row className="justify-content-center">
//         <Col md="8">
//           {loading ? (
//             <p>Loading...</p>
//           ) : (
//             <AvForm model={isNew ? {} : editorEntity} onSubmit={saveEntity}>
//               {!isNew ? (
//                 <AvGroup>
//                   <Label for="editor-id">
//                     <Translate contentKey="global.field.id">ID</Translate>
//                   </Label>
//                   <AvInput id="editor-id" type="text" className="form-control" name="id" required readOnly />
//                 </AvGroup>
//               ) : null}
//               <AvGroup>
//                 <Label id="editorLabel" for="editor-editor">
//                   <Translate contentKey="quicklyappApp.editor.editor">Editor</Translate>
//                 </Label>
//                 <AvField id="editor-editor" type="text" name="editor" />
//               </AvGroup>
//               <Button tag={Link} id="cancel-save" to="/editor" replace color="info">
//                 <FontAwesomeIcon icon="arrow-left" />
//                 &nbsp;
//                 <span className="d-none d-md-inline">
//                   <Translate contentKey="entity.action.back">Back</Translate>
//                 </span>
//               </Button>
//               &nbsp;
//               <Button color="primary" id="save-entity" type="submit" disabled={updating}>
//                 <FontAwesomeIcon icon="save" />
//                 &nbsp;
//                 <Translate contentKey="entity.action.save">Save</Translate>
//               </Button>
//             </AvForm>
//           )}
//         </Col>
//       </Row>
//     </div>
//   );
// };
//
// const mapStateToProps = (storeState: IRootState) => ({
//   editorEntity: storeState.editor.entity,
//   loading: storeState.editor.loading,
//   updating: storeState.editor.updating,
//   updateSuccess: storeState.editor.updateSuccess,
// });
//
// const mapDispatchToProps = {
//   getEntity,
//   updateEntity,
//   createEntity,
//   reset,
// };
//
// type StateProps = ReturnType<typeof mapStateToProps>;
// type DispatchProps = typeof mapDispatchToProps;
//
// export default connect(mapStateToProps, mapDispatchToProps)(EditorUpdate);
// import 'editorStyles.scss'
import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField ,AvBaseInput } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset} from './editor.reducer';
import { IEditor } from 'app/shared/model/editor.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';
import { Editor } from 'react-draft-wysiwyg';
import { EditorState,RichUtils, convertToRaw, convertFromRaw} from 'draft-js';
import 'react-draft-wysiwyg/dist/react-draft-wysiwyg.css';
import draftToHtml from 'draftjs-to-html';
// import draftToHtml from 'draftjs-to-html';

// //todo: up load image below
const getFileBase64 = (file, callback) => {
  const reader = new FileReader();
  reader.readAsDataURL(file);
  // Since FileReader is asynchronous,
  // we need to pass data back.
  reader.onload = () => callback(reader.result);
  // TODO: catch an error
  reader.onerror = error => {};
};

const imageUploadCallback = file => new Promise(
  (resolve, reject) => getFileBase64(
    file,
    data => resolve({ data: { link: data } })
  )
);
// function uploadImageCallBack(file) {
//   return new Promise(
//     (resolve, reject) => {
//       const xhr = new XMLHttpRequest();
//       xhr.open('POST', 'https://api.imgur.com/3/image');
//       xhr.setRequestHeader('Authorization', 'Client-ID ##clientid##');
//       const data = new FormData();
//       data.append('image', file);
//       xhr.send(data);
//       xhr.addEventListener('load', () => {
//         const response = JSON.parse(xhr.responseText);
//         resolve(response);
//       });
//       xhr.addEventListener('error', () => {
//         const error = JSON.parse(xhr.responseText);
//         reject(error);
//       });
//     }
//   );
// }

export interface IEditorUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const EditorUpdate = (props: IEditorUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const { editorEntity, loading, updating } = props;
  // const { loading, updating } = props;

  const [editorState, setEditorState]=useState(()=>EditorState.createEmpty());
  useEffect(() => {
    props.reset()
  }, []);
  const contentState=editorState.getCurrentContent();
  const contentRaw=JSON.stringify(convertToRaw(contentState));
  const dataJs=JSON.parse(contentRaw)
  const edsState={editor:contentRaw};
  const handleClose = () => {
    props.history.push('/editor');
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
//
  const saveEntity = (event, errors, values) => {
    if (errors.length === 0) {
      const entity = {
          ...editorEntity,
        // ...values,
        ...edsState as {}
      };

      if (isNew) {
        props.createEntity(entity);
      } else {
        props.updateEntity(entity);
      }
    }
  };
  const editorEntityRaw:any = editorEntity;
  window.console.log(editorEntityRaw.editor)
  useEffect(()=>{
    if(editorEntityRaw.editor && editorEntityRaw.editor!==null){
      setEditorState(EditorState.createWithContent(convertFromRaw(JSON.parse(editorEntityRaw.editor))));
    }
  },[])
//
//   return (
//     <div>
//       <div className="chat-box">
//         {loading ? (
//           <p>Loading...</p>
//         ) : (
//           <AvForm  onSubmit={saveEntity}>
//             <AvGroup className="editor">
//               <Editor
//                 // handleKeyCommand={handleKeyCommand}
//                 editorState={editorState}
//                 wrapperClassName="wrapper-class"
//                 editorClassName="editor-class"
//                 toolbarClassName="toolbar-class"
//                 onEditorStateChange={setEditorState}
//                 toolbar={{
//                   image: {
//                     uploadCallback: imageUploadCallback,
//                     previewImage: true,
//                   },
//                   // image: { uploadCallback: uploadImageCallBack, alt: { present: true, mandatory: true } },
//                 }}
//               />
//             </AvGroup>
//             <Button  className="mr-3" tag={Link} id="cancel-save" to="/editor" replace color="info">
//               <FontAwesomeIcon icon="arrow-left" />&nbsp;
//               <span className="d-none d-md-inline">
//                 <Translate contentKey="entity.action.back">Back</Translate>
//               </span>
//             </Button>
//             <Button color="primary" id="save-entity" type="submit" disabled={updating}>
//               <Translate contentKey="entity.action.save">Save</Translate>
//             </Button>
//           </AvForm>
//         )}
//       </div>
//       <div dangerouslySetInnerHTML={{__html: draftToHtml(dataJs)}}></div>
//     </div>
//   )
  return (
    <div>
      <Row className="justify-content-center">
        <Col md="8">
          <h2 id="quicklyappApp.editor.home.createOrEditLabel">
            <Translate contentKey="quicklyappApp.editor.home.createOrEditLabel">Create or edit a Editor</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : editorEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="editor-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="editor-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup className="editor">
                <Editor
                  // handleKeyCommand={handleKeyCommand}
                  editorState={editorState}
                  wrapperClassName="wrapper-class"
                  editorClassName="editor-class"
                  toolbarClassName="toolbar-class"
                  onEditorStateChange={setEditorState}
                  toolbar={{
                    image: {
                      uploadCallback: imageUploadCallback,
                      previewImage: true,
                    },
                    // image: { uploadCallback: uploadImageCallBack, alt: { present: true, mandatory: true } },
                  }}
                />
              </AvGroup>
              <AvGroup>
                <Label id="editorLabel" for="editor-editor">
                  <Translate contentKey="quicklyappApp.editor.editor">Editor</Translate>
                </Label>
                <AvField id="editor-editor" type="text" name="editor" />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/editor" replace color="info">
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
      <div dangerouslySetInnerHTML={{__html: draftToHtml(dataJs)}}></div>
    </div>
  );
};

const mapStateToProps = (storeState: IRootState) => ({
  editorEntity: storeState.editor.entity,
  loading: storeState.editor.loading,
  updating: storeState.editor.updating,
  updateSuccess: storeState.editor.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(EditorUpdate);