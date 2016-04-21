package com.example.mjuni.trabalho;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.mjuni.trabalho.dao.AbastecimentoDAO;
import com.example.mjuni.trabalho.model.Abastecimento;

import java.util.List;

public class ListaAbastecimentoActivity extends AppCompatActivity {

    private AbastecimentoDAO abastecimentoDAO;

    private ListView lvAbastecimentos;
    private List<Abastecimento> abastecimentoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_abastecimento);

        lvAbastecimentos = (ListView) findViewById(R.id.lvAbastecimentos);

        abastecimentoDAO = new AbastecimentoDAO(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        abastecimentoList = abastecimentoDAO.listar();
        ArrayAdapter<Abastecimento> adapterAbastecimento = new ArrayAdapter<Abastecimento>(
                this, android.R.layout.simple_list_item_1, abastecimentoList);

        lvAbastecimentos.setAdapter(adapterAbastecimento);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menugeral, menu);

        return super.onCreateOptionsMenu(menu);
    }
}
