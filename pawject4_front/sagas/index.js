// sagas/index.js 
import { all,fork } from 'redux-saga/effects';

import foodSaga      from './food/foodSaga';
import reviewSaga      from './review/reviewSaga';
import foodSearchSaga      from './food/foodSearchSaga';

export default  function * rootSaga(){
  yield all([
    fork( foodSaga ) ,
    fork( reviewSaga ) ,
    fork(foodSearchSaga),
  ]);  
} 
