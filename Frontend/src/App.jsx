import React from "react";
import { Routes, Route } from "react-router-dom";
import SignUp from "./pages/signup/index.jsx";
import Login from "./pages/login/index.jsx";

function App() {
  return (
    <Routes>
      <Route path="/signup" element={<SignUp />} />
      <Route path="/login" element={<Login />} />
      {/* Optional: default route */}
      <Route path="*" element={<div>Page Not Found</div>} />
    </Routes>
  );
}

export default App;
