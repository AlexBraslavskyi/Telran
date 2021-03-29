import React, {useEffect, useState} from 'react'
import {useSelector} from "react-redux";
import {getRandomOrderNumb} from "../utils/random";
import * as firebase from "firebase";
import ReactPaginate from 'react-paginate';
import {getTextFieldElement} from "../utils/inputElements";


const CommentsComp = (props) => {


    const userData = useSelector(state => state.userData);
    let comments = useSelector(state => state.comments);
    let authorName, setAuthorName;
    [authorName, setAuthorName] = useState('');
    let avatarNoUser = "https://vdostavka.ru/wp-content/uploads/2019/05/no-avatar.png";
    let avatarAdmin = "https://www.renaultkadjarforum.com/media/kunena/avatars/users/avatar280.png";
    const users = useSelector(state => state.clients);
    let currentUser = userData.username ? users.find(o => o.emailAddress == userData.username) : null;
    let avatarUser = userData.username ? firebase.auth().currentUser.photoURL : null;
    let userName = userData.isAdmin ? "Admin" : currentUser ? currentUser.name :
        firebase.auth().currentUser ? firebase.auth().currentUser.displayName : "UnRegisteredUser";
    let sortedComments = comments.sort((a, b) => b.id - a.id);
    let text, setText, error, setError;
    [text, setText] = useState({value: ""});
    const [pagination, setPagination] = useState({
        data: sortedComments.map((comment) => (({
            id: comment.id,
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
            currentData: sortedComments.map((comment) => (({
                id: comment.id,
                avatar: comment.avatar,
                author: comment.author,
                text: comment.text,
                data: comment.metadata.seconds
            }))).slice(pagination.offset, pagination.offset + pagination.numberPerPage),

        }));
        setText(text);
    }, [pagination.numberPerPage, pagination.offset, comments])

    const handlePageClick = event => {
        const selected = event.selected;
        const offset = selected * pagination.numberPerPage
        setPagination({...pagination, offset})
    }

    function addComment(event) {
        event.preventDefault();
        let id = getRandomOrderNumb();
        let getText = text.value;
        let author = userName != "UnRegisteredUser" ? userName : document.getElementById("authorName").value ? document.getElementById("authorName").value : "UnRegisteredUser";
        let metaData = firebase.firestore.Timestamp.fromDate(new Date());
        let avatar = userData.isAdmin ? avatarAdmin : avatarUser ? avatarUser : avatarNoUser;
        const comment = {id: id, author: author, text: getText, avatar: avatar, metadata: metaData};

        props.commentsServise.addNewComment(comment)
            .then(() => {
            })
        document.getElementById("textComment").value = "";
        if (!userData.username) {
            document.getElementById("authorName").value = "";
        }
        setText({value: ""});
        return true;

    }

    function setAuthor(event) {
        authorName = "@" + event.target.value;
        setAuthorName(authorName);
        document.getElementById("textComment").value += authorName;
    }

    function handlerTextField(event) {
        let addedText = event.target.value;
        text = {value: ""}

        if (addedText == "") {
            text = {value: addedText}
        } else {
            text = {value: addedText}
        }
        setText(text);
    }

    function validate() {
        if (text.value != "") {
            return false
        }
        return true
    }

    return (
        <div style={{display: "flex", justifyContent: "center"}}>
            <div className="ui comments">
                <h3 className="ui dividing header">Comments</h3>
                <form className="ui reply form" onSubmit={addComment}>
                    <div className="field">
                        <div className="ui left icon input">
                            {userData.isAdmin ?
                                <span><img className='img-avatar' src={require('../images/admin.jpg')}/></span> :
                                !userData.isAdmin && userData.username ? firebase.auth().currentUser.photoURL ?
                                    <span><img className='img-avatar' style={{marginRight: "5vw", borderRadius: "5%"}}
                                               src={firebase.auth().currentUser.photoURL}/>{firebase.auth().currentUser.displayName}</span> :
                                    <span><img className='img-avatar' style={{marginRight: "5vw", borderRadius: "5%"}}
                                               src={require('../images/noAvatar.png')}/>{currentUser.name}</span> :
                                    <div className="ui left icon input" style={{paddingRight: '30vw'}}>
                                        <input id="authorName" type="text" placeholder="Please enter your name..."
                                               style={{padding: 0, height: '5vh'}}/>
                                        <i className="users icon"></i>
                                    </div>}
                        </div>
                        {getTextFieldElement("textComment", "Please enter your message...", "pen icon", handlerTextField)}
                    </div>
                    <button type="submit" name="action" className="btn waves-effect waves-light grey"
                            disabled={validate()}
                    ><i className="icon edit"></i>Send
                    </button>
                </form>
                <div style={{marginTop: "10px"}}>
                    {pagination.currentData.map(((comment, index) => (
                        <div key={comment.data} className="comment">
                            <a className="avatar">
                                <img src={comment.avatar}/>
                            </a>
                            <div className="content" style={{display: 'contents'}}>
                                <button className="author" onClick={setAuthor}
                                        value={comment.author}>{comment.author}</button>
                                <div className="metadata">
                                    <span className="date">{new Date(comment.data * 1000).toLocaleString()}</span>
                                </div>
                                <div className="text">
                                    {comment.text}
                                </div>
                            </div>
                        </div>
                    )))
                    }
                    <div style={{marginBottom: "5vw"}}>
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
