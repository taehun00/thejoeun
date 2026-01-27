// reducers/index.js

import { combineReducers }  from  'redux';
import foodReducer          from  './food/foodReducer';
import authReducer from "./user/authReducer";

const rootReducer = combineReducers({
    food: foodReducer , 
    auth: authReducer,
});

export default rootReducer;
