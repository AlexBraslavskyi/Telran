import React, {useEffect, useState} from 'react';
import {nameMinLength} from "../../config/ShopConfig";
import {getInputElement, getInputElementActive} from "../../utils/inputElements";
import _ from "lodash";
import {useSelector} from "react-redux";



const letters = /^[a-zA-Z]+[\-'\s]?[a-zA-Z ]+$/;

export const ProfileForm = (props) =>{
    const users = useSelector(state=>state.clients);
    const user = props.user;
    let profileInf, setProfileInf, orderNumber, error, setError, emailAddress, setEmailAddress,
        name, setName, address, setAddress, phone, setPhone, passport, setPassport,  formRef, setFormRef, isDisable, setIsDisable;
    [profileInf,setProfileInf]=useState({
        emailAddress: user.emailAddress,
        name: user.name,
        address: user.address,
        passport: user.passport,
        phone: user.phone,
    });
    [error,setError]=useState({errorPassport:'',errorName:'',errorEmail:'',errorPhone:''});
    [emailAddress,setEmailAddress]=useState({value:user.emailAddress,controlError: 0});
    [name,setName]=useState({value:user.name,controlError: 0});
    [address,setAddress]=useState({value:user.address,controlError: 0});
    [phone,setPhone]=useState({value:user.phone,controlError: 0});
    [passport,setPassport]=useState({value:user.passport,controlError: 0});
    [formRef,setFormRef]=useState(null);
    [isDisable,setIsDisable]=useState(true);



    function updateUser(user){
        props.clientsService.updateUser(user.emailAddress, user)
            .then(() => {
            })
    }
    function submit(event) {
        event.preventDefault();
        let oldUser = user;
        if(isDisable) {
            setIsDisable(false);
            return;
        }
        updateUser(user);
        setIsDisable(true)
        formRef.reset();
        setFormRef(null);
        setError({...error});
    }

    useEffect(()=> {
        setProfileInf(user);
        setPhone(phone);
        setName(name);
        setEmailAddress(emailAddress);
        setAddress(address);
        setPassport(passport);
        setIsDisable(isDisable);
    },[error])


    function  handlerName(event) {
        name = event.target.value;
        error={errorName:''}
        if(name ==""){
            name={value:name,controlError: 0}
            user.name = name.value;

        }else if (name.length < nameMinLength) {
            error={errorName: 'Minimal name length should be '
                    + nameMinLength+' symbols'}
            name={value:name,controlError: -1}
            user.name = name.value;

        }  else if(!name.match(letters)) {
            error={errorName:'Name mast content only english letters'}
            name={value:name,controlError: -1}
            user.name = name.value;

        }
        else {
            error={errorName:''}
            name={value:name,controlError: 1}
            user.name = name.value;

        }
        setError({...error})
    }

    function handlerNonValidated(event) {
        user[event.target.name] = event.target.value;
        setError({...error})
    }
    function  handlerEmail(event) {
        emailAddress = event.target.value;
        error={errorEmail:''}
        const format = /^\w+([.-]?\w+)*@\w+([.-]?\w+)*(\.\w{2,3})+$/;

        if (emailAddress == "") {
            emailAddress={value:emailAddress,controlError: 0}
            user.emailAddress = emailAddress;
        } else if (!format.test(emailAddress)) {
            error={errorEmail:'Wrong email format'}
            emailAddress={value:emailAddress,controlError: -1}
            user.emailAddress = emailAddress;
        } else {
            error={errorEmail:''}
            user.emailAddress = emailAddress;
            emailAddress={value:emailAddress,controlError: 1}
        }
        setError({...error})
    }
    function  handlerPhone(event) {
        phone = event.target.value;
        error={errorPhone:''}
        const format = /^\+?(972|0)(\-)?0?(([23489]{1}\d{7})|[5]{1}\d{8})$/;

        if (phone == "") {
            phone={value:phone,controlError: 0}
            user.phone = phone;
        } else if (!format.test(phone)) {
            error={errorPhone:'Wrong phone format'}
            phone={value:phone,controlError: -1}
            user.phone = phone;
        } else {
            error={errorPhone:''}
            user.phone = phone;
            phone={value:phone,controlError: 1}
        }

        setError({...error})
    }

    function validate() {
        const res =  notErrors()
            && allFields();
        return res;
    }


    function notErrors() {
        return Object.values(error)
            .reduce((res, field) => {
                return res && !field
            } , true)
    }

    function allFields() {
        return Object.values(user)
            .reduce((res, field) => {
                return res && field
            } , true)
    }


    return  <div className="content">

        <div className="row">

            <form className="col s12" id="formValidate" ref={(ref) => formRef = ref} novalidate="novalidate" onSubmit={submit}>
              <div className="form-profile">
                {getInputElementActive('text',
                    'name', 'Name',
                    handlerName,error.errorName,user.name,'account_circle',isDisable)}
                {getInputElementActive('email',
                    'emailAddress', 'Email',
                    handlerEmail,error.errorEmail,user.emailAddress,'email',true)}
              </div>
                <div className="form-profile">
                {getInputElementActive('text',
                    'phone', 'Phone number',
                    handlerPhone,error.errorPhone,user.phone,'phone',isDisable)}

                {getInputElementActive('text',
                    'address', 'Address',
                    handlerNonValidated,"",user.address,'home',isDisable)}
                {getInputElementActive('text',
                    'passport', 'Passport',
                    handlerNonValidated,"",user.passport,'local_library',isDisable)}
                </div>
                <div className="form-profile">
                <button type="submit" name="action" className="btn waves-effect waves-light"
                        disabled={!validate()}
                > <i className="material-icons right"> {isDisable?"edit":"save"}</i>{isDisable?"Edit":"Save"}
                </button>
                    </div>
            </form>
        </div>
    </div>
}
