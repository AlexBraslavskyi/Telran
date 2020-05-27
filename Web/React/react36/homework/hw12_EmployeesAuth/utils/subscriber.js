import {useEffect, useState} from "react";
import {POLLING_INTERVAL} from "../config/EmployeesConfig";

export default function subscribeEffect(service, dataFn) {


    // eslint-disable-next-line react-hooks/rules-of-hooks
    const [data, setData] = useState([])
    let subscription;
    let intervalId;

    // eslint-disable-next-line react-hooks/rules-of-hooks
 useEffect(() => {
     subscription = dataFn.call(service)
         .subscribe(dataFromServer => setData(dataFromServer));
     intervalId = setInterval(poller, POLLING_INTERVAL)
     return (() => {
         if (subscription && !subscription.closed) {
             subscription.unsubscribe();
         }
         clearInterval(intervalId);
     })
 }, []) ;
    const poller = () => {
        if (!subscription || subscription.closed) {
            dataFn.call(service)
                .subscribe(dataFromServer => setData(dataFromServer));
        }
    }
    return [data, setData];
}

