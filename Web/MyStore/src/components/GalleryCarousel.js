import React from "react";
import Carousel from 'react-multi-carousel';
import 'react-multi-carousel/lib/styles.css';
import item1 from "../images/di1.png"
import item2 from "../images/di2.png"
import item3 from "../images/di3.jpeg"
import item4 from "../images/di4.png"
import item5 from "../images/di5.png"
import item6 from "../images/di6.png"
import item8 from "../images/di8.png"
import item9 from "../images/di9.png"
import item10 from "../images/di10.png"
import item11 from "../images/di11.jpg"
import item12 from "../images/di12.jpg"
import item13 from "../images/di13.jpg"
import item14 from "../images/di14.jpg"
import item15 from "../images/di15.jpg"
import {pathContacts} from "../config/ShopConfig";
import CommentsComp from "./CommentsComp";

export const GalleryCarousel = (props) => {
    const items = [item1, item2, item3, item5, item6, item8, item10, item11, item12, item13, item9, item14, item4, item15];
    window.scrollTo(0, 0)
    const responsive = {
        superLargeDesktop: {
            // the naming can be any, depends on you.
            breakpoint: {max: 4000, min: 3000},
            items: 5,
        },
        desktop: {
            breakpoint: {max: 3000, min: 1024},
            items: 4,
        },
        tablet: {
            breakpoint: {max: 1024, min: 464},
            items: 3,
        },
        mobile: {
            breakpoint: {max: 464, min: 0},
            items: 2,
        }
    };
    const openInNewTab = (url) => {
        const newWindow = window.open(url, '_blank', 'noopener,noreferrer')
        if (newWindow) newWindow.opener = null
    }
    let carouselList =
        items.map(item => {
            return (<div><img className="carousel-item" style={{cursor: "zoom-in", width: "97%"}}
                              onClick={() => openInNewTab(item)}
                              src={item}/>
                </div>
            )
        })

    return (<div>
        <div className="center">
            <h1 style={{fontFamily: 'fantasy', marginTop: "50px"}}>Our balloons decoration gallery</h1>
            <h2 style={{fontFamily: 'fantasy', marginBottom: "20px"}}>For all questions please call 555-555-555 or
                <a style={{fontFamily: 'fantasy'}} href={pathContacts}> send message</a> !</h2>

        </div>
        <Carousel
            swipeable={false}
            draggable={false}
            showDots={false}
            responsive={responsive}
            ssr={true} // means to render carousel on server-side.
            infinite={true}
            autoPlay={false}
            autoPlaySpeed={4000}
            keyBoardControl={true}
            customTransition="all .5"
            transitionDuration={500}
            containerClass="carousel-container"
            removeArrowOnDeviceType={["tablet", "mobile"]}
            dotListClass="custom-dot-list-style"
            itemClass="carousel-item-padding-40-px">
            {carouselList}
        </Carousel>
        <CommentsComp/>
    </div>)
}