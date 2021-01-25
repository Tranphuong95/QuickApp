import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IImagex, defaultValue } from 'app/shared/model/imagex.model';

export const ACTION_TYPES = {
  FETCH_IMAGEX_LIST: 'imagex/FETCH_IMAGEX_LIST',
  FETCH_IMAGEX: 'imagex/FETCH_IMAGEX',
  CREATE_IMAGEX: 'imagex/CREATE_IMAGEX',
  UPDATE_IMAGEX: 'imagex/UPDATE_IMAGEX',
  DELETE_IMAGEX: 'imagex/DELETE_IMAGEX',
  SET_BLOB: 'imagex/SET_BLOB',
  RESET: 'imagex/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IImagex>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type ImagexState = Readonly<typeof initialState>;

// Reducer

export default (state: ImagexState = initialState, action): ImagexState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_IMAGEX_LIST):
    case REQUEST(ACTION_TYPES.FETCH_IMAGEX):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_IMAGEX):
    case REQUEST(ACTION_TYPES.UPDATE_IMAGEX):
    case REQUEST(ACTION_TYPES.DELETE_IMAGEX):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_IMAGEX_LIST):
    case FAILURE(ACTION_TYPES.FETCH_IMAGEX):
    case FAILURE(ACTION_TYPES.CREATE_IMAGEX):
    case FAILURE(ACTION_TYPES.UPDATE_IMAGEX):
    case FAILURE(ACTION_TYPES.DELETE_IMAGEX):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_IMAGEX_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_IMAGEX):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_IMAGEX):
    case SUCCESS(ACTION_TYPES.UPDATE_IMAGEX):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_IMAGEX):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: {},
      };
    case ACTION_TYPES.SET_BLOB: {
      const { name, data, contentType } = action.payload;
      return {
        ...state,
        entity: {
          ...state.entity,
          [name]: data,
          [name + 'ContentType']: contentType,
        },
      };
    }
    case ACTION_TYPES.RESET:
      return {
        ...initialState,
      };
    default:
      return state;
  }
};

const apiUrl = 'api/imagexes';

// Actions

export const getEntities: ICrudGetAllAction<IImagex> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_IMAGEX_LIST,
  payload: axios.get<IImagex>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IImagex> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_IMAGEX,
    payload: axios.get<IImagex>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IImagex> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_IMAGEX,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IImagex> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_IMAGEX,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IImagex> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_IMAGEX,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const setBlob = (name, data, contentType?) => ({
  type: ACTION_TYPES.SET_BLOB,
  payload: {
    name,
    data,
    contentType,
  },
});

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
