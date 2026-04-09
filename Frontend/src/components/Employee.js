import { useEffect, useState } from "react";

function Employee() {

  const [data, setData] = useState([]);
  const [empName, setEmpName] = useState('');
  const [email, setEmail] = useState('');

  // 🔹 Edit Mode
  const [editId, setEditId] = useState(null);

  // 🔹 GET
  useEffect(() => {
    fetch('http://localhost:8080/employees')
      .then(res => res.json())
      .then(data => setData(data));
  }, []);

  // 🔹 POST
  const handleSubmit = (e) => {
    e.preventDefault();

    fetch('http://localhost:8080/employees', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({
        empName,
        email,
        salary: 0,
        phone: "",
        status: "ACTIVE"
      })
    })
      .then(res => res.json())
      .then(newData => {
        setData(prev => [...prev, newData]);
        setEmpName('');
        setEmail('');
      });
  };

  // 🔹 DELETE
  const handleDelete = (id) => {
    fetch(`http://localhost:8080/employees/${id}`, {
      method: 'DELETE'
    })
      .then(() => {
        setData(prev => prev.filter(item => item.empId !== id));
      });
  };

  // 🔹 UPDATE (PUT)
  const handleUpdate = (emp) => {
    fetch(`http://localhost:8080/employees/${emp.empId}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(emp)
    })
      .then(res => res.json())
      .then(updated => {
        setData(prev =>
          prev.map(item =>
            item.empId === updated.empId ? updated : item
          )
        );
        setEditId(null);
      });
  };

  return (
    <div className="employee">
      <h2 className="section-title">Employees</h2>

      {/* 🔹 ADD FORM */}
      <form onSubmit={handleSubmit}>
        <input className="input-field"
          placeholder="Name"
          value={empName}
          onChange={(e) => setEmpName(e.target.value)}
        />

        <input className="input-field"
          placeholder="Email"
          value={email}
          onChange={(e) => setEmail(e.target.value)}
        />

        <button className="btn-primary" type="submit">Add</button>
      </form>

      {/* 🔹 LIST */}
      {
        data.map((e) => (
          <div key={e.empId} className="card">

            {
              editId === e.empId ? (
                <>
                  {/* EDIT MODE */}
                  <input className="input-field"
                    value={e.empName}
                    onChange={(event) => {
                      const updated = data.map(item =>
                        item.empId === e.empId
                          ? { ...item, empName: event.target.value }
                          : item
                      );
                      setData(updated);
                    }}
                  />

                  <input className="input-field"
                    value={e.email}
                    onChange={(event) => {
                      const updated = data.map(item =>
                        item.empId === e.empId
                          ? { ...item, email: event.target.value }
                          : item
                      );
                      setData(updated);
                    }}
                  />

                  <button onClick={() => handleUpdate(e)}>Save</button>
                  <button onClick={() => setEditId(null)}>Cancel</button>
                </>
              ) : (
                <>
                  {/* VIEW MODE */}
                  <h3>{e.empName}</h3>
                  <p>{e.email}</p>

                  <button onClick={() => setEditId(e.empId)}>Edit</button>
                  <button onClick={() => handleDelete(e.empId)}>Delete</button>
                </>
              )
            }

          </div>
        ))
      }

    </div>
  );
}

export default Employee;