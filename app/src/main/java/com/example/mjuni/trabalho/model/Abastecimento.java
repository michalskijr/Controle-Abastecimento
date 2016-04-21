package com.example.mjuni.trabalho.model;

import com.example.mjuni.trabalho.dao.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by mjuni on 03/04/2016.
 */
public class Abastecimento implements Serializable {
    @Id
    private int cod;
    private String data = "12/12/2012";
    private double litros = 12;
    private double vl_total = 12;
    private double km_atual = 12;
    private int tp_combustivel = 12;
    private String posto = "teste";
    private int cod_veiculo = 0;

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public double getLitros() {
        return litros;
    }

    public void setLitros(double litros) {
        this.litros = litros;
    }

    public double getVl_total() {
        return vl_total;
    }

    public void setVl_total(double vl_total) {
        this.vl_total = vl_total;
    }

    public double getKm_atual() {
        return km_atual;
    }

    public void setKm_atual(double km_atual) {
        this.km_atual = km_atual;
    }

    public String getPosto() {
        return posto;
    }

    public void setPosto(String posto) {
        this.posto = posto;
    }

    public int getTp_combustivel() {
        return tp_combustivel;
    }

    public void setTp_combustivel(int tp_combustivel) {
        this.tp_combustivel = tp_combustivel;
    }

    public int getCod_veiculo() {
        return cod_veiculo;
    }

    public void setCod_veiculo(int cod_veiculo) {
        this.cod_veiculo = cod_veiculo;
    }

    public String toString() {
        return this.getCod() + " - " + this.getTp_combustivel() + " - " + this.getCod_veiculo() + " - " + this.getData();
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}