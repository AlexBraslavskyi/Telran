import React from "react";

export function getSelectOptions(optionStrings) {
    return optionStrings.map(os => {
        return <option key={os} value={os}>{os}</option>
    })
}
export function getErrorMessage(error){
    return error ?
        <div className="alert alert-danger">{error}</div> : <div></div>
}
export function getInputElement(type, name, label, handler,value) {
    return <div className="form-group">
        <label>{label}</label>
        <input className="form-control" value={value}
               onChange={handler} name={name} type={type}/>
    </div>
}
export function getSelectElement(name, handler, label, options) {
    return <div className="form-group">
        <label>{label}</label>
        <select className="form-control" name={name}
                onChange={handler}>
            {getSelectOptions(options)}
        </select>
    </div>
}
export function getRadioButtonElement(name, handler, value) {
    return <div className="form-check" key={value}>
        <label className="form-check-label">
            <input className="form-check-input" type="radio"
                   onChange={handler} value={value} name={name} required/>
            {value}
        </label>
    </div>
}
export function getButtonElement(label, onClickFn, type, disabledParam) {
    return <div className="form-group">
        <button type={type} onClick={onClickFn} disabled={disabledParam}>{label}</button>
    </div>
}