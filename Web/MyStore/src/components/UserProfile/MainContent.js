import React from "react";
import {Typography} from "@material-ui/core";
import makeStyles from "@material-ui/core/styles/makeStyles";
import * as firebase from "firebase";
import {useSelector} from "react-redux";
import OrdersTable from "../OrdersTable";
import UserOrders from "../UserOrders";
import {getInputElement} from "../../utils/inputElements";
import {ProfileForm} from "./ProfileForm";
import {NewUserForm} from "./NewUserForm";


const useStyles = makeStyles(theme => ({
    toolbar: theme.mixins.toolbar,
    title: {
        flexGrow: 1,
        backgroundColor: theme.palette.background.default,
        padding: theme.spacing(3),
    },
    content: {
        flexGrow: 1,
        padding: theme.spacing(3),
    },
    fullWidth: {
        width: '100%',
    },
}));

function MainContent(props) {
    const orders = useSelector(state=>state.orders);
    const users = useSelector(state=>state.clients);

    const classes = useStyles();
let currentUser = users.find(o => o.emailAddress == firebase.auth().currentUser.email);
let ordersByUser = currentUser?orders.filter(o=>o.emailAddress == currentUser.emailAddress):null;
console.log(currentUser)
    return (
        <main className={classes.fullWidth}>
            <div className={classes.toolbar} />
            <div className={classes.title}>
                <Typography variant='h6' style={{textAlign:"center"}}>User cabinet</Typography>
            </div>
            <div className={classes.content}>

                {currentUser?<ProfileForm user={currentUser}
                                           clientsService={props.clientsService}/>:<NewUserForm user={currentUser}
                                                                                                    clientsService={props.clientsService}/>}

                    <Typography paragraph>
                    {/*<span> User name : {currentUser.name}</span><br></br>*/}
                    {/*<span> Email : {currentUser.email}</span><br></br>*/}
                    {/*<span> Phone : {currentUser.phone}<i className="material-icons">edit</i></span><br></br>*/}
                    {/*<span> Address : {currentUser.address}</span><br></br>*/}
                    {/*<span> Passport : {currentUser.passport} </span><br></br>*/}
                    {/*<UserOrders orders={ordersByUser}/>*/}

                </Typography>
            </div>
        </main>
    );
}

export default MainContent;