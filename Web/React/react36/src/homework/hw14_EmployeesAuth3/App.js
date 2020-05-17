import React, {useState} from 'react';
import './App.css';
import {BrowserRouter, Switch, Route, Redirect} from "react-router-dom";
import {EmployeesNav} from "./homework/hw13_EmployeesAuth2/Components/EmployeesNav";
import Employees from "./homework/hw13_EmployeesAuth2/Components/Employees";
import TitleStatistics from "./homework/hw13_EmployeesAuth2/Components/TitleStatistics";
import {
    pathEmployees,
    pathTitleStatistics,
    pathSearch,
    pathSalaryStatistics,
    pathGenerations,
    pathLogin, pathLogout, pathWelcome
} from "./homework/hw13_EmployeesAuth2/config/EmployeesConfig";
import EmployeesGenerations from "./homework/hw13_EmployeesAuth2/Components/EmployeesGenerations";
import SalaryStatistics from "./homework/hw13_EmployeesAuth2/Components/SalaryStatistics";
import EmployeesSearch from "./homework/hw13_EmployeesAuth2/Components/EmployeesSearch";
import EmployeesHttpService from "./homework/hw13_EmployeesAuth2/Service/EmployeesHttpService";
import AuthJwtService from "./homework/hw13_EmployeesAuth2/Service/AuthJwtService";
import Login from "./homework/hw13_EmployeesAuth2/Components/Login";
import Logout from "./homework/hw13_EmployeesAuth2/Components/Logout";
import Welcome from "./homework/hw13_EmployeesAuth2/Components/Welcome";


// json-server-auth -p 3500 -w --id id employees.json
// json-server-auth -p 3500 employees.json
//json-server-auth employees.json -p 3500 -r routes.json

const App=()=>{
 const employeesService = new EmployeesHttpService('http://localhost:3500/employees/');
    const authService = new AuthJwtService('http://localhost:3500/');
    authService.register([
        {email:'user@tel-ran.co.il',password:'user'},
        {email:'admin@tel-ran.co.il',password:'admin'}
    ])
    const [username, setUsername] = useState(authService.getUsername());
    const usernameUpdateFn = (username) => {
        setUsername(username);
    }

    return <BrowserRouter>
    <EmployeesNav username={username}></EmployeesNav>
    <Switch>
        <Route path={pathWelcome} exact render ={() =>
        {return !username ? <Welcome/> :
            <Redirect to={pathEmployees}/>}}/>
      <Route path={pathEmployees} exact render ={() =>
        {return username ? <Employees employeesService = {employeesService}/> :
        <Redirect to={pathWelcome}/>}}/>
      <Route path={pathTitleStatistics} exact render={() =>
        {return username ? <TitleStatistics employeesService={employeesService}/>:
            <Redirect to={pathWelcome}/>}}/>
      <Route path={pathGenerations} exact render={() =>
        {return username ? <EmployeesGenerations  employeesService = {employeesService}/>:
            <Redirect to={pathWelcome}/>}}/>
      <Route path={pathSalaryStatistics} exact render={() =>
        {return username ? <SalaryStatistics employeesService={employeesService}/>:
            <Redirect to={pathWelcome}/>}}/>
        <Route path={pathSearch} exact render={() =>
        {return username ? <EmployeesSearch employeesService={employeesService}/>:
            <Redirect to={pathWelcome}/>}}/>
        <Route path={pathLogin} exact render={() =>
        {return !username ? <Login authService={authService} usernameUpdateFn={usernameUpdateFn}/> :
                <Redirect to={pathEmployees}/>}}/>
        <Route path={pathLogout} exact render={() =>
        {return username ? <Logout authService={authService} usernameUpdateFn={usernameUpdateFn}/> :
                    <Redirect to={pathWelcome}/>}}/>
              </Switch>
              </BrowserRouter>
      }
export default App;
