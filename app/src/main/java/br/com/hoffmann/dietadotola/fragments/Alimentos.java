package br.com.hoffmann.dietadotola.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.slider.Slider;

import java.util.ArrayList;
import java.util.List;

import br.com.hoffmann.dietadotola.R;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.Carboidratos;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.Frutas;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.Proteinas;

public class Alimentos extends Fragment {

    private static final String TAXA_BASAL_FINAL = "taxaBasalFinal";
    private static final String PROTEINA = "proteina";
    private static final String CARBOIDRATO = "carboidratro";

    private int taxaBasalFinal, proteina, carboidratro, qtdRefeicoes, proteinaPorRefeicao, carboidratroPorRefeicao;
    private ChipGroup chipGroupCarbo, chipGroupProteina, chipGroupFruta;
    private Slider refeicoesSlider;

    public Alimentos() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            taxaBasalFinal = getArguments().getInt(TAXA_BASAL_FINAL);
            proteina = getArguments().getInt(PROTEINA);
            carboidratro = getArguments().getInt(CARBOIDRATO);
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
            }
        });

        //alimentosPorRefeicao(carboidratro, proteina, qtdRefeicoes);

        return view;
    }

    private void alimentosPorRefeicao(int carboidratro, int proteina, int qtdRefeicoes) {
        proteinaPorRefeicao = proteina / qtdRefeicoes;
        carboidratroPorRefeicao = carboidratro / qtdRefeicoes;
    }

    private void CriaChipGroups() {
        List<Carboidratos> listaDeCarboidratos = new ArrayList<>();
        Carboidratos carboidrato1 = new Carboidratos("Arroz", 130, 30);
        Carboidratos carboidrato2 = new Carboidratos("Macarrão", 157, 30);
        Carboidratos carboidrato3 = new Carboidratos("Batata doce", 86, 22);
        Carboidratos carboidrato4 = new Carboidratos("Batata inglesa", 87, 18);
        Carboidratos carboidrato5 = new Carboidratos("Polenta", 94, 25);


        listaDeCarboidratos.add(carboidrato1);
        listaDeCarboidratos.add(carboidrato2);
        listaDeCarboidratos.add(carboidrato3);
        listaDeCarboidratos.add(carboidrato4);
        listaDeCarboidratos.add(carboidrato5);


        for (Carboidratos valor : listaDeCarboidratos) {
            Chip chip = new Chip(getContext());
            chip.setText(valor.getNome());
            chip.setClickable(true);
            chip.setCheckable(true);
            chipGroupCarbo.addView(chip);
        }

        List<Proteinas> listaDeProteinas = new ArrayList<>();
        Proteinas proteina1 = new Proteinas("Frango", 165, 28);
        Proteinas proteina2 = new Proteinas("Carne", 163, 28);
        Proteinas proteina3 = new Proteinas("Ovo", 78, 7);
        Proteinas proteina4 = new Proteinas("Atum", 116, 25);
        Proteinas proteina5 = new Proteinas("Peixe", 96, 20);
        Proteinas proteina6 = new Proteinas("Iogurte", 80, 5);
        Proteinas proteina7 = new Proteinas("Leite", 70, 3);

        listaDeProteinas.add(proteina1);
        listaDeProteinas.add(proteina2);
        listaDeProteinas.add(proteina3);
        listaDeProteinas.add(proteina4);
        listaDeProteinas.add(proteina5);
        listaDeProteinas.add(proteina6);
        listaDeProteinas.add(proteina7);

        for (Proteinas valor : listaDeProteinas) {
            Chip chip = new Chip(getContext());
            chip.setText(valor.getNome());
            chip.setClickable(true);
            chip.setCheckable(true);
            chipGroupProteina.addView(chip);
        }

        List<Frutas> listaDeFrutas = new ArrayList<>();
        Frutas fruta1 = new Frutas("Abacaxi", 54, 13);
        Frutas fruta2 = new Frutas("Maça", 52, 14);
        Frutas fruta3 = new Frutas("Pera", 57, 15);
        Frutas fruta4 = new Frutas("Mamão", 43, 11);
        Frutas fruta5 = new Frutas("Melancia", 24, 8);
        Frutas fruta6 = new Frutas("Morango", 32, 7);
        Frutas fruta7 = new Frutas("Banana", 105, 23);
        Frutas fruta8 = new Frutas("Abacate", 160, 9);

        listaDeFrutas.add(fruta1);
        listaDeFrutas.add(fruta2);
        listaDeFrutas.add(fruta3);
        listaDeFrutas.add(fruta4);
        listaDeFrutas.add(fruta5);
        listaDeFrutas.add(fruta6);
        listaDeFrutas.add(fruta7);
        listaDeFrutas.add(fruta8);

        for (Frutas valor : listaDeFrutas) {
            Chip chip = new Chip(getContext());
            chip.setText(valor.getNome());
            chip.setClickable(true);
            chip.setCheckable(true);
            chipGroupFruta.addView(chip);
        }
    }

    private void iniciaComponentes(View view) {
        chipGroupCarbo = view.findViewById(R.id.chip_group_carbo);
        chipGroupProteina = view.findViewById(R.id.chip_group_proteinas);
        chipGroupFruta = view.findViewById(R.id.chip_group_frutas);
        refeicoesSlider = view.findViewById(R.id.slider_qtd_refeicoes);
    }
}