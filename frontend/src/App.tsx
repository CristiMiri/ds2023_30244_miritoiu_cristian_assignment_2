import { useEffect, useState } from "react";
import { Route, Routes, useNavigate } from "react-router-dom";
import Navigation from "./Components/NavigationBar";
import { UserProvider } from "./Hooks/UserContext";

import { Home } from "./Pages/Home";
import { Login } from "./Pages/Login";

import DevicesPage from "./Pages/DevicesPage";
import Profile from "./Pages/UserPage/UserPage";
import Register from "./Pages/Register";
import UserPage from "./Pages/UsersPage";
import { User } from "./models/user";
import AdminPage from "./Pages/AdminPage/AdminPage";

import ProtectedRoute from "./Security/ProtectedRoute";
import TestingPage from "./Pages/Testing/TestingPage";
import PublicUserPage from "./Pages/PublicUserPage/PublicUserPage";

function App() {
  const navigate = useNavigate();
  const user = JSON.parse(localStorage.getItem("user") || "{}");
  return (
    <div className="App bg-info-subtle vh-100">
      <header className="App-header">
        {/* <Router> */}
        <UserProvider>
          <Navigation />
          <Routes>
            {user!.role === "admin" ? (
              <>
                <Route path="/admin" Component={AdminPage} />
                <Route path="/devices" Component={DevicesPage} />
              </>
            ) : (
              <>
                <Route path="/devices" Component={DevicesPage} />
                <Route path="/user" Component={Profile} />
              </>
            )}
            <Route path="/user" Component={Profile} />
            <Route path="/register" Component={Register} />
            <Route path="/testing" Component={TestingPage} />
            <Route path="/" Component={Home} />
            <Route path="/login" Component={Login} />
            <Route path="/profile/:id" Component={PublicUserPage} />
          </Routes>
        </UserProvider>
        {/* </Router> */}
      </header>
    </div>
  );
}

export default App;
