import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import FirstPage from './components/FirstPage';
import Login from './components/Login';
import SignUp from './components/SignUp';
import EquipmentByCompany from './components/EquipmentByCompany';

ReactDOM.render(
  <Router>
    <Routes>
      <Route path='/' element={<FirstPage/>}/>
      <Route path='/Login' element={<Login/>}/>
      <Route path='/SignUp' element={<SignUp/>}/>
      <Route path='/EquipmentByCompany' element={<EquipmentByCompany/>}/>
    </Routes>
  </Router>,
  document.getElementById('root')
)
