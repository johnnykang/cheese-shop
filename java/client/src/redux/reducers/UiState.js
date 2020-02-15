import {SELECTED_WIDGET, WIDGET_STATE_CHANGED, WIDGET_LANGUAGE_CHANGED, UPDATE_WIDGET_VERSION, WIDGET_AJAX_START, WIDGET_AJAX_END, SELECT_PRODUCT, UNSELECT_PRODUCT, LOAD_PRODUCT_LIST, PRODUCT_CHANGE} from '../actionTypes';
import produce from 'immer';

const urlParams = new URLSearchParams(window.location.search);
const siteId = urlParams.get('site_id');
const lang = urlParams.get('lang');
const env = urlParams.get('env');


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
