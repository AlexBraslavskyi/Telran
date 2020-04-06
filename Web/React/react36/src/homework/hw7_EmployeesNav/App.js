import React from 'react';

import './App.css';
import {BrowserRouter, Route, Switch} from "react-router-dom";
import EmployeesNav from "./components/EmployeesNav";
import {
  PATH_SALARY_STATISTICS,
  PATH_EMPLOYEES,
  PATH_EMPLOYEES_GENERATION, PATH_EMPLOYEES_SEARCH,
  PATH_TITLE_STATISTICS
} from "./config/employees_configuration";
import Employees from "./components/Employees";
import EmployeesGeneration from "./components/EmployeesGeneration";
import SalaryStatistics from "./components/SalaryStatistics";
import TitleStatistics from "./components/TitleStatistics";
import EmployeesSearch from "./components/EmployeesSearch";
class App extends React.Component {
  constructor(props) {
    super(props);
  }
  render () {
    return <BrowserRouter>
      <CoursesNav/>
      <Switch>
        <Route path={PATH_EMPLOYEES} exact render={() => {
          return <Employees/>
        }}/>
        <Route path={PATH_TITLE_STATISTICS} exact render={() => {
          return <TitleStatistics/>
        }}/>
        <Route path={PATH_EMPLOYEES_GENERATION} exact render={() => {
          return <EmployeesGeneration/>
        }}/>
        <Route path={PATH_SALARY_STATISTICS} exact render={() => {
          return <SalaryStatistics/>
        }}/>
        <Route path={PATH_EMPLOYEES_SEARCH} exact render={() => {
          return <EmployeesSearch/>
        }}/>


      </Switch>
    </BrowserRouter>
  }
}

export default App;
