import React, {useEffect, useState} from "react";

const Timer =(props)=>{

    const [time,setTime]=useState(new Date());
    let internalId;
    const tick = ()=>{
        console.log('tick');
        setTime(new Date());
    }
    useEffect(()=>{
        internalId = setInterval(tick,props.interval);
        return () =>{
            clearInterval(internalId);
        }
    },[])
    return <h3>{time.toLocaleTimeString()}</h3>
}
export default Timer;