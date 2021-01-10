import {pathShop} from "../config/ShopConfig";
import React from "react";
import Carousel from 'react-multi-carousel';
import 'react-multi-carousel/lib/styles.css';
import {useSelector} from "react-redux";

export const MyCarousel = () => {
    const items = useSelector(state => state.items);
    const responsive = {
        desktop: {
            breakpoint: { max: 3000, min: 1024 },
            items: 5,
        },
        tablet: {
            breakpoint: { max: 1024, min: 464 },
            items: 3,
        },
        mobile: {
            breakpoint: { max: 464, min: 0 },
            items: 2,
        }
    };
    const openInNewTab = (url) => {
        const newWindow = window.open(url, '_blank', 'noopener,noreferrer')
        if (newWindow) newWindow.opener = null
    }
    let carouselList = items.length?(
        items.map(item=>{
            return(<div><img className="carousel-item" style={{cursor:"zoom-in"}} onClick={() => openInNewTab(item.img)}
                                         src={item.img} />
            </div>
        )})):null


    return items.length? <div><Carousel responsive={responsive} centerMode={true}>
      {carouselList}

    </Carousel>

    </div>:null
}