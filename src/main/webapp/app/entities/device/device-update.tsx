import React, { useState, useEffect } from 'react';
import { connect } from 'react-redux';
import { Link, RouteComponentProps } from 'react-router-dom';
import { Button, Row, Col, Label } from 'reactstrap';
import { AvFeedback, AvForm, AvGroup, AvInput, AvField } from 'availity-reactstrap-validation';
import { Translate, translate, ICrudGetAction, ICrudGetAllAction, ICrudPutAction } from 'react-jhipster';
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome';
import { IRootState } from 'app/shared/reducers';

import { getEntity, updateEntity, createEntity, reset } from './device.reducer';
import { IDevice } from 'app/shared/model/device.model';
import { convertDateTimeFromServer, convertDateTimeToServer, displayDefaultDateTime } from 'app/shared/util/date-utils';
import { mapIdList } from 'app/shared/util/entity-utils';

import { Editor } from 'react-draft-wysiwyg';
import { EditorState,RichUtils, convertToRaw, convertFromRaw} from 'draft-js';
import 'react-draft-wysiwyg/dist/react-draft-wysiwyg.css';
import draftToHtml from 'draftjs-to-html';

function uploadImageCallBack(file) {
  return new Promise(
    (resolve, reject) => {
      const xhr = new XMLHttpRequest();
      xhr.open('POST', 'https://api.imgur.com/3/image');
      xhr.setRequestHeader('Authorization', 'Client-ID c166b3ccc22b789');
      const data = new FormData();
      data.append('image', file);
      xhr.send(data);
      xhr.addEventListener('load', () => {
        const response = JSON.parse(xhr.responseText);
        resolve(response);
      });
      xhr.addEventListener('error', () => {
        const error = JSON.parse(xhr.responseText);
        reject(error);
      });
    }
  );
}

export interface IDeviceUpdateProps extends StateProps, DispatchProps, RouteComponentProps<{ id: string }> {}

export const DeviceUpdate = (props: IDeviceUpdateProps) => {
  const [isNew, setIsNew] = useState(!props.match.params || !props.match.params.id);

  const [editorState, setEditorState]=useState(()=>EditorState.createEmpty());

  const contentState=editorState.getCurrentContent();

  const { deviceEntity, loading, updating } = props;

  const contentRaw=JSON.stringify(convertToRaw(contentState));
  const deviceEntityRaw:any=deviceEntity;
  const editorDeviceState={hotline:contentRaw}

  const handleClose = () => {
    props.history.push('/device');
  };

  useEffect(()=>{
    if(deviceEntityRaw.hotline && deviceEntityRaw.hotline!==null && props.match.params.id){
      setEditorState(EditorState.createWithContent(convertFromRaw(JSON.parse(deviceEntityRaw.hotline))))
    }
  },[deviceEntityRaw])

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
        ...deviceEntity,
        ...values,
        ...editorDeviceState as{}
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
          <h2 id="quicklyappApp.device.home.createOrEditLabel">
            <Translate contentKey="quicklyappApp.device.home.createOrEditLabel">Create or edit a Device</Translate>
          </h2>
        </Col>
      </Row>
      <Row className="justify-content-center">
        <Col md="8">
          {loading ? (
            <p>Loading...</p>
          ) : (
            <AvForm model={isNew ? {} : deviceEntity} onSubmit={saveEntity}>
              {!isNew ? (
                <AvGroup>
                  <Label for="device-id">
                    <Translate contentKey="global.field.id">ID</Translate>
                  </Label>
                  <AvInput id="device-id" type="text" className="form-control" name="id" required readOnly />
                </AvGroup>
              ) : null}
              <AvGroup>
                <Label id="tensanphamLabel" for="device-tensanpham">
                  <Translate contentKey="quicklyappApp.device.tensanpham">Tensanpham</Translate>
                </Label>
                <AvField id="device-tensanpham" type="text" name="tensanpham" />
              </AvGroup>
              <AvGroup>
                <Label id="kichthuocmatthungLabel" for="device-kichthuocmatthung">
                  <Translate contentKey="quicklyappApp.device.kichthuocmatthung">Kichthuocmatthung</Translate>
                </Label>
                <AvField id="device-kichthuocmatthung" type="text" name="kichthuocmatthung" />
              </AvGroup>
              <AvGroup>
                <Label id="kichthuocthanthungLabel" for="device-kichthuocthanthung">
                  <Translate contentKey="quicklyappApp.device.kichthuocthanthung">Kichthuocthanthung</Translate>
                </Label>
                <AvField id="device-kichthuocthanthung" type="text" name="kichthuocthanthung" />
              </AvGroup>
              <AvGroup>
                <Label id="phukienLabel" for="device-phukien">
                  <Translate contentKey="quicklyappApp.device.phukien">Phukien</Translate>
                </Label>
                <AvField id="device-phukien" type="text" name="phukien" />
              </AvGroup>
              <AvGroup>
                <Label id="chatlieuLabel" for="device-chatlieu">
                  <Translate contentKey="quicklyappApp.device.chatlieu">Chatlieu</Translate>
                </Label>
                <AvField id="device-chatlieu" type="text" name="chatlieu" />
              </AvGroup>
              <AvGroup>
                <Label id="baohanhLabel" for="device-baohanh">
                  <Translate contentKey="quicklyappApp.device.baohanh">Baohanh</Translate>
                </Label>
                <AvField id="device-baohanh" type="text" name="baohanh" />
              </AvGroup>
              <AvGroup>
                <Label id="diachiLabel" for="device-diachi">
                  <Translate contentKey="quicklyappApp.device.diachi">Diachi</Translate>
                </Label>
                <AvField id="device-diachi" type="text" name="diachi" />
              </AvGroup>
              {/*<AvGroup>*/}
              {/*  <Label id="hotlineLabel" for="device-hotline">*/}
              {/*    <Translate contentKey="quicklyappApp.device.hotline">Hotline</Translate>*/}
              {/*  </Label>*/}
              {/*  <AvField id="device-hotline" type="text" name="hotline" />*/}
              {/*</AvGroup>*/}

              <AvGroup className="hotline">
                <Editor
                  // handleKeyCommand={handleKeyCommand}
                  editorState={editorState}
                  wrapperClassName="wrapper-class"
                  editorClassName="editor-class"
                  toolbarClassName="toolbar-class"
                  onEditorStateChange={setEditorState}
                  toolbar={{
                    // image: {
                    //   uploadCallback: imageUploadCallback,
                    //   previewImage: true,
                    // },
                    image: { uploadCallback: uploadImageCallBack, alt: { present: true, mandatory: true }, previewImage: true },
                  }}
                />
              </AvGroup>
              <Button tag={Link} id="cancel-save" to="/device" replace color="info">
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
  deviceEntity: storeState.device.entity,
  loading: storeState.device.loading,
  updating: storeState.device.updating,
  updateSuccess: storeState.device.updateSuccess,
});

const mapDispatchToProps = {
  getEntity,
  updateEntity,
  createEntity,
  reset,
};

type StateProps = ReturnType<typeof mapStateToProps>;
type DispatchProps = typeof mapDispatchToProps;

export default connect(mapStateToProps, mapDispatchToProps)(DeviceUpdate);
