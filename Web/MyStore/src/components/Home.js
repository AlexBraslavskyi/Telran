  import React from 'react'
  import {MyCarousel} from "./MyCarousel";
  import CommentsComp from "./CommentsComp";
  import {useSelector} from "react-redux";
  // import {pathShop} from "../config/ShopConfig";
  // import M from "materialize-css";
  // import * as firebase from "../../firebase.json";

 export const Home =(props)=>{
const loader = useSelector(state => state.comments);

        return <div>
                <h3 className="bodyTitle">TODO Welcome</h3>
            <MyCarousel />
            {loader.length>0?<div>
            <CommentsComp commentsServise={props.commentsService} clientsService={props.clientsService}/>
            </div>:null}
            </div>
    
}
