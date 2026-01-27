// reducers/index.js
import { combineReducers } from "@reduxjs/toolkit";
import foodReducer from "./food/foodReducer";
import reviewReducer from "./review/reviewReducer";
import foodSearchReducer from "./food/foodSearchReducer";

const rootReducer = combineReducers({
  food: foodReducer,
  review: reviewReducer,
  search: foodSearchReducer
});

export default rootReducer;