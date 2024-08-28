package com.api.produtos.controllers;

import com.api.produtos.entities.produtoModel;
import com.api.produtos.entities.responseModel;
import com.api.produtos.services.produtoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class produtoController {

    @Autowired
    private produtoService ps;

    @PostMapping("/cadastrar")
    public ResponseEntity<?> cadastrar(@RequestBody produtoModel pm){
        return  ps.alterar(pm);
    }

    @PutMapping("/alterar")
    public ResponseEntity<?> alterar(@RequestBody produtoModel pm){
        return  ps.alterar(pm);
    }

    @GetMapping("listar")
    public Iterable<produtoModel> listar(){
        return  ps.listar();
    }

    @GetMapping("/")
    public  String rota(){
        return "Api funcionando";
    }

    @DeleteMapping("/remover/{codigo}")
    public ResponseEntity<responseModel>remover(@PathVariable long codigo){
        return ps.remover(codigo);
    }
}
