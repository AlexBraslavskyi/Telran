import React, {useEffect} from 'react';
import Navbar from './components/Navbar'
import Cart from './components/Cart'
import './style/Style.css'
import {Home} from './components/Home';
import Shop from './components/Shop'
import Login from './components/Login';
import Logout from './components/Logout';
import OrdersFirebaseService from "./services/OrdersFirebaseService";
import AuthFirebaseService from "./services/AuthFirebaseService";
import MobMenuService from "./services/MobMenuService";
import {useDispatch, useSelector} from "react-redux";
import 'fomantic-ui-css/semantic.css';
import {
    actionClients,
    actionComments,
    actionFlagMobMenu,
    actionItems,
    actionOrders,
    actionUserData,
} from "./components/actions/actions";
import {
    pathCart,
    pathShop,
    pathLogin,
    pathLogout,
    pathHome,
    pathOrders,
    pathProducts,
    pathStatistics,
    pathContacts,
    pathUserProfile,
    pathUserOrders,
    pathNewUserForm,
    pathGallery
} from './config/ShopConfig';
import Orders from './components/Orders';
import ItemsFirebaseService from "./services/ItemsFirebaseService";
import ProductsTable from "./components/ProductsTable";
import Statistics from "./components/Statistics";
import Contacts from "./components/Contacts";
import CommentsFirebaseService from "./services/CommentsFirebaseService";
import {Redirect, Route, Switch} from "react-router";
import {BrowserRouter} from "react-router-dom";
import UserProfile from "./components/UserProfile/UserProfile";
import {AppBar, Typography} from "@material-ui/core";
import makeStyles from "@material-ui/core/styles/makeStyles";
import UsersFirebaseService from "./services/UsersFirebaseService";
import UserOrders from "./components/UserOrders";
import {NewUserForm} from "./components/UserProfile/NewUserForm";
import {GalleryCarousel} from "./components/GalleryCarousel";


const App = () => {
    const ordersService =
        new OrdersFirebaseService('orders');
    const itemsService = new ItemsFirebaseService('items');
    const clientsService = new UsersFirebaseService('clients');
    const commentsService = new CommentsFirebaseService('comments');
    const authService = new AuthFirebaseService();
    const mobMenuService = new MobMenuService();
    const dispatch = useDispatch();
    const userData = useSelector(state => state.userData);


    useEffect(() => {
        dispatch(actionFlagMobMenu({flag: mobMenuService.getFlag()}))
        clientsService.getUsers().subscribe(clients => {
                dispatch(actionClients(clients));
            }
        )

        itemsService.getItems().subscribe(items => {
                dispatch(actionItems(items));
            }
        )
        commentsService.getComments().subscribe(comments => {
                dispatch(actionComments(comments));
            }
            , error => alert('CommentsData transfer finished')
        )

        authService.getUserData().subscribe(userData => {
            dispatch(actionUserData(userData))

            if (userData.username) {
                ordersService.getOrders().subscribe(orders => {
                        dispatch(actionOrders(orders));
                    }
                );
            }
        })

    }, []);

    const useStyles = makeStyles(theme => ({
        appBar: {
            top: 'auto',
            bottom: 0,
            width: '100%',
        },
        menuButton: {
            marginRight: theme.spacing(2),
        },
        title: {
            flexGrow: 1,
            marginLeft: 10,
            background: "black",
        },
        footer: {
            background: "black",
            fontSize: 17,
        },
    }));
    const classes = useStyles();
    return (
        <BrowserRouter>
            <Navbar userData={userData}/>
            <Switch>
                <Route exact path="/" render={() => {
                    return <Redirect to={pathHome}/>
                }}/>
                <Route path={pathHome} exact render={() => {
                    return <Home
                        commentsService={commentsService} clientsService={clientsService} itemsService={itemsService}/>
                }}/>
                <Route path={pathCart} exact render={() => {
                    return userData.username ? <Cart/> :
                        <Redirect to={pathLogin}/>
                }}/>
                <Route path={pathGallery} exact render={() =>
                    <GalleryCarousel/>}/>
                <Route path={pathContacts} exact render={() => {
                    return <Contacts/>
                }}/>
                <Route path={pathStatistics} exact render={() => {
                    return userData.isAdmin ? <Statistics/> :
                        <Redirect to={pathHome}/>
                }}/>
                <Route path={pathShop} exact render={() => {
                    // return userData.username ?
                       return <Shop
                            itemsService={itemsService}
                        />
                        // : <Redirect to={pathLogin}/>
                }}/>
                <Route path={pathOrders} exact render={() => {
                    return userData.username ? <Orders ordersService={ordersService}/> :
                        <Redirect to={pathLogin}/>
                }}/>
                <Route path={pathProducts} exact render={() => {
                    return userData.username ? <ProductsTable itemsService={itemsService}/> :
                        <Redirect to={pathLogin}/>
                }}/>
                <Route path={pathNewUserForm} exact render={() => {
                    return <NewUserForm clientsService={clientsService}/>
                }}/>

                <Route path={pathUserProfile} exact render={() => {
                    return !userData.isAdmin ? <UserProfile authService={authService} clientsService={clientsService}
                                                            ordersService={ordersService}/> :
                        <Redirect to={pathNewUserForm}/>
                }}/>
                <Route path={pathUserOrders} exact render={() => {
                    return userData.username ?
                        <UserOrders clientsService={clientsService} ordersService={ordersService}/> :
                        <Redirect to={pathLogin}/>
                }}/>

                <Route path={pathLogin} exact render={() => {
                    return !userData.username ? <Login authService={authService}/> :
                        <Redirect to={pathHome}/>
                }}/>

                <Route path={pathLogout} exact render={() => {
                    return userData.username ? <Logout authService={authService}/> :
                        <Redirect to={pathHome}/>
                }}/>
            </Switch>
            <AppBar position='sticky' className={classes.appBar}>
                <Typography variant='subtitle2' className={classes.footer}>
                    © 2020 Copyright Alex Braslavskyi
                </Typography>
            </AppBar>
        </BrowserRouter>
    );
}
export default App;