import {useDispatch, useSelector} from "react-redux";
import {LINKS} from "../config/EmployeesConfig";
import {Link} from "react-router-dom";
import React from "react";
import {actionFlagMobMenu} from "../store/actions";
import MobMenuService from "../Service/MobMenuService";

const MobileNavMenu= (props) =>{
    const dispatch = useDispatch();
    const mobMenuService = new MobMenuService();
    let flagMobMenu =  useSelector(state=>state.flagMobMenu);
const userData = useSelector(state=>state.userData);
    function showDetails (flag){

        dispatch(actionFlagMobMenu({flag:mobMenuService.changeFlag(flag)}))

        return true;
    }
const navItems = LINKS.map(link => {
    return link.isAdmin&&userData.isAdmin||!link.isAdmin? <button
        onClick={()=>showDetails(flagMobMenu.flag)}
        key={link.path} type="button" className='btn btn-info'>
        <Link to={link.path}>
            <span className='nav-link'>{link.image}{link.label}</span>
        </Link>
    </button>:null})
return <div  id='mobMenu' style={{display: flagMobMenu.flag,width:'55vw',background:'#ffc107'}}>
    <h4 className='card-header' style={{textAlign:'center'}}>Menu</h4>
    <div className="btn-group-vertical" style={{width:'55vw',padding:'0'}}>{navItems}</div>
</div>
}
export default MobileNavMenu;