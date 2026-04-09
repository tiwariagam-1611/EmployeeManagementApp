import './App.css'
import './styles/main.scss';

import { useState } from 'react';

import Department from './components/Department';
import Location from './components/Location';
import Employee from './components/Employee';

function App() {

  const [page, setPage] = useState('department');

  return (
    <div>
      <div className="glow"></div>

      {/* 🔷 NAVBAR */}
      <div className="navbar">
        <button onClick={() => setPage('department')}>Department</button>
        <button onClick={() => setPage('location')}>Location</button>
        <button onClick={() => setPage('employee')}>Employee</button>
      </div>

      {/* 🔷 PAGE SWITCHING */}
      <div className="page">
        {page === 'department' && <Department />}
        {page === 'location' && <Location />}
        {page === 'employee' && <Employee />}
      </div>

    </div>
  )
}

export default App;