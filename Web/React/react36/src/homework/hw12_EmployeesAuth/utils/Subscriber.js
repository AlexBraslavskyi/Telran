import React, {useEffect, useState} from "react";

export default function subscribeEffect (service,dataFn) {



    const [employees, setEmployees] = useState([]);
    let subscription;
    const getEmployees = () => {
        subscription =
            service
                .subscribe(employeesFromServer => {
                    setEmployees(employeesFromServer)
                }, error => {
                    alert(JSON.stringify(error))
                })
    }
    let intervalID;
    const poller = ()=>{
        if(!subscription||subscription.closed) {
            getEmployees();
        }
    }
    useEffect(
        () => {
            getEmployees();
            intervalID = setInterval(poller,1000);
            return () => {
                if(subscription && !subscription.closed) {
                    subscription.unsubscribe();
                }else {
                    clearInterval(intervalID);
                }
            }
        }, []
    )


}