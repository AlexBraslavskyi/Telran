import React from "react";
import s from './Profile.module.css'
import MyPosts from "./MyPosts/MyPosts";
const Profile =()=>{
    return <div className={s.content}>
        <div>
            <img src='https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcSC7Zl5NMazhwTzMkpqA5RneJTpiE7LYAh6-DSHiAWx2A5shf7F&usqp=CAU'/>
        </div>
        <div>
            ava+discr
        </div>
       <MyPosts/>
    </div>


}
export default Profile;