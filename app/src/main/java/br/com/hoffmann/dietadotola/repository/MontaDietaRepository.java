package br.com.hoffmann.dietadotola.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import br.com.hoffmann.dietadotola.domain.response.auxiliar.Carboidratos;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.CarboidratosCalculados;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.Frutas;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.FrutasCalculadas;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.Proteinas;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.ProteinasCalculadas;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.Vegetais;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.VegetaisCalculados;
import br.com.hoffmann.dietadotola.utils.Utilitarios;

public class MontaDietaRepository {

    private final Random random = new Random();
    Utilitarios utils = new Utilitarios();

    public LiveData<List<FrutasCalculadas>> escolherFrutas(List<Frutas> listFrutasSelecionadas) {
        MutableLiveData<List<FrutasCalculadas>> frutasLiveData = new MutableLiveData<>();
        List<FrutasCalculadas> frutasCalculadosList = new ArrayList<>();
        List<Frutas> frutasListFull = utils.createFruitList();

        for (Frutas frutas : frutasListFull) {
            int indiceAleatorio = random.nextInt(listFrutasSelecionadas.size());
            String nomeFruta = listFrutasSelecionadas.get(indiceAleatorio).getNome();

            if (nomeFruta.equalsIgnoreCase(frutas.getNome())) {
                FrutasCalculadas calculados = new FrutasCalculadas();

                listFrutasSelecionadas.get(indiceAleatorio).setQtdCarboidratos(frutas.getQtdCarboidratos());
                listFrutasSelecionadas.get(indiceAleatorio).setCalorias(frutas.getCalorias());

                calculados.setNome(listFrutasSelecionadas.get(indiceAleatorio).getNome());

                frutasCalculadosList.add(calculados);
            }
        }
        frutasLiveData.setValue(frutasCalculadosList);
        return frutasLiveData;
    }

    public LiveData<List<ProteinasCalculadas>> escolherProteinas(int qtdRefeicoes, int caloriasProteinaRefeicao, List<Proteinas> listProteinasSelecionadas) {
        MutableLiveData<List<ProteinasCalculadas>> proteinaLiveData = new MutableLiveData<>();
        List<ProteinasCalculadas> proteinasCalculadosList = new ArrayList<>();
        List<Proteinas> proteinasListFull = utils.createProteinaList();
        Set<String> proteinasSelecionados = new HashSet<>();

        if (qtdRefeicoes > listProteinasSelecionadas.size()) {
            for (int i = 0; i < qtdRefeicoes; i++) {
                int indiceAleatorio = random.nextInt(listProteinasSelecionadas.size());
                String nomeProteina = listProteinasSelecionadas.get(indiceAleatorio).getNome();

                for (Proteinas proteinas : proteinasListFull) {
                    if (nomeProteina.equalsIgnoreCase(proteinas.getNome())) {
                        ProteinasCalculadas calculados = new ProteinasCalculadas();

                        listProteinasSelecionadas.get(indiceAleatorio).setQtdProteinas(proteinas.getQtdProteinas());
                        listProteinasSelecionadas.get(indiceAleatorio).setCalorias(proteinas.getCalorias());

                        int qtdCaloriasPorPorcao = listProteinasSelecionadas.get(indiceAleatorio).getCalorias();
                        double gramasCalculadas = utils.calculaGramasPorRefeicao(caloriasProteinaRefeicao, qtdCaloriasPorPorcao);

                        calculados.setNome(listProteinasSelecionadas.get(indiceAleatorio).getNome());
                        calculados.setGramasPorRefeicao((int) gramasCalculadas);

                        proteinasCalculadosList.add(calculados);
                    }
                }
                proteinaLiveData.setValue(proteinasCalculadosList);
            }
        } else {
            for (int i = 0; i < qtdRefeicoes; i++) {
                int indiceAleatorio = random.nextInt(listProteinasSelecionadas.size());
                String nomeProteina = listProteinasSelecionadas.get(indiceAleatorio).getNome();

                if (proteinasSelecionados.contains(nomeProteina)) {
                    i--;
                    continue;
                } else {
                    for (Proteinas proteinas : proteinasListFull) {
                        if (nomeProteina.equalsIgnoreCase(proteinas.getNome())) {
                            ProteinasCalculadas calculados = new ProteinasCalculadas();

                            listProteinasSelecionadas.get(indiceAleatorio).setQtdProteinas(proteinas.getQtdProteinas());
                            listProteinasSelecionadas.get(indiceAleatorio).setCalorias(proteinas.getCalorias());

                            int qtdCaloriasPorPorcao = listProteinasSelecionadas.get(indiceAleatorio).getCalorias();
                            double gramasCalculadas = utils.calculaGramasPorRefeicao(caloriasProteinaRefeicao, qtdCaloriasPorPorcao);

                            calculados.setNome(listProteinasSelecionadas.get(indiceAleatorio).getNome());
                            calculados.setGramasPorRefeicao((int) gramasCalculadas);

                            proteinasCalculadosList.add(calculados);
                        }
                    }
                }
                proteinasSelecionados.add(nomeProteina);
            }
            proteinaLiveData.setValue(proteinasCalculadosList);
        }
        return proteinaLiveData;
    }

