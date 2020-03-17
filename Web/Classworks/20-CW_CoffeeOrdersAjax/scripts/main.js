const formOrder = new FormHandler('#new-order');
const url = 'http://localhost:3000/orders/'
const orders = new Orders(url);
const errorServerMessage = `Resource ${url} is unavailable`;
const tableOrders = new Table(['coffee', 'email',
'size', 'flavor', 'strength'], '#thOrders',
    '#tBodyOrders', 'email' ,
    function(email) {
    orders.removeOrder(email); //you don't need here 'then' functionality
        //because we don't run any code after removing Order
        //but in the Homework we do need 'then' as we should compute
        //salary budget after removing employee

    });
async function addOrderMain(order) {
    const addResult = await orders.addOrder(order);
    switch(addResult) {
        case 1: tableOrders.addRow(order); return '';
        case -1: return errorServerMessage;
        default: return `order with email ${order.email} 
        already exists`;
    }
}
formOrder.addHandler(addOrderMain);
let prevState;
async function displayAll() {

    const orderObjects = await orders.getAll();
    if(orderObjects == -1) {
        alert(errorServerMessage);
    } else {
        const ordersJSON = JSON.stringify(orderObjects);
        if (!prevState ||
            ordersJSON != prevState) {
            console.log('redrawing');
            tableOrders.clear();
            orderObjects.forEach(function(order) {
                tableOrders.addRow(order);
            })
            prevState = ordersJSON;
        }

    }
}
setInterval(displayAll, 500);
