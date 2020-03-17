async function addOrderServer(order) {
    try  {
        await $.ajax(
            {
                url: this.url,
                type: 'POST',
                contentType: 'application/json',
                data: JSON.stringify(order)
            }
        )
        return 1;
    } catch(error) {
        if (error.readyState == 0 ||
            error.status == 404) {
           return -1;
        }
        return -2;
    }
}
async function removeOrderServer(email) {
    const url = this.url + encodeURIComponent(email);
    try {
        await $.ajax({
            url,
            type: 'DELETE'

        })
        return 1;
    } catch(error) {
        return -1;
    }
}
async function getAllServer(){
    try {
        const orders = await $.ajax({
            url: this.url,
            type: 'GET'
        })
        return orders;
    }catch(error) {
        return -1;
    }
}
class Orders {
    constructor(url) {
        if (!url) {
            throw new Error('no url passed');
        }
        this.url = url;
    }
    addOrder(order) {

       return addOrderServer.call(this, order);
    }
    removeOrder(email) {
        return removeOrderServer.call(this, email);
    }
    getAll() {
        return getAllServer.call(this);
    }
}
