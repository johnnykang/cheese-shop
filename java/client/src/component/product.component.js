import * as React from 'react';
import {Avatar, Card, Checkbox, Icon, InputNumber} from 'antd';
import {connect} from 'react-redux';
import {PRODUCT_CHANGE, productStateChanged, SELECT_PRODUCT, UNSELECT_PRODUCT} from '../redux/actionTypes';

const gridStyle = {
    width: '25%',
    textAlign: 'center',
};


class Product extends React.Component {


    constructor(props, context) {
        super(props, context);
        this.state = {
            weight: 1,
            isChecked: false
        }
    }

    handleCheckboxEvt(e) {
        const checked = e.target.checked;
        this.props.dispatch({
            type: PRODUCT_CHANGE,
            isChecked: checked,
            productId: this.props.thisproduct.id,
            weight: this.state['weight']
        });

        this.state['isChecked'] = checked;
    }

    handleWeightEvt(evt) {
        let value = evt;

        this.state['weight'] = evt;

        this.props.dispatch(productStateChanged(
            this.props.thisproduct.id,
            value,
            this.state['isChecked']));
    }

    render() {
        let {thisproduct} = {...this.props};
        return (
            <Card.Grid style={gridStyle}>
                <Card
                    style={{width: 300}}
                    cover={
                        <img alt="example" src={thisproduct.image}/>
                    }
                    actions={[
                        <Checkbox onChange={(evt) => this.handleCheckboxEvt(evt)}/>,
                        <div>Weight <InputNumber min={1} max={10} defaultValue={1} onChange={(evt) => this.handleWeightEvt(evt)}/><span>kg</span></div>
                    ]}>
                    <Card.Meta title={this.props.thisproduct.name} description={'$' + thisproduct.price}/>
                </Card>


            </Card.Grid>
        )
    }
}

const mapStateToProps = state => {
    return {
        ...state.ui_state
    }

};

export default connect(mapStateToProps)(Product);
