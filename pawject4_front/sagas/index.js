// sagas/index.js 
import { all,fork } from 'redux-saga/effects';

import foodSaga      from './food/foodSaga';


export default  function * rootSaga(){
  yield all([
    fork( foodSaga ) ,
  ]);  
} 
