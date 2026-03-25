package com.example.crud.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false,unique = true)
    private String nome;
    private String descricao;
    private double preco;
    private int quantidade;
    private String imagem;

}

