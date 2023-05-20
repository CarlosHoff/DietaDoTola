package br.com.hoffmann.dietadotola.domain.response.auxiliar;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Carboidratos implements Parcelable {

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

    protected Carboidratos(Parcel in) {
        nome = in.readString();
        calorias = in.readInt();
        qtdCarboidratos = in.readInt();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeInt(calorias);
        dest.writeInt(qtdCarboidratos);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Carboidratos> CREATOR = new Creator<Carboidratos>() {
        @Override
        public Carboidratos createFromParcel(Parcel in) {
            return new Carboidratos(in);
        }

        @Override
        public Carboidratos[] newArray(int size) {
            return new Carboidratos[size];
        }
    };

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
