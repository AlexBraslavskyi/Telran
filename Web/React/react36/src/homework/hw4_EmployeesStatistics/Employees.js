<<<<<<< HEAD
import React from "react";
import _ from 'lodash';
import EmployeesStatistics from "./EmployeesStatistics";
import EmployeesTable from "./EmployeesTable";
import {getRandomEmployee} from "./GetRandomEmployee";

class Employees extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            employees: [], flStatistics: false
        }
    }
    addEmployee() {
        const employee = getRandomEmployee();
        const employees = this.state.employees;
        employees.unshift(employee);
        this.setState({
            employees
        })
    }
    removeEmployee(id) {
        const employees = this.state.employees;
        if (window.confirm(`You are going to delete employee ${id}`)) {
            _.remove(employees, employee => employee.id == id)
            this.setState({employees});
            return true;
        }
    }
    changeFlag(){
        if(!this.state.flStatistics){
    this.setState({flStatistics:true})}
        else {
            this.setState({flStatistics:false})
        }
}
    render() {
        if (!this.state.flStatistics) {
            return <div>
                <EmployeesTable employees = {this.state.employees}
                                removeFn={this.removeEmployee.bind(this)}/>
                <div style={{"textAlign": "center"}}>
                    <i style={{"cursor": "pointer"}} onClick={this.addEmployee.bind(this)} className="fa fa-plus-square fa-2x">
                        Add Employee</i>
                    <button style={{"marginLeft": 10}} type="button" onClick={this.changeFlag.bind(this)}>
                        Show Statistics</button>
                </div>
            </div>
        }
        if (this.state.flStatistics) {
            return <div>
                <EmployeesStatistics stat ={this.state.employees} />
                <div style={{"textAlign": "center"}}>
                    <button  type="button" onClick={this.changeFlag.bind(this)}>
                        Hide Statistics</button>
                </div>
            </div>
        }
    }
}
export default Employees;
=======
// import React from "react";
//import _ from 'lodash';
//import EmployeesStatistics from "./employeesStatistics";

// import {getRandomNumber, getRandomElement} from "./Random";
// let firstId = 1;
//
// class Employees extends React.Component {
//     constructor(props) {
//         super(props);
//         this.state = {
//             employees: [],titleStatistics:{}
//         }
//     }
//     addEmployee() {
//         const employee = getRandomEmployee() ;
//         const employees = this.state.employees;
//         employees.unshift(employee);
//         this.setState({
//             employees,titleStatistics
//         })
//     }
//deleteEmployee(email){
  //      const employees = this.state.employees;
    //    if(window.confirm `You are going to delete employee ${id}`){
      //      _.remove(employees, employee => employee.id == id)
        //    const titleStatistics = _.countBy(employees,'title');
          //  this.setState({employees,titleStatistics});
        //}
  //  }
// 
//     render() {
//	 const styleCursor = {cursor:"pointer"}
//         const employeeTableRecords  = this.state.employees.map (
//             (employee) => {
//                 return <tr key={employee.id}>
//                     <td>{employee.id}</td>
//                     <td>{employee.name}</td>
//                     <td>{employee.emailAddress}</td>
//                     <td>{employee.gender}</td>
//                     <td>{employee.salary}</td>
//                     <td>{employee.title}</td>
 //  <td>
   //             <i style={styleCursor}
     //              onClick={this.deleteEmployee.bind(this,employee.id)}
       //         className="fa fa-trash"></i>
         //       </td>
//                 </tr>
//             }
//         )
//         return <div>
//<table className="table">
//             <thead>
//             <tr>
//                 <th>ID</th>
//                 <th>Name</th>
//                 <th>Email</th>
//                 <th>Gender</th>
//                 <th>Salary</th>
//                 <th>Title</th>
//             </tr>
//             </thead>
//             <tbody>
//             {employeeTableRecords}
//             </tbody>
//         </table>
 //  <i style={styleCursor} onClick={this.addEmployee.bind(this)} className="fa fa-plus-circle"></i>
  //              <EmployeesStatistics statistics={this.state.titleStatistics} />
  //          </div>
//     }
// }
// function getRandomEmployee() {
//     const id = firstId++;
//     const emailAddress = `${id}@gmail.com`;
//     const gender = getRandomElement(['male', 'female']);
//     const name = getRandomName(gender);
//     const salary = getRandomNumber(5000,
//         35000);
//     const title = getRandomTitle();
//     return {id, name, emailAddress, gender, salary, title}
//
//
// }
//
// function getRandomName(gender) {
//     const genderNames = {
//         male: ['Moshe', 'David', 'Yosef', 'Asaf',
//             'Lee', 'Ivan', 'Nik', 'Yuri', 'Igor', 'Alex'],
//         female: ['Sara', 'Rivka', 'Klara', 'Olya', 'Irina', 'Vera',
//             'Katya', 'Rita', 'Ida', 'Elena']
//     }
//     return getRandomElement(genderNames[gender]);
// }
//
//
// function getRandomTitle() {
//     const titles = ['Developer' ,'Development Manager', 'QA Tester', 'QA Manager', 'Sales Person', 'Sales Manager']
//     return getRandomElement(titles);
// }
//
// export default Employees;
>>>>>>> 68c95c2290aa88385f70e6d80459421a778bbeb8
