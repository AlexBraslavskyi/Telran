import React, {useState} from "react";
import {getInputElement} from "../utils/inputElements";
import {pathNewUserForm} from "../config/ShopConfig";


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
    }
    const googleAuth = () => {
        authService.login('', 1)

    }
    const facebookAuth = () => {
        authService.login('', 2)

    }
    const onSubmit = (event) => {
        event.preventDefault();
        authService.login(credentials, 0).then().catch(() => alert('Wrong credentials'))

    }

    return <div className="content">
        <h2 className="center-align" style={{fontFamily: "fantasy", marginTop: "5vh"}}>Please sing in to enter</h2>

        <div className="log">
            <form className="col s6 center-align" onSubmit={onSubmit} style={{width: "35vw"}}>
                {getInputElement('email',
                    'email', 'Email',
                    inputHandler, '', '', 'email')}
                {getInputElement('password',
                    'password', 'Password',
                    inputHandler, '', '', 'vpn_key')}
                <div>
                    <div>
                        <button style={{minWidth: '10vw', margin: '2vh'}} type="submit" name="action"
                                className="btn waves-effect waves-light grey"
                        ><i className="material-icons right">send</i>Sign in
                        </button>
                        <button style={{minWidth: '10vw', margin: '2vh'}} type="reset" name="action"
                                className="btn waves-effect waves-light red"
                        ><i className="material-icons right">delete</i>Reset
                        </button>
                    </div>
                </div>
            </form>
        </div>
        <div className="center-auth">
            <div className="social-btn">

                <div className='log'>
                    <a className="waves-effect waves-light col s6 btn social reddit" href={pathNewUserForm}>
                        <i className="fa fa-user-circle"></i>Sign up Now</a>
                </div>
                <div className='log'>
                    <a className="waves-effect waves-light col s6 btn social google" onClick={googleAuth}>
                        <i className="fa fa-google"></i> Sign in with google</a>
                </div>
                <div className='log'>
                    <a className="waves-effect waves-light col s6 btn  social facebook" onClick={facebookAuth}>
                        <i className="fa fa-fw fa-facebook"></i> Sign in with facebook</a>
                </div>
            </div>
        </div>
    </div>

}
