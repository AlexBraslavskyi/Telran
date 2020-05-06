import {Axios} from "axios-observable";
import {map} from "rxjs/operators";

export default class EmployeesHttpService {

    constructor(url) {
        if(!url){
            throw Error ("Url doesn't exist")
        }
        this.url = url;
    }
    getEmployee(){
        return Axios.get(this.url).pipe(map(response=>response.data))
    }
    addEmployee(employee){
        return Axios.post(this.url,employee).toPromise()
    }

    deleteEmployee(id){
        return Axios.delete(this.url+id)
    }
}
