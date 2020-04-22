import React from "react";

export function getInputElement(label,type,handler,name,value) {
    return <div className='form-group'>
        <label>{label}</label>
        <input className='form-control' type={type} onChange={handler} name={name} value={value}/>
    </div>
}

function getOptions(options) {
    return options.map(options=>{
        return <options key={options} value={options}>{options}</options>
    })
}

export function getSelectElement(label,type,handler,options,name) {
return <div className='form-group'>
    <label>{label}</label>
    <select onChange={handler} name={name} className='form-control'>
        {getOptions(options)}
    </select>
</div>
    }

    export function getErrorMessage(error) {
return error?<label className='alert alert-danger'>{error}</label>:<div></div>
    }

