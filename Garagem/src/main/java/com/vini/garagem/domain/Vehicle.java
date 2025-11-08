package com.vini.garagem.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

@Entity //vira uma tabela no banco
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //Relações com outras entidades
    @ManyToOne(optional = false)
    private Brand brand;

    @ManyToOne(optional = false)
    private Model model;

    //Atributos dos veículos
    @Min(1900)
    private int ano;

    private String cor;

    @Positive
    private double preco;

    @NotNull
    @Min(0)
    private Double quilometragem;

    //Enum para status
    @Enumerated(EnumType.STRING)
    private VehicleStatus status = VehicleStatus.DISPONIVEL;

    public Vehicle() {}

    //Construtor
    public Vehicle(Brand brand, Model model, int ano, String cor, double preco, Double km, VehicleStatus status) {
        this.brand = brand;
        this.model = model;
        this.ano = ano;
        this.cor = cor;
        this.preco = preco;
        this.quilometragem = km;
        this.status = status;
    }

    //Getters e Setters (encapsulamento)
    public Long getId() { return id; }

    public Brand getBrand() { return brand; }
    public void setBrand(Brand brand) { this.brand = brand; }

    public Model getModel() { return model; }
    public void setModel(Model model) { this.model = model; }

    public int getAno() { return ano; }
    public void setAno(int ano) { this.ano = ano; }

    public String getCor() { return cor; }
    public void setCor(String cor) { this.cor = cor; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    public Double getQuilometragem() { return quilometragem; }
    public void setQuilometragem(Double quilometragem) { this.quilometragem = quilometragem; }

    public VehicleStatus getStatus() { return status; }
    public void setStatus(VehicleStatus status) { this.status = status; }
}
