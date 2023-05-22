package br.com.hoffmann.dietadotola.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import br.com.hoffmann.dietadotola.R;
import br.com.hoffmann.dietadotola.adapter.MontaDietaAdapter;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.Carboidratos;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.CarboidratosCalculados;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.Frutas;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.Proteinas;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.ProteinasCalculadas;
import br.com.hoffmann.dietadotola.viewmodel.MontaDietaViewModel;

public class MontaDieta extends Fragment {
    private static final String QTD_REFEICOES = "qtdRefeicoes";
    private static final String PROTEINAS_REFEICAO = "protRefeicao";
    private static final String CARBOIDRATOS_REFEICAO = "carbRefeicao";
    private static final String CARBOIDRATOS_SELECIONADOS = "carbosSelecionados";
    private static final String PROTEINAS_SELECIONADAS = "proteinasSelecionadas";
    private static final String FRUTAS_SELECIONADAS = "frutasSelecionadas";
    private MontaDietaAdapter adapter;
    MontaDietaViewModel viewModel = new MontaDietaViewModel();
    private RecyclerView recyclerView;
    private int qtdRefeicoes, protRefeicao, carbRefeicao;
    private List<Carboidratos> listaCarboidratosSelecionados;
    private List<Proteinas> listProteinasSelecionadas;

    public MontaDieta() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            qtdRefeicoes = getArguments().getInt(QTD_REFEICOES);
            protRefeicao = getArguments().getInt(PROTEINAS_REFEICAO);
            carbRefeicao = getArguments().getInt(CARBOIDRATOS_REFEICAO);
            listaCarboidratosSelecionados = getArguments().getParcelableArrayList(CARBOIDRATOS_SELECIONADOS);
            listProteinasSelecionadas = getArguments().getParcelableArrayList(PROTEINAS_SELECIONADAS);

            viewModel.iniciarMontagemDieta(qtdRefeicoes, protRefeicao, carbRefeicao, listaCarboidratosSelecionados, listProteinasSelecionadas);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        viewModel = new ViewModelProvider(this).get(MontaDietaViewModel.class);

        View view = inflater.inflate(R.layout.fragment_monta_dieta, container, false);
        recyclerView = view.findViewById(R.id.recicleViewDietas);

        setupRecyclerView();

        viewModel.getListaDeCarbosPorRefeicao(qtdRefeicoes, carbRefeicao, listaCarboidratosSelecionados).observe(getViewLifecycleOwner(), carbosCalculados -> {
            adapter.setCarboList(carbosCalculados);
            adapter.notifyDataSetChanged();
        });
        viewModel.getListaDeProteinasPorRefeicao(qtdRefeicoes, protRefeicao, listProteinasSelecionadas).observe(getViewLifecycleOwner(), proteinasCalculadas -> {
            adapter.setProteinaList(proteinasCalculadas);
            adapter.notifyDataSetChanged();
        });
//        viewModel.getListaDeFrutasPorRefeicao().observe(getViewLifecycleOwner(), frutasCalculadas -> {
//            adapter.setFrutasList(frutasCalculadas);
//            adapter.notifyDataSetChanged();
//        });
        return view;
    }

    private void setupRecyclerView() {
        adapter = new MontaDietaAdapter(getContext(), new ArrayList<>(), new ArrayList<>());
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

}