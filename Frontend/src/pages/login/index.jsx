import "./index.css";
import { Link } from "react-router-dom";

function Login() {
  return (
    <div className="container">
      <h2>Log in</h2>
      <input type="text" placeholder="Enter username" /><br />
      <input type="password" placeholder="Enter password" /><br />
      <button className="createButton">Log in</button> 
      <p>Don't have an account? <Link to="../signup">Sign Up</Link></p>
    </div>
  );
}

export default Login;
