import React from 'react';
import {Link} from 'react-router-dom'
import {connect, useDispatch} from 'react-redux';
import {useSelector} from "react-redux";
import {actionFlagMobMenu, addToCart} from "./actions/actions";
import {
    pathCart,
    pathShop,
    pathOrders,
    pathHome,
    pathLogout,
    pathLogin,
    pathProducts,
    pathStatistics, pathContacts, pathUserProfile, pathUserOrders, pathNewUserForm
} from '../config/ShopConfig';
import {Dropdown} from "react-materialize";
import MobMenuService from "../services/MobMenuService";
import navMediaObject from "../config/navMediaConfig";
import useMedia from "../utils/mediaHook";
import MobileNavMenu from "./MobileNavMenu";


const Navbar = (props) => {

    const buttons = useMedia(navMediaObject);
    const dispatch = useDispatch();
    const mobMenuService = new MobMenuService();
    let flagMobMenu = useSelector(state => state.flagMobile);

    const userData = props.userData
    const quantity = useSelector(state => state.quantity);
    let itemsCount = quantity;


    function showDetails(flag) {

        dispatch(actionFlagMobMenu({flag: mobMenuService.changeFlag(flag)}))

        return true;
    }


    const navItems = <div>
        <img className='left' id="logo_img" src={require('../style/images/Daco_4048601.png')}/>
        <Link to={pathHome} id="logo_text" className="left">
            Happy Balloons</Link>
        <ul className="right">
            <li><Link className="nav-button" to={pathShop}>Shop</Link></li>
            {userData.isAdmin ? <li><Link className="nav-button" to={pathOrders}>Orders</Link></li> : null}
            {userData.isAdmin ? <li><Link className="nav-button" to={pathStatistics}>Statistics</Link></li> : null}
            {userData.isAdmin ? <li><Link className="nav-button" to={pathProducts}>Products</Link></li> : null}
            <li><Link className="nav-button" to={pathContacts}>Contacts</Link></li>
            {userData.username ?
                <li><Dropdown
                    id="Dropdown_6"
                    options={{
                        alignment: 'left',
                        autoTrigger: true,
                        closeOnClick: true,
                        coverTrigger: false,
                        hover: false,
                        inDuration: 150,
                        onCloseEnd: null,
                        onCloseStart: null,
                        onOpenEnd: null,
                        onOpenStart: null,
                        outDuration: 250,
                    }}
                    trigger={<Link className="nav-buttonDrop" to={""}>My Cabinet <i
                        className="material-icons right">arrow_drop_down</i></Link>}
                >
                    <Link to={!userData.isAdmin ? pathUserProfile : pathNewUserForm}>Profile<i
                        className="material-icons left">account_circle</i></Link>
                    <Link to={!userData.isAdmin ? pathUserOrders : pathOrders}>My Orders<i
                        className="material-icons left">shopping_basket</i></Link>
                    <Link to={pathContacts}>Contact Us<i className="material-icons left">alternate_email</i></Link>
                    <Link to={pathHome}>Chat<i className="material-icons left">chat</i></Link>
                    <Link style={{borderTop: "solid"}} to={pathLogout}>Sing Out<i
                        className="material-icons left">exit_to_app</i></Link>
                </Dropdown></li>
                : null}
            <li><Link style={{height: '64px'}} to={pathCart}><span
                className='basketQuant'>{itemsCount ? itemsCount : null}</span>
                <i className="fa fa-shopping-cart fa-2x"/>
            </Link></li>
            {!userData.username ? <li><Link to={pathLogin}>Login</Link></li>
                : null}
        </ul>
    </div>


    function getLogo() {
        return <>

            <img className='right' style={{width: '50px', height: '85px', marginLeft: '5vw'}}
                 src={require('../style/images/Daco_4048601.png')}/>
            <ul className="right">
                {!userData.username ? <li><Link to={pathLogin}>Login</Link></li>
                    : null}
                <li><Link style={{height: '64px'}} to={pathCart}><span
                    className='basketQuant'>{itemsCount ? itemsCount : null}</span>
                    <i id="icon_basket" className="fa fa-shopping-cart fa-2x"/>
                </Link></li>
            </ul>
        </>;
    }

    return buttons === 1 ?
        <nav>
            <div className="nav-wrapper">
                <div>{navItems}</div>
            </div>
        </nav>
        : <nav>
            {flagMobMenu.flag === 'none' ? <div className="nav-wrapper">
                    <i style={{cursor: 'pointer', marginLeft: '15px'}}
                       onClick={() => showDetails(flagMobMenu.flag)} className="fa fa-bars fa-3x"/>
                    {getLogo()}</div> :
                <div className="nav-wrapper">
                    <i style={{cursor: 'pointer', marginLeft: '15px'}} onClick={() => showDetails(flagMobMenu.flag)}
                       className="fa fa-times fa-3x"/>
                    {getLogo()}
                    <MobileNavMenu/></div>}
        </nav>

}

const mapStateToProps = (state) => {
    return {
        quantity: state.quantity,
    }
}
const mapDispatchToProps = (dispatch) => {
    return {
        addToCart: (id) => {
            dispatch(addToCart(id))
        }
    }
}
export default connect(mapStateToProps, mapDispatchToProps)(Navbar);