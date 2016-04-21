package com.example.mjuni.trabalho.model;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by mjunior on 04/04/2016.
 */
public enum TpCombustivel {

    GASOLINA(0), ETANOL(1), DIESEL(2);

    private int id;

    private static Map<Integer, TpCombustivel> combustiveis;

    static {
        combustiveis = new TreeMap<>();

        for (TpCombustivel tp : TpCombustivel.values())
            combustiveis.put(tp.getId(), tp);

    }

    private TpCombustivel (int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Map<Integer, TpCombustivel> getCombustiveis() {
        return combustiveis;
    }
}
