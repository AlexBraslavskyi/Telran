import React, {useEffect, useState} from 'react';

import './App.css';
import TransformNumbers from "./services/TransformNumbers";
import {INTERVAL, N_COLUMNS, N_ROWS} from "./config/white-black-boxes";
import WhiteBlackBoxes from "./components/WhiteBlackBoxes";

function getRandomRow() {
  const row = [];
  for (let j = 0; j < N_COLUMNS; j++) {
    row.push(Math.round(Math.random()));
  }
return row;
}

function getRandomNumbers() {
  const result = [];
  for (let i = 0; i < N_ROWS; i++) {
    result.push(getRandomRow())
  }
  return result;
}
function App() {
 let numbersService;

 const [numbers, setNumbers] = useState(getRandomNumbers());
 let intervalId;
 useEffect(() => {


   numbersService = new TransformNumbers(numbers);
   intervalId = setInterval(() => setNumbers([...numbersService.getNumbers()]),
       INTERVAL);
   return () => clearInterval(intervalId)
 })
  return <WhiteBlackBoxes numbers={numbers}/>
}

export default App;
