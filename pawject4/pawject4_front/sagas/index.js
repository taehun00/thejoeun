// sagas/index.js 
import { all,fork } from 'redux-saga/effects';

import foodSaga      from './food/foodSaga';
import reviewSaga      from './review/reviewSaga';
import foodSearchSaga      from './food/foodSearchSaga';
import authSaga from "./user/authSaga";
import faqSaga from "./support/faqSaga";
import csSaga from "./support/csSaga";
import petdiseaseSaga from "./petdisease/petdiseaseSaga";
import testerSaga from "./tester/testerSaga";

export default  function * rootSaga(){
  yield all([
    fork( foodSaga ) ,
    fork( reviewSaga ) ,
    fork(foodSearchSaga),
    fork(authSaga), 
    fork(faqSaga),
    fork(csSaga),
    fork(petdiseaseSaga),
    fork(testerSaga),
  ]);  
} 

