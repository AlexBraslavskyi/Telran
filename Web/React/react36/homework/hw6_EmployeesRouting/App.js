import React from 'react';
import './App.css';
import {BrowserRouter,Switch,Route} from "react-router-dom";
import EmployeesNav from "./homework/hw6_EmployeesRouting/Components/EmployeesNav";
import Employees from "./homework/hw6_EmployeesRouting/Components/Employees";
import TitleStatistics from "./homework/hw6_EmployeesRouting/Components/TitleStatistics";
import {pathEmployees,pathTitleStatistics,pathSearch,pathSalaryStatistics,pathGenerations} from "./homework/hw6_EmployeesRouting/config/EmployeesConfig";
import EmployeesGenerations from "./homework/hw6_EmployeesRouting/Components/EmployeesGenerations";
import SalaryStatistics from "./homework/hw6_EmployeesRouting/Components/SalaryStatistics";
import EmployeesSearch from "./homework/hw6_EmployeesRouting/Components/EmployeesSearch";
class App extends React.Component{
    constructor(props) {
        super(props);
        this.state = {
            employees:[]
        }
    }
    employeesUpdate(employees){
        this.setState({employees})
    }
    render() {
    return <BrowserRouter>
    <EmployeesNav></EmployeesNav>
    <Switch>
      <Route path={pathEmployees} exact render ={() =>
        {return <Employees employees = {this.state.employees} updateEmployeesFn={this.employeesUpdate.bind(this)}/>}}/>
      <Route path={pathTitleStatistics} exact render={() =>
        {return <TitleStatistics employees={this.state.employees}/>}}/>
      <Route path={pathGenerations} exact render={() =>
        {return <EmployeesGenerations  employees = {this.state.employees} updateEmployeesFn={this.employeesUpdate.bind(this)}/>}}/>
      <Route path={pathSalaryStatistics} exact render={() =>
        {return <SalaryStatistics employees={this.state.employees}/>}}/>
        <Route path={pathSearch} exact render={() =>
        {return <EmployeesSearch employees={this.state.employees}/>}}/>
              </Switch>
              </BrowserRouter>
      }
}
export default App;
