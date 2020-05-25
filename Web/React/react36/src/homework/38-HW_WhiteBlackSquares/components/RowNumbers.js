import React from "react";
import {N_COLUMNS, N_ROWS} from "../config/white-black-boxes";

function getSize() {
    return window.innerHeight / N_COLUMNS - 2;
}


function getStyle(number) {
    return {
        "background": number === 1 ? "black" : "white",
        "border": "solid 1px gray", "width": getSize(), "height": getSize()
    };
}

export default  function RowNumbers (props) {
const row = props.row;

const boxes = row.map((number, index) => <div style={getStyle(number)}
                                               key={'column_' + index}>


</div>)
    return <div style={{"display": "flex", "flexDirection": "row"}}>
        {boxes}
    </div>
}
