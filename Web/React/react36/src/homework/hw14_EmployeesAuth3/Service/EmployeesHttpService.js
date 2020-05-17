import {Axios} from "axios-observable";
import {map} from "rxjs/operators";

export default class EmployeesHttpService {

    constructor(url) {
        if(!url){
            throw Error ("Url doesn't exist")
        }
        this.url = url;
    }
    getEmployees(){
        return Axios.get(this.url,{headers: {
            "Authorization": "Bearer " +
            localStorage.getItem("accessToken")
        }}).pipe(map(response=>response.data))
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
