import React, {Component} from 'react';
import './App.scss';
import {SERVER_URL} from "./config";
import store from './redux/store';
import {Provider} from 'react-redux';
import ProductsComponent from './component/products.component';

class App extends Component {

    render() {
        return (
            <Provider store={store}>
                <div className="App">
                    <ProductsComponent productType="CHEESE"/>
                </div>
            </Provider>
        );
    }
}

export default App;