    public LiveData<List<CarboidratosCalculados>> escolherCarbos(int qtdRefeicoes, int caloriasCarboidratoPorRefeicao,
                                                                 List<Carboidratos> listaCarboidratosSelecionados,
                                                                 int gorduraRefeicao, List<VegetaisCalculados> listaFinalVegetais) {

        MutableLiveData<List<CarboidratosCalculados>> carbosLiveData = new MutableLiveData<>();
        List<CarboidratosCalculados> carboidratosCalculadosList = new ArrayList<>();
        List<Carboidratos> carboidratosListFull = utils.createCarboList();
        Set<String> nomesSelecionados = new HashSet<>();

        if (qtdRefeicoes > listaCarboidratosSelecionados.size()) {
            for (int i = 0; i < qtdRefeicoes; i++) {
                int indiceAleatorio = random.nextInt(listaCarboidratosSelecionados.size());
                String nomeCarbo = listaCarboidratosSelecionados.get(indiceAleatorio).getNome();
                int caloriasVegetal = listaFinalVegetais.get(indiceAleatorio).getCalorias();

                for (Carboidratos carboidratos : carboidratosListFull) {
                    if (nomeCarbo.equalsIgnoreCase(carboidratos.getNome())) {
                        CarboidratosCalculados calculados = new CarboidratosCalculados();

                        listaCarboidratosSelecionados.get(indiceAleatorio).setQtdCarboidratos(carboidratos.getQtdCarboidratos());
                        listaCarboidratosSelecionados.get(indiceAleatorio).setCalorias(carboidratos.getCalorias());

                        int qtdCaloriasPorPorcao = listaCarboidratosSelecionados.get(indiceAleatorio).getCalorias();
                        double gramasCalculadas = utils.calculaGramasPorRefeicao(caloriasCarboidratoPorRefeicao, qtdCaloriasPorPorcao);

                        calculados.setNome(listaCarboidratosSelecionados.get(indiceAleatorio).getNome());

                        calculados.setGramasPorRefeicao((int) gramasCalculadas - gorduraRefeicao - caloriasVegetal);

                        carboidratosCalculadosList.add(calculados);
                    }
                }
                carbosLiveData.setValue(carboidratosCalculadosList);
            }
        } else {
            for (int i = 0; i < qtdRefeicoes; i++) {
                int indiceAleatorio = random.nextInt(listaCarboidratosSelecionados.size());
                String nomeCarbo = listaCarboidratosSelecionados.get(indiceAleatorio).getNome();

                if (nomesSelecionados.contains(nomeCarbo)) {
                    i--;
                    continue;
                } else {
                    for (Carboidratos carboidratos : carboidratosListFull) {
                        if (nomeCarbo.equalsIgnoreCase(carboidratos.getNome())) {

                            for (VegetaisCalculados vegetaisCalculados : listaFinalVegetais) {

                                CarboidratosCalculados calculados = new CarboidratosCalculados();

                                listaCarboidratosSelecionados.get(indiceAleatorio).setQtdCarboidratos(carboidratos.getQtdCarboidratos());
                                listaCarboidratosSelecionados.get(indiceAleatorio).setCalorias(carboidratos.getCalorias());

                                int qtdCaloriasPorPorcao = listaCarboidratosSelecionados.get(indiceAleatorio).getCalorias();
                                double gramasCalculadas = utils.calculaGramasPorRefeicao(caloriasCarboidratoPorRefeicao, qtdCaloriasPorPorcao);

                                calculados.setNome(listaCarboidratosSelecionados.get(indiceAleatorio).getNome());
                                calculados.setGramasPorRefeicao((int) gramasCalculadas - gorduraRefeicao - vegetaisCalculados.getCalorias());

                                carboidratosCalculadosList.add(calculados);
                            }
                        }
                    }
                }
                nomesSelecionados.add(nomeCarbo);
            }
            carbosLiveData.setValue(carboidratosCalculadosList);
        }
        return carbosLiveData;
    }

