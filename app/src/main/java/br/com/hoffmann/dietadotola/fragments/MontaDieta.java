package br.com.hoffmann.dietadotola.fragments;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import br.com.hoffmann.dietadotola.R;
import br.com.hoffmann.dietadotola.adapter.FrutaAdapter;
import br.com.hoffmann.dietadotola.adapter.MontaDietaAdapter;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.Carboidratos;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.Frutas;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.Proteinas;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.Vegetais;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.VegetaisCalculados;
import br.com.hoffmann.dietadotola.viewmodel.MontaDietaViewModel;

public class MontaDieta extends Fragment {
    private static final String QTD_REFEICOES = "qtdRefeicoes";
    private static final String PROTEINAS_REFEICAO = "proteinaRefeicao";
    private static final String CARBOIDRATOS_REFEICAO = "carboidratoRefeicao";
    private static final String GORDURA_REFEICAO = "gorduraRefeicao";
    private static final String CARBOIDRATOS_SELECIONADOS = "carbosSelecionados";
    private static final String PROTEINAS_SELECIONADAS = "proteinasSelecionadas";
    private static final String FRUTAS_SELECIONADAS = "frutasSelecionadas";
    private static final String VEGETAIS_SELECIONADAS = "vegetaisSelecionadas";
    private static final String PESO = "peso";
    private MontaDietaAdapter montaDietaAdapter;
    private FrutaAdapter frutaAdapter;
    MontaDietaViewModel viewModel = new MontaDietaViewModel();
    private RecyclerView recyclerViewDietas, recyclerViewFrutas;
    private int qtdRefeicoes, proteinaRefeicao, carboidratoRefeicao, gorduraRefeicao;
    private Double peso;
    private List<Carboidratos> listaCarboidratosSelecionados;
    private List<Proteinas> listProteinasSelecionadas;
    private List<Frutas> listFrutasSelecionadas;
    private List<Vegetais> listVegetaisSelecionadas;
    private TextView textoAgua;
    private Button botaoReload;

    public MontaDieta() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            qtdRefeicoes = getArguments().getInt(QTD_REFEICOES);
            proteinaRefeicao = getArguments().getInt(PROTEINAS_REFEICAO);
            carboidratoRefeicao = getArguments().getInt(CARBOIDRATOS_REFEICAO);
            gorduraRefeicao = getArguments().getInt(GORDURA_REFEICAO);
            peso = getArguments().getDouble(PESO);
            listaCarboidratosSelecionados = getArguments().getParcelableArrayList(CARBOIDRATOS_SELECIONADOS);
            listProteinasSelecionadas = getArguments().getParcelableArrayList(PROTEINAS_SELECIONADAS);
            listFrutasSelecionadas = getArguments().getParcelableArrayList(FRUTAS_SELECIONADAS);
            listVegetaisSelecionadas = getArguments().getParcelableArrayList(VEGETAIS_SELECIONADAS);

            viewModel.iniciarMontagemDieta();
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(MontaDietaViewModel.class);

        View view = inflater.inflate(R.layout.fragment_monta_dieta, container, false);
        recyclerViewDietas = view.findViewById(R.id.recicleViewDietas);
        recyclerViewFrutas = view.findViewById(R.id.recicleViewFrutas);
        textoAgua = view.findViewById(R.id.texto_agua);
        botaoReload = view.findViewById(R.id.botao_reload);

        criaLayout();


        botaoReload.setOnClickListener(v -> criaLayout());

        return view;
    }

    @SuppressLint("DefaultLocale")
    private void criaLayout() {
        setupRecyclerViewFrutas();
        viewModel.getListaDeFrutasPorRefeicao(listFrutasSelecionadas).observe(getViewLifecycleOwner(), frutasCalculadas -> {
            frutaAdapter.setFrutasList(frutasCalculadas);
            frutaAdapter.notifyDataSetChanged();
        });

        List<VegetaisCalculados> listaFinalVegetais = viewModel.getListaDeVegetaisPorRefeicao(qtdRefeicoes, carboidratoRefeicao, listVegetaisSelecionadas).getValue();

        setupRecyclerViewDietas();
        viewModel.getListaDeCarbosPorRefeicao(qtdRefeicoes, carboidratoRefeicao, listaCarboidratosSelecionados, gorduraRefeicao, listaFinalVegetais).observe(getViewLifecycleOwner(), carbosCalculados -> {
            montaDietaAdapter.setCarboList(carbosCalculados);
            montaDietaAdapter.notifyDataSetChanged();
        });

        viewModel.getListaDeProteinasPorRefeicao(qtdRefeicoes, proteinaRefeicao, listProteinasSelecionadas).observe(getViewLifecycleOwner(), proteinasCalculadas -> {
            montaDietaAdapter.setProteinaList(proteinasCalculadas);
            montaDietaAdapter.notifyDataSetChanged();
        });

        viewModel.getListaDeVegetaisPorRefeicao(qtdRefeicoes, carboidratoRefeicao, listVegetaisSelecionadas).observe(getViewLifecycleOwner(), vegetaisCalculados -> {
            montaDietaAdapter.setVegetaisList(vegetaisCalculados);
            montaDietaAdapter.notifyDataSetChanged();
        });

        textoAgua.setText(String.format("Você deve beber %.1f litros de água por dia.", formataNumero()));
    }

    private double formataNumero() {
        BigDecimal number = BigDecimal.valueOf(peso * 35).divide(BigDecimal.valueOf(1000), 1, RoundingMode.HALF_UP);
        return number.doubleValue();
    }

    private void setupRecyclerViewDietas() {
        montaDietaAdapter = new MontaDietaAdapter(getContext(), new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewDietas.setLayoutManager(layoutManager);
        recyclerViewDietas.setAdapter(montaDietaAdapter);
    }

    private void setupRecyclerViewFrutas() {
        frutaAdapter = new FrutaAdapter(getContext(), new ArrayList<>());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerViewFrutas.setLayoutManager(layoutManager);
        recyclerViewFrutas.setAdapter(frutaAdapter);
    }

}