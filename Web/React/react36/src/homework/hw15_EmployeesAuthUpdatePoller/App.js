import React, {useEffect, useState} from 'react';
import './App.css';
import {BrowserRouter, Switch, Route, Redirect} from "react-router-dom";
import {EmployeesNav} from "./homework/hw15_EmployeesAuthUpdatePoller/Components/EmployeesNav";
import Employees from "./homework/hw15_EmployeesAuthUpdatePoller/Components/Employees";
import TitleStatistics from "./homework/hw15_EmployeesAuthUpdatePoller/Components/TitleStatistics";
import {
    pathEmployees,
    pathTitleStatistics,
    pathSearch,
    pathSalaryStatistics,
    pathGenerations,
    pathLogin, pathLogout, pathWelcome
} from "./homework/hw15_EmployeesAuthUpdatePoller/config/EmployeesConfig";
import EmployeesGenerations from "./homework/hw15_EmployeesAuthUpdatePoller/Components/EmployeesGenerations";
import SalaryStatistics from "./homework/hw15_EmployeesAuthUpdatePoller/Components/SalaryStatistics";
import EmployeesSearch from "./homework/hw15_EmployeesAuthUpdatePoller/Components/EmployeesSearch";
import EmployeesHttpService from "./homework/hw15_EmployeesAuthUpdatePoller/Service/EmployeesHttpService";
import AuthJwtService from "./homework/hw15_EmployeesAuthUpdatePoller/Service/AuthJwtService";
import Login from "./homework/hw15_EmployeesAuthUpdatePoller/Components/Login";
import Logout from "./homework/hw15_EmployeesAuthUpdatePoller/Components/Logout";
import Welcome from "./homework/hw15_EmployeesAuthUpdatePoller/Components/Welcome";


//json-server-auth employees.json -p 3500 -r routes.json

const App=()=>{
 const employeesService = new EmployeesHttpService('http://localhost:3500/employees/',()=>{
     setUserData({})
 });
    const authService = new AuthJwtService('http://localhost:3500/');
    authService.register([
        {email:'user@tel-ran.co.il',password:'user'},
        {email:'admin@tel-ran.co.il',password:'admin'}
    ])
    const [userData, setUserData] = useState(authService.getUserData());
    const userDataUpdateFn = (userData) => {
        setUserData(userData);
    }
    useEffect(()=>{
        authService.getUserData().subscribe(userData =>setUserData(userData));
    },[])

    return <BrowserRouter>
    <EmployeesNav userData={userData}/>
    <Switch>
        <Route path={pathWelcome} exact render ={() =>
        {return !userData.username ? <Welcome/> :
            <Redirect to={pathEmployees}/>}}/>
      <Route path={pathEmployees} exact render ={() =>
        {return userData.username ? <Employees userData={userData} employeesService = {employeesService}/> :
        <Redirect to={pathWelcome}/>}}/>
      <Route path={pathTitleStatistics} exact render={() =>
        {return userData.username ? <TitleStatistics employeesService={employeesService}/>:
            <Redirect to={pathWelcome}/>}}/>
      <Route path={pathGenerations} exact render={() =>
        {return userData.isAdmin ? <EmployeesGenerations  employeesService = {employeesService}/>:
            <Redirect to={pathWelcome}/>}}/>
      <Route path={pathSalaryStatistics} exact render={() =>
        {return userData.username ? <SalaryStatistics employeesService={employeesService}/>:
            <Redirect to={pathWelcome}/>}}/>
        <Route path={pathSearch} exact render={() =>
        {return userData.username ? <EmployeesSearch employeesService={employeesService}/>:
            <Redirect to={pathWelcome}/>}}/>
        <Route path={pathLogin} exact render={() =>
        {return !userData.username ? <Login authService={authService} userDataUpdateFn={userDataUpdateFn}/> :
                <Redirect to={pathEmployees}/>}}/>
        <Route path={pathLogout} exact render={() =>
        {return userData.username ? <Logout authService={authService} userDataUpdateFn={userDataUpdateFn}/> :
                    <Redirect to={pathWelcome}/>}}/>
              </Switch>
              </BrowserRouter>
      }
export default App;
