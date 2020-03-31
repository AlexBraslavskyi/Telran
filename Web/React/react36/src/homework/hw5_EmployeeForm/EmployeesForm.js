import React from "react";

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
    }
	


handlerInputFields(event){
      event.preventDefault();
        const fieldName = event.target.name;
        const fieldValue = event.target.value;
        const employee = this.state.employee;
        employee[fieldName]=fieldValue;
        this.setState({errorID:'',employee})

}
    handlerInputSalary(event){
        event.preventDefault();
        const fieldValue = event.target.value;
        console.log(fieldValue);
        if (fieldValue<5000||fieldValue>35000) {
            if (this.state.employee.salary<5000|| this.state.employee.salary>35000) {
                this.setState({errorSalary: `Salary mast by in range of 5000-35000`});
            }
        }
    }

getSelectOptions(optionStrings){
        return optionStrings.map(os=>{
            return <option key={os} value={os}>{os}</option>
        })
}
onSubmit(event){
    event.preventDefault();
    if (this.state.employee.name.length <4) {
        this.setState({errorName: `In name mast be more then 4 symbols`});
    }
        if(!this.props.addFn(this.state.employee)){
            this.setState({errorID?:`employee with ID ${this.state.employee.id} already exist`})
        }

}
validate(){
        this.invalid = this.state.errorID ||this.state.errorSalary||this.state.errorName || !this.state.employee.id
            // ||this.state.employee.name.length <4
            // ||(this.state.employee.salary <5000&&this.state.employee.salary>35000)
         //checking
}
    render() {
        this.validate();
        return <div className='card'>
            <header className='card-header'></header>
            <h3>employee Input Form</h3>
            <div className='card-body'>
                <form onSubmit={this.onSubmit}>
                    <div className='form-group'>
                        <label>ID</label>
                        <div hidden={!this.state.errorID} className="alert alert-danger">
                        {this.state.errorID}
                        </div>
                        <input className='form-control' type='number' name='id' onChange={this.handlerInputFields} required/>
                    </div>
                    <div className='form-group' >
                        <label>Email</label>

                        <input className='form-control' type='email' name='emailAddress' onChange={this.handlerInputFields} required/>
                   
                    </div>
                            <div className='form-group'>
                                <label>Name</label>

                                <input className='form-control' name='name' onChange={this.handlerInputFields} required/>
                                <div hidden={!this.state.errorName} className="alert alert-danger">
                                    {this.state.errorName}
                                </div>
                                </div>
                            <div className="form-check">
                                <label className="form-check-label">
                                    <input className="form-check-input" type="radio"
                                           value="female" name="gender" required onChange={this.handlerInputFields}/>
                                    Female
                                </label>
                            </div>
                            <div className="form-check">
                                <label className="form-check-label">
                                    <input className="form-check-input" type="radio"
                                           name="gender" value="male" onChange={this.handlerInputFields}/>
                                    Male
                                </label>
                            </div>
                            <div className='form-group'>
                                <label>Salary</label>
                                <div hidden={!this.state.errorSalary} className="alert alert-danger">
                                    {this.state.errorSalary}
                                </div>
                                <input className='form-control' name='salary' type='number'
                                       onChange={this.handlerInputSalary} required/>
                            </div>
                            <div className='form-group' >
                                <label>Title</label>
                                <select className='form-control' name='title' onChange={this.handlerInputFields}>
                                    {this.getSelectOptions(this.titleOptions)}
                                </select>
                            </div>

                            <div>
                                <button type="submit" disabled={this.invalid}>Submit</button>
                            </div>
                </form>
            </div>
        </div>

    }

}
