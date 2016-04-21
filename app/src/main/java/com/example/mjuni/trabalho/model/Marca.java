package com.example.mjuni.trabalho.model;

import com.example.mjuni.trabalho.dao.Id;

import java.io.Serializable;

/**
 * Created by mjuni on 03/04/2016.
 */
public class Marca implements Serializable {
    @Id
    private int cod;
    private String nome;

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String toString() {
        return this.getNome();
    }
}
