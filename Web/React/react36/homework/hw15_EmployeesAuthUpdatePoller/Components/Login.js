import React, {useState} from "react";
export default function Login(props) {
    const authService = props.authService;
    const userDataUpdateFn = props.userDataUpdateFn;
    const [credentials, setCredentials] = useState({
        email: '',
        password: ''
    });
    const inputHandler = (event) => {
        event.preventDefault();
        credentials[event.target.name] = event.target.value;
        setCredentials({...credentials});
    }
    const onSubmit = (event) => {
        event.preventDefault();
        authService.login(credentials)
            .subscribe(userData => {
                userDataUpdateFn(userData);
            }, () => {
                alert('Wrong credentials');
            })
    }
    return <div className="card-log">
        <div className="card-header">
            <h4>Login Form</h4>
        </div>
        <div className="card-body">
            <form onSubmit={onSubmit}>
                <div className="form-group">
                    <label>Username/email</label>
                    <input name="email" required
                           onChange={inputHandler}
                    className="form-control"/>

                </div>
                <div className="form-group">
                    <label>Password</label>
                    <input type="password" name="password" required
                           onChange={inputHandler}
                           className="form-control"/>

                </div>
                <button type="submit" className='btn-success'><i className="fa fa-unlock"/>Sing in</button>
                <button type="reset" className='btn-danger'><i className="fa fa-trash"/>Reset</button>
            </form>

        </div>

    </div>
}
