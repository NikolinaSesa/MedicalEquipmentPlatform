import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import { BrowserRouter as Router, Routes, Route } from 'react-router-dom';
import FirstPage from './components/FirstPage';

ReactDOM.render(
  <Router>
    <Routes>
      <Route path='/' element={<FirstPage/>}/>
    </Routes>
  </Router>,
  document.getElementById('root')
)
