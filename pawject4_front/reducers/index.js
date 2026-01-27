// import { combineReducers }  from  'redux';
import { combineReducers } from "@reduxjs/toolkit";
import foodReducer from "./food/foodReducer";
<<<<<<< HEAD
import reviewReducer from "./review/reviewReducer";
import foodSearchReducer from "./food/foodSearchReducer";
=======
import foodReducer from "./review/reviewReducer";
import authReducer from "./user/authReducer";
>>>>>>> 6697b552ab2ce5fabdf548efc5d864b99fd995db

const rootReducer = combineReducers({
  food: foodReducer,
  review: reviewReducer,
<<<<<<< HEAD
  search: foodSearchReducer
=======
  auth: authReducer,
>>>>>>> 6697b552ab2ce5fabdf548efc5d864b99fd995db
});

export default rootReducer;