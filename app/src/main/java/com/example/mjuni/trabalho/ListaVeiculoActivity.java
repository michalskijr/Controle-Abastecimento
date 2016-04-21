package com.example.mjuni.trabalho;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Selection;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.mjuni.trabalho.dao.VeiculoDAO;
import com.example.mjuni.trabalho.model.Veiculo;

import java.util.List;

public class ListaVeiculoActivity extends AppCompatActivity {

    private VeiculoDAO veiculoDAO;

    private ListView lvVeiculos;
    private List<Veiculo> veiculoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_veiculo);

        lvVeiculos = (ListView) findViewById(R.id.lvVeiculos);

        veiculoDAO = new VeiculoDAO(this);

        lvVeiculos.setOnCreateContextMenuListener(
                new View.OnCreateContextMenuListener() {
                    @Override
                    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
                        getMenuInflater().inflate(R.menu.context, menu);
                    }
                }
        );

        lvVeiculos.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Veiculo veiculo = veiculoList.get(position);

                Intent intentAbastecimento = new Intent(view.getContext(), CadAbastecimentoActivity.class);
                intentAbastecimento.putExtra("veiculo", veiculo);
                startActivity(intentAbastecimento);

                Log.e("MSG", "Click: " + veiculo.getCod() + "  " + veiculo.getPlaca());
            }
        });


    }

    @Override
    protected void onResume() {
        super.onResume();

        veiculoList = veiculoDAO.listar();
        ArrayAdapter<Veiculo> adapterVeiculos = new ArrayAdapter<Veiculo>(this,
                android.R.layout.simple_list_item_1, veiculoList);

        lvVeiculos.setAdapter(adapterVeiculos);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menugeral, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnNovoVeiculo:
                startActivity(new Intent(this, CadVeiculoActivity.class));
                break;

            case R.id.mnListarAbastecimentos:
                startActivity(new Intent(this, ListaAbastecimentoActivity.class));
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo pListMenu =
                (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        Veiculo veiculo = veiculoList.get(pListMenu.position);

        switch (item.getItemId()) {
            case R.id.mnAlterar:
                Intent intentCadVeiculo = new Intent(this, CadVeiculoActivity.class);
                intentCadVeiculo.putExtra("veiculo", veiculo);
                this.startActivity(intentCadVeiculo);
                break;

            case R.id.mnDeletar:
                try {
                    veiculoDAO.deletar(veiculo);
                    this.onResume();
                } catch (IllegalAccessException e) {
                    Log.e("MSG", "Erro ao deletar!");
                }
                break;

            case R.id.mnInformacoes:
                Intent intent = new Intent(this, InfVeiculoActivity.class);
                intent.putExtra("veiculo", veiculo);
                this.startActivity(intent);
                break;
        }
        return super.onContextItemSelected(item);
    }
}