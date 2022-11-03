import "./App.css";
import { Routes, Route } from "react-router-dom";
import React from "react";

import { PopupTest, MypageTest, MainTest, LoadingTest } from "./pages/index";

function App() {
  return (
    <>
      <Routes>
        <Route path='/popup/popupTest' element={<PopupTest />} />
        <Route path='/mypage/mypageTest' element={<MypageTest />} />
        <Route path='/main/mainTest' element={<MainTest />} />
        <Route path='/LoadingTest' element={<LoadingTest />} />
      </Routes>
    </>
  );
}

export default App;
