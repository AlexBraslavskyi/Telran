const formOrder = new FormHandler('#new-order');
const orders = new Orders();
const tableOrders = new Table(['coffee', 'email',
'size', 'flavor', 'strength'], '#thOrders',
    '#tBodyOrders', 'email' ,
    function(email) {
    orders.removeOrder(email);
    });

formOrder.addHandler(function(order) {
    const res = orders.addOrder(order);
    if (!res) {
        return `order with email: ${order.email} already exists`
    }
    tableOrders.addRow(order);

});
