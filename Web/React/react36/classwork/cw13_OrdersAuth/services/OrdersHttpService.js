import {Axios} from "axios-observable";
import {map} from "rxjs/operators"
export default class OrdersHttpService {
    constructor(url) {
        if (!url) {
            throw Error("url doesn't exist");
        }
        this.url = url;
    }
    getOrders() {
        return Axios.get(this.url, {
            headers: {
               "Authorization": "Bearer " +
                   localStorage.getItem("accessToken")
            }
        })
            .pipe(map(response => response.data));
    }
    addOrder(order) {
        return Axios.post(this.url, order,{
            headers: {
                "Authorization": "Bearer " +
                    localStorage.getItem("accessToken")
            }
        })


    }
    deleteOrder(email) {
        return Axios.delete(this.url +
            encodeURIComponent(email), {
            headers: {
                "Authorization": "Bearer " +
                    localStorage.getItem("accessToken")
            }
        })


    }
}
