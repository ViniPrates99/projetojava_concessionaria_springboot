package com.vini.garagem.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity // Vai virar uma tabela no banco
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank // O nome do modelo não pode ser vazio
    private String nome;

    private String categoria; // ex: sedan, hatch, suv...

    // 🔹 Relação: muitos modelos pertencem a UMA marca
    @ManyToOne(optional = false)
    private Brand brand;

    // 🔹 Construtor padrão (obrigatório para o JPA)
    public Model() {}

    // 🔹 Construtor com parâmetros
    public Model(String nome, String categoria, Brand brand) {
        this.nome = nome;
        this.categoria = categoria;
        this.brand = brand;
    }

    // 🔹 Getters e Setters
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
