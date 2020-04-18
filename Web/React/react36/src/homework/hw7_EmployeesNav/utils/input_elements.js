import React from "react";
export function getInputElement(label, type, handler,
                         name, value) {
    return <div className="form-group">
        <label>{label}</label>
        <input type={type} onChange={handler} name={name}
        value={value} className="form-control"/>

    </div>
}

function getOptions(options) {
    return options.map(option => {
        return <option key={option} value={option}>{option} </option>
    })
}

function getSelectElement(label, handler,
    name, options) {
    return <div className="form-group">
        <label>{label}</label>
        <select onChange={handler} name={name} className="form-control">
            {getOptions(options)}
        </select>
    </div>
}
function getErrorMessage(error) {
    return error ? <label className="alert alert-danger">{error}
    </label> : <null></null>
}
