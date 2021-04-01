import React, {useEffect, useState} from 'react';
import {connect} from 'react-redux'
import {addToCart} from './actions/actions'
import ReactPaginate from "react-paginate";

const Shop = (props) => {
    window.scrollTo(0, 0)
    const handleClick = (id) => {
        props.addToCart(id);
    }
    const items = props.items;

    const [pagination, setPagination] = useState({
        data: items.map((item) => (({
            id: item.id,
            title: item.title,
            img: item.img,
            price: item.price,
            description: item.description
        }))),
        offset: 0,
        numberPerPage: 8,
        pageCount: 0,
        currentData: []
    });
    useEffect(() => {
        setPagination((prevState) => ({
            ...prevState,
            pageCount: prevState.data.length / prevState.numberPerPage,
            currentData: items.map((item) => (({
                id: item.id,
                title: item.title,
                img: item.img,
                price: item.price,
                description: item.description
            }))).slice(pagination.offset, pagination.offset + pagination.numberPerPage),
        }));
    }, [pagination.numberPerPage, pagination.offset])

    const handlePageClick = event => {
        const selected = event.selected;
        const offset = selected * pagination.numberPerPage
        setPagination({...pagination, offset})
        window.scrollTo(0, 0)
    }

    return (
        <div className="body">
            <h2 className="bodyTitle" style={{
                fontFamily: "fantasy", fontWeigh: "bold"
            }}> - Our balloons - </h2>
            <div className="box">
                {pagination.currentData.map(((item, index) => (
                    <div className="wrapper" key={item.id}>
                        <div className="card">
                            <div className="card-image">
                                <img className='itemImg' src={item.img} alt={item.title}/>
                                <span to="/" className="btn-floating halfway-fab waves-effect waves-light red"
                                      onClick={() => {
                                          handleClick(item.id)
                                      }}><i className="material-icons">add</i></span>
                            </div>

                            <div className="card-content">
                                <p style={{marginTop: '5px'}}><b>Price: {item.price}$</b></p>
                                <span className="card-title"
                                      style={{
                                          display: 'block',
                                          textOverflow: 'ellipsis',
                                          width: '400',
                                          overflow: 'hidden',
                                          whiteSpace: 'nowrap'
                                      }}
                                >{item.title}</span>
                            </div>
                            <div className="inside">
                                <div className="icon"><i className="material-icons">info</i></div>
                                <div className="contents">
                            <span className="card-title">
                            {item.description}</span>
                                </div>
                            </div>
                        </div>
                    </div>


                )))}
            </div>
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
    )
}

const mapStateToProps = (state) => {

    return {
        items: state.items,
    }
}
const mapDispatchToProps = (dispatch) => {

    return {
        addToCart: (id) => {
            dispatch(addToCart(id))
        }
    }
}

export default connect(
    mapStateToProps,
    mapDispatchToProps)(Shop);
