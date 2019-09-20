import { put, call } from 'redux-saga/effects';
import { GETRequest } from './core';
import { resultBeers } from '../reducers/services';
import { Platform } from 'react-native';

function buildResource(endpoint) {
  const baseUrl = Platform.OS === 'ios' ? 'http://localhost:3000/' : 'http://10.0.2.2:3000/';
  return baseUrl + endpoint;
}

export function* getBeers(action) {
  const {response, error} = yield call(GETRequest, buildResource('getBeers'));
  if (response) {
    yield put(resultBeers(response));
  } else {
    console.log('Error ', error);
  }
}