import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import FirstPage from './components/FirstPage';
import Login from './components/Login';
import SignUp from './components/SignUp';

ReactDOM.render(
  <Router>
    <Routes>
      <Route path='/' element={<FirstPage/>}/>
      <Route path='/Login' element={<Login/>}/>
      <Route path='/SignUp' element={<SignUp/>}/>
    </Routes>
  </Router>,
  document.getElementById('root')
)
