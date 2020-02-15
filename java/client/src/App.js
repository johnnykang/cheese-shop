import React, {Component} from 'react';
import './App.scss';
import {SERVER_URL} from "./config";
import store from './redux/store';
import {Provider} from 'react-redux';
import ProductsComponent from './component/products.component';

class App extends Component {

    //<1>
    state = {
        name: 'World',
        message: null
    };

    //<2>
    setName = e => this.setState({name: e.target.value});

    //<3>
    getMessage = e => {
        e.preventDefault();
        fetch(`${SERVER_URL}/${this.state.name}`)
            .then(r => r.text())
            .then(message => this.setState({message}))
            .catch(e => console.error(e))
    };

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
