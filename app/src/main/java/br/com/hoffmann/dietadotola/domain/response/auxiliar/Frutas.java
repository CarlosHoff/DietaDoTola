package br.com.hoffmann.dietadotola.domain.response.auxiliar;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Frutas implements Parcelable {

    private String nome;
    private int calorias;
    private int qtdCarboidratos;

    public Frutas() {
    }

    public Frutas(String nome, int calorias, int qtdCarboidratos) {
        this.nome = nome;
        this.calorias = calorias;
        this.qtdCarboidratos = qtdCarboidratos;
    }

    protected Frutas(Parcel in) {
        nome = in.readString();
        calorias = in.readInt();
        qtdCarboidratos = in.readInt();
    }

    public static final Creator<Frutas> CREATOR = new Creator<Frutas>() {
        @Override
        public Frutas createFromParcel(Parcel in) {
            return new Frutas(in);
        }

        @Override
        public Frutas[] newArray(int size) {
            return new Frutas[size];
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(nome);
        dest.writeInt(calorias);
        dest.writeInt(qtdCarboidratos);
    }
}
