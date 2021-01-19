import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITestuuid, defaultValue } from 'app/shared/model/testuuid.model';

export const ACTION_TYPES = {
  FETCH_TESTUUID_LIST: 'testuuid/FETCH_TESTUUID_LIST',
  FETCH_TESTUUID: 'testuuid/FETCH_TESTUUID',
  CREATE_TESTUUID: 'testuuid/CREATE_TESTUUID',
  UPDATE_TESTUUID: 'testuuid/UPDATE_TESTUUID',
  DELETE_TESTUUID: 'testuuid/DELETE_TESTUUID',
  RESET: 'testuuid/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITestuuid>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type TestuuidState = Readonly<typeof initialState>;

// Reducer

export default (state: TestuuidState = initialState, action): TestuuidState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TESTUUID_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TESTUUID):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_TESTUUID):
    case REQUEST(ACTION_TYPES.UPDATE_TESTUUID):
    case REQUEST(ACTION_TYPES.DELETE_TESTUUID):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_TESTUUID_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TESTUUID):
    case FAILURE(ACTION_TYPES.CREATE_TESTUUID):
    case FAILURE(ACTION_TYPES.UPDATE_TESTUUID):
    case FAILURE(ACTION_TYPES.DELETE_TESTUUID):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_TESTUUID_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_TESTUUID):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_TESTUUID):
    case SUCCESS(ACTION_TYPES.UPDATE_TESTUUID):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_TESTUUID):
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

const apiUrl = 'api/testuuids';

// Actions

export const getEntities: ICrudGetAllAction<ITestuuid> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_TESTUUID_LIST,
  payload: axios.get<ITestuuid>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<ITestuuid> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TESTUUID,
    payload: axios.get<ITestuuid>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ITestuuid> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TESTUUID,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITestuuid> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TESTUUID,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITestuuid> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TESTUUID,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
