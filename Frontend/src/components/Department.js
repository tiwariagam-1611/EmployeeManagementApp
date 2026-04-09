import { useEffect, useState } from "react";

function Department() {

  const [data, setData] = useState([]);
  const [deptName, setDeptName] = useState('');
  const [budget, setBudget] = useState('');

  // 🔹 For Edit Mode
  const [editId, setEditId] = useState(null);

  // 🔹 GET
  useEffect(() => {
    fetch('http://localhost:8080/departments/get')
      .then(res => res.json())
      .then(data => setData(data));
  }, []);

  // 🔹 POST
  const handleSubmit = (e) => {
    e.preventDefault();

    fetch('http://localhost:8080/departments', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        deptName,
        budget: parseFloat(budget)
      })
    })
      .then(res => res.json())
      .then(newData => {
        setData(prev => [...prev, newData]);
        setDeptName('');
        setBudget('');
      });
  };

  // 🔹 DELETE
  const handleDelete = (id) => {
    fetch(`http://localhost:8080/departments/${id}`, {
      method: 'DELETE'
    })
      .then(() => {
        setData(prev => prev.filter(item => item.deptId !== id));
      });
  };

  // 🔹 UPDATE (PUT)
  const handleUpdate = (dept) => {
    fetch(`http://localhost:8080/departments/${dept.deptId}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        ...dept,
        budget: parseFloat(dept.budget)
      })
    })
      .then(res => res.json())
      .then(updated => {
        setData(prev =>
          prev.map(item =>
            item.deptId === updated.deptId ? updated : item
          )
        );
        setEditId(null);
      });
  };

  return (
    <div className="department">
      <h2 className="section-title">Departments</h2>

      {/* 🔹 ADD FORM */}
      <form onSubmit={handleSubmit}>
        <input className="input-field"
          placeholder="Dept Name"
          value={deptName}
          onChange={(e) => setDeptName(e.target.value)}
        />

        <input className="input-field"
          placeholder="Budget"
          type="number"
          value={budget}
          onChange={(e) => setBudget(e.target.value)}
        />

        <button className="btn-primary" type="submit">Add</button>
      </form>

      {/* 🔹 LIST */}
      {
        data.map((d) => (
          <div key={d.deptId} className="card">

            {
              editId === d.deptId ? (
                <>
                  {/* EDIT MODE */}
                  <input className="input-field"
                    value={d.deptName}
                    onChange={(e) => {
                      const updated = data.map(item =>
                        item.deptId === d.deptId
                          ? { ...item, deptName: e.target.value }
                          : item
                      );
                      setData(updated);
                    }}
                  />

                  <input className="input-field"
                    type="number"
                    value={d.budget}
                    onChange={(e) => {
                      const updated = data.map(item =>
                        item.deptId === d.deptId
                          ? { ...item, budget: e.target.value }
                          : item
                      );
                      setData(updated);
                    }}
                  />

                  <button className="btn-secondary" onClick={() => handleUpdate(d)}>Save</button>
                  <button className="btn-secondary" onClick={() => setEditId(null)}>Cancel</button>
                </>
              ) : (
                <>
                  {/* VIEW MODE */}
                  <h3>{d.deptName}</h3>
                  <p>{d.budget}</p>

                  <button className="btn-secondary" onClick={() => setEditId(d.deptId)}>Edit</button>
                  <button className="btn-secondary" onClick={() => handleDelete(d.deptId)}>Delete</button>
                </>
              )
            }

          </div>
        ))
      }

    </div>
  );
}

export default Department;