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
import Login from "./Components/Login";
import Logout from "./Components/Logout";
import Welcome from "./Components/Welcome";
import EmployeesFirebaseService from "./Service/EmployeesFirebaseService";
import AuthFirebaseService from "./Service/AuthFirebaseService";
import {actionUserData,actionEmployees} from "./store/actions";
import {useDispatch, useSelector} from "react-redux";


//json-server-auth employees.json -p 3500 -r routes.json

// Project Console: https://console.firebase.google.com/project/employees-b0d46/overview
//     Hosting URL: https://employees-b0d46.web.app


const App=()=>{
 const employeesService =
     new EmployeesFirebaseService('employees');
    const authService =  new AuthFirebaseService();
    const dispatch = useDispatch();//hook for possibility to updating global store
    const userData = useSelector(state=>
        state.userData);//hook for getting global store
    useEffect(()=>{
        authService.getUserData().subscribe(userData=>{
            dispatch(actionUserData(userData))//update state
            if(userData.username){
                employeesService.getEmployees().subscribe(employees=>{
                    dispatch(actionEmployees(employees));
                },error => alert(JSON.stringify(error)))
            }
        })

    },[]);

    return <BrowserRouter>
    <EmployeesNav userData={userData}/>
    <Switch>
        <Route path={pathWelcome} exact render ={() =>
        {return !userData.username ? <Welcome/> :
            <Redirect to={pathEmployees}/>}}/>
      <Route path={pathEmployees} exact render ={() =>
        {return userData.username ? <Employees employeesService = {employeesService}/> :
        <Redirect to={pathWelcome}/>}}/>
      <Route path={pathTitleStatistics} exact render={() =>
        {return userData.username ? <TitleStatistics/>:
            <Redirect to={pathWelcome}/>}}/>
      <Route path={pathGenerations} exact render={() =>
        {return userData.isAdmin ? <EmployeesGenerations  employeesService = {employeesService}/>:
            <Redirect to={pathWelcome}/>}}/>
      <Route path={pathSalaryStatistics} exact render={() =>
        {return userData.username ? <SalaryStatistics/>:
            <Redirect to={pathWelcome}/>}}/>
        <Route path={pathSearch} exact render={() =>
        {return userData.username ? <EmployeesSearch/>:
            <Redirect to={pathWelcome}/>}}/>
        <Route path={pathLogin} exact render={() =>
        {return !userData.username ? <Login authService={authService}/> :
                <Redirect to={pathEmployees}/>}}/>
        <Route path={pathLogout} exact render={() =>
        {return userData.username ? <Logout authService={authService} /> :
                    <Redirect to={pathWelcome}/>}}/>
              </Switch>
              </BrowserRouter>
      }
export default App;
