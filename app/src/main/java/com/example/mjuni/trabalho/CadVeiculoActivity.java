package com.example.mjuni.trabalho;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.mjuni.trabalho.dao.MarcaDAO;
import com.example.mjuni.trabalho.dao.VeiculoDAO;
import com.example.mjuni.trabalho.model.Marca;
import com.example.mjuni.trabalho.model.Veiculo;

import java.util.List;

public class CadVeiculoActivity extends AppCompatActivity {

    private EditText edtCod;
    private EditText edtPlaca;
    private Spinner spnMarca;
    private EditText edtModelo;
    private EditText edtKmInicial;
    private EditText edtCapTanque;
    private EditText edtDescricao;

    private VeiculoDAO veiculoDAO;
    private Veiculo veiculo;

    private MarcaDAO marcaDAO;

    private boolean insert = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_veiculo);

        veiculoDAO = new VeiculoDAO(this);
        marcaDAO = new MarcaDAO(this);

        edtCod = (EditText) findViewById(R.id.edtCod);
        edtPlaca = (EditText) findViewById(R.id.edtPlaca);
        edtModelo = (EditText) findViewById(R.id.edtModelo);
        edtKmInicial = (EditText) findViewById(R.id.edtKmInicial);
        edtCapTanque = (EditText) findViewById(R.id.edtCapTanque);
        edtDescricao = (EditText) findViewById(R.id.edtDescricao);

        List<Marca> marcas = marcaDAO.listar();

        ArrayAdapter<Marca> adapterMarca =
                new ArrayAdapter<Marca>(this, android.R.layout.simple_dropdown_item_1line, marcas);

        spnMarca = (Spinner) findViewById(R.id.spnMarca);
        spnMarca.setAdapter(adapterMarca);

        edtCod.setEnabled(false);

        try {
            veiculo = (Veiculo) getIntent().getSerializableExtra("veiculo");
            setVeiculo(veiculo);
            insert = false;

        } catch (NullPointerException e) {
            Log.e("MSG", "Não veio veículo!");
        }
    }

    public void setVeiculo (Veiculo veiculo) {
        edtCod.setText(String.valueOf(veiculo.getCod()));
        edtPlaca.setText(veiculo.getPlaca());
        edtModelo.setText(veiculo.getModelo());
        edtKmInicial.setText(String.valueOf(veiculo.getKm_inicial()));
        edtCapTanque.setText(String.valueOf(veiculo.getCap_tanque()));
        edtDescricao.setText(veiculo.getDescricao());
        spnMarca.setSelection(veiculo.getCod_marca());
    }

    public void btnSalvarClick (View view) {
        try {
            Veiculo veiculo = this.getVeiculo();

            if (insert)
                veiculoDAO.salvar(veiculo);
            else {
                veiculoDAO.alterar(veiculo);
            }

            this.finish();
        } catch (IllegalAccessException e) {
            Toast.makeText(this, "Erro ao salvar veículo!", Toast.LENGTH_LONG).show();
        }

        Toast.makeText(this, "Veículo salvo com sucesso!", Toast.LENGTH_LONG).show();
    }

    public Veiculo getVeiculo () {
        Veiculo veiculo = new Veiculo();

        try {
            veiculo.setCod(Integer.parseInt(edtCod.getText().toString()));
        } catch (Exception e) {

        }
        veiculo.setPlaca(edtPlaca.getText().toString());
        veiculo.setModelo(edtModelo.getText().toString());
        veiculo.setCod_marca(spnMarca.getSelectedItemPosition());
        veiculo.setKm_inicial(Double.parseDouble(edtKmInicial.getText().toString()));
        veiculo.setCap_tanque(Double.parseDouble(edtCapTanque.getText().toString()));
        veiculo.setDescricao(edtDescricao.getText().toString());

        return veiculo;
    }

    public void btnListarClick() {
        Intent i = new Intent(this, ListaVeiculoActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cadastro, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mnListar:
                this.btnListarClick();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
}
