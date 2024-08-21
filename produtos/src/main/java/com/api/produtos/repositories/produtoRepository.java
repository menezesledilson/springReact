package com.api.produtos.repositories;

import com.api.produtos.entities.produtoModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface produtoRepository extends CrudRepository<produtoModel,Long> {
}
