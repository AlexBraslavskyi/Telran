import React from 'react'

const Details = (props) => {
    const items = props.items;
    const backFn = props.backFn;
    return <div id="mobile-card">
        <div className='card'>
            <h4 className='card-header'> -Order details- </h4>
            {items.map((item) => {
                return <div key={item.id}><h5 style={{marginLeft: '20px'}}>Title : {item.title}</h5>
                    <h5 style={{marginLeft: '20px'}}>Price : {item.price}</h5>
                    <h5 style={{marginLeft: '20px'}}>Quantity : {item.quantity}</h5>
                </div>
            })}
            <div><i className="fa fa-arrow-left fa-3x" style={{cursor: 'pointer', marginLeft: '20px'}}
                    onClick={() => {
                        backFn()
                    }}/>
            </div>
        </div>
    </div>
}
export default Details;