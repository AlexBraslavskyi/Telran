import React from 'react'
import {MyCarousel} from "./MyCarousel";
import CommentsComp from "./CommentsComp";
import {useSelector} from "react-redux";
import ShopAndGallery from "./ShopAndGallery";


export const Home = (props) => {
    const loader = useSelector(state => state.comments);

    return <div style={{marginTop: "50px"}}>
        {loader.length > 0 ?
            <div>
                <MyCarousel itemsService={props.itemsService}/>
                <ShopAndGallery/>
                <CommentsComp commentsServise={props.commentsService} clientsService={props.clientsService}/>
            </div>
            : null}
    </div>

}
