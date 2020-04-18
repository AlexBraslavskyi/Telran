import {maxSalary, titlesArr} from "../config/EmployeesConfig";
import  React from "react";
import {getInputElement, getInputElementBlur} from "../utils/InputElements";
import EmployeesTable from "./EmployeesTable";
let searchResult = [];
class EmployeesSearch extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            title:{value:'',controlError:0},
            salaryFrom:{value:'',controlError:0},
            salaryTo:{value:'',controlError:0},
            error:{}
        };
        this.formRef = null;
        this.submit = this.submit.bind(this);
        this.handlerTitle = this.handlerTitle.bind(this);
        this.handlerSalaryFrom = this.handlerSalaryFrom.bind(this);
        this.handlerSalaryTo = this.handlerSalaryTo.bind(this);
    }
    handlerTitle(event) {
        const title = event.target.value;
        this.state.title.controlError = 0;
        this.state.error.errorTitle = ''
        let flag = 0;
        titlesArr.forEach(function (t) {
                    if(t==title||title==""){
                        flag=1;
                        return flag;
                    }
        });
        if (flag==0) {
            this.state.error.errorTitle = 'Title '+ title+ ' wrong';
            this.state.title.value = title;
            this.state.title.controlError = -1;
        }else if (title=="") {
                this.state.error.errorTitle = '';
                this.state.title.value = title;
                this.state.title.controlError = 0;
        } else {
            this.state.title.value = title;
            this.state.title.controlError = 1;
           this.state.error.errorTitle = ''
        }
        this.setState({
            error:this.state.error,
            title:{value:this.state.title.value,controlError:this.state.title.controlError}
        })
    }

    handlerSalaryFrom(event) {
        const salaryFrom = event.target.value;
        this.state.salaryFrom.controlError = 0;
        this.state.error.errorSalaryFrom = ''
        if(salaryFrom < 0) {
            this.state.error.errorSalaryFrom = 'Interval can\'t be negative number';
            this.state.salaryFrom.value = salaryFrom;
            this.state.salaryFrom.controlError = -1;
        } else if(salaryFrom.indexOf(".") >= 0){
            this.state.error.errorSalaryFrom = 'Interval can\'t be fraction number';
            this.state.salaryFrom.value = salaryFrom;
            this.state.salaryFrom.controlError = -1;
        }else if (!salaryFrom) {
            this.state.error.errorSalaryFrom = '';
            this.state.title.value = "";
            this.state.title.controlError = 0;
        } else {
            this.state.salaryFrom.value = salaryFrom;
            this.state.salaryFrom.controlError = 1;
            this.state.error.errorSalaryFrom = ''
        }
        this.setState({
            error:this.state.error,
            salaryFrom:{value:this.state.salaryFrom.value,controlError:this.state.salaryFrom.controlError}
        })
    }
    handlerSalaryTo(event) {
        const salaryTo = event.target.value;
        this.state.salaryTo.controlError = 0;
        this.state.error.errorSalaryTo = ''
        if(salaryTo < 0) {
            this.state.error.errorSalaryTo = 'Interval can\'t be negative number';
            this.state.salaryTo.value = salaryTo;
            this.state.salaryTo.controlError = -1;
        } else if(salaryTo.indexOf(".") >= 0){
            this.state.error.errorSalaryTo = 'Interval can\'t be fraction number';
            this.state.salaryTo.value = salaryTo;
            this.state.salaryTo.controlError = -1;

        } else if (!salaryTo) {
            this.state.error.errorSalaryTo = '';
            this.state.title.value = "";
            this.state.title.controlError = 0;
        }else {
            this.state.salaryTo.value = salaryTo;
            this.state.salaryTo.controlError = 1;
            this.state.error.errorSalaryTo = ''
        }
        this.setState({
            error: this.state.error,
            salaryTo:{value:this.state.salaryTo.value,controlError:this.state.salaryTo.controlError}
        })

    }
    submit(event) {
        event.preventDefault();
        const employees = this.props.employees;
        searchResult = [];
        const titleProps = this.state.title.value;
        const salaryFromProps = +this.state.salaryFrom.value;
        const salaryToProps = +this.state.salaryTo.value;
        if (titleProps && salaryFromProps && salaryToProps && salaryFromProps < salaryToProps) {
            employees.forEach(function (employee) {
                if (employee.title == titleProps && employee.salary > salaryFromProps && employee.salary
                    < salaryToProps) {
                    searchResult.push(employee);
                }
            })
        }

        if (titleProps && salaryFromProps && salaryToProps && salaryFromProps > salaryToProps) {
            employees.forEach(function (employee) {
                if (employee.title == titleProps && employee.salary < salaryToProps) {
                    searchResult.push(employee);
                }
            })

        }
        if (!titleProps && salaryFromProps && salaryToProps && salaryFromProps > salaryToProps) {
            alert("Wrong parameters - salaryFrom can\'t be more then salaryTo")
        }

        if (!titleProps && salaryFromProps && salaryToProps && salaryFromProps < salaryToProps) {
            employees.forEach(function (employee) {
                if (employee.salary < salaryToProps&&employee.salary>salaryFromProps) {
                    searchResult.push(employee);
                }
            })
        }
        if (!titleProps && !salaryFromProps && salaryToProps) {
            employees.forEach(function (employee) {
                if (employee.salary <= salaryToProps) {
                    searchResult.push(employee);
                }
            })
        }
        if (!titleProps && salaryFromProps && !salaryToProps) {
            employees.forEach(function (employee) {
                if (employee.salary <= maxSalary&&employee.salary>=salaryFromProps) {
                    searchResult.push(employee);
                }
            })
        }
        if (titleProps && !salaryFromProps && salaryToProps) {
            employees.forEach(function (employee) {
                if (employee.salary < salaryToProps && employee.title == titleProps) {
                    searchResult.push(employee);
                }
            })
        }
        if (titleProps && !salaryFromProps && !salaryToProps) {
            employees.forEach(function (employee) {
                if (employee.title == titleProps) {
                    searchResult.push(employee);
                }
            })
        }
        if (titleProps && salaryFromProps && !salaryToProps) {
            employees.forEach(function (employee) {
                if (employee.title == titleProps&&employee.salary>=salaryFromProps) {
                    searchResult.push(employee);
                }
            })
        }
        if (!titleProps && !salaryFromProps && !salaryToProps) {
            employees.forEach(function (employee) {
                    searchResult.push(employee);
            })
        }
        this.state.resArr = searchResult;
        this.setState({
            resArr: this.state.resArr,
            title:{value:'',controlError:0},
            salaryFrom:{value:'',controlError:0},
            salaryTo:{value:'',controlError:0},
        })
        this.formRef.reset();
    }

    render() {
        let viewRes='';
        if(searchResult.length>0) {
            viewRes = <EmployeesTable employees = {searchResult}/>
        }

        return <div className="generations">
            <header className="card-header">
                <h3>Searching Employees</h3>
            </header>
            <form ref={(ref) => this.formRef = ref} className='form-group' onSubmit={this.submit}>
                <div className='firstLevel'>
            <div>
                {getInputElementBlur('','Enter title','Title',this.handlerTitle,
                    this.state.error.errorTitle,this.state.title)}
            </div>
            <div>
                {getInputElementBlur('number','Enter salaryFrom','Salary From',this.handlerSalaryFrom,
                    this.state.error.errorSalaryFrom,this.state.salaryFrom,)}
            </div>
            <div>
                {getInputElementBlur('number','Enter salaryTo','Salary To',this.handlerSalaryTo,
                    this.state.error.errorSalaryTo,this.state.salaryTo)}
            </div>
                </div>
                <div className='center'>
                <button type="submit" style={{cursor: 'pointer'}} className="btn btn-primary">
            <i style={{cursor: 'pointer'}} className="fa fa-search fa-2x"/>Search employee</button>
                </div>
            </form>
            <div>
                {viewRes}
            </div>
            </div>
        }
    }
export default EmployeesSearch;
