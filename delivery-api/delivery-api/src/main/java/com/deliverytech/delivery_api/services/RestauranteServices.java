package com.deliverytech.delivery_api.services;

import com.deliverytech.delivery_api.entity.Restaurante;
import com.deliverytech.delivery_api.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    public Restaurante cadastrar(Restaurante restaurante) {
        restaurante.setAtivo(true);
        return restauranteRepository.save(restaurante);
    }

    public List<Restaurante> listarTodos() {
        return restauranteRepository.findAll();
    }

    public Restaurante buscarPorId(Long id) {
        return restauranteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));
    }

    public Restaurante atualizar(Long id, Restaurante novo) {
        Restaurante restaurante = buscarPorId(id);
        restaurante.setNome(novo.getNome());
        restaurante.setCategoria(novo.getCategoria());
        restaurante.setEndereco(novo.getEndereco());
        restaurante.setTelefone(novo.getTelefone());
        restaurante.setTaxaEntrega(novo.getTaxaEntrega());
        restaurante.setAvaliacao(novo.getAvaliacao());
        return restauranteRepository.save(restaurante);
    }

    public void inativar(Long id) {
        Restaurante restaurante = buscarPorId(id);
        restaurante.setAtivo(false);
        restauranteRepository.save(restaurante);
    }

    public List<Restaurante> buscarPorCategoria(String categoria) {
        return restauranteRepository.findByCategoria(categoria);
    }

    public List<Restaurante> listarAtivosOrdenadosPorAvaliacao() {
        return restauranteRepository.findAllByOrderByAvaliacaoDesc();
    }
}
