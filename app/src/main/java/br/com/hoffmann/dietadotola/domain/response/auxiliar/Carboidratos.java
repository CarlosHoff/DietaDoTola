package br.com.hoffmann.dietadotola.domain.response.auxiliar;

import java.io.Serializable;

public class Carboidratos implements Serializable {

    private String nome;
    private int calorias;
    private int qtdCarboidratos;

    public Carboidratos() {
    }

    public Carboidratos(String nome, int calorias, int qtdCarboidratos) {
        this.nome = nome;
        this.calorias = calorias;
        this.qtdCarboidratos = qtdCarboidratos;
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

    public int getQtdCarboidratos() {
        return qtdCarboidratos;
    }

    public void setQtdCarboidratos(int qtdCarboidratos) {
        this.qtdCarboidratos = qtdCarboidratos;
    }
}
