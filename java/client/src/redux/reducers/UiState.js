import {LOAD_PRODUCT_LIST, PRODUCT_CHANGE} from '../actionTypes';
import produce from 'immer';

export const initialState = {
    entities: {},
    products: [],
    carts: []
};
export default function (state = initialState, action) {

    switch (action.type) {
        case PRODUCT_CHANGE:
            return produce(state, draft => {
                let selectedProductId = action.productId;

                let c = draft.carts.find(o => o.id === selectedProductId);
                if (!c) {
                    c = { id: selectedProductId, weight: action.weight , isChecked: action.isChecked };
                    draft.carts.push(c);
                } else {
                    c.weight = action.weight;
                    c.isChecked = action.isChecked;
                }
            });
        case LOAD_PRODUCT_LIST:
            return produce(state, draft => {
                draft['products']= action.payload.result['products'];
                draft.entities['product'] = action.payload.entities.product;
            });
        default:
            return state;
    }
}
