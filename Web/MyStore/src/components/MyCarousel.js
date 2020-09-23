import {pathShop} from "../config/ShopConfig";
import React from "react";
import M from "materialize-css";

export const MyCarousel = () => {
    document.addEventListener('DOMContentLoaded', function() {
        var elems = document.querySelector('.carousel');
        M.Carousel.init(elems);
    });
    return <div className="carousel">
        <a className="carousel-item" href={pathShop}><img
            src={require("../images/anagram-28-frozen-sing-a-tune-foil-balloon-30324-01-a-p-14790223102015_900x.jpg")}/></a>
        <a className="carousel-item" href="#"><img
            src={require("../images/anagram-28-spider-man-birthday-sing-a-tune-foil-balloon-19531-01-a-p-14240379502655_900x.jpg")}/></a>
        <a className="carousel-item" href="#"><img
            src={require("../images/anagram-29-sweet-chocolate-sing-a-tune-foil-balloon-31850-01-a-p-14130106073151_900x.jpg")}/></a>
        <a className="carousel-item" href="#"><img
            src={require("../images/cti-12-all-round-bachelorette-party-latex-balloons-950046-c-14798915436607_900x.jpg")}/></a>
        <a className="carousel-item" href="#"><img
            src={require("../images/cti-12-i-love-you-hearts-6-pk-latex-balloons-912646-c-14838162292799_900x.jpg")}/></a>
        <a className="carousel-item" href="#"><img
            src={require("../images/cti-12-pastel-birthday-6-pk-latex-balloons-912609-c-14841568395327_900x.jpg")}/></a>
    </div>
}