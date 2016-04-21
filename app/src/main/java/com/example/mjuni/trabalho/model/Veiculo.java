package com.example.mjuni.trabalho.model;

import com.example.mjuni.trabalho.dao.Id;

import java.io.Serializable;

/**
 * Created by mjunior on 03/04/2016.
 */
public class Veiculo implements Serializable {
    @Id
    private int cod;
    private int cod_marca;
    private String modelo;
    private String placa;
    private double km_inicial;
    private double cap_tanque;
    private String descricao;

    public int getCod_marca() {
        return cod_marca;
    }

    public void setCod_marca(int cod_marca) {
        this.cod_marca = cod_marca;
    }

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public double getKm_inicial() {
        return km_inicial;
    }

    public void setKm_inicial(double km_inicial) {
        this.km_inicial = km_inicial;
    }

    public double getCap_tanque() {
        return cap_tanque;
    }

    public void setCap_tanque(double cap_tanque) {
        this.cap_tanque = cap_tanque;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String toString() {
        return this.getPlaca();
    }
}