    public LiveData<List<VegetaisCalculados>> escolherVegetais(int qtdRefeicoes, int carboidratoRefeicao, List<Vegetais> listaVegetaisSelecionados) {
        MutableLiveData<List<VegetaisCalculados>> vegetaisLiveData = new MutableLiveData<>();
        List<VegetaisCalculados> VegetaisCalculadosList = new ArrayList<>();
        List<Vegetais> vegetaisListFull = utils.createVegetaisList();
        Set<String> nomesSelecionados = new HashSet<>();

        if (qtdRefeicoes > listaVegetaisSelecionados.size()) {
            for (int i = 0; i < qtdRefeicoes; i++) {
                int indiceAleatorio = random.nextInt(listaVegetaisSelecionados.size());
                String nomeVegetal = listaVegetaisSelecionados.get(indiceAleatorio).getNome();

                for (Vegetais vegetais : vegetaisListFull) {
                    if (nomeVegetal.equalsIgnoreCase(vegetais.getNome())) {
                        VegetaisCalculados calculados = new VegetaisCalculados();

                        listaVegetaisSelecionados.get(indiceAleatorio).setQtdCarboidratos(vegetais.getQtdCarboidratos());
                        listaVegetaisSelecionados.get(indiceAleatorio).setCalorias(vegetais.getCalorias());

                        int qtdVegetalPorPorcao = listaVegetaisSelecionados.get(indiceAleatorio).getQtdCarboidratos();
                        double gramasCalculadas = utils.calculaGramasPorRefeicao(carboidratoRefeicao, qtdVegetalPorPorcao);

                        calculados.setNome(listaVegetaisSelecionados.get(indiceAleatorio).getNome());
                        calculados.setGramasPorRefeicao((int) gramasCalculadas);

                        VegetaisCalculadosList.add(calculados);
                    }
                }
                vegetaisLiveData.setValue(VegetaisCalculadosList);
            }
        } else {
            for (int i = 0; i < qtdRefeicoes; i++) {
                int indiceAleatorio = random.nextInt(listaVegetaisSelecionados.size());
                String nomeVegetal = listaVegetaisSelecionados.get(indiceAleatorio).getNome();

                if (nomesSelecionados.contains(nomeVegetal)) {
                    i--;
                    continue;
                } else {
                    for (Vegetais vegetais : vegetaisListFull) {
                        if (nomeVegetal.equalsIgnoreCase(vegetais.getNome())) {
                            VegetaisCalculados calculados = new VegetaisCalculados();

                            listaVegetaisSelecionados.get(indiceAleatorio).setQtdCarboidratos(vegetais.getQtdCarboidratos());
                            listaVegetaisSelecionados.get(indiceAleatorio).setCalorias(vegetais.getCalorias());

                            int qtdVegetalPorPorcao = listaVegetaisSelecionados.get(indiceAleatorio).getQtdCarboidratos();
                            double gramasCalculadas = utils.calculaGramasPorRefeicao(carboidratoRefeicao, qtdVegetalPorPorcao);

                            calculados.setNome(listaVegetaisSelecionados.get(indiceAleatorio).getNome());
                            calculados.setGramasPorRefeicao((int) gramasCalculadas);

                            VegetaisCalculadosList.add(calculados);
                        }
                    }
                }
                nomesSelecionados.add(nomeVegetal);
            }
            vegetaisLiveData.setValue(VegetaisCalculadosList);
        }
        return vegetaisLiveData;
    }

}
