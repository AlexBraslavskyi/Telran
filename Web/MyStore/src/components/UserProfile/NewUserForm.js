import React, {useEffect, useState} from 'react';
import {nameMinLength} from "../../config/ShopConfig";
import {getInputElement, getInputElementActive} from "../../utils/inputElements";
import _ from "lodash";
import {useSelector} from "react-redux";



const letters = /^[a-zA-Z]+[\-'\s]?[a-zA-Z ]+$/;

export const NewUserForm = (props) =>{
    const users = useSelector(state=>state.clients);
    let user, setUser, error, setError, emailAddress, setEmailAddress,
        name, setName, address, setAddress, phone, setPhone, passport, setPassport,  formRef, setFormRef, isDisable, setIsDisable;
    [user,setUser]=useState({
        emailAddress: "",
        name: "",
        address: "",
        passport: "",
        phone: "",
    });
    [error,setError]=useState({errorPassport:'',errorName:'',errorEmail:'',errorPhone:''});
    [emailAddress,setEmailAddress]=useState({value:"",controlError: 0});
    [name,setName]=useState({value:"",controlError: 0});
    [address,setAddress]=useState({value:"",controlError: 0});
    [phone,setPhone]=useState({value:"",controlError: 0});
    [passport,setPassport]=useState({value:"",controlError: 0});
    [formRef,setFormRef]=useState(null);
    [isDisable,setIsDisable]=useState(true);



    function addUser(user) {
        const index = _.findIndex(users,u => u.emailAddress === user.emailAddress);
        if (index >= 0) {
            return false;
        }
        props.clientsService.addUser(user)
            .then(() => {
                    alert(`User with email ${user.emailAddress} added successfully`)
                }
                , error => {
                    alert(`User with email ${user.emailAddress} already exists`)
                })
        return true;
    }
    function submit(event) {
        event.preventDefault();
        if (!addUser(user)) {
            error.errorEmail = `User with email ${user.emailAddress} already exists`;
            emailAddress={value:user.emailAddress}
            error=error.errorEmail;
        }
        formRef.reset();
        setFormRef(null);
        setError({...error});
        formRef.reset();
        setFormRef(null);
        setError({...error});
    }

    useEffect(()=> {
        setUser(user);
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
        <h3 className="center" style={{marginTop:"5vh"}}>Fill the form to register new user</h3>
            <form className="col s12" id="formValidate" ref={(ref) => formRef = ref} novalidate="novalidate" onSubmit={submit}>
              <div className="form-profile">
                {getInputElement('text',
                    'name', 'Name',
                    handlerName,error.errorName,"",'account_circle')}
                {getInputElement('email',
                    'emailAddress', 'Email',
                    handlerEmail,error.errorEmail,"",'email')}
              </div>
                <div className="form-profile">
                {getInputElement('text',
                    'phone', 'Phone number',
                    handlerPhone,error.errorPhone,"",'phone')}

                {getInputElement('text',
                    'address', 'Address',
                    handlerNonValidated,"","",'home')}
                {getInputElement('text',
                    'passport', 'Passport',
                    handlerNonValidated,"","",'local_library')}

                </div>
                <div  className="form-profile">
                    {getInputElement('text',
                        'password', 'Password',
                        handlerNonValidated,"","",'vpn_key')}
                </div>
                {/*<div  className="form-profile">*/}
                {/*    {getInputElement('text',*/}
                {/*        'confirm_password', 'Confirm password',*/}
                {/*        handlerNonValidated,"","",'vpn_key')}*/}
                {/*</div>*/}
                <div className="form-profile">
                    <button type="submit" name="action" className="btn waves-effect waves-light"
                            disabled={!validate()}
                    > <i className="material-icons right">send</i>Submit
                    </button>
                    </div>
            </form>
        </div>
    </div>
}
