import React, {useState} from 'react';
import './homework/hw8_EmployeesHooks/App.css';
import {BrowserRouter,Switch,Route} from "react-router-dom";
import {EmployeesNav} from "./homework/hw8_EmployeesHooks/Components/EmployeesNav";
import Employees from "./homework/hw8_EmployeesHooks/Components/Employees";
import TitleStatistics from "./homework/hw8_EmployeesHooks/Components/TitleStatistics";
import {pathEmployees,pathTitleStatistics,pathSearch,pathSalaryStatistics,pathGenerations} from "./homework/hw8_EmployeesHooks/config/EmployeesConfig";
import EmployeesGenerations from "./homework/hw8_EmployeesHooks/Components/EmployeesGenerations";
import SalaryStatistics from "./homework/hw8_EmployeesHooks/Components/SalaryStatistics";
import EmployeesSearch from "./homework/hw8_EmployeesHooks/Components/EmployeesSearch";

const App=(props)=>{
 let employees;
 let setEmployees;
 [employees,setEmployees]=useState([]);

 const employeesUpdate = (employees) => {
  setEmployees(employees)
 }
    return <BrowserRouter>
    <EmployeesNav></EmployeesNav>
    <Switch>
      <Route path={pathEmployees} exact render ={() =>
        {return <Employees employees = {employees} updateEmployeesFn={employeesUpdate}/>}}/>
      <Route path={pathTitleStatistics} exact render={() =>
        {return <TitleStatistics employees={employees}/>}}/>
      <Route path={pathGenerations} exact render={() =>
        {return <EmployeesGenerations  employees = {employees} updateEmployeesFn={employeesUpdate}/>}}/>
      <Route path={pathSalaryStatistics} exact render={() =>
        {return <SalaryStatistics employees={employees}/>}}/>
        <Route path={pathSearch} exact render={() =>
        {return <EmployeesSearch employees={employees}/>}}/>
              </Switch>
              </BrowserRouter>
      }
export default App;
