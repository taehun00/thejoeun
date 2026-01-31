// import { combineReducers }  from  'redux';
import { combineReducers } from "@reduxjs/toolkit";
import foodReducer from "./food/foodReducer";
import reviewReducer from "./review/reviewReducer";
import foodSearchReducer from "./food/foodSearchReducer";
import authReducer from "./user/authReducer";
import faqReducer from "./support/faqReducer";
import csReducer from "./support/csReducer";
import likeReducer from "./likes/likeReducer";
import petReducer from "./pet/petReducer";
const rootReducer = combineReducers({
  food: foodReducer,
  review: reviewReducer,
  search: foodSearchReducer,
  auth: authReducer,
  faq: faqReducer,
  cs: csReducer,
  like: likeReducer,
  pet: petReducer,
});

export default rootReducer;