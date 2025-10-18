import "./index.css"
import { useState } from "react";

function SignUp(){
    const [submit, setSubmit] = useState("nema");
    setSubmit("asfdsdfg")
    const handleChangePassword = (e) => {
        if(e.target.value.length > 8){
            setSubmit(e.target.value);
        }
    }

    return(
        <div className="container">
            <h2>Sign up</h2>
            <input type = "text" placeholder="Enter username" /><br/>
            <input type = "password" placeholder="Enter password"/><br/>
           <input type = "password" placeholder="Enter password" onChange={handleChangePassword}/><br/>
            <p>Already have an account? <a href="../login
            ">Login</a></p>
            <button className="createButton" >Create account</button> 
        </div>
    );
}

export default SignUp;