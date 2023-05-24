package br.com.hoffmann.dietadotola.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.hoffmann.dietadotola.R;
import br.com.hoffmann.dietadotola.adapter.FrutaAdapter;
import br.com.hoffmann.dietadotola.adapter.MontaDietaAdapter;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.Carboidratos;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.Frutas;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.Proteinas;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.Vegetais;
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
    private MontaDietaAdapter montaDietaAdapter;
    private FrutaAdapter frutaAdapter;
    MontaDietaViewModel viewModel = new MontaDietaViewModel();
    private RecyclerView recyclerViewDietas, recyclerViewFrutas;
    private int qtdRefeicoes, proteinaRefeicao, carboidratoRefeicao, gorduraRefeicao;
    private List<Carboidratos> listaCarboidratosSelecionados;
    private List<Proteinas> listProteinasSelecionadas;
    private List<Frutas> listFrutasSelecionadas;
    private List<Vegetais> listVegetaisSelecionadas;

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
            listaCarboidratosSelecionados = getArguments().getParcelableArrayList(CARBOIDRATOS_SELECIONADOS);
            listProteinasSelecionadas = getArguments().getParcelableArrayList(PROTEINAS_SELECIONADAS);
            listFrutasSelecionadas = getArguments().getParcelableArrayList(FRUTAS_SELECIONADAS);
            listVegetaisSelecionadas = getArguments().getParcelableArrayList(VEGETAIS_SELECIONADAS);

            viewModel.iniciarMontagemDieta();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(MontaDietaViewModel.class);

        View view = inflater.inflate(R.layout.fragment_monta_dieta, container, false);
        recyclerViewDietas = view.findViewById(R.id.recicleViewDietas);
        recyclerViewFrutas = view.findViewById(R.id.recicleViewFrutas);


        setupRecyclerViewFrutas();
        viewModel.getListaDeFrutasPorRefeicao(listFrutasSelecionadas).observe(getViewLifecycleOwner(), frutasCalculadas -> {
            frutaAdapter.setFrutasList(frutasCalculadas);
            frutaAdapter.notifyDataSetChanged();
        });

        setupRecyclerViewDietas();
        viewModel.getListaDeCarbosPorRefeicao(qtdRefeicoes, carboidratoRefeicao, listaCarboidratosSelecionados).observe(getViewLifecycleOwner(), carbosCalculados -> {
            montaDietaAdapter.setCarboList(carbosCalculados);
            montaDietaAdapter.notifyDataSetChanged();
        });

        viewModel.getListaDeProteinasPorRefeicao(qtdRefeicoes, proteinaRefeicao, listProteinasSelecionadas).observe(getViewLifecycleOwner(), proteinasCalculadas -> {
            montaDietaAdapter.setProteinaList(proteinasCalculadas);
            montaDietaAdapter.notifyDataSetChanged();
        });

        viewModel.getListaDeVegetaisPorRefeicao(qtdRefeicoes, proteinaRefeicao, listVegetaisSelecionadas).observe(getViewLifecycleOwner(), vegetaisCalculados -> {
            montaDietaAdapter.setVegetaisList(vegetaisCalculados);
            montaDietaAdapter.notifyDataSetChanged();
        });

        return view;
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