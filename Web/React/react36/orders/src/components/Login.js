import React, {useState} from "react";
export default function Login(props) {
    const authService = props.authService;
    const [credentials, setCredentials] = useState({
        email: '',
        password: ''
    });
    const inputHandler = (event) => {
        event.preventDefault();
        credentials[event.target.name] = event.target.value;
        setCredentials({...credentials});
    };
    const googleAuth = ()=>  {
        authService.login().catch(()=>alert('Wrong Google credentials'))
    };
    const onSubmit = (event) => {
        event.preventDefault();
        authService.login(credentials).then().catch(()=>alert('Wrong credentials'))

    };
    return <div className="card">
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
                <button type="submit">Sing in</button>
                <button type="reset">Reset</button>
            </form>
<div className='card-body'>
    <button onClick={googleAuth}>Google Authentication</button>
</div>
        </div>

    </div>
}
