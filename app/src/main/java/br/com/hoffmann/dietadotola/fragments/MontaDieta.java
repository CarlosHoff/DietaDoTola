package br.com.hoffmann.dietadotola.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import br.com.hoffmann.dietadotola.R;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.Carboidratos;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.CarboidratosCalculados;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.Frutas;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.FrutasCalculadas;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.Proteinas;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.ProteinasCalculadas;
import br.com.hoffmann.dietadotola.utils.Utilitarios;

public class MontaDieta extends Fragment {

    private static final String TAXA_BASAL_FINAL = "taxaBasalFinal";
    private static final String PROTEINA = "proteina";
    private static final String CARBOIDRATO = "carboidrato";
    private static final String QTD_REFEICOES = "qtdRefeicoes";
    private static final String PROTEINAS_REFEICAO = "protRefeicao";
    private static final String CARBOIDRATOS_REFEICAO = "carbRefeicao";
    private static final String CARBOIDRATOS_SELECIONADOS = "carbosSelecionados";
    private static final String PROTEINAS_SELECIONADAS = "proteinasSelecionadas";
    private static final String FRUTAS_SELECIONADAS = "frutasSelecionadas";
    private int qtdRefeicoes, protRefeicao, carbRefeicao;
    private List<Carboidratos> listaCarboidratosSelecionados;
    private List<Proteinas> listProteinasSelecionadas;
    private List<Frutas> listFrutasListSelecionadas;
    private final Random random = new Random();
    private final double gramasPorPorcao = 100.0;
    Utilitarios utils = new Utilitarios();

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
            listFrutasListSelecionadas = getArguments().getParcelableArrayList(FRUTAS_SELECIONADAS);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_monta_dieta, container, false);


        List<CarboidratosCalculados> listaDeCarbosPorRefeicao = escolherCarbos();
        List<ProteinasCalculadas> listaDeProteinasPorRefeicao = escolherProteinas();
        List<FrutasCalculadas> listaDeFrutasPorRefeicao = escolherFrutas();
        System.out.println("teste");

        return view;
    }

    private List<CarboidratosCalculados> escolherCarbos() {
        List<CarboidratosCalculados> carboidratosCalculadosList = new ArrayList<>();
        List<Carboidratos> carboidratosListFull = utils.createCarboList();
        Set<String> carbosSelecionados = new HashSet<>();

        int limiteRefeicoes = Math.min(qtdRefeicoes, listaCarboidratosSelecionados.size());

        for (int i = 0; i < limiteRefeicoes; i++) {
            String nomeCarbo;
            int indiceAleatorio;

            do {
                indiceAleatorio = random.nextInt(listaCarboidratosSelecionados.size());
                nomeCarbo = listaCarboidratosSelecionados.get(indiceAleatorio).getNome();
            } while (carbosSelecionados.contains(nomeCarbo));

            carbosSelecionados.add(nomeCarbo);

            for (Carboidratos carboidratos : carboidratosListFull) {
                if (nomeCarbo.equalsIgnoreCase(carboidratos.getNome())) {
                    CarboidratosCalculados calculados = new CarboidratosCalculados();
                    listaCarboidratosSelecionados.get(indiceAleatorio).setQtdCarboidratos(carboidratos.getQtdCarboidratos());
                    listaCarboidratosSelecionados.get(indiceAleatorio).setCalorias(carboidratos.getCalorias());

                    int qtdCarboPorPorcao = listaCarboidratosSelecionados.get(indiceAleatorio).getQtdCarboidratos();
                    double gramasCalculadas = calculaGramasPorRefeicao(carbRefeicao, qtdCarboPorPorcao);

                    calculados.setNome(listaCarboidratosSelecionados.get(indiceAleatorio).getNome());
                    calculados.setGramasPorRefeicao((int) gramasCalculadas);

                    carboidratosCalculadosList.add(calculados);
                }
            }
        }

        return carboidratosCalculadosList;
    }

    private List<FrutasCalculadas> escolherFrutas() {
        List<FrutasCalculadas> frutasCalculadosList = new ArrayList<>();
        List<Frutas> frutasListFull = utils.createFruitList();
        Set<String> frutasSelecionados = new HashSet<>();

        int limiteRefeicoes = Math.min(qtdRefeicoes, listFrutasListSelecionadas.size());

        for (int i = 0; i < limiteRefeicoes; i++) {
            String nomeFruta;
            int indiceAleatorio;

            do {
                indiceAleatorio = random.nextInt(listFrutasListSelecionadas.size());
                nomeFruta = listFrutasListSelecionadas.get(indiceAleatorio).getNome();
            } while (frutasSelecionados.contains(nomeFruta));

            frutasSelecionados.add(nomeFruta);

            for (Frutas frutas : frutasListFull) {
                if (nomeFruta.equalsIgnoreCase(frutas.getNome())) {
                    FrutasCalculadas calculados = new FrutasCalculadas();
                    listFrutasListSelecionadas.get(indiceAleatorio).setQtdCarboidratos(frutas.getQtdCarboidratos());
                    listFrutasListSelecionadas.get(indiceAleatorio).setCalorias(frutas.getCalorias());

                    int qtdCarboPorPorcao = listFrutasListSelecionadas.get(indiceAleatorio).getQtdCarboidratos();
                    double gramasCalculadas = calculaGramasPorRefeicao(carbRefeicao, qtdCarboPorPorcao);

                    calculados.setNome(listFrutasListSelecionadas.get(indiceAleatorio).getNome());
                    calculados.setGramasPorRefeicao((int) gramasCalculadas);

                    frutasCalculadosList.add(calculados);
                }
            }
        }

        return frutasCalculadosList;
    }

    private List<ProteinasCalculadas> escolherProteinas() {
        List<ProteinasCalculadas> proteinasCalculadosList = new ArrayList<>();
        List<Proteinas> proteinasListFull = utils.createProteinaList();
        Set<String> proteinasSelecionados = new HashSet<>();

        int limiteRefeicoes = Math.min(qtdRefeicoes, listProteinasSelecionadas.size());

        for (int i = 0; i < limiteRefeicoes; i++) {
            String nomeProteina;
            int indiceAleatorio;

            do {
                indiceAleatorio = random.nextInt(listProteinasSelecionadas.size());
                nomeProteina = listProteinasSelecionadas.get(indiceAleatorio).getNome();
            } while (proteinasSelecionados.contains(nomeProteina));

            proteinasSelecionados.add(nomeProteina);

            for (Proteinas proteinas : proteinasListFull) {
                if (nomeProteina.equalsIgnoreCase(proteinas.getNome())) {
                    ProteinasCalculadas calculados = new ProteinasCalculadas();
                    listProteinasSelecionadas.get(indiceAleatorio).setQtdProteinas(proteinas.getQtdProteinas());
                    listProteinasSelecionadas.get(indiceAleatorio).setCalorias(proteinas.getCalorias());

                    int qtdProteinasPorPorcao = listProteinasSelecionadas.get(indiceAleatorio).getQtdProteinas();
                    double gramasCalculadas = calculaGramasPorRefeicao(protRefeicao, qtdProteinasPorPorcao);

                    calculados.setNome(listProteinasSelecionadas.get(indiceAleatorio).getNome());
                    calculados.setGramasPorRefeicao((int) gramasCalculadas);

                    proteinasCalculadosList.add(calculados);
                }
            }
        }

        return proteinasCalculadosList;
    }

    private double calculaGramasPorRefeicao(double carbRefeicao, double qtdPorporcao) {
        return (carbRefeicao / qtdPorporcao) * 100.0;
    }

}