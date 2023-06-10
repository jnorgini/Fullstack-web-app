import './App.css';
import Formulario from './Formulario';
import Tabela from './Tabela';
import React, { useEffect, useState } from "react";

function App() {

  // Objeto produto
  const produto = {
    id : 0,
    nome : '',
    marca : ''
  }

  // UseState
  const [btnCadastrar, setBtnCadastrar] = useState(true);
  const [produtos, setProdutos] = useState([]);
  const [objProduto, setObjProduto] = useState(produto);

  // UseEffect
  useEffect(()=>{
    fetch("https://web-service-msg6.onrender.com/produtos/listar")
    .then(retorno => retorno.json())
    .then(retorno_convertido => setProdutos(retorno_convertido))
  }, []);

  // Objendo os dados do formulário
  const aoDigitar = (e) => {
    setObjProduto({...objProduto, [e.target.name]:e.target.value});
  }

  // Cadastrar produto
  const cadastrar = () => {
    fetch('https://web-service-msg6.onrender.com/produtos/cadastrar', {
      method:'post',
      body:JSON.stringify(objProduto),
      headers:{
        'Content-type':'application/json',
        'Accept':'application/json'
      }
    })
    .then(retorno => retorno.json())
    .then(retorno_convertido => {
      
      if(retorno_convertido.mensagem !== undefined) {
        alert(retorno_convertido.mensagem);
      } else {
        setProdutos([...produtos, retorno_convertido]);
        alert('Produto cadastrado com sucesso!');
        limparFormulario();
      }

    })
  }

  // Alterar produto
  const alterar = () => {
    fetch('https://web-service-msg6.onrender.com/produtos/alterar', {
      method:'put',
      body:JSON.stringify(objProduto),
      headers:{
        'Content-type':'application/json',
        'Accept':'application/json'
      }
    })
    .then(retorno => retorno.json())
    .then(retorno_convertido => {
      
      if(retorno_convertido.mensagem !== undefined) {
        alert(retorno_convertido.mensagem);
      } else {
        // Mensagem
        alert('Produto alterado com sucesso!');

        // Cópia do vetor de produtos
        let vetorTemp = [...produtos];

      // Índice
      let indice = vetorTemp.findIndex((p) => {
        return p.id === objProduto.id;
      });

      // Alterar produto do vetorTemp
      vetorTemp[indice] = objProduto;

      // Atualizar o vetor de produtos
      setProdutos(vetorTemp);

      // Limpar formulário
      limparFormulario();
      }

    })
  }

   // Remover produto
   const remover = () => {
    fetch('https://web-service-msg6.onrender.com/produtos/remover/'+objProduto.id, {
      method:'delete',
      headers:{
        'Content-type':'application/json',
        'Accept':'application/json'
      }
    })
    .then(retorno => retorno.json())
    .then(retorno_convertido => {
      
     // Mensagem
     alert(retorno_convertido.mensagem);

    // Cópia do vetor de produtos
    let vetorTemp = [...produtos];

    // Índice
    let indice = vetorTemp.findIndex((p) => {
      return p.id === objProduto.id;
    });

    // Remover produto do vetorTemp
    vetorTemp.splice(indice, 1);

    // Atualizar o vetor de produtos
    setProdutos(vetorTemp);

    // Limpar formulário
    limparFormulario();

    })
  }

  // Limpar formulário
  const limparFormulario = () => {
    setObjProduto(produto);
    setBtnCadastrar(true);
  }

  // Selecionar produto
  const selecionarProduto = (indice) => {
    setObjProduto(produtos[indice]);
    setBtnCadastrar(false);
  }

  // Retorno
  return (
    <div>

      <Formulario botao={btnCadastrar} eventoTeclado={aoDigitar} cadastrar={cadastrar} obj={objProduto} cancelar={limparFormulario} 
      remover={remover} alterar={alterar} />
      <Tabela vetor={produtos} selecionar={selecionarProduto} />

    </div>
  );
}

export default App;
