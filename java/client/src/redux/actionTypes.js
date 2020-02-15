import {SERVER_URL} from '../config';
import {normalize, schema} from 'normalizr';

export const SELECT_PRODUCT = "SELECT_PRODUCT";
export const UNSELECT_PRODUCT = "UNSELECT_PRODUCT";
export const LOAD_PRODUCT_LIST = "LOAD_PRODUCT_LIST";
export const PRODUCT_CHANGE = "PRODUCT_CHANGE";

const product_schema = new schema.Entity('product');

export function fetchProducts(serviceName, productType) {
    return (dispatch) => {
        return fetch(`${SERVER_URL}/${serviceName}/list?productType=${productType}`)
            .then(r => {
                return r.json()
            })
            .then(message => {
                let products = normalize(message, {
                    products: [product_schema]
                });

                dispatch({
                    type: LOAD_PRODUCT_LIST,
                    payload: products
                });
            })
            .catch(e => console.error(e))
    }
}

export function productStateChanged(productId, weight, isChecked) {
    return (dispatch) => {
        dispatch({
            type: PRODUCT_CHANGE,
            productId: productId,
            weight: weight,
            isChecked: isChecked
        });
    }
}
