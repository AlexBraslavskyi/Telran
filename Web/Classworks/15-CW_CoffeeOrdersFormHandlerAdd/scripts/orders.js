class Orders {
    constructor() {
        this.orders = {}
    }
    addOrder(order) {
        if (this.orders[order.email]) {
            return false;
        }
        this.orders[order.email] = order;
        return true;
    }
}
