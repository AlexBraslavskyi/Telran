import {Axios} from "axios-observable";
import {map,catchError} from "rxjs/operators";
import {throwError} from "rxjs";

export default class OrdersHttpService {

    constructor(url,handler401,noServerResponse) {
        if(!url){
            throw Error ("Url doesn't exist")
        }
        this.url = url;
        this.handler = handler401;
        this.noServerResponse = noServerResponse;
    }
    errorHandler(error){
        if (!error.response) {
            if (this.noServerResponse) {
                this.noServerResponse();
            }
        } else {
            if (error.response.status == 401) {
                if (this.handler) {
                    this.handler();
                }
            }
        }
        return throwError(error)
    }
    getEmployees(){
        return Axios.get(this.url, {
            headers: {
                "Authorization": "Bearer " +
                    localStorage.getItem("accessToken")
            }
        })
            .pipe(map(response => response.data),catchError(this.errorHandler.bind(this)));
    }

    addEmployee(employee){
        return Axios.post(this.url,employee,{headers: {
            "Authorization": "Bearer " +
            localStorage.getItem("accessToken")
        }}).toPromise()
    }

    deleteEmployee(id){
        return Axios.delete(this.url+id,{headers: {
            "Authorization": "Bearer " +
            localStorage.getItem("accessToken")
        }}).toPromise()
    }

}
