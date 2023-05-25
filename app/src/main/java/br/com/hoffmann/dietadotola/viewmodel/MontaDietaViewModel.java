package br.com.hoffmann.dietadotola.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import br.com.hoffmann.dietadotola.domain.response.auxiliar.Carboidratos;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.CarboidratosCalculados;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.Frutas;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.FrutasCalculadas;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.Proteinas;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.ProteinasCalculadas;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.Vegetais;
import br.com.hoffmann.dietadotola.domain.response.auxiliar.VegetaisCalculados;
import br.com.hoffmann.dietadotola.repository.MontaDietaRepository;

public class MontaDietaViewModel extends ViewModel {
    private final MontaDietaRepository montaDietaRepository;
    public MontaDietaViewModel() {
        montaDietaRepository = new MontaDietaRepository();
    }
    private final MutableLiveData<List<CarboidratosCalculados>> listaDeCarbosPorRefeicao = new MutableLiveData<>();
    private final MutableLiveData<List<ProteinasCalculadas>> listaDeProteinasPorRefeicao = new MutableLiveData<>();
    private final MutableLiveData<List<ProteinasCalculadas>> listaDeFrutasPorRefeicao = new MutableLiveData<>();
    private final MutableLiveData<List<ProteinasCalculadas>> listaDeVegetaisPorRefeicao = new MutableLiveData<>();

    public void iniciarMontagemDieta() {

        listaDeCarbosPorRefeicao.setValue(new ArrayList<>());
        listaDeProteinasPorRefeicao.setValue(new ArrayList<>());
        listaDeFrutasPorRefeicao.setValue(new ArrayList<>());
        listaDeVegetaisPorRefeicao.setValue(new ArrayList<>());
    }

    public LiveData<List<CarboidratosCalculados>> getListaDeCarbosPorRefeicao(int qtdRefeicoes, int carbRefeicao, List<Carboidratos> listaCarboidratosSelecionados, int gorduraRefeicao, List<VegetaisCalculados> listaFinalVegetais) {
        return montaDietaRepository.escolherCarbos(qtdRefeicoes, carbRefeicao, listaCarboidratosSelecionados, gorduraRefeicao, listaFinalVegetais);
    }

    public LiveData<List<ProteinasCalculadas>> getListaDeProteinasPorRefeicao(int qtdRefeicoes, int protRefeicao, List<Proteinas> listProteinasSelecionadas) {
        return montaDietaRepository.escolherProteinas(qtdRefeicoes, protRefeicao, listProteinasSelecionadas);
    }

    public LiveData<List<FrutasCalculadas>> getListaDeFrutasPorRefeicao(List<Frutas> listFrutasSelecionadas) {
        return montaDietaRepository.escolherFrutas(listFrutasSelecionadas);
    }

    public LiveData<List<VegetaisCalculados>> getListaDeVegetaisPorRefeicao(int qtdRefeicoes, int carboidratoRefeicao, List<Vegetais> listVegetaisSelecionadas) {
        return montaDietaRepository.escolherVegetais(qtdRefeicoes, carboidratoRefeicao, listVegetaisSelecionadas);
    }

}
