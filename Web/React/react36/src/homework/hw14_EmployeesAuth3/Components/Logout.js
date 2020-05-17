import React from "react";
export default function Logout(props) {
    const authService = props.authService;
    const usernameUpdateFn = props.usernameUpdateFn;
        if (window.confirm('You are going to perform logout')) {
            authService.logout();
            usernameUpdateFn('');
        }
return true;
    }

