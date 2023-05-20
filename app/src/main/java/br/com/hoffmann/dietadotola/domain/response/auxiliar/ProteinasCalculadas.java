package br.com.hoffmann.dietadotola.domain.response.auxiliar;

import java.io.Serializable;

public class ProteinasCalculadas implements Serializable {

    private String nome;
    private int gramasPorRefeicao;

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
}
