package br.com.hoffmann.dietadotola.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import br.com.hoffmann.dietadotola.domain.response.auxiliar.Carboidratos;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.CarboidratosCalculados;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.Proteinas;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.ProteinasCalculadas;
import br.com.hoffmann.dietadotola.repository.MontaDietaRepository;
import br.com.hoffmann.dietadotola.utils.Utilitarios;

public class MontaDietaViewModel extends ViewModel {
    private final Random random = new Random();
    Utilitarios utils = new Utilitarios();

    private MontaDietaRepository montaDietaRepository;
    public MontaDietaViewModel() {
        montaDietaRepository = new MontaDietaRepository();
    }

    private MutableLiveData<List<CarboidratosCalculados>> listaDeCarbosPorRefeicao = new MutableLiveData<>();
    private MutableLiveData<List<ProteinasCalculadas>> listaDeProteinasPorRefeicao = new MutableLiveData<>();

    public void iniciarMontagemDieta(int qtdRefeicoes, int protRefeicao, int carbRefeicao,
            List<Carboidratos> listaCarboidratosSelecionados,
            List<Proteinas> listProteinasSelecionadas) {
        List<CarboidratosCalculados> carboidratosCalculadosList = escolherCarbos(qtdRefeicoes, carbRefeicao, listaCarboidratosSelecionados);
        List<ProteinasCalculadas> proteinasCalculadasList = escolherProteinas(qtdRefeicoes, protRefeicao, listProteinasSelecionadas);

        listaDeCarbosPorRefeicao.setValue(carboidratosCalculadosList);
        listaDeProteinasPorRefeicao.setValue(proteinasCalculadasList);
    }

    public List<CarboidratosCalculados> escolherCarbos(int qtdRefeicoes, int carbRefeicao, List<Carboidratos> listaCarboidratosSelecionados) {
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
                    double gramasCalculadas = utils.calculaGramasPorRefeicao(carbRefeicao, qtdCarboPorPorcao);

                    calculados.setNome(listaCarboidratosSelecionados.get(indiceAleatorio).getNome());
                    calculados.setGramasPorRefeicao((int) gramasCalculadas);

                    carboidratosCalculadosList.add(calculados);
                }
            }
        }

        return carboidratosCalculadosList;
    }

    private List<ProteinasCalculadas> escolherProteinas(int qtdRefeicoes, int protRefeicao, List<Proteinas> listProteinasSelecionadas) {
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
                    double gramasCalculadas = utils.calculaGramasPorRefeicao(protRefeicao, qtdProteinasPorPorcao);

                    calculados.setNome(listProteinasSelecionadas.get(indiceAleatorio).getNome());
                    calculados.setGramasPorRefeicao((int) gramasCalculadas);

                    proteinasCalculadosList.add(calculados);
                }
            }
        }

        return proteinasCalculadosList;
    }

    public LiveData<List<CarboidratosCalculados>> getListaDeCarbosPorRefeicao(int qtdRefeicoes, int carbRefeicao, List<Carboidratos> listaCarboidratosSelecionados) {
        return montaDietaRepository.escolherCarbos(qtdRefeicoes, carbRefeicao, listaCarboidratosSelecionados);
    }

    public LiveData<List<ProteinasCalculadas>> getListaDeProteinasPorRefeicao(int qtdRefeicoes, int protRefeicao, List<Proteinas> listProteinasSelecionadas) {
        return montaDietaRepository.escolherProteinas(qtdRefeicoes, protRefeicao, listProteinasSelecionadas);
    }

}
