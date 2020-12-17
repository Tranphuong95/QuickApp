import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IEditor, defaultValue } from 'app/shared/model/editor.model';

export const ACTION_TYPES = {
  FETCH_EDITOR_LIST: 'editor/FETCH_EDITOR_LIST',
  FETCH_EDITOR: 'editor/FETCH_EDITOR',
  CREATE_EDITOR: 'editor/CREATE_EDITOR',
  UPDATE_EDITOR: 'editor/UPDATE_EDITOR',
  DELETE_EDITOR: 'editor/DELETE_EDITOR',
  RESET: 'editor/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IEditor>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type EditorState = Readonly<typeof initialState>;

// Reducer

export default (state: EditorState = initialState, action): EditorState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_EDITOR_LIST):
    case REQUEST(ACTION_TYPES.FETCH_EDITOR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_EDITOR):
    case REQUEST(ACTION_TYPES.UPDATE_EDITOR):
    case REQUEST(ACTION_TYPES.DELETE_EDITOR):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_EDITOR_LIST):
    case FAILURE(ACTION_TYPES.FETCH_EDITOR):
    case FAILURE(ACTION_TYPES.CREATE_EDITOR):
    case FAILURE(ACTION_TYPES.UPDATE_EDITOR):
    case FAILURE(ACTION_TYPES.DELETE_EDITOR):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_EDITOR_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_EDITOR):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_EDITOR):
    case SUCCESS(ACTION_TYPES.UPDATE_EDITOR):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_EDITOR):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/editors';

// Actions

export const getEntities: ICrudGetAllAction<IEditor> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_EDITOR_LIST,
  payload: axios.get<IEditor>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IEditor> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_EDITOR,
    payload: axios.get<IEditor>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IEditor> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_EDITOR,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};
export const cE: ICrudPutAction<IEditor> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_EDITOR,
    payload: axios.post(apiUrl, entity),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IEditor> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_EDITOR,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IEditor> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_EDITOR,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
