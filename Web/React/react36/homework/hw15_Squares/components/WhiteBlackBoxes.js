import React from "react";
import RowNumbers from "./RowNumbers";

export default function WhiteBlackBoxes(props) {
    const numbers = props.numbers; //two-dimensional  array of numbers for Flow JS
    const rows = numbers.map((row, index) => <RowNumbers key={"row_" + index} row={row}></RowNumbers>)
    const styles = {display: 'flex',
    flexDirection: 'column'}
    return <div style={styles}>
        {rows}
    </div>
}
