import {useDispatch, useSelector} from "react-redux";
import {
    LINKS, pathCart,
    pathContacts, pathHome, pathLogin, pathLogout, pathNewUserForm,
    pathOrders,
    pathProducts,
    pathShop,
    pathStatistics, pathUserOrders,
    pathUserProfile
} from "../config/ShopConfig";
import {Link} from "react-router-dom";
import React from "react";
import {actionFlagMobMenu} from "./actions/actions";
import MobMenuService from "../services/MobMenuService";
import {Dropdown} from "react-materialize";

const MobileNavMenu = (props) => {
    let flagMobMenu = useSelector(state => state.flagMobile);
    const userData = useSelector(state => state.userData);

    return <div id='mobMenu' style={{display: flagMobMenu.flag}}>
        <h4 className='card-header' style={{textAlign: 'center'}}>Menu</h4>
        <ul className="mobile-button-group">
            <li><Link className="nav-button-mob" to={pathHome}>Home</Link></li>
            {userData.username ?
                <li><Dropdown
                    id="Dropdown_7"
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
                    trigger={<Link className="nav-button-mob" to={""}>My Cabinet <i id="drop_icon"
                                                                                    class="fa fa-caret-right"></i></Link>}
                >
                    <Link to={!userData.isAdmin ? pathUserProfile : pathNewUserForm}>Profile</Link>
                    <Link to={!userData.isAdmin ? pathUserOrders : pathOrders}>My Orders</Link>
                    <Link to={pathContacts}>Contact Us</Link>
                    <Link to={pathHome}>Chat</Link>
                    <Link style={{borderTop: "solid"}} to={pathLogout}>Sing Out</Link>
                </Dropdown></li>
                : null}
            <li><Link className="nav-button-mob" to={pathShop}>Shop</Link></li>
            {userData.isAdmin ? <li><Link className="nav-button-mob" to={pathOrders}>Orders</Link></li> : null}
            {userData.isAdmin ? <li><Link className="nav-button-mob" to={pathStatistics}>Statistics</Link></li> : null}
            {userData.isAdmin ? <li><Link className="nav-button-mob" to={pathProducts}>Products</Link></li> : null}
            <li><Link className="nav-button-mob" to={pathContacts}>Contacts</Link></li>
        </ul>
    </div>
}
export default MobileNavMenu;