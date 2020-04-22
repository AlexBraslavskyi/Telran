import React from 'react';
import './App.css';
import {Header} from "./kamasutra/socialnetwork/components/Header";
import {Navbar} from "./kamasutra/socialnetwork/components/Navbar";
import Profile from "./kamasutra/socialnetwork/components/Profile";


const App = () => {
  return (<div className='app-wrapper'>
     <Header/>
     <Navbar/>
     <Profile/>
  </div>);
}

export default App;
