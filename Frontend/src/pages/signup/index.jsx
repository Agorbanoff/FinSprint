import "./index.css";
import React, { useState } from "react";
import { Link } from "react-router-dom";

function SignUp() {
    const [password, setPassword] = useState("");

    const handleChangePassword = (e) => {
        if (e.target.value.length > 8) {
            setPassword(e.target.value);
        }
    };

    return (
        <div className="container">
            <h2>Sign up</h2>
            <input type="text" placeholder="Enter username" /><br />
            <input type="password" placeholder="Enter password" /><br />
            <input
                type="password"
                placeholder="Confirm password"
                onChange={handleChangePassword}
            /><br />
            <p>
                Already have an account? <Link to="/login">Login</Link>
            </p>
            <button className="createButton">Create account</button>
        </div>
    );
}

export default SignUp;
