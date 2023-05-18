package br.com.hoffmann.dietadotola.domain.response.auxiliar;

import java.io.Serializable;

public class Proteinas implements Serializable {

    private String nome;
    private int calorias;
    private int qtdProteinas;

    public Proteinas() {
    }

    public Proteinas(String nome, int calorias, int qtdProteinas) {
        this.nome = nome;
        this.calorias = calorias;
        this.qtdProteinas = qtdProteinas;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getCalorias() {
        return calorias;
    }

    public void setCalorias(int calorias) {
        this.calorias = calorias;
    }

    public int getQtdProteinas() {
        return qtdProteinas;
    }

    public void setQtdProteinas(int qtdProteinas) {
        this.qtdProteinas = qtdProteinas;
    }
}
