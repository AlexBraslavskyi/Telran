import React, {useState} from 'react';
export const OrderForm = (props) => {

    const coffeeOptions = [
        'espresso', 'latte-maciate', 'americana', 'black',
        'cappuccino'
    ];
    const sizeOptions = ['Short', 'Tall', 'Grand'];
    const flavorOptions = ['none', 'vanilla', 'caramel'];
    let invalid;
    let setInvalid;
    let error;
    let setError;
    let order;
    let setOrder;
    [invalid, setInvalid] = useState(true);
    [error, setError] = useState('');
    [order, setOrder] = useState({
        coffee: coffeeOptions[0],
        email: '',
        size: sizeOptions[0],
        flavor: flavorOptions[0],
        strength: '50'});

    function handlerInputFields(event) {
        event.preventDefault();
        const fieldName = event.target.name;
        const fieldValue = event.target.value;
        setError('');
        order[fieldName] = fieldValue;
        setOrder(order);
        setInvalid(isInvalid())

    }
    function isInvalid() {
        return error || !order.email;
    }
    function getSelectOptions(optionStrings) {
        return optionStrings.map(os => {
            return <option key={os} value={os}>{os}</option>
        })
    }
    function onSubmit(event) {
        event.preventDefault();
        if(!props.addFn(order)) {
            setError(
                `order with email ${order.email} already exists`
            )
            setInvalid(true);
        }
    }

    return <div className="card">
        <header className="card-header">
            <h3>Order Input Fom</h3>
        </header>
        <div className="card-body">
            <form onSubmit={onSubmit}>
                <div className="form-group">
                    <label>Coffee</label>
                    <select className="form-control"
                            name="coffee" onChange={handlerInputFields}>
                        {getSelectOptions(coffeeOptions)}
                    </select>
                </div>
                <div className="form-group">
                    <label>email</label>
                    <input type="email" name={'email'}
                           className="form-control" onChange={handlerInputFields}/>
                    <div hidden={!error}
                         className="alert alert-danger">
                        {error}

                    </div>
                </div>
                <div className="form-group">
                    <label>Strength</label>
                    <input type="range" name={'strength'}
                           onChange={handlerInputFields}
                           className="form-control"/>

                </div>
                <div className="form-group">
                    <label>Size</label>
                    <select className="form-control"
                            name="size" onChange={handlerInputFields}>
                        {getSelectOptions(sizeOptions)}
                    </select>
                </div>
                <div className="form-group">
                    <label>Flavor</label>
                    <select className="form-control"
                            name="flavor" onChange={handlerInputFields}>
                        {getSelectOptions(flavorOptions)}
                    </select>
                </div>
                <button type="submit" disabled={invalid}>Add Order</button>
            </form>
        </div>
    </div>
}
