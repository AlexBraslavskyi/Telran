import React from 'react';

import './homework/31-HW_EmployeesHooksWithBugs/App.css';
import {BrowserRouter, Redirect, Route, Switch} from "react-router-dom";
import EmployeesNav from "./homework/31-HW_EmployeesHooksWithBugs/components/EmployeesNav";
import {
  PATH_EMPLOYEES,
  PATH_GENERATION,
  PATH_SALARY_STATISTICS, PATH_SEARCH,
  PATH_TITLE_STATISTICS
} from "./homework/31-HW_EmployeesHooksWithBugs/config/employees_config";
import Employees from "./homework/31-HW_EmployeesHooksWithBugs/components/Employees";
import TitleStatistics from "./homework/31-HW_EmployeesHooksWithBugs/components/TitleStatistics";
import EmployeesGeneration from "./homework/31-HW_EmployeesHooksWithBugs/components/EmployeesGeneration";
import SalaryStatistics from "./homework/31-HW_EmployeesHooksWithBugs/components/SalaryStatistics";
import EmployeesSearch from "./homework/31-HW_EmployeesHooksWithBugs/components/EmployeesSearch";

class App extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      employees: []
    }
  }
  updateEmployeesFn(employees) {
    this.setState({
      employees
    })
  }
  render () {
    return <BrowserRouter>
      <EmployeesNav/>
      <Redirect to={PATH_GENERATION}/>
      <Switch>
        <Route path={PATH_EMPLOYEES} exact render={() => {
          return <Employees employees={this.state.employees}
          updateEmployeesFn={this.updateEmployeesFn.bind(this)}/>
        }}/>
        <Route path={PATH_TITLE_STATISTICS} exact render={() => {
          return <TitleStatistics employees={this.state.employees}/>
        }}/>
        <Route path={PATH_GENERATION} exact render={() => {
          return <EmployeesGeneration employees={this.state.employees}
                                    updateEmployeesFn={this.updateEmployeesFn.bind(this)}/>
        }}/>
        <Route path={PATH_SALARY_STATISTICS} exact render={() => {
          return <SalaryStatistics employees={this.state.employees}/>
        }}/>
        <Route path={PATH_SEARCH} exact render={() => {
          return <EmployeesSearch employees={this.state.employees}/>
        }}/>


      </Switch>
    </BrowserRouter>
  }
}

export default App;
