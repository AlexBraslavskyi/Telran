import React, {useEffect, useState} from 'react';

import './App.css';
import TransformNumbers from "./homework/hw15_Squares/services/TransformNumbers";
import {INTERVAL, N_COLUMNS, N_ROWS} from "./homework/hw15_Squares/config/white-black-boxes";
import WhiteBlackBoxes from "./homework/hw15_Squares/components/WhiteBlackBoxes";

function getRandomRow() {
  const row = [];
  for (let j = 0; j < N_COLUMNS; j++) {
    row.push(Math.round(Math.random()));
  }
  console.log(row)
return row;
}

function getRandomNumbers() {
  const result = [];
  for (let i = 0; i < N_ROWS; i++) {
    result.push(getRandomRow())
  }
  // console.log(result)
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
