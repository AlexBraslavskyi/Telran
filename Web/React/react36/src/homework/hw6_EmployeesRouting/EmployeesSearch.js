import {maxSalary, minSalary, nameMinLength, titlesArr} from "./EmployeesConfig";
import  React from "react";
import {getInputElement, getErrorMessage, getSelectElement, getButtonElement} from "./InputElements";
import EmployeesTable from "./EmployeesTable";

class EmployeesSearch extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            employeeSearch:{title:'',salaryFrom:0,salaryTo:0},
            error:{}
        }
        this.employees = this.props.employees;
        this.handlerTitle = this.handlerTitle.bind(this);
        this.handlerSalaryFrom = this.handlerSalaryFrom.bind(this);
        this.handlerSalaryTo = this.handlerSalaryTo.bind(this);
    }
    handlerTitle(event) {
        const title = event.target.value;
        console.log(title)
        const error = this.state.error;
        error.errorTitle = '';
        let flag = 0;
        titlesArr.forEach(function (t) {
                    if(t==title){
                        flag=1;
                        return flag;
                    }
        })
        if (flag==0) {
            error.errorTitle = 'Title '+ title+ ' wrong';
        } else {
            this.state.employeeSearch.title = title;
        }
        this.setState({
            error:this.state.error
        })
    }

    handlerSalaryFrom(event) {
        const salaryFrom = event.target.value;
        const error = this.state.error;
        error.errorSalaryFrom = '';
        if(+salaryFrom < 0) {
            error.errorSalaryFrom = 'Interval can\'t be negative number';
        } else if(salaryFrom.indexOf(".") >= 0){
            error.errorSalaryFrom = 'Interval can\'t be fraction number';
        } else {
            this.state.employeeSearch.salaryFrom = salaryFrom;
        }
        this.setState({
            error: this.state.error,
        })
    }
    handlerSalaryTo(event) {
        const salaryTo = event.target.value;
        const error = this.state.error;
        error.errorSalaryTo = '';
        if(salaryTo < 0) {
            error.errorSalaryTo = 'Interval can\'t be negative number';
        } else if(salaryTo.indexOf(".") >= 0){
            error.errorSalaryTo = 'Interval can\'t be fraction number';
        }  else if(salaryTo.substr(0,1)=="0") {
            error.errorSalaryTo = 'Interval can\'t start from 0';
        } else {
            this.state.employeeSearch.salaryTo = salaryTo;
        }
        this.setState({
            error: this.state.error,
        })
    }
    search() {
        const employees = this.employees;
        const searchResult = [];
        const titleProps = this.state.employeeSearch.title;
        const salaryFromProps = this.state.employeeSearch.salaryFrom;
        const salaryToProps = this.state.employeeSearch.salaryTo;
        if(titleProps&&salaryFromProps&&salaryToProps&&salaryFromProps<salaryToProps){
            employees.forEach(function (employee) {
        if(employee.title==titleProps&&employee.salary>salaryFromProps&&employee.salary
          <salaryToProps){
         searchResult.push(employee);
        }
            })
        }

        if(titleProps&&salaryFromProps&&salaryToProps&&salaryFromProps>salaryToProps){
            employees.forEach(function (employee) {
                if(employee.title==titleProps&&employee.salary<salaryToProps){
                    searchResult.push(employee);
                }
            })
        }
        if(!titleProps&&salaryFromProps&&salaryToProps&&salaryFromProps>salaryToProps){
            alert("Wrong parameters")
                }

    if(!titleProps&&salaryFromProps&&salaryToProps&&salaryFromProps<salaryToProps){
    employees.forEach(function (employee) {
        if(employee.salary<salaryToProps){
            searchResult.push(employee);
        }
    })
}
        if(!titleProps&&!salaryFromProps&&salaryToProps){
            employees.forEach(function (employee) {
                if(employee.salary<=salaryToProps){
                    searchResult.push(employee);
                }
            })
        }
        if(!titleProps&&salaryFromProps&&!salaryToProps){
            employees.forEach(function (employee) {
                if(employee.salary<=maxSalary){
                    searchResult.push(employee);
                }
            })
        }
        if(titleProps&&!salaryFromProps&&salaryToProps){
            employees.forEach(function (employee) {
                if(employee.salary<salaryToProps&&employee.title==titleProps){
                    searchResult.push(employee);
                }
            })
        }
        if(titleProps&&!salaryFromProps&&!salaryToProps){
            employees.forEach(function (employee) {
                if(employee.title==titleProps){
                    searchResult.push(employee);
                }
            })
        }
        this.employees=searchResult;
        this.setState({
            employees: this.employees,
        })
        console.log(this.employees)
        return  this.employees;
    }
    viewTable(props){

       return <EmployeesTable employees = {props} />
    }
    render() { console.log(this.employees)
        return <div className="generations">
            <header className="card-header">
                <h3>Searching Employees</h3>
            </header>
            <div>
                {getErrorMessage(this.state.error.errorTitle)}
                {getInputElement('','title','Title',this.handlerTitle)}
            </div>
            <div>
                {getErrorMessage(this.state.error.errorSalaryFrom)}
                {getInputElement('number','salaryFrom','Value Salary From',this.handlerSalaryFrom)}
            </div>
            <div>
                {getErrorMessage(this.state.error.errorSalaryTo)}
                {getInputElement('number','salaryTo','Value Salary To',this.handlerSalaryTo)}
            </div>
            <i style={{cursor: 'pointer'}}
               className="fa fa-search fa-3x"
               onClick={this.search.bind(this)}/>
            <div>
                {this.viewTable(this.employees)}
            </div>
        </div>

    }
}
export default EmployeesSearch;
