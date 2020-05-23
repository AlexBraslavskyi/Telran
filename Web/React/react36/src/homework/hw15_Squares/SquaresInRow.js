import React, {useEffect, useState} from "react";

export default function SquaresInRow (props){
const numCells= 2500;
    let [table,setTable] = useState([]);
    const colors = ["black", "white"];
    const squares =()=>{
        for(let i =0;i<numCells;i++){
            table.push(<div key={i} className='cell' style={{"background": colors[getRandomNumber(0, colors.length - 1)]}}/>)
        }
    }
    const cell = ()=>{
        table=[];
        squares()
        setTable(table)
        console.log('tick')
       }
    useEffect(() => {
        let intervalId = setInterval(cell, props.interval);
        return () => {
            clearInterval(intervalId);
        };
    },[cell]);
    function getRandomNumber(min, max) {
        return min + Math.round(Math.random() * (max - min));
    }

     return <div className='container'>
        <div className='grid'>
            {table.map((t)=>{
                return t
            })}
        </div>
    </div>
}