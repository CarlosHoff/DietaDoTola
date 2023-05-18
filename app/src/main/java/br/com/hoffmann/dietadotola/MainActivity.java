package br.com.hoffmann.dietadotola;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        List<String> listaDeCarboidratos = new ArrayList<>();
        listaDeCarboidratos.add("Arroz");
        listaDeCarboidratos.add("Macarrão");
        listaDeCarboidratos.add("Batata doce");
        listaDeCarboidratos.add("Batata inglesa");
        listaDeCarboidratos.add("Mandioca");
        listaDeCarboidratos.add("Polenta");

        List<String> listaDeProteinas = new ArrayList<>();
        listaDeProteinas.add("Frango");
        listaDeProteinas.add("Carne");
        listaDeProteinas.add("Ovo");
        listaDeProteinas.add("Atum");
        listaDeProteinas.add("Peixe");
        listaDeProteinas.add("Iogurte");
        listaDeProteinas.add("Leite");

        ChipGroup chipGroupCarbo = findViewById(R.id.chip_group_carbo);
        for (String valor : listaDeCarboidratos) {
            Chip chip = new Chip(this);
            chip.setText(valor);
            chip.setClickable(true);
            chip.setCheckable(true);
            chipGroupCarbo.addView(chip);
        }

        ChipGroup chipGroupProteina = findViewById(R.id.chip_group_proteinas);
        for (String valor : listaDeProteinas) {
            Chip chip = new Chip(this);
            chip.setText(valor);
            chip.setClickable(true);
            chip.setCheckable(true);
            chipGroupProteina.addView(chip);
        }
    }
}