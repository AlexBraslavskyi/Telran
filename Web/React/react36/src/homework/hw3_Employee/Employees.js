import React from "react";
import {getRandomNumber, getRandomElement} from "./Random";
let firstId = 1;

class Employees extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            employees: []
        }
    }
    addEmployee() {
        const employee = getRandomEmployee() ;
        const employees = this.state.employees;
        employees.unshift(employee);
        this.setState({
            employees
        })
    }
    componentDidMount() {
        this.intervalId = setInterval(this.addEmployee.bind(this),
            2000)
    }
    componentWillUnmount() {
        clearInterval(this.intervalId);
    }
    render() {
        const employeeTableRecords  = this.state.employees.map (
            (employee) => {
                return <tr>
                    <td>{employee.id}</td>
                    <td>{employee.name}</td>
                    <td>{employee.emailAddress}</td>
                    <td>{employee.gender}</td>
                    <td>{employee.salary}</td>
                    <td>{employee.title}</td>
                </tr>
            }
        )
        return <table className="table">
            <thead>
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Gender</th>
                <th>Salary</th>
                <th>Title</th>
            </tr>
            </thead>
            <tbody>
            {employeeTableRecords}
            </tbody>
        </table>
    }
}
function getRandomEmployee() {
    const id = firstId++;
    const emailAddress = `${id}@gmail.com`;
    const gender = getRandomElement(['male', 'female']);
    const name = getRandomName(gender);
    const salary = getRandomNumber(5000,
        35000);
    const title = getRandomTitle();
    return {id, name, emailAddress, gender, salary, title}


}

function getRandomName(gender) {
    const genderNames = {
        male: ['Moshe', 'David', 'Yosef', 'Asaf',
            'Lee', 'Ivan', 'Nik', 'Yuri', 'Igor', 'Alex'],
        female: ['Sara', 'Rivka', 'Klara', 'Olya', 'Irina', 'Vera',
            'Katya', 'Rita', 'Ida', 'Elena']
    }
    return getRandomElement(genderNames[gender]);
}


function getRandomTitle() {
    const titles = ['Developer' ,'Development Manager', 'QA Tester', 'QA Manager', 'Sales Person', 'Sales Manager']
    return getRandomElement(titles);
}

export default Employees;