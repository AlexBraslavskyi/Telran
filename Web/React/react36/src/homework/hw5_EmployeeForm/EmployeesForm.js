import React from "react";

export default class EmployeeForm extends React.Component {
    constructor(props) {
        super(props);
        this.invalid = true;
        this.titleOptions = ['Developer' ,'Development Manager', 'QA Tester', 'QA Manager', 'Sales Person', 'Sales Manager'];
        this.state = {
            error: '',
            employee: {
                id: "",
		name: "",
                email: "",
		gender:""
                salary: "",
                title: this.titleOptions[0];
            }
        }
        this.onSubmit= this.onSubmit.bind(this)
        this.handlerInputFields = this.handlerInputFields.bind(this);
    }

handlerInputFields(event){
      event.preventDefault();
        const fieldName = event.target.name;
        const fieldValue = event.target.value;
        const employee = this.state.employee;
        employee[fieldName]=fieldValue;
        this.setState({error:'',employee})

}

getSelectOptions(optionStrings){
        return optionStrings.map(os=>{
            return <option key={os} value={os}>{os}</option>
        })
}
onSubmit(event){
        event.preventDefault();
        if(!this.props.addFn(this.state.employee)){
            this.setState({error:`employee with ID ${this.state.employee.id} already exist`})
        }
}
validate(){
        this.invalid = this.state.error || !this.state.employee.id  //checking
}
    render() {
this.validate();
    return <div className='card'>
	<header className='card-header'></header>
        <h3>employee Input Form</h3>
        <div className='card-body'>
            <form onSubmit={this.onSubmit}>
                <div className='form-group' >
                <label>ID</label>
                    <input className='form-control' type='number' name='id' onChange={this.handlerInputFields}/>
                    <div hidden={!this.state.error} className="alert alert-danger">>
                </div>
                <div className='form-group' >
                      <label>Email</label>
                    <input className='form-control' type='email' name='email'onChange={this.handlerInputFields}/>
                    <div hidden={!this.state.error} className="alert alert-danger">
                {this.state.error}
                </div>
                <div className='form-group' >
                    <label>Name</label>
                    <input className='form-control' name='name'/>
			   </div>
	    <div class="form-check">
                <label class="form-check-label">
                    <input class="form-check-input" type="radio"
                           value="female" name="gender" required>
                    Female
                </label>
            </div>
            <div class="form-check">
                <label class="form-check-label">
                    <input class="form-check-input" type="radio"
                           name="gender" value="male">
                    Male
                </label>
            </div>
	    
                <div className='form-group' >
              <label>Salary</label>
                    <input className='form-control' name='salary' type='number' onChange={this.handlerInputFields}/>
                    </div>
                <button type="submit" disabled={this.invalid}>Submit</button>
            </form>
        </div>
        </div>

}
}
