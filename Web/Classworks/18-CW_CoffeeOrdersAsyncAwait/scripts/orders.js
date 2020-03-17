class Orders {
    constructor() {
        this.orders = {}
    }
    getPromise(value) {
        return new Promise(function(resolve) {
            setTimeout(function() {
                resolve(value);
            }, 3000);
        })
    }
    addOrder(order) {
        if (this.orders[order.email]) {
            return this.getPromise(false);
        }
        this.orders[order.email] = order;
        return this.getPromise(true);
    }
    removeOrder(email) {
        delete this.orders[email];
        return this.getPromise();
    }
}
