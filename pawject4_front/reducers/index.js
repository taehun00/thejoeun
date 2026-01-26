// reducers/index.js

import { combineReducers }  from  'redux';
import foodReducer          from  './food/foodReducer';

const rootReducer = combineReducers({
    food: foodReducer , 
});

export default rootReducer;
