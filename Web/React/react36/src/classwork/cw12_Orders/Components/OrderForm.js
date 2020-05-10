import React from "react";

export default class OrderForm extends React.Component {
    constructor(props) {
        super(props);
        this.invalid = true;
        this.coffeeOptions = ['espresso', 'latte', 'americana', 'black', 'cappuccino'];
        this.sizeOptions = ['Short', 'Tall', 'Grand'];
        this.flavorOptions = ['None', 'Vanilla', 'Caramel'];
        this.state = {
            error: '',
            order: {
                coffee: this.coffeeOptions[0],
                email: '',
                size: this.sizeOptions[0],
                flavor: this.flavorOptions[0],
                strength: '50'
            }
        }
        this.onSubmit= this.onSubmit.bind(this)
        this.handlerInputFields = this.handlerInputFields.bind(this);
    }

handlerInputFields(event){
      event.preventDefault();
        const fieldName = event.target.name;
        const fieldValue = event.target.value;
        const order = this.state.order;
        order[fieldName]=fieldValue;
        this.setState({error:'',order})

}

getSelectOptions(optionStrings){

        return optionStrings.map(os=>{
            return <option key={os} value={os}>{os}</option>
        })
}
onSubmit(event){
        event.preventDefault();
        if(!this.props.addFn(this.state.order)){
            this.setState({error:`order with email ${this.state.order.email} already exist`})
        }
}
validate(){
        this.invalid = this.state.error || !this.state.order.email  //checking
}
    render() {
this.validate();

    return <div className='card'>
<header className='card-header'></header>
        <h3>Order Input Form</h3>
        <div className='card-body'>
            <form onSubmit={this.onSubmit}>
                <div className='form-group' >
                <label>Coffee</label>
                <select className='form-control' name='coffee' onChange={this.handlerInputFields}>
                    {this.getSelectOptions(this.coffeeOptions)}
                </select>
                </div>
                <div className='form-group' >
                    <label>Size</label>
                    <select className='form-control' name='size' onChange={this.handlerInputFields}>
                        {this.getSelectOptions(this.sizeOptions)}
                    </select>
                </div>
                <div className='form-group' >
                    <label>Flavor</label>
                    <select className='form-control' name='flavor' onChange={this.handlerInputFields}>
                {this.getSelectOptions(this.flavorOptions)}
                    </select>
                </div>
                <div className='form-group' >
                    <label>Email</label>
                    <input className='form-control' type='email' name='email' onChange={this.handlerInputFields}/>
                    <div hidden={!this.state.error} className="alert alert-danger">
                {this.state.error}
                        {/*for all field where is error*/}
                    </div>
                </div>
                <div className='form-group'>
                    <label>Strength</label>
                    <input type='range' name={'strength'} onChange={this.handlerInputFields} className='form-control'/>
                </div>
                <button type="submit" disabled={this.invalid}>Add Order</button>
            </form>
        </div>
        </div>

}
}