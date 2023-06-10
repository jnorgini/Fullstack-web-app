package com.norgini.produtos.servicos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.norgini.produtos.modelo.ProdutoModelo;
import com.norgini.produtos.modelo.RespostaModelo;
import com.norgini.produtos.repositorio.RepositorioProduto;

@Service
public class produtoServico {
	
	@Autowired
	private RepositorioProduto repositorio;
	
	@Autowired
	private RespostaModelo rm;
	
	// metodo para listar todos os produtos
	public Iterable<ProdutoModelo> listar() {
		return repositorio.findAll();
	}
	
	// metodo para cadastrar ou alterar produtos
	public ResponseEntity<?> cadastrarAlterar(ProdutoModelo pm, String acao) {
		
		if(pm.getNome().equals("")) {
			rm.setMensagem("O nome do produto é obrigatório!");
			return new ResponseEntity<RespostaModelo> (rm, HttpStatus.BAD_REQUEST);
		} else if(pm.getMarca().equals("")) {
			rm.setMensagem("O nome da marca é obrigatório!");
			return new ResponseEntity<RespostaModelo> (rm, HttpStatus.BAD_REQUEST);
		} else if(acao.equals("cadastrar"))  {
			return new ResponseEntity<ProdutoModelo> (repositorio.save(pm), HttpStatus.CREATED);
		} else {
			return new ResponseEntity<ProdutoModelo> (repositorio.save(pm), HttpStatus.OK);
		}
	}
	
	// metodo para remover produtos
	public ResponseEntity<RespostaModelo> remover(Long id) {
		repositorio.deleteById(id);
		rm.setMensagem("O produto foi removido com sucesso!");
		return new ResponseEntity<RespostaModelo> (rm, HttpStatus.OK);
	}

}
