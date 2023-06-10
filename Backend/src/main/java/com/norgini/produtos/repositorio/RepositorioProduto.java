package com.norgini.produtos.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;

import com.norgini.produtos.modelo.ProdutoModelo;

public interface RepositorioProduto extends JpaRepository<ProdutoModelo, Long> {

}
