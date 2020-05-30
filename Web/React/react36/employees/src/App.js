import React, {useEffect, useState} from 'react';
import './App.css';
import {BrowserRouter, Switch, Route, Redirect} from "react-router-dom";
import {EmployeesNav} from "./Components/EmployeesNav";
import Employees from "./Components/Employees";
import TitleStatistics from "./Components/TitleStatistics";
import {
    pathEmployees,
    pathTitleStatistics,
    pathSearch,
    pathSalaryStatistics,
    pathGenerations,
    pathLogin, pathLogout, pathWelcome
} from "./config/EmployeesConfig";
import EmployeesGenerations from "./Components/EmployeesGenerations";
import SalaryStatistics from "./Components/SalaryStatistics";
import EmployeesSearch from "./Components/EmployeesSearch";
import EmployeesHttpService from "./Service/EmployeesHttpService";
import AuthJwtService from "./Service/AuthJwtService";
import Login from "./Components/Login";
import Logout from "./Components/Logout";
import Welcome from "./Components/Welcome";
import EmployeesFirebaseService from "./Service/EmployeesFirebaseService";
import AuthFirebaseService from "./Service/AuthFirebaseService";



//json-server-auth employees.json -p 3500 -r routes.json

// Project Console: https://console.firebase.google.com/project/employees-b0d46/overview
//     Hosting URL: https://employees-b0d46.web.app


const App=()=>{
 const employeesService =
 //     new EmployeesHttpService('http://localhost:3500/employees/',() => {
 //     setUserData({})
 // }, () => {
 //     alert("Server is unavailable, please retry again later on")
 // });
     new EmployeesFirebaseService('employees');
    const authService =  new AuthFirebaseService();
        // new AuthJwtService('http://localhost:3500/');
    const [userData, setUserData] = useState(authService.getUserData());
    const userDataUpdateFn = (userData) => {
        setUserData(userData);
    }
    useEffect(()=>{
        // authService.register([
        //     {email:'user@tel-ran.co.il',password:'user'},
        //     {email:'admin@tel-ran.co.il',password:'admin'}
        // ])
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
