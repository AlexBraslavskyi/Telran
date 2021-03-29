import React, {useEffect, useState} from 'react'
import {useSelector} from "react-redux";
import ReactPaginate from 'react-paginate';


const PaginationComments = (props) => {
    const sortedComments = useSelector(state => state.comments).sort((a, b) => b.id - a.id);
    let authorName, setAuthorName;
    [authorName, setAuthorName] = useState('');
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
    console.log(pagination);

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
        setPagination({...pagination, offset})
    }

    function setAuthor(event) {
        authorName = "@" + event.target.value;
        setAuthorName(authorName);
        document.getElementById("textComment").value += authorName;
    }

    return <div>
        {pagination.currentData.map(((comment, index) => (
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
}
export default PaginationComments