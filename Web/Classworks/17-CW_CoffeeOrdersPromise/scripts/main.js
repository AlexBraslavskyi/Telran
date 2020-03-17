const formOrder = new FormHandler('#new-order');
const orders = new Orders();
const tableOrders = new Table(['coffee', 'email',
'size', 'flavor', 'strength'], '#thOrders',
    '#tBodyOrders', 'email' ,
    function(email) {
    orders.removeOrder(email); //you don't need here 'then' functionality
        //because we don't run any code after removing Order
        //but in the Homework we do need 'then' as we should compute
        //salary budget after removing employee

    });

formOrder.addHandler(function(order) {

   return orders.addOrder(order)
        .then(function(res) {
            if (!res) {
                return `order with email: ${order.email} already exists`
            }
            tableOrders.addRow(order);
            return '';
        });


});
