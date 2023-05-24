package br.com.hoffmann.dietadotola.utils;

import java.util.ArrayList;
import java.util.List;

import br.com.hoffmann.dietadotola.domain.response.auxiliar.Carboidratos;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.Frutas;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.Vegetais;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.Proteinas;

public class Utilitarios {
    public List<Carboidratos> createCarboList() {
        List<Carboidratos> listaDeCarboidratos = new ArrayList<>();

        Carboidratos arroz = new Carboidratos("Arroz", 130, 30);
        Carboidratos macarrao = new Carboidratos("Macarrão", 157, 30);
        Carboidratos batataDoce = new Carboidratos("Batata doce", 86, 22);
        Carboidratos batataInglesa = new Carboidratos("Batata inglesa", 87, 18);
        Carboidratos mandioca = new Carboidratos("Mandioca", 125, 6);
        Carboidratos aveia = new Carboidratos("Aveia", 394, 66);

        listaDeCarboidratos.add(arroz);
        listaDeCarboidratos.add(macarrao);
        listaDeCarboidratos.add(batataDoce);
        listaDeCarboidratos.add(batataInglesa);
        listaDeCarboidratos.add(mandioca);
        listaDeCarboidratos.add(aveia);

        return listaDeCarboidratos;
    }

    public List<Proteinas> createProteinaList() {
        List<Proteinas> listaDeProteinas = new ArrayList<>();
        Proteinas frango = new Proteinas("Frango", 165, 28);
        Proteinas carne = new Proteinas("Carne", 163, 28);
        Proteinas ovo = new Proteinas("Ovo", 78, 7);
        Proteinas atum = new Proteinas("Atum", 116, 25);
        Proteinas peixe = new Proteinas("Peixe", 96, 20);

        listaDeProteinas.add(frango);
        listaDeProteinas.add(carne);
        listaDeProteinas.add(ovo);
        listaDeProteinas.add(atum);
        listaDeProteinas.add(peixe);

        return listaDeProteinas;
    }

    public List<Vegetais> createVegetaisList(){
        List<Vegetais> listaDeVegetais = new ArrayList<>();

        Vegetais pepino = new Vegetais("Pepino", 12, 2, 0);
        Vegetais cenoura = new Vegetais("Cenoura", 41, 10, 1);
        Vegetais chuchu = new Vegetais("Chuchu", 19, 4, 1);
        Vegetais abobrinha = new Vegetais("Abobrinha", 17, 3, 1);
        Vegetais repolho = new Vegetais("Repolho", 25, 6, 1);
        Vegetais couve = new Vegetais("Couve", 32, 5, 3);
        Vegetais brocolis = new Vegetais("Brocolis", 34, 7, 3);
        Vegetais berinjela = new Vegetais("Berinjela", 25, 6, 1);

        listaDeVegetais.add(pepino);
        listaDeVegetais.add(cenoura);
        listaDeVegetais.add(chuchu);
        listaDeVegetais.add(abobrinha);
        listaDeVegetais.add(repolho);
        listaDeVegetais.add(couve);
        listaDeVegetais.add(brocolis);
        listaDeVegetais.add(berinjela);

        return listaDeVegetais;
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

    public double calculaGramasPorRefeicao(double carbRefeicao, double qtdPorporcao) {
        return (carbRefeicao / qtdPorporcao) * 100.0;
    }
}
