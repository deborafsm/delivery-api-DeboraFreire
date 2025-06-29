package com.deliverytech.delivery_api.controler;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.deliverytech.delivery_api.entity.Produto;
import com.deliverytech.delivery_api.services.ProdutoService;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService produtoService;

    @PostMapping("/{restauranteId}")
    public Produto cadastrar(@RequestBody Produto produto, @PathVariable Long restauranteId) {
        return produtoService.cadastrar(produto, restauranteId);
    }

    @GetMapping
    public List<Produto> listarTodos() {
        return produtoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable Long id) {
        return produtoService.buscarPorId(id);
    }

    @GetMapping("/categoria/{categoria}")
    public List<Produto> listarPorCategoria(@PathVariable String categoria) {
        return produtoService.listarPorCategoria(categoria);
    }

    @GetMapping("/disponiveis")
    public List<Produto> listarDisponiveis() {
        return produtoService.listarDisponiveis();
    }

    // @GetMapping("/restaurante/{restauranteId}")
    // public List<Produto> findByRestaurante(@PathVariable Long restauranteId) {
    //     return produtoService.findByRestaurante(restauranteId);
    // }

    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        return produtoService.atualizar(id, produto);
    }
}
