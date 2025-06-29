package com.deliverytech.delivery_api.controler;

import com.deliverytech.delivery_api.entity.ItemPedido;
import com.deliverytech.delivery_api.entity.Pedido;
import com.deliverytech.delivery_api.services.PedidoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pedidos")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @PostMapping
    public Pedido criarPedido(
            @RequestParam Long clienteId,
            @RequestParam Long restauranteId,
            @RequestBody List<ItemPedido> itens,
            @RequestParam(required = false) String observacoes
    ) {
        return pedidoService.criarPedido(clienteId, restauranteId, itens, observacoes);
    }

    @GetMapping
    public List<Pedido> listarTodos() {
        return pedidoService.listarTodos();
    }

    @GetMapping("/{id}")
    public Pedido buscarPorId(@PathVariable Long id) {
        return pedidoService.buscarPorId(id);
    }

    @PutMapping("/{id}/status")
    public void atualizarStatus(@PathVariable Long id, @RequestParam String status) {
        pedidoService.atualizarStatus(id, status);
    }
}
