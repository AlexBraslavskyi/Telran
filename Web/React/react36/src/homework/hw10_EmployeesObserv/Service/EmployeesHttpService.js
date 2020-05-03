import {Axios} from "axios-observable";
import {map} from "rxjs/operators";

export default class EmployeesHttpService {

    constructor(url) {
        if(!url){
            throw Error ("Url doesn't exist")
        }
        this.url = url;
    }
    getOrders(){
        return Axios.get(this.url).pipe(map(response=>response.data))
    }
    addOrder(order){
        return Axios.post(this.url,order)
    }

    deleteOrder(email){
        return Axios.delete(this.url+encodeURIComponent(email))
    }
}