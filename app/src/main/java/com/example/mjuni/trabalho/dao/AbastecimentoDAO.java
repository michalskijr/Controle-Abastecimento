package com.example.mjuni.trabalho.dao;

import android.content.Context;
import android.database.Cursor;

import com.example.mjuni.trabalho.model.Abastecimento;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mjunior on 04/04/2016.
 */
public class AbastecimentoDAO extends DAO<Abastecimento> {

    public AbastecimentoDAO(Context context) {
        super(context, Abastecimento.class);
    }

    @Override
    public List<Abastecimento> listar() {
        Cursor cursor = getReadableDatabase().query("ABASTECIMENTO",
                new String[]{"cod", "data", "litros", "vl_total", "km_atual",
                "tp_combustivel", "posto", "cod_veiculo"}, null, null, null, null, null);

        List<Abastecimento> abastecimentos = new ArrayList<>();

        while (cursor.moveToNext()) {
            Abastecimento abastecimento = new Abastecimento();

            abastecimento.setCod(cursor.getInt(0));
            abastecimento.setData(cursor.getString(1));
            abastecimento.setLitros(cursor.getDouble(2));
            abastecimento.setVl_total(cursor.getDouble(3));
            abastecimento.setKm_atual(cursor.getDouble(4));
            abastecimento.setTp_combustivel(cursor.getInt(5));
            abastecimento.setPosto(cursor.getString(6));
            abastecimento.setCod_veiculo(cursor.getInt(7));

            abastecimentos.add(abastecimento);
        }
        cursor.close();

        return abastecimentos;
    }
}
