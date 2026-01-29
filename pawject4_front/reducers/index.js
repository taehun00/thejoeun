// import { combineReducers }  from  'redux';
import { combineReducers } from "@reduxjs/toolkit";
import foodReducer from "./food/foodReducer";
import reviewReducer from "./review/reviewReducer";
import foodSearchReducer from "./food/foodSearchReducer";
import authReducer from "./user/authReducer";
import faqReducer from "./support/faqReducer";
import csReducer from "./support/csReducer";
import petdiseaseReducer from "./petdisease/petdiseaseReducer";
const rootReducer = combineReducers({
  food: foodReducer,
  review: reviewReducer,
  search: foodSearchReducer,
  auth: authReducer,
  faq: faqReducer,
  cs: csReducer,
  petdisease: petdiseaseReducer,
});

export default rootReducer;