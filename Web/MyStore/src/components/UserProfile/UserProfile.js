import React from "react";
import SideMenu from "./SideMenu";
import MainContent from "./MainContent";
import {makeStyles} from '@material-ui/core/styles';
import useMedia from "../../utils/mediaHook";
import navMediaObject from "../../config/navMediaConfig";


const useStyles = makeStyles(theme => ({
    root: {
        display: 'flex',
        width: '100%',
    },
}));

function UserProfile(props) {
    const hideMenu = useMedia(navMediaObject);
    const classes = useStyles();
    return (
        <div className={classes.root}>
            {hideMenu === 1 ? <SideMenu userData={props.userData}/> : null}
            <MainContent clientsService={props.clientsService} ordersService={props.ordersService}/>

        </div>
    );
}

export default UserProfile;