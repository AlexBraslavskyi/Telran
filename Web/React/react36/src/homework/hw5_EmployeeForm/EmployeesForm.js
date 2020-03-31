import React from "react";
const letters = /^[A-Za-z]+$/;
export default class EmployeesForm extends React.Component {
    constructor(props) {
        super(props);
        this.invalid = true;
        this.titleOptions = ['Developer' ,'Development Manager', 'QA Tester', 'QA Manager', 'Sales Person', 'Sales Manager'];
        this.state = {
            errorID: '',
		   errorName: '',
		   errorSalary: '',
            employee: {
                id: "",
	        	name: "",
                emailAddress: "",
	        	gender:"",
                salary: "",
                title: this.titleOptions[0]
            }
        }
        this.onSubmit= this.onSubmit.bind(this)
        this.handlerInputFields = this.handlerInputFields.bind(this);
        this.handlerInputSalary = this.handlerInputSalary.bind(this);
	    this.handlerInputName = this.handlerInputName.bind(this);
        this.handlerInputID = this.handlerInputID.bind(this);
    }
	


handlerInputFields(event){
      event.preventDefault();
        const fieldName = event.target.name;
        const fieldValue = event.target.value;
        const employee = this.state.employee;
        employee[fieldName]=fieldValue;
        this.setState({employee})

}
    handlerInputSalary(event){
        event.preventDefault();
        const fieldName = event.target.name;
        const fieldValue = event.target.value;
        const employee = this.state.employee;
        employee[fieldName]=fieldValue;
        if (fieldValue<5000||fieldValue>35000){
                this.setState({errorSalary: `Salary mast by in range of 5000-35000`});
            }
	    else{ 
		    this.setState({errorSalary:'',employee})
        }
    }
    handlerInputName(event){
        event.preventDefault();
        const fieldName = event.target.name;
        const fieldValue = event.target.value;
        const employee = this.state.employee;
        employee[fieldName]=fieldValue;
        if (fieldValue.length <4||!fieldValue.match(letters)){
                this.setState({errorName: `Name mast content only english letters, min length - 4 symbol `});
            }
	    else{ 
		    this.setState({errorName:'',employee})
        }
    }
    handlerInputID(event){
        event.preventDefault();
        const fieldName = event.target.name;
        const fieldValue = event.target.value;
        const employee = this.state.employee;
        employee[fieldName]=fieldValue;
        if (fieldValue <10000||fieldValue>99999||fieldValue.substr(0,1)=="0"){
            this.setState({errorID: `ID mast be in the range 10000-99999 and can't start from 0`});
        }
        else{
            this.setState({errorID:'',employee})
        }
    }
getSelectOptions(optionStrings){
        return optionStrings.map(os=>{
            return <option key={os} value={os}>{os}</option>
        })
}
onSubmit(event){
    event.preventDefault();
        if(!this.props.addFn(this.state.employee)){
            this.setState({errorID:`employee with ID ${this.state.employee.id} already exist`})
        }

}
validate(){
        this.invalid = !this.state.employee.id
    ||this.state.employee.name.length <4 ||!this.state.employee.name.match(letters)
    ||(this.state.employee.salary <5000||this.state.employee.salary>35000)
    ||(this.state.employee.id <10000||this.state.employee.id>99999)
}
    render() {
        this.validate();
        return <div className='card'>
            <header className='card-header'></header>
            <h3 style={{'text-align':'center'}}>Employee Input Form</h3>
            <div className='card-body'>
                <form onSubmit={this.onSubmit}>
                <div className = 'firstLevel'>
                    <div className='form-group'>
                        <label>ID</label>
                        <span hidden={!this.state.errorID} style={{color: "red"}} className="alert alert-danger">
                        {this.state.errorID}
                        </span>
                        <input className='form-control' name='id' type="number" onChange={this.handlerInputID} required/>
                    </div>
                    <div className='form-group'>
                        <label>Name</label>
                        <span hidden={!this.state.errorName} style={{color: "red"}} className="alert alert-danger">
                            {this.state.errorName}
                        </span>
                        <input className='form-control' name='name' onChange={this.handlerInputName} required/>
                    </div>
	            		<div className='form-group' >
                        <label>Email</label>
                            <div>
                            <lable name='emailAddress' >{this.state.employee.emailAddress=
                            this.state.employee.name+this.state.employee.id.substr(0,3)+"@gmail.com"}</lable>
                            </div>
                         </div>
                </div>
                        <div className = 'secondLevel'>
                            <div className="form-check">
                                <label className="form-check-label">
                                    <input className="form-check-input" type="radio"
                                           value="female" name="gender" required onChange={this.handlerInputFields}/>Female
                                </label>
                            </div>
                            <div className="form-check">
                                <label className="form-check-label">
                                    <input className="form-check-input" type="radio"
                                           name="gender" value="male" onChange={this.handlerInputFields}/>Male
                                </label>
                            </div>
                        </div>
                            <div className = 'thirdLevel'>
                            <div className='form-group'>
                                <label>Salary</label>
                                <span hidden={!this.state.errorSalary} style={{color: "red"}} className="alert alert-danger">
                                    {this.state.errorSalary}
                                </span>
                                <input className='form-control' name='salary' type='number'
                                       onChange={this.handlerInputSalary} required/>
                            </div>
                            <div className='form-group' >
                                <label>Title</label>
                                <select className='form-control' name='title' onChange={this.handlerInputFields}>
                                    {this.getSelectOptions(this.titleOptions)}
                                </select>
                            </div>
                            </div>
                            <div>
                                <button type="submit" disabled={this.invalid}>Submit</button>
                            </div>
                </form>
            </div>
        </div>
    }
}
