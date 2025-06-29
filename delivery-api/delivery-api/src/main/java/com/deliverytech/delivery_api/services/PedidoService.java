package com.deliverytech.delivery_api.services;

import com.deliverytech.delivery_api.entity.*;
import com.deliverytech.delivery_api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private ItemPedidoRepository itemPedidoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private RestauranteServices restauranteRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    public Pedido criarPedido(Long clienteId, Long restauranteId, List<ItemPedido> itens, String observacoes) {
        Cliente cliente = clienteRepository.findById(clienteId)
                .orElseThrow(() -> new RuntimeException("Cliente não encontrado"));
        Restaurante restaurante = restauranteRepository.findById(restauranteId)
                .orElseThrow(() -> new RuntimeException("Restaurante não encontrado"));

        Pedido pedido = new Pedido();
        pedido.setNumeroPedido(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
        pedido.setCliente(cliente);
        pedido.setRestaurante(restaurante);
        pedido.setStatus("NOVO");
        pedido.setObservacoes(observacoes);

        BigDecimal total = BigDecimal.ZERO;

        for (ItemPedido item : itens) {
            Produto produto = produtoRepository.findById(item.getProduto().getId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
            item.setPedido(pedido);
            item.setPrecoUnitario(produto.getPreco());
            item.setSubtotal(produto.getPreco().multiply(BigDecimal.valueOf(item.getQuantidade())));
            total = total.add(item.getSubtotal());
        }

        pedido.setValorTotal(total);
        pedido.setItens(itens);

        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Pedido buscarPorId(Long id) {
        return pedidoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Pedido não encontrado"));
    }

    public void atualizarStatus(Long id, String novoStatus) {
        Pedido pedido = buscarPorId(id);
        pedido.setStatus(novoStatus);
        pedidoRepository.save(pedido);
    }
}
