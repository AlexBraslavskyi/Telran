import React from "react";
<<<<<<< HEAD
import _ from 'lodash';

export default function EmployeesStatistics(props) {
    const statisticsObj = _.countBy(props.stat,"title");
    const statisticsArray = Object.entries(statisticsObj).map(e=>{return{title:e[0],count:e[1]}})
        const statTableRecords = statisticsArray.map(
        (title) => {
        return <tr key={title.title}>
        <td>{title.title}</td>
        <td>{title.count}</td>
        </tr>
    })
        return <div  style={{"margin":"40px"}}>
        <table  className="table">
            <thead>
            <tr>
                <th>Title</th>
                <th>Count</th>
            </tr>
            </thead>
            <tbody>
            {statTableRecords}
            </tbody>
        </table>
        </div>
=======


export default function EmployeesStatistics(prors) {
    const listItems = JSON.stringify(prors.statistics);//hw table
return <div>
    <ul>
        {listItems}
    </ul>
</div>
>>>>>>> 68c95c2290aa88385f70e6d80459421a778bbeb8
}
