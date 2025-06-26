import React, { useState } from "react";

import { Routes, Route } from "react-router-dom";
import AddFood from "./pages/AddFood/AddFood";
import ListFood from "./pages/ListFood/ListFood";
import Orders from "./pages/OrderFood/Orders";
import SideBar from "./components/SideBar/SideBar";
import MenuBar from "./components/MenuBar/MenuBar";

const App = () => {
  const [sidebarVisible, setSidebarVisible] = useState(true);

  const toggleSideBar = () => {
    setSidebarVisible(!sidebarVisible);
  };

  return (
    <div>
      <div className="d-flex" id="wrapper">
        <SideBar sidebarVisible={sidebarVisible} />
        <div id="page-content-wrapper">
          <MenuBar toggleSideBar={toggleSideBar} />
          <div className="container-fluid">
            <Routes>
              <Route path="/add" element={<AddFood />} />
              <Route path="/list" element={<ListFood />} />
              <Route path="/orders" element={<Orders />} />
              <Route path="/" element={<ListFood />} />
            </Routes>
          </div>
        </div>
      </div>
    </div>
  );
};

export default App;
