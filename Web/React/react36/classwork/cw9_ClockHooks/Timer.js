import React, {useEffect, useState} from "react";

const Timer =(props)=>{

    const [time,setTime]=useState(new Date());
    let intervalId;
    const tick = ()=>{
        console.log('tick');
        setTime(new Date());
    }
    useEffect(()=>{
        intervalId = setInterval(tick,props.interval);
        return () =>{
            clearInterval(intervalId);
        }
    },[])
    return <h3>{time.toLocaleTimeString()}</h3>
}
export default Timer;