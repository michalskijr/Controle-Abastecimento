package com.example.mjuni.trabalho.dao;

import android.content.Context;
import android.database.Cursor;

import com.example.mjuni.trabalho.model.Veiculo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mjunior on 03/04/2016.
 */
public class VeiculoDAO extends DAO<Veiculo> {

    public VeiculoDAO(Context context) {
        super(context, Veiculo.class);
    }

    @Override
    public List<Veiculo> listar() {
        Cursor cursor = getReadableDatabase().query("Veiculo",
                new String[]{"cod", "cod_marca", "modelo", "placa", "km_inicial",
                "cap_tanque", "descricao"}, null, null, null, null, null);
        List<Veiculo> veiculos = new ArrayList<>();

        while (cursor.moveToNext()) {
            Veiculo veiculo = new Veiculo();
            veiculo.setCod(cursor.getInt(0));
            veiculo.setCod_marca(cursor.getInt(1));
            veiculo.setModelo(cursor.getString(2));
            veiculo.setPlaca(cursor.getString(3));
            veiculo.setKm_inicial(cursor.getDouble(4));
            veiculo.setCap_tanque(cursor.getDouble(5));
            veiculo.setDescricao(cursor.getString(6));

            veiculos.add(veiculo);
        }
        cursor.close();

        return veiculos;
    }
}