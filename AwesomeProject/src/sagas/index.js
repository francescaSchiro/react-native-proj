import { fork, put, takeEvery } from 'redux-saga/effects';
import { getBeers as getBeersAction, ACTION_TYPE } from '../reducers/services';
import { getBeers } from './services';

function * initialSaga() {
  yield put(getBeersAction())
}

export default function* rootSaga() {
  yield fork(initialSaga);
  yield takeEvery(ACTION_TYPE.GET_BEERS, getBeers);
}