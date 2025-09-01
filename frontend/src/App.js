import React, { useState, useEffect } from 'react';

function App() {
  const [nome, setNome] = useState('');
  const [email, setEmail] = useState('');
  const [eta, setEta] = useState('');
  const [messaggio, setMessaggio] = useState('');

  // ⬇️ Nuovo stato per salvare gli ospiti ricevuti dal backend
  const [ospiti, setOspiti] = useState([]);

  // ⬇️ Carica la lista ospiti all’avvio
  useEffect(() => {
    fetch('http://localhost:8080/ospiti/lista')
      .then((res) => res.json())
      .then((data) => setOspiti(data))
      .catch((error) => console.error('Errore nel caricamento ospiti:', error));
  }, []);

  const handleSubmit = async (e) => {
    e.preventDefault();
    const ospite = { nome, email, eta };

    try {
      const response = await fetch('http://localhost:8080/ospiti/aggiungi', {
        method: 'POST',
        headers: { 'Content-Type': 'application/json' },
        body: JSON.stringify(ospite),
      });

      if (response.ok) {
        const data = await response.json();
        setMessaggio(`Ospite ${data.nome} aggiunto con successo!`);
        setNome('');
        setEmail('');
        setEta('');
        
        // ⬅️ Aggiorna la lista dopo l’aggiunta
        setOspiti((prev) => [...prev, data]);
      } else {
        setMessaggio('Errore durante l’inserimento.');
      }
    } catch (error) {
      console.error('Errore:', error);
      setMessaggio('Errore di connessione.');
    }
  };

  return (
    <div style={{ padding: '20px' }}>
      <h1>Prenota appuntamento:</h1>
      <form onSubmit={handleSubmit}>
        <div>
          <label>Nome:</label>
          <input type="text" value={nome} onChange={(e) => setNome(e.target.value)} required />
        </div>
        <div>
          <label>Email:</label>
          <input type="email" value={email} onChange={(e) => setEmail(e.target.value)} required />
        </div>
        <div>
          <label>Età:</label>
          <input type="number" value={eta} onChange={(e) => setEta(e.target.value)} required />
        </div>
        <button type="submit">Invia</button>
      </form>
      {messaggio && <p>{messaggio}</p>}

      <hr />

      <h2>Lista Ospiti</h2>
      <table border="1" cellPadding="8">
        <thead>
          <tr>
            <th>ID</th>
            <th>Nome</th>
            <th>Email</th>
            <th>Età</th>
          </tr>
        </thead>
        <tbody>
          {ospiti.map((ospite) => (
            <tr key={ospite.id}>
              <td>{ospite.id}</td>
              <td>{ospite.nome}</td>
              <td>{ospite.email}</td>
              <td>{ospite.eta}</td>
            </tr>
          ))}
        </tbody>
      </table>
    </div>
  );
}

export default App;
