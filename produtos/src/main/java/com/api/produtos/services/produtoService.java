package com.api.produtos.services;

import com.api.produtos.entities.produtoModel;
import com.api.produtos.entities.responseModel;
import com.api.produtos.repositories.produtoRepository;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

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

    //Método para validar o produto
    public ResponseEntity<?> validarProduto(produtoModel pm){
        if(pm.getNome().equals("")){
            rm.setMessage("O nome do produto é obrigatório!");
            return new ResponseEntity<responseModel>(rm, HttpStatus.BAD_REQUEST);
        } else if(pm.getMarca().equals("")){
            rm.setMessage("O nome da marca é obrigatório!");
            return new ResponseEntity<responseModel>(rm, HttpStatus.BAD_REQUEST);
        }
        return  null;
    }
    //Método para cadastrar produto
    public ResponseEntity<?>cadastrar(produtoModel pm){
        ResponseEntity<?> validacao = validarProduto(pm);
        if(validacao != null){
            //Retorna o erro de validação, se houver
            return validacao;
        }
        // Salva o produto e retorna o status de criação
        return new ResponseEntity<produtoModel>(pr.save(pm),HttpStatus.CREATED);
    }
    //Método para alterar produto
    public ResponseEntity<?> alterar(produtoModel pm){
        // Verificar se o produto existe no banco de dados antes de tentar atualizar
        if (pr.existsById(pm.getCodigo())) {
            ResponseEntity<?> validacao = validarProduto(pm);
            if(validacao != null){
                return validacao;
            }
            // Se o produto existir, faça a atualização
            return new ResponseEntity<produtoModel>(pr.save(pm), HttpStatus.OK);
        } else {
            // Caso o produto não exista, retorne um erro
            rm.setMessage("Produto não encontrado!");
            return new ResponseEntity<responseModel>(rm, HttpStatus.NOT_FOUND);
        }
    }

    //Método para remover produtos
    public ResponseEntity <responseModel> remover(long codigo){

        pr.deleteById(codigo);

        rm.setMessage("Produto removido com sucesso!");
        return  new ResponseEntity<responseModel>(rm,HttpStatus.OK);
    }
}
