import React from "react";

// function Clock() {
//
//     return <h4>{new Date().toLocaleString()}</h4>
//
// }
class Clock extends React.Component{
    constructor(props) {
        super(props);
        this.state ={
            date: new Date()
        }
    }
    componentDidMount() {
        this.intervalId = setInterval(this.tick.bind(this),1000);
    }
componentWillUnmount() {
        clearInterval(this.intervalId);
}
render() {
    return <h3>{this.state.date.toLocaleString()}</h3>;
}

    tick(){
        this.setState({date: new Date()})
    }
}
export default Clock;