// reducers/index.js
import { combineReducers } from "@reduxjs/toolkit";
import foodReducer from "./food/foodReducer";
import foodReducer from "./review/reviewReducer";

const rootReducer = combineReducers({
  food: foodReducer,
  review: reviewReducer,
});

export default rootReducer;