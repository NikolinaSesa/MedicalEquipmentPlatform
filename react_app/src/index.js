import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import FirstPage from './components/FirstPage';
import Login from './components/Login';
import SignUp from './components/SignUp';
import EquipmentByCompany from './components/EquipmentByCompany';
import UserFirstPage from './components/UserFirstPage';
import SysAdminFirstPage from './components/SysAdminFirstPage';
import CompanyAdminFirstPage from './components/CompanyAdminFirstPage';
import ReservationPage from './components/ReservationPage';
import MyReservations from './components/MyReservations';
import AppointmentDetailsPDF from './components/AppointmentDetailsPDF';

ReactDOM.render(
  <Router>
    <Routes>
      <Route path='/' element={<FirstPage/>}/>
      <Route path='/Login' element={<Login/>}/>
      <Route path='/SignUp' element={<SignUp/>}/>
      <Route path='/EquipmentByCompany' element={<EquipmentByCompany/>}/>
      <Route path='/UserFirstPage' element={<UserFirstPage/>}/>
      <Route path='/SysAdminFirstPage' element={<SysAdminFirstPage/>}/>
      <Route path='/CompanyAdminFirstPage' element={<CompanyAdminFirstPage/>}/>
      <Route path='/ReservationPage' element={<ReservationPage/>}/>
      <Route path='/MyReservations' element={<MyReservations/>}/>
      <Route path='/appointmentPDF' element={<AppointmentDetailsPDF/>}></Route>
    </Routes>
  </Router>,
  document.getElementById('root')
)
