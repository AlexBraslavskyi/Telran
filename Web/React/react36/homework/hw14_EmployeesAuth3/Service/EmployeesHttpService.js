import {Axios} from "axios-observable";
import {map,catchError} from "rxjs/operators";
import {throwError} from "rxjs";

export default class EmployeesHttpService {

    constructor(url,handler401) {
        if(!url){
            throw Error ("Url doesn't exist")
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
    getEmployees(){
        return Axios.get(this.url,{headers: {
            "Authorization": "Bearer " +
            localStorage.getItem("accessToken")
        }}) .pipe(map(response => response.data),catchError(this.errorHandler.bind(this)));
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
