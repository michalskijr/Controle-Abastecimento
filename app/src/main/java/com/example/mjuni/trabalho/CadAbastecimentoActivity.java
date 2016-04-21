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

import com.example.mjuni.trabalho.dao.AbastecimentoDAO;
import com.example.mjuni.trabalho.dao.VeiculoDAO;
import com.example.mjuni.trabalho.model.Abastecimento;
import com.example.mjuni.trabalho.model.TpCombustivel;
import com.example.mjuni.trabalho.model.Veiculo;

import org.apache.commons.lang3.ObjectUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CadAbastecimentoActivity extends AppCompatActivity {

    private EditText edtData;
    private EditText edtPlaca;
    private int codVeiculo;
    private EditText edtQtnLitros;
    private EditText edtVlTotal;
    private EditText edtKmAtual;
    private Spinner spnCombustivel;
    private EditText edtPosto;

    private AbastecimentoDAO abastecimentoDAO;
    private Abastecimento abastecimento;

    private TpCombustivel tpCombustivel;

    private VeiculoDAO veiculoDAO;
    private Veiculo veiculo;

    private boolean insert = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cad_abastecimento);

        abastecimentoDAO = new AbastecimentoDAO(this);
        veiculoDAO = new VeiculoDAO(this);

        edtData = (EditText) findViewById(R.id.edtData);
        edtPlaca = (EditText) findViewById(R.id.edtPlacaAbastecimento);
        edtQtnLitros = (EditText) findViewById(R.id.edtQtnLitros);
        edtVlTotal = (EditText) findViewById(R.id.edtVlTotal);
        edtKmAtual = (EditText) findViewById(R.id.edtKmAtual);
        edtPosto = (EditText) findViewById(R.id.edtPosto);

        List<TpCombustivel> combustiveis = Arrays.asList(TpCombustivel.values());

        ArrayAdapter<TpCombustivel> adapterCombustivel =
                new ArrayAdapter<TpCombustivel>(this,
                        android.R.layout.simple_dropdown_item_1line, combustiveis);

        spnCombustivel = (Spinner) findViewById(R.id.spnCombustivel);
        spnCombustivel.setAdapter(adapterCombustivel);

        edtPlaca.setEnabled(false);

        try {
            veiculo = (Veiculo) getIntent().getSerializableExtra("veiculo");
            edtPlaca.setText(veiculo.getPlaca());
            codVeiculo = veiculo.getCod();
//            insert = false;
        } catch (NullPointerException e) {
            Log.e("MSG", "Não veio veículo!");
        }
    }

    public void btnSalvarAbastecimentoClick (View view) {
        try {
            Abastecimento abastecimento = this.getAbastecimento();

            if(insert)
                abastecimentoDAO.salvar(abastecimento);
            else {
                abastecimentoDAO.alterar(abastecimento);
            }

            this.finish();
        } catch (IllegalAccessException e) {
            Toast.makeText(this, "Erro ao salvar abastecimento!", Toast.LENGTH_LONG).show();
        }

        Toast.makeText(this, "Abastecimento salvo com sucesso!", Toast.LENGTH_LONG).show();
    }

    public Abastecimento getAbastecimento () {
        Abastecimento abastecimento = new Abastecimento();

        try {
            abastecimento.setCod(Integer.parseInt(null));
        } catch (Exception e) {

        }
        abastecimento.setData(edtData.getText().toString());
        abastecimento.setCod_veiculo(codVeiculo);
        abastecimento.setLitros(Double.parseDouble(edtQtnLitros.getText().toString()));
        abastecimento.setVl_total(Double.parseDouble(edtVlTotal.getText().toString()));
        abastecimento.setKm_atual(Double.parseDouble(edtKmAtual.getText().toString()));
        abastecimento.setTp_combustivel(spnCombustivel.getSelectedItemPosition());
        abastecimento.setPosto(edtPosto.getText().toString());

        return abastecimento;
    }

    public void btnListarClick() {
        Intent i = new Intent(this, ListaAbastecimentoActivity.class);
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
