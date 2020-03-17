const formOrder = new FormHandler('#new-order');
const thOrdersElement = document.getElementById('thOrders');
const tBodyElement = document.getElementById('tBodyOrders')
const tableOrders = new Table(['coffee', 'email',
'size', 'flavor', 'strength'], thOrdersElement, tBodyElement);
const orders = new Orders();
formOrder.addHandler(function(order) {
    const res = orders.addOrder(order);
    if (!res) {
        return `order with email: ${order.email} already exists`
    }
    tableOrders.addRow(order);

});
