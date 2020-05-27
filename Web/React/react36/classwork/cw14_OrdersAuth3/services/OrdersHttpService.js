import {Axios} from "axios-observable";
import {catchError, map} from "rxjs/operators"
import {throwError} from "rxjs";

export default class OrdersHttpService {
    constructor(url, handler401) {
        if (!url) {
            throw Error("url doesn't exist");
        }
        this.url = url;
        this.handler = handler401;
    }
    errorHandler(error){
        if(error.response && error.response.status == 401){
            this.handler();
        }
        return throwError(error);
    }
    getOrders() {
        return Axios.get(this.url, {
            headers: {
               "Authorization": "Bearer " +
                   localStorage.getItem("accessToken")
            }
        })
            .pipe(map(response => response.data),catchError(this.errorHandler.bind(this)));
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
