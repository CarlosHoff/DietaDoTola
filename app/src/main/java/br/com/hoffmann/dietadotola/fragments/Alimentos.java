package br.com.hoffmann.dietadotola.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.slider.Slider;

import java.util.ArrayList;
import java.util.List;

import br.com.hoffmann.dietadotola.R;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.Carboidratos;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.Frutas;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.Proteinas;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.Vegetais;
import br.com.hoffmann.dietadotola.utils.Utilitarios;

public class Alimentos extends Fragment {
    private static final String PROTEINA = "proteina";
    private static final String CARBOIDRATO = "carboidrato";
    private int proteina;
    private int carboidrato;
    private int qtdRefeicoes;
    private int protRefeicao;
    private int carbRefeicao;
    private ChipGroup chipGroupCarbo, chipGroupProteina, chipGroupFruta, chipGroupVegetais;
    private Slider refeicoesSlider;
    private Button montarDieta;
    Utilitarios utils = new Utilitarios();
    List<Carboidratos> carbosSelecionados = new ArrayList<>();
    List<Proteinas> proteinasSelecionadas = new ArrayList<>();
    List<Frutas> frutasSelecionadas = new ArrayList<>();
    List<Vegetais> vegetaisSelecionadas = new ArrayList<>();

    public Alimentos() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            proteina = getArguments().getInt(PROTEINA);
            carboidrato = getArguments().getInt(CARBOIDRATO);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_alimentos, container, false);

        iniciaComponentes(view);

        CriaChipGroups();

        refeicoesSlider.addOnSliderTouchListener(new Slider.OnSliderTouchListener() {
            @Override
            public void onStartTrackingTouch(@NonNull Slider slider) {
            }

            @Override
            public void onStopTrackingTouch(@NonNull Slider slider) {
                qtdRefeicoes = (int) slider.getValue();
                pegaChipsSelecionados();
                alimentosPorRefeicao(carboidrato, proteina, qtdRefeicoes);
                montarDieta.setVisibility(View.VISIBLE);
            }
        });

        montarDieta.setOnClickListener(v -> {
            validaCampos();

            MontaDieta montaDieta = new MontaDieta();
            Bundle args = new Bundle();
            args.putParcelableArrayList("carbosSelecionados", new ArrayList<>(carbosSelecionados));
            args.putParcelableArrayList("proteinasSelecionadas", new ArrayList<>(proteinasSelecionadas));
            args.putParcelableArrayList("frutasSelecionadas", new ArrayList<>(frutasSelecionadas));
            args.putParcelableArrayList("vegetaisSelecionadas", new ArrayList<>(vegetaisSelecionadas));
            args.putInt("qtdRefeicoes", qtdRefeicoes);
            args.putInt("protRefeicao", protRefeicao);
            args.putInt("carbRefeicao", carbRefeicao);
            args.putInt("carbRefeicao", carbRefeicao);
            montaDieta.setArguments(args);
            FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.replace(R.id.fragment_alimentos, montaDieta);
            fragmentTransaction.addToBackStack(null);
            fragmentTransaction.commit();

        });

        return view;
    }

    private void pegaChipsSelecionados() {
        pegaListaDeCarbos();
        pegaListaDeProteinas();
        pegaListaDeFrutas();
        pegaListaDeVegetais();
    }

    private void validaCampos() {
        if (carbosSelecionados.isEmpty()){
            Toast.makeText(getContext(), "É necessário escolher no minimo 1 Carboidrato.", Toast.LENGTH_SHORT).show();
        } else if (proteinasSelecionadas.isEmpty()){
            Toast.makeText(getContext(), "É necessário escolher no minimo 1 Proteina.", Toast.LENGTH_SHORT).show();
        } else if (vegetaisSelecionadas.isEmpty()) {
            Toast.makeText(getContext(), "É necessário escolher no minimo 1 Vegetal.", Toast.LENGTH_SHORT).show();
        } else if (qtdRefeicoes == 0) {
            Toast.makeText(getContext(), "É necessário escolher a quantidade de refeiçoes que deseja fazer.", Toast.LENGTH_SHORT).show();
        }
    }

    private void pegaListaDeCarbos() {
        List<Integer> checkedChipIds = chipGroupCarbo.getCheckedChipIds();
        for (int chipId : checkedChipIds) {
            Chip chip = chipGroupCarbo.findViewById(chipId);
            Carboidratos carboidratos = new Carboidratos();
            carboidratos.setNome(chip.getText().toString());
            carbosSelecionados.add(carboidratos);
        }
    }

    private void pegaListaDeProteinas() {
        List<Integer> checkedChipIds = chipGroupProteina.getCheckedChipIds();
        for (int chipId : checkedChipIds) {
            Chip chip = chipGroupProteina.findViewById(chipId);
            Proteinas proteinas = new Proteinas();
            proteinas.setNome(chip.getText().toString());
            proteinasSelecionadas.add(proteinas);
        }
    }

    private void pegaListaDeFrutas() {
        List<Integer> checkedChipIds = chipGroupFruta.getCheckedChipIds();
        for (int chipId : checkedChipIds) {
            Chip chip = chipGroupFruta.findViewById(chipId);
            Frutas frutas = new Frutas();
            frutas.setNome(chip.getText().toString());
            frutasSelecionadas.add(frutas);
        }
    }

    private void pegaListaDeVegetais() {
        List<Integer> checkedChipIds = chipGroupVegetais.getCheckedChipIds();
        for (int chipId : checkedChipIds) {
            Chip chip = chipGroupVegetais.findViewById(chipId);
            Vegetais vegetais = new Vegetais();
            vegetais.setNome(chip.getText().toString());
            vegetaisSelecionadas.add(vegetais);
        }
    }

    private void alimentosPorRefeicao(int carboidratro, int proteina, int qtdRefeicoes) {
        protRefeicao = proteina / qtdRefeicoes;
        carbRefeicao = carboidratro / qtdRefeicoes;
    }

    private void CriaChipGroups() {
        List<Carboidratos> listaDeCarboidratos = utils.createCarboList();

        for (Carboidratos valor : listaDeCarboidratos) {
            Chip chip = new Chip(getContext());
            chip.setText(valor.getNome());
            chip.setClickable(true);
            chip.setCheckable(true);
            chipGroupCarbo.addView(chip);
        }

        List<Proteinas> listaDeProteinas = utils.createProteinaList();

        for (Proteinas valor : listaDeProteinas) {
            Chip chip = new Chip(getContext());
            chip.setText(valor.getNome());
            chip.setClickable(true);
            chip.setCheckable(true);
            chipGroupProteina.addView(chip);
        }

        List<Frutas> listaDeFrutas = utils.createFruitList();

        for (Frutas valor : listaDeFrutas) {
            Chip chip = new Chip(getContext());
            chip.setText(valor.getNome());
            chip.setClickable(true);
            chip.setCheckable(true);
            chipGroupFruta.addView(chip);
        }

        List<Vegetais> listaDeVegetais = utils.createVegetaisList();
        for (Vegetais valor : listaDeVegetais) {
            Chip chip = new Chip(getContext());
            chip.setText(valor.getNome());
            chip.setClickable(true);
            chip.setCheckable(true);
            chipGroupVegetais.addView(chip);
        }
    }

    private void iniciaComponentes(View view) {
        chipGroupCarbo = view.findViewById(R.id.chip_group_carbo);
        chipGroupProteina = view.findViewById(R.id.chip_group_proteinas);
        chipGroupFruta = view.findViewById(R.id.chip_group_frutas);
        chipGroupVegetais = view.findViewById(R.id.chip_group_vegetais);
        refeicoesSlider = view.findViewById(R.id.slider_qtd_refeicoes);
        montarDieta = view.findViewById(R.id.irPaginaDieta);
    }
}