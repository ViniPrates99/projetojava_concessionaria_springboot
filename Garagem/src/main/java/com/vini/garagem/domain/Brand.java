package com.vini.garagem.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity // A classe vai virar uma TABELA no banco
public class Brand {

    @Id // Identificador único (chave primária)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank // Validação: nome não pode ser vazio
    @Column(unique = true)
    private String nome;

    private String paisOrigem;

    public Brand() {}

    public Brand(String nome, String paisOrigem) {
        this.nome = nome;
        this.paisOrigem = paisOrigem;
    }

    //Getters e Setters — acesso controlado aos atributos (encapsulamento)
    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPaisOrigem() {
        return paisOrigem;
    }

    public void setPaisOrigem(String paisOrigem) {
        this.paisOrigem = paisOrigem;
    }
}
