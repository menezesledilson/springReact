package com.api.produtos.repositorio;

import com.api.produtos.modelo.ProdutoModelo;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepositorio extends CrudRepository<ProdutoModelo, Long> {

}