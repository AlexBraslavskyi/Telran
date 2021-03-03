import React from "react";
import SideMenu from "./SideMenu";
import MainContent from "./MainContent";
import { makeStyles } from '@material-ui/core/styles';


const useStyles = makeStyles(theme => ({
    root: {
        display: 'flex',
    },
}));
function UserProfile(props) {

    const classes = useStyles();
    return (
        <div className={classes.root}>
            <SideMenu userData={props.userData}/>
            <MainContent clientsService = {props.clientsService} ordersService={props.ordersService}/>

        </div>
    );
}

export default UserProfile;