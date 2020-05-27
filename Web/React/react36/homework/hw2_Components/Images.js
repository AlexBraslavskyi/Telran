import React from "react";

class Images extends React.Component{
    constructor(props) {
        super(props);
        this.state = {imagesUrl: 'https://loremflickr.com/'+this.props.height+'/'+this.props.weight+'?random=1'}
        this.number = 2;

    }

    componentDidMount() {
        this.intervalId = setInterval(this.updateImagesUrl.bind(this),this.props.interval);
    }
    componentWillUnmount() {
        clearInterval(this.intervalId);
    }
    render() {
        return <img src={this.state.imagesUrl}/>;
    }

    updateImagesUrl(){
     this.number++;
        this.setState({imagesUrl: 'https://loremflickr.com/'+this.props.height+'/'+this.props.weight+'?random='+this.number})
    }

}
export default Images;