import React from "react";
export default function Logout(props) {
    const authService = props.authService;
    const userDataUpdateFn = props.userDataUpdateFn;
    const onLogout = () => {
        if (window.confirm('You are going to perform logout')) {
            authService.logout();
            userDataUpdateFn({});
        }

    }
    return <i style={{"cursor":"pointer"}} onClick={onLogout}
              className="fa fa-sign-out">Sign Out</i>
}
