import React from "react";
import { Routes, Route } from "react-router-dom";
import Login from "./pages/login/index";
import SignUp from "./pages/signup/index";

function App() {
    return (
        <Routes>
            <Route path="/login" element={<Login />} />
            <Route path="/signup" element={<SignUp />} />
            <Route path="*" element={<div>Page Not Found</div>} />
        </Routes>
    );
}

export default App;
