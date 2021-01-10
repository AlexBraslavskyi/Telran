import React from "react";
import SideMenu from "./SideMenu";
import MainContent from "./MainContent";
import { makeStyles } from '@material-ui/core/styles';


const useStyles = makeStyles(theme => ({
    root: {
        display: 'flex',
    },
}));
function UserProfile() {
    const classes = useStyles();

    return (
        <div className={classes.root}>
            <SideMenu />
            <MainContent />
        </div>
    );
}

export default UserProfile;