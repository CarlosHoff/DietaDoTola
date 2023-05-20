package br.com.hoffmann.dietadotola.utils;

import java.util.ArrayList;
import java.util.List;

import br.com.hoffmann.dietadotola.domain.response.auxiliar.Carboidratos;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.Frutas;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.Proteinas;

public class Utilitarios {
    public List<Carboidratos> createCarboList() {
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

        return listaDeCarboidratos;
    }

    public List<Proteinas> createProteinaList() {
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

        return listaDeProteinas;
    }

    public List<Frutas> createFruitList() {
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

        return listaDeFrutas;
    }
}
