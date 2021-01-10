import React from 'react';
import emailjs from 'emailjs-com';

export default function Contacts() {

    function sendEmail(e) {
        e.preventDefault();

        emailjs.sendForm('service_h2l1bmi', 'template_gt67wbs', e.target, 'user_hgGxYKQIpsUeFQxfvmYLk')
            .then((result) => {
                window.location.reload()  //This is if you still want the page to reload (since e.preventDefault() cancelled that behavior)
            }, (error) => {
                console.log(error.text);
            });
    }

    return (<div style={{marginTop:"50px",marginLeft:"27.5vw",marginRight:"27.5vw", height:"100%"}}>
            <div style={{textAlign:"center"}}>
            <h4>- Contact us - </h4>
            <h6> If you have a questions, enter the form and we will glad to contact you.</h6>
            </div>
        <form className="contact-form" onSubmit={sendEmail}>
            <input type="hidden" name="contact_number" />
            <label>Name</label>
            <input type="text" name="from_name" />
            <label>Email</label>
            <input type="email" name="from_email" />
            <label>Subject</label>
            <input type="text" name="subject" />
            <label>Message</label>
            <textarea name="message" />
            <input type="submit" value="Send" />
        </form>
        </div>
    );
}
