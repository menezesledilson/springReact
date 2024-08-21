package com.api.produtos.services;

import com.api.produtos.entities.produtoModel;
import com.api.produtos.entities.responseModel;
import com.api.produtos.repositories.produtoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class produtoService {
    @Autowired
    private produtoRepository pr;

    @Autowired
    private responseModel rm;

    //Método para lista todos os produtos
    public Iterable<produtoModel> listar(){
        return pr.findAll();
    }

    //Método para cadastrar produto
    public ResponseEntity<?> cadastrar(produtoModel pm){
        if(pm.getNome().equals("")){
            rm.setMessage("O nome do produto é obrigatório!");
            return new ResponseEntity<responseModel>(rm, HttpStatus.BAD_REQUEST);
        } else if(pm.getMarca().equals("")){
            rm.setMessage("O nome da marca é obrigatório!");
            return new ResponseEntity<responseModel>(rm, HttpStatus.BAD_REQUEST);
        }else{
            return  new ResponseEntity<produtoModel>(pr.save(pm),HttpStatus.CREATED);
        }
    }
}
