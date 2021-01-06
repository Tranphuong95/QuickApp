import axios from 'axios';
import { ICrudGetAction, ICrudGetAllAction, ICrudPutAction, ICrudDeleteAction } from 'react-jhipster';

import { cleanEntity } from 'app/shared/util/entity-utils';
import { REQUEST, SUCCESS, FAILURE } from 'app/shared/reducers/action-type.util';

import { IProductest, defaultValue } from 'app/shared/model/productest.model';

export const ACTION_TYPES = {
  FETCH_PRODUCTEST_LIST: 'productest/FETCH_PRODUCTEST_LIST',
  FETCH_PRODUCTEST: 'productest/FETCH_PRODUCTEST',
  CREATE_PRODUCTEST: 'productest/CREATE_PRODUCTEST',
  UPDATE_PRODUCTEST: 'productest/UPDATE_PRODUCTEST',
  DELETE_PRODUCTEST: 'productest/DELETE_PRODUCTEST',
  RESET: 'productest/RESET',
};

const initialState = {
  loading: false,
  errorMessage: null,
  entities: [] as ReadonlyArray<IProductest>,
  entity: defaultValue,
  updating: false,
  updateSuccess: false,
};

export type ProductestState = Readonly<typeof initialState>;

// Reducer

export default (state: ProductestState = initialState, action): ProductestState => {
  switch (action.type) {
    case REQUEST(ACTION_TYPES.FETCH_PRODUCTEST_LIST):
    case REQUEST(ACTION_TYPES.FETCH_PRODUCTEST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        loading: true,
      };
    case REQUEST(ACTION_TYPES.CREATE_PRODUCTEST):
    case REQUEST(ACTION_TYPES.UPDATE_PRODUCTEST):
    case REQUEST(ACTION_TYPES.DELETE_PRODUCTEST):
      return {
        ...state,
        errorMessage: null,
        updateSuccess: false,
        updating: true,
      };
    case FAILURE(ACTION_TYPES.FETCH_PRODUCTEST_LIST):
    case FAILURE(ACTION_TYPES.FETCH_PRODUCTEST):
    case FAILURE(ACTION_TYPES.CREATE_PRODUCTEST):
    case FAILURE(ACTION_TYPES.UPDATE_PRODUCTEST):
    case FAILURE(ACTION_TYPES.DELETE_PRODUCTEST):
      return {
        ...state,
        loading: false,
        updating: false,
        updateSuccess: false,
        errorMessage: action.payload,
      };
    case SUCCESS(ACTION_TYPES.FETCH_PRODUCTEST_LIST):
      return {
        ...state,
        loading: false,
        entities: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.FETCH_PRODUCTEST):
      return {
        ...state,
        loading: false,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.CREATE_PRODUCTEST):
    case SUCCESS(ACTION_TYPES.UPDATE_PRODUCTEST):
      return {
        ...state,
        updating: false,
        updateSuccess: true,
        entity: action.payload.data,
      };
    case SUCCESS(ACTION_TYPES.DELETE_PRODUCTEST):
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

const apiUrl = 'api/productests';

// Actions

export const getEntities: ICrudGetAllAction<IProductest> = (page, size, sort) => ({
  type: ACTION_TYPES.FETCH_PRODUCTEST_LIST,
  payload: axios.get<IProductest>(`${apiUrl}?cacheBuster=${new Date().getTime()}`),
});

export const getEntity: ICrudGetAction<IProductest> = id => {
  const requestUrl = `${apiUrl}/${id}`;
  return {
    type: ACTION_TYPES.FETCH_PRODUCTEST,
    payload: axios.get<IProductest>(requestUrl),
  };
};

export const createEntity: ICrudPutAction<IProductest> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.CREATE_PRODUCTEST,
    payload: axios.post(apiUrl, cleanEntity(entity)),
  });
  dispatch(getEntities());
  return result;
};

export const updateEntity: ICrudPutAction<IProductest> = entity => async dispatch => {
  const result = await dispatch({
    type: ACTION_TYPES.UPDATE_PRODUCTEST,
    payload: axios.put(apiUrl, cleanEntity(entity)),
  });
  return result;
};

export const deleteEntity: ICrudDeleteAction<IProductest> = id => async dispatch => {
  const requestUrl = `${apiUrl}/${id}`;
  const result = await dispatch({
    type: ACTION_TYPES.DELETE_PRODUCTEST,
    payload: axios.delete(requestUrl),
  });
  dispatch(getEntities());
  return result;
};

export const reset = () => ({
  type: ACTION_TYPES.RESET,
});
