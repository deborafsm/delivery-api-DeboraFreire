package com.deliverytech.delivery.service;

import com.deliverytech.delivery.entity.Cliente;
import com.deliverytech.delivery.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public Cliente cadastrar(Cliente cliente) {
        if (clienteRepository.findByEmail(cliente.getEmail()).isPresent()) {
            throw new IllegalArgumentException("E-mail já cadastrado");
        }
        cliente.setAtivo(true);
        return clienteRepository.save(cliente);
    }

    public List<Cliente> listarAtivos() {
        return clienteRepository.findByAtivoTrue();
    }

    public Cliente buscarPorId(Long id) {
        return clienteRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente não encontrado"));
    }

    public Cliente atualizar(Long id, Cliente dados) {
        Cliente cliente = buscarPorId(id);
        cliente.setNome(dados.getNome());
        // atualizar outros campos se necessário
        return clienteRepository.save(cliente);
    }

    public void inativar(Long id) {
        Cliente cliente = buscarPorId(id);
        cliente.setAtivo(false);
        clienteRepository.save(cliente);
    }
}