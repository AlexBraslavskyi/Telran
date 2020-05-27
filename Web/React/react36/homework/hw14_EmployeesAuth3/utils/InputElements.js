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
export function getInputElement(type, name, label, handler,errorMessage,valueProps) {
    return <div className="form-group">
        <label>{label}</label>
        <input className={"form-control" + (valueProps.controlError===-1
              ? " is-invalid"
              : valueProps.controlError===0
              ? ""
              : " is-valid")} defaultValue={valueProps.value}
               onChange={handler}  placeholder={name} name={name} type={type} />
        <div className="alert alert-danger">{errorMessage}</div>
    </div>
}
export function getInputElementBlur(type, name, label, handler,errorMessage,valueProps) {
    return <div className="form-group">
        <label>{label}</label>
        <input className={"form-control" + (valueProps.controlError===-1
            ? " is-invalid"
            : valueProps.controlError===0
                ? ""
                : " is-valid")} defaultValue={valueProps.value}
               onBlur={handler}  placeholder={name} name={name} type={type} />
        <div className="alert alert-danger">{errorMessage}</div>
    </div>
}
export function getSelectElement(name, handler, label, options) {
    return <div className="form-group">
        <label>{label}</label>
        <select className="form-control" name={name} placeholder={name}
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