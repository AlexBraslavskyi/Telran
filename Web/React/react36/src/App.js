import React from 'react';
import './App.css'
import Images from "./Homework/HW2_Components/Images";
const parameters = [{height:'300'},{weight:'300'},{interval:'5000'}]
function App() {

  return   <div className='Images'>
    <div>
      <h1>“Images from the site https://loremflickr.com/”</h1>
        <Images height = {parameters[0].height} weight = {parameters[1].weight} interval = {parameters[2].interval}/>
    </div>
  </div>
}

export default App;
