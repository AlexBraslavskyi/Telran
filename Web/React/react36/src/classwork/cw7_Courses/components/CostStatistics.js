import React from "react";
import {MAX_COST, MIN_COST} from "../config/courses_configurations";
import _ from "lodash";

export default class CostStatistics extends React.Component{
    constructor(props) {
        super(props);
        this.courses = props.courses;
    }
    getStatistics(){
        return this.courses.reduce((res,c)=>{
            return{min:c.cost<res.min?c.cost:res.min,
            max:c.cost>res.min?c.cost:res.min,total:res.total+c.cost}
        },{min:MAX_COST,max:MIN_COST,total:0})

    }
    getIntervalItems(interval) {
        const objIntervalStatistics =
            _.countBy(this.courses, c => {
                return Math.floor(c.cost / interval);
            })
        let startInterval = +MIN_COST;
        console.log(objIntervalStatistics);
        return Object.values(objIntervalStatistics).map(v => {
            const nextInterval = startInterval + +interval;
            const key = `${startInterval}-${nextInterval}`;
            startInterval = nextInterval;
            return <li key={key}>{key}:{v}</li>
        })
    }
render() {
        if(this.props.courses.length ==0){
            return <div/>
        }
        const interval = window.prompt('Enter interval');
        if(!interval){
            return <div></div>;
        }
        const statistics = this.getStatistics();

    return <div className="card" >
        <div className="card-header">
            <h3>Cost Statistics</h3>
        </div>
        <div className="card-body">
            <div className="container">
                <label>min cost {statistics.min}</label>
                <label>max cost {statistics.max}</label>
                <label>total cost {statistics.total}</label>
                <ol>
                    {this.getIntervalItems(interval)}
                </ol>
            </div>
        </div>
    </div>
}

}
