import React from "react";
export default function Logout(props) {
    const authService = props.authService;
    const userDataUpdateFn = props.userDataUpdateFn;
        if (window.confirm('You are going to perform logout')) {
            authService.logout();
        }
return true;
    }

