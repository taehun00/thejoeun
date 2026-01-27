// sagas/index.js 
import { all,fork } from 'redux-saga/effects';

import foodSaga      from './food/foodSaga';
import authSaga from "./user/authSaga";


export default  function * rootSaga(){
  yield all([
    fork( foodSaga ) ,
    fork(authSaga), 
  ]);  
} 
