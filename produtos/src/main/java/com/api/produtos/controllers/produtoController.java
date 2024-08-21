package com.api.produtos.controllers;

import com.api.produtos.entities.produtoModel;
import com.api.produtos.services.produtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class produtoController {

    @Autowired
    private produtoService ps;

    @GetMapping("listar")
    public Iterable<produtoModel> listar(){
        return  ps.listar();
    }

    @GetMapping("/")
    public  String rota(){
        return "Api funcionando";
    }
}
