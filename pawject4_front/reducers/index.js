// reducers/index.js
import { combineReducers } from "@reduxjs/toolkit";
import foodReducer from "./food/foodReducer";

const rootReducer = combineReducers({
  food: foodReducer,
});

export default rootReducer;