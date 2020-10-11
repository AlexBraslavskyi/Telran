  import React from 'react'
  import M from 'materialize-css/dist/js/materialize';
  import {MyCarousel} from "./MyCarousel";
  import {pathShop} from "../config/ShopConfig";
 export const Home =()=>{
     document.addEventListener('DOMContentLoaded', function() {
         var instance = {
             fullWidth: true,
             indicators: true
         };
         var elems = document.querySelectorAll('.carousel.carousel-slider');
         M.Carousel.init(elems,instance);

     });

        return <div className='body'>
                <h3 className="bodyTitle">TODO Welcome</h3>
            {/* <div className="carousel carousel-slider">
                <a className="carousel-item" href="#one!"><img src="https://lorempixel.com/800/400/food/1"/></a>
                <a className="carousel-item" href="#two!"><img src="https://lorempixel.com/800/400/food/2"/></a>
                <a className="carousel-item" href="#three!"><img src="https://lorempixel.com/800/400/food/3"/></a>
                <a className="carousel-item" href="#four!"><img src="https://lorempixel.com/800/400/food/4"/></a>
            </div> */}
            <MyCarousel/>
            </div>
    
}
