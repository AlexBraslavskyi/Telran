import React from "react";
import M from 'materialize-css/dist/js/materialize';
import $ from "jquery";


export function getSelectOptions(optionStrings) {
    return
    optionStrings.map(os => {
        return <div>
            <option key={os} value={os}>{os}</option>
        </div>
    })
}

export function getErrorMessage(error) {
    return error ?
        <div className="alert alert-danger">{error}</div> : <div></div>
}

export function getInputElement(type, name, label, handler, errorMessage, valueProps, icon, disabled) {
    return <div className="row">
        <div className="input-field col s12">
            <i className="material-icons prefix">{icon}</i>
            <input id={label} required="required" disabled={disabled}
                   className={errorMessage
                       ? "invalid"
                       : "validate"
                   }
                   defaultValue={valueProps.value}
                   onChange={handler} name={name} type={type}/>
            <label htmlFor={label}
            >{label}</label>

            <span className="helper-text" data-error={errorMessage}
                  data-success={"Perfect!"}></span>

        </div>
    </div>
    $(document).ready(function () {
        M.updateTextFields()
    });
}

export function getInputElementActive(type, name, label, handler, errorMessage, valueProps, icon, disabled) {
    return <div className="row">
        <div className="input-field col s12">
            <i className="material-icons prefix">{icon}</i>
            <input id={label} required="required" disabled={disabled}
                   className={errorMessage
                       ? "invalid"
                       : "validate"
                   }
                   defaultValue={valueProps}
                   onChange={handler} name={name} type={type}/>
            <label htmlFor={label} className="active"
            >{label}</label>
            <span className="helper-text" data-error={errorMessage} data-success="Perfect!"></span>
        </div>
    </div>
    $(document).ready(function () {
        M.updateTextFields()

    });
}

export function getInputElementBlur(type, name, label, handler, errorMessage, valueProps) {
    return <div className="form-group">
        <label>{label}</label>
        <input className={"form-control" + (valueProps.controlError === -1
            ? " is-invalid"
            : valueProps.controlError === 0
                ? ""
                : " is-valid")} defaultValue={valueProps.value}
               onBlur={handler} placeholder={name} name={name} type={type}/>
        <div className="alert alert-danger">{errorMessage}</div>
    </div>
}

export function getRadioButtonElement(name, handler, value) {
    return <p>
        <label>
            <input className="with-gap" type="radio"
                   onChange={handler} value={value} name={name}/>
            <span>{value}</span>
        </label>
    </p>
}

export function getTextFieldElement(id, placeholder, icon, handler) {
    return <div className="ui left icon input">
        <textarea id={id} placeholder={placeholder} onChange={handler}></textarea>
        <i className={icon}></i>
    </div>
}