package com.example.mjuni.trabalho;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import com.example.mjuni.trabalho.dao.AbastecimentoDAO;
import com.example.mjuni.trabalho.dao.VeiculoDAO;
import com.example.mjuni.trabalho.model.Abastecimento;
import com.example.mjuni.trabalho.model.TpCombustivel;
import com.example.mjuni.trabalho.model.Veiculo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class InfVeiculoActivity extends AppCompatActivity {

    private Spinner spnCombustivel;
    private EditText edtPlacaAbastecimento;
    private EditText edtMediaAutonomia;
    private EditText edtQtnTotalLitros;
    private EditText edtVlMedio;
    private EditText edtVlTotalAbastecido;

    private int codVeiculo;

    private AbastecimentoDAO abastecimentoDAO;
    private Abastecimento abastecimento;
    private List<Abastecimento> abastecimentoList;

    private TpCombustivel tpCombustivel;

    private VeiculoDAO veiculoDAO;
    private Veiculo veiculo;
    private List<Veiculo> veiculoList;

    int cont;
    Double qtnLitros;
    Double vlTotalAbastecimento;
    Double kmInicial;
    Double kmFinal;
    Double qtnLitrosAutonomia;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inf_veiculo);

        abastecimentoDAO = new AbastecimentoDAO(this);
        veiculoDAO = new VeiculoDAO(this);

        edtMediaAutonomia = (EditText) findViewById(R.id.edtMediaAutonomia);
        edtQtnTotalLitros = (EditText) findViewById(R.id.edtQtnTotalLitros);
        edtVlMedio = (EditText) findViewById(R.id.edtVlMedio);
        edtVlTotalAbastecido = (EditText) findViewById(R.id.edtVlTotalAbastecido);
        edtPlacaAbastecimento = (EditText) findViewById(R.id.edtPlacaVeiculo);

        veiculo = (Veiculo) getIntent().getSerializableExtra("veiculo");

        edtPlacaAbastecimento.setText(veiculo.getPlaca());
        edtPlacaAbastecimento.setEnabled(false);
        edtMediaAutonomia.setEnabled(false);
        edtQtnTotalLitros.setEnabled(false);
        edtVlMedio.setEnabled(false);
        edtVlTotalAbastecido.setEnabled(false);

        final List<TpCombustivel> tpCombustiveis = Arrays.asList(TpCombustivel.values());

        final ArrayAdapter<TpCombustivel> adapterCombustivel = new ArrayAdapter<TpCombustivel>(
                this, android.R.layout.simple_dropdown_item_1line, tpCombustiveis);

        spnCombustivel = (Spinner) findViewById(R.id.spnCombustivel);
        spnCombustivel.setAdapter(adapterCombustivel);
        spnCombustivel.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Log.e("MSG", "Teste: " + spnCombustivel.getSelectedItemPosition());

                cont = 0;
                qtnLitros = 0.0;
                vlTotalAbastecimento = 0.0;
                kmInicial = 0.0;
                kmFinal = 0.0;
                qtnLitrosAutonomia = 0.0;

                abastecimentoList = abastecimentoDAO.listar();

                for (int i = 0; i < abastecimentoList.size(); i++) {
                    abastecimento = new Abastecimento();
                    abastecimento = abastecimentoList.get(i);
                    if (abastecimento.getCod_veiculo() == veiculo.getCod()) {
                        if (abastecimento.getTp_combustivel() == spnCombustivel.getSelectedItemPosition()) {
                            cont++;
                            qtnLitros += abastecimento.getLitros();
                            vlTotalAbastecimento += abastecimento.getVl_total();

                            if (i == 0) {
                                kmInicial = abastecimento.getKm_atual();
                                Log.e("MSG", "KmInicial: " + kmInicial);
                            } else if (i == (abastecimentoList.size() - 2)) {
                                qtnLitrosAutonomia = qtnLitros;
                                Log.e("MSG", "Litros autonomia: " + qtnLitrosAutonomia + " tamanho: " + abastecimentoList.size());
                            } else if (i == abastecimentoList.size() - 1) {
                                kmFinal = abastecimento.getKm_atual();
                                Log.e("MSG", "KmFinal: " + kmFinal);
                            }
                        }
                        //Log.e("MSG", "Litros: " + qtnLitros + " Vl Total: " + vlTotalAbastecimento);
                    }

                }

                if (cont <= 1) {
                    Toast.makeText(parent.getContext(), "Não é possível realizar média! Somente possui um abastecimento!", Toast.LENGTH_LONG).show();
                    edtMediaAutonomia.setText("0.00");
                    edtQtnTotalLitros.setText("0.00");
                    edtVlMedio.setText("0.00");
                    edtVlTotalAbastecido.setText("0.00");
                } else {
                    edtMediaAutonomia.setText(String.valueOf((kmFinal - kmInicial)/qtnLitrosAutonomia));
                    edtQtnTotalLitros.setText(String.valueOf(qtnLitros));
                    edtVlMedio.setText(String.valueOf(vlTotalAbastecimento/cont));
                    edtVlTotalAbastecido.setText(String.valueOf(vlTotalAbastecimento));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }
}
