import React from "react";
export default function Logout(props) {
    const authService = props.authService;
        if (window.confirm('You are going to perform logout')) {
            authService.logout();
        }
return true;
    }

