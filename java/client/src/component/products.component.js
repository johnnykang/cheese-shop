import * as React from 'react';
import Product from './product.component';
import {Button, Card, List, Modal} from 'antd';
import {connect} from 'react-redux';
import {SERVER_URL} from '../config';
import {normalize, schema} from 'normalizr';
import {LOAD_PRODUCT_LIST} from '../redux/actionTypes';

const product_schema = new schema.Entity('product');


class ProductsComponent extends React.Component {

    serviceName = 'product';

    state = {
        modelVisible: false
    };


    constructor(props, context, serviceName) {
        super(props, context);
    }

    componentDidMount() {
        fetch(`${SERVER_URL}/${this.serviceName}/list?productType=${this.props.productType}`)
            .then(r => {
                return r.json()
            })
            .then(message => {
                let products = normalize(message, {
                    products: [product_schema]
                });

                this.props.dispatch({
                    type: LOAD_PRODUCT_LIST,
                    payload: products
                });
            })
            .catch(e => console.error(e))
    }

    handleOk = e => {
        this.setState({
            modelVisible: false,
        });
    };

    handleCancel = e => {
        this.setState({
            modelVisible: false,
        });
    };

    render() {
        const {products, entities, carts} = {...this.props};


        let selectedItems = carts.filter((selectedItem) => selectedItem.isChecked).map((selectedItem) => {

            let product = entities['product'][selectedItem.id];
            return {
                totalPrice: product.price * selectedItem.weight,
                name: product.name,
                weight: selectedItem.weight
            }
        });

        return (
            <div><Card title="CHEESE" actions={[
                <Button type="primary" shape="round" icon="download" onClick={(e) => this.setState({modelVisible: true})}>
                    Buy Now
                </Button>,
            ]}>

                {products.map(value => {
                    return (<Product key={value} thisproduct={entities.product[value]}/>)
                })}
            </Card>

                <Modal
                    title="Cart"
                    visible={this.state.modelVisible}
                    onCancel={this.handleCancel}
                    onOk={this.handleOk}
                >
                    <List
                        itemLayout="horizontal"
                        dataSource={selectedItems}
                        renderItem={item => (
                            <List.Item>
                                <List.Item.Meta
                                    title={item.name}
                                    description={'Total weight: ' + item.weight + 'kg\t' + 'Total price: $' + item.totalPrice}
                                />
                            </List.Item>
                        )}
                    />
                </Modal>
            </div>)
    }
}

const mapStateToProps = state => {
    return {
        ...state.ui_state
    }

};
export default connect(mapStateToProps)(ProductsComponent);
