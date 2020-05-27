import React from 'react';
import {BrowserRouter,Switch,Route} from "react-router-dom";
import Greeting from "./classwork/cw9_ClockHooks/Greeting";
import Timer from "./classwork/cw9_ClockHooks/Timer";
import {TimerGreetNav} from "./classwork/cw9_ClockHooks/TimerGreetNav";


const App = ()=>{
return <BrowserRouter>
  <TimerGreetNav/>
  <Switch>
    <Route path='/timer' exact render={()=>{
      return <Timer interval ="1000"/>
    }}/>
    <Route path='/greeting' exect render={()=>{
      return<Greeting user = 'Vova'/>
    }}/>
  </Switch>
</BrowserRouter>
}
export default App;