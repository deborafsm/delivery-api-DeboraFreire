package com.deliverytech.delivery.entity;

import jakarta.persistence.*;

@Entity
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private double preco;

    private boolean disponivel;

    @ManyToOne
    private Restaurante restaurante;

    // getters e setters
}