import React, { useEffect, useState } from 'react';
import axios from 'axios';
import './App.css';
import Formulario from './Formulario';
import Tabela from './Tabela';

function App() {
  // Objeto produto
  const produto = {
    codigo: 0,
    nome: '',
    marca: ''
  };

  // UseState
  const [btnCadastrar, setBtnCadastrar] = useState(true);
  const [produtos, setProdutos] = useState([]);
  const [objProduto, setObjProduto] = useState(produto);

  // UseEffect
  useEffect(() => {
    axios.get('http://localhost:8080/listar')
      .then(response => {
        setProdutos(response.data);
      })
      .catch(error => console.error("Erro ao listar produtos:", error));
  }, []);

  // Obtendo os dados dos formulários
  const aoDigitar = (e) => {
    setObjProduto({ ...objProduto, [e.target.name]: e.target.value });
  };

  // Cadastrar produto
  const cadastrar = () => {
    axios.post('http://localhost:8080/cadastrar', objProduto, {
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      }
    })
      .then(response => {
        const retorno_convertido = response.data;
        if (retorno_convertido.mensagem !== undefined) {
          alert(retorno_convertido.mensagem);
        } else {
          setProdutos([...produtos, retorno_convertido]);
          alert('Produto cadastrado com sucesso!');
          limparFormulario();
        }
      })
      .catch(error => console.error("Erro ao cadastrar produto:", error));
  };

  // Alterar produto
  const alterar = () => {
    axios.put('http://localhost:8080/alterar', objProduto, {
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      }
    })
      .then(response => {
        const retorno_convertido = response.data;
        if (retorno_convertido.mensagem !== undefined) {
          alert(retorno_convertido.mensagem);
        } else {
          alert('Produto alterado com sucesso!');
          let vetorTemp = [...produtos];
          let indice = vetorTemp.findIndex((p) => p.codigo === objProduto.codigo);
          vetorTemp[indice] = objProduto;
          setProdutos(vetorTemp);
          limparFormulario();
        }
      })
      .catch(error => console.error("Erro ao alterar produto:", error));
  };

  // Remover produto
  const remover = () => {
    axios.delete(`http://localhost:8080/remover/${objProduto.codigo}`, {
      headers: {
        'Content-Type': 'application/json',
        'Accept': 'application/json'
      }
    })
      .then(response => {
        const retorno_convertido = response.data;
        alert(retorno_convertido.mensagem);
        let vetorTemp = [...produtos];
        let indice = vetorTemp.findIndex((p) => p.codigo === objProduto.codigo);
        vetorTemp.splice(indice, 1);
        setProdutos(vetorTemp);
        limparFormulario();
      })
      .catch(error => console.error("Erro ao remover produto:", error));
  };

  // Limpar formulário
  const limparFormulario = () => {
    setObjProduto(produto);
    setBtnCadastrar(true);
  };

  // Selecionar produto
  const selecionarProduto = (indice) => {
    setObjProduto(produtos[indice]);
    setBtnCadastrar(false);
  };

  return (
    <div>
      <Formulario 
        botao={btnCadastrar}
        eventoTeclado={aoDigitar}
        cadastrar={cadastrar}
        obj={objProduto}
        cancelar={limparFormulario}
        remover={remover}
        alterar={alterar}
      />
      <Tabela vetor={produtos} selecionar={selecionarProduto} />
    </div>
  );
}

export default App;
