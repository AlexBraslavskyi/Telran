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
    }
    const googleAuth = ()=>  {
        authService.login('',1)
            // .catch(()=>alert('Wrong Google credentials'))
    }
    const facebookAuth = ()=>  {
        authService.login('',2)
        // .catch(()=>alert('Wrong Google credentials'))
    }
    const githubAuth = ()=>  {
        authService.login('',3)
        // .catch(()=>alert('Wrong Google credentials'))
    }
    const onSubmit = (event) => {
        event.preventDefault();
        authService.login(credentials,0).then().catch(()=>alert('Wrong credentials'))

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
            <div style={{'marginTop':'3vh',textAlign:"center"}}>
                <button className='btn-light' onClick={googleAuth} style={{width:'17vw',textAlign:'left'}}>
                    <img src={require('../images/Google_icon.png')}
                         style={{height:'3vh', weight:'3vh', cursor: 'pointer',marginRight:'1vw',paddingBottom: '2px'}}/>
                         LOGIN WITH GOOGLE</button>
            </div>
            <div style={{'marginTop':'1vh',textAlign:"center"}}>
                <button className='btn-light' onClick={facebookAuth} style={{width:'17vw',textAlign:'left'}}>
                    <img src={require('../images/facebook.png')}
                         style={{height:'3vh', weight:'3vh', cursor:'pointer',marginRight:'1vw',paddingBottom: '2px'}}/>
                    LOGIN WITH FACEBOOK</button>
            </div>
            <div style={{'marginTop':'1vh',textAlign:"center"}}>
                <button className='btn-light' onClick={githubAuth} style={{width:'17vw',textAlign:'left'}}>
                    <img src={require('../images/github.png')}
                         style={{height:'3vh', weight:'3vh', cursor:'pointer',marginRight:'1vw',paddingBottom: '2px'}}/>
                    LOGIN WITH GITHUB</button>
            </div>
        </div>
    </div>
}
