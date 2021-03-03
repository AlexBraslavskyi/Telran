import React from "react";
import {Avatar, Drawer, Grid, List, ListItem, ListItemIcon, ListItemText} from "@material-ui/core";
import {AccountCircle, ExitToApp} from "@material-ui/icons";
import makeStyles from "@material-ui/core/styles/makeStyles";
import * as firebase from "firebase";
import {Link} from "react-router-dom";
import {
    pathContacts,
    pathHome,
    pathLogout, pathNewUserForm,
    pathOrders, pathUserOrders,
    pathUserProfile
} from "../../config/ShopConfig";
import {useSelector} from "react-redux";

const drawerWidth = 240;

const useStyles = makeStyles(theme => ({
    drawer: {
        width: drawerWidth,
        flexShrink: 0,
    },
    drawerPaper: {
        width: drawerWidth,
        backgroundImage: `linear-gradient(#cfd9df,#e2ebf0)`,
        color: 'grey',
    },
    bigAvatar: {
        marginTop: '10vh',
        width: 100,
        height: 100,
    },
}));


function SideMenu(props) {
    const userData = useSelector(state => state.userData);
    const classes = useStyles();
    return (
        <Drawer
            open={true}
            variant='permanent'
            anchor='left'
            className={classes.drawer}
            classes={{
                paper: classes.drawerPaper,
            }}
        >
            <Grid container justify='center' alignItems='center'>
                <Avatar
                    src={firebase.auth().currentUser.photoURL}
                    className={classes.bigAvatar}
                />
                <span style={{marginBottom:"5vh"}}>{firebase.auth().currentUser.displayName}</span>
            </Grid>
            <List style={{display:"grid"}}>
                <Link className="list-left" to={!userData.isAdmin?pathUserProfile:pathNewUserForm}>Edit Profile<i className="material-icons left">account_circle</i></Link>
                <Link className="list-left" to={!userData.isAdmin?pathUserOrders:pathOrders}>My Orders<i className="material-icons left">shopping_basket</i></Link>
                <Link className="list-left" to={pathContacts}>Contact Us<i className="material-icons left">alternate_email</i></Link>
                <Link className="list-left" to={pathHome}>Chat<i className="material-icons left">chat</i></Link>
                <Link className="list-left" to={pathLogout}>Sing Out<i className="material-icons left">exit_to_app</i></Link>
            </List>
        </Drawer>
    );
}

export default SideMenu;