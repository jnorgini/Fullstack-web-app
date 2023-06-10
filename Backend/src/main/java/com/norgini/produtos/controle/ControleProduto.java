package com.norgini.produtos.controle;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.norgini.produtos.modelo.ProdutoModelo;
import com.norgini.produtos.modelo.RespostaModelo;
import com.norgini.produtos.servicos.produtoServico;

@RestController
@RequestMapping("/produtos")
@CrossOrigin(origins = "*")
public class ControleProduto {
	
	@Autowired
	private produtoServico servico;
	
	@GetMapping("/listar")
	public Iterable<ProdutoModelo> listar() {
		return servico.listar();
	}
	
	@PostMapping("/cadastrar")
	public ResponseEntity<?> cadastrar(@RequestBody ProdutoModelo pm) {
		return servico.cadastrarAlterar(pm, "cadastrar");
	}
	
	@PutMapping("/alterar")
	public ResponseEntity<?> alterar(@RequestBody ProdutoModelo pm) {
		return servico.cadastrarAlterar(pm, "alterar");
	}
	
	@DeleteMapping("/remover/{id}")
	public ResponseEntity<RespostaModelo> remover(@PathVariable Long id) {
		return servico.remover(id);
	}
	
	@GetMapping("/")
	public String rota() {
		return "API de produtos funcionando!";
	}

}
