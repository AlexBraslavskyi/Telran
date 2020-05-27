import React from "react";
import {getRandomElement, getRandomNumber} from "../util/random";
import {DIGITS_ID, LECTURES, MAX_COST, MIN_COST, TITLES} from "../config/courses_configurations";
import {getErrorMessage, getInputElement} from "../util/input_elements";



function  getCourse(){
    const id = getRandomNumber(10**(DIGITS_ID-1),10**DIGITS_ID-1);
    const title = getRandomElement(TITLES);
    const lecturer = getRandomElement(LECTURES);
    const cost = getRandomNumber(MIN_COST,MAX_COST);
return {id,title,lecturer,cost}
}
export default class CoursesGeneration  extends React.Component {
    constructor(props) {
        super(props);
        this.courses = props.courses;
        this.state = {
            nCourses: 0,
            error: ''
        }
        this.inputHandler = this.inputHandler.bind(this);
        this.onSubmit = this.onSubmit.bind(this);
    }

    inputHandler(event) {
        event.preventDefault();
        const nCourses = event.target.value;
        this.setState({error: ''});
        if (nCourses < 1) {
            this.setState({error: 'wrong amount <1'})
        } else {
            this.setState({nCourses})
        }
    }

    onSubmit(event) {
        event.preventDefault();
        for (let i = 0; i < this.state.nCourses; i++) {
            const course = getCourse();
            this.courses.push(course);
        }
        this.props.updateCourseFn(this.courses);
    }
    render() {
        return <div className="card">
            <div className="card-header">
                <h3>Generation Courses</h3>
            </div>
            <div className="card-body">
                <form onSubmit={this.onSubmit}>
                    {getInputElement("Enter amount of courses","number",this.inputHandler,'nCourses')}
                    {getErrorMessage(this.state.error)}
                    <button hidden={this.state.error ||!this.state.nCourses}>Generate</button>
                </form>
            </div>

        </div>;
    }
}
