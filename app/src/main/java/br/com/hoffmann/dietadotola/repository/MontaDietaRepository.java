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
import br.com.hoffmann.dietadotola.domain.response.auxiliar.Proteinas;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.ProteinasCalculadas;
import br.com.hoffmann.dietadotola.utils.Utilitarios;

public class MontaDietaRepository {

    private final Random random = new Random();
    Utilitarios utils = new Utilitarios();

    public LiveData<List<CarboidratosCalculados>> escolherCarbos(int qtdRefeicoes, int carbRefeicao, List<Carboidratos> listaCarboidratosSelecionados) {
        MutableLiveData<List<CarboidratosCalculados>> carbosLiveData = new MutableLiveData<>();
        List<CarboidratosCalculados> carboidratosCalculadosList = new ArrayList<>();
        List<Carboidratos> carboidratosListFull = utils.createCarboList();
        Set<String> nomesSelecionados = new HashSet<>();

        if (qtdRefeicoes > listaCarboidratosSelecionados.size()) {
            for (int i = 0; i < qtdRefeicoes; i++) {
                int indiceAleatorio = random.nextInt(listaCarboidratosSelecionados.size());
                String nomeCarbo = listaCarboidratosSelecionados.get(indiceAleatorio).getNome();

                for (Carboidratos carboidratos : carboidratosListFull) {
                    if (nomeCarbo.equalsIgnoreCase(carboidratos.getNome())) {
                        CarboidratosCalculados calculados = new CarboidratosCalculados();

                        listaCarboidratosSelecionados.get(indiceAleatorio).setQtdCarboidratos(carboidratos.getQtdCarboidratos());
                        listaCarboidratosSelecionados.get(indiceAleatorio).setCalorias(carboidratos.getCalorias());

                        int qtdCarboPorPorcao = listaCarboidratosSelecionados.get(indiceAleatorio).getQtdCarboidratos();
                        double gramasCalculadas = utils.calculaGramasPorRefeicao(carbRefeicao, qtdCarboPorPorcao);

                        calculados.setNome(listaCarboidratosSelecionados.get(indiceAleatorio).getNome());
                        calculados.setGramasPorRefeicao((int) gramasCalculadas);

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
                            CarboidratosCalculados calculados = new CarboidratosCalculados();

                            listaCarboidratosSelecionados.get(indiceAleatorio).setQtdCarboidratos(carboidratos.getQtdCarboidratos());
                            listaCarboidratosSelecionados.get(indiceAleatorio).setCalorias(carboidratos.getCalorias());

                            int qtdCarboPorPorcao = listaCarboidratosSelecionados.get(indiceAleatorio).getQtdCarboidratos();
                            double gramasCalculadas = utils.calculaGramasPorRefeicao(carbRefeicao, qtdCarboPorPorcao);

                            calculados.setNome(listaCarboidratosSelecionados.get(indiceAleatorio).getNome());
                            calculados.setGramasPorRefeicao((int) gramasCalculadas);

                            carboidratosCalculadosList.add(calculados);
                        }
                    }
                }
                nomesSelecionados.add(nomeCarbo);
            }
            carbosLiveData.setValue(carboidratosCalculadosList);
        }
        return carbosLiveData;
    }

    public LiveData<List<ProteinasCalculadas>> escolherProteinas(int qtdRefeicoes, int protRefeicao, List<Proteinas> listProteinasSelecionadas) {
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
                        ProteinasCalculadas proteinasCalculadas = new ProteinasCalculadas();

                        listProteinasSelecionadas.get(indiceAleatorio).setQtdProteinas(proteinas.getQtdProteinas());
                        listProteinasSelecionadas.get(indiceAleatorio).setCalorias(proteinas.getCalorias());

                        int qtdCarboPorPorcao = listProteinasSelecionadas.get(indiceAleatorio).getQtdProteinas();
                        double gramasCalculadas = utils.calculaGramasPorRefeicao(protRefeicao, qtdCarboPorPorcao);

                        proteinasCalculadas.setNome(listProteinasSelecionadas.get(indiceAleatorio).getNome());
                        proteinasCalculadas.setGramasPorRefeicao((int) gramasCalculadas);

                        proteinasCalculadosList.add(proteinasCalculadas);
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
                            ProteinasCalculadas proteinasCalculadas = new ProteinasCalculadas();

                            listProteinasSelecionadas.get(indiceAleatorio).setQtdProteinas(proteinas.getQtdProteinas());
                            listProteinasSelecionadas.get(indiceAleatorio).setCalorias(proteinas.getCalorias());

                            int qtdProteinasPorPorcao = listProteinasSelecionadas.get(indiceAleatorio).getQtdProteinas();
                            double gramasCalculadas = utils.calculaGramasPorRefeicao(protRefeicao, qtdProteinasPorPorcao);

                            proteinasCalculadas.setNome(listProteinasSelecionadas.get(indiceAleatorio).getNome());
                            proteinasCalculadas.setGramasPorRefeicao((int) gramasCalculadas);

                            proteinasCalculadosList.add(proteinasCalculadas);
                        }
                    }
                }
                proteinasSelecionados.add(nomeProteina);
            }
            proteinaLiveData.setValue(proteinasCalculadosList);
        }
        return proteinaLiveData;
    }
}
