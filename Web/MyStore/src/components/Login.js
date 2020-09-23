import React, {useState} from "react";
import {getInputElement} from "../utils/inputElements";


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
    const googleAuth = ()=>  {
        authService.login('',1)

    }
    const facebookAuth = ()=>  {
        authService.login('',2)

    }
    const githubAuth = ()=>  {
        authService.login('',3)
    }
    const linkedInAuth = ()=>  {
        authService.login('',4)
    }
    const onSubmit = (event) => {
        event.preventDefault();
        authService.login(credentials,0).then().catch(()=>alert('Wrong credentials'))

    }

    return  <div className="content">
            <h2 className="center-align" style={{color:"#ee6e73",fontFamily:"fantasy", marginTop:"5vh"}}>My cabinet</h2>
    
        <div className="log">
            <form className="col s6 center-align" onSubmit={onSubmit} style={{width:"35vw"}}>
                {getInputElement('email',
                    'email', 'Email',
                    inputHandler,'','','email')}
                {getInputElement('password',
                    'password', 'Password',
                    inputHandler,'','','vpn_key')}
                        <div>
                    <div >
                    <button style = {{minWidth:'10vw', margin:'2vh'}} type="submit" name="action"className="btn waves-effect waves-light blue"
                            > <i className="material-icons right">send</i>Sign
                        </button>
                    </div>
                    <div>
                        <button  style = {{minWidth:'10vw', margin:'2vh'}} type = "reset" name = "action" className = "btn waves-effect waves-light red"
                            > <i className="material-icons right">delete</i>Reset
                        </button>
                        </div>
                        </div>
            </form>
        </div>
    
            <div className="social-btn">
            <div className='log'>
                <a className="waves-effect waves-light col s6 btn social google" onClick={googleAuth}>
                    <i className="fa fa-google"></i> Sign in with google</a>
                </div>
                <div className='log'>
                <a className="waves-effect waves-light col s6 btn  social facebook" onClick={facebookAuth}>
                    <i className="fa fa-fw fa-facebook"></i> Sign in with facebook</a>
                </div>
                    <div className='log'>
                <a className="waves-effect waves-light col s6 btn social github" onClick={githubAuth}>
                        <i className="fa fa-github"></i> Sign in with github</a>
                    </div>
                    <div className='log'>
                    <a className="waves-effect waves-light col s6 btn social linkedin" onClick={linkedInAuth}>
                     <i className="fa fa-linkedin"></i> Sign in with linkedIn</a>
                    </div>
            </div>
        </div>
        
        {/* <div className="fixed-action-btn horizontal click-to-toggle">
            <a className="btn-floating btn-large red">
                <i className="material-icons">menu</i>
            </a>
            <ul>
                <li>
                    <a className="waves-effect waves-light btn-floating social google" onClick={googleAuth}><i className="fa fa-google"></i></a>
                </li>
                <li>
                    <a className="waves-effect waves-light btn-floating facebook" onClick={facebookAuth}><i className="fa fa-facebook"></i></a>
                </li>
                <li>
                    <a className="waves-effect waves-light btn-floating github" onClick={githubAuth}><i className="fa fa-github"></i></a>
                </li>
            </ul>
        </div> */}



}
