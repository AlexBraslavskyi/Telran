import React, {useState} from 'react';

import './App.css';
import {BrowserRouter, Redirect, Route, Switch} from "react-router-dom";
import EmployeesNav from "./components/EmployeesNav";
import {
  PATH_EMPLOYEES,
  PATH_GENERATION,
  PATH_SALARY_STATISTICS, PATH_SEARCH,
  PATH_TITLE_STATISTICS
} from "./config/employees_config";
import Employees from "./components/Employees";
import TitleStatistics from "./components/TitleStatistics";
import EmployeesGeneration from "./components/EmployeesGeneration";
import SalaryStatistics from "./components/SalaryStatistics";
import EmployeesSearch from "./components/EmployeesSearch";
import EmployeesHttpService from "./services/EmployeesHttpService";
const App = () => {
  const employeesService =
      new EmployeesHttpService("http://localhost:3500/employees/")
  return <BrowserRouter>
    <EmployeesNav/>
    <Redirect to={PATH_GENERATION}/>
    <Switch>
      <Route path={PATH_EMPLOYEES} exact render={() => {
        return <Employees employeesService={employeesService}
                          />
      }}/>
      <Route path={PATH_TITLE_STATISTICS} exact render={() => {
        return <TitleStatistics employeesService={employeesService}/>
      }}/>
      <Route path={PATH_GENERATION} exact render={() => {
        return <EmployeesGeneration employeesService={employeesService}
                                    />
      }}/>
      <Route path={PATH_SALARY_STATISTICS} exact render={() => {
        return <SalaryStatistics employeesService={employeesService}/>
      }}/>
      <Route path={PATH_SEARCH} exact render={() => {
        return <EmployeesSearch employeesService={employeesService}/>
      }}/>


    </Switch>
  </BrowserRouter>
}


export default App;
