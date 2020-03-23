import React from 'react';
import TodoItem from './TodoItem';
const styles = {
    ul:{
        listStyle:"none",
        margin:0,
        padding:0

    }
}

export default function TodoList (props){
    return (
        <div>
        <ul style={styles.ul}>
    {props.todos.map(todo=> {return <TodoItem todo={todo} key= {todo.id}/>})}
        </ul>
        </div>
    )}
