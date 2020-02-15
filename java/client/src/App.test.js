import React from 'react';
import ReactDOM from 'react-dom';
import App from './App';
import configureMockStore from 'redux-mock-store'
import thunk from 'redux-thunk'
import {initialState} from './redux/reducers/UiState';
import {fetchProducts, LOAD_PRODUCT_LIST, PRODUCT_CHANGE, productStateChanged} from './redux/actionTypes';
import fetchMock from 'fetch-mock'; // You can use any testing library

it('renders without crashing', () => {
    const div = document.createElement('div');
    ReactDOM.render(<App/>, div);
    ReactDOM.unmountComponentAtNode(div);
});

const middlewares = [thunk]
const mockStore = configureMockStore(middlewares)

describe('async actions', () => {
    afterEach(() => {
        fetchMock.restore()
    })
    it('make sure products are fetched to the action store', () => {
        fetchMock.getOnce('http://localhost:8080/product/list?productType=CHEESE', {
            body: {
                products: [
                    { name: 'hello', id: 1 }
                ]
            },
            headers: {'content-type': 'application/json'}
        });
        const store = mockStore(initialState);
        return store.dispatch(fetchProducts('product', 'CHEESE')).then(() => {
            expect(store.getActions()).toHaveLength(1);
            expect(store.getActions()[0].type).toEqual(LOAD_PRODUCT_LIST);
        });
    });

    it('make sure product is selected to the action store', () => {

        const store = mockStore(initialState);
        store.dispatch(productStateChanged(100, 12345, true));

        expect(store.getActions()).toHaveLength(1);
        let action = store.getActions()[0];
        expect(action.type).toEqual(PRODUCT_CHANGE);
        expect(action.productId).toEqual(100);
        expect(action.weight).toEqual(12345);
        expect(action.isChecked).toEqual(true);
    });
});

