package br.com.hoffmann.dietadotola.domain.response.auxiliar;

import java.io.Serializable;

public class VegetaisCalculados implements Serializable {
    private String nome;
    private int gramasPorRefeicao;
    private int calorias;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getGramasPorRefeicao() {
        return gramasPorRefeicao;
    }

    public void setGramasPorRefeicao(int gramasPorRefeicao) {
        this.gramasPorRefeicao = gramasPorRefeicao;
    }

    public int getCalorias() {
        return calorias;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }
}
