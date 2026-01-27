// import { combineReducers }  from  'redux';
import { combineReducers } from "@reduxjs/toolkit";
import foodReducer from "./food/foodReducer";
import foodReducer from "./review/reviewReducer";
import authReducer from "./user/authReducer";

const rootReducer = combineReducers({
  food: foodReducer,
  review: reviewReducer,
  auth: authReducer,
});

export default rootReducer;