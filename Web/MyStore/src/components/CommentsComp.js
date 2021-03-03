import React, {useEffect, useState} from 'react'
import {useSelector} from "react-redux";
import {getRandomOrderNumb} from "../utils/random";
import * as firebase from "firebase";
import ReactPaginate from 'react-paginate';




const CommentsComp = (props) => {


    const userData = useSelector(state => state.userData);
    let comments = useSelector(state => state.comments);
    let authorName, setAuthorName;
    [authorName, setAuthorName] = useState('');
    let avatarNoUser = "https://vdostavka.ru/wp-content/uploads/2019/05/no-avatar.png";
    let avatarAdmin = "https://www.renaultkadjarforum.com/media/kunena/avatars/users/avatar280.png";
    let avatarUser = userData.username ? firebase.auth().currentUser.photoURL : null;
    let userName = userData.username ? firebase.auth().currentUser.displayName : null;
    let sortComments = comments.sort((a, b) => b.id - a.id);
    const users = useSelector(state=>state.clients);
    let currentUser = users.find(o => o.emailAddress == userData.username );

    const [pagination, setPagination] = useState({
        data: sortComments.map((comment) => (({
                id: comments.id,
                avatar: comment.avatar,
                author: comment.author,
                text: comment.text,
                data: comment.metadata.seconds
        }))),
        offset: 0,
        numberPerPage: 5,
        pageCount: 0,
        currentData: []
    });

    useEffect(() => {

        setPagination((prevState) => ({
            ...prevState,
            pageCount: prevState.data.length / prevState.numberPerPage,
            currentData: prevState.data.slice(pagination.offset, pagination.offset + pagination.numberPerPage)
        }))
    }, [pagination.numberPerPage, pagination.offset])



    const handlePageClick = event => {
        const selected = event.selected;
        const offset = selected * pagination.numberPerPage
        setPagination({ ...pagination, offset })
    }

    function addComment() {
        let id = getRandomOrderNumb();
        let text = document.getElementById("textComment").value;
        let author = userData.isAdmin ? "Admin" : userData.username ? userName : document.getElementById("authorName").value;
        let metaData = firebase.firestore.Timestamp.fromDate(new Date());
        let avatar = userData.isAdmin ? avatarAdmin : userData.username ? avatarUser : avatarNoUser;
        const comment = {id: id, author: author, text: text, avatar: avatar, metadata: metaData};
        props.commentsService.addComment(comment)
            .then(() => {
            }, error => {
            })
        document.getElementById("textComment").value = "";
        if (!userData.username) {
            document.getElementById("authorName").value = "";
        }
        return true;
    }



    function setAuthor(event) {
        authorName = "@" + event.target.value;
        setAuthorName(authorName);
        document.getElementById("textComment").value += authorName;
    }


    return (
        <div style={{display:"flex", justifyContent:"center"}}>
            <div className="ui comments">
                <h3 className="ui dividing header">Comments</h3>
                <form className="ui reply form">
                    <div className="field">
                        <div className="ui left icon input">
                            {userData.isAdmin ?
                                <span><img className='img-avatar' src={require('../images/admin.jpg')}/></span> :
                                !userData.isAdmin && userData.username ?firebase.auth().currentUser.photoURL?
                                    <span><img className='img-avatar' style={{marginRight: "5vw", borderRadius: "5%"}}
                                               src={firebase.auth().currentUser.photoURL}/>{firebase.auth().currentUser.displayName}</span> :
                                    <span><img className='img-avatar' style={{marginRight: "5vw", borderRadius: "5%"}}
                                               src={require('../images/noAvatar.png')}/>{currentUser.name}</span>:
                                    <div className="ui left icon input" style={{paddingRight: '30vw'}}>
                                        <input id="authorName" type="text" placeholder="Enter your name..."
                                               style={{padding: 0, height: '5vh'}}/>
                                        <i className="users icon"></i>
                                    </div>}
                        </div>
                        <div className="ui left icon input">
                        <textarea id="textComment" placeholder='Enter your message...'
                        ></textarea>
                            <i className="pen icon"></i>
                        </div>
                    </div>
                    <div className="ui blue labeled submit icon button" onClick={addComment} style={{marginTop: "1vh"}}>
                        <i className="icon edit"></i> Send
                    </div>
                </form>
                <div style={{marginTop:"10px"}}>
                    {pagination.currentData && pagination.currentData.map(((comment, index) => (
                        <div key={comment.data} className="comment">
                            <a className="avatar">
                                <img src={comment.avatar}/>
                            </a>
                            <div className="content" style={{display: 'contents'}}>

                                <button className="author" onClick={setAuthor} value={comment.author}
                                >{comment.author}</button>

                                <div className="metadata">
                                    <span className="date">{new Date(comment.data * 1000).toLocaleString()}</span>
                                </div>
                                <div className="text">
                                    {comment.text}
                                </div>
                            </div>
                        </div>
                    )))
                    }<div style={{marginBottom:"5vw"}}>
                <ReactPaginate
                    previousLabel={'previous'}
                    nextLabel={'next'}
                    breakLabel={'...'}
                    pageCount={pagination.pageCount}
                    marginPagesDisplayed={2}
                    pageRangeDisplayed={5}
                    onPageChange={handlePageClick}
                    containerClassName={'pagination'}
                    activeClassName={'active'}
                />
                </div>
                </div>
            </div>
        </div>
    )
}
export default CommentsComp
