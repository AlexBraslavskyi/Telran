import React from 'react';
import {pathGallery, pathShop} from "../config/ShopConfig";
import {Link} from 'react-router-dom'


export default function ShopAndGallery() {


    return (<div className="gallery">
            <div className="cards-list">

                <div className="card_home">
                    <div className="card_image"><Link to={pathShop}>
                        <img src={require("../style/images/Balloons-Background-PNG-715x715.png")}
                             style={{width: "100%"}}/>
                        <div className="card_title title-black">
                            <p>Balloons shop</p>
                        </div>
                    </Link>
                    </div>
                </div>

                <div className="card_home">
                    <div className="card_image"><Link to={pathGallery}>
                        <img src={require("../images/di9.png")} style={{width: "100%", height: "100%"}}/>
                        <div className="card_title title-white">
                            <p>Design gallery</p>
                        </div>
                    </Link>
                    </div>
                </div>

            </div>
        </div>
    );
}