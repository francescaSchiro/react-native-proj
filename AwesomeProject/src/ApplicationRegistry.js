import React from 'react';

import { AppRegistry } from 'react-native'
import Containers from './Containers/index'

import { Provider } from 'react-redux';
import { createStore, applyMiddleware, compose } from 'redux';

import createSagaMiddleware from 'redux-saga';

import reducer from './reducers/index'
import rootSaga from './sagas/index'

const sagaMiddleware = createSagaMiddleware()

const classicApplyMiddleware = applyMiddleware(
  sagaMiddleware,
);

const storeCompose = [classicApplyMiddleware /*, window.__REDUX_DEVTOOLS_EXTENSION__ && window.__REDUX_DEVTOOLS_EXTENSION__() */];

const store = createStore(reducer, compose(...storeCompose));

sagaMiddleware.run(rootSaga);

const addDecorators = (Component) => (props) => {
  return <Provider store={store}>
    <Component {...props} />
  </Provider>
}

AppRegistry.registerComponent('Home', () => addDecorators(Containers.Home));
AppRegistry.registerComponent('List', () => addDecorators(Containers.List));
AppRegistry.registerComponent('Detail', () => addDecorators(Containers.Detail));
