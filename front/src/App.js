import './App.css';
import axios from 'axios';

import React, { useEffect, useState } from 'react';

import Formulario from './Formulario';
import Tabela from './tabela';

function App() {

  //UseState

  const[btnCadastrar, setBtnCadastrar] = useState(true);
  const[produtos, setProdutos] = useState([]);

  //UseEffect
  useEffect(()=>{
 axios.get("http://localhost:8080/listar")
 .then(response=> setProdutos(response.data))
  .catch( error => console.error("Erro ao buscar os dados",error));
   },[]);

  return (
    <div>
      <Formulario botao={btnCadastrar}/>
      <Tabela vetor={produtos}/>
    </div>
  );
}

export default App;
