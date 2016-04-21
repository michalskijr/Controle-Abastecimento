package com.example.mjuni.trabalho.dao;

import android.content.Context;
import android.database.Cursor;

import com.example.mjuni.trabalho.model.Marca;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mjunior on 03/04/2016.
 */
public class MarcaDAO extends DAO<Marca> {

    public MarcaDAO(Context context) {
        super(context, Marca.class);
    }

    @Override
    public List<Marca> listar() {
        Cursor cursor = getReadableDatabase().query("Marca",
                new String[]{"cod", "nome"}, null, null, null, null, null);

        List<Marca> marcas = new ArrayList<>();

        while (cursor.moveToNext()) {
            Marca marca = new Marca();
            marca.setCod(cursor.getInt(0));
            marca.setNome(cursor.getString(1));

            marcas.add(marca);
        }
        cursor.close();

        return marcas;
    }
}