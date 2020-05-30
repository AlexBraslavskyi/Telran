import React from "react";

export default function Welcome(props) {
return <div className='center' style={{'margin':'5vw 10vw','fontFamily':'serif'}}>
    <h1>Welcome to Application Employee</h1>
    <img src={require('../images/Employees.jpg')} style={{'width':'20vw','height':'15vw'}}/>
    <h3>For enter push button "Sing in"</h3>
</div>
}