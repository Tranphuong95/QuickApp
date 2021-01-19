import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './productest.reducer';
import { IProductest } from 'app/shared/model/productest.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';


import ReactDOM from 'react-dom';


// Require Editor CSS files.
import 'froala-editor/css/froala_style.min.css';
import 'froala-editor/css/froala_editor.pkgd.min.css';

import 'froala-editor/js/plugins.pkgd.min.js';

import FroalaEditor from 'react-froala-wysiwyg'; //todo import important!
import FroalaEditorImg from 'react-froala-wysiwyg/FroalaEditorImg'
// import Froalaeditor from 'froala-editor';
import FroalaEditorView from 'react-froala-wysiwyg/FroalaEditorView';
// import FroalaEditorA from 'react-froala-wysiwyg/FroalaEditorA';
// import FroalaEditorButton from 'react-froala-wysiwyg/FroalaEditorButton';
// import FroalaEditorImg from 'react-froala-wysiwyg/FroalaEditorImg';
// import FroalaEditorInput from 'react-froala-wysiwyg/FroalaEditorInput';


export interface IProductestUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const ProductestUpdate = (props: IProductestUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);
  const [contentState, setContentState]=useState('')

  const { productestEntity, loading, updating } = props;

  const productestEditorEntity:any=productestEntity;
  const handleModelChange=(model)=>{
    setContentState(model)
    // setContentState(event)
    // window.console.log(event)
  }
  const productTestState={tensanpham:contentState};
  const handleClose = () => {
    props.history.push('/productest');
  };
  useEffect(()=>{
    if(productestEditorEntity.tensanpham && productestEditorEntity.tensanpham!==null && props.match.params.id){
      setContentState(productestEditorEntity.tensanpham)
    }
  },[productestEditorEntity])
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
        ...productestEntity,
        ...values,
        ...productTestState as {}
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
          <h2 id="quicklyappApp.productest.home.createOrEditLabel">
            <Translate contentKey="quicklyappApp.productest.home.createOrEditLabel">Create or edit a Productest</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : productestEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="productest-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="productest-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="tensanphamLabel" for="productest-tensanpham">
                  <Translate contentKey="quicklyappApp.productest.tensanpham">Ten san pham</Translate>
                </Label>
                <AvField id="productest-tensanpham" type="text" name="tensanpham" />
              </AvGroup>
              <FroalaEditor
                model={contentState}
                onModelChange={handleModelChange}
                config={{
                  imageUploadURL: 'http://localhost:8080/api/fileanhs',
                  imageUploadParam: 'img',
                  imageUploadParams: {
                    pikachu: 'pikachi'
                  },
                  // Allow to upload PNG and JPG.
                  imageAllowedTypes: ['jpeg', 'jpg', 'png', 'gif'],
                  // Set request type.
                  imageUploadMethod: 'POST',

                  imageUpload: true,

                  // imageUploadParams: { Authorization:'Client-ID c166b3ccc22b789' }

                  // imageUploadURL:'https://api.imgur.com/3/image',
                  // imageUploadParams: {Authorization:'Client-ID c166b3ccc22b789'},
                  // imageUploadMethod: 'POST'
                }}
              />
              <FroalaEditorImg/>
              <FroalaEditorView
                model={contentState}
              />
              <Button tag={Link} id="cancel-save" to="/productest" replace color="info">
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
  productestEntity: storeState.productest.entity,
  loading: storeState.productest.loading,
  updating: storeState.productest.updating,
  updateSuccess: storeState.productest.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(ProductestUpdate);
