import React, {useState} from 'react';
import './homework/hw10_EmployeesObserv/App.css';
import {BrowserRouter,Switch,Route} from "react-router-dom";
import {EmployeesNav} from "./homework/hw10_EmployeesObserv/Components/EmployeesNav";
import Employees from "./homework/hw10_EmployeesObserv/Components/Employees";
import TitleStatistics from "./homework/hw10_EmployeesObserv/Components/TitleStatistics";
import {pathEmployees,pathTitleStatistics,pathSearch,pathSalaryStatistics,pathGenerations} from "./homework/hw10_EmployeesObserv/config/EmployeesConfig";
import EmployeesGenerations from "./homework/hw10_EmployeesObserv/Components/EmployeesGenerations";
import SalaryStatistics from "./homework/hw10_EmployeesObserv/Components/SalaryStatistics";
import EmployeesSearch from "./homework/hw10_EmployeesObserv/Components/EmployeesSearch";
import EmployeesHttpService from "./homework/hw10_EmployeesObserv/Service/EmployeesHttpService";



// json-server-auth -p 3500 -w --id id employees.json


const App=()=>{
 const employeesService = new EmployeesHttpService('http://localhost:3500/employees/');
 
    return <BrowserRouter>
    <EmployeesNav></EmployeesNav>
    <Switch>
      <Route path={pathEmployees} exact render ={() =>
        {return <Employees employeesService = {employeesService}/>}}/>
      <Route path={pathTitleStatistics} exact render={() =>
        {return <TitleStatistics employeesService={employeesService}/>}}/>
      <Route path={pathGenerations} exact render={() =>
        {return <EmployeesGenerations  employeesService = {employeesService}/>}}/>
      <Route path={pathSalaryStatistics} exact render={() =>
        {return <SalaryStatistics employeesService={employeesService}/>}}/>
        <Route path={pathSearch} exact render={() =>
        {return <EmployeesSearch employeesService={employeesService}/>}}/>
              </Switch>
              </BrowserRouter>
      }
export default App;
