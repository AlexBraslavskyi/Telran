import {useState, useEffect} from "react";
export default function useSubscribeEffect(service, dataFn, pollingInterval) {

    const [data, setData] = useState([]);
    let subscription;
    let polling = true;
    let intervalId;
    const getData = () => {
        subscription =
            dataFn.call(service)
                .subscribe(dataFromServer => {
                    setData(dataFromServer)
                }, (error) => {
                    alert(JSON.stringify(error));
                }, () => {
                    if (polling) {
                        intervalId = setInterval(poller, pollingInterval);
                    }
                })
    }

    const poller = () => {
        console.log('polling');
        dataFn.call(service).subscribe(dataFromServer => {
            setData(dataFromServer)
        })
    }
    useEffect(
        () => {
            getData();



            return () => {
                if(subscription && !subscription.closed) {
                    polling = false;
                    subscription.unsubscribe();

                }
                if (intervalId) {
                    clearInterval(intervalId)
                }
            }
        }, []
    )
    return [data, setData]
}
