import React from 'react';
import logo from './logo.svg';
import './App.css';
import Welcome from "./ComponentsLesson2/Welcome";
import Clock from "./ComponentsLesson2/Clock";
const users = [{name:'Moshe'},{name:'Sara'},{name:'David'}]
function App() {
  // return (
  //   <div className="App">
  //     <header className="App-header">
  //       <img src={logo} className="App-logo" alt="logo" />
  //       <p>
  //         Edit <code>src/App.js</code> and save to reload.
  //       </p>
  //       <a
  //         className="App-link"
  //         href="https://reactjs.org"
  //         target="_blank"
  //         rel="noopener noreferrer"
  //       >
  //         Learn React
  //       </a>
  //     </header>
  //   </div>
  // );
  return <div className='welcome'>
    <Welcome user = {users[0].name}/>
    <Welcome user = {users[1].name}/>
    <Welcome user = {users[2].name}/>
    <div>
      <h3> It's time:</h3>
    <Clock/>
    </div>
  </div>
}

export default App;
