import { useEffect, useState } from "react";

function Location() {

  const [data, setData] = useState([]);
  const [city, setCity] = useState('');
  const [state, setState] = useState('');
  const [country, setCountry] = useState('');

  // 🔹 Edit Mode
  const [editId, setEditId] = useState(null);

  // 🔹 GET
  useEffect(() => {
    fetch('http://localhost:8080/locations')
      .then(res => res.json())
      .then(data => setData(data));
  }, []);

  // 🔹 POST
  const handleSubmit = (e) => {
    e.preventDefault();

    fetch('http://localhost:8080/locations', {
      method: 'POST',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify({ city, state, country })
    })
      .then(res => res.json())
      .then(newData => {
        setData(prev => [...prev, newData]);
        setCity('');
        setState('');
        setCountry('');
      });
  };

  // 🔹 DELETE
  const handleDelete = (id) => {
    fetch(`http://localhost:8080/locations/${id}`, {
      method: 'DELETE'
    })
      .then(() => {
        setData(prev => prev.filter(item => item.locationId !== id));
      });
  };

  // 🔹 UPDATE (PUT)
  const handleUpdate = (loc) => {
    fetch(`http://localhost:8080/locations/${loc.locationId}`, {
      method: 'PUT',
      headers: { 'Content-Type': 'application/json' },
      body: JSON.stringify(loc)
    })
      .then(res => res.json())
      .then(updated => {
        setData(prev =>
          prev.map(item =>
            item.locationId === updated.locationId ? updated : item
          )
        );
        setEditId(null);
      });
  };

  return (
    <div className="location">
      <h2 className="section-title">Locations</h2>

      {/* 🔹 ADD FORM */}
      <form onSubmit={handleSubmit}>
        <input className="input-field"
          placeholder="City"
          value={city}
          onChange={(e) => setCity(e.target.value)}
        />

        <input className="input-field"
          placeholder="State"
          value={state}
          onChange={(e) => setState(e.target.value)}
        />

        <input className="input-field"
          placeholder="Country"
          value={country}
          onChange={(e) => setCountry(e.target.value)}
        />

        <button className="btn-primary" type="submit">Add</button>
      </form>

      {/* 🔹 LIST */}
      {
        data.map((l) => (
          <div key={l.locationId} className="card">

            {
              editId === l.locationId ? (
                <>
                  {/* EDIT MODE */}
                  <input className="input-field"
                    value={l.city}
                    onChange={(e) => {
                      const updated = data.map(item =>
                        item.locationId === l.locationId
                          ? { ...item, city: e.target.value }
                          : item
                      );
                      setData(updated);
                    }}
                  />

                  <input className="input-field"
                    value={l.state}
                    onChange={(e) => {
                      const updated = data.map(item =>
                        item.locationId === l.locationId
                          ? { ...item, state: e.target.value }
                          : item
                      );
                      setData(updated);
                    }}
                  />

                  <input className="input-field"
                    value={l.country}
                    onChange={(e) => {
                      const updated = data.map(item =>
                        item.locationId === l.locationId
                          ? { ...item, country: e.target.value }
                          : item
                      );
                      setData(updated);
                    }}
                  />

                  <button onClick={() => handleUpdate(l)}>Save</button>
                  <button onClick={() => setEditId(null)}>Cancel</button>
                </>
              ) : (
                <>
                  {/* VIEW MODE */}
                  <h3>{l.city}</h3>
                  <p>{l.state}, {l.country}</p>

                  <button onClick={() => setEditId(l.locationId)}>Edit</button>
                  <button onClick={() => handleDelete(l.locationId)}>Delete</button>
                </>
              )
            }

          </div>
        ))
      }

    </div>
  );
}

export default Location;