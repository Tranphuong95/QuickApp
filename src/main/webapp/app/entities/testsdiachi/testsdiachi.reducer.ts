import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { ITestsdiachi, defaultValue } from 'app/shared/model/testsdiachi.model';

export const ACTION_TYPES = {
  FETCH_TESTSDIACHI_LIST: 'testsdiachi/FETCH_TESTSDIACHI_LIST',
  FETCH_TESTSDIACHI: 'testsdiachi/FETCH_TESTSDIACHI',
  CREATE_TESTSDIACHI: 'testsdiachi/CREATE_TESTSDIACHI',
  UPDATE_TESTSDIACHI: 'testsdiachi/UPDATE_TESTSDIACHI',
  DELETE_TESTSDIACHI: 'testsdiachi/DELETE_TESTSDIACHI',
  RESET: 'testsdiachi/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<ITestsdiachi>,
  entity: defaultValue,
  updating: false,
  totalItems: 0,
  updateSuccess: false,
};

export type TestsdiachiState = Readonly<typeof initialState>;

// Reducer

export default (state: TestsdiachiState = initialState, action): TestsdiachiState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_TESTSDIACHI_LIST):
    case REQUEST(ACTION_TYPES.FETCH_TESTSDIACHI):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_TESTSDIACHI):
    case REQUEST(ACTION_TYPES.UPDATE_TESTSDIACHI):
    case REQUEST(ACTION_TYPES.DELETE_TESTSDIACHI):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_TESTSDIACHI_LIST):
    case FAILURE(ACTION_TYPES.FETCH_TESTSDIACHI):
    case FAILURE(ACTION_TYPES.CREATE_TESTSDIACHI):
    case FAILURE(ACTION_TYPES.UPDATE_TESTSDIACHI):
    case FAILURE(ACTION_TYPES.DELETE_TESTSDIACHI):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_TESTSDIACHI_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
        totalItems: parseInt(action.payload.headers['x-total-count'], 10),
      };
    case SUCCESS(ACTION_TYPES.FETCH_TESTSDIACHI):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_TESTSDIACHI):
    case SUCCESS(ACTION_TYPES.UPDATE_TESTSDIACHI):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_TESTSDIACHI):
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

const apiUrl = 'api/testsdiachis';

// Actions

export const getEntities: ICrudGetAllAction<ITestsdiachi> = (page, size, sort) => {
  const requestUrl = `${apiUrl}${sort ? `?page=${page}&size=${size}&sort=${sort}` : ''}`;
  return {
    type: ACTION_TYPES.FETCH_TESTSDIACHI_LIST,
    payload: axios.get<ITestsdiachi>(`${requestUrl}${sort ? '&' : '?'}cacheBuster=${new Date().getTime()}`),
  };
};

export const getEntity: ICrudGetAction<ITestsdiachi> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_TESTSDIACHI,
    payload: axios.get<ITestsdiachi>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<ITestsdiachi> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_TESTSDIACHI,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<ITestsdiachi> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_TESTSDIACHI,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<ITestsdiachi> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_TESTSDIACHI,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
