import React, {useState} from 'react';
import './homework/hw12_EmployeesAuth/App.css';
import {BrowserRouter,Switch,Route} from "react-router-dom";
import {EmployeesNav} from "./homework/hw12_EmployeesAuth/Components/EmployeesNav";
import Employees from "./homework/hw12_EmployeesAuth/Components/Employees";
import TitleStatistics from "./homework/hw12_EmployeesAuth/Components/TitleStatistics";
import {pathEmployees,pathTitleStatistics,pathSearch,pathSalaryStatistics,pathGenerations} from "./homework/hw12_EmployeesAuth/config/EmployeesConfig";
import EmployeesGenerations from "./homework/hw12_EmployeesAuth/Components/EmployeesGenerations";
import SalaryStatistics from "./homework/hw12_EmployeesAuth/Components/SalaryStatistics";
import EmployeesSearch from "./homework/hw12_EmployeesAuth/Components/EmployeesSearch";
import EmployeesHttpService from "./homework/hw12_EmployeesAuth/Service/EmployeesHttpService";
import AuthJwtService from "./homework/hw12_EmployeesAuth/Service/AuthJwtService";


// json-server-auth -p 3500 -w --id id employees.json
// json-server-auth -p 3500 employees.json


const App=()=>{
 const employeesService = new EmployeesHttpService('http://localhost:3500/employees/');
    const authService = new AuthJwtService('http://localhost:3500/');
    authService.register([
        {email:'user@telran.co.il',password:'user'},
        {email:'admin@telran.co.il',password:'admin'}
    ])
    authService.login({email:'user@telran.co.il',password:'user'}).subscribe(jwt=>{
        localStorage.setItem('accessToken',jwt)
        console.log(authService.getUsername())
    })

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
