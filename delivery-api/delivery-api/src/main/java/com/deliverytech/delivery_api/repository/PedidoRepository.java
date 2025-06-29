package com.deliverytech.delivery_api.repository;

import com.deliverytech.delivery_api.entity.Pedido;
import com.deliverytech.delivery_api.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Long> {
    List<Pedido> findByCliente(Cliente cliente);
    List<Pedido> findByStatus(String status);
}
