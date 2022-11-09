import "./App.css";
import { Routes, Route } from "react-router-dom";
import React, { useEffect, useReducer, useRef } from "react";


import {
  PopupTest,
  MypageTest,
  MainTest,
  LoadingTest,
  NewPage,
  BoardHome
} from "./pages/index";

import Login from "./components/login/Login";
import OauthRedirect from "./components/login/OauthRedirect";


function App() {


  return (
    <>
      <Routes>
        <Route path='/popup/popupTest' element={<PopupTest />} />
        <Route path='/mypage/mypageTest' element={<MypageTest />} />
        <Route path='/main/mainTest' element={<MainTest />} />
        <Route path='/LoadingTest' element={<LoadingTest />} />
        <Route path='/' element={<Login />} />
        <Route path='/oauth/redirect' element={<OauthRedirect />} />
        <Route path='/new' element={<NewPage />} />
        <Route path='/home/board' element={<BoardHome />} />

      </Routes>

    </>
  );
}

export default App;
