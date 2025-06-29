package com.deliverytech.delivery_api.controler;

import com.deliverytech.delivery_api.entity.Restaurante;
import com.deliverytech.delivery_api.services.RestauranteService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

    @Autowired
    private RestauranteService restauranteService;

    @PostMapping
    public Restaurante cadastrar(@RequestBody Restaurante restaurante) {
        return restauranteService.cadastrar(restaurante);
    }

    @GetMapping
    public List<Restaurante> listarTodos() {
        return restauranteService.listarTodos();
    }

    @GetMapping("/{id}")
    public Restaurante buscarPorId(@PathVariable Long id) {
        return restauranteService.buscarPorId(id);
    }

    @PutMapping("/{id}")
    public Restaurante atualizar(@PathVariable Long id, @RequestBody Restaurante restaurante) {
        return restauranteService.atualizar(id, restaurante);
    }

    @DeleteMapping("/{id}")
    public void inativar(@PathVariable Long id) {
        restauranteService.inativar(id);
    }

    @GetMapping("/categoria/{categoria}")
    public List<Restaurante> buscarPorCategoria(@PathVariable String categoria) {
        return restauranteService.buscarPorCategoria(categoria);
    }

    @GetMapping("/avaliacao")
    public List<Restaurante> ordenarPorAvaliacao() {
        return restauranteService.listarAtivosOrdenadosPorAvaliacao();
    }
}
