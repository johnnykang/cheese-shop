import rootReducer from './reducers/rootReducer';
import {applyMiddleware, createStore} from 'redux';
import { composeWithDevTools } from 'redux-devtools-extension';
import thunk from 'redux-thunk';

const store = createStore(rootReducer, composeWithDevTools(applyMiddleware(thunk)));

/*
store.subscribe(throttle(() => {
    store.getState();
}, 2000));
*/

export default store;
