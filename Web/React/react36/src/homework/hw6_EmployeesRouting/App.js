import React from 'react';
import './App.css';
import {BrowserRouter,Switch,Route} from "react-router-dom";
import EmployeesNav from "./homework/hw6_EmployeesRouting/EmployeesNav";
import employees from "./homework/hw6_EmployeesRouting/employees";
import EmployeesStatistics from "./homework/hw6_EmployeesRouting/EmployeresStatistics"


function App() {
 return <BrowserRouter>
    <EmployeesNav></EmployeesNav>
    <Switch>
      <Route path={'/employees'} exact render = {()=>{
        return <Employees employees = {this.state.employees}/>
      <Route path={'/statistics'} exact render = {()=>{
        return <EmployeesStatistics employees={this.state.employees}/>}}/>
      {/*not using in employee */}
    </Switch>
  </BrowserRouter>
}
}

export default App;
